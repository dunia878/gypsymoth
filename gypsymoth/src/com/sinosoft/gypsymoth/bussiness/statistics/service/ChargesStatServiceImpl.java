package com.sinosoft.gypsymoth.bussiness.statistics.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sinosoft.gypsymoth.dao.BaseHibernateDAO;

/**
 * 财务记录统计
 * @author lixin
 *
 */
public class ChargesStatServiceImpl implements ChargesStatService {
	
	private BaseHibernateDAO baseHibernateDAO;

	public BaseHibernateDAO getBaseHibernateDAO() {
		return baseHibernateDAO;
	}

	public void setBaseHibernateDAO(BaseHibernateDAO baseHibernateDAO) {
		this.baseHibernateDAO = baseHibernateDAO;
	}
	
	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.bussiness.statistics.service.ChargesStatService#getOrgList(java.util.Map)
	 */
	public List getOrgList(Map selectMap) throws Exception{
		List list = new ArrayList();
		String sql = " select * from organization_level t ";
		sql+=" where ";
		if(selectMap.get("parentId")!=null && !"".equals(selectMap.get("parentId").toString())){
			sql+=" t.parent_id in("+selectMap.get("parentId").toString()+") and ";
		}
		if(selectMap.get("isChild")!=null && !"".equals(selectMap.get("isChild").toString())){
			sql+=" t.is_child=? and ";
			list.add(selectMap.get("isChild").toString());
		}
		if(selectMap.get("orgId")!=null && !"".equals(selectMap.get("orgId").toString())){
			sql+=" t.id=? and ";
			list.add(selectMap.get("orgId").toString());
		}
		sql+=" 1=1 ";
		sql+=" order by to_number(t.id) ";
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;
	}
	
	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.bussiness.statistics.service.ChargesStatService#getPortList(java.util.Map)
	 */
	public List getPortList(Map selectMap) throws Exception{
		List list = new ArrayList();
		String sql = " select p.* from organization_port o,port p ";
		sql+=" where o.port_id=p.id and ";
		if(selectMap.get("orgId")!=null && !"".equals(selectMap.get("orgId").toString())){
			sql+=" o.org_id in("+ selectMap.get("orgId").toString() +") and ";
		}
		sql+=" 1=1 ";
		sql+=" order by p.id ";
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;
	}
	
	/**
	 * 根据机构ID获取港口ID
	 * @param orgIds
	 * @return
	 * @throws Exception
	 */
	public List getPortIdListByOrgId(String orgIds) throws Exception{
		List list = new ArrayList();
		String sql = " select * from organization_port t ";
		if(orgIds!=null && !"".equals(orgIds)){
			sql+=" where t.org_id in("+orgIds+") ";
		}
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;
	}
	
	/**
	 * 按机构等级获取该等级的全部机构
	 * @param level
	 * @return
	 * @throws Exception
	 */
	public List getOrgListByLevel(int level) throws Exception {
		List list = new ArrayList();
		String sql = " select o.id from organization_level o ";
			sql += " where o.is_child=? ";
			list.add(level);
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;
	}
	
