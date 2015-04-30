package com.sinosoft.gypsymoth.bussiness.login.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sinosoft.gypsymoth.bussiness.login.service.LoginService;
import com.sinosoft.gypsymoth.pojo.Account;
import com.sinosoft.gypsymoth.pojo.Client;
import com.sinosoft.gypsymoth.pojo.Person;
import com.sinosoft.gypsymoth.pojo.Right;
import com.sinosoft.gypsymoth.utils.CommonTool;
import com.sinosoft.gypsymoth.utils.Constants;
import com.sinosoft.gypsymoth.utils.MD5Util;
import com.sinosoft.gypsymoth.utils.SessionManager;

/**
 * 登陆、权限、注销
 * @author lixin
 *
 */
public class LoginAction extends ActionSupport {
	
	private final Logger _logger = Logger.getLogger(LoginAction.class);
	private LoginService loginService;
	private String accountName;
	private String password;
	private String validate;//验证码
	private Account account;
	private String right_id;
	private static int LOGIN_FAIL_TIMES = 0;// 登录失败次数
	private static List<Account> LOGIN_LIST = new ArrayList();
	private static CommonTool commonDao;
	private String message;//错误信息
	
	/**
	 * 登陆
	 * @return
	 * @throws Exception
	 */
	public String login() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
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
		
