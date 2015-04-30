
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
	</head>


	<script type="text/javascript">
	 $("document").ready(function(){
	 	
   			var radiochecked = $("#radiochecked").val(); 
			if(radiochecked==2){ 
				$("#bulletinStatus2").attr("checked","checked"); 
			} 
			var radiochecked1 = $("#radiochecked1").val(); 
			if(radiochecked1 ==2){
				$("#bulletinLanguage2").attr("checked","checked");
			}
			var radiochecked2 = $("#radiochecked2").val(); 
			if(radiochecked2 ==3){
			$("#bulletinLanguage3").attr("checked","checked");
			}
			var radiochecked3 = $("#radiochecked3").val(); 
			if(radiochecked3 ==1){
			$("#informationtips1").attr("checked","checked");
			}
			
      });
      
   	function gotoSubmit()
    {
    	var oldtext = document.getElementsByName("oldtext");
    	var upload = document.getElementsByName("upload"); 
   		if(oldtext.length<=0&&(upload[0].value==null||upload[0].value=="")){
   			alert("至少有一个文件上传项。");
   			document.getElementById("button").focus();
   			return false;
   		}
    	var title = document.getElementById("informationname").value;
        if(title==""){
        	document.getElementById("role_name_mess").innerHTML="请写标题！";
   			return false;
   		}
    	document.getElementById("role_name_mess").innerHTML="";
    	if(confirm("您是否确认保存?")){
        	document.updateform.submit(); 
        }
     }
     
     
	   	function jump(){
			window.location="${pageContext.request.contextPath}/page/information/getAllDataByPages.action";
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
	   		if(confirm("您是否删除?")){
				   	var filebutton = "#filebutton"+id;
				   	var deletebutton = "#deletebutton"+id;
				   	$(filebutton).remove();
				   	$(deletebutton).remove(); 
			   	}
	   } 
	   
	   
	   function remove_old(id)
	   {
	   		if(confirm("您是否删除?")){
		   		var old_div = "#old_div"+id;
		   		$(old_div).remove();
		   	}
	   }
  </script>

	<body>
		<input type="hidden" name="radiochecked" id="radiochecked" value="${radiochecked }" />
		<input type="hidden" name="radiochecked1" id="radiochecked1" value="${radiochecked1 }" />
		<input type="hidden" name="radiochecked2" id="radiochecked2" value="${radiochecked2 }" />
		<input type="hidden" name="radiochecked3" id="radiochecked3" value="${radiochecked3 }" />
		<s:form name="updateform" action="updataInformation.action"  enctype="multipart/form-data" method="post">
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
										
										<input type="hidden" name="information.informationid" id="information.informationid" value="<s:property value="information.informationid"/>" />
										<input type="hidden" name="information.accountname" id="information.accountname" value="<s:property value="information.accountname"/>" />
										<input type="hidden" name="information.fileuptime" id="information.fileuptime" value="<s:property value="information.fileuptime"/>" />
										<input type="hidden" name="information.fileurl" id="information.fileurl" value="<s:property value="information.fileurl"/>" />
										<input type="hidden" name="information.filename" id="information.filename" value="<s:property value="information.filename"/>" />
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
													<input id="informationname" name="information.informationname"
														type="text" value="<s:property value="information.informationname"/>"
														" onblur="javascript:char_valid('bulletinName','role_name_mess',3,30,'标题名称',2);"/>
												</td>
												<td width="272">
													<span class="mainred">
														<div id="role_name_mess">
															*
														</div> </span>
												</td>
											</tr>
												<tr>
												<td height="20" valign="top">
													文件上传
												</td>
												<td>
												
													<s:iterator value="#request.filelist" id="mop" status="stat">
													 <div id="old_div<s:property value="#stat.index" />">
												     <input type="text" name="oldtext" readonly="readonly"  value="<s:property value="#mop.name" />" />
												     <input type="button" value="修改" />&emsp;<input type="button"  onclick="javascript:remove_old('<s:property value="#stat.index" />')" value="删除" /> 
												     <br/>
												     </div>
											       </s:iterator> 
											        
													<input type="file" name="upload" id="button" />
													<input type="hidden" name="packName" value="upload" />
													<input type="button" value="添加" onclick="javascript:adddiv();" />
													
													<div id="add_div">
												
													</div>
													
												</td>
												<td width="272" valign="top">
													<span class="mainred">
														<div>
															*至少有一个文件上传项
														</div> </span>
												</td>
											</tr>
											
											<tr>
												<td height="20"></td>
												<td></td>
												<td width="272"></td>
											</tr>
											
											<tr>
												<td>
													是否置顶
												</td>
												<td>
												 
													<input name="informationtips" type="radio"
															id="informationtips2" value="2" <s:if test="information.informationtips==2">checked="checked"</s:if>  />
														否&emsp;
														<input name="informationtips" type="radio"
															id="informationtips1" value="1" <s:if test="information.informationtips==1">checked="checked"</s:if> />
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
													 
														<input name="informationtip" type="radio"
															id="bulletinStatus1" value="1" <s:if test="information.informationtip==1">checked="checked"</s:if>  />
														用户
														<input name="informationtip" type="radio"
															id="bulletinStatus3" value="3" <s:if test="information.informationtip==3">checked="checked"</s:if> />
														客户
														<input name="informationtip" type="radio"
															id="bulletinStatus2" value="2" <s:if test="information.informationtip==2">checked="checked"</s:if> />
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
													 
														<input name="filetip" type="radio"
															id="bulletinLanguage1" value="1" <s:if test="information.filetip==1">checked="checked"</s:if> />
														中文
														<input name="filetip" type="radio"
															id="bulletinLanguage2" value="2" <s:if test="information.filetip==2">checked="checked"</s:if> />
														英文
														<input name="filetip" type="radio"
															id="bulletinLanguage3" value="3" <s:if test="information.filetip==3">checked="checked"</s:if> />
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
														onclick="jump()" />
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
