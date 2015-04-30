package com.sinosoft.gypsymoth.pojo;

import java.util.Date;

/**
 * Clientlog entity. @author MyEclipse Persistence Tools
 */
public class Clientlog extends AbstractClientlog implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Clientlog() {
	}

	/** minimal constructor */
	public Clientlog(Long clientId, String accountId, String modifyBy,
			Date modifyDate, Integer nationId) {
		super(clientId, accountId, modifyBy, modifyDate, nationId);
	}

	/** full constructor */
	public Clientlog(Long clientId, String accountId, String coCnName,
			String coEnName, String businessLicense, String coProperty,
			String legalPerson, String tel, String email, String address,
			Long post, String registerEmail, String onePersonName,
			String onePersonTel, Long onePersonPhone, String onePersonFax,
			String onePersonEmail, String twoPersonName, String twoPersonTel,
			Long twoPersonPhone, String twoPersonFax, String twoPersonEmail,
			String des, String modifyBy, Date modifyDate, Integer nationId,
			Integer proid, Integer cityid, Integer modifyType, String parentId,
			String identify, String bankaccount, String vbrk) {
		super(clientId, accountId, coCnName, coEnName, businessLicense,
				coProperty, legalPerson, tel, email, address, post,
				registerEmail, onePersonName, onePersonTel, onePersonPhone,
				onePersonFax, onePersonEmail, twoPersonName, twoPersonTel,
				twoPersonPhone, twoPersonFax, twoPersonEmail, des, modifyBy,
				modifyDate, nationId, proid, cityid, modifyType, parentId,
				identify, bankaccount, vbrk);
	}

}
