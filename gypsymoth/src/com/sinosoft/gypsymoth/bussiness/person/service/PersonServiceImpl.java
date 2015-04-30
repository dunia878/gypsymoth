package com.sinosoft.gypsymoth.bussiness.person.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.sinosoft.gypsymoth.dao.BaseHibernateDAO;
import com.sinosoft.gypsymoth.pojo.Account;
import com.sinosoft.gypsymoth.pojo.AccountRecord;
import com.sinosoft.gypsymoth.pojo.AccountRole;
import com.sinosoft.gypsymoth.pojo.Example;
import com.sinosoft.gypsymoth.pojo.OpLog;
import com.sinosoft.gypsymoth.pojo.Organization;
import com.sinosoft.gypsymoth.pojo.OrganizationLevel;
import com.sinosoft.gypsymoth.pojo.Person;
import com.sinosoft.gypsymoth.pojo.Role;
import com.sinosoft.gypsymoth.utils.Constants;

public class PersonServiceImpl implements PersonService {
	
	private BaseHibernateDAO baseHibernateDAO;

	public BaseHibernateDAO getBaseHibernateDAO() {
		return baseHibernateDAO;
	}

	public void setBaseHibernateDAO(BaseHibernateDAO baseHibernateDAO) {
		this.baseHibernateDAO = baseHibernateDAO;
	}
	
