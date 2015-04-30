<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
		<title>中检集团船舶舞毒蛾检查业务信息系统</title>
		<link href="${pageContext.request.contextPath}/style/left.css" type="text/css" rel="stylesheet" />
	</head>

	<body>
    <TABLE width="187" height="100%" border="0" align="center" cellPadding="0 " cellSpacing="0">
			<TR>
				<TD width="100%" height="1" vAlign="top">
					<TABLE cellSpacing="0" cellPadding="0 " width="100%" border="0">
						<TBODY>
							<TR>
								<s:iterator value="#session.menu_parent" status="stat">
									<s:if test="rightId == #session.right_id">
										<TD height="42" align="left" class="left_tit">
											<strong><s:text name="%{rightName}"/></strong>
										</TD>
									</s:if>
									<s:if test="#session.right_id == null || #session.right_id == ''">
										<s:if test="rightId == 4">
										<TD height="42" align="left" class="left_tit">
											<s:if test="#session.WW_TRANS_I18N_LOCALE!=null && #session.WW_TRANS_I18N_LOCALE.toString()=='en_US'">
												<strong>inspection management</strong>
											</s:if>
											<s:else>
												<strong>业务管理</strong>
											</s:else>
										</TD>
										</s:if>
									</s:if>
								</s:iterator>
							</TR>
						</TBODY>
					</TABLE>
				</TD>
			</TR>
			
			<tr>
			    <td valign="top">
				    <table width="100%" border="0" cellspacing="0" cellpadding="0">
					<s:iterator value="#session.menu_parent" status="stat">
						<s:if test="parentid == #session.right_id">
							<tr>
								<td class="left_menu">
									<a href="${pageContext.request.contextPath}${resourceUrl }" target="rightfranme">
										<s:text name="%{rightName}"/>
									</a>
								</td>
							</tr>
						</s:if>
						<s:if test="#session.right_id == null || #session.right_id == ''">
							<s:if test="parentid == 4">
							<tr>
								<td class="left_menu">
									<s:if test="#session.WW_TRANS_I18N_LOCALE!=null && #session.WW_TRANS_I18N_LOCALE.toString()=='en_US'">
										<s:if test="rightId != 41 && rightId != 47&& rightId != 40">
											<a href="${pageContext.request.contextPath}${resourceUrl }" target="rightfranme">
												<s:text name="%{rightName}"/>
											</a>
										</s:if>
										<s:elseif test="rightId == 40">
											<a href="${pageContext.request.contextPath}${resourceUrl }" target="rightfranme">
												Inspection awoke
											</a>
										</s:elseif>
										<s:elseif test="rightId == 41">
											<a href="${pageContext.request.contextPath}${resourceUrl }" target="rightfranme">
												Inspection apply
											</a>
										</s:elseif>
										<s:elseif test="rightId == 47">
											<a href="${pageContext.request.contextPath}${resourceUrl }" target="rightfranme">
												Inspection inquiry
											</a>
										</s:elseif>
									</s:if>
									<s:else>
										<a href="${pageContext.request.contextPath}${resourceUrl }" target="rightfranme">
											<s:text name="%{rightName}"/>
										</a>
									</s:else>
								</td>
							</tr>
							</s:if>
						</s:if>
					</s:iterator>
			      </table>
				</td>
			</tr>
	</table>
  </body>
</html>
