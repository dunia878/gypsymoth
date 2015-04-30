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
 	document.forms[0].action ="${pageContext.request.contextPath}/page/person/getAllPersonByPage.action" ;
 	document.forms[0].submit();
		
	}
	function addperson(){
	document.forms[0].method="post";
 	document.forms[0].action ="${pageContext.request.contextPath}/page/person/select.action" ;
 	document.forms[0].submit();
	}
	function enableperson(){
	
	document.forms[0].method="post";
 	document.forms[0].action ="${pageContext.request.contextPath}/page/person/getEnable.action" ;
 	document.forms[0].submit();	
	}
	function checkall(){
	var a = document.getElementsByName("idArray");
	for(var i=0;i<a.length;i++){
		a[i].checked = document.getElementById("chkall").checked;
	}
  }	
	function cancelperson(){
	if(confirm("您确定要批量停用人员吗？")){
	var a = document.getElementsByName("idArray"); 
  	var count = 0;
	for(var i=0;i<a.length;i++){
		if(a[i].checked){
			count++;
		}
	}
  	if(count==0){
 		alert('停用人员至少选择一项信息');
 		return;
 	}	
	document.forms[0].method="post";
 	document.forms[0].action ="${pageContext.request.contextPath}/page/person/cancel.action" ;
 	document.forms[0].submit();	
	}
	else{
		return false;
	}
	}
	function deleteperson(){
	if(confirm("您确定要批量删除人员吗？")){
	var a = document.getElementsByName("idArray"); 
  	var count = 0;
	for(var i=0;i<a.length;i++){
		if(a[i].checked){
			count++;
		}
	}
  	if(count==0){
 		alert('删除人员至少选择一项信息');
 		return;
 	}	
	document.forms[0].method="post";
 	document.forms[0].action ="${pageContext.request.contextPath}/page/person/delete.action" ;
 	document.forms[0].submit();	
	}
	else{
		return false;
		}
	}	
</script>
<script type="text/javascript">
			function selectOrgTree()
				{	
					var url = "getOrgList.action";
					window.open(url,"_blank","height=500px,width=200px,scrollbars=yes,top=200,left=500"); 
					
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
								<s:form action="getAllPersonByPage" theme="simple" method="post" id="personList" name="personList">
									<input id="selectOrgid" name="selectOrgid" type="hidden" value="<s:property value="selectOrgid"/>"/>
 			<input id="selectOrgname" name="selectOrgname" type="hidden" value="<s:property value="selectOrgname"/>"/>
								<tr>
									<td align="left" valign="top" class="tabedit1" style="width:0px;">
										<%@ include file="orgTree.jsp"%>
									</td>
									<td align="left" valign="top">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">											
											<tr>
												<td height="460" valign="top" class="tabedit2">
													<table width="100%" border="0" cellpadding="0"
														cellspacing="0" class="dot">
														<tr>
															<td width="50%" height="35">
															
																<s:text name="name" />
																<input name="name" type="text" class="input" size="8" value="<s:property value='name' />" />
																<s:text name="belongCompany" />
																<input id="selectOrgname2" name="selectOrgname2" type="text" size="8" disabled="disabled" value="<s:property value="selectOrgname"/>"/>
							                <input type="button" class="button" onclick="selectOrgTree()" value="<s:text name="choose"/>" />
							                				<s:if test="#request.isCoordinator != null">
							                					<input type="checkbox" name="isCoordinator" id="isCoordinator" checked="checked" /><s:text name="isCoordinator"/>
							                				</s:if>
							                				<s:else>
							                					<input type="checkbox" name="isCoordinator" id="isCoordinator" /><s:text name="isCoordinator"/>
							                				</s:else>
							                				<s:if test="#request.isAuthorized != null">	
							                					<input type="checkbox" name="isAuthorized" id="isAuthorized" checked="checked" /><s:text name="isAuthorized"/>
							                				</s:if>
							                				<s:else>
							                					<input type="checkbox" name="isAuthorized" id="isAuthorized" /><s:text name="isAuthorized"/>
							                				</s:else>
							                				<s:if test='#request.isInspector != null'>	
							                					<input type="checkbox" name="isInspector" id="isInspector" checked="checked" /><s:text name="isInspector"/>	
							                				</s:if>	
							                				<s:else>																										
							                					<input type="checkbox" name="isInspector" id="isInspector" /><s:text name="isInspector"/>																												
															</s:else>	
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
															<c:if test="${mp3.rightId == 321}">
																<input type="button"
																	onclick="addperson()"
																	class="button_s" value="<s:text name="add"/>" />
															</c:if>															
															<c:if test="${mp3.rightId==325}">
																<input type="button" class="button_s"
																	value="<s:text name="stopuse"/>"onclick="cancelperson()"  />
															</c:if>
															<c:if test="${mp3.rightId==326}">
																<input type="button" class="button_s"
																	value="<s:text name="enable"/>"onclick="enableperson()"  />
															</c:if>
															<c:if test="${mp3.rightId==323}">
																<input type="button" class="button_s"
																	value="<s:text name="delete"/>"onclick="deleteperson()"  />
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
																				<th>
																					<s:text name="name" />
																				</th>
																				<th>
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
																				<th width="8%">
																					<s:text name="isCoordinator" />
																				</th>
																				<th width="8%">
																					<s:text name="authorization" />
																					<br/>
																					<s:text name="signatory" />
																				</th>
																				<th width="8%">
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
																						<s:if test="PASS_TIME!=null">
																						<td align="center">
																							<s:property value="PASS_TIME" />
																						</td>
																						</s:if>
																						<s:else>
																						<td>&nbsp;</td>
																						</s:else>
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
																</s:form>
																</div>
															</td>
														</tr>													
													</table>
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
										
									</td>
								</tr>
							</table>
						</div>
						
					</td>
				</tr>

		</table>
		
	</body>
</html>
