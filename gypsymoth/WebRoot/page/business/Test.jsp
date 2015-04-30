<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统</title>
		<%@ include file="../include/head.jsp"%>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/menu.js"></script>
	</head>
	
	
	<body>
		<script language="JavaScript" type="text/javascript">
function testDelete(){
flag = false;
var de = document.getElementsByName("delid");
for(i = 0; i < de.length; i++){
if(de[i].checked == true){
flag = true;
break;
} 
}
if(flag == false){
alert("至少选择一个待删除记录");
return false;
}
if(confirm("确定删除?")) {
testForm.action = "../test/delete.action"; 
testForm.submit();
}


}
</script>

	<s:form theme="simple" method="post" name="testForm">
	<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="tab">
			<tr>
			<td>
			<input name="testDel " type="button" value="删除" onClick="testDelete()">
			<s:iterator value="testList" status="st">
				<tr>
					<td>
						<s:hidden name="testId" />
						<input name="delid" type="checkbox" value=""
							id="">
					</td>
					<td>
						<s:property value="testName"></s:property>
					</td>

				</tr>
			</s:iterator>
			</td></tr>
		</table></s:form>
	</body>
</html>
