package com.shoufubang.model.util.gdb;

import java.util.*;
import javax.crypto.*;
import org.apache.http.*;
import org.apache.http.util.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import javax.crypto.spec.DESKeySpec;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.commons.codec.binary.Base64;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import org.apache.http.message.BasicNameValuePair;
import com.alibaba.fastjson.JSONObject;
import com.shoufubang.model.util.TimeUtil1;



public class DateMarke {

	private static final String FORMAT = "utf-8";

	
	// 加密方式
	private final static String DES = "DES";
	
	
	// 星河数银API接口地址
	public static void main(String[] args) throws Exception {
		
		Map<String, String> map = new HashMap<String, String>();
		long tm = System.currentTimeMillis() / 1000;
		//格式化时间 ->> MMddHHmmss
		System.out.println("tm:"+tm);
		map.put("tm",String.valueOf(tm));
		map.put("idCard", "110224198311015247");
		map.put("name", "任静");
		map.put("phone","13810681101");
		map.put("APPKEY",ConfigDate.APPKEY_AUTH);
		//System.out.println("接口地址:"+ConfigDate.GWDATAMOBILEURL);
		System.out.println(map);
	    String str = new DateMarke().sendMessage(map,ConfigDate.GWDATAMOBILEURL);
		System.out.println(str);
	}

	/**
	 * 
	 * @param data
	 *            请求参数
	 * @return 响应信息
	 * @throws Exception
	 *             异常信息
	 */
	public String sendMessage(Map<String, String> data,String url) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("appkey", data.get("APPKEY"));
		params.put("params", des(ConfigDate.MASTERKEY, data));// 加密数据
		return doPost(url, params);
	}

	/**
	 * 加密
	 * 
	 * @param masterkey
	 * @param data
	 *            加密参数
	 * @return 加密后数据
	 * @throws Exception
	 *             加密出现异常
	 */
	public String des(String masterkey, Map<String, String> data) throws Exception {
		if (data == null || data.size() <= 0) {
			return "";
		}
		// 生成一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密钥数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(masterkey.getBytes());
		// 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象 
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance(DES);
		// 用密钥初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
		byte[] bytes = cipher.doFinal(JSONObject.toJSONString(data).getBytes(FORMAT));
		
		return org.apache.commons.codec.binary.Base64.encodeBase64String(bytes);
	}
	
	
	
	
	
	
	
	
	
	/**
	 * Post请求
	 * 
	 * @param url
	 *            请求地址
	 * @param params
	 *            参数
	 * @return 响应信息
	 * @throws Exception
	 *             异常信息
	 */
	public String doPost(String url, Map<String, Object> params) throws Exception {
		HttpClient httpClient = null;
		HttpPost httpPost = null;
		HttpResponse response = null;
		try {
			httpClient = new SSLClient();
			httpPost = new HttpPost(url);
			List<NameValuePair> pairList = new ArrayList<>(params.size());
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
				pairList.add(pair);
			}
			httpPost.setEntity(new UrlEncodedFormEntity(pairList, FORMAT));
			response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				return null;
			}
			HttpEntity entity = response.getEntity();
			if (entity == null) {
				return null;
			}
			return EntityUtils.toString(entity, FORMAT);
		} catch (Exception ex) {
			throw ex;
		} finally {
			if (response != null) {
				EntityUtils.consume(response.getEntity());
			}
		}
	}

	class SSLClient extends DefaultHttpClient {

		private static final String TLS = "TLS";

		private static final String HTTPS = "https";

		public SSLClient() throws Exception {
			super();
			SSLContext ctx = SSLContext.getInstance(TLS);
			X509TrustManager tm = new X509TrustManager() {
				@Override
				public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				}

				@Override
				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				}

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};
			ctx.init(null, new TrustManager[] { tm }, null);
			SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			ClientConnectionManager ccm = this.getConnectionManager();
			SchemeRegistry sr = ccm.getSchemeRegistry();
			sr.register(new Scheme(HTTPS, 443, ssf));
		}
	}
}
