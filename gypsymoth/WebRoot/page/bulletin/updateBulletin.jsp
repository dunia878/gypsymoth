<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel=stylesheet href="../../style/index.css" type="text/css" />
    <link rel=stylesheet href="../../style/right.css" type="text/css" />

  </head>
  
  <!-- JS验证表单部分 -->
<script type="text/javascript">
   function gotoSubmit()
    {
    		this.document.forms[0].submit();
     }
  </script>
  
  <body>
   <!--内容开始-->
<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
  <tr>
    <td class="list_r">&nbsp;</td>
    <td valign="top" class="right"><div class="title">您的当前位置： 首页 &gt;&gt; 信息资料管理 &gt;&gt; <strong>公告通知</strong></div>
      <div>
        <table align="center" cellpadding="12" class="tabedit">
          <tr>
            <td height="300" align="left" valign="top"><!--编辑开始-->
                <form action="gonggao_success.htm" method="get">
                <table width="706" align="center" cellspacing="5">
                <s:hidden name="bulletin.bulletinId"></s:hidden>
                  <tr>
                    <td width="68" height="20">标　　题</td>
                    <td width="332"><s:text name="bulletinName" value="%{bulletin.bulletinName}"></s:text></td>
                    <td width="272">&nbsp;</td>
                  </tr>
                  <tr>
                    <td height="20">内　　容</td>
                    <td><s:textfield name="bulletin.bulletinContent"></s:textfield></td>
                    <td>&nbsp;</td>
                  </tr>

                  <tr>
                    <td height="20">查看权限</td>
                    <td><s:radio list="#{1:'用户',2:'客户'}" name="bulletin.bulletinTip" ></s:radio></td>
                    <td>&nbsp;</td>
                  </tr>
                  
                  <tr>
                    <td height="20">&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td height="38" align="left"></td>
                    <td height="38" align="left"><input name="input3" class="button" type="button"  onclick="gotoSubmit()" value="保 存" />
                        <input name="reset" class="button" type="reset" value="重 置" />
                        <input name="return" class="button" type="button" value="返 回"  onclick="history.back(-1)" /></td>
                    <td align="left">&nbsp;</td>
                  </tr>
                </table>
              </form>
              <!--编辑结束-->
            </td>
          </tr>
        </table>
      </div></td>
  </tr>
</table>
  </body>


