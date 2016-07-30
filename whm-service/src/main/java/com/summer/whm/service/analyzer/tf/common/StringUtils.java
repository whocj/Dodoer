/*
 * IBM Confidential
 * 
 * Copyright IBM Corp. 2014,
 *
 * The source code for this program is not published or otherwise divested of its trade secrets,
 * irrespective of what has been deposited with the U.S. Copyright Office.
 */

package com.summer.whm.service.analyzer.tf.common;

public class StringUtils {
	public static String join(String[] arg){
		return join(arg, " ", 0, arg.length);
	}
	public static String join(String[] arg, String sep){
		return join(arg, sep, 0, arg.length);
	}
	
	public static String join(String[] arg, String sep, int start, int end){
		StringBuilder sb = new StringBuilder();
		for(int i=start; i<end; i++){
			if(i > start)
				sb.append(sep);
			sb.append(arg[i]);
		}
		return sb.toString();
	}
}
