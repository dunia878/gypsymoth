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
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>/js/business/validata.js"></script> 
	</head>
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
			
		 	document.acceptlist.action = "searchBusiness.action";
			document.acceptlist.submit();  
		});
		 
		
		$("#chkall").click(function(){ 
			 if($(this).attr("checked")){
				$("input[@name='acceptcheck']").attr("checked","true");
			 }else{ 
				$("input[@name='acceptcheck']").attr("checked","");
			 }
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
	<body>

 		<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
			
			<tr>
				<td class="list_r">
					&nbsp;
				</td>
				<td valign="top" class="right">
					<div class="title">
						您的当前位置： 首页 &gt;&gt; 业务管理 &gt;&gt;
						<strong>业务受理</strong>
					</div>
					<div>
						<!--编辑开始-->
						<br/>
						<s:form id="acceptlist" name="acceptlist" action="getAcceptPage" theme="simple">
						<input name="businessstate_search" type="hidden" value="1"  />
						 <table width="100%" border="0" cellpadding="6" cellspacing="0" class="tabman">
					          <tr>
					          	<td height="35" align="center">
					          		 <s:text name="临时编号"/>: &nbsp; <input type="text" name="tempno_form" id="tempno_form" value="${tempno_form }" />
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
								<td width="5%" height="26">
									<img src="../../images/bg_mcontentL.gif" width="32" height="27" />
								</td>
								<td valign="middle">
									<h3>
										列表
									</h3>
								</td>
								<td align="right" valign="middle">
									<!-- 
									<input type="submit" class="button_s"
										onclick="alert('开发中..');"
										value="批量受理" />
									--> 
								</td>
								<td width="1%" align="right">
									<img src="../../images/bg_mcontentR.gif" width="7" height="27" />
								</td>
							</tr>
						</table>
						<table width="100%" class="tableCont">
							<tr>
								<td>
									<div class="tableContent">
										<table width="100%" border="0" cellspacing="1" cellpadding="0">

											<tr>
											<tr>
												<th width="5%">
													编号
												</th>
												<th width="10%">
													临时编号
												</th>
												<th width="10%">
													用户名
												</th>
												<th width="10%">
													船名
												</th>
												<th width="10%">
													港口城市
												</th>
												<th width="10%">
													申请人
												</th>
												<th width="10%">
													申请日期
												</th>
												<th width="12%">
													计划检查日期
												</th>
												<c:forEach items="${menu_parent}" var="mp3" varStatus="status">
													<c:if test="${mp3.rightId == 421}">
													<th width="6%">
													操 作
													</th></c:if>
												</c:forEach>
											</tr>
											
												<s:if test="#request.list.size>0">
													<s:iterator value="#request.list" status="stat" id="mop">
														<tr> 
															<td align="center"> &nbsp;<s:property value="#stat.index+1"/> </td> 
															<td align="center">&nbsp;<a name="accept" href="acceptPassdetail.action?id=<s:property value="#mop.ID" />"><font color="blue"><s:property value="#mop.TEMPNO" /></font></a></td>
															<td align="center">&nbsp;<s:property value="#mop.ACCOUNT_NAME" /></td>
															<td align="center">&nbsp;<s:property value="#mop.VESSELNAME" /></td>
															<td align="center">&nbsp;<s:property value="#mop.PORTCITY" /></td>
															<td align="center">&nbsp;<s:property value="#mop.COMPANY" /></td>
															<td align="center">&nbsp;<s:date name="#mop.APPDATE" format="yyyy-MM-dd" /></td>
															<td align="center">&nbsp;<s:date name="#mop.CHECKDATE" format="yyyy-MM-dd" /> </td>
															<td align="center">
																&nbsp;
																<c:forEach items="${menu_parent}" var="mp3" varStatus="status">
																	<c:if test="${mp3.rightId == 421}"><a name="accept" href="acceptPassdetail.action?id=<s:property value="#mop.ID" />">受理</a></c:if>
																</c:forEach>
																
															</td> 
														</tr>
													</s:iterator>
												</s:if>

										</table>
									</div>
								</td>
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
										<s:param name="formId" value="'acceptlist'" />
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
			</tr>
		</table>

	</body>
</html>
