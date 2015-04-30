<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>成功</title>
<link rel=stylesheet href="../../style/index.css" type="text/css" />
<link rel=stylesheet href="../../style/right.css" type="text/css" />
<script type="text/javascript">
function jump(){
	window.location="${pageContext.request.contextPath}/page/organization/getAllOrganizationByPage.action?goPage=<%=session.getAttribute("currtPage") %>";
}
</script>  
</head>
<body>
<!--内容开始-->
<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
  <tr>
    <td class="list_r">&nbsp;</td>
    <td valign="top" class="right"><div class="title"></div>
      <div>
        <div class="success">
          <div class="errtext">操作成功!<br />
            您的操作已经成功,点击 [<a href="#"  onclick="jump();"><span style="color:#FF0000">确定</span></a>] 下一页面 </div>
        </div>
      </div></td>
  </tr>
</table>
</body>
</html>