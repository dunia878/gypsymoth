package com.sinosoft.gypsymoth.bussiness.onlineqa.service;

import java.util.ArrayList;
import java.util.List;

import com.sinosoft.gypsymoth.dao.BaseHibernateDAO;
import com.sinosoft.gypsymoth.pojo.Account;
import com.sinosoft.gypsymoth.pojo.AccountRole;
import com.sinosoft.gypsymoth.pojo.Onlineqa;

public class OnlineqaServiceImpl implements OnlineqaService{
	private BaseHibernateDAO baseHibernateDAO;

	public BaseHibernateDAO getBaseHibernateDAO() {
		return baseHibernateDAO;
	}

	public void setBaseHibernateDAO(BaseHibernateDAO baseHibernateDAO) {
		this.baseHibernateDAO = baseHibernateDAO;
	}

	
	/**
	 * 批量删除
	 * @param id
	 * @param log
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void deletePerson(String id) throws Exception {	
		String sql="delete from onlineqa where id in(";
		List list=new ArrayList();
		list.add(id);
		baseHibernateDAO.deleteBatchBySql(sql, list);
	}
	
	
	/**
	 * 分角色
	 * @param accountId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<AccountRole> getAccountRole(String accountId) throws Exception{
		String hql = " from AccountRole ar where ar.accountId=? order by ar.roleId";
		List list = new ArrayList();
		list.add(accountId);
		List<AccountRole> returnList = baseHibernateDAO.getEntity(hql, list);
		return returnList;
	}
	
	
	
	/**
	 * 分角色
	 * @param accountId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Account> getAccount(String accountId) throws Exception{
		String hql = " from Account ar where ar.accountId=? ";
		List list = new ArrayList();
		list.add(accountId);
		List<Account> returnList = baseHibernateDAO.getEntity(hql, list);
		return returnList;
	}
	/**
	 * 新增问题
	 */
	 
	public void save(Onlineqa onlineqa) throws Exception{
		baseHibernateDAO.saveEntity(onlineqa);
		
	}

	@SuppressWarnings("unchecked")
	public List getPromory() throws Exception{
		String sql = " select * from  organization_level";
		List list = new ArrayList();
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;
	}
	/**
	 * 获得机构名称
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getPromory1() throws Exception{
		String sql = " select * from  organizationlevel";
		List list = new ArrayList();
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;
	}
	
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	
	@SuppressWarnings("unchecked")
	public int getAllDataCounts(Onlineqa onlineqa) throws Exception{
		String sql = " select count(*) from Onlineqa e where ";
		List list = new ArrayList();
		if(onlineqa.getState()!=null && !"".equals(onlineqa.getState())){
			sql+=" e.state =? and ";
			list.add(onlineqa.getState());
		}
		if(onlineqa.getClientname()!=null && !"".equals(onlineqa.getClientname())){
			sql+=" e.clientName like ? and   "; 
			list.add("%"+onlineqa.getClientname()+"%");
		}
		
		if(onlineqa.getTitle()!=null && !"".equals(onlineqa.getTitle())){
			sql+=" e.title like ? and  "; 
			list.add("%"+onlineqa.getTitle()+"%");
		}
		sql +=" 1=1  ";
		int count = baseHibernateDAO.getEntitiesCountBySql(sql, list);
		return count;
		
	}
	
	
	
	/**
	 * 客户查看数目
	 */
	@SuppressWarnings("unchecked")
	public int getAllDataCount(Onlineqa onlineqa) throws Exception{
		String sql = " select count(*) from Onlineqa e where ";
		List list = new ArrayList();
		if(onlineqa.getState()!=null && !"".equals(onlineqa.getState())){
			sql+=" e.state =? or ";
			list.add(onlineqa.getState());
		}
		if(onlineqa.getClientname()!=null && !"".equals(onlineqa.getClientname())){
			sql+=" e.clientname =? and ";
			list.add(onlineqa.getClientname());
		}
		
		sql +=" 1=1  order by e.id desc";
		int count = baseHibernateDAO.getEntitiesCountBySql(sql, list);
		return count;
		
	}
	
	/**
	 * 客户查看数目
	 */
	@SuppressWarnings("unchecked")
	public List<Onlineqa> getAllDataByPage(Onlineqa onlineqa,int begin,int numOfEachPage ) throws Exception{
		String sql = " from Onlineqa e where  ";
		List list = new ArrayList();
		if(onlineqa.getState()!=null && !"".equals(onlineqa.getState())){
			sql+=" e.state =? or ";
			list.add(onlineqa.getState());
		}
		if(onlineqa.getClientname()!=null && !"".equals(onlineqa.getClientname())){
			sql+=" e.clientname =? and ";
			list.add(onlineqa.getClientname());
		}
		
		sql +=" 1=1  order by e.id desc";
		List<Onlineqa> onlineqaList = baseHibernateDAO.getEntityByPage(sql,list,begin,numOfEachPage);
		 return onlineqaList;
		
 	}
	
	
	@SuppressWarnings("unchecked")
	public List<Onlineqa> getAllDataByPages(Onlineqa onlineqa,int begin,int numOfEachPage ) throws Exception{
		String sql = " from Onlineqa e where ";
		List list = new ArrayList();
		if(onlineqa.getState()!=null && !"".equals(onlineqa.getState())){
		sql+=" e.state =? and ";
		list.add(onlineqa.getState());
	  }
		if(onlineqa.getClientname()!=null && !"".equals(onlineqa.getClientname())){
			sql+=" e.clientname like ? and  "; 
			list.add("%"+onlineqa.getClientname()+"%");
		}
			
		if(onlineqa.getTitle()!=null && !"".equals(onlineqa.getTitle())){
		sql+=" e.title like ? and  "; 
		list.add("%"+onlineqa.getTitle()+"%");
    	}
		sql +=" 1=1 order by e.id desc ";
		List<Onlineqa> onlineqaList = baseHibernateDAO.getEntityByPage(sql,list,begin,numOfEachPage);
		 return onlineqaList;
		
 	}
	
	
	/**
	 * 根据ID查找Entity
	 */
	public Onlineqa getOnlineqaById(Long  id)throws Exception{
		return (Onlineqa) baseHibernateDAO.getEntityById(Onlineqa.class, id);
		 
	}
	
	
	/**
	 * 回答问题
	 */
	public void updateOnlineqa(Onlineqa onlineqa)throws Exception{
		baseHibernateDAO.updateEntity(onlineqa);
		
	}

	/**
	 * 删除问题
	 */
	public void deleteOnlineqaById(Long  id)throws Exception{
		baseHibernateDAO.deleteEntity(Onlineqa.class, id);
	}

}
