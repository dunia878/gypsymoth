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
    		document.form[0].submit();
     }
     
     function downlaod()
     {

     	document.form1.submit();
     }	
          function jump(){
	window.location="${pageContext.request.contextPath}/page/information/getAllADataByPage.action";
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
						<table align="center" cellpadding="12" class="tabedit">

							<tr>
								<td height="300" align="left" valign="top">
									<!--编辑开始-->
									<table width="706" align="center" cellspacing="5">
										<s:form action="downloadFile.action" name="form1" id="form1"
											method="post">
											<input type="hidden" name="informationid" id="informationid"
												value="<s:property value="informationid"/>" />
											<input type="hidden" name="fileurl" id="fileurl"
												value="<s:property value="information.fileurl"/>" />
											<input type="hidden" name="filename"
												value="<s:property value="information.filename"/>" />


											<tr>
												<td width="68" height="20">
													标题
												</td>
												<td width="332">
													<s:property value="information.informationname" />
												</td>
												<td>
													&nbsp;
												</td>
											</tr>
											<tr>
												<td height="20">
													下载文件
												</td>
												<td>
													<a href="#" onclick="downlaod();"> <span
														class="mainred">
															<div>
																<s:property value="information.filename" />
															</div> </span>
													</a>
												</td>
												<td>
													&nbsp;
												</td>
											</tr>

											<tr>
												<td height="20">
													发布时间
												</td>
												<td width="332">
													<s:date name="information.fileuptime"
														format="yyyy-MM-dd HH:mm" />
												</td>
												<td>
													&nbsp;
												</td>
											</tr>

											<tr>
												<td height="20">
													发布人
												</td>
												<td width="332">
													<s:property value="information.accountname" />
												</td>
												<td>
													&nbsp;
												</td>
											</tr>

											<tr>
												<td height="20">
													查看权限
												</td>
												<td >用户
													<!-- <s:radio list="#{1:'用户'}"
														value="information.informationtip"></s:radio>  -->
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
													<input name="return" class="button" type="button"
														value="返 回" onclick="jump()" />
												</td>
												<td align="left">
													&nbsp;
												</td>
											</tr>
										</s:form>
									</table>
									<!--编辑结束-->
								</td>
								<!-- 主内容结束 -->
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table>

	</body>
</html>
