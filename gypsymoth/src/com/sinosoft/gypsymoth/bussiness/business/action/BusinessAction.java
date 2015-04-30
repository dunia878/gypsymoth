package com.sinosoft.gypsymoth.bussiness.business.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sinosoft.gypsymoth.bussiness.assignment.service.AssignmentService;
import com.sinosoft.gypsymoth.bussiness.business.service.BusinessService;
import com.sinosoft.gypsymoth.bussiness.business.service.ChargesService;
import com.sinosoft.gypsymoth.bussiness.client.service.ClientService;
import com.sinosoft.gypsymoth.bussiness.examine.service.ExamineService;
import com.sinosoft.gypsymoth.bussiness.organization.service.OrganizationService;
import com.sinosoft.gypsymoth.bussiness.person.service.PersonService;
import com.sinosoft.gypsymoth.bussiness.register.service.RegisterService;
import com.sinosoft.gypsymoth.bussiness.system.service.AccountService;
import com.sinosoft.gypsymoth.exception.AppException;
import com.sinosoft.gypsymoth.pojo.Account;
import com.sinosoft.gypsymoth.pojo.Assignment;
import com.sinosoft.gypsymoth.pojo.Business;
import com.sinosoft.gypsymoth.pojo.BusinessDesCountry;
import com.sinosoft.gypsymoth.pojo.Client;
import com.sinosoft.gypsymoth.pojo.Config;
import com.sinosoft.gypsymoth.pojo.OrganizationLevel;
import com.sinosoft.gypsymoth.pojo.Payment;
import com.sinosoft.gypsymoth.utils.BusinessState;
import com.sinosoft.gypsymoth.utils.Constants;
import com.sinosoft.gypsymoth.utils.Pagination;
import com.sinosoft.gypsymoth.utils.PinYin;
import com.sinosoft.gypsymoth.utils.StringUtils;

public class BusinessAction extends ActionSupport {
	
private final Logger _logger = Logger.getLogger(BusinessAction.class);
	
	private BusinessService businessService;
	private PersonService personService;
	private AssignmentService assignmentService;
	private AccountService accountService;
	private RegisterService registerService;
	private ChargesService chargesService;
	private OrganizationService organizationService;
	private ClientService clientService;
	private ExamineService examineService;
	
	private Long businessid;
	private String firstport;
	private String firstcountry;
	private String secondport;
	private String secondcountry;
	private BusinessDesCountry busdesCountry;
	private String id;
	private Business business;
	private String businessname;
	private String appno;
	private String appname;
	private Date appdate;
	private Long destinationcountry;
	private Date plandatein;
	private Date plandateout;
	private Date checkdate;
	private Long businessstate;
	private String linkmanname;
	private String vesseltype;
	private String registry;
	private String vesselno;
	private Long tonnage;
	private String vesselname;
	private String imo;
	private String berth;
	private String certification;
	private String portid;
	private String company;
	private String remark;
	private String phone;
	private String email;
	private String fax;
	private String invoicetitle;
	private String invoiceaddress;
	private String invoicepost;
	private String invoicer;
	private String invoicetel;
	private String special;
	private String linkphone;
	private String phone_begin;
	private String phone_end;
	private String invoicetel_end;
	private String invoicetel_begin;
	private String tempno;
	private String portorgid;
	
	private String[] destinationcountrys;
	private String[] ports;
	private String[] countrys;
	
	private String actionName;
	private String right_id;
	
	private Integer qualified;
	private String certid;
	private String stampid;
	private String goPage;
	
	public String getGoPage() {
		return goPage;
	}
	public void setGoPage(String goPage) {
		this.goPage = goPage;
	}
	
