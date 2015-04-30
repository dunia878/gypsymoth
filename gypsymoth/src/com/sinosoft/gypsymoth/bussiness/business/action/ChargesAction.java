package com.sinosoft.gypsymoth.bussiness.business.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sinosoft.gypsymoth.bussiness.business.service.ChargesService;
import com.sinosoft.gypsymoth.bussiness.business.service.SelectService;
import com.sinosoft.gypsymoth.bussiness.organization.service.OrganizationService;
import com.sinosoft.gypsymoth.exception.AppException;
import com.sinosoft.gypsymoth.pojo.Account;
import com.sinosoft.gypsymoth.pojo.Opaccount;
import com.sinosoft.gypsymoth.pojo.Payment;
import com.sinosoft.gypsymoth.utils.Constants;
import com.sinosoft.gypsymoth.utils.Pagination;

public class ChargesAction extends ActionSupport {
	private final Logger _logger = Logger.getLogger(ChargesAction.class);
	
	private ChargesService chargesService;
	private OrganizationService organizationService;
	private SelectService selectService;
	private String actionName;
	
	private Payment payment;
	
	private Integer businessid;
	
	private String selectVesselname;//查询条件：IMO号
	
	private String selectAppno;//查询条件：业务编号
	
	private Integer selectLedgerstate;//查询条件：分账状态
	
	private Integer selectBusinessstate;//查询条件：业务状态
	
	private Integer selectPaystate;//查询条件：付费状态
	
	private String selectCityname;//查询条件：港口城市
	
	private String selectBeginAppdate;//查询条件：最小申请日期
	
	private String selectEndAppdate;//查询条件：最大申请日期
	
	private String selectOrgid;//查询条件：机构ID
	
	private String accountId;
	
	private String goPage;
	
	private String account_name; //申请人姓名
	
