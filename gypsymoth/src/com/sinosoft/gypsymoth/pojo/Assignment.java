package com.sinosoft.gypsymoth.pojo;

import java.util.Date;

/**
 * Assignment entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Assignment extends AbstractAssignment implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Assignment() {
	}

	/** minimal constructor */
	public Assignment(Long id) {
		super(id);
	}

	/** full constructor */
	public Assignment(Long id, String personto, Long assignumber,
			Date assigntime, String remark, Long assignerstate,
			Long businessid, String personfrom, String orgfrom, String orgto,
			Long assignrole, Long iscomplete,Long isapplay,String org4) {
		super(id, personto, assignumber, assigntime, remark, assignerstate,
				businessid, personfrom, orgfrom, orgto, assignrole, iscomplete,isapplay,org4);
	}

}
