package com.sinosoft.gypsymoth.pojo;
// default package

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * AbstractRole entity provides the base persistence definition of the Role entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractRole  implements java.io.Serializable {


    // Fields    

     private String roleId;
     private String roleName;
     private String createBy;
     private Date createDate;
     private String modifyBy;
     private Date modifyTime;
     private Integer roleStatus;
     private String des;


    // Constructors

    /** default constructor */
    public AbstractRole() {
    }

	/** minimal constructor */
    public AbstractRole(String roleName, Integer roleStatus) {
        this.roleName = roleName;
        this.roleStatus = roleStatus;
    }
    
    /** full constructor */
    public AbstractRole(String roleName, String createBy, Date createDate, String modifyBy, Date modifyTime, Integer roleStatus, String des) {
        this.roleName = roleName;
        this.createBy = createBy;
        this.createDate = createDate;
        this.modifyBy = modifyBy;
        this.modifyTime = modifyTime;
        this.roleStatus = roleStatus;
        this.des = des;
    }

   
    // Property accessors

    public String getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return this.roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
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
    	if(createDate!=null){
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	try {
				createDate = sdf.parse(sdf.format(createDate));
			} catch (ParseException e) {
				e.printStackTrace();
			}
    	}
		this.createDate = createDate;
    }

    public String getModifyBy() {
        return this.modifyBy;
    }
    
    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyTime() {
        return this.modifyTime;
    }
    
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getRoleStatus() {
        return this.roleStatus;
    }
    
    public void setRoleStatus(Integer roleStatus) {
        this.roleStatus = roleStatus;
    }

    public String getDes() {
        return this.des;
    }
    
    public void setDes(String des) {
        this.des = des;
    }
   








}