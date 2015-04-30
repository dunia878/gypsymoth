package com.sinosoft.gypsymoth.bussiness.client.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.sinosoft.gypsymoth.dao.BaseHibernateDAO;
import com.sinosoft.gypsymoth.pojo.Account;
import com.sinosoft.gypsymoth.pojo.AccountRecord;
import com.sinosoft.gypsymoth.pojo.City;
import com.sinosoft.gypsymoth.pojo.Client;
import com.sinosoft.gypsymoth.pojo.Clientlog;
import com.sinosoft.gypsymoth.pojo.Clients;
import com.sinosoft.gypsymoth.pojo.Nation;
import com.sinosoft.gypsymoth.pojo.Promary;
import com.sinosoft.gypsymoth.utils.Constants;
import com.sinosoft.gypsymoth.utils.StringUtils;

public class ClientServiceImpl implements ClientService {

	private BaseHibernateDAO baseHibernateDAO;

	public BaseHibernateDAO getBaseHibernateDAO() {
		return baseHibernateDAO;
	}

	public void setBaseHibernateDAO(BaseHibernateDAO baseHibernateDAO) {
		this.baseHibernateDAO = baseHibernateDAO;
	}

	/**
	 * 批量删除
	 * 
	 * @param id
	 * @param log
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void deleteClient(String clientId) throws Exception {
		String sql = "delete from client where clientid in(";
		List list = new ArrayList();
		list.add(clientId);
		baseHibernateDAO.deleteBatchBySql(sql, list);
	}

	/**
	 * 获得国家
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getNation() throws Exception {
		String sql = " select nation_id as nationId,nation_name as nationName,nation_cn_name,nation_en_name from nation order by nation_en_name";
		List list = new ArrayList();
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;
	}

	/**
	 * 获得省份
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getPromory() throws Exception {
		String sql = " select * from promary";
		List list = new ArrayList();
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;
	}

	/**
	 * 获得市级
	 * 
	 * @param proId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getCity(Integer proId) throws Exception {
		String sql = " select CITYID as cityId,CITYNAME as cityName from city where PROID=? order by CITYID";
		List list = new ArrayList();
		list.add(proId);
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;
	}

	/**
	 * 保存客户日志数据
	 * 
	 * @param client
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void saveClientLog(Client client) throws Exception {
		Map session = ActionContext.getContext().getSession();
		SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		date = simp.parse(simp.format(date));
		Clientlog clientlog = new Clientlog();
		clientlog
				.setModifyBy(((Account) session.get(Constants.ACCOUNT_SESSION))
						.getAccountName());
		clientlog.setModifyDate(date);
		baseHibernateDAO.saveEntity(clientlog);
	}

	/**
	 * 删除ACCOUNT ACCOUNTROLE Client表里面的数据
	 */
	public void deleteClientById(Long clientId) throws Exception {
		this.saveClientLog(clientId);
		this.deleteAccount_Role(clientId);
		this.deleteAccount(clientId);
		baseHibernateDAO.deleteEntity(Client.class, clientId);

	}

	/**
	 * 更行修改 添加LOG表
	 */
	public void updateClient(Client client) throws Exception {
		this.saveClientLogs(client);
		baseHibernateDAO.updateEntity(client);
	}

