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
	<!-- 网页过期限制 -->
	<%
		response.setHeader("Cache-Control ", "Public ");
		response.setHeader("Pragma ", "no-cache ");
		response.setDateHeader("Expires ", 0);
	%>
	<!-- JS验证表单部分 -->
	<script type="text/javascript">
   function gotoSubmit()
    {
    		this.document.forms[0].submit();
     }
     
     
var flag=false;
function DrawImage(ImgD,FitWidth,FitHeight){
var image=new Image();
image.src="<s:property />";
if(image.width>0 && image.height>0){
flag=true;
if(image.width/image.height>= FitWidth/FitHeight){
if(image.width>FitWidth){
ImgD.width=FitWidth;
ImgD.height=(image.height*FitWidth)/image.width;
}else{
ImgD.width=image.width;
ImgD.height=image.height;
}
ImgD.alt="原图片大小("+image.width+"×"+image.height+")";
}
else{
if(image.height>FitHeight){
ImgD.height=FitHeight;
ImgD.width=(image.width*FitHeight)/image.height;
}else{
ImgD.width=image.width;
ImgD.height=image.height;
}
ImgD.alt="原图片大小("+image.width+"×"+image.height+")";
}
}
}

function jump(){
	window.location="${pageContext.request.contextPath}/page/bulletin/getAllDataByPages.action";
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
						<table align="center" cellpadding="12" class="tabedit">
							<tr>
								<td height="300" align="left" valign="top">
									<!--编辑开始-->
									<table width="706" align="center" cellspacing="5">
										<tr>
											<td height="20" width="68">
												标题
											</td>
											<td>
												<s:property value="bulletin.bulletinName" />
											</td>
											<td>
												&nbsp;
											</td>
										</tr>
										<tr>
											<td height="20" width="68">
												内容
											</td>
											<td>
											
											</td>
											<td>
												&nbsp;
											</td>
										</tr>
									</table>
						
									<FCK:editor id="bulletinContent" basePath="../FCKeditor/"
										 toolbarSet="Default" word-wrap:
										break-word>
                                                      ${bulletin.bulletinContent}   
                                                </FCK:editor>
							
						
							<table width="706" align="center" cellspacing="5">
								<tr>
									<s:iterator value="#request.bulletinlist" status="st">
										<tr>
											<td></td>
											<td align="center">
												<img src="${pageContext.request.contextPath}<s:property/>"
													onload="if(this.width>600){this.width=600}" />
											</td>
										
										<td></td>
										</tr>
									</s:iterator>
								</tr>
								<tr>
									<td width="68" height="20">
										发布时间
									</td>
									<td>
										<s:date name="bulletin.bulletinTime" format="yyyy-MM-dd HH:mm" />
									</td>
									<td>
										&nbsp;
									</td>
								</tr>
								<tr>
									<td height="20">
										发布人
									</td>
									<td>
										<s:property value="bulletin.accountName" />
									</td>
									<td>
										&nbsp;
									</td>
								</tr>
								<tr>
									<td height="20">
										查看权限
									</td>
									<td cospla>
										<s:set name="bulletinTip" value="bulletin.bulletinTip" />
										<s:if test="#bulletinTip >1">  客户 </s:if>
										<s:elseif test="#bulletinTip<2">  用户 </s:elseif>
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
										<input name="return" class="button" type="button" value="返 回"
											onclick="jump()" />
									</td>
									<td align="left">
										&nbsp;
									</td>
								</tr>
							</table>
							<!--编辑结束-->
							</td>
							<!-- 主内容结束 -->
	</body>
</html>
