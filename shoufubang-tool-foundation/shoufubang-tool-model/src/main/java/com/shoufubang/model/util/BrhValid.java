package com.shoufubang.model.util;



import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shoufubang.model.exception.UnChkException;

import htz.util.format.mustache.Mustache;
import htz.util.lang.Verification;



/**
 * 验证参数
 * 
 * @author 
 * 
 */

public class BrhValid {
	private static Logger logger = LoggerFactory.getLogger(BrhValid.class);
	private static final String accept = "请输入拥有合法后缀名的字符串";
	private static final String mobile = "请输入合法的手机号";
	private static final String date = "请输入合法的日期";
	private static final String dateISO = "请输入合法的日期 (ISO).";
	private static final String nullObj = "需要验证的不存在";
	private static final String digits = "只能输入整数";
	private static final String digit = "请输入一个 小数点后只有{{0}}位的数字";
	private static final String email = "请输入正确格式的电子邮件";
	private static final String idCard = "请输入合法的身份证";
	private static final String idList = "传入的必须是id列表";
	private static final String max = "请输入一个最大为 {{0}} 的值";
	private static final String maxlength = "请输入一个长度最多是{{0}} 的字符串";
	private static final String min = "请输入一个最小为 {{0}} 的值";
	private static final String minlength = "请输入一个长度最少是 {{0}} 的字符串";
	private static final String minArraylength = "请输入一个长度最少是 {{0}} 的数组";
	private static final String maxArraylength = "请输入一个长度最多是 {{0}} 的数组";
	private static final String number = "请输入合法的数字";
	private static Map<String, String> prop = new HashMap<String, String>();
//	private static String PROPER_FILE = Global.Root + "val.properties";
	private static final String range = "请输入一个介于{{0}} 和 {{1}} 之间的值";

	private static final String rangelength = "请输入一个长度介于 {{0}} 和 {{1}} 之间的字符串";
	private static final String arraylength = "请输入一个长度介于 {{0}} 和 {{1}} 之间的数组";

	private static final String required = "必选字段";
	
	private static final String str = "请输入合法的字符串";

	private static final String url = "请输入合法的网址";
	private Map<String, String> errors = new HashMap<String, String>();
	private String form_item;
	private boolean isVal = true;
	private Map<String, Map<String, String>> messages = new HashMap<String, Map<String, String>>();
	private String[] o;

	private HttpServletRequest request;
	private boolean flag = true;

	private BrhValid() {

	}

	/*
	 * static { try { propHelper = new PropertiesHelper(PROPER_FILE); Properties
	 * config = propHelper.getProperties(); Set<Object> keys = config.keySet();
	 * for (Object key : keys) { String k = (String) key; String v = (String)
	 * config.get(key); prop.put(k, v); } } catch (IOException e) { throw new
	 * UnChkException("读取配置文件" + PROPER_FILE + "出错"); }
	 * 
	 * }
	 */

	public BrhValid(HttpServletRequest req) {
		this.request = req;
	}

	/**
	 * 合法日期验证
	 * 
	 * @name 方法名： date
	 * @author 作者： stone
	 * @version 创建时间： 2011-11-14 上午10:51:18
	 * @function 功能： 该方法...
	 * @return D7Valid
	 */
	public BrhValid date() {
		if (!flag) {
			return this;
		}
		for (int i = 0; i < o.length; i++) {
			if (!isNull(o[i])) {
				if (!checkDate(o[i])) {
					errors.put("date", BrhValid.date);
					isVal = false;
				}
			}
		}
		return this;
	}
	
	/**
	 * 合法手机号验证
	 * 
	 * @name 方法名： date
	 * @author 作者： stone
	 * @version 创建时间： 2011-11-14 上午10:51:18
	 * @function 功能： 该方法...
	 * @return D7Valid
	 */
	public BrhValid mobile() {
		if (!flag) {
			return this;
		}
		for (int i = 0; i < o.length; i++) {
			if (!isNull(o[i])) {
				if (!checkMobile(o[i])) {
					errors.put("mobile", BrhValid.mobile);
					isVal = false;
				}
			}
		}
		return this;
	}

