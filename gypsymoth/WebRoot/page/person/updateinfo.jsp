<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title><s:text name="persondetail"/></title>
<link rel=stylesheet href="${pageContext.request.contextPath}/style/index.css" type="text/css" />
<link rel=stylesheet href="${pageContext.request.contextPath}/style/right.css" type="text/css" />
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
 <script type="text/javascript">
 function addsubmit(){
	var name=document.getElementById("name");
	var sex=document.getElementById("sex");
	var birthday=document.getElementById("birthday");
	var education=document.getElementById("education");
	var orgId=document.getElementById("orgId");
	var personType=document.getElementById("personType");
	var tel=document.getElementById("tel");	
	var mobile=document.getElementById("mobile");
	var fax=document.getElementById("fax");
	var email=document.getElementById("email");
//	var training=document.getElementById("training");
//	var passTime=document.getElementById("passTime");
//	var isCoordinator=document.getElementById("isCoordinator");
//	var isAuthorized=document.getElementById("isAuthorized");
//	var attachment=document.getElementById("attachment");
//	var isInspector=document.getElementById("isInspector");
	var spell=document.getElementById("spell");
	
	var formatEmail = /^\s*([\w-]+(\.\w+)*@([\w-]+\.)+\w+)\s*$/; //email正则表达式
   	var formatZNEN = /^[a-zA-Z0-9_\u4e00-\u9fa5]+$/;//判断中英文的正则表达式
 	var formatPost = /[1-9]\d{5}(?!\d)/;//验证邮编
 	var formatPhone = /\d{3}-\d{8}|\d{4}-\d{7}|\d{3}-\d{7}|\d{4}-\d{8}/;
 	var formatMobile = /^0{0,1}(13[0-9]?|15[0-9]|18[0-9])[0-9]{8}$/;
/*
	if(name.value==""){
		alert("姓名不能为空!");
		name.focus();	
		return false;	
	}
	if(spell.value==""){
		alert("英文名不能为空");
		spell.focus();
		return false;
	}
	else if(!formatZNEN.test(name.value)){
		alert("姓名只能是中文和英文！");
		return false;
	}
	
	if(sex.value==""){	
		alert("性别不能为空!");
		sex.focus();
		return false;
	}
	if(birthday.value==""){	
		alert("出生年月日不能为空!");
		birthday.focus();
		return false;
	}
	if(education.value==""){	
		alert("学历不能为空!");
		education.focus();
		return false;
	}else if(!formatZNEN.test(education.value)){
		alert("学历只能是中文和英文！");
		return false;
	}
	if(mobile.value==""){	
		alert("手机不能为空!");
		mobile.focus();
		return false;
	}
	else if(!formatMobile.test(mobile.value)){
		alert("请输入正确的手机号码!");
		mobile.focus();
		return false;
	}	
	if(tel.value==""){
		alert("办公电话不能为空!");
		tel2.focus();
		return false;
	}
	if(personType.value==""){	
		alert("人员类别不能为空!");
		personType.focus();
		return false;
	}
	if(fax.value==""){	
		alert("传真不能为空!");
		fax.focus();
		return false;
	}
	if(email.value==""){	
		alert("电子邮件不能为空!");
		orgId.focus();
		return false;
	}else if(!formatEmail.test(email.value)){
		alert("请输入正确的电子邮件！");
		return false;
	}
	*/
//if(training.checked){	
//		if(passTime.value==""){
//		alert("参加过培训的人员,培训通过时间不能为空!");
//		passTime.focus();
//		return false;
//	}
//	}	
	return true;
	}
	function selectOrgTree()
				{
					var url = "getOrgList.action";
					window.open(url,"_blank","height=500px,width=200px,scrollbars=yes,top=200px,left=500px"); 
					
				}
 
