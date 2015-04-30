package com.sinosoft.gypsymoth.bussiness.organization.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sinosoft.gypsymoth.dao.BaseHibernateDAO;
import com.sinosoft.gypsymoth.pojo.City;
import com.sinosoft.gypsymoth.pojo.Example;
import com.sinosoft.gypsymoth.pojo.Nation;
import com.sinosoft.gypsymoth.pojo.OrgLog;
import com.sinosoft.gypsymoth.pojo.Organization;
import com.sinosoft.gypsymoth.pojo.OrganizationLevel;
import com.sinosoft.gypsymoth.pojo.OrganizationPort;
import com.sinosoft.gypsymoth.pojo.Port;
import com.sinosoft.gypsymoth.pojo.ProCityPort;
import com.sinosoft.gypsymoth.pojo.Promary;
import com.sinosoft.gypsymoth.pojo.Right;
import com.sinosoft.gypsymoth.utils.Constants;


public class OrganizationServiceImpl implements OrganizationService {
	
	private BaseHibernateDAO baseHibernateDAO;

	public BaseHibernateDAO getBaseHibernateDAO() {
		return baseHibernateDAO;
	}

	public void setBaseHibernateDAO(BaseHibernateDAO baseHibernateDAO) {
		this.baseHibernateDAO = baseHibernateDAO;
	}

/**
 * 根据机构编号查询机构信息
 */
	public List  getAllOrganization(String orgId) throws Exception {
		StringBuffer sql=new StringBuffer();
		sql.append(" select o.id,");                                           
		sql.append("org_sname,");                                           
		sql.append("org_fname, ol.org_name olname, ");                                           
		sql.append("        address,      ");                                           
		sql.append("        contact,      ");                                           
		sql.append("        tel,          ");                                           
		sql.append("        fax,          ");                                           
		sql.append("        email,        ");                                           
		sql.append("        o.org_status, ");                                           
		sql.append("        comments,     ");                                           
		sql.append("        ins_time,     ");                                           
		sql.append("        up_time,      ");                                           
		sql.append("        cancel_time,  ");                                           
		sql.append("        nationid,     ");                                           
		sql.append("        proid,        ");                                           
		sql.append("        cityid,       ");                                           
		sql.append("        org_id,       "); 
		sql.append("        bankaccount,  ");   
		sql.append("        vbrk,         ");   
		sql.append("       	ol.parent_id, ");                                           
		sql.append("       is_child       ");                                           
		sql.append("   from organization o,organization_level  ol");                    
		sql.append("   where   o.org_status=1 and o.id=ol.id and o.id=?");                        
		List list = new ArrayList();
		list.add(orgId);
		List returnList = baseHibernateDAO.getEntityBySql(sql.toString(), list);
		return returnList;	
	}
/**
 * 根据ID查询机构基本信息
 * @param orgId
 * @return
 * @throws Exception
 */
	public List<Organization> getOrganizationById(int orgId) throws Exception {
		String sql="select * from organizaiton where org_id=?";
		List list = new ArrayList();
		list.add(orgId);
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;		
	}

