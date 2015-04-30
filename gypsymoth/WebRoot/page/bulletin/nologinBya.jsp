<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统</title>
		<%@ include file="../include/head.jsp"%>
	</head>


	<!-- JS验证表单部分 -->
	<script type="text/javascript">
   function gotoSubmit()
    {
    		this.document.forms[0].submit();
     }
  </script>


	<body>

		<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
			<tr>
				<td class="list_r">
					&nbsp;
				</td>
				<td valign="top" class="right">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="title" valign="top">
							<tr>
								<td>
									<img src="../../images/icon_pass.gif" width="22" height="17"
										style="margin-bottom: -3px;" />
									<span class="news">新闻动态News</span>
								</td>
							</tr>
						</table>
						<table width="100%" border="0" cellspacing="5" cellpadding="12">
						
							
							<s:form id="bulletinlist" name="bulletinlist" action="getAllDataByPage"
								theme="simple">
								<s:if test="#request.bulletinlist.size>0">
									<s:iterator value="#request.bulletinlist" status="stat">
										<tr>
											
											<td height="25" align="left"  style="border-bottom:dotted 1px #8EC0E8;" ><img src="../../images/menu_icon.gif" width="11" height="9" />
												<a
													href="<s:url action="getBulletinByIdPerson"> 
			                            <s:param name="bulletinId"><s:property value="bulletinId"/></s:param>
			                            </s:url>">
													<s:property value="bulletinName" /> </a> </td>
												<td height="25" align="center"  style="border-bottom:dotted 1px #8EC0E8;" >【<s:date name="bulletinTime" format="yyyy-MM-dd " />】</td>
											
										</tr>
										</s:iterator>
								</s:if>
							</s:form>
                             <tr>
                             
					<td height="25" align="right" class="right_tb1" colspan="3">
						<!--分页  -->
						<s:component template="pagev2.jsp" templateDir="page/pageutil"
							theme="template">
							<!--action name  -->
							<s:param name="formId" value="'bulletinlist'" />
							<s:param name="action_name" value="actionName" />
							<!-- name space -->
							<s:param name="namespace" value="'/page/bulletin'" />
							<!-- pagination method name -->
							<s:param name="pagination_method_name" value="actionName" />
							<!--当前页前后显示的数量  -->
							<s:param name="distanceNum" value="5" />
						</s:component>
					</td>
				</tr>

						</table>
				</td>
				<!-- 主内容结束 -->

			</tr>
		</table>

	</body>
</html>
