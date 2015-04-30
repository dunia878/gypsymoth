package com.sinosoft.gypsymoth.bussiness.assignment.service;

import com.sinosoft.gypsymoth.dao.BaseHibernateDAO;
import com.sinosoft.gypsymoth.pojo.Assignment;
import com.sinosoft.gypsymoth.utils.StringUtils;
import java.util.*;

// Referenced classes of package com.sinosoft.gypsymoth.bussiness.assignment.service:
//			AssignmentService

public class AssignmentServiceImpl
	implements AssignmentService
{

	private BaseHibernateDAO baseHibernateDAO;

	public AssignmentServiceImpl()
	{
	}

	public BaseHibernateDAO getBaseHibernateDAO()
	{
		return baseHibernateDAO;
	}

	public void setBaseHibernateDAO(BaseHibernateDAO baseHibernateDAO)
	{
		this.baseHibernateDAO = baseHibernateDAO;
	}

	public void save(Assignment assignment)
		throws Exception
	{
		baseHibernateDAO.saveEntity(assignment);
	}

	public Assignment getAssignmentById(Long id)
		throws Exception
	{
		return (Assignment)baseHibernateDAO.getEntityById(Assignment.class, id);
	}
	
	/**
	 * 得到当前的生效状态的Assignment
	 * @param businessid
	 * @return
	 * @throws Exception
	 */
	public List getAssignmentlistByBusinessId(Long businessid)
		throws Exception
	{
		Assignment assignment = null;
		List paralist = new ArrayList();
		paralist.add(businessid);
		paralist.add(businessid);
		String sql = " select * from assignment t  \twhere t.businessid = ?  and t.assignumber = \t(select max(m.assignumber)   from assignment m   \twhere m.businessid = ? ) ";
		List list = baseHibernateDAO.getEntityBySql(sql, paralist);
		return list;
	}
	
	
	/**
	 * 将历史业务单记录先置为非激活状态
	 * 然后插入激活状态的且历史最高操作数+1的记录
	 * @param	List<Assignment> assignmentlist 待插入的记录
	 */
	public void updateAssignment(List assignmentlist)
		throws Exception
	{
		if (assignmentlist != null && assignmentlist.size() > 0)
		{
			Assignment assignment = (Assignment)assignmentlist.get(0);
			Long businessid = assignment.getBusinessid();
			String sql = " update assignment t set  t.isapplay = 0 where t.businessid = ? and t.isapplay = 1 ";
			List paralist = new ArrayList();
			paralist.add(businessid);
			baseHibernateDAO.updateBatchBySql(sql, paralist);
			for (int i = 0; i < assignmentlist.size(); i++)
			{
				Assignment tempassignment = (Assignment)assignmentlist.get(i);
				save(tempassignment);
			}

		}
	}
	
	/**
	 * 更新Assignment表状态
	 * 更新表的状态字段及当前分配人
	 * @param	assignment
	 * 
	 * 
	 */
	public void updateAssignmentToStateandPersonTo(Assignment assignment)
		throws Exception
	{
		Long assignstate = assignment.getAssignerstate();
		String asignpersonto = assignment.getPersonto();
		assignment = (Assignment)baseHibernateDAO.getEntityById(Assignment.class, assignment.getId());
		assignment.setAssignerstate(assignstate);
		assignment.setPersonto(asignpersonto);
		baseHibernateDAO.updateEntity(assignment);
	}

	public List getBusinessByPersonID(int begin, int numOfEachPage, String personid, HashMap map)
		throws Exception
	{
		List paralist = new ArrayList();
		String sql = " select * from ( select m.*,m2.*, (select c.cityname  from pro_city_port p, city c  where p.portid = m.portid  and p.proid = c.proid  and p.cityid = c.cityid) portcity ,  ( select a.account_name  from account a  where a.account_id = m.accountid) account_name  from (select * from  business  where business.businessstate = 3) m,   \t(select t1.businessid, t1.assignerstate   \t from assignment t1   where t1.isapplay = 1 \t group by t1.businessid, t1.assignerstate) \tm2 where exists (select t.businessid  from assignment t \t where t.businessid = m.id  and t.personto = ?   and t.isapplay = 1)  and m.id = m2.businessid \t) where 1=1\t";
		paralist.add(personid);
		String appno = (String)map.get("appno");
		if (appno != null && !appno.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and upper(appno) like ?  escape '\\' ").toString();
			paralist.add((new StringBuilder("%")).append(StringUtils.processSearch(appno)).append("%").toString());
		}
		String vesselname = (String)map.get("vesselname");
		if (vesselname != null && !vesselname.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and upper(vesselname) like ?  escape '\\' ").toString();
			paralist.add((new StringBuilder("%")).append(StringUtils.processSearch(vesselname)).append("%").toString());
		}
		String portcity = (String)map.get("portcity");
		if (portcity != null && !portcity.equals("") && !portcity.equals("-1"))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and portcity like ?  escape '\\' ").toString();
			paralist.add((new StringBuilder("%")).append(StringUtils.processSearch(portcity)).append("%").toString());
		}
		String appdate_begin = (String)map.get("appdate_begin");
		String appdate_end = (String)map.get("appdate_end");
		if (appdate_begin != null && !appdate_begin.equals("") && appdate_end != null && !appdate_end.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and appdate >= to_date(?,'yyyy-MM-dd')\tand to_date(?,'yyyy-MM-dd')+1\t>= appdate  ").toString();
			paralist.add(appdate_begin);
			paralist.add(appdate_end);
		}
		String allotstate = (String)map.get("allotstate");
		if (allotstate != null && allotstate != "")
			if (allotstate.equals("1"))
				sql = (new StringBuilder(String.valueOf(sql))).append(" and businessstate =3 ").toString();
			else
			if (allotstate.equals("2"))
				sql = (new StringBuilder(String.valueOf(sql))).append("  and businessstate = -1 ").toString();
			else
			if (allotstate.equals("3"))
				sql = (new StringBuilder(String.valueOf(sql))).append(" and  businessstate = 4 ").toString();
		List list = baseHibernateDAO.getEntityByPageBySql(sql, paralist, begin, numOfEachPage);
		return list;
	}
	/**
	 * 根据personID得到有权限分配的业务信息	
	 * @category	适用于非管理员权限用户。只能看到被分配到的内容
	 * @param begin
	 * @param numOfEachPage
	 * @param personid
	 * @return
	 * @throws Exception
	 */
	public int getBusinessByPersonIDCount(String personid, HashMap map)
		throws Exception
	{
		List paralist = new ArrayList();
		String sql = " select count(*) from ( select m.*,m2.*,(select c.cityname  from pro_city_port p, city c  where p.portid = m.portid  and p.proid = c.proid  and p.cityid = c.cityid) portcity ,  ( select a.account_name  from account a  where a.account_id = m.accountid) account_name \t from (select * from  business  where business.businessstate = 3 ) m  ,  \t(select t1.businessid, t1.assignerstate   \t from assignment t1   where t1.isapplay = 1 \t group by t1.businessid, t1.assignerstate) \tm2 where exists (select t.businessid  from assignment t \t where t.businessid = m.id and t.personto = ?    and t.isapplay = 1)  and m.id = m2.businessid \t) where 1=1 ";
		paralist.add(personid);
		String appno = (String)map.get("appno");
		if (appno != null && !appno.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and upper(appno) like ?  escape '\\' ").toString();
			paralist.add((new StringBuilder("%")).append(StringUtils.processSearch(appno)).append("%").toString());
		}
		String vesselname = (String)map.get("vesselname");
		if (vesselname != null && !vesselname.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and upper(vesselname) like ?  escape '\\' ").toString();
			paralist.add((new StringBuilder("%")).append(StringUtils.processSearch(vesselname)).append("%").toString());
		}
		String portcity = (String)map.get("portcity");
		if (portcity != null && !portcity.equals("") && !portcity.equals("-1"))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and portcity like ?  escape '\\' ").toString();
			paralist.add((new StringBuilder("%")).append(StringUtils.processSearch(portcity)).append("%").toString());
		}
		String appdate_begin = (String)map.get("appdate_begin");
		String appdate_end = (String)map.get("appdate_end");
		if (appdate_begin != null && !appdate_begin.equals("") && appdate_end != null && !appdate_end.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and appdate >= to_date(?,'yyyy-MM-dd')\tand to_date(?,'yyyy-MM-dd')+1\t>= appdate  ").toString();
			paralist.add(appdate_begin);
			paralist.add(appdate_end);
		}
		String allotstate = (String)map.get("allotstate");
		if (allotstate != null && allotstate != "")
			if (allotstate.equals("1"))
				sql = (new StringBuilder(String.valueOf(sql))).append(" and businessstate =3 ").toString();
			else
			if (allotstate.equals("2"))
				sql = (new StringBuilder(String.valueOf(sql))).append("  and businessstate = -1 ").toString();
			else
			if (allotstate.equals("3"))
				sql = (new StringBuilder(String.valueOf(sql))).append(" and  businessstate = 4 ").toString();
		int count = baseHibernateDAO.getEntitiesCountBySql(sql, paralist);
		return count;
	}
	
	/**
	 * 管理员权限用户可以看到全部的分配单内容
	 * @category	适用于管理员权限用户。
	 * @param begin
	 * @param numOfEachPage
	 * @param personid
	 * @return
	 * @throws Exception
	 */
	public List getBusinessByPersonIDSYS(int begin, int numOfEachPage, HashMap map)
		throws Exception
	{
		List paralist = new ArrayList();
		String sql = " select * from ( select m.*,m2.*, (select c.cityname  from pro_city_port p, city c  where p.portid = m.portid  and p.proid = c.proid  and p.cityid = c.cityid) portcity ,  ( select a.account_name  from account a  where a.account_id = m.accountid) account_name  from (select * from  business  where business.businessstate = 3 ) m ,   \t(select t1.businessid, t1.assignerstate   \t from assignment t1   where t1.isapplay = 1 \t group by t1.businessid, t1.assignerstate) \tm2 where exists (select t.businessid  from assignment t \t where t.businessid = m.id     and t.isapplay = 1)  and m.id = m2.businessid \t) where 1=1\t";
		String appno = (String)map.get("appno");
		if (appno != null && !appno.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and upper(appno) like ?  escape '\\' ").toString();
			paralist.add((new StringBuilder("%")).append(StringUtils.processSearch(appno)).append("%").toString());
		}
		String vesselname = (String)map.get("vesselname");
		if (vesselname != null && !vesselname.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and upper(vesselname) like ?  escape '\\' ").toString();
			paralist.add((new StringBuilder("%")).append(StringUtils.processSearch(vesselname)).append("%").toString());
		}
		String portcity = (String)map.get("portcity");
		if (portcity != null && !portcity.equals("") && !portcity.equals("-1"))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and portcity like ?  escape '\\' ").toString();
			paralist.add((new StringBuilder("%")).append(StringUtils.processSearch(portcity)).append("%").toString());
		}
		String appdate_begin = (String)map.get("appdate_begin");
		String appdate_end = (String)map.get("appdate_end");
		if (appdate_begin != null && !appdate_begin.equals("") && appdate_end != null && !appdate_end.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and appdate >= to_date(?,'yyyy-MM-dd')\tand to_date(?,'yyyy-MM-dd')+1\t>= appdate  ").toString();
			paralist.add(appdate_begin);
			paralist.add(appdate_end);
		}
		String allotstate = (String)map.get("allotstate");
		if (allotstate != null && allotstate != "")
			if (allotstate.equals("1"))
				sql = (new StringBuilder(String.valueOf(sql))).append(" and businessstate =3  and assignerstate =0 ").toString();
			else
			if (allotstate.equals("2"))
				sql = (new StringBuilder(String.valueOf(sql))).append("  and businessstate =3  and assignerstate >0 ").toString();
			else
			if (allotstate.equals("3"))
				sql = (new StringBuilder(String.valueOf(sql))).append(" and  businessstate = 4 ").toString();
		List list = baseHibernateDAO.getEntityByPageBySql(sql, paralist, begin, numOfEachPage);
		return list;
	}

	public int getBusinessByPersonIDCountSYS(HashMap map)
		throws Exception
	{
		List paralist = new ArrayList();
		String sql = " select count(*) from ( select m.*,m2.*, (select c.cityname  from pro_city_port p, city c  where p.portid = m.portid  and p.proid = c.proid  and p.cityid = c.cityid) portcity,  ( select a.account_name  from account a  where a.account_id = m.accountid) account_name \t from (select * from  business  where business.businessstate = 3) m  ,  \t(select t1.businessid, t1.assignerstate   \t from assignment t1   where t1.isapplay = 1 \t group by t1.businessid, t1.assignerstate) \tm2 where exists (select t.businessid  from assignment t \t where t.businessid = m.id     and t.isapplay = 1)  and m.id = m2.businessid \t) where 1=1 ";
		String appno = (String)map.get("appno");
		if (appno != null && !appno.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and upper(appno) like ?  escape '\\' ").toString();
			paralist.add((new StringBuilder("%")).append(StringUtils.processSearch(appno)).append("%").toString());
		}
		String vesselname = (String)map.get("vesselname");
		if (vesselname != null && !vesselname.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and upper(vesselname) like ?  escape '\\' ").toString();
			paralist.add((new StringBuilder("%")).append(StringUtils.processSearch(vesselname)).append("%").toString());
		}
		String portcity = (String)map.get("portcity");
		if (portcity != null && !portcity.equals("") && !portcity.equals("-1"))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and portcity like ?  escape '\\' ").toString();
			paralist.add((new StringBuilder("%")).append(StringUtils.processSearch(portcity)).append("%").toString());
		}
		String appdate_begin = (String)map.get("appdate_begin");
		String appdate_end = (String)map.get("appdate_end");
		if (appdate_begin != null && !appdate_begin.equals("") && appdate_end != null && !appdate_end.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and appdate >= to_date(?,'yyyy-MM-dd')\tand to_date(?,'yyyy-MM-dd')+1\t>= appdate  ").toString();
			paralist.add(appdate_begin);
			paralist.add(appdate_end);
		}
		String allotstate = (String)map.get("allotstate");
		if (allotstate != null && allotstate != "")
			if (allotstate.equals("1"))
				sql = (new StringBuilder(String.valueOf(sql))).append(" and businessstate =3  and assignerstate =0 ").toString();
			else
			if (allotstate.equals("2"))
				sql = (new StringBuilder(String.valueOf(sql))).append("  and businessstate =3  and assignerstate >0 ").toString();
			else
			if (allotstate.equals("3"))
				sql = (new StringBuilder(String.valueOf(sql))).append(" and  businessstate = 4 ").toString();
		int count = baseHibernateDAO.getEntitiesCountBySql(sql, paralist);
		return count;
	}

	public List getAssignmentWorkflow(Long businessid)
	{
		List paralist = new ArrayList();
		String sql = "  select assignrole,  assigntime,  assign_time,   assignerstate,    \t personto,   personfrom,  b.name,   o.org_sname org_name \tfrom \t( select t.assignumber,  t.assignrole,   t.assigntime,   \tto_char(t.assigntime, 'yyyy-MM-dd hh24:mi:ss') assign_time,  \t t.assignerstate,    t.orgto,     t.personto,   t.personfrom  \t  from assignment t where 1 = 1  and t.businessid = ? ) m \t left join person b on m.personto = b.person_id   \tleft join organization o on m.orgto = o.id \torder by assigntime,assignrole   ";
		paralist.add(businessid);
		List list = baseHibernateDAO.getEntityBySql(sql, paralist);
		return list;
	}

	public List getPersonOnAssignment(String businessid, String person_role)
		throws Exception
	{
		List paralist = new ArrayList();
		String sql = " select p.person_id,p.name,t.orgto  from \tassignment t ,person p   \twhere t.businessid = ? and t.isapplay = 1  \tand t.iscomplete = 1  and t.assignrole = ? \tand t.personto = p.person_id ";
		paralist.add(businessid);
		paralist.add(person_role);
		List list = baseHibernateDAO.getEntityBySql(sql, paralist);
		return list;
	}

	public Assignment getOrgIDsByBusinessid(String businessid)
		throws Exception
	{
		List paralist = new ArrayList();
		Assignment assignment = null;
		String hql = "  from Assignment t where t.businessid = ? and t.iscomplete = 1 and t.isapplay =1 and t.assignrole =1 ";
		paralist.add(Long.valueOf(businessid));
		List assignmentlist = baseHibernateDAO.getEntity(hql, paralist);
		if (assignmentlist != null && assignmentlist.size() > 0)
			assignment = (Assignment)assignmentlist.get(0);
		return assignment;
	}
}
