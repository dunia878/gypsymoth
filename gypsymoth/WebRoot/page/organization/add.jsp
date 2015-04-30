<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title><s:text name="addOrganization"/></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/index.css" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/right.css" type="text/css" />
<link rel="StyleSheet" href="${pageContext.request.contextPath}/style/dtree.css" type="text/css" />
<link href="${pageContext.request.contextPath}/style/help.css" rel="stylesheet" type="text/css" />
<%@ include file="../include/head.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajax.js"></script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/jsFrame.js"></script>
<script type="text/javascript" src=".${pageContext.request.contextPath}/js/dtree.js"></script>
<script language="javascript">


	var a = 1;
      function addRowToTable()
		{
		var name = "add_div"+a;
		var provinceId = "provinceId"+a;
		var citydivdiv = "citydivdiv"+a;
		var citydivId = "citydivId"+a;
		var tblSample = "tblSample"+a;
		a = a+1;
		var name2 = "add_div"+a;
		//var strHtml = '<span id="d_span'+name2+'"><input type="file" name="certificate">&nbsp;&nbsp;<input type="button" onclick="removeFile(\'d_span'+name2+'\');" value="<s:text name="delete"/>"></input><br></span><div id="'+name2+'"></div>';
		var strHtml =  '<table width="100%" border="0" cellspacing="0" cellpadding="0" id="'+tblSample+'"> <tr>'+
                '<td width="14%" height="20"><s:text name="portcity"/></td>'+
                '<td width="38%" height="20">'+
                '<select id="'+provinceId+'" name="provinceId" onchange="javascript:ajaxselect(\'getAllCity.action\',\''+provinceId+'\',\''+citydivdiv+'\');">'+
                '<option value="0">-请选择-</option>'+
                '<s:if test="#request.promaryList.size>0">'+
                '<s:iterator value="#request.promaryList">'+
                '<option value="${PROID}">${PRONAME}</option>'+
                '</s:iterator>'+
                '</s:if>'+
                '</select>'+
                '<s:text name="Province"/>*</td>'+
				'<td width="48%"><div id="'+citydivdiv+'" style="float:left;">  '+
               	'<select name="citydivId" id="'+citydivId+'">  <option value="">-<s:text name="choose"/>-</option> '+
                '</select><s:text name="City"/>* </div></td></tr><tr><td height="20">&nbsp;</td></tr>  <tr> '+
                '<td height="20"><s:text name="ChineseName"/></td>'+
                '<td colspan="2"><input name="portName" id="portName" type="text" size="50" /></td>'+             
                '</tr>  <tr>  <td height="20"></td>   </tr>   <tr> '+
                '<td height="20"><s:text name="EnglishName"/></td>  <td colspan="2"><input name="portSname" id="portSname" type="text" size="50" /><s:text name="EnglishNameispinyin"/></td> '+
                '</tr>  <tr> <td height="50" colspan="3" align="right"><input type="button" class="button_s" name="input" onclick="removeFile(\''+tblSample+'\')" value="删除该港口"/><br/><hr/></td> </tr> </table>'+
                ' <div id="'+name2+'"></div>';
		document.getElementById(name).innerHTML +=strHtml;
		}
      
      function removeFile(spanname) {
          var spanvalue = "#"+spanname;
		  $(spanvalue).remove(); 
      }
 

// document.getElementById(name).innerHTML = "<table width='100%' border='0' cellspacing='0' cellpadding='0' id='tblSample'> "+select+"   <tr><td height='20'><s:text name='name1'/></td><td><input name='portName' type='text' class='input' size='44' /></td></tr><tr><td height='20'><s:text name='sname'/></td><td><input name='portSname' type='text' class='input' size='44' /></td></tr><tr><td height='20'>&nbsp;</td><td height='28'></td></tr></table><div id='"+name2+"'></div>";

function deleteToTable()
{
	$("#add_div1 table:last").remove(); 
}

</script>
<script type="text/javascript">
			function selectOrgTree()
				{	
					var url = "getOrgList.action";
					window.open(url,"_blank","height=500px,width=200px,scrollbars=yes,top=200,left=500"); 
					
				}
</script>

<script type="text/javascript" charset="utf-8">
       function changeAddressDiv(){
        	if(document.getElementById("radio2").checked){
        		document.getElementById("addressdiv1").style.display="none";
        		document.getElementById("addressdiv2").style.display="block";
        		document.getElementById("nationId1").Value="";
        		document.getElementById("address1").Value="";
        		document.getElementById("proId").Value="";
        		document.getElementById("cityId").Value="";
        		
        	}
        	else if(document.getElementById("radio1").checked){
        		document.getElementById("addressdiv1").style.display="block";
        		document.getElementById("addressdiv2").style.display="none";
        		document.getElementById("nationId2").Value="";
        		document.getElementById("address2").Value="";
        	}
        }        
    </script>
