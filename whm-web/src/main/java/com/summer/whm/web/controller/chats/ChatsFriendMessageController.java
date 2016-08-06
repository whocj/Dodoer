package com.summer.whm.web.controller.chats;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.summer.whm.entiry.chats.ChatsFriendMessage;
import com.summer.whm.entiry.user.User;
import com.summer.whm.service.chats.ChatsFriendMessageService;
import com.summer.whm.service.chats.ChatsFriendService;
import com.summer.whm.service.user.UserService;
import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/chats/friend")
public class ChatsFriendMessageController extends BaseController {
    
    @Autowired
    private ChatsFriendMessageService chatsFriendMessageService;
    
    @Autowired
    private ChatsFriendService chatsFriendService;
    
    @Autowired
    private UserService userService;
    
    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        User user = this.getSessionUser(request);
        // 未登录用户，跳转至登录页面
        if (user == null) {
            return LOGIN_URL;
        }
        
        String chatkey = request.getParameter("chatkey");
        String friendName =  request.getParameter("friendName");
        String type =  request.getParameter("type");
        
        User acceptUser =  userService.loadByName(friendName);
        
        List<ChatsFriendMessage> chatsFriendMessageList = chatsFriendMessageService.queryByUsername(user.getUsername(), friendName);
        for(ChatsFriendMessage message : chatsFriendMessageList){
            message.setContent(escapeContent(message.getContent()));
        }
        model.put("chatsFriendMessageList", chatsFriendMessageList);
        model.put("chatkey", chatkey);
        model.put("friendName", friendName);
        model.put("type", type);
        model.put("acceptUser", acceptUser);
        
        return "chats/main/friend_message.ftl";
    }
    
    public String escapeContent(String content){
        if(content != null){
           return StringEscapeUtils.escapeHtml(content);
        }
        
        return "";
    }
    
}
