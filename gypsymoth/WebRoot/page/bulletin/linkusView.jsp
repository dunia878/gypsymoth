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
	<script language="JavaScript" type="text/javascript">
function save(){
document.form1.method="post";
document.form1.action="updataLink.action";
// document.form1.action="updataLinkus.action";
// document.form1.action="deleteLinkusById.action";
document.form1.submit();

}

 </script>
	<body>
		<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
			<s:form theme="simple" method="post" action="" id="form1"
				name="form1">
				<input type="hidden" name="linkusid" id="linkusid"
					value="<s:property value="linkus.linkusid"/>" />
				<tr>
					<td class="list_r">
						&nbsp;
					</td>
					<td valign="top" class="right">
						<div class="title">
						<s:text name="您的当前位置" />： <s:text name="首页" /> &gt;&gt; 	<s:text name="联系我们" /> &gt;&gt;
						<strong><s:text name="检验公司(总部)" /></strong>
						</div>
						<div>
							<table align="center" cellpadding="12" class="tabedit">

								<tr>
									<td height="300" align="left" valign="top">
										<!--编辑开始-->
										<table width="706" align="center" cellspacing="1">
										<!-- 
											<tr>
												<td align="center">

													<h5><font color="#2894FF"><s:property value="linkus.linkusname" /></font></h5>

												</td>
											</tr>
											<tr>
												<td height="1" colspan="1" align="left"
													style="border-bottom: solid 1px #8EC0E8;">
													<table width="100%" height="1" border="0" cellpadding="0"
														cellspacing="0" >
														<tr>
															<td>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										 -->
											<tr>
												<td align="center" height="30">
													<s:text name="更新时间" /> : [
													<s:date name="linkus.createtime" format="yyyy-MM-dd" />
													]
												</td>
											</tr>
										</table>

										<FCK:editor id="linkuscontent" basePath="../FCKeditor/"
											toolbarSet="Default" word-wrap: break-word>
                                                      ${linkus.linkuscontent}   
                                                </FCK:editor>

										<table width="706" align="center" cellspacing="1">
											<tr>
												<td align="center" height="50">
												</td>
											</tr>
											<c:forEach items="${menu_parent}" var="mp3"
												varStatus="status">
												<c:if test="${mp3.rightId == 811}">
													<tr>
														<td height="38" align="center">
															<input name="button" class="button" type="button"
																onclick="save()" value="更新" />
														</td>
													</tr>
												</c:if>
											</c:forEach>
										</table>
										<!--编辑结束-->
									</td>
									<!-- 主内容结束 -->
								</tr>
							</table>
						</div>
					</td>
				</tr>
			</s:form>
		</table>
	</body>
</html>
