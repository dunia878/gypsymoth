package com.sinosoft.gypsymoth.pojo;

/**
 * AbstractBusinessAppno entity provides the base persistence definition of the
 * BusinessAppno entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractBusinessAppno implements java.io.Serializable {

	// Fields

	private Long id;
	private String businessid;
	private String appno;

	// Constructors

	/** default constructor */
	public AbstractBusinessAppno() {
	}

	/** minimal constructor */
	public AbstractBusinessAppno(Long id) {
		this.id = id;
	}

	/** full constructor */
	public AbstractBusinessAppno(Long id, String businessid, String appno) {
		this.id = id;
		this.businessid = businessid;
		this.appno = appno;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBusinessid() {
		return this.businessid;
	}

	public void setBusinessid(String businessid) {
		this.businessid = businessid;
	}

	public String getAppno() {
		return this.appno;
	}

	public void setAppno(String appno) {
		this.appno = appno;
	}

}