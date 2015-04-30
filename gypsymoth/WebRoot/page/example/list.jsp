<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>示例</title>

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
  	<h1><font color="red"><center>示例</center></font></h1>
    <table align="center">
    	<tr>
    		<td>ID</td>
    		<td><s:text name="姓名"/></td>
    		<td><s:text name="年龄"/></td>
    		
    	</tr>
    	<s:if test="#request.list.size>0">
    	<s:iterator value="#request.list" status="stat">
    	<tr>
    		<td><s:property value="id"/></td>
    		<td><s:property value="businessname"/></td>
    		<td><s:property value="linkmanname"/></td>
    	</tr>
    	</s:iterator>
    	</s:if>
    </table>
  </body>
</html>
