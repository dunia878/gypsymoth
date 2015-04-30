package com.sinosoft.gypsymoth.bussiness.examine.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sinosoft.gypsymoth.bussiness.assignment.service.AssignmentService;
import com.sinosoft.gypsymoth.bussiness.business.service.BusinessService;
import com.sinosoft.gypsymoth.bussiness.examine.service.ExamineService;
import com.sinosoft.gypsymoth.bussiness.organization.service.OrganizationService;
import com.sinosoft.gypsymoth.bussiness.register.service.RegisterService;
import com.sinosoft.gypsymoth.exception.AppException;
import com.sinosoft.gypsymoth.pojo.Account;
import com.sinosoft.gypsymoth.pojo.Assignment;
import com.sinosoft.gypsymoth.pojo.Business;
import com.sinosoft.gypsymoth.pojo.BusinessDesCountry;
import com.sinosoft.gypsymoth.pojo.ExamPort;
import com.sinosoft.gypsymoth.pojo.Examinelog;
import com.sinosoft.gypsymoth.pojo.Operator;
import com.sinosoft.gypsymoth.pojo.Person;
import com.sinosoft.gypsymoth.utils.Constants;
import com.sinosoft.gypsymoth.utils.Pagination;
import com.sinosoft.gypsymoth.utils.BusinessState;
import com.sinosoft.gypsymoth.utils.PinYin;
import com.sinosoft.gypsymoth.utils.StringUtils;