	/**
	 * 校验不能包含连续的几位数字
	 * @return
	 */
	public BrhValid containsContinouus(int num) {
		if (!flag) {
			return this;
		}
		for (int i = 0; i < o.length; i++) {
			if (!isNull(o[i])) {
				if (checkContinouus(o[i],num)) {
					errors.put("str", BrhValid.str);
					isVal = false;
				}
			}
		}
		return this;
	}
	
	
	
	
	/**
	 * 只能是整数
	 * 
	 * @name 方法名： digits
	 * @author 作者： stone
	 * @version 创建时间： 2011-11-14 上午10:52:52
	 * @function 功能： 该方法...
	 * @return D7Valid
	 */
	public BrhValid digits() {
		if (!flag) {
			return this;
		}
		for (int i = 0; i < o.length; i++) {
			if (!isNull(o[i])) {
				if(o[i].length()>20){
					errors.put("digits","数额过大");
					return this;
				}else{
					try {
						Long.valueOf(o[i]);
					} catch (NumberFormatException e) {
						isVal = false;
						errors.put("digits", BrhValid.digits);
					}
				}
			}
		}

		return this;
	}

	/**
	 * 判断小数点后多少位
	 * @param digit
	 * @return
	 */
	public BrhValid digitPoint(int digit) {
		if (!flag) {
			return this;
		}
		for (int i = 0; i < o.length; i++) {
			if (!isNull(o[i])) {
				String [] strs = o[i].split("\\.");
				if(!(!Common1.empty(strs)&&(strs.length==digit||strs[1].length()<=digit))){
					isVal = false;
					errors.put("digit", getMustache(BrhValid.digit, context("0",
							digit)));
				}
			}
		}

		return this;
	}

	/**
	 * 邮箱验证
	 * 
	 * @name 方法名： Email
	 * @author 作者： stone
	 * @version 创建时间： 2011-11-14 上午10:34:45
	 * @function 功能： 该方法...
	 * @return D7Valid
	 */
	public BrhValid Email() {
		if (!flag) {
			return this;
		}
		for (int i = 0; i < o.length; i++) {
			if (!isNull(o[i])) {
				if (!Common1.isEmail(o[i])) {
					errors.put("email", BrhValid.email);
					isVal = false;
				}
			}
		}
		return this;
	}

	public BrhValid end() {

		Map<String, String> rs = new HashMap<String, String>();
		rs.putAll(errors);

		this.o = null;

		errors = new HashMap<String, String>();

		messages.put(this.form_item, rs);
		return this;
	}

	public Map<String, Map<String, String>> getMessages() {
		return messages;
	}
	
	/**
	 * 验证参数是否为id列表.默认分隔符为逗号
	 * 
	 * @name 方法名： idList
	 * @author 作者： stone
	 * @version 创建时间： 2011-11-14 上午10:33:00
	 * @function 功能： 该方法...
	 * @return D7Valid
	 */
	public BrhValid idList() {
		return idList(null);
	}

	/**
	 * 验证参数是否为id列表.提供分隔符选项
	 * 
	 * @name 方法名： idList
	 * @author 作者： stone
	 * @version 创建时间： 2011-11-14 上午10:30:29
	 * @function 功能： 该方法...
	 * @return D7Valid
	 */
	public BrhValid idList(String compart) {
		if (!flag) {
			return this;
		}
		for (int i = 0; i < o.length; i++) {
			if (!isNull(o[i])) {
				try {
					String temp = o[i];
					String[] k = temp.split((compart == null) ? "," : compart);
					for (String str : k) {
						Double.valueOf(str);
					}
				} catch (NumberFormatException e) {
					errors.put("idList", BrhValid.idList);
					this.isVal = false;
				}
			}
		}
		return this;
	}

	public boolean isVal() {
		return isVal;
	}

	/**
	 * 数字的最大值
	 * 
	 * @name 方法名： max
	 * @author 作者： stone
	 * @version 创建时间： 2011-11-11 下午06:47:38
	 * @function 功能： 该方法...
	 * @param maxl
	 * @return D7Valid
	 */
	public BrhValid max(int maxl) {
		if (!flag) {
			return this;
		}
		if (maxl <= 0) {
			errors.put("max","max方法传入的值必须大于0");
			return this;
		}
		for (int i = 0; i < o.length; i++) {
			if (!isNull(o[i])) {
				if (!isNumber(o[i])) {
					errors.put("max", "max方法验证的长度必须是数字");
					isVal = false;
					return this;
				}
				Double temp = Double.valueOf(o[i]);
				if (temp > maxl) {
					errors.put("max", getMustache(BrhValid.max, context("0",
							maxl)));
					isVal = false;
				}
			}
		}
		return this;
	}

