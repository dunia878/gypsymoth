package com.sinosoft.gypsymoth.pojo;

import java.util.Date;




/**
 * AbstractBulletin entity provides the base persistence definition of the Bulletin entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractBulletin  implements java.io.Serializable {


    // Fields    

     private Long bulletinId;
     private String bulletinName;
     private String bulletinContent;
     private String accountId;
     private String accountName;
     private Long bulletinState;
     private Long bulletinTip;
     private Date bulletinTime;
     private Long bulletinTips; 
     private Long bulletinLanguage;
     private String bulletinContents;
     private String filename;
     private String fileurl;


    // Constructors

    public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}


	public String getFileurl() {
		return fileurl;
	}


	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
	}


	public String getBulletinContents() {
		return bulletinContents;
	}


	public void setBulletinContents(String bulletinContents) {
		this.bulletinContents = bulletinContents;
	}


	/** default constructor */
    public AbstractBulletin() {
    }

    
    /** full constructor */
    public AbstractBulletin(Long bulletinId, String bulletinName, String bulletinContent,String  bulletinContents, String accountId, String accountName, Long bulletinState,Long bulletinTip,Date bulletinTime,Long bulletinTips,Long bulletinLanguage) {
        this.bulletinId = bulletinId;
        this.bulletinName = bulletinName;
        this.bulletinContent = bulletinContent;
        this.bulletinContents = bulletinContents;
        this.accountId = accountId;
        this.accountName = accountName;
        this.bulletinState = bulletinState;
        this.bulletinTip=bulletinTip;
        this.bulletinTime=bulletinTime;
        this.bulletinTips=bulletinTips;
        this.bulletinLanguage=bulletinLanguage;
    }


    // Property accessors
    
    
    
	public Long getBulletinId() {
		return bulletinId;
	}


	public void setBulletinId(Long bulletinId) {
		this.bulletinId = bulletinId;
	}


	public String getBulletinName() {
		return bulletinName;
	}


	public void setBulletinName(String bulletinName) {
		this.bulletinName = bulletinName;
	}


	public String getBulletinContent() {
		return bulletinContent;
	}


	public void setBulletinContent(String bulletinContent) {
		this.bulletinContent = bulletinContent;
	}


	public String getAccountId() {
		return accountId;
	}


	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}


	public String getAccountName() {
		return accountName;
	}


	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}


	public Long getBulletinState() {
		return bulletinState;
	}


	public void setBulletinState(Long bulletinState) {
		this.bulletinState = bulletinState;
	}


	public Long getBulletinTip() {
		return bulletinTip;
	}


	public void setBulletinTip(Long bulletinTip) {
		this.bulletinTip = bulletinTip;
	}


	public Date getBulletinTime() {
		return bulletinTime;
	}


	public void setBulletinTime(Date bulletinTime) {
		this.bulletinTime = bulletinTime;
	}


	public Long getBulletinTips() {
		return bulletinTips;
	}


	public void setBulletinTips(Long bulletinTips) {
		this.bulletinTips = bulletinTips;
	}


	public Long getBulletinLanguage() {
		return bulletinLanguage;
	}


	public void setBulletinLanguage(Long bulletinLanguage) {
		this.bulletinLanguage = bulletinLanguage;
	}
	
	

   

   

  




}