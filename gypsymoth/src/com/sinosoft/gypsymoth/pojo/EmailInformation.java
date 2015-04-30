package com.sinosoft.gypsymoth.pojo;

/**
 * EmailInformation entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class EmailInformation extends AbstractEmailInformation implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public EmailInformation() {
	}

	/** minimal constructor */
	public EmailInformation(Long businessid) {
		super(businessid);
	}

	/** full constructor */
	public EmailInformation(Long businessid, String billtop, String address,
			String receiver, String postcode, String phone) {
		super(businessid, billtop, address, receiver, postcode, phone);
	}

}
