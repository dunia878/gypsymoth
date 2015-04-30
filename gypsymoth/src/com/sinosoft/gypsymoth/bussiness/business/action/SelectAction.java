package com.sinosoft.gypsymoth.bussiness.business.action;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.RequestMap;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;
import com.sinosoft.gypsymoth.bussiness.assignment.service.AssignmentService;
import com.sinosoft.gypsymoth.bussiness.business.service.SelectService;
import com.sinosoft.gypsymoth.bussiness.organization.service.OrganizationService;
import com.sinosoft.gypsymoth.exception.AppException;
import com.sinosoft.gypsymoth.pojo.Account;
import com.sinosoft.gypsymoth.pojo.Business;
import com.sinosoft.gypsymoth.utils.Constants;
import com.sinosoft.gypsymoth.utils.ExcelUtil;
import com.sinosoft.gypsymoth.utils.Pagination;

/**
 * 业务查询
 * @author lixin
 *
 */
public class SelectAction extends ActionSupport {
	
	private final Logger _logger = Logger.getLogger(SelectAction.class);
	private SelectService selectService;
	private OrganizationService organizationService;
	private AssignmentService assignmentService;
	private String actionName;
	private Integer selectBusinessid;//查询条件：业务编号
	private Integer selectBusinesstate;//查询条件：业务状态
	private Integer selectPaystate;//查询条件：缴费状态
	private Integer selectLedgerstate;//查询条件：分账状态
	private String selectVesselname;//查询条件：船名
	private String selectBusinessname;//查询条件：申请人
	private String selectCityname;//查询条件：港口城市
	private String selectOrgid;//查询条件：机构ID
	private String selectOrgname;//查询条件：机构名称
	private String selectAppno;//查询条件：申请编号
	private String selectTempno;//查询条件：临时申请号
	private String selectBeginAppdate;//查询条件：最小申请日期
	private String selectEndAppdate;//查询条件：最大申请日期
	private String selectBeginOperatortime;//查询条件：最小实际检查日期
	private String selectEndOperatortime;//查询条件：最大实际检查日期
	private Integer businessid;
	private Business business;
	private String accountId;
	private String isDelete;
	
	private String account_name; //用户名
	private String businessname; //申请人
	
	
	public String getBusinessname() {
		return businessname;
	}

	public void setBusinessname(String businessname) {
		this.businessname = businessname;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public OrganizationService getOrganizationService() {
		return organizationService;
	}

	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}

	public String getSelectTempno() {
		return selectTempno;
	}

