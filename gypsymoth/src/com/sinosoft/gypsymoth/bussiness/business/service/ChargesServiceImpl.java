package com.sinosoft.gypsymoth.bussiness.business.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;

import com.sinosoft.gypsymoth.dao.BaseHibernateDAO;
import com.sinosoft.gypsymoth.pojo.Opaccount;
import com.sinosoft.gypsymoth.pojo.Payment;
import com.sinosoft.gypsymoth.utils.StringUtils;

/**
 * 财务收费
 * @author lixin
 *
 */
public class ChargesServiceImpl implements ChargesService {
	
	private BaseHibernateDAO baseHibernateDAO;

	public BaseHibernateDAO getBaseHibernateDAO() {
		return baseHibernateDAO;
	}

	public void setBaseHibernateDAO(BaseHibernateDAO baseHibernateDAO) {
		this.baseHibernateDAO = baseHibernateDAO;
	}
	
	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.bussiness.business.service.ChargesService#getPaymentByPage(int, int)
	 */
	public List getPaymentByPage(Map selectMap,int begin,int numOfEachPage) throws Exception{
		List list = new ArrayList();
		String sql = " select m.*,c.cityname,ac.account_name from ( ";
		  sql +=  " select distinct one.*,two.* ";
		  sql += " from (select p.businessid, ";
		  sql += " p.paystate, ";
		  sql += " p.ledgerstate, ";
		  sql += " b.vesselname, ";
		  sql += " b.portid, ";
		  sql += " b.imo, ";
		  sql += " b.tonnage, ";
		  sql += " b.appdate, ";
		  sql += " b.checkdate, ";
		  sql += " b.businessstate, ";
		  sql += " b.appno, ";
		  sql += " b.accountid, ";
		  sql += " b.portorgid, ";
		  sql += " b.businessname ";
		  sql += " from BUSINESS b, PAYMENT p ";
		  sql += " where b.id = p.businessid) one ";
		  sql += " left join (select  o.businessid as towid ";
		  sql += " from OPERATOR o ";
		  sql += " where o.disposetype = 3) two on one.businessid = two.towid ";
		  if(selectMap.get("selectOrgid")!=null && !"".equals(selectMap.get("selectOrgid").toString())){
				sql+=" left join assignment a on one.businessid = a.businessid ";
				sql+=" where ";
				sql+=" ( a.isapplay=1 ";
				sql+=" and a.orgto in("+selectMap.get("selectOrgid")+")) ";
				sql+=" or(a.isapplay is null and one.portorgid in("+selectMap.get("selectOrgid")+") ) ";
			}
			if(selectMap.get("accountId")!=null && !"".equals(selectMap.get("accountId").toString())){
				sql+=" or(one.accountid =?) ";
				list.add(selectMap.get("accountId"));
			}
		  sql += " )m,port o,city c,promary y,account ac ";
		  sql += " where m.portid = o.id and ";
		  sql += " o.cityid = c.cityid and ";
		  sql += " c.proid = y.proid and ";
		  sql += " o.proid = c.proid and ";
		  sql += " ac.account_id = m.accountid and ";
		    if(selectMap.get("selectVesselname")!=null && !"".equals(selectMap.get("selectVesselname"))){
				sql+=" upper(vesselname) like ? and ";
				list.add("%"+selectMap.get("selectVesselname")+"%");
			}
		    if(selectMap.get("selectAppno")!=null && !"".equals(selectMap.get("selectAppno").toString())){
				sql+=" upper(appno)= ? and ";
				list.add(selectMap.get("selectAppno"));
			}
			if(selectMap.get("selectLedgerstate")!=null && !"".equals(selectMap.get("selectLedgerstate").toString())){
				sql+=" ledgerstate= ? and ";
				list.add(selectMap.get("selectLedgerstate"));
			}
			if(selectMap.get("selectBusinessstate")!=null && !"".equals(selectMap.get("selectBusinessstate").toString())){
				sql+=" businessstate= ? and ";
				list.add(selectMap.get("selectBusinessstate"));
			}
			if(selectMap.get("selectPaystate")!=null && !"".equals(selectMap.get("selectPaystate").toString())){
				sql+=" paystate= ? and ";
				list.add(selectMap.get("selectPaystate"));
			}
			if(selectMap.get("selectCityname")!=null && !"".equals(selectMap.get("selectCityname").toString())){
				sql+=" cityname like ? and ";
				list.add("%"+selectMap.get("selectCityname")+"%");
			}
			if(selectMap.get("selectBeginAppdate")!=null && !"".equals(selectMap.get("selectBeginAppdate").toString())){
				sql+=" m.appdate>=TO_DATE(?,'yyyy-mm-dd') and ";
				list.add(selectMap.get("selectBeginAppdate"));
			}
			if(selectMap.get("selectEndAppdate")!=null && !"".equals(selectMap.get("selectEndAppdate").toString())){
				sql+=" m.appdate<=TO_DATE(?,'yyyy-mm-dd') and ";
				list.add(selectMap.get("selectEndAppdate"));
			}
			if(selectMap.get("businessname")!=null && !"".equals(selectMap.get("businessname").toString())){
				sql+=" m.BUSINESSNAME= ? and ";
				list.add(selectMap.get("businessname"));
			}
			//add by guodingjun 
			if(selectMap.get("account_name")!=null && !"".equals(selectMap.get("account_name").toString())){
				sql+=" AC.account_name like ? and ";
				list.add("%"+selectMap.get("account_name") +"%");
			}
		  sql += " 1=1 ";
		  sql += " order by appdate desc ";
		  
		  List returnList = baseHibernateDAO.getEntityByPageBySql(sql, list, begin, numOfEachPage);
		return returnList;
	}
	
	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.bussiness.business.service.ChargesService#getAllRoleCount()
	 */
	public int getAllPaymentCount(Map selectMap) throws Exception{
		List list = new ArrayList();
		String sql = " select count(*) from( ";
		  sql += " select distinct one.*,two.* from (select p.businessid, ";
		  sql += " p.paystate, ";
		  sql += " p.ledgerstate, ";
		  sql += " b.vesselname, ";
		  sql += " b.portid, ";
		  sql += " b.imo, ";
		  sql += " b.tonnage, ";
		  sql += " b.appdate, ";
		  sql += " b.checkdate, ";
		  sql += " b.businessstate, ";
		  sql += " b.appno, ";
		  sql += " b.accountid, ";
		  sql += " b.portorgid, ";
		  sql += " b.businessname ";
		  sql += " from BUSINESS b, PAYMENT p ";
		  sql += " where b.id = p.businessid) one ";
		  sql += " left join (select  o.businessid as towid ";
		  sql += " from OPERATOR o ";
		  sql += " where o.disposetype = 3) two on one.businessid = two.towid ";
		  if(selectMap.get("selectOrgid")!=null && !"".equals(selectMap.get("selectOrgid").toString())){
				sql+=" left join assignment a on one.businessid = a.businessid ";
				sql+=" where ";
				sql+=" ( a.isapplay=1 ";
				sql+=" and a.orgto in("+selectMap.get("selectOrgid")+")) ";
				sql+=" or(a.isapplay is null and one.portorgid in("+selectMap.get("selectOrgid")+") ) ";
			}
			if(selectMap.get("accountId")!=null && !"".equals(selectMap.get("accountId").toString())){
				sql+=" or(one.accountid =?) ";
				list.add(selectMap.get("accountId"));
			}
		  sql += "  )m,port o,city c,promary y,account ac ";
		  sql += " where m.portid = o.id and ";
		  sql += " o.cityid = c.cityid and ";
		  sql += " c.proid = y.proid and ";
		  sql += " o.proid = c.proid and ";
		  sql += " ac.account_id = m.accountid and ";
		  if(selectMap.get("selectVesselname")!=null && !"".equals(selectMap.get("selectVesselname"))){
				sql+=" upper(vesselname) like ? and ";
				list.add("%"+selectMap.get("selectVesselname")+"%");
			}
			if(selectMap.get("selectAppno")!=null && !"".equals(selectMap.get("selectAppno").toString())){
				sql+=" upper(appno)= ? and ";
				list.add(selectMap.get("selectAppno"));
			}
			if(selectMap.get("selectLedgerstate")!=null && !"".equals(selectMap.get("selectLedgerstate").toString())){
				sql+=" ledgerstate= ? and ";
				list.add(selectMap.get("selectLedgerstate"));
			}
			if(selectMap.get("selectBusinessstate")!=null && !"".equals(selectMap.get("selectBusinessstate").toString())){
				sql+=" businessstate= ? and ";
				list.add(selectMap.get("selectBusinessstate"));
			}
			if(selectMap.get("selectPaystate")!=null && !"".equals(selectMap.get("selectPaystate").toString())){
				sql+=" paystate= ? and ";
				list.add(selectMap.get("selectPaystate"));
			}
			if(selectMap.get("selectCityname")!=null && !"".equals(selectMap.get("selectCityname").toString())){
				sql+=" cityname like ? and ";
				list.add("%"+selectMap.get("selectCityname")+"%");
			}
			if(selectMap.get("selectBeginAppdate")!=null && !"".equals(selectMap.get("selectBeginAppdate").toString())){
				sql+=" m.appdate>=TO_DATE(?,'yyyy-mm-dd') and ";
				list.add(selectMap.get("selectBeginAppdate"));
			}
			if(selectMap.get("selectEndAppdate")!=null && !"".equals(selectMap.get("selectEndAppdate").toString())){
				sql+=" m.appdate<=TO_DATE(?,'yyyy-mm-dd') and ";
				list.add(selectMap.get("selectEndAppdate"));
			}
			if(selectMap.get("businessname")!=null && !"".equals(selectMap.get("businessname").toString())){
				sql+=" m.BUSINESSNAME= ? and ";
				list.add(selectMap.get("businessname"));
			}
			//add by guodingjun 
			if(selectMap.get("account_name")!=null && !"".equals(selectMap.get("account_name").toString())){
				sql+=" AC.account_name like ? and ";
				list.add("%"+selectMap.get("account_name") +"%");
			}
		  sql += " 1=1 ";
		  
		  int count = baseHibernateDAO.getEntitiesCountBySql(sql, list);
		return count;
	}
	
