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
     
     function jump(){
	window.location="${pageContext.request.contextPath}/page/bulletin/getAllADataByPage.action";
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
						<table width="100%" class="tableCont">
							<tr>
								<td>
									<table width="100%" border="0" cellspacing="5" cellpadding="12">
										<tr>
											<td align="center" class="news"
												style="font-size: 17px; border-bottom: dotted 1px #8EC0E8;">
												<span class="news" style="font-size: 16px;"><strong>
														<s:property value="bulletin.bulletinName" /> </strong> </span>
											</td>
										</tr>
										<tr>
											<td class="dot" style="font-size: 14px; line-height: 25px;">
												<p>
													<FCK:editor id="bulletinContent" basePath="../FCKeditor/"
														height="300" toolbarSet="Default"> ${bulletin.bulletinContent} </FCK:editor>
													<s:iterator value="#request.bulletinlist" status="st">
														<div align="center">
															<img
																src="${pageContext.request.contextPath}<s:property />"
																onload="if(this.width>750){this.width=750}" />
														</div>
													</s:iterator>
												<div style="float: right;">
													作者：中检集团 日期：
													<s:date name="bulletin.bulletinTime" format="yyyy-MM-dd " />
												</div>

											</td>
										</tr>

									</table>

								</td>
							</tr>

						</table>
						<table width="100%" >
							<tr>
								<td height="38" align="center">
									<input type="button" name="return" class="button" value="返 回"
										onclick="jump()" />
								</td>
								<td height="38" align="center">
									<br />
								</td>
								<td height="38" align="center">

									<br />
								</td>
								<td align="left">
									&nbsp;
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