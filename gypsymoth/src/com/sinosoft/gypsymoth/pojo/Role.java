package com.sinosoft.gypsymoth.pojo;

// default package

import java.util.Date;


/**
 * Role entity. @author MyEclipse Persistence Tools
 */
public class Role extends AbstractRole implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public Role() {
    }

	/** minimal constructor */
    public Role(String roleName, Integer roleStatus) {
        super(roleName, roleStatus);        
    }
    
    /** full constructor */
    public Role(String roleName, String createBy, Date createDate, String modifyBy, Date modifyTime, Integer roleStatus, String des) {
        super(roleName, createBy, createDate, modifyBy, modifyTime, roleStatus, des);        
    }
   
}
