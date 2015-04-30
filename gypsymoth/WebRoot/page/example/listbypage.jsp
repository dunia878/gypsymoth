<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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
  	<h1><font color="red"><center>分页查询示例</center></font></h1>
    <table align="center" width="80%" border="1">
    
    	<tr bgcolor="red">
    		<td>ID</td>
    		<td>姓名</td>
    		<td>年龄</td>
    	</tr>
    	<s:form id="datalist" name="datalist" action="getAllDataByPage"
									theme="simple">
    	<s:if test="#request.list.size>0">
    	<s:iterator value="#request.list" status="stat">
    	<tr>
    		<td><s:property value="id"/></td>
    		<td><s:property value="name"/></td>
    		<td><s:property value="age"/></td>
    	</tr>
    	</s:iterator>
    	</s:if>
    	</s:form>
    	<tr>
										<td height="25" align="right" class="right_tb1" colspan="3">
											<!--分页  -->
												<s:component template="pagev2.jsp" templateDir="page/pageutil"
													theme="template">
													<!--action name  -->
													<s:param name="formId" value="'datalist'" />
													<s:param name="action_name" value="actionName" />
													<!-- name space -->
													<s:param name="namespace" value="'/page/example'" />
													<!-- pagination method name -->
													<s:param name="pagination_method_name" value="actionName" />
													<!--当前页前后显示的数量  -->
													<s:param name="distanceNum" value="5" />
												</s:component>
										</td>
								</tr>
    </table>
  </body>
</html>
