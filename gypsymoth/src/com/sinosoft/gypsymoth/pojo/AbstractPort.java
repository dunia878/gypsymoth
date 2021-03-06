package com.sinosoft.gypsymoth.pojo;

/**
 * AbstractPort generated by MyEclipse Persistence Tools
 */

public abstract class AbstractPort implements java.io.Serializable {

	// Fields

	private Integer id;
	private String portId;
	private String portName;
	private String portSname;
	private Integer proid;
	private Integer cityid;

	// Constructors

	/** default constructor */
	public AbstractPort() {
	}

	/** minimal constructor */
	public AbstractPort(String portId, String portName, String portSname) {
		this.portId = portId;
		this.portName = portName;
		this.portSname = portSname;
	}

	/** full constructor */
	public AbstractPort(String portId, String portName, String portSname,
			Integer proid, Integer cityid) {
		this.portId = portId;
		this.portName = portName;
		this.portSname = portSname;
		this.proid = proid;
		this.cityid = cityid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPortId() {
		return this.portId;
	}

	public void setPortId(String portId) {
		this.portId = portId;
	}

	public String getPortName() {
		return this.portName;
	}

	public void setPortName(String portName) {
		this.portName = portName;
	}

	public String getPortSname() {
		return this.portSname;
	}

	public void setPortSname(String portSname) {
		this.portSname = portSname;
	}

	public Integer getProid() {
		return this.proid;
	}

	public void setProid(Integer proid) {
		this.proid = proid;
	}

	public Integer getCityid() {
		return this.cityid;
	}

	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}

}