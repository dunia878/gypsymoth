<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统——修改密码</title>
		<%@ include file="../include/head.jsp"%>
		<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/jsFrame.js"></script>
	</head>
	
	<script type="text/javascript" charset="utf-8">
        function checkAdd(){
        	var oldPassword = document.getElementById('oldPassword').value;
			var password = document.getElementById('password').value;
			var password2 = document.getElementById('password2').value;
			var formatPwd = /^\w+$/;//判断密码的正则表达式
			if(oldPassword==""){
   			document.getElementById("oldPassword_mess").innerHTML="<s:text name='password_error1'/>";
			return false;   			
	   		}else if(!formatPwd.test(oldPassword)){
	   			document.getElementById("oldPassword_mess").innerHTML="<s:text name='password_error2'/>";
	   			return false;
	   		}else if(oldPassword.length<6){
	   			document.getElementById("oldPassword_mess").innerHTML="<s:text name='password_error3'/>";
	   			return false;
	   		}else if(oldPassword.length>15){
	   			document.getElementById("oldPassword_mess").innerHTML="<s:text name='password_error4'/>";
	   			return false;
	   		}
	   		document.getElementById("oldPassword_mess").innerHTML="";
	   		
 			if(password==""){
   			document.getElementById("password_mess").innerHTML="<s:text name='password_error1'/>";
			return false;   			
	   		}else if(!formatPwd.test(password)){
	   			document.getElementById("password_mess").innerHTML="<s:text name='password_error2'/>";
	   			return false;
	   		}else if(password.length<6){
	   			document.getElementById("password_mess").innerHTML="<s:text name='password_error3'/>";
	   			return false;
	   		}else if(password.length>15){
	   			document.getElementById("password_mess").innerHTML="<s:text name='password_error4'/>";
	   			return false;
	   		}
	   		document.getElementById("password_mess").innerHTML="";
	   		
	   		if(password!=password2){
	   			document.getElementById("password2_mess").innerHTML="<s:text name='password_error5'/>";
	   			return false;
	   		}
	   		document.getElementById("password2_mess").innerHTML="";
   			
   			document.getElementById("updatePasswordForm").submit();
        }
    </script>
	<body>
					<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
					  <tr>
					    <td class="list_r">&nbsp;</td>
					    <td valign="top" class="right"><div class="title"><s:text name="您的当前位置"/>： <s:text name="首页"/> &gt;&gt;<s:text name="系统管理"/> &gt;&gt; <strong><s:text name="密码修改"/></strong></div>
					      <div>
					        <table align="center" cellpadding="12" class="tabedit">
					          <tr>
					            <td height="300" align="left" valign="top">
					            <!--编辑开始-->
					                <table width="700" align="center" cellspacing="5">
					                <s:form theme="simple" method="post" action="/page/system/updatePassword.action"
									id="updatePasswordForm" name="updatePasswordForm">
					                  <tr>
									    <td height="25" colspan="3" align="left"
												style="border-bottom: dotted 1px #8EC0E8;">
												<span style="color: #FF0000;">*</span><s:text name="号为必填项"/>
									    </td>
									  </tr>
									  <tr>
									    <td height="25" colspan="3" align="left">
												<font color="red">${message }</font>
									    </td>
									  </tr>
					                  <tr>
					                    <td width="68" height="20"><s:text name="原始密码"/></td>
					                    <td width="332"><label>
					                      <input id="oldPassword" name="oldPassword" type="password"" class="input" size="55" value="" />
					                    </label></td>
					                    <td width="272">
					                    	<span class="mainred">
													<div id="oldPassword_mess">
														* <s:text name="密码提示"/>
													</div> 
											</span>
					                    </td>
					                  </tr>
					                  <tr>
					                    <td width="68" height="20"><s:text name="新密码"/></td>
					                    <td width="332"><label>
					                      <input id="password" name="password" type="password" class="input" size="55" value="" />
					                    </label></td>
					                    <td width="272">
					                    	<span class="mainred">
													<div id="password_mess">
														* <s:text name="密码提示"/>
													</div> 
											</span>
					                    </td>
					                  </tr>
					                  <tr>
					                    <td width="68" height="20"><s:text name="重复密码"/></td>
					                    <td width="332"><label>
					                      <input id="password2" name="password2" type="password" class="input" size="55" value="" />
					                    </label></td>
					                    <td width="272">
					                    	<span class="mainred">
													<div id="password2_mess">
														* <s:text name="密码提示"/>
													</div> 
											</span>
					                    </td>
					                  </tr>
					                  <tr>
					                    <td height="38" align="left"></td>
					                    <td height="38" align="left">
					                    	<input name="" class="button" type="button" value="<s:text name='update'/>"
													onclick="checkAdd()" />  
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
