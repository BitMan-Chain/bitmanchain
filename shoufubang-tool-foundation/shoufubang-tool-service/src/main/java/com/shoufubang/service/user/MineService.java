package com.shoufubang.service.user;

import java.util.List;
import java.util.Map;


/**
 * @author 
 */
public interface MineService {
	//获取产生币数量

	Map<String, Object> getMineMoney(String mobile, String string);

	Map<String, Object> collectCandy(Integer userid, String mobile, Map<String, Object> mapNum);

	//List<Map<String, Object>> getDigMine(Integer userid); 
	
	
  
}
