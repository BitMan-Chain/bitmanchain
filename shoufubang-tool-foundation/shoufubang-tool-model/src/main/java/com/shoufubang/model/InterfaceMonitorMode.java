package com.shoufubang.model;

import java.util.Date;

/**
 * 接口监控
 * @author shanglishuai
 * 如何使用：
 * 1. 注入接口监控业务对象  
 * 	  @Autowired private InterfaceService interfaceService;
 * 2. 获取接口监控对象 
 * 	  InterfaceMonitorMode interfaceMonitorMode = InterfaceMonitorMode.getInterfaceMonitorMode("接口名称", "接口代码");
 * 	  此行代码需要在接口调用层的首行获取
 * 	  获取接口监控对象时需要【接口名称】、【接口代码】的入参，两个参数可以自定义，需要有意义，可以顾名思义
 *    接口监控默认是成功的
 * 3. 如果接口调用失败，需要设置失败的消息
 * 	  InterfaceMonitorMode.setFail(interfaceMonitorMode);
 *    interfaceMonitorMode参数是第二步中获取的接口监控对象
 * 4. 持久化接口监控信息
 * 	  interfaceService.interfaceMonitor(interfaceMonitorMode);
 * 示例：
 * 	@Controller
 * 	public class TestController {
 * 		@Autowired private InterfaceService interfaceService;
 *  	public void testLogin(){
 *  		InterfaceMonitorMode interfaceMonitorMode = InterfaceMonitorMode.getInterfaceMonitorMode("接口名称", "接口代码");
 *  		try{
 *  			// testLogin 业务代码 省略
 *  		}catch(Exception e){
 *  			// 捕获异常，并设置失败消息
 *  			InterfaceMonitorMode.setFail(interfaceMonitorMode);
 *  		}
 *  		interfaceService.interfaceMonitor(interfaceMonitorMode);
 *  	}
 *  }
 *
 */
public class InterfaceMonitorMode {
	private int id;
	private String name; // 接口名称
	private String code; // 接口编码
	private Date time; // 接口调用时间
	private int status; // 接口调用状态0:成功，1:失败
	private String remark; // 接口备注

	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	/**
	 * 接口监控初始化信息，默认接口调用成功
	 * @param name 接口名称
	 * @param code 接口编码
	 * @return 接口监控对象
	 */
	public static InterfaceMonitorMode getInterfaceMonitorMode(String name, String code) {
		InterfaceMonitorMode interfaceMonitorMode = new InterfaceMonitorMode();
		interfaceMonitorMode.setName(name);
		interfaceMonitorMode.setCode(code);
		interfaceMonitorMode.setTime(new Date());
		interfaceMonitorMode.setStatus(0);
		interfaceMonitorMode.setRemark("接口调用成功");
		return interfaceMonitorMode;
	}
	
	/**
	 * 设置接口调用失败属性
	 * @param interfaceMonitorMode 接口监控初始对象
	 */
	public static void setFail(InterfaceMonitorMode interfaceMonitorMode) {
		interfaceMonitorMode.setStatus(1);
		interfaceMonitorMode.setRemark("接口调用失败");
	}
	
}
