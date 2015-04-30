package com.sinosoft.gypsymoth.pojo;

import java.util.Date;

/**
 * AbstractHistroy entity provides the base persistence definition of the
 * Histroy entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractHistroy implements java.io.Serializable {

	// Fields

	private Long id;
	private String description;
	private Date operatordate;
	private String operator;
	private String businessname;
	private String appno;
	private String imo;
	private Long businessid;
	private Date appdate;
	private String destinationcountry;
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
	private String berth;
	private String certification;
	private String port;
	private String company;

	// Constructors

	/** default constructor */
	public AbstractHistroy() {
	}

	/** full constructor */
	public AbstractHistroy(String description, Date operatordate,
			String operator, String businessname, String appno, String imo,
			Long businessid, Date appdate, String destinationcountry,
			Date plandatein, Date plandateout, Date checkdate,
			Long businessstate, String linkmanname, String vesseltype,
			String registry, String vesselno, Long tonnage, String vesselname,
			String berth, String certification, String port, String company) {
		this.description = description;
		this.operatordate = operatordate;
		this.operator = operator;
		this.businessname = businessname;
		this.appno = appno;
		this.imo = imo;
		this.businessid = businessid;
		this.appdate = appdate;
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
		this.berth = berth;
		this.certification = certification;
		this.port = port;
		this.company = company;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getOperatordate() {
		return this.operatordate;
	}

	public void setOperatordate(Date operatordate) {
		this.operatordate = operatordate;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
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



	public String getImo() {
		return imo;
	}

	public void setImo(String imo) {
		this.imo = imo;
	}

	public Long getBusinessid() {
		return this.businessid;
	}

	public void setBusinessid(Long businessid) {
		this.businessid = businessid;
	}

	public Date getAppdate() {
		return this.appdate;
	}

	public void setAppdate(Date appdate) {
		this.appdate = appdate;
	}

	public String getDestinationcountry() {
		return this.destinationcountry;
	}

	public void setDestinationcountry(String destinationcountry) {
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

	public String getPort() {
		return this.port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

}