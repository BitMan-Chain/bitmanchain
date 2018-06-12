package com.shoufubang.model.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileTool {

	public static File MultipartFile2File(MultipartFile file) {
		CommonsMultipartFile cf = (CommonsMultipartFile) file;
		DiskFileItem fi = (DiskFileItem) cf.getFileItem();
		File f = fi.getStoreLocation();
		return f;
	}

	public static void Inputstream2File(InputStream ins,File file) {
		try {
		   OutputStream os = new FileOutputStream(file);
		   int bytesRead = 0;
		   byte[] buffer = new byte[8192];
		   while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
			   os.write(buffer, 0, bytesRead);
		   }
		   os.close();
		   ins.close();
		} catch (Exception e) {
		   e.printStackTrace();
		}
	}
	
	
	 public static byte[]  downLoadFromUrl(String urlStr) throws IOException{
	        URL url = new URL(urlStr);  
	        HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
	                //设置超时间为3秒
	        conn.setConnectTimeout(5*1000);
	        //防止屏蔽程序抓取而返回403错误
	        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

	        //得到输入流
	        InputStream inputStream = conn.getInputStream();  
	        //获取自己数组
	        byte[] getData = readInputStream(inputStream);    

	        //文件保存位置
//	        File saveDir = new File(savePath);
//	        if(!saveDir.exists()){
//	            saveDir.mkdir();
//	        }
//	        File file = new File(saveDir+File.separator+fileName);    
//	        FileOutputStream fos = new FileOutputStream(file);     
//	        fos.write(getData); 
//	        if(fos!=null){
//	            fos.close();  
//	        }
           if(inputStream!=null){
	            inputStream.close();
	        }

	        System.out.println("info:"+url+" download success");
	        
			return getData; 

	    }
	 public static  byte[] readInputStream(InputStream inputStream) throws IOException {  
	        byte[] buffer = new byte[1024];  
	        int len = 0;  
	        ByteArrayOutputStream bos = new ByteArrayOutputStream();  
	        while((len = inputStream.read(buffer)) != -1) {  
	            bos.write(buffer, 0, len);  
	        }  
	        bos.close();  
	        return bos.toByteArray();  
	    }  



}
