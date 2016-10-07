package com.summer.whm.spider.client;

import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;

import com.gargoylesoftware.htmlunit.AlertHandler;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.IncorrectnessListener;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.WebClient;
import com.summer.whm.common.configs.GlobalConfigHolder;
import com.summer.whm.common.configs.GlobalSystemConfig;
import com.summer.whm.plugin.ApplicationContextUtil;
import com.summer.whm.spider.utils.FileUtils;

public class WebClientPool {
    private final GenericObjectPool<WebClient> internalPool;
    private GlobalSystemConfig systemConfig = null;
    public WebClientPool() {
        this.internalPool = new GenericObjectPool<WebClient>(new BasePoolableObjectFactory<WebClient>() {
            public WebClient makeObject() throws Exception {
                WebClient webClient = new WebClient(BrowserVersion.FIREFOX_3);
//                if(systemConfig == null){
//                    systemConfig = ApplicationContextUtil.getBean(GlobalSystemConfig.class);
//                }
                
//                if (FileUtils.isWindow()) {
//                    if(systemConfig != null && GlobalSystemConfig.EVN_NAME_DEV.equals(systemConfig.getEnvName())){
//                        ProxyConfig proxyConfig = new ProxyConfig();
//                        proxyConfig.setProxyAutoConfigUrl("http://it.cnsuning.com/zongbu.pac");
//                        webClient.setProxyConfig(proxyConfig);
//                    }
//                }

                webClient.setUseInsecureSSL(true);
                webClient.setThrowExceptionOnScriptError(false);
                webClient.setThrowExceptionOnFailingStatusCode(false);
                webClient.setJavaScriptEnabled(false);
                webClient.setActiveXNative(false);
                webClient.setCssEnabled(false);
                webClient.setTimeout(GlobalConfigHolder.SPIDER_CLIENT_TIMEOUT);

                webClient.setIncorrectnessListener(new IncorrectnessListener() {
                    public void notify(String message, Object origin) {
                        // do nothing.
                    }
                });
                webClient.setAlertHandler(new AlertHandler() {
                    @Override
                    public void handleAlert(Page page, String message) {
                        System.out.println(message);
                    }
                });

                return webClient;
            }
        }, Integer.MAX_VALUE);
    }

    public WebClient borrowWebClient() throws Exception {
        return this.internalPool.borrowObject();
    }

    public void returnWebClient(WebClient client) throws Exception {
        this.internalPool.returnObject(client);
    }
}
