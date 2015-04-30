package com.sinosoft.gypsymoth.bussiness.bulletin.service;

import java.io.File;
import java.util.List;

import com.sinosoft.gypsymoth.pojo.AccountRole;
import com.sinosoft.gypsymoth.pojo.Bulletin;
import com.sinosoft.gypsymoth.pojo.Client;
import com.sinosoft.gypsymoth.pojo.Linkus;


public interface BulletinService {
	
	//添加公告
	public abstract void save(Bulletin bulletin,File getUpload,String filename,String packName) throws Exception;
	public abstract void save(Bulletin bulletin) throws Exception;
	//客户显示公告列表
	public abstract List<Bulletin> getAllDataByPage(int begin,int numOfEachPage )throws Exception;
	//管理员登陆公告列表
	public abstract List<Bulletin> getAllDataByPages(Bulletin bulletin,int begin,int numOfEachPage )throws Exception;
	public abstract List<Bulletin> getAllDataByPagesCH(int begin,int numOfEachPage )throws Exception;
	//用户登陆公告列表
	public abstract List<Bulletin> getAllADataByPage(int begin,int numOfEachPage ) throws Exception;
	//登陆客户显示公告列表
	public abstract List<Bulletin> getAllCDataByPage(int begin,int numOfEachPage ) throws Exception;
	//删除公告
	@SuppressWarnings("unchecked")
	public abstract void deleteBulletinListById(List  bulletinIds)throws Exception;
	public abstract int getAllDataCountsCH() throws Exception;
	//客户公告列表
	public abstract List<Bulletin> getIndexDataByPage(int begin,int numOfEachPage) throws Exception;
	//获得列表总数
	public abstract int getAllDataCount() throws Exception;
	//获得客户列表总数
	public abstract int getAllDataCountOut() throws Exception;
	public abstract List<Bulletin> getAllADataByPageCH(int begin,int numOfEachPage ) throws Exception;
	//获得列表总数
	public abstract int getAllDataCounts() throws Exception;
	public abstract int getAllADataCountCH() throws Exception;
//	public abstract int getAlgetAllDataByPageslDataCountsCH() throws Exception;
	//获得用户列表总数
	public abstract int getAllADataCount() throws Exception;
	//获得客户列表总数
	public abstract int getAllCDataCount() throws Exception;
	//删除公告
	public abstract void deleteBulletinById(Long  bulletinId)throws Exception;
	//获得公告信息
	public abstract Bulletin getBulletinById(Long bulletinId)throws Exception;
	//修改公告信息
	public abstract void updateBulletin(Bulletin bulletin)throws Exception;
	public abstract List<Bulletin> getIndexDataByPageCH(int begin,int numOfEachPage ) throws Exception;
	//获得用户与权限列表
	public abstract List<AccountRole> getAccountRole(String accountId) throws Exception;
	public abstract int getAllDataCountOutCH() throws Exception;
	public abstract List<Bulletin> getAllDataByPageCH(int begin,int numOfEachPage ) throws Exception;
	//删除公告
	public abstract void deleteBulletin(String bulletinId) throws Exception ;
	public abstract void updateBulletins(Bulletin bulletin)throws Exception;
	public abstract List getLinkusByType(String linkustype) throws Exception;
	public abstract Linkus getLinkusById(Long  linkusid)throws Exception;
	public abstract void deleteLinkusById(Long  linkusid)throws Exception;
	public  abstract void savelinkus(Linkus linkus) throws Exception;
	public void updateBulletins(Bulletin bulletin,File getUpload,String filename,String packName)throws Exception;

}
