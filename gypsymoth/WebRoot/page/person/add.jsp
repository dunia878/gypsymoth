<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title><s:text name="addPersonInformation"/></title>
<link rel=stylesheet href="${pageContext.request.contextPath}/style/index.css" type="text/css" />
<link rel=stylesheet href="${pageContext.request.contextPath}/style/right.css" type="text/css" />
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/style/screen.css" />
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript" charset="utf-8">
function addsubmit(){
	var name=document.getElementById("name");
	var sex=document.getElementById("sex");
	var birthday=document.getElementById("birthday");
	var education=document.getElementById("education");
	var orgId=document.getElementById("orgId");
	var personType=document.getElementById("personType");
	var tel1=document.getElementById("tel1");
	var tel2=document.getElementById("tel2");
	var mobile=document.getElementById("mobile");
	var fax=document.getElementById("fax");
	var email=document.getElementById("email");
	var training=document.getElementById("training");
	var passTime=document.getElementById("passTime");
	var isCoordinator=document.getElementById("isCoordinator");
	var isAuthorized=document.getElementById("isAuthorized");
	var attachment=document.getElementById("attachment");
	var isInspector=document.getElementById("isInspector");
	var spell=document.getElementById("spell");
	var selectOrgname2=document.getElementById("selectOrgname2");
	
	var formatEmail = /^\s*([\w-]+(\.\w+)*@([\w-]+\.)+\w+)\s*$/; //email正则表达式
   	var formatZNEN = /^[a-zA-Z0-9_\u4e00-\u9fa5]+$/;//判断中英文的正则表达式
 	var formatPost = /[1-9]\d{5}(?!\d)/;//验证邮编
 	var formatPhone = /\d{3}-\d{8}|\d{4}-\d{7}|\d{3}-\d{7}|\d{4}-\d{8}/;
 	var formatMobile = /^0{0,1}(13[0-9]?|15[0-9]|18[0-9])[0-9]{8}$/;
	if(name.value==""){
		alert("姓名不能为空!");
		name.focus();	
		return false;	
	}
	//if(spell.value==""){
	//	alert("英文名不能为空");
	//	spell.focus();
	//	return false;
	//}
	else if(!formatZNEN.test(name.value)){
		alert("姓名只能是中文和英文！");
		return false;
	}
	
	/* if(sex.value==""){	
		alert("性别不能为空!");
		sex.focus();
		return false;
	}*/
	/*
	if(birthday.value==""){	
		alert("出生年月日不能为空!");
		birthday.focus();
		return false;
	}
	*/
	/*
	if(education.value==""){	
		alert("学历不能为空!");
		education.focus();
		return false;
	}else if(!formatZNEN.test(education.value)){
		alert("学历只能是中文和英文！");
		return false;
	}
	*/
	/*
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
	*/
	/*
	if(tel1.value==""){
		alert("办公电话地区号不能为空!");
		tel1.focus();
		return false;
	}
	if(tel2.value==""){
		alert("办公电话不能为空!");
		tel2.focus();
		return false;
	}*/
	
	if(personType.value==""){	
		alert("人员类别不能为空!");
		personType.focus();
		return false;
	}
	/*
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
	}*/
	if(training.checked){	
		if(passTime.value==""){
		alert("参加过培训的人员,培训通过时间不能为空!");
		passTime.focus();
		return false;
	}
	}	
	if(selectOrgname2.value==""){	
		alert("所属公司不能为空!");
		selectOrgname2.focus();
		return false;
	}
	return true;
	}

</script>
		<script type="text/javascript">
			function selectOrgTree()
				{
					var url = "getOrgList.action";
					window.open(url,"_blank","height=500px,width=200px,scrollbars=yes,top=200px,left=500px"); 
					
				}
		</script>
<script type="text/javascript">
function jump(){
	window.location="${pageContext.request.contextPath}/page/person/getAllPersonByPage.action";
}
<!--setTimeout ("jump()",3000 )-->
</script>
</head>

