package com.sinosoft.gypsymoth.pojo;

/**
 * OrgStamp entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class OrgStamp extends AbstractOrgStamp implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public OrgStamp() {
	}

	/** minimal constructor */
	public OrgStamp(Long id) {
		super(id);
	}

	/** full constructor */
	public OrgStamp(Long id, String orgid, String stampid) {
		super(id, orgid, stampid);
	}

}
