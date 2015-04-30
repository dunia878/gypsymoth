package com.sinosoft.gypsymoth.bussiness.business.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sinosoft.gypsymoth.dao.BaseHibernateDAO;
import com.sinosoft.gypsymoth.pojo.Assignment;
import com.sinosoft.gypsymoth.pojo.Business;
import com.sinosoft.gypsymoth.pojo.Certificate;
import com.sinosoft.gypsymoth.pojo.OrgStamp;
import com.sinosoft.gypsymoth.utils.StringUtils;

/**
 * 财务收费
 * @author lixin
 *
 */
public class CertificateServiceImpl implements CertificateService {
	
	private BaseHibernateDAO baseHibernateDAO;

	public BaseHibernateDAO getBaseHibernateDAO() {
		return baseHibernateDAO;
	}

	public void setBaseHibernateDAO(BaseHibernateDAO baseHibernateDAO) {
		this.baseHibernateDAO = baseHibernateDAO;
	}
	
	
	public void save(Certificate certificate) throws Exception {
		baseHibernateDAO.saveEntity(certificate); 
	}

	
	public Certificate getCertificateById(Long id) throws Exception
	{
		return (Certificate) baseHibernateDAO.getEntityById(Certificate.class, id);
	}
	
	public Certificate getCertificateByCertNo(String certno) throws Exception
	{
		Certificate certificate = null;
		List paralist = new ArrayList();
		String hql = " from Certificate c where c.certno = ? ";
		paralist.add(certno);
		List list = baseHibernateDAO.getEntity(hql, paralist);
		if (list!=null&&list.size()>0) {
			certificate = (Certificate)list.get(0);
		}
		return certificate;
	}
	
	public void update(Certificate certificate) throws Exception {
		baseHibernateDAO.updateEntity(certificate); 
	}

	public void updateCertificateList(List<Certificate> certlist) throws Exception
	{
		if (certlist!=null&&certlist.size()>0) {
			for (int i = 0; i < certlist.size(); i++) {
				this.update(certlist.get(i));
			}
		}
	}
	
	/**
	 * 批量保存证书
	 */
	public void saveCertList(List<Certificate> certlist) throws Exception
	{
		if(certlist!=null&&certlist.size()>0){
			for (int i = 0; i < certlist.size(); i++) {
				Certificate certificate = (Certificate)certlist.get(i);
				this.save(certificate);
			}
		}
	}
	
	
	public int getCertList(List<Certificate> certlist) throws Exception
	{
		if(certlist!=null&&certlist.size()>0){
			for (int i = 0; i < certlist.size(); i++) {
				Certificate certificate = (Certificate)certlist.get(i);
				String certno = certificate.getCertno();
			}
		}
		return 0;
	}
	
	public List getCertisExistByCertNum(int min,int max,String certno) throws Exception 
	{
		List list = new ArrayList();
		String sql = "	select * from certificate t " +
				"	where t.certhead = ? and  t.certtype = 0 and t.isapply = 1  and t.certmax >= ? and ? >= t.certmin " +
				"	union " +
				"	select *   from certificate m  where m.certhead = ?   and m.isapply = 1 " +
				"	and  m.certtype = 0 and ? >= m.certmin   and m.certmax >= ? ";
		list.add(certno);
		list.add(min);
		list.add(min); 
		list.add(certno);
		list.add(max); 
		list.add(max); 
		List returnlist =  baseHibernateDAO.getEntityBySql(sql, list);
		return returnlist;
	}
	
	
	public int getCertMaxByCertNo(String certno) throws Exception
	{
		List list = new ArrayList();
		String sql = "	 select max(t.certmax) max from certificate t where t.certhead = ? ";
		list.add(certno);
		List returnlist =  baseHibernateDAO.getEntityBySql(sql, list);
		int i = 0;
		if (returnlist!=null&&returnlist.size()>0) {
			HashMap map = (HashMap)returnlist.get(i);
			i = Integer.valueOf(map.get("MAX").toString());
		}
		return i; 
	}
	
	
	
