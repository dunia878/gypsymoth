<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>激活成功</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/index.css" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/right.css" type="text/css" />
</head>
<body>
<!--内容开始-->
<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
  <tr>
    <td class="list_r">&nbsp;</td>
    <td valign="top" class="right"><div class="title"></div>
      <div>
        <div class="success">
          <div class="errtext"><s:text name="actsuccess1"/><br />
            <s:text name="actsuccess2"/> [<a href="${pageContext.request.contextPath}/index.jsp"><span style="color:#FF0000"><s:text name="return"/></span></a>] <s:text name="主页"/>,<s:text name="actsuccess3"/></div>
        </div>
      </div></td>
  </tr>
</table>
</body>
</html>
