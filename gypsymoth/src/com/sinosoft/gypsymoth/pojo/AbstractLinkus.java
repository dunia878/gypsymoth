package com.sinosoft.gypsymoth.pojo;

import java.util.Date;

/**
 * AbstractLinkus entity provides the base persistence definition of the Linkus
 * entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractLinkus implements java.io.Serializable {

	// Fields

	private Long linkusid;
	private String linkuscontent;
	private String linkusname;
	private Date createtime;
	private String linkustype;

	// Constructors

	/** default constructor */
	public AbstractLinkus() {
	}

	/** full constructor */
	public AbstractLinkus(String linkuscontent, String linkusname,
			Date createtime, String linkustipe) {
		this.linkuscontent = linkuscontent;
		this.linkusname = linkusname;
		this.createtime = createtime;
		this.linkustype = linkustype;
	}

	// Property accessors

	public Long getLinkusid() {
		return this.linkusid;
	}

	public void setLinkusid(Long linkusid) {
		this.linkusid = linkusid;
	}

	public String getLinkuscontent() {
		return this.linkuscontent;
	}

	public void setLinkuscontent(String linkuscontent) {
		this.linkuscontent = linkuscontent;
	}

	public String getLinkusname() {
		return this.linkusname;
	}

	public void setLinkusname(String linkusname) {
		this.linkusname = linkusname;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getLinkustype() {
		return linkustype;
	}

	public void setLinkustype(String linkustype) {
		this.linkustype = linkustype;
	}



}