	/**
	 * 保存客户修改记录
	 * 
	 * @param clientId
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void saveClientLogs(Client client) throws Exception {
		Map session = ActionContext.getContext().getSession();
		SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		date = simp.parse(simp.format(date));
		Clientlog clog = new Clientlog();
		clog.setAccountId(client.getAccountId());
		clog.setAddress(client.getAddress());
		clog.setBusinessLicense(client.getBusinessLicense());
		clog.setCityid(client.getCityid());
		clog.setClientId(client.getClientId());
		clog.setCoCnName(client.getCoCnName());
		clog.setCoEnName(client.getCoEnName());
		clog.setCoProperty(client.getCoProperty());
		clog.setDes(client.getDes());
		clog.setEmail(client.getEmail());
		clog.setNationId(client.getNationId());
		clog.setLegalPerson(client.getLegalPerson());
		clog.setTwoPersonTel(client.getTwoPersonTel());
		clog.setTwoPersonName(client.getTwoPersonName());
		clog.setModifyBy(((Account) session.get(Constants.ACCOUNT_SESSION))
				.getAccountName());
		clog.setModifyDate(date);
		clog.setModifyType(1);
		clog.setProid(client.getProid());
		clog.setOnePersonName(client.getOnePersonName());
		clog.setOnePersonPhone(client.getOnePersonPhone());
		baseHibernateDAO.saveEntity(clog);
	}

	/**
	 * 保存客户删除记录
	 * 
	 * @param clientId
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void saveClientLog(Long clientId) throws Exception {
		Client client = this.getClientById(clientId);
		Map session = ActionContext.getContext().getSession();
		SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		date = simp.parse(simp.format(date));
		Clientlog clog = new Clientlog();
		clog.setAccountId(client.getAccountId());
		clog.setAddress(client.getAddress());
		clog.setBusinessLicense(client.getBusinessLicense());
		clog.setCityid(client.getCityid());
		clog.setClientId(client.getClientId());
		clog.setCoCnName(client.getCoCnName());
		clog.setCoEnName(client.getCoEnName());
		clog.setCoProperty(client.getCoProperty());
		clog.setDes(client.getDes());
		clog.setEmail(client.getEmail());
		clog.setNationId(client.getNationId());
		clog.setLegalPerson(client.getLegalPerson());
		clog.setTwoPersonTel(client.getTwoPersonTel());
		clog.setTwoPersonName(client.getTwoPersonName());
		clog.setModifyBy(((Account) session.get(Constants.ACCOUNT_SESSION))
				.getAccountName());
		clog.setModifyDate(date);
		clog.setModifyType(2);
		clog.setOnePersonName(client.getOnePersonName());
		clog.setOnePersonPhone(client.getOnePersonPhone());
		clog.setProid(client.getProid());
		clog.setVbrk(client.getVbrk());
		clog.setBankaccount(client.getBankaccount());
		baseHibernateDAO.saveEntity(clog);
	}

	/**
	 * 保存删除记录
	 * 
	 * @param clientId
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void saveAccountLog(Long clientId) throws Exception {
		Map session = ActionContext.getContext().getSession();
		SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		date = simp.parse(simp.format(date));
		Client client = this.getClientById(clientId);
		String accountId = client.getAccountId();
		Account account = this.getAccountByAccountID(accountId);
		if (account != null) {
			AccountRecord ared = new AccountRecord();
			ared.setAccountId(account.getAccountId());
			ared.setAccountName(account.getAccountName());
			ared.setAccountStatus(account.getAccountStatus());
			ared.setAccountType(account.getAccountType());
			ared.setModifyBy(((Account) session.get(Constants.ACCOUNT_SESSION))
					.getAccountName());
			ared.setModifyDate(date);
			ared.setModifyType(2);
			ared.setPassword(account.getPassword());
			this.baseHibernateDAO.saveEntity(ared);
		} else {
			AccountRecord ared = new AccountRecord();
			ared.setAccountId(accountId);
			ared.setModifyBy(((Account) session.get(Constants.ACCOUNT_SESSION))
					.getAccountName());
			ared.setModifyDate(date);
			ared.setModifyType(2);
			this.baseHibernateDAO.saveEntity(ared);
		}

	}

	/**
	 * 删除ACCOUNT表数据
	 * 
	 * @param clientId
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void deleteAccount(Long clientId) throws Exception {
		String sql = "delete from Account rr where rr.account_id  =(select account_id "
				+ "from Client t where t.client_id = ";
		List list = new ArrayList();
		list.add(clientId);
		baseHibernateDAO.deleteBatchBySql(sql, list);
	}

	/**
	 * 删除ACCOUNT 关系表
	 * 
	 * @param clientId
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void deleteAccount_Role(Long clientId) throws Exception {

		String sql = "delete from Account_Role rr where rr.account_id =(select account_id "
				+ " from  Client t where t.client_id = ";
		List list = new ArrayList();
		list.add(clientId);
		baseHibernateDAO.deleteBatchBySql(sql, list);
	}

	/**
	 * 查询所有数据
	 */
	@SuppressWarnings("unchecked")
	public List<Client> getAllDataByPage(int begin, int numOfEachPage)
			throws Exception {
		String sql = "from Client r order by r.clientId desc";
		List list = new ArrayList();
		List<Client> returnList = baseHibernateDAO.getEntityByPage(sql, list,
				begin, numOfEachPage);
		return returnList;
	}

