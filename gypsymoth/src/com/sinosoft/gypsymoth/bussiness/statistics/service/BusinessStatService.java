package com.sinosoft.gypsymoth.bussiness.statistics.service;

import java.util.List;
import java.util.Map;

public interface BusinessStatService {

	/**
	 * 按业务量统计
	 * @param selectMap
	 * @return
	 * @throws Exception
	 */
	public abstract List getBusinessStat(Map selectMap) throws Exception;
	
	/**
	 * 按业务量统计(纵向总计)
	 * @param selectMap
	 * @return
	 * @throws Exception
	 */
	public abstract List getBusinessStatAllNum(Map selectMap) throws Exception;
	
	/**
	 * 合格率统计
	 * @param selectMap
	 * @return
	 * @throws Exception
	 */
	public abstract List getQualifiedStat(Map selectMap) throws Exception;
	
	/**
	 * 合格率统计(纵向总计)
	 * @param selectMap
	 * @return
	 * @throws Exception
	 */
	public abstract List getQualifiedStatAllNum(Map selectMap) throws Exception;

}