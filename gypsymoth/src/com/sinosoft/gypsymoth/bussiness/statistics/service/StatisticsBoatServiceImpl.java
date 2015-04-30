package com.sinosoft.gypsymoth.bussiness.statistics.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sinosoft.gypsymoth.dao.BaseHibernateDAO;

public class StatisticsBoatServiceImpl implements StatisticsBoatService {

	private BaseHibernateDAO baseHibernateDAO;

	public BaseHibernateDAO getBaseHibernateDAO() {
		return baseHibernateDAO;
	}

	public void setBaseHibernateDAO(BaseHibernateDAO baseHibernateDAO) {
		this.baseHibernateDAO = baseHibernateDAO;
	}

	public List getOrgList(Map selectMap) throws Exception {
		List list = new ArrayList();
		String sql = " select t.*,o.org_id org4 from organization_level t,organization o  ";
		sql += " where t.id = o.id  ";
		if (selectMap.get("parentId") != null
				&& !"".equals(selectMap.get("parentId").toString())) {
			sql += " and t.parent_id= (select max(organization.id) from organization  where organization.org_id = ? )  ";
			list.add(selectMap.get("parentId").toString());
		}
		if (selectMap.get("isChild") != null
				&& !"".equals(selectMap.get("isChild").toString())) {
			sql += " and t.is_child=?  ";
			list.add(selectMap.get("isChild").toString());
		}
		if (selectMap.get("orgId") != null
				&& !"".equals(selectMap.get("orgId").toString())) {
			sql += " and t.id=?  ";
			list.add(selectMap.get("orgId").toString());
		}
		sql += " order by t.id ";
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;

	}

	public List getPortList(Map selectMap) throws Exception {
		List list = new ArrayList();
		String sql = " select p.* from organization_port o,port p ";
		sql += " where o.port_id=p.id and ";
		if (selectMap.get("orgId") != null
				&& !"".equals(selectMap.get("orgId").toString())) {
			sql += " o.org_id=? and ";
			list.add(selectMap.get("orgId").toString());
		}
		sql += " 1=1 ";
		sql += " order by p.id ";
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;
	}

	public List getTypeStatistics(Map statisticsMap) throws Exception {
		List list = new ArrayList();
		String sql = "select o.org_sname ,decode(m.cn1,null,0,m.cn1) cn1,"
				+ "          decode(m.cn2,null,0,m.cn2) cn2,"
				+ "          decode(m.cn3,null,0,m.cn3) cn3, "
				+ "          decode(m.cn4,null,0,m.cn4) cn4, "
				+ "         decode(m.cn5,null,0,m.cn5) cn5,  "
				+ "       decode(m.cn6,null,0,m.cn6) cn6,    "
				+ "     decode(m.cn7,null,0,m.cn7) cn7,    "
				+ "     decode(m.cn8,null,0,m.cn8) cn8,     "
				+ "    decode(m.cn9,null,0,m.cn9) cn9,      "
				+ "   decode(m.cn10,null,0,m.cn10) cn10,      "
				+ "   decode(m.cn11,null,0,m.cn11) cn11,     "
				+ "    decode(m.cn12,null,0,m.cn12) cn12     "
				+ "    from organization o          "
				+ "       left join (                     select g.org_sname,"
				+ "sum(decode(t.vesseltype,'Container Vessel',1,0)) cn1,      "
				+ "sum(decode(t.vesseltype,'General Cargo Ship',1,0)) cn2,    "
				+ "sum(decode(t.vesseltype,'Bulk Carrier',1,0)) cn3,     "
				+ "sum(decode(t.vesseltype,'Multi-purpose vessel',1,0)) cn4,  "
				+ "sum(decode(t.vesseltype,'Oil Tanker',1,0)) cn5,    "
				+ "sum(decode(t.vesseltype,'Roll on/Roll off //Vessel',1,0)) cn6, "
				+ "sum(decode(t.vesseltype,'Refrigerated Vessel',1,0)) cn7,   "
				+ "sum(decode(t.vesseltype,'Vehicle and Passenger Ferry',1,0)) cn8,   "
				+ "sum(decode(t.vesseltype,'Heavy-cargo Carrier',1,0)) cn9,    "
				+ "sum(decode(t.vesseltype,'Lighter Aboard Ship--LASH',1,0)) cn10,  "
				+ "sum(decode(t.vesseltype,'Liquefied natural gas carrier--LNC',1,0)) cn11,  "
				+ "sum(decode(t.vesseltype,'Liquefied petroleum gas carrier--LPC',1,0)) cn12  "
				+ "from business t,organization g  where t.portorgid=g.id ";
		String portid = (String) statisticsMap.get("portId");
		String orgId = (String) statisticsMap.get("orgId");
		String parentId = (String) statisticsMap.get("parentId");
		String ifAll = (String) statisticsMap.get("ifAll");
		String vesseltype = (String) statisticsMap.get("vesseltype");
		String type=(String)statisticsMap.get("selectOrg");

		String date_begin = (String) statisticsMap.get("date_begin");
		String date_end = (String) statisticsMap.get("date_end");
		if (date_begin != null && !date_begin.equals("") && date_end != null
				&& !date_end.equals("")) {
			sql += "  and t.appdate > to_date(?,'yyyy-MM') and add_months(to_date(?,'yyyy-MM'),1) > t.appdate ";
			list.add(date_begin);
			list.add(date_end);
		}
		if ("all".equals(ifAll)) {
			sql += " group by g.org_sname ) m on o.org_sname = m.org_sname ";
			List returnList = baseHibernateDAO.getEntityBySql(sql, list);
			return returnList;
		} else {
			if (portid != null && !portid.equals("")) {
				sql += " and t.portid = ? ";
				list.add(portid);
			}
			sql += " group by g.org_sname ) m on o.org_sname = m.org_sname ";
			if (parentId != null && !"".equals(parentId)) {
				if ("1".equals(type)) {
					sql += "where  o.org_id = ? ";
					list.add(parentId);
				} else if("2".equals(type)){
					if (orgId != null && !orgId.equals("")) {
						sql += "where  o.org_id = ? ";
						list.add(orgId);
					} else {
						sql += "where  o.parent_id in (select id from organization where org_id= ?) ";
						list.add(parentId);
					}
				}
			} else {
				sql += "where  o.parent_id = '1' ";
			}
			sql += "order by to_number(o.id) ";
			List returnList = baseHibernateDAO.getEntityBySql(sql, list);
			return returnList;
		}
	}

