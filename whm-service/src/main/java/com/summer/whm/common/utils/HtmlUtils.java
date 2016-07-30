package com.summer.whm.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.sys.Sensitivity;
import com.summer.whm.mapper.sys.SensitivityMapper;

@Service
public class HtmlUtils {

    public List<Sensitivity> sensitivityList = new ArrayList<Sensitivity>();

    @Autowired
    private SensitivityMapper sensitivityMapper;

    private boolean init = false;

    public List<Sensitivity> initSensitivity() {
        PageModel<Sensitivity> page = new PageModel<Sensitivity>(1, 9999);
        sensitivityList = sensitivityMapper.list(page);

        init = true;
        return sensitivityList;
    }

    public String replace(String input) {
        if (input == null) {
            return null;
        }
        if (!init) {
            initSensitivity();
        }
        
        for (Sensitivity sensitivity : sensitivityList) {
            input = input.replaceAll(sensitivity.getSearch(), sensitivity.getReplacement());
        }
        
        return input.replaceAll("<script", "< script>");
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param args
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void main(String[] args) {

    }

}
