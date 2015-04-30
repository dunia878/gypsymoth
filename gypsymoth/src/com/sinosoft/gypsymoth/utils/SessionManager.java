package com.sinosoft.gypsymoth.utils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.sinosoft.gypsymoth.pojo.Account;
import com.sinosoft.gypsymoth.pojo.Right;

public class SessionManager {
	private static final Logger _logger = Logger.getLogger(SessionManager.class);
	
	
	/**
	 * 用户退出，关闭登录session
	 * @param request
	 */
	public static void closeLoginSession(Map session)
	{
		if(session==null)
		{
			 session = ActionContext.getContext().getSession();
		}
		_logger.info("log out");
		Account account = (Account)session.get(Constants.ACCOUNT_SESSION);
		List<Right> rightList = (List<Right>)session.get(Constants.RIGHT_SESSION);
		String rightId = String.valueOf(session.get(Constants.RIGHT_ID_SESSION));
		
		if(account != null)
		{
			session.remove(Constants.ACCOUNT_SESSION);
		}
		if(rightList!=null)
		{
			session.remove(Constants.RIGHT_SESSION);
		}
		if(rightId!=null)
		{
			session.remove(Constants.RIGHT_ID_SESSION);
		}
		if(session.get(Constants.ACCOUNT_PERSON)!=null)
		{
			session.remove(Constants.ACCOUNT_PERSON);
		}
		if(session.get(Constants.ACCOUNT_PERSON_ID)!=null)
		{
			session.remove(Constants.ACCOUNT_PERSON_ID);
		}
		if(session.get(Constants.ACCOUNT_PERSON_IS_CHILD)!=null)
		{
			session.remove(Constants.ACCOUNT_PERSON_IS_CHILD);
		}
		if(session.get(Constants.ACCOUNT_ISADMIN)!=null)
		{
			session.remove(Constants.ACCOUNT_ISADMIN);
		}
		if(session.get(Constants.ACCOUNT_PERSON_ORG)!=null)
		{
			session.remove(Constants.ACCOUNT_PERSON_ORG);
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		if(request.getSession().getAttribute("tip")!=null)
		{
			request.getSession().removeAttribute("tip");
		}
		
		if(session.get(Constants.ACCOUNT_ORG_ID4)!=null)
		{
			session.remove(Constants.ACCOUNT_ORG_ID4);
		}
		if(session.get(Constants.ACCOUNT_CLIENT)!=null)
		{
			session.remove(Constants.ACCOUNT_CLIENT);
		}
		if(session.get(Constants.ACCOUNT_PERSON)!=null)
		{
			session.remove(Constants.ACCOUNT_PERSON);
		} 
		if(session.get(Constants.ACCOUNT_PERSON_ORGNAME)!=null)
		{
			session.remove(Constants.ACCOUNT_PERSON_ORGNAME);
		} 
		//closeMenuSession(session);
	}
	
	/**
	 * 关闭页面菜单session
	 * @param session
	 */
	public static void closeMenuSession(Map session){

	}
	
	public static String sessionRightId(String right_id){
		/**
		 * 获取二级权限
		 */
		Map session = ActionContext.getContext().getSession();
		session.remove("right_id");
		List<Right> rightList = (List<Right>)session.get("menu_parent");
		if(rightList!=null && right_id!=null && !"".equals(right_id)){
			for(int i=0;i<rightList.size();i++){
				Right right = right = rightList.get(i);
				if(right_id.equals(right.getRightId())){
					session.put("right_id", right_id);
				}
			}
			if(session.get("right_id")==null){
				_logger.info("没有二级权限");
				return "noRight";
			}
		}
		else{
			_logger.info("没有一级权限");
			return "noRight";
		}
		return "success";
	}
}
