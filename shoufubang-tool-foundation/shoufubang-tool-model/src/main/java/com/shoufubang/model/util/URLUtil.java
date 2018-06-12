package com.shoufubang.model.util;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class URLUtil {

	/**
	 * url 编码
	 * @author cheng
	 * 2014年3月12日
	 * 上午10:30:19
	 * @param url
	 * @return
	 */
	public static String encode(String url){
		try {
			if(url==null){
				return "" ;
			}
			return URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}
	/**
	 * url 解码
	 * @author cheng
	 * 2014年3月12日
	 * 上午10:30:32
	 * @param url
	 * @return
	 */
	public static String decode(String url){
		try {
			return URLDecoder.decode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
