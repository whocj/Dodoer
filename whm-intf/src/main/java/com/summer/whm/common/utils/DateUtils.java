package com.summer.whm.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    public static final Locale LOCALE_CHINA = Locale.CHINA;
    
    private DateUtils() {
    }

    //计算两个时间相差天数
    public static long compare2Day(Date date, Date date2){
        if(date != null && date2 != null){
            long l = date.getTime();
            long l2 = date2.getTime();
            long temp = l2 - l;//毫秒
            long t = Math.abs(temp) / 1000 / 24 / 60 / 60;
            return t;
        }else{
            return -1;
        }
    }
    
    public static String currentDate(String pattern) {
        return formatDate(pattern, new Date());
    }

    public static String formatDate(String pattern, Date date) {
        SimpleDateFormat format = new SimpleDateFormat(pattern, LOCALE_CHINA);
        return format.format(date);
    }

    /**
     * 指定locale格式化日期
     * 
     * @param pattern
     * @param date
     * @param locale
     * @return
     */
    public static String formatDate(String pattern, Date date, Locale locale) {
        SimpleDateFormat format = new SimpleDateFormat(pattern, locale);
        return format.format(date);
    }

    /**
     * 解析日期，注:此处为严格模式解析，即20151809这样的日期会解析错误
     * 
     * @param pattern
     * @param date
     * @return
     */
    public static Date parse(String pattern, String date) {
        return parse(pattern, date, LOCALE_CHINA);
    }

    /**
     * 解析日期，注:此处为严格模式解析，即20151809这样的日期会解析错误
     * 
     * @param pattern
     * @param date
     * @param locale
     * @return
     */
    public static Date parse(String pattern, String date, Locale locale) {
        SimpleDateFormat format = new SimpleDateFormat(pattern, locale);
        format.setLenient(false);
        Date result = null;
        try {
            result = format.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void main(String[] args){
        Date date = new Date();
        Date date2 = DateUtils.parse("yyyy-MM-dd", "2016-7-5");
        
        //System.out.println(Math.abs(compare2Day(date, date2)) / 1000 / 24 / 60 / 60);
        System.out.println(compare2Day(date, null) > 30);
    }
}
