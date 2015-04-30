package com.sinosoft.gypsymoth.bussiness.statistics.action;

import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.jfree.data.category.DefaultCategoryDataset;

import com.opensymphony.xwork2.ActionSupport;
import com.sinosoft.gypsymoth.bussiness.statistics.service.StatisticsSecondService;
import com.sinosoft.gypsymoth.exception.AppException;
import com.sinosoft.gypsymoth.utils.CalendarUtils;
import com.sinosoft.gypsymoth.utils.ChartUtil;
import com.sinosoft.gypsymoth.utils.ExcelUtil;

/**
 * 客户月份业务量统计
 * @author Administrator
 *
 */
public class StatisticsFifthAction extends ActionSupport {

	
	private StatisticsSecondService statisticsSecondService;
	
	private String parentId;
	private String isChild;
	private String orgId;
	private String selectOrg;
	private String portId;
	private static int table_weith = 120;
	
	/**
	 * 初始化
	 * @return
	 */
	public String initFifth()
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
	public String toFifth()
	{ 
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String statisticsby = request.getParameter("statisticsby"); //1:按月 2:按季度 3:按年度 统计
		String year_begin = request.getParameter("year_begin");
		String year_end = request.getParameter("year_end");
		String quarter_begin = request.getParameter("quarter_begin");
		String quarter_end = request.getParameter("quarter_end");
		String month_begin = request.getParameter("month_begin");
		String month_end = request.getParameter("month_end");
		
		String client_input = request.getParameter("client_input");
		
		
		String filename_top ="";
		String filename_bottom ="";
		List list = null;
		List arealist = null;
		List totlelist = new ArrayList();
		int totle = 0;
		
		HashMap map = new HashMap();
		
		try {

			if (statisticsby!=null&&statisticsby.equals("2")) {	//按季度统计,设置统计区间日期
				month_begin = beginMonthQuarter(quarter_begin);
				month_end = endMonthQuarter(quarter_end);
			}else if (statisticsby!=null&&statisticsby.equals("3")) {	//按年度统计，设置区间为全年
				month_begin = "1";
				month_end = "12";
			}
			
			if (selectOrg!=null&&selectOrg.equals("1")) {	//统计一级公司的信息
				if (parentId!=null&&!parentId.equals("")) {
					map.put("org4", parentId.substring(0, 2));
				}
			}else if(selectOrg!=null&&selectOrg.equals("2")){	//统计二级公司的信息
				map.put("org4", orgId);
			}else if(selectOrg!=null&&selectOrg.equals("3")){	//统计港口的信息
				if (orgId!=null&&!orgId.equals("")) {	//二级对应所有港口
					map.put("org4", orgId);
				}else if(parentId!=null&&!parentId.equals("")){	//二级选择全部，则统计内容为一级对应所有港口
					map.put("org4", parentId.substring(0, 2));
				}
				map.put("portid", portId); 
			}
			
			map.put("condition", client_input);
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
			Date begin = simpleDateFormat.parse(year_begin+month_begin);
			Date end = simpleDateFormat.parse(year_end+month_end);
			map.put("date_begin",year_begin+month_begin);
			map.put("date_end",year_end+month_end);
			
			if (statisticsby!=null&&statisticsby.equals("1")) {	//按月统计
				arealist = CalendarUtils.monthBetweenYear(begin, end);
				map.put("arealist", arealist);
				list = statisticsSecondService.getFifthlistToMonth(map);
			}else if(statisticsby!=null&&statisticsby.equals("2")){	//按季度统计
				arealist = CalendarUtils.quarterBetweenYear(begin, end);
				map.put("arealist", arealist);
				list = statisticsSecondService.getFifthlistToQuarter(map);
			}else if(statisticsby!=null&&statisticsby.equals("3")){	//按年度统计
				arealist = CalendarUtils.yearBetweenYear(begin, end);
				map.put("arealist", arealist);
				list = statisticsSecondService.getFifthlistToYear(map);
			}
			
			
			if (list!=null&&list.size()>0) {
				for (int i = 0; i < list.size(); i++) {	//得到客户及统计结果列表
					HashMap monmap = (HashMap)list.get(i);
					for (int j = 0; j < arealist.size(); j++) {	//得到单行记录
						String cn = monmap.get("CN"+j).toString();
						totle = totle +Integer.valueOf(cn); 
						if(i==0) {
							totlelist.add(Integer.valueOf(cn));
						}else { 
							int temp = (Integer) totlelist.get(j);
							totlelist.set(j, temp+Integer.valueOf(cn));
						}
					}
				}
				filename_top = createFifthTopImg(arealist, list);	//生成柱状图
				filename_bottom = createFifthBottomImg(arealist, totlelist,statisticsby);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		
		request.setAttribute("statisicsby", statisticsby);
		request.setAttribute("filename_top", filename_top);
		request.setAttribute("filename_bottom", filename_bottom); 
		request.setAttribute("list", list);
		request.setAttribute("arealist", arealist);
		request.setAttribute("totlelist", totlelist);
		request.setAttribute("totle", totle);
		
		return SUCCESS;
	}
	
	
	/**
	 * 生成客户业务统计图(第一张)
	 * @param oneMap
	 * @return
	 */
	public String createFifthTopImg(List areaList,List list)
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String filename = "";
		int top_weith = 500;
		int top_hight = 300;
		
		try {
			DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
			for (int i = 0; i < list.size(); i++) {
				HashMap map = (HashMap)list.get(i);
				int totle_bn = 0;
				for(int j=0;j<areaList.size();j++){
           	 		String cn = map.get("CN"+j).toString();
           	 		totle_bn = totle_bn + Integer.valueOf(cn);
           	 	}
				String bn = map.get("BN").toString();
				dataset.addValue(totle_bn, "", bn);
			}
			if (list!=null&&list.size()>0) {
				top_weith = list.size()*table_weith>top_weith?list.size()*table_weith:top_weith;
			} 
			filename = ChartUtil.createBarChartChart("客户业务量统计图", "客户", "业务量(船次)", session, dataset, top_weith, top_hight);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
		request.setAttribute("top_weith", top_weith);
		request.setAttribute("top_hight", top_hight); 
		String graphURL = request.getContextPath() + "/DisplayChart?filename=" + filename; 
		return graphURL;
	}
	
	/**
	 * 生成客户业务统计图(第二张)
	 * @param areaList	表格列
	 * @param list	表格内容
	 * @param statisticsby	统计类型 1:月份 2:季度 3:年度
	 * @return
	 */ 
	public String createFifthBottomImg(List areaList,List totlelist,String statisticsby)
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String filename = "";
		String table_name = "";
		int bootom_weith = 500;
		int bootom_hight = 300;
		try {
			DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
			for (int i = 0; i < areaList.size(); i++) {
				HashMap map = (HashMap)areaList.get(i);
				dataset.setValue(Double.valueOf(totlelist.get(i).toString()), "",map.get("name").toString());
			}
			if (statisticsby!=null&&statisticsby.equals("1")) {	//月份
				table_name  = "月份";
			}else if (statisticsby!=null&&statisticsby.equals("2")) {	//季度
				table_name  = "季度";
			}else if (statisticsby!=null&&statisticsby.equals("3")) {	//年度
				table_name  = "年度";
			}
			
			if (areaList!=null&areaList.size()>0) {
				bootom_weith =	areaList.size()*table_weith>bootom_weith?areaList.size()*table_weith:bootom_weith;
			}
			filename = ChartUtil.createBarChartChart("客户业务量统计图", table_name, "业务量(船次)", session, dataset, bootom_weith, bootom_hight);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		String graphURL = request.getContextPath() + "/DisplayChart?filename=" + filename; 
		request.setAttribute("bootom_weith", bootom_weith);
		request.setAttribute("bootom_hight", bootom_hight); 
		return graphURL;
	}

	
	public void toFifthExcel()
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
		
			String sheetname = "客户业务量统计";
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
	
	
	/**	根据季度得到开始月份	*/
	public String beginMonthQuarter(String quarter){
		String month = "1";
		if (quarter.equals("1")) {
			month="1";
		}else if(quarter.equals("2")){
			month="4";
		}else if(quarter.equals("3")){
			month="7";
		}else if(quarter.equals("4")){
			month="10";
		}
		return month;
	}
	
	/**	根据季度得到结束月份	*/
	public String endMonthQuarter(String quarter){
		String month = "12";
		if (quarter.equals("1")) {
			month="3";
		}else if(quarter.equals("2")){
			month="6";
		}else if(quarter.equals("3")){
			month="9";
		}else if(quarter.equals("4")){
			month="12";
		}
		return month;
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
	
	public String getPortId() {
		return portId;
	}

	public void setPortId(String portId) {
		this.portId = portId;
	}
	
	public static void main(String[] args) {
		
		String yearBegin = "2010";
		String yearEnd = "2011";
		String month_begin = "1";
		String month_end = "12";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
		
//		List monthlist = CalendarUtils.monthBetweenYear(begin, end);
//		HashMap map = new HashMap();
//		map.put("arealist", monthlist);
//		List list = statisticsSecondService.getFifthlistToMonth(map);
		try {
			Date begin = simpleDateFormat.parse(yearBegin+month_begin);
			Date end = simpleDateFormat.parse(yearEnd+month_end);
			List list = CalendarUtils.yearBetweenYear(begin, end);
			System.out.println("end");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
}
