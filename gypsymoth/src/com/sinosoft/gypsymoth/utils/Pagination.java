/**
 *Create on 2008-5-6
 *Copyright (c) 2008 by sinosoft.
 *@author <a href="mailto:gaoweipeng0120@163.com">��gaoweipeng��ggasdasdasdgao gaoweipeng </a> 
 *@version 1.0
**/
package com.sinosoft.gypsymoth.utils;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
 
public  class  Pagination {
	private final Logger _logger = Logger.getLogger(Pagination.class);

	/**
	 * 当前页数
	 */
	private final int currPage;
	
	
	/**
	 * 每页显示数量
	 */
	private final int numOfEachPage;
			
	/**
	 * 总页数
	 */
	private final int totlePage;
	

	public Pagination(int currPage,int numOfEachPage,int totlePage){
		
		
		this.currPage=currPage;
				
		this.numOfEachPage=numOfEachPage;
	
		this.totlePage=totlePage;
		
	}
	
	
	

	public final int getCurrPage() {
		return currPage;
	}



	public final int getTotlePage() {
		return totlePage;
	}




	public final int getNumOfEachPage() {
		return numOfEachPage;
	}


	/**
	 * @return 分页起始行数 rownum_begin
	 */
	public int getRownum_begin(int numOfEachPage,int currPage) {
		// numOfEachPage*(currPage-1)+1

		//return numOfEachPage * (currPage - 1) + 1;
		return numOfEachPage * (currPage - 1);
	}

	/**
	 * @return 分页结束行数 rownum_end
	 */
	public int getRownum_end(int currPage,int numOfEachPage) {

		//currPage*numOfEachPage
		return currPage*numOfEachPage;
	}

	/**
	 * Action中需要分页功能是调用的参数配置
	 * @param request
	 * @param map
	 * @return
	 */
	public HashMap getPaginationMapParam(HttpServletRequest request,HashMap map,Integer currPage1){

		Pagination pagination = null;
		int  numOfEachPage = 1;
			if(map.get("msg")==null)
			{
				numOfEachPage =	Constants.NUMOFEACHPAGE;
			}
			
		int getRownum_begin=this.getRownum_begin(numOfEachPage, currPage1);
		int getRownum_end=this.getRownum_end(currPage1, numOfEachPage);
		
		map.put("getRownum_begin", getRownum_begin);
		map.put("getRownum_end", getRownum_end);
		return map;
	}
	
	/**
	 * 分页结果
	 * @param totleCount
	 * @param currPage1
	 * @param numOfEachPage
	 * @param pagination
	 * @return
	 */
	public void getPagination(HttpServletRequest request,Integer totleCount,Integer currPage1,
			String formId,String actionName,String namespace){
		_logger.info("totleCount:"+totleCount);
		int  numOfEachPage = 1;
		if(request.getAttribute("msg")==null)
		{
			numOfEachPage =	Constants.NUMOFEACHPAGE;
		}
		Integer totlePageCount =totleCount/numOfEachPage;
		if(totleCount%numOfEachPage!=0)
		{
			totlePageCount += 1;
		}
		Pagination pagination = new Pagination(currPage1, numOfEachPage, totlePageCount);	
		request.setAttribute("framework_util_Pagination", pagination);
	}

}
