package com.summer.whm.web.controller.chats;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.summer.whm.entiry.chats.ChatsFriendMessage;
import com.summer.whm.entiry.chats.ChatsKeys;
import com.summer.whm.service.chats.ChatsFriendMessageService;
import com.summer.whm.service.chats.ChatsFriendService;
import com.summer.whm.service.chats.ChatsKeysService;
import com.summer.whm.web.common.SpringContainer;
import com.summer.whm.web.common.utils.Base64ForJavascript;
import com.summer.whm.web.controller.chats.model.ChatsContext;
import com.summer.whm.web.controller.chats.model.MessageModel;

@ServerEndpoint(value = "/charts/friend/{key}")
public class ChatsFriendSocket {

    // 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    // concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static Map<String, ChatsContext> webSocketMap = new HashMap<String, ChatsContext>();

    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    
    private ChatsKeysService chatsKeysService; 
    
    private ChatsFriendMessageService chatsFriendMessageService;
    
    private ChatsFriendService chatsFriendService;
    
    public static final String MESSAGE_KEY_TYPE = "type";
    
    public static final String MESSAGE_KEY_CONTENT = "content";
    
    /**
     * 连接建立成功调用的方法
     * 
     * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(@PathParam("key") String key, Session session) {
        this.session = session;
        
        ChatsContext chatsContext = new ChatsContext();
        chatsContext.setChatkey(key);
        chatsContext.setFriendSocket(this);
        
        ChatsKeys chatsKeys = checkChatkey(key);
        chatsContext.setNickname(null);
        chatsContext.setUsername(chatsKeys.getObjId());

        webSocketMap.put(key, chatsContext);
        addOnlineCount(); // 在线数加1
        System.out.println(key + "加入！当前在线人数为" + getOnlineCount());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(@PathParam("key") String key) {
        webSocketMap.remove(key); // 从set中删除
        subOnlineCount(); // 在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     * 
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     * @throws IOException 
     */
    @OnMessage
    public void onMessage(@PathParam("key") String key, String message, Session session) throws IOException {
        System.out.println("来自客户端的消息:" + message);

        MessageModel messageModel = null;
        Map<String, String> messageMap = new HashMap<String, String>();
        String messageJson = null;
        try{
           messageModel = JSON.parseObject(message, MessageModel.class);
           String tempContent = Base64ForJavascript.decode(messageModel.getContent());
           messageModel.setContent(tempContent);
           
           boolean isOnLine = false;
           ChatsContext chatsContext = webSocketMap.get(messageModel.getChatkey());
           //如果不null，测接受人不在线，
           
           if(chatsContext != null){
               //发送给接受人消息
               messageMap.put(MESSAGE_KEY_TYPE, "1");//接受消息
               messageMap.put(MESSAGE_KEY_CONTENT, messageModel.getContent());
               messageMap.put("userChatkey", messageModel.getUserChatkey());
               messageMap.put("username", messageModel.getUsername());
               messageJson = JSON.toJSONString(messageMap);
               chatsContext.getFriendSocket().sendMessage(messageJson);
               isOnLine = true;
           }
           
           ChatsFriendMessage chatsFriendMessage = createChatsFriendMessage(messageModel ,isOnLine);
           this.chatsFriendMessageService.saveOrUpdate(chatsFriendMessage);
           
           //发送消息给自己，如果走到这一步，基本上可以确定消息已经成功发出
           chatsContext = webSocketMap.get(messageModel.getUserChatkey());
           
           messageMap.put(MESSAGE_KEY_TYPE, "0");//发送消息
           messageMap.put(MESSAGE_KEY_CONTENT, escapeContent(messageModel.getContent()));
  
           messageJson = JSON.toJSONString(messageMap);
           chatsContext.getFriendSocket().sendMessage(messageJson);
       }catch(Exception e){
           if(messageModel != null){
               messageMap.put(MESSAGE_KEY_TYPE, "99");//错误
               messageMap.put(MESSAGE_KEY_CONTENT,  "消息格式错误。");
               ChatsContext chatsContext = webSocketMap.get(messageModel.getUserChatkey());
               messageJson = JSON.toJSONString(messageMap);
               chatsContext.getFriendSocket().sendMessage(messageJson);
           }
           e.printStackTrace();
       }
    }

