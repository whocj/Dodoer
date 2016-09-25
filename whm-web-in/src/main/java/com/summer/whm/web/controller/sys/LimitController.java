package com.summer.whm.web.controller.sys;

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
import com.summer.whm.entiry.sys.Limit;
import com.summer.whm.service.sys.LimitService;
import com.summer.whm.web.common.utils.WebConstants;
import com.summer.whm.web.controller.BaseController;
import com.summer.whm.web.controller.category.CategoryController;

@Controller
@RequestMapping("/sys/limit")
public class LimitController extends BaseController {
    private static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);
    @Autowired
    private LimitService limitService;

    @RequestMapping("/list")
    public String list(HttpServletRequest request, @RequestParam(defaultValue = "1") int currentPage, ModelMap model) {
        PageModel<Limit> page = limitService.list(currentPage, WebConstants.PAGE_SIZE);
        model.put("page", page);
        return "sys/limit/list.ftl";
    }
    
    @RequestMapping("/edit")
    public String edit(HttpServletRequest request, @RequestParam(value = "id") int id, ModelMap model) {
        Limit limit = limitService.loadById(id + "");
        model.put("limit", limit);
        LOG.info("edit Limit id={}", id);
        return "sys/limit/edit.ftl";
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, ModelMap model) {
        model.put("limit", new Limit());
        return "sys/limit/edit.ftl";
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "ids") String ids) {
        LOG.info("delete Limit id={}", ids);
        limitService.deleteById(ids);
        return "redirect:list.htm";
    }

    @RequestMapping("/submit")
    public String submit(HttpServletRequest request, HttpServletResponse response, Limit limit) {
        LOG.info("submit Limit {}",limit);
        
        buildExtCreatorPara(request, limit);
        if (limit != null && limit.isNew()) {
            limitService.insert(limit);
        } else {
            limitService.update(limit);
        }

        return "redirect:list.htm";
    }
}
