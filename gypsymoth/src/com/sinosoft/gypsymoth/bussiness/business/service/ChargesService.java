package com.sinosoft.gypsymoth.bussiness.business.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sinosoft.gypsymoth.pojo.Opaccount;
import com.sinosoft.gypsymoth.pojo.Payment;

public interface ChargesService {

	public abstract List getPaymentByPage(Map selectMap,int begin, int numOfEachPage)
			throws Exception;

	public abstract int getAllPaymentCount(Map selectMap) throws Exception;
	
	public abstract List getPaymentByBusinessid(Integer businessid) throws Exception;

	public abstract void updatePayment(Payment payment) throws Exception;
	
	/**	根据业务表ID得到收费信息	*/
	public Payment getPaymentByBusinessID(Long businessid) throws Exception;
	
	/**	根据信息得到未付费的业务数量	*/
	public abstract int getNotPaymentsCount(HashMap map) throws Exception;
	
	public abstract void saveOpAccount(Opaccount opaccount) throws Exception;
	
	public abstract List getOpAccount(Integer businessid, int f) throws Exception;
}