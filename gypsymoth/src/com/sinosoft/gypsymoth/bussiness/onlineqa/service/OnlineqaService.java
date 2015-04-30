package com.sinosoft.gypsymoth.bussiness.onlineqa.service;

import java.util.List;

import com.sinosoft.gypsymoth.pojo.Account;
import com.sinosoft.gypsymoth.pojo.AccountRole;
import com.sinosoft.gypsymoth.pojo.Onlineqa;



public interface OnlineqaService {
	/**
	 * 新增问题
	 * @param bulletin
	 * @throws Exception
	 */
	public abstract void save(Onlineqa onlineqa) throws Exception;
	public abstract int getAllDataCounts(Onlineqa onlineqa) throws Exception;
	public abstract List<Onlineqa> getAllDataByPages(Onlineqa onlineqa,int begin,int numOfEachPage ) throws Exception;
	public abstract void updateOnlineqa(Onlineqa onlineqa)throws Exception;
	public abstract Onlineqa getOnlineqaById(Long  id)throws Exception;
	public abstract void deleteOnlineqaById(Long  id)throws Exception;
	@SuppressWarnings("unchecked")
	public abstract List getPromory1() throws Exception;
	@SuppressWarnings("unchecked")
	public abstract List getPromory() throws Exception;
	public abstract List<AccountRole> getAccountRole(String accountId) throws Exception;
	public abstract int getAllDataCount(Onlineqa onlineqa) throws Exception;
	public abstract void deletePerson(String id) throws Exception;
	public abstract List<Account> getAccount(String accountId) throws Exception;
	public abstract List<Onlineqa> getAllDataByPage(Onlineqa onlineqa,int begin,int numOfEachPage ) throws Exception;
}
