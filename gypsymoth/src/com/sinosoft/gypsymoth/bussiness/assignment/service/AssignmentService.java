package com.sinosoft.gypsymoth.bussiness.assignment.service;

import java.util.HashMap;
import java.util.List;

import com.sinosoft.gypsymoth.pojo.Assignment;
import com.sinosoft.gypsymoth.pojo.Business;
import com.sinosoft.gypsymoth.pojo.Example;

public interface AssignmentService {

	/**
	 * 保存
	 * @param example
	 * @throws Exception
	 */
	public abstract void save(Assignment assignment) throws Exception;

	/**
	 * 更新
	 */
	public abstract void updateAssignmentToStateandPersonTo(Assignment assignment) throws Exception;

	public abstract Assignment getAssignmentById(Long id) throws Exception;
	
	public void updateAssignment(List<Assignment> assignmentlist) throws Exception;
	
	public abstract int getBusinessByPersonIDCount(String personid,HashMap map) throws Exception; 
	public abstract List getBusinessByPersonID(int begin,int numOfEachPage,String personid,HashMap map) throws Exception;
	
	public abstract int getBusinessByPersonIDCountSYS(HashMap map) throws Exception;
	public abstract List getBusinessByPersonIDSYS(int begin,int numOfEachPage,HashMap map) throws Exception;
	
	public abstract List getAssignmentlistByBusinessId(Long businessid) throws Exception ;
	
	public abstract List getAssignmentWorkflow(Long businessid);
	
	
	/**		得到分配单当前状态指定的角色人名及ID	*/
	public abstract List getPersonOnAssignment(String businessid,String person_role) throws Exception;
	
	/**		根据业务单号得到授权签字人所在机构ID和ID4	*/
	public abstract Assignment getOrgIDsByBusinessid(String businessid) throws Exception;

}