package com.shoufubang.model.util;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;






/** 
 * <pre>项目名称：shoufubang-tool-model    
 * 类名称：PdfToByte    
 * 类描述：    pdf转化成byte
 * 创建人：李帅 ls314397644@163.com   
 * 创建时间：2017年3月27日 上午11:29:49    
 * 修改人：李帅 ls314397644@163.com    
 * 修改时间：2017年3月27日 上午11:29:49    
 * 修改备注：       
 * @version </pre>    
 */
public class PdfToByte { 
		   
//	public static ByteBuffer getAsByteArray(URL url) throws IOException {
//		 URLConnection connection = url.openConnection();
//		 // Since you get a URLConnection, use it to get the InputStream
//		 InputStream in = connection.getInputStream();
//		 // Now that the InputStream is open, get the content length
//		 int contentLength = connection.getContentLength();
//		 // To avoid having to resize the array over and over and over as
//		 // bytes are written to the array, provide an accurate estimate of
//		 // the ultimate size of the byte array
//		 ByteArrayOutputStream tmpOut;
//		 if (contentLength != -1) {
//		  tmpOut = new ByteArrayOutputStream(contentLength);
//		 } else {
//		  tmpOut = new ByteArrayOutputStream(16384); // Pick some appropriate size
//		 }
//		 byte[] buf = new byte[512];
//		 while (true) {
//		  int len = in.read(buf);
//		  if (len == -1) {
//		   break;
//		  }
//		  tmpOut.write(buf, 0, len);
//		 }
//		 in.close();
//		 tmpOut.close(); // No effect, but good to do anyway to keep the metaphor alive
//		 byte[] array = tmpOut.toByteArray();
//		 //Lines below used to test if file is corrupt
//		 //FileOutputStream fos = new FileOutputStream("C:\\abc.pdf");
//		 //fos.write(array);
//		 //fos.close();
//		 return ByteBuffer.wrap(array);
//		}
} 