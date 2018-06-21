package com.shoufubang.service.user;

import java.util.List;
import java.util.Map;

import com.shoufubang.model.user.AppStart;

/**
 * @author 
 */
public interface AppService {

	Map<String, String> startApp(AppStart appStart);

	void bitzuan(Map<String, Object> requestParam);

  
}
