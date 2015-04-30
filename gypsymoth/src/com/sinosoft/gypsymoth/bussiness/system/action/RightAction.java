package com.sinosoft.gypsymoth.bussiness.system.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.sinosoft.gypsymoth.pojo.Right;
import com.sinosoft.gypsymoth.utils.SessionManager;
import com.sinosoft.gypsymoth.bussiness.system.service.RightService;
import com.sinosoft.gypsymoth.exception.AppException;

public class RightAction extends ActionSupport {
	
	private final Logger _logger = Logger.getLogger(RightAction.class);
	private RightService rightService;
	private Right right;
	
	public RightService getRightService() {
		return rightService;
	}
	public void setRightService(RightService rightService) {
		this.rightService = rightService;
	}
	public Right getRight() {
		return right;
	}
	public void setRight(Right right) {
		this.right = right;
	}
	
	public String getAllRight() throws AppException {
		
		
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			List<Right> list = rightService.getAllRight();
			/*for(int i=0;i<list.size();i++){
				System.out.println("========="+((Right)list.get(i)).getRightName());
				
			}*/
			request.setAttribute("rightList", list);
			_logger.info("getAllRight success-------");
		} catch (Exception e) {
			throw new AppException("查询权限列表", e);
		}
		return SUCCESS;
	}

}
