package com.sinosoft.gypsymoth.bussiness.business.action;

import java.math.BigDecimal;
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
import com.sinosoft.gypsymoth.bussiness.assignment.service.AssignmentService;
import com.sinosoft.gypsymoth.bussiness.business.service.BusinessService;
import com.sinosoft.gypsymoth.bussiness.business.service.CertificateService;
import com.sinosoft.gypsymoth.bussiness.organization.service.OrganizationService;
import com.sinosoft.gypsymoth.bussiness.register.service.RegisterService;
import com.sinosoft.gypsymoth.pojo.Assignment;
import com.sinosoft.gypsymoth.pojo.Business;
import com.sinosoft.gypsymoth.pojo.BusinessDesCountry;
import com.sinosoft.gypsymoth.pojo.Certificate;
import com.sinosoft.gypsymoth.pojo.OrgStamp;
import com.sinosoft.gypsymoth.pojo.Organization;
import com.sinosoft.gypsymoth.pojo.Person;
import com.sinosoft.gypsymoth.utils.BusinessState;
import com.sinosoft.gypsymoth.utils.Constants;
import com.sinosoft.gypsymoth.utils.Pagination;
import com.sinosoft.gypsymoth.utils.StringUtils;

public class CertificateAction extends ActionSupport {

	private final Logger _logger = Logger.getLogger(CertificateAction.class);

	private CertificateService certificateService;
	private OrganizationService organizationService;
	private BusinessService businessService;
	private AssignmentService assignmentService;
	private RegisterService registerService;
	private String actionName;

