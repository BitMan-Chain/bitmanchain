package com.shoufubang.model.util;    
    
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;    
import freemarker.template.ObjectWrapper;     
/** 
 * <pre>项目名称：shoufubang-tool-model    
 * 类名称：FreeMakerExcelUtil    
 * 类描述：    生成excel表格
 * 创建人：李帅 ls314397644@163.com   
 * 创建时间：2017年2月13日 上午9:57:37    
 * 修改人：李帅 ls314397644@163.com    
 * 修改时间：2017年2月13日 上午9:57:37    
 * 修改备注：       
 * @version </pre>    
 */
public class FreeMakerExcelUtil {    
  
private static Configuration configuration;
private static final String TEMPLATEFILENAME = "templates/report";  	
	/**
	 * 生成Freemarker配置对象并设置模板读取路径
	 */
	static {
		
		if (null == configuration) {
			configuration = new Configuration();
			
			configuration.setDefaultEncoding("UTF-8");
			// 设置对象的包装器
			configuration.setObjectWrapper(new DefaultObjectWrapper());
			// 设置异常处理器//这样的话就可以${a.b.c.d}即使没有属性也不会出错
			configuration.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
			//configuration.setClassForTemplateLoading(FreeMarkerUtils.class,TEMPLATEFILENAME);  
	
		}
		
	}
	
