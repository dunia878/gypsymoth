package com.sinosoft.gypsymoth.bussiness.person.service;

import java.util.List;


import com.sinosoft.gypsymoth.pojo.Account;
import com.sinosoft.gypsymoth.pojo.AccountRole;
import com.sinosoft.gypsymoth.pojo.OpLog;
import com.sinosoft.gypsymoth.pojo.Organization;
import com.sinosoft.gypsymoth.pojo.Person;
import com.sinosoft.gypsymoth.pojo.OrganizationLevel;


public interface PersonService {
	
	
	/**
	 * 根据姓名查询人员
	 * @param name
	 * @return
	 * @throws Exception
	 */
	
	public Person getPersonById(Person person)  throws Exception;	
	
	public abstract void savePerson(Person person) throws Exception;//新增人员
	
	public abstract int getAllPersonCount(String name, String selectOrgid, Person ps) throws Exception;//查询人员的记录总数
	
	public abstract int getAllEnableCount() throws Exception;//查询人员的记录总数
	
	//public abstract List<Person> getAllPersonByPage(int begin,int numOfEachPage) throws Exception;
	
	public abstract List getAllPersonByPage(String Id,String name,String selectOrgid,String isCoordinator,String isAuthorized,String isInspector, int begin, int numOfEachPage ) throws Exception;
	
	public abstract List getAllEnableByPage(String Id,String name,String selectOrgid,String isCoordinator,String isAuthorized,String isInspector, int begin, int numOfEachPage) throws Exception;
	
	public abstract List<Person> getAllPerson(String orgId) throws Exception;
	
	public abstract List<OrganizationLevel> getAllOrganizationLevel(String parentId) throws Exception;

	public abstract List<OrganizationLevel> getAllOrganizationLevel() throws Exception;
	
	public abstract List getPersonById(String id) throws Exception;	
	
	public abstract List getPersonByAccountId(String accountId) throws Exception;	
	
	public abstract List<AccountRole> getRoleById(String id) throws Exception;	
	
	public void updatePerson(Person ps,OpLog ol,String isCoordinator,String isAuthorized,String isInspector) throws Exception;//更新人员信息

	public abstract void deletePerson(String id,Account account,OpLog lo) throws Exception;	
	
	public List select()  throws Exception;
	
	public abstract void updateBatch(String idArray,OpLog ol)throws Exception;
	
	public abstract void updateEnableBatch(String idArray,OpLog ol)throws Exception;		
	
	public Account getAccountById(String accountID)  throws Exception;
	
	public Organization getOrgByorgId(String orgId)  throws Exception;

}
