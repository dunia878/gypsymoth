package com.sinosoft.gypsymoth.bussiness.register.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.omg.CORBA.Request;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sinosoft.gypsymoth.bussiness.register.service.RegisterService;
import com.sinosoft.gypsymoth.bussiness.system.service.AccountService;
import com.sinosoft.gypsymoth.exception.AppException;
import com.sinosoft.gypsymoth.pojo.Account;
import com.sinosoft.gypsymoth.pojo.AccountRole;
import com.sinosoft.gypsymoth.pojo.Client;
import com.sinosoft.gypsymoth.utils.CommonTool;
import com.sinosoft.gypsymoth.utils.MD5Util;
import com.sinosoft.gypsymoth.utils.SendMail;

public class RegisterAction extends ActionSupport {

	private static final Logger _logger = Logger
			.getLogger(RegisterAction.class);

	private static CommonTool commonDao;

	private RegisterService registerService;
	
	private AccountService accountService;
	
	private Client client;
	
	private Account account;
	
	private String license;
	
	private Integer nationId1;//中国
	
	private String address1;//中国地址
	
	private Integer nationId2;//其他国家
	
	private String address2;//其他国家地址
	
	private String changeAddress;//中国or其他国家
	
	private String message;

	private String validate;//验证码
	
	
	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public Integer getNationId1() {
		return nationId1;
	}

