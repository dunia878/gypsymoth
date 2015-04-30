<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统</title>
		<%@ include file="../include/head.jsp"%>

		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/ajax.js"></script>
		<script language="javascript" type="text/javascript"
			src="${pageContext.request.contextPath}/js/jsFrame.js"></script>
		<script language="JavaScript" type="text/javascript">


  function changeAddressDiv(){
        	if(document.getElementById("radio2").checked){
        		//document.all.addressdiv1.style.display="none";
        		//document.all.addressdiv2.style.display="block";
        		document.getElementById("addressdiv1").style.display = "none";
        		document.getElementById("addressdiv2").style.display = "block";
        		document.getElementById("nationId1").Value="";
        		document.getElementById("proId").Value="";
        		document.getElementById("cityId").Value="";
        	}
        	else if(document.getElementById("radio1").checked){
        		//document.all.addressdiv1.style.display="block";
        		//document.all.addressdiv2.style.display="none";
        		document.getElementById("addressdiv1").style.display = "block";
        		document.getElementById("addressdiv2").style.display = "none";
        		document.getElementById("nationId2").Value="";
        	}
        }
        
        
 function checkAdd(){
   			var coEnName = document.getElementById("coEnName").value;
   			if(coEnName==""){
   				document.getElementById("en_name_mess").innerHTML="<font color='red'><s:text name='coEnName_error'/></font>";
   				document.getElementById("coEnName").focus();
   				return false;
   			}
   			
   			
   			var email = document.getElementById("email").value;
   			var formatEmail = /^\s*([\w-]+(\.\w+)*@([\w-]+\.)+\w+)\s*$/; //email正则表达式
   			if(email==""){
   				document.getElementById("email_mess").innerHTML="<font color='red'><s:text name='email_error1'/></font>";
   				document.getElementById("email").focus();
   				return false;
   			}else if(!formatEmail.test(email)){
   				document.getElementById("email_mess").innerHTML="<font color='red'><s:text name='email_error2'/></font>";
   				document.getElementById("email").focus();
   				return false;
   			}else if(email.length>50){
   				document.getElementById("email_mess").innerHTML="<font color='red'><s:text name='email_error3'/></font>";
   				document.getElementById("email").focus();
   				return false;
   			}
   			document.getElementById("email_mess").innerHTML="";
   			
   			
   			var post = $("#post").val();
     		var num_reg = /^[0-9]+$/; 
   			if(post!=null&&post!=""&&!num_reg.test(post)){
	     		$("#post").val("");
	     		alert("邮政编码必须输入数字"); 
	     		document.getElementById("post").focus();
	     		return false;
     		}
     		var onePersonPhone = $("#onePersonPhone").val();
     		if(onePersonPhone!=null&&onePersonPhone!=""&&!num_reg.test(onePersonPhone)){
	     		$("#onePersonPhone").val("");
	     		alert("手机必须输入数字"); 
	     		document.getElementById("onePersonPhone").focus();
	     		return false;
     		} 
   			
   			var linkman = document.getElementById("onePersonName").value;
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
   			
   			var bank1 =  document.getElementById("bank1").value;
			var bank2 =  document.getElementById("bank2").value;
			var branch =  document.getElementById("branch").value;
			var sub_branch =  document.getElementById("sub_branch").value;
			var account_name =  document.getElementById("account_name").value;
			var bank_account =  document.getElementById("bank_account").value;
   			
   			if(confirm("您确认添加?")){
	       		 document.getElementById("addClient").submit();
	        }
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
						<table align="center" cellpadding="12" class="tabedit">
							<tr>
								<td height="300" align="left" valign="top">
									<!--编辑开始-->
									<table width="600" align="center" cellspacing="5">
										<s:form theme="simple" method="post" action="saveClients"
											id="addClient" name="addClient">
																						<tr>
												<td height="25" colspan="3" align="left"
													style="border-bottom: dotted 1px #8EC0E8;">
													<span style="color: #FF0000;">*</span> <s:text name="号为必填项"/> 
													<font color="red">${message }</font>
												</td>
											</tr>
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
												<td width="80" height="20" align="left">
															<s:text name="公司名称" />
												</td>
												<td width="390" align="left">
													<s:text name="中文" />
													<input id="coCnName" name="client.coCnName" type="text"
														style="width: 273px;" class="input" />
												</td>
												<td width="130"></td>
											</tr>
											<tr>
												<td height="20" align="left">
													&nbsp;
												</td>
												<td align="left">
													<s:text name="英文" />
													<input id="coEnName" name="client.coEnName" type="text"
														style="width: 273px;" class="input" />
												</td>
												<td align="left">
													<span class="mainred">
														<div id="en_name_mess">
															<span style="color: #FF0000;">*</span>
														</div> </span>
												</td>
											</tr>
											<tr>
												<td height="20" align="left">
													<s:text name="工商注册号" />
												</td>
												<td align="left">
													<input id="businessLicense" name="client.businessLicense"
														type="text" class="input" size="55" />
												</td>
											</tr>
											<tr>
												<td height="20" align="left">
													<s:text name="企业性质" />
												</td>
												<td align="left">
													<label>
														<select id="coProperty" name="client.coProperty"
															class="select" style="width: 80%;">
															<option selected="selected" value="">
																<s:text name="请选择" />
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
												<td></td>
											</tr>
											<tr>
												<td height="20" align="left">
												<s:text name="法人代表"/>
												</td>
												<td align="left">
													<input id="legalPerson" name="client.legalPerson"
														type="text" class="input" size="55" />
												</td>
												<td></td>
											</tr>
											 
											<tr>
												<td height="20" align="left">
												<s:text name="电子邮件"/>
												</td>
												<td align="left">
													<input id="email" name="client.email" type="text"
														class="input" size="55" />
												</td>
												<td align="left">
													<span class="mainred">
														<div id="email_mess">
															<span style="color: #FF0000;">*</span>
														</div> </span>
												</td>
											</tr>

											<tr>
												<td height="20" align="left">
														<s:text name="公司地址"/>
												</td>
												<td align="left">

													<table>
														<tr>
															<td>
																<div>
																	<input type="radio" " name="changeAddress" id="radio1"
																		value="1" checked="checked"
																		onclick="changeAddressDiv()" />
																</div>
															</td>
															<td>
																<input type="hidden" name="nationId1" id="nationId1"
																	value="37" />
																<s:text name="中国" />
																&nbsp;
															</td>
															<td>
																<div>
																	<input type="radio" " name="changeAddress" id="radio2"
																		value="2" onclick="changeAddressDiv()" />
																</div>
															</td>
															<td>
																<s:text name="其它国家" />
															</td>
														</tr>
													</table>

													<div id="addressdiv1" style="display: block">
														<table>
															<tr>
																<td>
																	<s:select list="#request.promaryList"
																		name="client.proid" id="proId" listKey="PROID"
																		listValue="%{getText(PRONAME)}" headerKey="0" headerValue="-%{getText('请选择')}-"
																		onchange="javascript:ajaxselect('getCitys.action','proId','citydiv');"></s:select>
																<s:text name="Province"/>
																</td>
																<td>
																	<div id="citydiv">
																		<select name="client.cityid" id="cityId">
																			<option value="">
																				-<s:text name="请选择"/>-
																			</option>
																		</select>
																	　<s:text name="City"/>
																	</div>
																</td>
															</tr>
														</table>
													</div>
													<div id="addressdiv2" style="display: none">
														<table>
															<tr>
																<td align="left">
																	<s:if test="#session.WW_TRANS_I18N_LOCALE!=null && #session.WW_TRANS_I18N_LOCALE.toString()=='en_US'">
																		<s:select list="#request.nationList" name="nationId2"
																		id="client.nationId" listKey="NATIONID"
																		listValue="NATION_EN_NAME" headerKey="0"
																		headerValue="-%{getText('请选择')}-"></s:select>
																	</s:if>
																	<s:else>
																		<s:select list="#request.nationList" name="nationId2"
																		id="client.nationId" listKey="NATIONID"
																		listValue="NATION_CN_NAME" headerKey="0"
																		headerValue="-%{getText('请选择')}-"></s:select>
																	</s:else>
																	<s:text name="Country"/>
																</td>
															</tr>
														</table>
													</div>
													<table>
														<tr>
															<td colspan="2">
																	<s:text name="详细地址"/>
																<input id="address" name="client.address" type="text"
																	class="input" size="44" />
															</td>
														</tr>
													</table>
												</td>
												<td align="left">
													<span class="mainred">
														<div>
															<span style="color: #FF0000;"></span>
														</div> </span>
												</td>
											</tr>




											<tr>
												<td height="20" align="left">
													<s:text name="邮编"/>
												</td>
												<td align="left">
													<input id="post" name="client.post" type="text"
														class="input" size="55" />
												</td>
												<td></td>
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
																<input id="onePersonName" name="client.onePersonName"
																	type="text" style="width: 80px;" class="input" />
															</td>
															<td width="10%" align="center">
																<s:text name="电话"/>
															</td>
															<td width="20%">
																<input id="onePersonTel" name="client.onePersonTel"
																	type="text" style="width: 85px;" class="input" />
															</td>
															<td width="10%" align="center">
																<s:text name="手机"/>
															</td>
															<td>
																<input id="onePersonPhone" name="client.onePersonPhone"
																	type="text" style="width: 85px;" class="input" />
															</td>
														</tr>
														 
													</table>
												</td>
												<td align="left">
													<span class="mainred">
														<div id="onePersonName_mess">
															<span style="color: #FF0000;">*</span>
														</div> </span>
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
																<s:text name="姓名" />
															</td>
															<td width="20%">
																<input id="twoPersonName" name="client.twoPersonName"
																	type="text" style="width: 80px;" class="input" />
															</td>
															<td width="10%" align="center">
																	<s:text name="电话"/>
															</td>
															<td width="20%">
																<input id="twoPersonTel" name="client.twoPersonTel"
																	type="text" style="width: 85px;" class="input" />
															</td>
															<td width="10%" align="center">
																	<s:text name="手机"/>
															</td>
															<td>
																<input id="twoPersonPhone" name="client.twoPersonPhone"
																	type="text" style="width: 85px;" class="input" />
															</td>
														</tr>
														 
													</table>
												</td>
											</tr>
											 
											<tr>
														<td height="20" align="left">
															<s:text name="开户银行"/>
														</td>
														<td align="left">
															<s:if test="#session.WW_TRANS_I18N_LOCALE!=null && #session.WW_TRANS_I18N_LOCALE.toString()=='en_US'">
																<select id="bank1" name="bank1" onchange="javascript:changeBank();">
  																<option value ="" selected="selected"><s:text name="请选择"/></option>
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
													<s:text name="发票抬头" />
												</td>
												<td align="left">
													<input id="vbrk" name="client.vbrk" type="text"
														class="input" size="55" />
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
													<input id="des" name="client.des" type="text" class="input"
														size="55" />
												</td>
												<td></td>
											</tr>
											<tr>
												<td height="38" align="left"></td>
												<td height="38" align="left">
													<input name="" class="button" type="button"
														onclick="checkAdd()" value="<s:text name='add'/>" />
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
