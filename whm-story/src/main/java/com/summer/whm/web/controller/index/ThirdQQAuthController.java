package com.summer.whm.web.controller.index;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.summer.whm.common.utils.MD5;
import com.summer.whm.entiry.user.User;
import com.summer.whm.service.user.UserService;
import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/auth")
public class ThirdQQAuthController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(ThirdQQAuthController.class);

    @Autowired
    private UserService userService;

    public void getQQInfo(){
        
    }

    @RequestMapping("/submit")
    public void submit(HttpServletRequest request, HttpServletResponse response, ModelMap model, String username,
            String password, String key, String source, String token) throws IOException {
        LOG.debug("ThirdQQAuthController.submit()");
        boolean flag = true;
        User user = userService.loadByName(username);
        if (user == null) {
            user = new User();
            user.setUsername(username);
            String p = MD5.encode(password);
            user.setPassword(p);
            user.setCreateTime(new Date());
            user.setCreator(username);
            user.setNickname(username);
            user.setLastUpdate(new Date());
            user.setStatus("1");

            user.setThirdAuthType(source);
            user.setThirdAuthTime(new Date());
            user.setThirdAuthKey(key);
            user.setThirdAuthToken(token);
            userService.insert(user);
        } else {
            User tempUser = userService.login(username, password);
            if (tempUser == null) {
                flag = false;
            } else {
                User u = new User();
                u.setThirdAuthType(source);
                u.setThirdAuthTime(new Date());
                u.setThirdAuthKey(MD5.encode(password));
                u.setThirdAuthToken(token);
                u.setUsername(username);
                u.setId(user.getId());
                userService.update(u);
            }
            user = tempUser;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (flag) {
                map.put("succ", "S");
                map.put("user", user);
            } else {
                map.put("succ", "F");
            }

            response.getOutputStream().println(JSON.toJSONString(map));
        } catch (IOException e) {
            LOG.error("注册失败,", e);
            response.getOutputStream().print("{succ:F}");
        }

    }
}
