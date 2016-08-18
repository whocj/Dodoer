package com.summer.whm.spider.script;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.scripting.bsh.BshScriptUtils;

import bsh.EvalError;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.summer.whm.common.utils.MD5;
import com.summer.whm.spider.client.WebClientPool;

/**
 * 
 * 单例，脚本运行入口
 * 
 * @author 11041810
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ScriptManager {

    private static Map<String, IBshScript> BSH_SCRIPT_MAP = new HashMap<String, IBshScript>();

    private static ScriptManager instance = new ScriptManager();

    private ScriptManager() {

    }

    public static ScriptManager getInstance() {
        return instance;
    }

    public Object run(String script, Map<String, Object> map) {
        if (script != null) {
            IBshScript shell = getBshScript(script);
            if (shell != null) {
                return shell.process(map);
            }
        }

        return null;
    }

    private IBshScript getBshScript(String script) {
        String code = MD5.encode(script);
        IBshScript bshScript = BSH_SCRIPT_MAP.get(code);
        if (bshScript == null) {
            bshScript = createBshScript(script);
            if (bshScript != null) {
                BSH_SCRIPT_MAP.put(code, bshScript);
            } else {
                System.out.println("创建脚本对象失败," + script);
            }
        }

        return bshScript;
    }

    private IBshScript createBshScript(String script) {
        String temp = " process(map){" + script + "}";
        IBshScript shell;
        try {
            shell = (IBshScript) BshScriptUtils.createBshObject(temp, new Class[] { IBshScript.class });
            return shell;
        } catch (EvalError e) {
            System.out.println("创建脚本对象失败," + ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

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
        InputStream is = ScriptManager.class.getResourceAsStream("title.script");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String temp = null;
        StringBuffer sb = new StringBuffer();
        while ((temp = br.readLine()) != null) {
            sb.append(temp).append("\n");
        }
        System.out.println(sb.toString());

        HtmlPage htmlPage = webClientPool.borrowWebClient().getPage(
                "http://product.suning.com/0030000180/101822230.html");
        String scriptTxt = "val(map){" + sb.toString() + "}";

        IBshScript script = null;
        try {
            Map<String, Object> record = new HashMap<String, Object>();
            record.put("htmlPage", htmlPage);
            script = (IBshScript) BshScriptUtils.createBshObject(scriptTxt, new Class[] { IBshScript.class });
            Object ret = script.process(record);
            System.out.println(ret);
        } catch (EvalError e) {
            e.printStackTrace();
        }
    }

}
