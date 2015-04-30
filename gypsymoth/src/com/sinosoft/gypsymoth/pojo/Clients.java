package com.sinosoft.gypsymoth.pojo;

/**
 * Clients entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Clients extends AbstractClients implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Clients() {
	}

	/** minimal constructor */
	public Clients(Long clientId, String accountId) {
		super(clientId, accountId);
	}

	/** full constructor */
	public Clients(Long clientId, String accountId, String coCnName,
			String coEnName, String businessLicense, String coProperty,
			String legalPerson, String tel, String email, String address,
			Long post, String registerEmail, String onePersonName,
			String onePersonTel, Long onePersonPhone, String onePersonFax,
			String onePersonEmail, String twoPersonName, String twoPersonTel,
			Long twoPersonPhone, String twoPersonFax, String twoPersonEmail,
			String des, Integer nationId, Integer proid, Integer cityid, Long examinnum) {
		super(clientId, accountId, coCnName, coEnName, businessLicense,
				coProperty, legalPerson, tel, email, address, post,
				registerEmail, onePersonName, onePersonTel, onePersonPhone,
				onePersonFax, onePersonEmail, twoPersonName, twoPersonTel,
				twoPersonPhone, twoPersonFax, twoPersonEmail, des, nationId,
				proid, cityid, examinnum);
	}

}
