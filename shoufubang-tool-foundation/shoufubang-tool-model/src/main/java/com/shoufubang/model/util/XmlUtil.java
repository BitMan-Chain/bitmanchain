package com.shoufubang.model.util;

import java.util.ArrayList; 
import java.util.HashMap; 
import java.util.Iterator; 
import java.util.List; 
import java.util.Map; 
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import net.sf.json.JSONObject; 

/** 
 * <pre>项目名称：根据 CFCA	坑爹返回方法，解析 出想要数据 封装xml解析不适用其他
 * 类名称：XmlUtil    
 * 类描述：    
 * 创建人：李帅 ls314397644@163.com   
 * 创建时间：2017年3月15日 下午7:37:24    
 * 修改人：李帅 ls314397644@163.com    
 * 修改时间：2017年3月15日 下午7:37:24    
 * 修改备注：       
 * @version </pre>    
 */
public class XmlUtil { 
  @SuppressWarnings("unchecked")  
    public static Map<String, Object> xmlToMap(String str){ 
	  Map<String, Object> map = new HashMap<String, Object>(); 
	    Document doc = null;
		try {
			 doc = DocumentHelper.parseText(str);
			 if(doc == null) return map; 
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
       
        Element root = doc.getRootElement(); 
        for (Iterator iterator = root.elementIterator(); iterator.hasNext();) { 
            Element e = (Element) iterator.next(); 
            List list = e.elements(); 
            if(list.size() > 0){ 
                map.put(e.getName(), Dom2Map(e)); 
            }else 
                map.put(e.getName(), e.getText()); 
        } 
             String string = JSONObject.fromObject(map.get("Body")).toString();  
	    	 JSONObject  jasonObject = JSONObject.fromObject(string);
	    	 map = (Map<String,Object>)jasonObject;
        return map; 
    } 
     @SuppressWarnings("unchecked")
    public static Map Dom2Map(Element e){ 
        Map map = new HashMap(); 
        List list = e.elements(); 
        if(list.size() > 0){ 
            for (int i = 0;i < list.size(); i++) { 
                Element iter = (Element) list.get(i); 
                List mapList = new ArrayList(); 
                 
                if(iter.elements().size() > 0){ 
                    Map m = Dom2Map(iter); 
                    if(map.get(iter.getName()) != null){ 
                        Object obj = map.get(iter.getName()); 
                        if(!obj.getClass().getName().equals("java.util.ArrayList")){ 
                            mapList = new ArrayList(); 
                            mapList.add(obj); 
                            mapList.add(m); 
                        } 
                        if(obj.getClass().getName().equals("java.util.ArrayList")){ 
                            mapList = (List) obj; 
                            mapList.add(m); 
                        } 
                        map.put(iter.getName(), mapList); 
                    }else 
                        map.put(iter.getName(), m); 
                } 
                else{ 
                    if(map.get(iter.getName()) != null){ 
                        Object obj = map.get(iter.getName()); 
                        if(!obj.getClass().getName().equals("java.util.ArrayList")){ 
                            mapList = new ArrayList(); 
                            mapList.add(obj); 
                            mapList.add(iter.getText()); 
                        } 
                        if(obj.getClass().getName().equals("java.util.ArrayList")){ 
                            mapList = (List) obj; 
                            mapList.add(iter.getText()); 
                        } 
                        map.put(iter.getName(), mapList); 
                    }else 
                        map.put(iter.getName(), iter.getText()); 
                } 
            } 
        }else 
            map.put(e.getName(), e.getText()); 
        return map; 
    } 
} 