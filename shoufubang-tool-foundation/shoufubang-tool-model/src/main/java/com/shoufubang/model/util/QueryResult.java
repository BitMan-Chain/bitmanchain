package com.shoufubang.model.util;

import java.util.List;
import java.util.Map;

/**
 * 查询结果集,包括：查询结果（resultlist）+总记录数（totalrecord）
 * 
 */
public class QueryResult {

	private List<Map<String,Object>> resultlist; // 结果集
	private long totalrecord;	// 总记录数
	
	public QueryResult(){}
	
	public QueryResult(List<Map<String,Object>> resultlist,long totalrecord){
		this.totalrecord = totalrecord;
		this.resultlist = resultlist;
	}
	
	public List<Map<String,Object>> getResultlist() {

		return resultlist;
	}

	public void setResultlist(List<Map<String,Object>> resultlist) {

		this.resultlist = resultlist;
	}

	public long getTotalrecord() {

		return totalrecord;
	}

	public void setTotalrecord(long totalrecord) {

		this.totalrecord = totalrecord;
	}
}
