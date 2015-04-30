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
	window.location="${pageContext.request.contextPath}/page/examine/getAllExamineByPage.action";
}
setTimeout ("jump()",3000 )
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
          <div class="errtext">上传检查结果成功!<br />
            您的检查记录已经成功上传,您将在业务查询功能查看到上传的检查结果记录!<!--,点击 [<a href="#"  onclick="history.back(-1)"><span style="color:#FF0000">返 回</span></a>] 上一页面 --></div>
        </div>
      </div></td>
  </tr>
</table>
</body>
</html>