function jump(){
	window.location="${pageContext.request.contextPath}/page/person/getInfoById.action";
}
<!--setTimeout ("jump()",3000 )-->
</script>
</head>
<body>
<!--内容开始-->
<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
  <tr>
    <td class="list_r">&nbsp;</td>
    <td valign="top" class="right"><div class="title"><s:text name="local"/>： <s:text name="home"/> &gt;&gt; <s:text name="SystemManage"/> &gt;&gt; <strong><s:text name="basicInfoUpdate"/></strong></div>
      <div>
        <table align="center" cellpadding="12" class="tabedit">
        <s:form action="updateInfo" theme="simple" method="post" id="updateInfo">
        <input id="selectOrgid" name="selectOrgid" type="hidden" value="${selectOrgid }"/>
		<input id="selectOrgname" name="selectOrgname" type="hidden" value="${selectOrgname }"/>
          <tr>
            <td height="300" align="left" valign="top"><!--编辑开始-->
                  <table width="706" align="center" cellspacing="5">
                    <tr>
                      <td width="100" height="20"><s:text name="name"/></td>
                <td width="332"><label>
                        <input name="name" id="name" type="text" class="input" disabled="disabled"  value="<s:property value="#request.list.NAME"/>" size="55" />
                      </label></td>
                      <td width="272">&nbsp;</td>
                    </tr>
                   <tr>
                    <td width="68" height="20"><s:text name="EName"/></td>
                    <td width="332"><label>
                      <input name="spell" type="text" class="input" id="spell" size="55"  value="<s:property value="#request.list.SPELL"/>" />
                    </label></td>
                    <td width="272">&nbsp;</td>
                  </tr>
                   <tr>
                      <td><s:text name="sex"/></td>
                    <td><input name="sex" type="radio" id="sex"  value="0" <s:if test="#request.list.SEX==0">checked="checked"</s:if>/><s:text name="male"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="sex" type="radio" id="sex" value="1" <s:if test="#request.list.SEX==1">checked="checked"</s:if> /><s:text name="female"/></td>
                    <td>&nbsp;</td>
                    </tr>
                    <tr>
                      <td height="20"><s:text name="birthday"/><br /></td>
                      <td><input name="birthday" id="birthday" type="text" class="input"  value="<s:property value="#request.list.BIRTHDAY"/>" size="55" onclick="WdatePicker()"/></td>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                      <td height="20"><s:text name="education"/></td>
                      <td>
                      <select name="education" id="education" style="width:305px;">
                    <option value="" ><s:text name="choose"/></option>
                    <option value="<s:text name="technicalschool"/>" <s:if test="#request.list.EDUCATION=='中专'">selected</s:if>><s:text name="technicalschool"/></option>
                    <option value="<s:text name="college"/>" <s:if test="#request.list.EDUCATION=='大专'">selected</s:if>><s:text name="college"/></option>
                    <option value="<s:text name="bachelor"/>" <s:if test="#request.list.EDUCATION=='本科'">selected</s:if>><s:text name="bachelor"/></option>
                    <option value="<s:text name="master"/>" <s:if test="#request.list.EDUCATION=='硕士'">selected</s:if>><s:text name="master"/></option>
                    <option value="<s:text name="doctor"/>" <s:if test="#request.list.EDUCATION=='博士'">selected</s:if>><s:text name="doctor"/></option>
                    </select><td>&nbsp;</td>
                    </tr>
                    
                   <tr>
                    <td height="20"><s:text name="belongCompany"/></td>
                    <td><input id="selectOrgname2" name="selectOrgname2" type="text" size="43" disabled="disabled"  style="width:301px;" value="${selectOrgname}"/> </td>                    
                    <td>&nbsp;</td>
                  </tr>
                    <tr>
                    	<td height="20"><s:text name="personType"/></td>
                    	<td><select name="personType" id="personType" disabled="disabled" style="width:305px;"> 
                    	<option value=""></option>                   	
                    	<option <s:if test="#request.list.PERSON_TYPE==1">selected</s:if> value="1"><s:text name="user_zj"/></option>
                    	<option <s:if test="#request.list.PERSON_TYPE==2">selected</s:if> value="2"><s:text name="user_jt"/></option>
                    	<option <s:if test="#request.list.PERSON_TYPE==3">selected</s:if>  value="3"><s:text name="user_yzgs"/></option>
                    	<option <s:if test="#request.list.PERSON_TYPE==4">selected</s:if>  value="4"><s:text name="user_bm"/></option>
                    	<option <s:if test="#request.list.PERSON_TYPE==5">selected</s:if>  value="5"><s:text name="user_one"/></option>
                    	<option <s:if test="#request.list.PERSON_TYPE==6">selected</s:if>  value="6"><s:text name="user_two"/></option>
                    </select></td>
                    <td>&nbsp;</td>
                    </tr>                   
                    <tr>
                      <td height="20"><s:text name="tel"/></td>
                      <td><input name="tel" id="tel" type="text" class="input"  size="55" value="<s:property value="#request.list.TEL"/>" /></td>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                      <td height="20"><s:text name="mobile"/></td>
                      <td><input name="mobile" id="mobile" type="text" class="input"  value="<s:property value="#request.list.MOBILE"/>" size="55" /></td>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                      <td height="20"><s:text name="fax"/></td>
                      <td><input name="fax" id="fax" type="text" class="input"  value="<s:property value="#request.list.FAX"/>" size="55"  /></td>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                      <td height="20"><s:text name="email"/></td>
                      <td><input name="email" id="email" type="text" class="input"  value="<s:property value="#request.list.EMAIL"/>" size="55" /></td>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                      <td height="20"><s:text name="training"/><br />
                      <s:text name="Examination"/></td>
                      <td>
                      <input name="training" type="checkbox" id="checkbox" disabled="true" <s:if test="#request.list.TRAINING==1">checked="checked"</s:if>   value="<s:property value="#request.list.TRAINING"/>"/>
					</td>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                      <td height="20"><s:text name="training"/><br />
                      <s:text name="passTime"/></td>
                      <td><input name="passTime" type="text" class="input" disabled="true"  size="55" value="<s:property value="#request.list.PASS_TIME"/>"/></td>
                      <td>&nbsp;</td>
                    </tr>
                     <tr>
                      <td height="20"><s:text name="isCoordinator"/></td>
                      <td>
                      <input name="isCoordinator" type="checkbox" id="checkbox2" disabled="true"  <s:if test="#request.list.IS_COORDINATOR==1">checked="checked"</s:if>   value="<s:property value="#request.list.IS_COORDINATOR"/>"/>
						</td>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                      <td height="20"><s:text name="isAuthorized"/></td>
                      <td>
                      <input name="isAuthorized" type="checkbox" id="checkbox3" disabled="true"  <s:if test="#request.list.IS_AUTHORIZED==1">checked="checked"</s:if>   value="<s:property value="#request.list.IS_AUTHORIZED"/>"/>
						</td>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                      <td height="20"><s:text name="isAuthorized"/><br />
                      <s:text name="attachment"/></td>
                      <td>&nbsp;<s:if test="#request.list.ATTACHMENT!=null&&#request.list.ATTACHMENT!=''"><img src="${pageContext.request.contextPath}/attachment/<s:property value="#request.list.ATTACHMENT"/>"></img></s:if></td>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                      <td height="20"><s:text name="isInspector"/></td>
                      <td>
                      <input name="isInspector" type="checkbox" id="isInspector" <s:if test="#request.list.IS_INSPECTOR==1">checked="checked"</s:if>  value="<s:property value="#request.list.IS_INSPECTOR"/>" disabled="true" />
					  </td>
                      <td>&nbsp;</td>
                    </tr>
                    
                    <tr>
                      <td height="38" align="left"><input name="id" type="hidden" value="<s:property value="#request.list.ID"/>"/></td>
                      <td height="38" align="left">
                      	  <input name="input" class="button" type="submit" value="<s:text name="savebutton"/>"  onclick="return addsubmit();"/>
                      	  <input name="input" class="button" type="button" value="<s:text name="return"/>"  onclick="jump()" />
                      	  
                          </td>
                      <td align="left">&nbsp;</td>
                    </tr>
                  </table>
              <!--编辑结束-->
            </td>
          </tr>
          </s:form>
        </table>
      </div></td>
  </tr>
</table>
</body>
</html>
