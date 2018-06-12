package com.shoufubang.model.util;



import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class MapUtil {

	private static Logger log = LoggerFactory.getLogger("ERROR");
	
	private static final String INT = "int";
	private static final String DOUBLE = "double";
	
	private static Map<String, Object> allMap;
	
	public static Map<String, Object> mapRedis = null;
	static {
		mapRedis = new HashMap<String, Object>();
	}
	
	public static void setMap(String key, Object value) {
		if(null == mapRedis) {
			mapRedis = new HashMap<String, Object>();
		}
		mapRedis.put(key, value);
	}
	
	public static Map<String, Object> getMap() {
		return mapRedis;
	}
	
	
	/**
	 * 对象转map
	 * 
	 * @param thisObj
	 * @return
	 */
	public static Map objectToMap(Object thisObj) throws Exception {
		Map map = new HashMap();
		Class c;
		c = Class.forName(thisObj.getClass().getName());
		Method[] m = c.getMethods();
		for (int i = 0; i < m.length; i++) {
			String method = m[i].getName();
			if (method.startsWith("get")) {
				try {
					Object value = m[i].invoke(thisObj);
					if (value != null) {
						String key = method.substring(3);
						if ("Class".equals(key)) {
							continue;
						}
						key = key.substring(0, 1).toLowerCase()
								+ key.substring(1);
						map.put(key, value);
					}
				} catch (Exception e) {
					log.error("obj to map error", e);
				}
			}
		}
		return map;
	}

	
	/**
	 * 对象转map
	 * 
	 * @param thisObj
	 * @return
	 */
	public static Map<String,String> objectToStringMap(Object thisObj) throws Exception {
		Map<String,String> map = new HashMap<String,String>();
		Class c;
		c = Class.forName(thisObj.getClass().getName());
		Method[] m = c.getMethods();
		for (int i = 0; i < m.length; i++) {
			String method = m[i].getName();
			if (method.startsWith("get")) {
				try {
					String value = m[i].invoke(thisObj).toString();
					if (value != null) {
						String key = method.substring(3);
						if ("Class".equals(key)) {
							continue;
						}
						key = key.substring(0, 1).toLowerCase()
								+ key.substring(1);
						map.put(key, value);
					}
				} catch (Exception e) {
					log.error("obj to map error", e);
				}
			}
		}
		return map;
	}
	
	
	/**
	 * 返回格式
	 * 
	 * @param map
	 * @return 
	 *         card_id=6226090108501939|idcard=132903195103108922|name=周春青|quick=
	 *         0|user_id=UB201504232122420000000003749464
	 */
	public static String MapForString(Map<String, Object> map) {

		StringBuilder builder = new StringBuilder();
		if (!StringUtils.empty(map)) {
			int index = 0;
			for (Entry<String, Object> entry : map.entrySet()) {
				if (index < map.size() - 1) {
					builder.append(entry.getKey()).append("=")
							.append(entry.getValue()).append("|");
				} else {
					builder.append(entry.getKey()).append("=")
							.append(entry.getValue());
				}
				index++;
			}
		}
		return builder.toString();
	}
	
	public static String MapObjectForString(Map<String, String> map) {

		StringBuilder builder = new StringBuilder();
		if (!StringUtils.empty(map)) {
			int index = 0;
			for (Entry<String, String> entry : map.entrySet()) {
				if (index < map.size() - 1) {
					builder.append(entry.getKey()).append("=")
							.append(entry.getValue()).append("|");
				} else {
					builder.append(entry.getKey()).append("=")
							.append(entry.getValue());
				}
				index++;
			}
		}
		return builder.toString();
	}

	/**
	 * 将数组转换成Map
	 * 
	 * @param args
	 * @return
	 */
	public static Map<String, Object> asMap(Object... args) {
		LinkedHashMap<String, Object> map = null;
		if (!StringUtils.empty(args)) {
			map = new LinkedHashMap<String, Object>();
			for (int i = 0; i < args.length; i++) {
				map.put(String.valueOf(args[i]), args[++i]);
			}
		}
		return map;
	}

	public static LinkedHashMap<String, Object> getLinkedHashMap(
			String queryString) {
		LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<String, Object>();
		if (!StringUtils.empty(queryString)) {
			String[] arrays = queryString.split("&");
			if (!StringUtils.empty(arrays)) {
				for (String v : arrays) {
					String values[] = v.split("=");
					linkedHashMap.put(values[0], values[1]);
				}
			}
		}

		return linkedHashMap;
	}

	public static String append(Object object) {

		StringBuilder builder = new StringBuilder();

		Class<?> cls = object.getClass();

		Method[] methods = cls.getDeclaredMethods();

		try {

			for (Method m : methods) {

				if (m.getName().startsWith("get")) {
					Method newMethod = m;

					Object value = newMethod.invoke(object);
					if (null == value) {
						continue;
					}
					String methodName = newMethod.getName();

					String mName = methodName.substring(3).toLowerCase();

					builder.append(mName).append("=").append(value).append("|");

				}
			}
		} catch (Exception e) {
			log.error("append error", e);
		}

		String retValue = builder.toString();

		if (retValue.endsWith("|")) {
			
			return retValue.substring(0, retValue.lastIndexOf("|"));
		}
		return retValue;

	}
	
	
	/**
	 * 将返回的map对象数据，赋值到javabean对象中
	 * @param map
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static<T> T mapToObj(Map<String, String> map,Class<T> cls) {
		try{
			Object obj = cls.newInstance();
			Method[] methods = cls.getDeclaredMethods();
			for(Method m :methods) {
				String mName = m.getName();
				for(Entry<String, String> entry :map.entrySet()) {
					String name = entry.getKey();
					String value = entry.getValue();
					if (mName.toLowerCase().indexOf(name) != -1 && mName.startsWith("set")) {
						String parameterName = m.getParameterTypes()[0].getName();
						if (parameterName.indexOf(INT) !=-1 ) {
							m.invoke(obj, NumberUtils.toInt(value));
						} else {
							m.invoke(obj, value);
						}
						
						map.remove(name);
						break;
					}
				}
			}
			return (T) obj;
		}
		catch(Exception e) {
			log.error("mapToObj error,map:" + map, e);
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T[] parameterMapToObject(Map<String, String> map, Class<T>... classes) {
		Object[] arrays = new Object[classes.length];
		try {
			int index = 0;
			for(Class cls :classes) {
				Object obj = cls.newInstance();
				Method[] methods = cls.getDeclaredMethods();
				for(Method m: methods) {
					String methodName = m.getName();
					if(!StringUtils.empty(map)) {
						for(Entry<String, String> entry: map.entrySet()) {
							String key = entry.getKey().toLowerCase();
							String value = entry.getValue();
							if(methodName.indexOf("set") != -1) {
								String parameterName = m.getParameterTypes()[0].getName().toLowerCase();
								String subMethodName = methodName.substring(3).toLowerCase();
								if(key.equals(subMethodName)) {
									if (parameterName.indexOf(INT) !=-1 ) {
										m.invoke(obj, NumberUtils.toInt(value));
									} else if(parameterName.indexOf(DOUBLE) !=-1 ) {
										m.invoke(obj, NumberUtils.toDouble(value));
									}else {
										m.invoke(obj, value);
									}
									break;
								}
							}
						}
					}
				}
				arrays[index] = obj;
				index ++;
			}
		return (T[]) arrays;
		} catch (Exception e) {
			log.error(StringUtils.f("参数集合转化成map对象失败%s%s", e, e.getMessage()));
			return null;
		}
	}
	
	public static Map<String, String> ParameterArrayToMap(Map<String, String[]> map) {
		Map<String, String> newMap = new HashMap<String, String>();
		if(!StringUtils.empty(map)) {
			Iterator<Entry<String, String[]>> iter = map.entrySet().iterator();
			while(iter.hasNext()) {
				Entry<String, String[]> entry = iter.next();
				String key = entry.getKey();
				String[] values = entry.getValue();
				if (null != values && values.length >0) {
					newMap.put(key, StringUtils.getStrFormat(values[0], StringUtils.getEncoding(values[0])));
				}
			}
		}
		return newMap;
	}
	
	/**
	 * 不带字符集转换
	 * @param map
	 * @return
	 */
	public static Map<String, String> ParameterArrayToMapNotCharset(Map<String, String[]> map) {
		Map<String, String> newMap = new HashMap<String, String>();
		if(!StringUtils.empty(map)) {
			Iterator<Entry<String, String[]>> iter = map.entrySet().iterator();
			while(iter.hasNext()) {
				Entry<String, String[]> entry = iter.next();
				String key = entry.getKey();
				String[] values = entry.getValue();
				if (null != values && values.length >0) {
					newMap.put(key, values[0]);
				}
			}
		}
		return newMap;
	}
	
	
	

	public static Map<String, Object> getAllMap() {
		return allMap;
	}

	public static void setAllMap(Map<String, Object> allMap) {
		MapUtil.allMap = allMap;
	}

	public static void test(String...arge){
		
	}

	public static void main(String[] args) {
		
		/*Map<String, Object> map = getLinkedHashMap("balance=00000000000002&com_amt=1&mer_check_date=20140918&mer_date=20140918&mer_id=7000998&order_id=2014091801&ret_code=0000&ret_msg=%E5%85%85%E5%80%BC%E6%88%90%E5%8A%9F&service=recharge_notify&trade_no=1409181409024494&version=4.0&sign=lbKkoUiTu3IwYTdjiN%2BwuDBlSFB2mpi4HCeRmE3PZkuKTv2Z1%2F9%2Ff9BUSufJNmupEPz8W0z5KVpGBcCOqOA0HubP9jNJxvsfbpDinOQ6Rne9N5MzJrlU4iHyMScu%2F76ZBTjIe9FOQQMzuL8KH6CtHwInVUuoU3UC%2Fj7Z59V22ME%3D&sign_type=RSA");
		for (Entry<String, Object> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "\t" + entry.getValue());
		}*/
	}
}
