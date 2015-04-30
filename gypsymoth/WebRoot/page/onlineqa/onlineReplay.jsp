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
		<script language="JavaScript" type="text/javascript">
function save(){
document.form1.method="post";
document.form1.action="updataOnlineqa.action";
document.form1.submit();

}

 </script>
	<body>
<table border="0" cellspacing="0" cellpadding="0" class="contenttab">

  <tr>
    <td class="list_r">&nbsp;</td>
    <td valign="top" class="right"><div class="title">您的当前位置： 首页 &gt;&gt; 在线答疑 &gt;&gt; <strong>客户反馈专区</strong></div>
      <div>
        <table align="center" cellpadding="12" class="tabedit">
          <tr>
            <td height="300" align="left" valign="top"><!--编辑开始-->
                <table width="700" align="center" cellspacing="5">
                <s:form theme="simple" method="post" action="" id="form1" name="form1">
                   <input type="hidden" name="onlineqa.id" id="onlineqa.id" value="<s:property value="onlineqa.id"/>" />
                   <input type="hidden" name="onlineqa.time" id="onlineqa.time" value="<s:property value="onlineqa.time"/>" />
                   <input type="hidden" name="onlineqa.clientname" id="onlineqa.clientname" value="<s:property value="onlineqa.clientname"/>" />
                  <tr>
                    <td width="68" height="20">标　　题</td>
                    <td width="332"><label>
                      <input name="onlineqa.title" type="text" readonly="true" class="input" value="<s:property value="onlineqa.title"/>"  size="55" />
                    </label></td>
                    <td width="272">&nbsp;</td>
                  </tr>
                  <tr>
                    <td height="20">内　　容</td>
                    <td><s:textarea name="onlineqa.content" readonly="true"   id="content"   cols="53" rows="5" ></s:textarea></td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td height="20">备　　注</td>
                    <td><input name="onlineqa.remark" type="text" readonly="true" class="input" size="55" value="<s:property value="onlineqa.remark"/>"/></td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td height="20">回　　答</td>
                    <td><s:textarea name="onlineqa.namefor"  id="namefor"   cols="53" rows="13"></s:textarea></td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td height="20">&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                  
                  <tr>
                    <td height="20">&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td height="38" align="left"></td>
                    <td height="38" align="left"><input name="input2" class="button" type="submit" onclick="save()" value="提 交" />
                        <input name="input5" class="button" type="reset" value="重 置" />
                        <input name="input" class="button" type="reset" value="返 回"  onclick="history.back(-1)" />                    </td>
                    <td align="left">&nbsp;</td>
                  </tr></s:form>
                </table>
            <!--编辑结束-->            </td>
          </tr>
        </table>
      </div></td>
  </tr>
</table>
</body>
</html>