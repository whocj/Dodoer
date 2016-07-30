package com.summer.whm.common.configs;

public class GlobalSystemConfig {
    
    public static final String EVN_NAME_DEV = "dev";
    
    public static final String EVN_NAME_PRE = "pre";
    
    public static final String EVN_NAME_PROD = "prod";
    
    public String envName = "dev";

    public String imgDomain = "http://www.whohelpme.com";
    
    public String getEnvName() {
        return envName;
    }

    public void setEnvName(String envName) {
        this.envName = envName;
    }

    public String getImgDomain() {
        return imgDomain;
    }

    public void setImgDomain(String imgDomain) {
        this.imgDomain = imgDomain;
    }
    
}