<script language="javascript" type="text/javascript" charset="utf-8">
function addsubmit(){
	var orgId=document.getElementById("orgId");
	var orgSname=document.getElementById("orgSname");
	var orgFname=document.getElementById("orgFname");
	var contact=document.getElementById("contact");
	var tel=document.getElementById("tel");
	var fax=document.getElementById("fax");
	var email=document.getElementById("email");
	var nationId1=document.getElementById("nationId1");
	var proId=document.getElementById("proId");
	var cityId=document.getElementById("cityId");
	var provinceId=document.getElementById("provinceId");
	var citydivId=document.getElementById("citydivId");
	
	
	var formatEmail = /^\s*([\w-]+(\.\w+)*@([\w-]+\.)+\w+)\s*$/; //email正则表达式
   	var formatZNEN = /^[a-zA-Z0-9_\u4e00-\u9fa5]+$/;//判断中英文的正则表达式
 	var formatPost = /[1-9]\d{5}(?!\d)/;//验证邮编
 	var formatPhone = /\d{3}-\d{8}|\d{4}-\d{7}|\d{3}-\d{7}|\d{4}-\d{8}/;
 	var formatMobile = /^0{0,1}(13[0-9]?|15[0-9]|18[0-9])[0-9]{8}$/;
	if(orgId.value==""){
		alert("机构编号不能为空!");
		orgId.focus();	
		return false;	
	}
	
	if(orgSname.value==""){	
		alert("公司简称不能为空!");
		orgSname.focus();
		return false;	
	}
	
	if(nationId1.value=="37"){
		if(proId.value==""){	
		alert("请选择省份!");
		return false;	
		}
	    if(cityId.value==""){
	    	alert("请选择城市!");
	    	return false;	
	    }		
	}
	if(provinceId.value==""){
		alert("请选择港口省份!");
		return false;			
	}
	if(citydivId.value==""){	
		alert("请选择港口城市!");
		return false;			
	}
	
	if(email.value!=""){
	if(!formatEmail.test(email.value)){	
		alert("邮件格式不正确!");
		email.focus();
		return false;	
	}
	}	
	return true;
	}

</script>
<script type="text/javascript">
function jump(){
	window.location="${pageContext.request.contextPath}/page/organization/getAllOrganizationByPage.action";
}
</script>  
</head>

<body>
<!--内容开始-->
<s:form action="save" theme="simple" id="save" enctype ="multipart/form-data" method="post">
<input id="selectOrgid" name="selectOrgid" type="hidden" value="<s:property value="selectOrgid"/>"/>
<input id="selectOrgname" name="selectOrgname" type="hidden" value="<s:property value="selectOrgname"/>"/>

