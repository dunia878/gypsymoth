package com.sinosoft.gypsymoth.pojo;

import java.util.Date;

/**
 * Certificate entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Certificate extends AbstractCertificate implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Certificate() {
	}

	/** minimal constructor */
	public Certificate(Long id) {
		super(id);
	}

	/** full constructor */
	public Certificate(Long id, String orgName, Long certtype, String certno,
			Date dateto, Date datefrom, String personfrom, String personto,
			String certname, Long isuse, String orgsecond, String orgthird,
			Long certmax, Long certmin, String orgId, Long certstate,
			Long isapply,String certhead,String orgId4) {
		super(id, orgName, certtype, certno, dateto, datefrom, personfrom,
				personto, certname, isuse, orgsecond, orgthird, certmax,
				certmin, orgId, certstate, isapply,certhead,orgId4);
	}

}
