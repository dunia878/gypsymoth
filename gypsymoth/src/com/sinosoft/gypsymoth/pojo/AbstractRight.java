package com.sinosoft.gypsymoth.pojo;
// default package

import java.util.Date;


/**
 * AbstractRight entity provides the base persistence definition of the Right entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractRight  implements java.io.Serializable {

    // Fields    

     private String rightId;
     private String rightName;
     private String parentid;
     private Integer rightStatus;
     private Integer ischild;
     private String resourceUrl;
     private String des;
     private Date modifyDate;
     private String modifyBy;
     private Date createDate;
     private String createBy;
     private Integer orderBy;


    // Constructors

    /** default constructor */
    public AbstractRight() {
    }

	/** minimal constructor */
    public AbstractRight(String rightName, String parentid, Integer rightStatus, Integer ischild, String resourceUrl) {
        this.rightName = rightName;
        this.parentid = parentid;
        this.rightStatus = rightStatus;
        this.ischild = ischild;
        this.resourceUrl = resourceUrl;
    }
    
    /** full constructor */
    public AbstractRight(String rightName, String parentid, Integer rightStatus, Integer ischild, String resourceUrl, String des, Date modifyDate, String modifyBy, Date createDate, String createBy, Integer orderBy) {
        this.rightName = rightName;
        this.parentid = parentid;
        this.rightStatus = rightStatus;
        this.ischild = ischild;
        this.resourceUrl = resourceUrl;
        this.des = des;
        this.modifyDate = modifyDate;
        this.modifyBy = modifyBy;
        this.createDate = createDate;
        this.createBy = createBy;
        this.orderBy = orderBy;
    }

   
    // Property accessors

    public String getRightId() {
        return this.rightId;
    }
    
    public void setRightId(String rightId) {
        this.rightId = rightId;
    }

    public String getRightName() {
        return this.rightName;
    }
    
    public void setRightName(String rightName) {
        this.rightName = rightName;
    }

    public String getParentid() {
        return this.parentid;
    }
    
    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public Integer getRightStatus() {
        return this.rightStatus;
    }
    
    public void setRightStatus(Integer rightStatus) {
        this.rightStatus = rightStatus;
    }

    public Integer getIschild() {
        return this.ischild;
    }
    
    public void setIschild(Integer ischild) {
        this.ischild = ischild;
    }

    public String getResourceUrl() {
        return this.resourceUrl;
    }
    
    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public String getDes() {
        return this.des;
    }
    
    public void setDes(String des) {
        this.des = des;
    }

    public Date getModifyDate() {
        return this.modifyDate;
    }
    
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getModifyBy() {
        return this.modifyBy;
    }
    
    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return this.createBy;
    }
    
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}
   
    








}