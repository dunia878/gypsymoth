<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>资源列表</title>
<link rel=stylesheet href="${pageContext.request.contextPath}/style/index.css" type="text/css" />
<link rel=stylesheet href="${pageContext.request.contextPath}/style/right.css" type="text/css" />
<script>
function f1(chk){
 var t = document.getElementById("txt1");
 t.disabled = !chk.checked;
}
</script>

</head>
<body>
<!--内容开始-->
<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
<s:form action="updatePort" theme="simple" id="updatePort" enctype ="multipart/form-data" method="post">
  <tr>
    <td class="list_r">&nbsp;</td>
    <td valign="top" class="right"><div class="title"><s:text name="local" />： <s:text name="home" /> &gt;&gt; <s:text name="orgAndPerson" /> &gt;&gt; <strong><s:text name="OrganizationManage" /></strong></div>
      <div>
        <table align="center" cellpadding="12" class="tabedit">
          <tr>
            <td height="300" align="left" valign="top"><!--编辑开始-->
                  <table width="706" align="center" cellspacing="5">
                     <tr>
                      <td><s:text name="portID" /></td>
                      <td><input name="pId" type="text" class="input" disabled="true"  value="<s:property value="#request.list.PORT_ID"/>" size="55" /></td>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                      <td height="20"><s:text name="ChineseName"/></td>
                      <td><input name="portname" type="text" class="input" disabled="true"  value="<s:property value="#request.list.PORT_NAME"/>" size="55" /></td>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                      <td height="20"><s:text name="EnglishName"/></td>
                      <td><input name="portsname" type="text" class="input" disabled="true"  value="<s:property value="#request.list.PORT_SNAME"/>" size="55" /></td>
                      <td>&nbsp;</td>
                    </tr>
                    
                    <tr>
                      <td height="20">&nbsp;</td>
                      <td>&nbsp;</td>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                      <td height="38" align="left"><input type="hidden" name="portId" value="<s:property value="#request.list.ID"/>"/></td>
                      <td height="38" align="left">                      	   
                          <input name="input" class="button" type="reset" value="<s:text name="return" />"  onclick="history.back(-1)" /></td>
                      <td align="left">&nbsp;</td>
                    </tr>
                  </table>
              <!--编辑结束-->
            </td>
          </tr>
        </table>
      </div></td>
  </tr>
  </s:form>
</table>
</body>
</html>