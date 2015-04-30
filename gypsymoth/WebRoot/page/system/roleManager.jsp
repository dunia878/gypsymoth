<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统——角色管理</title>
		<%@ include file="../include/head.jsp"%>
	</head>
	<body>
					<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
					  <tr>
					    <td class="list_r">&nbsp;</td>
					    <td valign="top" class="right"><div class="title">您的当前位置： 首页 &gt;&gt; 系统管理 &gt;&gt; <strong>角色管理</strong></div>
					      <div>
					        <!--编辑开始-->
					        <c:forEach items="${menu_parent}" var="mp3" varStatus="status">
								<c:if test="${mp3.rightId == 114}">
									<table width="100%" border="0" cellpadding="6" cellspacing="0" class="tabman">
									<s:form  action="getRoleByPage" id="searchdatalist" theme="simple"
						name="searchdatalist">
							          <tr>
							            <td height="35" align="center">角色名称:&nbsp;
							                <input id="roleName" name="roleName" type="text" class="input" size="35" value="<s:property value="roleName"/>"/>
							              	&nbsp;角色状态:&nbsp;
							                    <label>
							                    <select name="roleStatus" class="select" id="roleStatus" style="width:200px;">
							                      <option value="">请选择状态</option>
							                      <option value="1" <s:if test="roleStatus == 1">selected="selected"</s:if> >启用</option>
							                      <option value="0" <s:if test="roleStatus == 0">selected="selected"</s:if> >禁用</option>
							                    </select>
							                    </label>
							              &nbsp;
							            <input name="查询" type="submit" class="button" value="查 询" /></td>
							          </tr>
							          </s:form>
							        </table>
								</c:if>
							</c:forEach>
					        <table width="100%" class="tableheader">
					          <tr>
					            <td width="5%" height="26"><img src="${pageContext.request.contextPath}/images/bg_mcontentL.gif"  width="32" height="27" /></td>
					            <td valign="middle"><h3>角色列表</h3></td>
					            <td align="right" valign="middle"><input type="submit" onclick="javascript:window.location.href='roleAdd.jsp'" class="button_s" value="添加角色" />
					              <!-- input type="submit" class="button_s" value="启 用" />
					              <input type="submit" class="button_s" value="停 用" /--></td>
					            <td width="1%" align="right"><img src="${pageContext.request.contextPath}/images/bg_mcontentR.gif"  width="7" height="27" /></td>
					          </tr>
					        </table>
					        <table width="100%" class="tableCont">
					          <tr>
					            <td>
					            <div class="tableContent">
					                  <table width="100%" border="0" cellspacing="1" cellpadding="0">
					                <s:form id="datalist" name="datalist" action="getRoleByPage" theme="simple">
									<tr>
					                      <!-- <th width="6%"><input name="chkall" type="checkbox" id="chkall" value="checkbox" /></th> -->
					                      <th width="5%">序号</th>
					                      <th>角色名称</th>
					                      <th width="12%" >角色状态</th>
					                      <th width="12%" >创建时间</th>
					                      <th>备　注</th>
					                      <th width="13%">操作</th>
					                </tr>
					                <s:if test="#request.RoleList.size>0">
					    			<s:iterator value="#request.RoleList" status="stat">
					                  <tr>
					                    <!-- <td align="center"><input type="hidden" name="roleId" value="<s:property value="roleId"/>" /><input type='checkbox' name='checkbox2' /></td> -->
					                    <td align="center"><s:property value="roleId"/></td>
					                    <td align="center"><s:property value="roleName"/></td>
					                    <td align="center">
					                    	<s:if test="roleStatus == 1">启用</s:if>
					                    	<s:if test="roleStatus == 0">禁用</s:if>
					                    </td>
					                    <td align="center"><s:property value="createDate"/></td>
					                    <td align="center"><s:property value="des"/></td>
					                    <td align="center">
					                    <s:if test="roleId==1">
					                    	<font color="#b3b3b3">编辑</font>
					                    </s:if>
					                    <s:else>
					                    	<a href="getRoleById.action?roleId=<s:property value="roleId"/>">编辑</a>
					                    </s:else>
					                     | 
					                    <s:if test="roleId==1 || roleId==2 || roleId==3 || roleId==4 || roleId==5 || roleId==6 || roleId==7">
					                    	<font color="#b3b3b3">删除</font>
					                    </s:if>
					                    <s:else>
					                    	<a href="deleteRoleById.action?roleId=<s:property value="roleId"/>" onclick="return confirm('您确定删除此角色吗？')">删除</a>
					                    </s:else>
					                    
					                    </td>
					                  </tr>
					                  </s:iterator>
					                  </s:if>
					                    </s:form>
					                  </table>
					            </div></td>
					          </tr>
					        </table>
					        <table width="98%" height="35" border="0" cellpadding="0" cellspacing="0">
					          <tr>
								<td height="25" align="right" class="right_tb1" colspan="3">
								<!--分页  -->
									<s:component template="pagev2.jsp" templateDir="page/pageutil" theme="template">
									<!--action name  -->
										<s:param name="formId" value="'searchdatalist'" />
										<s:param name="action_name" value="actionName" />
										<!-- name space -->
										<s:param name="namespace" value="'/page/system'" />
										<!-- pagination method name -->
										<s:param name="pagination_method_name" value="actionName" />
										<!--当前页前后显示的数量  -->
										<s:param name="distanceNum" value="5" />
									</s:component>
								</td>
							  </tr>
					        </table>
					        <!--编辑结束-->
					      </div>
					     </td>
					  </tr>
					</table>
	</body>
</html>
