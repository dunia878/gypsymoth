package com.sinosoft.gypsymoth.pojo;

/**
 * AbstractRegistry entity provides the base persistence definition of the
 * Registry entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractRegistry implements java.io.Serializable {

	// Fields

	private Long id;
	private Long port;
	private String country;
	private String remark;

	// Constructors

	/** default constructor */
	public AbstractRegistry() {
	}

	/** full constructor */
	public AbstractRegistry(Long port, String country, String remark) {
		this.port = port;
		this.country = country;
		this.remark = remark;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPort() {
		return this.port;
	}

	public void setPort(Long port) {
		this.port = port;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}