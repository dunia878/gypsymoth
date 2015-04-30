package com.sinosoft.gypsymoth.pojo;

/**
 * AbstractConfig entity provides the base persistence definition of the Config
 * entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractConfig implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private String value;

	// Constructors

	/** default constructor */
	public AbstractConfig() {
	}

	/** minimal constructor */
	public AbstractConfig(Long id) {
		this.id = id;
	}

	/** full constructor */
	public AbstractConfig(Long id, String name, String value) {
		this.id = id;
		this.name = name;
		this.value = value;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}