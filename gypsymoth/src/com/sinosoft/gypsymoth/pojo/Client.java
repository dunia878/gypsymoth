package com.sinosoft.gypsymoth.pojo;

/**
 * Client entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Client extends AbstractClient implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Client() {
	}

	/** minimal constructor */
	public Client(String accountId) {
		super(accountId);
	}

	/** full constructor */
	public Client(String accountId, String coCnName, String coEnName,
			String businessLicense, String coProperty, String legalPerson,
			String tel, String email, String address, Long post,
			String registerEmail, String onePersonName, String onePersonTel,
			Long onePersonPhone, String onePersonFax, String onePersonEmail,
			String twoPersonName, String twoPersonTel, Long twoPersonPhone,
			String twoPersonFax, String twoPersonEmail, String des,
			Integer nationId, Integer proid, Integer cityid, Long examinnum,
			String parentId, String identify, String bankaccount, String vbrk, String clientName,
			String bank, String branch, String subBranch, String accountName) {
		super(accountId, coCnName, coEnName, businessLicense, coProperty,
				legalPerson, tel, email, address, post, registerEmail,
				onePersonName, onePersonTel, onePersonPhone, onePersonFax,
				onePersonEmail, twoPersonName, twoPersonTel, twoPersonPhone,
				twoPersonFax, twoPersonEmail, des, nationId, proid, cityid,
				examinnum, parentId, identify, bankaccount, vbrk, clientName, bank,branch,subBranch,accountName);
	}

}
