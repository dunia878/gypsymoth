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

<body>
 
 	 <script language="text/javascript" type="text/javascript"> 
 		 $(document).ready(function(){
 		 	 
		 });
		 function toSecondExcel(){
			document.getElementById("secondform").action="toSecondExcel.action";
			document.getElementById("secondform").submit();
			return false;
		}
		
		function toSecondExcelOther(){
			document.getElementById("secondform").action="toSecondExcelOther.action";
			document.getElementById("secondform").submit();
			return false;
		}
		 
	</script>
 
 <!--内容开始-->
  <form id="secondform" name="secondform" method="post" action="">
 
  <table border="0" cellspacing="0" cellpadding="0" class="contenttab">
  <tr>
    <td class="list_r">&nbsp;</td>
    <td valign="top" class="right"><div class="title">您的当前位置： 首页 &gt;&gt; 数据统计 &gt;&gt; <strong>目的国业务量统计</strong></div>
      <div>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="65%">
	            <table width="100%" class="tableheader">
	              <tr>
	                <td width="4%" height="26"><img src="<%=basePath %>images/bg_mcontentL.gif"  width="32" height="27" /></td>
	                <td width="28%" valign="middle"><h3><strong>目的国业务量统计</strong></h3></td>
	                <td width="67%" align="right" valign="middle"><input name="添加" type="button" onclick="javascript:toSecondExcel();" class="button_s" value="导出Excel表" /></td>
	                <td width="1%" align="right"><img src="<%=basePath %>images/bg_mcontentR.gif"  width="7" height="27" /></td>
	              </tr>
	            </table>
	            
              <table width="100%" class="tableCont">
                <tr>
                  <td><div class="tableContent">
                      <table width="100%" border="0" cellspacing="1" cellpadding="0">
                          <tr>
                            <th>国　家<input name="excel_area_hidden" type="hidden" value="国家"/></th>
                            <th width="15%" >美　　国<input name="excel_area_hidden" type="hidden" value="美国"/></th>
                            <th width="15%">加 拿 大<input name="excel_area_hidden" type="hidden" value="加拿大"/></th>
                            <th width="15%">墨 西 哥<input name="excel_area_hidden" type="hidden" value="墨西哥"/></th>
                            <th width="15%">其它国家<input name="excel_area_hidden" type="hidden" value="其他国家"/></th>
                            <th>合计<input name="excel_area_hidden" type="hidden" value="合计"/></th>
                          </tr>
                          <tr>
                            <td align="center">美　　国<input name="excel_list_hidden" type="hidden" value="美国"/></td>
                            <td width="15%" align="center"><s:property value="#request.oneMap.USA"/><input name="excel_list_hidden" type="hidden" value="<s:property value="#request.oneMap.USA"/>"/> </td>
                            <td width="15%" align="center"><s:property value="#request.oneMap.CANADA_USA"/><input name="excel_list_hidden" type="hidden" value="<s:property value="#request.oneMap.CANADA_USA"/>"/></td>
                            <td width="15%" align="center"><s:property value="#request.oneMap.MEXICO_USA"/><input name="excel_list_hidden" type="hidden" value="<s:property value="#request.oneMap.MEXICO_USA"/>"/></td>
                            <td width="15%" align="center"><s:property value="#request.oneMap.OTHER_USA"/><input name="excel_list_hidden" type="hidden" value="<s:property value="#request.oneMap.OTHER_USA"/>"/></td>
                            <td align="center"><s:property value="%{#request.oneMap.USA+#request.oneMap.CANADA_USA+#request.oneMap.MEXICO_USA+#request.oneMap.OTHER_USA}"/><input name="excel_list_hidden" type="hidden" value="<s:property value="%{#request.oneMap.USA+#request.oneMap.CANADA_USA+#request.oneMap.MEXICO_USA+#request.oneMap.OTHER_USA}"/>"/> </td>
                          </tr>
                          <tr>
                            <td align="center">加 拿 大<input name="excel_list_hidden" type="hidden" value="加拿大"/></td>
                            <td align="center"><s:property value="#request.oneMap.CANADA_USA"/><input name="excel_list_hidden" type="hidden" value="<s:property value="#request.oneMap.CANADA_USA"/>"/></td>
                            <td align="center"><s:property value="#request.oneMap.CANADA"/><input name="excel_list_hidden" type="hidden" value="<s:property value="#request.oneMap.CANADA"/>"/></td>
                            <td align="center"><s:property value="#request.oneMap.CANADA_MEXICO"/><input name="excel_list_hidden" type="hidden" value="<s:property value="#request.oneMap.CANADA_MEXICO"/>"/></td>
                            <td align="center"><s:property value="#request.oneMap.CANADA_OTHER"/><input name="excel_list_hidden" type="hidden" value="<s:property value="#request.oneMap.CANADA_OTHER"/>"/></td>
                            <td align="center"><s:property value="%{#request.oneMap.CANADA_USA+#request.oneMap.CANADA+#request.oneMap.CANADA_MEXICO+#request.oneMap.CANADA_OTHER}"/><input name="excel_list_hidden" type="hidden" value="<s:property value="%{#request.oneMap.CANADA_USA+#request.oneMap.CANADA+#request.oneMap.CANADA_MEXICO+#request.oneMap.CANADA_OTHER}"/>"/></td>
                          </tr>
                          <tr>
                            <td align="center">墨 西 哥<input name="excel_list_hidden" type="hidden" value="墨西哥"/></td>
                            <td align="center"><s:property value="#request.oneMap.MEXICO_USA"/><input name="excel_list_hidden" type="hidden" value="<s:property value="#request.oneMap.MEXICO_USA"/>"/></td>
                            <td align="center"><s:property value="#request.oneMap.CANADA_MEXICO"/><input name="excel_list_hidden" type="hidden" value="<s:property value="#request.oneMap.CANADA_MEXICO"/>"/></td>
                            <td align="center"><s:property value="#request.oneMap.MEXICO"/><input name="excel_list_hidden" type="hidden" value="<s:property value="#request.oneMap.MEXICO"/>"/></td>
                            <td align="center"><s:property value="#request.oneMap.MEXICO_OTHER"/><input name="excel_list_hidden" type="hidden" value="<s:property value="#request.oneMap.MEXICO_OTHER"/>"/></td>
                            <td align="center"><s:property value="%{#request.oneMap.MEXICO_USA+#request.oneMap.CANADA_MEXICO+#request.oneMap.MEXICO+#request.oneMap.MEXICO_OTHER}"/><input name="excel_list_hidden" type="hidden" value="<s:property value="%{#request.oneMap.MEXICO_USA+#request.oneMap.CANADA_MEXICO+#request.oneMap.MEXICO+#request.oneMap.MEXICO_OTHER}"/>"/></td>
                          </tr>
                          <tr>
                            <td align="center">其它国家<input name="excel_list_hidden" type="hidden" value="其他国家"/></td>
                            <td align="center"><s:property value="#request.oneMap.OTHER_USA"/><input name="excel_list_hidden" type="hidden" value="<s:property value="#request.oneMap.OTHER_USA"/>"/></td>
                            <td align="center"><s:property value="#request.oneMap.CANADA_OTHER"/><input name="excel_list_hidden" type="hidden" value="<s:property value="#request.oneMap.CANADA_OTHER"/>"/></td>
                            <td align="center"><s:property value="#request.oneMap.MEXICO_OTHER"/><input name="excel_list_hidden" type="hidden" value="<s:property value="#request.oneMap.MEXICO_OTHER"/>"/></td>
                            <td align="center"><s:property value="#request.oneMap.OTHER"/><input name="excel_list_hidden" type="hidden" value="<s:property value="#request.oneMap.OTHER"/>"/></td>
                            <td align="center"><s:property value="%{#request.oneMap.OTHER_USA+#request.oneMap.CANADA_OTHER+#request.oneMap.MEXICO_OTHER+#request.oneMap.OTHER}"/><input name="excel_list_hidden" type="hidden" value="<s:property value="%{#request.oneMap.OTHER_USA+#request.oneMap.CANADA_OTHER+#request.oneMap.MEXICO_OTHER+#request.oneMap.OTHER}"/>"/></td>
                          </tr>
                          <tr>
                            <td align="center">合　计<input name="excel_totle_hidden" type="hidden" value="合计"/></td>
                            <td width="15%" align="center"><s:property value="%{#request.oneMap.USA+#request.oneMap.CANADA_USA+#request.oneMap.MEXICO_USA+#request.oneMap.OTHER_USA}" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="%{#request.oneMap.USA+#request.oneMap.CANADA_USA+#request.oneMap.MEXICO_USA+#request.oneMap.OTHER_USA}" />"/></td>
                            <td width="15%" align="center"><s:property value="%{#request.oneMap.CANADA_USA+#request.oneMap.CANADA+#request.oneMap.CANADA_MEXICO+#request.oneMap.CANADA_OTHER}" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="%{#request.oneMap.CANADA_USA+#request.oneMap.CANADA+#request.oneMap.CANADA_MEXICO+#request.oneMap.CANADA_OTHER}" />"/></td>
                            <td width="15%" align="center"><s:property value="%{#request.oneMap.MEXICO_USA+#request.oneMap.CANADA_MEXICO+#request.oneMap.MEXICO+#request.oneMap.MEXICO_OTHER}" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="%{#request.oneMap.MEXICO_USA+#request.oneMap.CANADA_MEXICO+#request.oneMap.MEXICO+#request.oneMap.MEXICO_OTHER}" />"/></td>
                            <td width="15%" align="center"><s:property value="%{#request.oneMap.OTHER_USA+#request.oneMap.CANADA_OTHER+#request.oneMap.MEXICO_OTHER+#request.oneMap.OTHER}" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="%{#request.oneMap.OTHER_USA+#request.oneMap.CANADA_OTHER+#request.oneMap.MEXICO_OTHER+#request.oneMap.OTHER}" />"/></td>
                            <td width="15%" align="center"><s:property value="%{#request.oneMap.USA+#request.oneMap.CANADA+#request.oneMap.MEXICO+#request.oneMap.CANADA_USA+#request.oneMap.MEXICO_USA+#request.oneMap.CANADA_MEXICO+#request.oneMap.OTHER+#request.oneMap.OTHER_USA+#request.oneMap.CANADA_OTHER+#request.oneMap.MEXICO_OTHER}" /><input name="excel_totle_hidden" type="hidden" value="<s:property value="%{#request.oneMap.USA+#request.oneMap.CANADA+#request.oneMap.MEXICO+#request.oneMap.CANADA_USA+#request.oneMap.MEXICO_USA+#request.oneMap.CANADA_MEXICO+#request.oneMap.OTHER+#request.oneMap.OTHER_USA+#request.oneMap.CANADA_OTHER+#request.oneMap.MEXICO_OTHER}" />"/></td>
                            <td align="center"><input name="excel_totle_hidden" type="hidden" value=""/></td> 
                          </tr>
                      </table>
                  </div></td>
                  
                </tr>
              </table>   
              
            </td>
            
            <td>
                  	<table width="100%" class="tableheader">
	                <tr>
	                  <td width="10%" height="26"><img src="<%=basePath %>images/bg_mcontentL.gif"  width="32" height="27" /></td>
	                  <td width="70%" valign="middle"><h3><strong>目的国业务量统计</strong></h3></td>
	                  <td width="17%" align="right" valign="middle"><input name="添加2" type="button" class="button_s" onclick="javascript:toSecondExcelOther();" value="导出Excel表" /></td>
	                  <td width="3%" align="right"><img src="<%=basePath %>images/bg_mcontentR.gif"  width="7" height="27" /></td>
	                </tr>
	              </table>
	                <table width="100%" class="tableCont">
	                  <tr>
	                    <td><div class="tableContent">
	                        <table width="100%" border="0" cellspacing="1" cellpadding="0">
	                            <tr>
	                              <th width="50%">国　家<input name="excel_other_hidden" type="hidden" value="国家"/></th>
	                              <th width="50%" >业务量<input name="excel_other_hidden" type="hidden" value="业务量"/></th>
	                            </tr>
	                            <tr>
	                              <td align="center">北美三国<input name="excel_other_hidden" type="hidden" value="北美三国"/></td>
	                              <td align="center">
	                             	<s:property value="%{#request.oneMap.USA+#request.oneMap.CANADA+#request.oneMap.MEXICO+#request.oneMap.CANADA_USA+#request.oneMap.MEXICO_USA+#request.oneMap.CANADA_MEXICO}"/>
	                             <input name="excel_other_hidden" type="hidden" value="<s:property value="%{#request.oneMap.USA+#request.oneMap.CANADA+#request.oneMap.MEXICO+#request.oneMap.CANADA_USA+#request.oneMap.MEXICO_USA+#request.oneMap.CANADA_MEXICO}"/>"/>
									</td>
	                            </tr>
	                            <tr>
	                              <td align="center">其他国家<input name="excel_other_hidden" type="hidden" value="其他国家"/></td>
	                              <td align="center">
	                             	<s:property value="%{#request.oneMap.OTHER_USA+#request.oneMap.CANADA_OTHER+#request.oneMap.MEXICO_OTHER+#request.oneMap.OTHER}"/>
	                             	<input name="excel_other_hidden" type="hidden" value="<s:property value="%{#request.oneMap.OTHER_USA+#request.oneMap.CANADA_OTHER+#request.oneMap.MEXICO_OTHER+#request.oneMap.OTHER}"/>"/>
	                              </td>
	                            </tr>
	                            <tr>
	                              <td align="center">总　计<input name="excel_other_hidden" type="hidden" value="总计"/></td>
	                              <td align="center">
	                              	<s:property value="%{#request.oneMap.USA+#request.oneMap.CANADA+#request.oneMap.MEXICO+#request.oneMap.CANADA_USA+#request.oneMap.MEXICO_USA+#request.oneMap.CANADA_MEXICO+#request.oneMap.OTHER+#request.oneMap.OTHER_USA+#request.oneMap.CANADA_OTHER+#request.oneMap.MEXICO_OTHER}"/>
	                              	<input name="excel_other_hidden" type="hidden" value="<s:property value="%{#request.oneMap.USA+#request.oneMap.CANADA+#request.oneMap.MEXICO+#request.oneMap.CANADA_USA+#request.oneMap.MEXICO_USA+#request.oneMap.CANADA_MEXICO+#request.oneMap.OTHER+#request.oneMap.OTHER_USA+#request.oneMap.CANADA_OTHER+#request.oneMap.MEXICO_OTHER}"/>"/>
	                              </td>
	                            </tr>
	                            <tr><td colspan="2">&nbsp;</td></tr>
	                            <tr><td colspan="2">&nbsp;</td></tr>
	                        </table>
	                    </div></td>
	                  </tr>
	              </table>
                  
                  </td>  
              
              
          </tr>
        </table>
        <br />
        <table width="100%" class="tableheader">
          <tr>
            <td width="5%" height="26"><img src="<%=basePath %>images/bg_mcontentL.gif"  width="32" height="27" /></td>
            <td valign="middle"><h3>国家统计</h3></td>
            <td align="right" valign="middle">&nbsp;</td>
            <td width="1%" align="right"><img src="<%=basePath %>images/bg_mcontentR.gif"  width="7" height="27" /></td>
          </tr>
        </table>
        <table width="100%" class="tableCont">
          <tr>
            <td><img src="${oneImg}" width="500" height="300" /></td>
          </tr>
        </table>
        <br />
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
