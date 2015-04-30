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

	<script type="text/javascript">
			
			
			 $(document).ready(function(){
		$("#chkall").click(function(){ 
			 if($(this).attr("checked")){
				$("input[@name='idArray']").attr("checked","true");
			 }else{ 
				$("input[@name='idArray']").attr("checked","");
			 }
		 }); 
	}
		
		
		
		
		);
		
		
function deletebulletin(){
	var a = document.getElementsByName("idArray"); 
  	var count = 0;
	for(var i=0;i<a.length;i++){
		if(a[i].checked){
			count++;
		}
	}
  	if(count==0){
 		alert('删除至少选择一项信息');
 		return;
 	}	
	document.forms[0].method="post";
 	document.forms[0].action ="delete.action" ;
 	document.forms[0].submit();	
	}
	
function clientAdd(){
document.qalist.method="post";
document.qalist.action="clientAdd.action";
document.qalist.submit();

}
	
	
  </script>

	<body>
		<!--内容开始-->
		<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
			<tr>
				<td class="list_r">
					&nbsp;
				</td>
				<td valign="top" class="right">
					<div class="title">
						<s:text name="您的当前位置" />： <s:text name="首页" /> &gt;&gt; 	<s:text name="客户管理" /> &gt;&gt;
						<strong><s:text name="客户信息" /></strong>
					</div>
					<div>
						<!--编辑开始-->

						<table width="100%" border="0" cellpadding="6" cellspacing="0"
							class="tabman">
							<s:form action="getClientsByPage" id="clientlist" theme="simple"
								name="clientlist">
								<tr>
									<td height="35" align="center">
										<s:text name="公司中文名称" />:&nbsp;
										<input name="coCnName_search" type="text" class="input"
											size="18" value="${coCnName_search }" />
										&nbsp; <s:text name="公司英文名称" />:&nbsp;
										<input id="coEnName" name="coEnName_search" type="text"
											class="input" size="18" value="${coEnName_search }" />
										&nbsp; &nbsp;
										<input name="search" id="search" type="submit" class="button"
											value="<s:text name='查询'/>"  />
									</td>
								</tr>
							</s:form>
						</table>

						<table width="100%" class="tableheader">
							<tr>
								<td width="5%" height="26">
									<img src="../../images/bg_mcontentL.gif" width="32" height="27" />
								</td>
								<td valign="middle">
									<h3>
										<s:text name="列表" />
									</h3>
								</td>
								<td align="right" valign="middle">
									<input type="submit"
										onclick="javascript:window.location.href='clientsAdd.action'"
										class="button_s" value="<s:text name='添加客户'/>" />
								</td>
								<td width="1%" align="right">
									<img src="../images/bg_mcontentR.gif" width="7" height="27" />
								</td>
							</tr>
						</table>
						<table width="100%" class="tableCont">
							<tr>
								<td>
									<div class="tableContent">
										<table width="100%" border="0" cellspacing="1" cellpadding="0">

											<tr>
												<th width="6%">
													<input name="chkall" type="checkbox" id="chkall"
														value="checkbox" />
												</th>
												<th width="28%">
													<s:text name="公司中文名称" />
												</th>
												<th width="28%">
													<s:text name="公司英文名称" />
												</th>
												<th>
													<s:text name="已检查的业务量" />
												</th>

												<th width="20%">
													<s:text name="操作" />
												</th>


											</tr>

											<s:form id="datalist" name="datalist"
												action="getClientByPage" theme="simple">
												<s:if test="#request.clientlist.size>0">
													<s:iterator value="#request.clientlist" status="stat"
														id="mop">
														<tr>
															<td align="center">

																<input type="checkbox" name="idArray"
																	value="<s:property value="clientId"/>" />
															<td align="center">
																<s:if test="%{#mop.CO_CN_NAME == null}">
																	<font color="#b3b3b3">&nbsp;</font>
																</s:if>
																<s:else>
																	<s:property value="#mop.CO_CN_NAME" />
																</s:else>
															</td>
															<td align="center">
																<s:if test="%{#mop.CO_EN_NAME == null}">
																	<font color="#b3b3b3">&nbsp;</font>
																</s:if>
																<s:else>
																	<s:property value="#mop.CO_EN_NAME" />
																</s:else>
															</td>
															<td align="center">
																<s:property value="#mop.EXAMINNUM" />
															</td>
															<td align="center">

																<a
																	href="<s:url action="getClientsById"><s:param name="clientsId"><s:property value="#mop.CLIENT_ID"/></s:param></s:url>"><s:text name="update" /></a>
																<a
																	href="<s:url action="deleteClientsById"><s:param name="clientsId"><s:property value="#mop.CLIENT_ID"/></s:param></s:url>"
																	onclick="return window.confirm('是否删除！')"><s:text name="delete" /></a>
																<a
																	href="<s:url action="getClientsInfoById"><s:param name="clientsId"><s:property value="#mop.CLIENT_ID"/></s:param></s:url>"><s:text name="查看" /></a>
															</td>
														</tr>
													</s:iterator>
												</s:if>
											</s:form>
										</table>
									</div>
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
										<s:param name="formId" value="'clientlist'" />
										<s:param name="action_name" value="actionName" />
										<!-- name space -->
										<s:param name="namespace" value="'/page/client'" />
										<!-- pagination method name -->
										<s:param name="pagination_method_name" value="actionName" />
										<!--当前页前后显示的数量  -->
										<s:param name="distanceNum" value="5" />
									</s:component>
								</td>
							</tr>
						</table>
						<!--编辑结束-->
		</table>

	</body>
</html>
