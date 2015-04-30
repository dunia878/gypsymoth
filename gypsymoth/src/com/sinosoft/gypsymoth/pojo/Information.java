package com.sinosoft.gypsymoth.pojo;

import java.util.Date;

/**
 * Information entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Information extends AbstractInformation implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Information() {
	}


	/** full constructor */
	public Information(Long informationid, String informationname,
			Long accountid, String accountname, String filename,
			String filediscripe, String fileurl, Date fileuptime, Long filetip,
			Long informationstate, Long informationtip,Long informationtips) {
		super(informationid, informationname, accountid, accountname, filename,
				filediscripe, fileurl, fileuptime, filetip, informationstate,
				informationtip,informationtips);
	}

}