	/**
	 * 综合统计(按机构)
	 * @param selectMap 查询条件
	 * @param selectType 类型(1:检查状态表, 2:付费状态表, 3:分帐状态表)
	 * @return
	 * @throws Exception
	 */
	public List getAllStatByOrg(Map selectMap,int selectType) throws Exception{
		List list = new ArrayList();
		String sql = " select ";
		sql+=" ol.org_name as orgname, ";
		sql+=" decode(m.all_num,null,0,m.all_num) as all_num, ";
		sql+=" decode(m.all_money,null,0,m.all_money) as all_money, ";
		sql+=" decode(m1.is_num,null,0,m1.is_num) as is_num, ";
		sql+=" decode(m1.is_money,null,0,m1.is_money) as is_money, ";
		sql+=" decode(m2.no_money,null,0,m2.no_money) as no_money, ";
		sql+=" decode(m2.no_num, null, 0, m2.no_num) as no_num ";
		sql+=" from ( ";
		sql+=" select org_name as orgname ,count(org_name) as all_num ,sum(money+special) as all_money ";
		sql+=" from ( ";
		sql+=" select distinct o.org_name,p.money,b.special,a.businessid ";
		sql+=" from business b ";
		sql+=" left join payment p on b.id = p.businessid ";
		sql+=" left join ASSIGNMENT a on b.id = a.businessid ";
		sql+=" left join organization_level o on a.orgto = o.id ";
		sql+=" where b.businessstate>2 and ";
		if(selectMap.get("selectOrgid")!=null && !"".equals(selectMap.get("selectOrgid").toString())){
			sql+=" a.orgto in("+selectMap.get("selectOrgid")+") and ";
		}
		if(selectMap.get("selectBeginAppdate")!=null && !"".equals(selectMap.get("selectBeginAppdate").toString())){
			sql+=" b.appdate>=TO_DATE(?,'yyyy-mm') and ";
			list.add(selectMap.get("selectBeginAppdate"));
		}
		if(selectMap.get("selectEndAppdate")!=null && !"".equals(selectMap.get("selectEndAppdate").toString())){
			sql+=" b.appdate<=TO_DATE(?,'yyyy-mm') and ";
			list.add(selectMap.get("selectEndAppdate"));
		}
		sql+=" 1=1 ";
		sql+=" ) ";
		sql+=" group by org_name ";
		sql+=" ) m left join ";
		sql+=" ( ";
		sql+=" select count(org_name) as is_num ,org_name ,sum(money+special) as is_money ";
		sql+=" from ( ";
		sql+=" select distinct o.org_name,p.money,b.special,a.businessid ";
		sql+=" from business b ";
		sql+=" left join payment p on b.id = p.businessid ";
		sql+=" left join ASSIGNMENT a on b.id = a.businessid ";
		sql+=" left join organization_level o on a.orgto = o.id ";
		sql+=" where ";
		if(selectType==1){
			sql+=" b.businessstate=5 and ";
		}
		else if(selectType==2){
			sql+=" b.businessstate>2 and ";
			sql+=" p.paystate in(2,3) and ";
		}
		else if(selectType ==3){
			sql+=" b.businessstate>2 and ";
			sql+=" p.ledgerstate=1 and ";
		}
		if(selectMap.get("selectOrgid")!=null && !"".equals(selectMap.get("selectOrgid").toString())){
			sql+=" a.orgto in("+selectMap.get("selectOrgid")+") and ";
		}
		if(selectMap.get("selectBeginAppdate")!=null && !"".equals(selectMap.get("selectBeginAppdate").toString())){
			sql+=" b.appdate>=TO_DATE(?,'yyyy-mm') and ";
			list.add(selectMap.get("selectBeginAppdate"));
		}
		if(selectMap.get("selectEndAppdate")!=null && !"".equals(selectMap.get("selectEndAppdate").toString())){
			sql+=" b.appdate<=TO_DATE(?,'yyyy-mm') and ";
			list.add(selectMap.get("selectEndAppdate"));
		}
		sql+=" 1=1 ";
		sql+=" ) ";
		sql+=" group by org_name ";
		sql+=" ) m1 on m.orgname = m1.org_name ";
		sql+=" left join ";
		sql+=" ( ";
		sql+=" select count(org_name) as no_num ,org_name,sum(money+special) as no_money ";
		sql+=" from ( ";
		sql+=" select distinct o.org_name,p.money,b.special,a.businessid ";
		sql+=" from business b ";
		sql+=" left join payment p on b.id = p.businessid ";
		sql+=" left join ASSIGNMENT a on b.id = a.businessid ";
		sql+=" left join organization_level o on a.orgto = o.id ";
		sql+=" where ";
		if(selectType==1){
			sql+=" b.businessstate>2 and b.businessstate<5 and ";
		}
		else if(selectType==2){
			sql+=" b.businessstate>2 and ";
			sql+=" p.paystate in(0,1,5) and ";
		}
		else if(selectType ==3){
			sql+=" b.businessstate>2 and ";
			sql+=" p.ledgerstate=0 and ";
		}
		if(selectMap.get("selectOrgid")!=null && !"".equals(selectMap.get("selectOrgid").toString())){
			sql+=" a.orgto in("+selectMap.get("selectOrgid")+") and ";
		}
		if(selectMap.get("selectBeginAppdate")!=null && !"".equals(selectMap.get("selectBeginAppdate").toString())){
			sql+=" b.appdate>=TO_DATE(?,'yyyy-mm') and ";
			list.add(selectMap.get("selectBeginAppdate"));
		}
		if(selectMap.get("selectEndAppdate")!=null && !"".equals(selectMap.get("selectEndAppdate").toString())){
			sql+=" b.appdate<=TO_DATE(?,'yyyy-mm') and ";
			list.add(selectMap.get("selectEndAppdate"));
		}
		sql+=" 1=1 ";
		sql+=" ) ";
		sql+=" group by org_name ";
		sql+=" ) m2 on m.orgname = m2.org_name ";
		sql+=" full join organization_level ol on m.orgname = ol.org_name ";
		if(selectMap.get("selectOrgid")!=null && !"".equals(selectMap.get("selectOrgid").toString())){
			sql+=" where ol.id in("+selectMap.get("selectOrgid")+") ";
		}

		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;
	}
	