	/**
	 * 根据ID查找Client Entity
	 */
	public Client getClientById(Long clientId) throws Exception {
		return (Client) baseHibernateDAO.getEntityById(Client.class, clientId);

	}

	// /**
	// * 根据ID查找Client Entity
	// */
	// public Client getclientbyaccountid(String accountId) throws Exception {
	// return (Client) baseHibernateDAO.getEntityById(Client.class, clientId);
	//
	// }
	//	
	/**
	 * 根据ID查找nation
	 */
	public Nation getNationById(String nationId) throws Exception {
		Integer id = Integer.parseInt(nationId);
		return (Nation) baseHibernateDAO.getEntityById(Nation.class, id);
	}

	/**
	 * 根据ID查找Account
	 */
	public Account getAccountByAccountID(String accountID) throws Exception {
		return (Account) baseHibernateDAO.getEntityById(Account.class,
				accountID);
	}

	/**
	 * 根据ID查找Promary
	 */
	public Promary getPromoryById(Long proid) throws Exception {
		return (Promary) baseHibernateDAO.getEntityById(Promary.class, proid);
	}

	/**
	 * 根据ID查找City
	 */
	public City getCityById(Long cityid) throws Exception {
		return (City) baseHibernateDAO.getEntityById(City.class, cityid);
	}

	/**
	 * 根据ID查找City
	 */
	@SuppressWarnings("unchecked")
	public List getCityByPromaryID(String clientId) throws Exception {
		String sql = " select distinct c.cityname"
				+ " from client t, city c, promary p"
				+ " where (select t.proid from client a where a.client_id = ?) = c.proid"
				+ " and (select t.cityid from client b where b.client_id = ?) = c.cityid"
				+ " and t.client_id = ? ";
		List list = new ArrayList();
		list.add(clientId);
		list.add(clientId);
		list.add(clientId);
		List listclient = baseHibernateDAO.getEntityBySql(sql, list);
		return listclient;

	}

	/**
	 * 分页查询数据 带查询功能
	 */
	@SuppressWarnings("unchecked")
	public List<Client> getClientByPage(Client client, int begin,
			int numOfEachPage) throws Exception {
		String sql = " from Client e where ";
		List list = new ArrayList();
		if (client.getCoEnName() != null && !"".equals(client.getCoEnName())) {
			sql += " e.coEnName like ? and  ";
			list.add(client.getCoEnName() + "%");
		}
		if (client.getCoCnName() != null && !"".equals(client.getCoCnName())) {
			sql += " e.coCnName like ? and  ";
			list.add(client.getCoCnName() + "%");
		}
		sql += " 1=1 order by e.clientId asc ";
		List<Client> clientList = baseHibernateDAO.getEntityByPage(sql, list,
				begin, numOfEachPage);
		return clientList;
	}

	/**
	 * 分页查询所有数据 带首页查询功能
	 */
	@SuppressWarnings("unchecked")
	public int getAllDataCounts(Client client) throws Exception {
		String sql = " select count(*) from Client e where ";
		List list = new ArrayList();
		if (client.getCoEnName() != null && !"".equals(client.getCoEnName())) {
			sql += " e.co_en_name like ? and  ";
			list.add("%" + client.getCoEnName() + "%");
		}
		if (client.getCoCnName() != null && !"".equals(client.getCoCnName())) {
			sql += " e.co_cn_name like ? and  ";
			list.add("%" + client.getCoCnName() + "%");
		}
		sql += " 1=1 ";
		int count = baseHibernateDAO.getEntitiesCountBySql(sql, list);
		return count;
	}

