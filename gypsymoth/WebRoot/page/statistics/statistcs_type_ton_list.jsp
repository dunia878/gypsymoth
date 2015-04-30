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
		 function toTypeExcel(){
			document.getElementById("typeform").action="getTypeExcel.action";
			document.getElementById("typeform").submit();
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
<form id="typeform" name="typeform" method="post" action="">
<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
  <tr>
    <td class="list_r">&nbsp;</td>
    <td valign="top" class="right"><div class="title">您的当前位置： 首页 &gt;&gt; 数据统计 &gt;&gt; <strong>船舶类型统计</strong></div>
      <div>
       
        <table  class="tableCont1" >
          <tr>
            <td>
             <table width="100%" class="tableheader" style="width:100%;">
          <tr>
            <td width="5%" height="26"><img src="${pageContext.request.contextPath}/images/bg_mcontentL.gif"  width="32" height="27" /></td>
            <td width="9%" valign="middle"><h3>统计</h3></td>
            <td width="37%" valign="middle">&nbsp;</td>
            <td width="48%" align="right" valign="middle"><input name="添加" type="button" onclick="javascript:toTypeExcel();" class="button_s" value="导出Excel表" /></td>
            <td width="1%" align="right"><img src="${pageContext.request.contextPath}/images/bg_mcontentR.gif"  width="7" height="27" /></td>
          </tr>
        </table>
            <div class="tableContent1">
                <table  border="0" cellspacing="1" cellpadding="0">
                  
                    <tr>
                      <th>机构名称<input name="excel_title_hidden" type="hidden" value="机构名称"/></th>
                      <s:if test="#request.judge == '-1'">
                      <th >Container Vessel<input name="excel_title_hidden" type="hidden" value="Container Vessel"/></th>
                      <th >General Cargo Ship<input name="excel_title_hidden" type="hidden" value="General Cargo Ship"/></th>
                      <th >Bulk Carrier<input name="excel_title_hidden" type="hidden" value="Bulk Carrier"/></th>
                      <th >Multi-purpose vessel<input name="excel_title_hidden" type="hidden" value="Multi-purpose vessel"/></th>
                      <th >Oil Tanker<input name="excel_title_hidden" type="hidden" value="Oil Tanker"/></th>
                      <th >Roll on/Roll off //Vessel<input name="excel_title_hidden" type="hidden" value="Roll on/Roll off //Vessel"/></th>
                      <th >Refrigerated Vessel<input name="excel_title_hidden" type="hidden" value="Refrigerated Vessel"/></th>
                      <th >Vehicle and Passenger Ferry<input name="excel_title_hidden" type="hidden" value="Vehicle and Passenger Ferry"/></th>
                      <th >Heavy-cargo Carrier<input name="excel_title_hidden" type="hidden" value="Heavy-cargo Carrier"/></th>
                      <th >Lighter Aboard Ship<input name="excel_title_hidden" type="hidden" value="Lighter Aboard Ship"/></th>
                      <th >Liquefied natural gas carrier--LNC<input name="excel_title_hidden" type="hidden" value="Liquefied natural gas carrier--LNC"/></th>
                      <th >Liquefied petroleum gas carrier--LPC<input name="excel_title_hidden" type="hidden" value="Liquefied petroleum gas carrier--LPC"/></th>                      
                      </s:if>
                      <s:if test="#request.judge == 'Container Vessel'">
                      <th >Container Vessel<input name="excel_title_hidden" type="hidden" value="Container Vessel"/></th>
                      </s:if>
                      <s:if test="#request.judge == 'General Cargo Ship'">
                      <th >General Cargo Ship<input name="excel_title_hidden" type="hidden" value="General Cargo Ship"/></th>
                      </s:if>
                      <s:if test="#request.judge == 'Bulk Carrier'">
                      <th >Bulk Carrier<input name="excel_title_hidden" type="hidden" value="Bulk Carrier"/></th>
                      </s:if>
                      <s:if test="#request.judge == 'Multi-purpose vessel'">
                      <th >Multi-purpose vessel<input name="excel_title_hidden" type="hidden" value="Multi-purpose vessel"/></th>
                      </s:if>
                      <s:if test="#request.judge == 'Oil Tanker'">
                      <th >Oil Tanker<input name="excel_title_hidden" type="hidden" value="Oil Tanker"/></th>
                      </s:if>
                      <s:if test="#request.judge == 'Roll on/Roll off //Vessel'">
                      <th >Roll on/Roll off //Vessel<input name="excel_title_hidden" type="hidden" value="Roll on/Roll off //Vessel"/></th>
                      </s:if>
                      <s:if test="#request.judge == 'Refrigerated Vessel'">
                      <th >Refrigerated Vessel<input name="excel_title_hidden" type="hidden" value="Refrigerated Vessel"/></th>
                      </s:if>
                      <s:if test="#request.judge == 'Vehicle and Passenger Ferry'">
                      <th >Vehicle and Passenger Ferry<input name="excel_title_hidden" type="hidden" value="Vehicle and Passenger Ferry"/></th>
                      </s:if>
                      <s:if test="#request.judge == 'Heavy-cargo Carrier'">
                      <th >Heavy-cargo Carrier<input name="excel_title_hidden" type="hidden" value="Heavy-cargo Carrier"/></th>
                      </s:if>
                      <s:if test="#request.judge == 'Lighter Aboard Ship'">
                      <th >Lighter Aboard Ship<input name="excel_title_hidden" type="hidden" value="Lighter Aboard Ship"/></th>
                      </s:if>
                      <s:if test="#request.judge == 'Liquefied natural gas carrier--LNC'"> 
                      <th >Liquefied natural gas carrier--LNC<input name="excel_title_hidden" type="hidden" value="Liquefied natural gas carrier--LNC"/></th>
                      </s:if>
                      <s:if test="#request.judge == 'Liquefied petroleum gas carrier--LPC'">
                      <th >Liquefied petroleum gas carrier--LPC<input name="excel_title_hidden" type="hidden" value="Liquefied petroleum gas carrier--LPC"/></th>
                      </s:if>                      
                      <th>合计<input name="excel_title_hidden" type="hidden" value="合计"/></th>
                    </tr>
                    <s:if test="#request.list.size>0">
						<s:iterator value="#request.list" status="stat">
                    <tr>
                      <td align="center"><s:property value="ORG_SNAME" /><input name="excel_list_hidden" type="hidden" value="<s:property value="ORG_SNAME" />"/></td>
                      <s:if test="#request.judge == '-1'">
                      <td align="center"><s:property value="CN1" /><input name="excel_list_hidden" type="hidden" value="<s:property value="CN1" />"/></td>
                      <td align="center"><s:property value="CN2" /><input name="excel_list_hidden" type="hidden" value="<s:property value="CN2" />"/></td>
                      <td  align="center"><s:property value="CN3" /><input name="excel_list_hidden" type="hidden" value="<s:property value="CN3" />"/></td>
                      <td  align="center"><s:property value="CN4" /><input name="excel_list_hidden" type="hidden" value="<s:property value="CN4" />"/></td>
                      <td  align="center"><s:property value="CN5" /><input name="excel_list_hidden" type="hidden" value="<s:property value="CN5" />"/></td>
                      <td  align="center"><s:property value="CN6" /><input name="excel_list_hidden" type="hidden" value="<s:property value="CN6" />"/></td>
                      <td  align="center"><s:property value="CN7" /><input name="excel_list_hidden" type="hidden" value="<s:property value="CN7" />"/></td>
                      <td  align="center"><s:property value="CN8" /><input name="excel_list_hidden" type="hidden" value="<s:property value="CN8" />"/></td>
                      <td  align="center"><s:property value="CN9" /><input name="excel_list_hidden" type="hidden" value="<s:property value="CN9" />"/></td>
                      <td  align="center"><s:property value="CN10" /><input name="excel_list_hidden" type="hidden" value="<s:property value="CN10" />"/></td>
                      <td  align="center"><s:property value="CN11" /><input name="excel_list_hidden" type="hidden" value="<s:property value="CN11" />"/></td>
                      <td  align="center"><s:property value="CN12" /><input name="excel_list_hidden" type="hidden" value="<s:property value="CN12" />"/></td>                      
                      </s:if>
                      <s:if test="#request.judge == 'Container Vessel'">
                      <td align="center"><s:property value="CN1" /><input name="excel_list_hidden" type="hidden" value="<s:property value="CN1" />"/></td>
                      </s:if>
                      <s:if test="#request.judge == 'General Cargo Ship'">
                      <td align="center"><s:property value="CN2" /><input name="excel_list_hidden" type="hidden" value="<s:property value="CN2" />"/></td>
                      </s:if>
                      <s:if test="#request.judge == 'Bulk Carrier'">
                      <td  align="center"><s:property value="CN3" /><input name="excel_list_hidden" type="hidden" value="<s:property value="CN3" />"/></td>
                      </s:if>
                      <s:if test="#request.judge == 'Multi-purpose vessel'">
                      <td  align="center"><s:property value="CN4" /><input name="excel_list_hidden" type="hidden" value="<s:property value="CN4" />"/></td>
                      </s:if>
                      <s:if test="#request.judge == 'Oil Tanker'">
                      <td  align="center"><s:property value="CN5" /><input name="excel_list_hidden" type="hidden" value="<s:property value="CN5" />"/></td>
                      </s:if>
                      <s:if test="#request.judge == 'Roll on/Roll off //Vessel'">
                      <td  align="center"><s:property value="CN6" /><input name="excel_list_hidden" type="hidden" value="<s:property value="CN6" />"/></td>
                      </s:if>
                      <s:if test="#request.judge == 'Refrigerated Vessel'">
                      <td  align="center"><s:property value="CN7" /><input name="excel_list_hidden" type="hidden" value="<s:property value="CN7" />"/></td>
                      </s:if>
                      <s:if test="#request.judge == 'Vehicle and Passenger Ferry'">
                      <td  align="center"><s:property value="CN8" /><input name="excel_list_hidden" type="hidden" value="<s:property value="CN8" />"/></td>
                      </s:if>
                      <s:if test="#request.judge == 'Heavy-cargo Carrier'">
                      <td  align="center"><s:property value="CN9" /><input name="excel_list_hidden" type="hidden" value="<s:property value="CN9" />"/></td>
                      </s:if>
                      <s:if test="#request.judge == 'Lighter Aboard Ship'">
                       <td  align="center"><s:property value="CN10" /><input name="excel_list_hidden" type="hidden" value="<s:property value="CN10" />"/></td>
                      </s:if>
                      <s:if test="#request.judge == 'Liquefied natural gas carrier--LNC'">
                      <td  align="center"><s:property value="CN11" /><input name="excel_list_hidden" type="hidden" value="<s:property value="CN11" />"/></td>
                      </s:if>
                      <s:if test="#request.judge == 'Liquefied petroleum gas carrier--LPC'">
                      <td  align="center"><s:property value="CN12" /><input name="excel_list_hidden" type="hidden" value="<s:property value="CN12" />"/></td>
                      </s:if>
                      <s:if test="#request.judge == '-1'">
                      <s:if test="#request.fp == 'fp'">
                      <td  align="center"><s:property value="#request.val" /><input name="excel_list_hidden" type="hidden" value="<s:property value="#request.val" />"/></td>                                             
                      </s:if>
                      <s:else>
                      <td align="center"><s:property value="CN1+CN2+CN3+CN4+CN5+CN6+CN7+CN8+CN9+CN10+CN11+CN12" /><input name="excel_list_hidden" type="hidden" value="<s:property value="CN1+CN2+CN3+CN4+CN5+CN6+CN7+CN8+CN9+CN10+CN11+CN12" />"/></td>                      
                      </s:else>
                      </s:if>
                      <s:if test="#request.judge == 'Container Vessel'">
                      <td align="center"><s:property value="CN1" /><input name="excel_list_hidden" type="hidden" value="<s:property value="CN1" />"/></td>
                      </s:if>
                      <s:if test="#request.judge == 'General Cargo Ship'">
                      <td align="center"><s:property value="CN2" /><input name="excel_list_hidden" type="hidden" value="<s:property value="CN1" />"/></td>
                      </s:if>
                      <s:if test="#request.judge == 'Bulk Carrier'">
                      <td align="center"><s:property value="CN3" /><input name="excel_list_hidden" type="hidden" value="<s:property value="CN1" />"/></td>
                      </s:if>
                      <s:if test="#request.judge == 'Multi-purpose vessel'">
                      <td align="center"><s:property value="CN4" /><input name="excel_list_hidden" type="hidden" value="<s:property value="CN1" />"/></td>
                      </s:if>
                      <s:if test="#request.judge == 'Oil Tanker'">
                      <td align="center"><s:property value="CN5" /><input name="excel_list_hidden" type="hidden" value="<s:property value="CN1" />"/></td>
                      </s:if>
                      <s:if test="#request.judge == 'Roll on/Roll off //Vessel'">
                      <td align="center"><s:property value="CN6" /><input name="excel_list_hidden" type="hidden" value="<s:property value="CN1" />"/></td>
                      </s:if>
                      <s:if test="#request.judge == 'Refrigerated Vessel'">
                      <td align="center"><s:property value="CN7" /><input name="excel_list_hidden" type="hidden" value="<s:property value="CN1" />"/></td>
                      </s:if>
                      <s:if test="#request.judge == 'Vehicle and Passenger Ferry'">
                      <td align="center"><s:property value="CN8" /><input name="excel_list_hidden" type="hidden" value="<s:property value="CN1" />"/></td>
                      </s:if>
                      <s:if test="#request.judge == 'Heavy-cargo Carrier'">
                      <td align="center"><s:property value="CN9" /><input name="excel_list_hidden" type="hidden" value="<s:property value="CN1" />"/></td>
                      </s:if>
                      <s:if test="#request.judge == 'Lighter Aboard Ship'">
                      <td align="center"><s:property value="CN10" /><input name="excel_list_hidden" type="hidden" value="<s:property value="CN1" />"/></td>
                      </s:if>
                      <s:if test="#request.judge == 'Liquefied natural gas carrier--LNC'">
                      <td align="center"><s:property value="CN11" /><input name="excel_list_hidden" type="hidden" value="<s:property value="CN1" />"/></td>
                      </s:if>
                      <s:if test="#request.judge == 'Liquefied petroleum gas carrier--LPC'">
                      <td align="center"><s:property value="CN12" /><input name="excel_list_hidden" type="hidden" value="<s:property value="CN1" />"/></td>
                      </s:if>
                           
                    </tr>
                    </s:iterator>
                    </s:if>                    
                    <tr>
                      <td align="center">合　计<input name="excel_totle_hidden" type="hidden" value="合计"/></td>
                      <s:if test="#request.judge == '-1'">                     
                      <td  align="center"><s:property value="#request.C1" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="#request.C1" />"/></td>
                      <td  align="center"><s:property value="#request.C2" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="#request.C2" />"/></td>
                      <td  align="center"><s:property value="#request.C3" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="#request.C3" />"/></td>
                      <td  align="center"><s:property value="#request.C4" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="#request.C4" />"/></td>
                      <td  align="center"><s:property value="#request.C5" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="#request.C5" />"/></td>
                      <td  align="center"><s:property value="#request.C6" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="#request.C6" />"/></td>
                      <td  align="center"><s:property value="#request.C7" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="#request.C7" />"/></td>
                      <td  align="center"><s:property value="#request.C8" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="#request.C8" />"/></td>
                      <td  align="center"><s:property value="#request.C9" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="#request.C9" />"/></td>
                      <td  align="center"><s:property value="#request.C10" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="#request.C10" />"/></td>
                      <td  align="center"><s:property value="#request.C11" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="#request.C11" />"/></td>
                      <td  align="center"><s:property value="#request.C12" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="#request.C12" />"/></td>
                      <td align="center"><s:property value="#request.total" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="#request.total" />"/></td>
                      </s:if>
                      <s:if test="#request.judge == 'Container Vessel'">
                      <td  align="center"><s:property value="#request.C1" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="#request.C1" />"/></td>                      
                      <td  align="center"><s:property value="#request.C1" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="#request.C1" />"/></td>                      
                      </s:if>
                      <s:if test="#request.judge == 'General Cargo Ship'">
                      <td  align="center"><s:property value="#request.C2" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="#request.C2" />"/></td>                     
                      <td  align="center"><s:property value="#request.C2" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="#request.C2" />"/></td>                     
                      </s:if>
                      <s:if test="#request.judge == 'Bulk Carrier'">
                      <td  align="center"><s:property value="#request.C3" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="#request.C3" />"/></td>                      
                      <td  align="center"><s:property value="#request.C3" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="#request.C3" />"/></td>                      
                      </s:if>
                      <s:if test="#request.judge == 'Multi-purpose vessel'">
                      <td  align="center"><s:property value="#request.C4" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="#request.C4" />"/></td>                      
                      <td  align="center"><s:property value="#request.C4" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="#request.C4" />"/></td>                      
                      </s:if>
                      <s:if test="#request.judge == 'Oil Tanker'">
                      <td  align="center"><s:property value="#request.C5" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="#request.C5" />"/></td>
                      <td  align="center"><s:property value="#request.C5" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="#request.C5" />"/></td>
                      </s:if>
                      <s:if test="#request.judge == 'Roll on/Roll off //Vessel'">
                       <td  align="center"><s:property value="#request.C6" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="#request.C6" />"/></td>                     
                       <td  align="center"><s:property value="#request.C6" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="#request.C6" />"/></td>                     
                      </s:if>
                      <s:if test="#request.judge == 'Refrigerated Vessel'">
                      <td  align="center"><s:property value="#request.C7" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="#request.C7" />"/></td>
                      <td  align="center"><s:property value="#request.C7" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="#request.C7" />"/></td>
                      </s:if>
                      <s:if test="#request.judge == 'Vehicle and Passenger Ferry'">
                      <td  align="center"><s:property value="#request.C8" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="#request.C8" />"/></td>
                      <td  align="center"><s:property value="#request.C8" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="#request.C8" />"/></td>
                      </s:if>
                      <s:if test="#request.judge == 'Heavy-cargo Carrier'">
                       <td  align="center"><s:property value="#request.C9" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="#request.C9" />"/></td>                     
                       <td  align="center"><s:property value="#request.C9" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="#request.C9" />"/></td>                     
                      </s:if>
                      <s:if test="#request.judge == 'Lighter Aboard Ship'">
                      <td  align="center"><s:property value="#request.C10" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="#request.C10" />"/></td>                      
                      <td  align="center"><s:property value="#request.C10" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="#request.C10" />"/></td>                      
                      </s:if>
                      <s:if test="#request.judge == 'Liquefied natural gas carrier--LNC'">
                      <td  align="center"><s:property value="#request.C11" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="#request.C11" />"/></td>
                      <td  align="center"><s:property value="#request.C11" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="#request.C11" />"/></td>
                      </s:if>
                      <s:if test="#request.judge == 'Liquefied petroleum gas carrier--LPC'">
                      <td  align="center"><s:property value="#request.C12" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="#request.C12" />"/></td>
                      <td  align="center"><s:property value="#request.C12" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="#request.C12" />"/></td>
                      </s:if>                 
                    </tr>                         
                </table>
            </div></td>
          </tr>
        </table>
        <br />
        <table width="100%" class="tableheader" style="width:100%;">
          <tr>
            <td width="5%" height="26"><img src="${pageContext.request.contextPath}/images/bg_mcontentL.gif"  width="32" height="27" /></td>
            <td valign="middle"><h3>机构统计</h3></td>
            <td align="right" valign="middle">&nbsp;</td>
            <td width="1%" align="right"><img src="${pageContext.request.contextPath}/images/bg_mcontentR.gif"  width="7" height="27" /></td>
          </tr>
        </table>
        <table width="100%" class="tableCont">
          <tr>
            <td align="center"><img src="${orgImg}" align="left"/></td>
          </tr>
        </table>
        <br />
        <table width="100%" class="tableheader" style="width:100%;">
          <tr>
            <td width="5%" height="26"><img src="${pageContext.request.contextPath}/images/bg_mcontentL.gif"  width="32" height="27" /></td>
            <td valign="middle"><h3><strong>类型统计</strong></h3></td>
            <td align="right" valign="middle">&nbsp;</td>
            <td width="1%" align="right"><img src="${pageContext.request.contextPath}/images/bg_mcontentR.gif"  width="7" height="27" /></td>
          </tr>
        </table>
        <table width="100%" class="tableCont" style="width:100%;">
          <tr>
            <td><img src="${typeImg}" /></td>
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
