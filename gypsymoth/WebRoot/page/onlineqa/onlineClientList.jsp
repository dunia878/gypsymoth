<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统</title>
		<%@ include file="../include/head.jsp"%>
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>/js/My97DatePicker/WdatePicker.js"></script>
	</head>
	<script language="javascript" type="text/javascript"> 
function onlineqac()
			{
				document.qalist.action = "onlineqaC.action";
				document.qalist.submit();
			} 
</script>
	<body>
		<!--内容开始-->
		<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
			<s:form action="getAllDataByPage" id="qalist" theme="simple"
				name="qalist">
				<tr>
					<td class="list_r">
						&nbsp;
					</td>
					<td valign="top" class="right">
						<div class="title">
							<s:text name="您的当前位置" />
							：
							<s:text name="首页" />
							&gt;&gt;
							<s:text name="在线答疑" />
							&gt;&gt;
							<strong><s:text name="客户反馈专区" />
							</strong>
						</div>
						<div>

							<table width="100%" class="tableheader">

								<tr>
									<td width="5%" height="26">
										<img src="../../images/bg_mcontentL.gif" width="32"
											height="27" />
									</td>
									<td valign="middle">
										<h3>
											<s:text name="问题列表" />
										</h3>
									</td>
									<c:forEach items="${menu_parent}" var="mp3" varStatus="status">
										<c:if test="${mp3.rightId == 714}">
											<td align="right" valign="middle">
												<input type="button" onclick="javascript:onlineqac();"
													class="button_s" value="<s:text name='提问'/>" />

											</td>
										</c:if>
									</c:forEach>
									<td width="1%" align="right">
										<img src="../../images/bg_mcontentR.gif" width="7" height="27" />
									</td>
								</tr>
							</table>
							<table width="100%" class="tableCont">
								<tr>
									<td>
										<div class="tableContent">
											<table width="100%" border="0" cellspacing="1"
												cellpadding="0">
												<tr>
													<th width="6%">
														<input name="chkall" type="checkbox" id="chkall"
															value="checkbox" />
													</th>
													<th align="center">
														<s:text name="编号" />
													</th>
													<th align="center">
														<s:text name="提问人" />
													</th>
													<th align="center">
														<s:text name="提问问题" />
													</th>
													<th align="center">
														<s:text name="提问时间" />
													</th>
													<th align="center">
														<s:text name="状态" />
													</th>
													<th align="center">
														<s:text name="回答人" />
													</th>

												</tr>
												<tr>

													<s:form id="qalist" name="qalist" action="getAllDataByPage"
														theme="simple">
														<s:if test="#request.qalist.size>0">
															<s:iterator value="#request.qalist" status="stat">
																<tr>
																	<td align="center">
																		<input type="checkbox" name="bulletinId"
																			value="<s:property value="bulletinId"/>" />
																	</td>

																	<td align="center">
																		<s:property value="id" />
																	</td>
																	<td align="center">
																		<s:property value="clientname" />
																	</td>
																	<td align="center">
																		<a
																			href="<s:url action="getOnlineqaById"> 
			                            <s:param name="id"><s:property value="id"/></s:param>
			                            </s:url>"><s:property
																				value="title" /> </a>
																	</td>
																	<td align="center">
																		<s:date name="time" format="yyyy-MM-dd HH:mm" />
																	</td>
																	<td align="center">
																		<s:if test="%{state == 1}"><font color="green"><s:text name="未回答"/></font></s:if>
																		<s:if test="%{state == 2}"><font color="red"><s:text name="已回答"/></font></s:if>
																	</td>
																	<td align="center">
																		<s:if test="%{state == 1}"><font color="#b3b3b3">&nbsp;</font>
																		</s:if>
																		<s:if test="%{state == 2}">
																			<s:property value="answer" />
																		</s:if>
																	</td>


																</tr>
															</s:iterator>
														</s:if>
													</s:form>
												</tr>
											</table>
									</td>
								</tr>
							</table>
							<table width="98%" height="35" border="0" cellpadding="0"
								cellspacing="0">
								<tr>
									<td height="25" align="right" class="right_tb1" colspan="3">
										<!--分页  -->
										<s:component template="pagev2.jsp" templateDir="page/pageutil"
											theme="template">
											<!--action name  -->
											<s:param name="formId" value="'qalist'" />
											<s:param name="action_name" value="actionName" />
											<!-- name space -->
											<s:param name="namespace" value="'/page/onlineqa'" />
											<!-- pagination method name -->
											<s:param name="pagination_method_name" value="actionName" />
											<!--当前页前后显示的数量  -->
											<s:param name="distanceNum" value="5" />
										</s:component>
									</td>
								</tr>
							</table>
							<!--编辑结束-->
						</div>
					</td>
				</tr>
			</s:form>
		</table>
	</body>

</html>