	public int getAllDataCountss(HashMap map) throws Exception {
		String sql = " select count(*) from ("
				+ " select account_name,client_id,account_id, co_cn_name, co_en_name,client_name, sum(cn) from ("
				+ " select a1.account_name,m.client_id,m.account_id, m.co_cn_name, m.co_en_name,m.client_name, 0 cn"
				+ " from client m , account a1"
				+ "   where m.account_id = a1.account_id"
				+ " union (select a2.account_name, t.client_id, t.account_id, t.co_cn_name, t.co_en_name,t.client_name, count(*) cn"
				+ " from client t, business b,account a2"
				+ " where b.accountid = a2.account_id and b.businessstate = '5' and b.businessname=t.co_en_name"
				+ " group by t.client_id,t.account_id, t.co_cn_name, t.co_en_name, a2.account_name,t.client_name))  where ";

		List paralist = new ArrayList();
		String coCnName_search = (String) map.get("coCnName_search");
		if (coCnName_search != null && !coCnName_search.equals("")) {
			sql += "  co_cn_name like ?    and ";
			paralist
					.add("%" + StringUtils.processSearch(coCnName_search) + "%");
		}

		String coEnName_search = (String) map.get("coEnName_search");
		if (coEnName_search != null && !coEnName_search.equals("")) {
			sql += "  upper(co_en_name) like ?    and ";
			paralist
					.add("%" + StringUtils.processSearch(coEnName_search) + "%");
		}
		//新增客户 用户名查询
		String clientName = (String) map.get("clientName");
		if(clientName != null && !"".equals(clientName)) {
			sql += " upper(CLIENT_NAME) like ?  and ";
			paralist.add("%" + clientName + "%");
		}
		
		sql += "1=1  group by client_id, account_id, co_cn_name, co_en_name ,client_name,account_name)";
		int count = baseHibernateDAO.getEntitiesCountBySql(sql, paralist);
		return count;
	}

	/**
	 * 获得客户自己的客户的列表
	 */
	public int getAllData(HashMap map) throws Exception {
		String sql = " select count(*) from ("
				+ " select parent_id,client_id,account_id, co_cn_name, co_en_name, sum(cn) from ("
				+ " select m.parent_id,m.client_id,m.account_id, m.co_cn_name, m.co_en_name, 0 cn"
				+ " from client m "
				+ " union (select  t.parent_id,t.client_id, t.account_id, t.co_cn_name, t.co_en_name, count(*) cn"
				+ " from client t, business b "
				+ " where  t.account_id = b.accountid and b.businessstate= '5'  "
				+ " group by t.parent_id,t.client_id,t.account_id, t.co_cn_name, t.co_en_name))  where ";

		List paralist = new ArrayList();
		String coCnName_search = (String) map.get("coCnName_search");
		if (coCnName_search != null && !coCnName_search.equals("")) {
			sql += "  co_cn_name like ?    and ";
			paralist
					.add("%" + StringUtils.processSearch(coCnName_search) + "%");
		}

		String coEnName_search = (String) map.get("coEnName_search");
		if (coEnName_search != null && !coEnName_search.equals("")) {
			sql += "  co_en_name like ?    and ";
			paralist
					.add("%" + StringUtils.processSearch(coEnName_search) + "%");
		}

		String parent_id = (String) map.get("parent_Id");
		if (parent_id != null && !parent_id.equals("")) {
			sql += "  parent_id = ?    and ";
			paralist.add(StringUtils.processSearch(parent_id));
		}

		sql += " 1=1  group by parent_id,client_id, account_id, co_cn_name, co_en_name )";
		int count = baseHibernateDAO.getEntitiesCountBySql(sql, paralist);
		return count;
	}

	/**
	 * 分页查询
	 * 
	 * @param begin
	 * @param numOfEachPage
	 * @param map
	 * @return
	 * @throws Exception
	 */

