package com.summer.whm.spider.client;

import java.io.IOException;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;

import com.gargoylesoftware.htmlunit.StringWebResponse;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequestSettings;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.util.FalsifyingWebConnection;

public class InterceptWebConnection extends FalsifyingWebConnection {
    private static Logger logger = LoggerFactory.getLogger(InterceptWebConnection.class);
    private String currentUrl;
    private String[] resources;
    private String[] ignoreResources;
    private AntPathMatcher pathMatcher = new AntPathMatcher();
    public InterceptWebConnection(WebClient webClient, String url, String[] resources, String[] ignoreResources ) throws Exception {
        super(webClient);
        this.currentUrl = url;
        this.resources = resources;
        this.ignoreResources = ignoreResources;
    }

    public WebResponse superGetResponse(WebRequestSettings request) throws Exception {
        long startTime = System.currentTimeMillis();
        WebResponse response = super.getResponse(request);
        logger.debug("url has get,spare time " + (System.currentTimeMillis() - startTime) + " ms:" + request.getUrl());
        return response;
    }

    public WebResponse returnEmptyResponse(URL url) throws Exception {
        logger.debug("url ignored:" + url.toString());
        return new StringWebResponse("", url);
    }

    public WebResponse getResponse(WebRequestSettings request) throws IOException {
        try {
            URL url = request.getUrl();
            if (currentUrl != null
                    && StringUtils.endsWithIgnoreCase(StringUtils.removeEnd(url.toString(), "/").trim(), StringUtils
                            .removeEnd(currentUrl, "/").trim())) {
                return superGetResponse(request);
            }
            String[] rPatterns = resources;
            if (rPatterns != null) {
                for (String pattern : rPatterns) {
                    if (pathMatcher.match(pattern, url.toString())) {
                        return superGetResponse(request);
                    }
                }
                return returnEmptyResponse(url);
            }
            String[] iPatterns = ignoreResources;
            if (iPatterns != null) {
                for (String pattern : iPatterns) {
                    if (pathMatcher.match(pattern, url.toString())) {
                        return returnEmptyResponse(url);
                    }
                }
                return superGetResponse(request);
            }
            return superGetResponse(request);
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

}
