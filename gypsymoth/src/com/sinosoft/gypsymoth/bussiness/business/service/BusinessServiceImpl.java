// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   BusinessServiceImpl.java

package com.sinosoft.gypsymoth.bussiness.business.service;

import com.opensymphony.xwork2.ActionContext;
import com.sinosoft.gypsymoth.dao.BaseHibernateDAO;
import com.sinosoft.gypsymoth.pojo.*;
import com.sinosoft.gypsymoth.utils.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.springframework.orm.hibernate3.HibernateTemplate;

// Referenced classes of package com.sinosoft.gypsymoth.bussiness.business.service:
//			BusinessService

public class BusinessServiceImpl
	implements BusinessService
{

	private HibernateTemplate hibernateTemplate;
	private BaseHibernateDAO baseHibernateDAO;

	public BusinessServiceImpl()
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

	public void save(Business business)
		throws Exception
	{
		baseHibernateDAO.saveEntity(business);
		saveBusinessLog(business);
	}

	public void saveBusinessDes(BusinessDesCountry busdes)
		throws Exception
	{
		baseHibernateDAO.saveEntity(busdes);
	}
	/**
	 * 保存业务单
	 */
	public void saveBusiness(Business business, List list)
		throws Exception
	{
		baseHibernateDAO.saveEntity(business);
		for (int i = 0; i < list.size(); i++)
		{
			BusinessDesCountry businessDesCountry = (BusinessDesCountry)list.get(i);
			businessDesCountry.setBusinessid(business.getId());
			baseHibernateDAO.saveEntity(businessDesCountry);
		}

		insertOtherBusinessTable(business, business.getAppdate());
	}
	/**
	 * 提交业务单	分如下两种情况 
	 * 1:	业务单已保存	(更新后提交)
	 * 2:	业务单未保存	(先保存再提交)
	 * @param	business
	 * @param	list
	 */
	public void savecommitBusiness(Business business, List list)
		throws Exception
	{
		Long businessid = business.getId();
		Long businessState = business.getBusinessstate();
		if (businessid == null)
		{
			baseHibernateDAO.saveEntity(business);
			for (int i = 0; i < list.size(); i++)
			{
				BusinessDesCountry businessDesCountry = (BusinessDesCountry)list.get(i);
				businessid = business.getId();
				businessDesCountry.setBusinessid(businessid);
				baseHibernateDAO.saveEntity(businessDesCountry);
			}

		} else
		{
			baseHibernateDAO.updateEntity(business);
		}
		insertOtherBusinessTable(business, business.getAppdate());
	}

	public Float getPaymentMoneyByTon(String vesseltype, Long ton)
	{
		Float money = null;
		List paralist = new ArrayList();
		String sql = "\tselect t.money from ship_rule t , ship_type b \twhere t.shiptype = b.id and b.type = ? and t.maxton >= ? and t.minton<=? ";
		paralist.add(vesseltype);
		paralist.add(ton);
		paralist.add(ton);
		List list = baseHibernateDAO.getEntityBySql(sql, paralist);
		if (list != null && list.size() >= 1)
		{
			HashMap map = (HashMap)list.get(0);
			BigDecimal str = (BigDecimal)map.get("MONEY");
			money = Float.valueOf(str.floatValue());
		}
		return money;
	}
	/**
	 * 	更新业务表单信息 及 业务表对应的目的国表信息
	 */
	public void updateBusinessDetail(Business business, List list)
		throws Exception
	{
		Date date = new Date();
		baseHibernateDAO.updateEntity(business);
		Long businessid = business.getId();
		Long businessState = business.getBusinessstate();
		List paralist = new ArrayList();
		String sql = " delete from business_des_country b where b.businessid in(   ";
		paralist.add(businessid);
		baseHibernateDAO.deleteBatchBySql(sql, paralist);
		for (int i = 0; i < list.size(); i++)
		{
			BusinessDesCountry businessDesCountry = (BusinessDesCountry)list.get(i);
			businessDesCountry.setBusinessid(businessid);
			baseHibernateDAO.saveEntity(businessDesCountry);
		}

		insertOtherBusinessTable(business, date);
	}

	public void updateCertificateEdit(Business business, List list, String personto)
		throws Exception
	{
		baseHibernateDAO.updateEntity(business);
		Long businessid = business.getId();
		Long businessState = business.getBusinessstate();
		List paralist = new ArrayList();
		String sql = " delete from business_des_country b where b.businessid in(   ";
		paralist.add(businessid);
		baseHibernateDAO.deleteBatchBySql(sql, paralist);
		for (int i = 0; i < list.size(); i++)
		{
			BusinessDesCountry businessDesCountry = (BusinessDesCountry)list.get(i);
			businessDesCountry.setBusinessid(businessid);
			baseHibernateDAO.saveEntity(businessDesCountry);
		}

		updateCertificateAuth(personto, businessid.toString());
	}

	public void updateCertificateAuth(String personto, String businessid)
	{
		List list = new ArrayList();
		String sql = " update assignment t set t.personto =? where  t.isapplay =1 and t.iscomplete = 1 and t.businessid  = ? and t.assignrole =1 ";
		list.add(personto);
		list.add(businessid);
		baseHibernateDAO.updateBatchBySql(sql, list);
	}

	public void saveBusinessLog(Business business)
		throws Exception
	{
		Map session = ActionContext.getContext().getSession();
		SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		date = simp.parse(simp.format(date));
		Histroy businessLog = new Histroy();
		businessLog.setBusinessid(business.getId());
		businessLog.setOperatordate(date);
		businessLog.setOperator(((Account)session.get("account")).getAccountName());
		businessLog.setBusinessname(business.getBusinessname());
		businessLog.setCheckdate(business.getCheckdate());
		businessLog.setCompany(business.getCompany());
		businessLog.setImo(business.getImo());
		businessLog.setLinkmanname(business.getLinkmanname());
		baseHibernateDAO.saveEntity(businessLog);
	}

	public void saveDesCountry(BusinessDesCountry businessdes)
		throws Exception
	{
		baseHibernateDAO.saveEntity(businessdes);
	}

	public void updateBusiness(Business business)
		throws Exception
	{
		baseHibernateDAO.updateEntity(business);
		business.setAppname(business.getAppname());
		saveBusinessLog(business);
	}

	public void updateBusinessToState(Business business)
		throws Exception
	{
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMM");
		Long businessState = business.getBusinessstate();
		Long id = business.getId();
		String remark = business.getRemark();
		String appno = "";
		business = (Business)baseHibernateDAO.getEntityById(Business.class, id);
		business.setBusinessstate(businessState);
		business.setRemark(remark);
		if (business.getBusinessstate().toString().equals(BusinessState.BUSINESS_ACCEPTSUCCESS))
		{
			appno = getnextAppno(dateFormat.format(date), business.getOrgid(), business.getId().toString());
			business.setAppno(appno);
		}
		baseHibernateDAO.updateEntity(business);
		insertOtherBusinessTable(business, date);
	}

	public void insertOtherBusinessTable(Business business, Date date)
		throws Exception
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		Map sessionmap = ActionContext.getContext().getSession();
		HttpSession session = request.getSession();
		Account account = (Account)sessionmap.get("account");
		String ACCOUNT_ID = account.getAccountId();
		String person_id = (String)session.getAttribute("PERSON_ID");
		String businessState = String.valueOf(business.getBusinessstate());
		if (businessState.equals(BusinessState.BUSINESS_ACCEPTWAIT))
		{
			Float paymentmoney = getPaymentMoneyByTon(business.getVesseltype(), business.getTonnage());
			Payment payment = new Payment();
			String special = business.getSpecial();
			payment.setMoney(paymentmoney);
			payment.setBusinessid(business.getId());
			payment.setLedgerstate(Long.valueOf(PaymentState.Payment_LEDGERWAIT));
			payment.setPaystate(Long.valueOf(BusinessState.LONG_ZERO));
			baseHibernateDAO.saveEntity(payment);
		} else
		if (!businessState.equals(BusinessState.BUSINESS_ACCEPTNOT))
			if (businessState.equals(BusinessState.BUSINESS_ACCEPTSUCCESS))
			{
				Assignment assignment = new Assignment();
				assignment.setPersonto("0");
				assignment.setAssignumber(Long.valueOf(BusinessState.LONG_ONE));
				assignment.setAssigntime(date);
				assignment.setAssignerstate(Long.valueOf(BusinessState.Allot_FIRST));
				assignment.setBusinessid(business.getId());
				assignment.setIscomplete(Long.valueOf(BusinessState.LONG_ZERO));
				assignment.setIsapplay(Long.valueOf(BusinessState.LONG_ONE));
				baseHibernateDAO.saveEntity(assignment);
			} else
			if (businessState.equals(BusinessState.Business_ALLOTED))
			{
				String assignsql = " update assignment t set t.iscomplete  =1 where t.businessid = ? ";
				List assignlist = new ArrayList();
				assignlist.add(business.getId());
				baseHibernateDAO.updateBatchBySql(assignsql, assignlist);
			}
		if (business.getBusinessstate().longValue() > 0L)
		{
			Operator operator = new Operator();
			operator.setBusinessid(business.getId());
			operator.setDisposetype(business.getBusinessstate());
			operator.setOperatortime(date);
			operator.setOperatorid(ACCOUNT_ID);
			baseHibernateDAO.saveEntity(operator);
		}
	}

	public void updateBusinessdes(BusinessDesCountry businessdes)
		throws Exception
	{
		baseHibernateDAO.updateEntity(businessdes);
	}

	public void apply(Business business)
		throws Exception
	{
		baseHibernateDAO.saveEntity(business);
	}

	public void deleteBusinessById(Long id)
		throws Exception
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String ACCOUNT_ID = "1";
		baseHibernateDAO.deleteEntity(Business.class, id);
		List paralist = new ArrayList();
		String sql = " delete from business_des_country b where b.businessid in(   ";
		paralist.add(id);
		baseHibernateDAO.deleteBatchBySql(sql, paralist);
		Operator operator = new Operator();
		operator.setBusinessid(id);
		operator.setDisposetype(Long.valueOf("-1"));
		operator.setOperatortime(new Date());
		operator.setOperatorid(ACCOUNT_ID);
		baseHibernateDAO.saveEntity(operator);
	}

	public void payment(Business business)
		throws Exception
	{
		baseHibernateDAO.saveEntity(business);
	}

	public List getAllBusiness(int begin, int numOfEachPage)
		throws Exception
	{
		String sql = "from Business r order by r.id desc";
		List list = new ArrayList();
		List returnList = baseHibernateDAO.getEntityByPage(sql, list, begin, numOfEachPage);
		return returnList;
	}

	public int getAllDataCount()
		throws Exception
	{
		List list = new ArrayList();
		String sql = "select count(*) from Business";
		int count = baseHibernateDAO.getEntitiesCountBySql(sql, list);
		return count;
	}

	public List getBusinessbyState(int begin, int numOfEachPage, Long state, HashMap map)
		throws Exception
	{
		String sql = "from Business r where r.businessstate = ? order by r.id desc";
		List list = new ArrayList();
		list.add(state);
		String businessname_search = (String)map.get("businessname_search");
		if (businessname_search != null && !businessname_search.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and r.businessname like ? escape '\\'  ").toString();
			list.add((new StringBuilder("%")).append(StringUtils.processSearch(businessname_search)).append("%").toString());
		}
		List returnList = baseHibernateDAO.getEntityByPage(sql, list, begin, numOfEachPage);
		return returnList;
	}

	public int getBusinessbyStateCount(Long state, HashMap map)
		throws Exception
	{
		List paralist = new ArrayList();
		String sql = " select count(*) from  \t( select r.*,(select c.cityname from city c  where c.cityid = p.cityid and p.proid = c.proid and  p.id = r.portid) portcity \t from Business r,port p \t where r.portid = p.id  and r.businessstate = ?   ) ";
		paralist.add(state);
		String appno = (String)map.get("appno");
		if (appno != null && !appno.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and appno like ?  escape '\\' ").toString();
			paralist.add((new StringBuilder("%")).append(StringUtils.processSearch(appno)).append("%").toString());
		}
		String vesselname = (String)map.get("vesselname");
		if (vesselname != null && !vesselname.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and vesselname like ?  escape '\\' ").toString();
			paralist.add((new StringBuilder("%")).append(StringUtils.processSearch(vesselname)).append("%").toString());
		}
		String portname = (String)map.get("portname");
		if (portname != null && !portname.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and portcity like ?  escape '\\' ").toString();
			paralist.add((new StringBuilder("%")).append(StringUtils.processSearch(portname)).append("%").toString());
		}
		int count = baseHibernateDAO.getEntitiesCountBySql(sql, paralist);
		return count;
	}

	public int getBusinessListCOUNT(HashMap map)
		throws Exception
	{
		List paralist = new ArrayList();
		String is_child = (String)map.get("is_child");
		String state = map.get("state").toString();
		String sql = " select count(*) from ( select b.*,\t (select c.cityname from pro_city_port p,city c where p.portid = b.portid and  p.proid = c.proid and p.cityid = c.cityid  ) portcity,  \t (select a.account_name   from account a  where a.account_id = b.accountid) account_name \t  from business b  where b.businessstate = ? ";
		paralist.add(state);
		String accountid = (String)map.get("accountid");
		if (accountid != null && !accountid.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and b.accountid = ? ").toString();
			paralist.add(accountid);
		}
		sql = (new StringBuilder(String.valueOf(sql))).append("\t  union    select b1.*, (select c1.cityname from pro_city_port p1,city c1 where p1.portid = b1.portid and  p1.proid = c1.proid and p1.cityid = c1.cityid  ) portcity, \t(select a1.account_name  from account a1   where a1.account_id = b1.accountid) account_name     \tfrom business b1  where b1.businessstate = ?  ").toString();
		paralist.add(state);
		String org4 = (String)map.get("org4");
		if (org4 != null && !org4.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and b1.orgid like ? ").toString();
			if (is_child != null && is_child.equals("1"))
				paralist.add((new StringBuilder(String.valueOf(org4.substring(0, 2)))).append("%").toString());
			else
			if (is_child != null && is_child.equals("2"))
				paralist.add((new StringBuilder(String.valueOf(org4))).append("%").toString());
			else
			if (is_child != null && is_child.equals("0"))
				paralist.add("%");
		}
		sql = (new StringBuilder(String.valueOf(sql))).append(" ) where 1=1  ").toString();
		String vesselname = (String)map.get("vesselname");
		if (vesselname != null && !vesselname.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and upper(vesselname) like ? ").toString();
			paralist.add((new StringBuilder("%")).append(StringUtils.processSearch(vesselname)).append("%").toString());
		}
		String portcity = (String)map.get("portcity");
		if (portcity != null && !portcity.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and portcity like ? ").toString();
			paralist.add((new StringBuilder("%")).append(StringUtils.processSearch(portcity)).append("%").toString());
		}
		String tempno = (String)map.get("tempno");
		if (tempno != null && !tempno.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and upper(tempno) like ? ").toString();
			paralist.add((new StringBuilder("%")).append(StringUtils.processSearch(tempno)).append("%").toString());
		}
		String appdate_begin = (String)map.get("appdate_begin");
		String appdate_end = (String)map.get("appdate_end");
		if (appdate_begin != null && !appdate_begin.equals("") && appdate_end != null && !appdate_end.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and appdate >= to_date(?,'yyyy-MM-dd')\tand to_date(?,'yyyy-MM-dd')+1\t>= appdate  ").toString();
			paralist.add(appdate_begin);
			paralist.add(appdate_end);
		}
		int count = baseHibernateDAO.getEntitiesCountBySql(sql, paralist);
		return count;
	}

	public List getBusinessList(int begin, int numOfEachPage, HashMap map)
		throws Exception
	{
		List paralist = new ArrayList();
		String is_child = (String)map.get("is_child");
		String state = map.get("state").toString();
		String sql = " select * from ( select b.*,\t (select c.cityname from pro_city_port p,city c where p.portid = b.portid and  p.proid = c.proid and p.cityid = c.cityid  ) portcity,  \t (select a.account_name   from account a  where a.account_id = b.accountid) account_name \t  from business b  where b.businessstate = ? ";
		paralist.add(state);
		String accountid = (String)map.get("accountid");
		if (accountid != null && !accountid.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and b.accountid = ? ").toString();
			paralist.add(accountid);
		}
		sql = (new StringBuilder(String.valueOf(sql))).append("\t  union    select b1.*, (select c1.cityname from pro_city_port p1,city c1 where p1.portid = b1.portid and  p1.proid = c1.proid and p1.cityid = c1.cityid  ) portcity, \t(select a1.account_name  from account a1   where a1.account_id = b1.accountid) account_name     \tfrom business b1  where b1.businessstate = ?  ").toString();
		paralist.add(state);
		String org4 = (String)map.get("org4");
		if (org4 != null && !org4.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and b1.orgid like ? ").toString();
			if (is_child != null && is_child.equals("1"))
				paralist.add((new StringBuilder(String.valueOf(org4.substring(0, 2)))).append("%").toString());
			else
			if (is_child != null && is_child.equals("2"))
				paralist.add((new StringBuilder(String.valueOf(org4))).append("%").toString());
			else
			if (is_child != null && is_child.equals("0"))
				paralist.add("%");
		}
		sql = (new StringBuilder(String.valueOf(sql))).append(" ) where 1=1 ").toString();
		String vesselname = (String)map.get("vesselname");
		if (vesselname != null && !vesselname.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and upper(vesselname) like ? ").toString();
			paralist.add((new StringBuilder("%")).append(StringUtils.processSearch(vesselname)).append("%").toString());
		}
		String portcity = (String)map.get("portcity");
		if (portcity != null && !portcity.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and portcity like ? ").toString();
			paralist.add((new StringBuilder("%")).append(StringUtils.processSearch(portcity)).append("%").toString());
		}
		String tempno = (String)map.get("tempno");
		if (tempno != null && !tempno.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and upper(tempno) like ? ").toString();
			paralist.add((new StringBuilder("%")).append(StringUtils.processSearch(tempno)).append("%").toString());
		}
		String appdate_begin = (String)map.get("appdate_begin");
		String appdate_end = (String)map.get("appdate_end");
		if (appdate_begin != null && !appdate_begin.equals("") && appdate_end != null && !appdate_end.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and appdate >= to_date(?,'yyyy-MM-dd')\tand to_date(?,'yyyy-MM-dd')+1\t>= appdate  ").toString();
			paralist.add(appdate_begin);
			paralist.add(appdate_end);
		}
		sql = (new StringBuilder(String.valueOf(sql))).append(" order by  appdate desc ").toString();
		List list = baseHibernateDAO.getEntityByPageBySql(sql, paralist, begin, numOfEachPage);
		return list;
	}
	/*	用户检索业务单		--结束	*/
	
	
	
	/*	客户检索业务单	-- begin	*/
	public int getBusinessListClientCOUNT(HashMap map)
		throws Exception
	{
		List paralist = new ArrayList();
		String state = map.get("state").toString();
		String sql = " select count(*) from ( select b.*,\t (select c.cityname from pro_city_port p,city c where p.portid = b.portid and  p.proid = c.proid and p.cityid = c.cityid  ) portcity,  \t (select a.account_name   from account a  where a.account_id = b.accountid) account_name \t  from business b  where b.businessstate = ? and b.businessname = ? ";
		paralist.add(state);
		String businessname = (String)map.get("businessname");
		paralist.add(businessname);
		sql = (new StringBuilder(String.valueOf(sql))).append(" )  where 1=1  ").toString();
		String vesselname = (String)map.get("vesselname");
		if (vesselname != null && !vesselname.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and vesselname like ? ").toString();
			paralist.add((new StringBuilder("%")).append(StringUtils.processSearch(vesselname)).append("%").toString());
		}
		String portcity = (String)map.get("portcity");
		if (portcity != null && !portcity.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and portcity like ? ").toString();
			paralist.add((new StringBuilder("%")).append(StringUtils.processSearch(portcity)).append("%").toString());
		}
		String tempno = (String)map.get("tempno");
		if (tempno != null && !tempno.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and tempno like ? ").toString();
			paralist.add((new StringBuilder("%")).append(StringUtils.processSearch(tempno)).append("%").toString());
		}
		String appdate_begin = (String)map.get("appdate_begin");
		String appdate_end = (String)map.get("appdate_end");
		if (appdate_begin != null && !appdate_begin.equals("") && appdate_end != null && !appdate_end.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and appdate >= to_date(?,'yyyy-MM-dd')\tand to_date(?,'yyyy-MM-dd')+1\t>= appdate  ").toString();
			paralist.add(appdate_begin);
			paralist.add(appdate_end);
		}
		int count = baseHibernateDAO.getEntitiesCountBySql(sql, paralist);
		return count;
	}

	public List getBusinessListClient(int begin, int numOfEachPage, HashMap map)
		throws Exception
	{
		List paralist = new ArrayList();
		String state = map.get("state").toString();
		String sql = " select * from ( select b.*,\t (select c.cityname from pro_city_port p,city c where p.portid = b.portid and  p.proid = c.proid and p.cityid = c.cityid  ) portcity,  \t (select a.account_name   from account a  where a.account_id = b.accountid) account_name \t  from business b  where b.businessstate = ? and b.businessname = ? ";
		paralist.add(state);
		String businessname = (String)map.get("businessname");
		paralist.add(businessname);
		sql = (new StringBuilder(String.valueOf(sql))).append(" )  where 1=1 ").toString();
		String vesselname = (String)map.get("vesselname");
		if (vesselname != null && !vesselname.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and vesselname like ? ").toString();
			paralist.add((new StringBuilder("%")).append(StringUtils.processSearch(vesselname)).append("%").toString());
		}
		String portcity = (String)map.get("portcity");
		if (portcity != null && !portcity.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and portcity like ? ").toString();
			paralist.add((new StringBuilder("%")).append(StringUtils.processSearch(portcity)).append("%").toString());
		}
		String tempno = (String)map.get("tempno");
		if (tempno != null && !tempno.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and tempno like ? ").toString();
			paralist.add((new StringBuilder("%")).append(StringUtils.processSearch(tempno)).append("%").toString());
		}
		String appdate_begin = (String)map.get("appdate_begin");
		String appdate_end = (String)map.get("appdate_end");
		if (appdate_begin != null && !appdate_begin.equals("") && appdate_end != null && !appdate_end.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and appdate >= to_date(?,'yyyy-MM-dd')\tand to_date(?,'yyyy-MM-dd')+1\t>= appdate  ").toString();
			paralist.add(appdate_begin);
			paralist.add(appdate_end);
		}
		List list = baseHibernateDAO.getEntityByPageBySql(sql, paralist, begin, numOfEachPage);
		return list;
	}

	public void commit(Business business)
		throws Exception
	{
		baseHibernateDAO.saveEntity(business);
	}

	public List registrySelect()
		throws Exception
	{
		String sql = "select country from Registry  ";
		List list = new ArrayList();
		List returnList = baseHibernateDAO.getEntity(sql, list);
		return returnList;
	}

	public Business getBusinessById(Long id)
		throws Exception
	{
		return (Business)baseHibernateDAO.getEntityById(Business.class, id);
	}

	public List getBusinessDesCountry(Long businessid)
		throws Exception
	{
		List list = new ArrayList();
		list.add(businessid);
		String hql = "  from BusinessDesCountry t where t.businessid = ? order by t.desindex ";
		List resultlist = baseHibernateDAO.getEntity(hql, list);
		return resultlist;
	}

	public List getBusinessByDisposetype(int begin, int numOfEachPage, String operatortype, HashMap map)
		throws Exception
	{
		String hql = "  from    Business bu  where   bu.businessstate = ? ";
		List list = new ArrayList();
		List resultlist = new ArrayList();
		list.add(Long.valueOf(operatortype));
		String businessname_search = (String)map.get("businessname_search");
		if (businessname_search != null && !businessname_search.equals(""))
		{
			hql = (new StringBuilder(String.valueOf(hql))).append(" and bu.businessname like ? escape '\\'  ").toString();
			list.add((new StringBuilder("%")).append(StringUtils.processSearch(businessname_search)).append("%").toString());
		}
		hql = (new StringBuilder(String.valueOf(hql))).append("\torder by bu.checkdate  ").toString();
		resultlist = baseHibernateDAO.getEntities(hql, list, begin, numOfEachPage);
		return resultlist;
	}

	public int getBusinessCountByDisposetype(String operatortype, HashMap map)
		throws Exception
	{
		List list = new ArrayList();
		list.add(Long.valueOf(operatortype));
		String sql = "select count(*) from Business bu where bu.businessstate = ?  ";
		String businessname_search = (String)map.get("businessname_search");
		if (businessname_search != null && !businessname_search.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and bu.businessname like ? escape '\\'  ").toString();
			list.add((new StringBuilder("%")).append(StringUtils.processSearch(businessname_search)).append("%").toString());
		}
		int count = baseHibernateDAO.getEntitiesCountBySql(sql, list);
		return count;
	}

	public String getnextAppno(String datastr, String orgstr, String businessid)
		throws Exception
	{
		List list = new ArrayList();
		String sql = " select * from (  select * from business t where t.appno like ? order by t.appno desc  ) where rownum =1\t";
		list.add((new StringBuilder(String.valueOf(orgstr))).append(datastr).append("%").toString());
		List parlist = baseHibernateDAO.getEntityBySql(sql, list);
		String num = "0001";
		if (parlist != null && parlist.size() > 0)
		{
			HashMap map = (HashMap)parlist.get(0);
			String agm = map.get("APPNO").toString();
			String tem = agm.substring(8, 12);
			int par = Integer.valueOf(tem).intValue() + 1;
			num = StringUtils.leftZero(4, par);
		}
		String str = (new StringBuilder(String.valueOf(orgstr))).append(datastr).append(num).append("-AGM").toString();
		BusinessAppno appno = new BusinessAppno();
		appno.setAppno(str);
		appno.setBusinessid(businessid);
		try
		{
			baseHibernateDAO.saveEntity(appno);
		}
		catch (Exception e)
		{
			throw new Exception("重复索引");
		}
		return str;
	}

	public String nextTempNo(String datastr, String orgstr)
	{
		List list = new ArrayList();
		String sql = " select * from (  select * from business t where t.tempno like ? order by t.id desc  ) where rownum =1\t";
		list.add((new StringBuilder("TEMP")).append(orgstr).append(datastr).append("%").toString());
		List parlist = baseHibernateDAO.getEntityBySql(sql, list);
		String num = "0001";
		if (parlist != null && parlist.size() > 0)
		{
			HashMap map = (HashMap)parlist.get(0);
			String tempno = map.get("TEMPNO").toString();
			String tem = tempno.substring(12, 16);
			int par = Integer.valueOf(tem).intValue() + 1;
			num = StringUtils.leftZero(4, par);
		}
		String str = (new StringBuilder("TEMP")).append(orgstr).append(datastr).append(num).toString();
		return str;
	}

	public List searchBusinessList(HashMap map, int begin, int numOfEachPage)
	{
		List paralist = new ArrayList();
		String sql = "  select *  from ( select t.*,  ( select c1.cityname from pro_city_port p1,city c1  where p1.proid = c1.proid  and p1.cityid = c1.cityid and p1.portid = t.portid  ) portcity  from business t   where  t.businessstate = ?  and t.certid is null) where 1=1 ";
		String bustate = (String)map.get("bustate");
		if (bustate != null && !bustate.equals(""))
			paralist.add(bustate);
		String person_id = (String)map.get("personid");
		if (person_id != null && !person_id.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and  id in  ( select s.businessid from assignment s where  s.iscomplete = 1   and s.isapplay = 1 and s.personto = ?  group by s.businessid  ) ").toString();
			paralist.add(person_id);
		}
		String org4 = (String)map.get("org4");
		String is_child = (String)map.get("is_child");
		if (org4 != null && !org4.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and  id in  ( select s.businessid from assignment s where  s.iscomplete = 1   and s.org4 like ? escape '\\' group by s.businessid  ) ").toString();
			if (is_child != null && is_child.equals("1"))
				paralist.add((new StringBuilder(String.valueOf(StringUtils.processSearch(org4.substring(0, 2))))).append("%").toString());
			else
			if (is_child != null && is_child.equals("2"))
				paralist.add(StringUtils.processSearch(org4));
		}
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
		String accountid = (String)map.get("accountid");
		if (accountid != null && !accountid.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and accountid = ? ").toString();
			paralist.add(accountid);
		}
		List list = baseHibernateDAO.getEntityByPageBySql(sql, paralist, begin, numOfEachPage);
		return list;
	}

	public int searchBusinessListCount(HashMap map)
	{
		List paralist = new ArrayList();
		String sql = "  select count(*)  from ( select t.*,  ( select c1.cityname from pro_city_port p1,city c1  where p1.proid = c1.proid  and p1.cityid = c1.cityid and p1.portid = t.portid  ) portcity  from business t   where  t.businessstate = ?  ";
		String flag = (String)map.get("flag");
		if (flag != null && "flag".equals(flag))
			sql = (new StringBuilder(String.valueOf(sql))).append("and t.certid is null").toString();
		sql = (new StringBuilder(String.valueOf(sql))).append(") where 1=1 ").toString();
		String bustate = (String)map.get("bustate");
		if (bustate != null && !bustate.equals(""))
			paralist.add(bustate);
		String person_id = (String)map.get("personid");
		if (person_id != null && !person_id.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and  id in  ( select s.businessid from assignment s where  s.iscomplete = 1   and s.isapplay = 1 and s.personto = ?  group by s.businessid  ) ").toString();
			paralist.add(person_id);
		}
		String org4 = (String)map.get("org4");
		String is_child = (String)map.get("is_child");
		if (org4 != null && !org4.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and  id in  ( select s.businessid from assignment s where  s.iscomplete = 1   and s.org4 like ? escape '\\' group by s.businessid  ) ").toString();
			if (is_child != null && is_child.equals("1"))
				paralist.add((new StringBuilder(String.valueOf(StringUtils.processSearch(org4.substring(0, 2))))).append("%").toString());
			else
			if (is_child != null && is_child.equals("2"))
				paralist.add(StringUtils.processSearch(org4));
		}
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
		String accountid = (String)map.get("accountid");
		if (accountid != null && !accountid.equals(""))
		{
			sql = (new StringBuilder(String.valueOf(sql))).append(" and accountid = ? ").toString();
			paralist.add(accountid);
		}
		int count = baseHibernateDAO.getEntitiesCountBySql(sql, paralist);
		return count;
	}

	public List getOrganizationLevelByOrgID(String orgid, String condition)
		throws Exception
	{
		com.sinosoft.gypsymoth.pojo.Person person = null;
		List paralist = new ArrayList();
		paralist.add(orgid);
		paralist.add(orgid);
		paralist.add(orgid);
		String sql = " select  m.* ,row_number() over ( order by to_number(m.id)  ) rn  from ( \tselect t.id,t.parent_id,t.is_child,t.org_name,t.org_status \tfrom organization_level t where t.id = ? \tunion \tselect d.id,d.parent_id,d.is_child,d.org_name,d.org_status \tfrom organization_level d where d.parent_id in \t( select d1.id from organization_level d1  where d1.id = ? )\t\tunion  \tselect s.id, s.parent_id,   s.is_child, s.org_name, s.org_status\t \tfrom organization_level s  where s.parent_id in \t(select s1.id from organization_level s1 where s1.parent_id = ? )\t\t) m where m.org_status = 1  ";
		if (condition != null && !condition.equals(""))
			if (condition.equals("3"))
				sql = (new StringBuilder(String.valueOf(sql))).append(" and m.is_child = 1 ").toString();
			else
				sql = (new StringBuilder(String.valueOf(sql))).append(" and m.is_child > ").append(condition).toString();
		List list = baseHibernateDAO.getEntityBySql(sql, paralist);
		return list;
	}

	public List getPersonFromOrg(String rog_id, String typecondition)
		throws Exception
	{
		com.sinosoft.gypsymoth.pojo.Person person = null;
		List paralist = new ArrayList();
		paralist.add(rog_id);
		String sql = (new StringBuilder(" select t.name,t.person_id from person  t where t.org_id = ? ")).append(typecondition).append(" order by t.id ").toString();
		List list = baseHibernateDAO.getEntityBySql(sql, paralist);
		return list;
	}

	public List getOrgByPersonID(String person_id)
		throws Exception
	{
		List paralist = new ArrayList();
		paralist.add(person_id);
		String sql = " select t.person_id,t.org_id,m.is_child , o.org_id org4 \tfrom person t,  organization_level m ,organization o  where  o.id = m.id and t.org_id = m.id  and t.person_id= ? ";
		List list = baseHibernateDAO.getEntityBySql(sql, paralist);
		return list;
	}

	public List getOrg4Byportid(String portid)
		throws Exception
	{
		String org4 = "";
		List list = new ArrayList();
		String sql = " select o.org_id,o.id  from \torganization_port t , port p  , organization o \twhere t.port_id = p.id and t.org_id = o.id and p.id = ? ";
		list.add(portid);
		List returnlist = baseHibernateDAO.getEntityBySql(sql, list);
		return returnlist;
	}

	public List getShipPaymentList(String vesselname)
		throws Exception
	{
		List paralist = new ArrayList();
		String sql = " select * from ship_rule t , ship_type s where s.type = ? and s.id = t.shiptype order by t.minton ";
		paralist.add(vesselname);
		List list = baseHibernateDAO.getEntityBySql(sql, paralist);
		return list;
	}

	public void deleteApply(Long id)
		throws Exception
	{
		baseHibernateDAO.getEntityById(Business.class, id);
	}

	public HibernateTemplate getHibernateTemplate()
	{
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate)
	{
		this.hibernateTemplate = hibernateTemplate;
	}
	/**
	 * 业务查询中修改业务信息
	 * @param business
	 * @param list
	 * @throws Exception
	 * @author lixin
	 */
	public void updateBusinessForSelect(Business business, List list)
		throws Exception
	{
		Date date = new Date();
		baseHibernateDAO.updateEntity(business);
		Long businessid = business.getId();
		Long businessState = business.getBusinessstate();
		List paralist = new ArrayList();
		String sql = " delete from business_des_country b where b.businessid in(   ";
		paralist.add(businessid);
		baseHibernateDAO.deleteBatchBySql(sql, paralist);
		for (int i = 0; i < list.size(); i++)
		{
			BusinessDesCountry businessDesCountry = (BusinessDesCountry)list.get(i);
			businessDesCountry.setBusinessid(businessid);
			baseHibernateDAO.saveEntity(businessDesCountry);
		}

		if (business.getBusinessstate().longValue() > 0L)
		{
			String ACCOUNT_ID = "1";
			Operator operator = new Operator();
			operator.setBusinessid(business.getId());
			operator.setDisposetype(business.getBusinessstate());
			operator.setOperatortime(date);
			operator.setOperatorid(ACCOUNT_ID);
			baseHibernateDAO.saveEntity(operator);
		}
	}

	public List getConfigList()
		throws Exception
	{
		List paralist = new ArrayList();
		String hql = " from Config ";
		List list = baseHibernateDAO.getEntity(hql, paralist);
		return list;
	}
}
