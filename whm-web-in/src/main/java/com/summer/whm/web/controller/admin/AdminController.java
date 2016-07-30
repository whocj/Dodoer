package com.summer.whm.web.controller.admin;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.summer.whm.common.configs.GlobalConfigHolder;
import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

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
    public String reloadConfig(HttpServletRequest request, HttpServletResponse respone) {
        JSONObject jsonObject = JSON.parseObject(request.getParameter("config.properties"));
        if (jsonObject != null) {
            GlobalConfigHolder.updateConfigByTemplate(jsonObject, true);
        }
        
        return JSON.toJSONString(GlobalConfigHolder.currentConfigs());
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

}