	public Integer getQualified() {
		return qualified;
	}
	public void setQualified(Integer qualified) {
		this.qualified = qualified;
	}
	public String getCertid() {
		return certid;
	}
	public void setCertid(String certid) {
		this.certid = certid;
	}
	public String getStampid() {
		return stampid;
	}
	public void setStampid(String stampid) {
		this.stampid = stampid;
	}
	public String getRight_id() {
		return right_id;
	}
	public void setRight_id(String right_id) {
		this.right_id = right_id;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public BusinessService getBusinessService() {
		return businessService;
	}
	public void setBusinessService(BusinessService businessService) {
		this.businessService = businessService;
	}
	public Business getBusiness() {
		return business;
	}
	public void setBusiness(Business business) {
		this.business = business;
	}
	public String getBusinessname() {
		return businessname;
	}
	public void setBusinessname(String businessname) {
		this.businessname = businessname;
	}
	public String getAppno() {
		return appno;
	}
	public void setAppno(String appno) {
		this.appno = appno;
	}
	public String getAppname() {
		return appname;
	}
	public void setAppname(String appname) {
		this.appname = appname;
	}
	public Date getAppdate() {
		return appdate;
	}
	public void setAppdate(Date appdate) {
		this.appdate = appdate;
	}
	public Long getDestinationcountry() {
		return destinationcountry;
	}
	public void setDestinationcountry(Long destinationcountry) {
		this.destinationcountry = destinationcountry;
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
	public Date getCheckdate() {
		return checkdate;
	}
	public void setCheckdate(Date checkdate) {
		this.checkdate = checkdate;
	}
	public Long getBusinessstate() {
		return businessstate;
	}
	public void setBusinessstate(Long businessstate) {
		this.businessstate = businessstate;
	}
	public String getLinkmanname() {
		return linkmanname;
	}
	public void setLinkmanname(String linkmanname) {
		this.linkmanname = linkmanname;
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
	public String getVesselno() {
		return vesselno;
	}
	public void setVesselno(String vesselno) {
		this.vesselno = vesselno;
	}
	public Long getTonnage() {
		return tonnage;
	}
	public void setTonnage(Long tonnage) {
		this.tonnage = tonnage;
	}
	public String getVesselname() {
		return vesselname;
	}
	public void setVesselname(String vesselname) {
		this.vesselname = vesselname;
	}
	public String getImo() {
		return imo;
	}
	public void setImo(String imo) {
		this.imo = imo;
	}
	public String getBerth() {
		return berth;
	}
	public void setBerth(String berth) {
		this.berth = berth;
	}
	public String getCertification() {
		return certification;
	}
	public void setCertification(String certification) {
		this.certification = certification;
	}
	public String getPortid() {
		return portid;
	}
	public void setPortid(String portid) {
		this.portid = portid;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	

	
	
	public String savebusiness()throws AppException{
		HttpServletRequest request = ServletActionContext.getRequest();
		Map sessionmap = ActionContext.getContext().getSession();
		List<BusinessDesCountry> businessDesCountryList = new ArrayList<BusinessDesCountry>();
		Account account = (Account) sessionmap.get(Constants.ACCOUNT_SESSION);
		String accountid = account.getAccountId();	//账号ID
		Date date = new Date();
		try {
			
			
			Business bus = new Business();
			if(id!=null){
				bus.setId(Long.valueOf(id)); 
			}
			if (businessname==null||businessname.equals("")) {
				businessname = request.getParameter("businessname_hidden");
			}
			
			bus.setAppname(appname);
			bus.setBusinessname(businessname);
//			bus.setAppno(appno);
			bus.setAppdate(date); 
			bus.setBerth(berth); 
			bus.setBusinessstate(Long.valueOf(BusinessState.BUSINESS_TEMP));
			bus.setCertification(certification);
		
			bus.setCheckdate(checkdate);
			bus.setCompany(company);
//			bus.setDestinationcountry(destinationcountry);
			bus.setEmail(email);
			bus.setFax(fax);
			bus.setImo(imo);
			bus.setLinkmanname(linkmanname);
			if(phone_begin!=null&&!phone_begin.equals("")&&phone_end!=null&&!phone_end.equals("")){
				phone = phone_begin+"-"+phone_end;
			}
			bus.setPhone(phone);
			bus.setPlandatein(plandatein);
			bus.setPlandateout(plandateout);
			bus.setPortid(portid);
			bus.setRegistry(registry);
			bus.setRemark(remark);
			bus.setTonnage(tonnage);
			bus.setVesselname(vesselname);
			bus.setVesselno(vesselno);
			bus.setVesseltype(vesseltype);
			bus.setAccountid(accountid);
			List portorglist = businessService.getOrg4Byportid(portid);	//根据港口ID得到对应的机构编号
			String orgid4 = "";
			if (portorglist!=null&&portorglist.size()>0) {
				HashMap portorgmap = (HashMap)portorglist.get(0);
				orgid4 = portorgmap.get("ORG_ID").toString();
				bus.setOrgid(orgid4);
				bus.setPortorgid(portorgmap.get("ID").toString());
			}
			bus.setInvoiceaddress(invoiceaddress);
			bus.setInvoicepost(invoicepost);
			bus.setInvoicer(invoicer);
			if(invoicetel_begin!=null&&!invoicetel_begin.equals("")&&invoicetel_end!=null&&!invoicetel_end.equals("")){
				invoicetel = invoicetel_begin+"-"+invoicetel_end;
			}
			bus.setInvoicetel(invoicetel);
			bus.setInvoicetitle(invoicetitle);
			bus.setSpecial(special);
			bus.setLinkphone(linkphone);
			bus.setQualified(0);
			for (int i = 0; i < destinationcountrys.length; i++) {
				if (destinationcountrys[i]!="-1") {
					BusinessDesCountry busdes = new BusinessDesCountry();
					busdes.setCountry(countrys[i]);
					busdes.setPort(ports[i]);
					busdes.setSelectindex(destinationcountrys[i]);
					busdes.setDesindex(i);
					businessDesCountryList.add(busdes);
				}
			}
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyMM");	//生产临时申请号
			String date_str = dateFormat.format(date);
			tempno= businessService.nextTempNo(date_str,orgid4);
			bus.setTempno(tempno);
			
			List<Config> configlist = businessService.getConfigList();
			Iterator<Config> configit = configlist.iterator();
			while (configit.hasNext()) {
				Config config = configit.next();
				if (config.getName()!=null&&config.getName().equals("INSPECTION1")) {
					bus.setInspection1(config.getValue());
				}else if (config.getName()!=null&&config.getName().equals("INSPECTION2")) {
					bus.setInspection2(config.getValue());
				}else if(config.getName()!=null&&config.getName().equals("INSPECTIONDATE")){
					bus.setInspectiondate(config.getValue());
				}
			}
			 
			businessService.saveBusiness(bus, businessDesCountryList);
			
			id = bus.getId().toString(); 
			getBusinessById();
			
		} catch (Exception e) {
			throw new AppException("添加信息", e);
		}
		return "save";
	}
	
	public String apply()throws AppException{
		HttpServletRequest request = ServletActionContext.getRequest();
		List<BusinessDesCountry> businessDesCountryList = new ArrayList<BusinessDesCountry>();
		 
		String page_countrys = request.getParameter("page_countrys");
		String page_ports = request.getParameter("page_ports");
		
		try {		
			SimpleDateFormat dateFormat = new SimpleDateFormat("MMM. d, yyyy",Locale.US);
			Business bus = businessService.getBusinessById(Long.valueOf(id));
			
			List<BusinessDesCountry> busList = businessService.getBusinessDesCountry(Long.valueOf(id));
			String countrysandports_input = "";		//目的国及港口 
			if(busList!=null&&busList.size()>0){
				for (int i = 0; i < busList.size(); i++) {
					BusinessDesCountry businessDesCountry = busList.get(i); 
					if (businessDesCountry.getSelectindex()!=null&&!businessDesCountry.getSelectindex().equals("-1")) {
						String tempport = "";
						if (businessDesCountry.getPort()!=null&&!businessDesCountry.getPort().equals("")) {
//							tempport = businessDesCountry.getPort() +" / ";
						}
						countrysandports_input  = countrysandports_input + tempport  + businessDesCountry.getCountry()+"; ";
					}
				} 
				countrysandports_input = countrysandports_input.substring(0,countrysandports_input.length()-2);
			}
			
			 
			String appno_input = "";	//申请单号
			if (bus.getAppno()!=null) {
				appno_input = bus.getAppno();
			}
			String date_input ="";
			if (bus.getAppdate()!=null) {	//申请单日期 
				date_input = dateFormat.format(bus.getAppdate()) ;
			}
			
			
			String vesselnameandtype_input = "";	//船名及类型
			vesselnameandtype_input = bus.getVesselname()+" / "+bus.getVesseltype();
			
			String reg_imo_tonnage_input = "";	//船藉 IMO 吨位
			String regis_input = bus.getRegistry();
			
			String tonn = "";
			if (bus.getTonnage()!=null) {
				tonn = StringUtils.tonString(bus.getTonnage().toString());
			}
			 
			reg_imo_tonnage_input = regis_input+ " / "+bus.getImo()+" / "+tonn+" MT";
				 
			String inoutdate_input = "";	//计划入港 出港日期
			inoutdate_input = dateFormat.format(bus.getPlandatein())+" / "+dateFormat.format(bus.getPlandateout());
			
			
			String breth_inpit = "";	//船舶及港口城市 breth, tianjin, 天津, CHINA
			List portlist = registerService.getPortProCity(bus.getPortid()); 
			if (portlist!=null&&portlist.size()>0) {
				HashMap portmap = (HashMap)portlist.get(0);
				String port_sname = (String)portmap.get("PORT_SNAME");
				String cityname = (String)portmap.get("CITYNAME");
				String bre = bus.getBerth()==null?"":bus.getBerth()+", ";
				breth_inpit = bre +port_sname+", "+cityname.toUpperCase()+", "+BusinessState.APPLY_CHINA;
			}
			 
			String checkdate_input = "";	//检查日期
			checkdate_input = dateFormat.format(bus.getCheckdate());
			String link_input = "";	//联系人 联系电话
			String phone_input  = "";
			if (bus.getPhone()!=null&&!bus.getPhone().equals("")) {
				phone_input = " , " + bus.getPhone();
			}
			link_input = bus.getLinkmanname()+phone_input; 
			
			String inspection2_input = bus.getInspection2();
			String inspectiondate_input = bus.getInspectiondate();
			
			request.setAttribute("businessname", bus.getBusinessname().toUpperCase());
			request.setAttribute("company", bus.getCompany().toUpperCase());
			request.setAttribute("appno_input", appno_input.toUpperCase());
			request.setAttribute("date_input", date_input);
			request.setAttribute("countrysandports_input", countrysandports_input.toUpperCase()); 
			request.setAttribute("vesselnameandtype_input", vesselnameandtype_input.toUpperCase());
			request.setAttribute("reg_imo_tonnage_input", reg_imo_tonnage_input.toUpperCase());
			request.setAttribute("breth_inpit", breth_inpit.toUpperCase());
			request.setAttribute("link_input", link_input.toUpperCase());
			request.setAttribute("inoutdate_input", inoutdate_input);
			request.setAttribute("checkdate_input", checkdate_input); 
			request.setAttribute("inspection2_input", inspection2_input);
			request.setAttribute("inspectiondate_input", inspectiondate_input);
			
		} catch (Exception e) {
			throw new AppException("预览", e);
			
		}
		return SUCCESS;
	}
	
	public String payment()throws AppException{
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			Business bus = businessService.getBusinessById(Long.valueOf(id));
			appno = bus.getAppno();
			invoicetitle = bus.getInvoicetitle();
			invoiceaddress = bus.getInvoiceaddress();
			invoicepost = bus.getInvoicepost();
			invoicer = bus.getInvoicer();
			invoicetel = bus.getInvoicetel();
			special = bus.getSpecial();
			List tonlist = businessService.getShipPaymentList(bus.getVesseltype());  
			List paylist = new ArrayList();
			request.setAttribute("ton", bus.getTonnage());
			request.setAttribute("tonlist", tonlist);
		} catch (Exception e) {
			throw new AppException("发票", e);
			
		}
		return SUCCESS;

	}
	
	
	/**
	 * 申请新业务
	 * @return
	 */
	public String businessApply()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Map sessionmap = ActionContext.getContext().getSession();
		List businessnamelist = null;
		List nationList = null; 
		List promaryList = null; 
		request.setAttribute("currPageb", goPage);
		Locale local_language = (Locale) session.getAttribute("WW_TRANS_I18N_LOCALE");
		String language = "CN"; 
		if(local_language!=null){
			language =  local_language.getCountry();
		} 
		
		Account account = (Account) sessionmap.get(Constants.ACCOUNT_SESSION);
		String accounttype = account.getAccountType().toString();
		try {
				Client client = (Client) sessionmap.get(Constants.ACCOUNT_CLIENT);
				businessnamelist = clientService.getClientListById(client.getClientId());
			nationList = registerService.getNation();
			promaryList = registerService.getPromarylistHavePort(); 
		} catch (Exception e) {
			e.printStackTrace();
		} 
		request.setAttribute("language", language);
		request.setAttribute("nationList", nationList);
		request.setAttribute("promaryList", promaryList);
		request.setAttribute("businessnamelist", businessnamelist);
		
		return SUCCESS;
	}
	
	
	/**
	 * 城市及港口的ajax查询
	 * @return
	 */
	public String ajaxSelect()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		String selecttype =request.getParameter("selecttype");
		String promaryid = request.getParameter("promaryid");
		String cityid = request.getParameter("cityid");
		List cityList = null;
		List portList = null;
		try {
			if (selecttype.equals("0")) {	//按省的ID查询下属市
				if(promaryid!=null){
					cityList = registerService.getCitylistHavePort(promaryid);
				}
				request.setAttribute("cityList", cityList);
			}else if (selecttype.equals("1")) {
				if(cityid!=null){
					portList = registerService.getPortlistByProandCity(promaryid, cityid);
				} 
				request.setAttribute("portList", portList); 
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("selecttype", selecttype);
		return SUCCESS;
	}
	

    public String getBusinessByPage() throws AppException{
    	
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Map sessionmap = ActionContext.getContext().getSession();
		Locale local_language = (Locale) session.getAttribute("WW_TRANS_I18N_LOCALE");
		String language = "CN"; 
		if(local_language!=null){
			language =  local_language.getCountry();
		} 
		//分页配置
		Pagination p = new Pagination(0,0,0);
		String currPage=(String)request.getParameter("goPage");
		if(currPage==null){  
			currPage="1";//” currPage”是当前页数
		}
		request.setAttribute("currPagea", currPage);
		_logger.info("currPage:"+currPage);
		Integer currPage1 = Integer.parseInt(currPage);
		HashMap map = new HashMap();
		try {
			Account account = (Account) sessionmap.get(Constants.ACCOUNT_SESSION);
			String accounttype = account.getAccountType().toString();
			businessstate = Long.valueOf(BusinessState.BUSINESS_TEMP);
			map.put("state", businessstate);
			List list = null;
			
			if (accounttype!=null&&accounttype.equals("1")) {	//用户	查看本机构及本机构下级机构的内容
				String is_child = (String) sessionmap.get(Constants.ACCOUNT_PERSON_IS_CHILD);	//得到用户机构等级
				String org4 = (String) sessionmap.get(Constants.ACCOUNT_ORG_ID4); //当前用户的4位机构ID
				map.put("is_child", is_child);
				map.put("accountid", account.getAccountId());
				map.put("org4",org4);
				
				Integer totleCount = businessService.getBusinessListCOUNT(map);
				p.getPagination(request,totleCount, currPage1,null,null,null);
				int numOfEachPage =	Constants.NUMOFEACHPAGE;
				int begin = p.getRownum_begin(numOfEachPage, currPage1);
				list =  businessService.getBusinessList(begin, numOfEachPage, map);
			}else if (accounttype!=null&&accounttype.equals("2")) {	//客户	查看自己提交的内容
				Client client = (Client)sessionmap.get(Constants.ACCOUNT_CLIENT);
				String clientname = client.getCoEnName();
				map.put("businessname", clientname);
				Integer totleCount = businessService.getBusinessListClientCOUNT(map);
				p.getPagination(request,totleCount, currPage1,null,null,null);
				int numOfEachPage =	Constants.NUMOFEACHPAGE;
				int begin = p.getRownum_begin(numOfEachPage, currPage1);
				list =  businessService.getBusinessListClient(begin, numOfEachPage, map);
			}
			
			List portcitylist = organizationService.getCityPortList();
			request.setAttribute("portcitylist", portcitylist); 
			request.setAttribute("list", list);
			request.setAttribute("language", language);
			_logger.info("get all role by page success-------"); 
		} catch (Exception e) {
			throw new AppException("分页查询角色", e);
		}
		this.actionName = "getBusinessByPage";
		return SUCCESS;
	}
    
   	public String searchBusiness() throws Exception
   	{ 
   		HttpServletRequest request = ServletActionContext.getRequest();
   		HttpSession session = request.getSession();
   		Map sessionmap = ActionContext.getContext().getSession();
   		Locale local_language = (Locale) session.getAttribute("WW_TRANS_I18N_LOCALE");
		String language = "CN"; 
		if(local_language!=null){
			language =  local_language.getCountry();
		} 
   		String bustate = request.getParameter("businessstate_search"); 
   		
   		String vesselname_form = request.getParameter("vesselname_form");
   		String portcity_form = request.getParameter("portcity_form"); 
   		String appno_form = request.getParameter("appno_form");
   		String tempno_form = request.getParameter("tempno_form");
   		String appdate_begin = request.getParameter("appdate_begin");
   		String appdate_end = request.getParameter("appdate_end");
   		
   		HashMap map = new HashMap(); 
   		List list = null;
   		if(vesselname_form != null) {
   			//查询忽略大小写
   			map.put("vesselname", vesselname_form.toUpperCase());
   		}else {
   			map.put("vesselname", vesselname_form);
   		}
   		if (portcity_form!=null&&!portcity_form.equals("")&&!portcity_form.equals("-1")) {
   			map.put("portcity", portcity_form);
		}
   		map.put("appno", appno_form); 
   		if(tempno_form != null) {
   			//忽略 临时编号 TEMP的大小写
   			map.put("tempno", tempno_form.toUpperCase());
   		}else {
   			map.put("tempno", tempno_form);
   		}map.put("appdate_begin", appdate_begin);
   		map.put("appdate_end", appdate_end);
   		if(bustate!=null&&!bustate.equals("")){
   			map.put("state", bustate);
   		}
   		
   		
   		
   		Pagination p = new Pagination(0,0,0); 
		String currPage=(String)request.getParameter("goPage"); 
		if(currPage==null){  
			currPage="1";//” currPage”是当前页数
		}
		Integer currPage1 = Integer.parseInt(currPage);
		_logger.info("searchBusiness begin");
		try {
			Account account = (Account) sessionmap.get(Constants.ACCOUNT_SESSION);
			String accounttype = account.getAccountType().toString();
			
			if (accounttype!=null&&accounttype.equals("1")) {	//用户	查看本机构及本机构下级机构的内容
				String is_child = (String) sessionmap.get(Constants.ACCOUNT_PERSON_IS_CHILD);	//得到用户机构等级
				String org4 = (String) sessionmap.get(Constants.ACCOUNT_ORG_ID4); //当前用户的4位机构ID
				map.put("is_child", is_child);
				map.put("accountid", account.getAccountId());
				map.put("org4",org4);
				
				Integer totleCount = businessService.getBusinessListCOUNT(map);
				p.getPagination(request,totleCount, currPage1,null,null,null);
				int numOfEachPage =	Constants.NUMOFEACHPAGE;
				int begin = p.getRownum_begin(numOfEachPage, currPage1);
				list =  businessService.getBusinessList(begin, numOfEachPage, map);
			}else if (accounttype!=null&&accounttype.equals("2")) {	//客户	查看自己提交的内容
				Client client = (Client)sessionmap.get(Constants.ACCOUNT_CLIENT);
				String clientname = client.getCoEnName();
				map.put("businessname", clientname);
				Integer totleCount = businessService.getBusinessListClientCOUNT(map);
				p.getPagination(request,totleCount, currPage1,null,null,null);
				int numOfEachPage =	Constants.NUMOFEACHPAGE;
				int begin = p.getRownum_begin(numOfEachPage, currPage1);
				list =  businessService.getBusinessListClient(begin, numOfEachPage, map);
			}
			
			
	   		_logger.info("searchBusiness end");
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		request.setAttribute("list", list); 
		request.setAttribute("vesselname_form", vesselname_form);
		request.setAttribute("portcity_form", portcity_form); 
		request.setAttribute("appno_form", appno_form);
		request.setAttribute("portcity_hidden", portcity_form);
		request.setAttribute("tempno_form", tempno_form);
   		request.setAttribute("appdate_begin", appdate_begin);
   		request.setAttribute("appdate_end", appdate_end);
   		request.setAttribute("language", language); 
   		this.actionName = "searchBusiness";
   		
   		if (bustate.equals(BusinessState.BUSINESS_TEMP)) {
   			List portcitylist = organizationService.getCityPortList();
			request.setAttribute("portcitylist", portcitylist);
   			return "success_list";
		}else if(bustate.equals(BusinessState.BUSINESS_ACCEPTWAIT)){
			List portcitylist = organizationService.getCityPortList();
			request.setAttribute("portcitylist", portcitylist);
			return "success_acceptlist";
		} 
   		return ERROR;  
   	}
	

    /**
     * 提交业务单
     * @return
     * @throws Exception
     */
	public String commit() throws Exception{
		
		HttpServletRequest request = ServletActionContext.getRequest();
		Map seMap = ActionContext.getContext().getSession();
		List<BusinessDesCountry> businessDesCountryList = new ArrayList<BusinessDesCountry>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMM");
		Date date = new Date();
		try {
			String accountid = request.getParameter("accountid");
			if(accountid==null||accountid.equals("")){
				Account account  = (Account)seMap.get(Constants.ACCOUNT_SESSION);
				accountid = account.getAccountId();
			}
			Business bus = new Business();
			if(id!=null){
				bus.setId(Long.valueOf(id)); 
			}
			if (businessname==null||businessname.equals("")) {
				businessname = request.getParameter("businessname_hidden");
			}
			bus.setAppname(appname);
			bus.setBusinessname(businessname);
//			bus.setAppno(appno);
			bus.setAppdate(date);
			bus.setBerth(berth); 
			bus.setBusinessstate(Long.valueOf(BusinessState.BUSINESS_ACCEPTWAIT));
			bus.setCertification(certification);
			bus.setCheckdate(checkdate);
			bus.setCompany(company);
//			bus.setDestinationcountry(destinationcountry);
			bus.setEmail(email);
			bus.setFax(fax);
			bus.setImo(imo);
			bus.setLinkmanname(linkmanname);
			if(phone_begin!=null&&!phone_begin.equals("")&&phone_end!=null&&!phone_end.equals("")){
				phone = phone_begin+"-"+phone_end;
			}
			bus.setPhone(phone);
			bus.setPlandatein(plandatein);
			bus.setPlandateout(plandateout);
			bus.setPortid(portid);
			bus.setRegistry(registry);
			bus.setRemark(remark);
			bus.setTonnage(tonnage);
			bus.setVesselname(vesselname);
			bus.setVesselno(vesselno);
			bus.setVesseltype(vesseltype);
			bus.setAccountid(accountid);
			bus.setInvoiceaddress(invoiceaddress);
			bus.setInvoicepost(invoicepost);
			bus.setInvoicer(invoicer);
			bus.setQualified(0);
			if(invoicetel_begin!=null&&!invoicetel_begin.equals("")&&invoicetel_end!=null&&!invoicetel_end.equals("")){
				invoicetel = invoicetel_begin+"-"+invoicetel_end;
			}
			bus.setInvoicetel(invoicetel);
			bus.setInvoicetitle(invoicetitle);
			bus.setSpecial(special);
			bus.setLinkphone(linkphone);
			
			String orgid = "";	//根据港口ID得到对应的机构编号
			List portorglist = businessService.getOrg4Byportid(portid);	//根据港口ID得到对应的机构编号
			if (portorglist!=null&&portorglist.size()>0) {
				HashMap portorgmap = (HashMap)portorglist.get(0);
				orgid = portorgmap.get("ORG_ID").toString();
				bus.setOrgid(orgid);
				bus.setPortorgid(portorgmap.get("ID").toString());
			}
			
			for (int i = 0; i < destinationcountrys.length; i++) {
				if (destinationcountrys[i]!="-1") {
					BusinessDesCountry busdes = new BusinessDesCountry();
					busdes.setCountry(countrys[i]);
					busdes.setPort(ports[i]);
					busdes.setSelectindex(destinationcountrys[i]);
					busdes.setDesindex(i);
					businessDesCountryList.add(busdes);
				}
			}
			 
			List<Config> configlist = businessService.getConfigList();
			
			Iterator<Config> configit = configlist.iterator();
			while (configit.hasNext()) {
				Config config = configit.next();
				if (config.getName()!=null&&config.getName().equals("INSPECTION1")) {
					bus.setInspection1(config.getValue());
				}else if (config.getName()!=null&&config.getName().equals("INSPECTION2")) {
					bus.setInspection2(config.getValue());
				}else if(config.getName()!=null&&config.getName().equals("INSPECTIONDATE")){
					bus.setInspectiondate(config.getValue());
				}
			}
			String date_str = dateFormat.format(date);
			setBusinessCountrysandPorts(businessDesCountryList, request);
			
			Business temp = null;
			if (id!=null) {
				temp = businessService.getBusinessById(Long.valueOf(id));
			} 
			if (temp==null||temp.getBusinessstate()==0) {	//为防止重复提交，只在新保存、未提交的单子的情况下才会提交
				if (temp==null) {
					tempno = businessService.nextTempNo(date_str,orgid);
					bus.setTempno(tempno);
				}else {
					bus.setTempno(temp.getTempno());
				}
				
				//如果没从数据库取得检查方法，就从 language_zh_CN.properties文件当中获取到检查方法
				if(bus.getInspection1() == null || "".equals(bus.getInspection1())) {
					ResourceBundle rb = PropertyResourceBundle.getBundle("language",Locale.CHINA);
					bus.setInspection1(rb.getString("INSPECTION1"));
				}
				
				businessService.savecommitBusiness(bus, businessDesCountryList); 
			//} 
			 
			id = String.valueOf(bus.getId()); 
			List procityList = registerService.getPromaryAndCityByPortID(bus.getPortid());
			if(procityList!=null&&procityList.size()>0){
	    		 HashMap procitymap = (HashMap)procityList.get(0);
	    		 BigDecimal cityid_b = (BigDecimal)procitymap.get("CITYID");	//港口所在最下级城市
	    		 BigDecimal proid_b = (BigDecimal)procitymap.get("PROID");	//港口所在省份
	    		 String proid = String.valueOf(proid_b);
	    		 String cityid = String.valueOf(cityid_b); 
	    		 request.setAttribute("select_portid", bus.getPortid()); 
	    	     request.setAttribute("select_proid", proid);
	    	     request.setAttribute("select_cityid", cityid);
	    	     List procityportnameList = registerService.getPCPNameByPortID(proid, cityid, bus.getPortid());
	 			 if (procityportnameList!=null&&procityportnameList.size()>0) {
					HashMap namemap = (HashMap)procityportnameList.get(0);
					String proname = (String)namemap.get("PRONAME");
					String cityname = (String)namemap.get("CITYNAME");
					String portname = (String)namemap.get("PORT_NAME"); 
					request.setAttribute("proname", proname);
					request.setAttribute("cityname", cityname);
					request.setAttribute("portname", portname);
					
				}
			}  
			 
		} catch (Exception e) {
			throw new AppException("添加信息", e);
		}
		return SUCCESS;
	
}
	
	
	public String commitaftersave() throws AppException
	{
		
		HttpServletRequest request = ServletActionContext.getRequest();
		Map seMap = ActionContext.getContext().getSession();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMM");
		Date date = new Date();
		try {
			String accountid = request.getParameter("accountid");
			if(accountid==null||accountid.equals("")){
				Account account  = (Account)seMap.get(Constants.ACCOUNT_SESSION);
				accountid = account.getAccountId();
			}
			Business bus =  businessService.getBusinessById(Long.valueOf(id));
			if(id!=null){
				bus.setId(Long.valueOf(id)); 
			}
			if (businessname==null||businessname.equals("")) {
				businessname = request.getParameter("businessname_hidden");
			}
			
			String orgid = ""; //根据港口ID得到对应的机构编号
			List portorglist = businessService.getOrg4Byportid(bus.getPortid());	//根据港口ID得到对应的机构编号
			if (portorglist!=null&&portorglist.size()>0) {
				HashMap portorgmap = (HashMap)portorglist.get(0);
				orgid = portorgmap.get("ORG_ID").toString();
				bus.setOrgid(orgid);
				bus.setPortorgid(portorgmap.get("ID").toString());
			}
			
			appname = bus.getAppname();
			businessname = bus.getBusinessname();
			berth = bus.getBerth();
			certification = bus.getCertification();
			checkdate = bus.getCheckdate();
			company = bus.getCompany();
			email = bus.getEmail();
			fax = bus.getFax();
			imo = bus.getImo();
			linkmanname = bus.getLinkmanname();
			phone = bus.getPhone();
			plandatein = bus.getPlandatein();
			plandateout = bus.getPlandateout();
			portid = bus.getPortid();
			registry = bus.getRegistry();
			remark = bus.getRemark();
			tonnage	= bus.getTonnage();
			vesselname = bus.getVesselname();
			vesselno = bus.getVesselno();
			vesseltype = bus.getVesseltype();
			invoiceaddress = bus.getInvoiceaddress();
			invoicepost = bus.getInvoicepost();
			invoicer = bus.getInvoicer();
			invoicetel = bus.getInvoicetel();
			invoicetitle = bus.getInvoicetitle();
			special = bus.getSpecial();
			linkphone = bus.getLinkphone();
			appdate = bus.getAppdate();
			
			bus.setBusinessstate(Long.valueOf(BusinessState.BUSINESS_ACCEPTWAIT));
			List<BusinessDesCountry> businessDesCountryList  = businessService.getBusinessDesCountry(bus.getId());
			String date_str = dateFormat.format(date);
			setBusinessCountrysandPorts(businessDesCountryList, request);
			
			Business temp = null;
			if (id!=null) {
				temp = businessService.getBusinessById(Long.valueOf(id));
			} 
			if (temp==null||temp.getBusinessstate()==0) { 	//为防止重复提交，只在新保存、未提交的单子的情况下才会提交
				if (temp==null) {
					tempno = businessService.nextTempNo(date_str,orgid);
					bus.setTempno(tempno);
				}
				businessService.savecommitBusiness(bus, businessDesCountryList); 
			}
			id = String.valueOf(bus.getId()); 
			List procityList = registerService.getPromaryAndCityByPortID(bus.getPortid());
			if(procityList!=null&&procityList.size()>0){
	    		 HashMap procitymap = (HashMap)procityList.get(0);
	    		 BigDecimal cityid_b = (BigDecimal)procitymap.get("CITYID");	//港口所在最下级城市
	    		 BigDecimal proid_b = (BigDecimal)procitymap.get("PROID");	//港口所在省份
	    		 String proid = String.valueOf(proid_b);
	    		 String cityid = String.valueOf(cityid_b); 
	    		 request.setAttribute("select_portid", bus.getPortid()); 
	    	     request.setAttribute("select_proid", proid);
	    	     request.setAttribute("select_cityid", cityid);
	    	     List procityportnameList = registerService.getPCPNameByPortID(proid, cityid, bus.getPortid());
	 			 if (procityportnameList!=null&&procityportnameList.size()>0) {
					HashMap namemap = (HashMap)procityportnameList.get(0);
					String proname = (String)namemap.get("PRONAME");
					String cityname = (String)namemap.get("CITYNAME");
					String portname = (String)namemap.get("PORT_NAME"); 
					request.setAttribute("proname", proname);
					request.setAttribute("cityname", cityname);
					request.setAttribute("portname", portname);
					
				}
			}  
			 
		} catch (Exception e) {
			throw new AppException("保存后提交", e);
		}
		return SUCCESS;
		
	}
	 
	/**
	 * 更新申请单
	 * @return
	 * @throws AppException
	 */
	public String updataApply()throws AppException{
		HttpServletRequest request = ServletActionContext.getRequest();
		List<BusinessDesCountry> businessDesCountryList = new ArrayList<BusinessDesCountry>();
		Date date = new Date();
		try {
			if (businessname==null||businessname.equals("")) {
				businessname = request.getParameter("businessname_hidden");
			}
			String accountid = request.getParameter("accountid");
			Business bus = new Business();
			bus.setId(Long.valueOf(id));
			bus.setAppname(appname);
			bus.setBusinessname(businessname);
//			bus.setAppno(appno);
			bus.setAppdate(date); 
			bus.setBerth(berth); 
			bus.setBusinessstate(Long.valueOf(BusinessState.BUSINESS_TEMP));
			bus.setCertification(certification);
			bus.setCheckdate(checkdate);
			bus.setCompany(company);
//			bus.setDestinationcountry(destinationcountry); 
			bus.setEmail(email);
			bus.setFax(fax);
			bus.setImo(imo);
			bus.setLinkmanname(linkmanname);
			if(phone_begin!=null&&!phone_begin.equals("")&&phone_end!=null&&!phone_end.equals("")){
				phone = phone_begin+"-"+phone_end;
			}
			bus.setPhone(phone);
			bus.setPlandatein(plandatein);
			bus.setPlandateout(plandateout);
			bus.setPortid(portid);
			bus.setRegistry(registry);
			bus.setRemark(remark);
			bus.setTonnage(tonnage);
			bus.setVesselname(vesselname);
			bus.setVesselno(vesselno);
			bus.setVesseltype(vesseltype); 
			bus.setAccountid(accountid); 
			bus.setInvoiceaddress(invoiceaddress);
			bus.setInvoicepost(invoicepost);
			bus.setInvoicer(invoicer);
			if(invoicetel_begin!=null&&!invoicetel_begin.equals("")&&invoicetel_end!=null&&!invoicetel_end.equals("")){
				invoicetel = invoicetel_begin+"-"+invoicetel_end;
			}
			bus.setInvoicetel(invoicetel);
			bus.setInvoicetitle(invoicetitle);
			bus.setSpecial(special);
			bus.setLinkphone(linkphone);
			bus.setTempno(tempno);
			
			String orgid4 = "";	//根据港口ID得到对应的机构编号
			List portorglist = businessService.getOrg4Byportid(bus.getPortid());	//根据港口ID得到对应的机构编号
			if (portorglist!=null&&portorglist.size()>0) {
				HashMap portorgmap = (HashMap)portorglist.get(0);
				orgid4 = portorgmap.get("ORG_ID").toString();
				bus.setOrgid(orgid4);
				bus.setPortorgid(portorgmap.get("ID").toString());
			}
			
			for (int i = 0; i < destinationcountrys.length; i++) {
				if (destinationcountrys[i]!="-1") {
					BusinessDesCountry busdes = new BusinessDesCountry();
					busdes.setCountry(countrys[i]);
					busdes.setPort(ports[i]);
					busdes.setSelectindex(destinationcountrys[i]);
					busdes.setDesindex(i);
					businessDesCountryList.add(busdes);
				}
			}
			
			setBusinessCountrysandPorts(businessDesCountryList, request); 
			
			businessService.updateBusinessDetail(bus, businessDesCountryList);
			business = businessService.getBusinessById(Long.valueOf(id)); 
			
			businessApply(); 	//国家列表
    	 
			List procityList = registerService.getPromaryAndCityByPortID(business.getPortid());
			if(procityList!=null&&procityList.size()>0){
	    		 HashMap procitymap = (HashMap)procityList.get(0);
	    		 BigDecimal cityid_b = (BigDecimal)procitymap.get("CITYID");	//港口所在最下级城市
	    		 BigDecimal proid_b = (BigDecimal)procitymap.get("PROID");	//港口所在省份
	    		 String proid = String.valueOf(proid_b);
	    		 String cityid = String.valueOf(cityid_b); 
	    		 request.setAttribute("select_portid", business.getPortid());
	    	     request.setAttribute("select_proid", proid);
	    	     request.setAttribute("select_cityid", cityid);
			} 
			request.setAttribute("view", "0");	//保存结束后跳转到查看界面
			request.setAttribute("message", "保存成功");
			System.out.println("------------------" + goPage);
			request.setAttribute("currPagec", goPage);
		} catch (Exception e) {
			throw new AppException("添加信息", e);
		}
		return SUCCESS;
			
	}
	
	public String shenqing()throws AppException{
		
		return SUCCESS;
	}
	public String pass()throws AppException{
		HttpServletRequest request = ServletActionContext.getRequest();
		//分页配置
		Pagination p = new Pagination(0,0,0);
		String currPage=(String)request.getParameter("goPage");
		if(currPage==null){  
			currPage="1";//” currPage”是当前页数
		}
		_logger.info("currPage:"+currPage);
		Integer currPage1 = Integer.parseInt(currPage);
		try {
			
			Integer totleCount = businessService.getAllDataCount();
			p.getPagination(request,totleCount, currPage1,null,null,null);
			int numOfEachPage =	Constants.NUMOFEACHPAGE;
			int begin = p.getRownum_begin(numOfEachPage, currPage1);
			List<Business> list = businessService.getAllBusiness(begin, numOfEachPage);
			request.setAttribute("acceptlist", list);
			_logger.info("get all role by page success-------");
		} catch (Exception e) {
			throw new AppException("分页查询角色", e);
		}
		this.actionName = "getAcceptPage";
		return SUCCESS;
	}
	
	/**
	 * 待受理页面
	 * @return
	 * @throws AppException
	 */
	public String getAcceptPage()throws AppException{ 
		HttpServletRequest request = ServletActionContext.getRequest();
		Map sessionmap = ActionContext.getContext().getSession();
		
		_logger.info("getAcceptPage begin-------");
		Pagination p = new Pagination(0,0,0);	//分页配置
		String currPage=(String)request.getParameter("goPage");
		if(currPage==null){  
			currPage="1";//” currPage”是当前页数
		}
		Integer currPage1 = Integer.parseInt(currPage);
		HashMap map = new HashMap();
		List list = null;
		try {
			Account account = (Account) sessionmap.get(Constants.ACCOUNT_SESSION);
			String accounttype = account.getAccountType().toString();
			String operatortype = BusinessState.BUSINESS_ACCEPTWAIT;	//处理状态为未受理
			map.put("state", operatortype);
			
			if (accounttype!=null&&accounttype.equals("1")) {	//用户	查看本机构及本机构下级机构的内容
				String is_child = (String) sessionmap.get(Constants.ACCOUNT_PERSON_IS_CHILD);	//得到用户机构等级
				String org4 = (String) sessionmap.get(Constants.ACCOUNT_ORG_ID4); //当前用户的4位机构ID
				map.put("is_child", is_child);
				map.put("accountid", account.getAccountId());
				map.put("org4",org4);
				map.put("accountid", "-1");	//用户只能受理自己机构所在的业务
				
				Integer totleCount = businessService.getBusinessListCOUNT(map);
				p.getPagination(request,totleCount, currPage1,null,null,null);
				int numOfEachPage =	Constants.NUMOFEACHPAGE;
				int begin = p.getRownum_begin(numOfEachPage, currPage1);
				list =  businessService.getBusinessList(begin, numOfEachPage, map);
			}else if (accounttype!=null&&accounttype.equals("2")) {	//客户	查看自己提交的内容
				Client client = (Client)sessionmap.get(Constants.ACCOUNT_CLIENT);
				String clientname = client.getCoEnName();
				map.put("businessname", clientname);
				Integer totleCount = businessService.getBusinessListClientCOUNT(map);
				p.getPagination(request,totleCount, currPage1,null,null,null);
				int numOfEachPage =	Constants.NUMOFEACHPAGE;
				int begin = p.getRownum_begin(numOfEachPage, currPage1);
				list =  businessService.getBusinessListClient(begin, numOfEachPage, map);
			}
			 
			List portcitylist = organizationService.getCityPortList();
			request.setAttribute("portcitylist", portcitylist);
	   		request.setAttribute("list", list);
	   		
			_logger.info("getAcceptPage success-------");
		} catch (Exception e) {
			throw new AppException("分页查询角色", e);
		}
		this.actionName = "getAcceptPage";
		return SUCCESS;
	}

	/**
	 * 待分配页面
	 * @return
	 */
	public String getAllotPage()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		Map sessionmap = ActionContext.getContext().getSession();
		HttpSession session = request.getSession();
		
		String vesselname_form = request.getParameter("vesselname_form");
   		String portcity_form = request.getParameter("portcity_form");
   		String appno_form = request.getParameter("appno_form");
   		String allotstate_form = request.getParameter("allotstate_form");
   		String appdate_begin = request.getParameter("appdate_begin");
   		String appdate_end = request.getParameter("appdate_end");
   		String bustate = BusinessState.BUSINESS_ACCEPTSUCCESS;
		
		_logger.info("getAcceptPage begin-------");
		Pagination p = new Pagination(0,0,0);	//分页配置
		String currPage=(String)request.getParameter("goPage");
		if(currPage==null){  
			currPage="1";//” currPage”是当前页数
		}
		Integer currPage1 = Integer.parseInt(currPage);
		request.setAttribute("currPagea", currPage);
		try {
			Account account = (Account) sessionmap.get(Constants.ACCOUNT_SESSION);
			String personid = (String)sessionmap.get(Constants.ACCOUNT_PERSON_ID);
			String isadmin = (String)sessionmap.get(Constants.ACCOUNT_ISADMIN);	//如果是超级管理员用户
			HashMap map = new HashMap(); 
			
			if(vesselname_form != null) {
				//忽略大小写
				map.put("vesselname", vesselname_form.toUpperCase());
			}else {
				map.put("vesselname", vesselname_form);
			}
			map.put("portcity", portcity_form);
	   		if(appno_form != null) {
	   			//忽略大小写
	   			map.put("appno", appno_form.toUpperCase()); 
	   		}else {
	   			map.put("appno", appno_form); 
	   		}
	   		map.put("allotstate", allotstate_form);
	   		map.put("appdate_begin", appdate_begin);
	   		map.put("appdate_end", appdate_end);
	   		if(bustate!=null&&!bustate.equals("")){
	   			map.put("bustate", bustate);
	   		} 
	   		
			List list = new ArrayList();
			if(isadmin!=null&&isadmin.equals("1")){	//系统管理员用户
				Integer totleCount = assignmentService.getBusinessByPersonIDCountSYS(map);
				p.getPagination(request,totleCount, currPage1,null,null,null);
				int numOfEachPage =	Constants.NUMOFEACHPAGE;
				int begin = p.getRownum_begin(numOfEachPage, currPage1);  
				list = assignmentService.getBusinessByPersonIDSYS(begin, numOfEachPage,map);
				
			}else if(account.getAccountType()==1){	//如果为用户  person (除去系统管理员)
				
				Integer totleCount = assignmentService.getBusinessByPersonIDCount(personid,map);
				p.getPagination(request,totleCount, currPage1,null,null,null);
				int numOfEachPage =	Constants.NUMOFEACHPAGE;
				int begin = p.getRownum_begin(numOfEachPage, currPage1); 
				list = assignmentService.getBusinessByPersonID(begin, numOfEachPage, personid,map);
			}else {		//	客户	client
				// do nothing
			}
			
			List portcitylist = organizationService.getCityPortList();
			request.setAttribute("portcitylist", portcitylist); 
		
			request.setAttribute("appdate_begin", appdate_begin);
	   		request.setAttribute("appdate_end", appdate_end);
			request.setAttribute("vesselname_form", StringUtils.codeHtml(vesselname_form));
			request.setAttribute("portcity_form", StringUtils.codeHtml(portcity_form)); 
			request.setAttribute("appno_form", StringUtils.codeHtml(appno_form));
			request.setAttribute("allotstate_hide", StringUtils.codeHtml(allotstate_form)); 
			request.setAttribute("allotlist", list);
			request.setAttribute("isadmin", isadmin);
			_logger.info("getAcceptPage success-------"); 
			
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		this.actionName = "getAllotPage";
		return SUCCESS;
	}
	
	/**
	 * 分配业务详细页面
	 * @return
	 */
	public String getAllotPageDetail()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Map sessionmap = ActionContext.getContext().getSession();
		
		Account account = (Account) session.getAttribute(Constants.ACCOUNT_SESSION);
		String business_id = request.getParameter("id");
		String account_name = account.getAccountName();
		Integer account_type = account.getAccountType();
		request.setAttribute("currPageb", goPage); 
		String orgid = "";	//所属机构
		
		_logger.info("getAllotPageDetail begin-------");
		try {
			if(account_type!=null&&account_type==1)	{	//如果为	用户(Person)
				
				String allotright = (String)sessionmap.get(Constants.ACCOUNT_ALLOT_LEVEL);
				
				business = businessService.getBusinessById(Long.valueOf(business_id));
				List assignlist = assignmentService.getAssignmentlistByBusinessId(business.getId());
				String assignstate ="";
				if(assignlist!=null&&assignlist.size()>0){
					HashMap map = (HashMap)assignlist.get(0);
					BigDecimal temp= (BigDecimal)map.get("ASSIGNERSTATE");
					assignstate  = temp.toString(); 
				}
				
				List<BusinessDesCountry> businessDesCountryList  = businessService.getBusinessDesCountry(business.getId());
				setBusinessCountrysandPorts(businessDesCountryList, request);
				
				List procityList = registerService.getPortProCity(business.getPortid());
				if (procityList!=null&&procityList.size()>0) {
					HashMap promap = (HashMap)procityList.get(0);
					String proname = (String)promap.get("PRONAME");
					String cityname = (String)promap.get("CITYNAME");
					String portname = (String)promap.get("PORT_NAME");
					request.setAttribute("proname", proname);
					request.setAttribute("cityname", cityname);
					request.setAttribute("portname", portname);
				} 
				 
				request.setAttribute("assignstate", assignstate); 
				request.setAttribute("business_id", business_id); 
				request.setAttribute("account_name", account_name);
				request.setAttribute("allotright", allotright);
				request.setAttribute("orgid", orgid);
			}else{
				// 客户 (client)	do nothing 
			}
			_logger.info("getAllotPageDetail success-------");
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		
		return SUCCESS; 
	}
	
	
	/**
	 * 分配详情查看页面
	 * @return
	 */
	public String allotDetailOnly()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Map sessionmap = ActionContext.getContext().getSession();
		
		Account account = (Account) session.getAttribute(Constants.ACCOUNT_SESSION);
		String business_id = request.getParameter("id");
		Integer account_type = account.getAccountType();
		
		_logger.info("allotDetailOnly begin-------"); 
		try {
			List list = null;
			if(account_type!=null&&account_type==1)	{	//如果为	用户(Person)
				
				business = businessService.getBusinessById(Long.valueOf(business_id));
				request.setAttribute("business_id", business_id); 
				list =  assignmentService.getAssignmentWorkflow(business.getId());
				
				
				List<BusinessDesCountry> businessDesCountryList  = businessService.getBusinessDesCountry(business.getId());
				setBusinessCountrysandPorts(businessDesCountryList, request);
				
				 List procityList = registerService.getPortProCity(business.getPortid());
		    	  
				 if (procityList!=null&&procityList.size()>0) {
					HashMap promap = (HashMap)procityList.get(0);
					String proname = (String)promap.get("PRONAME");
					String cityname = (String)promap.get("CITYNAME");
					String portname = (String)promap.get("PORT_NAME");
					request.setAttribute("proname", proname);
					request.setAttribute("cityname", cityname);
					request.setAttribute("portname", portname);
				} 
				
			}else{
				// 客户 (client)	do nothing 
			} 
			showAccountname(business.getAccountid());
			request.setAttribute("list", list);
			_logger.info("allotDetailOnly success-------");
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		
		return SUCCESS; 
		
	}
	
	/**
	 * 分配业务单
	 * @return
	 */
	public	String toAllot(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Map sessionmap = ActionContext.getContext().getSession();
		String person_id = (String)sessionmap.get(Constants.ACCOUNT_PERSON_ID);
		String curr_is_child = (String)sessionmap.get(Constants.ACCOUNT_PERSON_IS_CHILD);
		
		_logger.info("toAllot begin");
		
		String allottype = request.getParameter("allottype");	// 0:分配协调员 ,	1:分配授权签字人 检查员
		Account account = (Account) session.getAttribute(Constants.ACCOUNT_SESSION);
		Date date = new Date();
		String businessid = request.getParameter("businessid");
		String orgfrom = (String)sessionmap.get(Constants.ACCOUNT_PERSON_ORG);
		
		String currindex = "";
		try {
			List numberlist = assignmentService.getAssignmentlistByBusinessId(Long.valueOf(businessid));
			
			if(numberlist!=null&&numberlist.size()>0){
				HashMap numap = (HashMap)numberlist.get(0);
				BigDecimal temp= (BigDecimal)numap.get("ASSIGNUMBER");
				int i = temp.intValue();
				i++;
				currindex  = String.valueOf(i);  
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR; 
		}
		
		try {
			if(allottype.equals("0")){	//分配协调员
				String coor_input_id = request.getParameter("coor_input_id");	// 分配协调员的person_ID
				List list = businessService.getOrgByPersonID(coor_input_id);
				String org4 = "";
				if(list!=null&&list.size()>0){ 
					HashMap map = (HashMap)list.get(0);
					String is_child  =(String)map.get("IS_CHILD");	//被分配的协调员的机构等级
					String orgto = (String) map.get("ORG_ID");	//被分配的协调员所在的机构ID
					org4 = (String)map.get("ORG4");
					long assignerstate = 1;
					if(is_child.equals("1")){	//二级协调员
						assignerstate = Long.valueOf(BusinessState.Allot_SECOND_COOR);
					}else if(is_child.equals("2")){	//三级协调员
						assignerstate = Long.valueOf(BusinessState.Allot_THIRD_COOR);
					}
					
					Assignment assignment = new Assignment();
					assignment.setAssignerstate(assignerstate);
					assignment.setBusinessid(Long.valueOf(businessid));
					assignment.setPersonto(coor_input_id);
					assignment.setAssigntime(date);
					assignment.setPersonfrom(person_id);
					assignment.setOrgto(orgto);
					assignment.setOrgfrom(orgfrom);
					assignment.setAssignrole(BusinessState.LONG_ZERO);	//协调员
					assignment.setIscomplete(BusinessState.LONG_ZERO);	//设置当前业务单为未完成分配	(此状态在分配结束后一起更新)
					assignment.setIsapplay(BusinessState.LONG_ONE);	//设置当前状态为已激活生效
					assignment.setOrg4(org4);
				  
					assignment.setAssignumber(Long.valueOf(currindex)); 	//设置当前操作数 +1
					List<Assignment> paraAsslist = new ArrayList<Assignment>();
					paraAsslist.add(assignment);
					assignmentService.updateAssignment(paraAsslist);  
					
					Payment payment =  chargesService.getPaymentByBusinessID(Long.valueOf(businessid));
					if (is_child.equals("1")) {
						payment.setFirstcomid(orgfrom);
						payment.setSecondcomid(orgto);
						chargesService.updatePayment(payment);
					}else if(is_child.equals("2")&&curr_is_child.equals("0")){	//由系统管理员分配
						payment.setFirstcomid(orgfrom); 
						OrganizationLevel organizationLevel = organizationService.getOrgLevelbyId(orgto);
						payment.setSecondcomid(organizationLevel.getParentId());
						chargesService.updatePayment(payment);
					}else if(is_child.equals("2")&&curr_is_child.equals("1")){	//由一级协调员分配
						OrganizationLevel organizationLevelfrom = organizationService.getOrgLevelbyId(orgfrom);
						payment.setFirstcomid(organizationLevelfrom.getParentId());
						OrganizationLevel organizationLevel = organizationService.getOrgLevelbyId(orgto);
						payment.setSecondcomid(organizationLevel.getParentId());
						chargesService.updatePayment(payment);
					} 
					
				}
				 
			}else if(allottype.equals("1")){	//分配检查人及授权签字人
			
				String org4 = "";
				String auth_input_id = request.getParameter("auth_input_id");	//授权签字人
				List list = businessService.getOrgByPersonID(auth_input_id);
				long assignerstate = 1;
				String orgto  = "";
				String is_child_auth = "";
				if(list!=null&&list.size()>0){
					HashMap map = (HashMap)list.get(0);
					is_child_auth  =(String)map.get("IS_CHILD");
					orgto = (String) map.get("ORG_ID");
					org4 = (String)map.get("ORG4");
				
					if(is_child_auth.equals("1")){	//二级检查人、授权签字人
						assignerstate = Long.valueOf(BusinessState.Allot_SECOND_INS);
					}else if(is_child_auth.equals("2")){	//三级检查人、授权签字人
						assignerstate = Long.valueOf(BusinessState.Allot_THIRD_INS);
					}
					
				}
				Assignment assignment = new Assignment();
				assignment.setAssignerstate(assignerstate);
				assignment.setBusinessid(Long.valueOf(businessid));
				assignment.setPersonto(auth_input_id);
				assignment.setAssigntime(date);
				assignment.setPersonfrom(person_id);
				assignment.setOrgto(orgto);
				assignment.setOrgfrom(orgfrom);
				assignment.setAssignrole(BusinessState.LONG_ONE);	//授权签字人
				assignment.setIscomplete(BusinessState.LONG_ZERO);	// (此状态在分配结束后一起更新)
				assignment.setIsapplay(BusinessState.LONG_ONE);	//设置为激活生效
				assignment.setOrg4(org4);
				assignment.setAssignumber(Long.valueOf(currindex)); 
				

				List<Assignment> paraAsslist = new ArrayList<Assignment>();
				paraAsslist.add(assignment);
				
				String ins_input_ids = request.getParameter("ins_input_id");	//检查员
				String[] ins_input_id = null;
				if(ins_input_ids.indexOf(",")==-1){
					ins_input_id = new String[1];
					ins_input_id[0] = ins_input_ids;
				}else {
					ins_input_id = ins_input_ids.split(",");
				}
				
				for (int i = 0; i < ins_input_id.length; i++) {
					
					List inslist = businessService.getOrgByPersonID(ins_input_id[i]);
					long insstate = 1;
					String insorgto  = "";
					if(inslist!=null&&inslist.size()>0){
						HashMap insmap = (HashMap)inslist.get(0);
						String is_child  =(String)insmap.get("IS_CHILD");
						insorgto = (String) insmap.get("ORG_ID"); 
					
						if(is_child.equals("1")){	//二级检查人、授权签字人
							insstate = Long.valueOf(BusinessState.Allot_SECOND_INS);
						}else if(is_child.equals("2")){	//三级检查人、授权签字人
							insstate = Long.valueOf(BusinessState.Allot_THIRD_INS);
						}
						
					}
					Assignment insassignment = new Assignment();
					insassignment.setAssignerstate(insstate);
					insassignment.setBusinessid(Long.valueOf(businessid));
					insassignment.setPersonto(ins_input_id[i]);
					insassignment.setAssigntime(date);
					insassignment.setPersonfrom(person_id);
					insassignment.setOrgto(insorgto);
					insassignment.setOrgfrom(orgfrom);
					insassignment.setAssignrole(BusinessState.LONG_TWO);	// 检查员
					insassignment.setIscomplete(BusinessState.LONG_ZERO);	//(此状态在分配结束后一起更新) 
					insassignment.setIsapplay(BusinessState.LONG_ONE);	//设置为激活生效
					insassignment.setOrg4(org4);
					insassignment.setAssignumber(Long.valueOf(currindex)); 
					
					paraAsslist.add(insassignment);
					
				}
				
				assignmentService.updateAssignment(paraAsslist);
				
				Business business = businessService.getBusinessById(Long.valueOf(businessid));
				business.setBusinessstate(Long.valueOf(BusinessState.Business_ALLOTED)); 
				businessService.updateBusinessToState(business);
			
				if (is_child_auth.equals("2")) {
					Payment payment =  chargesService.getPaymentByBusinessID(Long.valueOf(businessid));
					payment.setThirdcomid(orgto);
					chargesService.updatePayment(payment);
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		_logger.info("toAllot success--------");
		return "list";
	}
	
	
	/**
	 * 重新分配
	 * @return
	 */
	public String reAllot()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Map sessionmap = ActionContext.getContext().getSession();
		
		String businessid = request.getParameter("id");
		Date date = new Date();
		String currindex = "";
		try {
			List numberlist = assignmentService.getAssignmentlistByBusinessId(Long.valueOf(businessid));
			 
			if(numberlist!=null&&numberlist.size()>0){
				HashMap numap = (HashMap)numberlist.get(0);
				BigDecimal temp= (BigDecimal)numap.get("ASSIGNUMBER");
				int i = temp.intValue();
				i++;
				currindex  = String.valueOf(i);  
			}
			
			
			
			Assignment assignment = new Assignment();
			assignment.setPersonto("0");
			assignment.setAssignumber(Long.valueOf(currindex));
			assignment.setAssigntime(date);
			assignment.setAssignerstate(Long.valueOf(BusinessState.Allot_FIRST));
			assignment.setBusinessid(Long.valueOf(businessid)); 
			assignment.setIscomplete(BusinessState.LONG_ZERO);	//设置当前业务单为未完成分配 
			assignment.setIsapplay(BusinessState.LONG_ONE);		//设置当前状态为已激活生效
			List<Assignment> assignlist = new ArrayList<Assignment>(); 
			assignlist.add(assignment);
			assignmentService.updateAssignment(assignlist); 
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR; 
		}
		
		return SUCCESS;
	}
	
	
	/**
	 * 显示机构树及权限分配人员界面
	 * 
	 * 分配权限分两部分:
	 * <br/>
	 * 一:	分配协调员
	 * <br/>		1.属于CCIC 一级机构 的用户可以分配 下级 及 下下级的 <b>协调员</b>
	 * <br/>		2.属于(河北...) 二级机构 的用户只可以分配 下级的<b>协调员</b>
	 * <br/>		3.属于(沧州...) 三级机构 的用户不能分配 <b>协调员</b>
	 * <br/><br/><br/>
	 * 二:	分配检查员(授权签字人)
	 * <br/>		1.属于CCIC 一级机构 的用户  <b>不能</b> 分配 <b>检查员</b>
	 * <br/>		2.属于(河北...) 二级机构 的用户可以分配 同级 及 下级的 <b>检查员</b>
	 * <br/>		3.属于(沧州...) 三级机构	的用户只能分配同级的 <b>检查员</b>
	 * <br/>
	 * @return
	 * @param
	 * 
	 */
	public String allotlist()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Map sessionmap = ActionContext.getContext().getSession();
		String orgid =	(String)sessionmap.get(Constants.ACCOUNT_PERSON_ORG);	//用户所属机构
		String allottype = request.getParameter("allottype");
		String radio_check =request.getParameter("radio_check");	//分配对象为 0:协调员 / 1:检查员授权签字人
		String SESSION_ORGLEVEL = (String)sessionmap.get(Constants.ACCOUNT_PERSON_IS_CHILD);	//所属机构等级
		 
		_logger.info("allotlist begin-------");
		try {
			String condition  = "";
			if(radio_check.equals("0")&&SESSION_ORGLEVEL.equals("1")){	//二级用户分配协调员，只能分配下级协调员
				condition = "1";
			}else if(radio_check.equals("0")&&SESSION_ORGLEVEL.equals("2")){	//三级用户分配协调员，只能分配下级协调员
				condition = "2"; 
			}else if (radio_check.equals("1")&&SESSION_ORGLEVEL.equals("1")) {	//二级用户分配时不能分配下级检查员
				condition = "3";  
			}
			
			List list = 	businessService.getOrganizationLevelByOrgID(orgid,condition);
			HashMap firstmap = null;
			List returnlist = new ArrayList();
			String first_is_child = "";
			if(list!=null&&list.size()>0){	//得到第一个分支的层级
				firstmap = (HashMap)list.get(0);
				first_is_child = (String)firstmap.get("IS_CHILD");
			}
			if(first_is_child.equals("1")){	//如果第一个分支是1级子公司 河北公司 则需要把他的父位置信息改为0 ,这样树形控件才能显示
				for (int i = 0; i < list.size(); i++) {
					HashMap map = (HashMap)list.get(i);
					String temp = (String)map.get("IS_CHILD");
					if (temp.equals("1")) {
						map.put("PARENT_ID", "0");
					}
					returnlist.add(map);
				}
			}else {
				returnlist = list;
			}
			
			request.setAttribute("list", returnlist);
			request.setAttribute("allottype", allottype); 
			_logger.info("allotlist success-------");
		} catch (Exception e) {
			e.printStackTrace();
			_logger.info("allotlist error-------");
			return ERROR;
		}
		return SUCCESS;
	}
	
	
	
	/**
	 * 显示机构树及权限分配人员界面
	 * 根据机构ID得到指定的机构的用户类型
	 * @return
	 * @param
	 * 
	 */
	public String allotlistbyorg()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String allottype = request.getParameter("allottype");
		String radio_check =request.getParameter("radio_check");	//分配对象为 0:协调员 / 1:检查员授权签字人
		String orgid = request.getParameter("orgid");
		_logger.info("allotlistbyorg begin-------");
		try {
			 
			String condition = "";
			List list = 	businessService.getOrganizationLevelByOrgID(orgid,condition);
			HashMap firstmap = null;
			List returnlist = new ArrayList();
			String first_is_child = "";
			if(list!=null&&list.size()>0){	//得到第一个分支的层级
				firstmap = (HashMap)list.get(0);
				first_is_child = (String)firstmap.get("IS_CHILD");
			}
			if(first_is_child.equals("1")){	//如果第一个分支是1级子公司 河北公司 则需要把他的父位置信息改为0 ,这样树形控件才能显示
				for (int i = 0; i < list.size(); i++) {
					HashMap map = (HashMap)list.get(i);
					String temp = (String)map.get("IS_CHILD");
					if (temp.equals("1")) {
						map.put("PARENT_ID", "0");
					}
					returnlist.add(map);
				}
			}else if(first_is_child.equals("2")){	//如果第一个分支是2级子公司 沧州分公司 则需要把父位置信息改为0  ,这样树形控件才能显示
				for (int i = 0; i < list.size(); i++) {
					HashMap map = (HashMap)list.get(i);
					String temp = (String)map.get("IS_CHILD");
					if (temp.equals("2")) {
						map.put("PARENT_ID", "0");
					}
					returnlist.add(map);
				}
			}else {
				returnlist = list;
			}
			
			request.setAttribute("list", returnlist);
			request.setAttribute("allottype", allottype); 
			_logger.info("allotlist success-------");
		} catch (Exception e) {
			e.printStackTrace();
			_logger.info("allotlistbyorg error-------");
			return ERROR;
		}
		return SUCCESS;
	}
	
	
	/**
	 * 根据机构和权限返回组织人员信息
	 * @return
	 */
	public String tableContent() 
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		String orgid = request.getParameter("orgid");
		String allottype =request.getParameter("allottype");	//分配对象类型 0:协调员 /1:授权签字人 /2:检查员
		
		String typecondition = "";
		if(allottype.equals("0")){	//协调员
			typecondition = " and t.is_coordinator = 1 ";
		}else if(allottype.equals("1")){	//授权签字人
			typecondition = " and t.is_authorized = 1 ";
		}else if(allottype.equals("2")){	//检查员
			typecondition = " and t.is_inspector = 1 "; 
		}
		 
		_logger.info("allotlist begin-------");
		
		try {
			List list = businessService.getPersonFromOrg(orgid,typecondition);  
			request.setAttribute("list", list);
			_logger.info("allotlist success-------");
		} catch (Exception e) {
			e.printStackTrace();
			_logger.info("allotlist error-------");
			return ERROR;
		}
		if(allottype.equals("2")){ 
			return "checkbox";
		}
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
				countrysandports_input = countrysandports_input.substring(0,countrysandports_input.length()-2);
			} 
			 
			
			String breth_inpit = "";	//船舶及港口城市 breth, tianjin, 天津, CHINA
			List portlist = registerService.getPortProCity(business.getPortid()); 
			if (portlist!=null&&portlist.size()>0) {
				HashMap portmap = (HashMap)portlist.get(0);
				String port_sname = (String)portmap.get("PORT_SNAME");
				String cityname = (String)portmap.get("CITYNAME");
//				String bre = business.getBerth()==null?"":business.getBerth()+", ";
				String bre = "";
				if(!cityname.toUpperCase().equals(port_sname.toUpperCase())) {
					breth_inpit = bre +port_sname+", "+cityname.toUpperCase()+", "+BusinessState.APPLY_CHINA;
				}else {
					breth_inpit = bre +port_sname+", "+BusinessState.APPLY_CHINA;
				}
				
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
			
			//================== add by guodingjun in 2011-03-31  ==============
			//==================          add star             ==============
			//如果没从数据库取得检查方法，就从 language_zh_CN.properties文件当中获取到检查方法
			if(business.getInspection1() == null || "".equals(business.getInspection1())) {
				ResourceBundle rb = PropertyResourceBundle.getBundle("language",Locale.CHINA);
				request.setAttribute("inspection_input",rb.getString("INSPECTION1"));
			}else{
				request.setAttribute("inspection_input", business.getInspection1());
			}
			//==================   add end  ===================
			
			_logger.info("getAllotPageView success-------");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	
	public String acceptPassdetail()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		List list = new ArrayList(); 
    	
		try {
			business = businessService.getBusinessById(Long.valueOf(id));
			list.add(business);
			List<BusinessDesCountry> busdesCountrylist = businessService.getBusinessDesCountry(Long.parseLong(id));
			setBusinessCountrysandPorts(busdesCountrylist, request);
			
			
			List procityList = registerService.getPromaryAndCityByPortID(business.getPortid());
			if(procityList!=null&&procityList.size()>0){
	    		 HashMap procitymap = (HashMap)procityList.get(0);
	    		 BigDecimal cityid_b = (BigDecimal)procitymap.get("CITYID");	//港口所在最下级城市
	    		 BigDecimal proid_b = (BigDecimal)procitymap.get("PROID");	//港口所在省份
	    		 String proid = String.valueOf(proid_b);
	    		 String cityid = String.valueOf(cityid_b); 
	    		 request.setAttribute("select_portid", business.getPortid()); 
	    	     request.setAttribute("select_proid", proid);
	    	     request.setAttribute("select_cityid", cityid);
	    	     List procityportnameList = registerService.getPCPNameByPortID(proid, cityid, business.getPortid());
	 			 if (procityportnameList!=null&&procityportnameList.size()>0) {
					HashMap namemap = (HashMap)procityportnameList.get(0);
					String proname = (String)namemap.get("PRONAME");
					String cityname = (String)namemap.get("CITYNAME");
					String portname = (String)namemap.get("PORT_NAME"); 
					request.setAttribute("proname", proname);
					request.setAttribute("cityname", cityname);
					request.setAttribute("portname", portname); 
					
				} 
			}  
			showAccountname(business.getAccountid());
		} catch (Exception e) { 
			e.printStackTrace();
			return ERROR;
		}
		request.setAttribute("list", list);
		return SUCCESS;
	}
	
     public String getBusinessById()throws Exception{
    	 
    	 HttpServletRequest request = ServletActionContext.getRequest();
    	 business = businessService.getBusinessById(Long.parseLong(id));
    	 List<BusinessDesCountry> busdesCountrylist = businessService.getBusinessDesCountry(Long.parseLong(id));
    	 String view = request.getParameter("view");
    	 
    	 //修改信息返回页面错误，原因 没有传currpage值3.24lxz
    	 String gopage = request.getParameter("goPage");
    	 request.setAttribute("currpageb", gopage);
    	 String phone_input = business.getPhone();
    	 if (phone_input!=null&&!phone_input.equals("")&&phone_input.indexOf("-")!=-1) {
    		 String[] phones = phone_input.split("-");
    		 phone_begin = phones[0];
    		 phone_end = phones[1];
		 } 
    	 String inv_input = business.getInvoicetel();
    	 if (inv_input!=null&&!inv_input.equals("")&&inv_input.indexOf("-")!=-1) {
    		 String[] inv_inputs = inv_input.split("-");
    		 invoicetel_begin = inv_inputs[0];
    		 invoicetel_end = inv_inputs[1]; 
		 }
    	 
    	 setBusinessCountrysandPorts(busdesCountrylist, request);
    	 businessApply(); 	//国家列表
    	 
    	 List procityList = registerService.getPromaryAndCityByPortID(business.getPortid());
    	 if(procityList!=null&&procityList.size()>0){
    		 HashMap procitymap = (HashMap)procityList.get(0);
    		 BigDecimal cityid_b = (BigDecimal)procitymap.get("CITYID");	//港口所在最下级城市
    		 BigDecimal proid_b = (BigDecimal)procitymap.get("PROID");	//港口所在省份
    		 String proid = String.valueOf(proid_b);
    		 String cityid = String.valueOf(cityid_b); 
    		 request.setAttribute("select_portid", business.getPortid());
    	     request.setAttribute("select_proid", proid);
    	     request.setAttribute("select_cityid", cityid);
    	 }
    	 
    	 if(view==null){
    		 view = "0"; 
    	 }
    	 
    	 showAccountname(business.getAccountid());
    	 
    	 request.setAttribute("registry_hidden", business.getRegistry());
    	 request.setAttribute("view", view); 
    	 request.setAttribute("busdesCountrylist", busdesCountrylist); 
    	 System.out.println("**********  *" + goPage + "  **");
    	 request.setAttribute("currPagec", goPage);
    	 //update by guodingjun 把业务方法值传递到修改方法
    	 request.setAttribute("inspection_input", business.getInspection1());
    	 return SUCCESS;
	}
     
     /**
      * 删除业务单
      * @return
      * @throws Exception
      */
     public String deleteBusinessById()throws Exception{
 	    businessService.deleteBusinessById(Long.parseLong(id)); 
 		return SUCCESS;
 	}
     
     
     /**
      * 如果当前登录为用户，则显示业务单申请来源信息
     * @throws Exception 
      */
     public void showAccountname(String accountid) throws Exception
     {
    	 HttpServletRequest request = ServletActionContext.getRequest();
    	 Map sessionmap = ActionContext.getContext().getSession();
    	 Account curr_account = (Account) sessionmap.get(Constants.ACCOUNT_SESSION);
    	 if (curr_account.getAccountType().toString().equals(Constants.ACCOUNTTYPE_PERSON)) {	//如果当前登录为用户，则显示业务单申请来源信息
    		 Account resoutceaccout = accountService.getAccountById(accountid);
    		 String account_name = "";
    		 if (resoutceaccout!=null) {
    			 account_name =  resoutceaccout.getAccountName();
    		 }
        	 request.setAttribute("business_accountname", account_name); 
		}
    	
    	 
     }
     
     /**
      * 受理业务单
      * @return
      */
     public String acceptpass()
     {
    	 HttpServletRequest request = ServletActionContext.getRequest();
    	 String pass_state = request.getParameter("pass_state");
    	 String remark = request.getParameter("remark");
    	 Date date = new Date();
    	 SimpleDateFormat dateFormat = new SimpleDateFormat("yyMM");	//生产临时申请号
    	 _logger.info("acceptpass begin");
    	
    	 try {
    		 Business tempbusiness = businessService.getBusinessById(Long.valueOf(id));
    		 if (tempbusiness.getBusinessstate().toString().equals("1")) {	//防止重复提交 (目前暂时不能完全防止重复提交)
    			 
    			 if(pass_state!=null&&pass_state.equals("0")){	//未通过
    	    			Business bus = new Business();
    	        		bus.setId(Long.valueOf(id));
    	        		bus.setRemark(remark);
    	        		bus.setBusinessstate(Long.valueOf(BusinessState.BUSINESS_ACCEPTNOT)); 
    	    			businessService.updateBusinessToState(bus);
    	    		}else if (pass_state!=null&&pass_state.equals("1")) {	//通过
    	    			Business business = businessService.getBusinessById(Long.valueOf(id));
    	    			Business bus = new Business();
    	        		bus.setId(Long.valueOf(id)); 
    	        		bus.setRemark(remark);
    	        		bus.setBusinessstate(Long.valueOf(BusinessState.BUSINESS_ACCEPTSUCCESS)); 
    	    			businessService.updateBusinessToState(bus);
    				}
    		 }
    		
			_logger.info("acceptpass success");
		} catch (Exception e) { 
			e.printStackTrace();
			if (e.getMessage().equals("重复索引")) {
				return "conflict";
			}
			return ERROR;
		}
    	 _logger.info("acceptpass end");
    	 return SUCCESS;
     }
     
     
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
   public String registryList() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		List list=businessService.registrySelect();
		request.setAttribute("registryList", list);

	return SUCCESS;
}
   
   
   /**
    * 返回页面目的国及港口
    * @param businessDesCountryList
    * @param request
    */
   public void setBusinessCountrysandPorts(List<BusinessDesCountry> businessDesCountryList,HttpServletRequest request) {
	   	
		String page_countrys = "";
		String page_ports = "";
		String page_selects = "";
		for (int j = 0; j < businessDesCountryList.size(); j++) {
			page_countrys += StringUtils.trimString(businessDesCountryList.get(j).getCountry())  + ",";
			page_ports += StringUtils.trimString(businessDesCountryList.get(j).getPort()) + ",";
			page_selects += StringUtils.trimString(businessDesCountryList.get(j).getSelectindex())  +",";
		}
		if (page_countrys.endsWith(",")) {
			page_countrys = page_countrys.substring(0,
					page_countrys.length() - 1);
		}
		if (page_ports.endsWith(",")) {
			page_ports = page_ports.substring(0, page_ports.length() - 1);
		}
		if (page_selects.endsWith(",")) {
			page_selects = page_selects.substring(0,page_selects.length() -1 );
		}
		
		request.setAttribute("page_selects", page_selects);
		request.setAttribute("page_countrys", page_countrys);
    	request.setAttribute("page_ports", page_ports); 
	}
   
   

public Long getBusinessid() {
	return businessid;
}
public void setBusinessid(Long businessid) {
	this.businessid = businessid;
}

public String getFirstport() {
	return firstport;
}
public void setFirstport(String firstport) {
	this.firstport = firstport;
}
public String getFirstcountry() {
	return firstcountry;
}
public void setFirstcountry(String firstcountry) {
	this.firstcountry = firstcountry;
}
public String getSecondport() {
	return secondport;
}
public void setSecondport(String secondport) {
	this.secondport = secondport;
}
public String getSecondcountry() {
	return secondcountry;
}
public void setSecondcountry(String secondcountry) {
	this.secondcountry = secondcountry;
}
public BusinessDesCountry getBusdesCountry() {
	return busdesCountry;
}
public void setBusdesCountry(BusinessDesCountry busdesCountry) {
	this.busdesCountry = busdesCountry;
}
public String[] getDestinationcountrys() {
	return destinationcountrys;
}
public void setDestinationcountrys(String[] destinationcountrys) {
	this.destinationcountrys = destinationcountrys;
}
public String[] getPorts() {
	return ports;
}
public void setPorts(String[] ports) {
	this.ports = ports;
}
public PersonService getPersonService() {
	return personService;
}
public void setPersonService(PersonService personService) {
	this.personService = personService;
}
public AssignmentService getAssignmentService() {
	return assignmentService;
}
public void setAssignmentService(AssignmentService assignmentService) {
	this.assignmentService = assignmentService;
}
public AccountService getAccountService() {
	return accountService;
}
public void setAccountService(AccountService accountService) {
	this.accountService = accountService;
}
public RegisterService getRegisterService() {
	return registerService;
}
public void setRegisterService(RegisterService registerService) {
	this.registerService = registerService;
}
public String getInvoicetitle() {
	return invoicetitle;
}
public void setInvoicetitle(String invoicetitle) {
	this.invoicetitle = invoicetitle;
}
public String getInvoiceaddress() {
	return invoiceaddress;
}
public void setInvoiceaddress(String invoiceaddress) {
	this.invoiceaddress = invoiceaddress;
}
public String getInvoicepost() {
	return invoicepost;
}
public void setInvoicepost(String invoicepost) {
	this.invoicepost = invoicepost;
}
public String getInvoicer() {
	return invoicer;
}
public void setInvoicer(String invoicer) {
	this.invoicer = invoicer;
}
public String getInvoicetel() {
	return invoicetel;
}
public void setInvoicetel(String invoicetel) {
	this.invoicetel = invoicetel;
}
public String getSpecial() {
	return special;
}
public void setSpecial(String special) {
	this.special = special;
}
public String getLinkphone() {
	return linkphone;
}
public void setLinkphone(String linkphone) {
	this.linkphone = linkphone;
}
public String getPhone_begin() {
	return phone_begin;
}
public void setPhone_begin(String phone_begin) {
	this.phone_begin = phone_begin;
}
public String getPhone_end() {
	return phone_end;
}
public void setPhone_end(String phone_end) {
	this.phone_end = phone_end;
}
public String getInvoicetel_end() {
	return invoicetel_end;
}
public void setInvoicetel_end(String invoicetel_end) {
	this.invoicetel_end = invoicetel_end;
}
public String getInvoicetel_begin() {
	return invoicetel_begin;
}
public void setInvoicetel_begin(String invoicetel_begin) {
	this.invoicetel_begin = invoicetel_begin;
}
public String[] getCountrys() {
	return countrys;
}
public void setCountrys(String[] countrys) {
	this.countrys = countrys;
}
public ChargesService getChargesService() {
	return chargesService;
}
public void setChargesService(ChargesService chargesService) {
	this.chargesService = chargesService;
}
	
/**
 * 业务查询中修改业务信息
 * @return
 * @throws AppException
 * @author lixin
 */
public String selectupdataApply()throws AppException{
	HttpServletRequest request = ServletActionContext.getRequest();
	List<BusinessDesCountry> businessDesCountryList = new ArrayList<BusinessDesCountry>();
	
	try {
		if (businessname==null||businessname.equals("")) {
			businessname = request.getParameter("businessname_hidden");
		}
		String accountid = request.getParameter("accountid");
		Business bus = new Business();
		bus.setId(Long.valueOf(id));
		bus.setAppname(appname);
		bus.setBusinessname(businessname);
		bus.setAppno(appno);
		bus.setAppdate(appdate);
		bus.setBerth(berth); 
		bus.setBusinessstate(businessstate);
		bus.setCertification(certification);
		bus.setCheckdate(checkdate);
		bus.setCompany(company);
//		bus.setDestinationcountry(destinationcountry); 
		bus.setEmail(email);
		bus.setFax(fax);
		bus.setImo(imo);
		bus.setLinkmanname(linkmanname);
		if(phone_begin!=null&&!phone_begin.equals("")&&phone_end!=null&&!phone_end.equals("")){
			phone = phone_begin+"-"+phone_end;
		}
		bus.setPhone(phone);
		bus.setPlandatein(plandatein);
		bus.setPlandateout(plandateout);
		bus.setPortid(portid);
		bus.setRegistry(registry);
		bus.setRemark(remark);
		bus.setTonnage(tonnage);
		bus.setVesselname(vesselname);
		bus.setVesselno(vesselno);
		bus.setVesseltype(vesseltype); 
		bus.setAccountid(accountid); 
		bus.setInvoiceaddress(invoiceaddress);
		bus.setInvoicepost(invoicepost);
		bus.setInvoicer(invoicer);
		if(invoicetel_begin!=null&&!invoicetel_begin.equals("")&&invoicetel_end!=null&&!invoicetel_end.equals("")){
			invoicetel = invoicetel_begin+"-"+invoicetel_end;
		}
		bus.setInvoicetel(invoicetel);
		bus.setInvoicetitle(invoicetitle);
		bus.setSpecial(special);
		bus.setLinkphone(linkphone);
		bus.setVesselno(vesselno);
		bus.setRemark(remark);
		bus.setQualified(qualified);
		bus.setCertid(certid);
		bus.setStampid(stampid);
		/**
		 * update by guodingjun  设置查询方法值
		 */
		System.out.println("=============================" +request.getParameter("inspection1"));
		business = businessService.getBusinessById(Long.parseLong(id));
		System.out.println("================================="+business.getRegistry());
		bus.setInspection1(request.getParameter("inspection1"));
		
		String orgid4 = "";	//根据港口ID得到对应的机构编号
		List portorglist = businessService.getOrg4Byportid(portid);	//根据港口ID得到对应的机构编号
		if (portorglist!=null&&portorglist.size()>0) {
			HashMap portorgmap = (HashMap)portorglist.get(0);
			orgid4 = portorgmap.get("ORG_ID").toString();
			bus.setOrgid(orgid4);
			bus.setPortorgid(portorgmap.get("ID").toString());
		}
		
		for (int i = 0; i < destinationcountrys.length; i++) {
			if (destinationcountrys[i]!="-1") {
				BusinessDesCountry busdes = new BusinessDesCountry();
				busdes.setCountry(countrys[i]);
				busdes.setPort(ports[i]);
				busdes.setSelectindex(destinationcountrys[i]);
				busdes.setDesindex(i);
				businessDesCountryList.add(busdes);
			}
		}
		
		setBusinessCountrysandPorts(businessDesCountryList, request); 
		
		businessService.updateBusinessForSelect(bus, businessDesCountryList);
		business = businessService.getBusinessById(Long.valueOf(id)); 
		
		businessApply(); 	//国家列表
	 
		List procityList = registerService.getPromaryAndCityByPortID(business.getPortid());
		if(procityList!=null&&procityList.size()>0){
    		 HashMap procitymap = (HashMap)procityList.get(0);
    		 BigDecimal cityid_b = (BigDecimal)procitymap.get("CITYID");	//港口所在最下级城市
    		 BigDecimal proid_b = (BigDecimal)procitymap.get("PROID");	//港口所在省份
    		 String proid = String.valueOf(proid_b);
    		 String cityid = String.valueOf(cityid_b); 
    		 request.setAttribute("select_portid", business.getPortid());
    	     request.setAttribute("select_proid", proid);
    	     request.setAttribute("select_cityid", cityid);
		} 
		request.setAttribute("view", "0");	//保存结束后跳转到查看界面
		
		this.addActionMessage("保存成功"); 
	} catch (Exception e) {
		throw new AppException("添加信息", e);
	}
	 String gopage = request.getParameter("goPage");
	 request.setAttribute("currpageb", gopage);
	return SUCCESS;
		
}

	/**
	 * 业务提醒
	 * @return
	 * @throws AppException
	 */
	public String businessRemind() throws AppException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Map sessionmap = ActionContext.getContext().getSession();
		Locale local_language = (Locale) session.getAttribute("WW_TRANS_I18N_LOCALE");
		String language = "CN"; 
		if(local_language!=null){
			language =  local_language.getCountry();
		} 
		HashMap map = new HashMap();
		Integer businessTempCount = 0;//未提交业务量
		Integer businessAcceptwaitCount = 0;//未受理业务量
		Integer businessAllotCount = 0;//未分配业务量
		Integer businessCertificateCount = 0;	//证书数量
		Integer businessSubmitCount = 0;	//待提交检查结果数量
		Integer businessCheckCount = 0;	//待检查
		Integer businessPayCount = 0;	//待付费
		
		try {
			Account account = (Account) sessionmap.get(Constants.ACCOUNT_SESSION);
			String accounttype = account.getAccountType().toString();
			String personid = (String)sessionmap.get(Constants.ACCOUNT_PERSON_ID);
			String role_type = (String) sessionmap.get(Constants.ACCOUNT_ALLOT_LEVEL);
			String own_orgid4 = (String)sessionmap.get(Constants.ACCOUNT_ORG_ID4);
			String isadmin = (String)sessionmap.get(Constants.ACCOUNT_ISADMIN);	//如果是超级管理员用户
			businessstate = Long.valueOf(BusinessState.BUSINESS_TEMP);  //处理状态为未提交
			String operatortype = BusinessState.BUSINESS_ACCEPTWAIT;	//处理状态为未受理
			String bustate = BusinessState.BUSINESS_ACCEPTSUCCESS;

			if (accounttype!=null&&accounttype.equals("1")) {	//用户	查看本机构及本机构下级机构的内容
				String is_child = (String) sessionmap.get(Constants.ACCOUNT_PERSON_IS_CHILD);	//得到用户机构等级
				String org4 = (String) sessionmap.get(Constants.ACCOUNT_ORG_ID4); //当前用户的4位机构ID
				/**
				 * 查询未提交业务量
				 */
				map.put("state", businessstate);
				map.put("is_child", is_child);
				map.put("accountid", account.getAccountId());
				map.put("org4",org4);
				businessTempCount = businessService.getBusinessListCOUNT(map);
				
				/**
				 * 查询待受理业务量
				 */
				map.put("state", operatortype);
				map.put("accountid", "-1");	//用户只能受理自己机构所在的业务
				businessAcceptwaitCount = businessService.getBusinessListCOUNT(map);
				
				/**
				 * 查询待分配业务量
				 */
				HashMap map2 = new HashMap();
				if(bustate!=null&&!bustate.equals("")){
					map2.put("bustate", bustate);
		   		}
				map2.put("allotstate", "1");
				if(isadmin!=null&&isadmin.equals("1")){
					businessAllotCount = assignmentService.getBusinessByPersonIDCountSYS(map2);
				}
				else{
					businessAllotCount = assignmentService.getBusinessByPersonIDCount(personid,map2);
				}
				
				/**
				 * 查询证书相关业务量
				 */
				HashMap map3 = new HashMap();
				map3.put("bustate", BusinessState.Business_ALLOTED);
				if (is_child!=null&&!is_child.equals("0")) {
		   			if (role_type!=null&&role_type.equals("2")) {	//协调员用户，查看自己所在机构及下级机构的所有证书
		   				map3.put("org4", own_orgid4);
		   				map3.put("is_child", is_child);
					}
				} 
				businessCertificateCount = businessService.searchBusinessListCount(map3);
				
				/**
				 * 查询检查结果相关业务量
				 */
				HashMap map4 = new HashMap();
				map4.put("bustate", BusinessState.Business_CHECKED);
				if(isadmin!=null&&isadmin.equals("1")){
					businessSubmitCount = examineService.getAllBussinessCount(map4);
				}else{
					map4.put("personid",personid);
					businessSubmitCount = examineService.getAllBussinessCount(map4);
				}
				
			}else if (accounttype!=null&&accounttype.equals("2")) {	//客户	查看自己提交的内容
				Client client = (Client)sessionmap.get(Constants.ACCOUNT_CLIENT);
				String clientname = client.getCoEnName();
				/**
				 * 查询未提交业务量
				 */
				map.put("state", businessstate);
				map.put("businessname", clientname);
				businessTempCount = businessService.getBusinessListClientCOUNT(map);
				
				/**
				 * 查询待受理业务量
				 */
				map.put("state", operatortype);
				map.put("businessname", clientname);
				businessAcceptwaitCount = businessService.getBusinessListClientCOUNT(map);
				
				/**
				 * 待检查
				 */
				map.put("state", BusinessState.BUSINESS_ACCEPTSUCCESS);
				Integer  checkCount=businessService.getBusinessListClientCOUNT(map);
				map.put("state", BusinessState.Business_ALLOTED);
				Integer checkCount1= businessService.getBusinessListClientCOUNT(map);
				
				 businessCheckCount=checkCount+checkCount1;
				/**
				 * 	待付费 
				 */
				businessPayCount = chargesService.getNotPaymentsCount(map);
				
			}
 
			request.setAttribute("businessTempCount", businessTempCount); 
			request.setAttribute("businessAcceptwaitCount", businessAcceptwaitCount); 
			request.setAttribute("businessAllotCount", businessAllotCount); 
			request.setAttribute("businessCertificateCount", businessCertificateCount);
			request.setAttribute("businessSubmitCount", businessSubmitCount);
			request.setAttribute("businessCheckCount", businessCheckCount);
			request.setAttribute("businessPayCount", businessPayCount);
			
			request.setAttribute("language", language);
			
			_logger.info("get business remind success-------"); 
		} catch (Exception e) {
			throw new AppException("业务提醒", e);
		}
		
		return SUCCESS;
	}

public OrganizationService getOrganizationService() {
	return organizationService;
}
public void setOrganizationService(OrganizationService organizationService) {
	this.organizationService = organizationService;
}
public String getTempno() {
	return tempno;
}
public void setTempno(String tempno) {
	this.tempno = tempno;
}
public String getPortorgid() {
	return portorgid;
}
public void setPortorgid(String portorgid) {
	this.portorgid = portorgid;
}
public ClientService getClientService() {
	return clientService;
}
public void setClientService(ClientService clientService) {
	this.clientService = clientService;
}
public ExamineService getExamineService() {
	return examineService;
}
public void setExamineService(ExamineService examineService) {
	this.examineService = examineService;
}
     
}
