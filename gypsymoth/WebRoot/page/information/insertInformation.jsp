
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


	<script type="text/javascript" language="javascript">
	   function gotoSubmit(){
	   
	   		var upload = document.getElementsByName("upload"); 
	   		if(upload[0].value==null||upload[0].value==""){
	   			alert("至少有一个文件上传项。");
	   			document.getElementById("button").focus();
	   			return false;
	   		}
	   
	     	var formatName = /^[a-zA-Z0-9_\u4e00-\u9fa5]+$/;
	     	var title = document.getElementById("informationname").value;
	     	if(title==""){
	     		document.getElementById("role_name_mess").innerHTML="请写标题！";
	   			return false;
	   		}
	    	document.getElementById("role_name_mess").innerHTML="";
	    	if(confirm("您是否确认保存?")){
	    		this.document.forms[0].submit();
	    	}else
	    	return false;
	     }
	     
	    var num = 0; 
	    function adddiv()
	    {
	    	var filehtml = '<input type="file" name="upload" id="filebutton'+num+'" />\
	    					<input type="button" name="deletebutton" id="deletebutton'+num+'"\
	    					onclick="javascript:deletediv('+num+');" value="删除" />'; 
	    	$("#add_div").append(filehtml);
	    	num++;
	    
	    } 
	     
	     
	    function deletediv(id)
	    {
	    	var filebutton = "#filebutton"+id;
	    	var deletebutton = "#deletebutton"+id;
	    	$(filebutton).remove();
	    	$(deletebutton).remove(); 
	    } 
	     
  </script>

	<body>
		<s:form action="save" enctype="multipart/form-data" method="post">
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
											<tr>
												<td height="25" colspan="3" align="left"
													style="border-bottom: dotted 1px #8EC0E8;">
													<span style="color: #FF0000;">*</span> 号为必填项
													<font color="red">${message }</font>
												</td>
											</tr>
											<tr>
												<td width="68" height="20">
													标题
												</td>
												<td width="332">
													<input id="informationname"
														name="information.informationname" type="text"
														 />
												</td>
												<td width="272">
													<span class="mainred">
														<div id="role_name_mess">
															*
														</div> 
													</span>
												</td>
											</tr>
											<tr>
												<td height="20">
													文件上传
												</td>
												<td>
													<input type="file" name="upload" id="button" />
													<input type="hidden" name="packName" value="upload" />
													  <input type="button" value="添加" onclick="javascript:adddiv();" />
												</td>
												<td width="272">
													<span class="mainred">
														<div id="password_mess">
														*
														</div> </span>
												</td>
											</tr>
											<tr>
												<td height="20"></td>
												<td><div id="add_div">
												
												</div></td>
												<td width="272"></td>
											</tr>
											<tr>
												<td>
													是否置顶
												</td>
												<td>
													 
														<input name="information.informationtips" type="radio"
															id="bulletinStatus2" value="2" checked="checked" />
														否 &emsp;
														<input name="information.informationtips" type="radio"
															id="bulletinStatus1" value="1" />
														是
												 
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
													 
														<input name="information.informationtip" type="radio"
															id="bulletinStatus1" value="1" checked="checked" />
														用户
														<input name="information.informationtip" type="radio"
															id="bulletinStatus3" value="3" />
														客户
														<input name="information.informationtip" type="radio"
															id="bulletinStatus2" value="2" />
														用户及客户
													 
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
														<input name="information.filetip" type="radio"
															id="information.filetip1" value="1" checked="checked" />
														中文
														<input name="information.filetip" type="radio"
															id="information.filetip2" value="2" />
														英文
														<input name="information.filetip" type="radio"
															id="information.filetip3" value="3" />
														中英文
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


												<td height="38" align="left"><br /></td>
												<td height="38" align="left">
													<input name="button" class="button" type="button"
														onclick="gotoSubmit()" value="保 存" />
													<input type="button" class="button" value="返 回"
														onclick="javascript:history.go(-1);" />
												</td>
												<td align="left">
													&nbsp;

												</td>
											</tr>
										</table>
										<!--编辑结束-->
									</td>
								</tr>
							</table>

						</div>
					</td>
				</tr>
			</table>
		</s:form>



	</body>
</html>
