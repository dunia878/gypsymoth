package com.sinosoft.gypsymoth.pojo;

import java.util.Date;

/**
 * AbstractPayment entity provides the base persistence definition of the
 * Payment entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractPayment implements java.io.Serializable {

	// Fields

	private Long id;
	private Long businessid;
	private Long paystate;
	private Float specialpay;
	private String payremark;
	private Date paymentdate;
	private Long ledgerstate;
	private Float firstcompay;
	private Float secondcompay;
	private Float thirdcompay;
	private String firstcomid;
	private String secondcomid;
	private String thirdcomid;
	private Float money;
    private Float amount;
    private Integer currency;
    private Date ledgerdate;
    private String paymentBy;

	// Constructors

	/** default constructor */
	public AbstractPayment() {
	}

	/** minimal constructor */
	public AbstractPayment(Long id) {
		this.id = id;
	}

	/** full constructor */
	public AbstractPayment(Long id, Long businessid, Long paystate,
			Float specialpay, String payremark, Date paymentdate,
			Long ledgerstate, Float firstcompay, Float secondcompay,
			Float thirdcompay, String firstcomid, String secondcomid,
			String thirdcomid, Float money,Float amount,Integer currency,Date ledgerdate,String paymentBy) {
		this.id = id;
		this.businessid = businessid;
		this.paystate = paystate;
		this.specialpay = specialpay;
		this.payremark = payremark;
		this.paymentdate = paymentdate;
		this.ledgerstate = ledgerstate;
		this.firstcompay = firstcompay;
		this.secondcompay = secondcompay;
		this.thirdcompay = thirdcompay;
		this.firstcomid = firstcomid;
		this.secondcomid = secondcomid;
		this.thirdcomid = thirdcomid;
		this.money = money;
		this.amount = amount;
		this.currency = currency;
		this.ledgerdate = ledgerdate;
		this.paymentBy = paymentBy;
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

	public Long getPaystate() {
		return this.paystate;
	}

	public void setPaystate(Long paystate) {
		this.paystate = paystate;
	}

	public Float getSpecialpay() {
		return this.specialpay;
	}

	public void setSpecialpay(Float specialpay) {
		this.specialpay = specialpay;
	}

	public String getPayremark() {
		return this.payremark;
	}

	public void setPayremark(String payremark) {
		this.payremark = payremark;
	}

	public Date getPaymentdate() {
		return this.paymentdate;
	}

	public void setPaymentdate(Date paymentdate) {
		this.paymentdate = paymentdate;
	}

	public Long getLedgerstate() {
		return this.ledgerstate;
	}

	public void setLedgerstate(Long ledgerstate) {
		this.ledgerstate = ledgerstate;
	}

	public Float getFirstcompay() {
		return this.firstcompay;
	}

	public void setFirstcompay(Float firstcompay) {
		this.firstcompay = firstcompay;
	}

	public Float getSecondcompay() {
		return this.secondcompay;
	}

	public void setSecondcompay(Float secondcompay) {
		this.secondcompay = secondcompay;
	}

	public Float getThirdcompay() {
		return this.thirdcompay;
	}

	public void setThirdcompay(Float thirdcompay) {
		this.thirdcompay = thirdcompay;
	}

	public String getFirstcomid() {
		return this.firstcomid;
	}

	public void setFirstcomid(String firstcomid) {
		this.firstcomid = firstcomid;
	}

	public String getSecondcomid() {
		return this.secondcomid;
	}

	public void setSecondcomid(String secondcomid) {
		this.secondcomid = secondcomid;
	}

	public String getThirdcomid() {
		return this.thirdcomid;
	}

	public void setThirdcomid(String thirdcomid) {
		this.thirdcomid = thirdcomid;
	}

	public Float getMoney() {
		return this.money;
	}

	public void setMoney(Float money) {
		this.money = money;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Integer getCurrency() {
		return currency;
	}

	public void setCurrency(Integer currency) {
		this.currency = currency;
	}

	public Date getLedgerdate() {
		return ledgerdate;
	}

	public void setLedgerdate(Date ledgerdate) {
		this.ledgerdate = ledgerdate;
	}

	public String getPaymentBy() {
		return paymentBy;
	}

	public void setPaymentBy(String paymentBy) {
		this.paymentBy = paymentBy;
	}

	
}