	public Person getPersonById(Person person)  throws Exception{
		return (Person) baseHibernateDAO.getEntityById(Person.class, person.getId());
	}
	public Account getAccountById(String accountID)  throws Exception{
		return (Account) baseHibernateDAO.getEntityById(Account.class, accountID);
	}
	/**
	 * 删除人员信息
	 */
	public void deletePerson(String id,Account account,OpLog log) throws Exception {	
		Person ps=new Person();
		ps.setId(Integer.parseInt(id));
		ps=getPersonById(ps);
		Account acc=new Account();
		acc=getAccountById(ps.getAccountId());
		AccountRecord ar=new AccountRecord();
		baseHibernateDAO.saveEntity(log);
		if(ps.getAccountId()!="noid"&&!ps.getAccountId().equals("noid")){
				ar.setAccountId(ps.getAccountId());
				ar.setAccountName(acc.getAccountName());
				ar.setAccountStatus(acc.getAccountStatus());
				ar.setAccountType(acc.getAccountType());
				ar.setModifyBy(account.getAccountName());
				ar.setModifyDate(new java.sql.Date(System.currentTimeMillis()));
				ar.setModifyType(2);
				baseHibernateDAO.saveEntity(ar);
				String sqlaccount="delete from account where account_id in(";
				List accountlist=new ArrayList();
				accountlist.add(ps.getAccountId());
				baseHibernateDAO.deleteBatchBySql(sqlaccount, accountlist);
				String sqlrole="delete from account_role where account_id in(";
				List rolelist=new ArrayList();
				rolelist.add(ps.getAccountId());
				baseHibernateDAO.deleteBatchBySql(sqlrole, rolelist);
			
		}
		String sql="delete from person where id in(";
		List list=new ArrayList();
		list.add(id);
		baseHibernateDAO.deleteBatchBySql(sql, list);
	}
/**
 * 得到人员信息
 */
	public List<Person> getAllPerson(String orgId) throws Exception {
		String sql = "select new Person(p.name,o.orgSname,p.position,p.training,p.passTime,p.isCoordinator,p.isAuthorized,p.isInspector) from Person p,Organization o where p.orgId = o.orgId and o.orgId=? order by p.personId desc";
		List list = new ArrayList();
		list.add(orgId);
		List<Person>returnList = baseHibernateDAO.getEntity(sql,list);
		return returnList;
	}
/**
 * 根据ID得到人员信息
 */
	public List getPersonById(String id) throws Exception {
		StringBuffer sql=new StringBuffer();
		sql.append(" select p.id,                              		");  
		sql.append(" 		person_id,                              ");  
		sql.append("        account_id,                             ");
		sql.append("        name,                                   ");
		sql.append("        sex,                                    ");
		sql.append("        birthday,                               ");
		sql.append("        education,                              ");
		sql.append("        position,                               ");
		sql.append("        training,                               ");
		sql.append("        pass_time,                              ");
		sql.append("        tel,                                    ");
		sql.append("        mobile,                                 ");
		sql.append("        fax,                                    ");
		sql.append("        email,                                  ");
		sql.append("        is_coordinator,                         ");
		sql.append("        is_authorized,                          ");
		sql.append("        is_inspector,                           ");
		sql.append("        attachment,                             ");
		sql.append("        ins_time,                               ");
		sql.append("        up_time,                                ");
		sql.append("        del_time,                               ");
		sql.append("        comments,                               ");
		sql.append("        person_type,                            ");
		sql.append("        p.org_id,                               ");
		sql.append("         org_name,                              ");
		sql.append("         person_status,                         ");
		sql.append("         spell                         			");
		sql.append("   from person p,organization_level ol          ");
		sql.append("   where p.org_id=ol.id                   ");
		sql.append("   and p.id=? and person_status=1        ");
		//String sql = "select p.personid,p.name,p.sex,p.birthday,p.education,p.position,p.training,p.passtime,p.tel,p.mobile,p.fax,p.email,ol.orgname,p.iscoordinator,p.isauthorized,p.isinspector,p.attachment,ol.orgname from person p,organizationlevel ol where p.orgId = ol.orgId and  p.personId=?  order by p.personId desc";
		//String sql = " select new Person(p.personId,p.name,ol.orgName,p.position,p.training,p.passTime,p.isCoordinator,p.isAuthorized,p.isInspector) from Person p,OrganizationLevel ol where p.orgId = ol.orgId and  p.personId=? order by p.personId desc";
		List list = new ArrayList();
		list.add(id);
		List returnList = baseHibernateDAO.getEntityBySql(sql.toString(), list);
		return returnList;
	}
	/**
	 * 根据机构类型得到人员编号
	 * @param type
	 * @return
	 */
	public String getPersonNo(String type){
		String seq="0000";
		String sql="select ";
		if(Constants.USER_ZJ.equals(type)){
			sql+="ZJ_SEQU.Nextval";
		}
		if(Constants.USER_JT.equals(type)){
			sql+="JT_SEQU.Nextval";
		}
		if(Constants.USER_YZGS.equals(type)){
			sql+="JYGS_SEQU.Nextval";
		}
		if(Constants.USER_BM.equals(type)){
			sql+="BM_SEQU.Nextval";
		}		
		sql+=" from dual ";
		String seqs=baseHibernateDAO.getSequenceFromSql(sql);
		if(seqs.length()<3){
			for(int i=0;i<(3-seqs.length());i++){
				seq=seq+"0";
			}
		}
		seq=seq+seqs;
		return seq;
	}
	/**
	 * 根据参数，构造人员编号
	 * @param type
	 * @return
	 * @throws Exception 
	 */
	public String getPersonNo(String type,String orgId) throws Exception{
		Organization org=new Organization();
		org=getOrgByorgId(orgId);
		String seq=org.getOrgId();		
		String sql="select ";
		if(Constants.USER_ONE.equals(type)){
			sql+="ONE_SEQU.Nextval";
		}
		if(Constants.USER_TWO.equals(type)){
			sql+="TWO_SEQU.Nextval";
		}
		sql+=" from dual ";
		String seqs=baseHibernateDAO.getSequenceFromSql(sql);
		if(seqs.length()<3){
			for(int i=0;i<(3-seqs.length());i++){
				seq=seq+"0";
			}
		}
		seq=seq+seqs;
		return seq;
	}
	
