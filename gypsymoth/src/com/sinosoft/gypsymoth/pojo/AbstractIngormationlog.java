package com.sinosoft.gypsymoth.pojo;

import java.util.Date;

/**
 * AbstractIngormationlog entity provides the base persistence definition of
 * the Ingormationlog entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractIngormationlog implements java.io.Serializable {

	// Fields

	private Long informationlogid;
	private Long informationid;
	private String informationname;
	private Long accountid;
	private String accountname;
	private String filename;
	private String filediscripe;
	private String fileurl;
	private Date fileuptime;
	private Long filetip;
	private Long informationtip;
	private String informationlogname;
	private Date informationlogtime;
	private Long informationlogstate;

	// Constructors

	/** default constructor */
	public AbstractIngormationlog() {
	}


	/** full constructor */
	public AbstractIngormationlog(Long informationlogid, Long informationid,
			String informationname, Long accountid, String accountname,
			String filename, String filediscripe, String fileurl,
			Date fileuptime, Long filetip, Long informationtip,
			String informationlogname, Date informationlogtime,
			Long informationlogstate) {
		this.informationlogid = informationlogid;
		this.informationid = informationid;
		this.informationname = informationname;
		this.accountid = accountid;
		this.accountname = accountname;
		this.filename = filename;
		this.filediscripe = filediscripe;
		this.fileurl = fileurl;
		this.fileuptime = fileuptime;
		this.filetip = filetip;
		this.informationtip = informationtip;
		this.informationlogname = informationlogname;
		this.informationlogtime = informationlogtime;
		this.informationlogstate = informationlogstate;
	}

	// Property accessors

	public Long getInformationlogid() {
		return this.informationlogid;
	}

	public void setInformationlogid(Long informationlogid) {
		this.informationlogid = informationlogid;
	}

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

	public Long getInformationtip() {
		return this.informationtip;
	}

	public void setInformationtip(Long informationtip) {
		this.informationtip = informationtip;
	}

	public String getInformationlogname() {
		return this.informationlogname;
	}

	public void setInformationlogname(String informationlogname) {
		this.informationlogname = informationlogname;
	}

	public Date getInformationlogtime() {
		return this.informationlogtime;
	}

	public void setInformationlogtime(Date informationlogtime) {
		this.informationlogtime = informationlogtime;
	}

	public Long getInformationlogstate() {
		return this.informationlogstate;
	}

	public void setInformationlogstate(Long informationlogstate) {
		this.informationlogstate = informationlogstate;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractIngormationlog))
			return false;
		AbstractIngormationlog castOther = (AbstractIngormationlog) other;

		return ((this.getInformationlogid() == castOther.getInformationlogid()) || (this
				.getInformationlogid() != null
				&& castOther.getInformationlogid() != null && this
				.getInformationlogid().equals(castOther.getInformationlogid())))
				&& ((this.getInformationid() == castOther.getInformationid()) || (this
						.getInformationid() != null
						&& castOther.getInformationid() != null && this
						.getInformationid()
						.equals(castOther.getInformationid())))
				&& ((this.getInformationname() == castOther
						.getInformationname()) || (this.getInformationname() != null
						&& castOther.getInformationname() != null && this
						.getInformationname().equals(
								castOther.getInformationname())))
				&& ((this.getAccountid() == castOther.getAccountid()) || (this
						.getAccountid() != null
						&& castOther.getAccountid() != null && this
						.getAccountid().equals(castOther.getAccountid())))
				&& ((this.getAccountname() == castOther.getAccountname()) || (this
						.getAccountname() != null
						&& castOther.getAccountname() != null && this
						.getAccountname().equals(castOther.getAccountname())))
				&& ((this.getFilename() == castOther.getFilename()) || (this
						.getFilename() != null
						&& castOther.getFilename() != null && this
						.getFilename().equals(castOther.getFilename())))
				&& ((this.getFilediscripe() == castOther.getFilediscripe()) || (this
						.getFilediscripe() != null
						&& castOther.getFilediscripe() != null && this
						.getFilediscripe().equals(castOther.getFilediscripe())))
				&& ((this.getFileurl() == castOther.getFileurl()) || (this
						.getFileurl() != null
						&& castOther.getFileurl() != null && this.getFileurl()
						.equals(castOther.getFileurl())))
				&& ((this.getFileuptime() == castOther.getFileuptime()) || (this
						.getFileuptime() != null
						&& castOther.getFileuptime() != null && this
						.getFileuptime().equals(castOther.getFileuptime())))
				&& ((this.getFiletip() == castOther.getFiletip()) || (this
						.getFiletip() != null
						&& castOther.getFiletip() != null && this.getFiletip()
						.equals(castOther.getFiletip())))
				&& ((this.getInformationtip() == castOther.getInformationtip()) || (this
						.getInformationtip() != null
						&& castOther.getInformationtip() != null && this
						.getInformationtip().equals(
								castOther.getInformationtip())))
				&& ((this.getInformationlogname() == castOther
						.getInformationlogname()) || (this
						.getInformationlogname() != null
						&& castOther.getInformationlogname() != null && this
						.getInformationlogname().equals(
								castOther.getInformationlogname())))
				&& ((this.getInformationlogtime() == castOther
						.getInformationlogtime()) || (this
						.getInformationlogtime() != null
						&& castOther.getInformationlogtime() != null && this
						.getInformationlogtime().equals(
								castOther.getInformationlogtime())))
				&& ((this.getInformationlogstate() == castOther
						.getInformationlogstate()) || (this
						.getInformationlogstate() != null
						&& castOther.getInformationlogstate() != null && this
						.getInformationlogstate().equals(
								castOther.getInformationlogstate())));
	}

	

}