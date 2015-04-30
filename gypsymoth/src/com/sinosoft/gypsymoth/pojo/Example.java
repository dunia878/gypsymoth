package com.sinosoft.gypsymoth.pojo;
// default package

import java.math.BigDecimal;


/**
 * Example entity. @author MyEclipse Persistence Tools
 */
public class Example extends AbstractExample implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public Example() {
    }

    
    /** full constructor */
    public Example(String name, Integer age, String password) {
        super(name, age, password);        
    }
    
    public Example(String name, Integer age, String password,String accountName) {
        super(name, age, password);        
        this.accountName=accountName;
    }
    
    private String accountName;

	public String getAccountName() {
		return accountName;
	}


	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
   
}
