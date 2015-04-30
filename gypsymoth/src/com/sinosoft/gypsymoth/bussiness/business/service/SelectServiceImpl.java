package com.sinosoft.gypsymoth.bussiness.business.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sinosoft.gypsymoth.dao.BaseHibernateDAO;
import com.sinosoft.gypsymoth.pojo.Assignment;
import com.sinosoft.gypsymoth.pojo.Business;
import com.sinosoft.gypsymoth.pojo.BusinessAppno;
import com.sinosoft.gypsymoth.pojo.BusinessDesCountry;
import com.sinosoft.gypsymoth.pojo.Examinelog;
import com.sinosoft.gypsymoth.pojo.Operator;
import com.sinosoft.gypsymoth.pojo.Payment;

/**
 * 业务查询
 * 
 * @author lixin
 * 
 */
public class SelectServiceImpl implements SelectService {

	private BaseHibernateDAO baseHibernateDAO;

	public BaseHibernateDAO getBaseHibernateDAO() {
		return baseHibernateDAO;
	}

	public void setBaseHibernateDAO(BaseHibernateDAO baseHibernateDAO) {
		this.baseHibernateDAO = baseHibernateDAO;
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.bussiness.business.service.SelectService#getSelectByPage(java.util.Map, int, int)
	 */
	public List getSelectByPage(Map selectMap, int begin, int numOfEachPage)
			throws Exception {
		List list = new ArrayList();
		String sql = " select m.*,c.cityname,ac.account_name,c.englishcityname,o.port_sname from ( ";
		sql += " select distinct b.id as businessid, ";
		sql += " b.vesselname, ";
		sql += " b.businessstate, ";
		sql += " b.businessname, ";
		sql += " b.portid, ";
		sql += " b.orgid, ";
		sql += " b.appno, ";
		sql += " b.appdate, ";
		sql += " b.tempno, ";
		sql += " b.accountid, ";
		// sql += " b.vesselname, ";
		//以下3列是导出北美报表用到的
		 sql += " b.vesseltype, ";
		 sql += " b.registry, ";
		 sql += " b.imo, ";
		sql += " p.paystate, ";
		sql += " p.ledgerstate, ";
		sql += " e.operatortime ";
		sql += " from business b ";
		sql += " left join payment p on b.id = p.businessid ";
		sql += " left join EXAMINELOG e on b.id = e.businessid ";
		if(selectMap.get("selectOrgid")!=null && !"".equals(selectMap.get("selectOrgid").toString())){
			sql+=" left join assignment a on b.id = a.businessid ";
			sql+=" where ";
			sql+=" ( a.isapplay=1 ";
			sql+=" and a.orgto in("+selectMap.get("selectOrgid")+")) ";
			sql+=" or(a.isapplay is null and b.portorgid in("+selectMap.get("selectOrgid")+") ) ";
			sql+=" or (a.isapplay = 1 and a.assignerstate=0 and b.portorgid in ("+selectMap.get("selectOrgid")+")) ";
		}
		if(selectMap.get("accountId")!=null && !"".equals(selectMap.get("accountId").toString())){
			sql+=" or(b.accountid =?) ";
			list.add(selectMap.get("accountId"));
		}
		sql += " ) m, ";
		sql += " port o,city c,promary y,account ac ";
		sql += " where ";
		sql += " m.portid = o.id and ";
		sql += " o.cityid = c.cityid and ";
		sql += " c.proid = y.proid and ";
		sql += " o.proid = c.proid and ";
		sql += " m.accountid = ac.account_id and ";
		if(selectMap.get("selectVesselname")!=null && !"".equals(selectMap.get("selectVesselname").toString())){
			sql+=" upper(m.vesselname) like ? and ";
			list.add("%"+selectMap.get("selectVesselname")+"%");
		}
		if(selectMap.get("selectBusinessid")!=null && !"".equals(selectMap.get("selectBusinessid").toString())){
			sql+=" m.businessid= ? and ";
			list.add(selectMap.get("selectBusinessid"));
		}
		if(selectMap.get("selectLedgerstate")!=null && !"".equals(selectMap.get("selectLedgerstate").toString())){
			sql+=" m.ledgerstate= ? and ";
			list.add(selectMap.get("selectLedgerstate"));
		}
		if(selectMap.get("selectBusinesstate")!=null && !"".equals(selectMap.get("selectBusinesstate").toString())){
			sql+=" m.businessstate= ? and ";
			list.add(selectMap.get("selectBusinesstate"));
		}
		if(selectMap.get("selectPaystate")!=null && !"".equals(selectMap.get("selectPaystate").toString())){
			sql+=" m.paystate= ? and ";
			list.add(selectMap.get("selectPaystate"));
		}
		if(selectMap.get("businessname")!=null && !"".equals(selectMap.get("businessname").toString())){
			sql+=" m.BUSINESSNAME= ? and ";
			list.add(selectMap.get("businessname"));
		}
		if(selectMap.get("selectBusinessname")!=null && !"".equals(selectMap.get("selectBusinessname").toString())){
			sql+=" upper(m.BUSINESSNAME) like ? and ";
			list.add("%"+selectMap.get("selectBusinessname")+"%");
		}
		if(selectMap.get("selectAppno")!=null && !"".equals(selectMap.get("selectAppno").toString())){
			sql+=" upper(m.appno)= ? and ";
			list.add(selectMap.get("selectAppno"));
		}
		if(selectMap.get("selectCityname")!=null && !"".equals(selectMap.get("selectCityname").toString())){
			sql+=" c.cityname like ? and ";
			list.add("%"+selectMap.get("selectCityname")+"%");
		}
		if(selectMap.get("selectTempno")!=null && !"".equals(selectMap.get("selectTempno").toString())){
			sql+=" upper(m.tempno)= ? and ";
			list.add(selectMap.get("selectTempno"));
		}
		if(selectMap.get("selectBeginAppdate")!=null && !"".equals(selectMap.get("selectBeginAppdate").toString())){
			sql+=" m.appdate>=TO_DATE(?,'yyyy-mm-dd') and ";
			list.add(selectMap.get("selectBeginAppdate"));
		}
		if(selectMap.get("selectEndAppdate")!=null && !"".equals(selectMap.get("selectEndAppdate").toString())){
			sql+=" m.appdate<=TO_DATE(?,'yyyy-mm-dd') and ";
			list.add(selectMap.get("selectEndAppdate"));
		}
		if(selectMap.get("selectBeginOperatortime")!=null && !"".equals(selectMap.get("selectBeginOperatortime").toString())){
			sql+=" m.operatortime>=TO_DATE(?,'yyyy-mm-dd') and ";
			list.add(selectMap.get("selectBeginOperatortime"));
		}
		if(selectMap.get("selectEndOperatortime")!=null && !"".equals(selectMap.get("selectEndOperatortime").toString())){
			sql+=" m.operatortime<=TO_DATE(?,'yyyy-mm-dd') and ";
			list.add(selectMap.get("selectEndOperatortime"));
		}
		/*
		if(selectMap.get("selectOrgid")!=null && !"".equals(selectMap.get("selectOrgid").toString())){
			sql+=" m.orgid like ? and ";
			list.add(selectMap.get("selectOrgid")+"%");
		}*/
		
		//add by guodingjun 
		if(selectMap.get("account_name")!=null && !"".equals(selectMap.get("account_name").toString())){
			sql+=" AC.account_name like ? and ";
			list.add("%"+selectMap.get("account_name") +"%");
		}
		
		sql += " 1=1 ";
		sql += " order by  m.appdate desc ";
		List returnList;
		if(begin == -1 && numOfEachPage == -1) {
			 returnList = baseHibernateDAO.getEntityBySql(sql, list);
		}else {
		returnList = baseHibernateDAO.getEntityByPageBySql(sql, list,
				begin, numOfEachPage);
		}
		return returnList;
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.bussiness.business.service.SelectService#getSelectCount(java.util.Map)
	 */
	public int getSelectCount(Map selectMap) throws Exception {
		List list = new ArrayList();
		String sql = " select count(*) from ( ";
		sql += " select distinct b.id as businessid, ";
		sql += " b.vesselname, ";
		sql += " b.businessstate, ";
		sql += " b.businessname, ";
		sql += " b.portid, ";
		sql += " b.orgid, ";
		sql += " b.appno, ";
		sql += " b.appdate, ";
		sql += " b.tempno, ";
		sql += " b.accountid, ";
		sql += " p.paystate, ";
		sql += " p.ledgerstate, ";
		sql += " e.operatortime ";
		sql += " from business b ";
		sql += " left join payment p on b.id = p.businessid ";
		sql += " left join EXAMINELOG e on b.id = e.businessid ";
		if(selectMap.get("selectOrgid")!=null && !"".equals(selectMap.get("selectOrgid").toString())){
			sql+=" left join assignment a on b.id = a.businessid ";
			sql+=" where ";
			sql+=" ( a.isapplay=1 ";
			sql+=" and a.orgto in("+selectMap.get("selectOrgid")+")) ";
			sql+=" or(a.isapplay is null and b.portorgid in("+selectMap.get("selectOrgid")+") ) ";
			sql+=" or (a.isapplay = 1 and a.assignerstate=0 and b.portorgid in ("+selectMap.get("selectOrgid")+")) ";
		}
		if(selectMap.get("accountId")!=null && !"".equals(selectMap.get("accountId").toString())){
			sql+=" or(b.accountid =?) ";
			list.add(selectMap.get("accountId"));
		}
		
		sql += " ) m, ";
		sql += " port o,city c,promary y,account ac ";
		sql += " where ";
		sql += " m.portid = o.id and ";
		sql += " o.cityid = c.cityid and ";
		sql += " c.proid = y.proid and ";
		sql += " o.proid = c.proid and ";
		sql += " m.accountid = ac.account_id and ";
		if(selectMap.get("selectVesselname")!=null && !"".equals(selectMap.get("selectVesselname").toString())){
			sql+=" upper(m.vesselname) like ? and ";
			list.add("%"+selectMap.get("selectVesselname")+"%");
		}
		if(selectMap.get("selectBusinessid")!=null && !"".equals(selectMap.get("selectBusinessid").toString())){
			sql+=" m.businessid= ? and ";
			list.add(selectMap.get("selectBusinessid"));
		}
		if(selectMap.get("selectLedgerstate")!=null && !"".equals(selectMap.get("selectLedgerstate").toString())){
			sql+=" m.ledgerstate= ? and ";
			list.add(selectMap.get("selectLedgerstate"));
		}
		if(selectMap.get("selectBusinesstate")!=null && !"".equals(selectMap.get("selectBusinesstate").toString())){
			sql+=" m.businessstate= ? and ";
			list.add(selectMap.get("selectBusinesstate"));
		}
		if(selectMap.get("selectPaystate")!=null && !"".equals(selectMap.get("selectPaystate").toString())){
			sql+=" m.paystate= ? and ";
			list.add(selectMap.get("selectPaystate"));
		}
		if(selectMap.get("businessname")!=null && !"".equals(selectMap.get("businessname").toString())){
			sql+=" m.BUSINESSNAME= ? and ";
			list.add(selectMap.get("businessname"));
		}
		if(selectMap.get("selectBusinessname")!=null && !"".equals(selectMap.get("selectBusinessname").toString())){
			sql+=" upper(m.BUSINESSNAME) like ? and ";
			list.add("%"+selectMap.get("selectBusinessname")+"%");
		}
		if(selectMap.get("selectAppno")!=null && !"".equals(selectMap.get("selectAppno").toString())){
			sql+=" upper(m.appno)= ? and ";
			list.add(selectMap.get("selectAppno"));
		}
		if(selectMap.get("selectCityname")!=null && !"".equals(selectMap.get("selectCityname").toString())){
			sql+=" c.cityname like ? and ";
			list.add("%"+selectMap.get("selectCityname")+"%");
		}
		if(selectMap.get("selectTempno")!=null && !"".equals(selectMap.get("selectTempno").toString())){
			sql+=" upper(m.tempno)= ? and ";
			list.add(selectMap.get("selectTempno"));
		}
		if(selectMap.get("selectBeginAppdate")!=null && !"".equals(selectMap.get("selectBeginAppdate").toString())){
			sql+=" m.appdate>=TO_DATE(?,'yyyy-mm-dd') and ";
			list.add(selectMap.get("selectBeginAppdate"));
		}
		if(selectMap.get("selectEndAppdate")!=null && !"".equals(selectMap.get("selectEndAppdate").toString())){
			sql+=" m.appdate<=TO_DATE(?,'yyyy-mm-dd') and ";
			list.add(selectMap.get("selectEndAppdate"));
		}
		if(selectMap.get("selectBeginOperatortime")!=null && !"".equals(selectMap.get("selectBeginOperatortime").toString())){
			sql+=" m.operatortime>=TO_DATE(?,'yyyy-mm-dd') and ";
			list.add(selectMap.get("selectBeginOperatortime"));
		}
		if(selectMap.get("selectEndOperatortime")!=null && !"".equals(selectMap.get("selectEndOperatortime").toString())){
			sql+=" m.operatortime<=TO_DATE(?,'yyyy-mm-dd') and ";
			list.add(selectMap.get("selectEndOperatortime"));
		}
		/*
		if(selectMap.get("selectOrgid")!=null && !"".equals(selectMap.get("selectOrgid").toString())){
			sql+=" m.orgid like ? and ";
			list.add(selectMap.get("selectOrgid")+"%");
		}*/
		//add by guodingjun 
		if(selectMap.get("account_name")!=null && !"".equals(selectMap.get("account_name").toString())){
			sql+=" AC.account_name like ? and ";
			list.add("%"+selectMap.get("account_name") +"%");
		}
		
		sql += " 1=1";

		int count = baseHibernateDAO.getEntitiesCountBySql(sql, list);
		return count;

	}

	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.bussiness.business.service.SelectService#getSelectByBusinessid(java.lang.Integer)
	 */
	public List getSelectByBusinessid(Integer businessid) throws Exception {
		List list = new ArrayList();
		
			String sql = " select m.*, c.cityname,ac.account_name,c.englishcityname from ( ";
			sql += " select a.*, ";
			sql += " o1.org_name as onename, ";
			sql += " o2.org_name as twoname, ";
			sql += " o3.org_name as threename ";
			sql += " from (select distinct b.*, ";
			sql += " p.paystate, ";
			sql += " p.specialpay, ";
			sql += " p.paymentdate, ";
			sql += " p.ledgerstate, ";
			sql += " p.firstcompay, ";
			sql += " p.secondcompay, ";
			sql += " p.thirdcompay, ";
			sql += " p.firstcomid, ";
			sql += " p.secondcomid, ";
			sql += " p.thirdcomid, ";
			sql += " p.money, ";
			sql += " p.amount, ";
			sql += " p.currency, ";
			sql += " p.ledgerdate, ";
			sql += " p.payment_by, ";
			sql += " t.port_name, ";
			sql += " t.port_sname, ";
			sql += " e.operatortime, ";
			sql += " e.gypsymoth1, ";
			sql += " e.gypsymoth2, ";
			sql += " e.gypsymoth3, ";
			sql += " e.gypsymoth4, ";
			sql += " e.doubt, ";
			sql += " e.certificate, ";
			sql += " e.examrecord, ";
			sql += " e.workphoto, ";
			sql += " e.shipinfo, ";
			sql += " e.stopportinfo,";
			sql += " e.gypsymothphoto, ";
			sql += " b.certid as certno ";
			sql += " from BUSINESS b ";
			sql += " left join PAYMENT p on b.id = p.businessid ";
			sql += " left join examinelog e on b.id = e.businessid ";
			sql += " left join port t on b.portid = t.id ";
			//sql += " left join CERTIFICATE c on b.certid = c.id ";
			sql += " where b.id = ?) a ";
			sql += " left join organization_level o1 on a.firstcomid = o1.id ";
			sql += " left join organization_level o2 on a.secondcomid = o2.id ";
			sql += " left join organization_level o3 on a.thirdcomid = o3.id ";
			sql += " )m,port o,city c,promary y,account ac ";
			sql += " where m.portid = o.id ";
			sql += " and o.cityid = c.cityid ";
			sql += " and c.proid = y.proid ";
			sql += " and o.proid = c.proid ";
			sql += " and ac.account_id = m.accountid ";

		list.add(businessid);
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;
	}
	
	/**
	 * 获取该单业务，业务检查所需停靠和途经港口
	 * @param appno
	 * @return
	 * @throws Exception
	 */
	public List getExamPort(String appno) throws Exception {
		List list = new ArrayList();
		String sql = " select e.port_name,e.stop_time,e.stop_status,n.nation_name from EXAM_PORT e,nation n where e.app_id=? and e.nationid = n.nation_id ";
		list.add(appno);
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;
		
	}
	
	/**
	 * 获取该单业务的操作记录
	 * @param businessid
	 * @return
	 * @throws Exception
	 */
	public List getOperator(BigDecimal businessid) throws Exception {
		List list = new ArrayList();
		String sql = " select o.disposetype, o.operatortime, a.account_name ";
		sql += " from operator o ";
		sql += " left join account a on o.operatorid = a.account_id ";
		sql += " where o.BUSINESSID =? and o.DISPOSETYPE!=0 ";
		sql += " order by o.OPERATORTIME asc";
		list.add(businessid);
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;
	}
	
	/**
	 * 获取目的国及港口
	 */
	public List getDesCountry(BigDecimal businessid) throws Exception {
		List list = new ArrayList();
		String sql = " select * from business_des_country b where b.BUSINESSID=? order by b.DESINDEX";
		list.add(businessid);
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;
	}
	
	/**
	 * 获取实施检查的公司名称
	 * @param businessid
	 * @return
	 * @throws Exception
	 */
	public String getorgName(BigDecimal businessid) throws Exception {
		String orgName="";
		List list = new ArrayList();
		Map map = new HashMap();
		String sql = " select distinct o.org_sname from ASSIGNMENT a,ORGANIZATION o where a.businessid=? and a.assignrole=2 and a.orgto=o.id ";
		list.add(businessid);
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		if(returnList.size()>0){
			map = (Map)returnList.get(0);
			orgName = (String)map.get("ORG_SNAME");
		}
		return orgName;
	}
	
	/**
	 * 更新业务信息
	 */
	public void updateSelect(Business business) throws Exception {
		baseHibernateDAO.updateEntity(business);
	}
	
	/**
	 * 根据用户ID获取客户的公司英文名称
	 * @param accountId
	 * @return
	 * @throws Exception
	 */
	public List getBusinessname(String accountId) throws Exception {
		List list = new ArrayList();
		String sql = " select c.CO_EN_NAME from CLIENT c where c.ACCOUNT_ID=? ";
		list.add(accountId);
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;
	}
	
	/**
	 * 根据人员的机构ID获取该人员所在机构及其下级机构的机构列表
	 * @param selectMap
	 * @return
	 * @throws Exception
	 */
	public List getOrgList(Map selectMap) throws Exception {

		List list = new ArrayList();
		String sql = " select o1.id, o1.parent_id, o1.org_sname, o1.org_id, 0 as disabled ";
		sql += " from organization o1 ";
		sql += " where  ";
		if(selectMap.get("id")!=null && !"".equals(selectMap.get("id").toString())){
			sql+=" (o1.id = ? or o1.parent_id = ?) and ";
			list.add(selectMap.get("id"));
			list.add(selectMap.get("id"));
		}
		sql += " o1.parent_id != '0'and ";
		sql += " 1=1 ";
		
		if(selectMap.get("isChild")!=null && "2".equals(selectMap.get("isChild").toString())){
			sql += " union ";
			sql += " select o2.id, o2.parent_id, o2.org_sname, o2.org_id, 1 as disabled ";
			sql += " from organization o2 ";
			sql += " where o2.id in ( ";
			sql += " select o3.parent_id ";
			sql += " from organization o3 ";
			sql += " where ";
			if(selectMap.get("id")!=null && !"".equals(selectMap.get("id").toString())){
				sql+=" (o3.id = ? or o3.parent_id = ?) and ";
				list.add(selectMap.get("id"));
				list.add(selectMap.get("id"));
			}
			sql += " 1=1 ";
			sql += " ) and o2.parent_id != '0' ";
		}
		

		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;
	}
	
	/**
	 * 根据机构Id获取下属子机构的ID（不算顶级检验公司）
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List getOrgIdList(String id) throws Exception {
		List list = new ArrayList();
		String sql = " select o.id from organization o ";
		if(id!=null && !"".equals(id)){
			sql += " where o.parent_id=? ";
			list.add(id);
		}
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;
	}
	
	/**
	 * 根据业务ID获取分配完成状态下，该业务所属机构的ID
	 * @param Bussinessid
	 * @return
	 * @throws Exception
	 */
	public List getBusinessOrg(Integer Bussinessid) throws Exception {
		List list = new ArrayList();
		String sql = " select distinct a.orgto from ASSIGNMENT a where a.BUSINESSID=? and a.isapplay=1 and a.iscomplete=1 ";
		list.add(Bussinessid);
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;
	}
	
	/**
	 * 更改检查是否合格
	 * @param businessid
	 * @param qualified
	 * @throws Exception
	 */
	public void updateQualified(Integer businessid,Integer qualified) throws Exception {
		String sql = " update BUSINESS set QUALIFIED=? where ID=? ";
		List list = new ArrayList();
		list.add(qualified);
		list.add(businessid);
		baseHibernateDAO.updateBatchBySql(sql, list);
	}

	public void deleteBusiness(Long id) {
		baseHibernateDAO.deleteEntity(Business.class, id);
		List list1 = new ArrayList();
		List list2 = new ArrayList();
		List list3 = new ArrayList();
		List list4 = new ArrayList();
		List list5 = new ArrayList();
		List list6 = new ArrayList();
		List list7 = new ArrayList();
		List list8 = new ArrayList();
		List list9 = new ArrayList();
		List list10 = new ArrayList();
		List list11 = new ArrayList();
		List list12 = new ArrayList();
		List list13 = new ArrayList();
		List list14 = new ArrayList();
		List list15 = new ArrayList();
		List list16 = new ArrayList();
		list1.add(id);
		String hql1 = "from Assignment a where a.businessid=?";
		List assignmentList =  baseHibernateDAO.getEntity(hql1, list1);
		if(assignmentList.size() > 0) {
			String sql2 = "delete from ASSIGNMENT a where a.id in (";
			for(int i = 0; i < assignmentList.size(); i ++) {
				Assignment assignment = (Assignment) assignmentList.get(i);
				list2.add(assignment.getId());
			}
			baseHibernateDAO.deleteBatchBySql(sql2, list2);
		}
		String hql3 = "from Operator o where o.businessid=?";
		list3.add(id);
		List operatorList = baseHibernateDAO.getEntity(hql3, list3);
		if(operatorList.size() > 0) {
			String sql4 = "delete from operator o where o.id in(";
			for(int i = 0; i < operatorList.size(); i ++) {
			Operator operator = (Operator) operatorList.get(i);
			list4.add(operator.getId());
			}
			baseHibernateDAO.deleteBatchBySql(sql4, list4);
		}
		String hql5 = "from BusinessDesCountry b where b.businessid=?";
		list5.add(id);
		List businessDesCountryList = baseHibernateDAO.getEntity(hql5, list5);
		if(businessDesCountryList.size() > 0) {
			String sql6 = "delete from BUSINESS_DES_COUNTRY b where b.id in(";
			for(int i = 0; i < businessDesCountryList.size(); i ++) {
				BusinessDesCountry businessDesCountry = (BusinessDesCountry) businessDesCountryList.get(i);
				list6.add(businessDesCountry.getId());
				System.out.println("---------------------------------" + businessDesCountry.getId());
			}
			baseHibernateDAO.deleteBatchBySql(sql6, list6);
		}
		String hql7 = "from Payment p where p.businessid=?";
		list7.add(id);
		List paymentList = baseHibernateDAO.getEntity(hql7, list7);
		if(paymentList.size() > 0) {
			String sql8 = "delete from payment p where p.businessid in(";
			/*for(int i = 0; i < paymentList.size(); i ++) {
				Payment payment = (Payment) paymentList.get(i);
				System.out.println("++++++++++++++++++++++++++++++++++++++++++++++:"+payment.getId());
			}*/
			list8.add(id);
			baseHibernateDAO.deleteBatchBySql(sql8, list8);
		}
		String hql9 = "from BusinessAppno b where b.businessid=?";
		list9.add(id.toString());
		List businessAppnoList = baseHibernateDAO.getEntity(hql9, list9);
		if(businessAppnoList.size() > 0) {
			String sql10 = "delete from business_appno b where b.id in(";
			for(int i = 0; i < businessAppnoList.size(); i ++) {
				BusinessAppno businessAppno = (BusinessAppno) businessAppnoList.get(i);
				list10.add(businessAppno.getId());
			}
			baseHibernateDAO.deleteBatchBySql(sql10, list10);
		}		
		String hql11 = "from Examinelog e where e.businessid=?";
		list11.add(id);
		List examineLogList = baseHibernateDAO.getEntity(hql11, list11);
		if(examineLogList.size() > 0) {
			String sql12 = "delete from EXAMINELOG e where e.id in(";
			for (int i = 0; i < examineLogList.size(); i++) {
				Examinelog examinelog = (Examinelog) examineLogList.get(i);
				list12.add(examinelog.getId());
			}
			baseHibernateDAO.deleteBatchBySql(sql12, list12);
		}
		/*String hql13 = "from Certificate c where c.businessid=?";
		list13.add(id);
		List certificateList = baseHibernateDAO.getEntity(hql13, list13);
		if(certificateList.size() > 0) {
			String sql14 = "delete from CERTIFICATE where c.businessid in(";
			for (int i = 0; i < certificateList.size(); i++) {
				Certificate certificate = (Certificate) certificateList.get(i);
				list14.add(certificate.getId());
			}
			baseHibernateDAO.deleteBatchBySql(sql14, list14);
		}*/
	}
}
