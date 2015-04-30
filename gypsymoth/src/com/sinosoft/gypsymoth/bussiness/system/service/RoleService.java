package com.sinosoft.gypsymoth.bussiness.system.service;

import java.util.List;

import com.sinosoft.gypsymoth.pojo.Right;
import com.sinosoft.gypsymoth.pojo.Role;
import com.sinosoft.gypsymoth.pojo.RoleRight;

public interface RoleService {

	public abstract List<Role> getRoleByPage(Role role,int begin, int numOfEachPage)
			throws Exception;

	public abstract int getAllRoleCount(Role role) throws Exception;

	public abstract void saveRole(Role role, String rightIds) throws Exception;

	public abstract void saveRoleRight(Role role,String rightIds) throws Exception;
	
	public abstract Role getRoleById(String roleId) throws Exception;
	
	public abstract void updateRole(Role role,String rightIds) throws Exception;
	
	public abstract void deleteRoleById(String roleId) throws Exception;
	
	public abstract void deleteRoleRight(String roleId) throws Exception;
	
	public abstract List<Right> getRightByRoleId(String roleId) throws Exception;

}