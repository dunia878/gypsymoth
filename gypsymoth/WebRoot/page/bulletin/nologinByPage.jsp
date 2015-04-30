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
					<!--  <td height="104" align="right" valign="bottom">
						<img src="../../images/logo_bt1.jpg" width="228" height="32" border="0"
							usemap="#Map" />
					</td>-->
				</tr>
			</table>
			<map name="Map" id="Map">
				<area shape="rect" coords="19,8,102,30" href="#" target="中文" />
				<area shape="rect" coords="113,8,190,31" href="#" target="英文" />
			</map>
		</div>
		<div class="content">
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
								<td valign="top" class="dot">
									<table width="90%" align="center" cellspacing="5">
										<s:form id="bulletinlist" name="bulletinlist"
											action="getAllDataByPage" theme="simple">
											<s:if test="#request.bulletinlist.size>0">
												<s:iterator value="#request.bulletinlist" status="stat">
													<tr>
														<td height="25" align="left"
															style="border-bottom: dotted 1px #8EC0E8;">
															<img src="../../images/menu_icon.gif" width="11" height="9" />
															<a
																href="<s:url action="getBulletinById"> 
			                            <s:param name="bulletinId"><s:property value="bulletinId"/></s:param>
			                            </s:url>"
																target="_blank"><span><s:property
																		value="bulletinName" /> </span> </a>
														</td>
														<td height="25" align="center"
															style="border-bottom: dotted 1px #8EC0E8;">
															【
															<s:date name="bulletinTime" format="yyyy-MM-dd " />
															】
														</td>
													</tr>
												</s:iterator>
											</s:if>
										</s:form>
									</table>
									<table width="640" height="35" border="0" align="center"
										cellpadding="0" cellspacing="0">
										<tr>
											<td height="25" align="right" class="right_tb1" colspan="3">
												<!--分页  -->
												<s:component template="pagev2.jsp"
													templateDir="page/pageutil" theme="template">
													<!--action name  -->
													<s:param name="formId" value="'bulletinlist'" />
													<s:param name="action_name" value="actionName" />
													<!-- name space -->
													<s:param name="namespace" value="'/page/bulletin'" />
													<!-- pagination method name -->
													<s:param name="pagination_method_name" value="actionName" />
													<!--当前页前后显示的数量  -->
													<s:param name="distanceNum" value="5" />
												</s:component>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>
