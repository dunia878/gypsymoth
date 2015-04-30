/**
 *Create on 2008-10-24
 *Copyright (c) 2008 by sinosoft.
 *@author <a href="mailto:heguanhua@sinosoft.com.cn">heguanhua </a> 
 *@version 1.0
**/
package com.sinosoft.gypsymoth.commonInterceptor;

import java.util.Map;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sinosoft.gypsymoth.pojo.Account;
import com.sinosoft.gypsymoth.utils.Constants;
import com.sinosoft.gypsymoth.utils.Initialiser.Gypsymoth;

public class AuthorityInterceptor extends AbstractInterceptor{

	/**
	 * 登陆拦截
	 */
	private static final long serialVersionUID = -302324220149874052L;
	private final Logger _logger = Logger.getLogger(AuthorityInterceptor.class);
	
	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		
		ActionContext ctx = ai.getInvocationContext();
		Map session = ctx.getSession();
		Account account = (Account)session.get(Constants.ACCOUNT_SESSION);

		if(account != null){
			_logger.info("user loginning");
			return ai.invoke();
		}
		_logger.info("intercept user is not login");
		//ctx.put("tip", Gypsymoth.getPropertie(Constants.PropertyKeys.NOT_LOGIN)); 
		return "login";
	}
}
