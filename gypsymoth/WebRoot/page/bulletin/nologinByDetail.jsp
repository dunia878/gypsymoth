<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统</title>
		<%@ include file="../include/head.jsp"%>
		<link href="style/help.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<div class="logo">
			<table width="340" border="0" align="right" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="22">
						&nbsp;

					</td>
					<td align="center">
						&nbsp;

					</td>
				</tr>
				<tr>
					<td>
						&nbsp;

					</td>
				<!-- 	<td height="104" align="right" valign="bottom">
						<img src="../../images/logo_bt1.jpg" width="228" height="32"
							border="0" usemap="#Map" />
					</td> -->
				</tr>
			</table>
			<map name="Map" id="Map">
				<area shape="rect" coords="19,8,102,30" href="#" target="中文" />
				<area shape="rect" coords="113,8,190,31" href="#" target="英文" />
			</map>
		</div>
		<div style="width: 1002px; margin: auto;">
			<table width="100%" border="0" cellpadding="0" cellspacing="7"
				bgcolor="#ffffff"
				style="background-image: url(images/img1.gif); background-repeat: repeat-x;">
				<tr>
					<td valign="top" bgcolor="#FFFFFF"
						style="border-bottom: 1px solid #96BBE9; border-top: 1px solid #96BBE9; border-left: 1px solid #96BBE9; border-right: 1px solid #96BBE9; background-color: #ffffff;">
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="title">
							<tr>
								<td>
									<img src="../../images/icon_pass.gif" width="22" height="17"
										style="margin-bottom: -3px;" />
									<span class="news">新闻动态News</span>
								</td>
							</tr>
						</table>
						<table width="100%" border="0" cellspacing="5" cellpadding="12">
							<tr>
								<td align="center" class="news"
									style="font-size: 17px; border-bottom: dotted 1px #8EC0E8;">
									<span class="news" style="font-size: 16px;"><strong><s:property
												value="bulletin.bulletinName" /> </strong> </span>
								</td>
							</tr>
							<tr>
								<td class="dot" style="font-size: 14px; line-height: 25px;">

										<FCK:editor id="bulletinContent" basePath="../FCKeditor/"
											height="300"  toolbarSet="Default">
                                                      ${bulletin.bulletinContent}   
                                                </FCK:editor>

									<br />
									<s:iterator value="#request.bulletinlist" status="st">
										<div align="center">
											<img src="${pageContext.request.contextPath}<s:property />" border="0" />
											<br />
										</div>
									</s:iterator>
									<div style="float: right;">
										作者：中检集团 日期：
										<s:date name="bulletin.bulletinTime" format="yyyy-MM-dd " />
									</div>

								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>
