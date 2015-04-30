package com.sinosoft.gypsymoth.bussiness.statistics.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sinosoft.gypsymoth.dao.BaseHibernateDAO;

public class BusinessStatServiceImpl implements BusinessStatService {

	private BaseHibernateDAO baseHibernateDAO;

	public BaseHibernateDAO getBaseHibernateDAO() {
		return baseHibernateDAO;
	}

	public void setBaseHibernateDAO(BaseHibernateDAO baseHibernateDAO) {
		this.baseHibernateDAO = baseHibernateDAO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sinosoft.gypsymoth.bussiness.statistics.service.BusinessStatService#getBusinessStat(java.util.Map)
	 */
	public List getBusinessStat(Map selectMap) throws Exception {
		int yearBegin = Integer.parseInt(selectMap.get("yearBegin").toString());
		int quarterBegin = Integer.parseInt(selectMap.get("quarterBegin")
				.toString());
		int monthBegin = Integer.parseInt(selectMap.get("monthBegin")
				.toString());
		int yearEnd = Integer.parseInt(selectMap.get("yearEnd").toString());
		int quarterEnd = Integer.parseInt(selectMap.get("quarterEnd")
				.toString());
		int monthEnd = Integer.parseInt(selectMap.get("monthEnd").toString());
		int cycleTime = Integer.parseInt(selectMap.get("cycleTime").toString());
		int begin = 0;
		int end = 0;
		String format = "";
		String valueBegin = "";
		String valueEnd = "";
		List list = new ArrayList();
		String sql = " select org_name ,id";

		// 按年统计
		if (cycleTime == 1) {
			int num = 0;
			format = "yyyy";
			valueBegin = Integer.toString(yearBegin);
			valueEnd = Integer.toString(yearEnd);
			for (int i = yearBegin; i <= yearEnd; i++) {
				sql += " ,max(case appdate when '" + i
						+ "' then num else 0 end) as a" + num + " ";
				num++;
			}
		}
		// 按季统计
		else if (cycleTime == 2) {
			int num = 0;
			format = "yyyy-q";
			valueBegin = Integer.toString(yearBegin) + "-"
					+ Integer.toString(quarterBegin);
			valueEnd = Integer.toString(yearEnd) + "-"
					+ Integer.toString(quarterEnd);
			for (int i = yearBegin; i <= yearEnd; i++) {
				if (i == yearEnd && i == yearBegin) {// 在同一年度
					for (int j = quarterBegin; j <= quarterEnd; j++) {
						sql += " ,max(case appdate when '" + i + "-" + j
								+ "' then num else 0 end) as a" + num + " ";
						num++;
					}
				} else if (i != yearEnd && i == yearBegin) {// 跨年度，第一年度
					for (int j = quarterBegin; j <= 4; j++) {
						sql += " ,max(case appdate when '" + i + "-" + j
								+ "' then num else 0 end) as a" + num + " ";
						num++;
					}
				} else if (i != yearEnd && i != yearBegin) {// 跨年度，中间年度
					for (int j = 1; j <= 4; j++) {
						sql += " ,max(case appdate when '" + i + "-" + j
								+ "' then num else 0 end) as a" + num + " ";
						num++;
					}
				} else {// 跨年度，最后年度
					for (int j = 1; j <= quarterEnd; j++) {
						sql += " ,max(case appdate when '" + i + "-" + j
								+ "' then num else 0 end) as a" + num + " ";
						num++;
					}
				}

			}
		}
		// 按月统计
		else {
			int num = 0;
			format = "yyyy-mm";
			valueBegin = Integer.toString(yearBegin) + "-"
					+ Integer.toString(monthBegin);
			valueEnd = Integer.toString(yearEnd) + "-"
					+ Integer.toString(monthEnd);
			for (int i = yearBegin; i <= yearEnd; i++) {

				if (i == yearEnd && i == yearBegin) {// 在同一年度
					for (int j = monthBegin; j <= monthEnd; j++) {
						sql += " ,max(case appdate when '" + i + "-" + j
								+ "' then num else 0 end) as a" + num + " ";
						num++;
					}
				} else if (i != yearEnd && i == yearBegin) {// 跨年度，第一年度
					for (int j = monthBegin; j <= 12; j++) {
						sql += " ,max(case appdate when '" + i + "-" + j
								+ "' then num else 0 end) as a" + num + " ";
						num++;
					}
				} else if (i != yearEnd && i != yearBegin) {// 跨年度，中间年度
					for (int j = 1; j <= 12; j++) {
						sql += " ,max(case appdate when '" + i + "-" + j
								+ "' then num else 0 end) as a" + num + " ";
						num++;
					}
				} else {// 跨年度，最后年度
					for (int j = 1; j <= monthEnd; j++) {
						sql += " ,max(case appdate when '" + i + "-" + j
								+ "' then num else 0 end) as a" + num + " ";
						num++;
					}
				}
			}
		}

		sql += " ,sum(num) as all_num ";
		sql += " from( ";
		if (selectMap.get("selectPortid") != null
				&& !"".equals(selectMap.get("selectPortid").toString())) {
			sql += " select m2.port_name as org_name, count(m1.org_name) as num, appdate,m2.id ";
		} else {
			sql += " select m2.org_name, count(m1.org_name) as num, appdate,m2.id";
		}
		if (selectMap.get("selectAll") != null
				&& !"".equals(selectMap.get("selectAll").toString())) {
			sql += " from (select distinct '检验公司' as org_name, b.id, to_char(b.appdate,'"
					+ format + "') as appdate ";
		} else {
			sql += " from (select distinct o.org_name,o.id, a.businessid, to_char(b.appdate,'"
					+ format + "') as appdate ";
		}
		if (selectMap.get("selectPortid") != null
				&& !"".equals(selectMap.get("selectPortid").toString())) {
			sql += " ,b.portid ";
		}
		sql += " from business b ";
		if (selectMap.get("selectAll") == null
				|| "".equals(selectMap.get("selectAll").toString())) {
			sql += " left join ASSIGNMENT a on b.id = a.businessid ";
			sql += " left join organization_level o on a.orgto = o.id ";
		}
		sql += " where 1 = 1 ";
		sql += " and to_char(b.appdate,'" + format + "')>= '" + valueBegin
				+ "' ";
		sql += " and to_char(b.appdate,'" + format + "')<= '" + valueEnd + "' ";
		if (selectMap.get("selectOrgid") != null
				&& !"".equals(selectMap.get("selectOrgid").toString())) {
			sql += " and a.orgto in(" + selectMap.get("selectOrgid") + ") ";
		}
		if (selectMap.get("selectPortid") != null
				&& !"".equals(selectMap.get("selectPortid").toString())) {
			sql += " and b.portid in(" + selectMap.get("selectPortid") + ") ";
		}
		sql += " and b.businessstate>2 ";
		sql += " )m1 ";
		if (selectMap.get("selectOrgid") != null
				&& !"".equals(selectMap.get("selectOrgid").toString())) {
			sql += " full join ";
			sql += " organization_level m2 on m1.org_name=m2.org_name ";
			sql += " where m2.id in(" + selectMap.get("selectOrgid") + ") ";
		}
		if (selectMap.get("selectPortid") != null
				&& !"".equals(selectMap.get("selectPortid").toString())) {
			sql += " full join ";
			sql += " port m2 on m2.id=m1.portid ";
			sql += " where m2.id in(" + selectMap.get("selectPortid") + ") ";
		}
		if (selectMap.get("selectAll") != null
				&& !"".equals(selectMap.get("selectAll").toString())) {
			sql += " left join ";
			sql += " organization_level m2 on m2.org_name=m1.org_name ";
		}
		if (selectMap.get("selectPortid") != null
				&& !"".equals(selectMap.get("selectPortid").toString())) {
			sql += " group by appdate,m2.id, m2.port_name ";
		} else {
			sql += " group by appdate,m2.id, m2.org_name ";
		}
		sql += " order by appdate ";
		sql += " ) ";
		sql += " where org_name is not null ";
		sql += " group by org_name,id ";
		sql += " order by to_number(id) ";

		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;

	}

	/**
	 * 按业务量统计(纵向总计)
	 * 
	 * @param selectMap
	 * @return
	 * @throws Exception
	 */
	public List getBusinessStatAllNum(Map selectMap) throws Exception {
		int yearBegin = Integer.parseInt(selectMap.get("yearBegin").toString());
		int quarterBegin = Integer.parseInt(selectMap.get("quarterBegin")
				.toString());
		int monthBegin = Integer.parseInt(selectMap.get("monthBegin")
				.toString());
		int yearEnd = Integer.parseInt(selectMap.get("yearEnd").toString());
		int quarterEnd = Integer.parseInt(selectMap.get("quarterEnd")
				.toString());
		int monthEnd = Integer.parseInt(selectMap.get("monthEnd").toString());
		int cycleTime = Integer.parseInt(selectMap.get("cycleTime").toString());
		int begin = 0;
		int end = 0;
		String format = "";
		String valueBegin = "";
		String valueEnd = "";
		List list = new ArrayList();
		String sql = " select 'name' as name ";

		// 按年统计
		if (cycleTime == 1) {
			int num = 0;
			format = "yyyy";
			valueBegin = Integer.toString(yearBegin);
			valueEnd = Integer.toString(yearEnd);
			for (int i = yearBegin; i <= yearEnd; i++) {
				sql += " ,max(case appdate when '" + i
						+ "' then num else 0 end) as a" + num + " ";
				num++;
			}
		}
		// 按季统计
		else if (cycleTime == 2) {
			int num = 0;
			format = "yyyy-q";
			valueBegin = Integer.toString(yearBegin) + "-"
					+ Integer.toString(quarterBegin);
			valueEnd = Integer.toString(yearEnd) + "-"
					+ Integer.toString(quarterEnd);
			for (int i = yearBegin; i <= yearEnd; i++) {
				if (i == yearEnd && i == yearBegin) {// 在同一年度
					for (int j = quarterBegin; j <= quarterEnd; j++) {
						sql += " ,max(case appdate when '" + i + "-" + j
								+ "' then num else 0 end) as a" + num + " ";
						num++;
					}
				} else if (i != yearEnd && i == yearBegin) {// 跨年度，第一年度
					for (int j = quarterBegin; j <= 4; j++) {
						sql += " ,max(case appdate when '" + i + "-" + j
								+ "' then num else 0 end) as a" + num + " ";
						num++;
					}
				} else if (i != yearEnd && i != yearBegin) {// 跨年度，中间年度
					for (int j = 1; j <= 4; j++) {
						sql += " ,max(case appdate when '" + i + "-" + j
								+ "' then num else 0 end) as a" + num + " ";
						num++;
					}
				} else {// 跨年度，最后年度
					for (int j = 1; j <= quarterEnd; j++) {
						sql += " ,max(case appdate when '" + i + "-" + j
								+ "' then num else 0 end) as a" + num + " ";
						num++;
					}
				}

			}
		}
		// 按月统计
		else {
			int num = 0;
			format = "yyyy-mm";
			valueBegin = Integer.toString(yearBegin) + "-"
					+ Integer.toString(monthBegin);
			valueEnd = Integer.toString(yearEnd) + "-"
					+ Integer.toString(monthEnd);
			for (int i = yearBegin; i <= yearEnd; i++) {

				if (i == yearEnd && i == yearBegin) {// 在同一年度
					for (int j = monthBegin; j <= monthEnd; j++) {
						sql += " ,max(case appdate when '" + i + "-" + j
								+ "' then num else 0 end) as a" + num + " ";
						num++;
					}
				} else if (i != yearEnd && i == yearBegin) {// 跨年度，第一年度
					for (int j = monthBegin; j <= 12; j++) {
						sql += " ,max(case appdate when '" + i + "-" + j
								+ "' then num else 0 end) as a" + num + " ";
						num++;
					}
				} else if (i != yearEnd && i != yearBegin) {// 跨年度，中间年度
					for (int j = 1; j <= 12; j++) {
						sql += " ,max(case appdate when '" + i + "-" + j
								+ "' then num else 0 end) as a" + num + " ";
						num++;
					}
				} else {// 跨年度，最后年度
					for (int j = 1; j <= monthEnd; j++) {
						sql += " ,max(case appdate when '" + i + "-" + j
								+ "' then num else 0 end) as a" + num + " ";
						num++;
					}
				}
			}
		}
		sql += " ,sum(num) as all_num ";
		sql += " from( ";
		sql += " select count(m1.org_name) as num, appdate ";
		if (selectMap.get("selectAll") != null
				&& !"".equals(selectMap.get("selectAll").toString())) {
			sql += " from (select distinct '检验公司' as org_name, b.id, to_char(b.appdate,'"
					+ format + "') as appdate ";
		} else {
			sql += " from (select distinct o.org_name, a.businessid, to_char(b.appdate,'"
					+ format + "') as appdate ";
		}
		if (selectMap.get("selectPortid") != null
				&& !"".equals(selectMap.get("selectPortid").toString())) {
			sql += " ,b.portid ";
		}
		sql += " from business b ";
		if (selectMap.get("selectAll") == null
				|| "".equals(selectMap.get("selectAll").toString())) {
			sql += " left join ASSIGNMENT a on b.id = a.businessid ";
			sql += " left join organization_level o on a.orgto = o.id ";
		}
		sql += " where 1 = 1 ";
		sql += " and to_char(b.appdate,'" + format + "')>= '" + valueBegin
				+ "' ";
		sql += " and to_char(b.appdate,'" + format + "')<= '" + valueEnd + "' ";
		if (selectMap.get("selectOrgid") != null
				&& !"".equals(selectMap.get("selectOrgid").toString())) {
			sql += " and a.orgto in(" + selectMap.get("selectOrgid") + ") ";
		}
		if (selectMap.get("selectPortid") != null
				&& !"".equals(selectMap.get("selectPortid").toString())) {
			sql += " and b.portid in(" + selectMap.get("selectPortid") + ") ";
		}
		sql += " and b.businessstate>2 ";
		sql += " )m1 ";
		if (selectMap.get("selectOrgid") != null
				&& !"".equals(selectMap.get("selectOrgid").toString())) {
			sql += " full join ";
			sql += " organization_level m2 on m1.org_name=m2.org_name ";
			sql += " where m2.id in(" + selectMap.get("selectOrgid") + ") ";
		}
		if (selectMap.get("selectPortid") != null
				&& !"".equals(selectMap.get("selectPortid").toString())) {
			sql += " full join ";
			sql += " port m2 on m2.id=m1.portid ";
			sql += " where m2.id in(" + selectMap.get("selectPortid") + ") ";
		}
		sql += " group by appdate ";
		sql += " order by appdate";
		sql += " ) ";

		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;

	}

	/**
	 * 合格率统计
	 * 
	 * @param selectMap
	 * @return
	 * @throws Exception
	 */
	public List getQualifiedStat(Map selectMap) throws Exception {
		int yearBegin = Integer.parseInt(selectMap.get("yearBegin").toString());
		int quarterBegin = Integer.parseInt(selectMap.get("quarterBegin")
				.toString());
		int monthBegin = Integer.parseInt(selectMap.get("monthBegin")
				.toString());
		int yearEnd = Integer.parseInt(selectMap.get("yearEnd").toString());
		int quarterEnd = Integer.parseInt(selectMap.get("quarterEnd")
				.toString());
		int monthEnd = Integer.parseInt(selectMap.get("monthEnd").toString());
		int cycleTime = Integer.parseInt(selectMap.get("cycleTime").toString());
		int begin = 0;
		int end = 0;
		String format = "";
		String valueBegin = "";
		String valueEnd = "";
		List list = new ArrayList();
		String sql = " select org_name ,id";

		// 按年统计
		if (cycleTime == 1) {
			int num = 0;
			format = "yyyy";
			valueBegin = Integer.toString(yearBegin);
			valueEnd = Integer.toString(yearEnd);
			for (int i = yearBegin; i <= yearEnd; i++) {
				sql += " ,max(case appdate when '" + i
						+ "' then num else 100 end) as a" + num + " ";
				num++;
			}
		}
		// 按季统计
		else if (cycleTime == 2) {
			int num = 0;
			format = "yyyy-q";
			valueBegin = Integer.toString(yearBegin) + "-"
					+ Integer.toString(quarterBegin);
			valueEnd = Integer.toString(yearEnd) + "-"
					+ Integer.toString(quarterEnd);
			for (int i = yearBegin; i <= yearEnd; i++) {
				if (i == yearEnd && i == yearBegin) {// 在同一年度
					for (int j = quarterBegin; j <= quarterEnd; j++) {
						sql += " ,max(case appdate when '" + i + "-" + j
								+ "' then num else 100 end) as a" + num + " ";
						num++;
					}
				} else if (i != yearEnd && i == yearBegin) {// 跨年度，第一年度
					for (int j = quarterBegin; j <= 4; j++) {
						sql += " ,max(case appdate when '" + i + "-" + j
								+ "' then num else 100 end) as a" + num + " ";
						num++;
					}
				} else if (i != yearEnd && i != yearBegin) {// 跨年度，中间年度
					for (int j = 1; j <= 4; j++) {
						sql += " ,max(case appdate when '" + i + "-" + j
								+ "' then num else 100 end) as a" + num + " ";
						num++;
					}
				} else {// 跨年度，最后年度
					for (int j = 1; j <= quarterEnd; j++) {
						sql += " ,max(case appdate when '" + i + "-" + j
								+ "' then num else 100 end) as a" + num + " ";
						num++;
					}
				}

			}
		}
		// 按月统计
		else {
			int num = 0;
			format = "yyyy-mm";
			valueBegin = Integer.toString(yearBegin) + "-"
					+ Integer.toString(monthBegin);
			valueEnd = Integer.toString(yearEnd) + "-"
					+ Integer.toString(monthEnd);
			for (int i = yearBegin; i <= yearEnd; i++) {

				if (i == yearEnd && i == yearBegin) {// 在同一年度
					for (int j = monthBegin; j <= monthEnd; j++) {
						sql += " ,max(case appdate when '" + i + "-" + j
								+ "' then num else 100 end) as a" + num + " ";
						num++;
					}
				} else if (i != yearEnd && i == yearBegin) {// 跨年度，第一年度
					for (int j = monthBegin; j <= 12; j++) {
						sql += " ,max(case appdate when '" + i + "-" + j
								+ "' then num else 100 end) as a" + num + " ";
						num++;
					}
				} else if (i != yearEnd && i != yearBegin) {// 跨年度，中间年度
					for (int j = 1; j <= 12; j++) {
						sql += " ,max(case appdate when '" + i + "-" + j
								+ "' then num else 100 end) as a" + num + " ";
						num++;
					}
				} else {// 跨年度，最后年度
					for (int j = 1; j <= monthEnd; j++) {
						sql += " ,max(case appdate when '" + i + "-" + j
								+ "' then num else 100 end) as a" + num + " ";
						num++;
					}
				}
			}
		}

		sql += " ,decode(sum(qualified_num) / sum(org_name_num) * 100, null , 100, sum(qualified_num) / sum(org_name_num) * 100) as all_num ";
		sql += " from( ";

		if (selectMap.get("selectPortid") != null
				&& !"".equals(selectMap.get("selectPortid").toString())) {
			sql += " select m2.port_name,m2.id as org_name, sum(qualified) as qualified_num, count(m2.port_name) as org_name_num,sum(qualified)/count(m2.port_name)*100 as num, appdate ";
		} else {
			sql += " select m2.org_name,m2.id, sum(qualified) as qualified_num, count(m2.org_name) as org_name_num,sum(qualified)/count(m2.org_name)*100 as num, appdate ";
		}

		if (selectMap.get("selectAll") != null
				&& !"".equals(selectMap.get("selectAll").toString())) {
			sql += " from (select distinct '检验公司' as org_name, decode(b.qualified,0,1,1,0, b.qualified) as qualified, b.id, to_char(b.appdate,'"
					+ format + "') as appdate ";
		} else {
			sql += " from (select distinct o.org_name, decode(b.qualified,0,1,1,0, b.qualified) as qualified, a.businessid, to_char(b.appdate,'"
					+ format + "') as appdate ";
		}

		if (selectMap.get("selectPortid") != null
				&& !"".equals(selectMap.get("selectPortid").toString())) {
			sql += " ,b.portid ";
		}

		sql += " from business b ";

		if (selectMap.get("selectAll") == null
				|| "".equals(selectMap.get("selectAll").toString())) {
			sql += " left join ASSIGNMENT a on b.id = a.businessid ";
			sql += " left join organization_level o on a.orgto = o.id ";
		}

		sql += " where 1 = 1 ";
		sql += " and to_char(b.appdate,'" + format + "')>= '" + valueBegin
				+ "' ";
		sql += " and to_char(b.appdate,'" + format + "')<= '" + valueEnd + "' ";

		if (selectMap.get("selectOrgid") != null
				&& !"".equals(selectMap.get("selectOrgid").toString())) {
			sql += " and a.orgto in(" + selectMap.get("selectOrgid") + ") ";
		}

		if (selectMap.get("selectPortid") != null
				&& !"".equals(selectMap.get("selectPortid").toString())) {
			sql += " and b.portid in(" + selectMap.get("selectPortid") + ") ";
		}

		sql += " and b.businessstate>2 ";
		sql += " )m1 ";

		if (selectMap.get("selectOrgid") != null
				&& !"".equals(selectMap.get("selectOrgid").toString())) {
			sql += " full join ";
			sql += " organization_level m2 on m1.org_name=m2.org_name ";
			sql += " where m2.id in(" + selectMap.get("selectOrgid") + ") ";
		}

		if (selectMap.get("selectPortid") != null
				&& !"".equals(selectMap.get("selectPortid").toString())) {
			sql += " full join ";
			sql += " port m2 on m2.id = m1.portid ";
			sql += " where m2.id in(" + selectMap.get("selectPortid") + ") ";
		}

		if (selectMap.get("selectAll") != null
				&& !"".equals(selectMap.get("selectAll").toString())) {
			sql += " left join ";
			sql += " organization_level m2 on m2.org_name=m1.org_name ";
		}

		if (selectMap.get("selectPortid") != null
				&& !"".equals(selectMap.get("selectPortid").toString())) {
			sql += " group by appdate, m2.id,m2.port_name ";
		} else {
			sql += " group by appdate, m2.id,m2.org_name ";
		}

		sql += " order by appdate ";
		sql += " ) ";
		sql += " where org_name is not null ";
		sql += " group by org_name,id ";
		sql += " order by to_number(id) ";

		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;

	}

	/**
	 * 合格率统计(纵向合计)
	 * 
	 * @param selectMap
	 * @return
	 * @throws Exception
	 */
	public List getQualifiedStatAllNum(Map selectMap) throws Exception {
		int yearBegin = Integer.parseInt(selectMap.get("yearBegin").toString());
		int quarterBegin = Integer.parseInt(selectMap.get("quarterBegin")
				.toString());
		int monthBegin = Integer.parseInt(selectMap.get("monthBegin")
				.toString());
		int yearEnd = Integer.parseInt(selectMap.get("yearEnd").toString());
		int quarterEnd = Integer.parseInt(selectMap.get("quarterEnd")
				.toString());
		int monthEnd = Integer.parseInt(selectMap.get("monthEnd").toString());
		int cycleTime = Integer.parseInt(selectMap.get("cycleTime").toString());
		int begin = 0;
		int end = 0;
		String format = "";
		String valueBegin = "";
		String valueEnd = "";
		List list = new ArrayList();
		String sql = " select 'name' as name ";

		// 按年统计
		if (cycleTime == 1) {
			int num = 0;
			format = "yyyy";
			valueBegin = Integer.toString(yearBegin);
			valueEnd = Integer.toString(yearEnd);
			for (int i = yearBegin; i <= yearEnd; i++) {
				sql += " ,max(case appdate when '" + i
						+ "' then num else 100 end) as a" + num + " ";
				num++;
			}
		}
		// 按季统计
		else if (cycleTime == 2) {
			int num = 0;
			format = "yyyy-q";
			valueBegin = Integer.toString(yearBegin) + "-"
					+ Integer.toString(quarterBegin);
			valueEnd = Integer.toString(yearEnd) + "-"
					+ Integer.toString(quarterEnd);
			for (int i = yearBegin; i <= yearEnd; i++) {
				if (i == yearEnd && i == yearBegin) {// 在同一年度
					for (int j = quarterBegin; j <= quarterEnd; j++) {
						sql += " ,max(case appdate when '" + i + "-" + j
								+ "' then num else 100 end) as a" + num + " ";
						num++;
					}
				} else if (i != yearEnd && i == yearBegin) {// 跨年度，第一年度
					for (int j = quarterBegin; j <= 4; j++) {
						sql += " ,max(case appdate when '" + i + "-" + j
								+ "' then num else 100 end) as a" + num + " ";
						num++;
					}
				} else if (i != yearEnd && i != yearBegin) {// 跨年度，中间年度
					for (int j = 1; j <= 4; j++) {
						sql += " ,max(case appdate when '" + i + "-" + j
								+ "' then num else 100 end) as a" + num + " ";
						num++;
					}
				} else {// 跨年度，最后年度
					for (int j = 1; j <= quarterEnd; j++) {
						sql += " ,max(case appdate when '" + i + "-" + j
								+ "' then num else 100 end) as a" + num + " ";
						num++;
					}
				}

			}
		}
		// 按月统计
		else {
			int num = 0;
			format = "yyyy-mm";
			valueBegin = Integer.toString(yearBegin) + "-"
					+ Integer.toString(monthBegin);
			valueEnd = Integer.toString(yearEnd) + "-"
					+ Integer.toString(monthEnd);
			for (int i = yearBegin; i <= yearEnd; i++) {

				if (i == yearEnd && i == yearBegin) {// 在同一年度
					for (int j = monthBegin; j <= monthEnd; j++) {
						sql += " ,max(case appdate when '" + i + "-" + j
								+ "' then num else 100 end) as a" + num + " ";
						num++;
					}
				} else if (i != yearEnd && i == yearBegin) {// 跨年度，第一年度
					for (int j = monthBegin; j <= 12; j++) {
						sql += " ,max(case appdate when '" + i + "-" + j
								+ "' then num else 100 end) as a" + num + " ";
						num++;
					}
				} else if (i != yearEnd && i != yearBegin) {// 跨年度，中间年度
					for (int j = 1; j <= 12; j++) {
						sql += " ,max(case appdate when '" + i + "-" + j
								+ "' then num else 100 end) as a" + num + " ";
						num++;
					}
				} else {// 跨年度，最后年度
					for (int j = 1; j <= monthEnd; j++) {
						sql += " ,max(case appdate when '" + i + "-" + j
								+ "' then num else 100 end) as a" + num + " ";
						num++;
					}
				}
			}
		}

		sql += " ,decode(sum(qualified_num) / sum(org_name_num) * 100, null , 100, sum(qualified_num) / sum(org_name_num) * 100) as all_num ";
		sql += " from( ";
		sql += " select sum(qualified) as qualified_num, count(appdate) as org_name_num,sum(qualified)/count(appdate)*100 as num, appdate ";
		if (selectMap.get("selectAll") != null
				&& !"".equals(selectMap.get("selectAll").toString())) {
			sql += " from (select distinct '检验公司' as org_name, decode(b.qualified,0,1,1,0, b.qualified) as qualified, b.id, to_char(b.appdate,'"
					+ format + "') as appdate ";
		} else {
			sql += " from (select distinct o.org_name, decode(b.qualified,0,1,1,0, b.qualified) as qualified, b.id, to_char(b.appdate,'"
					+ format + "') as appdate ";
		}
		if (selectMap.get("selectPortid") != null
				&& !"".equals(selectMap.get("selectPortid").toString())) {
			sql += " ,b.portid ";
		}
		sql += " from business b ";
		if (selectMap.get("selectAll") == null
				|| "".equals(selectMap.get("selectAll").toString())) {
			sql += " left join ASSIGNMENT a on b.id = a.businessid ";
			sql += " left join organization_level o on a.orgto = o.id ";
		}
		sql += " where 1 = 1 ";
		sql += " and to_char(b.appdate,'" + format + "')>= '" + valueBegin
				+ "' ";
		sql += " and to_char(b.appdate,'" + format + "')<= '" + valueEnd + "' ";
		if (selectMap.get("selectOrgid") != null
				&& !"".equals(selectMap.get("selectOrgid").toString())) {
			sql += " and a.orgto in(" + selectMap.get("selectOrgid") + ") ";
		}
		if (selectMap.get("selectPortid") != null
				&& !"".equals(selectMap.get("selectPortid").toString())) {
			sql += " and b.portid in(" + selectMap.get("selectPortid") + ") ";
		}
		sql += " and b.businessstate>2 ";
		sql += " )m1 ";
		if (selectMap.get("selectOrgid") != null
				&& !"".equals(selectMap.get("selectOrgid").toString())) {
			sql += " full join ";
			sql += " organization_level m2 on m1.org_name=m2.org_name ";
			sql += " where m2.id in(" + selectMap.get("selectOrgid") + ") ";
		}
		if (selectMap.get("selectPortid") != null
				&& !"".equals(selectMap.get("selectPortid").toString())) {
			sql += " full join ";
			sql += " port m2 on m2.id = m1.portid ";
			sql += " where m2.id in(" + selectMap.get("selectPortid") + ") ";
		}
		sql += " group by appdate ";
		sql += " order by appdate ";
		sql += " ) ";

		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;

	}

}