	/**
	 * 综合统计(按港口)
	 * @param selectMap 查询条件
	 * @param selectType 类型(1:检查状态表, 2:付费状态表, 3:分帐状态表)
	 * @return
	 * @throws Exception
	 */
	public List getAllStatByPort(Map selectMap,int selectType) throws Exception{
		List list = new ArrayList();
		String sql = " select ";
		sql+=" ol.port_name as orgname, ";
		sql+=" decode(m.all_num,null,0,m.all_num) as all_num, ";
		sql+=" decode(m.all_money,null,0,m.all_money) as all_money, ";
		sql+=" decode(m1.is_num,null,0,m1.is_num) as is_num, ";
		sql+=" decode(m1.is_money,null,0,m1.is_money) as is_money, ";
		sql+=" decode(m2.no_money,null,0,m2.no_money) as no_money, ";
		sql+=" decode(m2.no_num, null, 0, m2.no_num) as no_num ";
		sql+=" from ( ";
		sql+=" select port_name as orgname ,count(port_name) as all_num ,sum(money+special) as all_money ";
		sql+=" from ( ";
		sql+=" select distinct t.port_name,p.money,b.special,b.id ";
		sql+=" from business b ";
		sql+=" left join payment p on b.id = p.businessid ";
		sql+=" left join port t on b.portid = t.id ";
		sql+=" where b.businessstate>2 and ";
		if(selectMap.get("selectOrgid")!=null && !"".equals(selectMap.get("selectOrgid").toString())){
			sql+=" b.portid in("+selectMap.get("selectOrgid")+") and ";
		}
		if(selectMap.get("selectBeginAppdate")!=null && !"".equals(selectMap.get("selectBeginAppdate").toString())){
			sql+=" b.appdate>=TO_DATE(?,'yyyy-mm') and ";
			list.add(selectMap.get("selectBeginAppdate"));
		}
		if(selectMap.get("selectEndAppdate")!=null && !"".equals(selectMap.get("selectEndAppdate").toString())){
			sql+=" b.appdate<=TO_DATE(?,'yyyy-mm') and ";
			list.add(selectMap.get("selectEndAppdate"));
		}
		sql+=" 1=1 ";
		sql+=" ) ";
		sql+=" group by port_name ";
		sql+=" ) m FULL join ";
		sql+=" ( ";
		sql+=" select count(port_name) as is_num ,port_name ,sum(money+special) as is_money ";
		sql+=" from ( ";
		sql+=" select distinct t.port_name,p.money,b.special,b.id ";
		sql+=" from business b ";
		sql+=" left join payment p on b.id = p.businessid ";
		sql+=" left join port t on b.portid = t.id ";
		sql+=" where ";
		if(selectType==1){
			sql+=" b.businessstate=5 and ";
		}
		else if(selectType==2){
			sql+=" b.businessstate>2 and ";
			sql+=" p.paystate in(2,3) and ";
		}
		else if(selectType ==3){
			sql+=" b.businessstate>2 and ";
			sql+=" p.ledgerstate=1 and ";
		}
		if(selectMap.get("selectOrgid")!=null && !"".equals(selectMap.get("selectOrgid").toString())){
			sql+=" b.portid in("+selectMap.get("selectOrgid")+") and ";
		}
		if(selectMap.get("selectBeginAppdate")!=null && !"".equals(selectMap.get("selectBeginAppdate").toString())){
			sql+=" b.appdate>=TO_DATE(?,'yyyy-mm') and ";
			list.add(selectMap.get("selectBeginAppdate"));
		}
		if(selectMap.get("selectEndAppdate")!=null && !"".equals(selectMap.get("selectEndAppdate").toString())){
			sql+=" b.appdate<=TO_DATE(?,'yyyy-mm') and ";
			list.add(selectMap.get("selectEndAppdate"));
		}
		sql+=" 1=1 ";
		sql+=" ) ";
		sql+=" group by port_name ";
		sql+=" ) m1 on m.orgname = m1.port_name ";
		sql+=" FULL join ";
		sql+=" ( ";
		sql+=" select count(port_name) as no_num ,port_name,sum(money+special) as no_money ";
		sql+=" from ( ";
		sql+=" select distinct t.port_name,p.money,b.special,b.id ";
		sql+=" from business b ";
		sql+=" left join payment p on b.id = p.businessid ";
		sql+=" left join port t on b.portid = t.id ";
		sql+=" where ";
		if(selectType==1){
			sql+=" b.businessstate>2 and b.businessstate<5 and ";
		}
		else if(selectType==2){
			sql+=" b.businessstate>2 and ";
			sql+=" p.paystate in(0,1,5) and ";
		}
		else if(selectType ==3){
			sql+=" b.businessstate>2 and ";
			sql+=" p.ledgerstate=0 and ";
		}
		if(selectMap.get("selectOrgid")!=null && !"".equals(selectMap.get("selectOrgid").toString())){
			sql+=" b.portid in("+selectMap.get("selectOrgid")+") and ";
		}
		if(selectMap.get("selectBeginAppdate")!=null && !"".equals(selectMap.get("selectBeginAppdate").toString())){
			sql+=" b.appdate>=TO_DATE(?,'yyyy-mm') and ";
			list.add(selectMap.get("selectBeginAppdate"));
		}
		if(selectMap.get("selectEndAppdate")!=null && !"".equals(selectMap.get("selectEndAppdate").toString())){
			sql+=" b.appdate<=TO_DATE(?,'yyyy-mm') and ";
			list.add(selectMap.get("selectEndAppdate"));
		}
		sql+=" 1=1 ";
		sql+=" ) ";
		sql+=" group by port_name ";
		sql+=" ) m2 on m.orgname = m2.port_name ";
		sql+=" full join port ol on m.orgname = ol.port_name ";
		if(selectMap.get("selectOrgid")!=null && !"".equals(selectMap.get("selectOrgid").toString())){
			sql+=" where ol.id in("+selectMap.get("selectOrgid")+") ";
		}

		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;
	}
	
