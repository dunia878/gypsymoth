package com.sinosoft.gypsymoth.pojo;
// default package



/**
 * AbstractRoleRight entity provides the base persistence definition of the RoleRight entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractRoleRight  implements java.io.Serializable {


    // Fields    

     private String id;
     private String roleId;
     private String rightId;


    // Constructors

    /** default constructor */
    public AbstractRoleRight() {
    }

    
    /** full constructor */
    public AbstractRoleRight(String roleId, String rightId) {
        this.roleId = roleId;
        this.rightId = rightId;
    }

   
    // Property accessors

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRightId() {
        return this.rightId;
    }
    
    public void setRightId(String rightId) {
        this.rightId = rightId;
    }
   








}