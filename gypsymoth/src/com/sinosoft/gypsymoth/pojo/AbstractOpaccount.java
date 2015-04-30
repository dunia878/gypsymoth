package com.sinosoft.gypsymoth.pojo;

import java.util.Date;

/**
 * AbstractOpaccount entity provides the base persistence definition of the
 * Opaccount entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractOpaccount implements java.io.Serializable {

	// Fields

	private Long id;
	private String operator;
	private String opTime;
	private String opState;
	private String remark;
	private String isAccount;
	private String isPay;
	private String isCurrency;
	private String opContent;
	private Long businessid;

	// Constructors

	/** default constructor */
	public AbstractOpaccount() {
	}

	/** full constructor */
	public AbstractOpaccount(Long id, String operator, String opTime,
			String opState, String remark, String isAccount, String isPay,
			String isCurrency, String opContent, Long businessid) {
		this.id = id;
		this.operator = operator;
		this.opTime = opTime;
		this.opState = opState;
		this.remark = remark;
		this.isAccount = isAccount;
		this.isPay = isPay;
		this.isCurrency = isCurrency;
		this.opContent = opContent;
		this.businessid = businessid;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	
	public String getOpTime() {
		return opTime;
	}

	public void setOpTime(String opTime) {
		this.opTime = opTime;
	}

	public String getOpState() {
		return this.opState;
	}

	public void setOpState(String opState) {
		this.opState = opState;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIsAccount() {
		return this.isAccount;
	}

	public void setIsAccount(String isAccount) {
		this.isAccount = isAccount;
	}

	public String getIsPay() {
		return this.isPay;
	}

	public void setIsPay(String isPay) {
		this.isPay = isPay;
	}

	public String getIsCurrency() {
		return this.isCurrency;
	}

	public void setIsCurrency(String isCurrency) {
		this.isCurrency = isCurrency;
	}

	public String getOpContent() {
		return this.opContent;
	}

	public void setOpContent(String opContent) {
		this.opContent = opContent;
	}

	public Long getBusinessid() {
		return this.businessid;
	}

	public void setBusinessid(Long businessid) {
		this.businessid = businessid;
	}

}