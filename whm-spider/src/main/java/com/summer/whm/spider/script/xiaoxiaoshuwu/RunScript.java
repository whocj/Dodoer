package com.summer.whm.spider.script.xiaoxiaoshuwu;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebRequestSettings;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.summer.whm.spider.client.WebClientPool;
import com.summer.whm.spider.script.ScriptManager;

public class RunScript {

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param args
     * @throws Exception
     * @throws FailingHttpStatusCodeException
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void main(String[] args) throws FailingHttpStatusCodeException, Exception {

        WebClientPool webClientPool = new WebClientPool();
        InputStream is = RunScript.class.getResourceAsStream("story_title.script");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String temp = null;
        StringBuffer sb = new StringBuffer();
        while ((temp = br.readLine()) != null) {
            sb.append(temp).append("\n");
        }
        System.out.println(sb.toString());
        HtmlPage htmlPage = null;
        
        WebRequestSettings webRequest = new WebRequestSettings(new URL("http://www.xiaoxiaoshuwu.com/read/102927.html"));
        webRequest.setCharset("gb2312");
        
        htmlPage = webClientPool.borrowWebClient().getPage(webRequest);
        System.out.println(htmlPage.asText());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("htmlPage", htmlPage);
        Object ret = ScriptManager.getInstance().run(sb.toString(), map);

        System.out.println(ret);
    }

}
