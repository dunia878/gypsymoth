package com.sinosoft.gypsymoth.pojo;
// default package



/**
 * AbstractAbc entity provides the base persistence definition of the Abc entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractAbc  implements java.io.Serializable {


    // Fields    

     private String id;
     private String accountName;


    // Constructors

    /** default constructor */
    public AbstractAbc() {
    }

    
    /** full constructor */
    public AbstractAbc(String accountName) {
        this.accountName = accountName;
    }

   
    // Property accessors

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getAccountName() {
        return this.accountName;
    }
    
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
   








}