	public List getClientByPages(int begin, int numOfEachPage, HashMap map)
			throws Exception {
		List paralist = new ArrayList();
		String sql = " select account_name,client_id,account_id, co_cn_name, co_en_name,client_name, sum(cn) as examinnum from ("
				+ " select a1.account_name,m.client_id,m.account_id, m.co_cn_name, m.co_en_name,m.client_name, 0 cn"
				+ " from client m, account a1"
				+ "   where m.account_id = a1.account_id"
				+ " union (select a2.account_name, t.client_id,t.account_id, t.co_cn_name, t.co_en_name,t.client_name, count(*) cn"
				+ " from client t, business b,account a2"
				+ " where  b.accountid = a2.account_id and b.businessstate = '5' and b.businessname=t.co_en_name"
				+ " group by t.client_id,t.account_id, t.co_cn_name, t.co_en_name ,t.client_name, a2.account_name))   where ";

		String coCnName_search = (String) map.get("coCnName_search");
		if (coCnName_search != null && !coCnName_search.equals("")) {
			sql += "  co_cn_name like ?    and ";
			paralist
					.add("%" + StringUtils.processSearch(coCnName_search) + "%");
		}

		String coEnName_search = (String) map.get("coEnName_search");
		if (coEnName_search != null && !coEnName_search.equals("")) {
			sql += "  upper(co_en_name) like ?    and ";
			paralist
					.add("%" + StringUtils.processSearch(coEnName_search) + "%");
		}
		//新增客户 用户名查询
		String clientName = (String) map.get("clientName");
		if(clientName != null && !"".equals(clientName)) {
			sql += " upper(CLIENT_NAME) like ?  and ";
			paralist.add("%" + clientName + "%");
		}
		sql += " 1=1 group by client_id, account_id, co_cn_name, co_en_name ,client_name, account_name order by client_id  desc";
		List list = baseHibernateDAO.getEntityByPageBySql(sql, paralist, begin,
				numOfEachPage);

		return list;
	}

	/**
	 * 分页查询(获得客户的客户列表)
	 * 
	 * @param begin
	 * @param numOfEachPage
	 * @param map
	 * @return
	 * @throws Exception
	 */

	public List getClientByPage(int begin, int numOfEachPage, HashMap map)
			throws Exception {
		List paralist = new ArrayList();
		String sql = " select parent_id,client_id,account_id, co_cn_name, co_en_name, sum(cn) as examinnum from ("
				+ " select  m.parent_id,m.client_id,m.account_id, m.co_cn_name, m.co_en_name, 0 cn"
				+ " from client m"
				+ " union (select  t.parent_id,t.client_id,t.account_id, t.co_cn_name, t.co_en_name, count(*) cn"
				+ " from client t, business b"
				+ " where t.account_id = b.accountid and b.businessstate= '5'   "
				+ " group by  t.parent_id,t.client_id,t.account_id, t.co_cn_name, t.co_en_name ))   where ";

		String coCnName_search = (String) map.get("coCnName_search");
		if (coCnName_search != null && !coCnName_search.equals("")) {
			sql += "  co_cn_name like ?    and ";
			paralist
					.add("%" + StringUtils.processSearch(coCnName_search) + "%");
		}

		String coEnName_search = (String) map.get("coEnName_search");
		if (coEnName_search != null && !coEnName_search.equals("")) {
			sql += "  co_en_name like ?    and ";
			paralist
					.add("%" + StringUtils.processSearch(coEnName_search) + "%");
		}

		String parent_Id = (String) map.get("parent_Id");
		if (parent_Id != null && !parent_Id.equals("")) {
			sql += "  parent_id = ?    and ";
			paralist.add(StringUtils.processSearch(parent_Id));
		}
		sql += " 1=1 group by parent_id,client_id, account_id, co_cn_name, co_en_name order by client_id  desc";
		List list = baseHibernateDAO.getEntityByPageBySql(sql, paralist, begin,
				numOfEachPage);

		return list;
	}

