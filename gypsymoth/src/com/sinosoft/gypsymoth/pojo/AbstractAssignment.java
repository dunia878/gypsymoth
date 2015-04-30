package com.sinosoft.gypsymoth.pojo;

import java.util.Date;


/**
 * AbstractAssignment entity provides the base persistence definition of the Assignment entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractAssignment  implements java.io.Serializable {


    // Fields    

     private Long id;
     private String personto;
     private Long assignumber;
     private Date assigntime;
     private String remark;
     private Long assignerstate;
     private Long businessid;
     private String personfrom;
     private String orgfrom;
     private String orgto;
     private Long assignrole;
     private Long iscomplete;
     private Long isapplay;
     private String org4;

    // Constructors

    /** default constructor */
    public AbstractAssignment() {
    }

	/** minimal constructor */
    public AbstractAssignment(Long id) {
        this.id = id;
    }
    
    /** full constructor */
    public AbstractAssignment(Long id, String personto, Long assignumber, Date assigntime, String remark, Long assignerstate, Long businessid, String personfrom, String orgfrom, String orgto, Long assignrole, Long iscomplete,Long isapplay,String org4) {
        this.id = id;
        this.personto = personto;
        this.assignumber = assignumber;
        this.assigntime = assigntime;
        this.remark = remark;
        this.assignerstate = assignerstate;
        this.businessid = businessid;
        this.personfrom = personfrom;
        this.orgfrom = orgfrom;
        this.orgto = orgto;
        this.assignrole = assignrole;
        this.iscomplete = iscomplete;
        this.isapplay = isapplay;
        this.org4 = org4;
    }

   
    // Property accessors

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getPersonto() {
        return this.personto;
    }
    
    public void setPersonto(String personto) {
        this.personto = personto;
    }

    public Long getAssignumber() {
        return this.assignumber;
    }
    
    public void setAssignumber(Long assignumber) {
        this.assignumber = assignumber;
    }

    public Date getAssigntime() {
        return this.assigntime;
    }
    
    public void setAssigntime(Date assigntime) {
        this.assigntime = assigntime;
    }

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getAssignerstate() {
        return this.assignerstate;
    }
    
    public void setAssignerstate(Long assignerstate) {
        this.assignerstate = assignerstate;
    }

    public Long getBusinessid() {
        return this.businessid;
    }
    
    public void setBusinessid(Long businessid) {
        this.businessid = businessid;
    }

    public String getPersonfrom() {
        return this.personfrom;
    }
    
    public void setPersonfrom(String personfrom) {
        this.personfrom = personfrom;
    }

    public String getOrgfrom() {
        return this.orgfrom;
    }
    
    public void setOrgfrom(String orgfrom) {
        this.orgfrom = orgfrom;
    }

    public String getOrgto() {
        return this.orgto;
    }
    
    public void setOrgto(String orgto) {
        this.orgto = orgto;
    }

    public Long getAssignrole() {
        return this.assignrole;
    }
    
    public void setAssignrole(Long assignrole) {
        this.assignrole = assignrole;
    }

    public Long getIscomplete() {
        return this.iscomplete;
    }
    
    public void setIscomplete(Long iscomplete) {
        this.iscomplete = iscomplete;
    }

	public Long getIsapplay() {
		return isapplay;
	}

	public void setIsapplay(Long isapplay) {
		this.isapplay = isapplay;
	}

	public String getOrg4() {
		return org4;
	}

	public void setOrg4(String org4) {
		this.org4 = org4;
	}
   








}