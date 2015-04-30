package com.sinosoft.gypsymoth.pojo;
// default package

import java.util.Date;


/**
 * AbstractAccount entity provides the base persistence definition of the Account entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractAccount  implements java.io.Serializable {


    // Fields    

     private String accountId;
     private String accountName;
     private Integer accountType;
     private Integer accountStatus;
     private String password;
     private String createBy;
     private Date createDate;
     private Date lockDate;
     private Date lastLoginFail;
     private Integer loginFailTimes;
     private Date lastLoginDate;
     private Integer loginLock;


    // Constructors

    /** default constructor */
    public AbstractAccount() {
    }

	/** minimal constructor */
    public AbstractAccount(String accountName, Integer accountType, Integer accountStatus, String password, String createBy, Date createDate) {
        this.accountName = accountName;
        this.accountType = accountType;
        this.accountStatus = accountStatus;
        this.password = password;
        this.createBy = createBy;
        this.createDate = createDate;
    }
    
    /** full constructor */
    public AbstractAccount(String accountName, Integer accountType, Integer accountStatus, String password, String createBy, Date createDate, Date lockDate, Date lastLoginFail, Integer loginFailTimes, Date lastLoginDate, Integer loginLock) {
        this.accountName = accountName;
        this.accountType = accountType;
        this.accountStatus = accountStatus;
        this.password = password;
        this.createBy = createBy;
        this.createDate = createDate;
        this.lockDate = lockDate;
        this.lastLoginFail = lastLoginFail;
        this.loginFailTimes = loginFailTimes;
        this.lastLoginDate = lastLoginDate;
        this.loginLock = loginLock;
    }

   
    // Property accessors

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

    public Integer getAccountStatus() {
        return this.accountStatus;
    }
    
    public void setAccountStatus(Integer accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreateBy() {
        return this.createBy;
    }
    
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLockDate() {
        return this.lockDate;
    }
    
    public void setLockDate(Date lockDate) {
        this.lockDate = lockDate;
    }

    public Date getLastLoginFail() {
        return this.lastLoginFail;
    }
    
    public void setLastLoginFail(Date lastLoginFail) {
        this.lastLoginFail = lastLoginFail;
    }

    public Integer getLoginFailTimes() {
        return this.loginFailTimes;
    }
    
    public void setLoginFailTimes(Integer loginFailTimes) {
        this.loginFailTimes = loginFailTimes;
    }

    public Date getLastLoginDate() {
        return this.lastLoginDate;
    }
    
    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Integer getLoginLock() {
        return this.loginLock;
    }
    
    public void setLoginLock(Integer loginLock) {
        this.loginLock = loginLock;
    }
   








}