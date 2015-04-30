<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<s:head theme="ajax" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>中检集团船舶舞毒蛾检查业务信息系统</title>
	<link href="style/login.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
	if (window != top) 
    	top.location.href = location.href;

	function checkLogin(){
		var username = document.getElementById('accountName').value;
		var password = document.getElementById('password').value;
		var validate = document.getElementById('validate').value;

		if(username == null || username.length == 0){
			alert("用户名不能为空");
			document.getElementById("accountName").focus();
			return false;
		}
		if(password == null || password.length == 0){
			alert("密码不能为空");
			document.getElementById("password").focus();
			return false;
		}
		if(validate == null || validate.length == 0){
			alert("验证码不能为空");
			document.getElementById("validate").focus();
			return false;
		}
		return true;
	}
	
	function openLink(id)
	{
		var url = "<%=basePath%>/page/information/downloadlist.action?id="+id;
		window.open(url,"_blank","top=200px,left=300px,location=no,titlebar=no,toolbar=no,menubar=no,resizable=yes,width=400px,height=200px,scrollbars=yes"); 
		  
	}

</script>

	<body>
		<div class="top">
			<table border="0" align="right" cellpadding="0" cellspacing="0">
				<tr>
					<td align="right">
						<img src="${pageContext.request.contextPath}/images/logo_bt.jpg"
							width="228" height="113" border="0" usemap="#Map" />
					</td>
				</tr>
			</table>
		</div>
		<div class="mid">
			<table border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="412" height="352" valign="top"
						background="${pageContext.request.contextPath}/images/log_news.gif">
						<table width="95%" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td height="82" align="right" valign="bottom"
									style="padding-right: 15px;">
									[
									<a href="page/bulletin/getAllDataByPage.action">more..</a>]
								</td>
							</tr>
							<tr>
								<td height="106" valign="top">
									<s:action name="indexList" namespace="/page/bulletin" />
									<div class="list">
										<ul>
											<s:if test="#request.list.size>0">
												<s:iterator value="#request.list" status="st">
													<li>
														<a href="<s:url action="page/bulletin/getBulletinById" >   <s:param name="bulletinId"><s:property value="bulletinId"/></s:param>  </s:url>">
															<s:property value="bulletinName" /> </a>
													</li>
												</s:iterator>

											</s:if>
										</ul>
									</div>
								</td>
							</tr>
						</table>
						<table width="95%" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td height="45" align="right" valign="bottom"
									style="padding-right: 15px;">
									[
									<a href="page/information/getAllDataByPage.action">more..</a>]
								</td>
							</tr>
							<tr>
								<td height="72" valign="top">
									<div class="list1">

										<s:action name="indexList1" namespace="/page/information" />
										<ul>
											<s:if test="#request.list1.size>0">
												<s:iterator value="#request.list1" status="st">

													<li>
														
														<a href="#" onclick="javascript:openLink('<s:property value="informationid"/>');">
															<s:property value="informationname" /> 
														</a>
														
													</li>

												</s:iterator>
											</s:if>
										</ul>
									</div>
								</td>
							</tr>
						</table>
					</td>
					<td width="20">&nbsp;
						
					</td>
					<td width="412" valign="top"
						background="${pageContext.request.contextPath}/images/log_logbg.gif">
						<table width="80%" border="0" cellpadding="0" cellspacing="0">
