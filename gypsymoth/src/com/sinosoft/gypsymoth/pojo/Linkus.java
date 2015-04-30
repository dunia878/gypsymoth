package com.sinosoft.gypsymoth.pojo;

import java.util.Date;

/**
 * Linkus entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Linkus extends AbstractLinkus implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Linkus() {
	}

	/** full constructor */
	public Linkus(String linkuscontent, String linkusname, Date createtime,
			String linkustype) {
		super(linkuscontent, linkusname, createtime, linkustype);
	}

}
