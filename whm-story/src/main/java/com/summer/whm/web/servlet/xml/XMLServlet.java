package com.summer.whm.web.servlet.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.summer.whm.common.configs.GlobalConfigHolder;
import com.summer.whm.service.sys.sitemap.story.StoryBaiduZhanneiSitemapService;
import com.summer.whm.service.sys.sitemap.story.StorySitemapService;
import com.summer.whm.web.common.SpringContainer;
import com.summer.whm.web.common.utils.CheckMobile;
import com.summer.whm.web.servlet.AbstractBaseServlet;

/**
 * 
 * XML请求跳转
 * 
 * @author 11041810
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class XMLServlet extends AbstractBaseServlet {

    private static final long serialVersionUID = 1L;
    private final Logger log = LoggerFactory.getLogger(XMLServlet.class);
    
    @Override
    public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/xml;charset=utf-8");
        
        StringBuffer url = req.getRequestURL();
        boolean isMobile = CheckMobile.checkUrl(url.toString());
        
        String uri = req.getRequestURI();
        String path = req.getSession().getServletContext().getRealPath("/");
        File file = new File(path, uri);
        byte[] buf = new byte[1024 * 8];
        int length = 0;

        if (file != null && file.isFile()) {
            FileInputStream fis = new FileInputStream(file);
            ServletOutputStream os = resp.getOutputStream();
            while ((length = fis.read(buf)) > 0) {
                os.write(buf, 0, length);
            }
            os.flush();
            fis.close();
            fis = null;
        } else {
            // 读静态文件
            path = GlobalConfigHolder.DODOER_STATIC_PATH;
            file = new File(path, uri);
            if (file != null && file.isFile()) {
                FileInputStream fis = new FileInputStream(file);
                ServletOutputStream os = resp.getOutputStream();
                while ((length = fis.read(buf)) > 0) {
                    os.write(buf, 0, length);
                }
                os.flush();
                fis.close();
                fis = null;
            } else {
                // 网站地图
                if (uri.endsWith("sitemap.xml")) {
                    StorySitemapService sitemapService = SpringContainer.getBean(StorySitemapService.class);
                    XMLWriter xmlWriter = null;
                    try {
                        
                        String scount  = req.getParameter("count");
                        int count = GlobalConfigHolder.SITEMAP_COUNT;
                        if(scount != null){
                            try{
                                count = Integer.parseInt(scount);
                                if(count > 5000){
                                    count = 5000;
                                }
                            }catch(Exception e){
                                log.error("XML文件操作失败,scount" + scount , e);
                            }
                        }
                        Document sitemap = sitemapService.buildXMLDoc(count, isMobile);
                        xmlWriter = new XMLWriter(resp.getWriter());
                        xmlWriter.write(sitemap);
                    } catch (Exception e) {
                        log.error("XML文件操作失败," , e);
                    } finally {
                        try {
                            xmlWriter.close();
                        } catch (IOException e) {
                        }
                    }
                }

                // 百度站内网站地图
                if (uri.endsWith("baiduzn.xml")) {
                    StoryBaiduZhanneiSitemapService baiduZhanneiSitemapService = SpringContainer
                            .getBean(StoryBaiduZhanneiSitemapService.class);
                    XMLWriter xmlWriter = null;
                    try {
                        Document sitemap = baiduZhanneiSitemapService.buildBaiduZNXMLDoc(isMobile);
                        xmlWriter = new XMLWriter(resp.getWriter());
                        xmlWriter.write(sitemap);
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.error("XML文件操作失败," , e);
                    } finally {
                        try {
                            xmlWriter.close();
                        } catch (IOException e) {
                        }
                    }
                }
            }
        }
    }
}
