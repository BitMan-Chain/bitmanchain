package com.shoufubang.model.util;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import java.util.HashMap;
import java.util.Map;

public class ImageToOCR {

    private String host = "";
    private String path = "";
    private String method = "POST";
    private String appcode = "3a9d4c36e0e941fd9f6c6f605fde8de6";    //必填！Required

    //火车票接口
    public HttpResponse getTrainResult(String imagePath){
    	//设置要访问的接口Host和Path
    	host = "https://ocrhcp.market.alicloudapi.com";
    	path = "/api/predict/ocr_train_ticket";
        //get image file path and base64 image      设置文件路径及将图片Base64编码
        String imageFilePath = imagePath;
        String fileBase64 = Base64.getImageStr(imageFilePath);

        //add api http header       设置http请求头格式
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "APPCODE " + appcode);
        headers.put("Content-Type", "application/json; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();

        //set api http body         拼接http请求主体
        String bodys = "{" +
                "   \"image\":    \"" +fileBase64+"\"," +
                "" +
                "\"configure\":" +
                "    \"{\\\"min_size\\\" : 16, " +
                "      \\\"output_prob\\\" : true}\"}";

        try {
            //send http request     发送http请求
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);

            //返回对象结果集
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 返回空
        return null;
    }
    
    //文字识别OCR
    public ResponseBody getOcrResult(String imagePath){
    	//设置要访问的接口Host和Path
    	host = "http://tysbgpu.market.alicloudapi.com";
    	path = "/api/predict/ocr_general";
        //get image file path and base64 image      设置文件路径及将图片Base64编码
        String imageFilePath = imagePath;
        String fileBase64 = Base64.getImageStr(imageFilePath);

        //add api http header       设置http请求头格式
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "APPCODE " + appcode);
        headers.put("Content-Type", "application/json; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();

        //set api http body         拼接http请求主体
        String bodys = "{" +
                "   \"image\":    \"" +fileBase64+"\"," +
                "" +
                "\"configure\":" +
                "    \"{\\\"min_size\\\" : 16, " +
                "      \\\"output_prob\\\" : true}\"}";

        try {
            //send http request     发送http请求
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);

            //mapping Json to Object   映射返回数据到对象
            Gson gson = new Gson();
            ResponseBody responseBody = gson.fromJson(EntityUtils.toString(response.getEntity()),ResponseBody.class);

            //返回对象结果集
            return responseBody;
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 返回空
        return null;
    }
}