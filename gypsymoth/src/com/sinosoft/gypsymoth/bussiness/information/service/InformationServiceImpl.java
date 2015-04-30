package com.sinosoft.gypsymoth.bussiness.information.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.sinosoft.gypsymoth.dao.BaseHibernateDAO;
import com.sinosoft.gypsymoth.pojo.Account;
import com.sinosoft.gypsymoth.pojo.AccountRole;
import com.sinosoft.gypsymoth.pojo.Bulletin;
import com.sinosoft.gypsymoth.pojo.Information;
import com.sinosoft.gypsymoth.utils.Constants;


public class InformationServiceImpl  implements  InformationService {
	
	private BaseHibernateDAO baseHibernateDAO;

	public BaseHibernateDAO getBaseHibernateDAO() {
		return baseHibernateDAO;
	}

	public void setBaseHibernateDAO(BaseHibernateDAO baseHibernateDAO) {
		this.baseHibernateDAO = baseHibernateDAO;
	}
	
	
	
	/**
	 * 修改
	 */
	public void updateInformation(Information information)throws Exception{
		baseHibernateDAO.updateEntity(information);
		
	}
	/**
	 * 修改
	 */
	public void updateInformations(Information information,File[] upload,String[] uploadfilename,String packName,String[] oldname)throws Exception{
		
		String names = "";
		String urls = "";
		
		String fileurl = information.getFileurl();
		String filename = information.getFilename();
		String[] fileurls = fileurl.split("\\|\\|");
		String[] filenames = filename.split("\\|\\|");
		
		if (oldname!=null&&oldname.length>0) {
			for (int i = 0; i < oldname.length; i++) {
				if (filenames!=null&&!filenames.equals("")) {
					for (int j = 0; j < filenames.length; j++) {
						if (filenames[j].equals(oldname[i])) {
							names = names + filenames[j] +"||";
							urls = urls + fileurls[j] +"||";
						}		
					}
				}
				
			}
		}
		
		if (upload!=null) {
			for (int i = 0; i < upload.length; i++) {
				urls = urls + this.upload(upload[i], uploadfilename[i], packName) +"||";
				names = names + uploadfilename[i] + "||";
			}
		}
		 
		if (urls.endsWith("||")) { 
			urls = urls.substring(0, urls.length()-2);
			names = names.substring(0,names.length()-2);
		}
		
		 
		information.setFileurl(urls);
		information.setFilename(names); 
		baseHibernateDAO.updateEntity(information);
		
	}
	/**
	 * 批量删除
	 * @param id
	 * @param log
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void deleteInformation(String informationid) throws Exception {	
		String sql="delete from information where informationid in(";
		List list=new ArrayList();
		list.add(informationid);
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
	 * 客户 资料首页查看 英文
	 */
	@SuppressWarnings("unchecked")
	public List<Information> getIndexDataByPage(int begin,int numOfEachPage ) throws Exception{
		String sql = "from Information b where b.informationtip in(2,3) and ( b.filetip = 2 or b.filetip=3 )  order by b.informationtips ,b.fileuptime desc";
		List list = new ArrayList();
		List <Information> indexList1 = baseHibernateDAO.getEntityByPage(sql,list,begin,numOfEachPage);
		return indexList1;
	}
	
	/**
	 * 客户 资料首页查看 中文
	 */
	@SuppressWarnings("unchecked")
	public List<Information> getIndexDataByPageCH(int begin,int numOfEachPage ) throws Exception{
		String sql = "from Information b where b.informationtip in(2,3) and ( b.filetip = 1 or b.filetip=3 )  order by b.informationtips ,b.fileuptime desc";
		List list = new ArrayList();
		List <Information> indexList1 = baseHibernateDAO.getEntityByPage(sql,list,begin,numOfEachPage);
		return indexList1;
	}
     