	/**
	 * 分页查询所有数据 带首页查询功能
	 */
	@SuppressWarnings("unchecked")
	public int getNumByClient(String clientId) throws Exception {
		String sql = " select count(*) from Business b ,"
				+ " Client c where b.accountid = c.account_id and c.clientId = ? ";
		List list = new ArrayList();
		list.add(clientId);
		int count = baseHibernateDAO.getEntitiesCountBySql(sql, list);
		return count;
	}

	@SuppressWarnings("unchecked")
	public List<Client> getClientIds() throws Exception {
		String sql = "select e.client_id  from Client e  ";
		List list = new ArrayList();

		list = baseHibernateDAO.getEntityBySql(sql, list);
		return list;
	}

	/**
	 * 根据用户ID获取客户的ID
	 * 
	 * @param accountId
	 * @return
	 * @throws Exception
	 */
	public List getclientIdbyAccountId(String accountId) throws Exception {
		List list = new ArrayList();
		String sql = " select c.CLIENT_ID from CLIENT c where c.ACCOUNT_ID=? ";
		list.add(accountId);
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;
	}

	/**
	 * 保存Clients
	 * 
	 * @param
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void saveClients(Client client) throws Exception {

		baseHibernateDAO.saveEntity(client);

	}

	/** 通过account_id得到client */
	public Client getClientByAccountID(String account_id) throws Exception {
		Client client = null;
		List paralist = new ArrayList();
		paralist.add(account_id);
		String hql = " from Client c where c.accountId = ? ";
		List list = baseHibernateDAO.getEntity(hql, paralist);
		if (list != null && list.size() > 0) {
			client = (Client) list.get(0);
		}
		return client;
	}

	public List getClientListById(Long id) throws Exception {
		List paralist = new ArrayList();
		String sql = " SELECT * FROM CLIENT T WHERE T.CLIENT_ID = ? "
				+ "	UNION "
				+ "	   SELECT * FROM CLIENT T WHERE T.PARENT_ID = ? ";
		paralist.add(String.valueOf(id));
		paralist.add(String.valueOf(id)); 
		
		List list = baseHibernateDAO.getEntityBySql(sql, paralist);
		return list; 
	}

	public List getClientList() throws Exception {
		List paralist = new ArrayList();
		String sql = " SELECT * FROM CLIENT T order by lower(CO_EN_NAME) ";
		List list = baseHibernateDAO.getEntityBySql(sql, paralist);
		return list;
	}
		
	public  List getChild(Client client) throws Exception {
		Long client_id = client.getClientId();
		//String sql = " select * from client t where client_id= " + client_id;
		
		
		List paralist = new ArrayList();
		String sql = " select parent_id,client_id,account_id, co_cn_name, co_en_name, sum(cn) as examinnum from ("
				+ " select  m.parent_id,m.client_id,m.account_id, m.co_cn_name, m.co_en_name, 0 cn"
				+ " from client m"
				+ " union (select  t.parent_id,t.client_id,t.account_id, t.co_cn_name, t.co_en_name, count(*) cn"
				+ " from client t, business b"
				+ " where t.account_id = b.accountid and b.businessstate= '5'   "
				+ " group by  t.parent_id,t.client_id,t.account_id, t.co_cn_name, t.co_en_name ))   where ";

	

		if (client_id != null && !client_id.equals("")) {
			sql += "  parent_id = ?    and ";
			paralist.add(StringUtils.processSearch(String.valueOf(client_id)));
		}
		sql += " 1=1 group by parent_id,client_id, account_id, co_cn_name, co_en_name ";
		List list = baseHibernateDAO.getEntityBySql(sql, paralist);
		System.out.println("---------------------------------" + list + list.size());
		return list;
	}; 
	
	/** 得到clients_sequ */
	public String getClientsSequ() throws Exception {
		String sql = " select clients_sequ.nextval from dual "; 
		String seq = baseHibernateDAO.getSequenceFromSql(sql);
		return seq;
	}

}
