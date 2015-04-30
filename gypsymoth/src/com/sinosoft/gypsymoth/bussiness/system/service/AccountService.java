package com.sinosoft.gypsymoth.bussiness.system.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.sinosoft.gypsymoth.pojo.Account;
import com.sinosoft.gypsymoth.pojo.AccountRecord;
import com.sinosoft.gypsymoth.pojo.Client;
import com.sinosoft.gypsymoth.pojo.Role;

public interface AccountService {

	public abstract List getAccountByPage(Map account, int begin,
			int numOfEachPage) throws Exception;

	public abstract int getAllAccountCount(Map account) throws Exception;

	/**
	 * 获取机构人员列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public abstract List getAllOrgPerson() throws Exception;

	/**
	 * 获取角色列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public abstract List<Role> getAllRole() throws Exception;

	/**
	 * 添加人员用户
	 * 
	 * @param account
	 * @param personId
	 * @param roleIds
	 * @throws Exception
	 */
	public abstract void savePAccount(Account account, String personId,
			String roleIds) throws Exception;

	/**
	 * 添加客户用户
	 * 
	 * @param account
	 * @param client
	 * @throws Exception
	 */
	public abstract void saveCAccount(Account account, Client client)
			throws Exception;

	/**
	 * 保存用户角色
	 * 
	 * @param account
	 * @param roleIds
	 * @throws Exception
	 */
	public abstract void saveRoleRight(Account account, String roleIds)
			throws Exception;

	/**
	 * 获取用户拥有角色列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public abstract List getRoleHas(String accountId) throws Exception;

	/**
	 * 获取用户没有的角色列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public abstract List getRoleNotHas(String accountId) throws Exception;

	/**
	 * 根据ID获取用户信息
	 * 
	 * @param accountId
	 * @return
	 * @throws Exception
	 */
	public abstract Account getAccountById(String accountId) throws Exception;

	/**
	 * 更新用户角色信息
	 * 
	 * @param account
	 * @param accountRecord
	 * @param roleIds
	 * @throws Exception
	 */
	public abstract void updateAccount(Account account,
			AccountRecord accountRecord, String roleIds) throws Exception;

	/**
	 * 修改用户状态:激活、停用
	 * 
	 * @param account
	 * @param accountRecord
	 * @throws Exception
	 */
	public abstract void updateAccountStatus(AccountRecord accountRecord)
			throws Exception;

	/**
	 * 更新用户密码
	 * 
	 * @param accountId
	 * @param password
	 * @throws Exception
	 */
	public abstract void updateAccountPassword(String accountId, String password)
			throws Exception;

	/**
	 * 删除用户
	 * 
	 * @param accountRecord
	 * @param modifyBy
	 * @param modifyDate
	 * @throws Exception
	 */
	public abstract void deleteAccountById(String accountId, String modifyBy,
			Date modifyDate) throws Exception;

	/**
	 * 根据用户ID查询人员的检查员、协调员、授权签字人状态
	 * @param accountId
	 * @return
	 * @throws Exception
	 */
	public abstract List getPersonIs(String accountId) throws Exception;
	
	/**
	 * 用户解锁
	 * @param accountId
	 * @return
	 * @throws Exception
	 */
	public abstract void updateLog(String accountId) throws Exception;
}