package com.shoufubang.model.util;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringTools {

	public final static String SHOWWITHTITLE="<li><div class='guest_img'><img onerror='nofind(this)' width='140' src='#{{href}}'></div><div class='guest_title'>#{{text}}</a></div></li>";
	
	/**
	 * 从<img>中取得图片url
	 * @param coverUrl
	 * @return
	 */
	public static String getUrlFromSrc(String tempCoverUrl){
		String coverUrl = "";
		if(!Common1.empty(tempCoverUrl)){
			//如果有封面图，只保存其url地址
			if(tempCoverUrl.indexOf("title=")!=-1){
				coverUrl = Common1.empty(tempCoverUrl)?"":tempCoverUrl.substring(tempCoverUrl.indexOf("src="), tempCoverUrl.indexOf("title="));
			}else{
				coverUrl = Common1.empty(tempCoverUrl)?"":tempCoverUrl.substring(tempCoverUrl.indexOf("src="));
			}
			if(!Common1.empty(coverUrl)){
				int begin = coverUrl.indexOf("http:")>0?coverUrl.indexOf("http"):coverUrl.indexOf("https");
				coverUrl = coverUrl.substring(begin);
				int end = coverUrl.lastIndexOf(".");
				int length = coverUrl.length();
				for(int i =end+1;i<length;i++){
					char c = coverUrl.charAt(i);
					if((c>64&&c<91)||(c>96&&c<123)){
						continue;
					}else{
						end =i;
						break;
					}
				}
				coverUrl = coverUrl.substring(0,end);
			}
		}
		return coverUrl;
	}
	
	/**
	 * 去掉文章中包含所有html标签
	 * @param articleContent
	 * @return
	 */
	public static String removeAllHtmlTag(String articleContent){
		StringBuffer sb = new StringBuffer(articleContent);
		while(sb.indexOf("<")>=0){
			int left = sb.indexOf("<");
			int right = sb.indexOf(">");
			sb = sb.replace(left, right+1, "");
		}
		String temp = sb.toString();
		while(temp.contains("&nbsp;")){
			temp = temp.replaceAll("&nbsp;", "");
		}
		return temp;
	}
	/**
	 * 
	 * @param obj
	 * @param regex
	 * @param replacement
	 * @return
	 */
	public static String replaceAllHtmlTagWithNewTag(Object obj,String regex, String replacement){
		if(Common1.empty(obj)){
			return "";
		}else{
			String str = obj.toString().replaceAll("<"+regex+">", "<"+replacement+">");
			str = str.replaceAll("</"+regex+">", "</"+replacement+">");
			return str;
		}
	}
	
	
	/**
	 * 获取html标签中属性（第一个出现）的值
	 * 
	 * @param coverUrl（包含html的文本）
	 * @param attr（属性名称）
	 * @return
	 */
	public static String getValueFromHtmlTag(String coverUrl,String attr){
		if(!Common1.empty(coverUrl)){
			int begin = coverUrl.indexOf(attr);
			begin+=attr.length()+2;
			int end = begin+1;
			int length = coverUrl.length();
			for(int i =end;i<length;i++){
				char c = coverUrl.charAt(i);
				if(c==34||c==39){
					end =i;
					break;
				}
			}
			return coverUrl.substring(begin,end);
		}
		return "";
	}
	
	/**
	 * "本期嘉宾"，"本期展示项目"预处理
	 * @param obj
	 * @return String(处理完后的html)
	 */
	public static String preProcess(Object obj){
		if(Common1.empty(obj)){
			return "";
		}else{
			String strSb = obj.toString();
			StringBuffer result = new StringBuffer();
			while(strSb.indexOf("src")>0){
				String imgSrc = getValueFromHtmlTag(strSb,"src");
				strSb = strSb.replaceFirst("src", "html");
				String imgTitle = getValueFromHtmlTag(strSb,"title");
				strSb = strSb.replaceFirst("title", "html");
				String temp = SHOWWITHTITLE;
				temp = temp.replace("#{{href}}", imgSrc);
				temp = temp.replace("#{{text}}", imgTitle);
				result.append(temp);
			}
			return result.toString();
		}
	}
	

	/**
	 * 获取多张图片的url地址list
	 * @param obj
	 * @return List<string>
	 */
	public static List<String> getImgSrc(Object obj){
		if(Common1.empty(obj)){
			return null;
		}else{
			List<String> srcs = new ArrayList<String>();
			String strSb = obj.toString();
			while(strSb.indexOf("src")>0){
				String imgSrc = getValueFromHtmlTag(strSb,"src");
				strSb = strSb.replaceFirst("src", "html");
				srcs.add(imgSrc);
			}
			return srcs;
		}
	}
	
	
	public static String indexShow(String head,String current){
		String indexFlag = "";
		String secureFlag = "";
		String investFlag = "";
		String loanappFlag = "";
		String loanappFlagFlag = "";
		String offlineFlag = "";
		if("@index@".contains(current)){
			indexFlag = "selected";
		}else if("@secure@".contains(current)){
			secureFlag = "selected";
		}else if("@invest@".contains(current)){
			investFlag = "selected";
		}else if("@loanapp@".contains(current)){
			loanappFlag = "selected";
		}else if("@about@".contains(current)){
			loanappFlagFlag = "selected";
		}else if("@offline@".contains(current)){
			offlineFlag = "selected";
		}
		head = head.replace("@index@", indexFlag);
		head = head.replace("@secure@", secureFlag);
		head = head.replace("@invest@", investFlag);
		head = head.replace("@loanapp@", loanappFlag);
		head = head.replace("@about@", loanappFlagFlag);
		head = head.replace("@offline@", offlineFlag);
		return head;
	}
	
	public static String IndexMunber2String(Object obj){
		StringBuffer result = new StringBuffer("");
		if(!Common1.empty(obj)){
			char numbers[] = obj.toString().toCharArray();
			int length = numbers.length;
			if(length==0){
				return "0";
			}
			int count=0;
			for(int i=length-1;i>=0;i--){
				char c = numbers[i];
				result.insert(0,"<span class='br da3623'>"+c+"</span>");
				count++;
				if(count%3==0&&count!=length){
					result.insert(0,",");
				}
			}
			return result.toString();
		}else{
			return "0";
		}
	}
	
	/**
	 * Object to String
	 * @param o
	 * @return
	 */
	public static String Obj2Str(Object o){
		String str="";
		if(!Common1.empty(o)){
			try{
				str = o.toString();
			}catch(Exception e){
				return "";
			}
		}
		return str;
	}
	
	public static String companyRating(int rating){
		switch(rating){
			case 1:
				return "A";
			case 2:
				return "AA";
			case 3:
				return "AAA";
			case 4:
				return "B";
			case 5:
				return "BB";
			case 6:
				return "BBB";
			default:
				return "C";
		}
	}
	
	/**
	 * 返回当前“担保机构”的次序
	 * @param name
	 * @param list
	 * @return
	 */
	public static int getGuaranteeOrder(String name,List<Map<String,Object>>list){
		int size = list.size();
		int i=0;
		for(;i<size;i++){
			String guarname = Obj2Str(list.get(i).get("guarname"));
			if(guarname.equals(name)){
				break;
			}
		}
		return (i+1);
	}

	/**
	 * 把部分字符串以”*“号代替，用于银行卡部分的显示
	 * @param s
	 * @param displayLength
	 * @return
	 */
	public static String hideString(String s,int displayLength)
	{
		if(s==null||s.length()==0 || displayLength>=s.length() )return s;
		if(displayLength<0) displayLength=0;
		char[] cs=s.toCharArray();
	 
		for(int i=0;i<cs.length-displayLength;i++) cs[i]='*';
		return new String(cs);
				
	}

	/**
	 * 截取字符串前18个，英文字符算一个，中文算两个
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String subFirst18String(String str) throws Exception {
		if(Common1.empty(str)){
			return "";
		}
		int length = 0;
		StringBuffer sb = new StringBuffer();         //截取后的字符串
		char[] charArray = str.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			char cn = charArray[i];
			byte[] bytes = (String.valueOf(cn)).getBytes("GBK");
			
			Boolean flag = true;                     //标识
			if (bytes.length == 1) {                  //英文字符
				if(length<=17){
					flag = false;
					length ++;
					sb.append(cn);
				}
			}else if (bytes.length == 2) {            //中文字符
				if(length<=16){
					flag = false;
					length ++;
					length ++;
					sb.append(cn);
				}
			}
			if(flag){
				break;
			}
		}
		return sb.toString();
	}
	
	/**
	 * 字符串长度，英文字符算一个，中文算两个
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static Long stringLength(String str) throws Exception {
		Long length = (long) 0;
		if(Common1.empty(str)){
			return length;
		}

		char[] charArray = str.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			char cn = charArray[i];
			byte[] bytes = (String.valueOf(cn)).getBytes("GBK");
			
			if (bytes.length == 1) {                  //英文字符
				length ++;
			}else if (bytes.length == 2) {            //中文字符
				length ++;
				length ++;
			}
		}
		return length;
	}
}
