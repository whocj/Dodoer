package com.summer.whm.web.controller.index;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.plexus.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.summer.whm.common.configs.GlobalConfigHolder;
import com.summer.whm.common.configs.GlobalSystemConfig;
import com.summer.whm.common.utils.MD5;
import com.summer.whm.entiry.user.User;
import com.summer.whm.entiry.user.UserConstants;
import com.summer.whm.service.user.UserService;
import com.summer.whm.web.controller.BaseController;

@Controller
public class RegisterController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private GlobalSystemConfig globalSystemConfig;
    
    @RequestMapping("/register")
    public void reg(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password1 = request.getParameter("password1");

        // String url = request.getParameter("url");
        String flag = "S";
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || StringUtils.isEmpty(password1)) {
            flag = "F";
            response.getOutputStream().print(flag);
            return;
        }

        if (!password.equals(password1)) {
            return;
        }

        User u = userService.loadByName(username);
        if(u != null){
            flag = "F";
            response.getOutputStream().print(flag);
            return;
        }
        User user = new User();
        user.setUsername(username);
        String p = MD5.encode(password);
        user.setPassword(p);
        user.setCreateTime(new Date());
        user.setCreator(username);
        user.setNickname(username);
        user.setLastUpdate(new Date());
        user.setRole(UserConstants.USER_ROLE_CONTRIBUTOR);
        user.setStatus("1");
        user.setUserLogo(globalSystemConfig.getImgDomain() + GlobalConfigHolder.DEFAULT_USER_PIC);
        user.setUserLogo50(globalSystemConfig.getImgDomain() + GlobalConfigHolder.DEFAULT_USER_PIC);
        user.setUserLogo100(globalSystemConfig.getImgDomain() + GlobalConfigHolder.DEFAULT_USER_PIC);
        userService.insert(user);
        try {
            response.getOutputStream().print(flag);
        } catch (IOException e) {
            LOG.error("注册失败,", e);
        }

    }

}