    /** <pre>createReportExcel(生成导出excel 表格 传值末班文件 以及数据类dataMap)   
     * 创建人：李帅 ls314397644@163.com   
     * 创建时间：2017年2月13日 下午3:49:32    
     * 修改人：李帅 ls314397644@163.com    
     * 修改时间：2017年2月13日 下午3:49:32    
     * 修改备注： 
     * @param excelName 模板名称
     * @param dataMap   模板数据 
     * @return</pre>    
     */
    public static void createReportExcel(HttpServletRequest request,HttpServletResponse response,String excelName,Map<String, Object> dataMap){    
       // configuration.setClassForTemplateLoading(FreeMakerExcelUtil.class, "/com/shoufubang/model/util/templates/report/");  //FTL文件所存在的位置    
    	Template t=null;
    	try {    
    		//URL base = FreeMakerExcelUtil.class.getResource("");
        	//String path=Thread.currentThread().getContextClassLoader().getResource("/").getPath()+TEMPLATEFILENAME;
        	//PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        	// resolver.getResource()
        	//String path = new File(base.getFile(),TEMPLATEFILENAME).getCanonicalPath();
    		String path=Thread.currentThread().getContextClassLoader().getResource("/").getPath()+TEMPLATEFILENAME;
    		path =replaceAllString(path);
     		System.out.println(path);
    	    configuration.setDirectoryForTemplateLoading(new File(path));
            t = configuration.getTemplate(excelName+".ftl"); //文件名  
           
        } catch (IOException e) { 
        	
            e.printStackTrace();    
        } 
        //融资明细表201701011230.xls
        String url = excelName+TimeUtil1.dateFormat3(new Date())+".xls";
        File outFile = new File(url);  //生成文件的路径  
        Writer out = null;    
        try {    
            try {
				out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
        } catch (FileNotFoundException e1) {    
            e1.printStackTrace();    
        }    
             
        try {    
            t.process(dataMap, out,ObjectWrapper.BEANS_WRAPPER);
            out.close();
            File file = new File(url);
         
            // url=OSSTool.uploadFileByOSS("repor/"+excelName+"/"+file.getName(), file);
          //  createJfileChooser(url);
           response.setContentType("application/x-msdownload;charset=UTF-8");  
           // 设置附加文件名  
//           response.setHeader("Content-Disposition", "attachment;filename=\""  
//                   + new String(url.getBytes("utf-8"), "iso-8859-1")+"\"");
           String ua = request.getHeader("User-Agent");  
           if(ua != null){  
               if(( ua.toLowerCase().indexOf("firefox") > 0) ||  ua.toLowerCase().indexOf("mozilla") > 0  ){  
                   //解决火狐浏览器下载文件名乱码问题 (20150619 new)  
                   String userAgent = request.getHeader("User-Agent");    
                   byte[] bytes = userAgent.contains("MSIE") ? url.getBytes() : url.getBytes("UTF-8"); // name.getBytes("UTF-8")处理safari的乱码问题    
                   url = new String(bytes, "ISO-8859-1"); // 各浏览器基本都支持ISO编码    
                   response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", url)); // 文件名外的双引号处理firefox的空格截断问题    
               }else{  
                   //设置响应头，控制浏览器下载该文件 ,仅此火狐下会出现下载文件中文乱码的问题   ，ie chrome正常  
                    response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(url, "UTF-8").replace("+", "%20"));   
               }  
           }else{  
                response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(url, "UTF-8").replace("+", "%20"));   
           }  
 
           // 读出文件到i/o流  
           FileInputStream fis = new FileInputStream(file);  
           BufferedInputStream buff = new BufferedInputStream(fis);  
 
           byte[] b = new byte[1024];// 相当于我们的缓存  
 
           long k = 0;// 该值用于计算当前实际下载了多少字节  
 
           // 从response对象中得到输出流,准备下载  
 
           OutputStream myout = response.getOutputStream();  
 
           // 开始循环下载  
 
           while (k < outFile.length()) {  
 
               int j = buff.read(b, 0, 1024);  
               k += j;  
 
               // 将b中的数据写到客户端的内存  
               myout.write(b, 0, j);  
 
           }  
 
           // 将写入到客户端的内存的数据,刷新到磁盘  
           myout.flush();  
           myout.close();  
        } catch (TemplateException e) {    
            e.printStackTrace();    
            
        } catch (IOException e) {    
            e.printStackTrace();    
        }
		return;    
    }    
    
    /** <pre>downLoad(在线打开)   
     * 创建人：李帅 ls314397644@163.com   
     * 创建时间：2017年2月13日 上午10:04:06    
     * 修改人：李帅 ls314397644@163.com    
     * 修改时间：2017年2月13日 上午10:04:06    
     * 修改备注： 
     * @param filePath
     * @param response
     * @param isOnLine
     * @throws Exception</pre>    
     */
    public static void downLoad(String filePath, HttpServletResponse response,  
            boolean isOnLine) throws Exception {  
        File f = new File(filePath);  
        /*  
         * if (!f.exists()) { response.sendError(404, "File not found!");  
         * return; }  
         */  
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));  
        byte[] buf = new byte[1024];  
        int len = 0;  
        response.reset(); // 非常重要  
        // 在线打开方式  
        if (isOnLine) {  
            URL u = new URL(filePath);  
            response.setContentType(u.openConnection().getContentType());  
            response.setHeader("Content-Disposition", "inline; filename="  
                    + toUTF8(f.getName()));  
            // 文件名应该编码成UTF-8  
        }  
        // 纯下载方式  
        else {  
            response.setContentType("application/x-msdownload");  
            response.setHeader("Content-Disposition", "attachment; filename="  
                    + toUTF8(f.getName()));  
        }  
        OutputStream out = response.getOutputStream();  
        while ((len = br.read(buf)) > 0)  
            out.write(buf, 0, len);  
        out.flush();  
        br.close();  
        out.close();  
    }  

    /** <pre>toUTF8(转utf 8编码)   
     * 创建人：李帅 ls314397644@163.com   
     * 创建时间：2017年2月13日 上午10:02:15    
     * 修改人：李帅 ls314397644@163.com    
     * 修改时间：2017年2月13日 上午10:02:15    
     * 修改备注： 
     * @param s
     * @return</pre>    
     */
    public static String toUTF8(String s) {  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < s.length(); i++) {  
            char c = s.charAt(i);  
            if (c >= 0 && c <= 255) {  
                sb.append(c);  
            } else {  
                byte[] b;  
                try {  
                    b = Character.toString(c).getBytes("utf-8");  
                } catch (Exception ex) {  
                    System.out.println(ex);  
                    b = new byte[0];  
                }  
                for (int j = 0; j < b.length; j++) {  
                    int k = b[j];  
                    if (k < 0)  
                        k += 256;  
                    sb.append("%" + Integer.toHexString(k).toUpperCase());  
                }  
            }  
        }  
        return sb.toString();  
    }  
  
    public static String replaceAllString(String path){
		
    	
    	path = path.replaceAll("account", "account"); 
    	path = path.replaceAll("storage", "admin"); 
    	path = path.replaceAll("product", "admin");
    	//path = path.replaceAll("payment", "admin");
    	path = path.replaceAll("message", "admin");
    	return path;
    }
    
    
    public static void main(String[] args) {  
    	
    	
    	Map<String, Object> dataMap = new HashMap<String,Object>();
    	dataMap.put("title_name", "i love you");
    	String name="服务商融资明细表";
    	URL base = FreeMakerExcelUtil.class.getResource("");
		String path="";
		try {
			path = new File(base.getFile(),TEMPLATEFILENAME).getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(path);
    	//FreeMakerExcelUtil.createReportExcel(name,dataMap);
    	//String url = "融资明细表20170212172042.xls";
		String url ="D:/workspace2/apache-tomcat-7.0.40/webapps-account/shoufubang-account-web/WEB-INF/classes/templates/report";
    	
		url=replaceAllString(url);
		System.out.println("url: "+url);
    }

}    