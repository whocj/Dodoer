package com.summer.whm.spider.distributed.core.protocol.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.http.HttpContentEncoder.Result;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.codehaus.jackson.map.JsonSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.summer.whm.spider.distributed.core.DistributedException;
import com.summer.whm.spider.distributed.core.InvocationImpl;
import com.summer.whm.spider.distributed.core.ResultImpl;
import com.summer.whm.spider.distributed.core.serialization.ObjectInput;
import com.summer.whm.spider.distributed.core.serialization.Serializer;
import com.summer.whm.spider.distributed.utils.ClassUtils;
import com.summer.whm.spider.distributed.utils.StringUtils;

public class MessageDecoder extends LengthFieldBasedFrameDecoder {
    private static Logger logger = LoggerFactory.getLogger(MessageDecoder.class);

    public MessageDecoder() {
        super(TcpConstants.MAX_MSG_SIZE, 0, TcpConstants.MSG_LENGTH_SIZEINBYTE,
                0, TcpConstants.MSG_LENGTH_SIZEINBYTE);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        ByteBuf byteBuf = (ByteBuf) super.decode(ctx, in);
        if (byteBuf == null) {
            return null;
        }
        byte code = byteBuf.readByte();
        Object decodedObject = null;
        try {
            switch (code) {
                case TcpConstants.MSG_CODE_HEARTBEAT:
                    decodedObject = decodeHeartbeat(byteBuf);
                    break;
                default:
                    fail(byteBuf, ctx);
            }
        } finally {
            byteBuf.release();
        }
        return decodedObject;
    }

    @SuppressWarnings("unchecked")
    private InvocationMsg decodeInvocation(final Serializer serializer, ChannelHandlerContext ctx,
                                           ByteBuf byteBuf) throws IOException {
        final StringBuilder invocationId = new StringBuilder();
        final AtomicInteger index = new AtomicInteger(-1);
        try {
            return (InvocationMsg) serializer.deserialize(
                    new ByteBufInputStream(byteBuf),
                    new Serializer.DeserOperator<Object>() {
                        @Override
                        public Object operate(ObjectInput objectInput)
                                throws IOException, ClassNotFoundException {
                            invocationId.append(objectInput.readUTF());
                            int retryTimes = objectInput.readInt();
                            boolean idempotent = objectInput.readBoolean();
                            long timeout = objectInput.readLong();
                            index.set(objectInput.readInt());
                            String contract = objectInput.readUTF();
                            String implCode = objectInput.readUTF();
                            String methodName = objectInput.readUTF();
                            String parameterTypeNames = objectInput.readUTF();
                            int parameterCount;
                            Class<?>[] parameterTypes;
                            if (parameterTypeNames.length() > 0) {
                                String[] parameterNameArr = parameterTypeNames.split(",");
                                parameterCount = parameterNameArr.length;
                                parameterTypes = new Class<?>[parameterNameArr.length];
                                for (int i = 0; i < parameterNameArr.length; i++) {
                                    String parameterName = parameterNameArr[i];
                                    parameterTypes[i] = ClassUtils.forName(parameterName);
                                }
                            } else {
                                parameterCount = 0;
                                parameterTypes = new Class<?>[0];
                            }
                            Object[] parameters = new Object[parameterCount];
                            for (int i = 0; i < parameterCount; i++) {
                                parameters[i] = objectInput.readObject();
                            }
                            Map<String, String> attachments = (Map<String, String>) objectInput.readObject();
                            if (objectInput.available() != 0) {
                                throw new IOException("Invalid message.");
                            }
                            InvocationImpl invocation = new InvocationImpl(invocationId.toString(),
                                    contract, implCode, methodName,
                                    parameterTypes, parameters, attachments);
                            invocation.setRetryTimes(retryTimes);
                            invocation.setIdempotent(idempotent);
                            invocation.setRequestTimeout(timeout);
                            invocation.setAttachment(TcpConstants.SERIALIZATION,
                                    serializer.getSerialization().name());
                            return new InvocationMsg(invocation, index.get());
                        }
                    }
            );
        } catch (Throwable ex) {
            logger.error("Decode invocation error.", ex);
            if (!StringUtils.isEmpty(invocationId.toString()) && index.get() != -1) {
                ResultImpl result = new ResultImpl(invocationId.toString(),
                        (ex instanceof DistributedException) ?
                                ex : new DistributedException(ex)
                );
                result.setAttachment(TcpConstants.SERIALIZATION, serializer.getSerialization().name());
                result.setIndex(index.get());
                ctx.writeAndFlush(result);
            }
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    private Result decodeResult(Serializer serializer, ChannelHandlerContext ctx,
                                ByteBuf byteBuf) throws IOException, ClassNotFoundException {
        try {
            return (Result) serializer.deserialize(new ByteBufInputStream(byteBuf),
                    new Serializer.DeserOperator<Object>() {
                        @Override
                        public Object operate(ObjectInput objectInput)
                                throws IOException, ClassNotFoundException {
                            String id = objectInput.readUTF();
                            int index = objectInput.readInt();
                            Object obj = objectInput.readObject();
                            Throwable exp = (Throwable) objectInput.readObject();
                            Map<String, String> attachments = (Map<String, String>) objectInput.readObject();
                            if (objectInput.available() != 0) {
                                throw new IOException("Invalid message.");
                            }
                            ResultImpl result = new ResultImpl(id, obj, exp);
                            result.setIndex(index);
                            result.setAttachments(attachments);
                            return result;
                        }
                    }
            );
        } catch (Throwable ex) {
            logger.error("Decode result error.", ex);
            return null;
        }
    }

    private Heartbeat decodeHeartbeat(ByteBuf byteBuf) {
        return byteBuf.readByte() == Heartbeat.TYPE_REQUEST ? Heartbeat.REQUEST : Heartbeat.RESPONSE;
    }

    private void fail(ByteBuf byteBuf, ChannelHandlerContext ctx) {
        try {
            logger.error("illegal request from [" + ctx.channel() + "]: "
                    + new String(byteBuf.array(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            //do nothing
        }
        throw new DistributedException("illegal request from " + ctx.channel());
    }
}
