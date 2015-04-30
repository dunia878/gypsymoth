package com.sinosoft.gypsymoth.bussiness.statistics.action;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.sinosoft.gypsymoth.bussiness.business.service.SelectService;
import com.sinosoft.gypsymoth.bussiness.statistics.service.ChargesStatService;
import com.sinosoft.gypsymoth.exception.AppException;
import com.sinosoft.gypsymoth.utils.ExcelUtil;

/**
 * 财务统计
 * @author lixin
 *
 */
public class ChargesStatAction extends ActionSupport{
	
	private static final Logger _logger = Logger
	.getLogger(ChargesStatAction.class);
	
	private ChargesStatService chargesStatService;
	
	private SelectService selectService;
	
	private String parentId;
	
	private String isChild;
	
	private String orgId;
	
	private String portId;
	
	private String selectOrg;
	
	private String selectBeginAppdate;
	
	private String selectEndAppdate;
	
	private String hidden_type;
	
	
	
	public String getHidden_type() {
		return hidden_type;
	}



	public void setHidden_type(String hidden_type) {
		this.hidden_type = hidden_type;
	}



	public String getPortId() {
		return portId;
	}



	public void setPortId(String portId) {
		this.portId = portId;
	}



	public String getSelectBeginAppdate() {
		return selectBeginAppdate;
	}



	public void setSelectBeginAppdate(String selectBeginAppdate) {
		this.selectBeginAppdate = selectBeginAppdate;
	}



	public String getSelectEndAppdate() {
		return selectEndAppdate;
	}



	public void setSelectEndAppdate(String selectEndAppdate) {
		this.selectEndAppdate = selectEndAppdate;
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



	public String getIsChild() {
		return isChild;
	}



	public void setIsChild(String isChild) {
		this.isChild = isChild;
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
		for(int i=0;i<=5;i++){
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
	 * 综合统计
	 * @return
	 * @throws AppException
	 */
	public String chargesAllStat() throws AppException {
		HttpServletRequest request = ServletActionContext.getRequest();
		parentId = request.getParameter("parentId2");
		orgId = request.getParameter("orgId2");
		portId = request.getParameter("portId2");
		String selectOrgid = "";
		Map selectMap = new HashMap();
		List jcList = new ArrayList();
		List ffList = new ArrayList();
		List fzList = new ArrayList();
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
					throw new AppException("综合统计-获取全部一级公司", e);
				}
			}
			else{//选择了某个一级公司
				selectOrgid = parentId;
			}
			
			selectMap.put("selectOrgid", selectOrgid);
			selectMap.put("selectBeginAppdate", selectBeginAppdate);
			selectMap.put("selectEndAppdate", selectEndAppdate);
			
			/*************************** add star    *********************/
			/**################################################################*/
			//迭代selectMap
			Iterator iter = selectMap.entrySet().iterator();
			while(iter.hasNext()){
				Map.Entry entity = (Map.Entry)iter.next();
				System.out.println(" key==============" + entity.getKey() + " value ==================" + entity.getValue());
			}
			/*************************** add star    *********************/
			
			try {
				jcList = chargesStatService.getAllStatByOrg(selectMap, 1);
			} catch (Exception e) {
				throw new AppException("获取检查状态表(一级)", e);
			}
			try {
				ffList = chargesStatService.getAllStatByOrg(selectMap, 2);
			} catch (Exception e) {
				throw new AppException("获取付费状态表(一级)", e);
			}
			try {
				fzList = chargesStatService.getAllStatByOrg(selectMap, 3);
			} catch (Exception e) {
				throw new AppException("获取分帐状态表(一级)", e);
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
					throw new AppException("综合统计-获取全部一级公司下的全部二级公司", e);
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
					throw new AppException("综合统计-获取某个一级公司下的全部二级公司", e);
				}
			}
			else if(!"".equals(orgId)){//某个具体二级公司
				selectOrgid = orgId;
			}
			
			selectMap.put("selectOrgid", selectOrgid);
			selectMap.put("selectBeginAppdate", selectBeginAppdate);
			selectMap.put("selectEndAppdate", selectEndAppdate);
			try {
				jcList = chargesStatService.getAllStatByOrg(selectMap, 1);
			} catch (Exception e) {
				throw new AppException("获取检查状态表(二级)", e);
			}
			try {
				ffList = chargesStatService.getAllStatByOrg(selectMap, 2);
			} catch (Exception e) {
				throw new AppException("获取付费状态表(二级)", e);
			}
			try {
				fzList = chargesStatService.getAllStatByOrg(selectMap, 3);
			} catch (Exception e) {
				throw new AppException("获取分帐状态表(二级)", e);
			}
		}
		/*************************按港口******************************/
		else if("3".equals(selectOrg)){//按港口
			if("".equals(parentId) && "".equals(orgId) && "".equals(portId)){//全部港口
				selectOrgid="";
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
					selectOrgid = tmpStr+"-1";;
				} catch (Exception e) {
					throw new AppException("综合统计-获取某个一级公司下的全部港口", e);
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
					selectOrgid = tmpStr+"-1";;
				} catch (Exception e) {
					throw new AppException("综合统计-获取某个二级公司下的全部港口", e);
				}
			}
			else if(!"".equals(portId)){//某个具体港口
				selectOrgid = portId;
			}
			