	public void saveOrganization(Organization org, OrganizationLevel ol,
			String[] provinceId, String[] citydivId, String[] portName,
			String[] portSname, Port pos, OrganizationPort op, ProCityPort pcp)
			throws Exception {
		baseHibernateDAO.saveEntity(org);
		String orgId = org.getId();
		ol.setId(orgId);
		ol.setOrgUrl("/page/organization/getOrganization.action?Id=" + orgId);
		ol.setPersonUrl("/page/person/getAllPersonByPage.action?Id=" + orgId);
		baseHibernateDAO.saveEntity(ol);
		if (portName.length > 0 && portSname.length > 0) {
			for (int i = 0; i < portName.length; i++) {

				if (!"".equals(portName[i]) && !"".equals(portSname[i])
						&& !"".equals(provinceId[i])
						&& !"".equals(citydivId[i])) {
					Port po = new Port(); 
					po.setProid(Integer.parseInt(provinceId[i]));
					po.setCityid(Integer.parseInt(citydivId[i]));
					po.setPortId(getPortNo(org.getOrgId(), org.getCityid()
							.toString()));
					po.setPortName(portName[i]);
					po.setPortSname(portSname[i]);
					baseHibernateDAO.saveEntity(po);
					Integer portId = po.getId();
					OrganizationPort op2 = new OrganizationPort();
					op2.setOrgId(orgId);
					op2.setPortId(portId.toString());
					baseHibernateDAO.saveEntity(op2);
					ProCityPort pcp2 = new ProCityPort();
					pcp2.setCityid(pcp.getCityid());
					pcp2.setProid(pcp.getProid());
					pcp2.setPortid(portId);
					pcp2.setCityportid(getCityNo(org.getOrgId(), org.getCityid()
							.toString()));
					baseHibernateDAO.saveEntity(pcp2);
				}
			}
		}
	}
	/**
	 * 根据机构编号和城市编号得到港口编号
	 * @param orgId,cityId
	 * @return
	 */
	public String getPortNo(String orgId,String cityId){
		String seq=orgId;
		String sql="select PORT_SEQU.Nextval from dual";
		String seqs=baseHibernateDAO.getSequenceFromSql(sql);
		if(seqs.length()<2){
			for(int i=0;i<(2-seqs.length());i++){
				seq=seq+"0";
			}
		}
		if(cityId.length()<2){
			for(int i=0;i<(2-cityId.length());i++){
				cityId=cityId+"0";
			}
		}
		seq=seq+cityId+seqs;
		return seq;
	}
	/**
	 * 根据机构编号构造港口城市编号
	 * @param type
	 * @return
	 */
	public String getCityNo(String orgId,String cityId){
		String seq=orgId;		
		if(cityId.length()<2){
			for(int i=0;i<(2-cityId.length());i++){
				cityId=cityId+"0";
			}
		}
		seq=seq+cityId;
		return seq;
	}
	/**
	 * 得到Organization对象
	 */
	public Organization getOrganizationbyId(Organization org) throws Exception{
		return (Organization) baseHibernateDAO.getEntityById(Organization.class, org.getId());
	}
	/**
	 * 分页查询所有机构信息
	 */
	public List getAllOrganizationByPage(int begin,
			int numOfEachPage) throws Exception {
		StringBuffer sql=new StringBuffer();
		sql.append( " select * from (  	                 ");
		sql.append( " select o.id as orderbyid, o.id,    ");
		sql.append( "       org_sname,                   ");
		sql.append( "       org_fname,                   ");
		sql.append( "       address,                     ");
		sql.append( "       contact,                     ");
		sql.append( "       tel,                         ");
		sql.append( "       fax,                         ");
		sql.append( "       email,                       ");		
		sql.append( "       o.org_status,                  ");
		sql.append( "       comments,                    ");
		sql.append( "       o.nationid,                  ");
		sql.append( "       o.proid,                     ");
		sql.append( "       o.cityid,                    ");
		sql.append( "       o.parent_id                    ");
		sql.append( "   from  organization o,organization_level ol  ");
		sql.append( " where 1=1" );
		sql.append( " and (ol.is_child=0 or ol.is_child=1) " );
		sql.append( "       and o.org_status=1           ");
		sql.append( "       and o.id=ol.id          ");
		sql.append( " union                              ");
		sql.append( " select o.parent_id as orderbyid, o.id,    ");
		sql.append( "       org_sname,                   ");
		sql.append( "       org_fname,                   ");
		sql.append( "       address,                     ");
		sql.append( "       contact,                     ");
		sql.append( "       tel,                         ");
		sql.append( "       fax,                         ");
		sql.append( "       email,                       ");		
		sql.append( "       o.org_status,                  ");
		sql.append( "       comments,                    ");
		sql.append( "       o.nationid,                  ");
		sql.append( "       o.proid,                     ");
		sql.append( "       o.cityid,                    ");
		sql.append( "       o.parent_id                    ");
		sql.append( "   from  organization o,organization_level ol  ");
		sql.append( " where 1=1" );
		sql.append( " and (ol.is_child=2) " );
		sql.append( "       and o.org_status=1           ");
		sql.append( "       and o.id=ol.id          ");
		sql.append( " )order by to_number(orderbyid),to_number(id)     ");
		List list = new ArrayList();		
		List returnList = baseHibernateDAO.getEntityByPageBySql(sql.toString(), list, begin, numOfEachPage);
		return returnList;		
		
	}
	/**
	 * 得到所有机构信息的数量
	 */
	public int getAllOrganizationCount() throws Exception {
		List list = new ArrayList();
		String sql = "select count(*) from organization o,organization_level ol where o.org_status=1 and (ol.is_child=0 or ol.is_child=1 or ol.is_child=2)  and o.id=ol.id ";
		int count = baseHibernateDAO.getEntitiesCountBySql(sql, list);
		return count;
	}
	/**
	 * 得到机构信息树状菜单
	 */
	public List<OrganizationLevel> getAllOrganizationLevel() throws Exception {
		String sql = "from OrganizationLevel o where org_status=1 order by to_number(o.id) ";
		List list = new ArrayList();
		List<OrganizationLevel>returnList = baseHibernateDAO.getEntity(sql,list);
		return returnList;
	}
	/**
	 * 得到所有城市
	 */
	public List<City> getAllCity() throws Exception {
		String sql = "from city ";
		List list = new ArrayList();
		List<City>returnList = baseHibernateDAO.getEntity(sql,list);
		return returnList;
	}
	/**
	 * 得到所有省份
	 */
	public List<Promary> getAllProvince() throws Exception {
		String sql = "from promary ";
		List list = new ArrayList();
		List<Promary>returnList = baseHibernateDAO.getEntity(sql,list);
		return returnList;
	}
	/**
	 * 根据省份编号查找城市，二级联动
	 */
	public List findCityByProvinceId(String id) throws Exception {
		String sql="select cityid, cityname, proid from city where proid=? ";
		List list = new ArrayList();
		list.add(id);
		List returnList = baseHibernateDAO.getEntityBySql(sql.toString(), list);
		return returnList;
	}

