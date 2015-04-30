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
    
    <title>My JSP 'update.jsp' starting page</title>
    
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
     <s:form action="student_update" method="post">
    	<s:textfield name="student.stuName" label="name"></s:textfield>
    	<s:textfield name="student.stuBirthday" label="birthday"></s:textfield>
    	<s:radio list="#{0:'男',1:'女'}" name="student.stuSex" label="sex"></s:radio>
    	<s:select list="claList" listKey="classId" listValue="className" name="student.classes.classId" label="class"></s:select>
    	<s:hidden name="student.stuState"></s:hidden>
    	<s:hidden name="student.stuId"></s:hidden>
    	<s:submit></s:submit>
    </s:form>
  </body>
</html>
