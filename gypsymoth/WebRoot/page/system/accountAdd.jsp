<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统——添加用户</title>
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
   			*校验用户名
   			*/
        	var accountName = document.getElementById("accountName").value;
 			var formatName = /^[a-zA-Z0-9_]+$/;
 			if(accountName==""){
   				document.getElementById("account_name_mess").innerHTML="用户不能为空！";
   				return false;
   			}else if(!formatName.test(accountName)){
   				document.getElementById("account_name_mess").innerHTML="用户名称只能是、字母、数字或下划线!";
   				return false;
   			}else if(accountName.length<3)
   			{
   				document.getElementById("account_name_mess").innerHTML="用户不能少于3个字符！";
   				return false;
   			}else if(accountName.length>15)
   			{
   				document.getElementById("account_name_mess").innerHTML="用户不能多于15个字符！";
   				return false;
   			}
   			document.getElementById("account_name_mess").innerHTML="";
        
        	/**
   			*校验密码
   			*/
        	var password = document.getElementById("password").value;
 			if(password==""){
   				document.getElementById("account_password_mess").innerHTML="密码不能为空！";
   				return false;
   			}else if(password.length<6)
   			{
   				document.getElementById("account_password_mess").innerHTML="密码不能少于6个字符！";
   				return false;
   			}else if(password.length>15)
   			{
   				document.getElementById("account_password_mess").innerHTML="密码不能多于15个字符！";
   				return false;
   			}
   			document.getElementById("account_password_mess").innerHTML="";
   			
   			/**
   			*校验人员
   			*/
   			var personName = document.getElementById("personName").value;
   			if(personName==""){
   				document.getElementById("person_name_mess").innerHTML="人员不能为空！";
   				return false;
   			}
   			document.getElementById("person_name_mess").innerHTML="";
   			
   			/**
   			*校验角色
   			*/
   			var roleHas = document.forms['addAccountForm'].roleHas.options[0].value;
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
   				for(var j=0; j<document.forms['addAccountForm'].roleHas.length; j++)
				{
					if(document.forms['addAccountForm'].roleHas.options[j].value==3){
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
   				for(var j=0; j<document.forms['addAccountForm'].roleHas.length; j++)
				{
					if(document.forms['addAccountForm'].roleHas.options[j].value==4){
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
   				for(var j=0; j<document.forms['addAccountForm'].roleHas.length; j++)
				{
					if(document.forms['addAccountForm'].roleHas.options[j].value==2){
						tmp3=1;
					}
				}
				if(tmp3==0){
					document.getElementById("account_role_mess").innerHTML="该人员为检查员，检查员角色必选！";
   					return false;
				}
   			}
   			document.getElementById("account_role_mess").innerHTML="";
   			
   			/**
   			*选中角色值
   			*/
        	for(var j=0; j<document.forms['addAccountForm'].roleHas.length; j++)
			{
				document.forms['addAccountForm'].roleHas.options[j].selected=true;
			}
			
			/**
   			*提交表单
   			*/
   			if(confirm("您是否确认添加?")){
   				document.getElementById("addAccountForm").submit();
   			}
        }
		//div层锁定
		  function locking(divtype)
		  {   
		  	  var div = divtype;
		  	  tmpDiv = document.getElementById("ly");
		  	  uploadDiv = document.getElementById("divUpload");
			  tmpDiv.style.display="block";   
			  tmpDiv.style.width=document.body.clientWidth;   
			  tmpDiv.style.height=document.body.clientHeight;   
			  if(div=="divUpload"){
			  	uploadDiv.style.display='block';   
			  }
		  }   
		  //div层解锁
		  function Lock_CheckForm()
		  {   
		  	  tmpDiv = document.getElementById("ly");
		  	  uploadDiv = document.getElementById("divUpload");
			  tmpDiv.style.display='none';
			  uploadDiv.style.display='none'; 
			  return false;   
		  }
		  function show(name,noid,isc,isa,isi)
		  {
		  	document.addAccountForm.personName.value = name;
		  	document.addAccountForm.personId.value = noid;
		  	document.addAccountForm.isc.value = isc;//协调员
		  	document.addAccountForm.isa.value = isa;//授权签字人
		  	document.addAccountForm.isi.value = isi;//检查员
		  	 Lock_CheckForm();
		  }
    </script>
	<body>
		<div id="ly" style="position: absolute; top: 0px; filter: alpha(opacity=60); background-color: #E2DBCF; 
	        z-index: 2; left: 0px; display: none;"> 
	    </div>
	    <!--浮层框架开始--> 
	    <div id="divUpload" align="center" style="position: absolute; z-index: 3; left: expression((document.body.offsetWidth-0)/2); top: expression((document.body.offsetHeight-500)/2); 
	        background-color: #fff; display: none;" > 
	        <table  border="1"  cellpadding="6"  cellspacing="0" bordercolor="#E0A56B">
				<tr>
					<td bgcolor="#E0A56B" align="right" ><a href="#" onclick="Lock_CheckForm();">[ 关闭 ]</a></td>
				</tr>
				<tr>
					<td>
						<table width="200" border="0" align="center" cellpadding="0" cellspacing="0">
							<tr>
						    	<td height="10">&nbsp;</td>
							</tr>
					    	<tr>
						    	<td><%@ include file="../include/orgPersonTree.jsp"%></td>
							</tr>
					    	<tr>
						    	<td>
						    		<table width="80%" border="0" align="center" cellpadding="6" cellspacing="0">
						      			<tr>
						        			<td colspan="3" align="center">
								  				<input name="qx" class="button" type="button" value="取 消" onclick="Lock_CheckForm();"/>
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
	    <!--浮层框架结束-->
		
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
					                <s:form theme="simple" method="post" action="saveAccount"
									id="addAccountForm" name="addAccountForm">
									<input type="hidden" name="tempRight" id="tempRight" />
					                  <tr>
									    <td height="25" colspan="3" align="left"
												style="border-bottom: dotted 1px #8EC0E8;">
												<span style="color: #FF0000;">*</span>号为必填项
												<font color="red">${message }</font>
									    </td>
									  </tr>
					                  <tr>
					                    <td width="68" height="20">用 户 名</td>
					                    <td width="332"><label>
					                      <input id="accountName" name="account.accountName" type="text" class="input" size="24" 
					                      	onblur="char_valid('accountName','account_name_mess',3,15,'用户名',2);" />
					                      <input class="button" type="button" value="检查是否被使用" name="checkName" id="checkName" onclick="checkAjax('accountName','account_name_mess','../register/getAccountByName.action');"/>
					                    </label></td>
					                    <td width="272">
					                    	<span class="mainred">
													<div id="account_name_mess">
															<span style="color: #FF0000;">*</span>
															用户名长度为3-15，且只能是、字母、数字或下划线
													</div>
											</span>
					                    </td>
					                  </tr>
					                  <tr>
					                    <td width="68" height="20">初始密码</td>
					                    <td width="332"><label>
					                      <input id="password" name="account.password" type="text" class="input" size="55" value="111111" onblur="ncl_valid('password','account_password_mess',6,15,'密码',2)"/>
					                    </label></td>
					                    <td width="272">
					                    	<span class="mainred">
													<div id="account_password_mess">
														<span style="color: #FF0000;">*</span> 6到15位数字和字母的组合，字母区分大小写
													</div> 
											</span>
					                    </td>
					                  </tr>
					                  <tr>
					                    <td height="20">状　　态</td>
					                    <td><label>
					                      <input name="account.accountStatus" type="radio" id="roleStatus1" value="1" checked="checked" />
					                      激活</label><label>
					                      <input type="radio" name="account.accountStatus" id="roleStatus2" value="2" />
					                      停用</label></td>
					                    <td><span class="mainred">*</span></td>
					                  </tr>
					                  <tr>
					                    <td height="20">用户类型</td>
					                    <td><label>
					                      <input name="account.accountType" type="radio" id="roleType1" value="1" checked="checked" />
					                      人员
					                      </label></td>
					                    <td><span class="mainred">*</span></td>
					                  </tr>
					                  <tr>
					                    <td width="68" height="20">人　　员</td>
					                    <td width="332"><label>
					                      <input type="hidden"" id="personId" name="personId" />
					                      <input type="hidden"" id="isc" name="isc" />
					                      <input type="hidden"" id="isa" name="isa" />
					                      <input type="hidden"" id="isi" name="isi" />
					                      <input id="personName" name="personName" type="text" class="input" size="20" disabled="disabled" />
					                      <input name="zw" class="button" type="button" value="选择人员" onclick="locking('divUpload')" />
					                    </label>
										</td>
					                    <td width="272">
					                    	<span class="mainred">
													<div id="person_name_mess">
														*
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
																	<s:if test="#request.roleNotHas.size>0">
																		<s:iterator value="#request.roleNotHas">
																			<option value="<s:property value="roleId"/>">
																				<s:property value="roleName" />
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
																	onclick="moveSingleRole(document.forms['addAccountForm'].roleNotHas,document.forms['addAccountForm'].roleHas)" />
																<br />
																<input type="button" value=">>" class="button"
																	onclick="moveAllRole(document.forms['addAccountForm'].roleNotHas,document.forms['addAccountForm'].roleHas)" />
																<br />
																<input type="button" value=" < " class="button"
																	onclick="moveSingleRole(document.forms['addAccountForm'].roleHas,document.forms['addAccountForm'].roleNotHas)" />
																<br />
																<input type="button" value="<<" class="button"
																	onclick="moveAllRole(document.forms['addAccountForm'].roleHas,document.forms['addAccountForm'].roleNotHas)" />
															</td>
															<td>
																<select name="roleHas" size="12" id="roleHas"
																	multiple="multiple">
																	<s:if test="#request.roleHas.size>0">
																		<s:iterator value="#request.roleHas">
																			<option value="<s:property value="roleId"/>">
																				<s:property value="roleName" />
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
					                    	<input name="" class="button" type="button" value="添 加"
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
