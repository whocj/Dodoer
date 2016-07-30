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

import com.summer.whm.WebConstants;
import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.sys.Sensitivity;
import com.summer.whm.service.sys.SensitivityService;
import com.summer.whm.web.controller.BaseController;
import com.summer.whm.web.controller.category.CategoryController;

@Controller
@RequestMapping("/sys/sensitivity")
public class SensitivityController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private SensitivityService sensitivityService;

    @RequestMapping("/list")
    public String list(HttpServletRequest request, @RequestParam(defaultValue = "1") int currentPage, ModelMap model) {
        PageModel<Sensitivity> page = sensitivityService.list(currentPage, WebConstants.PAGE_SIZE);
        model.put("page", page);
        return "sys/sensitivity/list.ftl";
    }

    @RequestMapping("/edit")
    public String edit(HttpServletRequest request, @RequestParam(value = "id") int id, ModelMap model) {
        Sensitivity sensitivity = sensitivityService.loadById(id + "");
        model.put("sensitivity", sensitivity);
        LOG.info("edit Sensitivity id={}", id);
        return "sys/sensitivity/edit.ftl";
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, ModelMap model) {
        model.put("sensitivity", new Sensitivity());
        return "sys/sensitivity/edit.ftl";
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "ids") String ids) {
        LOG.info("delete FriendLink id={}", ids);
        sensitivityService.deleteById(ids);
        return "redirect:list.htm";
    }

    @RequestMapping("/submit")
    public String submit(HttpServletRequest request, HttpServletResponse response, Sensitivity sensitivity) {
        LOG.info("submit Sensitivity {}", sensitivity);

        buildExtCreatorPara(request, sensitivity);
        if (sensitivity != null && sensitivity.isNew()) {
            sensitivityService.insert(sensitivity);
        } else {
            sensitivityService.update(sensitivity);
        }

        return "redirect:list.htm";
    }
}
