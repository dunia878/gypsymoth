<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统</title>
		<%@ include file="../include/head.jsp"%>
	</head>
	<!-- JS验证表单部分 -->
	<script type="text/javascript">

	
	
	
   function gotoSubmit()
    {
    		this.document.forms[0].submit(); 
    		 
     }
     
     $(document).ready(function(){
		$("#chkall").click(function(){ 
			 if($(this).attr("checked")){
				$("input[@name='idArray']").attr("checked","true");
			 }else{ 
				$("input[@name='idArray']").attr("checked","");
			 }
		 }); 
	});
	
function deletebulletin(){
   if(confirm("是否删除公告？")){
	var a = document.getElementsByName("idArray"); 
  	var count = 0;
	for(var i=0;i<a.length;i++){
		if(a[i].checked){
			count++;
		}
	}
  	if(count==0){
 		alert('删除至少选择一项信息');
 		return;
 	}	
	document.forms[0].method="post";
 	document.forms[0].action ="delete.action" ;
 
 	document.forms[0].submit();	
 	
	}else{
		return false;
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
						您的当前位置： 首页 &gt;&gt; 信息与资料 &gt;&gt;
						<strong>公告通知</strong>
					</div>
					<div>
						<!--编辑开始-->
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
								<c:forEach items="${menu_parent}" var="mp3" varStatus="status">
									<c:if test="${mp3.rightId == 611}">
										<td align="right" valign="middle">
											<input type="button"
												onclick="javascript:window.location='insertBulletin.jsp'"
												class="button_s" value="添 加" />
											&nbsp;&nbsp;
											<input type="button" class="button_s"
												value="<s:text name="删 除"/>" onclick="deletebulletin()" />
										</td>
									</c:if>
								</c:forEach>
								<td width="1%" align="right">
									<img src="../images/bg_mcontentR.gif" width="7" height="27" />
								</td>
							</tr>
						</table>

						<table width="100%" class="tableCont">
							<tr>
								<td>
									<div class="tableContent">
										<table width="100%" border="0" cellspacing="1" cellpadding="0">
											<tr>
												<th width="5%">
													<input name="chkall" type="checkbox" id="chkall"
														value="checkbox" />
												</th>
												<th align="center">
													标 题
												</th>
												<th align="center">
													发布时间
												</th>

												<th align="center">
													发布人
												</th>
												<th align="center">
													查看权限
												</th>
												<th align="center">
													中英文
												</th>
												<th align="center">
													操作
												</th>
											</tr>
											<s:form id="bulletinlist" name="bulletinlist"
												action="getAllDataByPages" theme="simple">
												<s:if test="#request.bulletinlist.size>0">
													<s:iterator value="#request.bulletinlist" status="st">
														<tr>
															<input type="hidden" name="fileurl" id="fileurl"
																value="<s:property value="bulletin.fileurl"/>" />
															<td align="center">
																<input type="checkbox" name="idArray"
																	value="<s:property value="bulletinId"/>" />

															</td>
															<td>
																<a
																	href="<s:url action="getABulletinById"> 
			                            <s:param name="bulletinId"><s:property value="bulletinId"/></s:param>
			                            </s:url>">
																	<s:property value="bulletinName" /> </a>
															</td>
															<td align="center">
																<s:date name="bulletinTime" format="yyyy-MM-dd HH:mm" />
															</td>

															<td align="center">
																<s:property value="accountName" />
															</td>
															<td align="center">
																<s:if test="%{bulletinTip == 1}">
																	<font color="red">用户
																</s:if>
																<s:if test="%{bulletinTip == 2}">
																	<font color="green">用户及客户
																</s:if>
																<s:if test="%{bulletinTip == 3}">
																	<font color="blue">客户
																</s:if>
															</td>
															<td align="center">
																<s:if test="%{bulletinLanguage == 1}">中文</s:if>
																<s:if test="%{bulletinLanguage == 2}">英文</s:if>
																<s:if test="%{bulletinLanguage == 3}">中英文</s:if>
															</td>
															<td align="center">
																<a
																	href="<s:url action="getBulletinByIds"><s:param name="bulletinId"><s:property value="bulletinId"/></s:param></s:url>">修改</a>&nbsp|
																<a
																	href="<s:url action="deleteBulletinById"><s:param name="bulletinId"><s:property value="bulletinId"/></s:param></s:url>"
																	onclick="return window.confirm('是否删除公告！')">删除</a>
															</td>
														</tr>
													</s:iterator>
												</s:if>
											</s:form>





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
										<s:param name="formId" value="'bulletinlist'" />
										<s:param name="action_name" value="actionName" />
										<!-- name space -->
										<s:param name="namespace" value="'/page/bulletin'" />
										<!-- pagination method name -->
										<s:param name="pagination_method_name" value="actionName" />
										<!--当前页前后显示的数量  -->
										<s:param name="distanceNum" value="5" />
									</s:component>
								</td>
							</tr>
						</table>
					</div>
				</td>
				<!-- 主内容结束 -->

			</tr>
		</table>

	</body>
</html>