	public String getAccount_name() {
		return account_name;
	}



	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}



	public String getGoPage() {
		return goPage;
	}



	public void setGoPage(String goPage) {
		this.goPage = goPage;
	}



	public String getSelectOrgid() {
		return selectOrgid;
	}



	public void setSelectOrgid(String selectOrgid) {
		this.selectOrgid = selectOrgid;
	}



	public String getAccountId() {
		return accountId;
	}



	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}



	public SelectService getSelectService() {
		return selectService;
	}



	public void setSelectService(SelectService selectService) {
		this.selectService = selectService;
	}



	public OrganizationService getOrganizationService() {
		return organizationService;
	}



	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
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



	public String getSelectCityname() {
		return selectCityname;
	}



	public void setSelectCityname(String selectCityname) {
		this.selectCityname = selectCityname;
	}



	public Integer getSelectBusinessstate() {
		return selectBusinessstate;
	}



	public void setSelectBusinessstate(Integer selectBusinessstate) {
		this.selectBusinessstate = selectBusinessstate;
	}



	public Integer getSelectPaystate() {
		return selectPaystate;
	}



	public void setSelectPaystate(Integer selectPaystate) {
		this.selectPaystate = selectPaystate;
	}



	public String getSelectVesselname() {
		return selectVesselname;
	}



	public void setSelectVesselname(String selectVesselname) {
		this.selectVesselname = selectVesselname;
	}

	public String getSelectAppno() {
		return selectAppno;
	}


	public void setSelectAppno(String selectAppno) {
		this.selectAppno = selectAppno;
	}



	public Integer getSelectLedgerstate() {
		return selectLedgerstate;
	}



	public void setSelectLedgerstate(Integer selectLedgerstate) {
		this.selectLedgerstate = selectLedgerstate;
	}



	public Payment getPayment() {
		return payment;
	}



	public void setPayment(Payment payment) {
		this.payment = payment;
	}



	public Integer getBusinessid() {
		return businessid;
	}



	public void setBusinessid(Integer businessid) {
		this.businessid = businessid;
	}



	public ChargesService getChargesService() {
		return chargesService;
	}



	public void setChargesService(ChargesService chargesService) {
		this.chargesService = chargesService;
	}



	public String getActionName() {
		return actionName;
	}



	public void setActionName(String actionName) {
		this.actionName = actionName;
	}


	/**
	 * 分页财务收费
	 * @return
	 * @throws AppException
	 */
	public String getPaymentByPage() throws AppException{
		
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
		//分页配置
		Pagination p = new Pagination(0,0,0);
		String currPage= this.getGoPage();//(String)request.getParameter("goPage");
		//(String)request.getParameter("goPage");
		//String c = request.getParameter("goPage");
	
		if(currPage==null){  
			currPage="1";//” currPage”是当前页数
		}
		_logger.info("currPage:"+currPage);
		Integer currPage1 = Integer.parseInt(currPage);
		request.setAttribute("currPage1", currPage);
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
		//一下两条 目的 为 不区分大小写查询
		if(selectVesselname != null && selectAppno != null) {
			selectMap.put("selectVesselname", selectVesselname.toUpperCase());
			System.out.println("****" + selectVesselname.toUpperCase());
			selectMap.put("selectAppno", selectAppno.toUpperCase());
		}else {
			selectMap.put("selectVesselname", selectVesselname);
			selectMap.put("selectAppno", selectAppno);
		}
		
		//add by 郭定军 加上申请人查询条件
		if(account_name != null && !"".equals(account_name)){
			selectMap.put("account_name", account_name.toLowerCase());
		}
		
		selectMap.put("selectLedgerstate", selectLedgerstate);
		selectMap.put("selectBusinessstate", selectBusinessstate);
		selectMap.put("selectPaystate", selectPaystate);
		selectMap.put("selectCityname", selectCityname);
		selectMap.put("selectBeginAppdate", selectBeginAppdate);
		selectMap.put("selectEndAppdate", selectEndAppdate);
		selectMap.put("selectOrgid", selectOrgid);
		selectMap.put("businessname", businessname);
		selectMap.put("accountId", accountId);
		try {
			Integer totleCount = chargesService.getAllPaymentCount(selectMap);
			p.getPagination(request,totleCount, currPage1,null,null,null);
			int numOfEachPage =	Constants.NUMOFEACHPAGE;
			int begin = p.getRownum_begin(numOfEachPage, currPage1);
			List list = chargesService.getPaymentByPage(selectMap,begin, numOfEachPage);
			List portcitylist = organizationService.getCityPortList();
			request.setAttribute("portcitylist", portcitylist);
			request.setAttribute("paymentList", list);
			request.setAttribute("begin", begin);
			_logger.info("get all Payment by page success-------");
		} catch (Exception e) {
			throw new AppException("分页财务收费", e);
		}
		this.actionName = "getPaymentByPage";
		return SUCCESS;
	}
	
	/**
	 * 根据业务ID查询缴费记录
	 * @return
	 * @throws AppException
	 */
	public String getPaymentByBusinessid() throws AppException{
		HttpServletRequest request = ServletActionContext.getRequest();
		String currPagea = request.getParameter("currPagea");
		request.setAttribute("currPageb", currPagea);
		try {
			List payment = chargesService.getPaymentByBusinessid(businessid);
			request.setAttribute("payment", payment);
			List opaccounts = chargesService.getOpAccount(businessid,0);
			request.setAttribute("opaccounts", opaccounts);
		} catch (Exception e) {
			throw new AppException("分页财务收费", e);
		}
		return SUCCESS;
	}
	
	/**
	 * 更新财务收费
	 * @return
	 * @throws AppException
	 */
	public String updatePayment() throws AppException{
		try {
			businessid = Integer.valueOf(payment.getBusinessid().toString());
			chargesService.updatePayment(payment);
			List paymentList = chargesService.getPaymentByBusinessid(businessid);
			Map session = ActionContext.getContext().getSession();
			Account account = (Account) session.get(Constants.ACCOUNT_SESSION);
			Map map = (Map) paymentList.get(0);
			HttpServletRequest request = ServletActionContext.getRequest();
			String goPage = request.getParameter("goPage");
			
			
			request.setAttribute("goPage", goPage);
			Opaccount opaccount = new Opaccount();
			//付费状态
			String payState = map.get("PAYSTATE").toString();
			opaccount.setIsPay(payState);
			//是否付账
			String amount = map.get("AMOUNT").toString();
			if(amount != null && !"".equals(amount)) {
				opaccount.setIsAccount("1");
			}else {
				opaccount.setIsAccount("0");
			}
			//分账状态
			String lederstate = map.get("LEDGERSTATE").toString();
			opaccount.setIsCurrency(lederstate);
			//操作人
			String opterator = account.getAccountName();
			opaccount.setOperator(opterator);
			//request.setAttribute("payment", paymentList);
			//操作时间
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String sDate = sdf.format(date);
			opaccount.setOpTime(sDate);
			//账号————》businessId
			String businessId = map.get("BUSINESSID").toString();
			opaccount.setBusinessid(Long.parseLong(businessId));
			opaccount.setOpState("");
			opaccount.setOpContent("");
			opaccount.setRemark("");
			chargesService.saveOpAccount(opaccount);
			this.saveLog(account, map);
		} catch (Exception e) {
			throw new AppException("更新财务收费", e);
		}
		return SUCCESS;
	}
	
	/**
	 * "已到账" 的操作
	 * @return
	 * @throws AppException
	 */
	public String havePayment() throws AppException{
		HttpServletRequest request = ServletActionContext.getRequest();
		Map session = ActionContext.getContext().getSession();
		Account account = (Account) session.get(Constants.ACCOUNT_SESSION);
		try {
			businessid = Integer.valueOf(payment.getBusinessid().toString());
			chargesService.updatePayment(payment);
			List paymentList = chargesService.getPaymentByBusinessid(businessid);
			Map map = (Map) paymentList.get(0);
			Opaccount opaccount = new Opaccount();
			//付费状态
			String payState = map.get("PAYSTATE").toString();
			opaccount.setIsPay(payState);
			//是否付账
			String amount = map.get("AMOUNT").toString();
			if(amount != null && !"".equals(amount)) {
				opaccount.setIsAccount("1");
			}else {
				opaccount.setIsAccount("0");
			}
			//分账状态
			String lederstate = map.get("LEDGERSTATE").toString();
			opaccount.setIsCurrency(lederstate);
			//操作人
			String opterator = account.getAccountName();
			opaccount.setOperator(opterator);
			
			//操作时间
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String sDate = sdf.format(date);
			opaccount.setOpTime(sDate);
			//账号————》businessId
			String businessId = map.get("BUSINESSID").toString();
			opaccount.setBusinessid(Long.parseLong(businessId));
			opaccount.setOpState("");
			opaccount.setOpContent("");
			opaccount.setRemark("");
			
			chargesService.saveOpAccount(opaccount);
			List opaccounts = chargesService.getOpAccount(businessid);
			request.setAttribute("opaccounts", opaccounts);
			//-----------------------------
			this.saveLog(account, map);
			int f = 0;
			request.setAttribute("payment", paymentList);
			List opaccounts = chargesService.getOpAccount(businessid,f);
			request.setAttribute("opaccounts", opaccounts);
		} catch (Exception e) {
			throw new AppException("已到账", e);
		}
		return SUCCESS;
	}
	/**
	 * 
	 * Mar 25, 2011
	 * Administrator
	 * 财务模块操作日志
	 * @return
	 */
	private Boolean saveLog(Account account, Map map) throws Exception {
		Opaccount opaccount = new Opaccount();
		int flag = 0;
		//付费状态
		String payState = map.get("PAYSTATE").toString();
		opaccount.setIsPay(payState);
		System.out.println("************" + payState);
		//是否付账
		String amount = map.get("AMOUNT").toString();
		System.out.println("-------------------" + amount);
		if(amount != null && !"".equals(amount)) {
			opaccount.setIsAccount("1");
		}else {
			opaccount.setIsAccount("0");
		}
		//分账状态
		String lederstate = map.get("LEDGERSTATE").toString();
		opaccount.setIsCurrency(lederstate);
		//操作人
		String opterator = account.getAccountName();
		opaccount.setOperator(opterator);
		
		//操作时间
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String sDate = sdf.format(date);
		opaccount.setOpTime(sDate);
		//账号————》businessId
		String businessId = map.get("BUSINESSID").toString();
		opaccount.setBusinessid(Long.parseLong(businessId));
		opaccount.setOpState("");
		opaccount.setOpContent("");
		opaccount.setRemark("");
		//判断 操作是否相同
		boolean b = false;
		int f = 1;
		List opaccounts = chargesService.getOpAccount(Integer.parseInt(businessId),f);
		
		if(opaccounts != null && opaccounts.size() > 0) {
				flag = 1;
			//for(int i = 0; i < opaccounts.size(); i ++) {
				Opaccount opaccount2 = (Opaccount) opaccounts.get(0);
				//System.out.println("***********" +payState + "   " + opaccount.getIsPay());
				
				if(!opaccount.getIsPay().equals(opaccount2.getIsPay())) {
					b = true;
					flag =2;
					String isPay = opaccount.getIsPay();
					if("0".equals(isPay)) {
						opaccount.setOpContent(" 未付费未开发票");
					}else if("3".equals(isPay)) {
						opaccount.setOpContent("已付费已开发票");
					}else if("2".equals(isPay)) {
					opaccount.setOpContent("已付费未开发票");
				}
					chargesService.saveOpAccount(opaccount);
					//break;
				}
				
				
				if(!opaccount.getIsCurrency().equals(opaccount2.getIsCurrency())) {
					b = true;
					flag =2;
					String isCurrency = opaccount.getIsCurrency();
					if("0".equals(isCurrency)) {
						opaccount.setOpContent("未分账");
					}else {
						opaccount.setOpContent("已分账");
					}
					chargesService.saveOpAccount(opaccount);
					//break;
				}
				if(!opaccount.getOperator().equals(opaccount2.getOperator())) {
					b = true;
					if(flag ==1) {
						opaccount.setOpContent("无");
						chargesService.saveOpAccount(opaccount);
					}
					//break;
				}
				
			//0}
		}else {
			b = true;
		}
		if(b && flag == 0) {
			if(!"0".equals(opaccount.getIsAccount())) {
				flag = 3;
				String isAccount = opaccount.getIsAccount();
				if("1".equals(isAccount)) {
					opaccount.setOpContent("已到账");
				}else {
					opaccount.setOpContent("未到账");
				}
				chargesService.saveOpAccount(opaccount);
			}
			
			if(flag ==0) {
				opaccount.setOpContent("无");
				chargesService.saveOpAccount(opaccount);
			}
		}
			
		return b;
	} 

}