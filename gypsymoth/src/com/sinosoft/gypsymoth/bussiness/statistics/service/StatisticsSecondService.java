package com.sinosoft.gypsymoth.bussiness.statistics.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface StatisticsSecondService {

	/**	生成目的国统计信息	*/
	public abstract List getSecondlist(HashMap map) throws Exception;
	/**	根据月份列出统计表	*/
	public abstract List getFifthlistToMonth(HashMap map) throws Exception;
	/**	根据季度列出统计表	*/
	public abstract List getFifthlistToQuarter(HashMap map) throws Exception;
	/**	根据年度列出统计表	*/
	public abstract List getFifthlistToYear(HashMap map) throws Exception;
	
	/**	机构列表	*/
	public abstract List getOrgList(Map selectMap) throws Exception;
	/**	港口列表	*/
	public abstract List getPortList(Map selectMap) throws Exception;
	
}
