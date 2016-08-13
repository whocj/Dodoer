package com.summer.whm.web.controller.index;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.summer.whm.WebConstants;
import com.summer.whm.web.common.session.CookieRemberManager;
import com.summer.whm.web.common.utils.Constants;
import com.summer.whm.web.controller.BaseController;

@Controller
public class LogoutController extends BaseController {

    @RequestMapping("/logout")
    public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        Object obj = request.getSession().getAttribute(WebConstants.SESSION_USER);
        if (obj != null) {
            request.getSession().invalidate();
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (Constants.COOKIE_CONTEXT_ID.equalsIgnoreCase(cookie.getName())) {
                    cookie.setValue("");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    break;
                }
            }
            CookieRemberManager.logout(request, response);
            request.setAttribute(WebConstants.MESSAGE_TXT, "注销成功,欢迎再次使用!");
        } else {
            request.setAttribute(WebConstants.MESSAGE_TXT, "未登陆系统！");
        }
        return "redirect:index.htm";
    }

}
