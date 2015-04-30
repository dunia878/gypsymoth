<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ include file="../include/header.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统</title>
		<%@ include file="../include/head.jsp"%>
		<script language="javascript" type="text/javascript"
			src="${pageContext.request.contextPath}/js/jsFrame.js"></script>
	</head>

	<script type="text/javascript" charset="utf-8">
			function _onload(){
			var rolemessage = "${rolemsg}";
			if(!(rolemessage==""))
			{
				document.getElementById("role_name_mess").innerHTML="${rolemsg}";
			}
		}
      
   function gotoSubmit()
    {
    var title = document.getElementById("title").value;
    if(title==""){
   				document.getElementById("role_name_mess").innerHTML="标题不能为空！";
   				return false;
   			}
    
    	document.getElementById("content_mess").innerHTML="";
    	 var content = document.getElementById("content").value;
    if(content==""){
   				document.getElementById("content_mess").innerHTML="内容不能为空！";
   				return false;
   			}
    
    	document.getElementById("role_name_mess").innerHTML="";
    	
    	document.getElementById("addOnlineqaForm").submit();
    
     }
  </script>


	<body onload="_onload()">

		<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
			<tr>
				<td class="list_r">
					&nbsp;
				</td>
				<td valign="top" class="right">
					<div class="title">
						您的当前位置： 首页 &gt;&gt; 在线答疑 &gt;&gt;
						<strong>客户反馈专区</strong>
					</div>
					<div>

						<table align="center" cellpadding="12" class="tabedit">
							<tr>
								<td height="300" align="left" valign="top">
									<!--编辑开始-->
									<table width="700" align="center" cellspacing="5">
										<s:form action="save" method="post" id="addOnlineqaForm"
											name="addOnlineqaForm">
											<tr>
												<td height="25" colspan="3" align="left"
													style="border-bottom: dotted 1px #8EC0E8;">
													<span style="color: #FF0000;">*</span>号为必填项
												</td>
											</tr>
											<tr>
												<td width="68" height="20">
													标 题
												</td>
												<td width="332">
													<label>
														<input id="title" name="onlineqa.title" type="text"
															class="input" size="55"
															onblur="javascript:char_valid('title','role_name_mess',3,30,'标题名称',2);" />
													</label>
												</td>
												<td width="272">
													<span class="mainred">
														<div id="role_name_mess">
															*
														</div> </span>
												</td>
											</tr>
											<tr>
												<td height="20">
													内 容
												</td>
												<td>
													<textarea name="onlineqa.content" id="content" cols="53"
														rows="13"
														onblur="javascript:char_valid('content','content_mess',3,30,'内容',2);"></textarea>
												</td>
												<td>
													<span class="mainred">
														<div id="content_mess">
															*
														</div> </span>
												</td>
											</tr>
											<tr>
												<td height="20">
													备 注
												</td>
												<td>
													<input name="onlineqa.remark" type="text" class="input"
														size="55" />
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
														onclick="gotoSubmit();" value="提 交" />
													<input name="input4" class="button" type="reset"
														value="重 置" />
													<input name="input4" class="button" type="reset"
														value="返 回" onclick="history.back(-1)" />
												</td>
												<td align="left">
													&nbsp;
												</td>
											</tr>
										</s:form>
									</table>
									<!--编辑结束-->
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table>

	</body>
</html>