	/**
	 * 得到所有港口信息
	 */
	public List getAllPort(String orgId) throws Exception {
//		StringBuffer sql=new String Buffer();
		String sql="select p.id,port_id,port_name,port_sname,cityname  from port p,promary py, city c where c.proid=py.proid and p.proid=py.proid and p.cityid=c.cityid and p.id in(select op.port_id from organization_port op" +
				" where op.org_id in (select m.id  from (       select t.id, t.parent_id, t.is_child," +
				" t.org_name, t.org_status     from organization_level t            where t.id = "+orgId+" union " +
				"   select d.id, d.parent_id, d.is_child, d.org_name, d.org_status   from organization_level d " +
				"  where d.parent_id in          (select d1.id from organization_level d1 where d1.id = "+orgId+")   " +
				"union       select s.id, s.parent_id, s.is_child, s.org_name, s.org_status    from organization_level s    " +
				"where s.parent_id in (select s1.id         from organization_level s1   where s1.parent_id ="+orgId+" )   " +
				") m where m.org_status =1  ))";
			List list = new ArrayList();		
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);		
		return returnList;
	}
	
	/**
	 * 根据机构编号得到机构信息
	 */
	public List<Organization> getOrganizationById(String oid) throws Exception {
		String sql="select * from organizaiton where orgId=?";
		List list = new ArrayList();
		list.add(oid);
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;	
	}
	/**
	 * 修改机构信息
	 */
	public void updateOrganization(Organization org,OrganizationLevel ol) throws Exception {
		baseHibernateDAO.updateEntity(org);
		baseHibernateDAO.updateEntity(ol);
		
	}
	/**
	 * 保存日志信息
	 */
	public void saveLOG(OrgLog log) throws Exception {
		baseHibernateDAO.saveEntity(log);
		
	}
	/**
	 * 删除机构信息
	 */
	public void deleteOrganization(String[] idArray) throws Exception {
		String sql = "delete from organization where id in (";
		List list = new ArrayList();
		for(int i=0;i<idArray.length;i++){
			list.add(idArray[i]);
			}
		baseHibernateDAO.deleteBatchBySql(sql, list);
	}
	/**
	 * 根据省份获取城市列表，二级联动
	 */

	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.bussiness.register.service.RegisterService#getCity(java.lang.Integer)
	 */
	public List getCity(Integer proId) throws Exception{
		String sql = " select CITYID as cityId,CITYNAME as cityName from city where PROID=? order by CITYID";
		List list = new ArrayList();
		list.add(proId);
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;
	}
	/**
	 * 得到国家列表
	 */
	
	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.bussiness.register.service.RegisterService#getNation()
	 */
	public List getNation() throws Exception{
		String sql = " select nation_id as nationId,nation_name as nationName from nation";
		List list = new ArrayList();
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;
	}
	/**
	 * 得到省份列表
	 */
	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.bussiness.register.service.RegisterService#getPromory()
	 */
	public List getPromory() throws Exception{
		String sql = " select * from promary";
		List list = new ArrayList();
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;
	}
	/**
	 * 得到港口信息
	 */
	public List selectPort() throws Exception {
		//查询
		String sql = "from Port ";		
		List list = new ArrayList();				
		List<Port>returnList = baseHibernateDAO.getEntity(sql,list);		
		for(int i=0;i<returnList.size();i++){
			System.out.println("港口:"+returnList.get(i).getId()+"--"+returnList.get(i).getPortName());
		}
		return returnList;
	}

