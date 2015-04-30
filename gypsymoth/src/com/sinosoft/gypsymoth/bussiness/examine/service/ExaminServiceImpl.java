package com.sinosoft.gypsymoth.bussiness.examine.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sinosoft.gypsymoth.dao.BaseHibernateDAO;
import com.sinosoft.gypsymoth.pojo.Account;
import com.sinosoft.gypsymoth.pojo.Business;
import com.sinosoft.gypsymoth.pojo.ExamPort;
import com.sinosoft.gypsymoth.pojo.Examinelog;
import com.sinosoft.gypsymoth.pojo.Operator;
import com.sinosoft.gypsymoth.utils.StringUtils;

public class ExaminServiceImpl implements ExamineService {
	
	private BaseHibernateDAO baseHibernateDAO;

	public BaseHibernateDAO getBaseHibernateDAO() {
		return baseHibernateDAO;
	}

	public void setBaseHibernateDAO(BaseHibernateDAO baseHibernateDAO) {
		this.baseHibernateDAO = baseHibernateDAO;
	}
	public int getAllBussinessCount(HashMap map) throws Exception {
		List list = new ArrayList();
		String sql = " select count(*) from ( select m.*,m2.*, (select c.cityname  from pro_city_port p, city c  where p.portid = m.portid  and p.proid = c.proid  and p.cityid = c.cityid) portcity " +
		",  ( select a.account_name  from account a  where a.account_id = m.accountid) account_name " +
		" from (select * from  business  where  business.businessstate = 4 ) m,   " +
		"	(select t1.businessid   " +
		"	 from assignment t1   where  t1.iscomplete = 1 " +
		"	 group by t1.businessid) " +
		"	m2 where exists (select t.businessid  from assignment t " +
		"	 where t.businessid = m.id ";
		String personid = (String)map.get("personid");
		if(personid!=null&&!personid.equals("")){
			sql += " and t.personto = ? ";
			list.add(personid);
		}
		sql += "  and t.iscomplete =1 )  and m.id = m2.businessid and m.certid is not null  " +
		"	) where 1=1	";
	 
		String vesselname = (String)map.get("vesselname");
		if (vesselname!=null&&!vesselname.equals("")) {
			sql += " and upper(vesselname) like ? ";
			list.add("%"+StringUtils.processSearch(vesselname)+"%");
		}
		String portcity = (String)map.get("portcity");
		if (portcity!=null&&!portcity.equals("")) {
			sql += " and portcity like ? ";
			list.add("%"+StringUtils.processSearch(portcity)+"%");
		}
		String appno = (String)map.get("appno");
		if (appno!=null&&!appno.equals("")) {
			sql += " and upper(appno) like ?  escape '\\' ";
			list.add("%"+StringUtils.processSearch(appno)+"%");
		}
		String appdate_begin = (String)map.get("appdate_begin");
		String appdate_end = (String)map.get("appdate_end");
		if (appdate_begin!=null&&!appdate_begin.equals("")&&appdate_end!=null&&!appdate_end.equals("")) {
			sql += " and appdate >= to_date(?,'yyyy-MM-dd')	and to_date(?,'yyyy-MM-dd')+1	>= appdate  ";
			list.add(appdate_begin);
			list.add(appdate_end);
		}
		
		
		int count = baseHibernateDAO.getEntitiesCountBySql(sql, list);
		return count;
	}

