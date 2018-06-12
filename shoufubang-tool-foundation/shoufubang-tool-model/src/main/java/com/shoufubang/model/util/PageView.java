

package com.shoufubang.model.util;


public class PageView {
	
	
	/**
	 * 总条数
	 */
	private Integer totalCount;	
	
	/**
	 * 每页显示多少条
	 */
	private Integer numPerPage = 1;
	
	/**
	 * 页标数字多少个
	 */
	private Integer pageNumShown = 10;	
	
	/**
	 * 当前是第几页
	 */
	private Integer pageNum = 1;	
	
	/**
	 * 总页数
	 */
	private Integer totalPage ;
	   
	/**    
	 * <pre>创建一个新的实例 PageView.    
	 *    
	 * @param totalCount     实体里面的总页数
	 * @param numPerPage	  每页显示多少条
	 * @param pageNum</pre>  当前是第几页  
	 */
	public PageView(Integer totalCount, Integer numPerPage, Integer pageNum) {
		super();
		this.totalCount = totalCount;
		this.numPerPage = numPerPage;
		this.pageNum = pageNum;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getNumPerPage() {
		return numPerPage;
	}
	
	public void setNumPerPage(Integer numPerPage) {
		this.numPerPage = numPerPage;
	}
	public Integer getPageNumShown() {
		return pageNumShown;
	}
	public void setPageNumShown(Integer pageNumShown) {
		this.pageNumShown = pageNumShown;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getTotalPage() {
		return (((totalCount + numPerPage) -1) / numPerPage);
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

}

