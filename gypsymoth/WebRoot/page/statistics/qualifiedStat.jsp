<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统——业务量统计</title>
		<%@ include file="../include/head.jsp"%>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajax.js"></script>
		<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/jsFrame.js"></script>
		<style>
			html {
			overflow: scroll;
			overflow-x: hidden;
			overflow-x: auto !important;
			overflow-y: auto;
			}
		</style>
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
						<strong>业务量统计</strong>
					</div>
					<div>
					<s:form theme="simple" method="post" action="getExcelByBusinessStat"
											id="getExcel" name="getExcel">
						<table width="100%" class="tableheader">
							<tr>
								<td width="5%" height="26">
									<img src="${pageContext.request.contextPath}/images/bg_mcontentL.gif" width="32" height="27" align="left"/>
								</td>
								<td width="9%" valign="middle">
									<h3>
										统计合格率
									</h3>
								</td>
								<td width="37%" valign="middle">
									&nbsp;
								</td>
								<td width="48%" align="right" valign="middle">
									<input name="添加" type="submit" class="button_s"
										value="导出Excel表" />
								</td>
								<td width="1%" align="right">
									<img src="${pageContext.request.contextPath}/images/bg_mcontentR.gif" width="7" height="27" align="left"/>
								</td>
							</tr>
						</table>
						<%
							List tmpList = (List)request.getAttribute("numList");
							int allNum = tmpList.size();
						 %>
						<table width="100%" class="tableCont">
							<tr>
								<td>
									<div class="tableContent">
										<table width="100%" border="0" cellspacing="1" cellpadding="0">
											<tr>
												<th>
													机构名称
													<input type="hidden" name="hidden_top" value="机构名称" />
												</th>
												<s:if test="#request.numList.size>0">
					    						<s:iterator value="#request.numList" status="stat">
												<th>
													<s:property/>
													<input type="hidden" name="hidden_top" value="<s:property/>" />
												</th>
												</s:iterator>
												</s:if>
												<th>
													合计
													<input type="hidden" name="hidden_top" value="合计" />
												</th>
											</tr>
											<s:if test="#request.returnList.size>0">
					    					<s:iterator value="#request.returnList" status="stat">
											<tr>
												<td align="center">
													${ORG_NAME }
													<input type="hidden" name="hidden_body" value="${ORG_NAME }" />
												</td>
												<%
												for(int i=0;i<allNum;i++){
												 %>
												<td align="center">
													<%if(i==0){ %>${A0 }<input type="hidden" name="hidden_body" value="${A0 }%" /><%} %>
													<%if(i==1){ %>${A1 }<input type="hidden" name="hidden_body" value="${A1 }%" /><%} %>
													<%if(i==2){ %>${A2 }<input type="hidden" name="hidden_body" value="${A2 }%" /><%} %>
													<%if(i==3){ %>${A3 }<input type="hidden" name="hidden_body" value="${A3 }%" /><%} %>
													<%if(i==4){ %>${A4 }<input type="hidden" name="hidden_body" value="${A4 }%" /><%} %>
													<%if(i==5){ %>${A5 }<input type="hidden" name="hidden_body" value="${A5 }%" /><%} %>
													<%if(i==6){ %>${A6 }<input type="hidden" name="hidden_body" value="${A6 }%" /><%} %>
													<%if(i==7){ %>${A7 }<input type="hidden" name="hidden_body" value="${A7 }%" /><%} %>
													<%if(i==8){ %>${A8 }<input type="hidden" name="hidden_body" value="${A8 }%" /><%} %>
													<%if(i==9){ %>${A9 }<input type="hidden" name="hidden_body" value="${A9 }%" /><%} %>
													<%if(i==10){ %>${A10 }<input type="hidden" name="hidden_body" value="${A10 }%" /><%} %>
													<%if(i==11){ %>${A11 }<input type="hidden" name="hidden_body" value="${A11 }%" /><%} %>
													<%if(i==12){ %>${A12 }<input type="hidden" name="hidden_body" value="${A12 }%" /><%} %>
													%
												</td>
												<%} %>
												<td align="center">
													${ALL_NUM }%
													<input type="hidden" name="hidden_body" value="${ALL_NUM }%" />
												</td>
											</tr>
											</s:iterator>
											</s:if>
											<s:if test="#request.returnAllNum.size>0">
											<tr>
												<td align="center">
													合计
													<input type="hidden" name="hidden_bottom" value="合计" />
												</td>
					    						<s:iterator value="#request.returnAllNum" status="stat">
												<%
												for(int i=0;i<allNum;i++){
												 %>
												<td align="center">
													<%if(i==0){ %>${A0 }<input type="hidden" name="hidden_bottom" value="${A0 }%" /><%} %>
													<%if(i==1){ %>${A1 }<input type="hidden" name="hidden_bottom" value="${A1 }%" /><%} %>
													<%if(i==2){ %>${A2 }<input type="hidden" name="hidden_bottom" value="${A2 }%" /><%} %>
													<%if(i==3){ %>${A3 }<input type="hidden" name="hidden_bottom" value="${A3 }%" /><%} %>
													<%if(i==4){ %>${A4 }<input type="hidden" name="hidden_bottom" value="${A4 }%" /><%} %>
													<%if(i==5){ %>${A5 }<input type="hidden" name="hidden_bottom" value="${A5 }%" /><%} %>
													<%if(i==6){ %>${A6 }<input type="hidden" name="hidden_bottom" value="${A6 }%" /><%} %>
													<%if(i==7){ %>${A7 }<input type="hidden" name="hidden_bottom" value="${A7 }%" /><%} %>
													<%if(i==8){ %>${A8 }<input type="hidden" name="hidden_bottom" value="${A8 }%" /><%} %>
													<%if(i==9){ %>${A9 }<input type="hidden" name="hidden_bottom" value="${A9 }%" /><%} %>
													<%if(i==10){ %>${A10 }<input type="hidden" name="hidden_bottom" value="${A10 }%" /><%} %>
													<%if(i==11){ %>${A11 }<input type="hidden" name="hidden_bottom" value="${A11 }%" /><%} %>
													<%if(i==12){ %>${A12 }<input type="hidden" name="hidden_bottom" value="${A12 }%" /><%} %>
													%
												</td>
												<%} %>
												<td align="center">
													${ALL_NUM }%
													<input type="hidden" name="hidden_bottom" value="${ALL_NUM }%" />
												</td>
												</s:iterator>
											</tr>
											</s:if>
										</table>
									</div>
								</td>
							</tr>
						</table>
						</s:form>
					</div>
					<br/>
					<img src="${pageContext.request.contextPath}/DisplayChart?filename=${request.filename }" alt="统计图" />
					<br/>
					<img src="${pageContext.request.contextPath}/DisplayChart?filename=${request.filename2 }" alt="统计图" />
					<br/>
				</td>
			</tr>
		</table>
		<br/>
						<center>
						<input name="" class="button" onclick="window.close();" style="cursor:pointer;" type="button" value="关 闭" />
						</center>
	</body>
</html>
