package com.sinosoft.gypsymoth.pojo;

import java.util.Date;

/**
 * Opaccount entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Opaccount extends AbstractOpaccount implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Opaccount() {
	}

	/** full constructor */
	public Opaccount(Long id, String operator, String opTime, String opState,
			String remark, String isAccount, String isPay, String isCurrency,
			String opContent, Long businessid) {
		super(id, operator, opTime, opState, remark, isAccount, isPay,
				isCurrency, opContent, businessid);
	}

}
