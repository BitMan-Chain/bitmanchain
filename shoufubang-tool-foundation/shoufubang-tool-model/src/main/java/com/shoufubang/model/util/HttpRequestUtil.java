package com.shoufubang.model.util;



import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;






public class HttpRequestUtil {
	
	
	public static String getKeyFromCookies(HttpServletRequest request,String key) {
		return getKeyFromCookies(request.getCookies(), key);
	}
	private static String getKeyFromCookies(Cookie[] cookies,String key) {
		if (cookies != null && cookies.length > 0) {
			for (Cookie c : cookies) {
				if (c.getName().equals(key)) {
					return  URLUtil.decode(c.getValue());
				}
			}
		}
		return null;
	}
	public static Object getKeyFromSession(HttpServletRequest request, String key) {
		HttpSession session = request.getSession(false);
		return Common1.empty(session)?null:session.getAttribute(key);
	}
	public static void setKeyToSession(HttpServletRequest request, String key, String value){
		request.getSession(true).setAttribute(key, value);
	}
	public static void setKeyToCookies(HttpServletResponse response,
			String key, String value) {
		// 为当前用户生成一个新的cookie
		Cookie tmpCookie = new Cookie(key, URLUtil.encode(value));
		tmpCookie.setMaxAge(Constant.JSEESIONID_COOKIE_MAX_AGE);
		tmpCookie.setDomain(Constant.COOKIE_DOMAIN);
		tmpCookie.setPath("/");
		response.addCookie(tmpCookie);
	}
	
	public static void removeCookies(HttpServletResponse response,
			String key) {
		// 为当前用户生成一个新的cookie
		Cookie tmpCookie = new Cookie(key, "");
		tmpCookie.setMaxAge(0);
		tmpCookie.setDomain(Constant.COOKIE_DOMAIN);
		tmpCookie.setPath("/");
	
		response.addCookie(tmpCookie);
	}
}
