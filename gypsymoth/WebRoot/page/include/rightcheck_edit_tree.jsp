<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td>
			<div class="dchecktree">
				<script type="text/javascript">
d = new dTree('d','${pageContext.request.contextPath}/page/include/','editRoleForm');
d.add('0','-1','功能权限');
 <s:iterator value="#session.menu_parent">
    d.add('<s:property value="rightId"/>','<s:property value="parentid"/>','<s:property value="rightName"/>');
    </s:iterator>   
document.write(d);
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