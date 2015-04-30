<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>资源列表</title>
<link rel=stylesheet href="${pageContext.request.contextPath}/style/index.css" type="text/css" />
<link rel=stylesheet href="${pageContext.request.contextPath}/style/right.css" type="text/css" />
<%@ include file="../include/head.jsp"%>
<script language="text/javascript" type="text/javascript"> 
 		 $(document).ready(function(){
 		 	 
		 });
		 function toWormExcel(){
			document.getElementById("wormform").action="getWormExcel.action";
			document.getElementById("wormform").submit();
			return false;
		}		 
	</script>

<style>
html {
overflow: scroll;
overflow-x: hidden;
overflow-x: auto !important;
overflow-y: auto;
}
</style>
</head>
<body>
 <form id="wormform" name="wormform" method="post" action="">
<!--内容开始-->
<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
  <tr>
    <td class="list_r">&nbsp;</td>
    <td valign="top" class="right"><div class="title">您的当前位置： 首页 &gt;&gt; 数据统计 &gt;&gt; <strong>虫态数量统计</strong></div>
      <div>
 
        <table  class="tableCont1">
          <tr>
            <td>
                   <table  class="tableheader" style="width:100%;">
          <tr>
            <td width="5%" height="26"><img src="${pageContext.request.contextPath}/images/bg_mcontentL.gif"  width="32" height="27" align="left" /></td>
            <td width="9%" valign="middle"><h3>统计</h3></td>
            <td width="37%" valign="middle">&nbsp;</td>
            <td width="48%" align="right" valign="middle"><input name="添加" type="button" class="button_s" onclick="javascript:toWormExcel();" value="导出Excel表" /></td>
            <td width="1%" align="right"><img src="${pageContext.request.contextPath}/images/bg_mcontentR.gif"  width="7" height="27" align="left" /></td>
          </tr>
        </table>
            <div class="tableContent1">
                <table  border="0" cellspacing="1" cellpadding="0">
                  
                    <tr>
                      <th>机构名称<input name="excel_title_hidden" type="hidden" value="机构名称"/></th>
                      <th>卵块<input name="excel_title_hidden" type="hidden" value="卵块"/></th>
                      <th>成虫<input name="excel_title_hidden" type="hidden" value="成虫"/></th>
                      <th>幼虫<input name="excel_title_hidden" type="hidden" value="幼虫"/></th>
                      <th>蛹<input name="excel_title_hidden" type="hidden" value="蛹"/></th>
                      <th>疑似<input name="excel_title_hidden" type="hidden" value="疑似"/></th>
                      <th>合计<input name="excel_title_hidden" type="hidden" value="合计"/></th>
                    </tr>
                    <s:if test="#request.list.size>0">
						<s:iterator value="#request.list" status="stat">
                    <tr>
                      <td align="center"><s:property value="ORG_SNAME" /><input name="excel_list_hidden" type="hidden" value="<s:property value="ORG_SNAME" />"/></td>
                      <td align="center"><s:property value="CN1" /><input name="excel_list_hidden" type="hidden" value="<s:property value="CN1" />"/></td>
                      <td align="center"><s:property value="CN2" /><input name="excel_list_hidden" type="hidden" value="<s:property value="CN2" />"/></td>
                      <td align="center"><s:property value="CN3" /><input name="excel_list_hidden" type="hidden" value="<s:property value="CN3" />"/></td>
                      <td  align="center"><s:property value="CN4" /><input name="excel_list_hidden" type="hidden" value="<s:property value="CN4" />"/></td>
                      <td  align="center"><s:property value="CN5" /><input name="excel_list_hidden" type="hidden" value="<s:property value="CN5" />"/></td>
                      <s:if test="#request.fp == 'fp'">
                      <td  align="center"><s:property value="#request.val" /><input name="excel_list_hidden" type="hidden" value="<s:property value="#request.val" />"/></td>                                             
                      </s:if>
                      <s:else>
                      <td  align="center"><s:property value="CN1+CN2+CN3+CN4+CN5" /><input name="excel_list_hidden" type="hidden" value="<s:property value="CN1+CN2+CN3+CN4+CN5" />"/></td>                       
                      </s:else>
                    </tr>
                    </s:iterator>
                    </s:if>                    
                    <tr>
                      <td align="center">合　计<input name="excel_totle_hidden" type="hidden" value="合计"/></td>
                      <td  align="center"><s:property value="#request.G1" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="#request.G1" />"/></td>
                      <td  align="center"><s:property value="#request.G2" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="#request.G2" />"/></td>
                      <td  align="center"><s:property value="#request.G3" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="#request.G3" />"/></td>
                      <td  align="center"><s:property value="#request.G4" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="#request.G4" />"/></td>
                      <td  align="center"><s:property value="#request.G5" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="#request.G5" />"/></td>
                      <td align="center"><s:property value="#request.total" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="#request.total" />"/></td>
                    </tr>
					
                </table>
            </div></td>
          </tr>
        </table>
        <br />
        <table width="100%" class="tableheader" style="width:100%;">
          <tr>
            <td width="5%" height="26"><img src="${pageContext.request.contextPath}/images/bg_mcontentL.gif"  width="32" height="27" align="left" /></td>
            <td valign="middle"><h3>机构统计</h3></td>
            <td align="right" valign="middle">&nbsp;</td>
            <td width="1%" align="right"><img src="${pageContext.request.contextPath}/images/bg_mcontentR.gif"  width="7" height="27" align="left" /></td>
          </tr>
        </table>
        <table width="100%" class="tableCont" style="width:100%;">
          <tr>
            <td align="center"><img src="${orgImg}" align="left" /></td>
          </tr>
        </table>
        <br />
        <table width="100%" class="tableheader" style="width:100%;">
          <tr>
            <td width="5%" height="26"><img src="${pageContext.request.contextPath}/images/bg_mcontentL.gif"  width="32" height="27" align="left"  /></td>
            <td valign="middle"><h3><strong>类型统计</strong></h3></td>
            <td align="right" valign="middle">&nbsp;</td>
            <td width="1%" align="right"><img src="${pageContext.request.contextPath}/images/bg_mcontentR.gif"  width="7" height="27" align="left"  /></td>
          </tr>
        </table>
        <table width="100%" class="tableCont" style="width:100%;">
          <tr>
            <td><img src="${oneImg}" align="left" /></td>
          </tr>
        </table>
      </div></td>
  </tr>
</table>
<br/>
						<center>
						<input name="" class="button" onclick="window.close();" style="cursor:pointer;" type="button" value="关 闭" />
						</center>
</form>
</body>
</html>
