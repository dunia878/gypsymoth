package com.sinosoft.gypsymoth.pojo;

import java.util.Date;

/**
 * Operator entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Operator extends AbstractOperator implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Operator() {
	}

	/** minimal constructor */
	public Operator(Long id) {
		super(id);
	}

	/** full constructor */
	public Operator(Long id, Long businessid, String operatorid,
			Long disposetype, Date operatortime, String remark) {
		super(id, businessid, operatorid, disposetype, operatortime, remark);
	}

}
