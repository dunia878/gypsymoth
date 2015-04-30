<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title><s:text name="examinelist"/></title>
<link rel=stylesheet href="${pageContext.request.contextPath}/style/index.css" type="text/css" />
<link rel=stylesheet href="${pageContext.request.contextPath}/style/right.css" type="text/css" />
<%@ include file="../include/head.jsp"%>
<script language="javascript" type="text/javascript" src="<%=basePath%>/js/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript">
	$(document).ready(function(){
	
		 var portcity_hidden = $("#portcity_hidden").val();
		 if(portcity_hidden!=null&&portcity_hidden!=""){
		 	selectItem('portcity_form',portcity_hidden);
		 } 
	
		$("#search").click(function(){
			var appdate_begin = $("#appdate_begin").val();
			var appdate_end = $("#appdate_end").val();
			if(appdate_begin!=null&&appdate_begin!=""&&appdate_end!=null&&appdate_end!=""){
				if(Date.parse(appdate_begin.replace('-','/'))>Date.parse(appdate_end.replace('-','/'))){
					alert("申请时间区间的开始日期不能大于结束日期");
					document.getElementById("appdate_begin").focus();
					return false; 
				}
			}else if(appdate_begin!=null&&appdate_begin!=""){
				if(appdate_end==null||appdate_end==""){
					alert("请完整填写申请时间区间");
					document.getElementById("appdate_end").focus();
					return false;
				}
			}else if(appdate_end!=null&&appdate_end!=""){
				if(appdate_begin==null||appdate_begin==""){
					alert("请完整填写申请时间区间");
					document.getElementById("appdate_begin").focus();
					return false;
				}
			}
			
					var n = document.getElementById("appno_form");
					var v = n.value;
					var vs = v.substring(12);
					if(v.length != 0 && (v.length != 16 || (vs != '-AGM' && vs != '-agm')) ) {
						alert("您输入的申请编号有错误");
						return false;
					}
		 	document.examine.action = "getAllExamineByPage.action";
			document.examine.submit();  
		});
		 
	});
	
	
	//选中ID为obj的select控件的值为value的内容 如: 'city',1 
			function selectItem(obj,value)
			{
			  var objSelect = document.getElementById(obj);
			  for(var i=0;i<objSelect.options.length;i++){
		         if(objSelect.options[i].value == value)
		         {
		         	 setTimeout(function(){objSelect.options[i].selected = true;},1);
		             isExit = true;
		             break; 
		         } 
		  		 }    
			}
</script> 


</head>
<body>
  	
<!--内容开始-->
<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
  <tr>
    <td class="list_r">&nbsp;</td>
    <td valign="top" class="right"><div class="title"><s:text name="local"/>： <s:text name="home"/> &gt;&gt; <s:text name="bussinessManage"/> &gt;&gt; <strong><s:text name="examine"/></strong></div>
      <div>
        <!--编辑开始-->
          <s:form id="examine" name="examine" action="getAllExamineByPage" theme="simple">  
        <table width="100%" border="0" cellpadding="6" cellspacing="0" class="tabman">
            <tr>
					          	<td height="35" align="center">
					          		 <s:text name="申请编号"/>: &nbsp; <input type="text" name="appno_form" id="appno_form" value="${appno_form }" />
					          		&nbsp;  &nbsp;
					          		 <s:text name="船名"/>: &nbsp; <input type="text" name="vesselname_form" id="vesselname_form" value="${vesselname_form }" />
					          		&nbsp;  &nbsp; 
					          		<s:text name="港口城市"/>: &nbsp;
						          		<s:select list="#request.portcitylist" name="portcity_form" id="portcity_form" 
						          			headerKey="-1" headerValue="- %{getText('请选择')} -"  listKey="CITYNAME" listValue="CITYNAME" />
					          		<input type="hidden" name="portcity_hidden" id="portcity_hidden" value="${portcity_hidden}" />
					          	</td>
					          </tr>
					           <tr>
					          	<td height="35" align="center">
					          		申请日期:&nbsp;&nbsp;
						          	<input class="Wdate" type="text" id="appdate_begin" name="appdate_begin"
					          			 onfocus="WdatePicker()" value="${appdate_begin }" />
					          			 &nbsp;至&nbsp;
					          		<input class="Wdate" type="text" id="appdate_end" name="appdate_end"
					          			 onfocus="WdatePicker()" value="${appdate_end }" />	 
									&nbsp;  &nbsp;<input name="search" id="search" type="button" class="button" value="<s:text name='查询'/>" />
					          			
					          	</td>
					          </tr>
          
        </table>
        </s:form>
        <table width="100%" class="tableheader">
          <tr>
            <td width="5%" height="26"><img src="${pageContext.request.contextPath}/images/bg_mcontentL.gif"  width="32" height="27" /></td>
            <td valign="middle"><h3><s:text name="examinelist"/></h3></td>
            <td align="right" valign="middle">&nbsp;</td>
            <td width="1%" align="right"><img src="${pageContext.request.contextPath}/images/bg_mcontentR.gif"  width="7" height="27" /></td>
          </tr>
        </table>
        <table width="100%" class="tableCont">
          <tr>
            <td><div class="tableContent">
                <table width="100%" border="0" cellspacing="1" cellpadding="0">
               
                    <tr>
                      <th width="5%"><s:text name="code"/></th>
                      <th width="11%"><s:text name="username"/></th>
                      <th width="20%"><s:text name="appno"/></th>
                      <th width="15%" ><s:text name="boatname"/></th>
                      <th width="15%"><s:text name="portcity"/></th>
                      <th width="15%"><s:text name="examineorganization"/></th>
                      <th width="10%"><s:text name="examinetime"/></th>
                      <th width="8%"><s:text name="operation"/></th>
                    </tr>
                  <s:if test="#request.list.size>0">
    				<s:iterator value="#request.list" status="stat">
    				<tr>
    				<td align="center"><s:property value="#stat.index+1"/></td>
    				<td align="center"><s:property value="ACCOUNT_NAME"/></td>
    				<td align="center"><a href="${pageContext.request.contextPath}/page/examine/getExamineById.action?Id=<s:property value="ID"/>" ><font color="blue"><s:property value="APPNO"/></font></a></td>
    				<td align="center"><s:property value="VESSELNAME"/></td>    				 				
    				<td align="center"><s:property value="PORTCITY"/></td>
    				<td align="center"><s:property value="CERTIFICATION"/></td>
    				<td align="center"><s:property value="CHECKDATE"/></td>
    				<td align="center"><a href="${pageContext.request.contextPath}/page/examine/select.action?Id=<s:property value="ID"/>"><s:text name="insert"/></a></td>    				
    				</tr>
    				</s:iterator>
    				</s:if>
                </table>
              </div></td>
          </tr>
        </table>
        <table width="98%" height="35" border="0" cellpadding="0" cellspacing="0">
         <tr>
										<td height="25" align="right" class="right_tb1" colspan="3"><!--分页  -->
												<s:component template="pagev2.jsp" templateDir="page/pageutil"
													theme="template">
													<!--action name  -->
													<s:param name="formId" value="'examine'" />
													<s:param name="action_name" value="actionName" />
													<!-- name space -->
													<s:param name="namespace" value="'/page/examine'" />
													<!-- pagination method name -->
													<s:param name="pagination_method_name" value="actionName" />
													<!--当前页前后显示的数量  -->
													<s:param name="distanceNum" value="5" />
												</s:component>
										</td>
								</tr>
</table>
</div>
</body>
</html>
