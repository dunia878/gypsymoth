package com.sinosoft.gypsymoth.pojo;

/**
 * AbstractEmailInformation entity provides the base persistence definition of
 * the EmailInformation entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractEmailInformation implements java.io.Serializable {

	// Fields

	private Long id;
	private Long businessid;
	private String billtop;
	private String address;
	private String receiver;
	private String postcode;
	private String phone;

	// Constructors

	/** default constructor */
	public AbstractEmailInformation() {
	}

	/** minimal constructor */
	public AbstractEmailInformation(Long businessid) {
		this.businessid = businessid;
	}

	/** full constructor */
	public AbstractEmailInformation(Long businessid, String billtop,
			String address, String receiver, String postcode, String phone) {
		this.businessid = businessid;
		this.billtop = billtop;
		this.address = address;
		this.receiver = receiver;
		this.postcode = postcode;
		this.phone = phone;
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

	public String getBilltop() {
		return this.billtop;
	}

	public void setBilltop(String billtop) {
		this.billtop = billtop;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getReceiver() {
		return this.receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}