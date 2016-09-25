package com.summer.whm.web.controller.index;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.summer.whm.web.common.utils.WebConstants;

@Controller
public class LogoutController {

    @RequestMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Object obj = request.getSession().getAttribute(WebConstants.SESSION_USER);
        if (obj != null) {
            request.getSession().invalidate();
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (WebConstants.COOKIE_KEY.equalsIgnoreCase(cookie.getName())) {
                    cookie.setValue("");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    break;
                }
            }
            request.setAttribute(WebConstants.MESSAGE_TXT, "注销成功,欢迎再次使用!");
        } else {
            request.setAttribute(WebConstants.MESSAGE_TXT, "未登陆系统！");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }
}
