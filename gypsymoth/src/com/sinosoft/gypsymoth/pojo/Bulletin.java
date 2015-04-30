package com.sinosoft.gypsymoth.pojo;

import java.util.Date;

// default package



/**
 * Bulletin entity. @author MyEclipse Persistence Tools
 */
public class Bulletin extends AbstractBulletin implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public Bulletin() {
    }

    
    /** full constructor */
    public Bulletin(Long bulletinId,String bulletinContents, String bulletinName, String bulletinContent, String accountId, String accountName, Long bulletinState,Long bulletinTip,Date bulletinTime,Long bulletinTips,Long bulletinLanguage ) {
        super(bulletinId, bulletinContents,bulletinName, bulletinContent, accountId, bulletinContents,bulletinState,bulletinTip,bulletinTime,bulletinTips,bulletinLanguage);        
    }
    
   
   
}
