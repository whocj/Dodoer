package com.summer.whm.web.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.summer.whm.common.configs.GlobalConfigHolder;
import com.summer.whm.common.utils.HtmlUtils;
import com.summer.whm.entiry.search.SearchPost;
import com.summer.whm.entiry.sys.Sensitivity;
import com.summer.whm.service.search.SearchService;
import com.summer.whm.service.search.index.BaiduIndexPushService;
import com.summer.whm.service.search.index.IndexInitService;
import com.summer.whm.service.search.keyword.associate.AssociateWordFileService;
import com.summer.whm.service.search.keyword.associate.AssociateWordService;
import com.summer.whm.web.common.utils.ConfigUpdater;
import com.summer.whm.web.common.utils.Configs;
import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {
    private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private IndexInitService indexInitService;

    @Autowired
    private BaiduIndexPushService baiduIndexPushService;

    @Autowired
    private AssociateWordFileService associateWordFileService;

    @Autowired
    private AssociateWordService associateWordService;

    @Autowired
    private SearchService searchService;

    @Autowired
    private HtmlUtils htmlUtils;

    /**
     * 
     * 功能描述: <br>
     * 重新加载配置文件<br>
     * 1从请求参数中取出要修改的项目 2如果参数中存在待修改项目 a修改磁盘/opt/search下的配置文件 3读取/opt/search下的配置文件 4返回当前内存中的所有配置信息
     * 
     * 注意：配置文件中的键值对 都为String 类型，传入JSON串时，请将数值类型型加上引号 example
     * http://localhost:8080/ds/admin/reloadConfig.json?ds_config.properties
     * ={%22ABSTRACT_DETECTOR_SETTLEMENT_CYCLE%22:%22300%22}
     * 
     * @param request
     * @param respone
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping("/reloadConfig")
    public void reloadConfig(HttpServletRequest request, HttpServletResponse respone) {
        JSONObject jsonObject = JSON.parseObject(request.getParameter("config.properties"));
        if (jsonObject != null) {
            GlobalConfigHolder.updateConfigByTemplate(jsonObject, true);
        }

        ajaxJson(respone, JSON.toJSONString(GlobalConfigHolder.currentConfigs()));
        ;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉 解析逗号(,)分隔多值 等号(=)分隔键值对
     * 
     * @param request
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @SuppressWarnings("unused")
    private Properties getConfigProperites(HttpServletRequest request) {
        Properties p = null;
        String spliter = request.getParameter("configStrs");
        if (spliter == null) {
            spliter = ",";
        }
        String configStrs = request.getParameter("configStrs");
        if (configStrs != null && !"".equals(configStrs)) {
            p = new Properties();
            String[] configAry = configStrs.split(spliter);
            if (configAry != null) {
                for (int i = 0; i < configAry.length; i++) {
                    String configPare = configAry[i];
                    if (configPare != null && configPare.indexOf("=") > 0 && configPare.split("=").length == 2) {
                        String name = configPare.split("=")[0];
                        String value = configPare.split("=")[1];
                        if (name != null && value != null) {
                            p.put(name, value);
                        }
                    }
                }
            }
        }
        return p;
    }

    /**
     * 
     * 功能描述: <br>
     * 初始化索引文件
     * 
     * @param request
     * @param respone
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping("/initIndex")
    public void initIndex(HttpServletRequest request, HttpServletResponse respone) {
        indexInitService.startup();
        try {
            respone.getOutputStream().println("S");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * 功能描述: <br>
     * 初始化索引文件
     * 
     * @param request
     * @param respone
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping("/indexRomove")
    public void indexRomove(HttpServletRequest request, HttpServletResponse respone, SearchPost post) {
        try {
            searchService.remove(post);
            respone.getOutputStream().println("S");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * 功能描述: <br>
     * 百度索引push任务
     * 
     */
    @RequestMapping("/baiduIndexPush")
    public void baiduIndexPush(HttpServletRequest request, HttpServletResponse respone) {
        try {
            baiduIndexPushService.startup();
            respone.getOutputStream().println("S");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * 功能描述: <br>
     * 初始化联想词文件
     * 
     * @param request
     * @param response
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping("/iniAssWordFile")
    public void iniAssWordFile(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("initFile");
        try {
            associateWordFileService.initFile();
            ajaxHtml(response, SUCCESS_STR);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxHtml(response, e.getMessage());
        }
    }

    /**
     * 
     * 功能描述: <br>
     * 初始化联想词索引
     * 
     * @param request
     * @param response
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping("/initAssWordIndex")
    public void initAssWordIndex(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("initAssWord");
        try {
            associateWordService.reCreateIndex();
            ajaxHtml(response, SUCCESS_STR);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxHtml(response, e.getMessage());
        }
    }

    @RequestMapping("/initSensitivity")
    public void initSensitivity(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("initSensitivity");
        try {
            List<Sensitivity> list = htmlUtils.initSensitivity();
            ajaxJson(response, JSON.toJSONString(list));
        } catch (Exception e) {
            e.printStackTrace();
            ajaxHtml(response, e.getMessage());
        }
    }

    @RequestMapping("/reloadConfigs")
    public void reloadConfigs(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("reloadConfigs");
        try {
            String config = request.getParameter("configs");
            try {
                Map<String, Object> m = JSON.parseObject(config);
                ConfigUpdater.updateConfigByTemplate(Configs.p, m, true, Configs.CONFIG_FILE);
                Configs.initial();
                request.getSession().getServletContext().setAttribute(Configs.APP_CONFIGS, ConfigUpdater.currentConfigs(Configs.class));
            } catch (Exception e) {
                LOG.error("Configs reload error.", e);
            }
            ajaxJson(response, JSON.toJSONString(ConfigUpdater.currentConfigs(Configs.class)));
        } catch (Exception e) {
            e.printStackTrace();
            ajaxHtml(response, e.getMessage());
        }
    }

}
