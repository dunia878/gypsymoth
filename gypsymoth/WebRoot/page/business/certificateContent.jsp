<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:iterator value="#request.selectlist" id="mop">
	<option value="<s:property value="#mop.CERTNO"/>"><s:property value="#mop.CERTNO"/> </option>
</s:iterator>
 