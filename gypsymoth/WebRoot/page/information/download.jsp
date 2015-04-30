
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统</title>
		<%@ include file="../include/head.jsp"%>
	</head>


	<script type="text/javascript">
	 $("document").ready(function(){
	 	 
			 
      });
      
       function downlaod(id,index){
		var url = "downloadFile.action?id="+id+"&index="+index;
		window.open(url,"_blank","top=200px,left=300px,location=no,titlebar=no,toolbar=no,menubar=no,resizable=yes,width=400px,height=200px,scrollbars=yes"); 
		
     }	
     
  </script>

	<body>
		<br/><br/>
		<s:form action="downloadFile.action" name="form1" id="form1"
											method="post">
											<input type="hidden" name="informationid" id="informationid"
												value="<s:property value="informationid"/>" />
											<input type="hidden" name="fileurl" id="fileurl"
												value="<s:property value="information.fileurl"/>" />
											<input type="hidden" name="filename" id="filename"
												value="<s:property value="information.filename"/>" />
		<!--编辑开始-->
		<table width="90%" align="center" cellspacing="5">
				<tr>
					<td width="68" height="20">
						标题
					</td>
					<td width="332">
						<s:property value="information.informationname" />
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td height="20">
						下载文件
					</td>
					<td>
				       <s:iterator value="#request.filelist" id="mop" status="stat">
					     <a href="javascript:downlaod('<s:property value="#mop.id"/>','<s:property value="#mop.fileindex" />');" >
							<span class="mainred"> <s:property value="#mop.name" />
							</span>
						 </a>&nbsp;&nbsp;&nbsp;&nbsp;
						 <s:if test="#stat.index!=0&&stat.index%5==0"> 
						 	<br /> 
						 </s:if>
				       </s:iterator>
						
					</td>
					<td>
						&nbsp;
					</td>
				</tr>

				<tr>
					<td height="20">
						发布时间
					</td>
					<td width="332">
						<s:date name="information.fileuptime"
							format="yyyy-MM-dd HH:mm" />
					</td>
					<td>
						&nbsp;
					</td>
				</tr>

				<tr>
					<td height="20">
						发布人
					</td>
					<td width="332">
						<s:property value="information.accountname" />
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
 
				<tr>
					<td height="20">
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td height="38" align="left"></td>
					<td height="38" align="left">
						 
					</td>
					<td align="left">
						&nbsp;
					</td>
				</tr>
		</table>
		<!--编辑结束-->
		</s:form>
	</body>
</html>
