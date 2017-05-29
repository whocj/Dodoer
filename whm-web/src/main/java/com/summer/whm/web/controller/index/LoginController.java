package com.summer.whm.web.controller.index;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.summer.whm.common.configs.GlobalSystemConfig;
import com.summer.whm.common.utils.PinUtil;
import com.summer.whm.entiry.user.User;
import com.summer.whm.service.user.UserService;
import com.summer.whm.spider.utils.CustomerHttpClient;
import com.summer.whm.spider.utils.CustomerHttpClient.ClientName;
import com.summer.whm.web.common.session.CookieRemberManager;
import com.summer.whm.web.common.utils.Base64ForJavascript;
import com.summer.whm.web.common.utils.Constants;
import com.summer.whm.web.common.utils.CookieUtils;
import com.summer.whm.web.common.utils.WebConstants;
import com.summer.whm.web.controller.BaseController;
import com.summer.whm.web.model.auth.QQUserinfo;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private GlobalSystemConfig globalSystemConfig;

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String url = request.getParameter("url");
        model.put("url", url);
        return "login.ftl";
    }

    @RequestMapping("/indexDialog")
    public String indexDialog(HttpServletRequest request, HttpServletResponse response) {
        return "dialog_login.ftl";
    }

    @RequestMapping("/dialogLogin")
    public void dialogLogin(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remeber = request.getParameter("remeber");

        User user = null;
        String flag = "F";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("succ", flag);
        if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password)) {
            try {
                user = userService.login(username, password);
                if (user == null) {
                    request.setAttribute(WebConstants.MESSAGE_TXT, "用户名或密码错误.");
                    response.getOutputStream().print(JSON.toJSONString(map));
                    return;
                }

                CookieUtils cookieUtil = new CookieUtils(request, response);
                cookieUtil.setCookie(Constants.COOKIE_USER_NAME, username, false, 7 * 24 * 3600);
                boolean isRemeber = Boolean.parseBoolean(remeber);
                CookieRemberManager.loginSuccess(request, response, user.getId() + "", user.getPassword(), isRemeber);

                request.getSession().setAttribute(WebConstants.SESSION_USER, user);

                map.put("succ", "S");
                map.put("user", user);
                response.getOutputStream().println(JSON.toJSONString(map));
            } catch (Exception e) {
                response.getOutputStream().println(JSON.toJSONString(map));
                LOG.error("登录失败。", e);
            }
        }

    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remeber = request.getParameter("remeber");
        String url = request.getParameter("url");

        User user = null;

        if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password)) {
            user = userService.login(username, password);
            if (user == null) {
                request.setAttribute(WebConstants.MESSAGE_TXT, "用户名或密码错误.");
                return "login.ftl";
            }

            CookieUtils cookieUtil = new CookieUtils(request, response);
            cookieUtil.setCookie(Constants.COOKIE_USER_NAME, username, false, 7 * 24 * 3600);
            boolean isRemeber = Boolean.parseBoolean(remeber);
            CookieRemberManager.loginSuccess(request, response, user.getId() + "", user.getPassword(), isRemeber);

            request.getSession().setAttribute(WebConstants.SESSION_USER, user);
        }

        if (StringUtils.isNotEmpty(url)) {
            try {
                url = Base64ForJavascript.decode(url);
                response.sendRedirect(url);
                return null;
            } catch (IOException e) {
                LOG.error("URL跳转失败!", e.getMessage());
            }
        }

        return "redirect:index.htm";
    }

    @RequestMapping("/QQLogin")
    public String QQLogin(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException {
        return "auth_qq.ftl";
    }

    public QQUserinfo getQQInfo(String key, String token) throws IOException {
        String QQ_INFO_URL = "https://graph.qq.com/user/get_user_info";
        // oauth_consumer_key=100330589&access_token=4CA7D71A41CFDCE842BC94ECE5F4000F&openid=14863C2AF601216F6BBD91D5AFDE1938&format=json
        StringBuffer sb = new StringBuffer(QQ_INFO_URL);
        sb.append("?").append("oauth_consumer_key=101296555&format=json").append("&access_token=").append(token)
                .append("&openid=").append(key);

        ClientName clientName = ClientName.DEFAULT_CLIENT;
        // 如果是本地测试环境需要走代理访问
        if (GlobalSystemConfig.EVN_NAME_DEV.equals(globalSystemConfig.getEnvName())) {
            clientName = ClientName.PROXY_CLIENT;
        }

        String ret = CustomerHttpClient.get(sb.toString(), clientName);
        QQUserinfo userinfo = null;
        if (ret != null) {
            userinfo = JSON.parseObject(ret, QQUserinfo.class);
        }

        return userinfo;
    }

    @RequestMapping("/publicAuthLogin")
    public void publicAuthLogin(HttpServletRequest request, HttpServletResponse response, ModelMap model, String key,
            String source, String token) throws IOException {
        User user = null;
        // 库中存放的是加密后的key
        if (StringUtils.isNotBlank(token)) {
            user = userService.loadByAuthTypeAndKey(source, key);
        }

        Map<String, Object> map = new HashMap<String, Object>();
        if (user != null) {
            map.put("succ", "S");
            map.put("user", user);
            request.getSession().setAttribute(WebConstants.SESSION_USER, user);
        } else {
            user = new User();
            QQUserinfo userinfo = null;
            try {
                userinfo = getQQInfo(key, token);
            } catch (Exception e) {
                log.error("QQ用户认证失败。", e);
            }

            String pinNick = PinUtil.getPYHeadCharByCn(userinfo.getNickname());
            String username = userService.randomUsername(pinNick);

            user.setUsername(username);
            user.setDescription(username);
            user.setCreateTime(new Date());
            user.setCreator(userinfo.getNickname());
            user.setNickname(userinfo.getNickname());
            user.setLastUpdate(new Date());
            user.setStatus("1");

            user.setThirdAuthType(source);
            user.setThirdAuthTime(new Date());
            user.setThirdAuthKey(key);
            user.setThirdAuthToken(token);

            user.setUserLogo(userinfo.getFigureurl());
            user.setUserLogo50(userinfo.getFigureurl_1());
            user.setUserLogo100(userinfo.getFigureurl_2());
            userService.saveOrUpdate(user);
            user.setPassword(null);
            request.getSession().setAttribute(WebConstants.SESSION_USER, user);
            map.put("user", user);
            map.put("succ", "S");
        }
        response.getOutputStream().println(JSON.toJSONString(map));
    }

    @RequestMapping("/binduser")
    public String binduser(HttpServletRequest request, HttpServletResponse response, ModelMap model, String key,
            String token, String source) {
        model.put("key", key);
        model.put("source", source);
        model.put("token", token);

        return "bind_user.ftl";
    }

    @RequestMapping("/queryByUser")
    public void queryByUser(HttpServletRequest request, HttpServletResponse response, ModelMap model, String username)
            throws IOException {

        User user = userService.loadByName(username);
        Map<String, Object> map = new HashMap<String, Object>();
        if (user != null) {
            map.put("succ", "S");
            map.put("user", user);
        } else {
            map.put("succ", "F");
        }

        response.getOutputStream().println(JSON.toJSONString(map));
    }

}