	/**
	 * 验证字符串长度,必须小于等于最大长度
	 * 
	 * @name 方法名： maxlength
	 * @author 作者： stone
	 * @version 创建时间： 2011-11-11 下午02:10:29
	 * @function 功能： 该方法...
	 * @param maxl
	 * @return D7Valid
	 */
	public BrhValid maxlength(int maxl) {
		if (!flag) {
			return this;
		}
		if (maxl <= 0) {
			errors.put("maxlength", "maxlength方法传入的值必须大于0");
			isVal = false;
			return this;
		}
		for (int i = 0; i < o.length; i++) {
			if (!isNull(o[i])) {
				if (!isString(o[i])) {
					errors.put("maxlength", "maxlength方法验证的长度必须是字符串");
					isVal = false;
					return this;
				}

				if (o[i].length() > maxl) {
					errors.put("maxlength", getMustache(BrhValid.maxlength,
							context("0", maxl)));
					isVal = false;
				}
			}
		}
		return this;
	}
	
	/**
	 * 验证数组长度,必须小于等于最大长度
	 * 
	 * @name 方法名： maxArraylength
	 * @author 作者： stone
	 * @version 创建时间： 2011-11-11 下午02:10:29
	 * @function 功能： 该方法...
	 * @param maxl
	 * @return D7Valid
	 */
	public BrhValid maxArraylength(int maxl) {
		if (!flag) {
			return this;
		}
		if (maxl <= 0) {
			errors.put("maxArraylength", "maxArraylength方法传入的值必须大于0");
			isVal = false;
			return this;
		}
		if (o.length > maxl) {
			errors.put("maxArraylength", getMustache(BrhValid.maxArraylength,
					context("0", maxl)));
			isVal = false;
		}
		return this;
	}

	/**
	 * 数字的最小值
	 * 
	 * @name 方法名： min
	 * @author 作者： stone
	 * @version 创建时间： 2011-11-11 下午06:47:28
	 * @function 功能： 该方法...
	 * @param minl
	 * @return D7Valid
	 */
	public BrhValid min(int minl) {
		if (!flag) {
			return this;
		}
		if (minl < 0) {
			errors.put("min", "min方法传入的值必须大于0");
			isVal = false;
			return this;
		}
		for (int i = 0; i < o.length; i++) {
			if (!isNull(o[i])) {
				if (!isNumber(o[i])) {
					errors.put("min", "min方法验证的长度必须是数字");
					isVal = false;
					return this;
				}
				Double temp = Double.valueOf(o[i]);
				if (temp < minl) {
					errors.put("min", getMustache(BrhValid.min, context("0",
							minl)));
					isVal = false;
				}
			}
		}
		return this;
	}
	
	/**
	 * 
	 */
	public BrhValid isDouble() {
		if (!flag) {
			return this;
		}

		for (int i = 0; i < o.length; i++) {
			if (!isNull(o[i])) {
				if(!isD(o[i])){
					isVal = false;
				}
			}
		}
		return this;
	}
	
	
	/**
	 *  充值金额验证
	 * @return
	 */
	public BrhValid isPlusDouble() {
		if (!flag) {
			return this;
		}

		for (int i = 0; i < o.length; i++) {
			if (!isNull(o[i])) {
				if(!isD(o[i])){
					isVal = false;
				}else{
					double d=Double.valueOf(o[i]);
					if(d<=0)
						isVal=false;
				}
			}
		}
		return this;
	}
	
	/**
	 * 验证一个srt 是否是 Double
	 * @param str
	 * @return
	 */
	private boolean isD(String str){
		try
		   {
		      Double.parseDouble(str);
		      return true;
		   }
		   catch(NumberFormatException ex){}
		   return false;
	}
	
	
	

