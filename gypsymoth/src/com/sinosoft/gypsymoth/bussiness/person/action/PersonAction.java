package com.sinosoft.gypsymoth.bussiness.person.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.sinosoft.gypsymoth.bussiness.person.service.PersonService;
import com.sinosoft.gypsymoth.exception.AppException;
import com.sinosoft.gypsymoth.pojo.Account;
import com.sinosoft.gypsymoth.pojo.AccountRole;
import com.sinosoft.gypsymoth.pojo.Example;
import com.sinosoft.gypsymoth.pojo.OpLog;
import com.sinosoft.gypsymoth.pojo.OrganizationLevel;
import com.sinosoft.gypsymoth.pojo.Person;
import com.sinosoft.gypsymoth.utils.Constants;
import com.sinosoft.gypsymoth.utils.Pagination;



public class PersonAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Logger _logger = Logger.getLogger(PersonAction.class);
	private String id;
	private PersonService personService;
	private Person person;
	private OrganizationLevel orglevel;
	private String actionName;
	private String name;
	private String sex;
	private Date birthday;
	private String education;
	private String position;
	private String training;
	private Date passTime;
	private String tel;
	private String mobile;
	private String fax;
	private String email;
	private String isCoordinator;
	private String isAuthorized;
	private String isInspector;
	private File attachment;
	private String attachmentFileName;
	private String orgId;
	private String personType;
	private String[] idArray; 
    private String spell;
    private String tel1;
    private String tel2;
    private String selectOrgname2;
    private String selectOrgid;//查询条件：机构ID
	private String selectOrgname;//查询条件：机构名称
	public PersonService getPersonService() {
		return personService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTraining() {
		return training;
	}

	public void setTraining(String training) {
		this.training = training;
	}

	public String getIsCoordinator() {
		return isCoordinator;
	}

	public void setIsCoordinator(String isCoordinator) {
		this.isCoordinator = isCoordinator;
	}

	public String getIsAuthorized() {
		return isAuthorized;
	}

	public void setIsAuthorized(String isAuthorized) {
		this.isAuthorized = isAuthorized;
	}

	public String getIsInspector() {
		return isInspector;
	}

	public void setIsInspector(String isInspector) {
		this.isInspector = isInspector;
	}

	public OrganizationLevel getOrglevel() {
		return orglevel;
	}

	public void setOrglevel(OrganizationLevel orglevel) {
		this.orglevel = orglevel;
	}

	public File getAttachment() {
		return attachment;
	}

	public void setAttachment(File attachment) {
		this.attachment = attachment;
	}

	public String getAttachmentFileName() {
		return attachmentFileName;
	}

	public void setAttachmentFileName(String attachmentFileName) {
		this.attachmentFileName = attachmentFileName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}

	public String[] getIdArray() {
		return idArray;
	}

	public void setIdArray(String[] idArray) {
		this.idArray = idArray;
	}

	public Date getPassTime() {
		return passTime;
	}

	public void setPassTime(Date passTime) {
		this.passTime = passTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getSpell() {
		return spell;
	}

	public void setSpell(String spell) {
		this.spell = spell;
	}
	
	public String getTel1() {
		return tel1;
	}

	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}

	public String getTel2() {
		return tel2;
	}

	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}
	
	public String getSelectOrgname2() {
		return selectOrgname2;
	}

	public void setSelectOrgname2(String selectOrgname2) {
		this.selectOrgname2 = selectOrgname2;
	}
	
	public String getSelectOrgid() {
		return selectOrgid;
	}

	public void setSelectOrgid(String selectOrgid) {
		this.selectOrgid = selectOrgid;
	}

	public String getSelectOrgname() {
		return selectOrgname;
	}

	public void setSelectOrgname(String selectOrgname) {
		this.selectOrgname = selectOrgname;
	}
