package com.suning.sample;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestPattern {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String s = "“是！”应声依然整齐。←百度搜索→【ㄨ书?阅ぁ屋www.ShuYueWu.Com】整齐。←百度搜索→【ㄨ书?阅ぁ屋1www.ShuYueWu.Com】三垣四象再成阵势，虽威势不再，仍然让刚刚经历了生死";
        // Pattern p = Pattern.compile("(\\[[0-9]*\\])") ; //提取出来 [770]
        //Pattern p = Pattern.compile("(?<=【).*书?.*阅?.*(?=】)"); // /我想直接提取出来 770 不能包括 [1_1]中的 两个1
        Pattern p = Pattern.compile("(【(.*?[书|阅|屋|www|com]).*?】)");
        Matcher m = p.matcher(s);
        while (m.find()) {
            s  = s.replace(m.group(), "");
        }
        System.out.println(s);
    }
}
