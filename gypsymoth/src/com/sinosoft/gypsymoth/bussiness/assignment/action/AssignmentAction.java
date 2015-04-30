package com.sinosoft.gypsymoth.bussiness.assignment.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.sinosoft.gypsymoth.bussiness.assignment.service.AssignmentService;
import com.sinosoft.gypsymoth.bussiness.example.service.ExampleService;
import com.sinosoft.gypsymoth.exception.AppException;
import com.sinosoft.gypsymoth.pojo.Example;
import com.sinosoft.gypsymoth.utils.Constants;
import com.sinosoft.gypsymoth.utils.Pagination;

public class AssignmentAction extends ActionSupport {
	
	private final Logger _logger = Logger.getLogger(AssignmentAction.class);
	
	private AssignmentService assignmentService;
	private String actionName;
	 
 
	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public AssignmentService getAssignmentService() {
		return assignmentService;
	}

	public void setAssignmentService(AssignmentService assignmentService) {
		this.assignmentService = assignmentService;
	}

 
	 

}
