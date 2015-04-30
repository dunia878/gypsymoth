package com.sinosoft.gypsymoth.bussiness.organization.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sinosoft.gypsymoth.bussiness.organization.service.OrganizationService;
import com.sinosoft.gypsymoth.exception.AppException;
import com.sinosoft.gypsymoth.pojo.Account;
import com.sinosoft.gypsymoth.pojo.Example;
import com.sinosoft.gypsymoth.pojo.OpLog;

import com.sinosoft.gypsymoth.pojo.OrgLog;
import com.sinosoft.gypsymoth.pojo.Organization;
import com.sinosoft.gypsymoth.pojo.OrganizationLevel;
import com.sinosoft.gypsymoth.pojo.OrganizationPort;
import com.sinosoft.gypsymoth.pojo.Person;
import com.sinosoft.gypsymoth.pojo.Port;
import com.sinosoft.gypsymoth.pojo.ProCityPort;
import com.sinosoft.gypsymoth.pojo.Promary;
import com.sinosoft.gypsymoth.utils.Constants;
import com.sinosoft.gypsymoth.utils.Pagination;

public class OrganizationAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Logger _logger = Logger.getLogger(OrganizationAction.class);
	private List<Promary> provinces;
	private Integer nationId1;// 中国
	private String address1;// 中国地址
	private Integer nationId2;// 其他国家
	private String address2;// 其他国家地址
	private String changeAddress;// 中国or其他国家
	private OrganizationService organizationService;
	private Organization org;
	private OrganizationLevel orglevel;
	private String actionName;
	private String orgSname;
	private String orgFname;
	private String contact;
	private String tel;
	private String fax;
	private String email;
	private String[] provinceId;
	private Integer orgStatus;
	private String[] portName;
	private String[] portSname;
	private String orgId;
	private String parentId;
	private String Id;
	private String proId;
	private String cityId;
	private String organizationType;
	private String portname;
	private String portsname;
	private String pId;
	private String portId;
	private String address;
	private String[] idArray;
	private String[]citydivId;
	private String selectOrgid;
	private String selectOrgname;
	private String oId;
	private String bankaccount;
	private String vbrk;
	
	public OrganizationService getOrganizationService() {
		return organizationService;
	}

	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public OrganizationLevel getOrglevel() {
		return orglevel;
	}

	public void setOrglevel(OrganizationLevel orglevel) {
		this.orglevel = orglevel;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getOrgSname() {
		return orgSname;
	}

	public void setOrgSname(String orgSname) {
		this.orgSname = orgSname;
	}

	public String getOrgFname() {
		return orgFname;
	}

	public void setOrgFname(String orgFname) {
		this.orgFname = orgFname;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
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

	public String[] getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String[] provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getOrgStatus() {
		return orgStatus;
	}

	public void setOrgStatus(Integer orgStatus) {
		this.orgStatus = orgStatus;
	}

	public List<Promary> getProvinces() {
		return provinces;
	}

	public void setProvinces(List<Promary> provinces) {
		this.provinces = provinces;
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

	public String[] getPortName() {
		return portName;
	}

	public void setPortName(String[] portName) {
		this.portName = portName;
	}

	public String[] getPortSname() {
		return portSname;
	}

	public void setPortSname(String[] portSname) {
		this.portSname = portSname;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getOrganizationType() {
		return organizationType;
	}

	public void setOrganizationType(String organizationType) {
		this.organizationType = organizationType;
	}

	public String getPortname() {
		return portname;
	}

	public void setPortname(String portname) {
		this.portname = portname;
	}

	public String getPortsname() {
		return portsname;
	}

	public void setPortsname(String portsname) {
		this.portsname = portsname;
	}

	public String getPId() {
		return pId;
	}

	public void setPId(String id) {
		pId = id;
	}

	public String getPortId() {
		return portId;
	}

	public void setPortId(String portId) {
		this.portId = portId;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String[] getIdArray() {
		return idArray;
	}

	public void setIdArray(String[] idArray) {
		this.idArray = idArray;
	}
	
	public String[] getCitydivId() {
		return citydivId;
	}

	public void setCitydivId(String[] citydivId) {
		this.citydivId = citydivId;
	}
	
	public String getOId() {
		return oId;
	}

	public void setOId(String id) {
		oId = id;
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

	/**
	 * 获取机构列表
	 * @return
	 * @throws AppException
	 */
	public String getOrgList() throws AppException{
		/* 树状菜单 */
		try{
		HttpServletRequest request = ServletActionContext.getRequest();
		List<OrganizationLevel> orglist = organizationService
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
 * 初始机构信息
 * @return
 * @throws Exception
 */

	public String select() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		List nationList = new ArrayList();
		List promaryList = new ArrayList();
		_logger.info("get ajaxlist success-------");

		try {
			nationList = organizationService.getNation();
			promaryList = organizationService.getPromory();
		} catch (Exception e) {
			throw new AppException("获取添加机构的初始数据", e);
		}
		request.setAttribute("nationList", nationList);
		request.setAttribute("promaryList", promaryList);
		_logger.info("get initOrgData success-------");
		return SUCCESS;
	}
	/**
	 * 获取港口的初始信息
	 * @return
	 * @throws Exception
	 */

	public String selectPort() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		List nationList = new ArrayList();
		List promaryList = new ArrayList();
		_logger.info("get ajaxlist success-------");

		try {
			nationList = organizationService.getNation();
			promaryList = organizationService.getPromory();
		} catch (Exception e) {
			throw new AppException("获取添加港口的初始数据", e);
		}
		request.setAttribute("nationList", nationList);
		request.setAttribute("promaryList", promaryList);
		_logger.info("get initPortData success-------");
		return SUCCESS;
	}

	/**
	 * ajax根据省份获取城市列表
	 * 
	 * @return
	 * @throws AppException
	 */
	public String getCity() throws AppException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Integer proId = Integer.valueOf(request.getParameter("input"));
		List cityList = new ArrayList();

		try {
			cityList = organizationService.getCity(proId);
		} catch (Exception e) {
			throw new AppException("获取城市列表", e);
		}

		request.setAttribute("cityList", cityList);
		return SUCCESS;
	}
	/**
	 * ajax根据省份获取城市列表
	 * 
	 * @return
	 * @throws AppException
	 */
	public String getAllCity() throws AppException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Integer provinceId = Integer.valueOf(request.getParameter("input"));
		List cityList = new ArrayList();

		try {
			cityList = organizationService.getCity(provinceId);
		} catch (Exception e) {
			throw new AppException("获取城市列表", e);
		}

		request.setAttribute("citydivList", cityList);
		return SUCCESS;
	}
/**
 * 添加港口信息
 * @return
 * @throws AppException
 */	
	
	public String savePort() throws AppException {
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			String oId=request.getParameter("orgId");			
			organizationService.savePort(provinceId,citydivId,oId,portName,portSname);
			request.setAttribute("orgId", oId);
			
		}catch (Exception e) {
			throw new AppException("添加港口信息", e);

		}
		return SUCCESS;
		
	}
	/**
	 * 添加机构信息
	 * @return
	 * @throws AppException
	 */
	public String save() throws AppException {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			Organization org = new Organization();
			OrganizationLevel ol = new OrganizationLevel();
			Port po = new Port();
			ProCityPort pcp = new ProCityPort();
			OrganizationPort op = new OrganizationPort();
			// 插入机构表
			org.setOrgId(orgId);
			org.setOrgSname(orgSname);
			org.setOrgFname(orgFname);
			org.setContact(contact);
			org.setTel(tel);
			org.setFax(fax);
			org.setEmail(email);
			org.setOrgStatus(1);
			org.setBankaccount(bankaccount);
			org.setVbrk(vbrk);
			org.setInsTime(new java.sql.Date(System.currentTimeMillis()));
			org.setParentId(selectOrgid);
			if ("1".equals(changeAddress)) {
				org.setAddress(address1);
				org.setNationid(nationId1);
				if (proId != null && !"".equals(proId)) {
					org.setProid(Integer.parseInt(proId));
				}
				if (cityId != null && !"".equals(cityId)) {
					org.setCityid(Integer.parseInt(cityId));
				}

			} else if ("2".equals(changeAddress)) {
				org.setAddress(address2);
				org.setNationid(nationId2);
			}
			
			
			// 插入机构层次表
			ol.setParentId(selectOrgid);
			ol.setIsChild(organizationType);
			ol.setOrgName(orgSname);
			ol.setOrgStatus(1);
			// ol.setOrgUrl("/page/organization/getOrganization.action?Id="+Id);插入organization时获得主键ID
			// ol.setPersonUrl("/page/person/getAllPersonByPage.action?Id="+Id);
			// 插入港口表 在service做
			//			
			// 插入机构港口表(从插入的Organization表中得到主键值)在service层做
			// }
			// 插入省份城市港口对应表
			if (proId != null && !"".equals(proId)) {
				pcp.setProid(Integer.parseInt(proId));
			}
			if (cityId != null && !"".equals(cityId)) {
				pcp.setCityid(Integer.parseInt(cityId));
			}
			organizationService.saveOrganization(org, ol, provinceId,citydivId,portName, portSname,
					po, op, pcp);
			_logger.info("insert into organization success-------");
		} catch (Exception e) {
			throw new AppException("添加机构信息", e);

		}
		return SUCCESS;
	}

	/**
	 * 分页查询所机构信息
	 * @return
	 * @throws AppException
	 */
	public String getAllOrganizationByPage() throws AppException {
		HttpServletRequest request = ServletActionContext.getRequest();		
		HttpSession session = request.getSession();
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
			List<OrganizationLevel> orglist = organizationService
					.getAllOrganizationLevel();
			System.out.println("***" + orglist);
			for (int i = 0; i < orglist.size(); i++) {
				_logger.info("========="
						+ ((OrganizationLevel) orglist.get(i)).getOrgName());

			}
			request.setAttribute("orgLevelList", orglist);
			_logger.info("getAllOrgLevel success-------");
			/* 树状菜单结束 */
			Integer totleCount = organizationService.getAllOrganizationCount();
			p.getPagination(request, totleCount, currPage1, null, null, null);
			int numOfEachPage = Constants.NUMOFEACHPAGE;
			int begin = p.getRownum_begin(numOfEachPage, currPage1);
			List list = organizationService.getAllOrganizationByPage(begin,
					numOfEachPage);
			request.setAttribute("list", list);
			_logger.info("get all person by page success-------");
		} catch (Exception e) {
			throw new AppException("分页查询全部机构信息", e);
		}
		this.actionName = "getAllOrganizationByPage";
		return SUCCESS;
	}

	/**
	 * 根据ID查询机构信息
	 * @return
	 * @throws AppException
	 */
	public String getOrganization() throws AppException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String orgId = request.getParameter("Id");
		try {
			/* 树状菜单 */
			List<OrganizationLevel> orglist = organizationService
					.getAllOrganizationLevel();
			System.out.println("***" + orglist);
			for (int i = 0; i < orglist.size(); i++) {
				_logger.info("========="
						+ ((OrganizationLevel) orglist.get(i)).getOrgName());

			}
			request.setAttribute("orgLevelList", orglist);
			_logger.info("getAllOrgLevel success-------");
			/* 树状菜单结束 */

			List list = organizationService.getAllOrganization(orgId);
			List nationList = new ArrayList();
			List promaryList = new ArrayList();
			List cityList = new ArrayList();
			_logger.info("get ajaxlist success-------");
			nationList = organizationService.getNation();
			promaryList = organizationService.getPromory();
			
			request.setAttribute("nationList", nationList);
			request.setAttribute("promaryList", promaryList);
			_logger.info("get initOrgData success-------");
			if (null != list && list.size() > 0) {
				Map map = (Map) list.get(0);
				request.setAttribute("list", map);
				
//				String city_hidden = (String)map.get("CITYID");
				String pro_hidden = map.get("PROID").toString();
				String parent_id = map.get("PARENT_ID").toString();
				
				cityList = organizationService.getCity(Integer.valueOf(pro_hidden));
				request.setAttribute("cityList", cityList); 
				
				List parentlist = organizationService.getAllOrganization(parent_id);
				if(parentlist!=null&&parentlist.size()>0){
					HashMap parentmap = (HashMap)parentlist.get(0);
					String selectOrgname = (String)parentmap.get("OLNAME");
					String selectOrgid = (String)parentmap.get("ID");
					request.setAttribute("selectOrgname", selectOrgname);
					request.setAttribute("selectOrgid", selectOrgid); 
				}
				_logger.info("get all Organization success-------");
				// String ports="";
				// for (int i = 0; i <list.size(); i++) {
				// Map map=(Map) list.get(i);
				// ports=(String) map.get("PORT_ID") ;
				// }
				// ports=ports.substring(0,ports.length()-1);
				// System.out.println(ports);

				List portList = organizationService.getAllPort(orgId);
				request.setAttribute("portList", portList);
			}

			_logger.info("get all Port success-------");
		} catch (Exception e) {
			throw new AppException("查询机构信息", e);
		}
		this.actionName = "getOrganization";
		return SUCCESS;
	}
	/**
	 * 修改前查看机构信息
	 * @return
	 * @throws AppException
	 */
	public String getOrganizationPre() throws AppException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String orgId = request.getParameter("orgId");
		try {
			/* 树状菜单 */
			List<OrganizationLevel> orglist = organizationService
					.getAllOrganizationLevel();
			System.out.println("***" + orglist);
			for (int i = 0; i < orglist.size(); i++) {
				_logger.info("========="
						+ ((OrganizationLevel) orglist.get(i)).getOrgName());

			}
			request.setAttribute("orgLevelList", orglist);
			_logger.info("getAllOrgLevel success-------");
			/* 树状菜单结束 */
			List list = organizationService.getAllOrganization(orgId);
			List nationList = new ArrayList();
			List promaryList = new ArrayList();
			List cityList = new ArrayList();
			_logger.info("get ajaxlist success-------");
			nationList = organizationService.getNation();
			promaryList = organizationService.getPromory();			
			request.setAttribute("nationList", nationList);
			request.setAttribute("promaryList", promaryList);
			_logger.info("get initOrgData success-------");
			if (null != list && list.size() > 0) {
				Map map = (Map) list.get(0);
				request.setAttribute("list", map);
				
//				String city_hidden = (String)map.get("CITYID");
				String pro_hidden = map.get("PROID").toString();
				String parent_id = map.get("PARENT_ID").toString();
				
				cityList = organizationService.getCity(Integer.valueOf(pro_hidden));
				request.setAttribute("cityList", cityList); 
				
				List parentlist = organizationService.getAllOrganization(parent_id);
				if(parentlist!=null&&parentlist.size()>0){
					HashMap parentmap = (HashMap)parentlist.get(0);
					String selectOrgname = (String)parentmap.get("OLNAME");
					String selectOrgid = (String)parentmap.get("ID");
					request.setAttribute("selectOrgname", selectOrgname);
					request.setAttribute("selectOrgid", selectOrgid); 
				}
				_logger.info("get all Organization success-------");
				// String ports="";
				// for (int i = 0; i <list.size(); i++) {
				// Map map=(Map) list.get(i);
				// ports=(String) map.get("PORT_ID") ;
				// }
				// ports=ports.substring(0,ports.length()-1);
				// System.out.println(ports);

				List portList = organizationService.getAllPort(orgId);
				request.setAttribute("portList", portList);
			}

			_logger.info("get all Port success-------");
		} catch (Exception e) {
			throw new AppException("查询机构信息", e);
		}
		this.actionName = "getOrganizationPre";
		return SUCCESS;
	}
