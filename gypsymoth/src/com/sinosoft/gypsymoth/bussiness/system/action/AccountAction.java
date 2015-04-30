package com.sinosoft.gypsymoth.bussiness.system.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sinosoft.gypsymoth.bussiness.register.service.RegisterService;
import com.sinosoft.gypsymoth.bussiness.system.service.AccountService;
import com.sinosoft.gypsymoth.exception.AppException;
import com.sinosoft.gypsymoth.pojo.Account;
import com.sinosoft.gypsymoth.pojo.AccountRecord;
import com.sinosoft.gypsymoth.pojo.Client;
import com.sinosoft.gypsymoth.pojo.Role;
import com.sinosoft.gypsymoth.utils.CommonTool;
import com.sinosoft.gypsymoth.utils.Constants;
import com.sinosoft.gypsymoth.utils.MD5Util;
import com.sinosoft.gypsymoth.utils.Pagination;

public class AccountAction extends ActionSupport {

	private final Logger _logger = Logger.getLogger(AccountAction.class);

	private AccountService accountService;
	
	private RegisterService registerService;

	private String actionName;

	private Account account;

	private Client client;

	private String personId;
	
	private String accountId;
	
	private String roleHas;
	
	private Integer accountStatus;//查询条件：用户状态
	
	private Integer accountType;//查询条件：用户类型
	
	private String accountName;//查询条件：用户名
	
	private String password;
	
	private String oldPassword;
	
	private String realName;//查询条件：姓名或公司名
	
	private String hiddenAccountId;
	
	private Integer hiddenAccountStatus;
	
	private String currPageTmp;//临时存放页码
	
	
	
	public String getCurrPageTmp() {
		return currPageTmp;
	}

	public void setCurrPageTmp(String currPageTmp) {
		this.currPageTmp = currPageTmp;
	}

	public String getHiddenAccountId() {
		return hiddenAccountId;
	}

	public void setHiddenAccountId(String hiddenAccountId) {
		this.hiddenAccountId = hiddenAccountId;
	}

	public Integer getHiddenAccountStatus() {
		return hiddenAccountStatus;
	}

	public void setHiddenAccountStatus(Integer hiddenAccountStatus) {
		this.hiddenAccountStatus = hiddenAccountStatus;
	}

	private static CommonTool commonDao;

	private String message;
	
	
	
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public RegisterService getRegisterService() {
		return registerService;
	}

