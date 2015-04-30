<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ include file="include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统</title>
		<%@ include file="include/head.jsp"%>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/menu.js"></script>
		<script type="text/javascript">
			function rightfranme_onload(url){
				//window.open( "${pageContext.request.contextPath}/page/test.jsp","rightfranme","");
				//window.parent.rightfranme.location.href="${pageContext.request.contextPath}/page/test.jsp";
				window.rightfranme.location.href="${pageContext.request.contextPath}"+url;
			}
			
			function divcontrol(){
			
			if(document.getElementById("top").style.display=='none'){
			
			document.getElementById("top").style.display="";
			document.getElementById("map1").style.display="";
			document.getElementById("map2").style.display="none";
			
			}
			else{
			document.getElementById("top").style.display="none";
			document.getElementById("map1").style.display="none";
			document.getElementById("map2").style.display="";
			}
			
			}
		</script>
	</head>
	<body>
		<!--页头开始-->
		<%@ include file="include/page_header.jsp"%>
		<!--页头结束-->
		<!--菜单开始-->
		<%@ include file="include/menu.jsp"%>
		<!--菜单结束-->
		<!--内容开始-->
		<table width="1002" border="0" align="center" class="tabbg"
			cellPadding="0" cellSpacing="0">
			<tr>
				<!-- 左侧菜单开始 -->
				<td width="187" height="570" bgcolor="#def0fa" id="right_td" valign="top">
					<iframe src="${pageContext.request.contextPath}/page/include/left.jsp" name="leftframe" frameBorder="0"
						scrolling="no" marginwidth="0" marginheight="0" id="leftframe"
						width="187" height="380" border="0" >
					</iframe>
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
					    <tr>
					      	<td><img src="${pageContext.request.contextPath}/images/left_bg1.gif" /></td>
					    </tr>
					</table>
				</td>
				<!-- 左侧菜单结束 -->
				<!-- 分隔栏开始 -->
				<td width="10" height="570" valign="top">
					<iframe src="${pageContext.request.contextPath}/page/include/middle.jsp" name="middle" frameBorder="0"
						scrolling="no" marginwidth="0" marginheight="0" id="middle"
						width="10" height="570" border="0">
					</iframe>
				</td>
				<!-- 分隔栏结束 -->
				<!-- 主内容开始 -->
				<td width="805" height="570" bgcolor="#FFFFFF" valign="top">
					<iframe src="${pageContext.request.contextPath}/page/business/businessRemind.action" name="rightfranme" frameBorder="0"
						 marginwidth="0" marginheight="0" id="rightfranme"
						width="805" height="570" border="0">
					</iframe>
				</td>
				<!-- 主内容结束 -->
			</tr>
		</table>
		<!--内容结束-->
	</body>
</html>
