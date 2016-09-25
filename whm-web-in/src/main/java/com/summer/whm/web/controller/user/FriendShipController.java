package com.summer.whm.web.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.user.FriendShip;
import com.summer.whm.service.user.FriendShipService;
import com.summer.whm.web.common.utils.WebConstants;
import com.summer.whm.web.controller.BaseController;
import com.summer.whm.web.controller.category.CategoryController;

@Controller
@RequestMapping("/user/friendShip")
public class FriendShipController extends BaseController {
    private static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);
    @Autowired
    private FriendShipService friendShipService;

    @RequestMapping("/list")
    public String list(HttpServletRequest request, @RequestParam(defaultValue = "1") int currentPage, ModelMap model) {
        PageModel<FriendShip> page = friendShipService.list(currentPage, WebConstants.PAGE_SIZE);
        model.put("page", page);
        return "user/friendShip/list.ftl";
    }
}
