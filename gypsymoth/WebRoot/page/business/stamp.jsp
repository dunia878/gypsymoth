<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
 	isELIgnored="false"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统</title>
		<%@ include file="../include/head.jsp"%>
	</head>
	<body>
	
<script language="text/javascript" type="text/javascript"> 
	$(document).ready(function(){
		 
		
	});
	
	
	function deletestamp(id)
	{
		var con = confirm("是否删除?");
		if(con){
			 document.getElementById("stampid").value=id;
			 document.getElementById("stampform").action ="deletestamp.action";
		 	 document.getElementById("stampform").submit();
		}
	}
	
	 
 
</script>	
 
 <form action="savestamp.action" method="post" name="stampform" id="stampform">
 <!--内容开始-->
<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
  <tr>
    <td class="list_r">&nbsp;</td>
    <td valign="top" class="right"><div class="title">您的当前位置： 首页 &gt;&gt; 业务管理 &gt;&gt;证书管理  &gt;&gt;<strong>印章管理</strong></div>
      <div>
        <table align="center" cellpadding="12" class="tabedit">
          <tr>
            <td height="300" align="left" valign="top"><!--编辑开始-->
              <table width="706" align="center" cellspacing="5">
                <tr>
                  <td width="180" height="20"></td>
                  <td width="332"> <font style="color: red;">${message }</font> </td>
                  <td width="272">&nbsp;</td>
                </tr>
                <tr>
                  <td width="180" height="20">所属机构</td>
                  <td width="332" >印章号</td>
                  <td width="272">&nbsp;</td>
                </tr>
                
                <s:iterator value="#request.stamplist" id="mop">
	                <tr>
	                  <td height="20">&nbsp;<s:property value="#mop.ORG_SNAME"/> </td>
	                  <td>&nbsp;<input type="text" id="stampid_input" maxlength="5" name="stampid_input" value="<s:property value="#mop.STAMPID"/>" /> 
                	  <input type="button" id="submit_button" onclick="return deletestamp('<s:property value="#mop.STAMPID"/>');" class="button" value="删 除" />
                  
	                  </td>
	                  <td>&nbsp;</td>
	                </tr>
                </s:iterator>
                
                <tr>
                  <td height="38" align="left"></td>
                  <td height="38" align="left">
                  <input type="hidden" name="orgid" id="orgid" value="${orgid }" />
                  <input type="hidden" name="stampid" id="stampid" />
                     <input  name="input" class="button" type="button" value="返 回"  onclick="location.href='certificate.action?tagtype=2'" />
                      </td>
                  <td align="left">&nbsp;</td>
                </tr>
              </table>
              <!--编辑结束-->
            </td>
          </tr>
        </table>
      </div></td>
  </tr>
</table>
</form>

	</body>
</html>
