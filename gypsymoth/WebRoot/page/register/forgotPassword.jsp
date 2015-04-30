<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统</title>
		<link href="${pageContext.request.contextPath}/style/help.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajax.js"></script>
		<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/jsFrame.js"></script>
	</head>
 
	
	
	<script type="text/javascript" charset="utf-8">
        function checkAdd(){
        	var language = "${session.WW_TRANS_I18N_LOCALE }";

        	var loginname = document.getElementById("accountName").value;
        	var validate = document.getElementById('validate').value;

 			if(loginname==""){
 				document.getElementById("account_name_mess").innerHTML="<font color='red'><s:text name='account_error1'/></font>";
   				document.getElementById("accountName").focus();
   				return false;
   			}
   			document.getElementById("account_name_mess").innerHTML="";
 			
   			if(validate==""){
   				document.getElementById("validate_mess").innerHTML="<font color='red'>验证码不能为空</font>";
   				document.getElementById("validate").focus();
				return false;   			
   			}
   			document.getElementById("validate_mess").innerHTML="";
        
   			document.getElementById("forgotPasswordForm").submit();
        }

    </script>
	<body>
	
	
	
		<!--页头开始-->
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
					<td height="104" align="right" valign="bottom">
					</td>
				</tr>
			</table>
		</div>
		<!-- 页头结束 -->
		<div class="content">
			<table width="100%" border="0" cellpadding="0" cellspacing="7"
				bgcolor="#ffffff"
				style="background-image: url(../../images/img1.gif); background-repeat: repeat-x;">
				<tr>
					<td valign="top" bgcolor="#FFFFFF"
						style="border-bottom: 1px solid #96BBE9; border-top: 1px solid #96BBE9; border-left: 1px solid #96BBE9; border-right: 1px solid #96BBE9; background-color: #ffffff;">
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="title">
							<tr>
								<td>
									<s:if test="#session.WW_TRANS_I18N_LOCALE!=null && #session.WW_TRANS_I18N_LOCALE.toString()=='en_US'">
										Fotgot Password
									</s:if>
									<s:else>
										忘记密码
									</s:else>
								</td>
							</tr>
						</table>
						<table width="100%" border="0" cellspacing="5" cellpadding="12">
							<tr>
								<td class="dot">
									<table width="820" align="center" cellpadding="3"
										cellspacing="5">
										<tr>
											<td align="center">
											
											
											<!-- 2011年1月14日修改  -->
											<!--  
												<table width="750" align="right" cellspacing="5">
												<s:form theme="simple" method="post" action="forgotPassword"
													id="forgotPasswordForm" name="forgotPasswordForm">
													<tr>
														<td height="25" colspan="3" align="left"
															style="border-bottom: dotted 1px #8EC0E8;">
															<span style="color: #FF0000;">*</span> 
															<s:if test="#session.WW_TRANS_I18N_LOCALE!=null && #session.WW_TRANS_I18N_LOCALE.toString()=='en_US'">
																required fields
															</s:if>
															<s:else>
																号为必填
															</s:else>
															<font color="red">${message }</font>
														</td>
													</tr>
													<tr>
														<td width="85" height="20" align="left">
															<s:if test="#session.WW_TRANS_I18N_LOCALE!=null && #session.WW_TRANS_I18N_LOCALE.toString()=='en_US'">
																username
															</s:if>
															<s:else>
																用户名
															</s:else>
														</td>
														<td width="381" align="left">
															<label>
																<input id="accountName" name="account.accountName" type="text" 
																	size="35" />
															</label>
														</td>
														<td width="250" align="left" class="mainred">
															<span class="mainred">
																<div id="account_name_mess">
																	<span style="color: #FF0000;">*</span>
																</div>
															</span>
														</td>
													</tr>
													<tr>
														<td height="20" align="left">
															<s:if test="#session.WW_TRANS_I18N_LOCALE!=null && #session.WW_TRANS_I18N_LOCALE.toString()=='en_US'">
																code
															</s:if>
															<s:else>
																验证码
															</s:else>
														</td>
														<td align="left">
															<input name="validate" type="text" size="17" id="validate" />
															<iframe width="74" height="18" src="${pageContext.request.contextPath}/validate" frameBorder="0"
																scrolling="no" marginwidth="0" marginheight="0" border="0" id="validate" name="validate"">
																<img src="${pageContext.request.contextPath}/validate"
																	alt="验证码" width="74" height="18" />
															</iframe>
															<a href="#" onclick="javascript:window.parent.validate.location.reload();">
																<s:if test="#session.WW_TRANS_I18N_LOCALE!=null && #session.WW_TRANS_I18N_LOCALE.toString()=='en_US'">
																	<span style="font-size:11px;">Refresh</span>
																</s:if>
																<s:else>
																	刷新
																</s:else>
															</a>
														</td>
														<td align="left" class="mainred">
															<span class="mainred">
																<div id="validate_mess">
																	<span style="color: #FF0000;">*</span>
																</div>
															</span>
														</td>
													</tr>
													<tr>
														<td height="38" align="left"></td>
														<td height="38" align="left">
															<input name="" class="button" type="button" 
																<s:if test="#session.WW_TRANS_I18N_LOCALE!=null && #session.WW_TRANS_I18N_LOCALE.toString()=='en_US'">value="get password"</s:if><s:else>value="找回密码"</s:else>
																onclick="checkAdd()" />

															<input name="" class="button" type="button" onclick="javascript:window.location='${pageContext.request.contextPath}/index.jsp'"
																<s:if test="#session.WW_TRANS_I18N_LOCALE!=null && #session.WW_TRANS_I18N_LOCALE.toString()=='en_US'">value="Cancel"</s:if><s:else>value="取消"</s:else> />
														</td>
														<td align="left">
															&nbsp;
														</td>
													</tr>
												</s:form>
												</table>
												
										 -->
												
												
											<table width="750" align="right">	
											<tr>
											<td align="center" style="font-size:15px;font-weight: bold;">
												 				
												 				
												 			<s:if test="#session.WW_TRANS_I18N_LOCALE!=null && #session.WW_TRANS_I18N_LOCALE.toString()=='en_US'">
																If you forget your password, &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
																please conntect with CCIC Yanbo Zhang.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
																phone number:010-84603130,&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
																mailbox:zhangyb@ccic.com.						
																[<a href="${pageContext.request.contextPath}/index.jsp"><span style="color:#FF0000">return</span></a>] 
													homepage
															</s:if>
															<s:else>
																如果您忘记密码，请与&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
																中国检验认证集团公司张彦波联系，<br/>
																联系电话：010-84603130，&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
																邮箱：zhangyb@ccic.com
																[<a href="${pageContext.request.contextPath}/index.jsp"><span style="color:#FF0000">返回</span></a>] 
														主页
															
																
	
															</s:else>
												 
											
											</td></tr>
											</table>											
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
