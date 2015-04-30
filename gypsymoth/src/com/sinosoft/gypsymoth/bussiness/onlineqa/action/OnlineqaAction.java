package com.sinosoft.gypsymoth.bussiness.onlineqa.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sinosoft.gypsymoth.bussiness.login.action.LoginAction;
import com.sinosoft.gypsymoth.bussiness.login.service.LoginService;
import com.sinosoft.gypsymoth.bussiness.onlineqa.service.OnlineqaService;
import com.sinosoft.gypsymoth.exception.AppException;
import com.sinosoft.gypsymoth.pojo.Account;
import com.sinosoft.gypsymoth.pojo.AccountRole;
import com.sinosoft.gypsymoth.pojo.Onlineqa;
import com.sinosoft.gypsymoth.utils.Constants;
import com.sinosoft.gypsymoth.utils.Pagination;

public class OnlineqaAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Logger _logger = Logger.getLogger(LoginAction.class);

	private Long id;
	private String clientname;
	private Long accountid;
	private String title;
	private String content;
	private String remark;
	private Date time;
	private Date firsttime;
	private Date lasttime;
	private String state;
	private String namefor;
	private String actionName;
	private LoginService loginService;
	private OnlineqaService onlineqaService;
	private Onlineqa onlineqa;
	private String[] idArray;

	/**
	 * 根据不同角色进入不同在线答疑界面
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String selectRole() throws Exception {
		Map session = ActionContext.getContext().getSession();
		Account account = (Account) session.get(Constants.ACCOUNT_SESSION);
		String accountId = account.getAccountId();
		List<AccountRole> accountRoleList = (List) onlineqaService
				.getAccountRole(accountId);
		int accountType = account.getAccountType();
		if (accountType == 1) {
			if (accountRoleList.size() > 0) {
				for (int i = 0; i < accountRoleList.size(); i++) {
					AccountRole accountRole = accountRoleList.get(i);
					String roleId = accountRole.getRoleId();

					if ("1".equals(roleId)) {
						return "adminSuccess";
					} else {
						return "personSuccess";
					}
				}
			}
		} else {
			return "clientSuccess";
		}
		return ERROR;
	}

	/**
	 * 获得指定回答人(公司名称)
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String onlineqaSclect() throws Exception {
		@SuppressWarnings("unused")
		List promaryList = new ArrayList();
		try {
			promaryList = onlineqaService.getPromory();
		} catch (Exception e) {
			throw new AppException("获取指定回答人信息", e);
		}
		_logger.info("get person    onlineqainfo    success-------");
		return SUCCESS;
	}

	/**
	 * Admin 提问保存问题
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String save() throws Exception {
		Map session = ActionContext.getContext().getSession();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date createDate = new Date();
		createDate = sdf.parse(sdf.format(createDate));
		onlineqa.setClientname(((Account) session
				.get(Constants.ACCOUNT_SESSION)).getAccountName());
		onlineqa.setTime(createDate);
		onlineqa.setState("1");
		onlineqaService.save(onlineqa);
		_logger.info("save admin  onlineqa info   success-------");
		return SUCCESS;
	}

	/**
	 * 用户提问 保存问题
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String saveperson() throws Exception {
		Map session = ActionContext.getContext().getSession();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date createDate = new Date();
		createDate = sdf.parse(sdf.format(createDate));
		onlineqa.setClientname(((Account) session
				.get(Constants.ACCOUNT_SESSION)).getAccountName());
		onlineqa.setTime(createDate);
		onlineqa.setState("1");
		onlineqaService.save(onlineqa);
		_logger.info("save client onlineqa info   success-------");
		return SUCCESS;
	}
	
	/**
	 * 客户提问 保存问题
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String saveclient() throws Exception {
		Map session = ActionContext.getContext().getSession();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date createDate = new Date();
		createDate = sdf.parse(sdf.format(createDate));
		onlineqa.setClientname(((Account) session
				.get(Constants.ACCOUNT_SESSION)).getAccountName());
		onlineqa.setTime(createDate);
		onlineqa.setState("1");
		onlineqaService.save(onlineqa);
		_logger.info("save client onlineqa  info   success-------");
		return SUCCESS;
	}

	/**
	 * 
	 * Admin分页显示所有信息
	 */
	public String getAllDataByPage() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		// 分页配置
		Pagination p = new Pagination(0, 0, 0);
		String currPage = (String) request.getParameter("goPage");
		if (currPage == null) {
			currPage = "1";// ” currPage”是当前页数
		}
		_logger.info("currPage:" + currPage);
		Integer currPage1 = Integer.parseInt(currPage);

		try {

			Onlineqa onlineqa = new Onlineqa();
			// if(time!=null){
			//				
			//			
			// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			// // onlineqa.setTime(time);
			// onlineqa.setTime(sdf.parse(sdf.format(time)));
			// }
			System.out.print("====================" + clientname);

			onlineqa.setState(state);
			onlineqa.setTitle(title);
			onlineqa.setClientname(clientname);
			Integer totleCount = onlineqaService.getAllDataCounts(onlineqa);
			p.getPagination(request, totleCount, currPage1, null, null, null);
			int numOfEachPage = Constants.NUMOFEACHPAGE;
			int begin = p.getRownum_begin(numOfEachPage, currPage1);
			List<Onlineqa> qalist = onlineqaService.getAllDataByPages(onlineqa,
					begin, numOfEachPage);
			request.setAttribute("qalist", qalist);
		} catch (Exception e) {
			throw new AppException("分页查询全部信息", e);
		}
		this.actionName = "getAllDataByPage";
		_logger.info("get all   onlineqa info   admin   success-------");
		return SUCCESS;
	}

	/**
	 * 
	 * Person分页显示所有信息
	 */
	public String getPersonPage() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		// 分页配置
		Pagination p = new Pagination(0, 0, 0);
		String currPage = (String) request.getParameter("goPage");
		if (currPage == null) {
			currPage = "1";// ” currPage”是当前页数
		}
		_logger.info("currPage:" + currPage);
		Integer currPage1 = Integer.parseInt(currPage);

		try {

			Onlineqa onlineqa = new Onlineqa();
			onlineqa.setState(state);
			onlineqa.setClientname(clientname);
			onlineqa.setTitle(title);
			Integer totleCount = onlineqaService.getAllDataCounts(onlineqa);
			p.getPagination(request, totleCount, currPage1, null, null, null);
			int numOfEachPage = Constants.NUMOFEACHPAGE;
			int begin = p.getRownum_begin(numOfEachPage, currPage1);
			List<Onlineqa> qalist = onlineqaService.getAllDataByPages(onlineqa,
					begin, numOfEachPage);
			request.setAttribute("qalist", qalist);
		} catch (Exception e) {
			throw new AppException("分页查询全部信息", e);
		}
		this.actionName = "getPersonPage";
		_logger.info("get all   onlineqa info   person   success-------");
		return SUCCESS;
	}

	/**
	 * 
	 * Client分页显示所有信息
	 */
	@SuppressWarnings("unchecked")
	public String getClientPage() throws Exception {
		Map session = ActionContext.getContext().getSession();
		HttpServletRequest request = ServletActionContext.getRequest();
		// 分页配置
		Pagination p = new Pagination(0, 0, 0);
		String currPage = (String) request.getParameter("goPage");
		if (currPage == null) {
			currPage = "1";// ” currPage”是当前页数
		}
		_logger.info("currPage:" + currPage);
		Integer currPage1 = Integer.parseInt(currPage);

		try {

			Onlineqa onlineqa = new Onlineqa();
			onlineqa.setState("2");
			onlineqa.setClientname(((Account) session.get(Constants.ACCOUNT_SESSION))
					.getAccountName());
			Integer totleCount = onlineqaService.getAllDataCount(onlineqa);
			p.getPagination(request, totleCount, currPage1, null, null, null);
			int numOfEachPage = Constants.NUMOFEACHPAGE;
			int begin = p.getRownum_begin(numOfEachPage, currPage1);
			List<Onlineqa> qalist = onlineqaService.getAllDataByPage(onlineqa,
					begin, numOfEachPage);
			request.setAttribute("qalist", qalist);
		} catch (Exception e) {
			throw new AppException("分页查询全部信息", e);
		}
		this.actionName = "getClientPage";
		_logger.info("get all   onlineqa info   client   success-------");

		return SUCCESS;
	}

	/**
	 * @see 根据ID查找Entity
	 */
	public String getOnlineqaById() throws Exception {
		this.onlineqa = onlineqaService.getOnlineqaById(id);
		return SUCCESS;
	}

	/**
	 * @see 根据ID查找Entity
	 */
	public String getOnlineqaByIds() throws Exception {
		this.onlineqa = onlineqaService.getOnlineqaById(id);
		return SUCCESS;
	}
	
	/**
	 * @see 根据ID查找Entity
	 */
	public String getOnlineqaByIdsPerson() throws Exception {
		this.onlineqa = onlineqaService.getOnlineqaById(id);
		return SUCCESS;
	}

	/**
	 * admin回答问题
	 */
	public String updataOnlineqa() throws Exception {
		Map session = ActionContext.getContext().getSession();
		onlineqa.setState("2");
		onlineqa.setAnswer(((Account) session.get(Constants.ACCOUNT_SESSION))
				.getAccountName());
		onlineqaService.updateOnlineqa(onlineqa);
		return SUCCESS;
	}

	/**
	 * 用户回答问题
	 */
	public String updataOnlineqaPerson() throws Exception {
		Map session = ActionContext.getContext().getSession();
		onlineqa.setState("2");
		onlineqa.setAnswer(((Account) session.get(Constants.ACCOUNT_SESSION))
				.getAccountName());
		onlineqaService.updateOnlineqa(onlineqa);
		return SUCCESS;
	}

	/**
	 * 删除问题
	 */
	public String deleteOnlineqaById() throws Exception {
		onlineqaService.deleteOnlineqaById(id);
		return SUCCESS;
	}

	/**
	 * 批量删除问题
	 * 
	 * @return
	 * @throws AppException
	 */
	public String delete() throws AppException {
		try {
			String idstr = "";
			for (int i = 0; i < idArray.length; i++) {
				idstr = idstr + idArray[i] + ",";
				onlineqaService.deletePerson(idArray[i]);
			}
			_logger.info("delete onlineqa  success-------");
		} catch (Exception e) {
			throw new AppException("删除答疑信息", e);
		}
		return SUCCESS;
	}
	
	
	
	/**
	 * 获得指定回答人(公司名称)
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String onlineqaC() throws Exception {
		
		return SUCCESS;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClientname() {
		return clientname;
	}

	public void setClientname(String clientname) {
		this.clientname = clientname;
	}

	public Long getAccountid() {
		return accountid;
	}

	public void setAccountid(Long accountid) {
		this.accountid = accountid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Date getFirsttime() {
		return firsttime;
	}

	public void setFirsttime(Date firsttime) {
		this.firsttime = firsttime;
	}

	public Date getLasttime() {
		return lasttime;
	}

	public void setLasttime(Date lasttime) {
		this.lasttime = lasttime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getNamefor() {
		return namefor;
	}

	public void setNamefor(String namefor) {
		this.namefor = namefor;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public OnlineqaService getOnlineqaService() {
		return onlineqaService;
	}

	public void setOnlineqaService(OnlineqaService onlineqaService) {
		this.onlineqaService = onlineqaService;
	}

	public Onlineqa getOnlineqa() {
		return onlineqa;
	}

	public void setOnlineqa(Onlineqa onlineqa) {
		this.onlineqa = onlineqa;
	}

	public Logger get_logger() {
		return _logger;
	}

	public String[] getIdArray() {
		return idArray;
	}

	public void setIdArray(String[] idArray) {
		this.idArray = idArray;
	}

}
