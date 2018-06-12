package com.shoufubang.model.util;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    public static Properties properties = null;

    static {
        try {
            InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("system.properties");
            properties = new Properties();
            properties.load(inputStream);
        } catch (Exception var1) {
            ;
        }

    }

    public PropertiesUtil() {
    }

    public static String getProperty(String key) {
        return properties.getProperty(key) == null?"":properties.get(key).toString();
    }

    public static String getAesKey() {
        return properties.getProperty("AES_KEY") == null?"#123we#$%^fdhg34":properties.get("AES_KEY").toString();
    }

    public static String getHost() {
        return properties.getProperty("SERVER_URL") == null?"http://localhost:8080/diyou_server":properties.get("SERVER_URL").toString();
    }

    public static String getImageHost() {
        return properties.getProperty("IMAGE_VIEW_SERVER_URL") == null?"http://localhost:8080/diyou_image":properties.get("IMAGE_VIEW_SERVER_URL").toString();
    }

    public static String getImageUploadHost() {
        return properties.getProperty("IMAGE_SERVER_URL") == null?"http://localhost:8080/diyou_image":properties.get("IMAGE_SERVER_URL").toString();
    }
}

