package com.sinosoft.gypsymoth.pojo;

import java.util.Date;

/**
 * AbstractOperator entity provides the base persistence definition of the
 * Operator entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractOperator implements java.io.Serializable {

	// Fields

	private Long id;
	private Long businessid;
	private String operatorid;
	private Long disposetype;
	private Date operatortime;
	private String remark;

	// Constructors

	/** default constructor */
	public AbstractOperator() {
	}

	/** minimal constructor */
	public AbstractOperator(Long id) {
		this.id = id;
	}

	/** full constructor */
	public AbstractOperator(Long id, Long businessid, String operatorid,
			Long disposetype, Date operatortime, String remark) {
		this.id = id;
		this.businessid = businessid;
		this.operatorid = operatorid;
		this.disposetype = disposetype;
		this.operatortime = operatortime;
		this.remark = remark;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBusinessid() {
		return this.businessid;
	}

	public void setBusinessid(Long businessid) {
		this.businessid = businessid;
	}

	public String getOperatorid() {
		return this.operatorid;
	}

	public void setOperatorid(String operatorid) {
		this.operatorid = operatorid;
	}

	public Long getDisposetype() {
		return this.disposetype;
	}

	public void setDisposetype(Long disposetype) {
		this.disposetype = disposetype;
	}

	public Date getOperatortime() {
		return this.operatortime;
	}

	public void setOperatortime(Date operatortime) {
		this.operatortime = operatortime;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}