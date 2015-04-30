package com.sinosoft.gypsymoth.pojo;

import java.util.Date;

/**
 * Business entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Business extends AbstractBusiness implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Business() {
		
	}

	/** full constructor */
	public Business(String businessname,String appname, String appno, Date appdate,
			Long destinationcountry, Date plandatein, Date plandateout,
			Date checkdate, Long businessstate, String linkmanname,
			String vesseltype, String registry, String vesselno, Long tonnage,
			String vesselname, String imo, String berth, String certification,
			String portid, String company, String remark, String phone, String email,String fax, Integer qualified,
			String certid,String stampid,String accountid,String orgid, String invoicetitle,String invoiceaddress,
			String invoicepost,String invoicer,String invoicetel,String special,String linkphone,String tempno,String portorgid,
			String inspection1,String inspection2,String inspectiondate) {
		super(businessname, appname,appno, appdate, destinationcountry, plandatein,
				plandateout, checkdate, businessstate, linkmanname, vesseltype,
				registry, vesselno, tonnage, vesselname, imo, berth,
				certification, portid, company, remark, phone, email, fax,qualified,
				certid,stampid,accountid,orgid,invoicetitle,invoiceaddress,invoicepost,
				invoicer,invoicetel,special,linkphone,tempno,portorgid,
				inspection1,inspection2,inspectiondate);
	}

}
