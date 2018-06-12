package com.shoufubang.model.util;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public final class HttpUtil {

	private final static Logger log = Logger.getLogger("ERROR");
	
    public final static String get(String url) {
    	
        BufferedReader reader = null;
        InputStreamReader streamReader = null;
        CloseableHttpResponse response = null; 
        		
        try {  
        	CloseableHttpClient httpClient = HttpClients.createDefault();  
            HttpGet request = new HttpGet();  
            request.setURI(new URI(url));  
            response = httpClient.execute(request);  
            streamReader = new InputStreamReader(response.getEntity().getContent());
            reader = new BufferedReader(streamReader);  
            StringBuilder sb = new StringBuilder("");  
            String line = "";  
            while ((line = reader.readLine()) != null) {  
                sb.append(line);  
            }  
            reader.close();  
            response.close();
            response = null;
            return sb.toString();  
        }
        catch(Exception e) {
        	log.error("http get error,{url}" + url, e);
        	if(response != null) {
        		try {
					response.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
        	}
        	if (reader != null) {  
                try {  
                    reader.close();  
                } catch (Exception e1) {  
                    e.printStackTrace();  
                }  
            }
        	return "";
        }
    }  
    
    public static String sendPost(String targetUrl, Map<String, String> params) throws Exception {

		String jsonStr = null;
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		HttpPost httppost = new HttpPost(targetUrl);
		List<NameValuePair> nameValuePairArr = null;
		try {
			if (params != null) {
				nameValuePairArr = new ArrayList<NameValuePair>();

				Set<String> key = params.keySet();
				Iterator<String> keyIte = key.iterator();
				while (keyIte.hasNext()) {
					Object paramName = keyIte.next();
					Object paramValue = params.get(paramName);
					if (paramName instanceof String
							&& paramValue instanceof String) {
						NameValuePair pair = new BasicNameValuePair(
								(String) paramName, (String) paramValue);
						nameValuePairArr.add(pair);
					}
				}

				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairArr, "UTF-8"));
			}
			// 配置请求的超时设置
			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectionRequestTimeout(10000).setConnectTimeout(10000)
					.setSocketTimeout(10000).build();

			httppost.setConfig(requestConfig);

			CloseableHttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			jsonStr = EntityUtils.toString(entity, "utf-8");

		}
		catch(ConnectTimeoutException ce) {
			ce.printStackTrace();
			log.error("sendPost timeout error,{targetUrl, params, errorMessage}" + targetUrl + "," + params + "," + ce.getMessage());
		}
		catch(SocketTimeoutException se) {
			se.printStackTrace();
			log.error("sendPost timeout error,{targetUrl, params, errorMessage}" + targetUrl + "," + params + "," + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("sendPost error,{targetUrl, params}" + targetUrl + "," + params, e);
		} finally {
			// 释放链接
			if (httppost != null) {
				httppost.releaseConnection();
			}
			if(httpclient!=null){
				httpclient.close();
			}
		}

		return jsonStr;
	}
	
}
