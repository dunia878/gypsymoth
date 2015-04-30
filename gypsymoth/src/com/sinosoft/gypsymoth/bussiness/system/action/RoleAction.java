package com.sinosoft.gypsymoth.bussiness.system.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sinosoft.gypsymoth.bussiness.system.service.RoleService;
import com.sinosoft.gypsymoth.exception.AppException;
import com.sinosoft.gypsymoth.pojo.Account;
import com.sinosoft.gypsymoth.pojo.Right;
import com.sinosoft.gypsymoth.pojo.Role;
import com.sinosoft.gypsymoth.utils.Constants;
import com.sinosoft.gypsymoth.utils.Pagination;

public class RoleAction extends ActionSupport {
	
	private final Logger _logger = Logger.getLogger(RoleAction.class);
	private RoleService roleService;
	private String actionName;
	private Role role;
	private String tempRight;
	private String des;
	private String roleId;
	private String roleName;
	private Integer roleStatus;
	
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(Integer roleStatus) {
		this.roleStatus = roleStatus;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getTempRight() {
		return tempRight;
	}

	public void setTempRight(String tempRight) {
		this.tempRight = tempRight;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	/**
	 * 分页查询角色
	 * @return
	 * @throws Exception 
	 */
	public String getRoleByPage() throws Exception{
		
		HttpServletRequest request = ServletActionContext.getRequest();
		//分页配置
		Pagination p = new Pagination(0,0,0);
		String currPage=(String)request.getParameter("goPage");
		if(currPage==null){  
			currPage="1";//” currPage”是当前页数
		}
		_logger.info("currPage:"+currPage);
		Integer currPage1 = Integer.parseInt(currPage);
		try {
			Role tempRole = new Role();
			tempRole.setRoleName(roleName);
			tempRole.setRoleStatus(roleStatus);
			Integer totleCount = roleService.getAllRoleCount(tempRole);
			p.getPagination(request,totleCount, currPage1,null,null,null);
			int numOfEachPage =	Constants.NUMOFEACHPAGE;
			int begin = p.getRownum_begin(numOfEachPage, currPage1);
			List<Role> list = roleService.getRoleByPage(tempRole,begin, numOfEachPage);
			request.setAttribute("RoleList", list);
			_logger.info("get all role by page success-------");
		} catch (Exception e) {
			throw new AppException("分页查询角色", e);
		}
		this.actionName = "getRoleByPage";
		return SUCCESS;
	}
	
	/**
	 * 添加角色
	 * @return
	 * @throws AppException
	 */
	public String saveRole() throws AppException{
		HttpServletRequest request = ServletActionContext.getRequest();
		Map session = ActionContext.getContext().getSession();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date createDate = new Date();
		try {
			createDate = sdf.parse(sdf.format(createDate));
			role.setCreateBy(((Account)session.get(Constants.ACCOUNT_SESSION)).getAccountName());
			role.setCreateDate(createDate);
			roleService.saveRole(role, tempRight);
		} catch (Exception e) {
			throw new AppException("添加角色", e);
		}
		return SUCCESS;
	}
	
	/**
	 * 根据ID获取角色
	 * @return
	 * @throws AppException
	 */
	public String getRoleById() throws AppException{
		HttpServletRequest request = ServletActionContext.getRequest();
		List<Right> rightList = new ArrayList();
		try {
			role = roleService.getRoleById(roleId);
			rightList = roleService.getRightByRoleId(roleId);
		} catch (Exception e) {
			throw new AppException("通过ID获取角色", e);
		}
		request.setAttribute("righthas", rightList);
		return SUCCESS;
	}
	
	/**
	 * 更新角色
	 * @return
	 * @throws AppException
	 */
	public String updateRole() throws AppException{
		Map session = ActionContext.getContext().getSession();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date modifyDate = new Date();
		try {
			modifyDate = sdf.parse(sdf.format(modifyDate));
			role.setModifyBy(((Account)session.get(Constants.ACCOUNT_SESSION)).getAccountName());
			role.setModifyTime(modifyDate);
			roleService.updateRole(role,tempRight);
		} catch (Exception e) {
			throw new AppException("更新角色", e);
		}
		return SUCCESS;
	}
	
	/**
	 * 删除角色
	 * @return
	 * @throws AppException
	 */
	public String deleteRoleById() throws AppException{
		try {
			roleService.deleteRoleById(roleId);
		} catch (Exception e) {
			throw new AppException("删除角色", e);
		}
		return SUCCESS;
	}

}
