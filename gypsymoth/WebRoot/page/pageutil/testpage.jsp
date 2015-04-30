<!--Create on 2008-5-6   Copyright (c) 2007 by sinosoft. @author: gaoweipeng  @version 1.0 -->

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>My JSP 'pagination.jsp' starting page</title>   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <body>
  
  <!-- 具体的显示逻辑 -->
  
<!--分页  -->
		<s:component template="page.jsp" templateDir="page"
			theme="template">
			<!--action name  -->
			<s:param name="action_name" value="'demoPage'" />
			<!-- name space -->
			<s:param name="namespace" value="'page'" />			
			<!-- pagination method name -->
			<s:param name="pagination_method_name" value="'execute'" />			
			<!--当前页前后显示的数量  -->
			<s:param name="distanceNum" value="3" />
		</s:component>
  </body>
</html>
