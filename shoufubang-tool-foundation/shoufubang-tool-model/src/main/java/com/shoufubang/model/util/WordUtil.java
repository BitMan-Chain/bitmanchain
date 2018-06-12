package com.shoufubang.model.util;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import freemarker.template.Configuration;
import freemarker.template.Template;
/**
 * @Desc：word操作工具类
 * @Date：2014-1-22下午05:03:19
 */
public class WordUtil {
		
	 private static Configuration configuration = null;  
	 private static Map<String, Template> allTemplates = null;  
	 private static Logger log = Logger.getLogger(WordUtil.class);
	 
	 static {  
	        configuration = new Configuration();  
	        configuration.setDefaultEncoding("utf-8");  
	        configuration.setClassForTemplateLoading(WordUtil.class, "/com/shoufubang/template");  
	        allTemplates = new HashMap<>();   // Java 7 钻石语法  
	        try {  
	            allTemplates.put("resume", configuration.getTemplate("inquiryLetter.ftl"));  
	        } catch (IOException e) {  
	        	log.info("初始化代码块出问题");
	            e.printStackTrace();  
	            throw new RuntimeException(e);  
	        }  
	    }  
	  
	    private WordUtil() {  
	        throw new AssertionError();  
	    }  
	  
	    public static File createDoc(Map<?, ?> dataMap, String type) {  
	        String name = "temp" + (int) (Math.random() * 100000) + ".doc";  
	        File f = new File(name);  
	        Template t = allTemplates.get(type); 
	        try {  
	            // 这个地方不能使用FileWriter因为需要指定编码类型否则生成的Word文档会因为有无法识别的编码而无法打开  
	            Writer w = new OutputStreamWriter(new FileOutputStream(f), "utf-8"); 
	            t.process(dataMap, w);  
	            w.close();  
	        } catch (Exception ex) {  
	        	log.info("createDoc方法出问题");
	            ex.printStackTrace();  
	            throw new RuntimeException(ex);  
	        }  
	        return f;  
	    }  
}