	/**
	 * 保存人员信息
	 * @param type
	 * @return
	 */
	public void savePerson(Person person) throws Exception {
		 String pno="";
		if((person.getPersonType().equals("1")&&person.getPersonType()=="1")||(person.getPersonType().equals("2")&&person.getPersonType()=="2")||(person.getPersonType().equals("3")&&person.getPersonType()=="3")||(person.getPersonType().equals("4")&&person.getPersonType()=="4")){
		pno=getPersonNo(person.getPersonType());
	}
		else{
			
			pno=getPersonNo(person.getPersonType(),person.getOrgId());
		}
		
		person.setPersonId(pno);
		baseHibernateDAO.saveEntity(person);
	}
/**
 * 更新人员信息
 */
	public void updatePerson(Person ps,OpLog ol,String isCoordinator,String isAuthorized,String isInspector) throws Exception {
		AccountRole role = new AccountRole();
		if (isCoordinator != null) {
			ps.setIsCoordinator(1);
			ol.setIsCoordinator(1);
			if(ps.getAccountId()!="noid"&&!ps.getAccountId().equals("noid")){//如果它是用户，则修改它的身份时，要修改它的权限
			List list=getRoleById(ps.getAccountId());
			if (null != list && list.size() > 0) {
				String right_id=((AccountRole)list.get(0)).getRoleId();
				String id=((AccountRole)list.get(0)).getId();
				if(!right_id.equals("3")){
					role.setAccountId(ps.getAccountId());
					role.setRoleId("3");
					baseHibernateDAO.saveEntity(role);					
					}				
				}
			}
		}
		else{
			ps.setIsCoordinator(0);
			ol.setIsCoordinator(0);
			if(ps.getAccountId()!="noid"&&!ps.getAccountId().equals("noid")){
			List list=getRoleById(ps.getAccountId());
			if (null != list && list.size() > 0) {
				String right_id=((AccountRole)list.get(0)).getRoleId();
				String id=((AccountRole)list.get(0)).getId();
				if(right_id.equals("3")){
					baseHibernateDAO.deleteEntity(AccountRole.class, id);
					
				}
			}
			
		}
		}
		if (isAuthorized != null) {
			ps.setIsAuthorized(1);
			ol.setIsAuthorized(1);
			if(ps.getAccountId()!="noid"&&!ps.getAccountId().equals("noid")){
			List list=getRoleById(ps.getAccountId());
			if (null != list && list.size() > 0) {
				String right_id=((AccountRole)list.get(0)).getRoleId();
				String id=((AccountRole)list.get(0)).getId();
				if(!right_id.equals("4")){
					role.setAccountId(ps.getAccountId());
					role.setRoleId("4");
					baseHibernateDAO.saveEntity(role);						
					}				
				}
			}
		}
		else{
			ps.setIsAuthorized(0);
			ol.setIsAuthorized(0);
			if(ps.getAccountId()!="noid"&&!ps.getAccountId().equals("noid")){
			List list=getRoleById(ps.getAccountId());
			if (null != list && list.size() > 0) {
				String right_id=((AccountRole)list.get(0)).getRoleId();
				String id=((AccountRole)list.get(0)).getId();
				if(right_id.equals("4")){
					baseHibernateDAO.deleteEntity(AccountRole.class, id);					
				}
			}
		}
		}
		if (isInspector != null) {
			ps.setIsInspector(1);
			ol.setIsInspector(1);
			if(ps.getAccountId()!="noid"&&!ps.getAccountId().equals("noid")){
			List list=getRoleById(ps.getAccountId());
			if (null != list && list.size() > 0) {
				String right_id=((AccountRole)list.get(0)).getRoleId();
				String id=((AccountRole)list.get(0)).getId();
				if(!right_id.equals("2")){
					role.setAccountId(ps.getAccountId());
					role.setRoleId("2");
					baseHibernateDAO.saveEntity(role);						
					}				
				}
		}
		}
		else{
			ps.setIsInspector(0);
			ol.setIsInspector(0);
			if(ps.getAccountId()!="noid"&&!ps.getAccountId().equals("noid")){
			List list=getRoleById(ps.getAccountId());
			if (null != list && list.size() > 0) {
				String right_id=((AccountRole)list.get(0)).getRoleId();
				String id=((AccountRole)list.get(0)).getId();
				if(right_id.equals("4")){
					baseHibernateDAO.deleteEntity(AccountRole.class, id);					
				}
			}
		}
		}	
		baseHibernateDAO.saveEntity(ol);
		baseHibernateDAO.updateEntity(ps);
		
	}

