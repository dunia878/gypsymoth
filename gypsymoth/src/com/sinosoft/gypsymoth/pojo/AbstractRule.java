package com.sinosoft.gypsymoth.pojo;

import java.util.Date;

/**
 * AbstractRule entity provides the base persistence definition of the Rule
 * entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractRule implements java.io.Serializable {

	// Fields

	private Long id;
	private Long one;
	private Long two;
	private Long three;
	private String operatorid;
	private Date operatortime;
	private String rulestate;

	// Constructors

	/** default constructor */
	public AbstractRule() {
	}

	/** full constructor */
	public AbstractRule(Long one, Long two, Long three, String operatorid,
			Date operatortime, String rulestate) {
		this.one = one;
		this.two = two;
		this.three = three;
		this.operatorid = operatorid;
		this.operatortime = operatortime;
		this.rulestate = rulestate;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOne() {
		return this.one;
	}

	public void setOne(Long one) {
		this.one = one;
	}

	public Long getTwo() {
		return this.two;
	}

	public void setTwo(Long two) {
		this.two = two;
	}

	public Long getThree() {
		return this.three;
	}

	public void setThree(Long three) {
		this.three = three;
	}

	public String getOperatorid() {
		return this.operatorid;
	}

	public void setOperatorid(String operatorid) {
		this.operatorid = operatorid;
	}

	public Date getOperatortime() {
		return this.operatortime;
	}

	public void setOperatortime(Date operatortime) {
		this.operatortime = operatortime;
	}

	public String getRulestate() {
		return this.rulestate;
	}

	public void setRulestate(String rulestate) {
		this.rulestate = rulestate;
	}

}