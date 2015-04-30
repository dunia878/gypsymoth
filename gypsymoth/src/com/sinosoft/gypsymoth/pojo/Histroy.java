package com.sinosoft.gypsymoth.pojo;

import java.util.Date;

/**
 * Histroy entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Histroy extends AbstractHistroy implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Histroy() {
	}

	/** full constructor */
	public Histroy(String description, Date operatordate, String operator,
			String businessname, String appno, String imo, Long businessid,
			Date appdate, String destinationcountry, Date plandatein,
			Date plandateout, Date checkdate, Long businessstate,
			String linkmanname, String vesseltype, String registry,
			String vesselno, Long tonnage, String vesselname, String berth,
			String certification, String port, String company) {
		super(description, operatordate, operator, businessname, appno, imo,
				businessid, appdate, destinationcountry, plandatein,
				plandateout, checkdate, businessstate, linkmanname, vesseltype,
				registry, vesselno, tonnage, vesselname, berth, certification,
				port, company);
	}

}
