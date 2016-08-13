package com.suning.sample.web.test;

import com.suning.framework.test.web.WebDriverBaseTest;


public class WebBaseTest extends WebDriverBaseTest{
    /**
     * @return 应用程序的根路径，各项目要改成相应的contextPath.
     */
    protected String getWebRoot() {
        return "http://localhost:8080/sample-web";
    }
}
