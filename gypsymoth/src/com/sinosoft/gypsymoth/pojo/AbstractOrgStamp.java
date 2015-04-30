package com.sinosoft.gypsymoth.pojo;

/**
 * AbstractOrgStamp entity provides the base persistence definition of the
 * OrgStamp entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractOrgStamp implements java.io.Serializable {

	// Fields

	private Long id;
	private String orgid;
	private String stampid;

	// Constructors

	/** default constructor */
	public AbstractOrgStamp() {
	}

	/** minimal constructor */
	public AbstractOrgStamp(Long id) {
		this.id = id;
	}

	/** full constructor */
	public AbstractOrgStamp(Long id, String orgid, String stampid) {
		this.id = id;
		this.orgid = orgid;
		this.stampid = stampid;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrgid() {
		return this.orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getStampid() {
		return this.stampid;
	}

	public void setStampid(String stampid) {
		this.stampid = stampid;
	}

}