<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统</title>
		<%@ include file="../include/head.jsp"%>
	</head>


	<!-- JS验证表单部分 -->
	<script type="text/javascript">
   function gotoSubmit()
    {
    		this.document.forms[0].submit();
     }
  </script>


	<body>
	<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
  <tr>
    <td class="list_r">&nbsp;</td>
    <td valign="top" class="right"><div class="title"><s:text name="您的当前位置"/>： <s:text name="首页"/> &gt;&gt; <s:text name="在线答疑"/> &gt;&gt; <strong><s:text name="客户反馈专区"/></strong></div>
      <div>
        <table align="center" cellpadding="12" class="tabedit">
          <tr>
            <td height="300" align="left" valign="top"><!--编辑开始-->
              <table width="700" align="center" cellspacing="5">
                <tr>
                  <td width="68" height="20"><s:text name="标题"/></td>
                  <td width="332"><label>
                    <input name="onlineqa.title" type="text" readonly="true" class="input" value="<s:property value="onlineqa.title"/>" size="55" />
                  </label></td>
                  <td width="272">&nbsp;</td>
                </tr>
                <tr>
                  <td height="20"><s:text name="内容"/></td>
                  <td><s:textarea name="onlineqa.content" readonly="true"  id="content"   cols="53" rows="5" ></s:textarea></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="20"><s:text name="备注"/></td>
                  <td><input name="remark" type="text" readonly="true" class="input" size="55" value="<s:property value="onlineqa.remark"/>"/></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="20"><s:text name="回答"/></td>
                  <td><s:textarea name="onlineqa.namefor"  id="namefor" readonly="true" cols="53" rows="9" ></s:textarea></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="15">&nbsp;</td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                <s:if test="%{onlineqa.state == 2}">
                  <td height="20"><s:text name="回答人"/></td>
                  <td> <s:property value="onlineqa.answer" /></td>
                  <td>&nbsp;</td>
                </s:if></tr>
                <tr>
                  <td height="20">&nbsp;</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                </tr>
                
                <tr>
                  <td height="38" align="left"></td>
                  <td height="38" align="left"><input name="input" class="button" type="reset" value="<s:text name='return'/>"  onclick="history.back(-1)" />   </td>
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
