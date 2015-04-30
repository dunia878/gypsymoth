package com.sinosoft.gypsymoth.bussiness.statistics.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sinosoft.gypsymoth.dao.BaseHibernateDAO;

public class StatisticsWormServiceImpl implements StatisticsWormService {
	
	private BaseHibernateDAO baseHibernateDAO;

	public BaseHibernateDAO getBaseHibernateDAO() {
		return baseHibernateDAO;
	}

	public void setBaseHibernateDAO(BaseHibernateDAO baseHibernateDAO) {
		this.baseHibernateDAO = baseHibernateDAO;
	}

	public List getWormStatistics(Map statisticsMap) throws Exception {

		List list = new ArrayList();
		String sql="select o.org_sname ,decode(m.cn1,null,0,m.cn1) cn1," +
				"decode(m.cn2,null,0,m.cn2) cn2,decode(m.cn3,null,0,m.cn3) cn3," +
				"decode(m.cn4,null,0,m.cn4) cn4,decode(m.cn5,null,0,m.cn5) cn5  " +
				"from organization o left join ( select g.org_sname," +
				"sum(decode(t.gypsymoth1, null, 0, t.gypsymoth1)) cn1," +
				"sum(decode(t.gypsymoth1, null, 0, t.gypsymoth2)) cn2," +
				"sum(decode(t.gypsymoth1, null, 0, t.gypsymoth3)) cn3," +
				"sum(decode(t.gypsymoth1, null, 0, t.gypsymoth4)) cn4," +
				"sum(decode(t.doubt, null, 0, t.doubt)) cn5 " +
				"from examinelog t, business b, organization g " +
				"where t.businessid = b.id and t.orgid = g.id " ;
		
		String portid = (String)statisticsMap.get("portid");
		if (portid!=null&&!portid.equals("")) {
			sql+=	" and b.portid = ? ";
			list.add(portid);
		}
		String date_begin = (String)statisticsMap.get("date_begin");
		String date_end = (String)statisticsMap.get("date_end");
		if (date_begin!=null&&!date_begin.equals("")&&date_end!=null&&!date_end.equals("")) {
			sql+=	"  and b.appdate > to_date(?,'yyyy-MM') and add_months(to_date(?,'yyyy-MM'),1) > b.appdate ";
			list.add(date_begin);
			list.add(date_end);
		}
		sql+=" group by g.org_sname ) m on o.org_sname = m.org_sname ";
		String org4 = (String)statisticsMap.get("org4");
		if (org4!=null&&!org4.equals("")) {
			sql+=	"where  o.org_id like ? ";
			list.add(org4+"%");
		}
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;
	
	}

	public List getOrgList(Map selectMap) throws Exception {
		List list = new ArrayList();
		String sql = " select t.*,o.org_id org4 from organization_level t,organization o  ";
		sql+=" where t.id = o.id  ";
		if(selectMap.get("parentId")!=null && !"".equals(selectMap.get("parentId").toString())){
			sql+=" and t.parent_id= (select max(organization.id) from organization  where organization.org_id = ? )  ";
			list.add(selectMap.get("parentId").toString());
		}
		if(selectMap.get("isChild")!=null && !"".equals(selectMap.get("isChild").toString())){
			sql+=" and t.is_child=?  ";
			list.add(selectMap.get("isChild").toString());
		}
		if(selectMap.get("orgId")!=null && !"".equals(selectMap.get("orgId").toString())){
			sql+=" and t.id=?  ";
			list.add(selectMap.get("orgId").toString());
		}
		sql+=" order by t.id "; 
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;
	
	}

	public List getPortList(Map selectMap) throws Exception {
		List list = new ArrayList();
		String sql = " select p.* from organization_port o,port p ";
		sql+=" where o.port_id=p.id and ";
		if(selectMap.get("orgId")!=null && !"".equals(selectMap.get("orgId").toString())){
			sql+=" o.org_id=? and ";
			list.add(selectMap.get("orgId").toString());
		}
		sql+=" 1=1 ";
		sql+=" order by p.id ";
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;
	}


	
}
