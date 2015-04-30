package com.sinosoft.gypsymoth.bussiness.system.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.sinosoft.gypsymoth.dao.BaseHibernateDAO;
import com.sinosoft.gypsymoth.pojo.Account;
import com.sinosoft.gypsymoth.pojo.AccountRecord;
import com.sinosoft.gypsymoth.pojo.AccountRole;
import com.sinosoft.gypsymoth.pojo.Client;
import com.sinosoft.gypsymoth.pojo.Clientlog;
import com.sinosoft.gypsymoth.pojo.Role;

public class AccountServiceImpl implements AccountService {
	
	private BaseHibernateDAO baseHibernateDAO;

	public BaseHibernateDAO getBaseHibernateDAO() {
		return baseHibernateDAO;
	}

	public void setBaseHibernateDAO(BaseHibernateDAO baseHibernateDAO) {
		this.baseHibernateDAO = baseHibernateDAO;
	}
	
	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.bussiness.system.service.AccountService#getAccountByPage(com.sinosoft.gypsymoth.pojo.Account, int, int)
	 */
	public List getAccountByPage(Map account, int begin,int numOfEachPage) throws Exception{
		List list = new ArrayList();
		String sql = " select * from( ";
				sql += " select a.* ,c.co_en_name as realname from account a,client c ";
				sql += " where a.account_id = c.account_id ";
				sql += " union ";
				sql += " select a.* ,p.name as realname from account a,person p ";
				sql += " where a.account_id = p.account_id ";
				sql += " ) where ";
				if(account.get("accountName")!=null && !"".equals(account.get("accountName").toString())){
					sql+=" account_name like ? and ";
					list.add("%"+account.get("accountName")+"%");
				}
				if(account.get("accountStatus")!=null && !"".equals(account.get("accountStatus").toString())){
					sql+=" account_status=? and ";
					list.add(account.get("accountStatus"));
				}
				if(account.get("accountType")!=null && !"".equals(account.get("accountType").toString())){
					sql+=" account_type=? and ";
					list.add(account.get("accountType"));
				}
				if(account.get("realName")!=null && !"".equals(account.get("realName").toString())){
					sql+=" realname=? and ";
					list.add(account.get("realName"));
				}
				sql +=" account_id!='1' and ";
				sql +=" 1=1 ";
				sql +=" order by create_date desc ";
		List returnList = baseHibernateDAO.getEntityByPageBySql(sql, list, begin, numOfEachPage);
		return returnList;
	}
	
	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.bussiness.system.service.AccountService#getAllAccountCount(com.sinosoft.gypsymoth.pojo.Account)
	 */
	public int getAllAccountCount(Map account) throws Exception{
		List list = new ArrayList();
		String sql = " select count(*) from( ";
			sql += " select a.* ,c.co_en_name as realname from account a,client c ";
			sql += " where a.account_id = c.account_id ";
			sql += " union ";
			sql += " select a.* ,p.name as realname from account a,person p ";
			sql += " where a.account_id = p.account_id ";
			sql += " ) where ";
			if(account.get("accountName")!=null && !"".equals(account.get("accountName").toString())){
				sql+=" account_name like ? and ";
				list.add("%"+account.get("accountName")+"%");
			}
			if(account.get("accountStatus")!=null && !"".equals(account.get("accountStatus").toString())){
				sql+=" account_status=? and ";
				list.add(account.get("accountStatus"));
			}
			if(account.get("accountType")!=null && !"".equals(account.get("accountType").toString())){
				sql+=" account_type=? and ";
				list.add(account.get("accountType"));
			}
			if(account.get("realName")!=null && !"".equals(account.get("realName").toString())){
				sql+=" realname=? and ";
				list.add(account.get("realName"));
			}
			sql +=" account_id!='1' and ";
			sql +=" 1=1 ";
		int count = baseHibernateDAO.getEntitiesCountBySql(sql, list);
		return count;
	}
	
	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.bussiness.system.service.AccountService#getAllOrgPerson()
	 */
	public List getAllOrgPerson() throws Exception{
		String sql = " select id as id,PARENT_ID as parentid,ORG_NAME as name,'0' as noid,'1' as typeid,2 as isc,2 as isa,2 as isi from ORGANIZATION_LEVEL t  where t.Org_Status ='1' order by to_number(id)";
		List list = new ArrayList();
		List returnListOne = baseHibernateDAO.getEntityBySql(sql, list);
		sql = " select (p.id+500) as id,o.id as parentid,p.name as name,p.id as noid,'2' as typeid,IS_COORDINATOR as isc,IS_AUTHORIZED as isa,IS_INSPECTOR as isi from PERSON p, ORGANIZATION_LEVEL o  where p.org_id = o.id and p.account_id='noid' and o.Org_Status = '1' ";
		List returnListTwo  = baseHibernateDAO.getEntityBySql(sql, list);
		for(int i=0;i<returnListTwo.size();i++){
			returnListOne.add(returnListTwo.get(i));
		}
		return returnListOne;
	}
	
	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.bussiness.system.service.AccountService#getAllRole()
	 */
	public List<Role> getAllRole() throws Exception{
		String sql = "from Role r where r.roleId not in(5) order by r.roleId asc";
		List list = new ArrayList();
		List<Role> returnList = baseHibernateDAO.getEntity(sql, list);
		return returnList;
	}
	
	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.bussiness.system.service.AccountService#savePAccount(com.sinosoft.gypsymoth.pojo.Account, java.lang.String, java.lang.String)
	 */
	public void savePAccount(Account account,String personId,String roleIds) throws Exception{
		baseHibernateDAO.saveEntity(account);
		String sql = " update person set account_id=? where id=? ";
		List list = new ArrayList();
		list.add(account.getAccountId());
		list.add(personId);
		baseHibernateDAO.updateBatchBySql(sql, list);
		saveRoleRight(account,roleIds);
	}
	
	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.bussiness.system.service.AccountService#saveCAccount(com.sinosoft.gypsymoth.pojo.Account, com.sinosoft.gypsymoth.pojo.Client)
	 */
	public void saveCAccount(Account account,Client client) throws Exception{
		baseHibernateDAO.saveEntity(account);
		baseHibernateDAO.saveEntity(client);
		saveRoleRight(account,"5");
	}
	
	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.bussiness.system.service.AccountService#saveRoleRight(com.sinosoft.gypsymoth.pojo.Account, java.lang.String)
	 */
	public void saveRoleRight(Account account,String roleIds) throws Exception{
		String[] roleId = roleIds.split(",");
		for(int i=0;i<roleId.length;i++){
			AccountRole accountRole = new AccountRole();
			accountRole.setRoleId(roleId[i].trim());
			accountRole.setAccountId(account.getAccountId());
			baseHibernateDAO.saveEntity(accountRole);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.bussiness.system.service.AccountService#getRoleHas()
	 */
	public List getRoleHas(String accountId) throws Exception{
		String sql = " select r.role_id,r.role_name ";
				sql += " from Role r ,account_role ar ";
				sql += " where ar.account_id=? and ar.role_id = r.role_id ";
		List list = new ArrayList();
		list.add(accountId);
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;
	}
	
	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.bussiness.system.service.AccountService#getRoleNotHas()
	 */
	public List getRoleNotHas(String accountId) throws Exception{
		String sql = " select r.role_id,r.role_name from Role r ";
				sql += " where r.role_id ";
				sql += " not in(select r.role_id ";
				sql += " from Role r ,account_role ar ";
				sql += " where ar.account_id=? and ar.role_id = r.role_id ";
				sql += " union select r.role_id  from Role r where r.role_id='5') ";
		List list = new ArrayList();
		list.add(accountId);
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;
	}
	
	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.bussiness.system.service.AccountService#getAccountById(java.lang.String)
	 */
	public Account getAccountById(String accountId) throws Exception{
		Account account = (Account)baseHibernateDAO.getEntityById(Account.class, accountId);
		return account;
	}
	
	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.bussiness.system.service.AccountService#updateAccount(com.sinosoft.gypsymoth.pojo.Account, com.sinosoft.gypsymoth.pojo.AccountRecord, java.lang.String)
	 */
	public void updateAccount(Account account,AccountRecord accountRecord,String roleIds) throws Exception{
		//baseHibernateDAO.saveEntity(accountRecord);
		String sql = " delete from account_role where account_id in( ";
		List list = new ArrayList();
		list.add(account.getAccountId());
		baseHibernateDAO.deleteBatchBySql(sql, list);
		saveRoleRight(account,roleIds);
	} 
	
	/**
	 * 修改用户状态:激活、停用
	 * @param account
	 * @param accountRecord
	 * @throws Exception
	 */
	public void updateAccountStatus(AccountRecord accountRecord) throws Exception{
		String sql = " update account set account_status=? where account_id=? ";
		List list = new ArrayList();
		list.add(accountRecord.getAccountStatus());
		list.add(accountRecord.getAccountId());
		baseHibernateDAO.updateBatchBySql(sql, list);
		baseHibernateDAO.saveEntity(accountRecord);
	}
	
	/**
	 * 更新用户密码
	 * @param accountId
	 * @param password
	 * @throws Exception
	 */
	public void updateAccountPassword(String accountId,String password) throws Exception{
		String sql = " update account set password=? where account_id=? ";
		List list = new ArrayList();
		list.add(password);
		list.add(accountId);
		baseHibernateDAO.updateBatchBySql(sql, list);
	}

	/**
	 * 删除用户
	 * @param accountRecord
	 * @param modifyBy
	 * @param modifyDate
	 * @throws Exception
	 */
	public void deleteAccountById(String accountId,String modifyBy,Date modifyDate) throws Exception{
		
		Account account = getAccountById(accountId);
		AccountRecord accountRecord = new AccountRecord();
		accountRecord.setAccountId(accountId);
		accountRecord.setAccountName(account.getAccountName());
		accountRecord.setAccountStatus(account.getAccountStatus());
		accountRecord.setAccountType(account.getAccountType());
		accountRecord.setModifyBy(modifyBy);
		accountRecord.setModifyDate(modifyDate);
		accountRecord.setModifyType(2);

		baseHibernateDAO.saveEntity(accountRecord);
		
		//用户类型为人员，保留人员，只删除用户，插入用户操作记录
		if(account.getAccountType()==1){
			String sql = " update person set account_id='noid' where account_id=? ";
			List list1 = new ArrayList();
			list1.add(accountId);
			baseHibernateDAO.updateBatchBySql(sql, list1);
		}
		//用户类型为客户，删除用户、删除客户、插入用户操作记录和客户操作记录
		else if(account.getAccountType()==2){
			String hql = " from Client c where c.accountId=? ";
			List list2 = new ArrayList();
			list2.add(accountId);
			List<Client> clientList = baseHibernateDAO.getEntity(hql, list2);
			if(clientList.size()>0){
				
				Client client = clientList.get(0);
				System.out.println("-----client.getClientId()-------"+client.getClientId());
				Clientlog clientlog = new Clientlog();
				clientlog.setAccountId(client.getAccountId());
				clientlog.setAddress(client.getAddress());
				clientlog.setBusinessLicense(client.getBusinessLicense());
				clientlog.setCityid(client.getCityid());
				clientlog.setClientId(client.getClientId());
				clientlog.setCoCnName(client.getCoCnName());
				clientlog.setCoEnName(client.getCoEnName());
				clientlog.setCoProperty(client.getCoProperty());
				clientlog.setDes(client.getDes());
				clientlog.setEmail(client.getEmail());
				clientlog.setLegalPerson(client.getLegalPerson());
				clientlog.setModifyBy(modifyBy);
				clientlog.setModifyDate(modifyDate);
				clientlog.setModifyType(2);
				clientlog.setNationId(client.getNationId());
				clientlog.setOnePersonEmail(client.getOnePersonEmail());
				clientlog.setOnePersonFax(client.getOnePersonFax());
				clientlog.setOnePersonName(client.getOnePersonName());
				clientlog.setOnePersonPhone(client.getOnePersonPhone());
				clientlog.setOnePersonTel(client.getOnePersonTel());
				clientlog.setPost(client.getPost());
				clientlog.setProid(client.getProid());
				clientlog.setRegisterEmail(client.getRegisterEmail());
				clientlog.setTel(client.getTel());
				clientlog.setTwoPersonEmail(client.getTwoPersonEmail());
				clientlog.setTwoPersonFax(client.getTwoPersonFax());
				clientlog.setTwoPersonName(client.getTwoPersonName());
				clientlog.setTwoPersonPhone(client.getTwoPersonPhone());
				clientlog.setTwoPersonTel(client.getTwoPersonTel());
				clientlog.setBankaccount(client.getBankaccount());
				clientlog.setVbrk(client.getVbrk());
				baseHibernateDAO.saveEntity(clientlog);
				baseHibernateDAO.deleteEntity(Client.class, client.getClientId());
			}
		}
		
		baseHibernateDAO.deleteEntity(Account.class, accountId);
		String sql2 = " delete from account_role where account_id in( ";
		List list = new ArrayList();
		list.add(accountId);
		baseHibernateDAO.deleteBatchBySql(sql2, list);
		
	}
	
	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.bussiness.system.service.AccountService#getPersonIs(com.sinosoft.gypsymoth.pojo.Account, com.sinosoft.gypsymoth.pojo.AccountRecord, java.lang.String)
	 */
	public List getPersonIs(String accountId) throws Exception{
		String sql="select IS_COORDINATOR as isc,IS_AUTHORIZED as isa,IS_INSPECTOR as isi from person where account_id=? ";
		List list = new ArrayList();
		list.add(accountId);
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;
		
	}
	
	public void updateLog(String accountId) throws Exception {
		List list = new ArrayList();
		String sql = " update account set LOGIN_FAIL_TIMES=0 ,LOGIN_LOCK=0";
		baseHibernateDAO.updateBatchBySql(sql, list);
	}
}
