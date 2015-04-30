package com.sinosoft.gypsymoth.pojo;
// default package

import java.util.Date;


/**
 * AbstractAccountRecord entity provides the base persistence definition of the AccountRecord entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractAccountRecord  implements java.io.Serializable {


    // Fields    

     private String recordId;
     private String accountId;
     private String accountName;
     private Integer accountType;
     private String password;
     private String modifyBy;
     private Date modifyDate;
     private Integer accountStatus;
     private Integer modifyType;


    // Constructors

    /** default constructor */
    public AbstractAccountRecord() {
    }

    
    /** full constructor */
    public AbstractAccountRecord(String accountId, String accountName, Integer accountType, String password, String modifyBy, Date modifyDate,Integer accountStatus,Integer modifyType) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.accountType = accountType;
        this.password = password;
        this.modifyBy = modifyBy;
        this.modifyDate = modifyDate;
        this.accountStatus = accountStatus;
        this.modifyType = modifyType;
    }

   
    // Property accessors

    public String getRecordId() {
        return this.recordId;
    }
    
    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getAccountId() {
        return this.accountId;
    }
    
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return this.accountName;
    }
    
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Integer getAccountType() {
        return this.accountType;
    }
    
    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getModifyBy() {
        return this.modifyBy;
    }
    
    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyDate() {
        return this.modifyDate;
    }
    
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }


	public Integer getAccountStatus() {
		return accountStatus;
	}


	public void setAccountStatus(Integer accountStatus) {
		this.accountStatus = accountStatus;
	}


	public Integer getModifyType() {
		return modifyType;
	}


	public void setModifyType(Integer modifyType) {
		this.modifyType = modifyType;
	}
   
    








}