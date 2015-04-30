package com.sinosoft.gypsymoth.bussiness.organization.service;

import java.util.ArrayList;
import java.util.List;

import com.sinosoft.gypsymoth.pojo.City;
import com.sinosoft.gypsymoth.pojo.OpLog;
import com.sinosoft.gypsymoth.pojo.OrgLog;
import com.sinosoft.gypsymoth.pojo.Organization;
import com.sinosoft.gypsymoth.pojo.OrganizationLevel;
import com.sinosoft.gypsymoth.pojo.OrganizationPort;
import com.sinosoft.gypsymoth.pojo.Port;
import com.sinosoft.gypsymoth.pojo.ProCityPort;
import com.sinosoft.gypsymoth.pojo.Promary;



public interface OrganizationService {
	
	public String getPortNo(String orgId,String cityId)throws Exception;//得到港口ID	
	
	public Port getPortbyId(Port port) throws Exception;
	
	public OrganizationLevel getOrgLevelbyId(String id) throws Exception;
	
	public abstract void savePort(String[]provinceId, String[]citydivId, String orgId,String[] portName,String[] portSname)throws Exception;
	
	public abstract void saveOrganization(Organization org,OrganizationLevel ol,String[] provinceId,String[] citydivId,String[] portName,String[] portSname,Port po,OrganizationPort op,ProCityPort pcp) throws Exception;//新增机构
	
	public abstract int getAllOrganizationCount() throws Exception;//查询机构的记录总数
	
	public abstract int getAllEnableCount() throws Exception;//查询启用机构的记录总数
	
	public abstract List<Promary> getAllProvince() throws Exception;
	
	public abstract List getAllPort(String orgId) throws Exception;
	
	public abstract List<City> getAllCity() throws Exception;
	
	public List findCityByProvinceId(String id) throws Exception;  
	
	public abstract List getAllOrganizationByPage(int begin,int numOfEachPage ) throws Exception;//分页查询所有机构列表
	
	public abstract List getAllEnableByPage(int begin,int numOfEachPage ) throws Exception;//分页查询停用的机构列表

	public abstract List<OrganizationLevel> getAllOrganizationLevel() throws Exception;
	
	public abstract List getAllOrganization(String oid) throws Exception;

	public abstract List<Organization> getOrganizationById(String oid) throws Exception;
	
	public abstract void updateOrganization(Organization org,OrganizationLevel ol) throws Exception;
	
	public abstract void saveLOG(OrgLog log) throws Exception;//保存日志
	
	public abstract void deleteOrganization(String[] idArray) throws Exception;
	
	public Organization getOrganizationbyId(Organization org) throws Exception;	
	
	public List selectPort() throws Exception;

	public abstract int getAllOrgLevelCount() throws Exception;//查询机构数的记录总数
	
	public abstract List getPortById(String oid) throws Exception;
	
	public abstract void updatePort(Port port) throws Exception;
	
	public abstract void deletePort(String portId) throws Exception;
	
	public  void updateBatch(String idArray) throws Exception; 
	 
	public  void updateEnableBatch(String idArray) throws Exception;
	
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
	
	
	/**  得到父机构ID为parentid的LIST */
	public abstract List getOranizationLevelByParentID(String parentid) throws Exception;
	
	/**	得到港口城市列表	*/
	public abstract List getCityPortList() throws Exception;
	
	
	/**	得到机构印章	*/
	public abstract List getStampByorgID(String orgid) throws Exception;
	
}
