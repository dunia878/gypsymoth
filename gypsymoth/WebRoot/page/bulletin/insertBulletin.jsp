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
	   function _onload(){
			var rolemessage = "${rolemsg}";
			if(!(rolemessage=="")){document.getElementById("role_name_mess").innerHTML="${rolemsg}"}
		                 }
       function gotoSubmit(){
           var formatName = /^[a-zA-Z0-9_\u4e00-\u9fa5]+$/;
           var title = document.getElementById("bulletinName").value;
           if(title==""){
            document.getElementById("role_name_mess").innerHTML="请填写标题！";
   			return false;
   			}
   	   // 	 else if(!formatName.test(title)){
   		    //  document.getElementById("role_name_mess").innerHTML="标题不能含有空格!";
   		//  return false;
   		 //    }
    	   document.getElementById("role_name_mess").innerHTML="";
    	   if(confirm("您是否确认添加?")){
           		this.document.forms[0].submit();
           }
     }
     function addCertificateFile() {
           var fileDiv = document.all['certificateDiv'];
           var strHtml = '<span><input type="file" name="certificate">&nbsp;&nbsp;<button onclick="removeFile(parentNode);"><s:text name="delete"/></button><br></span>';
           fileDiv.innerHTML += strHtml;
      } 
      
      var a = 1;
      function addRowToTable()
		{
		var name = "add_div"+a;
		a = a+1;
		var name2 = "add_div"+a;
		var strHtml = '<span id="d_span'+name2+'"><input type="file" name="certificate">&nbsp;&nbsp;<input type="button" onclick="removeFile(\'d_span'+name2+'\');" value="<s:text name="delete"/>"></input><br></span><div id="'+name2+'"></div>';
		document.getElementById(name).innerHTML +=strHtml;
		}
      
      function removeFile(spanname) {
          var spanvalue = "#"+spanname;
		  $(spanvalue).remove(); 
      }

      CKEDITOR.replace('bulletinContent');   //content为textarea的id

  function jump(){
  	window.location="${pageContext.request.contextPath}/page/bulletin/getAllDataByPages.action";
}


  function char_valid1(id,mess_id,start_len,end_len,field_name,isNull){
			var name = getField(id);
			if(isNull == 2 && isEmpty(name.value)){
					setNoteValue(mess_id,field_name+'不可为空','red');
					return false;
			}
		
		}
		
		function showExplain(){
			if(document.getElementById("explainDiv").style.display == "none"){
				document.getElementById("explainDiv").style.display = "block";
				document.getElementById("explainButton").value = "说明↑";
			}
			else{
				document.getElementById("explainDiv").style.display = "none";
				document.getElementById("explainButton").value = "说明↓";
			}
		}
  </script>
	<body onload="_onload()">
		<s:form action="save" enctype="multipart/form-data" method="post">
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
						<table align="center" cellpadding="12" class="tabedit">
							<tr><td align="left">
								　　<font color="red">* </font>若firefox无法正常显示，请
									<a href="https://addons.mozilla.org/firefox/downloads/file/103951/ie_tab_plus-1.95.20100933-fx+sm-win.xpi?src=addon-detail-version">
										<font color="red">点击此处</font></a>　<input id="explainButton" type="button" onclick="showExplain();" value="说明↓" class="button"/>
									<div id="explainDiv" style="display: none; background-color: yellow">
									安装插件后，选择切换规则，将http://agmtest.ccic.com:9090/gypsymoth/加入到规则中，然后确定。
									</div>
							</td></tr>
							<tr>
								<td height="300" align="left" valign="top">
									<!--编辑开始-->
									<table width="700" align="center" cellspacing="5">
										<tr>
											<td height="20">
												标题
											</td>
											<td>
												<input id="bulletinName" name="bulletin.bulletinName"
													size="80" type="text"
													onblur="javascript:char_valid1('bulletinName','role_name_mess',3,30,'标题名称',2);" />
											</td>
											<td width="100">
												<span class="mainred">
													<div id="role_name_mess">
														*
													</div> </span>
											</td>
										</tr>
									</table>

									<textarea id="bulletinContent" style="word-break: break-all"
										name="bulletinContent" class="ckeditor" cols="60" rows="15"></textarea>

									<table width="706" align="center" cellspacing="5">
										<tr>
											<td height="20">
												多图片上传
											</td>
											<td width="457">
												<label>
													<input type="file" name="certificate" id="certificate" />
												</label>
											</td>
											<td width="272">
												<a href="javascript:void(addRowToTable());">添加图片</a>

											</td>
											<td></td>
										<tr>
											<td width="180" height="20"></td>
											<td>
												<div id="add_div1" name="add_div1"></div>
											</td>
											<TD></TD>
										</tr>

										<tr>
											<td>
												是否置顶
											</td>
											<td>
												<label>
													<input name="bulletin.bulletinTips" type="radio"
														id="bulletinStatus2" value="2" checked="checked" />
													否&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<input name="bulletin.bulletinTips" type="radio"
														id="bulletinStatus1" value="1" />
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
													<input name="bulletin.bulletinTip" type="radio"
														id="bulletinStatus1" value="1" checked="checked" />
													用户
													<input name="bulletin.bulletinTip" type="radio"
														id="bulletinStatus3" value="3" />
													客户
													<input name="bulletin.bulletinTip" type="radio"
														id="bulletinStatus2" value="2" />
													用户及客户												
												</label>
											</td>
											<td width="100">
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
													<input name="bulletin.bulletinLanguage" type="radio"
														id="bulletin.bulletinLanguage" value="1" checked="checked" />
													中文
													<input name="bulletin.bulletinLanguage" type="radio"
														id="bulletin.bulletinLanguage" value="2" />
													英文
													<input name="bulletin.bulletinLanguage" type="radio"
														id="bulletin.bulletinLanguage" value="3" />
													中英文
												</label>
											</td>
											<td width="100">
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
													onclick="gotoSubmit()" value="添 加" />
												<input type="button" class="button" value="返回"
													onclick="jump()" />
											</td>
											<td align="left">
												&nbsp;
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
						<!--编辑结束-->
					</td>
				</tr>
			</table>
		</s:form>
	</body>
</html>
