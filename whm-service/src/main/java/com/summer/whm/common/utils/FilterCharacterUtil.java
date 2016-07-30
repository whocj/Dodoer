package com.summer.whm.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 特殊符号转换工具类
 * @author 14060329
 *
 */
public class FilterCharacterUtil {

	/**
	 * 特殊字符过滤
	 * 
	 * @param chara
	 * @return
	 */
	public static String filterCharacter(String chara) {
		//String regEx = "[`~^\\[\\]~￥……|{}【】]";
		String regEx = "[`~^\\[\\]~￥……|{}【】\"“\\\\]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(chara);
		chara = m.replaceAll(" ").trim().toLowerCase();
		//chara = toDBC(chara);
		//chara = toEnSymb(chara);
		return chara.trim().toLowerCase();
	}

	/**
	 * 半角转全角
	 * 
	 * @param input
	 *            String.
	 * @return 全角字符串.
	 */
	public static String toSBC(String input) {
		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == ' ') {
				c[i] = '\u3000';
			} else if (c[i] < '\177') {
				c[i] = (char) (c[i] + 65248);

			}
		}
		return new String(c);
	}

	/**
	 * 全角转半角
	 * 
	 * @param input
	 *            String.
	 * @return 半角字符串
	 */
	public static String toDBC(String input) {

		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == '\u3000') {
				c[i] = ' ';
			} else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
				c[i] = (char) (c[i] - 65248);

			}
		}
		String returnString = new String(c);

		return returnString;
	}
	
	/**
	 * 中文标点转英文标点
	 * @param input
	 * @return
	 */
	public static String toEnSymb(String input) {
		String[] znSymb = { "！", "，", "。", "；", "《", "》", "：", "？", "、", "“", "”", "‘", "’", "（", "）", "——","【","】","……","￥","×","＼"};
        String[] enSymb = { "!", ",", ".", ";", "<",">", ":", "?", "\\", "\"", "\"", "'", "'","(", ")","_","[","]","^","$","*","\\"};
		for ( int i = 0; i < znSymb.length ; i++ )
        {
        	input = input.replace(znSymb[i], enSymb[i]);
        }
        return input;
	}
	
	public static void main(String[] args) {
        String QJstr1 = "ｈｅｌｌｏ。％强力清除广告，上网更快的’浏览器‘！搞“什么”飞机嘛。我去；《小说》";

        String result1 = filterCharacter("{[【手机shouji34“\"2342\\3￥");

        System.out.println(QJstr1 + "\n" + result1);
        
	} 
}