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
        
        	// var email = document.getElementById("email").value;
        	var loginname = document.getElementById("accountName").value;
        	var pwd = document.getElementById("password").value;
        	var repwd = document.getElementById("password2").value;
        	var desc = document.getElementById("des").value;
        	var postcode = document.getElementById("post").value;
        	// var telephone = document.getElementById("tel").value;
        	var linkman = document.getElementById("onePersonName").value;
        	var registerEmail = document.getElementById("registerEmail").value;
        	var coEnName = document.getElementById("coEnName").value;
        	
        	var onePersonPhone = document.getElementById("onePersonPhone").value;
			var twoPersonPhone = document.getElementById("twoPersonPhone").value;
			
        	var formatPwd = /^\w+$/;//判断密码的正则表达式
   			var formatEmail = /^\s*([\w-]+(\.\w+)*@([\w-]+\.)+\w+)\s*$/; //email正则表达式
   			var formatLoginName = /^[a-zA-Z0-9_\u4e00-\u9fa5]+$/;//判断登录名的正则表达式
 			var formatPost = /[1-9]\d{5}(?!\d)/;//验证邮编
 			var formatPhone = /\d{3}-\d{8}|\d{4}-\d{7}|\d{3}-\d{7}|\d{4}-\d{8}/;
 			var formatMobile = /^0{0,1}(13[0-9]?|15[0-9])[0-9]{8}$/;
 			var num_reg = /^[0-9]+$/; 
 			
 			
 			var bank1 =  document.getElementById("bank1").value;
			var bank2 =  document.getElementById("bank2").value;
			var branch =  document.getElementById("branch").value;
			var sub_branch =  document.getElementById("sub_branch").value;
			var account_name =  document.getElementById("account_name").value;
			var bank_account =  document.getElementById("bank_account").value;
 						
 						
 			if(loginname==""){
 				document.getElementById("account_name_mess").innerHTML="<font color='red'><s:text name='account_error1'/></font>";
   				document.getElementById("accountName").focus();
   				return false;
   			}else if(!formatLoginName.test(loginname)){
   				document.getElementById("account_name_mess").innerHTML="<font color='red'><s:text name='account_error2'/></font>";
   				document.getElementById("accountName").focus();
   				return false;
   			}else if(loginname.length<3)
   			{
   				document.getElementById("account_name_mess").innerHTML="<font color='red'><s:text name='account_error3'/></font>";
   				document.getElementById("accountName").focus();
   				return false;
   			}else if(loginname.length>15)
   			{
   				document.getElementById("account_name_mess").innerHTML="<font color='red'><s:text name='account_error4'/></font>";
   				document.getElementById("accountName").focus();
   				return false;
   			}
   			document.getElementById("account_name_mess").innerHTML="";
 			
   			if(pwd==""){
   				document.getElementById("account_password_mess").innerHTML="<font color='red'><s:text name='password_error1'/></font>";
   				document.getElementById("password").focus();
				return false;   			
   			}else if(!formatPwd.test(pwd)){
   				document.getElementById("account_password_mess").innerHTML="<font color='red'><s:text name='password_error2'/></font>";
   				document.getElementById("password").focus();
   				return false;
   			}else if(pwd.length<6){
   				document.getElementById("account_password_mess").innerHTML="<font color='red'><s:text name='password_error3'/></font>";
   				document.getElementById("password").focus();
   				return false;
   			}else if(pwd.length>15){
   				document.getElementById("account_password_mess").innerHTML="<font color='red'><s:text name='password_error4'/></font>";
   				document.getElementById("password").focus();
   				return false;
   			}
   			document.getElementById("account_password_mess").innerHTML="";
   			
   			if(pwd!=repwd){
   				document.getElementById("account_password2_mess").innerHTML="<font color='red'><s:text name='password_error5'/></font>";
   				document.getElementById("password2").focus();
   				return false;
   			}
   			document.getElementById("account_password2_mess").innerHTML="";
   			
   			if(coEnName==""){
   				document.getElementById("en_name_mess").innerHTML="<font color='red'><s:text name='coEnName_error'/></font>";
   				document.getElementById("coEnName").focus();
   				return false;
   			}
   			document.getElementById("account_password2_mess").innerHTML="";
   			
   			
   			if(postcode!=null&&postcode!=""&&!formatPost.test(postcode)){
   				document.getElementById("post_mess").innerHTML="<font color='red'><s:text name='post_error'/></font>";
   				document.getElementById("post").focus();
   				return false; 
   			}
   			document.getElementById("post_mess").innerHTML="";
   			if(onePersonPhone!=null&&onePersonPhone!=""&&!num_reg.test(onePersonPhone)){
   				document.getElementById("onePersonName_mess").innerHTML="<font color='red'><s:text name='tel_error2'/></font>";
   				document.getElementById("onePersonPhone").focus();
   				return false; 
   			}
   			if(twoPersonPhone!=null&&twoPersonPhone!=""&&!num_reg.test(twoPersonPhone)){
   				document.getElementById("onePersonName_mess").innerHTML="<font color='red'><s:text name='tel_error2'/></font>";
   				document.getElementById("twoPersonPhone").focus();
   				return false; 
   			}
   			document.getElementById("onePersonName_mess").innerHTML="";
   			
   			if(linkman==""){
   				document.getElementById("onePersonName_mess").innerHTML="<font color='red'><s:text name='PersonName_error1'/></font>";
   				document.getElementById("onePersonName").focus();
   				return false;
   			}else if(linkman!=""&&(linkman.length>30)){
   				document.getElementById("onePersonName_mess").innerHTML="<font color='red'><s:text name='PersonName_error2'/></font>";
   				document.getElementById("onePersonName").focus();
   				return false;
   			}
   			document.getElementById("onePersonName_mess").innerHTML="";
   			
   			if(registerEmail==""){
   				document.getElementById("register_email_mess").innerHTML="<font color='red'><s:text name='email_error1'/></font>";
   				document.getElementById("registerEmail").focus();
   				return false;
   			}else if(!formatEmail.test(registerEmail)){
   				document.getElementById("register_email_mess").innerHTML="<font color='red'><s:text name='email_error2'/></font>";
   				document.getElementById("registerEmail").focus();
   				return false;
   			}else if(registerEmail.length>50){
   				document.getElementById("register_email_mess").innerHTML="<font color='red'><s:text name='email_error3'/></font>";
   				document.getElementById("registerEmail").focus();
   				return false;
   			}
   			document.getElementById("register_email_mess").innerHTML="";
   			
   			
   			if(desc!=""&&desc.length>500){
   				
   				document.getElementById("des_mess").innerHTML="<font color='red'><s:text name='remark_error'/></font>";
   				document.getElementById("des").focus();
   				return false;
   			}
   			
   			
   			document.getElementById("des_mess").innerHTML="";
        
   			document.getElementById("addClientForm").submit();
        }
        
        function changeBank() {
       	
    	    var bank1 =  document.getElementById("bank1").value;
    	   
        	if(bank1 == '其他') {
        		document.getElementById("bank2").disabled = false;  
        		document.getElementById("bank2").focus();  
        		document.getElementById("span1").innerHTML="<font color='blue'><s:text name='请填写'/></font>";   		
        	}else {
        		document.getElementById("bank2").value = "";  
        		document.getElementById("bank2").disabled = true;   
        		document.getElementById("span1").innerHTML=""; 
        	}
        }
        
        function changeAddressDiv(){
        	if(document.getElementById("radio2").checked){
        		document.getElementById("addressdiv1").style.display = "none";
        		document.getElementById("addressdiv2").style.display = "block";
        		//document.all.addressdiv1.style.display="none";
        		//document.all.addressdiv2.style.display="block";
        		document.getElementById("nationId1").Value="";
        		document.getElementById("address1").Value="";
        		document.getElementById("proId").Value="";
        		document.getElementById("cityId").Value="";
        	}
        	else if(document.getElementById("radio1").checked){
        		document.getElementById("addressdiv1").style.display = "block";
        		document.getElementById("addressdiv2").style.display = "none";
        		//document.all.addressdiv1.style.display="block";
        		//document.all.addressdiv2.style.display="none";
        		document.getElementById("nationId2").Value="";
        		document.getElementById("address2").Value="";
        	}
        }
        
        function _onload(){
        	var addmsg = "${addusermsg }";
        	if(addmsg!=""&&addmsg=="userexist"){
        		document.getElementById("account_name_mess").innerHTML="该登录账号已存在，请重新填写！";
        	}
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
						<img src="${pageContext.request.contextPath}/images/logo_bt1.jpg"
							width="228" height="32" border="0" usemap="#Map" />
					</td>
				</tr>
			</table>
			<map name="Map" id="Map">
				<area shape="rect" coords="19,8,102,30" href="getInitData.action?request_locale=zh_CN" target="_top" alt="中文" />
  				<area shape="rect" coords="113,8,190,31" href="getInitData.action?request_locale=en_US" target="_top" alt="英文" />
				
			</map>
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
									<s:text name="用户注册"/>
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
												<table width="750" align="right" cellspacing="5">
												<s:form theme="simple" method="post" action="saveClient"
													id="addClientForm" name="addClientForm">
													<tr>
														<td height="25" colspan="3" align="left"
															style="border-bottom: dotted 1px #8EC0E8;">
															<span style="color: #FF0000;">*</span> <s:text name="号为必填项"/>
															<font color="red">${message }</font>
														</td>
													</tr>
													<tr>
														<td width="85" height="20" align="left">
															<s:text name="用户名"/>
														</td>
														<td width="381" align="left">
															<label>
																<input id="accountName" name="account.accountName" type="text" 
																	size="35" />
															</label>
																<input class="button" type="button" value="<s:text name='检查是否被使用'/>" name="checkName" id="checkName" onclick="checkAjax('accountName','account_name_mess','getAccountByName.action');"/>
														</td>
														<td width="250" align="left" class="mainred">
															<span class="mainred">
																<div id="account_name_mess">
																	<span style="color: #FF0000;">*</span>
																	<s:text name="用户名提示"/>
																</div>
															</span>
														</td>
													</tr>
													<tr>
														<td height="20" align="left">
															<s:text name="密码"/>
														</td>
														<td align="left">
															<input id="password" name="account.password" type="password" class="input"
																size="55" />
														</td>
														<td align="left" class="mainred">
															<span class="mainred">
																<div id="account_password_mess">
																	<span style="color: #FF0000;">*</span> <s:text name="密码提示"/>
																</div>
															</span>
														</td>
													</tr>
													<tr>
														<td height="20" align="left">
															<s:text name="重复密码"/>
														</td>
														<td align="left">
															<input id="password2" name="password2" type="password" class="input"
																size="55" />
														</td>
														<td align="left" class="mainred">
															<span class="mainred">
																<div id="account_password2_mess">
																	<span style="color: #FF0000;">*</span> <s:text name="密码提示"/>
																</div>
															</span>
														</td>
													</tr>
													<tr>
														<td height="20" align="left">
															<s:text name="公司名称"/>
														</td>
														<td align="left">
															<s:text name="中文"/>
															<input id="coCnName" name="client.coCnName" type="text" style="width: 325px;" />
														</td>
														<td align="left">
															<span class="mainred">
																<div id="cn_name_mess">
																</div>
															</span>
														</td>
													</tr>
													<tr>
														<td height="20" align="left">
															&nbsp;
														</td>
														<td align="left">
															<s:text name="英文"/>
															<input id="coEnName" name="client.coEnName" type="text" style="width: 325px;" />
														</td>
														<td align="left">
															<span class="mainred">
																<div id="en_name_mess">
																	<span style="color: #FF0000;">*</span> 
																</div>
															</span>
														</td>
													</tr>
													<tr>
														<td height="20" align="left">
															<s:text name="工商注册号"/>
														</td>
														<td align="left">
															<input id="businessLicense" name="client.businessLicense" type="text" class="input" />
														</td>
														<td align="left">
															<span class="mainred">
																<div id="business_license_mess">
																	<span style="color: #FF0000;"></span>
																</div>
															</span>
														</td>
													</tr>
													<tr>
														<td height="20" align="left">
															<s:text name="企业性质"/>
														</td>
														<td align="left">
															<label>
																<select id="coProperty" name="client.coProperty" class="select" >
																	<option selected="selected" value="">
																		<s:text name="请选择"/>
																	</option>
																	<option value="国有">
																		<s:text name="国有"/>
																	</option>
																	<option value="合作">
																		<s:text name="合作"/>
																	</option>
																	<option value="合资">
																		<s:text name="合资"/>
																	</option>
																	<option value="外商独资">
																		<s:text name="外商独资"/>
																	</option>
																	<option value="集体">
																		<s:text name="集体"/>
																	</option>
																	<option value="私营">
																		<s:text name="私营"/>
																	</option>
																</select>
															</label>
														</td>
														<td align="left">
															<span class="mainred">
																<div id="co_property_mess">
																	<span style="color: #FF0000;"></span>
																</div>
															</span>
														</td>
													</tr>
													<tr>
														<td height="20" align="left">
															<s:text name="法人代表"/>
														</td>
														<td align="left">
															<input id="legalPerson" name="client.legalPerson" type="text" class="input" />
														</td>
														<td align="left">
															<span class="mainred">
																<div id="legal_person_mess">
																	<span style="color: #FF0000;"></span>
																</div>
															</span>
														</td>
													</tr>
													<!-- <tr>
														<td height="20" align="left">
															<s:text name="联系电话"/>
														</td>
														<td align="left">
															<input id="tel" name="client.tel" type="text" class="input" size="55" />
														</td>
														<td align="left">
															<span class="mainred">
																<div id="tel_mess">
																	<span style="color: #FF0000;">*</span>
																</div>
															</span>
														</td>
													</tr>
													<tr>
														<td height="20" align="left">
															<s:text name="电子邮件"/>
														</td>
														<td align="left">
															<input id="email" name="client.email" type="text" class="input" size="55" />
														</td>
														<td align="left">
															<span class="mainred">
																<div id="email_mess">
																	<span style="color: #FF0000;">*</span>
																</div>
															</span>
														</td>
													</tr>  -->
													<tr>
														<td height="20" align="left">
															<s:text name="公司地址"/>
														</td>
														<td align="left">
															<table>
																<tr>
																	<td><div><input type="radio"" name="changeAddress" id="radio1" value="1" checked="checked" onclick="changeAddressDiv()" /></div></td>
																	<td><input type="hidden" name="nationId1" id="nationId1" value="37" /><s:text name="中国"/> &nbsp;</td>
																	<td><div><input type="radio"" name="changeAddress" id="radio2" value="2" onclick="changeAddressDiv()"/></div></td>
																	<td><s:text name="其它国家"/></td>
																</tr>
															</table>
															<div id="addressdiv1" style="display: block">
																<table>
																	<tr>
																		<td>
																		<s:select list="#request.promaryList" name="client.proid"
																		id="proId" listKey="PROID" listValue="%{getText(PRONAME)}"
																		headerKey="0" headerValue="-%{getText('请选择')}-"
																		onchange="javascript:ajaxselect('getCity.action','proId','citydiv');"></s:select>　<s:text name="Province"/>
																		</td>
																		<td>
																		<div id="citydiv">
																			<select name="client.cityid" id="cityId">
																				<option value="">-<s:text name="请选择"/>-</option>
																			</select>　<s:text name="City"/>
																		</div>
																		</td>
																	</tr>
																	<tr>
																		<td colspan="2"><s:text name="详细地址"/>　<input type="text" size="53" name="address1" id="address1"/></td>
																	</tr>
																</table>
															</div>
															<div id="addressdiv2" style="display: none">
																<table>
																	<tr>
																		<td>
																			<s:if test="#session.WW_TRANS_I18N_LOCALE!=null && #session.WW_TRANS_I18N_LOCALE.toString()=='en_US'">
																				<s:select list="#request.nationList" name="nationId2"
																				id="nationId2" listKey="NATIONID" listValue="NATIONENNAME"
																				headerKey="0" headerValue="-%{getText('请选择')}-"
																				></s:select>
																			</s:if>
																			<s:else>
																				<s:select list="#request.nationList" name="nationId2"
																				id="nationId2" listKey="NATIONID" listValue="NATIONCNNAME"
																				headerKey="0" headerValue="-%{getText('请选择')}-"
																				></s:select>
																			</s:else>
																		<s:text name="国"/>
																		</td>
																	</tr>
																	<tr>
																		<td>
																			<s:text name="详细地址"/>　<input type="text" size="53" name="address2" id="address2"/>
																		</td>
																	</tr>
																</table>
															</div>
														</td>
														<td align="left">
															<span class="mainred">
																<div id="address_mess">
																	<span style="color: #FF0000;">*</span>
																</div>
															</span>
														</td>
													</tr>
													<tr>
														<td height="20" align="left">
															<s:text name="邮编"/>
														</td>
														<td align="left">
															<input id="post" name="client.post" type="text" class="input" size="55" />
														</td>
														<td align="left">
															<span class="mainred">
																<div id="post_mess">
																</div>
															</span>
														</td>
													</tr>
													<tr>
														<td height="20" align="left">
															<s:text name="联系人"/>1
														</td>
														<td align="left">
															<table width="97%" border="0" cellpadding="0"
																cellspacing="0">
																<tr>
																	<td width="10%" align="center">
																		<s:text name="姓名"/>
																	</td>
																	<td width="20%">
																		<input id="onePersonName" name="client.onePersonName" type="text"
																			style="width: 80px;" />
																	</td>
																	<td width="10%" align="center">
																		<s:text name="电话"/>
																	</td>
																	<td width="20%">
																		<input id="onePersonTel" name="client.onePersonTel" type="text"
																			style="width: 85px;" />
																	</td>
																	<td width="10%" align="center">
																		<s:text name="手机"/>
																	</td>
																	<td>
																		<input id="onePersonPhone" name="client.onePersonPhone" type="text"
																			style="width: 85px;" />
																	</td>
																</tr>
																<!--  <tr>
																	<td align="center">
																		<s:text name="传真"/>
																	</td>
																	<td>
																		<input id="onePersonFax" name="client.onePersonFax" type="text"
																			style="width: 80px;" />
																	</td>
																	<td align="center">
																		Email
																	</td>
																	<td  colspan="3">
																		<input id="onePersonEmail" name="client.onePersonEmail" type="text"
																			style="width: 188px;" />
																	</td>
																</tr>  -->
															</table>
														</td>
														  <td align="left">
															<span class="mainred">
																<div id="onePersonName_mess">
																	<span style="color: #FF0000;">*</span>
																</div>
															</span>
														</td>
													</tr>  
													<tr>
														<td height="20" align="left">
															<s:text name="联系人"/>2
														</td>
														<td align="left">
															<table width="97%" border="0" cellpadding="0"
																cellspacing="0">
																<tr>
																	<td width="10%" align="center">
																		<s:text name="姓名"/>
																	</td>
																	<td width="20%">
																		<input id="twoPersonName" name="client.twoPersonName" type="text"
																			style="width: 80px;" />
																	</td>
																	<td width="10%" align="center">
																		<s:text name="电话"/>
																	</td>
																	<td width="20%">
																		<input id="twoPersonTel" name="client.twoPersonTel" type="text"
																			style="width: 85px;" />
																	</td>
																	<td width="10%" align="center">
																		<s:text name="手机"/>
																	</td>
																	<td>
																		<input id="twoPersonPhone" name="client.twoPersonPhone" type="text"
																			style="width: 85px;" />
																	</td>
																</tr>
																<!-- <tr>
																	<td align="center">
																		<s:text name="传真"/>
																	</td>
																	<td>
																		<input id="twoPersonFax" name="client.twoPersonFax" type="text"
																			style="width: 80px;" />
																	</td>
																	<td align="center">
																		Email
																	</td>
																	<td  colspan="3">
																		<input id="twoPersonEmail" name="client.twoPersonEmail" type="text"
																			style="width: 188px;" />
																	</td>
																</tr>   -->
															</table>
														</td>
														<td align="left">
															&nbsp;
														</td>
													</tr>
													<tr>
														<td height="20" align="left">
															<s:text name="注册激活邮箱"/>
														</td>
														<td align="left">
															<input id="registerEmail" name="client.registerEmail" type="text" class="input" size="55" />
														</td>
														<td align="left">
															<span class="mainred">
																<div id="register_email_mess">
																	<span style="color: #FF0000;">*</span> <s:text name="注册邮箱提示"/>
																</div>
															</span>
														</td>
													</tr>
													<tr>
														<td height="20" align="left">
															<s:text name="开户银行"/>
														</td>
														<td align="left">
														<s:if test="#session.WW_TRANS_I18N_LOCALE!=null && #session.WW_TRANS_I18N_LOCALE.toString()=='en_US'">
																<select id="bank1" name="bank1" onchange="javascript:changeBank();">
  																<option value ="a" selected="selected"><s:text name="请选择"/></option>
  																<option value ="Bank of China, BOC"><s:text name="中国银行" /></option>
  																<option value ="Industrial and Commercial Bank of China Limited, ICBC"><s:text name="中国工商银行" /></option>
 																<option value="The agriculture Bank of China, ABC"><s:text name="中国农业银行" /></option>
 																<option value="China Construction Bank, CCB"><s:text name="中国建设银行" /></option>
 																<option value="Communications Bank of China"><s:text name="中国交通银行" /></option>
 																<option value="Everbright Bank of China"><s:text name="中国光大银行" /></option>
 																<option value="CITIC Industrial Bank"><s:text name="中信实业银行" /></option>
 																<option value="China Merchants Bank"><s:text name="招商银行" /></option>
 																<option value="其他"><s:text name="其他" /></option>
 																</select>
																
																
														</s:if>
														<s:else>
																<select id="bank1" name="bank1" onchange="javascript:changeBank();">
  																<option value =" " selected="selected"><s:text name="请选择"/></option>
  																<option value ="中国银行"><s:text name="中国银行" /></option>
  																<option value ="中国工商银行"><s:text name="中国工商银行" /></option>
 																<option value="中国农业银行"><s:text name="中国农业银行" /></option>
 																<option value="中国建设银行"><s:text name="中国建设银行" /></option>
 																<option value="中国交通银行"><s:text name="中国交通银行" /></option>
 																<option value="中国光大银行"><s:text name="中国光大银行" /></option>
 																<option value="中信实业银行"><s:text name="中信实业银行" /></option>
 																<option value="招商银行"><s:text name="招商银行" /></option>
 																<option value="其他"><s:text name="其他" /></option>
															</select>
														</s:else>
															<input type="text" id="bank2" name="bank2" size="20" maxlength="30" size="45" disabled="disabled" />
															<span id="span1"></span>
														</td>
														
													</tr>
													<tr>
														<td height="20" align="left">
															<s:text name="分行"/>
														</td>
														<td align="left">
															<input type="text" id="branch" name="client.branch"  class="input" size="55"  />
														</td>
														
													</tr>
													<tr>
														<td height="20" align="left">
															<s:text name="支行" />
														</td>
														<td align="left">
															<input type="text" id="sub_branch" name="client.subBranch"  class="input" size="55"  />
														</td>
														
													</tr>
													<tr>
														<td height="20" align="left">
															<s:text name="账户名"/>
														</td>
														<td align="left">
															<input id="account_name" name="client.accountName" type="text" class="input" size="55" />
														</td>
														
													</tr>
													<tr>
														<td height="20" align="left">
															<s:text name="银行账号"/>
														</td>
														<td align="left">
															<input id="bank_account" name="client.bankaccount" type="text" class="input" size="55" />
														</td>
														
													</tr>
													<tr>
														<td height="20" align="left">
															<s:text name="发票抬头"/>
														</td>
														<td align="left">
															<input id="vbrk" name="client.vbrk" type="text" class="input" size="55" />
														</td>
														<td align="left">
															<div id="vbrk_mess">
															</div>
														</td>
													</tr>
													<tr>
														<td height="20" align="left">
															<s:text name="备注"/>
														</td>
														<td align="left">
															<input id="des" name="client.des" type="text" class="input" size="55" />
														</td>
														<td align="left">
															<div id="des_mess">
															</div>
														</td>
													</tr>
													<tr>
														<td height="38" align="left"></td>
														<td height="38" align="left">
															<input name="registbutton" class="button" type="button" value="<s:text name="注册"/>" onclick="javascript:checkAdd();" />

															<input name="backbutton" class="button" type="button" onclick="javascript:window.location='${pageContext.request.contextPath}/index.jsp'" value="<s:text name='取消'/>" />
														</td>
														<td align="left">
															&nbsp;
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
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>
