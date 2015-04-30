<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统</title>
	</head>
	<script>
	var flag = 'max';
	function setmax() {

		if (flag == 'max') {
			//alert(123);
			//parent.topframe1.rows="0,26,*";
			var tt = parent.document.getElementById("right_td");
			tt.style.display = "none";
			document.photo.src = "${pageContext.request.contextPath}/images/arrow_r.gif";
			document.photo.alt = "点击展开";
			flag = 'min';
		} else {
			var tt = parent.document.getElementById("right_td");
			// alert(tt.innerHTML)
			tt.style.display = "";
			document.photo.src = "${pageContext.request.contextPath}/images/arrow_l.gif";
			document.photo.alt = "点击隐藏";
			flag = 'max';
		}

	}
	</script>
	<body class="fontbody1"
		background="${pageContext.request.contextPath}/images/bg_blue.gif"
		 >

		<table width="10" height="240" border="0" align="left" cellpadding="0" cellspacing="0">
			<tr>

				<td height="200" valign="top"></td>
			</tr>
			<tr>
				<td height="30" valign="middle"></td>
			</tr>
			<tr>
				<td height="10" valign="bottom">&nbsp;
					
				</td>
			</tr>
		</table>
	</body>
</html>
