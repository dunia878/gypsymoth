package com.sinosoft.gypsymoth.bussiness.system.service;

import java.util.ArrayList;
import java.util.List;

import com.sinosoft.gypsymoth.pojo.Example;
import com.sinosoft.gypsymoth.pojo.Right;
import com.sinosoft.gypsymoth.dao.BaseHibernateDAO;

public class RightServiceImpl implements RightService {

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
	 * com.sinosoft.gypsymoth.bussiness.system.service.RightService#insertRight
	 * (com.sinosoft.gypsymoth.bussiness.system.pojo.Right)
	 */
	public void insertRight(Right right) throws Exception {
		baseHibernateDAO.saveEntity(right);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.sinosoft.gypsymoth.bussiness.system.service.RightService#
	 * getRightByParentId(int)
	 */
	public List<Right> getRightByParentId(int parentId) throws Exception {
		String sql = "from right where right.parentId= ? order by right.rightId desc";
		List list = new ArrayList();
		list.add(parentId);
		List<Right> returnList = baseHibernateDAO.getEntity(sql, list);
		return returnList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sinosoft.gypsymoth.bussiness.system.service.RightService#getRightById
	 * (int)
	 */
	public List<Right> getRightById(int id) throws Exception {
		String sql = "from right where right.rightId= ? order by right.rightId desc";
		List list = new ArrayList();
		list.add(id);
		List<Right> returnList = baseHibernateDAO.getEntity(sql, list);
		return returnList;
	}

	public List<Right> getAllRight() throws Exception {
		String sql = "from Right r where r.rightStatus=1 order by r.rightId asc";
		List list = new ArrayList();
		List<Right> returnList = baseHibernateDAO.getEntity(sql, list);
		return returnList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sinosoft.gypsymoth.bussiness.system.service.RightService#saveRight
	 * (com.sinosoft.gypsymoth.bussiness.system.pojo.Right)
	 */
	public void saveRight(Right right) throws Exception {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sinosoft.gypsymoth.bussiness.system.service.RightService#deleteRight
	 * (com.sinosoft.gypsymoth.bussiness.system.pojo.Right)
	 */
	public void deleteRight(Right right) throws Exception {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sinosoft.gypsymoth.bussiness.system.service.RightService#deleteRight
	 * (com.sinosoft.gypsymoth.bussiness.system.pojo.Right)
	 */
	public List<Right> getRightByAccountId(String accountId) {
		String sql = " select new Right ";
				sql += " from Account a, AccountRole ar, Role e, RoleRight rr, Right r ";
				sql += " where a.accountId=? ";
				sql += " and a.accountId=ar.accountId ";
				sql += " and ar.roleId=e.roleId ";
				sql += " and e.roleId=rr.roleId ";
				sql += " and rr.rightId=r.rightId ";
				sql += " and r.rightStatus=1 ";
				sql += " and e.roleStatus=1 ";
		List list = new ArrayList();
		list.add(accountId);
		List<Right> returnList = baseHibernateDAO.getEntity(sql, list);
		return returnList;
	}

}
