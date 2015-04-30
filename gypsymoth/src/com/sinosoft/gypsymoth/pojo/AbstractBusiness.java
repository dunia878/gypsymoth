package com.sinosoft.gypsymoth.pojo;

import java.util.Date;

/**
 * AbstractBusiness entity provides the base persistence definition of the
 * Business entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractBusiness implements java.io.Serializable {

	// Fields

	private Long id;
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
	private Integer qualified;
	private String certid;
	private String stampid;
	private String accountid;
	private String orgid;
	private String invoicetitle;
	private String invoiceaddress;
	private String invoicepost;
	private String invoicer;
	private String invoicetel;
	private String special;
	private String linkphone;
	private String tempno;
	private String portorgid;
	private String inspection1;
	private String inspection2;
	private String inspectiondate;
	// Constructors

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

	/** default constructor */
	public AbstractBusiness() {
	}

	/** full constructor */
	public AbstractBusiness(String businessname,String appname, String appno, Date appdate,
			Long destinationcountry, Date plandatein, Date plandateout,
			Date checkdate, Long businessstate, String linkmanname,
			String vesseltype, String registry, String vesselno, Long tonnage,
			String vesselname, String imo, String berth, String certification,
			String portid, String company, String remark,String phone,String email,String fax,Integer qualified,
			String certid,String stampid,String accountid,String orgid,
			String invoicetitle,String invoiceaddress,String invoicepost,
			String invoicer,String invoicetel,String special,String linkphone,String tempno,String portorgid,
			String inspection1,String inspection2,String inspectiondate) {
		this.businessname = businessname;
		this.appno = appno;
		this.appdate = appdate;
		this.appname = appname;
		this.destinationcountry = destinationcountry;
		this.plandatein = plandatein;
		this.plandateout = plandateout;
		this.checkdate = checkdate;
		this.businessstate = businessstate;
		this.linkmanname = linkmanname;
		this.vesseltype = vesseltype;
		this.registry = registry;
		this.vesselno = vesselno;
		this.tonnage = tonnage;
		this.vesselname = vesselname;
		this.imo = imo;
		this.berth = berth;
		this.certification = certification;
		this.portid = portid;
		this.company = company;
		this.remark = remark;
		this.email = email;
		this.fax = fax;
		this.phone = phone;
		this.qualified = qualified;
		this.certid = certid;
		this.stampid = stampid;
		this.accountid = accountid;
		this.orgid = orgid;
		this.invoicetitle = invoicetitle;
		this.invoiceaddress = invoiceaddress;
		this.invoicepost = invoicepost;
		this.invoicer = invoicer;
		this.invoicetel = invoicetel;
		this.special = special;
		this.linkphone = linkphone;
		this.tempno = tempno;
		this.portorgid = portorgid;
		this.inspection1 = inspection1;
		this.inspection2 = inspection2;
		this.inspectiondate = inspectiondate;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBusinessname() {
		return this.businessname;
	}

	public void setBusinessname(String businessname) {
		this.businessname = businessname;
	}

	public String getAppno() {
		return this.appno;
	}

	public void setAppno(String appno) {
		this.appno = appno;
	}

	public Date getAppdate() {
		return this.appdate;
	}

	public void setAppdate(Date appdate) {
		this.appdate = appdate;
	}

	public Long getDestinationcountry() {
		return this.destinationcountry;
	}

	public void setDestinationcountry(Long destinationcountry) {
		this.destinationcountry = destinationcountry;
	}

	public Date getPlandatein() {
		return this.plandatein;
	}

	public void setPlandatein(Date plandatein) {
		this.plandatein = plandatein;
	}

	public Date getPlandateout() {
		return this.plandateout;
	}

	public void setPlandateout(Date plandateout) {
		this.plandateout = plandateout;
	}

	public Date getCheckdate() {
		return this.checkdate;
	}

	public void setCheckdate(Date checkdate) {
		this.checkdate = checkdate;
	}

	public Long getBusinessstate() {
		return this.businessstate;
	}

	public void setBusinessstate(Long businessstate) {
		this.businessstate = businessstate;
	}

	public String getLinkmanname() {
		return this.linkmanname;
	}

	public void setLinkmanname(String linkmanname) {
		this.linkmanname = linkmanname;
	}

	public String getVesseltype() {
		return this.vesseltype;
	}

	public void setVesseltype(String vesseltype) {
		this.vesseltype = vesseltype;
	}

	public String getRegistry() {
		return this.registry;
	}

	public void setRegistry(String registry) {
		this.registry = registry;
	}

	public String getVesselno() {
		return this.vesselno;
	}

	public void setVesselno(String vesselno) {
		this.vesselno = vesselno;
	}

	public Long getTonnage() {
		return this.tonnage;
	}

	public void setTonnage(Long tonnage) {
		this.tonnage = tonnage;
	}

	public String getVesselname() {
		return this.vesselname;
	}

	public void setVesselname(String vesselname) {
		this.vesselname = vesselname;
	}

	public String getImo() {
		return this.imo;
	}

	public void setImo(String imo) {
		this.imo = imo;
	}

	public String getBerth() {
		return this.berth;
	}

	public void setBerth(String berth) {
		this.berth = berth;
	}

	public String getCertification() {
		return this.certification;
	}

	public void setCertification(String certification) {
		this.certification = certification;
	}

	public String getPortid() {
		return this.portid;
	}

	public void setPortid(String portid) {
		this.portid = portid;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
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

	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getLinkphone() {
		return linkphone;
	}

	public void setLinkphone(String linkphone) {
		this.linkphone = linkphone;
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

	public String getInspection1() {
		return inspection1;
	}

	public void setInspection1(String inspection1) {
		this.inspection1 = inspection1;
	}

	public String getInspection2() {
		return inspection2;
	}

	public void setInspection2(String inspection2) {
		this.inspection2 = inspection2;
	}

	public String getInspectiondate() {
		return inspectiondate;
	}

	public void setInspectiondate(String inspectiondate) {
		this.inspectiondate = inspectiondate;
	}


}