	/**
	 * 得到所有启用人员的人员数目
	 */
		public int getAllPersonCount(String name, String selectOrgid, Person ps) throws Exception {
		/*List list = new ArrayList();
		String sql = "select count(*) from person where person_status=1";*/
		int isAuthorized = ps.getIsAuthorized();
		int isCoordinator = ps.getIsCoordinator();
		int isInspector = ps.getIsInspector();
		List list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) from person p where p.person_status=1");
		String[] strs = null;
		
		
		if("1".equals(selectOrgid)) {
			selectOrgid = null;
		}else if(selectOrgid != null &&!selectOrgid.equals("")) {
			List list1 = new ArrayList();
			List listTemp = new ArrayList();
			List listTemp1 = new ArrayList();
			List listTemp2 = new ArrayList();
			String sql1 = "select ol.id from OrganizationLevel ol where ol.id=" + selectOrgid;
			listTemp = baseHibernateDAO.getEntity(sql1, list1);
			String tempStr1 = null;
			Iterator iterator1 = listTemp.iterator();
			while (iterator1.hasNext()) {
				tempStr1 = iterator1.next().toString();
			}
			
			String sql2 = "select org.orgId from Organization org where org.id= " + tempStr1;
			listTemp1 = baseHibernateDAO.getEntity(sql2, list1);
			String tempStr2 = null;			
			Iterator iterator2 = listTemp1.iterator();
			while (iterator2.hasNext()) {
				tempStr2 = iterator2.next().toString();
			}	
			String sql3 = "select org.id from Organization org where org.orgId=" + tempStr2;
			if(tempStr2 != null && "00".equals(tempStr2.substring(2))) {
				 sql3 = "select org.id from Organization org where org.orgId like '" + tempStr2.substring(0,2) +"%'";			
			}		
			listTemp2 =  baseHibernateDAO.getEntity(sql3, list1);
			System.out.println("---------------------------------------" + listTemp2);
			strs = new String[listTemp2.size()];
			int i =0;
			Iterator iterator3 = listTemp2.iterator();
			while(iterator3.hasNext()) {
				strs[i] = iterator3.next().toString();
				i ++;
			}
		}
		if(name!=null&&!"".equals(name)){
			sql.append("   and name=? ");
			list.add(name);
		}	
		if(selectOrgid!=null && !"".equals(selectOrgid)) {
			/*sql.append("   and org_id=?");
			list.add(selectOrgid);*/
			if(strs.length == 1) {
				sql.append("	and p.org_id=" + strs[0] );
			}else {
				sql.append("	and p.org_id in(" + strs[0] +"   ");
				for(int m = 1; m < strs.length; m ++) {
					if(m == strs.length-1) {
						sql.append("," + strs[m] + ")    ");
					}else {
						sql.append("," + strs[m]);
					}
				}
			}
		}
		if(isAuthorized!=0) {
			sql.append("   and is_authorized=?");
			list.add(isAuthorized);
		}
		if(isCoordinator!=0) {
			sql.append("   and is_coordinator=?");
			list.add(isCoordinator);
		}
		if(isInspector!=0) {
			sql.append("   and is_inspector=?");
			list.add(isInspector);
		}
		/*if(isCoordinator!=null && !"".equals(isCoordinator)) {
			sql.append("   and isCoordinator=?");
			list.add(isCoordinator);
		}*/
		int count = baseHibernateDAO.getEntitiesCountBySql(sql.toString(), list);
		return count;
	}
		/**
		 * 得到注销人员数目
		 */
		public int getAllEnableCount() throws Exception {
			List list = new ArrayList();
			String sql = "select count(*) from person where person_status=0";
			int count = baseHibernateDAO.getEntitiesCountBySql(sql, list);
			return count;
		}
		/**
		 * 得到机构树状结构信息
		 */
	public List select() throws Exception {
		//查询
		String sql = "from OrganizationLevel ";		
		List list = new ArrayList();				
		List<OrganizationLevel>returnList = baseHibernateDAO.getEntity(sql,list);		
		for(int i=0;i<returnList.size();i++){
			System.out.println("查询下拉框:"+returnList.get(i).getId()+"--"+returnList.get(i).getOrgName());
		}
		return returnList;
	}
	/**
	 * 得到父节点所有启用的机构的树状菜单
	 */
	public List<OrganizationLevel> getAllOrganizationLevel(String parentId) throws Exception {
		String sql = "from OrganizationLevel o where org_status=1 ";
		List list = new ArrayList();			
		if(parentId!=null&&!"".equals(parentId)){
			sql+="   and id=? ";
			list.add(parentId);
		}	
		sql=sql+"order by to_number(o.id)";
		List<OrganizationLevel>returnList = baseHibernateDAO.getEntity(sql,list);
		return returnList;
	}
	/**
	 * 得到所有启用的机构信息
	 */
	public List<OrganizationLevel> getAllOrganizationLevel() throws Exception {
		String sql = "from OrganizationLevel o where org_status=1 ";
		List list = new ArrayList();		
		sql=sql+"order by to_number(o.id)";
		List<OrganizationLevel>returnList = baseHibernateDAO.getEntity(sql,list);
		return returnList;
	}

	/*public List<Person> getAllPersonByPage(int begin, int numOfEachPage)
			throws Exception {
		String sql = "select p.person_id,p.name,ol.id,ol.org_name,p.position,p.training,p.pass_time,p.is_coordinator,p.is_authorized,p.is_inspector from person p,organization_level ol where p.org_id = ol.org_id ";
		List list = new ArrayList();
		List<Person>returnList = baseHibernateDAO.getEntityByPageBySql(sql, list, begin, numOfEachPage);
		return returnList;	
	}*/
