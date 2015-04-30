package com.sinosoft.gypsymoth.bussiness.register.service;

import java.util.ArrayList;
import java.util.List;

import com.sinosoft.gypsymoth.dao.BaseHibernateDAO;
import com.sinosoft.gypsymoth.pojo.Account;
import com.sinosoft.gypsymoth.pojo.AccountRole;
import com.sinosoft.gypsymoth.pojo.Client;

public class RegisterServiceImpl implements RegisterService {
	
	private BaseHibernateDAO baseHibernateDAO;

	public BaseHibernateDAO getBaseHibernateDAO() {
		return baseHibernateDAO;
	}

	public void setBaseHibernateDAO(BaseHibernateDAO baseHibernateDAO) {
		this.baseHibernateDAO = baseHibernateDAO;
	}
	
	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.bussiness.register.service.RegisterService#getNation()
	 */
	public List getNation() throws Exception{
		String sql = " select nation_id as nationId,nation_name as nationName, nation_en_name as nationenname , nation_cn_name as nationcnname from nation order by nation_en_name ";
		List list = new ArrayList();
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;
	}
	
	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.bussiness.register.service.RegisterService#getPromory()
	 */
	public List getPromory() throws Exception{
		String sql = " select * from promary";
		List list = new ArrayList();
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;
	}
	
	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.bussiness.register.service.RegisterService#getCity(java.lang.Integer)
	 */
	public List getCity(Integer proId) throws Exception{
		String sql = " select CITYID as cityId,CITYNAME as cityName from city where PROID=? order by CITYID";
		List list = new ArrayList();
		list.add(proId);
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;
	}
	
	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.bussiness.register.service.RegisterService#saveClient(com.sinosoft.gypsymoth.pojo.Account, com.sinosoft.gypsymoth.pojo.Client, com.sinosoft.gypsymoth.pojo.AccountRole)
	 */
	public void saveClient(Account account,Client client,AccountRole accountRole) throws Exception{
		baseHibernateDAO.saveEntity(account);
		baseHibernateDAO.saveEntity(client);
		baseHibernateDAO.saveEntity(accountRole);
	}
	
	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.bussiness.register.service.RegisterService#updateAccountStatus(java.lang.String)
	 */
	public void updateAccountStatus(String accountId) throws Exception{
		String sql = " update account set account_status=1 where account_id=?";
		List list = new ArrayList();
		list.add(accountId);
		baseHibernateDAO.updateBatchBySql(sql, list);
	}
	
	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.bussiness.register.service.RegisterService#getAccountByName(java.lang.String)
	 */
	public List getAccountByName(String accountName) throws Exception{
		String hql = " from Account as a where a.accountName=? ";
		List list = new ArrayList();
		list.add(accountName);
		List<Account> returnList = baseHibernateDAO.getEntity(hql, list);
		return returnList;
	}
	
	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.bussiness.register.service.RegisterService#getAccountByStatus(java.lang.String)
	 */
	public List getAccountByStatus() throws Exception{
		String hql = " from Account as a where a.accountStatus=0 ";
		List list = new ArrayList();
		List<Account> returnList = baseHibernateDAO.getEntity(hql, list);
		return returnList;
	}
	
	/**
	 * 根据用户名获取用户注册邮箱
	 * @param accountName
	 * @return
	 * @throws Exception
	 */
	public List getAccountMail(String accountName) throws Exception{
		String sql = " select a.account_id,a.account_name,c.register_email as mail ";
		sql += " from account a,client c ";
		sql += " where a.account_name=? and a.account_id=c.account_id ";
		sql += " union ";
		sql += " select a.account_id,a.account_name,p.email as mail ";
		sql += " from account a,person p ";
		sql += " where a.account_name=? and a.account_id=p.account_id ";
		List list = new ArrayList();
		list.add(accountName);
		list.add(accountName);
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;
	}
	
	/**
	 * 得到全部用户列表
	 * @return
	 * @throws Exception
	 */
	public List getAllAccount() throws Exception{
		String hql = " from Account ";
		List list = new ArrayList();
		List<Account> returnList = baseHibernateDAO.getEntity(hql, list);
		return returnList;
	}
	
