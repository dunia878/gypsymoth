<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<option value="-1">- <s:text name="请选择"/> -</option>
<s:iterator value="#request.cityList" id="mop">
	<option value="<s:property value="#mop.CITYID"/>" ><s:property value="%{getText(#mop.CITYNAME)}"/></option>
</s:iterator> 