/**
 * 分页查询所有人员信息
 */
	public List  getAllPersonByPage(String Id,String name,String selectOrgid,String isCoordinator,String isAuthorized,String isInspector, int begin, int numOfEachPage)throws Exception {
		StringBuffer sql= new StringBuffer();
		//String sql = "select p.person_id,p.name,ol.id,ol.org_name,p.position,p.training,p.passtime,p.iscoordinator,p.isauthorized,p.isinspector from person p,organizationlevel ol where p.org_id = ol.org_id and ol.id=?";
		sql.append("select p.id as pid,person_id,                      ");
		sql.append("       account_id,                     ");
		sql.append("       name,                           ");
		sql.append("       sex,                            ");
		sql.append("       birthday,                       ");
		sql.append("       education,                      ");
		sql.append("       position,                       ");
		sql.append("       training,                       ");
		sql.append("       pass_time,                      ");
		sql.append("       p.tel,                            ");
		sql.append("       mobile,                         ");
		sql.append("       p.fax,                            ");
		sql.append("       p.email,                          ");
		sql.append("       is_coordinator,                 ");
		sql.append("       is_authorized,                  ");
		sql.append("       is_inspector,                   ");
		sql.append("       attachment,                     ");
		sql.append("       p.ins_time,                       ");
		sql.append("       p.up_time,                        ");
		sql.append("       p.del_time,                       ");
		sql.append("       p.comments,                       ");
		sql.append("       ol.id,                          ");
		sql.append("       person_type,                    ");
		sql.append("       p.org_id,                       ");
		sql.append("       org_name,                       ");
		sql.append("       p.person_status                    ");
		sql.append("  from person p,organization_level ol , organization oz ");
		sql.append("       where p.org_id=ol.id  and ol.id = oz.id      ");
//		sql.append("       and p.person_status=1              ");
		
		String[] strs = null;
		List list = new ArrayList();		
		if(name!=null&&!"".equals(name)){
			sql.append("   and name=? ");
			list.add(name);
		}	
		if("1".equals(Id) || "1".equals(selectOrgid)) { 
			Id = null;
			selectOrgid = null;
			
		}else if(Id != null || (selectOrgid != null&&!selectOrgid.equals(""))) {
			if(Id != null) {
				selectOrgid = Id;
			}
			List list1 = new ArrayList();
			List listTemp = new ArrayList();
			List listTemp1 = new ArrayList();
			List listTemp2 = new ArrayList();
			String sql1 = "select ol.id from OrganizationLevel ol where ol.id=" + selectOrgid;
			listTemp = baseHibernateDAO.getEntity(sql1, list1);
			String tempStr1 = null;
			Iterator iterator1 = listTemp.iterator();
			while (iterator1.hasNext()) {
				tempStr1 = iterator1.next().toString();
			}
			
			String sql2 = "select org.orgId from Organization org where org.id= " + tempStr1;
			listTemp1 = baseHibernateDAO.getEntity(sql2, list1);
			String tempStr2 = null;			
			Iterator iterator2 = listTemp1.iterator();
			while (iterator2.hasNext()) {
				tempStr2 = iterator2.next().toString();
			}	
			String sql3 = "select org.id from Organization org where org.orgId=" + tempStr2;
			if(tempStr2 != null && "00".equals(tempStr2.substring(2))) {
				 sql3 = "select org.id from Organization org where org.orgId like '" + tempStr2.substring(0,2) +"%'";			
			}		
			listTemp2 =  baseHibernateDAO.getEntity(sql3, list1);
			strs = new String[listTemp2.size()];
			int i =0;
			Iterator iterator3 = listTemp2.iterator();
			while(iterator3.hasNext()) {
				strs[i] = iterator3.next().toString();
				i ++;
			}
		}
		if(Id!=null&&!"".equals(Id)){
			//sql.append("   and p.org_id=? ");
			//list.add(Id);
			selectOrgid = Id;
		}
		if(selectOrgid!=null&&!"".equals(selectOrgid)){
			/*for(int i = 0; i < strs.length; i ++  ) {
				sql.append("   or p.org_id=" + strs[i]+ "  " );
				System.out.println("---------------------------------------" + strs[i]);
			}*/
			if(strs.length == 1) {
				sql.append("	and p.org_id=" + strs[0] );
			}else {
				sql.append("	and p.org_id in(" + strs[0] +"   ");
				for(int m = 1; m < strs.length; m ++) {
					if(m == strs.length-1) {
						sql.append("," + strs[m] + ")    ");
					}else {
						sql.append("," + strs[m]);
					}
				}
			}
			/*sql.append("	and p.org_id=? ");
			list.add(selectOrgid);*/
		}
		if(isCoordinator!=null&&!"".equals(isCoordinator)){
			sql.append("	and is_coordinator=1 ");			
		}
		if(isAuthorized!=null&&!"".equals(isAuthorized)){
			sql.append("	and is_authorized=1 ");			
		}
		if(isInspector!=null&&!"".equals(isInspector)){
			sql.append("	and is_inspector=1 ");			
		}
		sql.append("       order by to_number(oz.org_id),p.id     ");
		List returnList = baseHibernateDAO.getEntityByPageBySql(sql.toString(), list, begin, numOfEachPage);
		return returnList;		
	}
	
	/**
	 * 得到所有启用人员信息
	 */
	public List  getAllEnableByPage(String Id,String name,String selectOrgid,String isCoordinator,String isAuthorized,String isInspector, int begin, int numOfEachPage)
			throws Exception {
		StringBuffer sql= new StringBuffer();
		//String sql = "select p.person_id,p.name,ol.id,ol.org_name,p.position,p.training,p.passtime,p.iscoordinator,p.isauthorized,p.isinspector from person p,organizationlevel ol where p.org_id = ol.org_id and ol.id=?";
		sql.append("select p.id as pid,person_id,                      ");
		sql.append("       account_id,                     ");
		sql.append("       name,                           ");
		sql.append("       sex,                            ");
		sql.append("       birthday,                       ");
		sql.append("       education,                      ");
		sql.append("       position,                       ");
		sql.append("       training,                       ");
		sql.append("       pass_time,                      ");
		sql.append("       tel,                            ");
		sql.append("       mobile,                         ");
		sql.append("       fax,                            ");
		sql.append("       email,                          ");
		sql.append("       is_coordinator,                 ");
		sql.append("       is_authorized,                  ");
		sql.append("       is_inspector,                   ");
		sql.append("       attachment,                     ");
		sql.append("       ins_time,                       ");
		sql.append("       up_time,                        ");
		sql.append("       del_time,                       ");
		sql.append("       comments,                       ");
		sql.append("       ol.id,                          ");
		sql.append("       person_type,                    ");
		sql.append("       p.org_id,                       ");
		sql.append("       org_name,                       ");
		sql.append("       p.person_status                    ");
		sql.append("  from person p,organization_level ol  ");
		sql.append("       where p.org_id=ol.id        ");
		sql.append("       and p.person_status=0             ");
	
		List list = new ArrayList();		
		if(name!=null&&!"".equals(name)){
			sql.append("   and name=? ");
			list.add(name);
		}		
		if(Id!=null&&!"".equals(Id)){
				sql.append("   and p.org_id=? ");
				list.add(Id);
		}
		if(selectOrgid!=null&&!"".equals(selectOrgid)){
				sql.append("	and p.org_id=? ");
				list.add(selectOrgid);
		}	
		if(isCoordinator!=null&&!"".equals(isCoordinator)){
			sql.append("	and is_coordinator=1 ");			
		}
		if(isAuthorized!=null&&!"".equals(isAuthorized)){
			sql.append("	and is_authorized=1 ");			
		}
		if(isInspector!=null&&!"".equals(isInspector)){
			sql.append("	and is_inspector=1 ");			
		}
		sql.append("        order by to_number(ol.id)         ");
		List returnList = baseHibernateDAO.getEntityByPageBySql(sql.toString(), list, begin, numOfEachPage);
		return returnList;			
	}
	/**
	 * 根据ID停用人员信息
	 */
	public void updateBatch(String idArray,OpLog ol) throws Exception {		
		String sql= "update person set person_status=0 where id=?";
		List list = new ArrayList();
		list.add(idArray);
		baseHibernateDAO.saveEntity(ol);
		baseHibernateDAO.updateBatchBySql(sql, list);
		
	}
	/**
	 * 根据ID启用人员信息
	 */
	public void updateEnableBatch(String idArray,OpLog ol) throws Exception {		
		String sql= "update person set person_status=1 where id=?";
		List list = new ArrayList();
		list.add(idArray);
		baseHibernateDAO.saveEntity(ol);
		baseHibernateDAO.updateBatchBySql(sql, list);
		
	}	
/**
 * 通过AccountID查询到用户对应的角色
 */
	public List<AccountRole> getRoleById(String id) throws Exception {
		String hql="from AccountRole where accountId=?";
		List list=new ArrayList();
		list.add(id);
		List<AccountRole> returnList=baseHibernateDAO.getEntity(hql, list);
		return returnList;
	}
	/**
	 * 根据帐号查找对应人员信息
	 */
	public List getPersonByAccountId(String accountId) throws Exception {
		String sql="select p.id from person p,organization_level ol where p.org_id=ol.id and p.account_id=? and person_status=1 ";
		List list = new ArrayList();
		list.add(accountId);
		List returnList = baseHibernateDAO.getEntityBySql(sql.toString(), list);
		return returnList;
	}
	/**
	 * 根据机构ID的到机构对象
	 */
	public Organization getOrgByorgId(String orgId) throws Exception {
		return (Organization) baseHibernateDAO.getEntityById(Organization.class, orgId);
	}



	
	
	
}
