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
			src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
		<script language="javascript" type="text/javascript"
			src="${pageContext.request.contextPath}/js/jsFrame.js"></script>
	</head>
	<script type="text/javascript">
 function gotoSubmit(){
            var title = document.getElementById("linkusname").value;
            if(title==""){
   				document.getElementById("title_name_mess").innerHTML="<s:text name='title_error'/>!";
   				return false;
   		         	}
    	    document.getElementById("title_name_mess").innerHTML="";
    	    
           this.document.forms[0].submit();
     }
     
     CKEDITOR.replace('linkuscontent');
   function jump(){
  	window.location="${pageContext.request.contextPath}/page/bulletin/getLinkusByType1.action";
}
  </script>
	<body >
		<s:form action="saveLinkus1" enctype="multipart/form-data" method="post">
			<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
			<input type="hidden" name="linkusid" id="linkusid"
					value="<s:property value="linkus.linkusid"/>" />
				<tr>
					<td class="list_r">
						&nbsp;
					</td>
					<td valign="top" class="right">
						<div class="title">
								<s:text name="您的当前位置" />： <s:text name="首页" /> &gt;&gt; 	<s:text name="联系我们" /> &gt;&gt;
						<strong><s:text name="各地方公司" /></strong>
						</div>
						<table align="center" cellpadding="12" class="tabedit">
							<tr>
								<td height="300" align="left" valign="top">
									<!--编辑开始-->
									<input id="linkusname" name="linkus.linkusname"
													size="80" type="hidden" value="无" />
									<!-- <table width="700" align="center" cellspacing="5">
										<tr>
											<td height="20">
												标题
											</td>
											<td>
												
											</td>
											<td width="100">
												<span class="mainred">
													<div id="title_name_mess">
															*<s:text name="标题提示"/>
													</div> </span>
											</td>
										</tr>
									</table>
									 -->

									<textarea id="linkuscontent" style="word-break: break-all"
										name="linkuscontent" class="ckeditor" cols="60" rows="15"></textarea>

									<table width="706" align="center" cellspacing="5">
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
												<input name="button" class="button" type="submit"
													 value="添 加" />
												<input type="button" class="button" value="返回"
													onclick="jump()" />
											</td>
											<td align="left">
												&nbsp;
											</td>
										</tr>
									</table>
						<!--编辑结束--></table>
					</td>
				</tr>
			</table>
		</s:form>
	</body>
</html>
