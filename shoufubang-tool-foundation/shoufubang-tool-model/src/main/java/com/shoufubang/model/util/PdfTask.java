package com.shoufubang.model.util;



import java.util.Map;

import com.shoufubang.model.util.BrhResult;

/**
 * 生成合同PDF的任务数据
 * @author star
 *
 */
public class PdfTask {

	/**
	 * 项目基本信息
	 */
	private Map<String,Object> projectInfo;
	private BrhResult rs;

	public Map<String, Object> getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(Map<String, Object> projectInfo) {
		this.projectInfo = projectInfo;
	}

	public void setContractInfo(BrhResult rs) {
		this.rs = rs;
	}
	public BrhResult getContractInfo() {
		return this.rs;
	}
	
}