	public List getTonStatistics(Map statisticsMap) throws Exception {
		List list = new ArrayList();
		String sql = "select o.org_sname ,";
		String[] beginTon = (String[]) statisticsMap.get("beginTon");
		String[] endTon = (String[]) statisticsMap.get("endTon");
		if (beginTon != null && beginTon.length != 0 && endTon != null
				&& endTon.length != 0) {
			for (int i = 0; i < beginTon.length; i++) {
				if (beginTon.length == 1 || i == beginTon.length - 1) {
					sql += "   decode(m.cn0,null,0,m.cn" + i + ") cn" + i + " ";
				} else {
					sql += "   decode(m.cn0,null,0,m.cn" + i + ") cn" + i + ",";
				}
			}
		}
		sql += "     from organization o   left join (       select o.org_sname,";
		if (beginTon != null && beginTon.length != 0 && endTon != null
				&& endTon.length != 0) {
			for (int i = 0; i < beginTon.length; i++) {
				if (beginTon.length == 1 || i == beginTon.length - 1) {
					sql += "sum(decode(case  when t.tonnage < " + beginTon[i]
							+ " and t.tonnage >" + endTon[i]
							+ " then 1 end,1,1,0)) cn" + i + " ";
				} else {
					sql += "sum(decode(case  when t.tonnage < " + beginTon[i]
							+ " and t.tonnage >" + endTon[i]
							+ " then 1 end,1,1,0)) cn" + i + ",";
				}
			}
		}

		sql += " from business t,organization o where t.portorgid=o.id  and t.appdate is not null ";
		String portid = (String) statisticsMap.get("portid");
		if (portid != null && !portid.equals("")) {
			sql += " and t.portid = ? ";
			list.add(portid);
		}
		String date_begin = (String) statisticsMap.get("date_begin");
		String date_end = (String) statisticsMap.get("date_end");
		if (date_begin != null && !date_begin.equals("") && date_end != null
				&& !date_end.equals("")) {
			sql += "  and t.appdate > to_date(?,'yyyy-MM') and add_months(to_date(?,'yyyy-MM'),1) > t.appdate ";
			list.add(date_begin);
			list.add(date_end);
		}
		sql += "  group by o.org_sname   ) m      on o.org_sname = m.org_sname";
		String org4 = (String) statisticsMap.get("org4");
		if (org4 != null && !org4.equals("")) {
			sql += " where o.org_id like ? ";
			list.add(org4 + "%");
		}

		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;
	}

}