	/**
	 * 单文件上传
	 * @param getUpload 文件对象流
	 * @param getUploadFileName 上传文件名
	 * @param packFile 保存文件的文件夹名称
	 * @return 保存文件的全路径
	 * @throws Exception
	 */
	public String upload(File getUpload,String filename,String packFile) throws Exception{
		//String rootPath = "d:/";
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");
		String rootPath = ServletActionContext.getServletContext().getRealPath("/");	//服务所在目录
		String datename = dateFormat.format(date);
		String timename = timeFormat.format(date);
		
		// 获取文件名
		String saveName =  packFile + "/" +datename+"/"+timename+"/" + filename;
		FileOutputStream fos = null;
		FileInputStream fis = null;
		 
			if(!new File(rootPath + packFile + "/"+datename+"/"+timename+"/").exists()){
				new File(rootPath + packFile + "/"+datename+"/"+timename+"/").mkdirs();	// 检查文件夹是否存在，如不存在创建
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
	 * 新增文件记录
	 */
	public void save(Information information,File[] upload,String[] filename,String packName) throws Exception{	
		
		Map session = ActionContext.getContext().getSession();
		String fileurl =  "";
		String name = "";
		for (int i = 0; i < upload.length; i++) {
			fileurl = fileurl + this.upload(upload[i], filename[i], packName) +"||";
			name = name + filename[i] + "||";
		}
		if (fileurl.endsWith("||")) { 
			fileurl = fileurl.substring(0, fileurl.length()-2);
			name = name.substring(0,name.length()-2);
		}
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	    information.setFileuptime(sdf.parse(sdf.format(new Date())));  //赋予系统当前时间
	    information.setFilename(name);
	    information.setInformationstate(Long.parseLong("1"));
	    information.setFileurl(fileurl);
	    information.setAccountname(((Account) session.get(Constants.ACCOUNT_SESSION)).getAccountName());
	    baseHibernateDAO.saveEntity(information);  //保存记录
	}
	
	public void save1(Information information)  throws Exception{
	     Map session = ActionContext.getContext().getSession();
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		 information.setFileuptime(sdf.parse(sdf.format(new Date())));  //赋予系统当前时间
		 information.setInformationstate(Long.parseLong("1"));
		 information.setAccountname(((Account) session
					.get(Constants.ACCOUNT_SESSION)).getAccountName());
		baseHibernateDAO.saveEntity(information);
	}
	/**
	 * @see 未登录
	 * 分页显示资料信息
	 * @see标题 发布时间 发布人 查看权限
	 */
	@SuppressWarnings("unchecked")
	public List<Information> getAllDataByPage(int begin,int numOfEachPage ) throws Exception{
		String sql = "from Information i where i.informationtip in(2,3) and (i.filetip = 2 or i.filetip=3)  order by i.informationtips ,i.fileuptime desc";
		List list = new ArrayList();
		List<Information>informationlist = baseHibernateDAO.getEntityByPage(sql,list,begin,numOfEachPage);
		return informationlist;
	}
	
	/**
	 * @see 未登录
	 * 分页显示资料信息
	 * @see标题 发布时间 发布人 查看权限
	 */
	@SuppressWarnings("unchecked")
	public List<Information> getAllDataByPageCH(int begin,int numOfEachPage ) throws Exception{
		String sql = "from Information i where i.informationtip in(2,3) and (i.filetip = 1 or i.filetip=3) order by i.informationtips ,i.fileuptime desc";
		List list = new ArrayList();
		List<Information>informationlist = baseHibernateDAO.getEntityByPage(sql,list,begin,numOfEachPage);
		return informationlist;
	}
	
	/**
	 * 记录数
	 */
	@SuppressWarnings("unchecked")
	public int getAllDataCount() throws Exception{
		List list = new ArrayList();
		String sql = "select count(*) from information i where i.informationtip in(2,3) and (i.filetip = 2 or i.filetip=3) ";
		int count = baseHibernateDAO.getEntitiesCountBySql(sql, list);
		return count;
	}
	
	/**
	 * 记录数
	 */
	@SuppressWarnings("unchecked")
	public int getAllDataCountCH() throws Exception{
		List list = new ArrayList();
		String sql = "select count(*) from information i where i.informationtip in(2,3) and (i.filetip = 1 or i.filetip=3) ";
		int count = baseHibernateDAO.getEntitiesCountBySql(sql, list);
		return count;
	}

	
	
	/**
	 * @see  登录客户（管理级别）英文
	 * 分页显示资料信息
	 * @see标题 发布时间 发布人 查看权限
	 */
	@SuppressWarnings("unchecked")
	public List<Information> getAllDataByPages(int begin,int numOfEachPage ) throws Exception{
		String sql = "from Information i   order by i.informationtips  ,i.fileuptime desc";
		List list = new ArrayList();
		List<Information>informationlist = baseHibernateDAO.getEntityByPage(sql,list,begin,numOfEachPage);
		return informationlist;
	}
	
	/**
	 * @see  登录客户（管理级别）中文
	 * 分页显示资料信息
	 * @see标题 发布时间 发布人 查看权限
	 */
	@SuppressWarnings("unchecked")
	public List<Information> getAllDataByPagesCH(int begin,int numOfEachPage ) throws Exception{
		String sql = "from Information i  where i.filetip = 1 or i.filetip=3 order by i.informationtips ,i.fileuptime desc";
		List list = new ArrayList();
		List<Information>informationlist = baseHibernateDAO.getEntityByPage(sql,list,begin,numOfEachPage);
		return informationlist;
	}
	/**
	 * 记录数
	 */
	@SuppressWarnings("unchecked")
	public int getAllDataCounts() throws Exception{
		List list = new ArrayList();
		String sql = "select count(*) from information i ";
		int count = baseHibernateDAO.getEntitiesCountBySql(sql, list);
		return count;
	}
	
	
	/**
	 * 记录数(中文)
	 */
	@SuppressWarnings("unchecked")
	public int getAllDataCountsCH() throws Exception{
		List list = new ArrayList();
		String sql = "select count(*) from information i ";
		int count = baseHibernateDAO.getEntitiesCountBySql(sql, list);
		return count;
	}
	/**
	 * @see  登录客户（非管理级别）informationtip=1 and informationtips=1
	 * 分页显示资料信息
	 * @see标题 发布时间 发布人 查看权限
	 */
	@SuppressWarnings("unchecked")
	public List<Information> getAllADataByPage(int begin,int numOfEachPage ) throws Exception{
		String sql = "from Information i where i.informationtip in(1,2) and  (i.filetip = 2 or i.filetip = 3) order by i.informationtips ,i.fileuptime desc";
		List list = new ArrayList();
		List<Information>informationlist = baseHibernateDAO.getEntityByPage(sql,list,begin,numOfEachPage);
		return informationlist;
	}
	
	/**
	 * @see  登录客户（非管理级别）informationtip=1 and informationtips=1
	 * 分页显示资料信息 中文
	 * @see标题 发布时间 发布人 查看权限
	 */
	@SuppressWarnings("unchecked")
	public List<Information> getAllADataByPageCH(int begin,int numOfEachPage ) throws Exception{
		String sql = "from Information i  where i.informationtip in(1,2)  order by i.informationtips ,i.fileuptime desc";
		List list = new ArrayList();
		List<Information>informationlist = baseHibernateDAO.getEntityByPage(sql,list,begin,numOfEachPage);
		return informationlist;
	}
	
	/**
	 * 记录数
	 */
	@SuppressWarnings("unchecked")
	public int getAllADataCount() throws Exception{
		List list = new ArrayList();
		String sql = "select count(*) from information i where i.informationtip in(1,2) ";
		int count = baseHibernateDAO.getEntitiesCountBySql(sql, list);
		return count;
	}
	
	/**
	 * 记录数 中文
	 */
	@SuppressWarnings("unchecked")
	public int getAllADataCountCH() throws Exception{
		List list = new ArrayList();
		String sql = "select count(*) from information i where i.informationtip in(1,2) ";
		int count = baseHibernateDAO.getEntitiesCountBySql(sql, list);
		return count;
	}
	
	/**
	 * @see  登录客户informationtip=1 and informationtips=2
	 * 分页显示资料信息
	 * @see标题 发布时间 发布人 查看权限
	 */
	@SuppressWarnings("unchecked")
	public List<Information> getAllCDataByPage(int begin,int numOfEachPage ) throws Exception{
		String sql = "from Information i where i.informationtip in(2,3) order by i.informationtips ,i.fileuptime desc";
		List list = new ArrayList();
		List<Information>informationlist = baseHibernateDAO.getEntityByPage(sql,list,begin,numOfEachPage);
		return informationlist;
	}
	/**
	 * 记录数
	 */
	@SuppressWarnings("unchecked")
	public int getAllCDataCount() throws Exception{
		List list = new ArrayList();
		String sql = "select count(*) from information i where i.informationtip in(2,3)  ";
		int count = baseHibernateDAO.getEntitiesCountBySql(sql, list);
		return count;
	}
	
	/**
	 * 根据ID查找Entity
	 */
	public Information getInformationById(Long  informationid)throws Exception{
		return (Information) baseHibernateDAO.getEntityById(Information.class, informationid);	 
	}

	/**
	 * 更新操作-----
	 * 删除(修改 方法  修改状态)
	 * @修改information表的state字段变为-1
	 * @往日志表插一条记录
	 */
	public void deleteInformationById(Long  informationid)throws Exception{
		baseHibernateDAO.deleteEntity(Information.class,informationid);
	}


}