	public void setSelectTempno(String selectTempno) {
		this.selectTempno = selectTempno;
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

	public String getSelectBeginOperatortime() {
		return selectBeginOperatortime;
	}

	public void setSelectBeginOperatortime(String selectBeginOperatortime) {
		this.selectBeginOperatortime = selectBeginOperatortime;
	}

	public String getSelectEndOperatortime() {
		return selectEndOperatortime;
	}

	public void setSelectEndOperatortime(String selectEndOperatortime) {
		this.selectEndOperatortime = selectEndOperatortime;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getSelectAppno() {
		return selectAppno;
	}

	public void setSelectAppno(String selectAppno) {
		this.selectAppno = selectAppno;
	}

	public String getSelectOrgname() {
		return selectOrgname;
	}

	public void setSelectOrgname(String selectOrgname) {
		this.selectOrgname = selectOrgname;
	}

	public String getSelectBusinessname() {
		return selectBusinessname;
	}

	public void setSelectBusinessname(String selectBusinessname) {
		this.selectBusinessname = selectBusinessname;
	}

	public String getSelectCityname() {
		return selectCityname;
	}

	public void setSelectCityname(String selectCityname) {
		this.selectCityname = selectCityname;
	}

	public String getSelectOrgid() {
		return selectOrgid;
	}

	public void setSelectOrgid(String selectOrgid) {
		this.selectOrgid = selectOrgid;
	}

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}

	public Integer getBusinessid() {
		return businessid;
	}

	public void setBusinessid(Integer businessid) {
		this.businessid = businessid;
	}

	public SelectService getSelectService() {
		return selectService;
	}

	public void setSelectService(SelectService selectService) {
		this.selectService = selectService;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public Integer getSelectBusinessid() {
		return selectBusinessid;
	}

	public void setSelectBusinessid(Integer selectBusinessid) {
		this.selectBusinessid = selectBusinessid;
	}

	public Integer getSelectBusinesstate() {
		return selectBusinesstate;
	}

	public void setSelectBusinesstate(Integer selectBusinesstate) {
		this.selectBusinesstate = selectBusinesstate;
	}

	public Integer getSelectPaystate() {
		return selectPaystate;
	}

	public void setSelectPaystate(Integer selectPaystate) {
		this.selectPaystate = selectPaystate;
	}

	public Integer getSelectLedgerstate() {
		return selectLedgerstate;
	}

	public void setSelectLedgerstate(Integer selectLedgerstate) {
		this.selectLedgerstate = selectLedgerstate;
	}

	public String getSelectVesselname() {
		return selectVesselname;
	}

	public void setSelectVesselname(String selectVesselname) {
		this.selectVesselname = selectVesselname;
	}

	/**
	 * 业务查询分页列表
	 * @return
	 * @throws AppException
	 */
	public String getSelectByPage() throws AppException{
		
		/**
		 * 判断是人员还是用户，人员显示全部信息，客户只显示其申请过的信息
		 */
		Map session = ActionContext.getContext().getSession();
		Account account = (Account) session.get(Constants.ACCOUNT_SESSION);
		String businessname = "";
		if(account.getAccountType()!=null && account.getAccountType()==2){
			
			String accountid = account.getAccountId();
			try {
				List list = selectService.getBusinessname(accountid);
				if(list.size()>0){
					Map map = (Map)list.get(0);
					businessname = (String)map.get("CO_EN_NAME");
				}
				else{
					businessname = "0";
				}
			} catch (Exception e) {
				throw new AppException("业务查询获取客户公司名称", e);
			}
		}
		/***************************************************/
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session2 = request.getSession();
		int isshow = account.getAccountType();
		String isShow ="";
		if(isshow == 1) {
			isShow = session.get(Constants.ACCOUNT_ISADMIN).toString();
		}		
		this.setIsDelete((String) session2.getAttribute("isDelete"));
		session2.removeAttribute("isDelete");
		request.setAttribute("isDelete", isDelete);
		request.setAttribute("isShow", isShow);
		
		//分页配置
		Pagination p = new Pagination(0,0,0);
		String currPage=(String)request.getParameter("goPage");
		if(currPage==null || currPage==""){  
			currPage="1";//” currPage”是当前页数
		}
		_logger.info("currPage:"+currPage);
		Integer currPage1 = Integer.parseInt(currPage);
		request.setAttribute("currPage1", currPage1);
		if("".equals(businessname) && (selectOrgid==null || "".equals(selectOrgid))){
			if(!"1".equals(session.get(Constants.ACCOUNT_PERSON_ORG).toString())){
				selectOrgid = session.get(Constants.ACCOUNT_PERSON_ORG).toString();
				accountId = account.getAccountId();
			}
		}
		if(selectOrgid!=null && !"".equals(selectOrgid)){
			/*
			if(selectOrgid.endsWith("00")){
				selectOrgid = selectOrgid.substring(0, 2);
			}
			*/
			try {
				List<Map> orgIdList = selectService.getOrgIdList(selectOrgid);
				String tmpStr = "";
				for(int i=0;i<orgIdList.size();i++){
					Map tmpMap = orgIdList.get(i);
					tmpStr += (String)tmpMap.get("ID")+",";
				}
				selectOrgid = tmpStr+selectOrgid;
			} catch (Exception e) {
				throw new AppException("根据机构Id获取下属子机构的ID", e);
			}
		}
		Map selectMap = new HashMap();
		selectMap.put("selectBusinessid", selectBusinessid);
		selectMap.put("selectBusinesstate", selectBusinesstate);
		selectMap.put("selectPaystate", selectPaystate);
		selectMap.put("selectLedgerstate", selectLedgerstate);
		// update by guodingjun 申请人
		if(selectBusinessname != null && !"".equals(selectBusinessname)){
			selectMap.put("selectBusinessname", selectBusinessname.toUpperCase());
		}
		
		selectMap.put("selectCityname", selectCityname);
		selectMap.put("selectOrgid", selectOrgid);
		selectMap.put("businessname", businessname);
		
		selectMap.put("accountId", accountId);
		
		selectMap.put("selectBeginAppdate", selectBeginAppdate);
		selectMap.put("selectEndAppdate", selectEndAppdate);
		selectMap.put("selectBeginOperatortime", selectBeginOperatortime);
		selectMap.put("selectEndOperatortime", selectEndOperatortime);
		if(selectVesselname != null && !"".equals(selectVesselname)) {
			selectMap.put("selectVesselname", selectVesselname.toUpperCase());
		}else {
			selectMap.put("selectVesselname", selectVesselname);
		}
		if(selectAppno != null && !"".equals(selectAppno)) {
			selectMap.put("selectAppno", selectAppno.toUpperCase());
		}else {
			selectMap.put("selectAppno", selectAppno);
		}
		if(selectTempno != null && !"".equals(selectTempno)) {
			selectMap.put("selectTempno", selectTempno.toUpperCase());
		}else {
			selectMap.put("selectTempno", selectTempno);
		}
		//add by 郭定军 加上申请人查询条件
		if(account_name != null && !"".equals(account_name)){
			selectMap.put("account_name", account_name.toLowerCase());
		}
//		if(businessname != null && !"".equals(businessname)){
//			selectMap.put("businessname", businessname.toUpperCase());
//		}
		
		try {
			Integer totleCount = selectService.getSelectCount(selectMap);
			p.getPagination(request,totleCount, currPage1,null,null,null);
			int numOfEachPage =	Constants.NUMOFEACHPAGE;
			//int numOfEachPage =	6;
			int begin = p.getRownum_begin(numOfEachPage, currPage1);
			List list = selectService.getSelectByPage(selectMap,begin, numOfEachPage);
			List portcitylist = organizationService.getCityPortList();
			request.setAttribute("portcitylist", portcitylist);
			request.setAttribute("selectList", list);
			request.setAttribute("begin", begin);
			_logger.info("get all select by page success-------");
		} catch (Exception e) {
			throw new AppException("业务查询分页列表", e);
		}
		this.actionName = "getSelectByPage";
		return SUCCESS;
		
	}
	
	/**
	 * 根据业务编号获取具体业务信息
	 * @return
	 * @throws AppException
	 */
	public String getSelectByBusinessid() throws AppException{
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			List<Map> list = selectService.getSelectByBusinessid(businessid);
			Map map = new HashMap();
			List certificateList = new ArrayList();//上传证书路径
			List examrecordList = new ArrayList();//上传检查记录表路径
			List workphotoList = new ArrayList();//上传工作照路径
			List shipinfoList = new ArrayList();
			List stopportinfoList = new ArrayList();
			
			
			
			if(list.size()>0){
				map = list.get(0);
				if(map.get("CERTIFICATE")!=null && !"".equals(map.get("CERTIFICATE").toString())){
					String[] certificate = ((String)map.get("CERTIFICATE")).split(",");
					Map certificateMap = new HashMap();
					for(int i=0;i<certificate.length;i++){
						certificateMap.put("certificate"+i, certificate[i]);
						certificateList.add(certificateMap);
					}
					request.setAttribute("certificateMap", certificateMap);
				}
				if(map.get("EXAMRECORD")!=null && !"".equals(map.get("EXAMRECORD").toString())){
					String[] examrecord = ((String)map.get("EXAMRECORD")).split(",");
					Map examrecordMap = new HashMap();
					for(int i=0;i<examrecord.length;i++){
						examrecordMap.put("examrecord"+i, examrecord[i]);
						examrecordList.add(examrecordMap);
					}
					request.setAttribute("examrecordMap", examrecordMap);
				}
				if(map.get("WORKPHOTO")!=null && !"".equals(map.get("WORKPHOTO").toString())){
					String[] workphoto = ((String)map.get("WORKPHOTO")).split(",");
					Map workphotoMap = new HashMap();
					for(int i=0;i<workphoto.length;i++){
						workphotoMap.put("workphoto"+i, workphoto[i]);
						workphotoList.add(workphotoMap);
					}
					request.setAttribute("workphotoMap", workphotoMap);
				}
				
				if(map.get("SHIPINFO")!=null && !"".equals(map.get("SHIPINFO").toString())){
					String[] shipinfo = ((String)map.get("SHIPINFO")).split(",");
					Map shipinfoMap = new HashMap();
					for(int i=0;i<shipinfo.length;i++){
						shipinfoMap.put("shipinfo"+i, shipinfo[i]);
						shipinfoList.add(shipinfoMap);
					}
					request.setAttribute("shipinfoMap", shipinfoMap);
				}
				if(map.get("STOPPORTINFO")!=null && !"".equals(map.get("STOPPORTINFO").toString())){
					String[] stopportinfo = ((String)map.get("STOPPORTINFO")).split(",");
					Map stopportinfoMap = new HashMap();
					for(int i=0;i<stopportinfo.length;i++){
						stopportinfoMap.put("stopportinfo"+i, stopportinfo[i]);
						stopportinfoList.add(stopportinfoMap);
					}
					request.setAttribute("stopportinfoMap", stopportinfoMap);
				}
				
			}
			List<Map> examPortlist = selectService.getExamPort((String)map.get("APPNO"));
			List<Map> operatorlist = selectService.getOperator((BigDecimal)map.get("ID"));
			List<Map> desCountrylist = selectService.getDesCountry((BigDecimal)map.get("ID"));
			request.setAttribute("appno", map.get("APPNO"));
			//可否预览证书
			/*int isshow = 0;//0：不可看，1：可看
			int isclient = 1;//0：人员，1：客户
			Map session = ActionContext.getContext().getSession();
			List<Map> orgIdList = new ArrayList();
			List<Map> businessOrglist = new ArrayList();
			if(session.get(Constants.ACCOUNT_PERSON_ORG)!=null && !"".equals(session.get(Constants.ACCOUNT_PERSON_ORG))){
				isclient = 0;
				String selectId = (String) session.get(Constants.ACCOUNT_PERSON_ORG);
				if("1".equals(selectId)){
					selectId="";
				}
				orgIdList = selectService.getOrgIdList(selectId);//得到可看的机构列表
				businessOrglist = selectService.getBusinessOrg(businessid);//业务所属机构
				if(businessOrglist.size()>0){
					for(int i=0;i<orgIdList.size();i++){
						Map tmpm1 = orgIdList.get(i);
						String tmps1 = tmpm1.get("ID").toString();
						for(int j=0;j<businessOrglist.size();j++){
							Map tmpm2 = businessOrglist.get(j);
							String tmps2 = tmpm2.get("ORGTO").toString();
							if(tmps1.equals(tmps2)){
								isshow=1;
							}
						}
					}
				}
			}
			isshow = isshow+isclient;*/
			
			Map session = ActionContext.getContext().getSession();
			Account account = (Account) session.get(Constants.ACCOUNT_SESSION);
			int isshow = account.getAccountType();
			
			String orgName = selectService.getorgName((BigDecimal)map.get("ID"));
			
			// 添加了授权签字人及检查员信息,assignmentlist为整个分配流程列表
			List assignmentlist =  assignmentService.getAssignmentWorkflow(Long.valueOf(map.get("ID").toString()));
			request.setAttribute("assignmentlist", assignmentlist);
			
			request.setAttribute("selectList", list);
			request.setAttribute("examPortlist", examPortlist);
			request.setAttribute("operatorlist", operatorlist);
			request.setAttribute("desCountrylist", desCountrylist);
			request.setAttribute("certificateList", certificateList);//上传证书
			request.setAttribute("examrecordList", examrecordList);//上传检查记录表
			request.setAttribute("workphotoList", workphotoList);//上传工作照
			request.setAttribute("orgName", orgName);//实施检查公司
			request.setAttribute("isshow", isshow);//可否预览证书
			request.setAttribute("shipinfoList", shipinfoList);
			request.setAttribute("stopportinfoList", stopportinfoList);
			
		} catch (Exception e) {
			throw new AppException("根据业务编号获取具体业务信息", e);
		}
		/**
		 * 3.24
		 * 取得currpagea的值 在查看具体信息后返回的是之前的页面
		 */
		int currPage = Integer.parseInt(request.getParameter("currPagea"));
		
		request.setAttribute("currPage", currPage);
		return SUCCESS;
	}
	
	/**
	 * 根据业务编号获取证书需要的信息
	 * @return
	 * @throws AppException
	 */
	public String getSelectZSByBusinessid() throws AppException{
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			List<Map> list = selectService.getSelectByBusinessid(businessid);
			Map map = new HashMap();
			if(list.size()>0){
				map = list.get(0);
			}
			List<Map> desCountrylist = selectService.getDesCountry((BigDecimal)map.get("ID"));
			
			request.setAttribute("selectList", list);
			request.setAttribute("desCountrylist", desCountrylist);
			
		} catch (Exception e) {
			throw new AppException("根据业务编号获取证书需要的信息", e);
		}
		return SUCCESS;
	}
	