<body>
<!--内容开始-->  
<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
 <s:form action="save" theme="simple" id="save" enctype ="multipart/form-data" method="post">
 <input id="selectOrgid" name="selectOrgid" type="hidden" value="<s:property value="selectOrgid"/>"/>
 <input id="selectOrgname" name="selectOrgname" type="hidden" value="<s:property value="selectOrgname"/>"/>
  <tr>  
    <td class="list_r">&nbsp;</td>
    <td valign="top" class="right"><div class="title"><s:text name="local"/>： <s:text name="home"/> &gt;&gt; <s:text name="orgAndPerson"/> &gt;&gt; <strong><s:text name="personManage"/></strong></div>
      <div>
        <table align="center" cellpadding="12" class="tabedit">
          <tr>
            <td height="300" align="left" valign="top"><!--编辑开始-->
                <table width="706" align="center" cellspacing="5">
                  <tr>
                    <td width="68" height="20"><s:text name="name"/></td>
                    <td width="332"><label>
                      <input name="name" type="text" class="input" id="name" size="55" />*
                    </label></td>
                    <td width="272">&nbsp;</td>
                  </tr>
                   <tr>
                    <td width="68" height="20"><s:text name="EName"/></td>
                    <td width="332"><label>
                      <input name="spell" type="text" class="input" id="spell" size="55" />
                    </label></td>
                    <td width="272">&nbsp;</td>
                  </tr>
                  <tr>
                    <td><s:text name="sex"/></td>
                    <td><input name="sex" type="radio" id="sex"  value="0" /><s:text name="male"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="sex" type="radio" id="sex" value="1"  /><s:text name="female"/></td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td height="20"><s:text name="birthday"/><br /></td>
                    <td><input name="birthday" type="text" class="input" id="birthday" size="55" onclick="WdatePicker()"/></td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td height="20"><s:text name="education"/></td>
                    <td>
                    <select name="education" id="education" style="width:305px;">
                    <option value=""><s:text name="choose"/></option>
                    <option value="<s:text name="technicalschool"/>"><s:text name="technicalschool"/></option>
                    <option value="<s:text name="college"/>"><s:text name="college"/></option>
                    <option value="<s:text name="bachelor"/>"><s:text name="bachelor"/></option>
                    <option value="<s:text name="master"/>"><s:text name="master"/></option>
                    <option value="<s:text name="doctor"/>"><s:text name="doctor"/></option>
                    </select>
                    </td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td height="20"><s:text name="belongCompany"/></td>
                     <td> 
                     <input id="selectOrgname2" name="selectOrgname2" type="text" size="43" disabled="disabled" value="<s:property value="selectOrgname"/>"/>
							                <input type="button" class="button" onclick="selectOrgTree()" value="<s:text name="choose"/>" />* </td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td height="20"><s:text name="personType"/></td>
                    <td><select id="personType" name="personType" style="width:305px;">
                    	<option value=""><s:text name="choose"/></option>
                    	<option value="1"><s:text name="user_zj"/></option>
                    	<option value="2"><s:text name="user_jt"/></option>
                    	<option value="3"><s:text name="user_yzgs"/></option>
                    	<option value="4"><s:text name="user_bm"/></option>
                    	<option value="5"><s:text name="user_one"/></option>
                    	<option value="6"><s:text name="user_two"/></option>
                    </select>*</td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td height="20"><s:text name="tel"/></td>
                    <td><input name="tel1" id="tel1" type="text" size="5"/>-<input name="tel2" id="tel2" type="text"  size="42"/> </td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td height="20"><s:text name="mobile"/></td>
                    <td><input id="mobile" name="mobile" type="text" class="input"  size="55" /></td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td height="20"><s:text name="fax"/></td>
                    <td><input id="fax" name="fax" type="text" class="input"  size="55" /></td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td height="20"><s:text name="email"/></td>
                    <td><input id="email" name="email" type="text" class="input"  size="55" /></td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td height="20"><s:text name="training"/><br />
                      <s:text name="Examination"/></td>
                    <td>
				<input type="checkbox" name="training" id="training" /> 
					</td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td height="20"><s:text name="training"/><br />
                      <s:text name="passTime"/></td>
                    <td><input name="passTime" id="passTime" type="text" class="input"  size="55" onClick="WdatePicker()"/></td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td height="20"><s:text name="isCoordinator"/></td>
                    <td>
                    <input type="checkbox" name="isCoordinator" id="isCoordinator" />
					</td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td height="20"><s:text name="isAuthorized"/></td>
                    <td>
                    <input type="checkbox" name="isAuthorized" id="isAuthorized" />
					</td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td height="20"><s:text name="isAuthorized"/><br />
                      <s:text name="attachment"/></td>
                    <td><label>
                      <input type="file" name="attachment" id="attachment" size="42"/>
                    </label></td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td height="20"><s:text name="isInspector"/></td>
                    <td>
                    <input type="checkbox" name="isInspector" id="isInspector" />
					</td>
                    <td>&nbsp;</td>
                  </tr>                  
                  <tr>
                    <td height="38" align="left"></td>
                    <td height="38" align="left"><input name="submit" class="button" type="submit"  value="<s:text name="add"/>" onclick="return addsubmit();"/>
                        <input name="reset" class="button" type="reset" value="<s:text name="reset"/>" />
                        <input name="return" class="button" type="button" value="<s:text name="return"/>"  onclick="jump()" /></td>
                    <td align="left">&nbsp;</td>
                  </tr>
                </table>
              <!--编辑结束-->
            </td>
          </tr>
        </table>
      </div></td>
  </tr></s:form>  
</table>

</body>
</html>
