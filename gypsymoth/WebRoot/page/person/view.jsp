<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title><s:text name="persondetail"/></title>
<link rel=stylesheet href="${pageContext.request.contextPath}/style/index.css" type="text/css" />
<link rel=stylesheet href="${pageContext.request.contextPath}/style/right.css" type="text/css" />
 <script type="text/javascript">
 function updateperson(){
 	document.forms[0].method="post";
 	document.forms[0].action ="${pageContext.request.contextPath}/page/person/getUpdatePre.action" ;
 	document.forms[0].submit();
  }
 </script>
 <script type="text/javascript">
function jump(){
	window.location="${pageContext.request.contextPath}/page/person/getAllPersonByPage.action?goPage="+<s:property value="#session.currtPage"/>;
}
<!--setTimeout ("jump()",3000 )-->
</script>
</head>
<body>
<!--内容开始-->
<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
  <tr>
    <td class="list_r">&nbsp;</td>
    <td valign="top" class="right"><div class="title"><s:text name="local"/>： <s:text name="home"/> &gt;&gt; <s:text name="orgAndPerson"/> &gt;&gt; <strong><s:text name="personManage"/></strong></div>
      <div>
        <table align="center" cellpadding="12" class="tabedit">
        <s:form action="getUpdatePre" theme="simple" method="post" id="getUpdatePre">
          <tr>
            <td height="300" align="left" valign="top"><!--编辑开始-->
                  <table width="706" align="center" cellspacing="5">
                    <tr>
                      <td width="100" height="20"><s:text name="name"/></td>
                <td width="332"><label>
                        <input name="name" type="text" class="input" disabled="true" value="<s:property value="#request.list.NAME"/>" size="55" />
                      </label></td>
                      <td width="272">&nbsp;</td>
                    </tr>
                    <tr>
                    <td width="68" height="20"><s:text name="EName"/></td>
                    <td width="332"><label>
                      <input name="spell" type="text" class="input" id="spell" size="55" disabled="true" value="<s:property value="#request.list.SPELL"/>"/>
                    </label></td>
                    <td width="272">&nbsp;</td>
                  </tr>
                   <tr>
                      <td><s:text name="sex"/></td>
                    <td><input name="sex" type="radio" id="sex"  value="0"   disabled="true" <s:if test="#request.list.SEX==0">checked="checked"</s:if>/><s:text name="male"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="sex" type="radio" id="sex" value="1" disabled="true" <s:if test="#request.list.SEX==1">checked="checked"</s:if> /><s:text name="female"/></td>
                    <td>&nbsp;</td>
                    </tr>
                    <tr>
                      <td height="20"><s:text name="birthday"/><br /></td>
                      <td><input name="birthday" type="text" class="input" disabled="true" value="<s:property value="#request.list.BIRTHDAY"/>" size="55" /></td>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                      <td height="20"><s:text name="education"/></td>
                      <td><input name="education" type="text" class="input" disabled="true" value="<s:property value="#request.list.EDUCATION"/>" size="55" /></td>
                      <td>&nbsp;</td>
                    </tr>
                    
                    <tr>
                      <td height="20"><s:text name="belongCompany"/></td>
                      <td><input name="orgName" type="text" class="input" disabled="disabled" value="<s:property value="#request.list.ORG_NAME"/>" size="55" /></td>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                    	<td height="20"><s:text name="personType"/></td>
                    	<td><select name="personType" disabled="disabled" style="width:305px;">
                    	
                    	<option  value="1" <s:if test="#request.list.PERSON_TYPE==1">selected</s:if>><s:text name="user_zj"/></option>
                    	<option  value="2" <s:if test="#request.list.PERSON_TYPE==2">selected</s:if>><s:text name="user_jt"/></option>
                    	<option   value="3" <s:if test="#request.list.PERSON_TYPE==3">selected</s:if>><s:text name="user_yzgs"/></option>
                    	<option   value="4" <s:if test="#request.list.PERSON_TYPE==4">selected</s:if>><s:text name="user_bm"/></option>
                    	<option   value="5" <s:if test="#request.list.PERSON_TYPE==5">selected</s:if>><s:text name="user_one"/></option>
                    	<option   value="6" <s:if test="#request.list.PERSON_TYPE==6">selected</s:if>><s:text name="user_two"/></option>
                    </select></td>
                    <td>&nbsp;</td>
                    </tr>                    
                    <tr>
                      <td height="20"><s:text name="tel"/></td>
                      <td><input name="tel" type="text" class="input" disabled="disabled" size="55" value="<s:property value="#request.list.TEL"/>" /></td>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                      <td height="20"><s:text name="mobile"/></td>
                      <td><input name="mobile" type="text" class="input" disabled="disabled" value="<s:property value="#request.list.MOBILE"/>" size="55" /></td>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                      <td height="20"><s:text name="fax"/></td>
                      <td><input name="fax" type="text" class="input" disabled="disabled" value="<s:property value="#request.list.FAX"/>" size="55"  /></td>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                      <td height="20"><s:text name="email"/></td>
                      <td><input name="email" type="text" class="input" disabled="disabled" value="<s:property value="#request.list.EMAIL"/>" size="55" /></td>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                      <td height="20"><s:text name="training"/><br />
                      <s:text name="Examination"/></td>
                      <td>
                      <input name="training" type="checkbox" id="checkbox" <s:if test="#request.list.TRAINING==1">checked="checked"</s:if> disabled="disabled"  value="<s:property value="#request.list.TRAINING"/>"/>
					</td>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                      <td height="20"><s:text name="training"/><br />
                      <s:text name="passTime"/></td>
                      <td><input name="passTime" type="text" class="input" disabled="disabled"  size="55" value="<s:property value="#request.list.PASS_TIME"/>"/></td>
                      <td>&nbsp;</td>
                    </tr>
                     <tr>
                      <td height="20"><s:text name="isCoordinator"/></td>
                      <td>
                      <input name="isCoordinator" type="checkbox" id="checkbox2" <s:if test="#request.list.IS_COORDINATOR==1">checked="checked"</s:if>  disabled="disabled" value="<s:property value="#request.list.IS_COORDINATOR"/>"/>
						</td>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                      <td height="20"><s:text name="isAuthorized"/></td>
                      <td>
                      <input name="isAuthorized" type="checkbox" id="checkbox3" <s:if test="#request.list.IS_AUTHORIZED==1">checked="checked"</s:if>  disabled="disabled" value="<s:property value="#request.list.IS_AUTHORIZED"/>"/>
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
                      <input name="isInspector" type="checkbox" id="isInspector" <s:if test="#request.list.IS_INSPECTOR==1">checked="checked"</s:if> disabled="disabled" value="<s:property value="#request.list.IS_INSPECTOR"/>"/>
					  </td>
                      <td>&nbsp;</td>
                    </tr>
                    
                    <tr>
                      <td height="38" align="left"><input name="pId" type="hidden" value="<s:property value="#request.list.ID"/>"/></td>
                      <td height="38" align="left">
                      <c:forEach items="${menu_parent}" var="mp3" varStatus="status">
                      <c:if test="${mp3.rightId == 322}">
                      <input name="input" class="button" type="button" value="<s:text name="update"/>"  onclick="updateperson()" />
                      </c:if>
                      </c:forEach>                      	  
                          <input name="input" class="button" type="button" value="<s:text name="return"/>"  onclick="jump()" /></td>
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