	/**
	 * 验证字符串长度,必须大于等于最小长度
	 * 
	 * @name 方法名：minArraylength
	 * @author 作者： stone
	 * @version 创建时间： 2011-11-11 下午02:10:29
	 * @function 功能： 该方法...
	 * @return D7Valid
	 */
	public BrhValid minArraylength(int minl) {
		if (!flag) {
			return this;
		}
		if (minl <= 0) {
			errors.put("minArraylength", "minArraylength方法传入的值必须大于0");
			isVal = false;
			return this;
		}

		if (o.length < minl) {
			errors.put("minArraylength", getMustache(BrhValid.minArraylength,
					context("0", minl)));
			isVal = false;
		}
		return this;
	}

	/**
	 * 验证数组长度,必须大于等于最小长度
	 * 
	 * @name 方法名： minlength
	 * @author 作者： stone
	 * @version 创建时间： 2011-11-11 下午02:10:29
	 * @function 功能： 该方法...
	 * @return D7Valid
	 */
	public BrhValid minlength(int minl) {
		if (!flag) {
			return this;
		}
		if (minl <= 0) {
			errors.put("minlength", "minlength方法传入的值必须大于0");
			isVal = false;
			return this;
		}
		for (int i = 0; i < o.length; i++) {
			if (!isNull(o[i])) {
				if (!isString(o[i])) {
					errors.put("minlength", "minlength方法验证的长度必须是字符串");
					isVal = false;
					return this;
				}

				if (o[i].length() < minl) {
					errors.put("minlength", getMustache(BrhValid.minlength,
							context("0", minl)));
					isVal = false;
				}
			}
		}
		return this;
	}
	
	/**
	 * 合法的数字
	 * 
	 * @name 方法名： number
	 * @author 作者： stone
	 * @version 创建时间： 2011-11-11 下午06:56:29
	 * @function 功能： 该方法...
	 * @return D7Valid
	 */
	public BrhValid number() {
		if (!flag) {
			return this;
		}
		for (int i = 0; i < o.length; i++) {
			if (!isNull(o[i])) {
				if (!isNumber(o[i])) {
					isVal = false;
					errors.put("number", BrhValid.number);
				}
			}
		}
		return this;
	}

	/**
	 * 验证数字的大小，必须在最大和最小值之间
	 * 
	 * @name 方法名： range
	 * @author 作者： stone
	 * @version 创建时间： 2011-11-11 下午06:47:07
	 * @function 功能： 该方法...
	 * @param minl
	 * @param maxl
	 * @return D7Valid
	 */
	public BrhValid range(int minl, int maxl) {
		if (!flag) {
			return this;
		}
		if (minl > maxl) {
			errors.put("range","range方法中最大值必须比最小值大");
			isVal = false;
			return this;
		}
		for (int i = 0; i < o.length; i++) {
			if (!isNull(o[i])) {
				if (!isNumber(o[i])) {
					errors.put("range", "range方法验证的长度必须是数字");
					isVal = false;
					return this;
				}
				Double temp = Double.valueOf(o[i]);
				if (temp < minl || temp > maxl) {
					errors.put("range", getMustache(BrhValid.range, context("0",
							minl, "1", maxl)));
					isVal = false;
				}
			}
		}
		return this;
	}

	/**
	 * 验证字符串长度必须在最大值和最小值之间
	 * 
	 * @name 方法名： rangelength
	 * @author 作者： stone
	 * @version 创建时间： 2011-11-11 下午06:46:37
	 * @function 功能： 该方法...
	 * @param minl
	 * @param maxl
	 * @return D7Valid
	 */
	public BrhValid rangelength(int minl, int maxl) {
		if (!flag) {
			return this;
		}
		if (minl > maxl) {
			errors.put("rangelength", "rangelength方法中最大值必须比最小值大");
			isVal = false;
			return this;
		}
		for (int i = 0; i < o.length; i++) {
			if (!isNull(o[i])) {
				if (!isString(o[i])) {
					errors.put("rangelength", "rangelength方法验证的长度必须是字符串");
					isVal = false;
					return this;
				}

				if (o[i].length() < minl || o[i].length() > maxl) {
					errors.put("rangelength", getMustache(BrhValid.rangelength,
							context("0", minl, "1", maxl)));
					isVal = false;
				}
			}
		}
		return this;
	}
	
