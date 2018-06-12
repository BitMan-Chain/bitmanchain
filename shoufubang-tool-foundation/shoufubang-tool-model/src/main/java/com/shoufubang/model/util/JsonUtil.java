package com.shoufubang.model.util;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.codehaus.jackson.map.SerializationConfig;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;






public final class JsonUtil {

    private final static Logger log = Logger.getLogger("ERROR");


    

    /**
     * json转对象
     *
     * @param json
     * @param valueTypeRef
     * @return
     */
    public static <T> T jsonToType(String json, TypeReference valueTypeRef) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, valueTypeRef);
        } catch (Exception e) {
            log.error("json convert obj error,{json}" + json, e);
            return null;
        }

    }

    public static Map json2Map(String json) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map map = mapper.readValue(json, Map.class);
        return map;
    }
    /**
	 * 对象转json
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String objToJson(Object obj) {
		
		if(obj == null) {
			return "";
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationConfig.Feature.WRITE_NULL_PROPERTIES, false);
		try {
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("obj convert json error.", e);
			return "";
		}
		
	}
	
	/**
	 * json转对象
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T jsonToObj(String json, Class<T> clazz) {
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, clazz);
		} catch (Exception e) {
			log.error("json convert obj error,{json}" + json, e);
			return null;
		}
		
	}
	public static String escapeJson(String string){  
        if (string == null || string.length() == 0){  
            return string;  
        }  
        char b,c = 0;  
        int len = string.length();  
        StringBuilder sb = new StringBuilder(len+4);  
        String t;  
        
        for (int i=0;i<len;i ++){  
            b = c;  
            c = string.charAt(i);  
            switch (c){  
                case '\\':  
                    sb.append("\\\\");  
                    break;  
                case '\'':  
                    sb.append("\\'");  
                    break;  
                case '/':  
                    if (b == '<')sb.append("\\");  
                    sb.append(c);  
                    break;  
                case '\b':  
                    sb.append("\\b");  
                    break;  
                case '\t':  
                    sb.append("\\t");  
                    break;  
                case '\n':  
                    sb.append("\\n");  
                    break;  
                case '\f':  
                    sb.append("\\f");  
                    break;  
                case '\r':  
                    sb.append("\\r");  
                    break;  
                default:  
                    if (c <' '||(c >='\u0080'&&c <'\u00a0')||(c >='\u2000'&& c <'\u2100')){  
                        t = "000" + Integer.toHexString(c);  
                        sb.append("\\u"+t.substring(t.length()-4));  
                    }else{  
                        sb.append(c);  
                    }  
            }  
        }  
        
        System.out.println(sb.toString());  
        try{return sb.toString();}finally{sb=null;}  
    }  
	
}
