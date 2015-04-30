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
					<s:form theme="simple" method="post" action="getExcelByLedgerStatApp"
											id="getExcel" name="getExcel">
						<table width="100%" class="tableheader">
							<tr>
								<td width="5%" height="26">
									<img src="${pageContext.request.contextPath}/images/bg_mcontentL.gif" width="32" height="27" />
								</td>
								<td width="9%" valign="middle">
									<h3>
										分帐金额表
									</h3>
								</td>
								<td width="37%" valign="middle">
									&nbsp;
								</td>
								<td width="48%" align="right" valign="middle">
									单位（元）
									<input name="添加" type="submit" class="button_s"
										value="导出Excel表" />
								</td>
								<td width="1%" align="right">
									<img src="${pageContext.request.contextPath}/images/bg_mcontentR.gif" width="7" height="27" />
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
													业务单号
												</th>
												<th rowspan="2">
													到账金额
												</th>
												<th colspan="2">
													检验公司
												</th>
												<th colspan="2">
													一级公司
												</th>
												<th colspan="2">
													二级公司
												</th>
												<th rowspan="2">
													总 计
												</th>
											</tr>
											<tr>
												<th>
													名称
												</th>
												<th>
													金额
												</th>
												<th>
													名称
												</th>
												<th>
													金额
												</th>
												<th>
													名称
												</th>
												<th>
													金额
												</th>
											</tr>
											<s:if test="#request.returnList.size>0">
					    					<s:iterator value="#request.returnList" status="stat">
											<tr>
												<td align="center">
													${APPNO }
													<input type="hidden" name="hidden_body" value="${APPNO }" />
												</td>
												<td align="center">
													${TOTAL }
													<input type="hidden" name="hidden_body" value="${TOTAL }" />
												</td>
												<td align="center">
													${ONENAME }
													<input type="hidden" name="hidden_body" value="${ONENAME }" />
												</td>
												<td align="center">
													${FIRSTCOMPAY }
													<input type="hidden" name="hidden_body" value="${FIRSTCOMPAY }" />
												</td>
												<td align="center">
													${TWONAME }
													<input type="hidden" name="hidden_body" value="${TWONAME }" />
												</td>
												<td align="center">
													${SECONDCOMPAY }
													<input type="hidden" name="hidden_body" value="${SECONDCOMPAY }" />
												</td>
												<td align="center">
													${THREENAME }
													<input type="hidden" name="hidden_body" value="${THREENAME }" />
												</td>
												<td align="center">
													${THIRDCOMPAY }
													<input type="hidden" name="hidden_body" value="${THIRDCOMPAY }" />
												</td>
												<td align="center">
													${TOTAL }
													<input type="hidden" name="hidden_body" value="${TOTAL }" />
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
