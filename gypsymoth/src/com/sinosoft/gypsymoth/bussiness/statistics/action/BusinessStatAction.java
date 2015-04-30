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
import com.sinosoft.gypsymoth.bussiness.business.service.SelectService;
import com.sinosoft.gypsymoth.bussiness.statistics.service.BusinessStatService;
import com.sinosoft.gypsymoth.bussiness.statistics.service.ChargesStatService;
import com.sinosoft.gypsymoth.exception.AppException;
import com.sinosoft.gypsymoth.utils.ChartUtil;
import com.sinosoft.gypsymoth.utils.ExcelUtil;

/**
 * 业务量统计
 * @author lixin
 *
 */
public class BusinessStatAction extends ActionSupport{
	
	private static final Logger _logger = Logger
	.getLogger(BusinessStatAction.class);
	
	private static int TABLE_WIDTH = 120;
	
	private ChargesStatService chargesStatService;
	
	private SelectService selectService;
	
	private BusinessStatService businessStatService;
	
	private String parentId;

	private String orgId;
	
	private String portId;
	
	private String selectOrg;
	
	private String yearBegin;
	
	private String yearEnd;
	
	private String monthBegin;
	
	private String monthEnd;
	
	private String quarterBegin;
	
	private String quarterEnd;
	
	private String cycleTime;
	
	
	
	public String getCycleTime() {
		return cycleTime;
	}



	public void setCycleTime(String cycleTime) {
		this.cycleTime = cycleTime;
	}



	public String getYearBegin() {
		return yearBegin;
	}



	public void setYearBegin(String yearBegin) {
		this.yearBegin = yearBegin;
	}



	public String getYearEnd() {
		return yearEnd;
	}



	public void setYearEnd(String yearEnd) {
		this.yearEnd = yearEnd;
	}



	public String getMonthBegin() {
		return monthBegin;
	}



	public void setMonthBegin(String monthBegin) {
		this.monthBegin = monthBegin;
	}



	public String getMonthEnd() {
		return monthEnd;
	}



	public void setMonthEnd(String monthEnd) {
		this.monthEnd = monthEnd;
	}



	public String getQuarterBegin() {
		return quarterBegin;
	}



	public void setQuarterBegin(String quarterBegin) {
		this.quarterBegin = quarterBegin;
	}



	public String getQuarterEnd() {
		return quarterEnd;
	}



	public void setQuarterEnd(String quarterEnd) {
		this.quarterEnd = quarterEnd;
	}



	public BusinessStatService getBusinessStatService() {
		return businessStatService;
	}



	public void setBusinessStatService(BusinessStatService businessStatService) {
		this.businessStatService = businessStatService;
	}



	public String getPortId() {
		return portId;
	}



	public void setPortId(String portId) {
		this.portId = portId;
	}



	public SelectService getSelectService() {
		return selectService;
	}



	public void setSelectService(SelectService selectService) {
		this.selectService = selectService;
	}



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



	public ChargesStatService getChargesStatService() {
		return chargesStatService;
	}



	public void setChargesStatService(ChargesStatService chargesStatService) {
		this.chargesStatService = chargesStatService;
	}

