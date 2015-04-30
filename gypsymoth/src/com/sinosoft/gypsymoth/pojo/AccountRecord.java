package com.sinosoft.gypsymoth.pojo;
// default package

import java.util.Date;


/**
 * AccountRecord entity. @author MyEclipse Persistence Tools
 */
public class AccountRecord extends AbstractAccountRecord implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public AccountRecord() {
    }

    
    /** full constructor */
    public AccountRecord(String accountId, String accountName, Integer accountType, String password, String modifyBy, Date modifyDate,Integer accountStatus,Integer modifyType) {
        super(accountId, accountName, accountType, password, modifyBy, modifyDate,accountStatus,modifyType);        
    }
   
}
