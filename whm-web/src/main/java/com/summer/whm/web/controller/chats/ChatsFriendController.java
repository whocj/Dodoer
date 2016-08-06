package com.summer.whm.web.controller.chats;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.plexus.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.summer.whm.entiry.chats.ChatsFriend;
import com.summer.whm.entiry.user.User;
import com.summer.whm.service.chats.ChatsFriendService;
import com.summer.whm.service.user.UserService;
import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/chats/friend")
public class ChatsFriendController extends BaseController {
    public static Logger log = LoggerFactory.getLogger(ChatsFriendController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private ChatsFriendService chatsFriendService;

    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        User user = this.getSessionUser(request);

        String friendname = request.getParameter("friendname");
        String username = user.getUsername();
        try{
            if (StringUtils.isNotEmpty(friendname)) {
                User friendUser = userService.loadByName(friendname);
                if (friendUser != null) {
                    ChatsFriend chatsFriend = new ChatsFriend(username, friendname);
                    chatsFriend.setCreator(username);
                    chatsFriendService.saveOrUpdate(chatsFriend);

                    chatsFriend = new ChatsFriend(friendname, username);
                    chatsFriend.setCreator(username);
                    chatsFriendService.saveOrUpdate(chatsFriend);

                    ajaxHtml(response, "1");
                    return;
                }
            }
        }catch(Exception e){
            log.error("添加好友失败" ,e );
        }

        ajaxHtml(response, "0");
    }
}
