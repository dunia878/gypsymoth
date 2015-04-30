package com.sinosoft.gypsymoth.pojo;

/**
 * AbstractClient entity provides the base persistence definition of the Client
 * entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractClient implements java.io.Serializable {

	// Fields

	private Long clientId;
	private String accountId;
	private String coCnName;
	private String coEnName;
	private String businessLicense;
	private String coProperty;
	private String legalPerson;
	private String tel;
	private String email;
	private String address;
	private Long post;
	private String registerEmail;
	private String onePersonName;
	private String onePersonTel;
	private Long onePersonPhone;
	private String onePersonFax;
	private String onePersonEmail;
	private String twoPersonName;
	private String twoPersonTel;
	private Long twoPersonPhone;
	private String twoPersonFax;
	private String twoPersonEmail;
	private String des;
	private Integer nationId;
	private Integer proid;
	private Integer cityid;
	private Long examinnum;
	private String parentId;
	private String identify;
	private String bankaccount;
	private String vbrk;
	private String clientName;
	private String bank;
	private String branch;
	private String subBranch;
	private String accountName;

	// Constructors

	/** default constructor */
	public AbstractClient() {
	}

	/** minimal constructor */
	public AbstractClient(String accountId) {
		this.accountId = accountId;
	}

	/** full constructor */
	public AbstractClient(String accountId, String coCnName, String coEnName,
			String businessLicense, String coProperty, String legalPerson,
			String tel, String email, String address, Long post,
			String registerEmail, String onePersonName, String onePersonTel,
			Long onePersonPhone, String onePersonFax, String onePersonEmail,
			String twoPersonName, String twoPersonTel, Long twoPersonPhone,
			String twoPersonFax, String twoPersonEmail, String des,
			Integer nationId, Integer proid, Integer cityid, Long examinnum,
			String parentId, String identify, String bankaccount, String vbrk, String clientName,
			String bank, String branch, String subBranch, String accountName) {
		this.accountId = accountId;
		this.coCnName = coCnName;
		this.coEnName = coEnName;
		this.businessLicense = businessLicense;
		this.coProperty = coProperty;
		this.legalPerson = legalPerson;
		this.tel = tel;
		this.email = email;
		this.address = address;
		this.post = post;
		this.registerEmail = registerEmail;
		this.onePersonName = onePersonName;
		this.onePersonTel = onePersonTel;
		this.onePersonPhone = onePersonPhone;
		this.onePersonFax = onePersonFax;
		this.onePersonEmail = onePersonEmail;
		this.twoPersonName = twoPersonName;
		this.twoPersonTel = twoPersonTel;
		this.twoPersonPhone = twoPersonPhone;
		this.twoPersonFax = twoPersonFax;
		this.twoPersonEmail = twoPersonEmail;
		this.des = des;
		this.nationId = nationId;
		this.proid = proid;
		this.cityid = cityid;
		this.examinnum = examinnum;
		this.parentId = parentId;
		this.identify = identify;
		this.bankaccount = bankaccount;
		this.vbrk = vbrk;
		this.clientName=clientName;
		this.bank = bank;
		this.branch = branch;
		this.subBranch = subBranch;
		this.accountName = accountName;
	}

	// Property accessors

	public Long getClientId() {
		return this.clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getCoCnName() {
		return this.coCnName;
	}

	public void setCoCnName(String coCnName) {
		this.coCnName = coCnName;
	}

	public String getCoEnName() {
		return this.coEnName;
	}

	public void setCoEnName(String coEnName) {
		this.coEnName = coEnName;
	}

	public String getBusinessLicense() {
		return this.businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}

	public String getCoProperty() {
		return this.coProperty;
	}

	public void setCoProperty(String coProperty) {
		this.coProperty = coProperty;
	}

	public String getLegalPerson() {
		return this.legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getPost() {
		return this.post;
	}

	public void setPost(Long post) {
		this.post = post;
	}

	public String getRegisterEmail() {
		return this.registerEmail;
	}

	public void setRegisterEmail(String registerEmail) {
		this.registerEmail = registerEmail;
	}

	public String getOnePersonName() {
		return this.onePersonName;
	}

	public void setOnePersonName(String onePersonName) {
		this.onePersonName = onePersonName;
	}

	public String getOnePersonTel() {
		return this.onePersonTel;
	}

	public void setOnePersonTel(String onePersonTel) {
		this.onePersonTel = onePersonTel;
	}

	public Long getOnePersonPhone() {
		return this.onePersonPhone;
	}

	public void setOnePersonPhone(Long onePersonPhone) {
		this.onePersonPhone = onePersonPhone;
	}

	public String getOnePersonFax() {
		return this.onePersonFax;
	}

	public void setOnePersonFax(String onePersonFax) {
		this.onePersonFax = onePersonFax;
	}

	public String getOnePersonEmail() {
		return this.onePersonEmail;
	}

	public void setOnePersonEmail(String onePersonEmail) {
		this.onePersonEmail = onePersonEmail;
	}

	public String getTwoPersonName() {
		return this.twoPersonName;
	}

	public void setTwoPersonName(String twoPersonName) {
		this.twoPersonName = twoPersonName;
	}

	public String getTwoPersonTel() {
		return this.twoPersonTel;
	}

	public void setTwoPersonTel(String twoPersonTel) {
		this.twoPersonTel = twoPersonTel;
	}

	public Long getTwoPersonPhone() {
		return this.twoPersonPhone;
	}

	public void setTwoPersonPhone(Long twoPersonPhone) {
		this.twoPersonPhone = twoPersonPhone;
	}

	public String getTwoPersonFax() {
		return this.twoPersonFax;
	}

	public void setTwoPersonFax(String twoPersonFax) {
		this.twoPersonFax = twoPersonFax;
	}

	public String getTwoPersonEmail() {
		return this.twoPersonEmail;
	}

	public void setTwoPersonEmail(String twoPersonEmail) {
		this.twoPersonEmail = twoPersonEmail;
	}

	public String getDes() {
		return this.des;
	}

	public void setDes(String des) {
		this.des = des;
	}


	public Long getExaminnum() {
		return this.examinnum;
	}

	public void setExaminnum(Long examinnum) {
		this.examinnum = examinnum;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getIdentify() {
		return this.identify;
	}

	public void setIdentify(String identify) {
		this.identify = identify;
	}

	public Integer getNationId() {
		return nationId;
	}

	public void setNationId(Integer nationId) {
		this.nationId = nationId;
	}

	public Integer getProid() {
		return proid;
	}

	public void setProid(Integer proid) {
		this.proid = proid;
	}

	public Integer getCityid() {
		return cityid;
	}

	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}

	public String getBankaccount() {
		return bankaccount;
	}

	public void setBankaccount(String bankaccount) {
		this.bankaccount = bankaccount;
	}

	public String getVbrk() {
		return vbrk;
	}

	public void setVbrk(String vbrk) {
		this.vbrk = vbrk;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String client_name) {
		this.clientName = client_name;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getSubBranch() {
		return subBranch;
	}

	public void setSubBranch(String subBranch) {
		this.subBranch = subBranch;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	

}