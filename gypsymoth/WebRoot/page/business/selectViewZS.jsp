<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统——业务查询</title>
		<%@ include file="../include/head.jsp"%>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/dchecktree.js"></script>
		<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/jsFrame.js"></script>
	</head>
	
	<script type="text/javascript" charset="utf-8">
        function checkAdd(){
   			document.getElementById("editSelectForm").submit();
        }
    </script>
	<body>
					<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
					  <tr>
					    <td class="list_r">&nbsp;</td>
					    <td valign="top" class="right"><div class="title">您的当前位置： 首页 &gt;&gt; 业务管理 &gt;&gt; <strong>业务查询</strong></div>
					      <div>
					        <table align="center" cellpadding="12" class="tabedit">
					          <tr>
					            <td height="300" align="left" valign="top">
					            <!--编辑开始-->
					            <s:if test="#request.selectList.size>0">
					    		<s:iterator value="#request.selectList" status="stat">
					                <table width="700" align="center" cellspacing="5">
					                  <tr>
									    <td height="25" colspan="3" align="left"
												style="border-bottom: dotted 1px #8EC0E8;">
												<span style="color: #FF0000;">*</span>号为必填项
									    </td>
									  </tr>
					                  <tr>
					                    <td width="68" height="20">申请人</td>
					                    <td width="332"><label>
					                      <input id="roleName" name="role.roleName" type="text" class="input" size="55" value="${BUSINESSNAME }"/>"
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
					                    <td width="68" height="20">船东或租船人名称</td>
					                    <td width="332"><label>
					                      <input id="roleName" name="role.roleName" type="text" class="input" size="55" value="${COMPANY }"/>"
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
					                    <td width="68" height="20">船名</td>
					                    <td width="332"><label>
					                      <input id="roleName" name="role.roleName" type="text" class="input" size="55" value="${VESSELNAME }"/>"
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
					                    <td width="68" height="20">船舶类型</td>
					                    <td width="332"><label>
					                      <input id="roleName" name="role.roleName" type="text" class="input" size="55" value="${VESSELTYPE }"/>"
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
					                    <td width="68" height="20">船籍</td>
					                    <td width="332"><label>
					                      <input id="roleName" name="role.roleName" type="text" class="input" size="55" value="${REGISTRY }"/>"
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
					                    <td width="68" height="20">IMO编号</td>
					                    <td width="332"><label>
					                      <input id="roleName" name="role.roleName" type="text" class="input" size="55" value="${IMO }"/>"
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
					                    <td width="68" height="20">总吨位</td>
					                    <td width="332"><label>
					                      <input id="roleName" name="role.roleName" type="text" class="input" size="55" value="${TONNAGE }"/>"
					                    </label></td>
					                    <td width="272">
					                    	<span class="mainred">
													<div id="role_name_mess">
														*
													</div> 
											</span>
					                    </td>
					                  </tr>
					                  <s:if test="#request.desCountrylist.size>0">
					                  	<s:iterator value="#request.desCountrylist" status="stat">
					                  <tr>
					                    <td width="68" height="20">目的国及港口</td>
					                    <td width="332"><label>
					                      <input id="roleName" name="role.roleName" type="text" class="input" size="55" value="${COUNTRY }"/>"
					                      <input id="roleName" name="role.roleName" type="text" class="input" size="55" value="${PORT }"/>"
					                    </label></td>
					                    <td width="272">
					                    	<span class="mainred">
													<div id="role_name_mess">
														*
													</div> 
											</span>
					                    </td>
					                  </tr>
					                  </s:iterator>
					                  </s:if>
					                  <tr>
					                    <td width="68" height="20">船舶停靠港</td>
					                    <td width="332"><label>
					                      <input id="roleName" name="role.roleName" type="text" class="input" size="55" value="${PORT_NAME }" />
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
					                    <td width="68" height="20">泊位、锚位</td>
					                    <td width="332"><label>
					                      <input id="roleName" name="role.roleName" type="text" class="input" size="55" value="${BERTH }" />
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
					                    <td width="68" height="20">计划离港日期</td>
					                    <td width="332"><label>
					                      <input id="roleName" name="role.roleName" type="text" class="input" size="55" value="${PLANDATEOUT }" />
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
					                    <td width="68" height="20">检查日期</td>
					                    <td width="332"><label>
					                      <input id="roleName" name="role.roleName" type="text" class="input" size="55" value="${OPERATORTIME }" />
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
					                    <td width="68" height="20">授权签字人</td>
					                    <td width="332"><label>
					                      <input id="roleName" name="role.roleName" type="text" class="input" size="55" value="" />
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
					                    <td width="68" height="20">证书纸流水号</td>
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
					                </table>
					              </s:iterator>
					              </s:if>
					              <!--编辑结束-->
					            </td>
					          </tr>
					        </table>
					      </div></td>
					  </tr>
					</table>
	</body>
</html>