/**
 * 根据ID查看港口信息
 * @return
 * @throws AppException
 */

	public String getPortById() throws AppException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String portId = request.getParameter("portId");
		try {
			List list = organizationService.getPortById(portId);
			if (null != list && list.size() > 0) {
				request.setAttribute("list", list.get(0));
			}

			_logger.info("get one organization success-------");
		} catch (Exception e) {
			throw new AppException("查看信息", e);
		}
		return SUCCESS;
	}
/**
 * 修改前查看港口信息
 * @return
 * @throws AppException
 */
	public String getPrePortById() throws AppException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String portId = request.getParameter("portId");
		try {
			List list = organizationService.getPortById(portId);
			if (null != list && list.size() > 0) {
				request.setAttribute("list", list.get(0));
			}

			_logger.info("get one organization success-------");
		} catch (Exception e) {
			throw new AppException("查看信息", e);
		}
		return SUCCESS;
	}
/**
 * 修改港口信息
 * @return
 * @throws AppException
 */
	
	public String updatePort() throws AppException {

		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			Port port = new Port();
			port.setId(Integer.parseInt(portId));
    		port = organizationService.getPortbyId(port);
			port.setPortId(pId);
			port.setPortName(portname);
			port.setPortSname(portsname);
			organizationService.updatePort(port);
			_logger.info("update port success-------");
		} catch (Exception e) {
			throw new AppException("修改港口信息", e);
		}

		return SUCCESS;
	}
	/**
	 * 修改机构信息
	 * @return
	 * @throws AppException
	 */
	public String update() throws AppException {

		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			Organization org = new Organization();
			OrgLog log = new OrgLog();
			OrganizationLevel ol=new OrganizationLevel();
			Account accont = (Account) session
					.getAttribute(Constants.ACCOUNT_SESSION);
			org.setInsTime(new java.sql.Date(System.currentTimeMillis()));
			org.setId(oId);
			org = organizationService.getOrganizationbyId(org);
			ol= organizationService.getOrgLevelbyId(oId);
			System.out.print(orgSname);
			org.setOrgSname(orgSname);
			log.setOrgSname(org.getOrgSname());
			org.setOrgFname(orgFname);
			log.setOrgFname(org.getOrgFname());
//			org.setAddress(address);
//			log.setAddress(address);
			org.setContact(contact);
			log.setContact(org.getContact());
			org.setTel(tel);
			log.setTel(org.getTel());
			org.setEmail(email);
			log.setEmail(org.getEmail());
			org.setUpTime(new java.sql.Date(System.currentTimeMillis()));
			log.setOpTime(new java.sql.Date(System.currentTimeMillis()));
			org.setFax(fax);
			log.setFax(org.getFax());			
			System.out.println(selectOrgid+"++++"+selectOrgname);
			if(selectOrgid==null&&"".equals(selectOrgid))
			{
				org.setParentId("0");
				
			}
			log.setParentId(org.getParentId());
			org.setParentId(selectOrgid);
			org.setBankaccount(bankaccount);
			log.setBankaccount(org.getBankaccount());
			org.setVbrk(vbrk);
			log.setVbrk(org.getVbrk());			
			if ("1".equals(changeAddress)) {
				org.setAddress(address1);
				log.setAddress(address1);
				org.setNationid(nationId1);
				log.setNationid(nationId1);
				
				if (proId != null && !"".equals(proId)) {
					org.setProid(Integer.parseInt(proId));
					log.setProid(Integer.parseInt(proId));
				}
				if (cityId != null && !"".equals(cityId)) {
					org.setCityid(Integer.parseInt(cityId));
					log.setCityid(Integer.parseInt(cityId));
				}

			} else if ("2".equals(changeAddress)) {
				org.setAddress(address2);
				log.setAddress(address2);
				org.setNationid(nationId2);
				log.setNationid(nationId2);
			}			
			// 插入机构层次表
			if(selectOrgid==null&&"".equals(selectOrgid)){
			ol.setParentId("0");
			}
			ol.setIsChild(organizationType);
			ol.setOrgName(orgSname);
			log.setComments("修改机构信息");
			organizationService.updateOrganization(org,ol);
			_logger.info("update person success-------");			
		} catch (Exception e) {
			throw new AppException("修改信息", e);
		}

		return SUCCESS;
	}
	/**
	 * 根据ID删除港口
	 * @return
	 * @throws AppException
	 */
	public String deletePort() throws AppException{
		HttpServletRequest request = ServletActionContext.getRequest();
		Port port =new Port();
		OrganizationPort op=new OrganizationPort();
		String portId=request.getParameter("portId");
		try {
		
			organizationService.deletePort(portId);
		} catch (Exception e) {
			throw new AppException("根据ID删除港口", e);
		}
		return SUCCESS;
	}
	/**
	 * 注销机构
	 * @return
	 * @throws AppException
	 */
	public String cancel() throws AppException {
		try{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Account accont = (Account) session
		.getAttribute(Constants.ACCOUNT_SESSION);
		Organization o = new Organization();
		OrganizationLevel ol = new OrganizationLevel();
		OrgLog log = new OrgLog();
		for(int i=0;i<idArray.length;i++){
			o.setId(idArray[i]);
			ol.setId(idArray[i]);
			o=organizationService.getOrganizationbyId(o);
			ol=organizationService.getOrgLevelbyId(idArray[i]);
			o.setOrgStatus(0);
			ol.setOrgStatus(0);
			log.setId(Integer.parseInt(idArray[i]));
			log.setOrgId(o.getOrgId());
			log.setAddress(o.getAddress());
			log.setCityid(o.getCityid());
			log.setContact(o.getContact());
			log.setTel(o.getTel());
			log.setEmail(o.getEmail());
			log.setFax(o.getFax());
			log.setNationid(o.getNationid());
			log.setOrgSname(o.getOrgSname());
			log.setOrgFname(o.getOrgFname());
			log.setOpPerson(accont.getAccountName());
			log.setOpTime(new java.sql.Date(System.currentTimeMillis()));
			log.setComments("注销机构信息");
			log.setOrgStatus(0);
			organizationService.saveLOG(log);
			_logger.info("insert org_log success-------");
			organizationService.updateBatch(idArray[i]);
			_logger.info("update batch success");	
		}			
		}catch(Exception e){
			throw new AppException("注销机构信息", e);
		}
		return SUCCESS;
		
		
	}
	/**
	 * 得到启用机构列表
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
			Integer totleCount = organizationService.getAllEnableCount();
			p.getPagination(request, totleCount, currPage1, null, null, null);
			int numOfEachPage = Constants.NUMOFEACHPAGE;
			int begin = p.getRownum_begin(numOfEachPage, currPage1);
			List list = organizationService.getAllEnableByPage(begin, numOfEachPage);
			request.setAttribute("list", list);
			_logger.info("get all person by page success-------");
		} catch (Exception e) {
			throw new AppException("分页查询被启用机构信息", e);
		}
		this.actionName = "getEnable";
		return SUCCESS;
	}
	/**
	 * 启用机构
	 * @return
	 * @throws AppException
	 */
	public String enable() throws AppException {
		try{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Account accont = (Account) session
		.getAttribute(Constants.ACCOUNT_SESSION);
		Organization o = new Organization();
		OrganizationLevel ol = new OrganizationLevel();
		OrgLog log = new OrgLog();
		for(int i=0;i<idArray.length;i++){
			o.setId(idArray[i]);
			ol.setId(idArray[i]);
			o=organizationService.getOrganizationbyId(o);
			ol=organizationService.getOrgLevelbyId(idArray[i]);
			o.setOrgStatus(1);
			ol.setOrgStatus(1);
			log.setId(Integer.parseInt(idArray[i]));
			log.setOrgId(o.getOrgId());
			log.setAddress(o.getAddress());
			log.setCityid(o.getCityid());
			log.setContact(o.getContact());
			log.setTel(o.getTel());
			log.setEmail(o.getEmail());
			log.setFax(o.getFax());
			log.setNationid(o.getNationid());
			log.setOrgSname(o.getOrgSname());
			log.setOrgFname(o.getOrgFname());
			log.setOpPerson(accont.getAccountName());
			log.setOpTime(new java.sql.Date(System.currentTimeMillis()));
			log.setComments("启用机构信息");
			log.setOrgStatus(1);
			organizationService.saveLOG(log);
			_logger.info("insert org_log success-------");
			organizationService.updateEnableBatch(idArray[i]);
			_logger.info("update batch success");	
		}			
		}catch(Exception e){
			throw new AppException("启用机构信息", e);
		}
		return SUCCESS;
		
		
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

}