	/**
	 * 更新业务查询信息
	 * @return
	 * @throws AppException
	 */
	public String updateSelect() throws AppException{
		try {
			selectService.updateSelect(business);
		} catch (Exception e) {
			throw new AppException("更新业务查询信息", e);
		}
		return SUCCESS;
	}
	
	/**
	 * 更改检查是否合格
	 * @return
	 * @throws AppException
	 */
	public String updateQualified() throws AppException{
		HttpServletRequest request = ServletActionContext.getRequest();
		Integer id = Integer.parseInt(request.getParameter("businessid"));
		Integer qualified = Integer.parseInt(request.getParameter("qualified"));
		try {
			selectService.updateQualified(id, qualified);
			_logger.info("updateQualified success-------");
		} catch (Exception e) {
			throw new AppException("更改检查是否合格", e);
		}
		return SUCCESS;
	}
	
	/**
	 * 根据人员的机构ID获取该人员所在机构及其下级机构的机构列表
	 * @return
	 * @throws AppException
	 */
	public String getOrgList() throws AppException{
		HttpServletRequest request = ServletActionContext.getRequest();
		Map session = ActionContext.getContext().getSession();
		String selectId = (String) session.get(Constants.ACCOUNT_PERSON_ORG);
		String isChild = (String)session.get(Constants.ACCOUNT_PERSON_IS_CHILD);
		Map selectMap = new HashMap();
		if(!"1".equals(selectId)){
			selectMap.put("id", selectId);
		}
		if("2".equals(isChild)){
			selectMap.put("isChild", isChild);
		}
		try {
			List<Map> list = selectService.getOrgList(selectMap);
			request.setAttribute("selectOrgList", list);
			_logger.info("getOrgList success-------");
		} catch (Exception e) {
			throw new AppException("根据人员的机构ID获取该人员所在机构及其下级机构的机构列表", e);
		}
		return SUCCESS;
	}

