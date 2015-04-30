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
<s:if test="#request.type==1">
<s:if test="#request.firstOrgList.size>0">
一级公司 <s:select list="#request.firstOrgList" name="parentId" 
		id="parentId" listKey="ID" listValue="ORG_NAME" headerKey="-1" headerValue="-请选择-" onchange="javascript:ajaxselect('getSecondOrgListChargesStat.action','parentId','secondOrgDiv');">
</s:select>
</s:if>
<s:elseif test="#request.firstOrgList.size==0">
一级公司 <s:select list="#request.firstOrgList" name="parentId" 
		id="parentId" listKey="ID" listValue="ORG_NAME" headerKey="-1" headerValue="-请选择-">
	</s:select>
</s:elseif>
</s:if>
<s:elseif test="#request.type==2">
<s:if test="#request.secondOrgList.size>0">
二级公司 <s:select list="#request.secondOrgList" name="orgId" 
		id="orgId" listKey="ID" listValue="ORG_NAME" headerKey="-1" headerValue="-请选择-" onchange="javascript:ajaxselect('getPortListChargesStat.action','orgId','portDiv');">
</s:select>
</s:if>
<s:elseif test="#request.secondOrgList.size==0">
二级公司 <s:select list="#request.secondOrgList" name="orgId" 
		id="orgId" listKey="ID" listValue="ORG_NAME" headerKey="-1" headerValue="-请选择-">
	</s:select>
</s:elseif>
</s:elseif>
<s:elseif test="#request.type==3">
<s:if test="#request.portList.size>0">
港口 <s:select list="#request.portList" name="portId" 
		id="portId" listKey="ID" listValue="PORT_NAME" headerKey="" headerValue="-全部-">
</s:select>
</s:if>
<s:elseif test="#request.portList.size==0">
港口 <s:select list="#request.portList" name="portId" 
		id="portId" listKey="ID" listValue="PORT_NAME" headerKey="-1" headerValue="-请选择-">
	</s:select>
</s:elseif>
</s:elseif>