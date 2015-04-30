package com.sinosoft.gypsymoth.bussiness.login.service;

import java.util.ArrayList;
import java.util.List;

import com.sinosoft.gypsymoth.dao.BaseHibernateDAO;
import com.sinosoft.gypsymoth.pojo.Account;
import com.sinosoft.gypsymoth.pojo.Client;
import com.sinosoft.gypsymoth.pojo.Person;
import com.sinosoft.gypsymoth.pojo.Right;

public class LoginServiceImpl implements LoginService {

	private BaseHibernateDAO baseHibernateDAO;

	public BaseHibernateDAO getBaseHibernateDAO() {
		return baseHibernateDAO;
	}

	public void setBaseHibernateDAO(BaseHibernateDAO baseHibernateDAO) {
		this.baseHibernateDAO = baseHibernateDAO;
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sinosoft.gypsymoth.bussiness.login.service.LoginService#getAccountByName
	 * (java.lang.String)
	 */
	public List<Account> getAccountByName(String name) throws Exception {
		String sql = " from Account as a where a.accountName=? and a.accountStatus=1  ";
		List list = new ArrayList();
		list.add(name);
		List<Account> returnList = baseHibernateDAO.getEntity(sql, list);
		return returnList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.sinosoft.gypsymoth.bussiness.login.service.LoginService#
	 * updateAccountLogin_lock(com.sinosoft.gypsymoth.pojo.Account)
	 */
	public void updateAccountLogin_lock(Account account) throws Exception {
	}

	public void updateAccount(Account account) throws Exception {
		baseHibernateDAO.updateEntity(account);
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.sinosoft.gypsymoth.bussiness.login.service.LoginService#
	 * getRightByAccountId(java.lang.String)
	 */
	public List<Right> getRightByAccountId(String accountId,String parentid) throws Exception {
		String sql = " select distinct r ";
		sql += " from Account a, AccountRole ar, Role e, RoleRight rr, Right r ";
		sql += " where a.accountId=? ";
		sql += " and a.accountId=ar.accountId ";
		sql += " and ar.roleId=e.roleId ";
		sql += " and e.roleId=rr.roleId ";
		sql += " and rr.rightId=r.rightId ";
		sql += " and r.rightStatus=1 ";
		sql += " and e.roleStatus=1 ";
		//sql += " and r.parentid=? ";
		sql += " order by r.orderBy, r.rightId ";
		List list = new ArrayList();
		list.add(accountId);
		//list.add(parentid);
		List<Right> returnList = baseHibernateDAO.getEntity(sql, list);
		return returnList;
	}
	
	
	/**  通过account_id得到person */
	public Person getPersonByAccountID(String account_id) throws Exception {
		Person person =  null;
		List paralist = new ArrayList();
		paralist.add(account_id);
		String hql = " from Person p where p.accountId = ? ";
		List list = baseHibernateDAO.getEntity(hql, paralist);
		if (list!=null&&list.size()>0) {
			person = (Person)list.get(0);
		}
		return person;
	}
	
	/**  通过account_id得到client */
	public Client getClientByAccountID(String account_id) throws Exception
	{
		Client client = null;
		List paralist = new ArrayList();
		paralist.add(account_id);
		String hql = " from Client c where c.accountId = ? ";
		List list = baseHibernateDAO.getEntity(hql, paralist);
		if (list!=null&&list.size()>0) {
			client = (Client)list.get(0);
		}
		return client;
	}
	
	/**
	 * 通过person_id得到所属人员相关信息
	 */
	public List getInfomationByPersonID(String person_id) throws Exception {
		List paralist = new ArrayList();
		paralist.add(person_id);
		String sql = " select r.*, t.is_child, p.org_id, p.person_id , o.org_id orgid , t.org_name " +
				"	from organization_level t, person p, account_role r  ,organization o " +
				"	where p.org_id = t.id  and p.account_id = r.account_id  and o.id = t.id  " +
				"	and p.person_id = ? ";
		List list = baseHibernateDAO.getEntityBySql(sql, paralist);
		return list; 
	}

}