	public AssignmentService getAssignmentService() {
		return assignmentService;
	}

	public void setAssignmentService(AssignmentService assignmentService) {
		this.assignmentService = assignmentService;
	}
	
	public String deleteBusiness() {
		String isDelete = "删除成功！";
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.setAttribute("isDelete", isDelete);
		
		selectService.deleteBusiness(Long.parseLong(this.getBusinessid().toString()));		
		return SUCCESS;
	}
	
	public void getExcel() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String hidden_top[] = request.getParameterValues("hidden_top");
		//String hidden_body[] = request.getParameterValues("hidden_body");
		//String hidden_bottom[] = request.getParameterValues("hidden_bottom");
		try
		{
			List returnList = new ArrayList();
			List contentlist = new ArrayList();
			for (int i = 0; i < hidden_top.length; i++)
				contentlist.add(hidden_top[i]);

			returnList.add(contentlist);
			
			Map session = ActionContext.getContext().getSession();
			Account account = (Account) session.get(Constants.ACCOUNT_SESSION);
			String businessname = "";
			if(account.getAccountType()!=null && account.getAccountType()==2){
				
				String accountid = account.getAccountId();
				try {
					List list = selectService.getBusinessname(accountid);
					if(list.size()>0){
						Map map = (Map)list.get(0);
						businessname = (String)map.get("CO_EN_NAME");
					}
					else{
						businessname = "0";
					}
				} catch (Exception e) {
					throw new AppException("业务查询获取客户公司名称", e);
				}
			}
			/***************************************************/
			
			HttpSession session2 = request.getSession();
			int isshow = account.getAccountType();
			String isShow ="";
			if(isshow == 1) {
				isShow = session.get(Constants.ACCOUNT_ISADMIN).toString();
			}		
			this.setIsDelete((String) session2.getAttribute("isDelete"));
			session2.removeAttribute("isDelete");
			request.setAttribute("isDelete", isDelete);
			request.setAttribute("isShow", isShow);
			
			if("".equals(businessname) && (selectOrgid==null || "".equals(selectOrgid))){
				if(!"1".equals(session.get(Constants.ACCOUNT_PERSON_ORG).toString())){
					selectOrgid = session.get(Constants.ACCOUNT_PERSON_ORG).toString();
					accountId = account.getAccountId();
				}
			}
			if(selectOrgid!=null && !"".equals(selectOrgid)){
				try {
					List<Map> orgIdList = selectService.getOrgIdList(selectOrgid);
					String tmpStr = "";
					for(int i=0;i<orgIdList.size();i++){
						Map tmpMap = orgIdList.get(i);
						tmpStr += (String)tmpMap.get("ID")+",";
					}
					selectOrgid = tmpStr+selectOrgid;
				} catch (Exception e) {
					throw new AppException("根据机构Id获取下属子机构的ID", e);
				}
			}
			Map selectMap = new HashMap();
			selectMap.put("selectBusinessid", selectBusinessid);
			selectMap.put("selectBusinesstate", selectBusinesstate);
			selectMap.put("selectPaystate", selectPaystate);
			selectMap.put("selectLedgerstate", selectLedgerstate);
			selectMap.put("selectVesselname", selectVesselname);
			selectMap.put("selectBusinessname", selectBusinessname);
			selectMap.put("selectCityname", selectCityname);
			selectMap.put("selectOrgid", selectOrgid);
			selectMap.put("businessname", businessname);
			selectMap.put("selectAppno", selectAppno);
			selectMap.put("accountId", accountId);
			selectMap.put("selectTempno", selectTempno);
			selectMap.put("selectBeginAppdate", selectBeginAppdate);
			selectMap.put("selectEndAppdate", selectEndAppdate);
			selectMap.put("selectBeginOperatortime", selectBeginOperatortime);
			selectMap.put("selectEndOperatortime", selectEndOperatortime);
			List listAll = new ArrayList();
			try {
				Integer totleCount = selectService.getSelectCount(selectMap);
				int numOfEachPage =	Constants.NUMOFEACHPAGE;
				listAll = selectService.getSelectByPage(selectMap,-1, -1);
				List portcitylist = organizationService.getCityPortList();
				_logger.info("get all select by page success-------");
			} catch (Exception e) {
				throw new AppException("业务查询分页列表", e);
			}
			for(int i = 0; i < listAll.size(); i ++) {
				List c = new ArrayList();
				Map map = (Map) listAll.get(i);
				c.add(i+1);
				c.add(map.get("ACCOUNT_NAME"));
				if(map.get("APPNO") != null) {
					c.add(map.get("APPNO"));
				}else {
					c.add("未知");
				}
				
				c.add(map.get("TEMPNO"));
				c.add(map.get("VESSELNAME"));
				c.add(map.get("BUSINESSNAME"));
				if(map.get("APPDATE") != null) {
					c.add(map.get("APPDATE"));
				}else {
					c.add("未知");
				}
				if(map.get("ENGLISHCITYNAME") != null) {
					c.add(map.get("ENGLISHCITYNAME"));
				}else {
					c.add(map.get("CITYNAME"));
				}
				String b = null;
				if(map.get("BUSINESSSTATE") != null) {
					 b = map.get("BUSINESSSTATE").toString();
				}
				if("0".equals(b)) {
					c.add("未提交");
				}else if("1".equals(b)) {
					c.add("未受理");
				}else if("2".equals(b)) {
					c.add("未通过");
				}else if("3".equals(b)) {
					c.add("已受理");
				}else if("4".equals(b)) {
					c.add("已分配");
				}else if("5".equals(b)) {
					c.add("已检查");
				}else  {
					c.add("未知");
				}
				if(map.get("OPERATORTIME") != null) {
					c.add(map.get("OPERATORTIME"));
				}else {
					c.add("未知");
				}
				String p = null;
				if(map.get("PAYSTATE") != null) {
					p = map.get("PAYSTATE").toString();
				}				
				if("0".equals(p)) {
					c.add("未付费未开发票");
				}else if("1".equals(p)) {
					c.add("未付费已开发票");
				}else if("2".equals(p)) {
					c.add("已付费未开发票");
				}else {
					c.add("未知");
				}
				String l = null;
				if(map.get("LEDGERSTATE") != null) {
					l = map.get("LEDGERSTATE").toString();
				}
				
				if("0".equals(l)) {
					c.add("未分账");
				}else if("1".equals(l)) {
					c.add("已分账");
				}else {
					c.add("未知");
				}
				returnList.add(c);
			}
			
			/*for (int i = 0; i < hidden_body.length; i++)
			{
				if (i % hidden_top.length == 0)
					contentlist2 = new ArrayList();
				contentlist2.add(hidden_body[i]);
				if ((i + 1) % hidden_top.length == 0)
					list.add(contentlist2);
			}

			List contentlist3 = new ArrayList();
			for (int i = 0; i < hidden_bottom.length; i++)
				contentlist3.add(hidden_bottom[i]);

			list.add(contentlist3);
			*/
			String sheetname = "业务查询表";
			String formatFileName = URLEncoder.encode(sheetname, "UTF-8");
			HSSFWorkbook workbook = ExcelUtil.toExcel(sheetname, returnList);
			response.reset();
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition", (new StringBuilder("attachment;filename=")).append(formatFileName).append(".xls").toString());
			workbook.write(response.getOutputStream());
			response.getOutputStream().close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void getBMBB() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		//String hidden_top[] = request.getParameterValues("hidden_top");
		//String hidden_body[] = request.getParameterValues("hidden_body");
		//String hidden_bottom[] = request.getParameterValues("hidden_bottom");
		try
		{
			List returnList = new ArrayList();
			List contentlist = new ArrayList();
			contentlist.add("Certificate No.");
			contentlist.add("Name of Vessel");
			contentlist.add("Nationality");
			contentlist.add("IMO No.");
			contentlist.add("Type of Vessel");
			contentlist.add("Port of Inspection");
			contentlist.add("Date of Inspection");

			returnList.add(contentlist);
			
			Map session = ActionContext.getContext().getSession();
			Account account = (Account) session.get(Constants.ACCOUNT_SESSION);
			String businessname = "";
			if(account.getAccountType()!=null && account.getAccountType()==2){
				
				String accountid = account.getAccountId();
				try {
					List list = selectService.getBusinessname(accountid);
					if(list.size()>0){
						Map map = (Map)list.get(0);
						businessname = (String)map.get("CO_EN_NAME");
					}
					else{
						businessname = "0";
					}
				} catch (Exception e) {
					throw new AppException("业务查询获取客户公司名称", e);
				}
			}
			/***************************************************/
			
			HttpSession session2 = request.getSession();
			int isshow = account.getAccountType();
			String isShow ="";
			if(isshow == 1) {
				isShow = session.get(Constants.ACCOUNT_ISADMIN).toString();
			}		
			this.setIsDelete((String) session2.getAttribute("isDelete"));
			session2.removeAttribute("isDelete");
			request.setAttribute("isDelete", isDelete);
			request.setAttribute("isShow", isShow);
			
			if("".equals(businessname) && (selectOrgid==null || "".equals(selectOrgid))){
				if(!"1".equals(session.get(Constants.ACCOUNT_PERSON_ORG).toString())){
					selectOrgid = session.get(Constants.ACCOUNT_PERSON_ORG).toString();
					accountId = account.getAccountId();
				}
			}
			if(selectOrgid!=null && !"".equals(selectOrgid)){
				try {
					List<Map> orgIdList = selectService.getOrgIdList(selectOrgid);
					String tmpStr = "";
					for(int i=0;i<orgIdList.size();i++){
						Map tmpMap = orgIdList.get(i);
						tmpStr += (String)tmpMap.get("ID")+",";
					}
					selectOrgid = tmpStr+selectOrgid;
				} catch (Exception e) {
					throw new AppException("根据机构Id获取下属子机构的ID", e);
				}
			}
			Map selectMap = new HashMap();
			selectMap.put("selectBusinessid", selectBusinessid);
			selectMap.put("selectBusinesstate", selectBusinesstate);
			selectMap.put("selectPaystate", selectPaystate);
			selectMap.put("selectLedgerstate", selectLedgerstate);
			selectMap.put("selectVesselname", selectVesselname);
			selectMap.put("selectBusinessname", selectBusinessname);
			selectMap.put("selectCityname", selectCityname);
			selectMap.put("selectOrgid", selectOrgid);
			selectMap.put("businessname", businessname);
			selectMap.put("selectAppno", selectAppno);
			selectMap.put("accountId", accountId);
			selectMap.put("selectTempno", selectTempno);
			selectMap.put("selectBeginAppdate", selectBeginAppdate);
			selectMap.put("selectEndAppdate", selectEndAppdate);
			selectMap.put("selectBeginOperatortime", selectBeginOperatortime);
			selectMap.put("selectEndOperatortime", selectEndOperatortime);
			List listAll = new ArrayList();
			try {
				Integer totleCount = selectService.getSelectCount(selectMap);
				int numOfEachPage =	Constants.NUMOFEACHPAGE;
				listAll = selectService.getSelectByPage(selectMap,-1, -1);
				List portcitylist = organizationService.getCityPortList();
				_logger.info("get all select by page success-------");
			} catch (Exception e) {
				throw new AppException("业务查询分页列表", e);
			}
			
			for(int i = 0; i < listAll.size(); i ++) {
				List c = new ArrayList();
				Map map = (Map) listAll.get(i);
				c.add(map.get("APPNO"));
				c.add(map.get("VESSELNAME"));
				c.add(map.get("REGISTRY"));
				c.add(map.get("IMO"));
				c.add(map.get("VESSELTYPE"));
				c.add(map.get("PORT_SNAME"));
				c.add(map.get("OPERATORTIME"));
				returnList.add(c);
			}
			
			/*for (int i = 0; i < hidden_body.length; i++)
			{
				if (i % hidden_top.length == 0)
					contentlist2 = new ArrayList();
				contentlist2.add(hidden_body[i]);
				if ((i + 1) % hidden_top.length == 0)
					list.add(contentlist2);
			}

			List contentlist3 = new ArrayList();
			for (int i = 0; i < hidden_bottom.length; i++)
				contentlist3.add(hidden_bottom[i]);

			list.add(contentlist3);
			*/
			String sheetname = "业务查询表";
			String formatFileName = URLEncoder.encode(sheetname, "UTF-8");
			HSSFWorkbook workbook = ExcelUtil.toExcel(sheetname, returnList);
			response.reset();
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition", (new StringBuilder("attachment;filename=")).append(formatFileName).append(".xls").toString());
			workbook.write(response.getOutputStream());
			response.getOutputStream().close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
