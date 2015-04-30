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
			src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
		<script language="JavaScript" type="text/javascript">
function save(){
	document.form1.method="post";
	document.form1.action="updataCs.action";
	if(confirm("您是否确认修改?")){
		document.form1.submit();
	}
}

function del(){
window.confirm('是否删除！');
document.form1.method="post";
document.form1.action="deleteClientsById.action";
document.form1.submit();

}

function turnBack(){
document.form1.method="post";
document.form1.action="turnBack.action";
document.form1.submit();

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
						<s:text name="您的当前位置" />： <s:text name="首页" /> &gt;&gt; 	<s:text name="客户管理" /> &gt;&gt;
						<strong><s:text name="客户信息" /></strong>
					</div>
					<div>
						<table align="center" cellpadding="12" class="tabedit">
							<tr>
								<td height="300" align="left" valign="top">
									<!--编辑开始-->
									<table width="600" align="center" cellspacing="5">
										<s:form theme="simple" method="post" action="" id="form1"
											name="form1">
											<input type="hidden" name="clientId" id="clientId"
												value="<s:property value="client.clientId"/>" />
												<input type="hidden" name="address" id="address"
												value="<s:property value="client.address"/>" />
											<input type="hidden" name="parentId" id="parentId"
												value="<%=request.getAttribute("parentId") %>"" />
												
										<tr>
												<td width="80" height="20" align="left">
													<s:text name="用户名"></s:text>
												</td>
												<td width="390" align="left">
													<label>
														<input id="clientName" name="client.clientName"
															type="text" class="input" size="42" disabled="disabled" 
															value="<%=request.getAttribute("client_name") %>"
															 />
													</label>
												</td>
											</tr>
											<tr>
												<td height="20" align="left">
														<s:text name="公司名称" />
												</td>
												<td align="left">
														<s:text name="中文" />
													<input id="client.coCnName" name="client.coCnName"
														type="text" class="input" size="49" disabled="disabled"
														value="<s:property value="client.coCnName"/>" />
												</td>
											</tr>
											<tr>
												<td height="20" align="left">
													&nbsp;
												</td>
												<td align="left">
													<s:text name="英文" />
													<input id="client.coEnName" name="client.coEnName"
														type="text" class="input" size="49" disabled="disabled"
														value="<s:property value="client.coEnName"/>" />
												</td>
											</tr>
											<tr>
												<td height="20" align="left">
													<s:text name="工商注册号" />
												</td>
												<td align="left">
													<input id="client.businessLicense"
														name="client.businessLicense" type="text" class="input"
														size="55" disabled="disabled"
														value="<s:property value="client.businessLicense"/>" />
												</td>
											</tr>
											<tr>
												<td height="20" align="left">
														<s:text name="企业性质" />
												</td>
												<td align="left">
													<input id="client.coProperty"
														name="client.coProperty" type="text" class="input"
														size="55" disabled="disabled"
														value="<s:property value="client.coProperty"/>" />
												</td>
											</tr>
											<tr>
												<td height="20" align="left">
												<s:text name="法人代表"/>
												</td>
												<td align="left">
													<input disabled="disabled" id="client.legalPerson"
														name="client.legalPerson" type="text" class="input"
														size="55" value="<s:property value="client.legalPerson"/>" />
												</td>
											</tr>
											<tr>
												<td height="20" align="left">
													<s:text name="联系电话"/>
												</td>
												<td align="left">
													<input disabled="disabled" id="client.tel"
														name="client.tel" type="text" class="input" size="55"
														value="<s:property value="client.tel"/>" />
												</td>
											</tr>
											<tr>
												<td height="20" align="left">
														<s:text name="电子邮件"/>
												</td>
												<td align="left">
													<input disabled="disabled" id="client.email"
														name="client.email" type="text" class="input" size="55"
														value="<s:property value="client.email"/>" />
												</td>
											</tr>
											<tr>
												<td height="20" align="left">
												<s:text name="公司地址"/>
												</td>
												<td align="left">
													<input disabled="disabled" id="client.address"
														name="client.address" type="text" class="input" size="55"
														value="<s:property value="promary.proname"/><s:property value="cityname"/><s:property value="client.address"/>" />
												</td>
											</tr>
	

											<tr>
												<td height="20" align="left">
												<s:text name="邮编"/>
												</td>
												<td align="left">
													<input disabled="disabled" id="client.post"
														name="client.post" type="text" class="input" size="55"
														value="<s:property value="client.post"/>" />
												</td>
											</tr>
											<tr>
												<td height="20" align="left">
														<s:text name="联系人"/>1
												</td>
												<td align="left">
													<table width="73%" border="0" cellpadding="0"
														cellspacing="0">
														<tr>
															<td width="10%" align="center">
																<s:text name="姓名"/>
															</td>
															<td width="20%">
																<input disabled="disabled" id="client.onePersonName"
																	name="client.onePersonName" type="text" class="input"
																	style="width: 65px;"
																	value="<s:property value="client.onePersonName"/>" />
															</td>
															<td width="10%" align="center">
																	<s:text name="电话"/>
															</td>
															<td>
																<input disabled="disabled" id="client.onePersonTel"
																	name="client.onePersonTel" type="text" class="input"
																	style="width: 85px;"
																	value="<s:property value="client.onePersonTel"/>" />
															</td>
															<td width="10%" align="center">
															<s:text name="手机"/>
															</td>
															<td>
																<input disabled="disabled" id="client.onePersonPhone"
																	name="client.onePersonPhone" type="text" class="input"
																	style="width: 85px;"
																	value="<s:property value="client.onePersonPhone"/>" />
															</td>
														</tr>
														
													</table>
												</td>
											</tr>
											<tr>
												<td height="20" align="left">
													<s:text name="联系人"/>2
												</td>
												<td align="left">
													<table width="73%" border="0" cellpadding="0"
														cellspacing="0">
														<tr>
															<td width="10%" align="center">
																<s:text name="姓名"/>
															</td>
															<td width="20%">
																<input disabled="disabled" id="client.twoPersonName"
																	name="client.twoPersonName" type="text" class="input"
																	style="width: 65px;"
																	value="<s:property value="client.twoPersonName"/>" />
															</td>
															<td width="10%" align="center">
															<s:text name="电话"/>
															</td>
															<td>
																<input disabled="disabled" id="client.twoPersonTel"
																	name="client.twoPersonTel" type="text" class="input"
																	style="width: 85px;"
																	value="<s:property value="client.twoPersonTel"/>" />
															</td>
															<td width="10%" align="center">
																<s:text name="手机"/>
															</td>
															<td>
																<input id="client.twoPersonPhone" disabled="disabled"
																	name="client.twoPersonPhone" type="text" class="input"
																	style="width: 85px;"
																	value="<s:property value="client.twoPersonPhone"/>" />
															</td>
														</tr>
														
													</table>
												</td>
											</tr>
											<tr>
												<td height="20" align="left">
														<s:text name="邮箱" />
												</td>
												<td align="left">
													<input disabled="disabled" id="client.registerEmail"
														name="client.registerEmail" type="text" class="input"
														size="55"
														value="<s:property value="client.registerEmail"/>" />
												</td>
											</tr>
											<tr>
												<td height="20" align="left">
													<s:text name="开户银行" />
												</td>
												<td align="left">
													<input disabled="disabled" id="client.bank"
														name="client.bank" type="text" class="input"
														size="55"
														value="<s:property value="client.bank"/>" />
												</td>												
											</tr>
											<tr>
												<td height="20" align="left">
													<s:text name="分行" />
												</td>
												<td align="left">
													<input disabled="disabled" id="client.branch"
														name="client.branch" type="text" class="input"
														size="55"
														value="<s:property value="client.branch"/>" />
												</td>												
											</tr>
											<tr>
												<td height="20" align="left">
													<s:text name="支行" />
												</td>
												<td align="left">
													<input disabled="disabled" id="client.subBranch"
														name="client.subBranch" type="text" class="input"
														size="55"
														value="<s:property value="client.subBranch"/>" />
												</td>												
											</tr>
											<tr>
												<td height="20" align="left">
													<s:text name="账户名" />
												</td>
												<td align="left">
													<input disabled="disabled" id="client.accountName"
														name="client.accountName" type="text" class="input"
														size="55"
														value="<s:property value="client.accountName"/>" />
												</td>												
											</tr>
											<tr>
												<td height="20" align="left">
													<s:text name="银行账号" />
												</td>
												<td align="left">
													<input disabled="disabled" id="client.bankaccount"
														name="client.bankaccount" type="text" class="input"
														size="55"
														value="<s:property value="client.bankaccount"/>" />
												</td>
												
											</tr>
											<tr>
												<td height="20" align="left">
													<s:text name="发票抬头" />
												</td>
												<td align="left">
													<input disabled="disabled" id="client.vbrk"
														name="client.vbrk" type="text" class="input"
														size="55"
														value="<s:property value="client.vbrk"/>" />
												</td>
												
											</tr>
											<tr>
												<td height="20" align="left">
													<s:text name="备注"/>
												</td>
												<td align="left">
													<input disabled="disabled" id="client.des"
														name="client.des" type="text" class="input" size="55"
														value="<s:property value="client.des"/>" />
												</td>
											</tr>
											<tr>
												<td height="38" align="left"></td>
												<td height="38" align="left">
															<input name="input" class="button" type="button"
																onclick="save()" value="<s:text name='update'/>" />
												
												<s:if test="#request.accounttype == 1 ">
															<input name="input" class="button" type="button"
																onclick="del()" value="删除" />
												</s:if>
												
												
													<input name="input4" class="button" type="reset"
														value="<s:text name='return'/>" onclick="history.back(-1)" />
												</td>
											</tr>
										</s:form>
									</table>
									<!--编辑结束-->
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table>

	</body>
</html>
