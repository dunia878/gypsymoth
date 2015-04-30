<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	request.setAttribute("decorator", "none");
	response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>
<head>
</head>
<s:if test="#request.cityList.size>0">
<s:select list="#request.cityList" name="client.cityid" 
		id="cityId" listKey="CITYID" listValue="%{getText(CITYNAME)}" headerKey="" headerValue="-%{getText('请选择')}-">
</s:select>　<s:text name="City"/>
</s:if>
<s:elseif test="#request.cityList.size==0">
	<s:select list="#request.cityList" name="client.cityid" 
		id="cityId" listKey="CITYID" listValue="%{getText(CITYNAME)}" headerKey="" headerValue="-%{getText('请选择')}-">
	</s:select>　<s:text name="City"/>
</s:elseif>

<s:if test="#request.namelist.size>0"> 
<s:text name="account_error6"/> 
</s:if>
<s:else></s:else>
<s:if test="#request.namelist!=null && #request.namelist.size==0"> 
<s:text name="account_error7"/>
</s:if>

<s:if test="#request.emaillist.size>0">
该邮箱已被使用
</s:if>
<s:if test="#request.emaillist!=null && #request.emaillist.size==0"> 
该邮箱可以使用
</s:if>

<s:if test="#request.rolenamelist.size>0"> 
该角色名已被使用 
</s:if>
<s:if test="#request.rolenamelist!=null && #request.rolenamelist.size==0"> 
该角色名可以使用
</s:if>