/**
 * 添加人员信息
 * @return
 * @throws AppException
 */

	public String save() throws AppException {
		try {
			String realpath = ServletActionContext.getServletContext()
					.getRealPath("/attachment");
			System.out.println(realpath);
			String fileName="";
			if (attachment != null) {
				String aName="";
				int pos = attachmentFileName.lastIndexOf( "." );//获得后缀名的位置
				String last= "."+attachmentFileName.substring(pos+1);//获得后缀名	
				fileName=new Date().getTime()+last;//将图片重命名成当前时间
				File savefile = new File(new File(realpath), fileName);//生成文件对象
				if (!savefile.getParentFile().exists()) {//判断是否存在attachment文件夹，如果没有此文件夹就创建
					savefile.getParentFile().mkdirs();
				}
				FileUtils.copyFile(attachment, savefile);//将文件从struts的临时路径拷贝到硬盘指定路径下
				System.out.println("上传文件成功！");
				_logger.info("上传文件成功！-------");
			}
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			Account accont = (Account) session
					.getAttribute(Constants.ACCOUNT_SESSION);

			Person ps = new Person();
			ps.setAccountId("noid");
			ps.setOrgId(selectOrgid);
			ps.setName(name);
			ps.setSpell(spell);
			ps.setSex(sex);
			ps.setBirthday(birthday);
			ps.setEducation(education);
//			ps.setPosition(position);
			if (training != null) {
				ps.setTraining(1);
			}
			else{
				ps.setTraining(0);
			}
			ps.setPassTime(passTime);
			ps.setTel(tel1+"-"+tel2);
			ps.setMobile(mobile);
			ps.setFax(fax);
			ps.setEmail(email);
			ps.setInsTime(new java.sql.Date(System.currentTimeMillis()));//插入当前时间
			if (isCoordinator != null) {
				ps.setIsCoordinator(1);
			}
			else{
				ps.setIsCoordinator(0);
			}
			if (isAuthorized != null) {
				ps.setIsAuthorized(1);
			}
			else{
				ps.setIsAuthorized(0);
			}
			if (isInspector != null) {
				ps.setIsInspector(1);
			}
			else{
				ps.setIsInspector(0);
			}
			ps.setPersonType(personType);			
			ps.setAttachment(fileName);
			ps.setPersonStatus(1);
			personService.savePerson(ps);
			_logger.info("insert into person success-------");
		} catch (Exception e) {
			throw new AppException("添加人员信息", e);

		}
		return SUCCESS;
	}
	/**
	 * 获取机构列表
	 * @return
	 * @throws AppException
	 */
	public String getOrgList() throws AppException{
		/* 树状菜单 */
		try{
		HttpServletRequest request = ServletActionContext.getRequest();
		List<OrganizationLevel> orglist = personService
				.getAllOrganizationLevel();
		System.out.println("***" + orglist);
		for (int i = 0; i < orglist.size(); i++) {
			_logger.info("========="
					+ ((OrganizationLevel) orglist.get(i)).getOrgName());

		}
		request.setAttribute("selectOrgList", orglist);
		_logger.info("getselectOrgList success-------");
		} catch (Exception e) {
			throw new AppException("获取机构列表", e);
		}
		return SUCCESS;
	}

	/**
	 * 按条件分页查询所有人员
	 * @return
	 * @throws AppException
	 */
	public String getAllPersonByPage() throws AppException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		System.out.print(selectOrgid+"++++"+selectOrgname);
		Person ps = new Person();
		if (isCoordinator != null) {
			ps.setIsCoordinator(1);
		}
		else{
			ps.setIsCoordinator(0);
		}
		if (isAuthorized != null) {
			ps.setIsAuthorized(1);
		}
		else{
			ps.setIsAuthorized(0);
		}
		if (isInspector != null) {
			ps.setIsInspector(1);
		}
		else{
			ps.setIsInspector(0);
		}
		// 分页配置
		Pagination p = new Pagination(0, 0, 0);
		String currPage = (String) request.getParameter("goPage");
		if (currPage == null || "".equals(currPage)) {
			currPage = "1";// ” currPage”是当前页数
		}
		_logger.info("currPage:" + currPage);
		session.setAttribute("currtPage", currPage);
		Integer currPage1 = Integer.parseInt(currPage);
		try {
			/* 树状菜单 */
			List<OrganizationLevel> orglist = personService
					.getAllOrganizationLevel();
			System.out.println("***" + orglist);
			for (int i = 0; i < orglist.size(); i++) {
				_logger.info("========="
						+ ((OrganizationLevel) orglist.get(i)).getOrgName());

			}
			request.setAttribute("orgLevelList", orglist);
			_logger.info("getAllOrgLevel success-------");
			/* 树状菜单结束 */
			String Id = request.getParameter("Id");
			if(Id != null) {
				selectOrgid = Id;
			}
			Integer totleCount = personService.getAllPersonCount(name, selectOrgid, ps);
			p.getPagination(request, totleCount, currPage1, null, null, null);
			int numOfEachPage = Constants.NUMOFEACHPAGE;
			int begin = p.getRownum_begin(numOfEachPage, currPage1);
			List<Person> list = null;
			System.out.print(isCoordinator+"="+isAuthorized+"="+isInspector);
			list = personService.getAllPersonByPage(Id,name,selectOrgid,isCoordinator,isAuthorized,isInspector,begin, numOfEachPage);
			request.setAttribute("list", list);
			_logger.info("get all person by page success-------");
		} catch (Exception e) {
			throw new AppException("分页查询全部人员信息", e);
		}
		this.actionName = "getAllPersonByPage";	
		return SUCCESS;
	}
	/**
	 * 分页查询
	 * 
	 * @return
	 * @throws AppException
	 */
	public String getEnable() throws AppException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String Id = request.getParameter("Id");
		String name = request.getParameter("name");
		String position = request.getParameter("position");
		String tel = request.getParameter("tel");
		// 分页配置
		Pagination p = new Pagination(0, 0, 0);
		String currPage = (String) request.getParameter("goPage");
		if (currPage == null || "".equals(currPage)) {
			currPage = "1";// ” currPage”是当前页数
		}
		_logger.info("currPage:" + currPage);
		Integer currPage1 = Integer.parseInt(currPage);
		try {
			
			Integer totleCount = personService.getAllEnableCount();
			p.getPagination(request, totleCount, currPage1, null, null, null);
			int numOfEachPage = Constants.NUMOFEACHPAGE;
			int begin = p.getRownum_begin(numOfEachPage, currPage1);
			List<Person> list = null;
			list = personService.getAllEnableByPage(Id,name,selectOrgid,isCoordinator,isAuthorized,isInspector,begin, numOfEachPage);
			request.setAttribute("list", list);
			_logger.info("get all person by page success-------");
		} catch (Exception e) {
			throw new AppException("分页查询全部人员信息", e);
		}
		this.actionName = "getAllPersonByPage";
		return SUCCESS;
	}
	/**
	 * 人员管理添加页面
	 * @return
	 * @throws Exception
	 */
	public String select() throws Exception {
//
//		HttpServletRequest request = ServletActionContext.getRequest();
//		List list = personService.select();
//		request.setAttribute("orglist", list);
//		_logger.info("get select success-------");
		return SUCCESS;
	}

	/**
	 *根据ID查询人员信息 
	 * @return
	 * @throws AppException
	 */
	public String getPersonById() throws AppException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String pId = request.getParameter("pId");
