package com.sinosoft.gypsymoth.bussiness.login.service;

import java.util.ArrayList;
import java.util.List;

import com.sinosoft.gypsymoth.pojo.Account;
import com.sinosoft.gypsymoth.pojo.Client;
import com.sinosoft.gypsymoth.pojo.Person;
import com.sinosoft.gypsymoth.pojo.Right;

public interface LoginService {

	/**
	 * 根据用户名查询用户
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public abstract List<Account> getAccountByName(String name)
			throws Exception;

	/**
	 * 更改用户锁定状态
	 * @param account
	 * @throws Exception
	 */
	public abstract void updateAccountLogin_lock(Account account)
			throws Exception;

	
	public abstract void updateAccount(Account account) throws Exception;
	
	/**
	 * 根据用户ID获取用户拥有的权限
	 * @param accountId
	 * @return
	 * @throws Exception
	 */
	public abstract List<Right> getRightByAccountId(String accountId,String parentid) throws Exception;

	
	/**  通过account_id得到person  */
	public abstract Person getPersonByAccountID(String account_id) throws Exception;
	
	/** 通过account_id得到client  */
	public abstract Client getClientByAccountID(String account_id) throws Exception;
	
	
	/**
	 * 通过person_id得到所属机构信息
	 */
	public abstract List getInfomationByPersonID(String person_id) throws Exception;
}