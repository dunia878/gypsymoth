package com.sinosoft.gypsymoth.bussiness.business.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.sinosoft.gypsymoth.pojo.Business;

public interface SelectService {

	/**
	 * 业务查询分页列表
	 * 
	 * @param selectMap
	 * @param begin
	 * @param numOfEachPage
	 * @return
	 * @throws Exception
	 */
	public abstract List getSelectByPage(Map selectMap, int begin,
			int numOfEachPage) throws Exception;

	/**
	 * 业务查询列表总数
	 * 
	 * @param selectMap
	 * @return
	 * @throws Exception
	 */
	public abstract int getSelectCount(Map selectMap) throws Exception;

	/**
	 * 根据业务编号获取具体业务信息
	 * 
	 * @param businessid
	 * @return
	 * @throws Exception
	 */
	public abstract List getSelectByBusinessid(Integer businessid)
			throws Exception;

	public abstract List getExamPort(String appno) throws Exception;
	
	public abstract List getOperator(BigDecimal businessid) throws Exception;
	
	public abstract List getDesCountry(BigDecimal businessid) throws Exception;
	
	public abstract String getorgName(BigDecimal businessid) throws Exception;
	
	public abstract void updateSelect(Business business) throws Exception;
	
	public abstract List getBusinessname(String accountId) throws Exception;
	
	public abstract List getOrgList(Map selectMap) throws Exception;
	
	public abstract void updateQualified(Integer businessid,Integer qualified) throws Exception;
	
	public abstract List getOrgIdList(String id) throws Exception;
	
	public abstract List getBusinessOrg(Integer Bussinessid) throws Exception;
	
	public abstract void deleteBusiness(Long id);
}