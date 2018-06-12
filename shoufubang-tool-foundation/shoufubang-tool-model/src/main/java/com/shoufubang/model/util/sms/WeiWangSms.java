package com.shoufubang.model.util.sms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class WeiWangSms {

	protected Logger logger = Logger.getLogger(this.getClass());

	
	
	
	public static String send(String Phone ,String msg)throws UnsupportedEncodingException{
		
		 ResourceBundle resource = ResourceBundle.getBundle("sms-config");
		 String  postUrl = resource.getString("postUrl");
		 String sname = resource.getString("sname");
		 String spwd = resource.getString("spwd");
		 String sprdid = "";
		
		try {
			return send(postUrl, sname, spwd, sprdid, Phone, msg);
		} catch (UnsupportedEncodingException e) {
		
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	
	
		
	}

	/**
	 * 发送短信
	 * 
	 * @param url
	 * @param sname
	 *            用户
	 * @param spwd
	 *            密码
	 * 
	 */
	
	
	public static String send(String postUrl, String sname, String spwd, String sprdid, String sdst, String smsg) throws UnsupportedEncodingException{
		String postData = "sname=" + sname + "&spwd=" + spwd + "&scorpid=&sprdid=1012888&sdst=" + sdst + "&smsg=" + java.net.URLEncoder.encode(smsg, "utf-8");
		try {
			// 发送POST请求
			URL url = new URL(postUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setUseCaches(false);
			conn.setDoOutput(true);

			conn.setRequestProperty("Content-Length", "" + postData.length());
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			out.write(postData);
			out.flush();
			out.close();

			// 获取响应状态
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				System.out.println("connect failed!");
				return "连接失败";
			}
			// 获取响应内容体
			String line, result = "";
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			while ((line = in.readLine()) != null) {
				result += line + "\n";
			}
			in.close();
			//获取返回的状态码State,信息提示MsgState
			String error=result.substring(result.indexOf("<State>")+"<State>".length(), result.indexOf("</State>"));
			String message=result.substring(result.indexOf("<MsgState>")+"<MsgState>".length(), result.indexOf("</MsgState>"));
			if(!"0".equals(error)){
				return message;
			}
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
		return null;
	}	
	public static void main(String[] args) {
		try {
			String xx=WeiWangSms.send("http://cf.51welink.com/submitdata/service.asmx/g_Submit?","dlbjsfb0","hp8zow4r","","13058701456","【比特曼】尊敬的【帅哥】用户，您于[2017-1-9]成功充值￥[20000元]，其中手续费￥[0]。");
			System.out.println(xx);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
