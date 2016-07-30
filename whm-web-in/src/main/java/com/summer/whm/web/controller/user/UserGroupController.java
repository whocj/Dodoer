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
import com.summer.whm.entiry.sys.Limit;
import com.summer.whm.entiry.user.UserGroup;
import com.summer.whm.service.user.UserGroupService;
import com.summer.whm.web.controller.BaseController;
import com.summer.whm.web.controller.category.CategoryController;

@Controller
@RequestMapping("/user/userGroup")
public class UserGroupController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);
    @Autowired
    private UserGroupService userGroupService;

    @RequestMapping("/list")
    public String list(HttpServletRequest request, @RequestParam(defaultValue = "1") int currentPage, ModelMap model) {
        PageModel<UserGroup> page = userGroupService.list(currentPage, WebConstants.PAGE_SIZE);
        model.put("page", page);
        return "user/userGroup/list.ftl";
    }
    
    @RequestMapping("/edit")
    public String edit(HttpServletRequest request, @RequestParam(value = "id") int id, ModelMap model) {
        Limit limit = userGroupService.loadById(id + "");
        model.put("limit", limit);
        LOG.info("edit Limit id={}", id);
        return "user/userGroup/edit.ftl";
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, ModelMap model) {
        model.put("userGroup", new UserGroup());
        return "user/userGroup/edit.ftl";
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "ids") String ids) {
        LOG.info("delete UserGroup id={}", ids);
        userGroupService.deleteById(ids);
        return "redirect:list.htm";
    }

    @RequestMapping("/submit")
    public String submit(HttpServletRequest request, HttpServletResponse response, UserGroup userGroup) {
        LOG.info("submit UserGroup {}",userGroup);
        
        buildExtCreatorPara(request, userGroup);
        if (userGroup != null && userGroup.isNew()) {
            userGroupService.insert(userGroup);
        } else {
            userGroupService.update(userGroup);
        }

        return "redirect:list.htm";
    }
}
