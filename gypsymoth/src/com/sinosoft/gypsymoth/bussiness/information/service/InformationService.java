package com.sinosoft.gypsymoth.bussiness.information.service;

import java.io.File;
import java.util.List;

import com.sinosoft.gypsymoth.pojo.AccountRole;
import com.sinosoft.gypsymoth.pojo.Information;

public interface InformationService {
	
	//文件上传保存
	public void save(Information information,File[] upload,String[] filename,String packName)throws Exception;
	//文件保存
	public void save1(Information information)throws Exception;
	//获得用户权限
	public abstract List<AccountRole> getAccountRole(String accountId) throws Exception;
	//获得资料列表
	public abstract List<Information> getAllDataByPages(int begin,int numOfEachPage ) throws Exception;
	//获得客户资料列表
	public abstract List<Information> getAllDataByPage(int begin,int numOfEachPage ) throws Exception;
	//获得用户资料列表
	public abstract List<Information> getAllADataByPage(int begin,int numOfEachPage ) throws Exception;
	//客户登陆后资料列表
	public abstract List<Information> getAllCDataByPage(int begin,int numOfEachPage ) throws Exception;
	//未登录客户资料列表
	public abstract List<Information> getIndexDataByPage(int begin,int numOfEachPage) throws Exception;
	//获得所有资料总数
	public abstract int getAllDataCounts() throws Exception;
	public abstract int getAllDataCountCH() throws Exception;
	public abstract List<Information> getAllDataByPageCH(int begin,int numOfEachPage ) throws Exception;
	//获得用户所有资料总数
	public abstract int getAllDataCount() throws Exception;
	public abstract List<Information> getIndexDataByPageCH(int begin,int numOfEachPage ) throws Exception;
	//获得用户资料总数
	public abstract int getAllADataCount() throws Exception;
	public abstract void updateInformation(Information information)throws Exception;
	//获得客户资料总数
	public abstract List<Information> getAllADataByPageCH(int begin,int numOfEachPage ) throws Exception;
	public abstract int getAllCDataCount() throws Exception;
	public abstract int getAllADataCountCH() throws Exception;
	public abstract int getAllDataCountsCH() throws Exception;
	public abstract List<Information> getAllDataByPagesCH(int begin,int numOfEachPage ) throws Exception;
	//获得资料列表
	public abstract Information getInformationById(Long  informationid)throws Exception;
	//删除资料
	public abstract void deleteInformationById(Long  informationid)throws Exception;
	//批量删除资料
	public abstract void deleteInformation(String informationid) throws Exception;
	public abstract void updateInformations(Information information,File[] upload,String[] uploadfilename,String packName,String[] oldname)throws Exception;

}
