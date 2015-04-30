package com.sinosoft.gypsymoth.pojo;

import java.util.Date;

/**
 * Payment entity. @author MyEclipse Persistence Tools
 */
public class Payment extends AbstractPayment implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Payment() {
	}

	/** minimal constructor */
	public Payment(Long id) {
		super(id);
	}

	/** full constructor */
	public Payment(Long id, Long businessid, Long paystate, Float specialpay,
			String payremark, Date paymentdate, Long ledgerstate,
			Float firstcompay, Float secondcompay, Float thirdcompay,
			String firstcomid, String secondcomid, String thirdcomid, Float money,Float amount,Integer currency,Date ledgerdate,String paymentBy) {
		super(id, businessid, paystate, specialpay, payremark, paymentdate,
				ledgerstate, firstcompay, secondcompay, thirdcompay,
				firstcomid, secondcomid, thirdcomid, money,amount,currency,ledgerdate,paymentBy);
	}

}
