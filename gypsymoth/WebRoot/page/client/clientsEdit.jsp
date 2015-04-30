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
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/ajax.js"></script>
		<script language="javascript" type="text/javascript"
			src="${pageContext.request.contextPath}/js/jsFrame.js"></script>
		<script language="JavaScript" type="text/javascript">
	
   $("document").ready(function(){
   			var radiochecked = $("#radiochecked").val(); 
			if(radiochecked==2){ 
				$("#radio2").attr("checked","checked"); 
				document.all.addressdiv1.style.display="none"; 
        		document.all.addressdiv2.style.display="block";  
			} 
			initSelect();	
      });
			 
	function initSelect()
		{
			var proid = $("#proId").val();
			var cityid_select  = $("#cityid_select").val();
			var url = "ajaxSelect.action?promaryid="+proid;	// 为省份查询城市 
			ajax_selectContent(url,'cityid');  
			selectItem('cityid',cityid_select); 
		}
		
function changepromary()
{
	var proid = $("#proId").val(); 
	var url = "ajaxSelect.action?promaryid="+proid;	// 为省份查询城市 
	ajax_selectContent(url,'cityid');  
}

//选中ID为obj的select控件的值为value的内容 如: 'city',1 
function selectItem(obj,value)
	{
	  var objSelect = document.getElementById(obj);
	  for(var i=0;i<objSelect.options.length;i++){
         if(objSelect.options[i].value == value)
         {
         	setTimeout(function(){objSelect.options[i].selected = true;},1);
             
             isExit = true;
             break;
         } 
  		 }    
	} 


