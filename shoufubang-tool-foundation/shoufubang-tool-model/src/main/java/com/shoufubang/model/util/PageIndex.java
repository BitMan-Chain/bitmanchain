package com.shoufubang.model.util;

/**
 * 页面索引类,即 第一页/第二页/第三页...第十页
 */
public class PageIndex {

	private long startindex;	// 页面起始索引
	private long endindex;		// 页面结束索引

	public PageIndex(long startindex, long endindex) {

		this.startindex = startindex;
		this.endindex = endindex;
	}

	public long getStartindex() {

		return startindex;
	}

	public void setStartindex(long startindex) {

		this.startindex = startindex;
	}

	public long getEndindex() {

		return endindex;
	}

	public void setEndindex(long endindex) {

		this.endindex = endindex;
	}

	/**
	 * 获取分页索引
	 * 
	 * @param viewpagecount  页码量,如：等于2的时候就显示：第一页 第二页 ... 第十页
	 * @param currentpage    当前页
	 * @param totalpage		 总页数
	 * @return
	 */
	public static PageIndex getPageIndex(long viewpagecount, int currentpage, long totalpage) {
		
		long startpage = currentpage - (viewpagecount % 2 == 0 ? viewpagecount / 2 - 1 : viewpagecount / 2);
		long endpage = currentpage + viewpagecount / 2;
		if (startpage < 1) {
			startpage = 1;
			if (totalpage >= viewpagecount)
				endpage = viewpagecount;
			else
				endpage = totalpage;
		}
		if (endpage > totalpage) {
			endpage = totalpage;
			if ((endpage - viewpagecount) > 0)
				startpage = endpage - viewpagecount + 1;
			else
				startpage = 1;
		}
		return new PageIndex(startpage, endpage);
	}
}
