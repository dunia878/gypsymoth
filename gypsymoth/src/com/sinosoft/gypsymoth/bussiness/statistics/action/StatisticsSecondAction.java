package com.sinosoft.gypsymoth.bussiness.statistics.action;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;

import com.opensymphony.xwork2.ActionSupport;
import com.sinosoft.gypsymoth.bussiness.statistics.service.ChargesStatService;
import com.sinosoft.gypsymoth.bussiness.statistics.service.StatisticsSecondService;
import com.sinosoft.gypsymoth.exception.AppException;
import com.sinosoft.gypsymoth.utils.ChartUtil;
import com.sinosoft.gypsymoth.utils.ExcelUtil;

/**
 * 目的国业务量统计
 * @author Administrator
 *
 */
public class StatisticsSecondAction extends ActionSupport {

	
	private StatisticsSecondService statisticsSecondService;
	
	private String parentId;
	private String isChild;
	private String orgId;
	private String selectOrg;
	private String portId;
	
	/**
	 * 初始化
	 * @return
	 */
	public String initSecond()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		Calendar ca = Calendar.getInstance();
		int year = ca.get(Calendar.YEAR)-5;
		List yearList = new ArrayList();
		for(int i=0;i<5;i++){
			year = year+1;
			Map map = new HashMap();
			map.put("year", year);
			yearList.add(map);
		}
		request.setAttribute("yearList", yearList);
		return SUCCESS; 
	}

	/**
	 * 目的国统计
	 * @return
	 */
	public String toSecond()
	{ 
		HttpServletRequest request = ServletActionContext.getRequest();
		String yearBegin = request.getParameter("yearBegin");
		String yearEnd = request.getParameter("yearEnd");
		String month_begin = request.getParameter("month_begin");
		String month_end = request.getParameter("month_end");
		
		HashMap oneMap = null;
		HashMap map = new HashMap(); 
		String date_begin ="";
		String date_end ="";
		try {
			if (yearBegin!=null&&!yearBegin.equals("-1")&&yearEnd!=null&&!yearEnd.equals("-1")) {
				date_begin = yearBegin+"-"+month_begin; 
				date_end = yearEnd+"-"+month_end;
				map.put("date_begin", date_begin);
				map.put("date_end", date_end);
			}
			if (selectOrg!=null&&selectOrg.equals("1")) {	//统计一级公司的信息
				if (parentId!=null&&!parentId.equals("")) {
					map.put("org4", parentId.substring(0, 2));
				}
			}else if(selectOrg!=null&&selectOrg.equals("2")){	//统计二级公司的信息
				if (orgId==null||orgId.equals("")) {
					map.put("org4", parentId.substring(0, 2));
				}else { 
					map.put("org4", orgId);
				}
				
			}else if(selectOrg!=null&&selectOrg.equals("3")){	//统计港口的信息
				if (orgId!=null&&!orgId.equals("")) {	//二级对应所有港口
					map.put("org4", orgId);
				}else if(parentId!=null&&!parentId.equals("")){	//二级选择全部，则统计内容为一级对应所有港口
					map.put("org4", parentId.substring(0, 2));
				}
				map.put("portid", portId); 
			}
			
			List onetempList = statisticsSecondService.getSecondlist(map);
			if (onetempList!=null&&onetempList.size()>0) {
				oneMap = (HashMap)onetempList.get(0);
			}
			String oneImg = createSecondImg(oneMap);
			
			 
			request.setAttribute("oneImg", oneImg);
			request.setAttribute("oneMap", oneMap);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	
	public void toSecondExcel()
	{
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String[] excel_area_hidden = request.getParameterValues("excel_area_hidden"); 
		String[] excel_list_hidden = request.getParameterValues("excel_list_hidden");
		String[] excel_totle_hidden = request.getParameterValues("excel_totle_hidden");
		try {
			List list = new ArrayList();
			if (excel_area_hidden!=null&&excel_area_hidden.length>0) {
				List arealist = new ArrayList();
				int num = excel_area_hidden.length;
				for (int i = 0; i < excel_area_hidden.length; i++)	//第一行
					arealist.add(excel_area_hidden[i]);
				list.add(arealist);
				
				int colnum = excel_list_hidden.length/excel_area_hidden.length;
				for (int i = 0; i < colnum; i++) {
					List contentlist = new ArrayList();
					for (int j = 0; j < num; j++) {
						int cur = i*num+j;
						contentlist.add(excel_list_hidden[cur]); 
					}
					list.add(contentlist); 
				}
				List totlelist = new ArrayList();
				for (int i = 0; i < excel_totle_hidden.length; i++)	//合计行
					totlelist.add(excel_totle_hidden[i]);
				list.add(totlelist); 
				
			}
		
			String sheetname = "目的国业务量统计";
			String formatFileName = URLEncoder.encode(sheetname, "UTF-8");
			
			HSSFWorkbook workbook = ExcelUtil.toExcel(sheetname, list);
			
			response.reset();
			response.setContentType("application/octet-stream");
            response.addHeader("Content-Disposition", "attachment;filename="+formatFileName+".xls");
            workbook.write(response.getOutputStream());
			 
			response.getOutputStream().close(); 
			
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}
	
	
	public void toSecondExcelOther()
	{
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String[] excel_other_hidden = request.getParameterValues("excel_other_hidden"); 
		try {
			List list = new ArrayList();
			int num = excel_other_hidden.length/2;
			for (int i = 0; i < num ; i++) {
				List contentlist = new ArrayList();
				for (int j = 0; j < 2; j++) {
					int cur = i*2+j;
					contentlist.add(excel_other_hidden[cur]);
				}
				list.add(contentlist);
			}
		
			String sheetname = "目的国业务量统计";
			String formatFileName = URLEncoder.encode(sheetname, "UTF-8");
			HSSFWorkbook workbook = ExcelUtil.toExcel(sheetname, list);
			response.reset();
			response.setContentType("application/octet-stream");
            response.addHeader("Content-Disposition", "attachment;filename="+formatFileName+".xls");
            workbook.write(response.getOutputStream());
			 
			response.getOutputStream().close(); 
			
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 目的国柱状图
	 * @param oneMap
	 * @return
	 */
	public String createSecondImg(HashMap oneMap)
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String filename = "";
		try {
			/**  简单的数据集dataset */
			DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
			double USA = Double.valueOf(oneMap.get("USA").toString());
			double CANADA = Double.valueOf(oneMap.get("CANADA").toString());
			double OTHER = Double.valueOf(oneMap.get("OTHER").toString());
			double MEXICO = Double.valueOf(oneMap.get("MEXICO").toString());
			double CANADA_USA = Double.valueOf(oneMap.get("CANADA_USA").toString());
			double CANADA_MEXICO = Double.valueOf(oneMap.get("CANADA_MEXICO").toString());
			double CANADA_OTHER = Double.valueOf(oneMap.get("CANADA_OTHER").toString());
			double MEXICO_USA = Double.valueOf(oneMap.get("MEXICO_USA").toString());
			double MEXICO_OTHER = Double.valueOf(oneMap.get("MEXICO_OTHER").toString());
			double OTHER_USA = Double.valueOf(oneMap.get("OTHER_USA").toString());
			
			double threecountry = USA+CANADA+MEXICO+CANADA_USA+MEXICO_USA+CANADA_MEXICO;
			double totlecountry = USA+CANADA+MEXICO+CANADA_USA+MEXICO_USA+CANADA_MEXICO+OTHER_USA+CANADA_OTHER+MEXICO_OTHER+OTHER;
			dataset.addValue(threecountry, "", "北美三国");
			dataset.addValue(totlecountry-threecountry, "", "其他国家");
			dataset.addValue(totlecountry, "", "总计"); 
			filename = ChartUtil.createBarChartChart("国家业务量统计图", "国家", "业务量(船次)", session, dataset, 500, 300);
		}catch (Exception e) {
			e.printStackTrace(); 
		}
	
		String graphURL = request.getContextPath() + "/DisplayChart?filename=" + filename; 
		return graphURL;
	}
	

	
	/**
	 * 根据权限获取一级机构菜单
	 * @return
	 * @throws AppException
	 */
	public String firstList() throws AppException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Map selectMap = new HashMap();
		selectMap.put("parentId", "");
		selectMap.put("isChild", "1");
		List orgList = new ArrayList();
		try {
			orgList = statisticsSecondService.getOrgList(selectMap);
		} catch (Exception e) {
			throw new AppException("获取机构列表", e);
		}
		request.setAttribute("firstOrgList", orgList);
		request.setAttribute("type", 1);
		return SUCCESS;
	}
	
	/**
	 * 根据权限获取二级机构菜单
	 * @return
	 * @throws AppException
	 */
	public String secondList() throws AppException {
		HttpServletRequest request = ServletActionContext.getRequest();
		parentId = (String)request.getParameter("input");
		Map selectMap = new HashMap();
		selectMap.put("parentId", parentId);
		selectMap.put("isChild", "2");
		List orgList = new ArrayList();
		try {
			orgList = statisticsSecondService.getOrgList(selectMap);
		} catch (Exception e) {
			throw new AppException("获取机构列表", e);
		}
		request.setAttribute("secondOrgList", orgList);
		request.setAttribute("type", 2);
		return SUCCESS;
	}

	/**
	 * 获取港口列表
	 * @return
	 * @throws AppException
	 */
	public String portList() throws AppException {
		HttpServletRequest request = ServletActionContext.getRequest();
		orgId = (String)request.getParameter("input");
		Map selectMap = new HashMap();
		selectMap.put("orgId", orgId);
		List portList = new ArrayList();
		try {
			portList = statisticsSecondService.getPortList(selectMap);
		} catch (Exception e) {
			throw new AppException("获取港口列表", e);
		}
		request.setAttribute("portList", portList);
		request.setAttribute("type", 3);
		return SUCCESS;
	}
	

	public StatisticsSecondService getStatisticsSecondService() {
		return statisticsSecondService;
	}


	public void setStatisticsSecondService(
			StatisticsSecondService statisticsSecondService) {
		this.statisticsSecondService = statisticsSecondService;
	}

	public String getParentId() {
		return parentId;
	}


	public void setParentId(String parentId) {
		this.parentId = parentId;
	}


	public String getIsChild() {
		return isChild;
	}


	public void setIsChild(String isChild) {
		this.isChild = isChild;
	}


	public String getOrgId() {
		return orgId;
	}


	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}


	public String getSelectOrg() {
		return selectOrg;
	}


	public void setSelectOrg(String selectOrg) {
		this.selectOrg = selectOrg;
	}
	
	public static void main(String[] args) {
	 
	}

	public String getPortId() {
		return portId;
	}

	public void setPortId(String portId) {
		this.portId = portId;
	}
	
}
