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
		<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
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
						<table width="100%" class="tableCont">
							<tr>
								<td>
									<table width="650" align="center" cellspacing="5">
										<s:if test="#request.informationlist.size<=0">
											<tr>
												<th width="15%">
													<h3>
														<font color="#b3b3b3">暂无资料</font>
													</h3>
												</th>
											</tr>
										</s:if>
									
										<s:form id="datalist" name="datalist"
											action="getAllDataByPage" theme="simple">
											<s:if test="#request.informationlist.size>0">
												<s:iterator value="#request.informationlist" status="stat">
													<tr>
														<td width="572" height="25" align="left"
															style="border-bottom: dotted 1px #8EC0E8;">
															<img src="../../images/menu_icon.gif" width="11" height="9" />
															<a
																href="<s:url action="getInformationByIdPerson"> 
			                            <s:param name="informationid"><s:property value="informationid"/></s:param>
			                            </s:url>">
																<span><s:property value="informationname" /> <img
																		src="../../images/print.gif" style="margin-bottom: -5px;" />
															</span> </a>
														</td>
														<td width="157" height="25" align="center"
															style="border-bottom: dotted 1px #8EC0E8;">
															【
															<s:date name="fileuptime" format="yyyy-MM-dd " />
															】
														</td>
													</tr>
												</s:iterator>
											</s:if>
										</s:form>
									</table>
								</td>
							</tr>
						</table>
						<table width="98%" height="35" border="0" cellpadding="0"
							cellspacing="0">
							<tr>
							<s:if test="#request.informationlist.size>0">
								<td height="25" align="right" class="right_tb1" colspan="3">
									<!--分页  -->
									<s:component template="pagev2.jsp" templateDir="page/pageutil"
										theme="template">
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
								</s:if>
							</tr>
						</table>
						<!--编辑结束-->
					</div>
				</td>
			</tr>
		</table>


	</body>
</html>
