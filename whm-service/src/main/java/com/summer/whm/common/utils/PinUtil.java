package com.summer.whm.common.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 拼音分析接口实现类
 * 
 * @author yanc
 */
public class PinUtil {

    final static Logger log = LoggerFactory.getLogger(PinUtil.class);

    private static Map<String, List<String>> pinyinMap = new HashMap<String, List<String>>();
    private static String fileName = "/opt/search/datasupport/configs/duoyinzi.dic";
    private static boolean isLoadDouyinzi = false;

    /**
     * 根据汉字生成拼音
     */
    public static String getPyByCn(String cn) {
        
        if(!isLoadDouyinzi){
            isLoadDouyinzi = true;
            initPinyin(fileName);
        }
        
        char[] t1 = null;
        t1 = cn.toCharArray();
        String[] t2 = null;
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
        StringBuffer t4 = new StringBuffer();
        int t0 = t1.length;
        try {
            for (int i = 0; i < t0; i++) {
                // 判断是否为汉字字符
                if (java.lang.Character.toString(t1[i]).matches("[\u4E00-\u9FA5]+")) {
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
                    try {
                        if (t2 != null) {
                            if (t2.length == 1) {
                                t4.append(t2[0]);
                            } else if (t2.length > 1) {
                                t4.append(convertDuoyizi(cn, i, t2));
                            }
                        }
                    } catch (NullPointerException ne) {
                        log.info("未找到" + t1[i] + "的拼音", ne);
                    }

                } else {
                    t4.append(java.lang.Character.toString(t1[i]));
                }

            }
            return t4.toString();
        } catch (BadHanyuPinyinOutputFormatCombination e1) {
            log.info("汉字转换拼音发生异常" + e1.getMessage());
        }
        return t4.toString();
    }

    /**
     * 根据汉字生成拼音首字母
     */
    public static String getPYHeadCharByCn(String cn) {
        if(!isLoadDouyinzi){
            isLoadDouyinzi = true;
            initPinyin(fileName);
        }
        
        char[] t1 = null;
        t1 = cn.toCharArray();
        String[] pinyinArray = null;
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
        int t0 = t1.length;
        StringBuffer convert = new StringBuffer();
        try {
            for (int j = 0; j < t0; j++) {
                char word = cn.charAt(j);
                pinyinArray = PinyinHelper.toHanyuPinyinStringArray(t1[j], t3);
                if (pinyinArray != null) {
                    if (pinyinArray.length == 1) {
                        convert.append(pinyinArray[0].charAt(0));
                    } else if (pinyinArray.length > 1) {
                        convert.append(convertDuoyizi(cn, j, pinyinArray).charAt(0));
                    }
                } else {
                    convert.append(word);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e1) {
            log.info("汉字转换拼音发生异常" + e1.getMessage());
        }
        return convert.toString();
    }

    public static String convertDuoyizi(String chinese, int i, String[] pinyi) {
        String ret = "";
        char[] arr = chinese.toCharArray();
        log.debug("多音字：" + arr[i]);
        int length = arr.length;
        int len = pinyi.length;

        boolean flag = false;

        String s = null;

        List<String> keyList = null;

        for (int x = 0; x < len; x++) {

            String py = pinyi[x];

            if (py.contains("u:")) { // 过滤 u:
                py = py.replace("u:", "v");
                System.out.println("filter u:" + py);
            }

            keyList = pinyinMap.get(py);

            if (i + 3 <= length) { // 后向匹配2个汉字 大西洋
                s = chinese.substring(i, i + 3);
                if (keyList != null && (keyList.contains(s))) {
                    ret = py;
                    flag = true;
                    break;
                }
            }

            if (i + 2 <= length) { // 后向匹配 1个汉字 大西
                s = chinese.substring(i, i + 2);
                if (keyList != null && (keyList.contains(s))) {
                    ret = py;
                    flag = true;
                    break;
                }
            }

            if ((i - 2 >= 0) && (i + 1 <= length)) { // 前向匹配2个汉字 龙固大
                s = chinese.substring(i - 2, i + 1);
                if (keyList != null && (keyList.contains(s))) {
                    ret = py;
                    flag = true;
                    break;
                }
            }

            if ((i - 1 >= 0) && (i + 1 <= length)) { // 前向匹配1个汉字 固大
                s = chinese.substring(i - 1, i + 1);
                if (keyList != null && (keyList.contains(s))) {
                    ret = py;
                    flag = true;
                    break;
                }
            }

            if ((i - 1 >= 0) && (i + 2 <= length)) { // 前向1个，后向1个 固大西
                s = chinese.substring(i - 1, i + 2);
                if (keyList != null && (keyList.contains(s))) {
                    ret = py;
                    flag = true;
                    break;
                }
            }
        }

        // 没匹配到返回第一个
        if (!flag) {
            ret = pinyi[0];
        }

        return ret;
    }

    /**
     * 初始化 所有的多音字词组
     * 
     * @param fileName
     * @throws FileNotFoundException
     */
    synchronized public static void initPinyin(String fileName) {
        // 读取多音字的全部拼音表;
        InputStream file = null;
        try {
            file = new FileInputStream(fileName);
        } catch (FileNotFoundException e1) {
            log.error(e1.getMessage());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(file));

        String s = null;
        try {
            while ((s = br.readLine()) != null) {

                if (s != null) {
                    String[] arr = s.split("#");
                    String pinyin = arr[0];
                    String chinese = arr[1];

                    if (chinese != null) {
                        String[] strs = chinese.split(" ");
                        List<String> list = Arrays.asList(strs);
                        pinyinMap.put(pinyin, list);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination {
        System.out.println(java.util.regex.Pattern.matches("[a-zA-Z]*", "fff"));// "^[a-zA-Z]{0,128}".matches("fff"))
        System.out.println(PinUtil.getPyByCn("美的空调"));
        System.out.println(PinUtil.getPYHeadCharByCn("美的空调"));
    }
}
