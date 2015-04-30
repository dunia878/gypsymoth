<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统——财务统计</title>
		<%@ include file="../include/head.jsp"%>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajax.js"></script>
		<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/jsFrame.js"></script>
	</head>

	<script type="text/javascript" charset="utf-8">
	</script>
	<body>
		<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
			<tr>
				<td class="list_r">
					&nbsp;
				</td>
				<td valign="top" class="right">
					<div class="title">
						您的当前位置： 首页 &gt;&gt; 数据统计 &gt;&gt;
						<strong>财务记录统计</strong>
					</div>
					<div>
						<s:form theme="simple" method="post" action="getExcelByChargesStat"
											id="getExcel" name="getExcel">
						<table width="100%" class="tableheader">
							<tr>
								<td width="5%" height="26">
									<img src="${pageContext.request.contextPath}/images/bg_mcontentL.gif" width="32" height="27" />
								</td>
								<td width="9%" valign="middle">
									<h3>
										检查状态表
									</h3>
								</td>
								<td width="37%" valign="middle">
									&nbsp;
								</td>
								<td width="48%" align="right" valign="middle">
									<input type="hidden" name="hidden_type" value="jc" />
									<input name="添加" type="submit" class="button_s"
										value="导出Excel表" />
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
											<tr>
												<th rowspan="2">
													机构<strong>/</strong>港口
												</th>
												<th colspan="2">
													<strong>未检查</strong>
												</th>
												<th colspan="2">
													<strong>已检查</strong>
												</th>
												<th rowspan="2">
													业务量总计
												</th>
												<th rowspan="2">
													金额总计
												</th>
											</tr>
											<tr>
												<th>
													业务量
												</th>
												<th>
													金 额
												</th>
												<th>
													业务量
												</th>
												<th>
													金 额
												</th>
											</tr>
											<s:if test="#request.jcList.size>0">
					    					<s:iterator value="#request.jcList" status="stat">
											<tr>
												<td align="center">
													${ORGNAME }<input type="hidden" name="hidden_body" value="${ORGNAME }" />
												</td>
												<td width="15%" align="center">
													<s:if test="NO_NUM==null">0<input type="hidden" name="hidden_body" value="0" /></s:if>
													<s:else>${NO_NUM }<input type="hidden" name="hidden_body" value="${NO_NUM }" /></s:else>
												</td>
												<td width="15%" align="center">
													<s:if test="NO_MONEY==null">0<input type="hidden" name="hidden_body" value="0" /></s:if>
													<s:else>${NO_MONEY }<input type="hidden" name="hidden_body" value="${NO_MONEY }" /></s:else>
												</td>
												<td width="15%" align="center">
													<s:if test="IS_NUM==null">0<input type="hidden" name="hidden_body" value="0" /></s:if>
													<s:else>${IS_NUM }<input type="hidden" name="hidden_body" value="${IS_NUM }" /></s:else>
												</td>
												<td width="15%" align="center">
													<s:if test="IS_MONEY==null">0<input type="hidden" name="hidden_body" value="0" /></s:if>
													<s:else>${IS_MONEY }<input type="hidden" name="hidden_body" value="${IS_MONEY }" /></s:else>
												</td>
												<td width="15%" align="center">
													${ALL_NUM }
													<input type="hidden" name="hidden_body" value="${ALL_NUM }" />
												</td>
												<td align="center">
													${ALL_MONEY }
													<input type="hidden" name="hidden_body" value="${ALL_MONEY }" />
												</td>
											</tr>
											</s:iterator>
											</s:if>
										</table>
									</div>
								</td>
							</tr>
						</table>
						</s:form>
						<br />
						<s:form theme="simple" method="post" action="getExcelByChargesStat"
											id="getExcel" name="getExcel">
						<table width="100%" class="tableheader">
							<tr>
								<td width="5%" height="26">
									<img src="${pageContext.request.contextPath}/images/bg_mcontentL.gif" width="32" height="27" />
								</td>
								<td width="9%" valign="middle">
									<h3>
										付费状态表
									</h3>
								</td>
								<td width="37%" valign="middle">
									&nbsp;
								</td>
								<td width="48%" align="right" valign="middle">
									<input type="hidden" name="hidden_type" value="ff" />
									<input name="添加2" type="submit" class="button_s"
										value="导出Excel表" />
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
											<tr>
												<th rowspan="2">
													机构<strong>/</strong>港口
												</th>
												<th colspan="2">
													<strong>未付费</strong>
												</th>
												<th colspan="2">
													<strong>已付费</strong>
												</th>
												<th rowspan="2">
													业务量总计
												</th>
												<th rowspan="2">
													金额总计
												</th>
											</tr>
											<tr>
												<th>
													业务量
												</th>
												<th>
													金 额
												</th>
												<th>
													业务量
												</th>
												<th>
													金 额
												</th>
											</tr>
											<s:if test="#request.ffList.size>0">
					    					<s:iterator value="#request.ffList" status="stat">
											<tr>
												<td align="center">
													${ORGNAME }<input type="hidden" name="hidden_body" value="${ORGNAME }" />
												</td>
												<td width="15%" align="center">
													<s:if test="NO_NUM==null">0<input type="hidden" name="hidden_body" value="0" /></s:if>
													<s:else>${NO_NUM }<input type="hidden" name="hidden_body" value="${NO_NUM }" /></s:else>
												</td>
												<td width="15%" align="center">
													<s:if test="NO_MONEY==null">0<input type="hidden" name="hidden_body" value="0" /></s:if>
													<s:else>${NO_MONEY }<input type="hidden" name="hidden_body" value="${NO_MONEY }" /></s:else>
												</td>
												<td width="15%" align="center">
													<s:if test="IS_NUM==null">0<input type="hidden" name="hidden_body" value="0" /></s:if>
													<s:else>${IS_NUM }<input type="hidden" name="hidden_body" value="${IS_NUM }" /></s:else>
												</td>
												<td width="15%" align="center">
													<s:if test="IS_MONEY==null">0<input type="hidden" name="hidden_body" value="0" /></s:if>
													<s:else>${IS_MONEY }<input type="hidden" name="hidden_body" value="${IS_MONEY }" /></s:else>
												</td>
												<td width="15%" align="center">
													${ALL_NUM }
													<input type="hidden" name="hidden_body" value="${ALL_NUM }" />
												</td>
												<td align="center">
													${ALL_MONEY }
													<input type="hidden" name="hidden_body" value="${ALL_MONEY }" />
												</td>
											</tr>
											</s:iterator>
											</s:if>
										</table>
									</div>
								</td>
							</tr>
						</table>
						</s:form>
						<br />
						<s:form theme="simple" method="post" action="getExcelByChargesStat"
											id="getExcel" name="getExcel">
						<table width="100%" class="tableheader">
							<tr>
								<td width="5%" height="26">
									<img src="${pageContext.request.contextPath}/images/bg_mcontentL.gif" width="32" height="27" />
								</td>
								<td width="9%" valign="middle">
									<h3>
										分帐状态表
									</h3>
								</td>
								<td width="37%" valign="middle">
									&nbsp;
								</td>
								<td width="48%" align="right" valign="middle">
									<input type="hidden" name="hidden_type" value="fz" />
									<input name="添加3" type="submit" class="button_s"
										value="导出Excel表" />
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
											<tr>
												<th rowspan="2">
													机构<strong>/</strong>港口
												</th>
												<th colspan="2">
													<strong>未分帐</strong>
												</th>
												<th colspan="2">
													<strong>已分帐</strong>
												</th>
												<th rowspan="2">
													业务量总计
												</th>
												<th rowspan="2">
													金额总计
												</th>
											</tr>
											<tr>
												<th>
													业务量
												</th>
												<th>
													金 额
												</th>
												<th>
													业务量
												</th>
												<th>
													金 额
												</th>
											</tr>
											<s:if test="#request.fzList.size>0">
					    					<s:iterator value="#request.fzList" status="stat">
											<tr>
												<td align="center">
													${ORGNAME }<input type="hidden" name="hidden_body" value="${ORGNAME }" />
												</td>
												<td width="15%" align="center">
													<s:if test="NO_NUM==null">0<input type="hidden" name="hidden_body" value="0" /></s:if>
													<s:else>${NO_NUM }<input type="hidden" name="hidden_body" value="${NO_NUM }" /></s:else>
												</td>
												<td width="15%" align="center">
													<s:if test="NO_MONEY==null">0<input type="hidden" name="hidden_body" value="0" /></s:if>
													<s:else>${NO_MONEY }<input type="hidden" name="hidden_body" value="${NO_MONEY }" /></s:else>
												</td>
												<td width="15%" align="center">
													<s:if test="IS_NUM==null">0<input type="hidden" name="hidden_body" value="0" /></s:if>
													<s:else>${IS_NUM }<input type="hidden" name="hidden_body" value="${IS_NUM }" /></s:else>
												</td>
												<td width="15%" align="center">
													<s:if test="IS_MONEY==null">0<input type="hidden" name="hidden_body" value="0" /></s:if>
													<s:else>${IS_MONEY }<input type="hidden" name="hidden_body" value="${IS_MONEY }" /></s:else>
												</td>
												<td width="15%" align="center">
													${ALL_NUM }
													<input type="hidden" name="hidden_body" value="${ALL_NUM }" />
												</td>
												<td align="center">
													${ALL_MONEY }
													<input type="hidden" name="hidden_body" value="${ALL_MONEY }" />
												</td>
											</tr>
											</s:iterator>
											</s:if>
										</table>
									</div>
								</td>
							</tr>
						</table>
						<br/>
						<center>
						<input name="" class="button" onclick="window.close();" style="cursor:pointer;" type="button" value="关 闭" />
						</center>
						</s:form>
					</div>
					<br/>
				</td>
			</tr>
		</table>
	</body>
</html>
