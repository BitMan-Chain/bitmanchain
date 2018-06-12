package com.shoufubang.model.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.shoufubang.model.util.PropertiesUtil;

import cfca.sadk.util.Base64;

import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;

public class OSSTool {

	/**
	 * putObject()
	 * 
	 * @author zhangyiying
	 * @date 2016年11月14日 上午9:47:19
	 * @param key
	 *            保存在oss上的文件名(包括路径)
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 */
	public static String uploadFileByOSS(String key, File file) throws FileNotFoundException {

		// key指的是 保存在oss上后的路径+文件名
		// filePath 指的是上传的文件路径
		OSSClient client = null;

		// 参数设置
		// 关于这个endPoint，可以参考：http://bbs.aliyun.com/read/149100.html?spm=5176.7189909.0.0.YiwiFw
		String endpoint = PropertiesUtil.getProperty("endpoint");// 青岛的接口
		String accessKeyId = PropertiesUtil.getProperty("accessKeyId");
		String accessKeySecret = PropertiesUtil.getProperty("accessKeySecret");
		String bucketName = PropertiesUtil.getProperty("bucketName");
		System.out.println("bucketName-------" + bucketName);
		// String key="peju/url1.jpg";//保存在oss上的文件名
		// String filePath="d://架构.png";//本地或者服务器上文件的路径
		String OOS_url = "";
		try {
			client = new OSSClient(endpoint, accessKeyId, accessKeySecret);

			// 获取指定文件的输入流
			// File file = new File(filePath);
			InputStream content = new FileInputStream(file);
			// 创建上传Object的Metadata
			ObjectMetadata meta = new ObjectMetadata();
			// 必须设置ContentLength
			meta.setContentLength(file.length());
			// 上传Object.
			PutObjectResult result = client.putObject(bucketName, key, content, meta);
			if (file.isFile() && file.exists()) {
				// file.delete();
				String[] endpointArray = endpoint.split("//");
				System.out.println(endpointArray[0] + "//" + bucketName + "." + endpointArray[1] + key);
				OOS_url = endpointArray[0] + "//" + bucketName + "." + endpointArray[1] + key;
			}
			return OOS_url;
		} catch (OSSException oe) {
			System.out.println("Error Message: " + oe.getErrorCode());
			System.out.println("Error Code:       " + oe.getErrorCode());
			System.out.println("Request ID:      " + oe.getRequestId());
			System.out.println("Host ID:           " + oe.getHostId());
			OOS_url = "error";
			return OOS_url;
		}
		// 打印ETag
		// System.out.println(result.getETag());
	}

