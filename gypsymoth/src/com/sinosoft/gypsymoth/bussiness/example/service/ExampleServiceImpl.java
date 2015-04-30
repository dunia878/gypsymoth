package com.sinosoft.gypsymoth.bussiness.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sinosoft.gypsymoth.dao.BaseHibernateDAO;
import com.sinosoft.gypsymoth.pojo.Example;


public class ExampleServiceImpl implements ExampleService {
	
	private BaseHibernateDAO baseHibernateDAO;

	public BaseHibernateDAO getBaseHibernateDAO() {
		return baseHibernateDAO;
	}

	public void setBaseHibernateDAO(BaseHibernateDAO baseHibernateDAO) {
		this.baseHibernateDAO = baseHibernateDAO;
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.bussiness.example.service.ExampleService#save(com.sinosoft.gypsymoth.bussiness.example.pojo.Example)
	 */
	public void save(Example example) throws Exception{
		baseHibernateDAO.saveEntity(example);
		baseHibernateDAO.saveEntity(null);
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.bussiness.example.service.ExampleService#getAllData()
	 */
	public List<Example> getAllData() throws Exception{
		String sql = "select new Example(e.name,e.age,e.password,a.accountName) from Example e,Abc a where e.name = a.accountName and a.id=? order by e.id desc";
		List list = new ArrayList();
		list.add("1");
		List<Example>returnList = baseHibernateDAO.getEntity(sql,list);
		return returnList;
	}
	
	public List<Example> getAllDataByPage(int begin,int numOfEachPage ) throws Exception{
		String sql = "select new Example(e.name,e.age,e.password) from Example e order by e.id desc";
		List list = new ArrayList();
		List<Example>returnList = baseHibernateDAO.getEntityByPage(sql,list,begin,numOfEachPage);
		return returnList;
	}
	
	public int getAllDataCount() throws Exception{
		List list = new ArrayList();
		String sql = "select count(*) from example";
		int count = baseHibernateDAO.getEntitiesCountBySql(sql, list);
		return count;
	}
	
	public void select()  throws Exception{
		//exampleDAO.select();
		
		//1查询
		String sql = "select * from example where id=? and name=?";		
		List list = new ArrayList();
		list.add("1");
		list.add("张三");
		List<Example>returnList = baseHibernateDAO.getEntity(sql,list);		
		for(int i=0;i<returnList.size();i++){
			System.out.println("@@@查询@@@"+returnList.get(i).getName());
		}
		//2分页查询
		String sql2 = "select * from example order by id asc";	
		List list2 = new ArrayList();
		List<Example>returnList2 = baseHibernateDAO.getEntityByPage(sql2,list2,0,3);
		for(int i=0;i<returnList2.size();i++){
			System.out.println("@@@分页查询@@@"+returnList2.size());
			System.out.println("@@@分页查询@@@"+returnList2.get(i).getName());
		}
		
		//3取数目
		List list3 = new ArrayList();
		list3.add("3");
		String sql3 = "select count(*) from example where id=?";
		int count = baseHibernateDAO.getEntitiesCountBySql(sql3, list3);
		System.out.println("===@@@取数目@@@"+count);
		
		//4删除
		//this.deleteEntity(Example.class, "1");
		
		/*
		//5添加
		Example example = new Example();
		example.setName("赵六");
		example.setAge(26);
		this.saveEntity(example);
		*/
		
		//6按ID查询
		Example ex = (Example)baseHibernateDAO.getEntityById(Example.class, "2");
		System.out.println("@@@按id查询@@@"+ex.getName());
		
		//7更新
		Example example = new Example();
		example.setAge(30);
		example.setName("赵六");
		example.setId("402892042bcca760012bcca7a47b0001");
		baseHibernateDAO.updateEntity(example);
		
		/*
		//8批量删除
		String sql8 = "delete from example where id in (";
		List list8 = new ArrayList();
		list8.add("6");
		list8.add("7");
		this.deleteBatchBySql(sql8, list8);
		*/
		
		//批量更新
		String sql9 = "update example set age=? where name in('张三','王五')";
		List list9 = new ArrayList();
		list9.add(6);
		baseHibernateDAO.updateBatchBySql(sql9, list9);
	}
	
	/**
	 * 统计查询
	 */
	public List getStatistics(Map statMap) throws Exception{
		List list = new ArrayList();
		String sql = " select count(a.account_type) as num,a.account_type from account a group by a.account_type ";
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;
	}

}