//		if(pId==null&&pId.equals("")){
//			pId=request.getParameter("idArray");
//		}
		try {
			List list = personService.getPersonById(pId);//根据ID查询人员信息
			if (null != list && list.size() > 0) {
				request.setAttribute("list", list.get(0));
			}

			_logger.info("get one data success-------");
		} catch (Exception e) {
			throw new AppException("查看人员信息", e);
		}
		return SUCCESS;
	}
/**
 * 根据ID显示需要修改的人员信息
 * @return
 * @throws Exception
 */
	public String getUpdatePre() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String pId=request.getParameter("pId");
		try {
				List list = personService.getPersonById(pId);
				if (null != list && list.size() > 0) {
					Map map = (Map) list.get(0);
					request.setAttribute("list", map);
					String parent_id = map.get("ORG_ID").toString();					
					List<OrganizationLevel> parentlist = personService.getAllOrganizationLevel(parent_id);
					if(parentlist!=null&&parentlist.size()>0){
						String selectOrgname = parentlist.get(0).getOrgName();
						String selectOrgid = parentlist.get(0).getId();
						request.setAttribute("selectOrgname", selectOrgname);
						request.setAttribute("selectOrgid", selectOrgid); 
					}
				
				}
				_logger.info("preupdate success-------");
			} catch (Exception e) {
				throw new AppException("修改前查看信息", e);
			}		
				
		return SUCCESS;
	}
	/**
	 * 根据AccountID显示需要修改的人员的基本信息
	 * @return
	 * @throws Exception
	 */
	public String getUpdateInfo() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute(Constants.ACCOUNT_SESSION);
		String accountId=account.getAccountId();
		String pId="";
		try {
				List<Map> plist = personService.getPersonByAccountId(accountId);	
				String tmpStr = "";
					for(int i=0;i<plist.size();i++){
						Map tmpMap = plist.get(i);
						tmpStr += tmpMap.get("ID").toString();
					}
				pId=tmpStr;				
			    List list = personService.getPersonById(pId);
			    if (null != list && list.size() > 0) {
					Map map = (Map) list.get(0);
					request.setAttribute("list", map);
					String parent_id = map.get("ORG_ID").toString();					
					List<OrganizationLevel> parentlist = personService.getAllOrganizationLevel(parent_id);
					if(parentlist!=null&&parentlist.size()>0){
						String selectOrgname = parentlist.get(0).getOrgName();
						String selectOrgid = parentlist.get(0).getId();
						request.setAttribute("selectOrgname", selectOrgname);
						request.setAttribute("selectOrgid", selectOrgid); 
					}
				
				}
				_logger.info("preupdate success-------");
			} catch (Exception e) {
				throw new AppException("修改个人资料信息", e);
			}
			List lista = personService.select();
			request.setAttribute("orglist", lista);
			_logger.info("get select success-------");			
				
		return SUCCESS;
	}
	/**
	 * 根据AccountID查看人员的基本信息
	 * @return
	 * @throws Exception
	 */
	public String getInfoById() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute(Constants.ACCOUNT_SESSION);
		String accountId=account.getAccountId();
		String pId="";
		try {
				List<Map> plist = personService.getPersonByAccountId(accountId);	
				String tmpStr = "";
					for(int i=0;i<plist.size();i++){
						Map tmpMap = plist.get(i);
						tmpStr += tmpMap.get("ID").toString();
					}
				pId=tmpStr;				
			    List list = personService.getPersonById(pId);
				if (null != list && list.size() > 0) {
					request.setAttribute("list", list.get(0));
				}
				_logger.info("preupdate success-------");
			} catch (Exception e) {
				throw new AppException("修改个人资料信息", e);
			}
			List lista = personService.select();
			request.setAttribute("orglist", lista);
			_logger.info("get select success-------");			
				
		return SUCCESS;
	}
	/**
	 * 修改人员信息
	 * @return
	 * @throws AppException
	 */
	public String update() throws AppException {
		try {
			String realpath = ServletActionContext.getServletContext()
					.getRealPath("/attachment");
			System.out.println(realpath);
			String fileName="";
			if (attachment != null) {
				String aName="";				
				int pos = attachmentFileName.lastIndexOf( "." );//获得后缀名的位置
				String last= "."+attachmentFileName.substring(pos+1);//获得后缀名	
				fileName=new Date().getTime()+last;//将图片重命名成当前时间
				File savefile = new File(new File(realpath), fileName);//生成文件对象
				if (!savefile.getParentFile().exists()) {
					savefile.getParentFile().mkdirs();
				}
				FileUtils.copyFile(attachment, savefile);
				System.out.println("上传文件成功！");
			}
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			Person ps = new Person();
			OpLog ol = new OpLog();			
			Account accont = (Account) session
					.getAttribute(Constants.ACCOUNT_SESSION);
			ps.setId(Integer.parseInt(id));
			ps=personService.getPersonById(ps);
				
			ol.setPersonId(ps.getPersonId());
			ol.setAccountId(ps.getAccountId());
			ps.setOrgId(selectOrgid);
			ol.setOrgId(ps.getOrgId());
			ps.setName(name);
			ol.setName(ps.getName());	
			ps.setSpell(spell);
			ol.setSpell(ps.getSpell());			
			ps.setSex(sex);
			ol.setSex(ps.getSex());			
			ps.setBirthday(birthday);
			ol.setBirthday(ps.getBirthday());
			ps.setEducation(education);
			ol.setEducation(ps.getEducation());
//			ps.setPosition(position);
//			ol.setPosition(position);
			if (training != null) {
				ps.setTraining(1);
				ol.setTraining(ps.getTraining());
			}
			else{
				ps.setTraining(0);
				ol.setTraining(ps.getTraining());
			}
			ps.setPassTime(passTime);
			ol.setPassTime(ps.getPassTime());
			ps.setTel(tel);
			ol.setTel(ps.getTel());
			ps.setMobile(mobile);
			ol.setMobile(ps.getMobile());
			ps.setFax(fax);
			ol.setFax(ps.getFax());
			ps.setEmail(email);
			ol.setEmail(ps.getEmail());
			ps.setUpTime(new java.sql.Date(System.currentTimeMillis()));
			ol.setOpTime(new java.sql.Date(System.currentTimeMillis()));			
			ps.setPersonType(personType);
			ol.setPersonType(ps.getPersonType());			
			if(attachment != null){
			ps.setAttachment(fileName);
			}
			ol.setAttachment(ps.getAttachment());
			ol.setComments("修改人员信息");
			ol.setOpName(accont.getAccountName());
			personService.updatePerson(ps,ol,isCoordinator,isAuthorized,isInspector);
			_logger.info("update person success-------");		
			
		} catch (Exception e) {
			throw new AppException("修改人员信息", e);
		}

		return SUCCESS;
	}
	/**
	 * 修改个人基本信息
	 * @return
	 * @throws AppException
	 */
	public String updateInfo() throws AppException {
		try {
			String realpath = ServletActionContext.getServletContext()
					.getRealPath("/attachment");
			System.out.println(realpath);
			String fileName="";
			if (attachment != null) {
				String aName="";				
				int pos = attachmentFileName.lastIndexOf( "." );//获得后缀名的位置
				String last= "."+attachmentFileName.substring(pos+1);//获得后缀名	
				fileName=new Date().getTime()+last;//将图片重命名成当前时间
				File savefile = new File(new File(realpath), fileName);//生成文件对象
				if (!savefile.getParentFile().exists()) {
					savefile.getParentFile().mkdirs();
				}
				FileUtils.copyFile(attachment, savefile);
				System.out.println("上传文件成功！");
			}
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			Person ps = new Person();
			OpLog ol = new OpLog();			
			Account accont = (Account) session
					.getAttribute(Constants.ACCOUNT_SESSION);
			ps.setId(Integer.parseInt(id));
			ps=personService.getPersonById(ps);				
			ol.setPersonId(ps.getPersonId());
			ol.setAccountId(ps.getAccountId());
			ol.setOrgId(ps.getOrgId());			
			ol.setName(ps.getName());	
			ps.setSpell(spell);
			ol.setSpell(ps.getSpell());		
			ps.setSex(sex);
			ol.setSex(ps.getSex());			
			ps.setBirthday(birthday);
			ol.setBirthday(ps.getBirthday());
			ps.setEducation(education);
			ol.setEducation(ps.getEducation());
//			ps.setPosition(position);
//			ol.setPosition(position);
			ps.setTel(tel);
			ol.setTel(ps.getTel());
			ps.setMobile(mobile);
			ol.setMobile(ps.getMobile());
			ps.setFax(fax);
			ol.setFax(ps.getFax());
			ps.setEmail(email);
			ol.setEmail(ps.getEmail());
			ps.setUpTime(new java.sql.Date(System.currentTimeMillis()));
			ol.setOpTime(new java.sql.Date(System.currentTimeMillis()));			
			ol.setPersonType(ps.getPersonType());			
			if(attachment != null){
			ps.setAttachment(fileName);
			}
			ol.setAttachment(ps.getAttachment());
			ol.setComments("修改人员信息");
			ol.setOpName(accont.getAccountName());
			personService.updatePerson(ps,ol,isCoordinator,isAuthorized,isInspector);
			_logger.info("update person success-------");		
			
		} catch (Exception e) {
			throw new AppException("修改人员信息", e);
		}

		return SUCCESS;
	}
	/**
	 * 注销人员
	 * @return
	 * @throws AppException
	 */
	public String cancel() throws AppException {
		try{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Account accont = (Account) session
		.getAttribute(Constants.ACCOUNT_SESSION);
		Person ps = new Person();
		OpLog ol = new OpLog();
		for(int i=0;i<idArray.length;i++){			
			ps.setId(Integer.parseInt(idArray[i]));
			ps=personService.getPersonById(ps);
			ol.setId(ps.getId());
			ol.setPersonId(ps.getPersonId());
			ol.setAccountId(ps.getAccountId());
			ol.setOrgId(ps.getOrgId());
			ol.setName(ps.getName());
			ol.setSex(ps.getSex());
			ol.setBirthday(ps.getBirthday());
			ol.setEducation(ps.getEducation());
			ol.setPosition(ps.getPosition());
			ol.setTraining(ps.getTraining());
			ol.setPassTime(ps.getPassTime());
			ol.setTel(ps.getTel());
			ol.setMobile(ps.getMobile());
			ol.setFax(ps.getFax());
			ol.setEmail(ps.getEmail());
			ol.setOpTime(new java.sql.Date(System.currentTimeMillis()));
			ol.setIsCoordinator(ps.getIsCoordinator());
			ol.setIsAuthorized(ps.getIsAuthorized());
			ol.setIsInspector(ps.getIsInspector());
			ol.setPersonType(ps.getPersonType());
			ol.setOrgId(ps.getOrgId());
			ol.setAttachment(ps.getAttachment());
			ol.setComments("注销人员信息");
			ol.setOpName(accont.getAccountName());			
			personService.updateBatch(idArray[i],ol);
			_logger.info("update enablebatch success");	
		}			
		}catch(Exception e){
			throw new AppException("注销人员信息", e);
		}
		return SUCCESS;
		
		
	}
	/**
	 * 启用人员
	 * @return
	 * @throws AppException
	 */
	public String enable() throws AppException {
		try{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Account accont = (Account) session
		.getAttribute(Constants.ACCOUNT_SESSION);
		Person ps = new Person();
		OpLog ol = new OpLog();
		for(int i=0;i<idArray.length;i++){
			
			ps.setId(Integer.parseInt(idArray[i]));
			ps=personService.getPersonById(ps);
			ol.setId(ps.getId());
			ol.setPersonId(ps.getPersonId());
			ol.setAccountId(ps.getAccountId());
			ol.setOrgId(ps.getOrgId());
			ol.setName(ps.getName());
			ol.setSex(ps.getSex());
			ol.setBirthday(ps.getBirthday());
			ol.setEducation(ps.getEducation());
			ol.setPosition(ps.getPosition());
			ol.setTraining(ps.getTraining());
			ol.setPassTime(ps.getPassTime());
			ol.setTel(ps.getTel());
			ol.setMobile(ps.getMobile());
			ol.setFax(ps.getFax());
			ol.setEmail(ps.getEmail());
			ol.setOpTime(new java.sql.Date(System.currentTimeMillis()));
			ol.setIsCoordinator(ps.getIsCoordinator());
			ol.setIsAuthorized(ps.getIsAuthorized());
			ol.setIsInspector(ps.getIsInspector());
			ol.setPersonType(ps.getPersonType());
			ol.setOrgId(ps.getOrgId());
			ol.setAttachment(ps.getAttachment());
			ol.setComments("启用人员信息");
			ol.setOpName(accont.getAccountName());	
			personService.updateEnableBatch(idArray[i],ol);
			_logger.info("update batch success");	
		}			
		}catch(Exception e){
			throw new AppException("启用人员信息", e);
		}
		return SUCCESS;
		
		
	}
	/**
	 * 删除人员
	 * @return
	 * @throws AppException
	 */
	public String delete() throws AppException{
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			String idstr="";
			Account accont = (Account) session
			.getAttribute(Constants.ACCOUNT_SESSION);
			Person ps = new Person();
			OpLog ol = new OpLog();
			for(int i=0;i<idArray.length;i++){
				idstr=idstr+idArray[i]+",";
				ps.setId(Integer.parseInt(idArray[i]));
				ps=personService.getPersonById(ps);
				String accountID=accont.getAccountId();
				ol.setId(ps.getId());
				ol.setPersonId(ps.getPersonId());
				ol.setAccountId(ps.getAccountId());
				ol.setOrgId(ps.getOrgId());
				ol.setName(ps.getName());
				ol.setSex(ps.getSex());
				ol.setBirthday(ps.getBirthday());
				ol.setEducation(ps.getEducation());
				ol.setPosition(ps.getPosition());
				ol.setTraining(ps.getTraining());
				ol.setPassTime(ps.getPassTime());
				ol.setTel(ps.getTel());
				ol.setMobile(ps.getMobile());
				ol.setFax(ps.getFax());
				ol.setEmail(ps.getEmail());
				ol.setOpTime(new java.sql.Date(System.currentTimeMillis()));
				ol.setIsCoordinator(ps.getIsCoordinator());
				ol.setIsAuthorized(ps.getIsAuthorized());
				ol.setIsInspector(ps.getIsInspector());
				ol.setPersonType(ps.getPersonType());
				ol.setOrgId(ps.getOrgId());
				ol.setAttachment(ps.getAttachment());
				ol.setComments("删除人员信息");
				ol.setOpName(accont.getAccountName());
//				personService.saveLOG(ol);				
				personService.deletePerson(idArray[i],accont,ol);
				_logger.info("insert op_log success-------");
				
			}
			_logger.info("update batch success");	
			}catch(Exception e){
				throw new AppException("删除人员信息", e);
			}
			return SUCCESS;			
	}

}
