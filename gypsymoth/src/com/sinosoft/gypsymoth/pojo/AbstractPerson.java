package com.sinosoft.gypsymoth.pojo;

import java.util.Date;

/**
 * AbstractPerson generated by MyEclipse Persistence Tools
 */

public abstract class AbstractPerson implements java.io.Serializable {

	// Fields

	private Integer id;
	private String personId;
	private String accountId;
	private String name;
	private String sex;
	private Date birthday;
	private String education;
	private String position;
	private Integer training;
	private Date passTime;
	private String tel;
	private String mobile;
	private String fax;
	private String email;
	private Integer isCoordinator;
	private Integer isAuthorized;
	private Integer isInspector;
	private String attachment;
	private Date insTime;
	private Date upTime;
	private Date delTime;
	private String comments;
	private String personType;
	private String orgId;
	private Integer personStatus;
	private String  spell;
	

	// Constructors

	/** default constructor */
	public AbstractPerson() {
	}
//
//	/** minimal constructor */
//	public AbstractPerson(String personId, String accountId, String name,
//			String sex, Date birthday, String education, String position,
//			Integer training, Date passTime, String tel, String mobile,
//			String fax, String email, Date insTime, String personType,
//			String orgId) {
//		this.personId = personId;
//		this.accountId = accountId;
//		this.name = name;
//		this.sex = sex;
//		this.birthday = birthday;
//		this.education = education;
//		this.position = position;
//		this.training = training;
//		this.passTime = passTime;
//		this.tel = tel;
//		this.mobile = mobile;
//		this.fax = fax;
//		this.email = email;
//		this.insTime = insTime;
//		this.personType = personType;
//		this.orgId = orgId;
//	}

//	/** full constructor */
//	public AbstractPerson(String personId, String accountId, String name,
//			String sex, Date birthday, String education, String position,
//			Integer training, Date passTime, String tel, String mobile,
//			String fax, String email, Integer isCoordinator, Integer isAuthorized,
//			Integer isInspector, String attachment, Date insTime, Date upTime,
//			Date delTime, String comments, String personType, String orgId,
//			Integer personStatus) {
//		this.personId = personId;
//		this.accountId = accountId;
//		this.name = name;
//		this.sex = sex;
//		this.birthday = birthday;
//		this.education = education;
//		this.position = position;
//		this.training = training;
//		this.passTime = passTime;
//		this.tel = tel;
//		this.mobile = mobile;
//		this.fax = fax;
//		this.email = email;
//		this.isCoordinator = isCoordinator;
//		this.isAuthorized = isAuthorized;
//		this.isInspector = isInspector;
//		this.attachment = attachment;
//		this.insTime = insTime;
//		this.upTime = upTime;
//		this.delTime = delTime;
//		this.comments = comments;
//		this.personType = personType;
//		this.orgId = orgId;
//		this.personStatus = personStatus;
//	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPersonId() {
		return this.personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEducation() {
		return this.education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Integer getTraining() {
		return this.training;
	}

	public void setTraining(Integer training) {
		this.training = training;
	}

	public Date getPassTime() {
		return this.passTime;
	}

	public void setPassTime(Date passTime) {
		this.passTime = passTime;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getIsCoordinator() {
		return this.isCoordinator;
	}

	public void setIsCoordinator(Integer isCoordinator) {
		this.isCoordinator = isCoordinator;
	}

	public Integer getIsAuthorized() {
		return this.isAuthorized;
	}

	public void setIsAuthorized(Integer isAuthorized) {
		this.isAuthorized = isAuthorized;
	}

	public Integer getIsInspector() {
		return this.isInspector;
	}

	public void setIsInspector(Integer isInspector) {
		this.isInspector = isInspector;
	}

	public String getAttachment() {
		return this.attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public Date getInsTime() {
		return this.insTime;
	}

	public void setInsTime(Date insTime) {
		this.insTime = insTime;
	}

	public Date getUpTime() {
		return this.upTime;
	}

	public void setUpTime(Date upTime) {
		this.upTime = upTime;
	}

	public Date getDelTime() {
		return this.delTime;
	}

	public void setDelTime(Date delTime) {
		this.delTime = delTime;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getPersonType() {
		return this.personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}

	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public Integer getPersonStatus() {
		return this.personStatus;
	}

	public void setPersonStatus(Integer personStatus) {
		this.personStatus = personStatus;
	}

	public String getSpell() {
		return spell;
	}

	public void setSpell(String spell) {
		this.spell = spell;
	}

	
	
	
}