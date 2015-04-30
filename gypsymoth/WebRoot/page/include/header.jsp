<%@ page language="java" import="java.util.*" import="com.sinosoft.gypsymoth.pojo.Account" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<c:if test="${empty account_id}">
	<%
		Account account  = (Account)request.getSession().getAttribute("account");
		if(account == null){
			String log_info =null;
			//log_info = "你未登陆，或长时间未操作，请登陆";
			log_info = "";
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