	public void setNationId1(Integer nationId1) {
		this.nationId1 = nationId1;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public Integer getNationId2() {
		return nationId2;
	}

	public void setNationId2(Integer nationId2) {
		this.nationId2 = nationId2;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getChangeAddress() {
		return changeAddress;
	}

	public void setChangeAddress(String changeAddress) {
		this.changeAddress = changeAddress;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public RegisterService getRegisterService() {
		return registerService;
	}

	public void setRegisterService(RegisterService registerService) {
		this.registerService = registerService;
	}

	public CommonTool getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonTool commonDao) {
		this.commonDao = commonDao;
	}

	public static BigDecimal getSerialNo() {

		return commonDao.getDateSerialNo();
	}

	/**
	 * 获取用户注册初始数据
	 * @return
	 * @throws AppException
	 */
	public String getInitData() throws AppException {
		if("have".equals(message)){
			this.setMessage(this.getText("account_error5"));
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		List nationList = new ArrayList();
		List promaryList = new ArrayList();
		try {
			nationList = registerService.getNation();
			promaryList = registerService.getPromory();
		} catch (Exception e) {
			throw new AppException("获取用户注册初始数据", e);
		}
		request.setAttribute("nationList", nationList);
		request.setAttribute("promaryList", promaryList);
		return SUCCESS;
	}
	
	/**
	 * ajax根据省份获取城市列表
	 * @return
	 * @throws AppException
	 */
	public String getCity() throws AppException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Integer proId = Integer.valueOf(request.getParameter("input"));
		List cityList = new ArrayList();
		
		try{
			cityList = registerService.getCity(proId);
		} catch (Exception e) {
			throw new AppException("获取城市列表",e);
		}
		
		request.setAttribute("cityList", cityList);
		return SUCCESS;
	}
	
	/**
	 * 校验用户名唯一
	 * @return
	 * @throws AppException
	 */
	public String getAccountByName() throws AppException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String accountName = request.getParameter("input");
		List namelist = new ArrayList();
		try{
			namelist = registerService.getAccountByName(accountName.toLowerCase());
		} catch (Exception e) {
			throw new AppException("校验用户名唯一",e);
		}
	
		request.setAttribute("namelist", namelist);
		
		return SUCCESS;
	}
	
	/**
	 * 保存注册用户
	 * @return
	 * @throws AppException
	 */
	public String saveClient() throws AppException {
		HttpServletRequest request = ServletActionContext.getRequest();
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
		try {
			Date createDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			createDate = sdf.parse(sdf.format(createDate));
			//用户角色关系对象
			AccountRole accountRole = new AccountRole();
			accountRole.setAccountId(accountId);
			accountRole.setRoleId("5");
			//用户对象
			account.setAccountName(account.getAccountName().toLowerCase());
			account.setAccountId(accountId);
			account.setAccountType(2);
			//不发邮件直接激活时的改动 0-1
			account.setAccountStatus(1);
			account.setCreateBy(account.getAccountName());
			account.setCreateDate(createDate);
			account.setLoginLock(0);
			account.setLoginFailTimes(0);
			account.setPassword(MD5Util.getMD5String(account.getPassword()));
			//客户对象
			client.setAccountId(accountId);
			if("1".equals(changeAddress)){
				client.setAddress(address1);
				client.setNationId(nationId1);
			}
			else if("2".equals(changeAddress)){
				client.setAddress(address2);
				client.setNationId(nationId2);
			}
			
			client.setClientName(account.getAccountName().toLowerCase());
			
			String bank1 = (String) request.getParameter("bank1");
			String bank2 = (String) request.getParameter("bank2");
			if(bank1 != null && bank1.equals("其他")) {
				client.setBank(bank2);
			}else {
				client.setBank(bank1);
			}
			registerService.saveClient(account, client, accountRole);
		} catch (Exception e) {
			throw new AppException("保存注册用户",e);
		}
		accountId = MD5Util.getMD5String(accountId);
		/*SendMail sendMail = new SendMail();
		String text = "Thank you for registering at agm.ccic.com. To activate your account and verify your email address, please click on the following link:\n";
		text += "http://agmtest.ccic.com:9090/gypsymoth/page/register/updateAccountStatus.action?license="+accountId;
		text += "\nFor questions or concerns regarding your account, please contact us.\nThank you for using agm.ccic.com";
		String subject = "舞毒蛾检验系统注册激活信息";
		sendMail.send(text, client.getRegisterEmail(),subject);*/
		return SUCCESS;
	}
	
	/**
	 * 激活注册用户
	 * @return
	 * @throws AppException
	 */
	public String updateAccountStatus() throws AppException {
		//System.out.println("-license-"+license);
			List<Account> accountList = new ArrayList();
			try {
				accountList = registerService.getAccountByStatus();
			} catch (Exception e) {
				throw new AppException("激活注册用户1",e);
			}
			for(int i=0; i<accountList.size();i++){
				Account tmpAccount = accountList.get(i);
				String tmpName = MD5Util.getMD5String(tmpAccount.getAccountId());
				if(license.equals(tmpName)){
					try {
						registerService.updateAccountStatus(tmpAccount.getAccountId());
					} catch (Exception e) {
						throw new AppException("激活注册用户2",e);
					}
					return SUCCESS;
				}
			}
		return ERROR;
	}
	
	/**
	 * 忘记密码
	 * @return
	 * @throws AppException
	 */
	public String forgotPassword() throws AppException {
		
		Map session = ActionContext.getContext().getSession();
		
		/** 
		* 获得验证码
		*/
		String rand = (String) session.get("rand");
		
		if(validate!=null && rand!=null && !rand.toLowerCase().equals(validate.toLowerCase()))
		{
			message = "验证码输入有误！";
			session.remove("rand");
			 _logger.info(" vert is error");
			return ERROR;
		}
		
		List mailList = new ArrayList();
		Map tmpMap = new HashMap();
		try{
			mailList = registerService.getAccountMail(account.getAccountName().toLowerCase());
		} catch (Exception e) {
			throw new AppException("忘记密码",e);
		}
		if(mailList.size()>0){
			tmpMap = (Map)mailList.get(0);
			String accountId = (String)tmpMap.get("ACCOUNT_ID");
			accountId = MD5Util.getMD5String(accountId);
			SendMail sendMail = new SendMail();
			String text = "我们收到了重设与此电子邮件地址关联的密码的请求。 如果是您提交的请求，请遵照下面的指示操作。\n";
			text += "单击下面的链接通过我们的安全服务器重新设置您的密码：\n";
			text += "http://agmtest.ccic.com:9090/gypsymoth/page/register/updateAccountPassword.action?license="+accountId;
			text += "\n重置后的初始密码为111111，请您尽快登录系统修改密码。\n";
			text += "如果您没有请求重设密码，则完全可以忽略此邮件。请放心，您的客户帐户是安全的。";
			String subject = "重置密码";
			sendMail.send(text, (String)tmpMap.get("MAIL"), subject);
		}
			return SUCCESS;
	}
	
	/**
	 * 重置密码
	 * @return
	 * @throws AppException
	 */
	public String updateAccountPassword() throws AppException {
		//System.out.println("-license-"+license);
			List<Account> accountList = new ArrayList();
			try {
				accountList = registerService.getAllAccount();
			} catch (Exception e) {
				throw new AppException("重置密码1",e);
			}
			for(int i=0; i<accountList.size();i++){
				Account tmpAccount = accountList.get(i);
				String tmpName = MD5Util.getMD5String(tmpAccount.getAccountId());
				if(license.equals(tmpName)){
					try {
						accountService.updateAccountPassword(tmpAccount.getAccountId(), MD5Util.getMD5String("111111"));
					} catch (Exception e) {
						throw new AppException("重置密码2",e);
					}
					return SUCCESS;
				}
			}
		return ERROR;
	}

}
