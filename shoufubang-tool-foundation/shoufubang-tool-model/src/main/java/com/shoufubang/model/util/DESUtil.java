package com.shoufubang.model.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import com.shoufubang.model.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;

/** 
 * 3DES加密工具类 
 */  
public final class DESUtil {  
	
	private final static Logger log = Logger.getLogger("ENCRYPT");
    // 向量  
    private final static String iv = "01234567";  
    // 加解密统一使用的编码方式  
    private final static String encoding = "utf-8";  
  
    /** 
     * 3DES加密 
     *  
     * @param plainText 普通文本 
     * @return 
     * @throws Exception  
     */  
    public static String encode(String plainText, String secretKey) {  
    	try{
	    	if(StringUtils.empty(plainText) || StringUtils.empty(secretKey)) {
	    		return "";
	    	}
	    	
	        Key deskey = null;  
	        DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());  
	        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");  
	        deskey = keyfactory.generateSecret(spec);  
	  
	        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");  
	        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());  
	        cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);  
	        byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));  
	        Base64 base64 = new Base64();
	        return new String(base64.encode(encryptData));
    	}
    	catch(Exception e) {
    		log.error("encode error, {plainText, secretKey}" + plainText + "," + secretKey, e);
    		return "";
    	}
    }  
    
   
    
}  