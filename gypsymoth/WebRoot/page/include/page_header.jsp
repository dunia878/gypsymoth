<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<div class="logo" id="top">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="688" class="logo_l">&nbsp;</td>
    <td>&nbsp;</td>
    <td width="360" class="logo_r">
        <table width="460" border="0" align="right" cellpadding="0" cellspacing="0">
		    <tr>
		      <td width="22"><img src="${pageContext.request.contextPath}/images/top_1.gif" width="22" height="22" /></td>
		      <td align="center" background="${pageContext.request.contextPath}/images/top_2.gif">
		      	<s:text name="currentUser"/>: ${account.accountName}<s:if test="#session.org_name!=null && #session.org_name!=''"> | <s:text name="所属机构"/>： <s:property value="#session.org_name"/></s:if> | 
		      <a href="help.htm" target="_blank"><span><s:text name="help"/></span></a> | <a href="${pageContext.request.contextPath}/logout.action" target="_parent"><span><s:text name="logout"/></span></a></td>
		    </tr>
		    <tr>
		      <td>&nbsp;</td>
		      <td height="104" align="right" valign="bottom"><s:if test="#session.account.accountType==2"><img src="${pageContext.request.contextPath}/images/logo_bt1.jpg" width="228" height="32" border="0" usemap="#Map" /></s:if><s:else>&nbsp;</s:else></td>
		    </tr>
  		</table>
    </td>
  </tr>
</table>
</div>
<map name="Map" id="Map">
  <area shape="rect" coords="19,8,102,30" href="${pageContext.request.contextPath}/language.action?request_locale=zh_CN" target="_top" alt="中文" />
  <area shape="rect" coords="113,8,190,31" href="${pageContext.request.contextPath}/language.action?request_locale=en_US" target="_top" alt="英文" />
</map>