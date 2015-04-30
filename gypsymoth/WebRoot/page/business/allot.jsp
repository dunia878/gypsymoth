<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
 	isELIgnored="false"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统</title>
		<%@ include file="../include/head.jsp"%>
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>/js/My97DatePicker/WdatePicker.js"></script>
	</head>

<body>
 
 	 <script language="text/javascript" type="text/javascript"> 
 		 $(document).ready(function(){
 		 		 var portcity_hidden = $("#portcity_hidden").val();
				 if(portcity_hidden!=null&&portcity_hidden!=""){
				 	selectItem('portcity_form',portcity_hidden);
				 } 
				 var allotstate_hide = $("#allotstate_hide").val();
				 if(allotstate_hide!=null&&allotstate_hide!=""){
				  	selectItem('allotstate_form',allotstate_hide); 
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
				  
				 	document.allot.action = "getAllotPage.action";
					document.allot.submit();  
				 });
				 
		});
 	 
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
 	 
		function allotDetail(id)
		{	
			document.getElementById("id").value=id;
			document.allotform.method="post";
			document.allotform.action="getAllotPageDetail.action?goPage=<s:property value="#request.currPagea"/>"; 
			document.allotform.submit();
		}
		function allotDetailOnly(id)
		{	
			document.getElementById("id").value=id;
			document.allotform.method="post";
			document.allotform.action="allotDetailOnly.action"; 
			document.allotform.submit();
		}
		
		function reAllot(id) 
		{
			var reallot = confirm("确定要重新分配此业务单？");
		 	if(reallot){
			 	document.getElementById("id").value=id;
				document.allotform.method="post";
				document.allotform.action="reAllot.action?goPage=<s:property value="#request.currPagea"/>"; 
				document.allotform.submit();
		 	}
			
		}
		
		
	</script>
 
 <!--内容开始-->