//	public List PortId() throws Exception {
//		String sql="select count(*)+1 as ports_id from port ";
//		List list = new ArrayList();
//		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
//		return returnList;	
//		
//	}

	public int getAllOrgLevelCount() throws Exception {
		List list = new ArrayList();
		String sql = "select count(*) from organization_level";
		int count = baseHibernateDAO.getEntitiesCountBySql(sql, list);
		return count;
	}
/**
 * 根据ID的到港口信息
 */
	public List getPortById(String oid) throws Exception {
		String sql="select * from port where id=?";
		List list = new ArrayList();
		list.add(oid);
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;	
	}
/**
 * 得到港口对象
 */
	public Port getPortbyId(Port port) throws Exception {
		return (Port) baseHibernateDAO.getEntityById(Port.class, port.getId());
	}
/**
 * 修改港口信息
 */
	public void updatePort(Port port) throws Exception {
		baseHibernateDAO.updateEntity(port);
		
	}
/**
 * 删除港口信息
 */
	public void deletePort(String portId) throws Exception {
		Port port=new Port();
		port=(Port)baseHibernateDAO.getEntityById(Port.class, Integer.parseInt(portId));
		baseHibernateDAO.deleteEntity(Port.class, Integer.parseInt(portId));
		String sql = " delete from organization_port where port_id in( ";
		List list = new ArrayList();
		list.add(portId);
		baseHibernateDAO.deleteBatchBySql(sql, list);
	}
/**
 * 注销机构
 */
	
		public void updateBatch(String idArray) throws Exception {
			
			String sql= "update organization set org_status=0 where id=?";
			List list = new ArrayList();
			list.add(idArray);
			baseHibernateDAO.updateBatchBySql(sql, list);
			String sql1="update organization_level set org_status=0 where id=?";
			List list1 = new ArrayList();
			list1.add(idArray);
			baseHibernateDAO.updateBatchBySql(sql1, list);
		}

	/**
	 * 保存港口信息
	 */
	public void savePort(String[] provinceId, String[] citydivId, String orgId,
			String[] portName, String[] portSname) throws Exception {
		Organization o = new Organization();
		o.setId(orgId);
		o = getOrganizationbyId(o);
		if (portName.length > 0 && portSname.length > 0) {
			for (int i = 0; i < portName.length; i++) {
				if (!"".equals(provinceId[i]) && !"".equals(citydivId[i])
						&& !"".equals(portName[i]) && !"".equals(portSname[i])) {
					Port port = new Port();
					OrganizationPort op = new OrganizationPort();
					ProCityPort pcp = new ProCityPort();
					port.setPortId(getPortNo(o.getOrgId(), o.getCityid()
							.toString()));
					port.setPortName(portName[i]);
					port.setPortSname(portSname[i]);
					port.setProid(Integer.parseInt(provinceId[i]));
					port.setCityid(Integer.parseInt(citydivId[i]));
					baseHibernateDAO.saveEntity(port);
					Integer portId = port.getId();
					op.setOrgId(o.getId());
					op.setPortId(portId.toString());
					baseHibernateDAO.saveEntity(op);
					pcp.setProid(Integer.parseInt(provinceId[i]));
					pcp.setCityid(Integer.parseInt(citydivId[i]));
					pcp.setPortid(portId);
					pcp.setCityportid(getCityNo(o.getOrgId(), citydivId[i]));
					baseHibernateDAO.saveEntity(pcp);
				}
			}
		}
	}
