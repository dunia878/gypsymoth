<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<script type="text/javascript">
function jump(){
	window.location="${pageContext.request.contextPath}/page/examine/getAllExamineByPage.action";
}
function updateBussiness(){
	window.location="${pageContext.request.contextPath}/page/examine/getExaminePre.action?Id="+<s:property value="#request.list.ID"/>;	
	}
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>查看业务详情</title>
<link rel=stylesheet href="${pageContext.request.contextPath}/style/index.css" type="text/css" />
<link rel=stylesheet href="${pageContext.request.contextPath}/style/right.css" type="text/css" />
</head>
<body>
<!--内容开始-->
<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
  <tr>
    <td class="list_r">&nbsp;</td>
    <td valign="top" class="right"><div class="title"><s:text name="local"/>： <s:text name="home"/> &gt;&gt; <s:text name="bussinessManage"/> &gt;&gt; <strong><s:text name="applicat"/></strong></div>
      <div>
        <table align="center" cellpadding="12" class="tabedit">
          <tr>
            <td height="300" align="left" valign="top"><!--编辑开始-->
              <table width="706" align="center" cellspacing="5">
                <tr>
                  <td width="180" height="20"><s:text name="applicat"/></td>
                  <td width="332"><label>
                    <input disabled="true" name="libName" type="text" class="input" value="<s:property value="#request.list.BUSINESSNAME"/>" size="55" />
                  </label></td>
                  <td width="272">&nbsp;</td>
                </tr>
                <tr>
                  <td><s:text name="boatowner"/></td>
                  <td><label>
                    <input disabled="true" name="libName2" type="text" class="input" value="<s:property value="#request.list.LINKMANNAME"/>" size="55" />
                  </label></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="20"><s:text name="boatname"/></td>
                  <td><input disabled="true" name="libName11" type="text" class="input" value="<s:property value="#request.list.VESSELNAME"/>" size="55" /></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="20"><s:text name="type"/></td>
                  <td><input disabled="true" name="libName11" type="text" class="input" value="<s:property value="#request.list.VESSELTYPE"/>" size="55" /></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="20"><s:text name="shipper"/></td>
                  <td><input disabled="true" name="libName11" type="text" class="input" value="<s:property value="#request.list.REGISTRY"/>" size="55" /></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="20">IMO<s:text name="code"/></td>
                  <td><input disabled="true" name="libName5" type="text" class="input" value="<s:property value="#request.list.IMO"/>" size="55" /></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="20"><s:text name="totalton"/></td>
                  <td><input disabled="true" name="libName8" type="text" class="input" value="<s:property value="#request.list.TONNAGE"/>" size="55" /></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="20"><s:text name="planinportdate"/></td>
                  <td><input disabled="true" name="libName6" type="text" class="input" value="<s:property value="#request.list.PLANDATEIN"/>" size="55" /></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="20"><s:text name="planoutportdate"/></td>
                  <td><input disabled="true" name="libName6" type="text" class="input" value="<s:property value="#request.list.PLANDATEOUT"/>" size="55" /></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="20"><s:text name="berth"/></td>
                  <td><input disabled="true" name="libName9" type="text" class="input" value="<s:property value="#request.list.BERTH"/>" size="55" /></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="20"><s:text name="applicat"/><s:text name="name"/></td>
                  <td><input  name="linkmanname" id="linkmanname" type="text" class="input" disabled="true" value="<s:property value="#request.list.LINKMANNAME"/>" size="55" /></td>
                  <td>&nbsp;</td>
                </tr>
                 <tr>
                  <td height="20"><s:text name="applicat"/><s:text name="contacttel"/></td>
                  <td><input disabled="true" name="libName12" type="text" class="input" value="<s:property value="#request.list.PHONE"/>" size="55" /></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="20"><s:text name="applicat"/><s:text name="fax"/></td>
                  <td><input disabled="true" name="libName14" type="text" class="input" size="55" value="<s:property value="#request.list.FAX"/>"/></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="20"><s:text name="applicat"/><s:text name="email"/></td>
                  <td><input disabled="true" name="libName15" type="text" class="input" value="<s:property value="#request.list.EMAIL"/>" size="55" /></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="20"><s:text name="examineorganization"/></td>
                  <td><input name="libName10" type="text" class="input" disabled="true" value="CCIC INSPECTION CO.,LTD" size="55" /></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="38" align="left"></td>
                  <td height="38" align="left"> 
                  	  <input  name="input" class="button" type="button" value="修改"  onclick="updateBussiness()" />                   
                      <input  name="input" class="button" type="button" value="返 回"  onclick="jump()" /></td>
                  <td align="left">&nbsp;</td>
                </tr>
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