	/**
	 * 根据业务ID查询缴费记录
	 * @param businessid
	 * @return
	 * @throws Exception
	 */
	public List getPaymentByBusinessid(Integer businessid) throws Exception{
		String sql = " select a.*, ";
		sql += " o1.org_name as onename, ";
		sql += " o2.org_name as twoname, ";
		sql += " o3.org_name as threename ";
		sql += " from (select p.*, ";
		sql += " b.vesselname, ";
		sql += " b.vesseltype, ";
		sql += " b.tonnage, ";
		sql += " b.special, ";
		sql += " b.appno, ";
		sql += " b.invoicetitle, ";
		sql += " r.one, ";
		sql += " r.two, ";
		sql += " r.three ";
		sql += " from BUSINESS b, PAYMENT p, rule r ";
		sql += " where b.id = p.businessid ";
		sql += " and r.RULESTATE=1 ";
		sql += " and p.businessid = ? ";
		sql += " ) a ";
		sql += " left join organization_level o1 on a.firstcomid = o1.id ";
		sql += " left join organization_level o2 on a.secondcomid = o2.id ";
		sql += " left join organization_level o3 on a.thirdcomid = o3.id ";
		List list = new ArrayList();
		list.add(businessid);
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;

	}
	
	public void updatePayment(Payment payment) throws Exception{
		baseHibernateDAO.updateEntity(payment);
	}
	
