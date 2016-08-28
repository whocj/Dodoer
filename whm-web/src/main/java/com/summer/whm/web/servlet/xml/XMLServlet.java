package com.summer.whm.web.servlet.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
import com.summer.whm.service.sys.BaiduZhanneiSitemapService;
import com.summer.whm.service.sys.SitemapService;
import com.summer.whm.web.common.SpringContainer;
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
            path = GlobalConfigHolder.BASE_STATIC_PATH;
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
                    SitemapService sitemapService = SpringContainer.getBean(SitemapService.class);
                    XMLWriter xmlWriter = null;
                    try {
                        Document sitemap = sitemapService.buildXMLDoc(100);
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
                
                // 网站地图
                if (uri.endsWith("allsitemap.xml")) {
                    SitemapService sitemapService = SpringContainer.getBean(SitemapService.class);
                    XMLWriter xmlWriter = null;
                    int count = 100;
                    try {
                        Document sitemap = sitemapService.buildXMLDoc(count);
                        FileOutputStream fos = new FileOutputStream(file);
                        xmlWriter = new XMLWriter(fos);
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
                    BaiduZhanneiSitemapService baiduZhanneiSitemapService = SpringContainer
                            .getBean(BaiduZhanneiSitemapService.class);
                    XMLWriter xmlWriter = null;
                    try {
                        Document sitemap = baiduZhanneiSitemapService.buildBaiduZNXMLDoc();
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
                if (uri.endsWith("baiduzntopic.xml")) {
                    BaiduZhanneiSitemapService baiduZhanneiSitemapService = SpringContainer
                            .getBean(BaiduZhanneiSitemapService.class);
                    XMLWriter xmlWriter = null;
                    try {
                        int count = 200;
                        String save = req.getParameter("save");
                        Document sitemap = baiduZhanneiSitemapService.buildBaiduZNTopicXMLDoc(count);
                        if("1".equals(save)){
                            FileOutputStream fos = new FileOutputStream(file);
                            xmlWriter = new XMLWriter(fos);
                            xmlWriter.write(sitemap);
                        }else{
                            xmlWriter = new XMLWriter(resp.getWriter());
                            xmlWriter.write(sitemap);
                        }
                        
                    } catch (Exception e) {
                        log.error("XML文件操作失败," , e);
                    } finally {
                        try {
                            if(xmlWriter != null){
                                xmlWriter.close();
                            }
                        } catch (IOException e) {
                        }
                    }
                }
            }
        }
    }
}
