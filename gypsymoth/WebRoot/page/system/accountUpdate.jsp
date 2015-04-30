<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统——修改用户</title>
		<%@ include file="../include/head.jsp"%>
		<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/jsFrame.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/updateRole.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajax.js"></script>
		<link href="${pageContext.request.contextPath}/style/dtree.css" type="text/css" rel="stylesheet" />
	</head>
	
	<script type="text/javascript" charset="utf-8">
		function _onload(){
			var rolemessage = "${rolemsg}";
			if(!(rolemessage==""))
			{
				document.getElementById("role_name_mess").innerHTML="${rolemsg}";
			}
		}
        function checkAdd(){
        
        	/**
   			*校验角色
   			*/
   			if(document.forms['updateAccountForm'].roleHas.options.length==0){
   				document.getElementById("account_role_mess").innerHTML="角色不能为空！";
   				return false;
   			}
   			var roleHas = document.forms['updateAccountForm'].roleHas.options[0].value;
   			if(roleHas=="-123"){
   				document.getElementById("account_role_mess").innerHTML="角色不能为空！";
   				return false;
   			}
   			document.getElementById("account_role_mess").innerHTML="";
   			

   			/**
   			*判断人员类型（检查员、协调员、授权签字人）是否已选择了相应角色
   			*/
   			var isc = document.getElementById("isc").value;
   			if(isc==1){//是协调员
   				var tmp1=0;
   				for(var j=0; j<document.forms['updateAccountForm'].roleHas.length; j++)
				{
					if(document.forms['updateAccountForm'].roleHas.options[j].value==3){
						tmp1=1;
					}
				}
				if(tmp1==0){
					document.getElementById("account_role_mess").innerHTML="该人员为协调员，协调员角色必选！";
   					return false;
				}
   			}
   			var isa = document.getElementById("isa").value;
   			if(isa==1){//授权签字人
   				var tmp2=0;
   				for(var j=0; j<document.forms['updateAccountForm'].roleHas.length; j++)
				{
					if(document.forms['updateAccountForm'].roleHas.options[j].value==4){
						tmp2=1;
					}
				}
				if(tmp2==0){
					document.getElementById("account_role_mess").innerHTML="该人员为授权签字人，授权签字人角色必选！";
   					return false;
				}
   			}
   			var isi = document.getElementById("isi").value;
   			if(isi==1){//是检查员
   				var tmp3=0;
   				for(var j=0; j<document.forms['updateAccountForm'].roleHas.length; j++)
				{
					if(document.forms['updateAccountForm'].roleHas.options[j].value==2){
						tmp3=1;
					}
				}
				if(tmp3==0){
					document.getElementById("account_role_mess").innerHTML="该人员为检查员，检查员角色必选！";
   					return false;
				}
   			}
   			document.getElementById("account_role_mess").innerHTML="";
        	
        	for(var j=0; j<document.forms['updateAccountForm'].roleHas.length; j++)
			{
				document.forms['updateAccountForm'].roleHas.options[j].selected=true;
			}
   			document.getElementById("updateAccountForm").submit();
        }
    </script>
	<body>
		
					<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
					  <tr>
					    <td class="list_r">&nbsp;</td>
					    <td valign="top" class="right"><div class="title">您的当前位置： 首页 &gt;&gt; 系统管理 &gt;&gt; <strong>用户管理</strong></div>
					      <div>
					        <table align="center" cellpadding="12" class="tabedit">
					          <tr>
					            <td height="300" align="left" valign="top">
					            <!--编辑开始-->
					                <table width="700" align="center" cellspacing="5">
					                <s:form theme="simple" method="post" action="updateAccount"
									id="updateAccountForm" name="updateAccountForm">
									<input type="hidden" name="currPageTmp" id="currPageTmp" value="<s:property value="#request.currPage"/>" />
									<input type="hidden" name="account.accountId" id="accountId" value="${account.accountId }" />
									<input type="hidden" name="account.accountType" id="accountType" value="${account.accountType }" />
									<input type="hidden" name="account.accountStatus" id="accountStatus" value="${account.accountStatus }" />
					                <s:if test="#request.personIs.size>0">
					                	<s:iterator value="#request.personIs">
					                	<input type="hidden" name="isc" id="isc" value="${ISC }" />
					                	<input type="hidden" name="isa" id="isa" value="${ISA }" />
					                	<input type="hidden" name="isi" id="isi" value="${ISI }" />
					                	</s:iterator>
					                </s:if>
					                  <tr>
									    <td height="25" colspan="3" align="left"
												style="border-bottom: dotted 1px #8EC0E8;">
												<span style="color: #FF0000;">*</span>号为必填项
									    </td>
									  </tr>
					                  <tr>
					                    <td width="68" height="20">用 户 名</td>
					                    <td width="332"><label>
					                      <input id="accountName" name="account.accountName" type="text" class="input" size="55" disabled="disabled" value="${account.accountName }" />
					                    </label></td>
					                    <td width="272">
					                    	<span class="mainred">
													<div id="account_name_mess">
															<span style="color: #FF0000;">*</span>
													</div>
											</span>
					                    </td>
					                  </tr>
					                  <tr>
					                    <td width="68" height="20">角　　色</td>
					                    <td width="332">
					                    			<table>
														<tr>
															<td>
																<select name="roleNotHas" size="12" id="roleList"
																	multiple="multiple">
																	<s:if test="#request.roleNotHasList.size>0">
																		<s:iterator value="#request.roleNotHasList">
																			<option value="<s:property value="ROLE_ID"/>">
																				<s:property value="ROLE_NAME" />
																			</option>
																		</s:iterator>
																	</s:if>
																	<s:else>
																		<option value="-123">
																			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																			&nbsp;&nbsp;&nbsp;
																		</option>
																	</s:else>
																</select>
															</td>
															<td>
																<input type="button" value=" > " class="button"
																	onclick="moveSingleRole(document.forms['updateAccountForm'].roleNotHas,document.forms['updateAccountForm'].roleHas)" />
																<br />
																<input type="button" value=">>" class="button"
																	onclick="moveAllRole(document.forms['updateAccountForm'].roleNotHas,document.forms['updateAccountForm'].roleHas)" />
																<br />
																<input type="button" value=" < " class="button"
																	onclick="moveSingleRole(document.forms['updateAccountForm'].roleHas,document.forms['updateAccountForm'].roleNotHas)" />
																<br />
																<input type="button" value="<<" class="button"
																	onclick="moveAllRole(document.forms['updateAccountForm'].roleHas,document.forms['updateAccountForm'].roleNotHas)" />
															</td>
															<td>
																<select name="roleHas" size="12" id="roleHas"
																	multiple="multiple">
																	<s:if test="#request.roleHasList.size>0">
																		<s:iterator value="#request.roleHasList">
																			<option value="<s:property value="ROLE_ID"/>">
																				<s:property value="ROLE_NAME" />
																			</option>
																		</s:iterator>
																	</s:if>
																	<s:else>
																		<option value="-123">
																			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																			&nbsp;&nbsp;&nbsp;
																		</option>
																	</s:else>
																</select>
															</td>
														</tr>
													</table>
					                    </td>
					                    <td width="272">
					                    	<span class="mainred">
													<div id="account_role_mess">
														<span style="color: #FF0000;">*</span> 用户所具有的角色
													</div> 
											</span>
					                    </td>
					                  </tr>

					                  <tr>
					                    <td height="38" align="left"></td>
					                    <td height="38" align="left">
					                    	<input name="" class="button" type="button" value="修 改"
													onclick="checkAdd()" />
											<input name="" class="button"
													onclick="javascript:history.back(-1)" type="reset"
													value="返 回" />    
					                    </td>
					                    <td align="left">&nbsp;</td>
					                  </tr>
					                  </s:form>
					                </table>
					              <!--编辑结束-->
					            </td>
					          </tr>
					        </table>
					      </div></td>
					  </tr>
					</table>
	</body>
</html>
