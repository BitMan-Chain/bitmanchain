

package com.shoufubang.model.util;

import java.math.BigDecimal;

public class BaseModel {
	private Integer page = 1; // 当前页,默认1
	/**
	 * 当前是第几页
	 */
	private Integer pageNum = 1;
	
	/**
	 * 每页显示多少条
	 */
	
	private Integer numPerPage = 20;
	
	private Integer pageSize;
	
	private Integer startRow = 0; // 分页开始行
    private String startTime; //开始时间
    private String endTime;   //结束时间
    private String nstartTime; //2开始时间 
    private String nendTime;  //2结束时间
    private BigDecimal startMoney;
    private BigDecimal endMoney;
    private String selectPhone;
    private String nstatus;
    private String fuzzyString; //模糊查询字符串参数
    private String nfuzzyString; //模糊查询字符串参数
    private String templateName;//模板名称

    
	public BigDecimal getStartMoney() {
		return startMoney;
	}

	public void setStartMoney(BigDecimal startMoney) {
		this.startMoney = startMoney;
	}

	public BigDecimal getEndMoney() {
		return endMoney;
	}

	public void setEndMoney(BigDecimal endMoney) {
		this.endMoney = endMoney;
	}

	public String getNstartTime() {
		return nstartTime;
	}

	public void setNstartTime(String nstartTime) {
		this.nstartTime = nstartTime;
	}

	public String getNendTime() {
		return nendTime;
	}

	public void setNendTime(String nendTime) {
		this.nendTime = nendTime;
	}

	public String getNstatus() {
		return nstatus;
	}

	public void setNstatus(String nstatus) {
		this.nstatus = nstatus;
	}

	public String getSelectPhone() {
		return selectPhone;
	}

	public void setSelectPhone(String selectPhone) {
		this.selectPhone = selectPhone;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		if(pageSize > 0){
			this.setNumPerPage(pageSize);
		}
		this.pageSize = pageSize;
	}



	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}


	public Integer getPageNum() {
		return pageNum;
	}

	public Integer getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(Integer numPerPage) {
		this.numPerPage = numPerPage;
	}

	public Integer getStartRow() {
		return startRow;
	}

	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}

	public void setStartRow(Integer currentpage,Integer maxresult) {
		this.startRow = (currentpage - 1) * maxresult;
	}
	public void setStartRow1(Integer current,Integer max) {
		this.startRow = (current - 1) * max;
	}
	
	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getFuzzyString() {
		return fuzzyString;
	}

	public void setFuzzyString(String fuzzyString) {
		this.fuzzyString = fuzzyString;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getNfuzzyString() {
		return nfuzzyString;
	}

	public void setNfuzzyString(String nfuzzyString) {
		this.nfuzzyString = nfuzzyString;
	}

	
}

