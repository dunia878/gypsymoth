package com.sinosoft.gypsymoth.pojo;

import java.util.Date;

/**
 * AbstractCertificate entity provides the base persistence definition of the
 * Certificate entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractCertificate implements java.io.Serializable {

	// Fields

	private Long id;
	private String orgName;
	private Long certtype;
	private String certno;
	private Date dateto;
	private Date datefrom;
	private String personfrom;
	private String personto;
	private String certname;
	private Long isuse;
	private String orgsecond;
	private String orgthird;
	private Long certmax;
	private Long certmin;
	private String orgId;
	private Long certstate;
	private Long isapply;
	private String certhead;
	private String orgId4;

	// Constructors

	/** default constructor */
	public AbstractCertificate() {
	}

	/** minimal constructor */
	public AbstractCertificate(Long id) {
		this.id = id;
	}

	/** full constructor */
	public AbstractCertificate(Long id, String orgName, Long certtype,
			String certno, Date dateto, Date datefrom, String personfrom,
			String personto, String certname, Long isuse, String orgsecond,
			String orgthird, Long certmax, Long certmin, String orgId,
			Long certstate, Long isapply,String certhead,String orgId4) {
		this.id = id;
		this.orgName = orgName;
		this.certtype = certtype;
		this.certno = certno;
		this.dateto = dateto;
		this.datefrom = datefrom;
		this.personfrom = personfrom;
		this.personto = personto;
		this.certname = certname;
		this.isuse = isuse;
		this.orgsecond = orgsecond;
		this.orgthird = orgthird;
		this.certmax = certmax;
		this.certmin = certmin;
		this.orgId = orgId;
		this.certstate = certstate;
		this.isapply = isapply;
		this.certhead = certhead;
		this.orgId4 = orgId4;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Long getCerttype() {
		return this.certtype;
	}

	public void setCerttype(Long certtype) {
		this.certtype = certtype;
	}

	public String getCertno() {
		return this.certno;
	}

	public void setCertno(String certno) {
		this.certno = certno;
	}

	public Date getDateto() {
		return this.dateto;
	}

	public void setDateto(Date dateto) {
		this.dateto = dateto;
	}

	public Date getDatefrom() {
		return this.datefrom;
	}

	public void setDatefrom(Date datefrom) {
		this.datefrom = datefrom;
	}

	public String getPersonfrom() {
		return this.personfrom;
	}

	public void setPersonfrom(String personfrom) {
		this.personfrom = personfrom;
	}

	public String getPersonto() {
		return this.personto;
	}

	public void setPersonto(String personto) {
		this.personto = personto;
	}

	public String getCertname() {
		return this.certname;
	}

	public void setCertname(String certname) {
		this.certname = certname;
	}

	public Long getIsuse() {
		return this.isuse;
	}

	public void setIsuse(Long isuse) {
		this.isuse = isuse;
	}

	public String getOrgsecond() {
		return this.orgsecond;
	}

	public void setOrgsecond(String orgsecond) {
		this.orgsecond = orgsecond;
	}

	public String getOrgthird() {
		return this.orgthird;
	}

	public void setOrgthird(String orgthird) {
		this.orgthird = orgthird;
	}

	public Long getCertmax() {
		return this.certmax;
	}

	public void setCertmax(Long certmax) {
		this.certmax = certmax;
	}

	public Long getCertmin() {
		return this.certmin;
	}

	public void setCertmin(Long certmin) {
		this.certmin = certmin;
	}

	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public Long getCertstate() {
		return this.certstate;
	}

	public void setCertstate(Long certstate) {
		this.certstate = certstate;
	}

	public Long getIsapply() {
		return this.isapply;
	}

	public void setIsapply(Long isapply) {
		this.isapply = isapply;
	}

	public String getCerthead() {
		return certhead;
	}

	public void setCerthead(String certhead) {
		this.certhead = certhead;
	}

	public String getOrgId4() {
		return orgId4;
	}

	public void setOrgId4(String orgId4) {
		this.orgId4 = orgId4;
	}

}