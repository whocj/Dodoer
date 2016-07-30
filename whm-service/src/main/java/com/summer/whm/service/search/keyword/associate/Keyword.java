package com.summer.whm.service.search.keyword.associate;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.summer.whm.common.utils.PinUtil;

public class Keyword {
    public final static String ZH = "zh";

    public final static String PIN = "pin";

    public final static String REGEX = "([\u4E00-\u9FA50-9]+|[a-zA-Z0-9]+)";
    public final static Pattern p = Pattern.compile(REGEX);
    private String[] name;
    private String[] type;
    
    private String keyword;
    
    private String srckeyword;
    
    public static Keyword init(String keyword) {
        
        List<String> list = new ArrayList<String>();
        if (StringUtils.isNotEmpty(keyword)) {
            Matcher m = p.matcher(keyword);
            while (m.find()) {
                list.add(m.group());
            }
            String[] names = new String[list.size()];
            String[] types = new String[list.size()];
            int index = 0;
            for (String s : list) {
                //s.matches("[a-zA-Z]+")
                if (s.matches("[\\u4E00-\\u9FA5]+")) {
                    types[index] = ZH;
                } else {
                    types[index] = PIN;
                }
                names[index] = s;
                index++;
            }

            return new Keyword(keyword, names, types);
        }

        return null;
    }

    public int length() {
        if (name != null) {
            return name.length;
        }
        return 0;
    }

    public Keyword() {
        super();
    }

    public Keyword(String keyword,String[] name, String[] type) {
        this.name = name;
        this.type = type;
        
        this.srckeyword = keyword;
        this.keyword = keyword.toLowerCase();
    }

    public Keyword(String[] name, String[] type) {
        super();
        this.name = name;
        this.type = type;
    }

    public String[] getName() {
        return name;
    }

    public void setName(String[] name) {
        this.name = name;
    }

    public String[] getType() {
        return type;
    }

    public void setType(String[] type) {
        this.type = type;
    }

    //全拼
    public String pin() {
        StringBuffer sb = new StringBuffer();
        int index = 0;
        for (String t : type) {
            if (PIN.equals(t)) {
                sb.append(name[index]);
            } else if (ZH.equals(t)) {
                sb.append(PinUtil.getPyByCn(name[index]));
            }
            index++;
        }

        return sb.toString();
    }

    //简拼
    public String spin() {
        StringBuffer sb = new StringBuffer();
        int index = 0;
        for (String t : type) {
            if (PIN.equals(t)) {
                sb.append(name[index]);
            } else if (ZH.equals(t)) {
                sb.append(PinUtil.getPYHeadCharByCn(name[index]));
            }
            index++;
        }

        return sb.toString();
    }
    
    public String name() {
        StringBuffer sb = new StringBuffer();
        for (String n : name) {
            sb.append(n);
        }

        return sb.toString();
    }

    public String getWildcardQuery() {
        int index = 0;
        StringBuffer sb = new StringBuffer();
        for (String t : type) {
            if (PIN.equals(t)) {
                sb.append("*");
            } else if (ZH.equals(t)) {
                sb.append(name[index]);
            }
            index++;
        }
        
        if(ZH.equals(type[index - 1])){
            sb.append("*");
        }
        
        return sb.toString();
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getSrckeyword() {
        return srckeyword;
    }

    public void setSrckeyword(String srckeyword) {
        this.srckeyword = srckeyword;
    }
}
