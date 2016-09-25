package com.summer.whm.web.controller.sys;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.sys.FriendLink;
import com.summer.whm.entiry.sys.Site;
import com.summer.whm.service.sys.FriendLinkService;
import com.summer.whm.service.sys.SiteService;
import com.summer.whm.web.common.utils.WebConstants;
import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/sys/friendLink")
public class FriendLinkController extends BaseController {
    private static final Logger LOG = LoggerFactory.getLogger(FriendLinkController.class);
    @Autowired
    private FriendLinkService friendLinkService;

    @Autowired
    private SiteService siteService;
    
    @RequestMapping("/list")
    public String list(HttpServletRequest request, @RequestParam(defaultValue = "1") int currentPage, ModelMap model) {
        PageModel<FriendLink> page = friendLinkService.list(currentPage, WebConstants.PAGE_SIZE);
        model.put("page", page);
        return "sys/friendLink/list.ftl";
    }
    
    @RequestMapping("/edit")
    public String edit(HttpServletRequest request, @RequestParam(value = "id") int id, ModelMap model) {
        FriendLink friendLink = friendLinkService.loadById(id + "");
        List<Site> siteList = siteService.queryAll();
        
        model.put("siteList", siteList);
        model.put("friendLink", friendLink);
        LOG.info("edit FriendLink id={}", id);
        return "sys/friendLink/edit.ftl";
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, ModelMap model) {
        model.put("friendLink", new FriendLink());
        List<Site> siteList = siteService.queryAll();
        
        model.put("siteList", siteList);
        return "sys/friendLink/edit.ftl";
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "ids") String ids) {
        LOG.info("delete FriendLink id={}", ids);
        friendLinkService.deleteById(ids);
        return "redirect:list.htm";
    }

    @RequestMapping("/submit")
    public String submit(HttpServletRequest request, HttpServletResponse response, FriendLink friendLink) {
        LOG.info("submit FriendLink {}", friendLink);
        
        
        buildExtCreatorPara(request, friendLink);
        if (friendLink != null && friendLink.isNew()) {
            friendLinkService.insert(friendLink);
        } else {
            friendLinkService.update(friendLink);
        }

        return "redirect:list.htm";
    }

}
