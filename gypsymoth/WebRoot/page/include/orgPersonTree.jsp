<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td align="left">
			<div class="dtree">

				<p>
					<a href="javascript: org.openAll();">全部展开</a> |
					<a href="javascript: org.closeAll();">全部关闭</a>
				</p>
				<script type="text/javascript">
					<!--
			
					org = new dTree('org');
			
					org.add(0,-1,'机构人员');
					<s:if test="#request.orgPersonList.size>0">
			    	<s:iterator value="#request.orgPersonList" status="stat">
			    		<s:if test="TYPEID =='1'">
						org.add(${ID},${PARENTID},'${NAME}','','','','img/folder.gif');
						</s:if>
						<s:if test="TYPEID =='2'">
						org.add(${ID},${PARENTID},'${NAME}',"javascript:show('${NAME}','${NOID}','${ISC}','${ISA}','${ISI}')");
						</s:if>
			    	</s:iterator>
			    	</s:if>
					
					document.write(org);
			
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
