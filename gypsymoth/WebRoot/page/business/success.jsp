<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统</title>
		<%@ include file="../include/head.jsp"%>
	</head>
	<body>
		<%@ include file="../include/page_header.jsp"%>
		<%@ include file="../include/menu.jsp"%>
		<table width="1002" border="0" align="center" class="tabbg"
			cellPadding="0" cellSpacing="0">
			<tr>
				<td width="187" height="470" style="display: inline" id="right_td">
					<iframe
						src="${pageContext.request.contextPath}/page/include/left.jsp"
						name="leftframe" frameBorder="0" scrolling="auto" marginwidth="0"
						marginheight="0" id="leftframe" width="100%" height="100%"
						border="0">
					</iframe>
				</td>
				<td width="10" height="470">
					<iframe
						src="${pageContext.request.contextPath}/page/include/middle.jsp"
						name="middle" frameBorder="0" scrolling="no" marginwidth="0"
						marginheight="0" id="middle" width="100%" height="100%" border="0">
					</iframe>
				</td>
				<!-- 主内容开始 -->
				<td height="470" bgcolor="#FFFFFF">
					<table border="0" cellspacing="0" cellpadding="0"
						class="contenttab">
						<tr>
							<td class="list_r">
								&nbsp;
							</td>
							<td valign="top" class="right">
								<div class="title"></div>
								<div>
									<div class="success">
										<div class="errtext">
											操作成功!
											<br />
											您的操作已经成功
											<!--,点击 [<a href="#"  onclick="history.back(-1)"><span style="color:#FF0000">返 回</span></a>] 上一页面 -->
										</div>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
				<!-- 主内容结束 -->
			</tr>
		</table>
	</body>
</html>