	/**
	 * 验证用户名username长度必须在最大值和最小值之间
	 * 
	 * @name 方法名： rangelength
	 * @author 作者： stone
	 * @version 创建时间： 2011-11-11 下午06:46:37
	 * @function 功能： 该方法...
	 * @param minl
	 * @param maxl
	 * @return D7Valid
	 */
	public BrhValid usernamerangelength(int minl, int maxl) {
		if (!flag) {
			return this;
		}
		if (minl > maxl) {
			errors.put("rangelength", "usernamerangelength方法中最大值必须比最小值大");
			isVal = false;
			return this;
		}
		for (int i = 0; i < o.length; i++) {
			if (!isNull(o[i])) {
				if (!isString(o[i])) {
					errors.put("rangelength", "usernamerangelength方法验证的长度必须是字符串");
					isVal = false;
					return this;
				}

				//字符串长度
				try {
					Long length = StringTools.stringLength(o[i]+"");
					if (length < minl || length > maxl) {
						errors.put("rangelength", "请输入一个长度介于 "+ minl + "和 "+maxl+" 之间的字符串");
						isVal = false;
					}
				} catch (Exception e) {
					errors.put("rangelength", "获得字符串长度出错");
					isVal = false;
				}
				
			}
		}
		return this;
	}
	
	/**
	 * 验证数组长度必须在最大值和最小值之间
	 * 
	 * @name 方法名： arraylength
	 * @author 作者： stone
	 * @version 创建时间： 2011-11-11 下午06:46:37
	 * @function 功能： 该方法...
	 * @param minl
	 * @param maxl
	 * @return D7Valid
	 */
	public BrhValid arraylength(int minl, int maxl) {
		if (!flag) {
			return this;
		}
		if (minl > maxl) {
			errors.put("arraylength", "arraylength方法中最大值必须比最小值大");
			isVal = false;
			return this;
		}
		if (o.length < minl || o.length > maxl) {
			errors.put("arraylength", getMustache(BrhValid.arraylength,
					context("0", minl, "1", maxl)));
			isVal = false;
		}
		return this;
	}
	/**
	 * 判断是否必填字段
	 * 
	 * @name 方法名： required
	 * @author 作者： stone
	 * @version 创建时间： 2011-11-11 下午02:57:08
	 * @function 功能： 该方法...
	 * @return D7Valid
	 */
	public BrhValid required() {
		if (!flag) {
			this.isVal = false;
			return this;
		}
		for (int i = 0; i < o.length; i++) {
			if (isNull(o[i])) {
				errors.put("required", BrhValid.required);
				this.isVal = false;
			}
		}
		return this;
	}

	public void setRequest(HttpServletRequest req) {
		// TODO Auto-generated method stub
		this.request = req;
	}

	/**
	 * 
	 * @name 方法名： start
	 * @author 作者： stone
	 * @version 创建时间： 2011-11-11 下午03:31:06
	 * @function 功能： 该方法...
	 * @param form_item
	 * @return D7Valid
	 */

	public BrhValid start(String form_item) {
		flag = true;
		this.o = request.getParameterValues(form_item);
		if (o == null) {
			logger.warn("需要验证的" + form_item + "不存在");
			flag = false;
			//this.isVal = false;
		}
		this.form_item = form_item;
		return this;

	}
	/**
	 * 手机和邮箱至少有一个必填
	 */
	public BrhValid minOne(String from_item1,String from_item2){
		
		String[] obj1=request.getParameterValues(from_item1);
		String[] obj2=request.getParameterValues(from_item2);
		
		if(Common1.empty(obj1) && Common1.empty(obj2)){
			logger.warn(from_item1+"和"+from_item2+"至少填入一个");
			flag=false;
			this.isVal=false;
			errors.put("mobileOrEmail", "手机和邮箱至少填入一个");
		}
		return this;
	}
	/**
	 * 何方字符串
	 * 
	 * @name 方法名： string
	 * @author 作者： stone
	 * @version 创建时间： 2011-11-14 上午10:38:26
	 * @function 功能： 该方法...
	 * @return D7Valid
	 */
	public BrhValid string() {
		if (!flag) {
			return this;
		}
		for (int i = 0; i < o.length; i++) {
			if (!isNull(o[i])) {
				if (!isString(o[i])) {
					isVal = false;
					errors.put("number", BrhValid.number);
				}
			}
		}
		return this;
	}

