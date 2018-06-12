
package com.shoufubang.model.schedule;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;



/**
 * Created  2016年7月15日09:26:27
 */

@Component
public class Schedule
{
	protected Logger logger = LoggerFactory.getLogger(Schedule.class);
    /**
     * 
     * test
     */
   // @Scheduled(cron = "0/2 * *  * * ?")
    public void subscribeRemind() {
    	logger.info("----------------------------------------提醒开始----------------------------------------------");
    	try {
    	
        	logger.info("----------------------------------------提醒成功----------------------------------------------");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("失败", e);
		}
    }

}