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
	<body>
	
<script language="text/javascript" type="text/javascript"> 
	$(document).ready(function(){
		 
		
	});
	 
 
</script>	
 
  
<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
  <tr>
    <td class="list_r">&nbsp;</td>
    <td valign="top" class="right"><div class="title">您的当前位置： 首页 &gt;&gt; 业务管理 &gt;&gt;证书管理 &gt;&gt; <strong>空白证书管理</strong></div>
      <div>
        <table align="center" cellpadding="12" class="tabedit">
          <tr>
            <td height="300" align="left" valign="top"><!--编辑开始-->
              <table width="706" align="center" cellspacing="5">
                <tr>
                  <th width="180" height="20">证书编号区间</th>
                  <th width="332">证书编号</th>
                  <th width="272">&nbsp;</th>
                </tr>
                <s:iterator value="#request.certlist" id="mop">
	                <tr>
	                  <td><s:property value="CERTNAME" /></td>
	                  <td><s:property value="CERTNO" /></td> 
	                  <td>&nbsp;</td> 
	                </tr> 
                </s:iterator>
                <tr>
                  <td height="20">&nbsp;</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="38" align="left"></td>
                  <td height="38" align="left"> 
                      <input  name="input" class="button" type="reset" value="返 回"  onclick="history.back(-1);" />
                  </td>
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
