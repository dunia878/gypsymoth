<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>中检集团船舶舞毒蛾检查业务信息系统——权限管理</title>
<%@ include file="../include/head.jsp"%>
</head>
<body>
<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
  <tr>
    <td class="list_r">&nbsp;</td>
    <td valign="top" class="right"><div class="title">您的当前位置： 首页 &gt;&gt; 系统管理 &gt;&gt; <strong>权限管理</strong></div>
      <div>
        <table width="100%" align="center" cellpadding="0" cellspacing="5">
          <tr>
            <td align="left" valign="top"><table width="100%" class="tableheader">
                <tr>
                  <td width="5%" height="26"><img src="${pageContext.request.contextPath}/images/bg_mcontentL.gif"  width="32" height="27" /></td>
                  <td valign="middle"><h3>权限管理</h3></td>
                  <td align="right" valign="middle"><!--<input type="submit" class="button_s" value="删 除" />--></td>
                  <td width="1%" align="right"><img src="${pageContext.request.contextPath}/images/bg_mcontentR.gif"  width="7" height="27" /></td>
                </tr>
              </table>
              <table width="100%" class="tableCont">
                <tr>
                  <td valign="top">
                  	  <table width="100%" border="0" cellpadding="5" cellspacing="1" bgcolor="#a8c6e7">
          				<tr >
                          <th width="29%" align="center" style="border:1px solid #fff;">一级功能名称</th>
                          <th width="19%" align="center" style="border:1px solid #fff;">二级功能名称</th>
                          <th width="52%" align="center" style="border:1px solid #fff;">操　作</th>
                        </tr>
                  	  </table>
                  	  
                      <table width="100%" border="0" cellpadding="5" cellspacing="1" bgcolor="#a8c6e7">
                        <s:if test="#request.rightList.size>0">
                          <s:iterator value="#request.rightList" status="stat" id="rightList1">
                            <s:if test="#rightList1.ischild == 0">
                              <tr>
                                <th colspan="3" align="left" bgcolor="#eaf4ff" style="border:1px solid #fff;color:#0033cc;"><div align="left"> <strong>
                                    <s:property value="rightName"/>
                                    </strong></div></th>
                              </tr>
                              <s:iterator value="#request.rightList" status="stat" id="rightList2">
                                <s:if test="#rightList2.parentid == #rightList1.rightId">
                                  <tr>
                                    <td width="29%" align="right" bgcolor="#FFFFFF">&nbsp;</td>
                                    <td width="19%" align="left" bgcolor="#FFFFFF"><strong>
                                      <s:property value="rightName"/>
                                      </strong></td>
                                    <td width="52%" align="left" bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                                        <tr>
                                          <td align="left"><s:iterator value="#request.rightList" status="stat" id="rightList3">
                                              <s:if test="#rightList3.parentid == #rightList2.rightId">【<s:property value="rightName"/>】</s:if>
                                            </s:iterator></td>
                                        </tr>
                                      </table></td>
                                  </tr>
                                </s:if>
                              </s:iterator>
                            </s:if>
                          </s:iterator>
                        </s:if>
                      </table>
                    </td>
                </tr>
              </table></td>
          </tr>
        </table>
      </div></td>
  </tr>
</table>
</body>
</html>
