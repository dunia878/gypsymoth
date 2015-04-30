<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<div class="dtree">

	<p><a href="javascript: d.openAll();">全部展开</a> | <a href="javascript: d.closeAll();">全部关闭</a></p>

	<script type="text/javascript">
		<!--

		d = new dTree('d');		
		d.add(0,-1,'机构列表');
		<s:if test="#request.orgLevelList.size>0">
    	<s:iterator value="#request.orgLevelList" status="stat">
    		d.add(<s:property value='id'/>,<s:property value='parentId'/>,"<s:property value='orgName'/>","${pageContext.request.contextPath}<s:property value='orgUrl'/>");
    	</s:iterator>
    	</s:if>
		
		document.write(d);

		//-->
	</script>

</div>
