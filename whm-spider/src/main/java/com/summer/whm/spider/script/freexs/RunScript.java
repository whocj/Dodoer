package com.summer.whm.spider.script.freexs;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.summer.whm.spider.client.WebClientPool;
import com.summer.whm.spider.script.ScriptManager;
import com.summer.whm.spider.utils.SerializeUtil;

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
        InputStream is = RunScript.class.getResourceAsStream("detail_content.script");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String temp = null;
        StringBuffer sb = new StringBuffer();
        while ((temp = br.readLine()) != null) {
            sb.append(temp).append("\n");
        }
        System.out.println(sb.toString());
        HtmlPage htmlPage = null;
        htmlPage = webClientPool.borrowWebClient().getPage("http://www.freexs.cn/novel/73/73410/11303589.html");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("htmlPage", htmlPage);
        Object ret = ScriptManager.getInstance().run(sb.toString(), map);
        
        System.out.println(ret);
    }

}