function ajax_selectContent(select_action,select_id)
{	
	var select = "#"+select_id;
	$.ajax({ 
	      url: select_action,
	      type: "get",
	      beforeSend:function(){
	      },
	      dataType: "text",  
	      success: function(data) {
	      	 $(select).html(data);
		  },
	      async:false,  
	      cache:true
	   }
	);
	 
}
		
		
function save(){

            var coEnName = document.getElementById("coEnName").value;
            
            var bank1 =  document.getElementById("bank1").value;
			var bank2 =  document.getElementById("bank2").value;
			var branch =  document.getElementById("branch").value;
			var sub_branch =  document.getElementById("sub_branch").value;
			var account_name =  document.getElementById("account_name").value;
			var bank_account =  document.getElementById("bank_account").value;
            
            
   			if(coEnName==""){
   				document.getElementById("en_name_mess").innerHTML="<font color='red'><s:text name='coEnName_error'/></font>";
   				document.getElementById("coEnName").focus();
   				return false;
   			}
   			
   			var telephone = document.getElementById("tel").value;
   			if(telephone==""){
   				document.getElementById("tel_mess").innerHTML="<font color='red'><s:text name='tel_error1'/></font>";
   				document.getElementById("tel").focus();
   				return false;
   			}
   			
   			var email = document.getElementById("email").value;
   			var formatEmail = /^\s*([\w-]+(\.\w+)*@([\w-]+\.)+\w+)\s*$/; //email正则表达式
   			if(email==""){
   				document.getElementById("email_mess").innerHTML="font color='red'><s:text name='email_error1'/></font>";
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
   			if(confirm("您是否确认保存?")){
		   			document.getElementById("onePersonName_mess").innerHTML="";
		           document.getElementById("form1").submit();
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


function showandhide(v){
  //alert(v);
  for(i=1;i<3;i++){
   document.getElementById(i).style.display = 'none';
   if(i==v){
    document.getElementById(v).style.display = 'block';
   }
  }
 }







    function changeAddressDiv(){
        	if(document.getElementById("radio2").checked){
        		document.all.addressdiv1.style.display="none";
        		document.all.addressdiv2.style.display="block";
        		document.getElementById("nationId1").Value="";
        		document.getElementById("proId").Value="";
        		document.getElementById("cityId").Value="";
        	}
        	else if(document.getElementById("radio1").checked){
        		document.all.addressdiv1.style.display="block";
        		document.all.addressdiv2.style.display="none";
        	}
        }



	    function char_valid1(id,mess_id,start_len,end_len,field_name,isNull){
			var name = getField(id);
			if(isNull == 2 && isEmpty(name.value)){
					setNoteValue(mess_id,field_name+'不可为空','red');
					return false;
			}

		}



 </script>
	</head>
	<body>
		<input type="hidden" name="radiochecked" id="radiochecked"
			value="${radiochecked }" />
		<!--内容开始-->
		<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
			<tr>
				<td class="list_r">
					&nbsp;

				</td>
				<td valign="top" class="right">
					<div class="title">
						您的当前位置： 首页 &gt;&gt; 客户管理 &gt;&gt;
						<strong>客户信息</strong>
					</div>
					<div>
						<table align="center" cellpadding="12" class="tabedit">
							<tr>
								<td height="300" align="left" valign="top">
									<!--编辑开始-->
									<table width="600" align="center" cellspacing="5">
										<s:form theme="simple" method="post" action="updataClients" id="form1"
											name="form1">
											<input type="hidden" name="client.clientId"
												id="client.clientId"
												value="<s:property value="client.clientId"/>" />

											<input type="hidden" name="client.accountId"
												id="client.accountId"
												value="<s:property value="client.accountId"/>" />
											<input type="hidden" name="cityid_select" id="cityid_select"
												value="<s:property value="client.cityid"/>" />
											<input type="hidden" name="client.parentId" id="parentId"
												value="<s:property value="client.parentId"/>" />
											<input type="hidden" name="client.identify" id="identify"
												value="<s:property value="client.identify"/>" />



											<tr>
												<td height="25" colspan="3" align="left"
													style="border-bottom: dotted 1px #8EC0E8;">
													<span style="color: #FF0000;">*</span>
													<s:text name="号为必填项" />
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
														class="input" size="49"
														value="<s:property value="client.coCnName"/>" />
												</td>
												<td align="left" width="130">
													<span class="mainred">
														<div >
															<span style="color: #FF0000;"></span>
														</div> </span>
												</td>
											</tr>
											<tr>
												<td height="20" align="left">
													&nbsp;

												</td>
												<td align="left">
													<s:text name="英文" />
													<input id="coEnName" name="client.coEnName" type="text"
														class="input" size="49"
														value="<s:property value="client.coEnName"/>"
														 />
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
														type="text" class="input" size="55"
														value="<s:property value="client.businessLicense"/>" />
												</td>
											</tr>
											<tr>
												<td height="20" align="left">
													<s:text name="企业性质" />
												</td>
												<td align="left">
													<label>
														<select name="client.coProperty" id="client.coProperty"
															class="select" id="client.coProperty" style="width: 80%;">
															<option value="<s:property value="client.coProperty"/>">
																<s:property value="client.coProperty" />
															</option>

															<option value="国有">
																<s:text name="国有" />
															</option>
															<option value="合作">
																<s:text name="合作" />
															</option>
															<option value="合资">
																<s:text name="合资" />
															</option>
															<option value="外商独资">
																<s:text name="外商独资" />
															</option>
															<option value="集体">
																<s:text name="集体" />
															</option>
															<option value="私营">
																<s:text name="私营" />
															</option>
														</select>
													</label>
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
													<s:text name="法人代表"/>
												</td>
												<td align="left">
													<input id="legalPerson" name="client.legalPerson"
														type="text" class="input" size="55"
														value="<s:property value="client.legalPerson"/>" />
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
													<s:text name="联系电话"/>
												</td>
												<td align="left">
													<input id="tel" name="client.tel" type="text" class="input"
														size="55" value="<s:property value="client.tel"/>"
														/>
												</td>
												<td align="left">
													<span class="mainred">
														<div id="tel_mess">
															<span style="color: #FF0000;">*</span>
														</div> </span>
												</td>
											</tr>
											<tr>
												<td height="20" align="left">
												<s:text name="电子邮件"/>
												</td>
												<td align="left">
													<input id="email" name="client.email" type="text"
														class="input" size="55"
														value="<s:property value="client.email"/>"
														onblur="javascript:char_valid1('email','email_mess1',3,30,'电子邮件',2);" />
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
																		listValue="%{getText(PRONAME)}" headerKey="0" headerValue="%{getText('请选择')}"
																		onchange="javascript:changepromary();"></s:select>
																	<s:text name="Province"/>
																</td>
																<td>
																	<div id="citydiv">
																		<select name="cityid" id="cityid">
																			<option value="">
																				<s:text name="请选择"/>
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
																	<s:select list="#request.nationList"
																		name="client.nationId" id="client.nationId"
																		listKey="NATIONID" listValue="NATIONNAME"
																		headerKey="0" headerValue="%{getText('请选择')}"></s:select>
																	<s:text name="Country"/>
																</td>
															</tr>
														</table>
													</div>
													<table>
														<tr>
															<td colspan="2">
															<s:text name="详细地址"/>
																<input id="client.address" name="client.address"
																	type="text" class="input" size="44"
																	value="<s:property value="client.address"/>" />
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
													<input id="client.post" name="client.post" type="text"
														class="input" size="55"
														value="<s:property value="client.post"/>" />
												</td>
											</tr>
											<tr>
												<td height="20" align="left">
													<s:text name="联系人"/>1
												</td>
												<td align="left">
													<table width="90%" border="0" cellpadding="0"
														cellspacing="0">
														<tr>
															<td width="10%" align="center">
																<s:text name="姓名"/>
															</td>
															<td width="20%">
																<input id="onePersonName" name="client.onePersonName"
																	type="text" class="input" style="width: 65px;"
																	value="<s:property value="client.onePersonName"/>"
																 />
															</td>
															<td width="10%" align="center">
																	<s:text name="电话"/>
															</td>
															<td>
																<input id="onePersonTel" name="client.onePersonTel"
																	type="text" class="input" style="width: 85px;"
																	value="<s:property value="client.onePersonTel"/>" />
															</td>
															<td width="10%" align="center">
															<s:text name="手机"/>
															</td>
															<td>
																<input id="onePersonPhone" name="client.onePersonPhone"
																	type="text" class="input" style="width: 85px;"
																	value="<s:property value="client.onePersonPhone"/>" />
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
													<table width="89%" border="0" cellpadding="0"
														cellspacing="0">
														<tr>
														<td width="10%" align="center">
																<s:text name="姓名"/>
															</td>
															<td width="20%">
																<input id="client.twoPersonName"
																	name="client.twoPersonName" type="text" class="input"
																	style="width: 65px;"
																	value="<s:property value="client.twoPersonName"/>" />
															</td>
															<td width="10%" align="center">
																<s:text name="电话"/>
															</td>
															<td>
																<input id="client.twoPersonTel"
																	name="client.twoPersonTel" type="text" class="input"
																	style="width: 85px;"
																	value="<s:property value="client.twoPersonTel"/>" />
															</td>
															<td width="10%" align="center">
																<s:text name="手机"/>
															</td>
															<td>
																<input id="client.twoPersonPhone"
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
													<input id="registerEmail" name="client.registerEmail"
														type="text" class="input" size="55"
														value="<s:property value="client.registerEmail"/>" />
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
															<s:text name="开户银行"/>
														</td>
														<td align="left">
															<s:if test="#session.WW_TRANS_I18N_LOCALE!=null && #session.WW_TRANS_I18N_LOCALE.toString()=='en_US'">
																<select id="bank1" name="bank1" onchange="javascript:changeBank();">
  																<option value =" " <s:if test="client.bank == ' '">selected</s:if>><s:text name="请选择"/></option>
  																<option value ="Bank of China, BOC" <s:if test="client.bank == 'Bank of China, BOC'">selected</s:if>><s:text name="中国银行" /></option>
  																<option value ="Industrial and Commercial Bank of China Limited, ICBC" <s:if test="client.bank == 'Industrial and Commercial Bank of China Limited, ICBC'">selected</s:if>><s:text name="中国工商银行" /></option>
 																<option value="The agriculture Bank of China, ABC" <s:if test="client.bank == 'The agriculture Bank of China, ABC'">selected</s:if>><s:text name="中国农业银行" /></option>
 																<option value="China Construction Bank, CCB" <s:if test="client.bank == 'China Construction Bank, CCB'">selected</s:if>><s:text name="中国建设银行" /></option>
 																<option value="Communications Bank of China" <s:if test="client.bank == 'Communications Bank of China'">selected</s:if>><s:text name="中国交通银行" /></option>
 																<option value="Everbright Bank of China" <s:if test="client.bank == 'Everbright Bank of China'">selected</s:if>><s:text name="中国光大银行" /></option>
 																<option value="CITIC Industrial Bank" <s:if test="client.bank == 'CITIC Industrial Bank'">selected</s:if>><s:text name="中信实业银行" /></option>
 																<option value="China Merchants Bank" <s:if test="client.bank == 'China Merchants Bank'">selected</s:if>><s:text name="招商银行" /></option>
 																<option value="其他" <s:if test="#request.flag == 'nohave'">selected</s:if>><s:text name="其他" /></option>
 																</select>
																
																
														</s:if>
														<s:else>
															<select id="bank1" name="bank1" onchange="javascript:changeBank();">
  																<option value =" " <s:if test="client.bank == ' '">selected</s:if>><s:text name="请选择"/></option>
  																<option value ="中国银行" <s:if test="client.bank == '中国银行'">selected</s:if>><s:text name="中国银行" /></option>
  																<option value ="中国工商银行" <s:if test="client.bank == '中国工商银行'">selected</s:if>><s:text name="中国工商银行" /></option>
 																<option value="中国农业银行" <s:if test="client.bank == '中国农业银行'">selected</s:if>><s:text name="中国农业银行" /></option>
 																<option value="中国建设银行" <s:if test="client.bank == '中国建设银行'">selected</s:if>><s:text name="中国建设银行" /></option>
 																<option value="中国交通银行" <s:if test="client.bank == '中国交通银行'">selected</s:if>><s:text name="中国交通银行" /></option>
 																<option value="中国光大银行" <s:if test="client.bank == '中国光大银行'">selected</s:if>><s:text name="中国光大银行" /></option>
 																<option value="中信实业银行" <s:if test="client.bank == '中信实业银行'">selected</s:if>><s:text name="中信实业银行" /></option>
 																<option value="招商银行" <s:if test="client.bank == '招商银行'">selected</s:if>><s:text name="招商银行" /></option>
 																<option value="其他" <s:if test="#request.flag == 'nohave'">selected</s:if>><s:text name="其他" /></option>
															</select>
														</s:else>
															<s:if test="#request.flag == 'nohave'">
																<input type="text" id="bank2" name="bank2" size="20" maxlength="30" size="45" value="<s:property value="client.bank"/>" />
															</s:if>
															<s:else>
															<input type="text" id="bank2" name="bank2" size="20" maxlength="30" size="45" disabled="disabled" />
															</s:else>
															<span id="span1"></span>
														</td>
													</tr>
													<tr>
														<td height="20" align="left">
															<s:text name="分行"/>
														</td>
														<td align="left">
															<input type="text" id="branch" name="client.branch" value="<s:property value="client.branch"/>"  class="input" size="55"  />
														</td>
													</tr>
													<tr>
														<td height="20" align="left">
															<s:text name="支行" />
														</td>
														<td align="left">
															<input type="text" id="sub_branch" name="client.subBranch" value="<s:property value="client.subBranch"/>"  class="input" size="55"  />
														</td>
													</tr>
													<tr>
														<td height="20" align="left">
															<s:text name="账户名"/>
														</td>
														<td align="left">
															<input id="account_name" name="client.accountName" value="<s:property value="client.accountName"/>" type="text" class="input" size="55" />
														</td>
													</tr>
													<tr>
														<td height="20" align="left">
															<s:text name="银行账号"/>
														</td>
														<td align="left">
															<input id="bank_account" name="client.bankaccount" value="<s:property value="client.bankaccount"/>" type="text" class="input" size="55" />
														</td>
													</tr>
											<tr>
											<tr>
												<td height="20" align="left">
													<s:text name="发票抬头" />
												</td>
												<td align="left">
													<input  id="client.vbrk"
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
													<input id="client.des" name="client.des" type="text"
														class="input" size="55"
														value="<s:property value="client.des"/>" />
												</td>
											</tr>
											<tr>
												<td height="38" align="left"></td>
												<td height="38" align="left">
													<input name="input1" class="button" type="button" 
														onclick="javascript:save();"  value="<s:text name='save'/>" />

													<input name="input4" class="button" type="button" 
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
