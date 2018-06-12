package com.shoufubang.model.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class OrderUtil {
	
	/**
	 * 生成订单id
	 * @param userId
	 * @param dealType
	 * @return
	 */
	public static String generateOrderId(Integer userId, String dealType) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = sdf.format(new Date());
		return userId + dealType + time;
	}

}