	private boolean checkDate(Object date) {
		String eL = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1][0-9])|([2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(date.toString());
		boolean b = m.matches();
		return b;
	}

	private boolean checkHomePhone(Object phone) {
		String eL = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1][0-9])|([2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(phone.toString());
		boolean b = m.matches();
		return b;
	}
	
	private boolean checkContinouus(Object form_item,int num){
		Pattern p = Pattern.compile(".*\\d{"+num+"}.*");
        Matcher m = p.matcher(form_item.toString());
        return m.matches();
	} 
	
	private  boolean checkMobile(Object mobiles){  
		return Common1.checkMobile(mobiles);  
		  
	}  

	private Object context(Object... data) {
		Map<String, Object> ctx = new HashMap<String, Object>();
		for (int ii = 0; ii < data.length; ii += 2) {
			ctx.put(data[ii].toString(), data[ii + 1]);
		}
		return ctx;
	}

	private String getMustache(String key, Object obj) {
		return Mustache.compiler().compile(key).execute(obj);
	}

	/**
	 * 得到配置文件对应key的值。若key不存在。则抛出异常。
	 * 
	 * @name 方法名： getPropertiesVal
	 * @author 作者： stone
	 * @version 创建时间： 2011-11-1 上午10:28:26
	 * @function 功能： 该方法...
	 * @param key
	 * @return String
	 */

	private String getPropertiesVal(String key) {
		if (prop.containsKey(key)) {
			return prop.get(key);
		} else {
			throw new UnChkException("传入" + key + "不再配置文件 中");
		}
	}

	/**
	 * 判断对象是否为空
	 * 
	 * @name 方法名： isNull
	 * @author 作者： stone
	 * @version 创建时间： 2011-11-14 上午09:49:10
	 * @function 功能： 该方法...
	 * @return boolean
	 */
	private boolean isNull(String s) {
		if (s == null || ("".equals(s))) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isNumber(Object obj) {
		boolean flag = true;
		if (obj instanceof String) {

			try {
				Double temp = Double.valueOf(obj.toString());
			} catch (Exception e) {
				flag = false;
			}

		} else if (obj instanceof Number) {

		} else {
			logger.error("验证对象不为字符串，也不为数字");
			flag = false;
		}

		return flag;
	}

	private boolean isString(Object obj) {
		if (obj instanceof String) {
			return true;
		} else if (obj instanceof Number) {
			return false;
		} else {
			logger.error("验证对象不为字符串，也不为数字");
			return false;
		}
	}

	public BrhValid maxConentlength(int maxl) {
		// TODO Auto-generated method stub
		if (!flag) {
			return this;
		}
		if (maxl <= 0) {
			logger.error("maxlength方法传入的值必须大于0");
			return this;
		}
		for (int i = 0; i < o.length; i++) {
			if (!isNull(o[i])) {
				if (!isString(o[i])) {
					logger.error("maxlength方法验证的长度必须是字符串");
					return this;
				}
				int length = o[i].length();
				Pattern p=Pattern.compile("@\\S+\\s"); 
				Matcher m=p.matcher(o[i]);
				int num = 0;
				int count = 0;
				while(m.find()) {
					for(int j = 0; j <= m.groupCount(); j++){
						num = num + m.group(j).length();
						count++;
					}
				}
				num = count-num;
				length = length + num;
				if (length> maxl) {
					errors.put("maxlength", getMustache(BrhValid.maxlength,
							context("0", maxl)));
					isVal = false;
				}
			}
		}
		return this;
	}

	public void setVal(boolean isVal) {
		this.isVal = isVal;
	}
	
	/**
	 * 第一个字符为非数字
	 * @return
	 */
	public BrhValid isNotNumberStart() {
		if (!flag) {
			return this;
		}
		for (int i = 0; i < o.length; i++) {
			if (!isNull(o[i])) {
				if (!Verification.isNotNumberStart(o[i])) {
					isVal = false;
					errors.put("isNotNumberStart", "不能以数字开头");
				}
			}
		}
		return this;
	}
	
	/**
	 * 是否包含特殊字符script iframe < >
	 * @return
	 */
	public BrhValid isSpeHtml() {
		if (!flag) {
			return this;
		}
		String[] spe = new String[]{"script","iframe","<",">"};
		for (int i = 0; i < o.length; i++) {
			if (!isNull(o[i])) {
				if (!Verification.isContainSpeHtml(o[i],spe)) {
					isVal = false;
					errors.put("isSpeHtml", "不能包含特殊字符");
				}
			}
		}
		return this;
	}
	
}