<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
  <tr>
    <td class="list_r">&nbsp;</td>
    <td valign="top" class="right"><div class="title"><s:text name="local"/>：<s:text name="home"/>  &gt;&gt; <s:text name="orgAndPerson"/> &gt;&gt; <strong><s:text name="OrganizationManage"/></strong></div>
      <div>
        <table align="center" cellpadding="12" class="tabedit">
          <tr>
            <td height="300" align="left" valign="top"><!--编辑开始-->
                <table width="600" align="center" cellspacing="5">
                <tr>
                    <td width="60" height="20" align="left"><s:text name="orgId"/></td>
                    <td width="362" align="left"><label>
                      <input name="orgId" type="text" class="input" id="orgId" size="55" />*
                    </label></td>
                </tr>
                  <tr>
                    <td width="60" height="20" align="left"><s:text name="companySName"/></td>
                    <td width="362" align="left"><label>
                      <input name="orgSname"  id="orgSname" type="text" class="input"  size="55" />*
                    </label></td>
                  </tr>
                  <tr>
                    <td height="20" align="left"><s:text name="companyFName"/></td>
                    <td align="left"><input name="orgFname" id="orgFname" type="text" class="input"  size="55" />                    </td>
                  </tr>
                  <tr>
                    <td height="20" align="left"><s:text name="contact"/></td>
                    <td align="left"><input name="contact" id="contact" type="text" class="input" size="55" />                    </td>
                  </tr>
                  <tr>
                    <td height="20" align="left"><s:text name="contacttel"/></td>
                    <td align="left"><input name="tel" id="tel" type="text" class="input" size="55"/></td>
                  </tr>
                  <tr>
                    <td height="20" align="left"><s:text name="contactfax"/></td>
                    <td align="left"><input name="fax"  id="fax" type="text" class="input" size="55" /></td>
                  </tr>
                  <tr>
                    <td height="20" align="left"><s:text name="contactemail"/></td>
                    <td align="left"><input name="email" id="email" type="text" class="input" size="55" /></td>
                  </tr>
                 <tr>
														
														<td height="20" align="left">
															<s:text name="companyAddress"/>
														</td>
														<td align="left">
															<table>
																<tr>
																	<td><div><input type="radio"" name="changeAddress" id="radio1" value="1" checked="checked" onclick="changeAddressDiv()" /></div></td>
																	<td><input type="hidden" name="nationId1" id="nationId1" value="37" /><s:text name="中国"/> &nbsp;</td>
																	<td><div><input type="radio"" name="changeAddress" id="radio2" value="2" onclick="changeAddressDiv()"/></div></td>
																	<td><s:text name="othercountry"/></td>
																</tr>
															</table>
															<div id="addressdiv1" style="display: block">
																<table>
																	<tr>
																		<td>
																		<s:select list="#request.promaryList" name="proId"
																		id="proId" listKey="PROID" listValue="PRONAME"
																		headerKey="0" headerValue="-%{getText('请选择')}-"
																		onchange="javascript:ajaxselect('getCity.action','proId','citydiv');"></s:select>　<s:text name="Province"/>*
																		</td>
																		<td>
																		<div id="citydiv">
																			<select name="cityId" id="cityId">
																				<option value="">-<s:text name="请选择"/>-</option>
																			</select>　<s:text name="City"/>*
																		</div>
																		</td>
																	</tr>
																	<tr>
																		<td colspan="2"><input type="text" size="65" name="address1" id="address1"/></td>
																	</tr>
																</table>
															</div>
															<div id="addressdiv2" style="display: none">
																<table>
																	<tr>
																		<td>
																			<s:select list="#request.nationList" name="nationId2"
																			id="nationId2" listKey="NATIONID" listValue="NATIONNAME"
																			headerKey="0" headerValue="-%{getText('请选择')}-"  																			
																			></s:select>　<s:text name="nation"/>
																		</td>
																	</tr>
																	<tr>
																		<td>
																			<input type="text" size="65" name="address2" id="address2"/>
																		</td>
																	</tr>
																</table>
															</div>
														</td>
													</tr>                  
                    <tr>
                    <td height="20" align="left"> <s:text name="organizationType"/></td>
                    <td align="left">
                    <select name="organizationType" id="organizationType" style="width:360px;">
                    	<option value=""><s:text name="choose"/></option>
                    	<option value="0"><s:text name="user_yzgs"/></option>
                    	<option value="1"><s:text name="user_one"/></option>
                    	<option value="2"><s:text name="user_two"/></option>
                    </select>*</td>
                  </tr>
                  <tr>
                    <td height="20"><s:text name="belongOrganizaiton"/></td>
                    <td> 
                     <input id="selectOrgname2" name="selectOrgname2" type="text" size="54" disabled="disabled" value="<s:property value="selectOrgname"/>" />
							                <input type="button" class="button" onclick="selectOrgTree()" value="<s:text name="choose"/>" />* </td>
                  </tr>
                 <tr>
                    <td height="20" align="left"><s:text name="bankaccount"/></td>
                    <td align="left"><input name="bankaccount" id="bankaccount" type="text" class="input" size="55" /></td>
                  </tr>
                  <tr>
                    <td height="20" align="left"><s:text name="vbrk"/></td>
                    <td align="left"><input name="vbrk" id="vbrk" type="text" class="input" size="55" /></td>
                  </tr>
                  <tr>
                    <td height="20" valign="top"><s:text name="manageport"/></td>
                    <td><table width="100%" border="0" cellspacing="0" cellpadding="0" id="tblSample">
                     <tr>
                     	<td width="14%" height="20"><s:text name="portcity"/></td>
                        <td width="38%" height="20">
						<s:select list="#request.promaryList" name="provinceId"
						id="provinceId" listKey="PROID" listValue="PRONAME"
						headerKey="0" headerValue="-%{getText('请选择')}-"
						onchange="javascript:ajaxselect('getAllCity.action','provinceId','citydivdiv');"></s:select>　<s:text name="Province"/>*</td>
						<td width="48%"><div id="citydivdiv" style="float:left;">
                          <select name="citydivId" id="citydivId">
                            <option value="">-<s:text name="choose"/>-</option>
                          </select>
						  　
						  <s:text name="City"/>* </div></td>
						</tr> 
						<tr>
                    	<td height="20">&nbsp;</td>                    	
                 	 </tr>                       
                     <tr>
                        <td height="20"><s:text name="ChineseName"/></td>
                        <td colspan="2"><input name="portName" id="portName" type="text" size="50" /></td>                        
                     </tr>
                       <tr>
                    <td height="20">&nbsp;</td>                    
                  </tr>  
                      <tr>
                        <td height="20"><s:text name="EnglishName"/></td>
                        <td colspan="2"><input name="portSname" id="portSname" type="text" size="50" />&nbsp;&nbsp;&nbsp;<s:text name="EnglishNameispinyin"/></td>
                      </tr>
                      <tr>
                    	<td height="20" colspan="3"><hr/></td>                    	
                  	  </tr>  
                  	                    
                    </table>
					<div id="add_div1" name="add_div1"></div>
　　　　　
<input type="button" class="button_s" value="<s:text name="addport"/>" onclick="addRowToTable()" />
					</td>
                  </tr>
                  <tr>
                    <td height="20">&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td height="38" align="left"></td>
                    <td height="38" align="left"><input name="input3" class="button" type="submit"  value="<s:text name="add"/>" onclick="return addsubmit()"/>
                        <input name="input2" class="button" type="reset" value="<s:text name="reset"/>" />
                        <input name="input" class="button" type="button" value="<s:text name="return"/>"  onclick="jump()" /></td>
                  </tr>
                </table>
              <!--编辑结束-->
            </td>
          </tr>
        </table>
      </div></td>
  </tr>
</table>
</s:form>
</body>
</html>
