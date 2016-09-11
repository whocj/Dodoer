package com.summer.whm.web.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.summer.whm.web.common.utils.Configs;

public class CacheFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        
        if(Configs.BAIDU_JIASHU && uri != null){
            if(!(uri.indexOf("add") != -1 || uri.indexOf("update") != -1)){
                res.addHeader("Cache-Control", Configs.CACHE_CONTROL);
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        
    }

}
