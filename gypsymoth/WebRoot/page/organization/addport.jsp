<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>资源列表</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/index.css" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/right.css" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajax.js"></script>
		<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/jsFrame.js"></script>
<%@ include file="../include/head.jsp"%>
<script language="javascript">

	var a = 1;
      function addRowToTable()
		{
		var name = "add_div"+a;
		var provinceId = "provinceId"+a;
		var citydivdiv = "citydivdiv"+a;
		var citydivId = "citydivId"+a;
		var tblSample = "tblSample"+a;
		a = a+1;
		var name2 = "add_div"+a;
		//var strHtml = '<span id="d_span'+name2+'"><input type="file" name="certificate">&nbsp;&nbsp;<input type="button" onclick="removeFile(\'d_span'+name2+'\');" value="<s:text name="delete"/>"></input><br></span><div id="'+name2+'"></div>';
		var strHtml =  '<table width="100%" border="0" cellspacing="0" cellpadding="0" id="'+tblSample+'"> <tr>'+
                '<td width="14%" height="20"><s:text name="portcity"/></td>'+
                '<td width="38%" height="20">'+
                '<select id="'+provinceId+'" name="provinceId" onchange="javascript:ajaxselect(\'getAllCity.action\',\''+provinceId+'\',\''+citydivdiv+'\');">'+
                '<option value="0">-请选择-</option>'+
                '<s:if test="#request.promaryList.size>0">'+
                '<s:iterator value="#request.promaryList">'+
                '<option value="${PROID}">${PRONAME}</option>'+
                '</s:iterator>'+
                '</s:if>'+
                '</select>'+
                '<s:text name="Province"/>*</td>'+
				'<td width="48%"><div id="'+citydivdiv+'" style="float:left;">  '+
               	'<select name="citydivId" id="'+citydivId+'">  <option value="">-<s:text name="choose"/>-</option> '+
                '</select><s:text name="City"/>* </div></td></tr><tr><td height="20">&nbsp;</td></tr>  <tr> '+
                '<td height="20"><s:text name="ChineseName"/></td>'+
                '<td colspan="2"><input name="portName" id="portName" type="text" size="50" /></td>'+             
                '</tr>  <tr>  <td height="20"></td>   </tr>   <tr> '+
                '<td height="20"><s:text name="EnglishName"/></td>  <td colspan="2"><input name="portSname" id="portSname" type="text" size="50" /><s:text name="EnglishNameispinyin"/></td> '+
                '</tr>  <tr> <td height="50" colspan="3" align="right"><input type="button" class="button_s" name="input" onclick="removeFile(\''+tblSample+'\')" value="删除该港口"/><br/><hr/></td> </tr> </table>'+
                ' <div id="'+name2+'"></div>';
		document.getElementById(name).innerHTML +=strHtml;
		}
      
      function removeFile(spanname) {
          var spanvalue = "#"+spanname;
		  $(spanvalue).remove(); 
      }
 

// document.getElementById(name).innerHTML = "<table width='100%' border='0' cellspacing='0' cellpadding='0' id='tblSample'> "+select+"   <tr><td height='20'><s:text name='name1'/></td><td><input name='portName' type='text' class='input' size='44' /></td></tr><tr><td height='20'><s:text name='sname'/></td><td><input name='portSname' type='text' class='input' size='44' /></td></tr><tr><td height='20'>&nbsp;</td><td height='28'></td></tr></table><div id='"+name2+"'></div>";

function deleteToTable()
{
	$("#add_div1 table:last").remove(); 
}


function addsubmit(){
	var provinceId=document.getElementById("provinceId");
	var portName=document.getElementsByName("portName");
	var portSname=document.getElementsByName("portSname");
	var formatEN = /^[a-zA-Z0-9]+$/;//判断英文的正则表达式 
	for(var i=0;i<portName.length;i++){
		if(portName[i].value=="")
		{
			alert("港口中文名称不能为空!");
			return  false;
		}
	}
	for(var i=0;i<portSname.length;i++){
		if(portSname[i].value=="")
		{
			alert("港口英文名称不能为空!");
			return false;
		}
		else if(portSname[i].value!=""){
			if(!formatEN.test(portSname[i].value)){
				alert("港口英文名称只能为英文!");
				return false;
			}
		}
	}		 	
	
	return true;
	}

</script>
<script type="text/javascript">
function jump(){
	window.location="${pageContext.request.contextPath}/page/organization/getAllOrganizationByPage.action";
}
</script> 
</head>
<body>
<!--内容开始-->
<s:form action="savePort" theme="simple" id="savePort" enctype ="multipart/form-data" method="post">
<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
<input type="hidden" name="orgId" value="<%=request.getAttribute("oId")%>"/>
  <tr>
  
    <td class="list_r">&nbsp;</td>
    <td valign="top" class="right"><div class="title"><s:text name="local" />： <s:text name="home" /> &gt;&gt; <s:text name="orgAndPerson" /> &gt;&gt; <strong><s:text name="OrganizationManage" /></strong></div>
      <div>
        <table align="center" cellpadding="12" class="tabedit">
          <tr>
          		<td height="20" valign="top"><s:text name="manageport"/></td>
          		<td>
                    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tblSample">
                     <tr>
                     	<td width="14%" height="20"><s:text name="portcity"/></td>
                        <td width="38%" height="20">
						<s:select list="#request.promaryList" name="provinceId"
						id="provinceId" listKey="PROID" listValue="PRONAME"
						headerKey="0" headerValue="-%{getText('请选择')}-"
						onchange="javascript:ajaxselect('getAllCity.action','provinceId','citydivdiv');"></s:select>　<s:text name="Province"/>*</td>
						<td width="48%"><div id="citydivdiv" style="float:left;">
                          <select name="citydivId" id="citydivId">
                            <option value="">-<s:text name="choose"/>-</option>
                          </select>
						  　
						  <s:text name="City"/>* </div></td>
						</tr> 
						<tr>
                    	<td height="20">&nbsp;</td>                    	
                 	 </tr>                       
                     <tr>
                        <td height="20"><s:text name="ChineseName"/></td>
                        <td colspan="2"><input name="portName" id="portName" type="text" size="50" /></td>                        
                     </tr>
                       <tr>
                    <td height="20">&nbsp;</td>                    
                  </tr>  
                      <tr>
                        <td height="20"><s:text name="EnglishName"/></td>
                        <td colspan="2"><input name="portSname" id="portSname" type="text" size="50" />&nbsp;&nbsp;&nbsp;<s:text name="EnglishNameispinyin"/></td>
                      </tr>
                      <tr>
                    	<td height="20" colspan="3"><hr/></td>                    	
                  	  </tr>  
                  	                    
                    </table>
					<div id="add_div1" name="add_div1"></div>
　　　　　
<input type="button" class="button_s" value="<s:text name="addport"/>" onclick="addRowToTable();" />
<input type="submit" name="submit" class="button_s" value="<s:text name="savebutton" />" onclick="return addsubmit();"/>
<input type="button" onclick="jump()" class="button_s" value="<s:text name="return" />" />

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