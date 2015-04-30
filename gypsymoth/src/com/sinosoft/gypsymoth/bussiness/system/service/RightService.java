package com.sinosoft.gypsymoth.bussiness.system.service;

import java.util.List;

import com.sinosoft.gypsymoth.pojo.Right;

public interface RightService {

	public abstract void insertRight(Right right) throws Exception;

	public abstract List<Right> getRightByParentId(int parentId)
			throws Exception;
	
	public abstract List<Right> getAllRight() throws Exception;

	public abstract List<Right> getRightById(int id) throws Exception;
	
	/**
	 * 根据用户ID获取用户所有权限
	 * @param accountId
	 * @return
	 * @throws Exception
	 */
	public abstract List<Right> getRightByAccountId(String accountId) throws Exception;

	public abstract void saveRight(Right right) throws Exception;

	public abstract void deleteRight(Right right) throws Exception;

}