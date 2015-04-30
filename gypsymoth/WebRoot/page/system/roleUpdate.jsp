<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统——权限管理</title>
		<%@ include file="../include/head.jsp"%>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/dchecktree.js"></script>
		<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/jsFrame.js"></script>
	</head>
	
	<script type="text/javascript" charset="utf-8">
		function _onload(){
			var rolemessage = "${rolemsg}";
			if(!(rolemessage==""))
			{
				document.getElementById("role_name_mess").innerHTML="${rolemsg}";
			}
			_roleRights();
		}
		function _roleRights(){
			d.openAll();
	 		<s:iterator value="#request.righthas">
	 		var form = document.getElementById("editRoleForm");
			loop:for (var i=0; i<form.elements.length; i++) {
					var element = form.elements[i];
					if (element.name == "id" && element.type=='checkbox'){
					if(element.value=='<s:property value="rightId"/>')
					{
						element.checked=true;
						break loop;
					}
				}
			}		
			//d.add('<s:property value="rightId"/>','<s:property value="parentid"/>','<s:property value="rightName"/>','javascript:gotoUrl("blocktype=6");');
    		</s:iterator>   
		}
        function checkAdd(){
        	var rolename = document.getElementById("roleName").value;
 			var formatName = /^[a-zA-Z0-9_\u4e00-\u9fa5]+$/;
 			if(rolename==""){
   				document.getElementById("role_name_mess").innerHTML="角色不能为空！";
   				return false;
   			}else if(!formatName.test(rolename)){
   				document.getElementById("role_name_mess").innerHTML="角色名称只能是汉字、字母、数字或下划线!";
   				return false;
   			}else if(rolename.length<2)
   			{
   				document.getElementById("role_name_mess").innerHTML="角色不能少于2个字符！";
   				return false;
   			}else if(rolename.length>30)
   			{
   				document.getElementById("role_name_mess").innerHTML="角色不能多于30个字符！";
   				return false;
   			}
   			document.getElementById("role_name_mess").innerHTML="";
 			
 			var desc = document.getElementById("des").value;
 			if(desc!=""&&desc.length>500){
   				
   				document.getElementById("des_mess").innerHTML="描述不能超过500字符！";
   				return false;
   			}
   			document.getElementById("des_mess").innerHTML="";
   			
 			var rolerights = new Array();
 			var form_operation = document.getElementById("editRoleForm");
 			for(var i=0,j=0;i<form_operation.elements.length;i++){
 				var element = form_operation.elements[i];
 				if (element.name == "id" && element.type=='checkbox'&& element.checked==true){
 					rolerights[j] = element.value;
 					j++;
 				}
 			}
 			if(rolerights.length==0){
 				alert("请选择权限");
 				return false;
 			}
 			document.getElementById("tempRight").value=rolerights;
   			document.getElementById("editRoleForm").submit();
        }
    </script>
	<body onload="_onload()">
					<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
					  <tr>
					    <td class="list_r">&nbsp;</td>
					    <td valign="top" class="right"><div class="title">您的当前位置： 首页 &gt;&gt; 系统管理 &gt;&gt; <strong>角色管理</strong></div>
					      <div>
					        <table align="center" cellpadding="12" class="tabedit">
					          <tr>
					            <td height="300" align="left" valign="top">
					            <!--编辑开始-->
					                <table width="700" align="center" cellspacing="5">
					                <s:form theme="simple" method="post" action="updateRole"
									id="editRoleForm" name="editRoleForm">
									<input type="hidden" name="tempRight" id="tempRight" />
									<input type="hidden" name="role.roleId" id="role.roleId" value="<s:property value="role.roleId"/>" />
									<input type="hidden" name="role.createBy" id="role.createBy" value="<s:property value="role.createBy"/>" />
									<input type="hidden" name="role.createDate" id="role.createDate" value="<s:property value="role.createDate"/>" />
					                  <tr>
									    <td height="25" colspan="3" align="left"
												style="border-bottom: dotted 1px #8EC0E8;">
												<span style="color: #FF0000;">*</span>号为必填项
									    </td>
									  </tr>
					                  <tr>
					                    <td width="68" height="20">角色名称</td>
					                    <td width="332"><label>
					                      <input id="roleName" name="role.roleName" type="text" class="input" size="55" value="<s:property value="role.roleName"/>"
					                      		onblur="javascript:char_valid('roleName','role_name_mess',2,30,'角色名称',2);"/>
					                    </label></td>
					                    <td width="272">
					                    	<span class="mainred">
													<div id="role_name_mess">
														*
													</div> 
											</span>
					                    </td>
					                  </tr>
					                  <tr>
					                    <td height="20">状　　态</td>
					                    <td><label>
					                      <input name="role.roleStatus" type="radio" id="roleStatus1" value="1" <s:if test="role.roleStatus == 1">checked="checked"</s:if> />
					                      启用</label><label>
					                      <input type="radio" name="role.roleStatus" id="roleStatus2" value="0" <s:if test="role.roleStatus == 0">checked="checked"</s:if>/>
					                      停用</label></td>
					                    <td><span class="mainred">*</span></td>
					                  </tr>
					                  <tr>
					                    <td height="20">角色描述</td>
					                    <td><label>
										  <textarea name="role.des" id="des" rows="5" cols="41" 
										  	onblur="javascript:char_valid('des','des_mess',-1,500,'描述',1);"><s:property value="role.des"/></textarea>
					                    </label></td>
					                    <td><span class="mainred"><div id="des_mess"></div> </span></td>
					                  </tr>
					                  <tr>
					                    <td height="20" valign="top">拥有权限</td>
					                    <td><%@ include file="../include/rightcheck_edit_tree.jsp"%></td>
					                    <td>&nbsp;</td>
					                  </tr>
					                  <tr>
					                    <td height="38" align="left"></td>
					                    <td height="38" align="left">
					                    	<input name="" class="button" type="button" value="修 改"
													onclick="checkAdd()" />
											<input name="" class="button"
													onclick="javascript:history.back(-1)" type="reset"
													value="返 回" />    
					                    </td>
					                    <td align="left">&nbsp;</td>
					                  </tr>
					                  </s:form>
					                </table>
					              <!--编辑结束-->
					            </td>
					          </tr>
					        </table>
					      </div></td>
					  </tr>
					</table>
	</body>
</html>
