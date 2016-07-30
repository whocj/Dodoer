package com.summer.whm.spider.examples;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlHiddenInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlRadioButtonInput;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class BaiduGaojiSpider {
    public static void main(String[] args) {

        try {
            final WebClient webclient = new WebClient(BrowserVersion.FIREFOX_3, "10.19.110.31", 8080);
            final HtmlPage htmlpage = webclient.getPage("http://www.baidu.com/gaoji/advanced.html");

            // 搜索按钮
            final HtmlForm form = htmlpage.getFormByName("f1");
            final HtmlSubmitInput button = form.getInputByValue("百度一下");

            // 搜索结果-关键词
            final HtmlTextInput textField = form.getInputByName("q1");
            textField.setValueAttribute("HTML我帮您");

            // 分页条数
            final HtmlSelect htmlSelet = form.getSelectByName("rn");
            htmlSelet.setDefaultValue("10");

            // 网页的时间
            final HtmlSelect htmlSeletlm = form.getSelectByName("rn");
            htmlSeletlm.setDefaultValue("0");

            // 语言
            final List<HtmlRadioButtonInput> radioButtonCts = form.getRadioButtonsByName("ct");
            radioButtonCts.get(0).setChecked(true);
            radioButtonCts.get(1).setChecked(false);
            radioButtonCts.get(2).setChecked(false);

            // 文档格式
            final HtmlSelect htmlSeletft = form.getSelectByName("ft");
            htmlSeletft.setDefaultValue("");

            // 关键词位置
            final List<HtmlRadioButtonInput> radioButtonq5s = form.getRadioButtonsByName("q5");
            radioButtonq5s.get(0).setChecked(true);
            radioButtonq5s.get(1).setChecked(false);
            radioButtonq5s.get(2).setChecked(false);

            // 站内搜索 限定要搜索指定的网站
            final HtmlTextInput htmlTextInputq6 = form.getInputByName("q6");
            htmlTextInputq6.setDefaultValue("html580.com");

            // 隐藏值
            final HtmlHiddenInput hiddenInputtn = form.getInputByName("tn");
            hiddenInputtn.setDefaultValue("baiduadv");
            final HtmlPage page2 = button.click();
            String result = page2.asXml();
            System.out.println(result);
            webclient.closeAllWindows();
        } catch (FailingHttpStatusCodeException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
