package com.sinosoft.gypsymoth.bussiness.client.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sinosoft.gypsymoth.bussiness.client.service.ClientService;
import com.sinosoft.gypsymoth.bussiness.login.service.LoginService;
import com.sinosoft.gypsymoth.bussiness.register.service.RegisterService;
import com.sinosoft.gypsymoth.bussiness.system.service.AccountService;
import com.sinosoft.gypsymoth.exception.AppException;
import com.sinosoft.gypsymoth.pojo.Account;
import com.sinosoft.gypsymoth.pojo.AccountRole;
import com.sinosoft.gypsymoth.pojo.City;
import com.sinosoft.gypsymoth.pojo.Client;
import com.sinosoft.gypsymoth.pojo.Clients;
import com.sinosoft.gypsymoth.pojo.Nation;
import com.sinosoft.gypsymoth.pojo.Promary;
import com.sinosoft.gypsymoth.utils.CommonTool;
import com.sinosoft.gypsymoth.utils.Constants;
import com.sinosoft.gypsymoth.utils.MD5Util;
import com.sinosoft.gypsymoth.utils.Pagination;

public class ClientAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Logger _logger = Logger.getLogger(ClientAction.class);
	
	private static CommonTool commonDao;

	private String clientId;
	private String clientsId;
	private AccountService accountService;
	private RegisterService registerService;
	private String accountId;
	private String coCnName;
	private String coEnName;
	private String businessLicense;
	private String coProperty;
	private String legalPerson;
	private String tel;
	private String email;
	private String address;
	private Long post;
	private String registerEmail;
	private String onePersonName;
	private String onePersonTel;
	private Long onePersonPhone;
	private String onePersonFax;
	private String onePersonEmail;
	private String twoPersonName;
	private String twoPersonTel;
	private Long twoPersonPhone;
	private String twoPersonFax;
	private String twoPersonEmail;
	private String des;
	private Integer nationId;
	private Integer proid;
	private Integer cityid;
	private Client client;
	private Clients clients;
	private ClientService clientService;
	private String actionName;
	private Account account;
	private Promary promary;
	private City city;
	private Nation nat;
	private String[] idArray;
	private Long examinNum;
	private String message;
	
	private Integer nationId1;//中国
	private String address1;//中国地址
	private Integer nationId2;//其他国家
	private String address2;//其他国家地址
	private String changeAddress;//中国or其他国家
	
	private String cityids;
	private String cityname;
	private String bankaccount;
	private String vbrk;
	private String clientName;
	
	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public Nation getNat() {
		return nat;
	}

	public void setNat(Nation nat) {
		this.nat = nat;
	}

	public String getBankaccount() {
		return bankaccount;
	}

	public void setBankaccount(String bankaccount) {
		this.bankaccount = bankaccount;
	}

	public String getVbrk() {
		return vbrk;
	}

	public void setVbrk(String vbrk) {
		this.vbrk = vbrk;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getExaminNum() {
		return examinNum;
	}

	public void setExaminNum(Long examinNum) {
		this.examinNum = examinNum;
	}

	public static BigDecimal getSerialNo() {

		return commonDao.getDateSerialNo();
	}

	
	/**
	 * 保存注册用户
	 * @return
	 * @throws AppException
	 */
	public String saveClient() throws AppException {
		 
		HttpServletRequest request = ServletActionContext.getRequest();
		Map sessionmap = ActionContext.getContext().getSession();
		Account curr_account = (Account)sessionmap.get(Constants.ACCOUNT_SESSION);
		String account_type = request.getParameter("account_type");
		if (account==null) {
			account = new Account();
		}
		List namelist = new ArrayList();
		try{
			String sequ = "";
			if (account_type!=null&&account_type.equals("1")) {	//如果是用户申请,则用户名为当前用户名+随机数
				sequ = clientService.getClientsSequ();
				String temp_account_name = curr_account.getAccountName()+"_"+sequ;
				account.setAccountName(temp_account_name);
				account.setPassword(Constants.ACCOUNT_DEFAULT_PASSWORD);
			}
			
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
			account.setAccountStatus(1);
			account.setCreateBy(account.getAccountName());
			account.setCreateDate(createDate);
			account.setLoginLock(0);
			account.setLoginFailTimes(0);
			account.setPassword(MD5Util.getMD5String(account.getPassword()));
			//客户对象
			client.setClientName(account.getAccountName().toLowerCase());
			client.setAccountId(accountId);
			
			String bank1 = (String) request.getParameter("bank1");
			String bank2 = (String) request.getParameter("bank2");
			if(bank1 != null && bank1.equals("其他")) {
				client.setBank(bank2);
			}else {
				client.setBank(bank1);
			}
			
			if("1".equals(changeAddress)){
				client.setNationId(nationId1);
			}
			else if("2".equals(changeAddress)){
				client.setNationId(nationId2);
				client.setProid(-1);
				client.setCityid(-1);
			}
			Account accounts = account;
			Client clients = client;
			registerService.saveClient(account, client, accountRole);
		} catch (Exception e) {
			throw new AppException("保存注册用户",e); 
		}
		accountId = MD5Util.getMD5String(accountId);
		
//		SendMail sendMail = new SendMail();
//		sendMail.send("http://192.168.1.51:9090/gypsymoth/page/register/updateAccountStatus.action?license="+accountId, client.getRegisterEmail());
		return SUCCESS;
	}
	
	
	/**
	 * 保存客户添加的客户
	 * @return
	 * @throws AppException
	 */
	public String saveClients() throws AppException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Map sessionmap = ActionContext.getContext().getSession();
		Client curr_client = (Client)sessionmap.get(Constants.ACCOUNT_CLIENT);
		String account_id = getSerialNo().toString();
		try {
//			Client client = clientService.getClientByAccountID(account_id);
			Date createDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			createDate = sdf.parse(sdf.format(createDate));
			//客户对象
			
			String bank1 = (String) request.getParameter("bank1");
			String bank2 = (String) request.getParameter("bank2");
			if(bank1 != null && bank1.equals("其他")) {
				client.setBank(bank2);
			}else {
				client.setBank(bank1);
			}
			
			client.setClientName(curr_client.getClientName());
			client.setAccountId(account_id);
			client.setParentId(String.valueOf(curr_client.getClientId()));
			client.setIdentify("2");
			if("1".equals(changeAddress)){
//				client.setAddress(address1);
				client.setNationId(nationId1);
			}
			else if("2".equals(changeAddress)){
//				client.setAddress(address2);
				client.setNationId(nationId2);
			}
			clientService.saveClients(client);
		} catch (Exception e) {
			throw new AppException("保存客户的客户",e);
		}
		return SUCCESS;
	}
	

	
	/**
	 *添加客户
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String clientAdd() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Map sessionmap = ActionContext.getContext().getSession();
		Account account = (Account)sessionmap.get(Constants.ACCOUNT_SESSION);
		List nationList = new ArrayList();
		List promaryList = new ArrayList();
		try {
			nationList = clientService.getNation();
			promaryList = clientService.getPromory();
		} catch (Exception e) {
			throw new AppException("获取用户注册初始数据", e);
		}
		request.setAttribute("nationList", nationList);
		request.setAttribute("promaryList", promaryList);
		request.setAttribute("account_type", account.getAccountType());
		_logger.info("get client    addpage    success-------");
		return SUCCESS;
	}
	
	/**
	 *添加客户
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String clientsAdd() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		Map sessionmap = ActionContext.getContext().getSession();
		Account account = (Account)sessionmap.get(Constants.ACCOUNT_SESSION);
		Client curr_client = (Client)sessionmap.get(Constants.ACCOUNT_CLIENT);
//		String client_name = curr_client.getClientName();
		String sequ = clientService.getClientsSequ(); 
		String client_name = account.getAccountName()+"_"+sequ;
		request.setAttribute("client_name", client_name);
		List nationList = new ArrayList();
		List promaryList = new ArrayList();
		try {
			nationList = clientService.getNation();
			promaryList = clientService.getPromory();
		} catch (Exception e) {
			throw new AppException("获取用户注册初始数据", e);
		}
		request.setAttribute("nationList", nationList);
		request.setAttribute("promaryList", promaryList);

		
		_logger.info("get client  of client info      success-------");
		return SUCCESS;
	}
	
	/**
	 * 修改客户信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String updataC() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String clientId = request.getParameter("clientId");

		List nationList = new ArrayList();
		List promaryList = new ArrayList();
		try {
			nationList = clientService.getNation();
			promaryList = clientService.getPromory();
		} catch (Exception e) {
			throw new AppException("获取用户注册初始数据", e);
		}
		request.setAttribute("nationList", nationList);
		request.setAttribute("promaryList", promaryList);

		client = clientService.getClientById(Long.parseLong(clientId));
		
		String flag = "nohave";
		String banks[] = {"中国银行","中国工商银行","中国农业银行","中国建设银行","中国交通银行","中国光大银行","中信实业银行","招商银行"," ",
				"Bank of China, BOC","Industrial and Commercial Bank of China Limited, ICBC","The agriculture Bank of China, ABC","China Construction Bank, CCB","Communications Bank of China","Everbright Bank of China","CITIC Industrial Bank","China Merchants Bank"};
		for(int i = 0; i < banks.length; i ++) {
			if(client.getBank() != null && client.getBank().equals(banks[i])) {
				flag = "have";
				break;
			}
		}
		request.setAttribute("flag", flag);
		
		
		
		
		int nationvalue = client.getNationId();
		if (nationvalue != 37) {
			request.setAttribute("radiochecked", "2");
		}
		Account updata_account = clientService.getAccountByAccountID(client
				.getAccountId());
		account = updata_account;
		_logger.info("updata client  by page success-------");
		return SUCCESS; // 详情页面

	}
	
	
	/**
	 * 修改客户的客户信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String updataCs() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String clientId = request.getParameter("clientId");

		List nationList = new ArrayList();
		List promaryList = new ArrayList();
		try {
			nationList = clientService.getNation();
			promaryList = clientService.getPromory();
		} catch (Exception e) {
			throw new AppException("获取用户注册初始数据", e);
		}
		request.setAttribute("nationList", nationList);
		request.setAttribute("promaryList", promaryList);

		client = clientService.getClientById(Long.parseLong(clientId));
		
		
		String flag = "nohave";
		String banks[] = {"中国银行","中国工商银行","中国农业银行","中国建设银行","中国交通银行","中国光大银行","中信实业银行","招商银行"," ",
				"Bank of China, BOC","Industrial and Commercial Bank of China Limited, ICBC","The agriculture Bank of China, ABC","China Construction Bank, CCB","Communications Bank of China","Everbright Bank of China","CITIC Industrial Bank","China Merchants Bank"};
		for(int i = 0; i < banks.length; i ++) {
			if(client.getBank() != null && client.getBank().equals(banks[i])) {
				flag = "have";
				break;
			}
		}
		request.setAttribute("flag", flag);
		
		
		
		
		int nationvalue = client.getNationId();
		if (nationvalue != 37) {
			request.setAttribute("radiochecked", "2");
		}
		Account updata_account = clientService.getAccountByAccountID(client
				.getAccountId());
		account = updata_account;
		_logger.info("updata client  by page success-------");
		
		Map sessionmap = ActionContext.getContext().getSession();
		String is_admin = (String) sessionmap.get(Constants.ACCOUNT_ISADMIN);
		String parentId = request.getParameter("parentId");
		request.setAttribute("accounttype", is_admin);
		String client_name = null;
		Client curr_client = (Client)sessionmap.get(Constants.ACCOUNT_CLIENT);
		if(curr_client != null) {
		client_name = curr_client.getClientName();
		request.setAttribute("client_name", client_name);
		}else {
			Client clientp = clientService.getClientById(Long.parseLong(parentId));
			client_name = clientp.getClientName();
			request.setAttribute("client_name", client_name);
		}
		
		return SUCCESS; // 详情页面

	}
	/**
	 * 城市的ajax查询
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String ajaxSelect() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String promaryid = request.getParameter("promaryid");
		List cityList = null;
		try {
			if (promaryid != null) {
				cityList = registerService.getCity(Integer.valueOf(promaryid));
			}
			request.setAttribute("cityList", cityList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 城市的ajax查询
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String ajaxSelect1() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String promaryid = request.getParameter("promaryid");
		List cityList = null;
		try {
			if (promaryid != null) {
				cityList = registerService.getCity(Integer.valueOf(promaryid));
			}
			request.setAttribute("cityList", cityList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 修改客户信息
	 * 
	 * @return
	 * @throws AppException
	 */
	public String updataClient() throws AppException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String cityid = request.getParameter("cityid");
		
		String bank1 = (String) request.getParameter("bank1");
		String bank2 = (String) request.getParameter("bank2");
		if(bank1 != null && bank1.equals("其他")) {
			client.setBank(bank2);
		}else {
			client.setBank(bank1);
		}
		
		String radiochecked = request.getParameter("changeAddress");
		if (!cityid.equals(null)) {
			client.setCityid(Integer.valueOf(cityid));
		}
		try {
			if (radiochecked != null && radiochecked.equals("2")) {
				client.setProid(-1);
				client.setCityid(-1);
			}else if(radiochecked != null && radiochecked.equals("1")){
				client.setNationId(37);
			}
			clientService.updateClient(client);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		request.setAttribute("radiochecked", radiochecked);
		return SUCCESS;
	}
	
	
	/**
	 * 修改客户的客户信息
	 * 
	 * @return
	 * @throws AppException
	 */
	public String updataClients() throws AppException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String cityid = request.getParameter("cityid");
		String radiochecked = request.getParameter("changeAddress");
		String parentId = request.getParameter("parentId");
		String identify = request.getParameter("identify");
		
		
		String bank1 = (String) request.getParameter("bank1");
		String bank2 = (String) request.getParameter("bank2");
		if(bank1 != null && bank1.equals("其他")) {
			client.setBank(bank2);
		}else {
			client.setBank(bank1);
		}
		
		
		if (!cityid.equals(null)) {
			client.setCityid(Integer.valueOf(cityid));
		}
		try {
			if (radiochecked != null && radiochecked.equals("2")) {
				client.setProid(-1);
				client.setCityid(-1);
			}else if(radiochecked != null && radiochecked.equals("1")){
				client.setNationId(37);
			}
			Client clientss = client;
			clientService.updateClient(client);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		request.setAttribute("radiochecked", radiochecked);
		Map sessionmap = ActionContext.getContext().getSession();
		Account account = (Account) sessionmap.get(Constants.ACCOUNT_SESSION);
		Integer accounttype = account.getAccountType();
		
		if(accounttype == 1) {
			//getClientById
			System.out.println("----------------------------" + accounttype);
			clientId = client.getParentId(); 
			return "goToClient";
		}else {
			//getClientsByPage
			return SUCCESS;
		}	
	}

	
	/**
	 * 修改客户信息
	 * 
	 * @return
	 * @throws AppException
	 */
	public String updataClientS() throws AppException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String cityid = request.getParameter("cityid");
		String radiochecked = request.getParameter("changeAddress");
		
		String bank1 = (String) request.getParameter("bank1");
		String bank2 = (String) request.getParameter("bank2");
		if(bank1 != null && bank1.equals("其他")) {
			client.setBank(bank2);
		}else {
			client.setBank(bank1);
		}
		
		
		
		if (cityid!=null && !"".equals(cityid)) {
			client.setCityid(Integer.valueOf(cityid));
		}
		try {
			if (radiochecked != null && radiochecked.equals("2")) {
				client.setProid(-1);
				client.setCityid(-1);
			}else if(radiochecked != null && radiochecked.equals("1")){
				client.setNationId(37);
			}
			clientService.updateClient(client);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		request.setAttribute("radiochecked", radiochecked);
		return SUCCESS;
	}
	/**
	 * 删除客户
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteClientById() throws Exception {
		clientService.deleteClientById(Long.parseLong(clientId));
		_logger.info("delete client by Id success-------");
		return SUCCESS;
	}
	
	/**
	 * 删除客户的客户
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteClientsById() throws Exception {
		if(clientsId == null || clientsId.equals("")) {
			clientsId = clientId;
		}
		clientService.deleteClientById(Long.parseLong(clientsId));
		_logger.info("delete clients by Id success-------");
		HttpServletRequest request = ServletActionContext.getRequest();
		String parentId = request.getParameter("parentId");
		request.setAttribute("clientId", parentId);
		
		Map sessionmap = ActionContext.getContext().getSession();
		Account account = (Account) sessionmap.get(Constants.ACCOUNT_SESSION);
		Integer accounttype = account.getAccountType();
		
		if(accounttype == 1) {
			//getClientById 
			clientId = parentId;
			return "child";
		}else {
			//getClientsByPage
			return SUCCESS;
		}	

	}


	
	/**
	 * 修改客户信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String updataCS() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String clientId = request.getParameter("clientId");

		List nationList = new ArrayList();
		List promaryList = new ArrayList();
		try {
			nationList = clientService.getNation();
			promaryList = clientService.getPromory();
		} catch (Exception e) {
			throw new AppException("获取用户注册初始数据", e);
		}
		request.setAttribute("nationList", nationList);
		request.setAttribute("promaryList", promaryList);

		client = clientService.getClientById(Long.parseLong(clientId));
		
		
		String flag = "nohave";
		String banks[] = {"中国银行","中国工商银行","中国农业银行","中国建设银行","中国交通银行","中国光大银行","中信实业银行","招商银行"," ",
				"Bank of China, BOC","Industrial and Commercial Bank of China Limited, ICBC","The agriculture Bank of China, ABC","China Construction Bank, CCB","Communications Bank of China","Everbright Bank of China","CITIC Industrial Bank","China Merchants Bank"};
		for(int i = 0; i < banks.length; i ++) {
			if(client.getBank() != null && client.getBank().equals(banks[i])) {
				flag = "have";
				break;
			}
		}
		request.setAttribute("flag", flag);
		
		
		
		int nationvalue = client.getNationId();
		if (nationvalue != 37) {
			request.setAttribute("radiochecked", "2");
		}
		Account updata_account = clientService.getAccountByAccountID(client
				.getAccountId());
		account = updata_account;
		_logger.info("updata client  by page success-------");
		return SUCCESS; // 详情页面

	}

	/**
	 * 获得客户注册信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String getClientById() throws Exception {
		@SuppressWarnings("unused")
		HttpServletRequest request = ServletActionContext.getRequest();
		List nationList = new ArrayList();
		List promaryList = new ArrayList();
		try {
			nationList = clientService.getNation();
			promaryList = clientService.getPromory();
		} catch (Exception e) {
			throw new AppException("获取用户注册初始数据", e);
		}
		request.setAttribute("nationList", nationList);// 获得国家
		request.setAttribute("promaryList", promaryList);// 获得省
//		clientId = clientService.getclientbyaccountid(accountId);
		
		client = clientService.getClientById(Long.parseLong(clientId));
		
		String flag = "nohave";
		String banks[] = {"中国银行","中国工商银行","中国农业银行","中国建设银行","中国交通银行","中国光大银行","中信实业银行","招商银行"," ",
				"Bank of China, BOC","Industrial and Commercial Bank of China Limited, ICBC","The agriculture Bank of China, ABC","China Construction Bank, CCB","Communications Bank of China","Everbright Bank of China","CITIC Industrial Bank","China Merchants Bank"};
		for(int i = 0; i < banks.length; i ++) {
			if(client.getBank() != null && client.getBank().equals(banks[i])) {
				flag = "have";
				break;
			}
		}
		request.setAttribute("flag", flag);
		
		
		int nationvalue = client.getNationId();
		if (nationvalue != 37) {
			request.setAttribute("radiochecked", "2");
		}
		Account updata_account = clientService.getAccountByAccountID(client
				.getAccountId());
		account = updata_account;
		_logger.info("get all client  by page success-------");
		return SUCCESS; // 详情页面
	}
	
	/**
	 * 获得客户的客户  注册信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String getClientsById() throws Exception {
		@SuppressWarnings("unused")
		HttpServletRequest request = ServletActionContext.getRequest();
		List nationList = new ArrayList();
		List promaryList = new ArrayList();
		try {
			nationList = clientService.getNation();
			promaryList = clientService.getPromory();
		} catch (Exception e) {
			throw new AppException("获取用户注册初始数据", e);
		}
		request.setAttribute("nationList", nationList);// 获得国家
		request.setAttribute("promaryList", promaryList);// 获得省
//		clientId = clientService.getclientbyaccountid(accountId);
		client = clientService.getClientById(Long.parseLong(clientsId));
		
		String flag = "nohave";
		String banks[] = {"中国银行","中国工商银行","中国农业银行","中国建设银行","中国交通银行","中国光大银行","中信实业银行","招商银行"," ",
				"Bank of China, BOC","Industrial and Commercial Bank of China Limited, ICBC","The agriculture Bank of China, ABC","China Construction Bank, CCB","Communications Bank of China","Everbright Bank of China","CITIC Industrial Bank","China Merchants Bank"};
		for(int i = 0; i < banks.length; i ++) {
			if(client.getBank() != null && client.getBank().equals(banks[i])) {
				flag = "have";
				break;
			}
		}
		request.setAttribute("flag", flag);
		
		
		
		String parentId = request.getParameter("parentId");
		
		int nationvalue = client.getNationId();
		if (nationvalue != 37) {
			request.setAttribute("radiochecked", "2");
		}
//		Account updata_account = clientService.getAccountByAccountID(client
//				.getAccountId());
//		account = updata_account;
		_logger.info("get all client  by page success-------");
		
		Map sessionmap = ActionContext.getContext().getSession();
		String client_name = null;
		Client curr_client = (Client)sessionmap.get(Constants.ACCOUNT_CLIENT);
		if(curr_client != null) {
		client_name = curr_client.getClientName();
		request.setAttribute("client_name", client_name);
		}else {
			Client clientp = clientService.getClientById(Long.parseLong(parentId));
			client_name = clientp.getClientName();
			System.out.println("===============" + client_name);
			request.setAttribute("client_name", client_name);
		}
		return SUCCESS; // 详情页面
	}

	/**
	 * ajax根据省份获取城市列表
	 * 
	 * @return
	 * @throws AppException
	 */
	@SuppressWarnings("unchecked")
	public String getCityfor() throws AppException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Integer proId = Integer.valueOf(request.getParameter("input"));
		List cityList = new ArrayList();
		try {
			cityList = clientService.getCity(proId);
		} catch (Exception e) {
			throw new AppException("获取城市列表", e);
		}
		request.setAttribute("cityList", cityList);
		_logger.info("get all city by list success-------");
		return SUCCESS;
	}
	
	/**
	 * ajax根据省份获取城市列表(客户的客户)
	 * 
	 * @return
	 * @throws AppException
	 */
	@SuppressWarnings("unchecked")
	public String getCitys() throws AppException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Integer proId = Integer.valueOf(request.getParameter("input"));
		List cityList = new ArrayList();
		try {
			cityList = clientService.getCity(proId);
		} catch (Exception e) {
			throw new AppException("获取城市列表", e);
		}
		request.setAttribute("cityList", cityList);
		_logger.info("get all city by list success-------");
		return SUCCESS;
	}

	/**
	 * 由ID得到客户信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getClientInfoById() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		if(clientId == null || clientId.equals("")) {
			clientId = (String)request.getAttribute("clientId");
		}
		
		client = clientService.getClientById(Long.parseLong(clientId));
			
		Promary pro = clientService.getPromoryById(Long.valueOf(client.getProid()));
		promary = pro;
		List citylist = clientService.getCityByPromaryID(clientId);
		
		if(citylist.size()>0){
			Map map = (Map)citylist.get(0);
		   cityname =   map.get("CITYNAME").toString();
		}else{
			cityname = "";
		}
		List list = clientService.getChild(client);
		request.setAttribute("list", list);
		
		Account updata_account = clientService.getAccountByAccountID(client
				.getAccountId());
		account = updata_account;
		_logger.info("get all client by page success-------");
		return SUCCESS; // 详情页面
	}
	
	

	/**
	 * 由ID得到客户信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getClientsInfoById() throws Exception {
		client = clientService.getClientById(Long.parseLong(clientsId));
		Promary pro = clientService.getPromoryById(Long.valueOf(client.getProid()));
		promary = pro;
		List citylist = clientService.getCityByPromaryID(clientsId);
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String parentId = client.getParentId();
		request.setAttribute("parentId", parentId);
		
		if(citylist.size()>0){
			Map map = (Map)citylist.get(0);
		   cityname =   map.get("CITYNAME").toString();
		}else{
			cityname = "";
		}
		
//		Integer cityid = Integer.parseInt(cityids);
//	
//	    city = clientService.getCityById(Long.valueOf(cityids));
//		
		Account updata_account = clientService.getAccountByAccountID(client
				.getAccountId());
		account = updata_account;
		_logger.info("get all client by page success-------");
		
		Map sessionmap = ActionContext.getContext().getSession();
		String is_admin = (String) sessionmap.get(Constants.ACCOUNT_ISADMIN);
		
		request.setAttribute("accounttype", is_admin);
		String client_name = null;
		Client curr_client = (Client)sessionmap.get(Constants.ACCOUNT_CLIENT);
		if(curr_client != null) {
		client_name = curr_client.getClientName();
		request.setAttribute("client_name", client_name);
		}else {
			Client clientp = clientService.getClientById(Long.parseLong(parentId));
			client_name = clientp.getClientName();
			request.setAttribute("client_name", client_name);
		}
		return SUCCESS; // 详情页面
	}
	/**
	 * 由AccountId得到客户信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getClientInfoByIds() throws Exception{
	Map session = ActionContext.getContext().getSession();
	Account account = (Account) session.get(Constants.ACCOUNT_SESSION);
		String accountId = account.getAccountId();
		try {
			List list = clientService.getclientIdbyAccountId(accountId);
			if(list.size()>0){
				Map map = (Map)list.get(0);
				clientId = map.get("CLIENT_ID").toString();
			}
			else{
				clientId = "0";
			}
		} catch (Exception e) {
			throw new AppException("获得client信息", e);
		}
		
		client = clientService.getClientById(Long.parseLong(clientId));
		Promary pro = clientService.getPromoryById(Long.valueOf(client.getProid()));
		promary = pro;
		System.out.println("---------"+client.getNationId());
		Nation nation = clientService.getNationById(client.getNationId().toString());
		nat = nation;
		List citylist = clientService.getCityByPromaryID(clientId);
		
		if(citylist.size()>0){
			Map map = (Map)citylist.get(0);
		   cityname =   map.get("CITYNAME").toString();
		}else{
			cityname = "";
		}
		Account updata_account = clientService.getAccountByAccountID(client
				.getAccountId());
		account = updata_account;
		_logger.info("get all client by page success-------");
		
		return SUCCESS; // 详情页面
	}
	
	/**
	 * 用户客户分类查看
	 * @return
	 * @throws AppException
	 */
	public String selectClient() throws AppException{
		Map session = ActionContext.getContext().getSession();
		Account account = (Account)session.get(Constants.ACCOUNT_SESSION);
		if(account.getAccountType()==1){
			return "getClientByPage";
		}
		else{
			return "getClientsByPage";
		}
	}
	

	/**
	 * 分页查询客户(用户登录)
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getClientByPage() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String coCnName_search = request.getParameter("coCnName_search");
		String coEnName_search = request.getParameter("coEnName_search");
		//新增： 客户 按用户名查询 lxz
		String clientName = request.getParameter("clientName");
		//查询的时候不区分大小写
		//coCnName_search = coCnName_search.toUpperCase();
		/*if(coEnName_search != null && clientName != null) {
			coEnName_search = coEnName_search.toUpperCase();
			clientName = clientName.toUpperCase();
		}*/
		// 分页配置
		Pagination p = new Pagination(0, 0, 0);
		String currPage = (String) request.getParameter("goPage");
		
		if (currPage == null) {
			currPage = "1";// ” currPage”是当前页数
		}
		HttpSession session = request.getSession();
		session.setAttribute("goPage1", currPage);
		
		_logger.info("currPage:" + currPage);
		Integer currPage1 = Integer.parseInt(currPage);
		HashMap map = new HashMap();
		map.put("coCnName_search", coCnName_search);
		if(coEnName_search != null) {
			map.put("coEnName_search", coEnName_search.toUpperCase());
			
		}else {
			map.put("coEnName_search", coEnName_search);
		}
		if(clientName != null) {
			//新增： 客户 按用户名查询
			map.put("clientName", clientName.toUpperCase());
		}else {
			//新增： 客户 按用户名查询
			map.put("clientName", clientName);
		}
		try {
			String nationId = request.getParameter("nationId");
			if (nationId != null) {
				@SuppressWarnings("unused")
//				Integer nationIdionIdtionId = client.getNationId();
				Nation nation = clientService.getNationById(nationId);
				client.setAddress(nation.getNationName());
			}

			Integer totleCount = clientService.getAllDataCountss(map);
			p.getPagination(request, totleCount, currPage1, null, null, null);
			int numOfEachPage = Constants.NUMOFEACHPAGE;
			int begin = p.getRownum_begin(numOfEachPage, currPage1);
			List<Client> list = clientService.getClientByPages(begin, numOfEachPage, map);
			request.setAttribute("clientlist", list);


			_logger.info("get all client by page success-------");
		} catch (Exception e) {
			throw new AppException("分页查询角色", e);
		}
		this.actionName = "getClientByPage";
		request.setAttribute("coCnName_search",coCnName_search);
		request.setAttribute("coEnName_search",coEnName_search);
		request.setAttribute("clientName",clientName);
		return SUCCESS;
	}
	
	/**
	 * 分页查询客户(客户登陆)
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getClientsByPage() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String coCnName_search = request.getParameter("coCnName_search");
		String coEnName_search = request.getParameter("coEnName_search");
		Map sessionmap = ActionContext.getContext().getSession();
		Account account = (Account) sessionmap.get(Constants.ACCOUNT_SESSION);
		Integer accounttype = account.getAccountType();
		
		// 分页配置
		Pagination p = new Pagination(0, 0, 0);
		String currPage = (String) request.getParameter("goPage");
		if (currPage == null) {
			currPage = "1";// ” currPage”是当前页数
		}
		_logger.info("currPage:" + currPage);
		Integer currPage1 = Integer.parseInt(currPage);
		HashMap map = new HashMap();
		map.put("coCnName_search", coCnName_search);
		map.put("coEnName_search", coEnName_search);
		if(accounttype==1){
		map.put("parent_Id", String.valueOf(client.getParentId()));
		}
		else
		{
			
			Client curr_client = (Client)sessionmap.get(Constants.ACCOUNT_CLIENT);
			Long parentId = curr_client.getClientId();
			map.put("parent_Id", String.valueOf(parentId));
		}
		
		try {
			String nationId = request.getParameter("nationId");
			if (nationId != null) {
				@SuppressWarnings("unused")
//				Integer nationIdionIdtionId = client.getNationId();
				Nation nation = clientService.getNationById(nationId);
				client.setAddress(nation.getNationName());
			}

			Integer totleCount = clientService.getAllData(map);
			p.getPagination(request, totleCount, currPage1, null, null, null);
			int numOfEachPage = Constants.NUMOFEACHPAGE;
			int begin = p.getRownum_begin(numOfEachPage, currPage1);
			List<Client> list = clientService.getClientByPage(begin, numOfEachPage, map);
			request.setAttribute("clientlist", list);
			request.setAttribute("client_name", clientName);

			_logger.info("get all client by page success-------");
		} catch (Exception e) {
			throw new AppException("分页查询角色", e);
		}
		this.actionName = "getClientsByPage";
		return SUCCESS;
	}

	/**
	 * 批量删除客户
	 * 
	 * @return
	 * @throws AppException
	 */
	public String delete() throws AppException {
		String idstr = "";
		for (int i = 0; i < idArray.length; i++) {
			idstr = idstr + idArray[i] + ",";
			try {
				clientService.deleteClient(idArray[i]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			_logger.info("delete client success-------");
		}
		_logger.info("update batch success");
		return SUCCESS;
	}
	
	

	public ClientService getClientService() {
		return clientService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getCoCnName() {
		return coCnName;
	}

	public void setCoCnName(String coCnName) {
		this.coCnName = coCnName;
	}

	public String getCoEnName() {
		return coEnName;
	}

	public void setCoEnName(String coEnName) {
		this.coEnName = coEnName;
	}

	public String getBusinessLicense() {
		return businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}

	public String getCoProperty() {
		return coProperty;
	}

	public void setCoProperty(String coProperty) {
		this.coProperty = coProperty;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getPost() {
		return post;
	}

	public void setPost(Long post) {
		this.post = post;
	}

	public String getRegisterEmail() {
		return registerEmail;
	}

	public void setRegisterEmail(String registerEmail) {
		this.registerEmail = registerEmail;
	}

	public String getOnePersonName() {
		return onePersonName;
	}

	public void setOnePersonName(String onePersonName) {
		this.onePersonName = onePersonName;
	}

	public String getOnePersonTel() {
		return onePersonTel;
	}

	public void setOnePersonTel(String onePersonTel) {
		this.onePersonTel = onePersonTel;
	}

	public Long getOnePersonPhone() {
		return onePersonPhone;
	}

	public void setOnePersonPhone(Long onePersonPhone) {
		this.onePersonPhone = onePersonPhone;
	}

	public String getOnePersonFax() {
		return onePersonFax;
	}

	public void setOnePersonFax(String onePersonFax) {
		this.onePersonFax = onePersonFax;
	}

	public String getOnePersonEmail() {
		return onePersonEmail;
	}

	public void setOnePersonEmail(String onePersonEmail) {
		this.onePersonEmail = onePersonEmail;
	}

	public String getTwoPersonName() {
		return twoPersonName;
	}

	public void setTwoPersonName(String twoPersonName) {
		this.twoPersonName = twoPersonName;
	}

	public String getTwoPersonTel() {
		return twoPersonTel;
	}

	public void setTwoPersonTel(String twoPersonTel) {
		this.twoPersonTel = twoPersonTel;
	}

	public Long getTwoPersonPhone() {
		return twoPersonPhone;
	}

	public void setTwoPersonPhone(Long twoPersonPhone) {
		this.twoPersonPhone = twoPersonPhone;
	}

	public String getTwoPersonFax() {
		return twoPersonFax;
	}

	public void setTwoPersonFax(String twoPersonFax) {
		this.twoPersonFax = twoPersonFax;
	}

	public String getTwoPersonEmail() {
		return twoPersonEmail;
	}

	public void setTwoPersonEmail(String twoPersonEmail) {
		this.twoPersonEmail = twoPersonEmail;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Integer getNationId() {
		return nationId;
	}

	public void setNationId(Integer nationId) {
		this.nationId = nationId;
	}

	public Integer getProid() {
		return proid;
	}

	public void setProid(Integer proid) {
		this.proid = proid;
	}



	public Logger get_logger() {
		return _logger;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public String[] getIdArray() {
		return idArray;
	}

	public void setIdArray(String[] idArray) {
		this.idArray = idArray;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public RegisterService getRegisterService() {
		return registerService;
	}

	public void setRegisterService(RegisterService registerService) {
		this.registerService = registerService;
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


	public CommonTool getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonTool commonDao) {
		this.commonDao = commonDao;
	}

	public Clients getClients() {
		return clients;
	}

	public void setClients(Clients clients) {
		this.clients = clients;
	}




	public String getClientsId() {
		return clientsId;
	}

	public void setClientsId(String clientsId) {
		this.clientsId = clientsId;
	}

	public Promary getPromary() {
		return promary;
	}

	public void setPromary(Promary promary) {
		this.promary = promary;
	}
	

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Integer getCityid() {
		return cityid;
	}

	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}

	public String getCityids() {
		return cityids;
	}

	public void setCityids(String cityids) {
		this.cityids = cityids;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}



}
