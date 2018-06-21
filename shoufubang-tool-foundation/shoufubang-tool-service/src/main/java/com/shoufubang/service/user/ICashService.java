package com.shoufubang.service.user;

import java.util.List;
import java.util.Map;

import com.shoufubang.model.user.Cash;

/**
 * @author 
 */
public interface ICashService {
	//申请提现
	Map<String, String> cash(Cash cash, String address);
	//获取所有体现记录
	List<Map<String, Object>> cashInfo(Cash cash); 
	//获取所有体现数量
	int cashInfoCount(Cash cash);
	
	//审核体现
	int updateCashAudit(Cash cash);
	
	//通过id 查询账户信息
	double selectAccount(Integer userId);
  
  
}
