package com.sinosoft.gypsymoth.pojo;

import java.util.Date;

/**
 * Ingormationlog entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Ingormationlog extends AbstractIngormationlog implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Ingormationlog() {
	}

	/** full constructor */
	public Ingormationlog(Long informationlogid, Long informationid,
			String informationname, Long accountid, String accountname,
			String filename, String filediscripe, String fileurl,
			Date fileuptime, Long filetip, Long informationtip,
			String informationlogname, Date informationlogtime,
			Long informationlogstate) {
		super(informationlogid, informationid, informationname, accountid,
				accountname, filename, filediscripe, fileurl, fileuptime,
				filetip, informationtip, informationlogname,
				informationlogtime, informationlogstate);
	}

}
