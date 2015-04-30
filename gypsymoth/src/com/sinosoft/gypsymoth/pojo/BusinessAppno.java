package com.sinosoft.gypsymoth.pojo;

/**
 * BusinessAppno entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class BusinessAppno extends AbstractBusinessAppno implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public BusinessAppno() {
	}

	/** minimal constructor */
	public BusinessAppno(Long id) {
		super(id);
	}

	/** full constructor */
	public BusinessAppno(Long id, String businessid, String appno) {
		super(id, businessid, appno);
	}

}
