package com.summer.whm.ssh.web;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.summer.whm.ssh.command.AbstractCommand;
import com.summer.whm.ssh.command.CommandContext;
import com.summer.whm.ssh.command.core.TailCommand;
import com.summer.whm.ssh.command.core.parser.ParserFactory;
import com.summer.whm.ssh.command.core.thread.ThreadOperate;
import com.summer.whm.ssh.command.sys.SysFactory;
import com.summer.whm.ssh.command.utils.file.FileUtils;
import com.summer.whm.ssh.ws.WSModel;

@ServerEndpoint(value = "/ssh/{nickname}")
public class SSHWebSocket {
    // 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    private static final String EXIT = "exit";
    
    private static final String OS = "@os";

    // concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static Map<String, WSModel> webSocketMap = new ConcurrentHashMap<String, WSModel>();

    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
     * 连接建立成功调用的方法
     * 
     * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(@PathParam("nickname") String nickname, Session session) {
        this.session = session;
        webSocketMap.put(nickname, new WSModel(nickname, this, CommandContext.getDefaultHtmlContext())); // 加入set中
        addOnlineCount(); // 在线数加1
        System.out.println(nickname + "加入！当前在线人数为" + getOnlineCount());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(@PathParam("nickname") String nickname) {
        webSocketMap.remove(nickname); // 从set中删除
        subOnlineCount(); // 在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     * 
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(@PathParam("nickname") String nickname, String message, Session session) {
        System.out.println("来自客户端的消息:" + message);

        WSModel wsModel = webSocketMap.get(nickname);
        try {
            AbstractCommand command = null;
            if (!message.startsWith("@")) {
                
                if(EXIT.equals(message)){
                    wsModel.stopThread();
                    wsModel.getWebSocket().sendMessage("监听停止.");
                }else{
                    command = ParserFactory.build(message, wsModel.getContext());
                    if (command instanceof TailCommand) {
                        TailCommand tailCommand = (TailCommand) command;
                        BlockingQueue<String> queue = tailCommand.tail();
                        TailThread tailThread = new TailThread(wsModel, queue);
                        wsModel.setThreadOperate(tailThread);
                        new Thread(tailThread).start();
                    } else {
                        wsModel.getWebSocket().sendMessage(command.doTask(null));
                    }
                }
            } else {
                if(OS.equals(message)){
                    command = SysFactory.build(message, wsModel.getContext());
                    wsModel.getWebSocket().sendMessage(command.doTask(null));
                }else{
                    command = SysFactory.build(message, wsModel.getContext());
                    wsModel.getWebSocket().sendMessage(command.doTask(null));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                wsModel.getWebSocket().sendMessage(message + "命令错误." + e.getMessage() + "</br>");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 发生错误时调用
     * 
     * @param session
     * @param error
     */
    @OnError
    public void onError(@PathParam("nickname") String nickname, Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     * 
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        SSHWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        SSHWebSocket.onlineCount--;
    }

    class TailThread implements Runnable,ThreadOperate {
        private boolean isEnd = false;

        private BlockingQueue<String> queue;

        private WSModel wsModel;

        public TailThread(WSModel wsModel, BlockingQueue<String> queue) {
            super();
            this.queue = queue;
            this.wsModel = wsModel;
        }

        public void stop() {
            isEnd = true;
        }

        @Override
        public void run() {
            synchronized (this) {
                String temp = null;
                try {
                    while (!isEnd) {
                        temp = queue.take();
                        if (isEnd) {
                            return;
                        }

                        wsModel.getWebSocket().sendMessage(temp);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
