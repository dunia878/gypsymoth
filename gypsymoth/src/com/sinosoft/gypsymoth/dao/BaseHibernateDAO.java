package com.sinosoft.gypsymoth.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseHibernateDAO {
	
	
	
	public String getSequenceFromSql(String sql);

	/**
	 * 查询
	 * 
	 * @param hql
	 *            查询语句
	 * @param list
	 *            参数列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract List getEntity(String hql, List list);

	/**
	 * 分页查询
	 * 
	 * @param hql
	 *            查询语句
	 * @param list
	 *            参数列表
	 * @param begin
	 *            开始数
	 * @param numOfEachPage
	 *            每页个数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract List getEntityByPage(String hql, List list, int begin,
			int numOfEachPage);

	
	/**
	 * 分页查询 
	 * 
	 * @param hql
	 *            查询语句
	 * @param list
	 *            参数列表
	 * @param begin
	 *            开始数
	 * @param numOfEachPage
	 *            每页个数
	 * @return
	 */
	public List getEntities(String hql, List list, int begin,
			int numOfEachPage);
	
	
	/**
	 * 查询数目
	 * 
	 * @param sql
	 *            sql语句
	 * @param list
	 *            参数列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract int getEntitiesCountBySql(String sql, List list);

	
	/**
	 * 查询内容
	 * @param hql
	 *            查询语句
	 * @param list
	 *            参数列表
	 * @param begin
	 *            开始数
	 * @param numOfEachPage
	 *            每页个数
	 * @return
	 */
	public List getEntitiesBySql(String sql, List list,int begin,
			int numOfEachPage) ;
	
	/**
	 * 根据ID删除实体
	 * 
	 * @param cla 实体类
	 * @param id 主键ID
	 */
	@SuppressWarnings("unchecked")
	public abstract void deleteEntity(Class cla, Serializable id);

	/**
	 * 添加实体
	 * 
	 * @param object 实体对象
	 */
	public abstract void saveEntity(Object object) throws Exception;

	/**
	 * 根据ID获取实体
	 * 
	 * @param cla 实体类
	 * @param id 主键ID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract Object getEntityById(Class cla, Serializable id);

	/**
	 * 更新实体对象
	 * 
	 * @param object 实体对象
	 */
	public abstract void updateEntity(Object object);

	/**
	 * 批量删除
	 * 
	 * @param sql
	 *            sql语句：例如:"delete from person where id in("
	 * @param list
	 *            删除条件列表
	 */
	@SuppressWarnings("unchecked")
	public abstract void deleteBatchBySql(String sql, List list);

	/**
	 * 批量更新
	 * 
	 * @param sql
	 *            sql语句
	 * @param list
	 *            参数列表
	 */
	@SuppressWarnings("unchecked")
	public abstract void updateBatchBySql(String sql, List list);
	
	/**
	 * 原生SQL查询
	 * @param sql
	 * @param list
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract List getEntityBySql(String sql, List list);
	
	/**
	 * 原生SQL分页查询
	 * @param sql
	 * @param list
	 * @param begin
	 * @param numOfEachPage
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract List getEntityByPageBySql(String sql, List list, int begin,
			int numOfEachPage);
	
	public abstract List updateBatchByHql(String hql, List list) ;

}