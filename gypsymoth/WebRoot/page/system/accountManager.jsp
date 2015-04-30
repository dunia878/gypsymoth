<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统——用户管理</title>
		<%@ include file="../include/head.jsp"%>
		<script language="javascript" type="text/javascript"
			src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
	</head>
	<script type="text/javascript">
	function changeStatus(accountId,accountStatus){
		
		document.searchdatalist.hiddenAccountId.value=accountId;
		document.searchdatalist.hiddenAccountStatus.value=accountStatus;
		document.searchdatalist.action = "updateAccountStatus.action";
		document.searchdatalist.submit();
	
	}
	</script>
	<body>
		<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
			<tr>
				<td class="list_r">
					&nbsp;
				</td>
				<td valign="top" class="right">
					<div class="title">
						您的当前位置： 首页 &gt;&gt; 系统管理 &gt;&gt;
						<strong>用户管理</strong>
					</div>
					<div>
						<!--编辑开始-->
						<table width="100%" border="0" cellpadding="6" cellspacing="0"
							class="tabman">
							<s:form  action="getAccountByPage" id="searchdatalist" theme="simple"
						name="searchdatalist">
							<tr>
								<td height="35" align="center">
									<input type="hidden" id="hiddenAccountId" name="hiddenAccountId" />
									<input type="hidden" id="hiddenAccountStatus" name="hiddenAccountStatus" />
									用户名:&nbsp;
									<input name="accountName" type="text" class="input" size="10" id="accountName" value="${accountName }" />
									&nbsp; 
									姓名 / 公司:&nbsp;
									<input name="realName" type="text" class="input" size="17" id="realName" value="${realName }" />
									&nbsp; 
									状态:&nbsp;
									<label>
							        	<select name="accountStatus" class="select" id="accountStatus" style="width:100px;">
							            	<option value="">请选择状态</option>
							            	<option value="0" <s:if test="accountStatus == 0">selected="selected"</s:if> >未激活</option>
							            	<option value="1" <s:if test="accountStatus == 1">selected="selected"</s:if> >已激活</option>
							            	<option value="2" <s:if test="accountStatus == 2">selected="selected"</s:if> >已停用</option>
							        	</select>
							     	</label>
									&nbsp; 
									类型:&nbsp;
									<label>
							        	<select name="accountType" class="select" id="accountType" style="width:100px;">
							            	<option value="">请选择类型</option>
							            	<option value="1" <s:if test="accountType == 1">selected="selected"</s:if> >人员</option>
							            	<option value="2" <s:if test="accountType == 2">selected="selected"</s:if> >客户</option>
							        	</select>
							     	</label>
									&nbsp; 
									&nbsp;
									<input name="search" type="submit" class="button" value="查 询" />
								</td>
							</tr>
							</s:form>
						</table>
						<table width="100%" class="tableheader">
							<tr>
								<td width="5%" height="26">
									<img src="${pageContext.request.contextPath}/images/bg_mcontentL.gif" width="32" height="27" />
								</td>
								<td valign="middle">
									<h3>
										列表
									</h3>
								</td>
								<td align="right" valign="middle">
									<input type="submit"
										onclick="javascript:window.location.href='getAccountInitData.action'"
										class="button_s" value="添加用户" />
									<!-- input type="submit" class="button_s" value="删除" />
									<input type="submit" class="button_s" value="注销" / -->
								</td>
								<td width="1%" align="right">
									<img src="../images/bg_mcontentR.gif" width="7" height="27" />
								</td>
							</tr>
						</table>
						<table width="100%" class="tableCont">
							<tr>
								<td>
									<div class="tableContent">
										<table width="100%" border="0" cellspacing="1" cellpadding="0">
											<s:form>
												<tr>
													<!-- <th width="6%">
														<input name="chkall" type="checkbox" id="chkall"
															value="checkbox" />
													</th> -->
													<th>
														用户名
													</th>
													<th>
														姓名 / 公司
													</th>
													<th>
														类 型
													</th>
													<th>
														创建时间
													</th>
													<th width="25%">
														状 态
													</th>
													<th width="22%">
														操作
													</th>
												</tr>
											<s:if test="#request.accountList.size>0">
					    					<s:iterator value="#request.accountList" status="stat">
												<tr>
													<!-- <td align="center">
														<input type='checkbox' name='checkbox2' value="ACCOUNT_ID" />
													</td> -->
													<td align="center">
														${ACCOUNT_NAME }
													</td>
													<td align="center">
														${REALNAME }
													</td>
													<td align="center">
														<s:if test="ACCOUNT_TYPE==1"><font color="red">人员</font></s:if>
														<s:if test="ACCOUNT_TYPE==2">客户</s:if>
													</td>
													<td align="center">
														${CREATE_DATE }
													</td>
													<td align="center">
														<s:if test="ACCOUNT_STATUS==0"><font color="red">未激活</font></s:if>
														<s:if test="ACCOUNT_STATUS==1">已激活</s:if>
														<s:if test="ACCOUNT_STATUS==2">已停用</s:if>
														<input type="button" class="button" value="激活" <s:if test="ACCOUNT_STATUS==1">disabled="disabled" </s:if> onclick="changeStatus('${ACCOUNT_ID }','1')" />
														<input type="button" class="button" value="停用" <s:if test="ACCOUNT_STATUS==2 || ACCOUNT_STATUS==0">disabled="disabled" </s:if> onclick="changeStatus('${ACCOUNT_ID }','2')"/>
													</td>
													<td align="left">
														
														<s:if test="ACCOUNT_TYPE==1"><a href="getAccountById.action?accountId=<s:property value="ACCOUNT_ID"/>&&currPage=<s:property value="#request.currPage"/>">修改角色</a> | </s:if>
														<s:if test="ACCOUNT_TYPE==2"><font color="#b3b3b3">修改角色</font> | </s:if>
														<a href="updateAccountPassword.action?accountId=<s:property value="ACCOUNT_ID"/>&password=111111" onclick="return confirm('您确认重置此用户密码?')">密码重置</a> | 
														<a href="deleteAccountById.action?accountId=<s:property value="ACCOUNT_ID" />" onclick="return confirm('你确定删除此用户吗？')">删除 |</a>
														<s:if test="LOGIN_LOCK==0">
															<font color="#b3b3b3">用户解锁</font>
														</s:if>
														<s:if test="LOGIN_LOCK==1">
															<a href="updateLog.action">用户解锁</a>
														</s:if>
													</td>
												</tr>
											</s:iterator>
											</s:if>
											</s:form>
										</table>
									</div>
								</td>
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
