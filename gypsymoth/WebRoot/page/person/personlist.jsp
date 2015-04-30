<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title><s:text name="personManage" />
		</title>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
		<link rel=stylesheet href="${pageContext.request.contextPath}/style/index.css" type="text/css" />
		<link rel=stylesheet href="${pageContext.request.contextPath}/style/right.css" type="text/css" />
		<link rel="StyleSheet" href="${pageContext.request.contextPath}/style/dtree.css" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree.js"></script>
		<script type="text/javascript">
		function searchperson(){
	  
	document.forms[0].method="post";
 	document.forms[0].action ="${pageContext.request.contextPath}/page/person/getEnable.action" ;
 	document.forms[0].submit();
		
	}
		function enableperson(){
	
	var a = document.getElementsByName("idArray"); 
  	var count = 0;
	for(var i=0;i<a.length;i++){
		if(a[i].checked){
			count++;
		}
	}
  	if(count==0){
 		alert('启用至少选择一项信息');
 		return;
 	}	
	document.forms[0].method="post";
 	document.forms[0].action ="${pageContext.request.contextPath}/page/person/enable.action" ;
 	document.forms[0].submit();	
	}
	</script>
	</head>
	<body>
		<!--内容开始-->

		<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
			
				<tr>
					<td class="list_r">
						&nbsp;
					</td>
					<td valign="top" class="right">
						<div class="title">
							<s:text name="local" />
							：
							<s:text name="home" />
							&gt;&gt;
							<s:text name="orgAndPerson" />
							&gt;&gt;
							<strong><s:text name="personManage" />
							</strong>
						</div>
						<div>						
							<table width="100%" align="center" cellpadding="0"
								cellspacing="5">
								<s:form action="getEnable" theme="simple" method="post" id="personList" name="personList">
								<tr>
									<td align="left" valign="top">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">											
											<tr>
												<td height="460" valign="top" class="tabedit2">
													<table width="100%" border="0" cellpadding="0"
														cellspacing="0" class="dot">
														<tr>
															<td width="50%" height="35">
															
																<s:text name="name" />
																<input name="name" type="text" class="input" size="8" />																	
																<input name="search" type="button" class="button_s"
																	id="search" onclick="javascript:searchperson()"
																	value="<s:text name="search"/>" />
																
															</td>
														</tr>
													</table>
													<table width="100%" class="tableheader">
														<tr>
															<td width="5%" height="26">
																<img src="${pageContext.request.contextPath}/images/bg_mcontentL.gif" width="32"
																	height="27" />
															</td>
															<td valign="middle">
																<h3>
																	<s:text name="personInformationList" />
																</h3>
															</td>
															<td align="right" valign="middle">
															<c:forEach items="${menu_parent}" var="mp3" varStatus="status">															
															<c:if test="${mp3.rightId==326}">
																<input type="button" class="button_s"
																	value="<s:text name="enable"/>"onclick="enableperson()"  />
															</c:if>
																													
															</c:forEach>
																</td>
															<td width="1%" align="right">
																<img src="${pageContext.request.contextPath}/images/bg_mcontentR.gif" width="7"
																	height="27" />
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
																				<th width="10%">
																					<s:text name="name" />
																				</th>
																				<th width="15%">
																					<s:text name="belongCompany" />
																				</th>
																				<th>
																					<s:text name="training" />
																					<br />
																					<s:text name="Examination" />
																					<br />
																				</th>
																				<th>
																					<s:text name="training" />
																					<br />
																					<s:text name="passTime" />
																					<br />
																				</th>
																				<th>
																					<s:text name="isCoordinator" />
																				</th>
																				<th>
																					<s:text name="isAuthorized" />
																				</th>
																				<th>
																					<s:text name="isInspector" />
																				</th>
																			</tr>
																			<s:if test="#request.list.size>0">
																				<s:iterator value="#request.list" status="stat">
																					<tr>
																						<td align="center">
																							<input type='checkbox' name='idArray' value="<s:property value="PID" />"/>
																						</td>
																						<td align="center">
																							<a
																								href="${pageContext.request.contextPath}/page/person/getPersonById.action?pId=<s:property value="PID"/>"><s:property
																									value="NAME" />
																							</a>
																						</td>
																						<td align="center">
																							<s:property value="ORG_NAME" />
																						</td>																						
																						<td align="center">
																							<s:if test="TRAINING!=0">
																								<img src="${pageContext.request.contextPath}/images/tick.gif"/>
																							</s:if>
																							<s:else>
																								<img src="${pageContext.request.contextPath}/images/error.gif"/>
																							</s:else>
																						</td>
																						<td align="center">
																							<s:property value="PASS_TIME" />
																						</td>
																						<td align="center">
																							<s:if test="IS_COORDINATOR!=0">
																								<img src="${pageContext.request.contextPath}/images/tick.gif"/>
																							</s:if>
																							<s:else>
																								<img src="${pageContext.request.contextPath}/images/error.gif"/>
																							</s:else>
																						</td>
																						<td align="center">
																							<s:if test="IS_AUTHORIZED!=0">
																								<img
																									src="${pageContext.request.contextPath}/images/tick.gif"/>
																							</s:if>
																							<s:else>
																								<img src="${pageContext.request.contextPath}/images/error.gif"/>
																							</s:else>
																						</td>
																						<td align="center">
																							<s:if test="IS_INSPECTOR!=0">
																								<img src="${pageContext.request.contextPath}/images/tick.gif"/>
																							</s:if>
																							<s:else>
																								<img src="${pageContext.request.contextPath}/images/error.gif"/>
																							</s:else>
																						</td>
																					</tr>
                                                                                  </s:iterator>
																			</s:if>
																		

																	</table>
																</div>
															</td>
														</tr>
														</s:form>
													</table>
													
												</td>
											</tr>

										</table>
										
									</td>
								</tr>
							</table>
						</div>
						<table width="98%" height="35" border="0" cellpadding="0" cellspacing="0">
					          <tr>
								<td height="25" align="right" class="right_tb1" colspan="3">
								<!--分页  -->
									<s:component template="pagev2.jsp" templateDir="page/pageutil" theme="template">
									<!--action name  -->
										<s:param name="formId" value="'personList'" />
										<s:param name="action_name" value="actionName" />
										<!-- name space -->
										<s:param name="namespace" value="'/page/person'" />
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
		
	</body>
</html>
