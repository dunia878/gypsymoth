package com.sinosoft.gypsymoth.pojo;

import java.util.Date;

/**
 * Onlineqa entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Onlineqa extends AbstractOnlineqa implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Onlineqa() {
	}

	/** full constructor */
	public Onlineqa(String clientname, Long accountid, String title,
			String content, String remark, Date time, Date firsttime,
			Date lasttime, String state, String namefor) {
		super(clientname, accountid, title, content, remark, time, firsttime,
				lasttime, state, namefor);
	}

}