public class ExamineAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final Logger _logger = Logger.getLogger(ExamineAction.class);
	private ExamineService examineService;
	private OrganizationService organizationService;
	private AssignmentService assignmentService;
	private RegisterService registerService;
	private BusinessService businessService;
	private Examinelog examinelog;
	private ExamPort examPort;
	private Operator operator;
	private String actionName;
	private Business business;
	private long id;
	private long businessid;
	private String port;
	private long country;
	private String description;
	private String checkerid;
	private String operatorid;
	private Date operatortime;
	private long gypsymoth1;
	private long gypsymoth2;
	private long gypsymoth3;
	private long gypsymoth4;
	private long doubt;
	private File[] certificate;
	private String[] certificateFileName;
	private File[] examrecord;
	private String[] examrecordFileName;
	private File[] workphoto;
	private String[] workphotoFileName;	
	private File[] gypsymothphoto;
	private String[] gypsymothphotoFileName;
	private File[]   shipinfo;
	private String[] shipinfoFileName;
	private File[] 	 stopportinfo;
	private String[] stopportinfoFileName;
	private String appId;
	private String[] portName;
	private String[] nationid;
	private String[] anationid;
	private String[] aportName;
	private Date[] stopTime;
	private Date[] astopTime;
	private long stopStatus;
	private String portciy;
	private String appno;
	private String boatname;
	private Date examinetime;
	private String selectBeginAppdate;//查询条件：最小申请日期
	private String selectEndAppdate;//查询条件：最大申请日期
	private String company;
	private String vesseltype;
	private String registry;
	private String imo;
	private String tonnage;
	private Date plandatein;
	private Date plandateout;
	private String berth;
	private String linkmanname;
	private String tel;
	private String fax;
	private String email;
	private String certid;
	private String vesselname;
	
	public ExamineService getExamineService() {
		return examineService;
	}
	public void setExamineService(ExamineService examineService) {
		this.examineService = examineService;
	}
	public Examinelog getExaminelog() {
		return examinelog;
	}
	public void setExaminelog(Examinelog examinelog) {
		this.examinelog = examinelog;
	}
	public ExamPort getExamPort() {
		return examPort;
	}
	public void setExamPort(ExamPort examPort) {
		this.examPort = examPort;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public Business getBusiness() {
		return business;
	}
	public void setBusiness(Business business) {
		this.business = business;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getBusinessid() {
		return businessid;
	}
	public void setBusinessid(long businessid) {
		this.businessid = businessid;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public long getCountry() {
		return country;
	}
	public void setCountry(long country) {
		this.country = country;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCheckerid() {
		return checkerid;
	}
	public void setCheckerid(String checkerid) {
		this.checkerid = checkerid;
	}
	public String getOperatorid() {
		return operatorid;
	}
	public void setOperatorid(String operatorid) {
		this.operatorid = operatorid;
	}
	public Date getOperatortime() {
		return operatortime;
	}
	public void setOperatortime(Date operatortime) {
		this.operatortime = operatortime;
	}
	public long getGypsymoth1() {
		return gypsymoth1;
	}
	public void setGypsymoth1(long gypsymoth1) {
		this.gypsymoth1 = gypsymoth1;
	}
	public long getGypsymoth2() {
		return gypsymoth2;
	}
	public void setGypsymoth2(long gypsymoth2) {
		this.gypsymoth2 = gypsymoth2;
	}
	public long getGypsymoth3() {
		return gypsymoth3;
	}
	public void setGypsymoth3(long gypsymoth3) {
		this.gypsymoth3 = gypsymoth3;
	}
	public long getGypsymoth4() {
		return gypsymoth4;
	}
	public void setGypsymoth4(long gypsymoth4) {
		this.gypsymoth4 = gypsymoth4;
	}
	public long getDoubt() {
		return doubt;
	}
	public void setDoubt(long doubt) {
		this.doubt = doubt;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String[] getPortName() {
		return portName;
	}
	public void setPortName(String[] portName) {
		this.portName = portName;
	}
	public Date[] getStopTime() {
		return stopTime;
	}
	public void setStopTime(Date[] stopTime) {
		this.stopTime = stopTime;
	}
	public long getStopStatus() {
		return stopStatus;
	}
	public void setStopStatus(long stopStatus) {
		this.stopStatus = stopStatus;
	}
		
	public String[] getNationid() {
		return nationid;
	}
	public void setNationid(String[] nationid) {
		this.nationid = nationid;
	}
	public String[] getAnationid() {
		return anationid;
	}
	public void setAnationid(String[] anationid) {
		this.anationid = anationid;
	}
	public String[] getAportName() {
		return aportName;
	}
	public void setAportName(String[] aportName) {
		this.aportName = aportName;
	}
	public File[] getCertificate() {
		return certificate;
	}
	public void setCertificate(File[] certificate) {
		this.certificate = certificate;
	}
	public String[] getCertificateFileName() {
		return certificateFileName;
	}
	public void setCertificateFileName(String[] certificateFileName) {
		this.certificateFileName = certificateFileName;
	}
	public File[] getExamrecord() {
		return examrecord;
	}
	public void setExamrecord(File[] examrecord) {
		this.examrecord = examrecord;
	}
	public File[] getWorkphoto() {
		return workphoto;
	}
	public void setWorkphoto(File[] workphoto) {
		this.workphoto = workphoto;
	}
	public File[] getGypsymothphoto() {
		return gypsymothphoto;
	}
	public void setGypsymothphoto(File[] gypsymothphoto) {
		this.gypsymothphoto = gypsymothphoto;
	}
	public String[] getExamrecordFileName() {
		return examrecordFileName;
	}
	public void setExamrecordFileName(String[] examrecordFileName) {
		this.examrecordFileName = examrecordFileName;
	}
	public String[] getWorkphotoFileName() {
		return workphotoFileName;
	}
	public void setWorkphotoFileName(String[] workphotoFileName) {
		this.workphotoFileName = workphotoFileName;
	}
	public String[] getGypsymothphotoFileName() {
		return gypsymothphotoFileName;
	}
	public void setGypsymothphotoFileName(String[] gypsymothphotoFileName) {
		this.gypsymothphotoFileName = gypsymothphotoFileName;
	}
	public Date[] getAstopTime() {
		return astopTime;
	}
	public void setAstopTime(Date[] astopTime) {
		this.astopTime = astopTime;
	}
	
	public Operator getOperator() {
		return operator;
	}
	public void setOperator(Operator operator) {
		this.operator = operator;
	}
	
	public String getPortciy() {
		return portciy;
	}
	public void setPortciy(String portciy) {
		this.portciy = portciy;
	}
	public String getAppno() {
		return appno;
	}
	public void setAppno(String appno) {
		this.appno = appno;
	}
	public String getBoatname() {
		return boatname;
	}
	public void setBoatname(String boatname) {
		this.boatname = boatname;
	}
	
	public Date getExaminetime() {
		return examinetime;
	}
	public void setExaminetime(Date examinetime) {
		this.examinetime = examinetime;
	}
	
	public File[] getShipinfo() {
		return shipinfo;
	}
	public void setShipinfo(File[] shipinfo) {
		this.shipinfo = shipinfo;
	}
	public String[] getShipinfoFileName() {
		return shipinfoFileName;
	}
	public void setShipinfoFileName(String[] shipinfoFileName) {
		this.shipinfoFileName = shipinfoFileName;
	}
	public File[] getStopportinfo() {
		return stopportinfo;
	}
	public void setStopportinfo(File[] stopportinfo) {
		this.stopportinfo = stopportinfo;
	}
	public String[] getStopportinfoFileName() {
		return stopportinfoFileName;
	}
	public void setStopportinfoFileName(String[] stopportinfoFileName) {
		this.stopportinfoFileName = stopportinfoFileName;
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
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getVesseltype() {
		return vesseltype;
	}
	public void setVesseltype(String vesseltype) {
		this.vesseltype = vesseltype;
	}
	public String getRegistry() {
		return registry;
	}
	public void setRegistry(String registry) {
		this.registry = registry;
	}
	public String getImo() {
		return imo;
	}
	public void setImo(String imo) {
		this.imo = imo;
	}
	public String getTonnage() {
		return tonnage;
	}
	public void setTonnage(String tonnage) {
		this.tonnage = tonnage;
	}
	public Date getPlandatein() {
		return plandatein;
	}
	public void setPlandatein(Date plandatein) {
		this.plandatein = plandatein;
	}
	public Date getPlandateout() {
		return plandateout;
	}
	public void setPlandateout(Date plandateout) {
		this.plandateout = plandateout;
	}
	public String getBerth() {
		return berth;
	}
	public void setBerth(String berth) {
		this.berth = berth;
	}
	public String getLinkmanname() {
		return linkmanname;
	}
	public void setLinkmanname(String linkmanname) {
		this.linkmanname = linkmanname;
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
	public String getCertid() {
		return certid;
	}
	public void setCertid(String certid) {
		this.certid = certid;
	}
	public String getVesselname() {
		return vesselname;
	}
	public void setVesselname(String vesselname) {
		this.vesselname = vesselname;
	}
	
	public RegisterService getRegisterService() {
		return registerService;
	}
	public void setRegisterService(RegisterService registerService) {
		this.registerService = registerService;
	}
	
	public BusinessService getBusinessService() {
		return businessService;
	}
	public void setBusinessService(BusinessService businessService) {
		this.businessService = businessService;
	}
	/**
	 * 分页查询
	 * 
	 * @return
	 * @throws AppException
	 */
	public String getAllExamineByPage() throws AppException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Map sessionmap = ActionContext.getContext().getSession();
		Locale local_language = (Locale) session.getAttribute("WW_TRANS_I18N_LOCALE");
		String language = "CN"; 
		if(local_language!=null){
			language =  local_language.getCountry();
		} 
	
		String portcity_form = request.getParameter("portcity_form"); 
		String vesselname_form = request.getParameter("vesselname_form");
   		String appno_form = request.getParameter("appno_form");
   		String appdate_begin = request.getParameter("appdate_begin");
   		String appdate_end = request.getParameter("appdate_end");
		
		Pagination p = new Pagination(0, 0, 0);		// 分页配置
		String currPage = (String) request.getParameter("goPage");
		if (currPage == null || "".equals(currPage)) {
			currPage = "1";// ” currPage”是当前页数
		}
		_logger.info("currPage:" + currPage);
		Integer currPage1 = Integer.parseInt(currPage);
		
		HashMap map = new HashMap();
		List list = null;
		try {
			Account account = (Account) sessionmap.get(Constants.ACCOUNT_SESSION);
			String isadmin = (String)sessionmap.get(Constants.ACCOUNT_ISADMIN);
			String accounttype = account.getAccountType().toString();
			String accoutrole = (String)sessionmap.get(Constants.ACCOUNT_ALLOT_LEVEL);
			String businessstate = BusinessState.Business_ALLOTED;	//处理状态为未受理
			map.put("state", businessstate);
	   		if (portcity_form!=null&&!portcity_form.equals("")&&!portcity_form.equals("-1")) {
	   			map.put("portcity", portcity_form);
			}
	   		if(vesselname_form != null) {
				map.put("vesselname", vesselname_form.toUpperCase());
			}else {
				map.put("vesselname", vesselname_form);
			}
			if(appno_form != null) {
				map.put("appno", appno_form.toUpperCase()); 
			}else {
				map.put("appno", appno_form); 
			}
	   		map.put("appdate_begin", appdate_begin);
	   		map.put("appdate_end", appdate_end);
			
			
			if (accounttype!=null&&accounttype.equals("1")) {	//用户	查看本机构及本机构下级机构的内容
				if(isadmin!=null&&isadmin.equals("1")){//	 是超级admin
					Integer totleCount = examineService.getAllBussinessCount(map);
					p.getPagination(request, totleCount, currPage1, null, null, null);
					int numOfEachPage = Constants.NUMOFEACHPAGE;
					int begin = p.getRownum_begin(numOfEachPage, currPage1);
					list = examineService.getAllBussinessByPage(map, begin,numOfEachPage); 
					
				}else if(accoutrole!=null){//不为协调员,可以看到内容	
					String personid = (String)sessionmap.get(Constants.ACCOUNT_PERSON_ID);
					map.put("personid",personid);
					
					Integer totleCount = examineService.getAllBussinessCount(map);
					p.getPagination(request, totleCount, currPage1, null, null, null);
					int numOfEachPage = Constants.NUMOFEACHPAGE;
					int begin = p.getRownum_begin(numOfEachPage, currPage1);
					list = examineService.getAllBussinessByPage(map, begin,numOfEachPage);
				}
				
			} else {
					// client 什么也不做
			}
			
			List portcitylist = organizationService.getCityPortList();
			request.setAttribute("portcitylist", portcitylist);
			
			request.setAttribute("list", list); 
			request.setAttribute("vesselname_form", vesselname_form);
			request.setAttribute("portcity_form", portcity_form); 
			request.setAttribute("appno_form", appno_form);
	   		request.setAttribute("appdate_begin", appdate_begin);
	   		request.setAttribute("appdate_end", appdate_end);
	   		request.setAttribute("language", language); 
			
			
			_logger.info("get all examine by page success-------");
		} catch (Exception e) {
			throw new AppException("分页查询检查结果信息", e);
		}
		this.actionName = "getAllExamineByPage";
		return SUCCESS;
	}
	/**
	 * 证书预览
	 * @return
	 */
	public String getAllotPageView() 
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		String business_id = request.getParameter("id");
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM. d, yyyy",Locale.US);
		_logger.info("getAllotPageView begin-------");
		try {
			business = businessService.getBusinessById(Long.valueOf(business_id));
			
			String appno_view ="";	//申请号
			appno_view = business.getAppno();
			
			String appdate_view = dateFormat.format(business.getAppdate());	//申请日期
			
			String businessname_view ="";
			businessname_view = business.getBusinessname();	//申请人
			String company_view = "";
			company_view = business.getCompany();	//船主
			
			String vesselnameandtype_view = "";	//船名及类型
			vesselnameandtype_view = business.getVesselname()+" / "+business.getVesseltype();
			
			String reg_imo_tonnage_view = "";	//船藉 IMO 吨位
			String regis_input = business.getRegistry();
			
			String tonn = "";
			if (business.getTonnage()!=null) {
				tonn = StringUtils.tonString(business.getTonnage().toString());
			}
			 
			reg_imo_tonnage_view = regis_input+" / "+business.getImo()+" / "+tonn+" MT"; 
			
			String plandateout_view =	dateFormat.format(business.getPlandateout()) ;	//计划离港日期
			String checkdate_view = dateFormat.format(business.getCheckdate());	//检查日期
			
			List<BusinessDesCountry> busdesCountrylist = businessService.getBusinessDesCountry(business.getId());
			String countrysandports_input = "";
			for (int i = 0; i < busdesCountrylist.size(); i++) {
				BusinessDesCountry businessDesCountry = busdesCountrylist.get(i);
				if (businessDesCountry.getSelectindex()!=null&&!businessDesCountry.getSelectindex().equals("-1")) {
					String tempport = "";
					if (businessDesCountry.getPort()!=null&&!businessDesCountry.getPort().equals("")) {
//						tempport = businessDesCountry.getPort()+" / "; 
					}
					countrysandports_input = countrysandports_input+tempport+businessDesCountry.getCountry()+" ; ";
				}
			}
			if(countrysandports_input.indexOf(";")!=-1){
				countrysandports_input = countrysandports_input.substring(0,countrysandports_input.length()-1);
			} 
			 
			
			String breth_inpit = "";	//船舶及港口城市 breth, tianjin, 天津, CHINA
			List portlist = registerService.getPortProCity(business.getPortid()); 
			if (portlist!=null&&portlist.size()>0) {
				HashMap portmap = (HashMap)portlist.get(0);
				String port_sname = (String)portmap.get("PORT_SNAME");
				String cityname = (String)portmap.get("CITYNAME");
//				String bre = business.getBerth()==null?"":business.getBerth()+", ";
				String bre = "";
				breth_inpit = bre +port_sname+", "+PinYin.getPingYin(cityname,false,false).toUpperCase()+", "+BusinessState.APPLY_CHINA;
			}
			
			String auth_input ="";
			List authlist  =	assignmentService.getPersonOnAssignment(business.getId().toString(), String.valueOf(BusinessState.LONG_ONE));
			if (authlist!=null&&authlist.size()>0) {
				HashMap authmap = (HashMap)authlist.get(0);
				auth_input = authmap.get("NAME").toString();
				
			}
			  
			request.setAttribute("appno_view", appno_view.toUpperCase()); 
			request.setAttribute("appdate_view", appdate_view);
			request.setAttribute("businessname_view", businessname_view.toUpperCase());
			request.setAttribute("company_view", company_view.toUpperCase());
			request.setAttribute("vesselnameandtype_view", vesselnameandtype_view.toUpperCase());
			request.setAttribute("reg_imo_tonnage_view", reg_imo_tonnage_view.toUpperCase());
			request.setAttribute("plandateout_view", plandateout_view);
			request.setAttribute("checkdate_view", checkdate_view);
			request.setAttribute("countrysandports_input", countrysandports_input.toUpperCase());
			request.setAttribute("breth_inpit", breth_inpit.toUpperCase());
			request.setAttribute("auth_input", PinYin.getPingYin(auth_input,false,false).toUpperCase());
			request.setAttribute("certid_view", business.getCertid());
			request.setAttribute("inspection_input", business.getInspection1());
			_logger.info("getAllotPageView success-------");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	/**
	 * 查看业务详情
	 * 
	 * @return
	 * @throws AppException
	 */
	public String getExamineById() throws AppException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String aid=request.getParameter("Id");
		int aidint=Integer.parseInt(aid);
		try {
			List list = examineService.getBussinessById(aidint);
			if (null != list && list.size() > 0) {
				request.setAttribute("list", list.get(0));
			}
			
			_logger.info("get examine bussiness data success-------");
		} catch (Exception e) {
			throw new AppException("查看检查结果业务详情信息", e);
		}
		return SUCCESS;
	}
	/**
	 * 业务详情修改之前
	 * 
	 * @return
	 * @throws AppException
	 */
	public String getExaminePre() throws AppException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String aid=request.getParameter("Id");
		int aidint=Integer.parseInt(aid);
		try {
			List list = examineService.getBussinessById(aidint);
			if (null != list && list.size() > 0) {
				request.setAttribute("list", list.get(0));
			}
			
			_logger.info("get examine bussiness data success-------");
		} catch (Exception e) {
			throw new AppException("检查结果业务详情信息修改前查看信息", e);
		}
		return SUCCESS;
	}
	/**
	 * 业务详情修改
	 * 
	 * @return
	 * @throws AppException
	 */
	public String update() throws AppException {
		try {
		HttpServletRequest request = ServletActionContext.getRequest();	
		Business bus = null;
		String Id=request.getParameter("Id");
		bus = examineService.getBusinessObject(Long.parseLong(Id));
		bus.setBusinessname(bus.getBusinessname());
		bus.setAppno(bus.getAppno());
		bus.setAppdate(bus.getAppdate());
		bus.setDestinationcountry(bus.getDestinationcountry());
		bus.setPlandatein(plandatein);
		bus.setPlandateout(plandateout);
		bus.setCheckdate(bus.getCheckdate());
		bus.setBusinessstate(bus.getBusinessstate());
		bus.setLinkmanname(linkmanname);
		bus.setVesseltype(vesseltype);
		bus.setRegistry(registry);
		bus.setVesselno(bus.getVesselno());
		bus.setTonnage(Long.parseLong(tonnage));
		bus.setVesselname(vesselname);
		bus.setImo(imo);
		bus.setBerth(berth);
		bus.setCertification(bus.getCertification());
		bus.setPortid(bus.getPortid());
		bus.setCompany(company);
		bus.setRemark(bus.getRemark());
		bus.setAppname(bus.getAppname());
		bus.setFax(fax);
		bus.setEmail(email);
		bus.setPhone(tel);
		bus.setQualified(bus.getQualified());
		bus.setCertid(certid);
		bus.setStampid(bus.getStampid());
		bus.setAccountid(bus.getAccountid());
		bus.setOrgid(bus.getOrgid());
		bus.setInvoicetitle(bus.getInvoicetitle());
		bus.setInvoiceaddress(bus.getInvoiceaddress());
		bus.setInvoicepost(bus.getInvoicepost());
		bus.setInvoicer(bus.getInvoicer());
		bus.setInvoicetel(bus.getInvoicetel());
		bus.setSpecial(bus.getSpecial());
		bus.setLinkphone(bus.getLinkphone());
		bus.setTempno(bus.getTempno());
		bus.setPortorgid(bus.getPortorgid());		
		examineService.updateBusiness(bus);	
		System.out.println("修改业务信息");
		_logger.info("update examine bussiness data success-------");
		} catch (Exception e) {
			e.printStackTrace(); 
			throw new AppException("业务详情信息修改", e);
		}
		return SUCCESS;
	}
	/**
	 * 上传检查结果
	 * 
	 * @return
	 * @throws AppException
	 */
	public String save() throws AppException {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			Account accont = (Account) session.getAttribute(Constants.ACCOUNT_SESSION);
//			String orgId=session.getAttribute(Constants.ACCOUNT_PERSON_ORG).toString();	
//			String orgcode=session.getAttribute(Constants.ACCOUNT_ORG_ID4).toString();	
			String orgId= "";//机构唯一标示
			String orgcode = "";//机构4位标示
			Assignment assignment = assignmentService.getOrgIDsByBusinessid(appId);
			String idsss = assignment.getId().toString();
			orgId = assignment.getOrgto();
			orgcode = assignment.getOrg4(); 
			
			Examinelog log=new Examinelog();
			Operator op=new Operator();
			log.setBusinessid(Integer.parseInt(appId));
			op.setBusinessid(Long.parseLong(appId));
			log.setOperatorid(accont.getAccountId());
			op.setOperatorid(accont.getAccountId());
			log.setOrgId(orgId);
			log.setOrgcode(orgcode);
			op.setOperatortime(new java.sql.Date(System.currentTimeMillis()));
			op.setDisposetype(Long.parseLong(BusinessState.Business_CHECKED));
			log.setGypsymoth1(gypsymoth1);
			log.setGypsymoth2(gypsymoth2);
			log.setGypsymoth3(gypsymoth3);
			log.setGypsymoth4(gypsymoth4);
			log.setDoubt(doubt);
			log.setOperatortime(examinetime);
			String realpath = ServletActionContext.getServletContext().getRealPath("/attachment");
			System.out.println(realpath);
			if(certificate!=null){
				String cName="";
				Date date= new Date();
			    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			    String datestr=sdf.format(date);

				File savedir = new File(realpath+"\\/"+datestr);
				if(!savedir.exists()) savedir.mkdirs();
				for(int i = 0 ; i<certificate.length ; i++){
					int pos = certificateFileName[i].lastIndexOf( "." );
					String last= "."+certificateFileName[i].substring(pos+1);//获得后缀名	
					String fileName=new Date().getTime()+Math.random()*100000+i+last;//合成图片的名称
					//cName=cName+fileName;
					File savefile = new File(savedir, fileName);
					FileUtils.copyFile(certificate[i], savefile);
					cName+=datestr+"/"+fileName+",";
				}
					//cName.substring(0,cName.lastIndexOf(","));
					System.out.println(cName);
				log.setCertificate(cName);
				System.out.println("多证书上传文件成功！");
			}
			if(examrecord!=null){
				String eName="";
				Date date= new Date();
			    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			    String datestr=sdf.format(date);
			    File savedir = new File(realpath+"\\/"+datestr);
				if(!savedir.exists()) savedir.mkdirs();
				for(int i = 0 ; i<examrecord.length ; i++){	
					int pos = examrecordFileName[i].lastIndexOf( "." );
					String last= "."+examrecordFileName[i].substring(pos+1);//获得后缀名	
					String fileName=new Date().getTime()+Math.random()*100000+i+last;//合成图片的名称
					//eName=eName+fileName+",";
					File savefile = new File(savedir, fileName);
					FileUtils.copyFile(examrecord[i], savefile);
					eName+=datestr+"/"+fileName+",";
					//eName=eName+realpath+"\\"+datestr+eName;
				}
					//eName.substring(0,eName.lastIndexOf(","));
					System.out.println(eName);
					log.setExamrecord(eName);
				System.out.println("多检查记录表上传文件成功！");
			}
			if(workphoto!=null){
				String wName="";
				Date date= new Date();
			    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			    String datestr=sdf.format(date);
			    File savedir = new File(realpath+"\\/"+datestr);
				if(!savedir.exists()) savedir.mkdirs();
				for(int i = 0 ; i<workphoto.length ; i++){
					int pos = workphotoFileName[i].lastIndexOf( "." );
					String last= "."+workphotoFileName[i].substring(pos+1);//获得后缀名	
					String fileName=new Date().getTime()+Math.random()*100000+i+last;//合成图片的名称
					//wName=wName+fileName+",";
					File savefile = new File(savedir, fileName);
					FileUtils.copyFile(workphoto[i], savefile);
					wName+=datestr+"/"+fileName+",";
					//wName=realpath+"\\"+datestr+wName;
				}
				//wName.substring(0,wName.lastIndexOf(","));
				System.out.println(wName);
				log.setWorkphoto(wName);
				System.out.println("多工作照上传文件成功！");
			}
			if(shipinfo!=null){
				String sName="";
				Date date= new Date();
			    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			    String datestr=sdf.format(date);
			    File savedir = new File(realpath+"\\/"+datestr);
				if(!savedir.exists()) savedir.mkdirs();				
				for(int i = 0 ; i<shipinfo.length ; i++){
					int pos = shipinfoFileName[i].lastIndexOf( "." );
					String last= "."+shipinfoFileName[i].substring(pos+1);//获得后缀名	
					String fileName=new Date().getTime()+Math.random()*100000+i+last;//合成图片的名称
					//gName=gName+fileName+",";
					File savefile = new File(savedir, fileName);
					FileUtils.copyFile(shipinfo[i], savefile);
					sName+=datestr+"/"+fileName+",";
					//gName=realpath+"\\"+datestr+gName;
				}
				sName.substring(0,sName.lastIndexOf(","));
				System.out.println(sName);
				log.setShipinfo(sName);
				System.out.println("多船舶资料上传文件成功！");
			}
			if(stopportinfo!=null){
				String sName="";
				Date date= new Date();
			    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			    String datestr=sdf.format(date);
			    File savedir = new File(realpath+"\\/"+datestr);
				if(!savedir.exists()) savedir.mkdirs();				
				for(int i = 0 ; i<stopportinfo.length ; i++){
					int pos = stopportinfoFileName[i].lastIndexOf( "." );
					String last= "."+stopportinfoFileName[i].substring(pos+1);//获得后缀名	
					String fileName=new Date().getTime()+Math.random()*100000+i+last;//合成图片的名称
					//gName=gName+fileName+",";
					File savefile = new File(savedir, fileName);
					FileUtils.copyFile(stopportinfo[i], savefile);
					//gName=realpath+"\\"+datestr+gName;
				}
				sName.substring(0,sName.lastIndexOf(","));
				System.out.println(sName);
				log.setStopportinfo(sName);
				System.out.println("多船舶停靠港清单上传成功！");
			}
			
			examineService.saveExamine(log,op,nationid,appId,portName,stopTime,anationid,aportName,astopTime);
			_logger.info("upload examine success-------");
		} catch (Exception e) {
			throw new AppException("提交检查结果", e);

		}
		return SUCCESS;
	}
	
	/** ============================== add by guodingjun in 2011-04-06 ===============================*/
	/** ============================== add star ===============================*/
	/**
	 * 保存检查结果
	 * 
	 * @return
	 * @throws AppException
	 */
	public String updateFalse() throws AppException {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			Account accont = (Account) session.getAttribute(Constants.ACCOUNT_SESSION);
//			String orgId=session.getAttribute(Constants.ACCOUNT_PERSON_ORG).toString();	
//			String orgcode=session.getAttribute(Constants.ACCOUNT_ORG_ID4).toString();	
			String orgId= "";//机构唯一标示
			String orgcode = "";//机构4位标示
			Assignment assignment = assignmentService.getOrgIDsByBusinessid(appId);
			System.out.println("===========" + appId);
			orgId = assignment.getOrgto();
			orgcode = assignment.getOrg4(); 
			
			Examinelog log=new Examinelog();
//			Operator op=new Operator();
			System.out.println("====================="+id);
			log.setId(id);
			log.setBusinessid(Integer.parseInt(appId));
//			op.setBusinessid(Long.parseLong(appId));
			log.setOperatorid(accont.getAccountId());
//			op.setOperatorid(accont.getAccountId());
			log.setOrgId(orgId);
			log.setOrgcode(orgcode);
//			op.setOperatortime(new java.sql.Date(System.currentTimeMillis()));
//			op.setDisposetype(Long.parseLong(BusinessState.Business_CHECKED));
			log.setGypsymoth1(gypsymoth1);
			log.setGypsymoth2(gypsymoth2);
			log.setGypsymoth3(gypsymoth3);
			log.setGypsymoth4(gypsymoth4);
			log.setDoubt(doubt);
			log.setOperatortime(examinetime);
			String realpath = ServletActionContext.getServletContext().getRealPath("/attachment");
			System.out.println(realpath);
			if(certificate!=null){
				String cName="";
				Date date= new Date();
			    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			    String datestr=sdf.format(date);

				File savedir = new File(realpath+"\\/"+datestr);
				if(!savedir.exists()) savedir.mkdirs();
				for(int i = 0 ; i<certificate.length ; i++){
					int pos = certificateFileName[i].lastIndexOf( "." );
					String last= "."+certificateFileName[i].substring(pos+1);//获得后缀名	
					String fileName=new Date().getTime()+Math.random()*100000+i+last;//合成图片的名称
					//cName=cName+fileName;
					File savefile = new File(savedir, fileName);
					FileUtils.copyFile(certificate[i], savefile);
					cName+=datestr+"/"+fileName+",";
				}
					//cName.substring(0,cName.lastIndexOf(","));
					System.out.println(cName);
				log.setCertificate(cName);
				System.out.println("多证书上传文件成功！");
			}
			if(examrecord!=null){
				String eName="";
				Date date= new Date();
			    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			    String datestr=sdf.format(date);
			    File savedir = new File(realpath+"\\/"+datestr);
				if(!savedir.exists()) savedir.mkdirs();
				for(int i = 0 ; i<examrecord.length ; i++){	
					int pos = examrecordFileName[i].lastIndexOf( "." );
					String last= "."+examrecordFileName[i].substring(pos+1);//获得后缀名	
					String fileName=new Date().getTime()+Math.random()*100000+i+last;//合成图片的名称
					//eName=eName+fileName+",";
					File savefile = new File(savedir, fileName);
					FileUtils.copyFile(examrecord[i], savefile);
					eName+=datestr+"/"+fileName+",";
					//eName=eName+realpath+"\\"+datestr+eName;
				}
					//eName.substring(0,eName.lastIndexOf(","));
					System.out.println(eName);
					log.setExamrecord(eName);
				System.out.println("多检查记录表上传文件成功！");
			}
			if(workphoto!=null){
				String wName="";
				Date date= new Date();
			    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			    String datestr=sdf.format(date);
			    File savedir = new File(realpath+"\\/"+datestr);
				if(!savedir.exists()) savedir.mkdirs();
				for(int i = 0 ; i<workphoto.length ; i++){
					int pos = workphotoFileName[i].lastIndexOf( "." );
					String last= "."+workphotoFileName[i].substring(pos+1);//获得后缀名	
					String fileName=new Date().getTime()+Math.random()*100000+i+last;//合成图片的名称
					//wName=wName+fileName+",";
					File savefile = new File(savedir, fileName);
					FileUtils.copyFile(workphoto[i], savefile);
					wName+=datestr+"/"+fileName+",";
					//wName=realpath+"\\"+datestr+wName;
				}
				//wName.substring(0,wName.lastIndexOf(","));
				System.out.println(wName);
				log.setWorkphoto(wName);
				System.out.println("多工作照上传文件成功！");
			}
			if(shipinfo!=null){
				String sName="";
				Date date= new Date();
			    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			    String datestr=sdf.format(date);
			    File savedir = new File(realpath+"\\/"+datestr);
				if(!savedir.exists()) savedir.mkdirs();				
				for(int i = 0 ; i<shipinfo.length ; i++){
					int pos = shipinfoFileName[i].lastIndexOf( "." );
					String last= "."+shipinfoFileName[i].substring(pos+1);//获得后缀名	
					String fileName=new Date().getTime()+Math.random()*100000+i+last;//合成图片的名称
					//gName=gName+fileName+",";
					File savefile = new File(savedir, fileName);
					FileUtils.copyFile(shipinfo[i], savefile);
					//gName=realpath+"\\"+datestr+gName;
				}
				sName.substring(0,sName.lastIndexOf(","));
				System.out.println(sName);
				log.setShipinfo(sName);
				System.out.println("多船舶资料上传文件成功！");
			}
			if(stopportinfo!=null){
				String sName="";
				Date date= new Date();
			    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			    String datestr=sdf.format(date);
			    File savedir = new File(realpath+"\\/"+datestr);
				if(!savedir.exists()) savedir.mkdirs();				
				for(int i = 0 ; i<stopportinfo.length ; i++){
					int pos = stopportinfoFileName[i].lastIndexOf( "." );
					String last= "."+stopportinfoFileName[i].substring(pos+1);//获得后缀名	
					String fileName=new Date().getTime()+Math.random()*100000+i+last;//合成图片的名称
					//gName=gName+fileName+",";
					File savefile = new File(savedir, fileName);
					FileUtils.copyFile(stopportinfo[i], savefile);
					sName+=datestr+"/"+fileName+",";
					//gName=realpath+"\\"+datestr+gName;
				}
				sName.substring(0,sName.lastIndexOf(","));
				System.out.println(sName);
				log.setStopportinfo(sName);
				System.out.println("多船舶停靠港清单上传成功！");
			}
			//先判断检查结果是否有记录，有则update，没有则直接添加
			List examineList = examineService.getExamineByBusinessId(appId);
			if(examineList.size()>0){
				examineService.updateExaminelog(log);
			}else{
				examineService.saveExamine(log);
			}
			//先判断检查结果停靠港口表中是否存在记录，如果存在则删除，不存在则直接添加
			List examPortList = examineService.getExamPortByBusinesssId(appId);
			if(examPortList.size()>0){
				System.out.println("存在港口");
				examineService.deleteExamPort(appId);
				examineService.saveExamPort( nationid, appId, portName, stopTime, anationid, aportName, astopTime);
			}else{
				System.out.println("不存在港口");
				examineService.saveExamPort( nationid, appId, portName, stopTime, anationid, aportName, astopTime);
			}
			//examineService.saveExamineByTest(log,op,nationid,appId,portName,stopTime,anationid,aportName,astopTime);
			//_logger.info("upload examine success-------");
		} catch (Exception e) {
			throw new AppException("保存检查结果", e);

		}
		return SUCCESS;
	}
	
	/** ============================== add end ===============================*/
	
	public String select() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String aid=request.getParameter("Id");
		int aidint=Integer.parseInt(aid);
		try {
			List list = examineService.getBussinessById(aidint);
			if (null != list && list.size() > 0) {
				request.setAttribute("list", list.get(0));
			}
			List deslist = examineService.getDesCountryById(aidint);
			if (null != deslist && deslist.size() > 0) {
				request.setAttribute("deslist", deslist.get(0));
			}
			/** ========================  add by guodingjun in 2011-04-06 ========================*/
			/** ========================  add star ========================*/
			List examineList = examineService.getExamineByBusinessId(aid);
			System.out.println("===================" + examineList.size());
			if (null != examineList && examineList.size() > 0) {
				request.setAttribute("examineList", examineList.get(0));
			}
			/** ========================  add end ========================*/

			_logger.info("addPre success-------");
		} catch (Exception e) {
			throw new AppException("查看检查结果业务详情信息", e);
		}
		return SUCCESS;	
	}
	public AssignmentService getAssignmentService() {
		return assignmentService;
	}
	public void setAssignmentService(AssignmentService assignmentService) {
		this.assignmentService = assignmentService;
	}

}
