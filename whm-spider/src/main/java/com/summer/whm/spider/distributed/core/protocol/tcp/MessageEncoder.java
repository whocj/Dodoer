package com.summer.whm.spider.distributed.core.protocol.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.IOException;
import java.io.ObjectOutput;

import com.summer.whm.spider.distributed.core.DistributedException;
import com.summer.whm.spider.distributed.core.Invocation;
import com.summer.whm.spider.distributed.core.Result;
import com.summer.whm.spider.distributed.core.model.Serialization;
import com.summer.whm.spider.distributed.core.serialization.Serializer;
import com.summer.whm.spider.distributed.core.serialization.SerializerFactory;

public class MessageEncoder extends MessageToByteEncoder<Object> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws IOException, DistributedException {
        if (msg instanceof InvocationMsg) {
            String serialization = ((InvocationMsg) msg).invocation.getAttachment(TcpConstants.SERIALIZATION);
            encodeInvocation(ctx, Serialization.valueOf(serialization), (InvocationMsg) msg, out);
        } else if (msg instanceof Result) {
            String serialization = ((Result) msg).getAttachment(TcpConstants.SERIALIZATION);
            encodeResult(ctx, Serialization.valueOf(serialization), (Result) msg, out);
        } else if (msg instanceof Heartbeat) {
            encodeHeartbeat(ctx, (Heartbeat) msg, out);
        } else {
            throw new DistributedException("invalid data type: " + msg.getClass().getName());
        }
    }

    private byte getInvocationMsgCode(Serialization serialization) {
        byte msgCode;
        switch (serialization) {
            case HESSIAN:
                msgCode = TcpConstants.MSG_CODE_INVOCATION_HESSIAN;
                break;
            case KRYO:
                msgCode = TcpConstants.MSG_CODE_INVOCATION_KRYO;
                break;
            case JSON:
                msgCode = TcpConstants.MSG_CODE_INVOCATION_JSON;
                break;
            default:
                throw new DistributedException("Invalid serialization:" + serialization);
        }
        return msgCode;
    }

    private void encodeInvocation(ChannelHandlerContext ctx, Serialization serialization,
                                  InvocationMsg invocationMsg, ByteBuf out) throws IOException {
        out.writeInt(0);
        out.writeByte(getInvocationMsgCode(serialization));
        Serializer serializer = SerializerFactory.getSerializer(serialization);
        serializer.serialize(new ByteBufOutputStream(out), invocationMsg,
                new Serializer.SerOperator<InvocationMsg>() {
                    @Override
                    public void operate(ObjectOutput objectOutput,
                                        InvocationMsg msg) throws IOException {
                        Invocation invocation = msg.invocation;
                        objectOutput.writeUTF(invocation.getId());
                        objectOutput.writeInt(invocation.getRetryTimes());
                        objectOutput.writeBoolean(invocation.isIdempotent());
                        objectOutput.writeLong(invocation.getRequestTimeout());
                        objectOutput.writeInt(msg.index);
                        objectOutput.writeUTF(invocation.getContract());
                        objectOutput.writeUTF(invocation.getImplCode());
                        objectOutput.writeUTF(invocation.getMethodName());
                        StringBuilder sb = new StringBuilder();
                        Class[] parameterTypes = invocation.makeParameterTypes();
                        for (int i = 0; i < parameterTypes.length; i++) {
                            if (i > 0) {
                                sb.append(",");
                            }
                            sb.append(parameterTypes[i].getName());
                        }
                        objectOutput.writeUTF(sb.toString());
                        for (Object parameter : invocation.getArguments()) {
                            objectOutput.writeObject(parameter);
                        }
                        objectOutput.writeObject(invocation.getAttachments());
                    }
                }
        );
        out.setInt(0, out.writerIndex() - TcpConstants.MSG_LENGTH_SIZEINBYTE);
    }

    private byte getResultMsgCode(Serialization serialization) {
        byte msgCode;
        switch (serialization) {
            case HESSIAN:
                msgCode = TcpConstants.MSG_CODE_RESULT_HESSIAN;
                break;
            case KRYO:
                msgCode = TcpConstants.MSG_CODE_RESULT_KRYO;
                break;
            case JSON:
                msgCode = TcpConstants.MSG_CODE_RESULT_JSON;
                break;
            default:
                throw new DistributedException("Invalid serialization:" + serialization);
        }
        return msgCode;
    }

    private void encodeResult(ChannelHandlerContext ctx, Serialization serialization,
                              Result result, ByteBuf out) throws IOException {
        out.writeInt(0);
        out.writeByte(getResultMsgCode(serialization));
        Serializer serializer = SerializerFactory.getSerializer(serialization);
        serializer.serialize(new ByteBufOutputStream(out), result,
                new Serializer.SerOperator<Result>() {
                    @Override
                    public void operate(ObjectOutput objectOutput, Result result) throws IOException {
                        objectOutput.writeUTF(result.getId());
                        objectOutput.writeInt(result.getIndex());
                        objectOutput.writeObject(result.getValue());
                        objectOutput.writeObject(result.getException());
                        objectOutput.writeObject(result.getAttachments());
                    }
                }
        );
        out.setInt(0, out.writerIndex() - TcpConstants.MSG_LENGTH_SIZEINBYTE);
    }

    private void encodeHeartbeat(ChannelHandlerContext ctx, Heartbeat heartbeat, ByteBuf out) {
        out.writeInt(0);
        out.writeByte(TcpConstants.MSG_CODE_HEARTBEAT);
        out.writeByte(heartbeat.getType());
        out.setInt(0, out.writerIndex() - TcpConstants.MSG_LENGTH_SIZEINBYTE);
    }
}

