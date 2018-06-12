package com.shoufubang.model.catchpack;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 响应类
 * 作者：Administrator<br>
 * 版本：1.0<br>
 * 创建日期：下午9:36:03<br>
 */
public class Response {

    /**响应码*/
    private int statusCode ;

    /**响应内容*/
    private byte[] content ;

    /**响应头部*/
    private Map<String, String> headers;

    /**
     * 设置响应头
     * 
     * @param name
     * @param value
     */
    public void setHeader(String name, String value) {
        if (headers == null) {
            headers = new LinkedHashMap<String, String>();
        }
        headers.put(name, value);
    }

    /**
     * 按指定编码获得响应内容，有些响应乱码了 需要解决乱码问题
     * @param encoding
     * @return
     */
    public String getContentString(String encoding) {
        try {
            String contentString = new String(getContent(), encoding);
            return contentString;
        } catch (Exception e) {
            System.out.println("不支持编码");
        }
        return null;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }


}