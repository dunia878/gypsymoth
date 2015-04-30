package com.sinosoft.gypsymoth.pojo;

// default package

import java.util.Date;

/**
 * Right entity. @author MyEclipse Persistence Tools
 */
public class Right extends AbstractRight implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public Right() {
    }

	/** minimal constructor */
    public Right(String rightName, String parentid, Integer rightStatus, Integer ischild, String resourceUrl) {
        super(rightName, parentid, rightStatus, ischild, resourceUrl);        
    }
    
    /** full constructor */
    public Right(String rightName, String parentid, Integer rightStatus, Integer ischild, String resourceUrl, String des, Date modifyDate, String modifyBy, Date createDate, String createBy, Integer orderBy) {
        super(rightName, parentid, rightStatus, ischild, resourceUrl, des, modifyDate, modifyBy, createDate, createBy, orderBy);        
    }
    
    private String roleId;
   

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Right(String roleId){
    	this.roleId = roleId;
    }
   
}
