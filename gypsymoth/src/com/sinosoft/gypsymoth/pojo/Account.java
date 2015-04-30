package com.sinosoft.gypsymoth.pojo;
// default package

import java.util.Date;


/**
 * Account entity. @author MyEclipse Persistence Tools
 */
public class Account extends AbstractAccount implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public Account() {
    }

	/** minimal constructor */
    public Account(String accountName, Integer accountType, Integer accountStatus, String password, String createBy, Date createDate) {
        super(accountName, accountType, accountStatus, password, createBy, createDate);        
    }
    
    /** full constructor */
    public Account(String accountName, Integer accountType, Integer accountStatus, String password, String createBy, Date createDate, Date lockDate, Date lastLoginFail, Integer loginFailTimes, Date lastLoginDate, Integer loginLock) {
        super(accountName, accountType, accountStatus, password, createBy, createDate, lockDate, lastLoginFail, loginFailTimes, lastLoginDate, loginLock);        
    }
   
}
