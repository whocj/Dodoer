package com.summer.whm.web.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.summer.whm.WebConstants;
import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.user.User;
import com.summer.whm.service.user.UserService;
import com.summer.whm.web.controller.BaseController;
import com.summer.whm.web.controller.category.CategoryController;

@Controller
@RequestMapping("/user/user")
public class UserController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);
    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public String list(HttpServletRequest request, @RequestParam(defaultValue = "1") int currentPage, ModelMap model) {
        PageModel<User> page = userService.list(currentPage, WebConstants.PAGE_SIZE);
        model.put("page", page);
        return "user/user/list.ftl";
    }

    @RequestMapping("/edit")
    public String edit(HttpServletRequest request, @RequestParam(value = "id") int id, ModelMap model) {
        User user = userService.loadById(id + "");
        model.put("user", user);
        LOG.info("edit User id={}", id);
        return "user/user/edit.ftl";
    }
    
    @RequestMapping("/main")
    public String main(HttpServletRequest request, HttpServletResponse response) {
        return "frame/user_main.ftl";
    }

    @RequestMapping("/left")
    public String left(HttpServletRequest request, HttpServletResponse response) {
        return "frame/user_left.ftl";
    }

    @RequestMapping("/right")
    public String right(HttpServletRequest request, @RequestParam(defaultValue = "1") int currentPage, ModelMap model) {
        return "user/right.ftl";
    }
}
