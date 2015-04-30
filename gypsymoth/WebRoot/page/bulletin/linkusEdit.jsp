<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ include file="../include/header.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	response.setHeader("Cache-Control ", "Public ");
	response.setHeader("Pragma ", "no-cache ");
	response.setDateHeader("Expires ", 0);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统</title>
		<%@ include file="../include/head.jsp"%>
		<script language="javascript" type="text/javascript"
			src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
		<script language="javascript" type="text/javascript"
			src="${pageContext.request.contextPath}/js/jsFrame.js"></script>
	</head>

	<script type="text/javascript">

  </script>


	<body onload="_onload()">

	
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
						<table align="center" cellpadding="12" class="tabedit">
						<s:form action="" enctype="multipart/form-data" method="post">
							<tr>
								<td height="300" align="left" valign="top">
									<!--编辑开始-->
									
									<input id="linkusname" name="linkus.linkusname" size="80" type="hidden" value="<s:property value="linkus.linkusname" />" />
									<!-- 
										<table width="706" align="center" cellspacing="5">

											
											<tr>
												<td width="68" height="20">
													标题
												</td>
												<td width="332">
													<input id="linkusname" name="linkus.linkusname"
														size="80" type="text"
															value="<s:property value="linkus.linkusname" />" />
												</td>
												<td width="272">
													<span class="mainred">
														<div >
															*
														</div> </span>
												</td>
											</tr>
										</table>
									 -->
										

										<textarea id="linkuscontent" style="word-break: break-all"
											name="linkuscontent" class="ckeditor" cols="53" rows="15">
													    	 ${linkus.linkuscontent}  
															</textarea>

										<table width="706" align="center" cellspacing="5">
										
											<tr>
												<td width="68" height="20">
													发布时间
												</td>
												<td>
													<s:date name="linkus.createtime" format="yyyy-MM-dd HH:mm" />
												</td>
												<td>
													&nbsp;
												</td>
											</tr>

											

											<tr>
												<td height="20">
													&nbsp;
												</td>
												<td>
													&nbsp;
												</td>
												<td>
													&nbsp;
												</td>
											</tr>
											<tr>


												<td height="38" align="left"></td>
												<td height="38" align="left">
													<input name="button" class="button" type="button"
														onclick="save()" value="修改" />
													<input type="button" class="button" value="返回"
														onclick="jump()" />
												</td>
												<td align="left">
													&nbsp;
												</td>
											</tr>
											
										</table>
									</td>
								</tr>
							</s:form>
						</table>
						</div>
						<!--编辑结束-->
				</td>
			</tr>







	</body>
</html>