	/**
	 * 综合统计(全国)
	 * @param selectMap
	 * @param selectType
	 * @return
	 * @throws Exception
	 */
	public List getAllStat(Map selectMap,int selectType) throws Exception{
		List list = new ArrayList();
		String sql = " select * from( ";
		sql+=" select '检验公司' as orgname,count(id) as all_num,sum(money + special) as all_money ";
		sql+=" from ( ";
		sql+=" select distinct p.money, b.special, b.id ";
		sql+=" from business b ";
		sql+=" left join payment p on b.id = p.businessid ";
		sql+=" where b.businessstate>2 and ";
		if(selectMap.get("selectBeginAppdate")!=null && !"".equals(selectMap.get("selectBeginAppdate").toString())){
			sql+=" b.appdate>=TO_DATE(?,'yyyy-mm') and ";
			list.add(selectMap.get("selectBeginAppdate"));
		}
		if(selectMap.get("selectEndAppdate")!=null && !"".equals(selectMap.get("selectEndAppdate").toString())){
			sql+=" b.appdate<=TO_DATE(?,'yyyy-mm') and ";
			list.add(selectMap.get("selectEndAppdate"));
		}
		sql+=" 1=1 ";
		sql+=" ) ";
		sql+=" ) m FULL join ";
		sql+=" ( ";
		sql+=" select count(id) as is_num, '检验公司' as org_name, sum(money + special) as is_money ";
		sql+=" from ( ";
		sql+=" select distinct p.money, b.special, b.id ";
		sql+=" from business b ";
		sql+=" left join payment p on b.id = p.businessid ";
		sql+=" where ";
		if(selectType==1){
			sql+=" b.businessstate=5 and ";
		}
		else if(selectType==2){
			sql+=" b.businessstate>2 and ";
			sql+=" p.paystate in(2,3) and ";
		}
		else if(selectType ==3){
			sql+=" b.businessstate>2 and ";
			sql+=" p.ledgerstate=1 and ";
		}
		if(selectMap.get("selectBeginAppdate")!=null && !"".equals(selectMap.get("selectBeginAppdate").toString())){
			sql+=" b.appdate>=TO_DATE(?,'yyyy-mm') and ";
			list.add(selectMap.get("selectBeginAppdate"));
		}
		if(selectMap.get("selectEndAppdate")!=null && !"".equals(selectMap.get("selectEndAppdate").toString())){
			sql+=" b.appdate<=TO_DATE(?,'yyyy-mm') and ";
			list.add(selectMap.get("selectEndAppdate"));
		}
		sql+=" 1=1 ";
		sql+=" ) ";
		sql+=" ) m1 on m.orgname = m1.org_name ";
		sql+=" FULL join ";
		sql+=" ( ";
		sql+=" select count(id) as no_num,'检验公司' as org_name,sum(money + special) as no_money ";
		sql+=" from ( ";
		sql+=" select distinct p.money, b.special, b.id ";
		sql+=" from business b ";
		sql+=" left join payment p on b.id = p.businessid ";
		sql+=" where ";
		if(selectType==1){
			sql+=" b.businessstate>2 and b.businessstate<5 and ";
		}
		else if(selectType==2){
			sql+=" b.businessstate>2 and ";
			sql+=" p.paystate in(0,1,5) and ";
		}
		else if(selectType ==3){
			sql+=" b.businessstate>2 and ";
			sql+=" p.ledgerstate=0 and ";
		}
		if(selectMap.get("selectBeginAppdate")!=null && !"".equals(selectMap.get("selectBeginAppdate").toString())){
			sql+=" b.appdate>=TO_DATE(?,'yyyy-mm') and ";
			list.add(selectMap.get("selectBeginAppdate"));
		}
		if(selectMap.get("selectEndAppdate")!=null && !"".equals(selectMap.get("selectEndAppdate").toString())){
			sql+=" b.appdate<=TO_DATE(?,'yyyy-mm') and ";
			list.add(selectMap.get("selectEndAppdate"));
		}
		sql+=" 1=1 ";
		sql+=" ) ";
		sql+=" ) m2 on m.orgname = m2.org_name ";

		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;
	}
	
	
	/**
	 * 分账明细(按机构)
	 * @param selectMap
	 * @return
	 * @throws Exception
	 */
	public List getLedgerStatByOrg(Map selectMap) throws Exception{
		List list = new ArrayList();
		String sql = " select ";
		
		if(selectMap.get("selectPortid")!=null && !"".equals(selectMap.get("selectPortid").toString())){
			sql+=" ol.port_name as orgname, ";
		}
		else if(selectMap.get("selectOrgid")!=null && !"".equals(selectMap.get("selectOrgid").toString())){
			sql+=" ol.org_name as orgname, ";
		}
		else{
			sql+=" '检验公司' as orgname, ";
		}
		sql+=" decode(sum(total),null,0,sum(total)) as total, ";
		sql+=" decode(sum(firstcompay),null,0,sum(firstcompay)) as onemoney, ";
		sql+=" decode(sum(secondcompay),null,0,sum(secondcompay)) as twomoney, ";
		sql+=" decode(sum(thirdcompay),null,0,sum(thirdcompay)) as threemoney, ";
		sql+=" decode(max(onename),null,'无',max(onename)) as onename, ";
		sql+=" decode(max(twoname),null,'无',max(twoname)) as twoname, ";
		sql+=" decode(max(threename),null,'无',max(threename)) as threename ";
		sql+=" from( ";
		sql+=" select a.*, ";
		sql+=" o1.org_name as onename, ";
		sql+=" o2.org_name as twoname, ";
		sql+=" o3.org_name as threename, ";
		sql+=" o4.org_name as orgname, ";
		sql+=" a.firstcompay+a.secondcompay+a.thirdcompay as total ";
		sql+=" from (select distinct p.businessid, ";
		sql+=" p.firstcomid, ";
		sql+=" p.firstcompay, ";
		sql+=" p.secondcomid, ";
		sql+=" p.secondcompay, ";
		sql+=" p.thirdcomid, ";
		sql+=" p.thirdcompay, ";
		sql+=" b.portid, ";
		sql+=" b.appdate, ";
		sql+=" s.orgto ";
		sql+=" from BUSINESS b, PAYMENT p,assignment s ";
		sql+=" where b.id = p.businessid ";
		sql+=" and b.id = s.businessid ";
		sql+=" and s.isapplay = 1 ";
		//sql+=" and s.iscomplete = 1 ";
		//sql+=" and p.ledgerstate=1 ";
		sql+=" ) a ";
		sql+=" left join organization_level o1 on a.firstcomid = o1.id ";
		sql+=" left join organization_level o2 on a.secondcomid = o2.id ";
		sql+=" left join organization_level o3 on a.thirdcomid = o3.id ";
		sql+=" left join organization_level o4 on a.orgto = o4.id ";
		sql+=" where ";
		if(selectMap.get("selectOrgid")!=null && !"".equals(selectMap.get("selectOrgid").toString())){
			sql+=" a.orgto in("+selectMap.get("selectOrgid")+") and ";
		}
		if(selectMap.get("selectPortid")!=null && !"".equals(selectMap.get("selectPortid").toString())){
			sql+=" a.portid in("+selectMap.get("selectPortid")+") and ";
		}
		if(selectMap.get("selectBeginAppdate")!=null && !"".equals(selectMap.get("selectBeginAppdate").toString())){
			sql+=" a.appdate>=TO_DATE(?,'yyyy-mm') and ";
			list.add(selectMap.get("selectBeginAppdate"));
		}
		if(selectMap.get("selectEndAppdate")!=null && !"".equals(selectMap.get("selectEndAppdate").toString())){
			sql+=" a.appdate<=TO_DATE(?,'yyyy-mm') and ";
			list.add(selectMap.get("selectEndAppdate"));
		}
		sql+=" 1=1 ";
		sql+=" )m1 ";
		if(selectMap.get("selectOrgid")!=null && !"".equals(selectMap.get("selectOrgid").toString())){
			sql+=" full join ";
			sql+=" organization_level ol on ol.org_name = m1.orgname ";
			sql+=" where ol.id in("+selectMap.get("selectOrgid")+") ";
		}
		if(selectMap.get("selectPortid")!=null && !"".equals(selectMap.get("selectPortid").toString())){
			sql+=" full join ";
			sql+=" port ol on ol.id = m1.portid ";
			sql+=" where ol.id in("+selectMap.get("selectPortid")+") ";
		}
		if(selectMap.get("selectPortid")!=null && !"".equals(selectMap.get("selectPortid").toString())){
			sql+=" group by ol.port_name ";
		}
		if(selectMap.get("selectOrgid")!=null && !"".equals(selectMap.get("selectOrgid").toString())){
			sql+=" group by ol.org_name ";
		}
		
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;
	}
	