/**
 * 得到机构层次对象
 */
		public OrganizationLevel getOrgLevelbyId(String id)
				throws Exception {
			return (OrganizationLevel) baseHibernateDAO.getEntityById(OrganizationLevel.class, id);

		}
/**
 * 得到启用机构数目
 */
		public int getAllEnableCount() throws Exception {
			List list = new ArrayList();
			String sql = "select count(*) from organization where org_status=0";
			int count = baseHibernateDAO.getEntitiesCountBySql(sql, list);
			return count;
		}
/**
 * 得到启用机构列表
 */
		public List getAllEnableByPage(int begin, int numOfEachPage)
				throws Exception {
			StringBuffer sql=new StringBuffer();
			sql.append( "select o.id,    	                 ");
			sql.append( "       org_sname,                   ");
			sql.append( "       org_fname,                   ");
			sql.append( "       address,                     ");
			sql.append( "       contact,                     ");
			sql.append( "       tel,                         ");
			sql.append( "       fax,                         ");
			sql.append( "       email,                       ");		
			sql.append( "       org_status,                  ");
			sql.append( "       comments,                    ");
			sql.append( "       o.nationid,                  ");
			sql.append( "       o.proid,                     ");
			sql.append( "       o.cityid,                    ");
			sql.append( "       parent_id                    ");
			sql.append( "       from  organization o         ");
			sql.append( "       where org_status=0           ");
			
			List list = new ArrayList();		
			List returnList = baseHibernateDAO.getEntityByPageBySql(sql.toString(), list, begin, numOfEachPage);
			return returnList;		
		}
/**
 * 启用机构
 */
		public void updateEnableBatch(String idArray) throws Exception {

			String sql= "update organization set org_status=1 where id=?";
			List list = new ArrayList();
			list.add(idArray);
			baseHibernateDAO.updateBatchBySql(sql, list);
			String sql1="update organization_level set org_status=1 where id=?";
			List list1 = new ArrayList();
			list1.add(idArray);
			baseHibernateDAO.updateBatchBySql(sql1, list);
			
		}
/**
 *  得到父机构ID为parentid的LIST
 */
		
	public List getOranizationLevelByParentID(String parentid) throws Exception
	{
		List list = new ArrayList();
		String sql=" select t.* , row_number() over ( order by to_number(t.id)  ) rn  " +
				"	from organization_level t" +
				" 	where t.parent_id = ? and t.org_status = 1 order by t.id ";
		list.add(parentid);
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList; 
	}		
	/**
	 * 得到港口城市列表
	 */
	
	public List getCityPortList() throws Exception
	{
		List list = new ArrayList();
		String sql=" select  c.cityname,c.cityname,c.englishcityname,c.proid from pro_city_port t,city c where  c.proid = t.proid and c.cityid = t.cityid group by c.cityname,c.cityname,c.englishcityname,c.proid order by c.proid  ";
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList; 
	}
/**
 * 得到机构印章
 */
	public List getStampByorgID(String orgid) throws Exception
  
	{
		String stamp = "";
		List list = new ArrayList();
		String sql="  select p.stampid from organization_level t,org_stamp p where t.id  = p.orgid  and t.id = ? ";
		list.add(orgid);
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList; 
	}

	

	


	

	

	
	

		
	

}
