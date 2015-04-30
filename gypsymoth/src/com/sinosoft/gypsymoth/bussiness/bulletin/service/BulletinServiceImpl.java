package com.sinosoft.gypsymoth.bussiness.bulletin.service;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.sinosoft.gypsymoth.dao.BaseHibernateDAO;
import com.sinosoft.gypsymoth.pojo.Account;
import com.sinosoft.gypsymoth.pojo.AccountRole;
import com.sinosoft.gypsymoth.pojo.Bulletin;
import com.sinosoft.gypsymoth.pojo.Linkus;
import com.sinosoft.gypsymoth.utils.Constants;
import com.sinosoft.gypsymoth.utils.LobFactory;


    public class BulletinServiceImpl  implements  BulletinService  {
	
	
	private BaseHibernateDAO baseHibernateDAO;

	public BaseHibernateDAO getBaseHibernateDAO() {
		return baseHibernateDAO;
	}

	public void setBaseHibernateDAO(BaseHibernateDAO baseHibernateDAO) {
		this.baseHibernateDAO = baseHibernateDAO;
	}

	/**
	 * 根据类型获取联系人员信息
	 * @param accountId
	 * @return
	 * @throws Exception
	 */
	public List getLinkusByType(String linkustype) throws Exception {
		List list = new ArrayList();
		String sql = " select  t.LINKUSID from LINKUS t where t.LINKUSTYPE = ?";
		list.add(linkustype);
		List returnList = baseHibernateDAO.getEntityBySql(sql, list);
		return returnList;
	}

	
	
	/**
	 * 根据ID查找Entity
	 */
	public Linkus getLinkusById(Long  linkusid)throws Exception{
		List list = LobFactory.read1(linkusid);
		Map map = new HashMap();
		String content="";
		if(list.size()>0){
			map = (Map)list.get(0);
			content = (String)map.get("LINKUSCONTENT");
		}
		Linkus linkus = new Linkus();
		linkus = (Linkus) baseHibernateDAO.getEntityById(Linkus.class, linkusid);
		linkus.setLinkuscontent(content);
		return linkus;
		 
	}

	/**
	 * 批量删除
	 * @param id
	 * @param log
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void deleteBulletin(String bulletinId) throws Exception {	
		String sql="delete from bulletin where bulletinid in(";
		List list=new ArrayList();
		list.add(bulletinId);
		baseHibernateDAO.deleteBatchBySql(sql, list);
	}
	
	
	/**
	 * 根据用户Id查询角色
	 * @param accountId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<AccountRole> getAccountRole(String accountId) throws Exception{
		String hql = " from AccountRole ar where ar.accountId=? order by ar.roleId";
		List list = new ArrayList();
		list.add(accountId);
		List<AccountRole> returnList = baseHibernateDAO.getEntity(hql, list);
		return returnList;
	}
	
	
	/**
	 * 新增公告
	 */
	
	@SuppressWarnings("unchecked")
	public void save(Bulletin bulletin,File getUpload,String filename,String packName) throws Exception{
		Map session = ActionContext.getContext().getSession();
		String fileurl = this.upload(getUpload, filename, packName);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		bulletin.setBulletinTime(sdf.parse(sdf.format(new Date())));
		bulletin.setAccountName(((Account) session
				.get(Constants.ACCOUNT_SESSION)).getAccountName());
		bulletin.setFileurl(fileurl);
		bulletin.setFilename(filename);
		baseHibernateDAO.saveEntity(bulletin);
		
	}
	

	


	
	/**
	 * 图片上传
	 * @param getUpload 文件对象流
	 * @param getUploadFileName 上传文件名
	 * @param packFile 保存文件的文件夹名称
	 * @return 保存文件的全路径
	 * @throws Exception
	 */
	public String upload(File getUpload,String filename,String packFile) throws Exception{
		String rootPath = "d:/";
		// 获取文件名
		String saveName =  packFile + "/"  + filename;
		FileOutputStream fos = null;
		FileInputStream fis = null;
		
			if(!new File(rootPath + packFile + "/").exists()){
				new File(rootPath + packFile + "/").mkdirs();	// 检查文件夹是否存在，如不存在创建
			}
			fos = new FileOutputStream(rootPath+saveName);
			fis = new FileInputStream(getUpload);
			// 将上传文件的内容写入服务器
			byte[] buffer = new byte[1024];
			int len = 0;
			while((len = fis.read(buffer))>0){
				fos.write(buffer,0,len);
			}
			fos.close();
			fis.close();
			
		return "/"+saveName;
	}

	/**
	 * @see  客户查看 公告 首页(英文)
	 * 分页显示所有公告(标题、发布时间、内容、发布人、查看权限)
	 */
	@SuppressWarnings("unchecked")
	public List<Bulletin> getIndexDataByPage(int begin,int numOfEachPage ) throws Exception{
		String sql = "from Bulletin b where b.bulletinTip in(2,3)  and ( b.bulletinLanguage = 2  or b.bulletinLanguage =3   )  order by b.bulletinTips , b.bulletinTime desc";
		List list = new ArrayList();
		List <Bulletin> indexList = baseHibernateDAO.getEntityByPage(sql,list,begin,numOfEachPage);
		return indexList;
	}
	
	/**
	 * @see  客户查看 公告 首页 (中文)
	 * 分页显示所有公告(标题、发布时间、内容、发布人、查看权限)
	 */
	@SuppressWarnings("unchecked")
	public List<Bulletin> getIndexDataByPageCH(int begin,int numOfEachPage ) throws Exception{
		String sql = "from Bulletin b where b.bulletinTip in(2,3)  and ( b.bulletinLanguage = 1  or b.bulletinLanguage =3   )  order by b.bulletinTips ,b.bulletinTime desc";
		List list = new ArrayList();
		List <Bulletin> indexList = baseHibernateDAO.getEntityByPage(sql,list,begin,numOfEachPage);
		return indexList;
	}
	/**
	 * @see  未登录 bulletinTips=2
	 * 分页显示所有公告(标题、发布时间、内容、发布人、查看权限)
	 */
	@SuppressWarnings("unchecked")
	public List<Bulletin> getAllDataByPage(int begin,int numOfEachPage ) throws Exception{
		String sql = "from Bulletin b  where b.bulletinTip in(2,3)  and   (b.bulletinLanguage = 2  or b.bulletinLanguage =3) order by b.bulletinTips, b.bulletinTime desc";
		List list = new ArrayList();
		List<Bulletin>bulletinlist = baseHibernateDAO.getEntityByPage(sql,list,begin,numOfEachPage);
		return bulletinlist;
	}
	
	/**
	 * @see  未登录 bulletinTips=2 中文
	 * 分页显示所有公告(标题、发布时间、内容、发布人、查看权限)
	 */
	@SuppressWarnings("unchecked")
	public List<Bulletin> getAllDataByPageCH(int begin,int numOfEachPage ) throws Exception{
		String sql = "from Bulletin b  where b.bulletinTip in(2,3)  and   (b.bulletinLanguage = 1  or b.bulletinLanguage =3) order by b.bulletinTips ,b.bulletinTime desc";
		List list = new ArrayList();
		List<Bulletin>bulletinlist = baseHibernateDAO.getEntityByPage(sql,list,begin,numOfEachPage);
		return bulletinlist;
	}
	
	/**
	 * @see   已登录的用户(管理级别)(英文)
	 * 分页显示所有公告(标题、发布时间、内容、发布人、查看权限)
	 */
	@SuppressWarnings("unchecked")
	public List<Bulletin> getAllDataByPages(Bulletin bulletin ,int begin,int numOfEachPage ) throws Exception{
		String sql = "from Bulletin b   order by b.bulletinTips ,b.bulletinTime desc";
		List list = new ArrayList();
		List<Bulletin>bulletinlist = baseHibernateDAO.getEntityByPage(sql,list,begin,numOfEachPage);
		return bulletinlist;
	}
	
	/**
	 * @see   已登录的用户(管理级别)(中文)
	 * 分页显示所有公告(标题、发布时间、内容、发布人、查看权限)
	 */
	@SuppressWarnings("unchecked")
	public List<Bulletin> getAllDataByPagesCH(int begin,int numOfEachPage ) throws Exception{
		String sql = "from Bulletin b where b.bulletinLanguage =1 or b.bulletinLanguage =3   order by b.bulletinTime desc";
		List list = new ArrayList();
		List<Bulletin>bulletinlist = baseHibernateDAO.getEntityByPage(sql,list,begin,numOfEachPage);
		return bulletinlist;
	}
	
	
	/**
	 * @see  已登录的用户(非管理级别)  bulletinTips=1 & and bulletinTip=1
	 * 分页显示所有公告(标题、发布时间、内容、发布人、查看权限)
	 */
	@SuppressWarnings("unchecked")
	public List<Bulletin> getAllADataByPage(int begin,int numOfEachPage ) throws Exception{
		String sql = "from Bulletin b  where b.bulletinTip in(1,2) and (b.bulletinLanguage =2 or b.bulletinLanguage =3)  order by b.bulletinTips,b.bulletinTime desc";
		List list = new ArrayList();
		List<Bulletin>bulletinlist = baseHibernateDAO.getEntityByPage(sql,list,begin,numOfEachPage);
		return bulletinlist;
	}
	
	/**
	 * @see  已登录的用户(非管理级别)  bulletinTips=1 & and bulletinTip=1
	 * 分页显示所有公告(标题、发布时间、内容、发布人、查看权限)
	 */
	@SuppressWarnings("unchecked")
	public List<Bulletin> getAllADataByPageCH(int begin,int numOfEachPage ) throws Exception{
		String sql = "from Bulletin b  where b.bulletinTip in(1,2)  order by b.bulletinTips , b.bulletinTime desc";
		List list = new ArrayList();
		List<Bulletin>bulletinlist = baseHibernateDAO.getEntityByPage(sql,list,begin,numOfEachPage);
		return bulletinlist;
	}
	
	/**
	 * @see  已登录的客户 bulletinTips=1 & and bulletinTip=2
	 * 分页显示所有公告(标题、发布时间、内容、发布人、查看权限)
	 */
	@SuppressWarnings("unchecked")
	public List<Bulletin> getAllCDataByPage(int begin,int numOfEachPage ) throws Exception{
		String sql = "from Bulletin b where b.bulletinTip in(2,3)   order by b.bulletinTime desc";
		List list = new ArrayList();
		List<Bulletin>bulletinlist = baseHibernateDAO.getEntityByPage(sql,list,begin,numOfEachPage);
		return bulletinlist;
	}
	/**
	 * @see  未登录  bulletinTips=2
	 * 记录数
	 */
	@SuppressWarnings("unchecked")
	public int getAllDataCount() throws Exception{
		List list = new ArrayList();
		String sql = "select count(*) from bulletin b where b.bulletinTips=2";
		int count = baseHibernateDAO.getEntitiesCountBySql(sql, list);
		return count;
	}
	
	/**
	 * @see  未登录  bulletinTips=2
	 * 记录数
	 */
	@SuppressWarnings("unchecked")
	public int getAllDataCountOut() throws Exception{
		List list = new ArrayList();
		String sql = "select count(*) from bulletin  b where b.bulletinTip in(2,3) and (b.bulletinLanguage =2 or b.bulletinLanguage =3)";
		int count = baseHibernateDAO.getEntitiesCountBySql(sql, list);
		return count;
	}
	
	/**
	 * @see  未登录  bulletinTips=2 中文
	 * 记录数
	 */
	@SuppressWarnings("unchecked")
	public int getAllDataCountOutCH() throws Exception{
		List list = new ArrayList();
		String sql = "select count(*) from bulletin  b where b.bulletinTip in(2,3) and (b.bulletinLanguage =1 or b.bulletinLanguage =3)";
		int count = baseHibernateDAO.getEntitiesCountBySql(sql, list);
		return count;
	}
	/**
	 * @see 已登录的用户(管理级别)
	 * 记录数
	 */
	@SuppressWarnings("unchecked")
	public int getAllDataCounts() throws Exception{
		List list = new ArrayList();
		String sql = "select count(*) from bulletin ";
		int count = baseHibernateDAO.getEntitiesCountBySql(sql, list);
		return count;
	}
	
	/**
	 * @see 已登录的用户(管理级别)(中文)
	 * 记录数
	 */
	@SuppressWarnings("unchecked")
	public int getAllDataCountsCH() throws Exception{
		List list = new ArrayList();
		String sql = "select count(*) from bulletin b where b.bulletinLanguage =1 or b.bulletinLanguage =3";
		int count = baseHibernateDAO.getEntitiesCountBySql(sql, list);
		return count;
	}
	
	/**
	 * @see  已登录的用户(非管理级别)  bulletinTips=1 & and bulletinTip=1
	 * 记录数
	 */
	@SuppressWarnings("unchecked")
	public int getAllADataCount() throws Exception{
		List list = new ArrayList();
		String sql = "select count(*) from bulletin  b where b.bulletinTip in (1,2) ";
		int count = baseHibernateDAO.getEntitiesCountBySql(sql, list);
		return count;
	}
	
	/**
	 * @see  已登录的用户(非管理级别)  bulletinTips=1 & and bulletinTip=1
	 * 记录数 中文
	 */
	@SuppressWarnings("unchecked")
	public int getAllADataCountCH() throws Exception{
		List list = new ArrayList();
		String sql = "select count(*) from bulletin  b where b.bulletinTip in (1,2)  ";
		int count = baseHibernateDAO.getEntitiesCountBySql(sql, list);
		return count;
	}
	
	/**
	 * @see  已登录的客户 bulletinTips=1 & and bulletinTip=2
	 * 记录数
	 */
	@SuppressWarnings("unchecked")
	public int getAllCDataCount() throws Exception{
		List list = new ArrayList();
		String sql = "select count(*) from bulletin where bulletin.bulletinTip in(2,3) ";
		int count = baseHibernateDAO.getEntitiesCountBySql(sql, list);
		return count;
	}
	
	
	/**
	 * 删除公告
	 */
	public void deleteBulletinById(Long  bulletinId)throws Exception{
		baseHibernateDAO.deleteEntity(Bulletin.class, bulletinId);
	}
	
	
	/**
	 * 删除集团联系方式
	 */
	public void deleteLinkusById(Long  linkusid)throws Exception{
		baseHibernateDAO.deleteEntity(Linkus.class, linkusid);
	}
	
	/**
	 * 批量删除公告
	 */
	@SuppressWarnings("unchecked")
	public void deleteBulletinListById(List  bulletinIds)throws Exception{
		String sql = " delete from bulletin b where b.bulletinId in(   ";
		baseHibernateDAO.deleteBatchBySql(sql, bulletinIds);
		
	}
	
	
	/**
	 * 根据ID查找Entity
	 */
	public Bulletin getBulletinById(Long  bulletinId)throws Exception{
		List list = LobFactory.read(bulletinId);
		Map map = new HashMap();
		String content="";
		if(list.size()>0){
			map = (Map)list.get(0);
			content = (String)map.get("BULLETINCONTENT");
		}
		Bulletin bulletin = new Bulletin();
		bulletin = (Bulletin)baseHibernateDAO.getEntityById(Bulletin.class, bulletinId);
		bulletin.setBulletinContent(content);
		return bulletin;
		 
	}
	
	/**
	 * 修改无图片
	 */
	public void updateBulletin(Bulletin bulletin)throws Exception{
		String content = bulletin.getBulletinContent();
		bulletin.setBulletinContent("");//先往里插入一个空值 必须的
		baseHibernateDAO.updateEntity(bulletin);
		LobFactory.update(content, bulletin.getBulletinId());
		
	}
	
	/**
	 * 修改更新
	 */
	public void updateBulletins(Bulletin bulletin,File getUpload,String filename,String packName)throws Exception{
		String fileurl = this.upload(getUpload, filename, packName);
		bulletin.setFileurl(fileurl);
		bulletin.setFilename(filename);
		baseHibernateDAO.updateEntity(bulletin);
		
	}
	
	/**
	 * 修改图片更新
	 */
	public void updateBulletins(Bulletin bulletin)throws Exception{
		String content = bulletin.getBulletinContent();
		bulletin.setBulletinContent("");//先往里插入一个空值 必须的
		baseHibernateDAO.updateEntity(bulletin);
		LobFactory.update(content, bulletin.getBulletinId());
		
	}
	/**
	 * 保存操作
	 */
	public  void save(Bulletin bulletin) throws Exception{
		LobFactory.write(bulletin );

    }
	
	/**
	 * 保存操作
	 */
	public  void savelinkus(Linkus linkus) throws Exception{
		LobFactory.write1(linkus);

    }



}
