package com.shoufubang.service.user;

import java.util.List;
import java.util.Map;

import com.shoufubang.model.user.StressLog;

/**
 * @author 
 */
public interface IStressService {

	List<Map<String, Object>> getStressLog(StressLog stressLog);
  

  
}
