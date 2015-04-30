package com.sinosoft.gypsymoth.bussiness.examine.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.sinosoft.gypsymoth.pojo.Account;
import com.sinosoft.gypsymoth.pojo.Business;
import com.sinosoft.gypsymoth.pojo.ExamPort;
import com.sinosoft.gypsymoth.pojo.Examinelog;
import com.sinosoft.gypsymoth.pojo.Operator;



public interface ExamineService {
	

	public abstract int getAllBussinessCount(HashMap map) throws Exception; //查询需要检查的记录总数
	
	public abstract List getAllBussinessByPage(HashMap map, int begin, int numOfEachPage)throws Exception;
	
	public abstract void savePort(ExamPort ep) throws Exception;//停泊的港口
	
	public abstract List getBussinessById(int id) throws Exception;	//查看业务详细信息
	
	public abstract Business getBusinessObject(Long id)  throws Exception;//得到修改前业务实体对象
	
	public abstract List getDesCountryById(int id)throws Exception;//查看目的国及其港口
	
	public abstract void saveExamine(Examinelog el,Operator op,String[] nationid,String appId,String[] portName,Date[] stopTime,String[] anationid,String[] aportName,Date[] astopTime) throws Exception;//上传检查记录
	
	public abstract List getPersonByAccountId(String accountId) throws Exception;	//根据帐号查询人员信息
	
	public abstract void updateBusiness(Business bus) throws Exception;//更新业务信息
	
	// **********************   add by guodingjun in 2011-04-06 ***************************
	// **********************   add star ***************************
	
	/**
	 * 根据businessid查询检查结果
	 */
	public abstract List getExamineByBusinessId(String businessId) throws Exception;
	
	
	/**
	 * 保存检查结果
	 * @param el
	 * @throws Exception
	 */
	public abstract void saveExamine(Examinelog el) throws Exception;
	
	/**
	 * 修改检查结果
	 */
	public abstract void updateExaminelog(Examinelog examinelog) throws Exception;
	
	/**
	 * 根据businessid查询Examport
	 * @param businessId
	 * @return
	 * @throws Exception
	 */
	public abstract List getExamPortByBusinesssId(String businessId) throws Exception;
	
	/**
	 * 保存检查结果中的停靠港口
	 * @param el
	 * @throws Exception
	 */
	public void saveExamPort(String[] nationid,String appId,String[] portName,Date[] stopTime,String[] anationid,String[] aportName,Date[] astopTime) throws Exception;
	
	/**
	 * 修改检查结果
	 */
	public abstract void deleteExamPort(String businessId) throws Exception;
	
	// **********************   add end ****************************
}
