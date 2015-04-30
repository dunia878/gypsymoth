package com.sinosoft.gypsymoth.pojo;

import java.util.Date;

/**
 * AbstractInformation entity provides the base persistence definition of the
 * Information entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractInformation implements java.io.Serializable {

	// Fields

	private Long informationid;
	private String informationname;
	private Long accountid;
	private String accountname;
	private String filename;
	private String filediscripe;
	private String fileurl;
	private Date fileuptime;
	private Long filetip;
	private Long informationstate;
	private Long informationtip;
	private Long informationtips;
	
	
	

	// Constructors




	public Long getInformationtips() {
		return informationtips;
	}



	public void setInformationtips(Long informationtips) {
		this.informationtips = informationtips;
	}



	/** default constructor */
	public AbstractInformation() {
	}

	

	/** full constructor */
	public AbstractInformation(Long informationid,String informationname,
			Long accountid, String accountname, String filename,
			String filediscripe, String fileurl, Date fileuptime, Long filetip,
			Long informationstate,Long informationtip ,Long informationtips) {
		this.informationid = informationid;
		this.informationname = informationname;
		this.accountid = accountid;
		this.accountname = accountname;
		this.filename = filename;
		this.filediscripe = filediscripe;
		this.fileurl = fileurl;
		this.fileuptime = fileuptime;
		this.filetip = filetip;
		this.informationstate = informationstate;
		this.informationtip = informationtip;
		this.informationtips = informationtips;
	}

	// Property accessors

	public Long getInformationid() {
		return this.informationid;
	}

	public void setInformationid(Long informationid) {
		this.informationid = informationid;
	}

	public String getInformationname() {
		return this.informationname;
	}

	public void setInformationname(String informationname) {
		this.informationname = informationname;
	}

	public Long getAccountid() {
		return this.accountid;
	}

	public void setAccountid(Long accountid) {
		this.accountid = accountid;
	}

	public String getAccountname() {
		return this.accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilediscripe() {
		return this.filediscripe;
	}

	public void setFilediscripe(String filediscripe) {
		this.filediscripe = filediscripe;
	}

	public String getFileurl() {
		return this.fileurl;
	}

	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
	}

	public Date getFileuptime() {
		return this.fileuptime;
	}

	public void setFileuptime(Date fileuptime) {
		this.fileuptime = fileuptime;
	}

	public Long getFiletip() {
		return this.filetip;
	}

	public void setFiletip(Long filetip) {
		this.filetip = filetip;
	}

	public Long getInformationstate() {
		return this.informationstate;
	}

	public void setInformationstate(Long informationstate) {
		this.informationstate = informationstate;
	}

	public Long getInformationtip() {
		return this.informationtip;
	}

	public void setInformationtip(Long informationtip) {
		this.informationtip = informationtip;
	}

	
}