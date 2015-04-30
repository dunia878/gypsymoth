<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>

<html>

<head>
	<title>权限树</title>
</head>
<body>

<div class="dtree">

	<p><a href="javascript: d.openAll();">全部展开</a> | <a href="javascript: d.closeAll();">全部关闭</a></p>

	<script type="text/javascript">
		<!--

		d = new dTree('d');

		d.add(0,-1,'权限');
		<s:if test="#request.rightList.size>0">
    	<s:iterator value="#request.rightList" status="stat">
    		d.add(<s:property value='rightId'/>,<s:property value='parentId'/>,"<s:property value='rightName'/>","<s:property value='resourceUrl'/>");
    	</s:iterator>
    	</s:if>
		
		document.write(d);

		//-->
	</script>

</div>

</body>

</html>