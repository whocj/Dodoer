package com.summer.whm.web.common.utils;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.summer.whm.common.configs.GlobalConfigHolder;
import com.summer.whm.entiry.category.Category;
import com.summer.whm.service.category.CategoryService;
import com.summer.whm.web.common.SpringContainer;

/**
 * 执行时间过滤器
 * 
 * @author tom
 * 
 */
public class ProcessTimeFilter implements Filter {
	protected final Logger log = LoggerFactory
			.getLogger(ProcessTimeFilter.class);
	/**
	 * 请求执行开始时间
	 */
	public static final String START_TIME = "_start_time";

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		
		long time = System.currentTimeMillis();
		request.setAttribute(START_TIME, time);
		chain.doFilter(request, response);
		time = System.currentTimeMillis() - time;
		log.debug("process in {} ms: {}", time, request.getRequestURI());
	}

	public void init(FilterConfig config) throws ServletException {
	    config.getServletContext().setAttribute(Configs.APP_CONFIGS, ConfigUpdater.currentConfigs(Configs.class));
	    CategoryService categoryService = SpringContainer.getBean(CategoryService.class);
	    List<Category> categoryList = categoryService.queryBySite(Constants.SITE_ID_STORY);
        config.getServletContext().setAttribute(Configs.CATEGORY_LIST, categoryList);
	}
}
