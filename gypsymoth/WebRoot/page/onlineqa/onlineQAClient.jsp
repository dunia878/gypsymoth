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
	
	    function char_valid1(id,mess_id,start_len,end_len,field_name,isNull){
			var name = getField(id);
			if(isNull == 2 && isEmpty(name.value)){
					setNoteValue(mess_id,field_name+'不可为空','red');
					return false;
			}

		}
		
		
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
   				document.getElementById("role_name_mess").innerHTML="<s:text name='title_error'/>!";
   				return false;
   			}
    
    	document.getElementById("content_mess").innerHTML="";
    	 var content = document.getElementById("content").value;
    if(content==""){
   				document.getElementById("content_mess").innerHTML="<s:text name='content_error'/>!";
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
						<s:text name="您的当前位置"/>： <s:text name="首页"/> &gt;&gt; <s:text name="在线答疑"/> &gt;&gt; <strong><s:text name="客户反馈专区"/></strong>
					</div>
					<div>

						<table align="center" cellpadding="12" class="tabedit">
							<tr>
								<td height="300" align="left" valign="top">
									<!--编辑开始-->
									<table width="700" align="center" cellspacing="5">
										<s:form action="saveclient" method="post" id="addOnlineqaForm"
											name="addOnlineqaForm" namespace="/page/onlineqa">
											<tr>
												<td height="25" colspan="3" align="left"
													style="border-bottom: dotted 1px #8EC0E8;">
													<span style="color: #FF0000;">*</span>
													<s:text name="号为必填项"/> 
													<font color="red">${message }</font>
												</td>
											</tr>
											<tr>
												<td width="68" height="20">
													<s:text name="标题"/>
												</td>
												<td width="332">
													<label>
														<input id="title" name="onlineqa.title" type="text"
															class="input" size="55"
															 />
													</label>
												</td>
												<td width="272">
													<span class="mainred">
														<div id="role_name_mess">
															*<s:text name="标题提示"/>
														</div> </span>
												</td>
											</tr>
											<tr>
												<td height="20">
														<s:text name="内容"/>
												</td>
												<td>
													<textarea name="onlineqa.content" id="content" cols="53"
														rows="13"
														></textarea>
												</td>
												<td>
													<span class="mainred">
														<div id="content_mess">
															*<s:text name="内容提示"/>
														</div> </span>
												</td>
											</tr>
											<tr>
												<td height="20">
													<s:text name="备注"/>
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
														onclick="gotoSubmit();"  value="<s:text name='submit'/>" />
													<input name="input" class="button" type="reset"
														value="<s:text name='reset'/>" />
													<input name="input4" class="button" type="reset"
														value="<s:text name='return'/>"   onclick="history.back(-1)" />
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
