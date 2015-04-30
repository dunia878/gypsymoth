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
		 function toTonExcel(){
			document.getElementById("tonform").action="getTonExcel.action";
			document.getElementById("tonform").submit();
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
<!--内容开始-->
<form id="tonform" name="tonform" method="post" action="">
<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
  <tr>
    <td class="list_r">&nbsp;</td>
    <td valign="top" class="right"><div class="title">您的当前位置： 首页 &gt;&gt; 数据统计 &gt;&gt; <strong>船舶吨位类型统计</strong></div>
      <div>
        
        <table  class="tableCont1" >
          <tr>
            <td>
            <table width="100%" class="tableheader" style="width:100%;">
          <tr>
            <td width="5%" height="26"><img src="${pageContext.request.contextPath}/images/bg_mcontentL.gif"  width="32" height="27" /></td>
            <td width="15%" valign="middle"><h3>业务量</h3></td>
            <td  valign="middle">&nbsp;</td>
            <td width="48%" align="right" valign="middle"><input name="添加" type="button" onclick="javascript:toTonExcel();" class="button_s" value="导出Excel表" /></td>
            <td width="1%" align="right"><img src="${pageContext.request.contextPath}/images/bg_mcontentR.gif"  width="7" height="27" /></td>
          </tr>
        </table>
            <div class="tableContent1">
              <table width="100%" border="0" cellspacing="1" cellpadding="0">                
                  <tr>
                    <th width="100">机构名称<input name="excel_title_hidden" type="hidden" value="机构名称"/></th>
                     <s:iterator value="#request.titlelist" id="title">
                      	<th width="100"> 
                      		<s:property value="#title.title"/>吨<input name="excel_title_hidden" type="hidden" value="<s:property value="#title.title"/>吨"/>
                      	</th>
                      </s:iterator>                    
                    <th width="100">合计<input name="excel_title_hidden" type="hidden" value="合计"/></th>
                  </tr>
                 <% List list = (List)request.getAttribute("list");
                           List titlelist = (List)request.getAttribute("titlelist");
                           if(list!=null&&list.size()>0){
                           	 for(int i=0;i<list.size();i++){
                           	 	out.write("<tr>");
                           	 	HashMap map = (HashMap)list.get(i);
                           	 	out.write("<td align=\"center\">&nbsp;&nbsp;"+map.get("ORG_SNAME").toString()+"<input name=\"excel_list_hidden\" type=\"hidden\" value=\""+map.get("ORG_SNAME").toString()+"\"/></td>");
                           	 	int totle = 0;
                           	 	for(int j=0;j<titlelist.size();j++){
                           	 		String cn = map.get("CN"+j).toString();
                           	 		out.write("<td align=\"center\">"+cn+"<input name=\"excel_list_hidden\" type=\"hidden\" value=\""+cn+"\"/></td>"); 
                           	 		totle= totle + Integer.valueOf(cn);
                           	 	}
                           	 	out.write("<td align=\"center\">"+totle+"<input name=\"excel_list_hidden\" type=\"hidden\" value=\""+totle+"\"/></td>"); 
                           	 	out.write("</tr>"); 
                           	 } 
                           }
                           
                        %>
                        <tr>
                      <td align="center">合　计<input name="excel_totle_hidden" type="hidden" value="合　计"/></td>
                      <s:if test="#request.totlelist.size()>0">
	                      <s:iterator value="#request.totlelist" id="totle">
	                      	<td align="center"><s:property value="#totle"/><input name="excel_totle_hidden" type="hidden" value="<s:property value="#totle"/>"/></td>
	                      </s:iterator>
                      	  </s:if>
                      	  <s:else>
	                      	<s:iterator value="#request.titlelist">
			                    <td align="center">&nbsp;</td>
			                 </s:iterator>
                    	  </s:else> 
                      <td align="center"><s:property value="#request.total" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="#request.totle"/>"/></td>
                    </tr>
              </table>
            </div></td>
          </tr>
        </table>
        <br />
        <table width="100%" class="tableheader" style="width:100%;">
          <tr>
            <td width="5%" height="26"><img src="${pageContext.request.contextPath}/images/bg_mcontentL.gif"  width="32" height="27" /></td>
            <td valign="middle"><h3><strong>公司统计</strong></h3></td>
            <td align="right" valign="middle">&nbsp;</td>
            <td width="1%" align="right"><img src="${pageContext.request.contextPath}/images/bg_mcontentR.gif"  width="7" height="27" /></td>
          </tr>
        </table>
        <table width="100%" class="tableCont" style="width:100%;">
          <tr>
            <td align="left"><img src="${orgImg}" /></td>
          </tr>
        </table>
        <br />
        <table width="100%" class="tableheader" style="width:100%;">
          <tr>
            <td width="5%" height="26"><img src="${pageContext.request.contextPath}/images/bg_mcontentL.gif"  width="32" height="27" /></td>
            <td valign="middle"><h3><strong>吨位统计</strong></h3></td>
            <td align="right" valign="middle">&nbsp;</td>
            <td width="1%" align="right"><img src="${pageContext.request.contextPath}/images/bg_mcontentR.gif"  width="7" height="27" /></td>
          </tr>
        </table>
        <table width="100%" class="tableCont" style="width:100%;">
          <tr>
            <td><img src="${tonImg}" /></td>
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