	/**
	 * downloadFile() 下载文件到本地磁盘上
	 * 
	 * @author zhangyiying
	 * @date 2017年3月29日 下午8:53:13
	 * @param key
	 *            文件名(带文件夹)
	 *            例如文件在oss的路径为:http://sfb-demo1.oss-cn-qingdao.aliyuncs.com/font
	 *            /ARIALUNI.TTF 则key = font/ARIALUNI.TTF
	 * @param downloadPath
	 *            文件要下载到磁盘中的位置 "G:/test/photo1.jpg"(test文件夹需存在)
	 */
	public static void downloadFile(String key, String downloadPath) {
		try {
			OSSClient client = null;
			String endpoint = PropertiesUtil.getProperty("endpoint");// 青岛的接口
			String accessKeyId = PropertiesUtil.getProperty("accessKeyId");
			String accessKeySecret = PropertiesUtil.getProperty("accessKeySecret");
			String bucketName = PropertiesUtil.getProperty("bucketName");

			client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
			client.getObject(new GetObjectRequest(bucketName, key), new File(downloadPath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据key获取OSS服务器上的文件
	 * 
	 * @param diskName
	 *            文件路径
	 * @param key
	 *            Bucket下的文件的路径名+文件名
	 */
	public static final byte[] getOSS2Byte( String diskName,
			String key) {
		try{
			OSSClient client = null;
			String endpoint = PropertiesUtil.getProperty("endpoint");// 青岛的接口
			String accessKeyId = PropertiesUtil.getProperty("accessKeyId");
			String accessKeySecret = PropertiesUtil.getProperty("accessKeySecret");
			String bucketName = PropertiesUtil.getProperty("bucketName");

			client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
			OSSObject ossObj = client.getObject(bucketName, diskName + key);
			
			ByteArrayOutputStream swapStream = new ByteArrayOutputStream();  
	        byte[] buff = new byte[100];  
	        int rc = 0;  
	        while ((rc = ossObj.getObjectContent().read(buff, 0, 100)) > 0) {  
	            swapStream.write(buff, 0, rc);  
	        }  
	        byte[] bt = swapStream.toByteArray();  
	        return bt;  
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}

	public static String uploadFileByOSSTest() throws FileNotFoundException {

		// key指的是 保存在oss上后的路径+文件名
		// filePath 指的是上传的文件路径
		OSSClient client;

		// 参数设置
		// 关于这个endPoint，可以参考：http://bbs.aliyun.com/read/149100.html?spm=5176.7189909.0.0.YiwiFw
		String endpoint = "http://oss-cn-qingdao.aliyuncs.com";// 青岛的接口
		String accessKeyId = "LTAIVFQ63AKJa43Z";
		String accessKeySecret = "H2CueqPg2zAyQD4IP5Rfg1B2PaQk3k";
		String bucketName = "my-root";
		String key = "zyy_2.jpg";// 保存在oss上的文件名
		String filePath = "d://2.jpg";// 本地或者服务器上文件的路径
		String OOS_url = "";
		try {
			client = new OSSClient(endpoint, accessKeyId, accessKeySecret);

			// 获取指定文件的输入流
			File file = new File(filePath);
			InputStream content = new FileInputStream(file);
			// 创建上传Object的Metadata
			ObjectMetadata meta = new ObjectMetadata();
			// 必须设置ContentLength
			meta.setContentLength(file.length());
			// 上传Object.
			PutObjectResult result = client.putObject(bucketName, key, content, meta);
			if (file.isFile() && file.exists()) {
				// file.delete();
				String[] endpointArray = endpoint.split("//");
				System.out.println(endpointArray[0] + "//" + bucketName + "." + endpointArray[1] + "/" + key);
				OOS_url = endpointArray[0] + "//" + bucketName + "." + endpointArray[1] + key;
			}
		} catch (OSSException oe) {
			System.out.println("Error Message: " + oe.getErrorCode());
			System.out.println("Error Code:       " + oe.getErrorCode());
			System.out.println("Request ID:      " + oe.getRequestId());
			System.out.println("Host ID:           " + oe.getHostId());
			OOS_url = "error";
		} finally {
			return OOS_url;
		}

	}

	public static void main(String[] args) {
		String diskName ="project/pdf/170419000177/1/";
		 byte[] pdf=getOSS2Byte(diskName, "170419000177-30803.pdf");
		 byte[] pdfTest= Base64.encode(pdf);
		 byte[] pdfTest2=Base64.decode(pdfTest);
		 System.out.println(pdf);
		 System.out.println("1:"+pdf.toString());
		 System.out.println("2:"+pdfTest);
		 System.out.println("3"+pdfTest2);
	}

	/**
	 * MultipartFile2File() MultipartFile转File文件
	 * 
	 * @author zhangyiying
	 * @date 2016年11月14日 上午11:21:45
	 * @param file
	 * @return
	 */
	public static File MultipartFile2File(MultipartFile file) {
		File f = null;
		if (file != null) {
			CommonsMultipartFile cf = (CommonsMultipartFile) file;
			DiskFileItem fi = (DiskFileItem) cf.getFileItem();
			f = fi.getStoreLocation();
		}
		return f;
	}

}
