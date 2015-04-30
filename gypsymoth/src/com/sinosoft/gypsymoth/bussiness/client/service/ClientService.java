package com.sinosoft.gypsymoth.bussiness.client.service;

import java.util.HashMap;
import java.util.List;

import com.sinosoft.gypsymoth.pojo.Account;
import com.sinosoft.gypsymoth.pojo.City;
import com.sinosoft.gypsymoth.pojo.Client;
import com.sinosoft.gypsymoth.pojo.Clients;
import com.sinosoft.gypsymoth.pojo.Nation;
import com.sinosoft.gypsymoth.pojo.Promary;

public interface ClientService {
	
	//获得客户信息
	public abstract List<Client> getAllDataByPage(int begin,int numOfEachPage )throws Exception;
	//得到列表总数
	public abstract int getAllDataCounts(Client client) throws Exception;
	//由ID获得客户信息
	public abstract Client getClientById(Long  clientId)throws Exception;
	//获得用户信息
	public abstract Account getAccountByAccountID(String  accountID)throws Exception;
	//客户列表
	public abstract List<Client> getClientByPage(Client client,int begin, int numOfEachPage)throws Exception;
	//获得城市列表
	@SuppressWarnings("unchecked")
	public abstract List getCity(Integer proId) throws Exception;
	//获得国家列表
	@SuppressWarnings("unchecked")
	public abstract List getNation() throws Exception;
	//获得省份列表
	@SuppressWarnings("unchecked")
	public abstract List getPromory() throws Exception;
	//修改客户信息
	public abstract void updateClient(Client client) throws Exception;
	//删除客户信息
	public abstract void deleteClientById(Long  clientId)throws Exception;
	//有ID获得国家信息
	public abstract Nation getNationById(String  nationId)throws Exception;
	//批量删除客户
	public abstract void deleteClient(String clientId) throws Exception;
	//查找城市
	public abstract List getCityByPromaryID(String clientId) throws Exception;
	public abstract int getNumByClient(String  clientId) throws Exception ;
	public abstract List<Client> getClientIds() throws Exception;
	public abstract int getAllDataCountss(HashMap map) throws Exception;
	public abstract List getClientByPages(int begin, int numOfEachPage,HashMap map) throws Exception;
	public abstract List getclientIdbyAccountId(String accountId) throws Exception;
	public abstract List getClientByPage(int begin, int numOfEachPage,HashMap map) throws Exception;
	public abstract int getAllData(HashMap map) throws Exception;
	public abstract void saveClients(Client client) throws Exception;
	/**  通过account_id得到client */
	public abstract Client getClientByAccountID(String account_id) throws Exception;
	
	
	/**	根据client_id得到客户及客户下属客户的列表	*/
	public abstract List getClientListById(Long id) throws Exception;
	/**	得到所有的客户列表	*/
	public abstract List getClientList() throws Exception;
	public abstract Promary getPromoryById(Long proid) throws Exception;
	public abstract City getCityById(Long cityid) throws Exception;
	public abstract List getChild(Client client) throws Exception; 
	/** 得到clients_sequ */
	public String getClientsSequ() throws Exception;
	
}