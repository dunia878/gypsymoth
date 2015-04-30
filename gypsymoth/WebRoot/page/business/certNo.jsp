<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:if test="#request.list.size()==0">
<option value="-1" >- 暂无数据 -</option>  
</s:if><s:else>
<option value="-1" >- 请选择 -</option>
</s:else>
<s:iterator value="#request.list" id="mop">
	<option value="<s:property value="#mop.CERTNO"/>" ><s:property value="#mop.CERTNO"/></option>
</s:iterator>
