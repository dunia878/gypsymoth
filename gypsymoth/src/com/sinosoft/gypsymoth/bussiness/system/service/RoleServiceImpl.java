package com.sinosoft.gypsymoth.bussiness.system.service;

import java.util.ArrayList;
import java.util.List;

import com.sinosoft.gypsymoth.dao.BaseHibernateDAO;
import com.sinosoft.gypsymoth.pojo.Right;
import com.sinosoft.gypsymoth.pojo.Role;
import com.sinosoft.gypsymoth.pojo.RoleRight;

public class RoleServiceImpl implements RoleService {
	
	private BaseHibernateDAO baseHibernateDAO;

	public BaseHibernateDAO getBaseHibernateDAO() {
		return baseHibernateDAO;
	}

	public void setBaseHibernateDAO(BaseHibernateDAO baseHibernateDAO) {
		this.baseHibernateDAO = baseHibernateDAO;
	}
	
	/**
	 * 分页获取角色列表
	 */
	public List<Role> getRoleByPage(Role role,int begin,int numOfEachPage) throws Exception{
		String sql = " from Role e where ";
		List list = new ArrayList();
			if(role.getRoleName()!=null && !"".equals(role.getRoleName())){
				sql+=" e.roleName like ? and ";
				list.add("%"+role.getRoleName()+"%");
			}
			if(role.getRoleStatus()!=null && !"".equals(role.getRoleStatus().toString())){
				sql+=" e.roleStatus=? and ";
				list.add(role.getRoleStatus());
			}
		sql +=" 1=1 order by e.roleId asc ";
		List<Role>returnList = baseHibernateDAO.getEntityByPage(sql,list,begin,numOfEachPage);
		return returnList;
	}
	
	/**
	 * 
	 */
	public int getAllRoleCount(Role role) throws Exception{
		List list = new ArrayList();
		String sql = " select count(*) from role e where ";
			if(role.getRoleName()!=null && !"".equals(role.getRoleName())){
				sql+=" e.role_name like ? and ";
				list.add("%"+role.getRoleName()+"%");
			}
			if(role.getRoleStatus()!=null && !"".equals(role.getRoleStatus().toString())){
				sql+="e.role_status=? and ";
				list.add(role.getRoleStatus());
			}
			sql +=" 1=1 ";
		int count = baseHibernateDAO.getEntitiesCountBySql(sql, list);
		return count;
	}
	
	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.bussiness.system.service.RoleService#saveRole(com.sinosoft.gypsymoth.pojo.Role, java.lang.String)
	 */
	public void saveRole(Role role,String rightIds) throws Exception{
		baseHibernateDAO.saveEntity(role);
		saveRoleRight(role,rightIds);
	}
	
	/* (non-Javadoc)
	 * @see com.sinosoft.gypsymoth.bussiness.system.service.RoleService#saveRoleRight(com.sinosoft.gypsymoth.pojo.RoleRight)
	 */
	public void saveRoleRight(Role role,String rightIds) throws Exception{
		String[] rightId = rightIds.split(",");
		for(int i=0;i<rightId.length;i++){
			RoleRight roleRight = new RoleRight();
			roleRight.setRightId(rightId[i]);
			roleRight.setRoleId(role.getRoleId());
			baseHibernateDAO.saveEntity(roleRight);
		}
		
	}
	
	public Role getRoleById(String roleId) throws Exception{
		Role role = (Role)baseHibernateDAO.getEntityById(Role.class, roleId);
		return role;
	}
	
	public void updateRole(Role role,String rightIds) throws Exception{
		baseHibernateDAO.updateEntity(role);
		deleteRoleRight(role.getRoleId());
		saveRoleRight(role,rightIds);
		
	}
	
	public void deleteRoleById(String roleId) throws Exception{
		baseHibernateDAO.deleteEntity(Role.class, roleId);
		deleteRoleRight(roleId);
	}
	
	public void deleteRoleRight(String roleId) throws Exception{
		String sql = "delete from role_right rr where rr.role_id in(";
		List list = new ArrayList();
		list.add(roleId);
		baseHibernateDAO.deleteBatchBySql(sql, list);
	}
	
	public List<Right> getRightByRoleId(String roleId) throws Exception{
		String hql = " select r from Right r,RoleRight rr where rr.roleId=? and rr.rightId=r.rightId";
		List list = new ArrayList();
		list.add(roleId);
		List<Right>returnList = baseHibernateDAO.getEntity(hql, list);
		return returnList;
	}

}
