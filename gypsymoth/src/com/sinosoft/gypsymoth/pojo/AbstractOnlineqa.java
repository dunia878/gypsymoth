package com.sinosoft.gypsymoth.pojo;

import java.util.Date;

/**
 * AbstractOnlineqa entity provides the base persistence definition of the
 * Onlineqa entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractOnlineqa implements java.io.Serializable {

	// Fields

	private Long id;
	private String clientname;
	private Long accountid;
	private String title;
	private String content;
	private String remark;
	private Date time;
	private Date firsttime;
	private Date lasttime;
	private String state;
	private String namefor;
	private String answer;

	// Constructors

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/** default constructor */
	public AbstractOnlineqa() {
	}

	/** full constructor */
	public AbstractOnlineqa(String clientname, Long accountid, String title,
			String content, String remark, Date time, Date firsttime,
			Date lasttime, String state, String namefor) {
		this.clientname = clientname;
		this.accountid = accountid;
		this.title = title;
		this.content = content;
		this.remark = remark;
		this.time = time;
		this.firsttime = firsttime;
		this.lasttime = lasttime;
		this.state = state;
		this.namefor = namefor;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClientname() {
		return this.clientname;
	}

	public void setClientname(String clientname) {
		this.clientname = clientname;
	}

	public Long getAccountid() {
		return this.accountid;
	}

	public void setAccountid(Long accountid) {
		this.accountid = accountid;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Date getFirsttime() {
		return this.firsttime;
	}

	public void setFirsttime(Date firsttime) {
		this.firsttime = firsttime;
	}

	public Date getLasttime() {
		return this.lasttime;
	}

	public void setLasttime(Date lasttime) {
		this.lasttime = lasttime;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getNamefor() {
		return this.namefor;
	}

	public void setNamefor(String namefor) {
		this.namefor = namefor;
	}

}