	/**
	 * 分账明细(按业务单)
	 * @param selectMap
	 * @return
	 * @throws Exception
	 */
	public List getLedgerStatByApp(Map selectMap) throws Exception{
		List list = new ArrayList();
		String sql = " select distinct ";
		sql+=" a.appno, ";
		sql+=" decode(a.firstcompay,null,0,a.firstcompay) as firstcompay, ";
		sql+=" decode(a.secondcompay,null,0,a.secondcompay) as secondcompay, ";
		sql+=" decode(a.thirdcompay,null,0,a.thirdcompay) as thirdcompay, ";
		sql+=" decode(o1.org_name,null,'无',o1.org_name) as onename, ";
		sql+=" decode(o2.org_name,null,'无',o2.org_name) as twoname, ";
		sql+=" decode(o3.org_name,null,'无',o3.org_name) as threename, ";
		sql+=" decode(a.firstcompay + a.secondcompay + a.thirdcompay,null,0,a.firstcompay + a.secondcompay + a.thirdcompay) as total ";

		//sql+=" ,o4.org_name as orgname ";
		sql+=" from (select distinct b.appno, ";
		sql+=" p.firstcomid, ";
		sql+=" p.firstcompay, ";
		sql+=" p.secondcomid, ";
		sql+=" p.secondcompay, ";
		sql+=" p.thirdcomid, ";
		sql+=" p.thirdcompay, ";
		sql+=" b.portid, ";
		sql+=" b.appdate ";
		sql+=" ,s.orgto ";
		sql+=" from BUSINESS b, PAYMENT p,assignment s ";
		sql+=" where b.id = p.businessid ";
		sql+=" and b.id = s.businessid ";
		sql+=" and s.isapplay = 1 ";
		//sql+=" and s.iscomplete = 1 ";
		//sql+=" and p.ledgerstate=1 ";
		sql+=" ) a ";
		sql+=" left join organization_level o1 on a.firstcomid = o1.id ";
		sql+=" left join organization_level o2 on a.secondcomid = o2.id ";
		sql+=" left join organization_level o3 on a.thirdcomid = o3.id ";
		sql+=" left join organization_level o4 on a.orgto = o4.id ";
		sql+=" where ";
		if(selectMap.get("selectOrgid")!=null && !"".equals(selectMap.get("selectOrgid").toString())){
			sql+=" a.orgto in("+selectMap.get("selectOrgid")+") and ";
		}
		if(selectMap.get("selectPortid")!=null && !"".equals(selectMap.get("selectPortid").toString())){
			sql+=" a.portid in("+selectMap.get("selectPortid")+") and ";
		}
		if(selectMap.get("selectBeginAppdate")!=null && !"".equals(selectMap.get("selectBeginAppdate").toString())){
			sql+=" a.appdate>=TO_DATE(?,'yyyy-mm') and ";
			list.add(selectMap.get("selectBeginAppdate"));
		}
		if(selectMap.get("selectEndAppdate")!=null && !"".equals(selectMap.get("selectEndAppdate").toString())){
			sql+=" a.appdate<=TO_DATE(?,'yyyy-mm') and ";
			list.add(selectMap.get("selectEndAppdate"));
		}
		sql+=" 1=1 ";
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;
	}

}