<s:form id="login_id" action="login" method="post" onsubmit="return checkLogin();">
								<tr>
									<td width="25%" height="166" align="right" valign="bottom">&nbsp;</td>
									<td width="21%" align="right" valign="bottom" class="maina"
										style="padding-bottom: 5px;">
								  		<s:if test="#session.WW_TRANS_I18N_LOCALE!=null && #session.WW_TRANS_I18N_LOCALE.toString()=='en_US'">
											Account:&nbsp;&nbsp;
								  		</s:if>
										<s:else>
											用户名:&nbsp;&nbsp;
										</s:else>
									</td>
					  				<td height="166" align="right" valign="bottom">
                                
										<input name="accountName" type="text" class="input_login"
											id="accountName" style="margin-right: 15px;" />
									</td>
							  	</tr>
								<tr>
									<td height="33" align="right" valign="bottom">&nbsp;</td>
							  		<td align="right" valign="bottom" class="maina" style="padding-bottom: 5px;">
						  				<s:if test="#session.WW_TRANS_I18N_LOCALE!=null && #session.WW_TRANS_I18N_LOCALE.toString()=='en_US'">
											password:&nbsp;&nbsp;
										</s:if>
										<s:else>
											密　码:&nbsp;&nbsp;
										</s:else>
									</td>
							  		<td align="right" valign="bottom">
					        			<input name="password" type="password" class="input_login"
											id="password" style="margin-right: 15px;" />
									</td>
			   	 				</tr>
								<tr>
									<td height="40" colspan="3" align="right" valign="bottom">
							          <table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="25%">&nbsp;</td>
												<td width="21%" align="right" class="maina">
											  		<s:if test="#session.WW_TRANS_I18N_LOCALE!=null && #session.WW_TRANS_I18N_LOCALE.toString()=='en_US'">
											      		code:&nbsp;&nbsp;</s:if>
													<s:else>
														验证码:&nbsp;&nbsp;</s:else>
												</td>
										  		<td  align="left" >
													<input name="validate" type="text" class="input_login"
														id="validate" style="width: 50px;" />
												</td>
												<td align="right">
													&nbsp;&nbsp;<iframe width="74" height="18" src="${pageContext.request.contextPath}/validate" frameBorder="0"
														scrolling="no" marginwidth="0" marginheight="0" border="0" id="validate" name="validate"">
														<img src="${pageContext.request.contextPath}/validate"
														alt="验证码" width="74" height="18" />
													</iframe>
												</td>
												<td>
													<a href="#" onclick="javascript:window.parent.validate.location.reload();">
														<s:if test="#session.WW_TRANS_I18N_LOCALE!=null && #session.WW_TRANS_I18N_LOCALE.toString()=='en_US'">
															<span style="font-size:11px;">Refresh</span>
														</s:if>
														<s:else>
															刷新	
														</s:else>
													</a>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td height="57" colspan="2" align="right" valign="bottom">&nbsp;</td>
									<td width="54%" align="left" valign="bottom">
										<input type="submit"" class="bt" <s:if test="#session.WW_TRANS_I18N_LOCALE!=null && #session.WW_TRANS_I18N_LOCALE.toString()=='en_US'">value="Login"</s:if><s:else>value="登 录"</s:else> />
										<input type="button" class="bt" onclick="window.location.href='${pageContext.request.contextPath}/page/register/getInitData.action'" <s:if test="#session.WW_TRANS_I18N_LOCALE!=null && #session.WW_TRANS_I18N_LOCALE.toString()=='en_US'">value="Registrate"</s:if><s:else>value="注 册"</s:else> />
									</td>
								</tr>
								<tr>
								  <td colspan="2" align="right" valign="bottom">&nbsp;</td>
								  <td align="left" valign="bottom"><img src="${pageContext.request.contextPath}/images/menu_icon.gif" border="0" />
								  	<a href="${pageContext.request.contextPath}/page/register/forgotPassword.jsp">
								  		<s:if test="#session.WW_TRANS_I18N_LOCALE!=null && #session.WW_TRANS_I18N_LOCALE.toString()=='en_US'">
											<span style="font-size:11px;">Forgot Password</span>
										</s:if>
										<s:else>
											忘记密码
										</s:else>
								  	</a>
								  </td>
		    					</tr>
							</s:form>
						</table>
					  <div align="left"><br /><font color="red"><b>　${message }${session.tip}</b></font></div>
					</td>
				</tr>
			</table>
		</div>
		<div class="footer"></div>

		<map name="Map" id="Map">
			<area shape="rect" coords="29,89,94,109" href="${pageContext.request.contextPath}/index.jsp?request_locale=zh_CN" target="_top" alt="中文" />
			<area shape="rect" coords="116,88,186,110" href="${pageContext.request.contextPath}/index.jsp?request_locale=en_US" target="_top" alt="英文" />
		</map>
	</body>
</html>