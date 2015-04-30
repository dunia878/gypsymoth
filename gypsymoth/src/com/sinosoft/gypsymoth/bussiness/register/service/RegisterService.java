package com.sinosoft.gypsymoth.bussiness.register.service;

import java.util.List;

import com.sinosoft.gypsymoth.pojo.Account;
import com.sinosoft.gypsymoth.pojo.AccountRole;
import com.sinosoft.gypsymoth.pojo.Client;

public interface RegisterService {

	/**
	 * 获取国家列表
	 * @return
	 * @throws Exception
	 */
	public abstract List getNation() throws Exception;

	/**
	 * 获取省份列表
	 * @return
	 * @throws Exception
	 */
	public abstract List getPromory() throws Exception;

	/**
	 * 根据省份ID获取城市列表
	 * @param proId
	 * @return
	 * @throws Exception
	 */
	public abstract List getCity(Integer proId) throws Exception;

	/**
	 * 保存注册用户
	 * @param account
	 * @param client
	 * @throws Exception
	 */
	public abstract void saveClient(Account account, Client client,
			AccountRole accountRole) throws Exception;

	/**
	 * 激活
	 * @param accountId
	 * @throws Exception
	 */
	public abstract void updateAccountStatus(String accountId) throws Exception;

	/**
	 * 检查用户名唯一性
	 * @param accountName
	 * @return
	 * @throws Exception
	 */
	public abstract List getAccountByName(String accountName) throws Exception;
	
	/**
	 * 获取未激活用户列表
	 * @return
	 * @throws Exception
	 */
	public abstract List getAccountByStatus() throws Exception;
	
	/**
	 * 根据用户名获取用户注册邮箱
	 * @param accountName
	 * @return
	 * @throws Exception
	 */
	public abstract List getAccountMail(String accountName) throws Exception;
	
	/**
	 * 得到全部用户列表
	 * @return
	 * @throws Exception
	 */
	public abstract List getAllAccount() throws Exception;
	
	/**
	 * 根据省ID市ID查出港口信息
	 * <br/><br/>由省ID和市ID可以查出对应的机构信息，由机构_港口表可以查到最下级城市和港口的对应关系信息，
	 * 关联可得省市港口的对应关系
	 * @param proid		省ID 
	 * @param cityid	市ID
	 * @return	港口信息
	 * @throws Exception 
	 */
	public abstract List getPortFromORG(Integer proid,Integer cityid) throws Exception;


	/** 根据港口信息查出省市ID  */
	public abstract List getPromaryAndCityByPortID(String portid) throws Exception;
	
	/**  根据港口ID查出省市港口名称 */
	public abstract List getPortProCity(String portid) throws Exception;
	
	/**
	 * 根据省市港口ID查出省市港口名
	 * 
	 * 
	 */
	public abstract List getPCPNameByPortID(String proid,String cityid,String portid) throws Exception;
	
	
	/**	通过港口省份城市表找到所有包含港口的省份列表	*/
	public abstract List getPromarylistHavePort() throws Exception;
	/**	通过省份找到所有包含港口的城市列表	*/
	public abstract List getCitylistHavePort(String proid) throws Exception;
	/**	通过省份城市找到所有包含港口列表	*/
	public abstract List getPortlistByProandCity(String proid,String cityid) throws Exception;
	/**	通过港口ID得到对应的城市和省		*/
	public abstract List getPromaryCity(String portid) throws Exception;
	
	/**	通过港口ID得到对应的省市及同城港口列表	*/
	public abstract List getCertificateProCityPort(String portid) throws Exception;
}