	/**
	 * 根据省ID市ID查出港口信息
	 * <br/><br/>由省ID和市ID可以查出对应的机构信息，由机构_港口表可以查到最下级城市和港口的对应关系信息，
	 * 关联可得省市港口的对应关系
	 * @param proid		省ID 
	 * @param cityid	市ID
	 * @return	港口信息
	 * @throws Exception
	 */
	public List getPortFromORG(Integer proid,Integer cityid) throws Exception{
		String sql = " select p.*  from organization s,   port p  ,  " +
					"	(select t.port_id, max(to_number(t.org_id)) orgid " +
					"	from organization_port t  group by t.port_id) m " +
					"	where s.proid = ? and s.cityid = ?  and s.id = m.orgid   and p.id = m.port_id " ;
		List list = new ArrayList();
		list.add(proid);
		list.add(cityid);
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;
	}
	
	/**
	 * 根据港口信息查出省市ID
	 * 
	 * 
	 */
	public List getPromaryAndCityByPortID(String portid) throws Exception{
		String sql = " select s.cityid,s.proid,p.port_name from organization s ,port p , " +
				"	 ( select t.port_id, max(to_number(t.org_id)) orgid " +
				"	from organization_port t group by t.port_id ) m " +
				"	where s.id = m.orgid and m.port_id= ? and m.port_id = p.id "; 
		List list = new ArrayList();
		list.add(portid);
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList; 
	}
	
	
	
	/**  根据港口ID查出省市港口名称 */
	public List getPortProCity(String portid) throws Exception
	{
		String sql = "  select (select c.ENGLISHCITYNAME  from city c    " +
				"	where c.cityid = t.cityid  and c.proid = t.proid) cityname,   " +
				"	(select s.proname from promary s where s.proid = t.proid) proname,    " +
				"	p.port_name,   p.port_sname   from pro_city_port t, port p  where p.id = ? " +
				"	 and p.id = t.portid  "; 
		List list = new ArrayList();
		list.add(portid);
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList; 
	}
	
	
	/**
	 * 根据省市港口ID查出省市港口名
	 * 
	 * 
	 */
	public List getPCPNameByPortID(String proid,String cityid,String portid) throws Exception{
		String sql = "  select * from promary m,city t,port p" +
				"	where   t.proid = m.proid and m.proid = ? and t.cityid = ? and p.id=?  "; 
		List list = new ArrayList();
		list.add(proid);
		list.add(cityid);
		list.add(portid);
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;  
	}
	
	
	public List getPromarylistHavePort() throws Exception
	{
		String sql = " select t.proid,p.proname from pro_city_port t ,city c , promary p " +
				"	where t.cityid = c.cityid and t.proid = p.proid and c.proid = p.proid  " +
				"	group by t.proid,p.proname  order by t.proid " ;
		List list = new ArrayList(); 
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;
	}
	
	public List getCitylistHavePort(String proid) throws Exception
	{
		String sql = "  select  t.cityid ,c.cityname  from pro_city_port t,city c,port p  where  " +
				"	t.proid = ? and t.cityid = c.cityid and c.proid = t.proid   and t.portid = p.id " +
				"	   group by t.cityid ,c.cityname " ;
		List list = new ArrayList(); 
		list.add(proid);
		List returnList = baseHibernateDAO.getEntityBySql(sql, list); 
		return returnList;
	}
	
	public List getPortlistByProandCity(String proid,String cityid) throws Exception
	{
		String sql = "  select * from pro_city_port t,port p where  t.proid = ? and t.cityid = ? and t.portid = p.id and t.proid = p.proid and t.cityid = t.cityid " ;
		List list = new ArrayList();  
		list.add(proid);
		list.add(cityid);
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;
		
	}
	
	public List getPromaryCity(String portid) throws Exception
	{
		String sql = " select p.proname,c.cityname,s.port_name " +
				"	from pro_city_port t,promary p ,city c ,port s" +
				"	 where t.proid = p.proid and t.proid = c.proid " +
				"	and t.cityid = c.cityid and s.id = t.portid and t.portid = ? ";
		
		List list = new ArrayList();
		list.add(portid);
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList; 
	}
	
	
	public List getCertificateProCityPort(String portid) throws Exception
	{
		String sql = " select p.id,p.port_name,m.*  " +
				"	from pro_city_port m,  " +
				"	(select * from pro_city_port t where t.portid = ? ) n, " +
				"	port p " +
				"	where m.proid = n.proid and m.cityid = n.cityid and m.portid = p.id ";
		List list = new ArrayList();
		list.add(portid);
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList; 
	}

}