	public void setRegisterService(RegisterService registerService) {
		this.registerService = registerService;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(Integer accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getRoleHas() {
		return roleHas;
	}

	public void setRoleHas(String roleHas) {
		this.roleHas = roleHas;
	}

	public CommonTool getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonTool commonDao) {
		this.commonDao = commonDao;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	/**
	 * 十四位随机数ID
	 * @return
	 */
	public static BigDecimal getSerialNo() {

		return commonDao.getDateSerialNo();
	}

	/**
	 * 分页获取用户列表
	 * @return
	 * @throws AppException
	 */
	public String getAccountByPage() throws AppException {

		HttpServletRequest request = ServletActionContext.getRequest();
		// 分页配置
		Pagination p = new Pagination(0, 0, 0);
		String currPage = (String) request.getParameter("goPage");
		if (currPage == null || "".equals(currPage)) {
			currPage = currPageTmp;
		}
		if (currPage == null || "".equals(currPage)) {
			currPage = "1";// ” currPage”是当前页数
		}
		_logger.info("currPage:" + currPage);
		Integer currPage1 = Integer.parseInt(currPage);
		try {
			Map tempAccount = new HashMap();
			if(accountName!=null){
				accountName = accountName.toLowerCase();
			}
			tempAccount.put("accountName", accountName);
			tempAccount.put("accountStatus", accountStatus);
			tempAccount.put("accountType", accountType);
			tempAccount.put("realName", realName);
			Integer totleCount = accountService.getAllAccountCount(tempAccount);
			p.getPagination(request, totleCount, currPage1, null, null, null);
			int numOfEachPage = Constants.NUMOFEACHPAGE;
			int begin = p.getRownum_begin(numOfEachPage, currPage1);
			List list = accountService.getAccountByPage(tempAccount, begin,
					numOfEachPage);
			request.setAttribute("accountList", list);
			request.setAttribute("currPage", currPage);
			_logger.info("get all account by page success-------");
		} catch (Exception e) {
			throw new AppException("分页查询用户", e);
		}
		this.actionName = "getAccountByPage";
		return SUCCESS;
	}

	/**
	 * 获取机构人员列表
	 * 
	 * @return
	 * @throws AppException
	 */
	public String getAccountInitData() throws AppException {
		if("have".equals(message)){
			this.setMessage("用户名已存在，请重填");
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			List orgPersonList = accountService.getAllOrgPerson();
			List<Role> roleNotHas = accountService.getAllRole();
			request.setAttribute("orgPersonList", orgPersonList);
			request.setAttribute("roleNotHas", roleNotHas);
		} catch (Exception e) {
			throw new AppException("获取机构人员列表", e);
		}
		_logger.info("getAllOrgPerson success-------");
		return SUCCESS;
	}

	/**
	 * 添加用户
	 * @return
	 * @throws AppException
	 */
	public String saveAccount() throws AppException {
		List namelist = new ArrayList();
		try{
			namelist = registerService.getAccountByName(account.getAccountName().toLowerCase());
			if(namelist.size()>0){
				message="have";
				return "haveName";
			}
		} catch (Exception e) {
			throw new AppException("校验用户名唯一",e);
		}
		
		String accountId = getSerialNo().toString();
		Map session = ActionContext.getContext().getSession();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date createDate = new Date();
		try {
			createDate = sdf.parse(sdf.format(createDate));
			account.setAccountName(account.getAccountName().toLowerCase());
			account.setAccountId(accountId);
			account.setCreateBy(((Account)session.get(Constants.ACCOUNT_SESSION)).getAccountName());
			account.setCreateDate(createDate);
			account.setLoginFailTimes(0);
			account.setLoginLock(0);
			account.setPassword(MD5Util.getMD5String(account.getPassword()));
			if (account.getAccountType() == 1) {//类型为人员
				accountService.savePAccount(account, personId,roleHas);
			} else if (account.getAccountType() == 2) {//类型为客户
				accountService.saveCAccount(account, client);
			}
		} catch (Exception e) {
			throw new AppException("添加用户", e);
		}
		_logger.info("saveAccount success-------");
		return SUCCESS;
	}
	
	/**
	 * 根据ID获取用户信息
	 * @return
	 * @throws Exception
	 */
	public String getAccountById() throws AppException{
		HttpServletRequest request = ServletActionContext.getRequest();
		currPageTmp = (String)request.getParameter("currPage");
		try {
			account = accountService.getAccountById(accountId);
		
			List roleHasList = accountService.getRoleHas(accountId);
			List roleNotHasList = accountService.getRoleNotHas(accountId);
			List personIs = accountService.getPersonIs(accountId);
			request.setAttribute("roleHasList", roleHasList);
			request.setAttribute("roleNotHasList", roleNotHasList);
			request.setAttribute("personIs", personIs);
			request.setAttribute("currPage", currPageTmp);
		} catch (Exception e) {
			throw new AppException("根据ID获取用户信息", e);
		}
		return SUCCESS;
	}
	
	/**
	 * 更新用户角色信息
	 * @return
	 * @throws Exception
	 */
	public String updateAccount() throws AppException{
		HttpServletRequest request = ServletActionContext.getRequest();
		AccountRecord ar = new AccountRecord();
		/*暂时没用
		Map session = ActionContext.getContext().getSession();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date modifyDate = new Date();
		modifyDate = sdf.parse(sdf.format(modifyDate));
		ar.setAccountId(account.getAccountId());
		ar.setAccountName(account.getAccountName());
		ar.setAccountType(account.getAccountType());
		ar.setAccountStatus(account.getAccountStatus());
		ar.setModifyType(1);
		ar.setModifyDate(modifyDate);
		ar.setModifyBy(((Account)session.get(Constants.ACCOUNT_SESSION)).getAccountName());
		*/
		try {
			accountService.updateAccount(account, ar, roleHas);
		} catch (Exception e) {
			throw new AppException("更新用户角色信息", e);
		}
		request.setAttribute("currPageTmp", currPageTmp);
		return SUCCESS;
	}
	
	/**
	 * 更改用户状态
	 * @return
	 * @throws AppException
	 */
	public String updateAccountStatus() throws AppException{
		Map session = ActionContext.getContext().getSession();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date modifyDate = new Date();
		try {
			modifyDate = sdf.parse(sdf.format(modifyDate));
			account = accountService.getAccountById(hiddenAccountId);
			AccountRecord accountRecord = new AccountRecord();
			accountRecord.setAccountId(hiddenAccountId);
			accountRecord.setAccountName(account.getAccountName());
			accountRecord.setAccountStatus(hiddenAccountStatus);
			accountRecord.setAccountType(account.getAccountType());
			accountRecord.setModifyBy(((Account)session.get(Constants.ACCOUNT_SESSION)).getAccountName());
			accountRecord.setModifyDate(modifyDate);
			accountRecord.setModifyType(1);
			accountService.updateAccountStatus(accountRecord);
		} catch (Exception e) {
			throw new AppException("更改用户状态", e);
		}
		return getAccountByPage();
	}
	
	/**
	 * 重置用户密码
	 * @return
	 * @throws AppException
	 */
	public String updateAccountPassword() throws AppException{
		password = MD5Util.getMD5String(password);
		try {
			accountService.updateAccountPassword(accountId, password);
		} catch (Exception e) {
			throw new AppException("修改用户密码", e);
		}
		return SUCCESS;
	}
	
	/**
	 * 根据ID删除用户
	 * @return
	 * @throws AppException
	 */
	public String deleteAccountById() throws AppException{
		Map session = ActionContext.getContext().getSession();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date modifyDate = new Date();
		String modifyBy = ((Account)session.get(Constants.ACCOUNT_SESSION)).getAccountName();
		try {
			modifyDate = sdf.parse(sdf.format(modifyDate));
			accountService.deleteAccountById(accountId, modifyBy, modifyDate);
		} catch (Exception e) {
			throw new AppException("根据ID删除用户", e);
		}
		return SUCCESS;
	}
	
	/**
	 * 用户修改密码
	 * @return
	 * @throws AppException
	 */
	public String updatePassword() throws AppException{
		HttpServletRequest request = ServletActionContext.getRequest();
		Map session = ActionContext.getContext().getSession();
		Account account = (Account)session.get(Constants.ACCOUNT_SESSION);
		String realpassword = account.getPassword();
		oldPassword = MD5Util.getMD5String(oldPassword);
		accountId = account.getAccountId();
		if(oldPassword.equals(realpassword)){
			try {
				accountService.updateAccountPassword(accountId, MD5Util.getMD5String(password));
			} catch (Exception e) {
				throw new AppException("用户修改密码", e);
			}
		}
		else{
			request.setAttribute("message", this.getText("password_error6"));
			return "passworderror";
		}
		request.setAttribute("message", this.getText("password_error7"));
		return SUCCESS;
		
	}
	
	/**
	 * 修改人员或客户基本信息
	 * @return
	 * @throws AppException
	 */
	public String updateCorP() throws AppException{
		Map session = ActionContext.getContext().getSession();
		Account account = (Account)session.get(Constants.ACCOUNT_SESSION);
		if(account.getAccountType()==1){
			return "updatePerson";
		}
		else{
			return "updateClient";
		}
	}

	/**
	 * 解锁
	 * @return
	 * @throws AppException
	 */
	
	public String updateLog() throws Exception {
		accountService.updateLog(hiddenAccountId);
		return SUCCESS;
	}
}