			selectMap.put("selectOrgid", selectOrgid);
			selectMap.put("selectBeginAppdate", selectBeginAppdate);
			selectMap.put("selectEndAppdate", selectEndAppdate);
			try {
				jcList = chargesStatService.getAllStatByPort(selectMap, 1);
			} catch (Exception e) {
				throw new AppException("获取检查状态表(港口)", e);
			}
			try {
				ffList = chargesStatService.getAllStatByPort(selectMap, 2);
			} catch (Exception e) {
				throw new AppException("获取付费状态表(港口)", e);
			}
			try {
				fzList = chargesStatService.getAllStatByPort(selectMap, 3);
			} catch (Exception e) {
				throw new AppException("获取分帐状态表(港口)", e);
			}
		}
		/*************************全国******************************/
		else if("0".equals(selectOrg)){
			
			selectMap.put("selectBeginAppdate", selectBeginAppdate);
			selectMap.put("selectEndAppdate", selectEndAppdate);
			try {
				jcList = chargesStatService.getAllStat(selectMap, 1);
			} catch (Exception e) {
				throw new AppException("获取检查状态表(港口)", e);
			}
			try {
				ffList = chargesStatService.getAllStat(selectMap, 2);
			} catch (Exception e) {
				throw new AppException("获取付费状态表(港口)", e);
			}
			try {
				fzList = chargesStatService.getAllStat(selectMap, 3);
			} catch (Exception e) {
				throw new AppException("获取分帐状态表(港口)", e);
			}
			
		}
		
		request.setAttribute("jcList", jcList);
		request.setAttribute("ffList", ffList);
		request.setAttribute("fzList", fzList);
		return SUCCESS;
	}
	
	/**
	 * 按机构名称分账明细统计
	 * @return
	 * @throws AppException
	 */
	public String chargesLedgerStatByOrg() throws AppException {
		HttpServletRequest request = ServletActionContext.getRequest();
		parentId = request.getParameter("parentId2");
		orgId = request.getParameter("orgId2");
		portId = request.getParameter("portId2");
		String selectOrgid = "";
		String selectPortid = "";
		Map selectMap = new HashMap();
		List returnList = new ArrayList();
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
					throw new AppException("按机构名称分账明细统计-获取全部一级公司", e);
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
					throw new AppException("按机构名称分账明细统计-获取全部一级公司下的全部二级公司", e);
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
					throw new AppException("按机构名称分账明细统计-获取某个一级公司下的全部二级公司", e);
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
					throw new AppException("综合统计-获取某个一级公司下的全部港口", e);
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
					throw new AppException("综合统计-获取某个二级公司下的全部港口", e);
				}
			}
			else if(!"".equals(portId)){//某个具体港口
				selectPortid = portId;
			}
		}
		/*************************全国******************************/
		else if("0".equals(selectOrg)){
			
		}
		
		selectMap.put("selectOrgid", selectOrgid);
		selectMap.put("selectPortid", selectPortid);
		selectMap.put("selectBeginAppdate", selectBeginAppdate);
		selectMap.put("selectEndAppdate", selectEndAppdate);
		try {
			returnList = chargesStatService.getLedgerStatByOrg(selectMap);
		} catch (Exception e) {
			throw new AppException("获取检查状态表(一级)", e);
		}
		
		request.setAttribute("returnList", returnList);
		return SUCCESS;
	}
	
	/**
	 * 按业务单号分账明细统计
	 * @return
	 * @throws AppException
	 */
	public String chargesLedgerStatByApp() throws AppException {
		HttpServletRequest request = ServletActionContext.getRequest();
		parentId = request.getParameter("parentId2");
		orgId = request.getParameter("orgId2");
		portId = request.getParameter("portId2");
		String selectOrgid = "";
		String selectPortid = "";
		Map selectMap = new HashMap();
		List returnList = new ArrayList();
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
					throw new AppException("按机构名称分账明细统计-获取全部一级公司", e);
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
					throw new AppException("按机构名称分账明细统计-获取全部一级公司下的全部二级公司", e);
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
					throw new AppException("按机构名称分账明细统计-获取某个一级公司下的全部二级公司", e);
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
					throw new AppException("综合统计-获取某个一级公司下的全部港口", e);
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
					throw new AppException("综合统计-获取某个二级公司下的全部港口", e);
				}
			}
			else if(!"".equals(portId)){//某个具体港口
				selectPortid = portId;
			}
		}
		/*************************全国******************************/
		else if("0".equals(selectOrg)){
			
		}
		
		selectMap.put("selectOrgid", selectOrgid);
		selectMap.put("selectPortid", selectPortid);
		selectMap.put("selectBeginAppdate", selectBeginAppdate);
		selectMap.put("selectEndAppdate", selectEndAppdate);
		try {
			returnList = chargesStatService.getLedgerStatByApp(selectMap);
		} catch (Exception e) {
			throw new AppException("获取检查状态表(一级)", e);
		}
		
		request.setAttribute("returnList", returnList);
		return SUCCESS;
	}
	
	
	public void getExcelByChargesStat() {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();

		String[] hidden_body = request.getParameterValues("hidden_body");
		try {
			List list = new ArrayList();

			List contentlist = new ArrayList();
			contentlist.add("机构/港口");
			if("jc".equals(hidden_type)){
				contentlist.add("未检查");
			}
			else if("ff".equals(hidden_type)){
				contentlist.add("未付费");
			}
			else if("fz".equals(hidden_type)){
				contentlist.add("未分帐");
			}
			contentlist.add("");
			if("jc".equals(hidden_type)){
				contentlist.add("已检查");
			}
			else if("ff".equals(hidden_type)){
				contentlist.add("已付费");
			}
			else if("fz".equals(hidden_type)){
				contentlist.add("已分帐");
			}
			contentlist.add("");
			contentlist.add("业务量总计");
			contentlist.add("金额总计");
			list.add(contentlist);
			List contentlist1 = new ArrayList();
			contentlist1.add("");
			contentlist1.add("业务量");
			contentlist1.add("金额");
			contentlist1.add("业务量");
			contentlist1.add("金 额");
			contentlist1.add("");
			contentlist1.add("");
			list.add(contentlist1);

			List contentlist2 = new ArrayList();
			for (int i = 0; i < hidden_body.length; i++) {

				if (i % 7 == 0) {
					contentlist2 = new ArrayList();
				}
				contentlist2.add(hidden_body[i]);
				if ((i + 1) % 7 == 0) {
					list.add(contentlist2);
				}
			}

			String sheetname = "财务记录统计";
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
	
	
	public void getExcelByLedgerStatApp() {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();

		String[] hidden_body = request.getParameterValues("hidden_body");
		try {
			List list = new ArrayList();

			List contentlist = new ArrayList();
			contentlist.add("业务单号");
			contentlist.add("到账金额");
			contentlist.add("检验公司");
			contentlist.add("");
			contentlist.add("一级公司");
			contentlist.add("");
			contentlist.add("二级公司");
			contentlist.add("");
			contentlist.add("总计");
			list.add(contentlist);
			List contentlist1 = new ArrayList();
			contentlist1.add("");
			contentlist1.add("");
			contentlist1.add("名称");
			contentlist1.add("金额");
			contentlist1.add("名称");
			contentlist1.add("金额");
			contentlist1.add("名称");
			contentlist1.add("金额");
			contentlist1.add("");
			list.add(contentlist1);

			List contentlist2 = new ArrayList();
			for (int i = 0; i < hidden_body.length; i++) {

				if (i % 9 == 0) {
					contentlist2 = new ArrayList();
				}
				contentlist2.add(hidden_body[i]);
				if ((i + 1) % 9 == 0) {
					list.add(contentlist2);
				}
			}

			String sheetname = "财务记录统计";
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
	
	public void getExcelByLedgerStatOrg() {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();

		String[] hidden_body = request.getParameterValues("hidden_body");
		try {
			List list = new ArrayList();

			List contentlist = new ArrayList();
			contentlist.add("机构名称");
			contentlist.add("检验公司");
			contentlist.add("");
			contentlist.add("一级公司");
			contentlist.add("");
			contentlist.add("二级公司");
			contentlist.add("");
			contentlist.add("总计");
			list.add(contentlist);
			List contentlist1 = new ArrayList();
			contentlist1.add("");
			contentlist1.add("名称");
			contentlist1.add("金额");
			contentlist1.add("名称");
			contentlist1.add("金额");
			contentlist1.add("名称");
			contentlist1.add("金额");
			contentlist1.add("");
			list.add(contentlist1);

			List contentlist2 = new ArrayList();
			for (int i = 0; i < hidden_body.length; i++) {

				if (i % 8 == 0) {
					contentlist2 = new ArrayList();
				}
				contentlist2.add(hidden_body[i]);
				if ((i + 1) % 8 == 0) {
					list.add(contentlist2);
				}
			}

			String sheetname = "财务记录统计";
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
