package com.sinosoft.gypsymoth.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * HibernateDao封装
 * @author lixin
 * 
 */
public class BaseHibernateDAOImpl extends HibernateDaoSupport implements BaseHibernateDAO {

	public Session openSession() {
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		 return session;
	}
	
	
	public String getSequenceFromSql(String sql){
		String res="";
		try{
			Session session = this.openSession();
			BigDecimal sequence=(BigDecimal) session.createSQLQuery(sql).uniqueResult();
			session.close();
			res= sequence.toString();
		}catch(Exception e){}
		return res;
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.dao.BaseHibernateDAO#getEntityBySql(java.lang.String, java.util.List, java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public List getEntity(String hql, List list){
		Session session = this.openSession();
		Transaction tx = null;
		List returnList = new ArrayList();
		try {
			tx = session.beginTransaction();
			session.clear();
			Query query = session.createQuery(hql);
			Iterator iterator = list.iterator();
			int i = 0;
			while (iterator.hasNext()) {
				query.setParameter(i, iterator.next());
				i++;
			}
			tx.commit();
			returnList = query.list();

		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return returnList;
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.dao.BaseHibernateDAO#getEntityBySql(java.lang.String, java.util.List, java.lang.Class, int, int)
	 */
	@SuppressWarnings("unchecked")
	public List getEntityByPage(String hql, List list, int begin,
			int numOfEachPage) {
		Session session = this.openSession();
		Transaction tx = null;
		List returnList = new ArrayList();
		try {
			tx = session.beginTransaction();
			session.clear();
			Query query = session.createQuery(hql);
			Iterator iterator = list.iterator();
			int i = 0;
			while (iterator.hasNext()) {
				query.setParameter(i, iterator.next());
				i++;
			}
			query.setFirstResult(begin);
			query.setMaxResults(numOfEachPage);
			tx.commit();
			returnList = query.list();

		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return returnList;
	}
	
	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.dao.BaseHibernateDAO#getEntities(java.lang.String, java.util.List, java.lang.Class, int, int)
	 */
	@SuppressWarnings("unchecked")
	public List getEntities(String hql, List list, int begin,
			int numOfEachPage) {
		Session session = this.openSession();
		Transaction tx = null;
		List returnList = new ArrayList();
		try {
			tx = session.beginTransaction();
			session.clear();
			Query query = session.createQuery(hql);
			Iterator iterator = list.iterator();
			int i = 0;
			while (iterator.hasNext()) {
				query.setParameter(i, iterator.next());
				i++;
			}
			query.setFirstResult(begin);
			query.setMaxResults(numOfEachPage);
			tx.commit();
			returnList = query.list();
			System.out.println(returnList);
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return returnList;
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.dao.BaseHibernateDAO#getEntitiesCountBySql(java.lang.String, java.util.List)
	 */
	@SuppressWarnings("unchecked")
	public int getEntitiesCountBySql(String sql, List list) {
		Session session = this.openSession();
		Transaction tx = null;
		int count = 0;
		List returnList = new ArrayList();
		try {
			tx = session.beginTransaction();
			session.clear();
			Query query = session.createSQLQuery(sql);
			Iterator iterator = list.iterator();
			int i = 0;
			while (iterator.hasNext()) {
				query.setParameter(i, iterator.next());
				i++;
			}
			tx.commit();
			returnList = query.list();
			count = ((BigDecimal) returnList.get(0)).intValue();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return count;
	}
	
	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.dao.BaseHibernateDAO#getEntitiesCountBySql(java.lang.String, java.util.List)
	 */
	@SuppressWarnings("unchecked")
	public List getEntitiesBySql(String sql, List list,int begin,
			int numOfEachPage) {
		Session session = this.openSession();
		Transaction tx = null;
		List returnList = new ArrayList();
		try {
			tx = session.beginTransaction();
			session.clear();
			Query query = session.createSQLQuery(sql);
			Iterator iterator = list.iterator();
			int i = 0;
			while (iterator.hasNext()) {
				query.setParameter(i, iterator.next());
				i++;
			}
			tx.commit();
			returnList = query.list();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return returnList;
	}


	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.dao.BaseHibernateDAO#deleteEntity(java.lang.Class, java.io.Serializable)
	 */
	@SuppressWarnings("unchecked")
	public void deleteEntity(Class cla, Serializable id) {
		this.getHibernateTemplate().delete(
				this.getHibernateTemplate().load(cla, id));
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.dao.BaseHibernateDAO#saveEntity(java.lang.Object)
	 */
	public void saveEntity(Object object) throws Exception {
		this.getHibernateTemplate().save(object);
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.dao.BaseHibernateDAO#getEntityById(java.lang.Class, java.io.Serializable)
	 */
	@SuppressWarnings("unchecked")
	public Object getEntityById(Class cla, Serializable id) {
		return this.getHibernateTemplate().get(cla, id);
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.dao.BaseHibernateDAO#updateEntity(java.lang.Object)
	 */
	public void updateEntity(Object object) {
		this.getHibernateTemplate().update(object);
	}
	
	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.dao.BaseHibernateDAO#deleteBatchBySql(java.lang.String, java.util.List)
	 */
	@SuppressWarnings("unchecked")
	public void deleteBatchBySql(String sql, List list) {
		Session session = this.openSession();
		// sql例如:delete from Person where id in(
		StringBuilder sb = new StringBuilder(sql);
		for (int i = 0; i < list.size(); i++) {
			sb.append("?,");
		}
		sb.replace(sb.length() - 1, sb.length(), ")");// 把最后一个逗号换成括号
		System.out.println(sb);
		Query q = session.createSQLQuery(sb.toString());
		for (int i = 0; i < list.size(); i++)
			q.setParameter(i, list.get(i));
		q.executeUpdate();
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.dao.BaseHibernateDAO#updateBatchBySql(java.lang.String, java.util.List)
	 */
	@SuppressWarnings("unchecked")
	public void updateBatchBySql(String sql, List list) {
		Session session = this.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.clear();
			Query query = session.createSQLQuery(sql);
			Iterator iterator = list.iterator();
			int i = 0;
			while (iterator.hasNext()) {
				query.setParameter(i, iterator.next());
				i++;
			}
			query.executeUpdate();
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	
	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.dao.BaseHibernateDAO#updateBatchBySql(java.lang.String, java.util.List)
	 */
	@SuppressWarnings("unchecked")
	public List updateBatchByHql(String hql, List list) {
		Session session = this.openSession(); 
		Transaction tx = null;
		List returnlist = new ArrayList();
		try {
			tx = session.beginTransaction();
			session.clear();
			Query query = session.createQuery(hql);
			Iterator iterator = list.iterator();
			int i = 0;
			while (iterator.hasNext()) {
				query.setParameter(i, iterator.next());
				i++;
			}
			int num = query.executeUpdate();
			tx.commit();
			returnlist.add(num);
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return returnlist;
	}
	
	
	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.dao.BaseHibernateDAO#getEntityBySql(java.lang.String, java.util.List)
	 */
	@SuppressWarnings("unchecked")
	public List getEntityBySql(String sql, List list){
		Session session = this.openSession();
		Transaction tx = null;
		List returnList = new ArrayList();
		try {
			tx = session.beginTransaction();
			session.clear();
			Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			Iterator iterator = list.iterator();
			int i = 0;
			while (iterator.hasNext()) {
				query.setParameter(i, iterator.next());
				i++;
			}
			tx.commit();
			returnList = query.list();

		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return returnList;
	}
	
	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.dao.BaseHibernateDAO#getEntityByPageBySql(java.lang.String, java.util.List)
	 */
	@SuppressWarnings("unchecked")
	public List getEntityByPageBySql(String sql, List list, int begin,
			int numOfEachPage) {
		Session session = this.openSession();
		Transaction tx = null;
		List returnList = new ArrayList();
		try {
			tx = session.beginTransaction();
			session.clear();
			Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			Iterator iterator = list.iterator();
			int i = 0;
			while (iterator.hasNext()) {
				query.setParameter(i, iterator.next());
				i++;
			}
			query.setFirstResult(begin);
			query.setMaxResults(numOfEachPage);
			tx.commit();
			returnList = query.list();

		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return returnList;
	}
	
}