	public List getAllBussinessByPage(HashMap map, int begin, int numOfEachPage)throws Exception {
		
		List paralist = new ArrayList();
		String sql = " select * from ( select m.*,m2.*, (select c.cityname  from pro_city_port p, city c  where p.portid = m.portid  and p.proid = c.proid  and p.cityid = c.cityid) portcity " +
		",  ( select a.account_name  from account a  where a.account_id = m.accountid) account_name " +
		" from (select * from  business  where  business.businessstate = 4 ) m,   " +
		"	(select t1.businessid  " +
		"	 from assignment t1   " +
		"	 group by t1.businessid) " +
		"	m2 where exists (select t.businessid  from assignment t " +
		"	 where t.businessid = m.id ";
		
		String personid = (String)map.get("personid");
		if(personid!=null&&!personid.equals("")){
			sql += "  and t.personto = ?  ";
			paralist.add(personid);
		}
	
		sql +=	"  and t.iscomplete =1 )  and m.id = m2.businessid and m.certid is not null  " +
		"	) where 1=1	";
		String vesselname = (String)map.get("vesselname");
		if (vesselname!=null&&!vesselname.equals("")) {
			sql += " and upper(vesselname) like ? ";
			paralist.add("%"+StringUtils.processSearch(vesselname)+"%");
		}
		String portcity = (String)map.get("portcity");
		if (portcity!=null&&!portcity.equals("")) {
			sql += " and portcity like ? ";
			paralist.add("%"+StringUtils.processSearch(portcity)+"%");
		}
		String tempno = (String)map.get("tempno");
		if (tempno!=null&&!tempno.equals("")) {
			sql += " and upper(tempno) like ? ";
			paralist.add("%"+StringUtils.processSearch(tempno)+"%");
		}
		String appno = (String)map.get("appno");
		if (appno!=null&&!appno.equals("")) {
			sql += " and appno like ?  escape '\\' ";
			paralist.add("%"+StringUtils.processSearch(appno)+"%");
		}
		String appdate_begin = (String)map.get("appdate_begin");
		String appdate_end = (String)map.get("appdate_end");
		if (appdate_begin!=null&&!appdate_begin.equals("")&&appdate_end!=null&&!appdate_end.equals("")) {
			sql += " and appdate >= to_date(?,'yyyy-MM-dd')	and to_date(?,'yyyy-MM-dd')+1	<= appdate  ";
			paralist.add(appdate_begin);
			paralist.add(appdate_end);
		}
		
		List returnList = baseHibernateDAO.getEntityByPageBySql(sql.toString(), paralist, begin, numOfEachPage);
		return returnList;		
		
	}

	public List getBussinessById(int id) throws Exception {
		StringBuffer sql= new StringBuffer();
		sql.append(" select b.id,                  ");
		sql.append("        businessname,          ");
		sql.append("        appno,                 ");
		sql.append("        appdate,               ");
		sql.append("        destinationcountry,    ");
		sql.append("        plandatein,            ");
		sql.append("        plandateout,           ");
		sql.append("        checkdate,             ");
		sql.append("        businessstate,         ");
		sql.append("        linkmanname,           ");
		sql.append("        vesseltype,            ");
		sql.append("        registry,              ");
		sql.append("        vesselno,              ");
		sql.append("        tonnage,               ");
		sql.append("        vesselname,            ");
		sql.append("        imo,                   ");
		sql.append("        berth,                 ");
		sql.append("        certification,         ");
		sql.append("        portid,                ");
		sql.append("        company,               ");
		sql.append("        remark,                ");
		sql.append("        appname,               ");
		sql.append("        fax,                   ");
		sql.append("        email,                 ");
		sql.append("        phone,                 ");
		sql.append("        port_name,             ");
		sql.append("        port_sname             ");
		sql.append("   from business  b,port p     ");
		sql.append("   where b.id=?                ");
		sql.append("	and  b.portid=p.id   	   ");
		List list = new ArrayList();
		list.add(id);
		List returnList = baseHibernateDAO.getEntityBySql(sql.toString(), list);
		return returnList;
	}
	public void saveExamine(Examinelog el,Operator op,String[] nationid,String appId,String[] portName,Date[] stopTime,String[] anationid,String[] aportName,Date[] astopTime) throws Exception {
		baseHibernateDAO.saveEntity(el);
		baseHibernateDAO.saveEntity(op);
		ExamPort ep=new ExamPort();			
		for(int i=0;i<nationid.length;i++){
			if((nationid[i]!=null&&!"".equals(nationid[i]))&&(portName[i]!=null&&!"".equals(portName[i]))&&(stopTime[i]!=null&&!"".equals(stopTime[i]))){
			ep.setAppId(appId);	
			if(nationid[i]!=null&&!"".equals(nationid[i])){
			ep.setNationid(Integer.parseInt(nationid[i]));
			}
			ep.setPortName(portName[i]);
			ep.setStopTime(stopTime[i]);
			ep.setStopStatus(0);
			baseHibernateDAO.saveEntity(ep);
		}
		}
		for(int i=0;i<anationid.length;i++){
			if((anationid[i]!=null&&!"".equals(anationid[i]))&&(aportName[i]!=null&&!"".equals(aportName[i]))&&(astopTime[i]!=null&&!"".equals(astopTime[i]))){
			ep.setAppId(appId);	
			ep.setNationid(Integer.parseInt(anationid[i]));
			ep.setPortName(aportName[i]);
			ep.setStopTime(astopTime[i]);
			ep.setStopStatus(1);
			baseHibernateDAO.saveEntity(ep);
		}	
		}
		String sql= "update business set businessstate=5 where id=?";
		List list = new ArrayList();
		list.add(appId);
		baseHibernateDAO.updateBatchBySql(sql, list);
	}
	