	public int getEmptyCertByOrgIDCount(HashMap map) throws Exception 
	{
		List list = new ArrayList();
		String orgname = (String)map.get("orgname");
		String orgid4 = (String)map.get("orgid4");
		String is_child = (String)map.get("is_child");
		
		String sql = "   select count(*) from (" +
		"	select org_name, org_id, sum(cn1) cn1, sum(cn2) cn2, sum(cn3) cn3  " +
		"	from " +
		"	(   select  t.org_name, t.org_id, count(*) cn1, 0 cn2, sum(t.isuse) cn3 " +
		"		from certificate t   " +
		"		where t.certtype = 0 and t.isapply = 1 ";
		
//		---------		二级跟三级	-----------
		if (is_child!=null&&is_child.equals("1")) {
			sql+= "	 and  t.org_id4 like ?	";
			list.add( orgid4.substring(0,2)+"%");
		}else if(is_child!=null&&is_child.equals("2")){
			sql+= "	 and  t.org_id4 like ?	";
			list.add(orgid4+"%");
		}
//		---------------------------------------
		
		sql += "		group by t.org_name, t.org_id, t.isuse " +
		"	union  " +
		"		select m.org_name, m.org_id, 0 cn1, count(*) cn2, sum(m.isuse) cn3 " +
		"		from certificate m " +
		"		where m.certtype = 1  and m.isapply = 1  ";
		
//		---------		二级跟三级	-----------
		if (is_child!=null&&is_child.equals("1")) {
			sql+= "	 and  m.org_id4 like ?	";
			list.add( orgid4.substring(0,2)+"%");
		}else if (is_child!=null&&is_child.equals("2")) {
			sql+= "	 and  m.org_id4 like ?	";
			list.add( orgid4+"%");
		}
//		---------------------------------------
		
		sql +=	"		group by m.org_name,m.org_id,m.isuse ) where 1=1  ";
		if (orgname!=null&&!orgname.equals("")) {
			sql += "	and org_name like  ? escape '\\' ";
			list.add("%"+StringUtils.processSearch(orgname)+"%");
		} 
 
		sql	+=	 "	group by org_name,org_id ) "; 
		int count =  baseHibernateDAO.getEntitiesCountBySql(sql, list);
		return count;
	}  
	
	public List getEmptyCertByOrgID(HashMap map,int begin,int numOfEachPage) throws Exception 
	{
		List list = new ArrayList();
		String orgname = (String)map.get("orgname");
		String orgid4 = (String)map.get("orgid4");
		String is_child = (String)map.get("is_child");

		String sql = "   select * from (" +
		"	select org_name, org_id, sum(cn1) cn1, sum(cn2) cn2, sum(cn3) cn3  " +
		"	from " +
		"	(   select  t.org_name, t.org_id, count(*) cn1, 0 cn2, sum(t.isuse) cn3 " +
		"		from certificate t   " +
		"		where t.certtype = 0 and t.isapply = 1 ";
		
//		---------		二级跟三级	-----------
		if (is_child!=null&&is_child.equals("1")) {
			sql+= "	 and  t.org_id4 like ?	";
			list.add( orgid4.substring(0,2)+"%");
		}else if(is_child!=null&&is_child.equals("2")){
			sql+= "	 and  t.org_id4 like ?	";
			list.add(orgid4+"%");
		}
//		---------------------------------------
		
		sql += "		group by t.org_name, t.org_id, t.isuse " +
		"	union  " +
		"		select m.org_name, m.org_id, 0 cn1, count(*) cn2, sum(m.isuse) cn3 " +
		"		from certificate m " +
		"		where m.certtype = 1  and m.isapply = 1  ";
		
//		---------		二级跟三级	-----------
		if (is_child!=null&&is_child.equals("1")) {
			sql+= "	 and  m.org_id4 like ?	";
			list.add( orgid4.substring(0,2)+"%");
		}else if (is_child!=null&&is_child.equals("2")) {
			sql+= "	 and  m.org_id4 like ?	";
			list.add( orgid4+"%");
		} 
//		---------------------------------------
		
		sql +=	"		group by m.org_name,m.org_id,m.isuse ) where 1=1  ";
		if (orgname!=null&&!orgname.equals("")) {
			sql += "	and org_name like  ? escape '\\' ";
			list.add("%"+StringUtils.processSearch(orgname)+"%");
		} 
 
		sql	+=	 "	group by org_name,org_id ) "; 
		
		
		List returnlist =  baseHibernateDAO.getEntityByPageBySql(sql, list, begin, numOfEachPage);
		return returnlist;
	}
	
	
	public String getPortInfo(String portid) throws Exception
	{
		String portinfo ="";
		String sql = "	select upper(p.port_sname) || ' / ' || c.cityname || ' / CHINA' portinfo from pro_city_port t, port p, city c where t.portid = p.id and c.cityid = t.cityid   and c.proid = t.proid  and t.portid = ? ";
		List list = new ArrayList();
		list.add(portid); 
		List returnlist =  baseHibernateDAO.getEntityBySql(sql, list);
		if (returnlist!=null&&returnlist.size()>0) {
			HashMap map = (HashMap)returnlist.get(0);
			portinfo = (String)map.get("PORTINFO");
			
		}
		return portinfo;
	}
	
	public List getCanUseCertlist(String orgid)
	{
		List list = new ArrayList();
		String sql = " select t.certno from certificate t where t.org_id = ? and t.isapply = 1 and t.isuse = 0  ";
		list.add(orgid);
		List returnlist =  baseHibernateDAO.getEntityBySql(sql, list);
		return returnlist;
	}
	
