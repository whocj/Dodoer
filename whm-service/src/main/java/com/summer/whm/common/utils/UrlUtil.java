package com.summer.whm.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class UrlUtil{

  public static String encode(String url){
    try{
      return URLEncoder.encode(url,  "UTF-8");
    }catch(UnsupportedEncodingException e){
      return url;
    }
  }

  public static String decode(String url){
    try{
      return URLDecoder.decode(url, "UTF-8");
    }catch(UnsupportedEncodingException e){
      return url;
    }
  }

}
