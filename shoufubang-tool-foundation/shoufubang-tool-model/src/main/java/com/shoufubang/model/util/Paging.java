package com.shoufubang.model.util;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import arch.util.lang.Predef;

/**
 * 分页模板生成类
 * @author hello
 *
 */
public class Paging {
	
	private static  final String pageTemplate = "<div class='page'><b>共#{{rowCount}}条</b> <b>#{{pageRows}}条/页</b>  <b>第#{{currentPage}}/#{{pageCount}}页</b>"
			+ "#{{firstPage}} #{{pagePrev}} #{{target_index_list}} #{{pageNext}} #{{lastPage}}</div>";
	
	/**
	 * "第一页"模板
	 */
	private static final String firstPage = "<a href='#{{firstPage_href}}.html' class='page-ctrl page-ctrl_first'>首页</a>";
	
	/**
	 * "最后一页"模板
	 */
	private static final String lastPage = "<a href='#{{lastPage_href}}.html' class='page-ctrl page-ctrl_first'>尾页</a>";
	
	/**
	 * 页码所指地址
	 */
	private static final String targetPageIndex = "<a href='#{{target_href}}.html'>#{{target_index}}</a> ";//<a href='index2.html'>2</a> 
	
	/**
	 * 当前页页码
	 */
	private static final String currentPageIndex = "<span class='this_page'>#{{current_index}}</span>";
	
	/**
	 * "上一页"模板
	 */
	private static final String pagePrev = "<a href='#{{pagePrev_href}}.html' class='page-ctrl page-ctrl_prev'>上一页</a>";
	
	/**
	 * "下一页"模板
	 */
	private static final String pageNext = "<a href='#{{pageNext_href}}.html' class='page-ctrl page-ctrl_next'>下一页</a>";
	
