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
			src="<%=basePath %>/js/My97DatePicker/WdatePicker.js"></script>
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>/js/business/validata.js"></script> 
			
		<script language="javascript" type="text/javascript"> 
			$(document).ready(function(){
				 var portcity_hidden = $("#portcity_hidden").val();
				 if(portcity_hidden!=null&&portcity_hidden!=""){
				 	selectItem('portcity_form',portcity_hidden);
				 } 
				 
				 $("#search").click(function(){
				 	document.formlist.action = "searchBusiness.action"; 
					document.formlist.submit(); 
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
			
			function businessapply()
			{
				document.formlist.action = "businessApply.action";
				document.formlist.submit();
			} 
			
			function businessview(id)
			{
				document.getElementById("id").value=id;
				document.getElementById("view").value=0;
				document.formlist.action = "getBusinessById.action"; 
				document.formlist.submit();
			}
			
		</script>	
	</head>
	<body>

		<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
			<tr>
				<td class="list_r">
					&nbsp;
				</td>
				<td valign="top" class="right">
					<div class="title">
						<s:text name="您的当前位置"/>：  <s:text name="首页"/> &gt;&gt; <s:text name="业务管理"/> &gt;&gt;
						<strong><s:text name="业务申请"/></strong>
					</div>
					<div>
						<!--编辑开始-->
						<s:form id="formlist" name="formlist" action="" theme="simple" namespace="/page/business" >
						<input name="businessstate_search" type="hidden" value="0"  />
						<input name="search_condition_select" id="search_condition_select" type="hidden" value="${search_condition }"  />
						<table width="100%" border="0" cellpadding="6" cellspacing="0" class="tabman">
					          <tr>
					          	<td height="35" align="center">
					          		 <s:text name="船名"/>: &nbsp; <input type="text" name="vesselname_form" id="vesselname_form" value="${vesselname_form }" />
					          		&nbsp;  &nbsp; <s:text name="港口城市"/>: &nbsp;
					          		
					          		<s:if test="#request.language=='CN'">
					          			 <s:select list="#request.portcitylist" name="portcity_form" id="portcity_form" headerKey="-1" headerValue="- %{getText('请选择')} -"  listKey="CITYNAME" listValue="CITYNAME" />
					          		</s:if><s:else>
					          			 <s:select list="#request.portcitylist" name="portcity_form" id="portcity_form" headerKey="-1" headerValue="- %{getText('请选择')} -"  listKey="CITYNAME" listValue="ENGLISHCITYNAME" />
					          		</s:else>
					          		
					          		&nbsp;  &nbsp; <input name="search" id="search" type="button" class="button" value="<s:text name='查询'/>" />
					         
					         		<input type="hidden" name="portcity_hidden" id="portcity_hidden" value="${portcity_hidden}" />
					          	</td>
					          </tr> 
					        </table> 
						
						<table width="100%" class="tableheader">
							<tr>
								<td width="5%" height="26">
									<img src="../../images/bg_mcontentL.gif" width="32" height="27" />
								</td>
								<td valign="middle">
									<h3>
										
									</h3>
								</td>
								<td align="right" valign="middle">
									<c:forEach items="${menu_parent}" var="mp3" varStatus="status">
										<c:if test="${mp3.rightId == 411}"> 
											<input type="button" class="button_s"
											onclick="javascript:businessapply();"
											value="<s:text name='申请新业务'/>" /> 
										</c:if>
									</c:forEach>
									
									
								</td>
								<td width="1%" align="right">
									<img src="../../images/bg_mcontentR.gif" width="7" height="27" />
								</td>
							</tr>
						</table>
						<table width="100%" class="tableCont" >
							<tr>
								<td>
									<div class="tableContent" style=" height:300px; width:768px;overflow:auto;">
										<table width="100%" border="0" cellspacing="1" cellpadding="0"  style="width:1200px;">
											<tr>
												<th>
													<s:text name="编号"/>
												</th>
												<th >
													<s:text name="临时编号"/>
												</th>
												<th>
													<s:text name="IMO号"/>
												</th>
												<th>
													<s:text name="用户名"/>
												</th>
												<th>
													<s:text name="船名"/>
												</th>
												<th>
													<s:text name="申请人"/>
												</th>
												<th>
													<s:text name="港口城市"/>
												</th>
												<th>
													<s:text name="操作"/>
												</th>

											</tr>
											
												<s:if test="#request.list.size>0">
													<s:iterator value="#request.list" status="st" id="mop">
														<tr>
															<td align="center">
																<s:property value="#st.index+1"/> 
												        	</td>
												        	<td  align="center">
												        		<c:forEach items="${menu_parent}" var="mp3" varStatus="status">
																<c:if test="${mp3.rightId == 413}">
																	<a href="<s:url action="getBusinessById">
																	<s:param name="id"><s:property value="#mop.ID"/></s:param>
																	<s:param name="view">1</s:param>
																	<s:param name="goPage"><s:property value="#request.currPagea" /></s:param>
																	</s:url>">
																	<font color="blue"><s:property value="#mop.TEMPNO" /></font> </a>
																</c:if>
																</c:forEach>
															</td>
															<td  align="center">
																<s:property value="#mop.IMO" /> 
															</td>
															<td  align="center">
															<s:property value="#mop.ACCOUNT_NAME" /> 
															</td>
															<td align="center">
																<s:property value="#mop.VESSELNAME" />
															</td>
															<td align="center">
																<s:property value="#mop.BUSINESSNAME" />
															</td>
															<td align="center">
																<s:property value="%{getText(#mop.PORTCITY)}" />
															</td>

															<td align="center">
															<c:forEach items="${menu_parent}" var="mp3" varStatus="status">
															<c:if test="${mp3.rightId == 412}">
																<a href="<s:url action="getBusinessById"><s:param name="id"><s:property value="#mop.ID"/></s:param><s:param name="view">1</s:param></s:url>"><s:text name="update"/></a>&nbsp;
															</c:if>
															<c:if test="${mp3.rightId == 414}">
																<a href="<s:url action="deleteBusinessById"><s:param name="id"><s:property value="#mop.ID"/></s:param></s:url>"onclick="return window.confirm('您确认删除?')"><s:text name="delete"/></a>
															</c:if> 
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
						<input type="hidden" name="id" id="id" />
						<input type="hidden" name="view" id="view" />
						<s:hidden name="goPage" value="%{#request.currPagea}" ></s:hidden>
						 </s:form> 
						 
						 
						<table width="98%" height="35" border="0" cellpadding="0"
							cellspacing="0">
							<tr>
								<td height="25" align="right" class="right_tb1" colspan="3">
									<!--分页  -->
									<s:component template="pagev2.jsp" templateDir="page/pageutil"
										theme="template">
										<!--action name  -->
										<s:param name="formId" value="'formlist'" />
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
				 </div>
			 	</td>
			</tr>
		</table>
		
		
	</body>
</html>
