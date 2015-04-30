<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>查看业务详情</title>
<link rel=stylesheet href="${pageContext.request.contextPath}/style/index.css" type="text/css" />
<link rel=stylesheet href="${pageContext.request.contextPath}/style/right.css" type="text/css" />
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script language="JavaScript" type="text/javascript">
function validata()
{
	
	var company = document.getElementById("company").value;
	var vesselname = document.getElementById("vesselname").value;
	var vesseltype = document.getElementById("vesseltype").value;
	var registry = document.getElementById("registry").value;
	var imo = document.getElementById("imo").value;
	var tonnage = document.getElementById("tonnage").value; 
	var plandatein = document.getElementById("plandatein").value;
	var plandateout = document.getElementById("plandateout").value;
	var linkmanname = document.getElementById("linkmanname").value;
 	var tel = document.getElementById("tel").value;
	var fax = document.getElementById("fax").value; 
	var email = document.getElementById("email").value;
	var certid = document.getElementById("certid").value;
	var reg_tonnage = /^(\+|-)?\d+$/;
	var reg_num = /^[\d]+$/;
	var reg_businessname = /^[a-zA-Z0-9][^\u4E00-\u9FA5]+$/;
	var reg_eng =  /^[a-zA-Z0-9]*[^\u4E00-\u9FA5]+$/;
	var reg_linkphone = /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/;
	var reg_post = /^[0-9]{6}$/;	
	if(company==null||company==""){
		alert("<s:text name='船东或租船人名称'/> <s:text name='cannot_empty_error1'/>");
		document.getElementById("company").focus();
		return false;
	}
	if(!reg_eng.test(company)){ 
		alert("<s:text name='船东或租船人名称'/> <s:text name='onlyenglish'/>"); 
		document.getElementById("company").focus();
		return false;
	}
	if(vesselname==null||vesselname==""){
		alert("<s:text name='船名'/> <s:text name='cannot_empty_error1'/>");
		document.getElementById("vesselname").focus();
		return false;
	}
	if(!reg_eng.test(vesselname)){ 
		alert("<s:text name='船名'/> <s:text name='onlyenglish'/>"); 
		document.getElementById("vesselname").focus();
		return false;
	}
	if(vesseltype==null||vesseltype==""||vesseltype=="-1"){
		alert("<s:text name='船舶类型'/> <s:text name='cannot_empty_error1'/>");
		document.getElementById("vesseltype").focus();
		return false;
	}
	if(registry==null||registry==""||registry=="-1"){
		alert("<s:text name='船籍'/> <s:text name='cannot_empty_error1'/>");
		document.getElementById("registry").focus();
		return false;
	}
	if(imo==null||imo==""){
		alert("<s:text name='IMO号'/> <s:text name='cannot_empty_error1'/>");
		document.getElementById("imo").focus();
		return false;
	}
	if(!reg_num.test(imo)){ 
		alert("<s:text name='IMO号'/> <s:text name='onlynumber' />"); 
		document.getElementById("imo").focus();
		return false;
	}
	if(tonnage==null||tonnage==""){
		alert("<s:text name='总吨位'/> <s:text name='cannot_empty_error1'/>");
		document.getElementById("tonnage").focus();
		return false;
	}
	if(!reg_num.test(tonnage)){
		alert("<s:text name='总吨位'/> <s:text name='onlynumber' />");
		document.getElementById("tonnage").focus();
		return false;
	}	
	if(plandatein==null||plandatein==""){
		alert("<s:text name='计划入港日期'/> <s:text name='cannot_empty_error1'/>");
		document.getElementById("plandatein").focus();
		return false;
	}
	if(plandateout==null||plandateout==""){
		alert("<s:text name='计划离港日期'/> <s:text name='cannot_empty_error1'/>");
		document.getElementById("plandateout").focus(); 
		return false;
	}
	if(Date.parse(plandatein.replace('-','/'))>Date.parse(plandateout.replace('-','/'))){
		$("plandateout").val("");
		alert("<s:text name='计划离港日期'/> <s:text name='必须晚于'/> <s:text name='计划入港日期'/>");
		document.getElementById("plandateout").focus();
		return false; 
	}
	if(linkmanname==null||linkmanname==""){
		alert("<s:text name='联系人姓名'/> <s:text name='cannot_empty_error1'/>");
		document.getElementById("linkmanname").focus();
		return false;
	}	
	if(tel==null||tel==""){
			alert("<s:text name='联系人电话'/> <s:text name='cannot_empty_error1'/>");
			document.getElementById("tel").focus();
			return false;
	} 
	if(certid==null||certid==""){
			alert("<s:text name='certificateRadomNum'/> <s:text name='cannot_empty_error1'/>");
			document.getElementById("certid").focus();
			return false;
	}
	if(!confirm("您确认保存?")){
		return false;
	}
	return true;  

}
function jump(){
	window.location="${pageContext.request.contextPath}/page/examine/getAllExamineByPage.action";
}
</script>
</head>
<body>
<!--内容开始-->
<s:form id="examine" name="examine" action="update" theme="simple">
<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
  <tr>
    <td class="list_r">&nbsp;</td>
    <td valign="top" class="right"><div class="title"><s:text name="local"/>： <s:text name="home"/> &gt;&gt; <s:text name="bussinessManage"/> &gt;&gt; <strong><s:text name="applicat"/></strong></div>
      <div>
        <table align="center" cellpadding="12" class="tabedit">
          <tr>
            <td height="300" align="left" valign="top"><!--编辑开始-->
              <table width="706" align="center" cellspacing="5">
                <tr>
                  <td width="180" height="20"><s:text name="applicat"/></td>
                  <td width="332"><label>
                    <input disabled="true" name="businessname" id="businessname" type="text" class="input" value="<s:property value="#request.list.BUSINESSNAME"/>" size="55" />
                  </label></td>
                  <td width="272">&nbsp;</td>
                </tr>
                <tr>
                  <td><s:text name="boatowner"/></td>
                  <td><label>
                    <input  name="company" id="company" type="text" class="input" value="<s:property value="#request.list.COMPANY"/>" size="55" />
                  </label></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="20"><s:text name="boatname"/></td>
                  <td><input  name="vesselname" id="vesselname" type="text" class="input" value="<s:property value="#request.list.VESSELNAME"/>" size="55" /></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="20"><s:text name="type"/></td>
                  <td><input  name="vesseltype" id="vesseltype" type="text" class="input" value="<s:property value="#request.list.VESSELTYPE"/>" size="55" /></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="20"><s:text name="shipper"/></td>
                  <td><input  name="registry" id="registry" type="text" class="input" value="<s:property value="#request.list.REGISTRY"/>" size="55" /></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="20">IMO<s:text name="code"/></td>
                  <td><input  name="imo" id="imo" type="text" class="input" value="<s:property value="#request.list.IMO"/>" size="55" /></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="20"><s:text name="totalton"/></td>
                  <td><input  name="tonnage" id="tonnage" type="text" class="input" value="<s:property value="#request.list.TONNAGE"/>" size="55" />MT</td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="20"><s:text name="planinportdate"/></td>
                  <td><input  name="plandatein" id="plandatein" type="text" class="input" value="<s:property value="#request.list.PLANDATEIN"/>" size="55" onclick="WdatePicker()"/></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="20"><s:text name="planoutportdate"/></td>
                  <td><input  name="plandateout" id="plandateout" type="text" class="input" value="<s:property value="#request.list.PLANDATEOUT"/>" size="55" onclick="WdatePicker()"/></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="20"><s:text name="berth"/></td>
                  <td><input  name="berth" id="berth" type="text" class="input" value="<s:property value="#request.list.BERTH"/>" size="55" /></td>
                  <td>&nbsp;</td>
                </tr>
                 <tr>
                  <td height="20"><s:text name="applicat"/><s:text name="name"/></td>
                  <td><input  name="linkmanname" id="linkmanname" type="text" class="input" value="<s:property value="#request.list.LINKMANNAME"/>" size="55" /></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="20"><s:text name="applicat"/><s:text name="contacttel"/></td>
                  <td><input  name="tel" id="tel" type="text" class="input" value="<s:property value="#request.list.PHONE"/>" size="55" /></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="20"><s:text name="applicat"/><s:text name="fax"/></td>
                  <td><input  name="fax" id="fax" type="text" class="input" size="55" value="<s:property value="#request.list.FAX"/>"/></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="20"><s:text name="applicat"/><s:text name="email"/></td>
                  <td><input  name="email" id="email" type="text" class="input" value="<s:property value="#request.list.EMAIL"/>" size="55" /></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="20"><s:text name="examineorganization"/></td>
                  <td><input name="certification" id="certification" type="text" class="input" disabled="true" value="CCIC INSPECTION CO.,LTD" size="55" /></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="20"><s:text name="certificateRadomNum"/></td>
                  <td><input name="certid" id="certid" type="text" class="input"  size="55" />*</td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="38" align="left"><input name="Id" type="hidden" class="input"  value="<s:property value="#request.list.ID"/>" size="55" /></td>
                  <td height="38" align="left"> 
                  	  <input  name="input" class="button" type="submit" value="<s:text name="savebutton"/>"   onclick="return validata();"/>                   
                      &nbsp;&nbsp;<input  name="input" class="button" type="button" value="<s:text name="return"/>" onclick="jump()"/></td>
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
</s:form>
</body>
</html>
