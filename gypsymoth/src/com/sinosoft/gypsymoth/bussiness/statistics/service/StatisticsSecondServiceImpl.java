package com.sinosoft.gypsymoth.bussiness.statistics.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.sinosoft.gypsymoth.dao.BaseHibernateDAO;
import com.sinosoft.gypsymoth.utils.StringUtils;

public class StatisticsSecondServiceImpl implements StatisticsSecondService{
	
	private HibernateTemplate hibernateTemplate;
	private BaseHibernateDAO baseHibernateDAO;
 
	
	public List getSecondlist(HashMap map) throws Exception
	{
		List paralist = new ArrayList(); 
		String sql =  "  select " +
				"	decode(sum(USA),null,0,sum(USA)) USA, " +
				"	decode(sum(CANADA),null,0,sum(CANADA)) CANADA,  " +
				"	decode(sum(OTHER),null,0,sum(OTHER)) OTHER,	" +
				"	decode(sum(MEXICO),null,0,sum(MEXICO)) MEXICO, " +
				"	decode(sum(CANADA_USA),null,0,sum(CANADA_USA)) CANADA_USA, " +
				"	decode(sum(CANADA_MEXICO),null,0,sum(CANADA_MEXICO)) CANADA_MEXICO,  " +
				"	decode(sum(CANADA_OTHER),null,0,sum(CANADA_OTHER)) CANADA_OTHER,  " +
				"	decode(sum(MEXICO_USA),null,0,sum(MEXICO_USA)) MEXICO_USA,  " +
				"	decode(sum(MEXICO_OTHER),null,0,sum(MEXICO_OTHER)) MEXICO_OTHER,   " +
				"	decode(sum(OTHER_USA),null,0,sum(OTHER_USA)) OTHER_USA  " +
				"	from (	select t.des, sum(decode(t.des, 'USA', 1, 0)) USA," +
				"		 sum(decode(t.des, 'CANADA', 1, 0)) CANADA," +
				"		 sum(decode(t.des, 'MEXICO', 1, 0)) MEXICO,  " +
				"		 sum(decode(t.des, 'OTHER', 1, 0)) OTHER,	" +
				"		 sum(decode(t.des, 'CANADA,USA', 1, 0)) CANADA_USA, " +
				"		 sum(decode(t.des, 'CANADA,MEXICO', 1, 0)) CANADA_MEXICO, " +
				"		 sum(decode(t.des, 'CANADA,OTHER', 1, 0)) CANADA_OTHER, " +
				"		 sum(decode(t.des, 'MEXICO,USA', 1, 0)) MEXICO_USA, " +
				"		 sum(decode(t.des, 'MEXICO,OTHER', 1, 0)) MEXICO_OTHER, " +
				"		 sum(decode(t.des, 'OTHER,USA', 1, 0)) OTHER_USA " +
				"		 from country_statistics_view t  where 1=1 ";
		
		String org4 = (String)map.get("org4");
		if (org4!=null&&!org4.equals("")) {
			sql +=	" and t.orgid like ? ";
			paralist.add(org4+"%");
		}
		String portid = (String)map.get("portid");
		if (portid!=null&&!portid.equals("")) {
			sql +=	" and t.portid = ? ";
			paralist.add(portid);
		}
		String date_begin = (String)map.get("date_begin");
		String date_end = (String)map.get("date_end");
		if (date_begin!=null&&!date_begin.equals("")&&date_end!=null&&!date_end.equals("")) {
			sql +=	"  and t.appdate > to_date(?,'yyyy-MM') and add_months(to_date(?,'yyyy-MM'),1) > t.appdate ";
			paralist.add(date_begin);
			paralist.add(date_end);
		}
		sql +=	"	 group by t.des  ) " ;
		
		List list  = baseHibernateDAO.getEntityBySql(sql, paralist);
		return list;
	}
	
	
	/**	根据月份列出统计表	*/
	public List getFifthlistToMonth(HashMap map) throws Exception
	{
		List list = null;
		List paralist = new ArrayList();
		List arealist = (List)map.get("arealist");
		StringBuffer sql_top = new StringBuffer();
		StringBuffer sql_end = new StringBuffer();
		
		String sql = "	select businessname bn, ";
			
		for (int i = 0; i < arealist.size(); i++) {
			sql_top.append("sum(decode(cn"+i+",null,0,1)) cn"+i+" ");
			if (i<arealist.size()-1) {
				sql_top.append(", ");
			}
		}
		
		sql +=  sql_top;
		sql+=  "  from (select t.id,t.businessname,t.year,		";
		 
		for (int i = 0; i < arealist.size(); i++) {
			HashMap areaMap = (HashMap)arealist.get(i);
			sql_end.append(" sum(decode(to_char(t.appdate,'yyyy-MM'), to_char(to_date('"+areaMap.get("year").toString()+"-"+areaMap.get("month").toString()+"','yyyy-MM'),'yyyy-MM'), 1)) cn"+i+" ");
			if (i<arealist.size()-1) {
				sql_end.append(", ");
			}
		}
	     
	    sql += sql_end;
	    sql += "      from date_statistics_view t where 1=1 ";
	    
	    String date_begin = (String)map.get("date_begin");
	    String date_end = (String)map.get("date_end");
	    if (date_begin!=null&&!date_begin.equals("")&&date_end!=null&&!date_end.equals("")) {
			sql += "  and t.appdate < add_months ( to_date(?,'yyyyMM'),1)-1 and to_date(?,'yyyyMM')-1 <t.appdate 	";
			paralist.add(date_end);
			paralist.add(date_begin); 
	    }
	    String org4 = (String)map.get("org4");
		if (org4!=null&&!org4.equals("")) {
			sql +=	" and t.orgid like ? ";
			paralist.add(org4+"%");
		}
		String portid = (String)map.get("portid");
		if (portid!=null&&!portid.equals("")) {
			sql +=	" and t.portid = ? ";
			paralist.add(portid);
		}
		String condition = (String)map.get("condition");
		if (condition!=null&&!condition.equals("")) {
			sql +=	" and t.businessname like ? escape '\\' ";
			paralist.add("%"+StringUtils.processSearch(condition)+"%");
		}
	    
	    sql += " group by t.id, t.businessname ,t.year)  group by businessname 	";
		
	    list = baseHibernateDAO.getEntityBySql(sql, paralist);
		return list; 
	}
	
	
	/**	根据季度列出统计表	*/
	public List getFifthlistToQuarter(HashMap map) throws Exception
	{
		List list = null;
		List paralist = new ArrayList();
		List arealist = (List)map.get("arealist");
		StringBuffer sql_top = new StringBuffer();
		StringBuffer sql_end = new StringBuffer();
		
		String sql = "	select businessname bn, ";
			
		for (int i = 0; i < arealist.size(); i++) {
			sql_top.append("sum(decode(cn"+i+",null,0,1)) cn"+i+" ");
			if (i<arealist.size()-1) {
				sql_top.append(", ");
			}
		}
		
		sql +=  sql_top;
		sql+=  "  from (select t.id,t.businessname,t.year,		";
		 
		for (int i = 0; i < arealist.size(); i++) {
			HashMap areaMap = (HashMap)arealist.get(i);
			sql_end.append(" sum(decode(to_char(t.appdate, 'yyyy-q'), '"+areaMap.get("year").toString()+"-"+areaMap.get("quarter").toString()+"',1)) cn"+i+" ");
			if (i<arealist.size()-1) {
				sql_end.append(", ");
			}
		}
	     
	    sql += sql_end;
	    sql += "      from date_statistics_view t where 1=1 ";
	    
	    
	    String date_begin = (String)map.get("date_begin");
	    String date_end = (String)map.get("date_end");
	    if (date_begin!=null&&!date_begin.equals("")&&date_end!=null&&!date_end.equals("")) {
			sql += "  and	t.appdate < add_months ( to_date(?,'yyyyMM'),1)-1 and to_date(?,'yyyyMM')-1 <t.appdate 	";
			paralist.add(date_end);
			paralist.add(date_begin); 
	    }
	    String org4 = (String)map.get("org4");
		if (org4!=null&&!org4.equals("")) {
			sql +=	" and t.orgid like ? ";
			paralist.add(org4+"%");
		}
		String portid = (String)map.get("portid");
		if (portid!=null&&!portid.equals("")) {
			sql +=	" and t.portid = ? ";
			paralist.add(portid);
		}
		String condition = (String)map.get("condition");
		if (condition!=null&&!condition.equals("")) {
			sql +=	" and t.businessname like ? escape '\\' ";
			paralist.add("%"+StringUtils.processSearch(condition)+"%");
		}
	    
	    sql += " group by t.id, t.businessname ,t.year)  group by businessname 	";
		
	    list = baseHibernateDAO.getEntityBySql(sql, paralist);
		return list; 
	}
	
