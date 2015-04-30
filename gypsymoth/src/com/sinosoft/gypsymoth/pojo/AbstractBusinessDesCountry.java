package com.sinosoft.gypsymoth.pojo;

/**
 * AbstractBusinessDesCountry entity provides the base persistence definition of the BusinessDesCountry entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractBusinessDesCountry  implements java.io.Serializable {


    // Fields    

     private Long id;
     private Long businessid;
     private String port;
     private String country;
     private Integer desindex;
     private String selectindex;

    // Constructors

    /** default constructor */
    public AbstractBusinessDesCountry() {
    }

    
    /** full constructor */
    public AbstractBusinessDesCountry(Long businessid, String port, String country,Integer desindex,String selectindex) {
        this.businessid = businessid;
        this.port = port;
        this.country = country;
        this.desindex = desindex;
        this.selectindex = selectindex;
    }

   
    // Property accessors

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public Long getBusinessid() {
        return this.businessid;
    }
    
    public void setBusinessid(Long businessid) {
        this.businessid = businessid;
    }

    public String getPort() {
        return this.port;
    }
    
    public void setPort(String port) {
        this.port = port;
    }

    public String getCountry() {
        return this.country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }


	public Integer getDesindex() {
		return desindex;
	}


	public void setDesindex(Integer desindex) {
		this.desindex = desindex;
	}


	public String getSelectindex() {
		return selectindex;
	}


	public void setSelectindex(String selectindex) {
		this.selectindex = selectindex;
	}
   








}