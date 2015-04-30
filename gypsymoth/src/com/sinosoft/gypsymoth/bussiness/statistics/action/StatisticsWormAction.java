package com.sinosoft.gypsymoth.bussiness.statistics.action;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.jfree.data.category.DefaultCategoryDataset;

import com.opensymphony.xwork2.ActionSupport;
import com.sinosoft.gypsymoth.bussiness.statistics.service.StatisticsWormService;
import com.sinosoft.gypsymoth.exception.AppException;
import com.sinosoft.gypsymoth.utils.ChartUtil;
import com.sinosoft.gypsymoth.utils.ExcelUtil;

public class StatisticsWormAction extends ActionSupport {


	
	private static final long serialVersionUID = 1L;
	
	private StatisticsWormService statisticsWormService;
	
	private static final Logger _logger = Logger.getLogger(StatisticsWormAction.class);	
	
	private String parentId;
	
	private String isChild;
	
	private String orgId;
	
	private String selectOrg;
	
	private String portId;
	
	private static int TABLE_WEITH = 120; 
	
	public String getSelectOrg() {
		return selectOrg;
	}



	public void setSelectOrg(String selectOrg) {
		this.selectOrg = selectOrg;
	}



	public String getOrgId() {
		return orgId;
	}



	public void setOrgId(String orgId) {
		this.orgId = orgId;
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
	

	public String getPortId() {
		return portId;
	}



	public void setPortId(String portId) {
		this.portId = portId;
	}



	public StatisticsWormService getStatisticsWormService() {
		return statisticsWormService;
	}
	

	public void setStatisticsWormService(StatisticsWormService statisticsWormService) {
		this.statisticsWormService = statisticsWormService;
	}



	/**
	 * 初始化数据
	 * @return
	 * @throws AppException
	 */
	public String getInitWorm() throws AppException {
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
	 * 根据权限获取一级机构菜单
	 * @return
	 * @throws AppException
	 */
	public String getFirstOrgList() throws AppException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Map selectMap = new HashMap();
		selectMap.put("parentId", "");
		selectMap.put("isChild", "1");
		List orgList = new ArrayList();
		try {
			orgList = statisticsWormService.getOrgList(selectMap);
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
	public String getSecondOrgList() throws AppException {
		HttpServletRequest request = ServletActionContext.getRequest();
		parentId = (String)request.getParameter("input");
		Map selectMap = new HashMap();
		selectMap.put("parentId", parentId);
		selectMap.put("isChild", "2");
		List orgList = new ArrayList();
		try {
			orgList = statisticsWormService.getOrgList(selectMap);
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
	public String getPortList() throws AppException {
		HttpServletRequest request = ServletActionContext.getRequest();
		orgId = (String)request.getParameter("input");
		Map selectMap = new HashMap();
		selectMap.put("orgId", orgId);
		List portList = new ArrayList();
		try {
			portList = statisticsWormService.getPortList(selectMap);
		} catch (Exception e) {
			throw new AppException("获取港口列表", e);
		}
		request.setAttribute("portList", portList);
		request.setAttribute("type", 3);
		return SUCCESS;
	}

	/**
	 * 虫态及数量统计
	 * @return
	 */
	public String getWormStatistics()
	{ 
		HttpServletRequest request = ServletActionContext.getRequest();
		String yearBegin = request.getParameter("yearBegin");
		String yearEnd = request.getParameter("yearEnd");
		String month_begin = request.getParameter("beginmonth");
		String month_end = request.getParameter("endmonth");
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
				map.put("org4", orgId);
			}else if(selectOrg!=null&&selectOrg.equals("3")){	//统计港口的信息
				if (orgId!=null&&!orgId.equals("")) {	//二级对应所有港口
					map.put("org4", orgId);
				}else if(parentId!=null&&!parentId.equals("")){	//二级选择全部，则统计内容为一级对应所有港口
					map.put("org4", parentId.substring(0, 2));
				}
				map.put("portid", portId); 
			}
			List onetempList = statisticsWormService.getWormStatistics(map);
			if (onetempList!=null&&onetempList.size()>0) {
				oneMap = (HashMap)onetempList.get(0);
			}
			String oneImg = createWormImg(onetempList);
			String orgImg = createOrgImg(onetempList);
			request.setAttribute("list", onetempList);
			request.setAttribute("orgImg", orgImg);
			request.setAttribute("orgMap", oneMap);
			request.setAttribute("oneImg", oneImg);
			request.setAttribute("oneMap", oneMap);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		
		return SUCCESS;
	}
	/**
	 * 机构虫态数量柱状图
	 * @param oneMap
	 * @return
	 */
	public String createOrgImg(List list)
	{
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String filename = "";
		int weith=500;
		int hight=300;
		
		try {
			/**  简单的数据集dataset */
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			for(int i=0;i<list.size();i++){
				Map returnMap = (Map)list.get(i);
				double CN1 = Double.valueOf(returnMap.get("CN1").toString());
				double CN2 = Double.valueOf(returnMap.get("CN2").toString());
				double CN3 = Double.valueOf(returnMap.get("CN3").toString());
				double CN4 = Double.valueOf(returnMap.get("CN4").toString());
				double CN5 = Double.valueOf(returnMap.get("CN5").toString());
				double total = CN1+CN2+CN3+CN4+CN5;
				String orgName = returnMap.get("ORG_SNAME").toString();
				dataset.addValue(total, "", orgName);				
			}		
			if (list!=null&&list.size()>0) {
				weith = list.size()*TABLE_WEITH>weith?list.size()*TABLE_WEITH:weith;
			} 
				filename = ChartUtil.createBarChartChart("舞毒蛾类型统计图", "机构舞毒蛾数量统计", "数量(个)", session, dataset, weith, hight);
			
		}catch (Exception e) {
			e.printStackTrace(); 
		}
		
		String graphURL = request.getContextPath() + "/DisplayChart?filename=" + filename; 
		return graphURL;
	}
	
	/**
	 * 虫态数量柱状图
	 * @param oneMap
	 * @return
	 */
	public String createWormImg(List list)
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String filename = "";
		int weith=500;
		int hight=300;
		try {
			/**  简单的数据集dataset */
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			double CN1=0;
			double CN2=0;
			double CN3=0;
			double CN4=0;
			double CN5=0;	
			for(int i=0;i<list.size();i++){
				Map returnMap = (Map)list.get(i);
			 CN1 += Double.valueOf(returnMap.get("CN1").toString());
			 CN2 += Double.valueOf(returnMap.get("CN2").toString());
			 CN3 += Double.valueOf(returnMap.get("CN3").toString());
			 CN4 += Double.valueOf(returnMap.get("CN4").toString());
			 CN5 += Double.valueOf(returnMap.get("CN5").toString());
			}
			request.setAttribute("G1", (int)CN1	);
			request.setAttribute("G2", (int)CN2	);
			request.setAttribute("G3", (int)CN3	);
			request.setAttribute("G4", (int)CN4	);
			request.setAttribute("G5", (int)CN5	);		
			double total = CN1+CN2+CN3+CN4+CN5; 
			request.setAttribute("total", (int)total); 
			dataset.addValue(CN1, "", "卵块");  
			dataset.addValue(CN2, "", "幼虫");
			dataset.addValue(CN3, "", "蛹");
			dataset.addValue(CN4, "", "成虫");
			dataset.addValue(CN5, "", "疑似");		
			dataset.addValue(total, "", "总计");	
			if (list!=null&&list.size()>0) {
				weith = list.size()*TABLE_WEITH>weith?list.size()*TABLE_WEITH:weith;
			} 
			filename = ChartUtil.createBarChartChart("舞毒蛾类型统计图", "舞毒蛾类型数量统计", "数量(个)", session, dataset, 500, 300);
		}catch (Exception e) {
			e.printStackTrace(); 
		}		
		String graphURL = request.getContextPath() + "/DisplayChart?filename=" + filename; 
		return graphURL;
	}
	public void getWormExcel()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String[] excel_title_hidden = request.getParameterValues("excel_title_hidden"); 
		String[] excel_list_hidden = request.getParameterValues("excel_list_hidden");
		String[] excel_totle_hidden = request.getParameterValues("excel_totle_hidden");
		try {
			List list = new ArrayList();
			if (excel_title_hidden!=null&&excel_title_hidden.length>0) {
				List titlelist = new ArrayList();
				int num = excel_title_hidden.length;
				for (int i = 0; i < excel_title_hidden.length; i++)	//第一行
					titlelist.add(excel_title_hidden[i]);
				list.add(titlelist);
				
				int colnum = excel_list_hidden.length/excel_title_hidden.length;
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
		
			String sheetname = "虫态类型和数量统计";
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
	
	
}
