<%@ page language="java" import="java.util.*" import="java.text.SimpleDateFormat" import="com.sinosoft.gypsymoth.pojo.Account" import="com.sinosoft.gypsymoth.utils.Constants;" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<c:if test="${empty account_id}">
	<%
		Account account  = (Account)request.getSession().getAttribute("account");
		if(account == null){
			String log_info =null;
			log_info = "你还没有登陆";
			request.getSession().setAttribute("tip",log_info);
		response.setContentType("text/html; charset=utf-8");
        response.setHeader("Pragma","No-cache"); 
        response.setHeader("Cache-Control","no-cache"); 
        response.setDateHeader("Expires", 0); 
		response.getWriter().write(
				"<script language=javascript>window.parent.location.href='"
						+ request.getScheme() + "://" + request.getServerName()
						+ ":" + request.getLocalPort()
						+ request.getContextPath()
						+ "/index.jsp'</script>");
	 %>
	
	<% } %>
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统</title>
		<style>
			body{ background-image:url(${pageContext.request.contextPath}/images/welcome.jpg); background-position:right top; background-repeat:no-repeat; background-color:#c2e1f9;}
		</style>
	</head>
	<body>
	 
		<%
			Account account = (Account)session.getAttribute(Constants.ACCOUNT_SESSION);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 %>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		  <tr>
		    <td width="17%" height="100">&nbsp;</td>
		    <td width="83%" valign="bottom" style="font-size:14px; font-weight:bold;">
		    	&nbsp;
		    </td>
		  </tr>
		  <tr>
		    <td width="17%" height="30">&nbsp;</td>
		    <td width="83%" valign="bottom" style="font-size:14px; font-weight:bold;">
		    </td>
		  </tr>
		  <tr>
		    <td width="17%" >&nbsp;</td>
		    <td width="83%" valign="bottom" style="font-size:14px; font-weight:bold;">
		    
		    	<img src="${pageContext.request.contextPath}/images/gif-0347.gif" />
		    	<s:if test="#session.WW_TRANS_I18N_LOCALE!=null && #session.WW_TRANS_I18N_LOCALE.toString()=='en_US'">
		    	&nbsp;<font color="#fd3000">${businessTempCount }</font> applications not yet submitted
		    	</s:if>
		    	<s:else>
		    	&nbsp;目前：&nbsp;共有　<font color="#fd3000">${businessTempCount }</font>　单业务申请未提交
		    	</s:else>
		    </td>
		  </tr>
		  <tr>
		    <td width="17%" >&nbsp;</td>
		    <td width=83%" valign="bottom" style="font-size:14px; font-weight:bold;">
		    	<img src="${pageContext.request.contextPath}/images/gif-0347.gif" />
		    	<s:if test="#session.WW_TRANS_I18N_LOCALE!=null && #session.WW_TRANS_I18N_LOCALE.toString()=='en_US'">
		    	&nbsp;<font color="#fd3000">${businessAcceptwaitCount }</font> waiting to be accepted
		    	</s:if>
		    	<s:else>
		    	&nbsp;目前：&nbsp;共有　<font color="#fd3000">${businessAcceptwaitCount }</font>　单业务待受理
		    	</s:else>
		    </td>
		  </tr>
		  <%if(account.getAccountType()==1){ %>
	  		<tr>
		    <td width="17%" >&nbsp;</td>
		    <td width="83%" valign="bottom" style="font-size:14px; font-weight:bold;">
		    	<img src="${pageContext.request.contextPath}/images/gif-0347.gif" />
		   		<s:if test="#session.WW_TRANS_I18N_LOCALE!=null && #session.WW_TRANS_I18N_LOCALE.toString()=='en_US'">
		    	</s:if><s:else>
		    		&nbsp;目前：&nbsp;共有　<font color="#fd3000">${businessAllotCount }</font>　单业务待分配
		     	</s:else>
		    </td>
		  </tr>
		  <tr>
		    <td width="17%" >&nbsp;</td>
		    <td width="83%" valign="bottom" style="font-size:14px; font-weight:bold;">
		    	<img src="${pageContext.request.contextPath}/images/gif-0347.gif" />
		    	<s:if test="#session.WW_TRANS_I18N_LOCALE!=null && #session.WW_TRANS_I18N_LOCALE.toString()=='en_US'">
		    	</s:if><s:else>
		    		&nbsp;目前：&nbsp;共有　<font color="#fd3000">${businessCertificateCount }</font>　单业务待检查/制作证书
		    	</s:else>
		    </td>
		  </tr>
		  <tr>
		    <td width="17%" >&nbsp;</td>
		    <td width="83%" valign="bottom" style="font-size:14px; font-weight:bold;">
		    		<img src="${pageContext.request.contextPath}/images/gif-0347.gif" />
		    	<s:if test="#session.WW_TRANS_I18N_LOCALE!=null && #session.WW_TRANS_I18N_LOCALE.toString()=='en_US'">
		    	</s:if><s:else>
		    		&nbsp;目前：&nbsp;共有　<font color="#fd3000">${businessSubmitCount }</font>　单业务待提交检查结果
		   		</s:else>
		    </td>
		  </tr>
	  <%}else if(account.getAccountType()==2){ %>
	    <tr>
		    <td width="17%" >&nbsp;</td>
		    <td width="83%" valign="bottom" style="font-size:14px; font-weight:bold;">
		    	<img src="${pageContext.request.contextPath}/images/gif-0347.gif" />
		    	<s:if test="#session.WW_TRANS_I18N_LOCALE!=null && #session.WW_TRANS_I18N_LOCALE.toString()=='en_US'">
		    	&nbsp;<font color="#fd3000">${businessCheckCount }</font> waiting to be inspected
		    	</s:if>
		    	<s:else>		    	
		    	&nbsp;目前：&nbsp;共有　<font color="#fd3000">${businessCheckCount }</font>　单业务待检查
		    	</s:else>
		    </td>
		  </tr>
		 <tr>
		    <td width="17%" >&nbsp;</td>
		    <td width="83%" valign="bottom" style="font-size:14px; font-weight:bold;">
		    	<img src="${pageContext.request.contextPath}/images/gif-0347.gif" />
		    	<s:if test="#session.WW_TRANS_I18N_LOCALE!=null && #session.WW_TRANS_I18N_LOCALE.toString()=='en_US'">
		    	&nbsp;<font color="#fd3000">${businessPayCount }</font> unpaid
		    	</s:if>
		    	<s:else>
		    	&nbsp;目前：&nbsp;共有　<font color="#fd3000">${businessPayCount }</font>　单业务待付费
		    	</s:else>
		    </td>
		  </tr>
	  
	  <%} %>
		</table>
	</body>
</html>
