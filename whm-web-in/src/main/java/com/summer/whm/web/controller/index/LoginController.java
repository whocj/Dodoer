package com.summer.whm.web.controller.index;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.plexus.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.summer.whm.WebConstants;
import com.summer.whm.entiry.user.User;
import com.summer.whm.service.user.UserService;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = null;

        if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password)) {
            user = userService.login(username, password);
            if (user == null || !"admin".equals(user.getRole())) {
                request.setAttribute(WebConstants.MESSAGE_TXT, "用户名或密码错误.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
                return null;
            }
        }

        request.getSession().setAttribute(WebConstants.SESSION_USER, user);
        return "index.ftl";
    }

}
