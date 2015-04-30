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
	
	function addorganization(){
	document.forms[0].method="post";
 	document.forms[0].action ="${pageContext.request.contextPath}/page/organization/select.action";
 	document.forms[0].submit();
	}
	function checkall(){
	var a = document.getElementsByName("idArray");
	for(var i=0;i<a.length;i++){
		a[i].checked = document.getElementById("chkall").checked;
	}
  }	
	function enableorganization(){
	document.forms[0].method="post";
 	document.forms[0].action ="${pageContext.request.contextPath}/page/organization/getEnable.action" ;
 	document.forms[0].submit();	
	}
	function cancelorganization(){
	if(confirm("您确定要批量停用机构吗？")){
	var a = document.getElementsByName("idArray"); 
  	var count = 0;
	for(var i=0;i<a.length;i++){
		if(a[i].checked){
			count++;
		}
	}
  	if(count==0){
 		alert('停用机构至少选择一项信息');
 		return;
 	}	
	document.forms[0].method="post";
 	document.forms[0].action ="${pageContext.request.contextPath}/page/organization/cancel.action" ;
 	document.forms[0].submit();	
	}
}
	function addorganization(){
	document.forms[0].method="post";
 	document.forms[0].action ="${pageContext.request.contextPath}/page/organization/select.action" ;
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
							<strong><s:text name="OrganizationManage" />
							</strong>
						</div>
						<div>
							<table width="100%" align="center" cellpadding="0"
								cellspacing="5">
								<s:form action="getAllOrganizationByPage" theme="simple" method="post" id="organization" name="organization">
								<tr>
									<td align="left" valign="top" class="tabedit1" style="width:0px;">
										<%@ include file="orgTree.jsp"%>
									</td>
									<td align="left" valign="top">
										<table width="100%" class="tableheader">
										
														<tr>
															<td width="5%" height="26">
																<img src="${pageContext.request.contextPath}/images/bg_mcontentL.gif" width="32"
																	height="27" />
															</td>
															<td valign="middle">
																<h3>
																	<s:text name="organizationlist" />
																</h3>
															</td>
															<td align="right" valign="middle">
															
															<c:forEach items="${menu_parent}" var="mp3" varStatus="status">
															<c:if test="${mp3.rightId == 311}">
																<input type="button"
																	onclick="addorganization()"
																	class="button_s" value="<s:text name="add"/>" />
															</c:if>
															<c:if test="${mp3.rightId==314}">
																<input type="button" class="button_s"
																	value="<s:text name="stopuse"/>"onclick="cancelorganization()"  />
															</c:if>
															<c:if test="${mp3.rightId==315}">
																<input type="button" class="button_s"
																	value="<s:text name="enable"/>"onclick="enableorganization()"  />
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
															
																<div class="tableContent" style=" overflow:hidden;padding-bottom:15px; width:632px;overflow-y: hidden;overflow:auto;">
																	<table width="100%" border="0" cellspacing="1"
																		cellpadding="0" align="center" style="width:1200px;" >
																			<tr>
																				<th width="3%">
																					<input name="chkall" type="checkbox" id="chkall"
																						value="checkbox" />
																				</th>
																				<th>
																					<s:text name="companySName" />
																				</th>
																				<th>
																					<s:text name="companyFName" />
																				</th>
																				<th>
																					<s:text name="companyAddress" />
																				</th>
																				<th>
																					<s:text name="contact" />
																																								
																				</th>
																				<th>
																					<s:text name="contacttel" />
																					
																				</th>
																				<th>
																					<s:text name="contactemail" />
																				</th>
																				<th>
																					<s:text name="contactfax" />
																				</th>
																			</tr>
																			<s:if test="#request.list.size>0">
																				<s:iterator value="#request.list" status="stat">
																					<tr>
																						<td align="center">
																							<input type='checkbox' name='idArray' value="<s:property value="ID" />"/>
																						</td>
																						<td align="center">																							
																								<a href="${pageContext.request.contextPath}/page/organization/getOrganization.action?Id=<s:property value="ID" />"><s:property	value="ORG_SNAME" /></a>																							
																						</td>											   
																						<s:if test="ORG_FNAME!=null">
																						<td align="center">
																							<s:property value="ORG_FNAME" />
																						</td>
																						</s:if>
																						<s:else>
																						<td>&nbsp;</td>
																						</s:else>
																						
																						<s:if test="ADDRESS!=null">
																						<td align="center">
																							<s:property value="ADDRESS" />
																						</td>
																						</s:if>
																						<s:else>
																						<td>&nbsp;</td>
																						</s:else>
																						<s:if test="CONTACT!=null">
																						<td align="center">
																							<s:property value="CONTACT" />
																						</td>
																						</s:if>
																						<s:else>
																						<td>&nbsp;</td>
																						</s:else>
																						<s:if test="TEL!=null">
																						<td align="center">
																							<s:property value="TEL" />
																						</td>
																						</s:if>
																						<s:else>
																						<td>&nbsp;</td>
																						</s:else>
																						
																						<s:if test="EMAIL!=null">
																						<td align="center">
																							<s:property value="EMAIL" />
																						</td>
																						</s:if>
																						<s:else>
																						<td>&nbsp;</td>
																						</s:else>
																						<s:if test="FAX!=null">
																						<td align="center">
																							<s:property value="FAX" />
																						</td>
																						</s:if>
																						<s:else>
																						<td>&nbsp;</td>
																						</s:else>																						
																					</tr>
																				</s:iterator>
																			</s:if>
																		

																	</table>
																</div>
															</td>
														</tr>
														
														</s:form>
														
														
													</table>
													<table width="98%" height="35" border="0" cellpadding="0" cellspacing="0">
					          <tr>
								<td height="25" align="right" class="right_tb1" colspan="3">
								<!--分页  -->
									<s:component template="pagev2.jsp" templateDir="page/pageutil" theme="template">
									<!--action name  -->
										<s:param name="formId" value="'organization'" />
										<s:param name="action_name" value="actionName" />
										<!-- name space -->
										<s:param name="namespace" value="'/page/organization'" />
										<!-- pagination method name -->
										<s:param name="pagination_method_name" value="actionName" />
										<!--当前页前后显示的数量  -->
										<s:param name="distanceNum" value="5" />
									</s:component>
								</td>
							  </tr>
					    </table>
													
												

										</table>
										
									</td>
									
								</tr>
							</table>		
		

	</body>
</html>