	/**
	 * 初始化数据
	 * @return
	 * @throws AppException
	 */
	public String getInitData() throws AppException {
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
			orgList = chargesStatService.getOrgList(selectMap);
			String tmpStr = "";
			if(orgList.size()>0){
				for(int i=0;i<orgList.size();i++){
					Map tmpMap = (Map)orgList.get(i);
					tmpStr += (String)tmpMap.get("ID")+",";
				}
				tmpStr = tmpStr+"-1";
				Map addMap = new HashMap();
				addMap.put("ID", tmpStr);
				addMap.put("ORG_NAME", "-全部-");
				orgList.add(addMap);
			}
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
			orgList = chargesStatService.getOrgList(selectMap);
			String tmpStr = "";
			if(orgList.size()>0){
				if(parentId!=null && !"".equals(parentId)){
					for(int i=0;i<orgList.size();i++){
						Map tmpMap = (Map)orgList.get(i);
						tmpStr += (String)tmpMap.get("ID")+",";
					}
					tmpStr = tmpStr+parentId;
				}
				Map addMap = new HashMap();
				addMap.put("ID", tmpStr);
				addMap.put("ORG_NAME", "-全部-");
				orgList.add(addMap);
			}
			else{
				if(parentId!=null && !"".equals(parentId) && !"-1".equals(parentId)){
					Map addMap = new HashMap();
					addMap.put("ID", parentId);
					addMap.put("ORG_NAME", "-上级公司-");
					orgList.add(addMap);
				}
			}
			
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
			portList = chargesStatService.getPortList(selectMap);
		} catch (Exception e) {
			throw new AppException("获取港口列表", e);
		}
		request.setAttribute("portList", portList);
		request.setAttribute("type", 3);
		return SUCCESS;
	}
	
	
	/**
	 * 按业务量统计
	 * @return
	 * @throws AppException
	 */
	public String getBusinessStat() throws AppException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		parentId = request.getParameter("parentId2");
		orgId = request.getParameter("orgId2");
		portId = request.getParameter("portId2");
		if(orgId!=null){
			orgId=orgId.replaceAll(","+parentId, "");
		}
		String selectOrgid = "";//机构
		String selectPortid = "";//港口
		String selectAll = "";//全国
		Map selectMap = new HashMap();
		List returnList = new ArrayList();
		List returnAllNum = new ArrayList();
		/*************************按机构，一级公司******************************/
		if("1".equals(selectOrg)){//按机构，一级公司
			if("".equals(parentId)){//选择了全部一级公司
				try {
					List<Map> orgIdList = selectService.getOrgIdList("1");
					String tmpStr = "";
					for(int i=0;i<orgIdList.size();i++){
						Map tmpMap = orgIdList.get(i);
						tmpStr += (String)tmpMap.get("ID")+",";
					}
					selectOrgid = tmpStr+"-1";
				} catch (Exception e) {
					throw new AppException("按业务量统计-获取全部一级公司", e);
				}
			}
			else{//选择了某个一级公司
				selectOrgid = parentId;
			}
		}
		/*************************按机构，二级公司******************************/
		else if("2".equals(selectOrg)){//按机构，二级公司
			if("".equals(parentId) && "".equals(orgId)){//全部一级公司下的全部二级公司
				try {
					List<Map> orgIdList = chargesStatService.getOrgListByLevel(2);
					String tmpStr = "";
					for(int i=0;i<orgIdList.size();i++){
						Map tmpMap = orgIdList.get(i);
						tmpStr += (String)tmpMap.get("ID")+",";
					}
					selectOrgid = tmpStr+"-1";
				} catch (Exception e) {
					throw new AppException("按业务量统计-获取全部一级公司下的全部二级公司", e);
				}
			}
			else if(!"".equals(parentId) && "".equals(orgId)){//某个一级公司下的全部二级公司
				try {
					List<Map> orgIdList = selectService.getOrgIdList(parentId);
					String tmpStr = "";
					for(int i=0;i<orgIdList.size();i++){
						Map tmpMap = orgIdList.get(i);
						tmpStr += (String)tmpMap.get("ID")+",";
					}
					selectOrgid = tmpStr+"-1";
				} catch (Exception e) {
					throw new AppException("按业务量统计-获取某个一级公司下的全部二级公司", e);
				}
			}
			else if(!"".equals(orgId)){//某个具体二级公司
				selectOrgid = orgId;
			}
			
		}
		/*************************按港口******************************/
		else if("3".equals(selectOrg)){//按港口
			if("".equals(parentId) && "".equals(orgId) && "".equals(portId)){//全部港口
				selectPortid="";
			}
			else if(!"".equals(parentId) && "".equals(orgId) && "".equals(portId)){//某个一级公司下的全部港口
				try {
					List<Map> orgIdList = selectService.getOrgIdList(parentId);
					String tmpStr = "";
					for(int i=0;i<orgIdList.size();i++){
						Map tmpMap = orgIdList.get(i);
						tmpStr += (String)tmpMap.get("ID")+",";
					}
					String orgIds = tmpStr+parentId;
					List<Map> portIdList = chargesStatService.getPortIdListByOrgId(orgIds);
					tmpStr = "";
					for(int i=0;i<portIdList.size();i++){
						Map tmpMap = portIdList.get(i);
						tmpStr += (String)tmpMap.get("PORT_ID")+",";
					}
					selectPortid = tmpStr+"-1";;
				} catch (Exception e) {
					throw new AppException("按业务量统计-获取某个一级公司下的全部港口", e);
				}
				
			}
			else if(!"".equals(orgId) && "".equals(portId)){//某个二级公司下的全部港口
				try {
					List<Map> portIdList = chargesStatService.getPortIdListByOrgId(orgId);
					String tmpStr = "";
					for(int i=0;i<portIdList.size();i++){
						Map tmpMap = portIdList.get(i);
						tmpStr += (String)tmpMap.get("PORT_ID")+",";
					}
					selectPortid = tmpStr+"-1";;
				} catch (Exception e) {
					throw new AppException("按业务量统计-获取某个二级公司下的全部港口", e);
				}
			}
			else if(!"".equals(portId)){//某个具体港口
				selectPortid = portId;
			}
		}
		/*************************全国******************************/
		else if("0".equals(selectOrg)){
			selectAll = "all";
		}
		selectMap.put("selectAll", selectAll);
		selectMap.put("selectOrgid", selectOrgid);
		selectMap.put("selectPortid", selectPortid);
		selectMap.put("yearBegin", yearBegin);
		selectMap.put("yearEnd", yearEnd);
		selectMap.put("quarterBegin", quarterBegin);
		selectMap.put("quarterEnd", quarterEnd);
		selectMap.put("monthBegin", monthBegin);
		selectMap.put("monthEnd", monthEnd);
		selectMap.put("cycleTime", cycleTime);
		try {
			returnList = businessStatService.getBusinessStat(selectMap);
			returnAllNum = businessStatService.getBusinessStatAllNum(selectMap);
		} catch (Exception e) {
			throw new AppException("按业务量统计", e);
		}
		
		/*******************************************/
		int i1=0;
		int i2=0;
		int j1=0;
		int j2=0;
		String statType = "";
		//按年统计
		if("1".equals(cycleTime)){
			i1=Integer.parseInt(yearBegin);
			i2=Integer.parseInt(yearEnd);
			statType="y";
		}
		//按季统计
		else if("2".equals(cycleTime)){
			i1=Integer.parseInt(yearBegin);
			i2=Integer.parseInt(yearEnd);
			j1=Integer.parseInt(quarterBegin);
			j2=Integer.parseInt(quarterEnd);
			statType="q";
		}
		//按月统计
		else if("3".equals(cycleTime)){
			i1=Integer.parseInt(yearBegin);
			i2=Integer.parseInt(yearEnd);
			j1=Integer.parseInt(monthBegin);
			j2=Integer.parseInt(monthEnd);
			statType="m";
		}
		
		//统计图1
		int width = 500;
		int hight = 300;
		DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
		for(int i=0;i<returnList.size();i++){
			Map returnMap = (Map)returnList.get(i);
			String columnKey = returnMap.get("ORG_NAME").toString();
			dataset.addValue(Double.parseDouble(returnMap.get("ALL_NUM").toString()), "", columnKey);
		}
		if (returnList != null && returnList.size() > 0) {
			width = returnList.size() * TABLE_WIDTH > width ? returnList.size() * TABLE_WIDTH : width;
		} 
		String filename = ChartUtil.createBarChartChart("业务量统计图（按机构）", "机构名称", "业务量", session, dataset, width, hight);
		
		//统计图2
		width = 500;
		hight = 300;
		int allNum = 0;
		List numList = new ArrayList();
		if ("y".equals(statType)) {// 按年统计
			for (int i = i1; i <= i2; i++) {
				for (int j = j1; j <= j2; j++) {
					allNum++;
					numList.add(i + "年");
				}
			}
		} else if ("q".equals(statType)) {// 按季统计
			for (int i = i1; i <= i2; i++) {
				if (i == i2 && i == i1) {// 在同一年度
					for (int j = j1; j <= j2; j++) {
						allNum++;
						numList.add(i + "年" + j + "季度");
					}
				} else if (i != i2 && i == i1) {// 跨年度，第一年度
					for (int j = j1; j <= 4; j++) {
						allNum++;
						numList.add(i + "年" + j + "季度");
					}
				} else if (i != i2 && i != i1) {// 跨年度，中间年度
					for (int j = 1; j <= 4; j++) {
						allNum++;
						numList.add(i + "年" + j + "季度");
					}
				} else {// 跨年度，最后年度
					for (int j = 1; j <= j2; j++) {
						allNum++;
						numList.add(i + "年" + j + "季度");
					}
				}
			}
		} else {// 按月统计
			for (int i = i1; i <= i2; i++) {
				if (i == i2 && i == i1) {// 在同一年度
					for (int j = j1; j <= j2; j++) {
						allNum++;
						numList.add(i + "年" + j + "月");
					}
				} else if (i != i2 && i == i1) {// 跨年度，第一年度
					for (int j = j1; j <= 12; j++) {
						allNum++;
						numList.add(i + "年" + j + "月");
					}
				} else if (i != i2 && i != i1) {// 跨年度，中间年度
					for (int j = 1; j <= 12; j++) {
						allNum++;
						numList.add(i + "年" + j + "月");
					}
				} else {// 跨年度，最后年度
					for (int j = 1; j <= j2; j++) {
						allNum++;
						numList.add(i + "年" + j + "月");
					}
				}
			}
		}
		
		DefaultCategoryDataset dataset2 = new DefaultCategoryDataset(); 
		if(returnAllNum.size()>0){
			Map returnMap = (Map)returnAllNum.get(0);
			for(int i=0;i<numList.size();i++){
				String columnKey = numList.get(i).toString();
				String tmpName = "A"+i;
				if(returnMap.get(tmpName)==null){
					dataset2.addValue(Double.parseDouble("0"), "", columnKey);
				}else{
					dataset2.addValue(Double.parseDouble(returnMap.get(tmpName).toString()), "", columnKey);
				}
				
			}
		}
		if (numList != null && numList.size() > 0) {
			width = numList.size() * TABLE_WIDTH > width ? numList.size() * TABLE_WIDTH : width;
		}

		String filename2 = ChartUtil.createBarChartChart("业务量统计图（按时间）", "时间段", "业务量", session, dataset2, width, hight);
		request.setAttribute("filename", filename);
		request.setAttribute("filename2", filename2);
		request.setAttribute("returnList", returnList);
		request.setAttribute("returnAllNum", returnAllNum);
		request.setAttribute("numList", numList);
		return SUCCESS;
	}
	
	
	/**
	 * 按合格率统计
	 * @return
	 * @throws AppException
	 */
	public String getQualifiedStat() throws AppException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		parentId = request.getParameter("parentId2");
		orgId = request.getParameter("orgId2");
		portId = request.getParameter("portId2");
		String selectOrgid = "";//机构
		String selectPortid = "";//港口
		String selectAll = "";//全国
		Map selectMap = new HashMap();
		List returnList = new ArrayList();
		List returnAllNum = new ArrayList();
		/*************************按机构，一级公司******************************/
		if("1".equals(selectOrg)){//按机构，一级公司
			if("".equals(parentId)){//选择了全部一级公司
				try {
					List<Map> orgIdList = selectService.getOrgIdList("1");
					String tmpStr = "";
					for(int i=0;i<orgIdList.size();i++){
						Map tmpMap = orgIdList.get(i);
						tmpStr += (String)tmpMap.get("ID")+",";
					}
					selectOrgid = tmpStr+"-1";
				} catch (Exception e) {
					throw new AppException("按合格率统计-获取全部一级公司", e);
				}
			}
			else{//选择了某个一级公司
				selectOrgid = parentId;
			}
		}
		/*************************按机构，二级公司******************************/
		else if("2".equals(selectOrg)){//按机构，二级公司
			if("".equals(parentId) && "".equals(orgId)){//全部一级公司下的全部二级公司
				try {
					List<Map> orgIdList = chargesStatService.getOrgListByLevel(2);
					String tmpStr = "";
					for(int i=0;i<orgIdList.size();i++){
						Map tmpMap = orgIdList.get(i);
						tmpStr += (String)tmpMap.get("ID")+",";
					}
					selectOrgid = tmpStr+"-1";
				} catch (Exception e) {
					throw new AppException("按合格率统计-获取全部一级公司下的全部二级公司", e);
				}
			}
			else if(!"".equals(parentId) && "".equals(orgId)){//某个一级公司下的全部二级公司
				try {
					List<Map> orgIdList = selectService.getOrgIdList(parentId);
					String tmpStr = "";
					for(int i=0;i<orgIdList.size();i++){
						Map tmpMap = orgIdList.get(i);
						tmpStr += (String)tmpMap.get("ID")+",";
					}
					selectOrgid = tmpStr+"-1";
				} catch (Exception e) {
					throw new AppException("按合格率统计-获取某个一级公司下的全部二级公司", e);
				}
			}
			else if(!"".equals(orgId)){//某个具体二级公司
				selectOrgid = orgId;
			}
			
		}
		/*************************按港口******************************/
		else if("3".equals(selectOrg)){//按港口
			if("".equals(parentId) && "".equals(orgId) && "".equals(portId)){//全部港口
				selectPortid="";
			}
			else if(!"".equals(parentId) && "".equals(orgId) && "".equals(portId)){//某个一级公司下的全部港口
				try {
					List<Map> orgIdList = selectService.getOrgIdList(parentId);
					String tmpStr = "";
					for(int i=0;i<orgIdList.size();i++){
						Map tmpMap = orgIdList.get(i);
						tmpStr += (String)tmpMap.get("ID")+",";
					}
					String orgIds = tmpStr+parentId;
					List<Map> portIdList = chargesStatService.getPortIdListByOrgId(orgIds);
					tmpStr = "";
					for(int i=0;i<portIdList.size();i++){
						Map tmpMap = portIdList.get(i);
						tmpStr += (String)tmpMap.get("PORT_ID")+",";
					}
					selectPortid = tmpStr+"-1";;
				} catch (Exception e) {
					throw new AppException("按合格率统计-获取某个一级公司下的全部港口", e);
				}
				
			}
			else if(!"".equals(orgId) && "".equals(portId)){//某个二级公司下的全部港口
				try {
					List<Map> portIdList = chargesStatService.getPortIdListByOrgId(orgId);
					String tmpStr = "";
					for(int i=0;i<portIdList.size();i++){
						Map tmpMap = portIdList.get(i);
						tmpStr += (String)tmpMap.get("PORT_ID")+",";
					}
					selectPortid = tmpStr+"-1";;
				} catch (Exception e) {
					throw new AppException("按合格率统计-获取某个二级公司下的全部港口", e);
				}
			}
			else if(!"".equals(portId)){//某个具体港口
				selectPortid = portId;
			}
		}
		/*************************全国******************************/
		else if("0".equals(selectOrg)){
			selectAll = "all";
		}
		
		selectMap.put("selectAll", selectAll);
		selectMap.put("selectOrgid", selectOrgid);
		selectMap.put("selectPortid", selectPortid);
		selectMap.put("yearBegin", yearBegin);
		selectMap.put("yearEnd", yearEnd);
		selectMap.put("quarterBegin", quarterBegin);
		selectMap.put("quarterEnd", quarterEnd);
		selectMap.put("monthBegin", monthBegin);
		selectMap.put("monthEnd", monthEnd);
		selectMap.put("cycleTime", cycleTime);
		try {
			returnList = businessStatService.getQualifiedStat(selectMap);
			returnAllNum = businessStatService.getQualifiedStatAllNum(selectMap);
		} catch (Exception e) {
			throw new AppException("按合格率统计", e);
		}
		
		/*******************************************/
		int i1=0;
		int i2=0;
		int j1=0;
		int j2=0;
		String statType = "";
		//按年统计
		if("1".equals(cycleTime)){
			i1=Integer.parseInt(yearBegin);
			i2=Integer.parseInt(yearEnd);
			statType="y";
		}
		//按季统计
		else if("2".equals(cycleTime)){
			i1=Integer.parseInt(yearBegin);
			i2=Integer.parseInt(yearEnd);
			j1=Integer.parseInt(quarterBegin);
			j2=Integer.parseInt(quarterEnd);
			statType="q";
		}
		//按月统计
		else if("3".equals(cycleTime)){
			i1=Integer.parseInt(yearBegin);
			i2=Integer.parseInt(yearEnd);
			j1=Integer.parseInt(monthBegin);
			j2=Integer.parseInt(monthEnd);
			statType="m";
		}
		
		//统计图
		int width = 500;
		int hight = 300;
		DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
		for(int i=0;i<returnList.size();i++){
			Map returnMap = (Map)returnList.get(i);
			String columnKey = returnMap.get("ORG_NAME").toString();
			dataset.addValue(Double.parseDouble(returnMap.get("ALL_NUM").toString()), "", columnKey);
		}
		if (returnList != null && returnList.size() > 0) {
			width = returnList.size() * TABLE_WIDTH > width ? returnList.size() * TABLE_WIDTH : width;
		}
		String filename = ChartUtil.createBarChartChart("合格率统计图（按机构）", "机构名称", "合格率", session, dataset, width, hight);
		
		//统计图2
		width = 500;
		hight = 300;
		int allNum = 0;
		List numList = new ArrayList();
		if ("y".equals(statType)) {// 按年统计
			for (int i = i1; i <= i2; i++) {
				for (int j = j1; j <= j2; j++) {
					allNum++;
					numList.add(i + "年");
				}
			}
		} else if ("q".equals(statType)) {// 按季统计
			for (int i = i1; i <= i2; i++) {
				if (i == i2 && i == i1) {// 在同一年度
					for (int j = j1; j <= j2; j++) {
						allNum++;
						numList.add(i + "年" + j + "季度");
					}
				} else if (i != i2 && i == i1) {// 跨年度，第一年度
					for (int j = j1; j <= 4; j++) {
						allNum++;
						numList.add(i + "年" + j + "季度");
					}
				} else if (i != i2 && i != i1) {// 跨年度，中间年度
					for (int j = 1; j <= 4; j++) {
						allNum++;
						numList.add(i + "年" + j + "季度");
					}
				} else {// 跨年度，最后年度
					for (int j = 1; j <= j2; j++) {
						allNum++;
						numList.add(i + "年" + j + "季度");
					}
				}
			}
		} else {// 按月统计
			for (int i = i1; i <= i2; i++) {
				if (i == i2 && i == i1) {// 在同一年度
					for (int j = j1; j <= j2; j++) {
						allNum++;
						numList.add(i + "年" + j + "月");
					}
				} else if (i != i2 && i == i1) {// 跨年度，第一年度
					for (int j = j1; j <= 12; j++) {
						allNum++;
						numList.add(i + "年" + j + "月");
					}
				} else if (i != i2 && i != i1) {// 跨年度，中间年度
					for (int j = 1; j <= 12; j++) {
						allNum++;
						numList.add(i + "年" + j + "月");
					}
				} else {// 跨年度，最后年度
					for (int j = 1; j <= j2; j++) {
						allNum++;
						numList.add(i + "年" + j + "月");
					}
				}
			}
		}
		
		DefaultCategoryDataset dataset2 = new DefaultCategoryDataset(); 
		if(returnAllNum.size()>0){
			Map returnMap = (Map)returnAllNum.get(0);
			for(int i=0;i<numList.size();i++){
				String columnKey = numList.get(i).toString();
				String tmpName = "A"+i;
				if(returnMap.get(tmpName)==null){
					dataset2.addValue(Double.parseDouble("0"), "", columnKey);
				}else{
					dataset2.addValue(Double.parseDouble(returnMap.get(tmpName).toString()), "", columnKey);
				}
			}
		}
		if (numList != null && numList.size() > 0) {
			width = numList.size() * TABLE_WIDTH > width ? numList.size() * TABLE_WIDTH : width;
		}
		String filename2 = ChartUtil.createBarChartChart("合格率统计图（按时间）", "时间段", "合格率", session, dataset2, width, hight);
		
		request.setAttribute("filename", filename);
		request.setAttribute("filename2", filename2);
		request.setAttribute("returnList", returnList);
		request.setAttribute("returnAllNum", returnAllNum);
		request.setAttribute("numList", numList);
		return SUCCESS;
	}
	
	public void getExcelByBusinessStat() {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();

		String[] hidden_top = request.getParameterValues("hidden_top");
		String[] hidden_body = request.getParameterValues("hidden_body");
		String[] hidden_bottom = request.getParameterValues("hidden_bottom");
		try {
			List list = new ArrayList();

			List contentlist = new ArrayList();
			for (int i = 0; i < hidden_top.length; i++) {
				contentlist.add(hidden_top[i]);
			}
			list.add(contentlist);

			List contentlist2 = new ArrayList();
			for (int i = 0; i < hidden_body.length; i++) {

				if (i % hidden_top.length == 0) {
					contentlist2 = new ArrayList();
				}
				contentlist2.add(hidden_body[i]);
				if ((i + 1) % hidden_top.length == 0) {
					list.add(contentlist2);
				}
			}

			List contentlist3 = new ArrayList();
			for (int i = 0; i < hidden_bottom.length; i++) {
				contentlist3.add(hidden_bottom[i]);
			}
			list.add(contentlist3);

			String sheetname = "业务量统计";
			String formatFileName = URLEncoder.encode(sheetname, "UTF-8");

			HSSFWorkbook workbook = ExcelUtil.toExcel(sheetname, list);

			response.reset();
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition", "attachment;filename="
					+ formatFileName + ".xls");
			workbook.write(response.getOutputStream());

			response.getOutputStream().close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
