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
  </script>


	<body>
	
					<table border="0" cellspacing="0" cellpadding="0"
						class="contenttab">
						<tr>
							<td class="list_r">
								&nbsp;
							</td>
							<td valign="top" class="right">
								<div class="title">
									您的当前位置： 首页 &gt;&gt; 信息与资料 &gt;&gt;
									<strong>资料查看与下载</strong>
								</div>
								<div>
									<!--编辑开始-->
									<table width="100%" class="tableheader">
										<tr>
											<td width="5%" height="26">
												<img src="../../images/bg_mcontentL.gif" width="32"
													height="27" />
											</td>
											<td valign="middle">
												<h3>
													列表
												</h3>
											</td>
										
											<td width="1%" align="right">
												<img src="../images/bg_mcontentR.gif" width="7" height="27" />
											</td>
										</tr>
									</table>

									<table width="100%" class="tableCont">
										<tr>
											<td>
												<div class="tableContent">
													<table width="100%" border="0" cellspacing="1"
														cellpadding="0">
														<tr>
															<th width="5%">
																选择
															</th>
															<th>
																标 题
															</th>
															<th>
																发布时间
															</th>
															<th>
																发布人
															</th>
															<th>
																查看权限
															</th>
								

														</tr>
														<s:form id="datalist" name="datalist"
															action="getAllCDataByPage" theme="simple">
															<s:if test="#request.informationlist.size>0">
																<s:iterator value="#request.informationlist">
																	<tr>
																		<td align="center">
																			<input type="checkbox" name="informationids"
																				value="<s:property value="informationid"/>" />
																		</td>
																		<td>
																			<a
																				href="<s:url action="getInformationById"> 
			                            <s:param name="informationid"><s:property value="informationid"/></s:param>
			                            </s:url>">
																				<s:property value="informationname" /> </a>
																		</td>
																		<td align="center">
																			<s:date name="fileuptime" format="yyyy-MM-dd HH:mm" />
																		</td>
																		<td align="center">
																			<s:property value="accountname" /> 
																		</td>
																		<td align="center">
																			<s:if test="%{informationtip == 1}">用户</s:if>
																			<s:if test="%{informationtip == 2}">客户</s:if>
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
												<s:component template="pagev2.jsp"
													templateDir="page/pageutil" theme="template">
													<!--action name  -->
													<s:param name="formId" value="'datalist'" />
													<s:param name="action_name" value="actionName" />
													<!-- name space -->
													<s:param name="namespace" value="'/page/information'" />
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
