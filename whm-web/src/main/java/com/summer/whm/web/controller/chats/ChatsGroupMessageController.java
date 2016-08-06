package com.summer.whm.web.controller.chats;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.summer.whm.entiry.chats.ChatsFriend;
import com.summer.whm.entiry.chats.ChatsUserReta;
import com.summer.whm.entiry.user.User;
import com.summer.whm.service.chats.ChatsFriendMessageService;
import com.summer.whm.service.chats.ChatsFriendService;
import com.summer.whm.service.chats.ChatsGroupService;
import com.summer.whm.service.chats.ChatsTopicService;
import com.summer.whm.service.chats.ChatsUserRetaService;
import com.summer.whm.web.controller.BaseController;

public class ChatsGroupMessageController extends BaseController {
    
    @Autowired
    private ChatsFriendMessageService chatsFriendMessageService;
    
    @Autowired
    private ChatsFriendService chatsFriendService;
    
    @Autowired
    private  ChatsGroupService chatsGroupService;
    
    @Autowired
    private  ChatsTopicService chatsTopicService;
    
    @Autowired
    private  ChatsUserRetaService chatsUserRetaService;
    
    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        User user = this.getSessionUser(request);
        // 未登录用户，跳转至登录页面
        if (user == null) {
            return LOGIN_URL;
        }

        List<ChatsUserReta> chatsUserList = chatsUserRetaService.queryByUsername(user.getUsername());
        List<ChatsFriend> chatsFriendList = chatsFriendService.queryByUsername(user.getUsername());
        
        model.put("chatsUserList", chatsUserList);
        model.put("chatsFriendList", chatsFriendList);
        
        return "chats/index.ftl";
    }
}
