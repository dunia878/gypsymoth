package com.sinosoft.gypsymoth.bussiness.statistics.service;

import java.util.List;
import java.util.Map;

public interface ChargesStatService {

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
	 * 按机构等级获取该等级的全部机构
	 * @param level
	 * @return
	 * @throws Exception
	 */
	public abstract List getOrgListByLevel(int level) throws Exception;
	
	/**
	 * 根据机构ID获取港口ID
	 * @param orgIds
	 * @return
	 * @throws Exception
	 */
	public abstract List getPortIdListByOrgId(String orgIds) throws Exception;
	
	/**
	 * 综合统计(按机构)
	 * @param selectMap 查询条件
	 * @param selectType 类型(1:检查状态表, 2:付费状态表, 3:分帐状态表)
	 * @return
	 * @throws Exception
	 */
	public abstract List getAllStatByOrg(Map selectMap,int selectType) throws Exception;
	
	/**
	 * 综合统计(按港口)
	 * @param selectMap 查询条件
	 * @param selectType 类型(1:检查状态表, 2:付费状态表, 3:分帐状态表)
	 * @return
	 * @throws Exception
	 */
	public abstract List getAllStatByPort(Map selectMap,int selectType) throws Exception;
	
	/**
	 * 综合统计(全国)
	 * @param selectMap
	 * @param selectType
	 * @return
	 * @throws Exception
	 */
	public abstract List getAllStat(Map selectMap,int selectType) throws Exception;
	
	/**
	 * 分账明细(按机构)
	 * @param selectMap
	 * @return
	 * @throws Exception
	 */
	public abstract List getLedgerStatByOrg(Map selectMap) throws Exception;
	
	/**
	 * 分账明细(按业务单)
	 * @param selectMap
	 * @return
	 * @throws Exception
	 */
	public abstract List getLedgerStatByApp(Map selectMap) throws Exception;

}