package com.sinosoft.gypsymoth.bussiness.business.service;

import java.util.HashMap;
import java.util.List;

import com.sinosoft.gypsymoth.pojo.Assignment;
import com.sinosoft.gypsymoth.pojo.Business;
import com.sinosoft.gypsymoth.pojo.Certificate;
import com.sinosoft.gypsymoth.pojo.OrgStamp;

public interface CertificateService {

	public abstract void save(Certificate certificate) throws Exception;
	
	public abstract void saveCertList(List<Certificate> certlist) throws Exception;
	
	public abstract Certificate getCertificateById(Long id) throws Exception;
	public abstract Certificate getCertificateByCertNo(String certno) throws Exception;
	public abstract void update(Certificate certificate) throws Exception;
	public abstract void updateCertificateList(List<Certificate> certlist) throws Exception;
	/**	指定证书编号的证书是否存在	
	 * @param min	证书最小编号	
	 * @param max	证书最大编号	
	 * @param certno	证书标识号	*/
	public abstract List getCertisExistByCertNum(int min,int max,String certno) throws Exception; 
	/**	得到证书标号的最大编号	*/
	public abstract int getCertMaxByCertNo(String certno) throws Exception;
	
	/**	得到机构可以查看的空白证书内容数量	*/
	public abstract int getEmptyCertByOrgIDCount(HashMap map) throws Exception;
	/**	得到机构可以查看的空白证书内容	*/
	public abstract List getEmptyCertByOrgID(HashMap map,int begin,int numOfEachPage) throws Exception;

	/**	查看港口信息	*/
	public abstract String getPortInfo(String portid) throws Exception;
	
	/**	根据机构ID得到可用的证书编号区间	*/
	public abstract List getCanUseCertlist(String orgid) throws Exception;
	public abstract List getCanUseCertNamelist(String orgid) throws Exception;
	public abstract List getCanUseCertNoByName(String certname,String org_id) throws Exception;
	
	/**	更新证书流水号	*/
	public abstract void updateCertNo(Business business) throws Exception;
	
	/**	通过流水号得到证书ID	*/
	public abstract List getCertByCertno(String certno) throws Exception;
	
	/**	印章	*/
	public abstract int getStampListCount(HashMap map)  throws Exception;
	public abstract List getStampList(HashMap map,int begin,int numOfEachPage)  throws Exception;
	
	/**	印章是否存在	*/
	public abstract List getStampByStampID(String stampid)  throws Exception;
	public abstract List getStamps(String orgid) throws Exception;
	/**	当前机构持有证书列表	*/
	public abstract List getCertlist(String orgid) throws Exception;
	public abstract void saveStamp(OrgStamp orgStamp)  throws Exception; 
	public abstract void deleteStamp(OrgStamp orgStamp)  throws Exception;
	public abstract void deleteStampByStampId(String stampid)  throws Exception;
	public abstract void updateStamp(OrgStamp orgStamp)  throws Exception;
	
}