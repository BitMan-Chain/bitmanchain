package com.shoufubang.pay.lianpay.util.finaldata;



import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shoufubang.pay.lianpay.config.PartnerConfig;
import com.shoufubang.pay.lianpay.model.PaymentInfo;
import com.shoufubang.pay.lianpay.util.LLPayUtil;

@Service
public class LianPayData  {

	public static String getFinalPayData(PaymentInfo paymentInfo) {
		String sign = LLPayUtil.addSign(JSON.parseObject(JSON
                .toJSONString(paymentInfo)), PartnerConfig.TRADER_PRI_KEY,
				PartnerConfig.MD5_KEY);
		paymentInfo.setSign(sign);
		String req_data = JSON.toJSONString(paymentInfo);
		return req_data;
	}
	  /**
     * 根据连连支付风控部门要求的参数进行构造风控参数
     * @return
     */
	public static String createRiskItem()
    {
        JSONObject riskItemObj = new JSONObject();
        riskItemObj.put("user_info_bind_phone", "13058701456");
        riskItemObj.put("user_info_full_name", "李帅");
        riskItemObj.put("frms_ware_category", "1014");
        return riskItemObj.toString();
    }




	
}
