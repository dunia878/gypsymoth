package com.sinosoft.gypsymoth.bussiness.business.service;

import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.List;

import com.sinosoft.gypsymoth.pojo.Business;
import com.sinosoft.gypsymoth.pojo.BusinessDesCountry;
import com.sinosoft.gypsymoth.pojo.Config;
import com.sinosoft.gypsymoth.pojo.Example;
import com.sinosoft.gypsymoth.pojo.Operator;
import com.sinosoft.gypsymoth.pojo.Organization;
import com.sinosoft.gypsymoth.pojo.OrganizationLevel;
import com.sinosoft.gypsymoth.pojo.Person;
import com.sinosoft.gypsymoth.pojo.Right;
import com.sinosoft.gypsymoth.pojo.Role;

public interface BusinessService {
	
	/**
	 * 保存
	 * @param example
	 * @throws Exception
	 */
	public abstract void save(Business business) throws Exception;
	public abstract void saveDesCountry(BusinessDesCountry businessdes) throws Exception;
	public void saveBusiness(Business business,List<BusinessDesCountry> list) throws Exception;
	/**
	 * 查询
	 * @return
	 * @throws Exception
	 */
	
	public abstract List<Business> getAllBusiness(int begin,int numOfEachPage ) throws Exception;

	public abstract int getAllDataCount() throws Exception;
	public abstract void commit(Business business)  throws Exception;
	public abstract Business getBusinessById(Long id)  throws Exception;
	public void deleteBusinessById(Long  id)throws Exception;
	public abstract void apply(Business business) throws Exception;
	public abstract void payment(Business business) throws Exception;
	
	public abstract void updateBusiness(Business business) throws Exception;
	public abstract void updateBusinessdes(BusinessDesCountry businessdes) throws Exception;
	/**	证书编制->证书信息修改->修改授权签字人及业务单部分信息	*/
	public abstract void updateCertificateEdit(Business business,List<BusinessDesCountry> list,String personto) throws Exception;
	
	
	public abstract List registrySelect() throws Exception;
	/**	生成业务申请编号	*/
	public abstract String getnextAppno(String datastr,String orgstr,String businessid) throws Exception;
	/**	生成临时申请号	*/
	public abstract String nextTempNo(String datastr,String orgstr);
	
	public abstract void updateBusinessToState(Business business) throws Exception;
	public abstract void updateBusinessDetail(Business business,List<BusinessDesCountry> list) throws Exception;
	public abstract void savecommitBusiness(Business business,List<BusinessDesCountry> list) throws Exception;
	
	public abstract List<Business> getBusinessbyState(int begin, int numOfEachPage,Long state,HashMap map) throws Exception;
	public abstract int getBusinessbyStateCount(Long state,HashMap map) throws Exception ;
	
	/**	用户检索业务单,分页计数 */
	public abstract List getBusinessList(int begin, int numOfEachPage,HashMap map) throws Exception;
	/**	用户检索业务单,分页内容 */
	public abstract int getBusinessListCOUNT(HashMap map) throws Exception;
	/**	客户检索业务单,分页计数 */
	public abstract List getBusinessListClient(int begin, int numOfEachPage,HashMap map) throws Exception;
	/**	客户检索业务单,分页内容 */
	public abstract int getBusinessListClientCOUNT(HashMap map) throws Exception;
	
	
	public List<Business> getBusinessByDisposetype(int begin,int numOfEachPage,String operatortype,HashMap map) throws Exception;	
	public int getBusinessCountByDisposetype(String operatortype,HashMap map) throws Exception;
	public  List<BusinessDesCountry> getBusinessDesCountry(Long businessid) throws Exception;
	
	public abstract List searchBusinessList(HashMap map ,int begin ,int numOfEachPage);
	public abstract int searchBusinessListCount(HashMap map);
	
	
	//机构及人员信息
	public abstract List getOrganizationLevelByOrgID(String rog_id,String condition) throws Exception ;
	public abstract List getPersonFromOrg(String rog_id,String typecondition) throws Exception;
	
	public abstract List getOrgByPersonID(String person_id) throws Exception ;
	
	/**	通过港口城市得到所在机构4位编码,机构ID	*/
	public abstract List getOrg4Byportid(String portid) throws Exception;
	
	/**	根据船舶类型得到收费项目标准	*/
	public abstract List getShipPaymentList(String vesselname) throws Exception;
	
	public abstract void updateBusinessForSelect(Business business,List<BusinessDesCountry> list) throws Exception;
	/**	得到配置信息	*/
	public abstract List<Config> getConfigList() throws Exception;
}