	/**	根据年度列出统计表	*/
	public List getFifthlistToYear(HashMap map) throws Exception
	{
		List list = null;
		List paralist = new ArrayList();
		List arealist = (List)map.get("arealist");
		StringBuffer sql_top = new StringBuffer();
		StringBuffer sql_end = new StringBuffer();
		
		String sql = "	select businessname bn, ";
			
		for (int i = 0; i < arealist.size(); i++) {
			sql_top.append("sum(decode(cn"+i+",null,0,1)) cn"+i+" ");
			if (i<arealist.size()-1) {
				sql_top.append(", ");
			}
		}
		
		sql +=  sql_top;
		sql+=  "  from (select t.id,t.businessname,t.year,		";
		 
		for (int i = 0; i < arealist.size(); i++) {
			HashMap areaMap = (HashMap)arealist.get(i);
			sql_end.append(" sum(decode(to_char(t.appdate, 'yyyy'), '"+areaMap.get("year").toString()+"', 1)) cn"+i+" ");
			if (i<arealist.size()-1) {
				sql_end.append(", ");
			}
		}
	     
	    sql += sql_end;
	    sql += "      from date_statistics_view t where 1=1 ";
	    
	    
	    String date_begin = (String)map.get("date_begin");
	    String date_end = (String)map.get("date_end");
	    if (date_begin!=null&&!date_begin.equals("")&&date_end!=null&&!date_end.equals("")) {
			sql += "  and t.appdate <  add_months(to_date(?,'yyyyMM'),1)-1 and to_date(?,'yyyyMM')-1 <t.appdate 	";
			paralist.add(date_end);
			paralist.add(date_begin); 
	    }
	    String org4 = (String)map.get("org4");
		if (org4!=null&&!org4.equals("")) {
			sql +=	" and t.orgid like ? ";
			paralist.add(org4+"%");
		}
		String portid = (String)map.get("portid");
		if (portid!=null&&!portid.equals("")) {
			sql +=	" and t.portid = ? ";
			paralist.add(portid);
		} 
		String condition = (String)map.get("condition");
		if (condition!=null&&!condition.equals("")) {
			sql +=	" and t.businessname like ? escape '\\' ";
			paralist.add("%"+StringUtils.processSearch(condition)+"%");
		}
		
	    sql += " group by t.id, t.businessname ,t.year)  group by businessname 	";
		
	    list = baseHibernateDAO.getEntityBySql(sql, paralist);
		return list; 
	}
	
	
	public List getOrgList(Map selectMap) throws Exception{
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
	
 
	public List getPortList(Map selectMap) throws Exception{
		List list = new ArrayList();
		String sql = " select p.* from organization_port o,port p ";
		sql+=" where o.port_id=p.id  ";
		if(selectMap.get("orgId")!=null && !"".equals(selectMap.get("orgId").toString())){
			sql+=" and o.org_id= (select max(organization.id) from organization  where organization.org_id = ? )  ";
			list.add(selectMap.get("orgId").toString());
		}
		sql+=" order by p.id ";
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList; 
	}
	
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}


	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}


	public BaseHibernateDAO getBaseHibernateDAO() {
		return baseHibernateDAO;
	}


	public void setBaseHibernateDAO(BaseHibernateDAO baseHibernateDAO) {
		this.baseHibernateDAO = baseHibernateDAO;
	}

}
