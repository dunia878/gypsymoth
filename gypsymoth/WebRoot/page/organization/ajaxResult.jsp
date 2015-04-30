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
<s:select list="#request.cityList" name="cityId" 
		id="cityId" listKey="CITYID" listValue="CITYNAME" headerKey="" headerValue="-请 选 择-">
</s:select>
</s:if>
<s:elseif test="#request.cityList.size==0">
	<s:select list="#request.cityList" name="cityId" 
		id="cityId" listKey="CITYID" listValue="CITYNAME" headerKey="" headerValue="-请 选 择-">
	</s:select>
</s:elseif>