	public void savePort(ExamPort ep) throws Exception {
		baseHibernateDAO.saveEntity(ep);		
	}

	public List getDesCountryById(int id) throws Exception {
		StringBuffer sql= new StringBuffer();
		sql.append("  select id,                 ");
		sql.append("  businessid,                ");
		sql.append("  port, country,             ");
		sql.append("  desindex                   ");
		sql.append("  from business_des_country  ");
		sql.append("   where businessid=?        ");
		List list = new ArrayList();
		list.add(id);
		List returnList = baseHibernateDAO.getEntityBySql(sql.toString(), list);
		return returnList;
	}

	public List getPersonByAccountId(String accountId) throws Exception {
		String sql="select p.id,is_coordinator,is_authorized,is_inspector,org_id from person p,organization_level ol where p.org_id=ol.id and p.account_id=? and person_status=1 ";
		List list = new ArrayList();
		list.add(accountId);
		List returnList = baseHibernateDAO.getEntityBySql(sql.toString(), list);
		return returnList;
	}

	public Business getBusinessObject(Long id) throws Exception {
		return (Business) baseHibernateDAO.getEntityById(Business.class, id);
	}

	public void updateBusiness(Business bus) throws Exception {
		baseHibernateDAO.updateEntity(bus);
		System.out.print("asdad");
		
	}
	/* ***************** add by guodingjun in 2011-04-06 ************************** */
	/* *****************          add star         ************************** */
	
	public List getExamineByBusinessId(String businessId) throws Exception {
		String sql = "select * from examinelog t where t.businessid = ?";
		List list = new ArrayList();
		list.add(businessId);
		List examineList = baseHibernateDAO.getEntityBySql(sql, list);
		return examineList;
	}

	public void updateExaminelog(Examinelog examinelog) throws Exception {
		baseHibernateDAO.updateEntity(examinelog);
	}
	
	public void saveExamine(Examinelog el) throws Exception {
		baseHibernateDAO.saveEntity(el);
	}
	
	public List getExamPortByBusinesssId(String businessId) throws Exception {
		String sql = "select * from exam_port t where t.app_id =?";
		List list = new ArrayList();
		list.add(businessId);
		List examPortList = baseHibernateDAO.getEntityBySql(sql, list);
		return examPortList;
	}

	public void saveExamPort(String[] nationid,String appId,String[] portName,Date[] stopTime,String[] anationid,String[] aportName,Date[] astopTime) throws Exception {
		ExamPort ep=new ExamPort();			
		for(int i=0;i<nationid.length;i++){
			if((nationid[i]!=null&&!"".equals(nationid[i]))&&(portName[i]!=null&&!"".equals(portName[i]))&&(stopTime[i]!=null&&!"".equals(stopTime[i]))){
			ep.setAppId(appId);	
			if(nationid[i]!=null&&!"".equals(nationid[i])){
			ep.setNationid(Integer.parseInt(nationid[i]));
			}
			ep.setPortName(portName[i]);
			ep.setStopTime(stopTime[i]);
			ep.setStopStatus(0);
			baseHibernateDAO.saveEntity(ep);
		}
		}

	}
	
	public void deleteExamPort(String businessId) throws Exception {
		String sql = "delete from exam_port t where t.app_id in (";
		List list = new ArrayList();
		list.add(businessId);
		baseHibernateDAO.deleteBatchBySql(sql, list);
	}
	
	/* *****************          add end         ************************** */



}
