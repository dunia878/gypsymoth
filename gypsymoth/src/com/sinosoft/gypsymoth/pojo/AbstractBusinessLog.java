package com.sinosoft.gypsymoth.pojo;

import java.util.Date;

public abstract class AbstractBusinessLog implements java.io.Serializable {
	
	private Long Id;
	private String description;
	private Date operatorDate;
	private Date operator;
	private Long businessId;
	private String businessName;
	private Integer appNo;
	private Date appDate;
	private String company;
	private String destinationCountry;
	private String port;
	private Date planDateIn;
	private Date planDateOut;
	private Integer businessState;
	private String linkmanName;
	private Integer IMO;
	private Integer vesselType;
	private String vesselName;
	private String registry;
	private Integer vesselNo;
	private Integer tonnage;
	private String berth;
	private String certification;
	private Long paperNo;
	
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getOperatorDate() {
		return operatorDate;
	}
	public void setOperatorDate(Date operatorDate) {
		this.operatorDate = operatorDate;
	}
	public Date getOperator() {
		return operator;
	}
	public void setOperator(Date operator) {
		this.operator = operator;
	}
	public Long getBusinessId() {
		return businessId;
	}
	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public Integer getAppNo() {
		return appNo;
	}
	public void setAppNo(Integer appNo) {
		this.appNo = appNo;
	}
	public Date getAppDate() {
		return appDate;
	}
	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getDestinationCountry() {
		return destinationCountry;
	}
	public void setDestinationCountry(String destinationCountry) {
		this.destinationCountry = destinationCountry;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public Date getPlanDateIn() {
		return planDateIn;
	}
	public void setPlanDateIn(Date planDateIn) {
		this.planDateIn = planDateIn;
	}
	public Date getPlanDateOut() {
		return planDateOut;
	}
	public void setPlanDateOut(Date planDateOut) {
		this.planDateOut = planDateOut;
	}
	public Integer getBusinessState() {
		return businessState;
	}
	public void setBusinessState(Integer businessState) {
		this.businessState = businessState;
	}
	public String getLinkmanName() {
		return linkmanName;
	}
	public void setLinkmanName(String linkmanName) {
		this.linkmanName = linkmanName;
	}
	public Integer getIMO() {
		return IMO;
	}
	public void setIMO(Integer imo) {
		IMO = imo;
	}
	public Integer getVesselType() {
		return vesselType;
	}
	public void setVesselType(Integer vesselType) {
		this.vesselType = vesselType;
	}
	public String getVesselName() {
		return vesselName;
	}
	public void setVesselName(String vesselName) {
		this.vesselName = vesselName;
	}
	public String getRegistry() {
		return registry;
	}
	public void setRegistry(String registry) {
		this.registry = registry;
	}
	public Integer getVesselNo() {
		return vesselNo;
	}
	public void setVesselNo(Integer vesselNo) {
		this.vesselNo = vesselNo;
	}
	public Integer getTonnage() {
		return tonnage;
	}
	public void setTonnage(Integer tonnage) {
		this.tonnage = tonnage;
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
	public Long getPaperNo() {
		return paperNo;
	}
	public void setPaperNo(Long paperNo) {
		this.paperNo = paperNo;
	}

}
