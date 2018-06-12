package com.shoufubang.model.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class FreeMarkerUtils {
	
	private static Configuration configuration;
	
	/**
	 * 生成Freemarker配置对象并设置模板读取路径
	 */
	static {
		
		if (null == configuration) {
			configuration = new Configuration();
			
			configuration.setDefaultEncoding("UTF-8");
			
			configuration.setClassForTemplateLoading(FreeMarkerUtils.class, "/templates");
			
			// 设置对象的包装器
			configuration.setObjectWrapper(new DefaultObjectWrapper());
			// 设置异常处理器//这样的话就可以${a.b.c.d}即使没有属性也不会出错
			configuration.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
			
		}
		
	}
	
	/**
	 * <pre>getTemplate(获取Freemarker模板)   
	 * 创建人：尚立帅 lishuaishang@galaxyinternet.com    
	 * 创建时间：2017年3月15日 上午11:32:15    
	 * 修改人：尚立帅 lishuaishang@galaxyinternet.com     
	 * 修改时间：2017年3月15日 上午11:32:15    
	 * 修改备注： 
	 * @param templateName 模板名称
	 * @return
	 * @throws IOException</pre>
	 */
	private static Template getTemplate(String templateName) throws IOException{
		
		Template template = configuration.getTemplate(templateName);
		template.setEncoding("UTF-8");
		
		return template;
	}
	
	/**
	 * <pre>write(生成模板文件)   
	 * 创建人：尚立帅 lishuaishang@galaxyinternet.com    
	 * 创建时间：2017年3月15日 上午11:36:53    
	 * 修改人：尚立帅 lishuaishang@galaxyinternet.com     
	 * 修改时间：2017年3月15日 上午11:36:53    
	 * 修改备注： 
	 * @param templateName 模板的名称
	 * @param rootMap 模板中数据的集合</pre>
	 */
	public static File write(String templateName, Map<String, Object> rootMap){
		File file = new File("tempFileName");
		try {
			Template template = getTemplate(templateName);
			Writer out = new OutputStreamWriter(new FileOutputStream(file),"utf-8");
			template.process(rootMap, out);
			out.close();
		} catch (IOException | TemplateException e) {
			e.printStackTrace();
		}
		return file;
	}
	
}