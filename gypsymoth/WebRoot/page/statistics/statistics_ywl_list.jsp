<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
 	isELIgnored="false"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel=stylesheet href="<%=basePath %>style/index.css" type="text/css" />
		<link rel=stylesheet href="<%=basePath %>style/right.css" type="text/css" />
		<title>中检集团船舶舞毒蛾检查业务信息系统</title>
		<%@ include file="../include/head.jsp"%>
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>/js/My97DatePicker/WdatePicker.js"></script>
	</head> 

<style>
html {
overflow: scroll;
overflow-x: hidden;
overflow-x: auto !important;
overflow-y: auto;
}
</style>

<body >
 
 	 <script language="text/javascript" type="text/javascript"> 
 		 $(document).ready(function(){
 		 	  
		 }); 
		 
		function toFifthExcel()
		{
			document.getElementById("fifthlistform").action="toFifthExcel.action";
			document.getElementById("fifthlistform").submit();
			return false;
		}
		 
	</script>
 <form id="fifthlistform" name="fifthlistform" method="post" action="">
 
 <!--内容开始-->
  <table border="0" cellspacing="0" cellpadding="0" class="contenttab">
  <tr>
    <td class="list_r">&nbsp;</td>
    <td valign="top" class="right"><div class="title">您的当前位置： 首页 &gt;&gt; 数据统计 &gt;&gt; <strong>客户业务量统计</strong></div>
      <div>
        <table width="100%" class="tableheader">
          <tr>
            <td width="5%" height="26"><img src="<%=basePath %>images/bg_mcontentL.gif"  width="32" height="27" /></td>
            <td valign="middle"><h3><strong>客户业务量统计</strong></h3></td>
            <td align="right" valign="middle"><input name="添加" type="button" onclick="javascript:toFifthExcel();" class="button_s" value="导出Excel表" /></td>
            <td width="1%" align="right"><img src="<%=basePath %>images/bg_mcontentR.gif"  width="7" height="27" /></td>
          </tr>
        </table>
        <table width="100%" class="tableCont">
          <tr>
            <td><div class="tableContent"> 
                <table width="100%" border="0" cellspacing="1" cellpadding="0">
                 
                    <tr>
                      <th width="15%">客户名称<input name="excel_area_hidden" type="hidden" value="客户名称"/> </th>
                      <s:iterator value="#request.arealist" id="area">
                      	<th> 
                      	<s:property value="#area.year"/>
                      	<s:if test="#request.statisicsby==1">年 <s:property value="#area.month"/>月
                      		<input name="excel_area_hidden" type="hidden" value="<s:property value="#area.year"/>年 <s:property value="#area.month"/>月"/></s:if>
                      	<s:elseif test="#request.statisicsby==2">年 第<s:property value="#area.quarter"/>季度
                      		<input name="excel_area_hidden" type="hidden" value="<s:property value="#area.year"/>年 第<s:property value="#area.quarter"/>季度"/></s:elseif>
                      	<s:elseif test="#request.statisicsby==3">年
                      		<input name="excel_area_hidden" type="hidden" value="<s:property value="#area.year"/>年"/>
                      	</s:elseif> 
                      	</th>
                      </s:iterator>
                      <th>合计<input name="excel_area_hidden" type="hidden" value="合计"/></th> 
                    </tr>
                    
                        <% List list = (List)request.getAttribute("list");
                           List arealist = (List)request.getAttribute("arealist");
                           if(list!=null&&list.size()>0){
                           	 for(int i=0;i<list.size();i++){
                           	 	out.write("<tr>");
                           	 	HashMap map = (HashMap)list.get(i);
                           	 	out.write("<td align=\"center\">&nbsp;&nbsp;"+map.get("BN").toString()+"<input name=\"excel_list_hidden\" type=\"hidden\" value=\""+map.get("BN").toString()+"\"/></td>");
                           	 	int totle_bn = 0;
                           	 	for(int j=0;j<arealist.size();j++){
                           	 		String cn = map.get("CN"+j).toString();
                           	 		out.write("<td align=\"center\">"+cn+"<input name=\"excel_list_hidden\" type=\"hidden\" value=\""+cn+"\"/></td>"); 
                           	 		totle_bn = totle_bn + Integer.valueOf(cn);
                           	 	}
                           	 	out.write("<td align=\"center\">"+totle_bn+"<input name=\"excel_list_hidden\" type=\"hidden\" value=\""+totle_bn+"\"/></td>"); 
                           	 	out.write("</tr>"); 
                           	 } 
                           }
                           
                        %>
                 
                   
                    <tr>
                      <td align="center">合　计<input name="excel_totle_hidden" type="hidden" value="合计"/></td>
	                      <s:if test="#request.totlelist.size()>0">
	                      <s:iterator value="#request.totlelist" id="totle">
	                      	<td align="center"><s:property value="#totle"/><input name="excel_totle_hidden" type="hidden" value="<s:property value="#totle"/>"/></td>
	                      </s:iterator>
                      	  </s:if>
                      	  <s:else>
	                      		<s:iterator value="#request.arealist">
			                    <td align="center">0&nbsp;</td>
			                 </s:iterator>
                    	  </s:else> 
                      <td align="center">&nbsp;<s:property value="#request.totle"/><input name="excel_totle_hidden" type="hidden" value="<s:property value="#request.totle"/>"/> </td>
                    </tr>
                
                </table>   
            </div></td>
          </tr>
        </table>
        <br />
        <table width="100%" class="tableheader">
          <tr>
            <td width="5%" height="26"><img src="<%=basePath %>images/bg_mcontentL.gif"  width="32" height="27" /></td>
            <td valign="middle"><h3><strong></strong>业务量</h3></td>
            <td align="right" valign="middle">&nbsp;</td>
            <td width="1%" align="right"><img src="<%=basePath %>images/bg_mcontentR.gif"  width="7" height="27" /></td>
          </tr>
        </table>
        <table width="100%" class="tableCont">
          <tr>
            <td><img src="${filename_top}" width="${top_weith }" height="${top_hight }" /></td>
          </tr>
        </table>
        <br />
        <table width="100%" class="tableheader">
          <tr>
            <td width="5%" height="26"><img src="<%=basePath %>images/bg_mcontentL.gif"  width="32" height="27" /></td>
            <td valign="middle"><h3><strong>月份统计</strong></h3></td>
            <td align="right" valign="middle">&nbsp;</td>
            <td width="1%" align="right"><img src="<%=basePath %>images/bg_mcontentR.gif"  width="7" height="27" /></td>
          </tr>
        </table>
        <table width="100%" class="tableCont">
          <tr>
            <td><img src="${filename_bottom }" width="${bootom_weith }" height="${bootom_hight }" /></td>
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
