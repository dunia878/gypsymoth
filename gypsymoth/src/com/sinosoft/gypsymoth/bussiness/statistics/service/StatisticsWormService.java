package com.sinosoft.gypsymoth.bussiness.statistics.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface StatisticsWormService {
	
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
	 * 虫态数量统计 
	 * @param statisticsMap
	 * @return
	 * @throws Exception
	 */	
	public abstract List getWormStatistics(Map statisticsMap) throws Exception;
	
}