    public String escapeContent(String content){
        if(content != null){
           return StringEscapeUtils.escapeHtml(content);
        }
        
        return "";
    }
    
    public List<Map<String, String>> buildMessage(Map<String, String> messageMap){
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map = null;
        if(messageMap != null && messageMap.get(MESSAGE_KEY_CONTENT) != null){
            if(messageMap.get(MESSAGE_KEY_CONTENT).length() > 1000){
                String content = messageMap.get(MESSAGE_KEY_CONTENT);
                String temp = "";
                int length = content.length();
                int max = length / 1000;
                if(max * 1000 < length){
                    max++;
                }
                int begin = 0;
                int end = 0;
                for(int i = 0; i < max; i++){
                    begin = i * 1000;
                    end = begin + 1000;
                    if(end > length){
                        end = length;
                    }
                    temp = content.substring(begin, end);
                    map = new HashMap<String, String>();
                    map.putAll(messageMap);
                    map.put(MESSAGE_KEY_CONTENT, temp);
                    
                    list.add(map);
                }
            }else{
                list.add(messageMap);
            }
        }
        
        return list;
        
    }
    
    /**
     * 发生错误时调用
     * 
     * @param session
     * @param error
     */
    @OnError
    public void onError(@PathParam("key") String key, Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    public ChatsKeys checkChatkey(String key){
        ChatsKeys chatsKeys = getChatsKeysService().queryByChatkey(key);
        return chatsKeys;
    }
    
    public ChatsFriendMessage createChatsFriendMessage(MessageModel messageModel, boolean isRead){
        ChatsFriendMessage chatsFriendMessage = new ChatsFriendMessage();
        ChatsContext chatsContext = null;
        
        if(isRead){
            chatsContext = webSocketMap.get(messageModel.getChatkey());
            chatsFriendMessage.setAcceptname(chatsContext.getUsername());
            chatsFriendMessage.setStatus("1");
        }else{
            ChatsKeys chatsKeys = this.getChatsKeysService().queryByChatkey(messageModel.getChatkey());
            chatsFriendMessage.setAcceptname(chatsKeys.getObjId());
            chatsFriendMessage.setStatus("0");
        }
        
        chatsFriendMessage.setContent(messageModel.getContent());
        chatsFriendMessage.setUsername(messageModel.getUsername());
        
        String messageId =  this.getChatsFriendMessageService().getMessageId(chatsFriendMessage.getUsername(), chatsFriendMessage.getAcceptname());
        chatsFriendMessage.setMessageId(messageId);
        
        chatsFriendMessage.setCreator(messageModel.getUsername());
        chatsFriendMessage.setCreateTime(new Date());
        chatsFriendMessage.setLastUpdate(new Date());
        return chatsFriendMessage;
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
        ChatsFriendSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        ChatsFriendSocket.onlineCount--;
    }

    public ChatsKeysService getChatsKeysService() {
        
        if(chatsKeysService == null){
            chatsKeysService = SpringContainer.getBean(ChatsKeysService.class);
        }
        
        return chatsKeysService;
    }

    public ChatsFriendMessageService getChatsFriendMessageService() {
        
        if(chatsFriendMessageService == null){
            chatsFriendMessageService = SpringContainer.getBean(ChatsFriendMessageService.class);
        }
        
        return chatsFriendMessageService;
    }

    public ChatsFriendService getChatsFriendService() {
        if(chatsFriendService == null){
            chatsFriendService = SpringContainer.getBean(ChatsFriendService.class);
        }
        
        return chatsFriendService;
    }
}
