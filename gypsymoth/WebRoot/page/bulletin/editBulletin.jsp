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
	
	 $("document").ready( function(){
   			 
      });
	   function _onload(){
			var rolemessage = "${rolemsg}";
			if(!(rolemessage=="")){document.getElementById("role_name_mess").innerHTML="${rolemsg}"}
		                 }
     function save(){
         var formatName = /^[a-zA-Z0-9_\u4e00-\u9fa5]+$/;
         var title = document.getElementById("bulletinName").value;
           if(title==""){
           document.getElementById("role_name_mess").innerHTML="请填写标题!";
   			return false;
   		   }
   		  //   else if(!formatName.test(title)){
   		   //  document.getElementById("role_name_mess").innerHTML="标题不能含有空格!";
   			//  return false;
   		  //  }
    	   document.getElementById("role_name_mess").innerHTML="";
     
            document.forms[0].method="post";
            document.forms[0].action="updataBulletin.action";
            if(confirm("您是否确认保存?")){
            	document.forms[0].submit();
            }
     }
        var a=1;
         function addCertificateFile() 
         	{
		var name = "add_div"+a;
		a = a+1;
		var name2 = "add_div"+a;
		var strHtml = '<span id="d_span'+name2+'"><input type="file" name="certificate">&nbsp;&nbsp;<input type="button" onclick="removeFile(\'d_span'+name2+'\');" value="<s:text name="delete"/>"></input><br></span><div id="'+name2+'"></div>';
		document.getElementById(name).innerHTML +=strHtml;
		}
		
          //  {  var fileDiv = document.all['certificateDiv'];
          //  var strHtml = '<span><input type="file" name="certificate">&nbsp;&nbsp;<button onclick="removeFile(parentNode);"><s:text name="delete"/></button><br></span>';
          // fileDiv.innerHTML += strHtml;
          // } 
      
       function removeFile(spanname) {
          var spanvalue = "#"+spanname;
		  $(spanvalue).remove(); 
      }
      
        CKEDITOR.replace('bulletinContent');   //content为textarea的id
        function jump(){
	window.location="${pageContext.request.contextPath}/page/bulletin/getAllDataByPages.action";
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
						您的当前位置： 首页 &gt;&gt; 信息与资料 &gt;&gt;
						<strong>公告通知</strong>
					</div>
					<div>
						<table align="center" cellpadding="12" class="tabedit">
							<tr>
								<td height="300" align="left" valign="top">
									<!--编辑开始-->
									<s:form action="" enctype="multipart/form-data" method="post">
										<table width="706" align="center" cellspacing="5">

											<input type="hidden" name="bulletin.bulletinId"
												id="bulletin.bulletinId"
												value="<s:property value="bulletin.bulletinId"/>" />
											<input type="hidden" name="bulletin.accountName"
												id="bulletin.accountName"
												value="<s:property value="bulletin.accountName"/>" />
											<input type="hidden" name="bulletin.bulletinTime"
												id="bulletin.bulletinTime"
												value="<s:property value="bulletin.bulletinTime"/>" />
											<input type="hidden" name="bulletin.filename"
												id="bulletin.filename"
												value="<s:property value="bulletin.filename"/>" />
											<input type="hidden" name="bulletin.fileurl"
												id="bulletin.fileurl"
												value="<s:property value="bulletin.fileurl"/>" />
											<tr>
												<td width="68" height="20">
													标题
												</td>
												<td width="332">
													<input id="bulletinName" name="bulletin.bulletinName"
														size="80" type="text"
														value="<s:property value="bulletin.bulletinName"/>"
														" onblur="javascript:char_valid('bulletinName','role_name_mess',3,30,'标题名称',2);" />
												</td>
												<td width="272">
													<span class="mainred">
														<div id="role_name_mess">
															*
														</div> </span>
												</td>
											</tr>
										</table>

										<textarea id="bulletinContent" style="word-break: break-all"
											name="bulletinContent" class="ckeditor" cols="53" rows="15">
													    	 ${bulletin.bulletinContent}  
															</textarea>

										<table width="706" align="center" cellspacing="5">
											<tr>
												<td width="180" height="20">
													多图片上传
												</td>
												<td width="332">
													<label>
														<input type="file" name="certificate" id="certificate" />
													</label>
												</td>
												<td width="272">
													<a href="javascript:void(addCertificateFile());">添加图片</a>
												</td>
												<td></td>
											<tr>
												<td width="180" height="20"></td>
												<td>
												 <div id="add_div1" name="add_div1"></div>
												</td>
											</tr>
											<tr>
												<td>
													是否置顶
												</td>
												<td>
													<label>
													<input name="bulletinTips" type="radio"
															id="bulletinTips2" value="2" <s:if test="#request.BULLETINTIPS==2">checked="checked"</s:if>  />
														否&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														<input name="bulletinTips" type="radio"
															id="bulletinTips1" value="1" <s:if test="#request.BULLETINTIPS==1">checked="checked"</s:if> />
														是
													</label>  
												</td>
												<td width="272">
													<span class="mainred">
														<div>
															*
														</div> </span>
												</td>
											</tr>



											<tr>
												<td>
													查看权限
												</td>
												<td>
													<label>
														<input name="bulletinTip" type="radio"
															id="bulletinStatus1" value="1" <s:if test="#request.BULLETINTIP==1">checked="checked"</s:if> />
														用户														
														<input name="bulletinTip" type="radio"
															id="bulletinStatus3" value="3" <s:if test="#request.BULLETINTIP==3">checked="checked"</s:if> />
														客户
														<input name="bulletinTip" type="radio"
															id="bulletinStatus2" value="2" <s:if test="#request.BULLETINTIP==2">checked="checked"</s:if> />
														用户及客户
													</label>
												</td>
												<td width="272">
													<span class="mainred">
														<div>
															*
														</div> </span>
												</td>
											</tr>
											<tr>
												<td>
													查看语言
												</td>
												<td>
													<label>
														<input name="bulletinLanguage" type="radio" 
															id="bulletinLanguage1" value="1" <s:if test="#request.BULLETINLANGUAGE==1">checked="checked"</s:if> />
														中文
														<input name="bulletinLanguage" type="radio"
															id="bulletinLanguage2" value="2" <s:if test="#request.BULLETINLANGUAGE==2">checked="checked"</s:if> />
														英文
														<input name="bulletinLanguage" type="radio"
															id="bulletinLanguage3" value="3" <s:if test="#request.BULLETINLANGUAGE==3">checked="checked"</s:if> />
														中英文
													</label>
												</td>
												<td width="272">
													<span class="mainred">
														<div>
															*
														</div> </span>
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
														onclick="save()" value="保 存" />
													<input type="button" class="button" value="返回"
														onclick="jump()" />
												</td>
												<td align="left">
													&nbsp;
												</td>
											</tr>
										</table>
									</s:form>
						</table>
						<!--编辑结束-->
				</td>
			</tr>
		</table>






	</body>
</html>
