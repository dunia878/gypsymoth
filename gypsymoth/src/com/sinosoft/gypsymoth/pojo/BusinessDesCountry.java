package com.sinosoft.gypsymoth.pojo;

/**
 * BusinessDesCountry entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class BusinessDesCountry extends AbstractBusinessDesCountry implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public BusinessDesCountry() {
	}

	/** full constructor */
	public BusinessDesCountry(Long businessid, String port, String country,Integer desindex,String selectindex) {
		super(businessid, port, country,desindex,selectindex);
	}

}
