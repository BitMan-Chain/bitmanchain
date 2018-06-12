package com.shoufubang.model.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertiesUtils {

	public static Properties properties = null;

	public static String getValue(String key, String propertyName) {
		try {
			if (properties == null) {
				properties = new Properties();
			}
			properties.load(new InputStreamReader(
					PropertiesUtil.class.getClassLoader().getResourceAsStream(propertyName), "UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties.getProperty(key);
	}
}
