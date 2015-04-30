package com.sinosoft.gypsymoth.pojo;

/**
 * Registry entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Registry extends AbstractRegistry implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Registry() {
	}

	/** full constructor */
	public Registry(Long port, String country, String remark) {
		super(port, country, remark);
	}

}