		if (session.get(Constants.ACCOUNT_SESSION) != null && // 如果有用户正在登录，先将该用户退出
				!"".equals(session.get(Constants.ACCOUNT_SESSION))) {
			SessionManager.closeLoginSession(session);
		}
		if(password!=null && !"".equals(password)){
			password = MD5Util.getMD5String(password.trim());
		}
		List<Account> accountlist = loginService.getAccountByName(accountName.trim().toLowerCase());
		if(accountlist.size()==0){//用户名错误
			message = "用户名不正确！";
			return ERROR;
		}
		else{
			account = accountlist.get(0);
			String realpassword = account.getPassword();
			if(account.getLoginLock()==0){//账号未锁定
				if(!password.equals(realpassword)){//密码错误
					if(account.getLoginFailTimes()!=null){
						if(account.getLoginFailTimes() >= 5){
							account.setLoginFailTimes(account.getLoginFailTimes()+1);
							account.setLoginLock(1);
							account.setLockDate(new Date());
							_logger.info(account.getAccountName()+" is lock!");
							message = "连续登录失败6次，此账号当天已锁定，请联系管理员！";
						}
						else{
							account.setLoginFailTimes(account.getLoginFailTimes()+1);
							message = "密码不正确！";
						}
					}else{
						account.setLoginFailTimes(1);
						message = "密码不正确！";
					}
					account.setLastLoginFail(new Date());
					loginService.updateAccount(account);
					return ERROR;
				}else{//登录成功
					if(account.getAccountType()==1){
						Locale currentLocale = new Locale("zh","CN");
						request.getSession().setAttribute("WW_TRANS_I18N_LOCALE",currentLocale);
					}
					account.setLastLoginDate(new Date());
					account.setLoginFailTimes(0);
					loginService.updateAccount(account);
					List<Right> rightList = loginService.getRightByAccountId(account.getAccountId(),"0");
					List<Right> tmpRightList = rightList;
					List<Right> rightList2 = new ArrayList();
					for(int i=0;i<rightList.size();i++){
						Right tmpRight1 = rightList.get(i);
						int a=0;
						for(int j=0;j<tmpRightList.size();j++){
							Right tmpRight2 = rightList.get(j);
							if(tmpRight1.getIschild()==0 && tmpRight2.getParentid().equals(tmpRight1.getRightId())){
								a++;
								if(a==1){
									tmpRight1.setResourceUrl(tmpRight2.getResourceUrl());
								}
							}
						}
						rightList2.add(tmpRight1);
					}
					session.put(Constants.ACCOUNT_SESSION,account);
					session.put(Constants.RIGHT_SESSION, rightList2);
					initSession(request, session);
					_logger.info(account.getAccountName()+" login success!");
					_logger.info(account.getAccountName()+" get right success!");
					return SUCCESS;
				}
			}else{//账号已锁定
				Date lockDate = account.getLockDate();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				int date1=Integer.parseInt(sdf.format(lockDate));
				int date2=Integer.parseInt(sdf.format(new Date()));
				if(date2>date1){//可解锁
					account.setLoginFailTimes(0);
					account.setLoginLock(0);
					if(!password.equals(realpassword)){//密码错误
						account.setLoginFailTimes(1);
						account.setLastLoginFail(new Date());
						loginService.updateAccount(account);
						message = "密码不正确！";
						return ERROR;
					}
					else{//登录成功
						if(account.getAccountType()==1){
							Locale currentLocale = new Locale("zh","CN");
							request.getSession().setAttribute("WW_TRANS_I18N_LOCALE",currentLocale);
						}
						account.setLastLoginDate(new Date());
						loginService.updateAccount(account);
						List<Right> rightList = loginService.getRightByAccountId(account.getAccountId(),"0");
						List<Right> tmpRightList = rightList;
						List<Right> rightList2 = new ArrayList();
						for(int i=0;i<rightList.size();i++){
							Right tmpRight1 = rightList.get(i);
							int a=0;
							for(int j=0;j<tmpRightList.size();j++){
								Right tmpRight2 = rightList.get(j);
								if(tmpRight1.getIschild()==0 && tmpRight2.getParentid().equals(tmpRight1.getRightId())){
									a++;
									if(a==1){
										tmpRight1.setResourceUrl(tmpRight2.getResourceUrl());
									}
								}
							}
							rightList2.add(tmpRight1);
						}
						session.put(Constants.ACCOUNT_SESSION,account);
						session.put(Constants.RIGHT_SESSION, rightList2);
						initSession(request, session);
						_logger.info(account.getAccountName()+" login success!");
						_logger.info(account.getAccountName()+" get right success!");
						return SUCCESS;
					}
				}
				else{//不可解锁
					message = "此账号当日处于锁定状态！";
					return ERROR;
				}
			}
		}
	}
	
	public String getMenu() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Map session = ActionContext.getContext().getSession();
		account = (Account)session.get("account");
		String rightId = (String)request.getAttribute("rightId");
		List<Right> rightList = loginService.getRightByAccountId(account.getAccountId(),rightId);
		request.setAttribute("childRightList", rightList);
		return SUCCESS;
		
	}
	
	public String getLeftRight(){
		/**
		 * 获取二级权限
		 */
		String returnValue = SessionManager.sessionRightId(right_id);
		if("noRight".equals(returnValue)){
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String logout() {
		Map session = ActionContext.getContext().getSession();
		SessionManager.closeLoginSession(session);
		return SUCCESS;
	
		
	}
	
	public String language(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String returnType = (String)request.getParameter("type");
		Map session = ActionContext.getContext().getSession();
		session.remove("right_id");
		if(returnType!=null && "index".equals(returnType)){
			return "index";
		}
		else if(returnType!=null && "pass".equals(returnType)){
			return "password";
		}
		else{
			return SUCCESS;
		}
	}

	
	/**
	 * 初始化session信息
	 * @param HttpServletRequest request
	 * @param Map session
	 */
	public void initSession(HttpServletRequest request,Map session)
	{
		Integer accounttype = account.getAccountType();
		String is_admin = "0";
		String person_id  =  "";
		String is_child = "";
		String person_org = "";
		String orgid = "";
		String org_name = "";
		String account_id = account.getAccountId();
		try {
			if(accounttype==1){		//person用户
				Person person = loginService.getPersonByAccountID(account_id);
				List orglist = loginService.getInfomationByPersonID(person.getPersonId());
				if (orglist!=null&&orglist.size()>0) {
					HashMap orgmap  = (HashMap)orglist.get(0);
					person_id = (String)orgmap.get("PERSON_ID");
					is_child = (String)orgmap.get("IS_CHILD");
					person_org = (String)orgmap.get("ORG_ID");	//主键ID
					orgid = (String) orgmap.get("ORGID");	//4位的ID
					org_name = (String) orgmap.get("ORG_NAME");//机构简称
					
					for (int i = 0; i < orglist.size(); i++) {
						HashMap rolemap = (HashMap)orglist.get(i);
						String roleid = (String)rolemap.get("ROLE_ID"); 
						
						if(roleid.equals("1")){	//用户是否具有超级管理员角色
							is_admin = "1";
							break	; 
						}
					} 
				}
				Integer is_coordinator = 0;	//协调员
				Integer is_authorized = 0;	//授权签字人
				Integer is_inspector = 0;	//检察员
				String allotright = "";
				is_coordinator  = person.getIsCoordinator();
				is_authorized = person.getIsAuthorized();
				is_inspector = person.getIsInspector();
				if(is_inspector==1){
					allotright = "0";
				}
				if(is_authorized==1){
					allotright = "1";			
				}
				if(is_coordinator==1){
					allotright = "2";
				}
				 
				
				session.put(Constants.ACCOUNT_PERSON, person);	//用户信息
				session.put(Constants.ACCOUNT_PERSON_ID,  person.getPersonId());	//用户ID
				session.put(Constants.ACCOUNT_PERSON_IS_CHILD, is_child);	//用户所在机构等级
				session.put(Constants.ACCOUNT_ISADMIN, is_admin);	//是否是管理员权限
				session.put(Constants.ACCOUNT_PERSON_ORG, person_org); 	//用户所属机构	唯一的机构标示
				session.put(Constants.ACCOUNT_ORG_ID4, orgid);	//用户所属机构4位编码 不唯一的机构标示
				session.put(Constants.ACCOUNT_ALLOT_LEVEL, allotright);
				session.put(Constants.ACCOUNT_PERSON_ORGNAME, org_name);//用户所属机构简称
			}else if (accounttype==2) {	//客户
				Client client = loginService.getClientByAccountID(account_id);
				session.put(Constants.ACCOUNT_CLIENT, client);	//用户信息
				session.put(Constants.ACCOUNT_ISADMIN, is_admin);	//是否是管理员权限
				
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public static CommonTool getCommonDao() {
		return commonDao;
	}

	public static void setCommonDao(CommonTool commonDao) {
		LoginAction.commonDao = commonDao;
	}

	public String getRight_id() {
		return right_id;
	}

	public void setRight_id(String right_id) {
		this.right_id = right_id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