<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
  <tr>
    <td class="list_r">&nbsp;</td>
    <td valign="top" class="right"><div class="title">您的当前位置： 首页 &gt;&gt; 业务管理 &gt;&gt; <strong>业务分配</strong></div>
      <div>
      
        <!--编辑开始-->
        <br/>
        <s:form id="allot" name="allot" action="getAllotPage" theme="simple">
	      	<input type="hidden" name="allotstate_hide" id="allotstate_hide" value="${ allotstate_hide}" />
	       <table width="100%" border="0" cellpadding="6" cellspacing="0" class="tabman">
	          <tr>
	          	<td height="35" align="center">
	          		 <s:text name="申请编号"/>: &nbsp; <input type="text" name="appno_form" id="appno_form" value="${appno_form }" />
	          		&nbsp;  &nbsp;
	          		 <s:text name="船名"/>: &nbsp; <input type="text" name="vesselname_form" id="vesselname_form" value="${vesselname_form }" />
	          		&nbsp;  &nbsp; <s:text name="港口城市"/>: &nbsp; 
	          		 <s:select list="#request.portcitylist" name="portcity_form" id="portcity_form" headerKey="-1" headerValue="- %{getText('请选择')} -"  listKey="CITYNAME" listValue="CITYNAME" />
					 <s:text name="分配状态"/>:
					<select name="allotstate_form" id="allotstate_form" >
					<option value="0">全部</option><option value="1">待分配</option>
					 <s:if test="#request.isadmin==1"><option value="2">分配中</option></s:if>
					<option value="3">分配已完成</option>
					</select> 
					
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
						<input type="hidden" name="portcity_hidden" id="portcity_hidden" value="${portcity_form}" />
		          	</td>
		          </tr>
	      </table> 
	      
	      
    	</s:form>
        
        <table width="100%" class="tableheader">
          <tr>
            <td width="5%" height="26"><img src="<%=basePath %>/images/bg_mcontentL.gif"  width="32" height="27" /></td>
            <td valign="middle"><h3>列表</h3></td>
            <td align="right" valign="middle">
             </td>
            <td width="1%" align="right"><img src="../images/bg_mcontentR.gif"  width="7" height="27" /></td>
          </tr>
        </table>
        <table width="100%" class="tableCont">
          <tr>
            <td><div class="tableContent">
            <form id="allotform" name="allotform" method="post" action="">
            	<input	type="hidden" name="id" id="id" />
                <table width="100%" border="0" cellspacing="1" cellpadding="0">
                  
                    <tr>
                      <th width="6%">编号</th>
                      <th>申请编号</th>
                      <th width="10%">用户名</th>
                      <th>船名</th>
                      <th>港口城市</th>
                      <th>申请时间</th>
                      <th>分配状态</th>
                      <th>操作</th>
                       
                    </tr>
                    <s:iterator value="#request.allotlist"  id="bus" status="va">
                     <tr>
                      <td height="25" align="center"><s:property value="#va.index+1"/>  </td>
                       <td align="center"><a href="allotDetailOnly.action?id=<s:property value="#bus.ID"/>&goPage=<s:property value="#request.currPagea" />"><font color="blue"><s:property value="#bus.APPNO"/></font></a></td>
                      <td align="center"><s:property value="#bus.ACCOUNT_NAME"/></td> 
                      <td align="center"><s:property value="#bus.VESSELNAME"/></td>
                      <td align="center"><s:property value="#bus.PORTCITY"/></td> 
                      <td align="center"><s:date name="#bus.APPDATE" format="yyyy-MM-dd"/></td>
                      <td align="center">
                      	<s:if test="#request.isadmin==1">
                      		<s:if test="#bus.BUSINESSSTATE==4">
                      		 分配已完成
                      		</s:if><s:elseif test="#bus.BUSINESSSTATE==3&&#bus.ASSIGNERSTATE>0">
                      		 分配进行中
                      		</s:elseif><s:elseif test="#bus.BUSINESSSTATE==3&&#bus.ASSIGNERSTATE==0">
                      		 待分配
                      		</s:elseif>
                      	</s:if><s:else>
                      		<s:if test="#bus.BUSINESSSTATE==4">
                      		 分配已完成
                      		</s:if><s:elseif test="#bus.BUSINESSSTATE==3">
                      		 待分配 
                      		</s:elseif>  
                      	</s:else>
                      	
                      
                      </td>
                      <td align="center">
	                      <s:if test="#request.isadmin==1">
	                      		<s:if test="#bus.BUSINESSSTATE==3&&#bus.ASSIGNERSTATE==0">
	                      		  <a href="#" onclick="allotDetail(<s:property value="#bus.ID"/>);" >分配</a>
	                      		</s:if><s:elseif test="#bus.BUSINESSSTATE==4"> 
	                      		  <a href="#" onclick="allotDetailOnly(<s:property value="#bus.ID"/>);" >查看</a>
	                      		</s:elseif><s:else>
	                      		  <a href="#" onclick="reAllot(<s:property value="#bus.ID"/>);" >重新分配</a>
	                      		</s:else>
	                      	</s:if><s:else>
	                      		<s:if test="#bus.BUSINESSSTATE==4">
	                      		 <a href="#" onclick="allotDetailOnly(<s:property value="#bus.ID"/>);" >查看</a>
	                      		</s:if><s:elseif test="#bus.BUSINESSSTATE==3">
	                      		 <a href="#" onclick="allotDetail(<s:property value="#bus.ID"/>);" >分配</a>
	                      		</s:elseif> 
	                      	</s:else>
                      
                      	
                      </td>
                      <td align="center">
                     
                      </td>
                    </tr> 
                    </s:iterator>
                  
                </table>
                </form>
              </div></td>
          </tr>
        </table>
         <table width="98%" height="35" border="0" cellpadding="0"
							cellspacing="0">
			<tr>
				<td height="25" align="right" class="right_tb1" colspan="3">
					<!--分页  -->
					<s:component template="pagev2.jsp" templateDir="page/pageutil"
						theme="template">
						<!--action name  -->
						<s:param name="formId" value="'allot'" />
						<s:param name="action_name" value="actionName" />
						<!-- name space -->
						<s:param name="namespace" value="'/page/business'" />
						<!-- pagination method name -->
						<s:param name="pagination_method_name" value="actionName" />
						<!--当前页前后显示的数量  -->
						<s:param name="distanceNum" value="5" />
					</s:component>
				</td> 
			</tr>
		</table>
        <!--编辑结束-->
      </div></td>
  </tr>
</table>

	</body>
</html>