	public List getCanUseCertNamelist(String orgid) throws Exception
	{
		List list = new ArrayList();
		String sql = " select t.certname  from certificate t " +
				"	 where t.org_id = ?  and t.isapply = 1  " +
				"	and t.isuse = 0  and t.certtype = 0  group by t.certname  ";
		list.add(orgid);
		List returnlist =  baseHibernateDAO.getEntityBySql(sql, list);
		return returnlist;
	}
	public List getCanUseCertNoByName(String certname,String org_id) throws Exception
	{
		List list = new ArrayList();
		String sql = " select t.certno  from certificate t " +
				"	 where     t.isapply = 1     " +
				"	and t.isuse = 0  and t.certtype = 0 and  t.certname  = ? and t.org_id = ? ";
		list.add(certname);
		list.add(org_id);
		List returnlist =  baseHibernateDAO.getEntityBySql(sql, list);
		return returnlist;
	}
	
	
	public void updateCertNo(Business business) throws Exception
	{
		List list = new ArrayList();
		String sql = " update business t set t.certid = ? ,t.stampid =  ?   where t.id = ?  ";
		list.add(business.getCertid());
		list.add(business.getStampid());
		list.add(business.getId());
		baseHibernateDAO.updateBatchBySql(sql, list); 
		
//		String id = business.getCertid(); 
//		
//		List paralist = new ArrayList();
//		String psql = " update certificate t  set t.isuse  = ? where t.id = ?  ";  
//		paralist.add("1");
//		paralist.add(id); 
//		baseHibernateDAO.updateBatchBySql(psql, paralist); 
		
	}
	
	public List getCertByCertno(String certno) throws Exception
	{
		String sql = " select * from certificate t  where t.certno = ?  ";
		List list = new ArrayList();
		list.add(certno);
		List certlist =	baseHibernateDAO.getEntityBySql(sql, list);
		return certlist; 
		 
	}
	
	
	
	public int getStampListCount(HashMap map)  throws Exception
	{
		List list = new ArrayList();
		String sql = "  select count(*) from " +
				"	( select t.orgid, o.org_sname, WMSYS.WM_CONCAT(t.stampid) stamp " +
				"	 from org_stamp t , organization o " +
				"	 where t.orgid = o.id  group by t.orgid , o.org_sname )   ";
		String id = (String)map.get("id");
		if (id!=null&&!id.equals("")) {
			sql += "	and orgid = ?	";
			list.add(id);
		}
		int count =  baseHibernateDAO.getEntitiesCountBySql(sql, list); 
		return count ;
	}
	
	public List getStampList(HashMap map,int begin,int numOfEachPage)  throws Exception
	{
		List list = new ArrayList(); 
		String sql = "  select * from " +
		"	( select t.orgid, o.org_sname, WMSYS.WM_CONCAT(t.stampid) stamp " +
		"	 from org_stamp t , organization o " +
		"	 where t.orgid = o.id  group by t.orgid , o.org_sname )   ";
		String id = (String)map.get("id"); 
		if (id!=null&&!id.equals("")) { 
			sql += "	and orgid = ?	";
			list.add(id); 
		}  
		List returnlist =  baseHibernateDAO.getEntityByPageBySql(sql, list, begin, numOfEachPage);
		return returnlist;	
	}
	
	public List getStampByStampID(String stampid)  throws Exception
	{
		List list = new ArrayList(); 
		String sql = " select * from org_stamp t where t.stampid = ?   ";
		list.add(stampid);
		List returnlist =  baseHibernateDAO.getEntityBySql(sql, list);
		return returnlist;	 
	}
	
	public List getStamps(String orgid) throws Exception
	{
		List list = new ArrayList(); 
		String sql = " select t.*,o.org_sname from org_stamp t,organization  o  where t.orgid = o.id and  t.orgid = ?   ";
		list.add(orgid);
		List returnlist =  baseHibernateDAO.getEntityBySql(sql, list);
		return returnlist;	
	}
	
	public List getCertlist(String orgid) throws Exception
	{
		List list = new ArrayList(); 
		String sql = " select t.certname,t.certno from certificate t where t.org_id = ? group by t.certname ,t.certno ";
		list.add(orgid);
		List returnlist =  baseHibernateDAO.getEntityBySql(sql, list);
		return returnlist;	
	}
	
	
	
	public void updateStamp(OrgStamp orgStamp)  throws Exception
	{
		baseHibernateDAO.updateEntity(orgStamp); 
	}
	
	public void saveStamp(OrgStamp orgStamp)  throws Exception
	{
		baseHibernateDAO.saveEntity(orgStamp);
	}
	
	public void deleteStamp(OrgStamp orgStamp)  throws Exception
	{
		baseHibernateDAO.deleteEntity(OrgStamp.class,orgStamp.getId());
	}
	
	public void deleteStampByStampId(String stampid)  throws Exception
	{
		List list = new ArrayList();
		String sql = " delete from org_stamp t where t.stampid in ( ";
		list.add(stampid);
		baseHibernateDAO.deleteBatchBySql(sql, list); 
	}
	
	public void updateOrgStampBySQL(String orgid,String oldstampid,String newstampid)  throws Exception
	{
		List list = new ArrayList(); 
		String sql = " update org_stamp t set t.stampid  = ? where t.orgid = ? and t.stampid = ?   ";
		list.add(newstampid);
		list.add(orgid);
		list.add(oldstampid);
		baseHibernateDAO.updateBatchBySql(sql, list);
	}
	
	
	
	
}
