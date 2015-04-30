package com.sinosoft.gypsymoth.pojo;
// default package



/**
 * AbstractAccountRole entity provides the base persistence definition of the AccountRole entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractAccountRole  implements java.io.Serializable {


    // Fields    

     private String id;
     private String accountId;
     private String roleId;


    // Constructors

    /** default constructor */
    public AbstractAccountRole() {
    }

    
    /** full constructor */
    public AbstractAccountRole(String accountId, String roleId) {
        this.accountId = accountId;
        this.roleId = roleId;
    }

   
    // Property accessors

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getAccountId() {
        return this.accountId;
    }
    
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
   








}