	/**	根据业务表ID得到收费信息	*/
	public Payment getPaymentByBusinessID(Long businessid) throws Exception
	{
		List paralist = new ArrayList();
		Payment payment = null;
		String hql = "  from Payment t where t.businessid = ? ";
		paralist.add(businessid);
		List list = baseHibernateDAO.getEntity(hql, paralist);
		if (list!=null&&list.size()>0) {
			payment = (Payment)list.get(0);
		}
		return payment;
	}
	

	public int getNotPaymentsCount(HashMap map) throws Exception
	{
		List paralist  = new ArrayList();
//		String sql = "  select count(*) from payment t ,business b " +
//				"	where t.businessid  = b.id  and b.businessname = ? and b.businessstate = 0 " +
//				"	or  ( t.businessid  = b.id  and  b.businessstate =1 and b.businessname = ? ) ";
		 
		String sql="select count(*) from (select * from business b where b.businessname= ? and b.id not in(select t.businessid from payment t ) union select b1.* from business b1, payment t1 where b1.businessname= ? and  t1.businessid=b1.id and t1.paystate in(0,1,4))";
		String businessname = (String)map.get("businessname");
		paralist.add(businessname); 
		paralist.add(businessname); 
		int count = baseHibernateDAO.getEntitiesCountBySql(sql, paralist);
		return count; 
	}
   /**
    * 
    * liuxiaozhe
    * 11.3.24
    * 保存财务操作记录
    */
	public void saveOpAccount(Opaccount opaccount) throws Exception {
		// TODO Auto-generated method stub
		baseHibernateDAO.saveEntity(opaccount);
	}
	 /**
	    * 
	    * liuxiaozhe
	    * 11.3.24
	    * 取得财务操作记录
	    */
	public List getOpAccount(Integer businessid, int f) throws Exception {
		// TODO Auto-generated method stub
		List paralist = new ArrayList();
		String hql = null;
		List list = null;
		if(f == 0) {
			hql = "  from Opaccount o where o.businessid =" + businessid + " order by id asc";
			list = baseHibernateDAO.getEntity(hql, paralist);
		}else if(f == 1) {
			hql = "from Opaccount o where o.businessid =" + businessid + " order by id desc ";
			list = baseHibernateDAO.getEntityByPage(hql, paralist, 0, 1);
		}
		 
		
		return list;
	}
	
}
