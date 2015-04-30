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
  	<h1><font color="red"><center>添加示例</center></font></h1>
    <table align="center">
    <s:form action="save" theme="simple" id="save">
    	<tr>
    		<td><s:text name="姓名"/></td>
    		<td><input size="26" name="name" id="name"/></td>
    	</tr>
    	<tr>
    		<td><s:text name="年龄"/></td>
    		<td><input size="26" name="age" id="age"/></td>
    	</tr>
    	<tr>
							<td colspan="2">
								<input  type="submit"" class="button" value=" 提 交 " />
							</td>
						</tr>
    	</s:form>
    </table>
  </body>
</html>
