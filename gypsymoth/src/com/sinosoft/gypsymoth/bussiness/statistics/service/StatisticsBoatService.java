package com.sinosoft.gypsymoth.bussiness.statistics.service;

import java.util.List;
import java.util.Map;

public interface StatisticsBoatService {
	/**
	 * 根据条件获取机构列表
	 * @param selectMap
	 * @return
	 * @throws Exception
	 */
	public abstract List getOrgList(Map selectMap) throws Exception;

	/**
	 * 根据条件获取港口列表
	 * @param selectMap
	 * @return
	 * @throws Exception
	 */
	public abstract List getPortList(Map selectMap) throws Exception;
	
	/**
	 * 船舶类型统计 
	 * @param statisticsMap
	 * @return
	 * @throws Exception
	 */	
	public abstract List getTypeStatistics(Map statisticsMap) throws Exception;
	
	/**
	 * 船舶吨位统计 
	 * @param statisticsMap
	 * @return
	 * @throws Exception
	 */	
	public abstract List getTonStatistics(Map statisticsMap) throws Exception;
	
}
