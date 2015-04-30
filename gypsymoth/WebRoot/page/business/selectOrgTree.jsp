<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
 	isELIgnored="false"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统</title>
		<%@ include file="../include/head.jsp"%> 
		<link rel="StyleSheet" href="<%=basePath %>style/dtree.css" type="text/css" />
		<script type="text/javascript" src="<%=basePath %>js/dtree.js"></script> 
		<script type="text/javascript">
			function coor_click(name,id){
				window.opener.document.getElementById("selectOrgid").value=id;
				window.opener.document.getElementById("selectOrgname").value=name;
				window.opener.document.getElementById("selectOrgname2").value=name;
				window.close();
			}
		</script>
	</head>
	<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td align="left">
				<div class="dtree">
	
					<p>
						<a href="javascript: selectorg.openAll();">全部展开</a> |
						<a href="javascript: selectorg.closeAll();">全部关闭</a>
					</p>
					<script type="text/javascript">
						<!--
				
						selectorg = new dTree('selectorg');
				
						selectorg.add(0,-1,'选择机构');
						<s:if test="#session.person_org==1">
							selectorg.add(1,0,'检验公司',"javascript:coor_click('检验公司','');");
						</s:if>
						<s:else>
							selectorg.add(1,0,'检验公司');
						</s:else>
						<s:if test="#request.selectOrgList.size>0">
				    	<s:iterator value="#request.selectOrgList" status="stat">
				    		<s:if test="DISABLED==0">
								selectorg.add('${ID}','${PARENT_ID}','${ORG_SNAME}',"javascript:coor_click('${ORG_SNAME}','${ID}');");
							</s:if>
							<s:else>
								selectorg.add('${ID}','${PARENT_ID}','${ORG_SNAME}');
							</s:else>
				    	</s:iterator>
				    	</s:if>
						
						document.write(selectorg);
				
						//-->
					</script>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				&nbsp;
			</td>
		</tr>
	</table>
	</body>
</html>