	/**
	 * 生成页面静态分页模板：
	 * 参数：
	 * 		pageName(String): 要生成的页面名称前缀（eg:pagename1.html,pagename2.html,...）；
	 * 		rowCount  (int) : 条目总数；
	 * 		pageRows:(int)  : 每页显示条目最大数目；
	 *      pageIndexCount(int): 每页显示的页码最大数目
	 *      
	 *      //pageCount(int)  : 页面总数,根据上面信息计算
	 *      //currentPage :当前页的页码
	 * @param args
	 * @return List<Map<String,Object>> 所有页面对应的分页模板
	 */
	public static List<String> pageIndexStatic(Map<String,Object> args){
		List<String> pages = new ArrayList<String>();
		int rowCount = Integer.parseInt(args.get("rowCount").toString());
		if(rowCount<1){
			return pages;
		}
		Object prePath = args.get("prePath");//eg:front/index/
		String categoryId = args.get("categoryId")+"";
		String prePathStr = "";
		//if(Predef.isNotEmpty(prePath)){
		if(prePath!=null){
			prePathStr = prePath.toString();
		}
		int pageRows = Integer.parseInt(args.get("pageRows").toString());
		//得到生成页面总数
		int pageCount = rowCount/pageRows+(rowCount%pageRows>0?1:0);
		//得到生成的页面名称前缀
		String pageName = (String)args.get("pageName");
		//生成每个页面的页码
		for(int i=0;i<pageCount;i++){
			//创建临时模板
			String pageTemplate = Paging.pageTemplate;
			String pageNext = Paging.pageNext;
			String pagePrev = Paging.pagePrev;
			String firstPage = Paging.firstPage;
			String lastPage = Paging.lastPage;
			String targetPageIndex = Paging.targetPageIndex;
			String currentPageIndex = Paging.currentPageIndex;
			
			//
			String pageShowName = prePathStr+pageName;
			//当前页的页码
			int currentPage = i+1;
			//1.替换模板统计类信息
			pageTemplate=pageTemplate.replace("#{{rowCount}}", ""+rowCount);
			pageTemplate=pageTemplate.replace("#{{pageRows}}", ""+pageRows);
			pageTemplate=pageTemplate.replace("#{{currentPage}}", ""+currentPage);
			pageTemplate=pageTemplate.replace("#{{pageCount}}", ""+pageCount);
			//2.上、下页，首、末页
			if(currentPage==1){
				//"上一页"不显示
				pageTemplate=pageTemplate.replace("#{{pagePrev}}", "");
				//下一页
				int nextPage = currentPage+1;
				if(nextPage>pageCount){
					pageTemplate=pageTemplate.replace("#{{pageNext}}", "");
				}else{
					pageNext = pageNext.replace("#{{pageNext_href}}", pageShowName+(currentPage+1));
					pageTemplate=pageTemplate.replace("#{{pageNext}}", pageNext);
				}
			}else if(currentPage>1&&currentPage<pageCount){
				//上一页
				pagePrev = pagePrev.replace("#{{pagePrev_href}}", pageShowName+(currentPage-1));
				pageTemplate=pageTemplate.replace("#{{pagePrev}}", pagePrev);
				
				//下一页
				pageNext = pageNext.replace("#{{pageNext_href}}", pageShowName+(currentPage+1));
				pageTemplate=pageTemplate.replace("#{{pageNext}}", pageNext);
			}else {
				//上一页
				pagePrev = pagePrev.replace("#{{pagePrev_href}}", pageShowName+(currentPage-1));
				
				pageTemplate=pageTemplate.replace("#{{pagePrev}}", pagePrev);
				//"下一页"不显示
				pageTemplate=pageTemplate.replace("#{{pageNext}}", "");
			}
			//首页
			firstPage = firstPage.replace("#{{firstPage_href}}", pageShowName+1);
			pageTemplate=pageTemplate.replace("#{{firstPage}}", firstPage);
			
			//尾页
			lastPage = lastPage.replace("#{{lastPage_href}}", pageShowName+pageCount);
			pageTemplate=pageTemplate.replace("#{{lastPage}}", lastPage);
			
			//3.生成页码列表
			int pageIndexCount = Integer.parseInt(args.get("pageIndexCount").toString());
			StringBuffer target_index_list = new StringBuffer();
			int count =0;
		
			if(pageCount<=pageIndexCount){//页面总数<= 4,默认为4
				for(int j=1;j<=pageCount;j++){
					if(count>=pageIndexCount-1){
						break;
					}
					if(j!=currentPage){
						String targetPageIndexTemp = targetPageIndex;
						targetPageIndexTemp = targetPageIndexTemp.replace("#{{target_href}}", pageShowName+j);
						targetPageIndexTemp = targetPageIndexTemp.replace("#{{target_index}}", ""+j);
						target_index_list.append(targetPageIndexTemp+" ");
					}else{
						currentPageIndex = currentPageIndex.replace("#{{current_index}}", ""+currentPage);
						target_index_list.append(currentPageIndex+" ");
					}
				}
			}else{//页面总数> 4,默认为4
				
				for(int j=currentPage-1<1?1:(currentPage-1);j<=pageCount;j++){
					if(count>=pageIndexCount){
							break;
					}
					if(j==currentPage){
						currentPageIndex = currentPageIndex.replace("#{{current_index}}", ""+currentPage);
						target_index_list.append(currentPageIndex+" ");
					}else{
						String targetPageIndexTemp = targetPageIndex;
						targetPageIndexTemp = targetPageIndexTemp.replace("#{{target_href}}", pageShowName+j);
						targetPageIndexTemp = targetPageIndexTemp.replace("#{{target_index}}", ""+j);
						target_index_list.append(targetPageIndexTemp+" ");
					}
					count++;
				}
				//后续页面不足，前插
				if(count<pageIndexCount){
					for(int j=currentPage-2;j>0;j--){
						if(count>=pageIndexCount){
							break;
						}
						String targetPageIndexTemp = targetPageIndex;
						targetPageIndexTemp = targetPageIndexTemp.replace("#{{target_href}}", pageShowName+j);
						targetPageIndexTemp = targetPageIndexTemp.replace("#{{target_index}}", ""+j);
						target_index_list.insert(0,targetPageIndexTemp+" ");
						count++;
					}
				}
			}
			pageTemplate=pageTemplate.replace("#{{target_index_list}}", target_index_list.toString());
			pages.add(pageTemplate);
		}
		return pages;
	}

//	public static void main(String[] args){
//		Map<String,Object> argsMap = new HashMap<String,Object>(); 
//		argsMap.put("rowCount", "106");
//		argsMap.put("pageRows", "10");
//		argsMap.put("pageName", "index");
//		argsMap.put("pageIndexCount", "4");
//		List<String> pages = pageIndexStatic(argsMap);
//		for(int i =0;i<pages.size();i++){
//			System.out.println(pages.get(i));
//		}
//	}
	
}
