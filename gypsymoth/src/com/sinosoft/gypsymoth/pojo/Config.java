package com.sinosoft.gypsymoth.pojo;

/**
 * Config entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Config extends AbstractConfig implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Config() {
	}

	/** minimal constructor */
	public Config(Long id) {
		super(id);
	}

	/** full constructor */
	public Config(Long id, String name, String value) {
		super(id, name, value);
	}

}