	/**
	 * 证书管理
	 * 
	 * @return
	 */
	public String certificate() {

		HttpServletRequest request = ServletActionContext.getRequest();
		Map sessionMap = ActionContext.getContext().getSession();

		String is_child = (String) sessionMap.get(Constants.ACCOUNT_PERSON_IS_CHILD);
		String role_type = (String) sessionMap.get(Constants.ACCOUNT_ALLOT_LEVEL);
		String person_ordid = (String)sessionMap.get(Constants.ACCOUNT_PERSON_ORG);
		String own_orgid4 = (String)sessionMap.get(Constants.ACCOUNT_ORG_ID4);
		String tagtype = request.getParameter("tagtype"); // 页面TAG 0证书编制/1空白证书管理/2印章管理
		Person person = (Person)sessionMap.get(Constants.ACCOUNT_PERSON);
		String person_id = person.getPersonId();
		String person_name = person.getName();
		
		String vesselname_form = request.getParameter("vesselname_form");
   		String portcity_form = request.getParameter("portcity_form");
   		String appno_form = request.getParameter("appno_form");
		
		
		// 分页配置
		Pagination p = new Pagination(0, 0, 0);
		String currPage = (String) request.getParameter("goPage");
		if (currPage == null) {
			currPage = "1";// ” currPage”是当前页数
		}
		_logger.info("currPage:" + currPage);
		Integer currPage1 = Integer.parseInt(currPage);
		List list = null;
		HashMap map = new HashMap();
		try {
			if (tagtype == null || tagtype.equals("0")) {	//证书编制
				
				String bustate = BusinessState.Business_ALLOTED;
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
		   		
		   		map.put("portcity", portcity_form);
		   		if(bustate!=null&&!bustate.equals("")){
		   			map.put("bustate", bustate);
		   		} 
				
		   		if (is_child!=null&&!is_child.equals("0")) {
		   			if (role_type!=null&&role_type.equals("2")) {	//协调员用户，查看自己所在机构及下级机构的所有证书
			   			map.put("org4", own_orgid4);
			   			map.put("is_child", is_child);
					}else if((role_type!=null&&role_type.equals("1"))||(role_type!=null&&role_type.equals("0"))){	//授权签字人,检查员得到当前用户相关的证书列表
						map.put("personid", person_id);
					}
				}
				 Integer totleCount = businessService.searchBusinessListCount(map);
				 p.getPagination(request,totleCount,
				 currPage1,null,null,null);
				 int numOfEachPage = Constants.NUMOFEACHPAGE;
				 int begin = p.getRownum_begin(numOfEachPage, currPage1);
				 list = businessService.searchBusinessList(map, begin, numOfEachPage);
				 tagtype = "0";
			} else if (tagtype.equals("1")) {		//空白证书
				String orgid ="";
				if (!is_child.equals("0")) {	//如果是一级用户 则可以看到所有的机构信息 \如果是二级用户，则可以看到自己及下级机构的信息\如果是三级用户，则只可以看到自己机构的信息
					map.put("orgid4", own_orgid4);
				}
				map.put("orgname", "");
				map.put("is_child", is_child);
				Integer totleCount = certificateService.getEmptyCertByOrgIDCount(map);
				p.getPagination(request,totleCount,
				currPage1,null,null,null);
				int numOfEachPage = Constants.NUMOFEACHPAGE;
				int begin = p.getRownum_begin(numOfEachPage, currPage1);
				list = certificateService.getEmptyCertByOrgID(map, begin, numOfEachPage);
				
			} else if(tagtype.equals("2")){
				
				Integer totleCount = certificateService.getStampListCount(map);
				p.getPagination(request,totleCount,
				currPage1,null,null,null);
				int numOfEachPage = Constants.NUMOFEACHPAGE;
				int begin = p.getRownum_begin(numOfEachPage, currPage1);
				list = certificateService.getStampList(map,begin, numOfEachPage);
				
			}else {
				
			} 
			List portcitylist = organizationService.getCityPortList();
			request.setAttribute("portcitylist", portcitylist);
			request.setAttribute("vesselname_form", StringUtils.codeHtml(vesselname_form));
			request.setAttribute("portcity_form", StringUtils.codeHtml(portcity_form)); 
			request.setAttribute("appno_form", StringUtils.codeHtml(appno_form)); 
			request.setAttribute("portcity_form", portcity_form);
			request.setAttribute("list", list);
			_logger.info("certificate success-------");
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		request.setAttribute("tagtype", tagtype);
		request.setAttribute("person_name", person_name);
		request.setAttribute("is_child", is_child);
		this.actionName = "certificate";
		return SUCCESS;
	}

	/**
	 * ajax可用证书下拉框内容
	 * 
	 * @return
	 */
	public String certificateContent() {

		HttpServletRequest request = ServletActionContext.getRequest();
		Map sessionMap = ActionContext.getContext().getSession();
		String orgid = (String)sessionMap.get(Constants.ACCOUNT_PERSON_ORG);
		
		String certname = request.getParameter("certname");
		List list = null;
		try {
			list = certificateService.getCanUseCertNoByName(certname,orgid);
			_logger.info("certificate success-------");
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("selectlist", list);
		this.actionName = "certificateContent";
		return SUCCESS;
	}

	/**
	 * 发放空白证书
	 * 
	 * @return
	 */
	public String certificateEmptySend() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Map sessionMap = ActionContext.getContext().getSession();
		
		String orgid = (String)sessionMap.get(Constants.ACCOUNT_PERSON_ORG);
		Person person = (Person)sessionMap.get(Constants.ACCOUNT_PERSON);
		String personname = person.getName();
		_logger.info("certificateEmptySend begin ...........");  
		try {
			List list = organizationService.getOranizationLevelByParentID(orgid);
			
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
			 
			request.setAttribute("personname", personname);
			request.setAttribute("list", returnlist); 
			_logger.info("certificateEmptySend success ...........");
		} catch (Exception e) { 
			e.printStackTrace();
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	
	/**
	 * 根据所属机构得到拥有的可用证书
	 * @return
	 */
	public String certificateEmptylist()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		Map sessionMap = ActionContext.getContext().getSession();
		
		String orgid = (String)sessionMap.get(Constants.ACCOUNT_PERSON_ORG);
		List list = null;
		try {
			
			list = certificateService.getCanUseCertNamelist(orgid);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		request.setAttribute("selectlist", list);
		return SUCCESS;
	}
	
	
	
	/**
	 * 创建空白证书，同时发放给指定的机构
	 * @return
	 */
	public String toCreateEmptyCert()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		Map sessionMap = ActionContext.getContext().getSession();
		
		Date date = new Date();
		Person person = (Person)sessionMap.get(Constants.ACCOUNT_PERSON);
		String org_id = (String)sessionMap.get(Constants.ACCOUNT_PERSON_ORG);
		String person_id = person.getPersonId();
		String person_name = person.getName();

		String certtype = request.getParameter("certtype");	// 证书类型 0正本/ 1副本
		String certcount = request.getParameter("certcount");
		String cert_name = request.getParameter("cert_name");
		String cert_min = request.getParameter("cert_min");
		String cert_max = request.getParameter("cert_max");
		String personname = request.getParameter("personname");
		String datefrom = request.getParameter("datefrom");
		String orgto =request.getParameter("orgto");
		String orgto_name = request.getParameter("orgto_name");
		String orgto4 = "";
		
		String certname = cert_name+" "+cert_min+"-"+cert_name+" "+cert_max; 
		List<Certificate> certlist = new ArrayList<Certificate>();
		try {
			Organization tempOrganization = new Organization();
			tempOrganization.setId(orgto);
			Organization organization = organizationService.getOrganizationbyId(tempOrganization);
			orgto4 = organization.getOrgId();
			if (certtype.equals("0")) {	//正本需要校验流水号区间 等
				int valicount = Integer.valueOf(certcount);
				int min = Integer.valueOf(cert_min);
				int max = Integer.valueOf(cert_max);
				if((max-min+1)!=valicount){ 
					request.setAttribute("message", "证书发放数量跟证书编号区间不对应，验证未通过"); 
					return "input";  
				} 
				
				List isExistList = certificateService.getCertisExistByCertNum(min, max, cert_name);
				if(isExistList!=null&&isExistList.size()>0){
					int maxvalue = certificateService.getCertMaxByCertNo(cert_name);
					request.setAttribute("message", "证书区间内有已使用的编号，请换用其他区间，区间 "+cert_name+" 最大的编号值为"+maxvalue); 
					return "input"; 
				}
				
				cert_name =cert_name.trim();
				List list = numberText(min, max);
				for (int i = 0; i < list.size(); i++) {
					Certificate certificate = new Certificate();
					certificate.setOrgName(orgto_name);
					certificate.setOrgId(orgto);
					certificate.setOrgsecond(orgto);
					certificate.setCerttype(Long.valueOf(certtype));
					certificate.setCertname(certname);
					certificate.setCertmin(Long.valueOf(cert_min));
					certificate.setCertmax(Long.valueOf(cert_max));
					certificate.setDatefrom(date);
					certificate.setPersonfrom(person_id);
					certificate.setCerthead(cert_name);
					certificate.setIsuse(BusinessState.LONG_ZERO);
					certificate.setCertstate(BusinessState.LONG_ONE);
					certificate.setIsapply(BusinessState.LONG_ONE);
					certificate.setCertno(cert_name+" "+list.get(i).toString());
					certificate.setOrgId4(orgto4);
					certlist.add(certificate); 
				}
			}else if (certtype.equals("1")) {	//副本不需要校验
				int valicount = Integer.valueOf(certcount);
				if(valicount>10){
					request.setAttribute("message", "副本发放数量一次不能超过10。 "); 
					return "input";  
				}
				for (int i = 0; i < valicount; i++) {
					Certificate certificate = new Certificate();
					certificate.setOrgName(orgto_name);
					certificate.setOrgId(orgto);
					certificate.setOrgsecond(orgto);
					certificate.setCerttype(Long.valueOf(certtype));
					certificate.setDatefrom(date);
					certificate.setPersonfrom(person_id);
					certificate.setIsuse(BusinessState.LONG_ZERO);
					certificate.setCertstate(BusinessState.LONG_ONE);
					certificate.setIsapply(BusinessState.LONG_ONE);
					certificate.setOrgId4(orgto4); 
					certlist.add(certificate); 
				}
				
			}
			 
			certificateService.saveCertList(certlist); 
			
			request.setAttribute("tagtype", "1");
		} catch (Exception e) {
			e.printStackTrace();
			 
		}
		
		return SUCCESS;
	}
	
	/**
	 * 编制证书->修改页面
	 * @return
	 */
	public String certificatemodify()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		Map sessionMap = ActionContext.getContext().getSession();
		
		String id = request.getParameter("businessid");
		List list = new ArrayList(); 
    	
		try {
			Business business = businessService.getBusinessById(Long.valueOf(id));
			list.add(business);
			List<BusinessDesCountry> busdesCountrylist = businessService.getBusinessDesCountry(Long.parseLong(id));
			
			setBusinessCountrysandPorts(busdesCountrylist, request);
			
			List procityportnameList = registerService.getPromaryCity(business.getPortid());
			if (procityportnameList!=null&&procityportnameList.size()>0) {
				HashMap namemap = (HashMap)procityportnameList.get(0);
				String proname = (String)namemap.get("PRONAME");
				String cityname = (String)namemap.get("CITYNAME");
				String portname = (String)namemap.get("PORT_NAME");  
				request.setAttribute("proname", proname);
				request.setAttribute("cityname", cityname);
				request.setAttribute("portname", portname); 
			} 
			List portlist = registerService.getCertificateProCityPort(business.getPortid());
			request.setAttribute("portlist", portlist); 
			
			List nationList = null; 
			List promaryList = null; 
			try {
				nationList = registerService.getNation();
				promaryList = registerService.getPromarylistHavePort(); 
			} catch (Exception e) {
				e.printStackTrace();
			} 
			request.setAttribute("nationList", nationList);
			request.setAttribute("promaryList", promaryList);
			
			String orgto = "";	//分配到机构ID
			String coor_view = "";	//授权签字人名
			String auth_view = "";	//授权签字人ID
			List coorlist = assignmentService.getPersonOnAssignment(business.getId().toString(), "1");
			if (coorlist!=null&&coorlist.size()>0) {
				HashMap coor_map = (HashMap) coorlist.get(0);
				auth_view = (String) coor_map.get("PERSON_ID");
				coor_view = (String) coor_map.get("NAME");
				orgto = coor_map.get("ORGTO").toString();
			}
			
		 
			
			request.setAttribute("orgto", orgto);
			request.setAttribute("businessid", business.getId());
			request.setAttribute("coor_view", coor_view);
			request.setAttribute("auth_view", auth_view);
		} catch (Exception e) { 
			e.printStackTrace();
			return ERROR;
		}
		request.setAttribute("list", list);
		return SUCCESS;
	}
	
	 /**
 	 * 编制证书->修改页面->保存
 	 * @return
 	 */
 	public String savecertificate()
 	{
 		
 		HttpServletRequest request = ServletActionContext.getRequest();
 		Map sessionMap = ActionContext.getContext().getSession();	
 	 
 		String company = request.getParameter("company");
 		String vesselname = request.getParameter("vesselname");
 		String vesseltype = request.getParameter("vesseltype");
 		String registry = request.getParameter("registry");
 		String tonnage = request.getParameter("tonnage");
 		
     	String[] destinationcountrys = request.getParameterValues("destinationcountrys");
     	String[] countrys = request.getParameterValues("countrys");
     	String[] ports = request.getParameterValues("ports");
    	String imo = request.getParameter("imo");
     	String berth = request.getParameter("berth");
     	String plandatein = request.getParameter("plandatein");
     	String plandateout = request.getParameter("plandateout");
     	String checkdate = request.getParameter("checkdate");
 		String businessid = request.getParameter("businessid"); 
     	String auth_input_id = request.getParameter("auth_input_id");
     	List list = new ArrayList();
     	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
 		try {
 			 
			
			Business business = businessService.getBusinessById(Long.valueOf(businessid));
			business.setCompany(company);
			business.setVesselname(vesselname);
			business.setVesseltype(vesseltype);
			business.setRegistry(registry);
			business.setTonnage(Long.valueOf(tonnage));
			
			business.setBerth(berth);
			business.setImo(imo); 
			if (plandateout!=null&&!plandateout.equals("")) {
				business.setPlandateout(dateFormat.parse(plandateout));
			}
			if (checkdate!=null&&!checkdate.equals("")) {
				business.setCheckdate(dateFormat.parse(checkdate));
			}
			if (plandatein!=null&&!plandatein.equals("")) {
				business.setPlandatein(dateFormat.parse(plandatein));
			}
			
			List<BusinessDesCountry> businessDesCountryList = new ArrayList<BusinessDesCountry>();
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
		String portid = request.getParameter("portid");
			business.setPortid(request.getParameter("portid"));
			businessService.updateCertificateEdit(business, businessDesCountryList, auth_input_id);
			list.add(business); 
			List<BusinessDesCountry> busdesCountrylist = businessService.getBusinessDesCountry(Long.parseLong(businessid));			
			setBusinessCountrysandPorts(busdesCountrylist, request);	
			List procityportnameList = registerService.getPromaryCity(request.getParameter("portid"));
			if (procityportnameList!=null&&procityportnameList.size()>0) {
				HashMap namemap = (HashMap)procityportnameList.get(0);
				String proname = (String)namemap.get("PRONAME");
				String cityname = (String)namemap.get("CITYNAME");
				String portname = (String)namemap.get("PORT_NAME");
				request.setAttribute("proname", proname);
				request.setAttribute("cityname", cityname);
				request.setAttribute("portname", portname); 
			} 
			List portlist = registerService.getCertificateProCityPort(business.getPortid());
			request.setAttribute("portlist", portlist); 
			
			List nationList = null; 
			List promaryList = null; 
			try {
				nationList = registerService.getNation();
				promaryList = registerService.getPromarylistHavePort(); 
			} catch (Exception e) {
				e.printStackTrace();
			} 
			request.setAttribute("nationList", nationList);
			request.setAttribute("promaryList", promaryList);
			
			String orgto = "";	//分配到机构ID
			String coor_view = "";	//授权签字人名
			String auth_view = "";	//授权签字人ID
			List coorlist = assignmentService.getPersonOnAssignment(business.getId().toString(), "1");
			if (coorlist!=null&&coorlist.size()>0) {
				HashMap coor_map = (HashMap) coorlist.get(0);
				auth_view = (String) coor_map.get("PERSON_ID");
				coor_view = (String) coor_map.get("NAME");
				orgto = coor_map.get("ORGTO").toString();
			}
			 
			request.setAttribute("orgto", orgto);
			request.setAttribute("businessid", business.getId());
			request.setAttribute("coor_view", coor_view); 
			request.setAttribute("auth_view", auth_view);
			request.setAttribute("message", "保存成功");
 		} catch (Exception e) { 
 			e.printStackTrace();  
 			return ERROR; 
 		}
 		request.setAttribute("list", list);
 		return SUCCESS;
 		
 	}
	
	
	public String toAllotCertNo()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		Map sessionMap = ActionContext.getContext().getSession();
		
		Date date = new Date();
		Person person = (Person)sessionMap.get(Constants.ACCOUNT_PERSON);
		String org_id = (String)sessionMap.get(Constants.ACCOUNT_PERSON_ORG);
		String person_id = person.getPersonId();
		String person_name = person.getName();
		
		String certtype = request.getParameter("certtype");	// 证书类型 0正本/ 1副本
		String datefrom = request.getParameter("datefrom");
		String orgto =request.getParameter("orgto");
		String orgto_name = request.getParameter("orgto_name");
		String[] certnolist_select = request.getParameterValues("certnolist_select");
		String orgto4 = "";
		
		try { 
			Organization tempOrganization = new Organization();
			tempOrganization.setId(orgto);
			Organization organization = organizationService.getOrganizationbyId(tempOrganization);
			orgto4 = organization.getOrgId();
			
			if (certtype.equals("0")) {
				
				List<Certificate> cerList = new ArrayList<Certificate>();
				if (certnolist_select!=null&&certnolist_select.length>0) {
					for (int i = 0; i < certnolist_select.length; i++) {
						Certificate certificate = certificateService.getCertificateByCertNo(certnolist_select[i]);
						certificate.setCertstate(BusinessState.LONG_TWO);
						certificate.setOrgId(orgto);
						certificate.setOrgId4(orgto4);
						certificate.setOrgName(orgto_name);
						certificate.setOrgthird(orgto); 
						cerList.add(certificate);
					}
				}
				certificateService.updateCertificateList(cerList); 
				
			}else if (certtype.equals("1")) {	//副本不需要校验
				String certcount = request.getParameter("certcount");
				List<Certificate> certlist = new ArrayList<Certificate>();
				int valicount = Integer.valueOf(certcount);
				if(valicount>100){
					request.setAttribute("message", "副本发放数量一次不能超过100。 "); 
					return "input";  
				}
				for (int i = 0; i < valicount; i++) {
					Certificate certificate = new Certificate(); 
					certificate.setOrgName(orgto_name);
					certificate.setOrgId(orgto);
					certificate.setOrgsecond(orgto);
					certificate.setCerttype(Long.valueOf(certtype));
					certificate.setDatefrom(date);
					certificate.setPersonfrom(person_id);
					certificate.setIsuse(BusinessState.LONG_ZERO);
					certificate.setCertstate(BusinessState.LONG_ONE);
					certificate.setIsapply(BusinessState.LONG_ONE);
					certificate.setOrgId4(orgto4); 
					certlist.add(certificate); 
				}
				
				certificateService.saveCertList(certlist); 
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		
		
		
		return SUCCESS;
	}
	
	
	/**
	 * 空白证书查找
	 * @return
	 */
	public String searchEmpty() 
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		Map sessionMap = ActionContext.getContext().getSession();
		
		String person_ordid = (String) sessionMap.get(Constants.ACCOUNT_PERSON_ORG);
		String is_child = (String)sessionMap.get(Constants.ACCOUNT_PERSON_IS_CHILD);
		String businessname_search = request.getParameter("businessname_search");
		String tagtype = request.getParameter("tagtype");
		String own_orgid4 = (String)sessionMap.get(Constants.ACCOUNT_ORG_ID4);
		
		Pagination p = new Pagination(0, 0, 0);
		String currPage = (String) request.getParameter("goPage");
		if (currPage == null) {
			currPage = "1";// ” currPage”是当前页数
		}
		_logger.info("currPage:" + currPage);
		Integer currPage1 = Integer.parseInt(currPage);
		List list = null;
		try {
			HashMap map = new HashMap();
			map.put("is_child", is_child);
			map.put("orgid4", own_orgid4);
			map.put("orgname", businessname_search); 
			Integer totleCount = certificateService.getEmptyCertByOrgIDCount(map);
			p.getPagination(request,totleCount,
			currPage1,null,null,null);
			int numOfEachPage = Constants.NUMOFEACHPAGE;
			int begin = p.getRownum_begin(numOfEachPage, currPage1);
			list = certificateService.getEmptyCertByOrgID(map, begin, numOfEachPage);
			request.setAttribute("list", list); 
			request.setAttribute("businessname_search", StringUtils.codeHtml(businessname_search));
			request.setAttribute("tagtype", tagtype);  
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("is_child", is_child); 
		this.actionName = "searchEmpty";
		return SUCCESS;
	}
	
	
	
	public String editcert()
	{
		
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String business_id = request.getParameter("businessid");
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM, d, yyyy",Locale.US);
		_logger.info("editcert begin-------");
		Business business = null;
		try {
			business = businessService.getBusinessById(Long.valueOf(business_id));
			
			String appno_view = business.getAppno();	//申请号
			String appdate_view = dateFormat.format(business.getAppdate());	//申请日期
			String businessname_view = business.getBusinessname();	//申请人
			String company_view = business.getCompany();	//船主
			
			String vesselnameandtype_view = "";	//船名及类型
			vesselnameandtype_view = business.getVesselname()+" / "+business.getVesseltype();
			
			String reg_imo_tonnage_view = "";	//船藉 IMO 吨位
			reg_imo_tonnage_view = business.getRegistry()+" / "+business.getImo()+" / "+business.getTonnage()+" MT";
			
			String plandateout_view =	dateFormat.format(business.getPlandateout()) ;	//计划离港日期
			String checkdate_view = dateFormat.format(business.getCheckdate());	//检查日期
			
			String port_view = certificateService.getPortInfo(business.getPortid());	//港口信息
			
			String coor_view = "";
			List coorlist = assignmentService.getPersonOnAssignment(business.getId().toString(), "1");
			if (coorlist!=null&&coorlist.size()>0) {
				HashMap coor_map = (HashMap) coorlist.get(0);
				coor_view = (String) coor_map.get("NAME");
			}
			
			List<BusinessDesCountry> busdesCountrylist = businessService.getBusinessDesCountry(business.getId());
			String countrysandports_input = "";
			for (int i = 0; i < busdesCountrylist.size(); i++) {
				BusinessDesCountry businessDesCountry = busdesCountrylist.get(i);
				countrysandports_input = countrysandports_input+businessDesCountry.getPort()+"/"+businessDesCountry.getCountry()+" ; ";
			}
			if(countrysandports_input.indexOf(";")!=-1){
				countrysandports_input = countrysandports_input.substring(0,countrysandports_input.length()-1);
			} 
			 
			
			request.setAttribute("appno_view", appno_view);
			request.setAttribute("appdate_view", appdate_view);
			request.setAttribute("businessname_view", businessname_view);
			request.setAttribute("company_view", company_view);
			request.setAttribute("vesselnameandtype_view", vesselnameandtype_view);
			request.setAttribute("reg_imo_tonnage_view", reg_imo_tonnage_view);
			request.setAttribute("plandateout_view", plandateout_view);
			request.setAttribute("checkdate_view", checkdate_view);
			request.setAttribute("countrysandports_input", countrysandports_input);
			request.setAttribute("port_view", port_view);
			request.setAttribute("coor_view", coor_view); 
			
			_logger.info("editcert success-------");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
		
		
	}
	
	
	public String lookCert() 
	{
		
		HttpServletRequest request = ServletActionContext.getRequest();
		Map sessionMap = ActionContext.getContext().getSession();
		
		String id = request.getParameter("businessid");
		List list = new ArrayList(); 
    	
		try {
			Business business = businessService.getBusinessById(Long.valueOf(id));
			list.add(business);
			List<BusinessDesCountry> busdesCountrylist = businessService.getBusinessDesCountry(Long.parseLong(id));
			
			setBusinessCountrysandPorts(busdesCountrylist, request);
			
			List procityportnameList = registerService.getPromaryCity(business.getPortid());
			if (procityportnameList!=null&&procityportnameList.size()>0) {
				HashMap namemap = (HashMap)procityportnameList.get(0);
				String proname = (String)namemap.get("PRONAME");
				String cityname = (String)namemap.get("CITYNAME");
				String portname = (String)namemap.get("PORT_NAME");  
				request.setAttribute("proname", proname);
				request.setAttribute("cityname", cityname);
				request.setAttribute("portname", portname); 
			} 
			
			String orgto = "";	//分配到机构ID
			String coor_view = "";	//授权签字人名
			List coorlist = assignmentService.getPersonOnAssignment(business.getId().toString(), "1");
			if (coorlist!=null&&coorlist.size()>0) {
				HashMap coor_map = (HashMap) coorlist.get(0);
				coor_view = (String) coor_map.get("NAME");
				orgto = coor_map.get("ORGTO").toString();
			}
			
			
			
			//印章
//			List stamplist = organizationService.getStampByorgID(orgto);
			 
			certificateEmptylist();
			
			request.setAttribute("orgto", orgto);
			request.setAttribute("businessid", business.getId());
			request.setAttribute("coor_view", coor_view);
		} catch (Exception e) { 
			e.printStackTrace();
			return ERROR;
		}
		request.setAttribute("list", list);
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
	   
	
	
	public String certNo()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		String orgid = request.getParameter("orgid");
		List list = null;
		try {
			list = certificateService.getCanUseCertlist(orgid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("list", list);  
		return SUCCESS;
	}
	
	public String saveCertApp()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		String certnolist = request.getParameter("certnolist");
		String stamp = request.getParameter("stamp");
		String businessid = request.getParameter("businessid");
		Business business = new Business();
		try {
			
//			List certlist = certificateService.getCertByCertno(certnolist);
//			if (certlist!=null&&certlist.size()>0) {
//				HashMap map = (HashMap) certlist.get(0);
//				String id = map.get("ID").toString();
//				business.setCertid(id);
//				business.setId(Long.valueOf(businessid)); 
//				certificateService.updateCertNo(business); 
//			}  
			business.setCertid(certnolist);
			business.setId(Long.valueOf(businessid)); 
			certificateService.updateCertNo(business);  
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}  
		 
		return SUCCESS;
	}
	
	/**	根据机构得到证书来源列表	*/
	public String certinfo()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("orgid");	//机构ID
		List list = null;
		HashMap map = new HashMap();
		try {
			list = certificateService.getCertlist(id);
			request.setAttribute("certlist", list);
		} catch (Exception e) {
			e.printStackTrace(); 
			return ERROR;
		}
		return SUCCESS;
		
	}
	
	
	public String stamp() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String id = request.getParameter("orgid");	//机构ID
		List list = null;
		HashMap map = new HashMap();
		try {
			list = certificateService.getStamps(id);
			
			request.setAttribute("stamplist", list);
			request.setAttribute("orgid", id);
		} catch (Exception e) {
			e.printStackTrace(); 
			return ERROR;
		}
		return SUCCESS;
	}
	
	
	public String savestamp()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String stampid = request.getParameter("stampid");
		String orgid = request.getParameter("orgid");
		HashMap map = new HashMap();
		try {
			List list = certificateService.getStampByStampID(stampid);	//印章存在 则不能更新
			if(list!=null&&list.size()>0){
				request.setAttribute("message", "印章已经存在");
				return SUCCESS;
			}else {
				String orgto = request.getParameter("orgto");
				OrgStamp orgStamp = new OrgStamp();
				orgStamp.setOrgid(orgto); 
				orgStamp.setStampid(stampid);
				certificateService.saveStamp(orgStamp); 
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		request.setAttribute("tagtype", "2");
		return SUCCESS;
	}
	
	
	public String createstamp()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		Map sessionMap = ActionContext.getContext().getSession();
		_logger.info("certificateEmptySend begin ...........");  
		try {
			
			List list = businessService.getOrganizationLevelByOrgID("1", ""); 
			
			request.setAttribute("list", list);  
			_logger.info("certificateEmptySend success ...........");
		} catch (Exception e) { 
			e.printStackTrace();
			return ERROR;
		}
		
		return SUCCESS; 
	}
	
	
	public String deletestamp()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String stampid = request.getParameter("stampid");
		 
		try {
			 certificateService.deleteStampByStampId(stampid);
			 stamp();
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	
	
	/**
	 * 得到7位的数字编号
	 * @return
	 */
	public List numberText(int begin,int end)
	{
		List list = new ArrayList();
		try {
			
			int num = end -begin;
			if (num>10) {
				System.out.println("流水一次分配不能超过10.");
			}
			for (int i = 0; i <= num; i++) { 
				String temp = String.valueOf(begin+i);
				while (temp.length()<7) {
					temp = "0"+temp;
				}
				list.add(temp); 
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public CertificateService getCertificateService() {
		return certificateService;
	}

	public void setCertificateService(CertificateService certificateService) {
		this.certificateService = certificateService;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public OrganizationService getOrganizationService() {
		return organizationService;
	}

	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}
	
	
	public static void main(String[] args) {
		
		String aa = "123";
		while (aa.length()<7) {
			aa = "0"+aa;
		}
		System.out.println(aa);
	}

	public BusinessService getBusinessService() {
		return businessService;
	}

	public void setBusinessService(BusinessService businessService) {
		this.businessService = businessService;
	}

	public AssignmentService getAssignmentService() {
		return assignmentService;
	}

	public void setAssignmentService(AssignmentService assignmentService) {
		this.assignmentService = assignmentService;
	}

	public RegisterService getRegisterService() {
		return registerService;
	}

	public void setRegisterService(RegisterService registerService) {
		this.registerService = registerService;
	}

}
