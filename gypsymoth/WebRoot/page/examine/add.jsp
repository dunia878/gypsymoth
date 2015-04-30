<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title><s:text name="commitexamine"/></title>
<link rel=stylesheet href="${pageContext.request.contextPath}/style/index.css" type="text/css" />
<link rel=stylesheet href="${pageContext.request.contextPath}/style/right.css" type="text/css" />
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<%@ include file="../include/head.jsp"%>
<script language="javascript">

$("document").ready(function(){
 
});

var a = 1;
function addRowToTable1()
{
 
var name = "add_div"+a;
 
a = a+1;
var name2 = "add_div"+a;
var app_div = $("#tblSample").html();
 
var dome_div_begin = "<table width=\"600\" align=\"center\" cellspacing=\"5\" cellpadding=\"0\" id=\"tblSample\" name=\"tblSample\">";
var app = dome_div_begin + app_div +"</table>";
$("#add_div1").append(app); 
 

// document.getElementById(name).innerHTML = "<table width='100%' border='0' cellspacing='0' cellpadding='0' id='tblSample'> "+select+"   <tr><td height='20'><s:text name='name1'/></td><td><input name='portName' type='text' class='input' size='44' /></td></tr><tr><td height='20'><s:text name='sname'/></td><td><input name='portSname' type='text' class='input' size='44' /></td></tr><tr><td height='20'>&nbsp;</td><td height='28'></td></tr></table><div id='"+name2+"'></div>";
}
function deleteToTable1()
{
	if(confirm("您确认删除新增港口?")){
		$("#add_div1 table:last").remove(); 
	}
}
var b=1;
function addRowToTable()
{
 
var name = "insert_div"+b;
 
a = a+1;
var name2 = "insert_div"+b;
var app_div = $("#tblSample1").html();
 
var dome_div_begin = "<table width=\"600\" align=\"center\" cellspacing=\"5\" cellpadding=\"0\" id=\"tblSample1\" name=\"tblSample1\">";
var app = dome_div_begin + app_div +"</table>";
$("#insert_div1").append(app); 
 

// document.getElementById(name).innerHTML = "<table width='100%' border='0' cellspacing='0' cellpadding='0' id='tblSample'> "+select+"   <tr><td height='20'><s:text name='name1'/></td><td><input name='portName' type='text' class='input' size='44' /></td></tr><tr><td height='20'><s:text name='sname'/></td><td><input name='portSname' type='text' class='input' size='44' /></td></tr><tr><td height='20'>&nbsp;</td><td height='28'></td></tr></table><div id='"+name2+"'></div>";
}
function deleteToTable()
{
	if(confirm("您确认删除新增港口?")){
		$("#insert_div1 table:last").remove(); 
	}
}
</script>
<script language="javascript">
var a = 1;
function addCertificateFile() {
		var name = "certificateDiv"+a;
		a = a+1;
		var name2 = "certificateDiv"+a;
		var strHtml = '<span id="cert_span'+name2+'"><input type="file" name="certificate">&nbsp;&nbsp;<input type="button"  onclick="javascript:removeFile(\'cert_span'+name2+'\');" value="<s:text name="delete"/>"></input><br></span><div id="'+name2+'"></div>';
		document.getElementById(name).innerHTML += strHtml;		
      }
var b = 1;    
function addExamrecordFile() {
		 var name = "examrecordDiv"+b;
		 b = b+1;
		 var name2 = "examrecordDiv"+b;
		 var strHtml = '<span id="exam_span'+name2+'"><input type="file" name="examrecord">&nbsp;&nbsp;<input type="button"  onclick="removeFile(\'exam_span'+name2+'\');" value="<s:text name="delete"/>"></input><br></span><div id="'+name2+'"></div>';
		 document.getElementById(name).innerHTML += strHtml;         
      }
var c = 1;  
 function addWorkphotoFile() {
 		  var name = "workphotoDiv"+c;
		  c = c+1;
		  var name2 = "workphotoDiv"+c;
          var strHtml = '<span id="work_span'+name2+'"><input type="file" name="workphoto">&nbsp;&nbsp;<input type="button"  onclick="removeFile(\'work_span'+name2+'\');" value="<s:text name="delete"/>"></input><br></span><div id="'+name2+'"></div>';
          document.getElementById(name).innerHTML += strHtml; 
      }
var d = 1;
function addGypsymothphotoFile() {
		   var name = "gypsymothphotoDiv"+d;
		   d = d+1;
           var name2 = "gypsymothphotoDiv"+d;
           var strHtml = '<span id="gypsy_span'+name2+'"><input type="file" name="gypsymothphoto">&nbsp;&nbsp;<input type="button" onclick="removeFile(\'gypsy_span'+name2+'\');" value="<s:text name="delete"/>"></input><br></span><div id="'+name2+'"></div>';
           document.getElementById(name).innerHTML += strHtml;
      }
var e = 1;
function addShipinfoFile() {
		   var name = "shipinfoDiv"+e;
		   e = e+1;
           var name2 = "shipinfoDiv"+e;           
           var strHtml = '<span id="ship_span'+name2+'"><input type="file" name="shipinfo">&nbsp;&nbsp;<input type="button" onclick="removeFile(\'ship_span'+name2+'\');" value="<s:text name="delete"/>"></input><br></span><div id="'+name2+'"></div>';
           document.getElementById(name).innerHTML +=strHtml;
      }
var f = 1;
function addStopportinfoFile() {
		   var name = "stopportinfoDiv"+f;
		   f = f+1;
           var name2 = "stopportinfoDiv"+f;
           var strHtml = '<span id="stop_span'+name2+'"><input type="file" name="stopportinfo">&nbsp;&nbsp;<input type="button" onclick="removeFile(\'stop_span'+name2+'\');" value="<s:text name="delete"/>"></input><br></span><div id="'+name2+'"></div>';
           document.getElementById(name).innerHTML += strHtml;
      }
function removeFile(spanname) {
		var spanvalue = "#"+spanname;
		$(spanvalue).remove(); 
        // obj.removeNode(true); 
      }
function allotView(id)
		{	var examinetime = document.getElementById("examinetime");
			if(examinetime.value==""){
 			alert("检查时间不能为空");
 			examinetime.focus();
 			return false;
 }   
			var url = "getAllotPageView.action?id="+id;
			window.open(url,"_blank");
		}
function updateBussiness(){
	window.location="${pageContext.request.contextPath}/page/examine/getExaminePre.action?Id="+<s:property value="#request.list.ID"/>;	
	}
 function addsubmit(){
 	var examinetime = document.getElementById("examinetime");
 	var nationid = document.getElementById("nationid");
 	var portName = document.getElementById("portName");
 	var stopTime = document.getElementById("stopTime");
 	var anationid = document.getElementById("anationid");
 	var aportName = document.getElementById("aportName");
 	var astopTime = document.getElementById("astopTime"); 
 	var gypsymoth1 = document.getElementById("gypsymoth1");
 	var gypsymoth2 = document.getElementById("gypsymoth2"); 
 	var gypsymoth3 = document.getElementById("gypsymoth3");
 	var gypsymoth4 = document.getElementById("gypsymoth4");
 	var doubt =  document.getElementById("doubt");
 	var certificate =   document.getElementById("certificate");
 	var examrecord = document.getElementById("examrecord");
 	var workphoto = document.getElementById("workphoto");
 	var gypsymothphoto = document.getElementById("gypsymothphoto");
 	var shipinfo = document.getElementById("shipinfo");
 	var stopportinfo = document.getElementById("stopportinfo");
 	if(examinetime.value==""){
 		alert("检查时间不能为空");
 		examinetime.focus();
 		return false;
 }   
	 if(nationid.value!=""){
	    if(portName.value==""){
 		alert("请输入港口名称！");
 		portName.focus();
 		return false;
 		}
 		if(stopTime.value==""){
 		alert("请输入停靠日期");
 		stopTime.focus();
 		return false;
 		} 	 		
 	}   
 	if(anationid.value!=""){
	    if(aportName.value==""){
 		alert("请输入港口名称！");
 		aportName.focus();
 		return false;
 		}
 		if(astopTime.value==""){
 		alert("请输入停靠日期");
 		astopTime.focus();
 		return false;
 		} 
 		 		
 	}  
 	if(gypsymoth1.value==""){
 		alert("请输入舞毒蛾卵块数量！");
 		gypsymoth1.focus();
 		return false;
 		}	
 	if(gypsymoth2.value==""){
 		alert("请输入舞毒蛾幼虫数量！");
 		gypsymoth2.focus();
 		return false;
 		}
 	if(gypsymoth3.value==""){
 		alert("请输入舞毒蛾蛹数量！");
 		gypsymoth3.focus();
 		return false;
 		}	
 	if(gypsymoth3.value==""){
 		alert("请输入舞毒蛾蛹数量！");
 		gypsymoth3.focus();
 		return false;
 		}
 	if(gypsymoth4.value==""){
 		alert("请输入舞毒蛾成虫数量！");
 		gypsymoth4.focus();
 		return false;
 		}
 	if(doubt.value==""){
 		alert("请输入疑似舞毒蛾数量！");
 		doubt.focus();
 		return false;
 		}
 	if(certificate.value==""){
 		alert("请上传证书！");
 		certificate.focus();
 		return false;
 		}
 	if(examrecord.value==""){
 		alert("请上传检查记录表！");
 		examrecord.focus();
 		return false;
 		}
 	if(workphoto.value==""){
 		alert("请上传工作照！");
 		workphoto.focus();
 		return false;
 		}
 	if(gypsymoth1.value!="0"||gypsymoth2.value!="0"||gypsymoth3.value!="0"||gypsymoth4.value!="0"||doubt.value!="0"){
 	if(gypsymothphoto.value==""){
 		alert("请上传舞毒蛾照片！");
 		gypsymothphoto.focus();
 		return false;
 		}	
 		}
 	if(shipinfo.value==""){
 		alert("请上传船舶资料！");
 		shipinfo.focus();
 		return false;
 		}
 	if(stopportinfo.value==""){
 		alert("请上传船舶停靠港口清单！");
 		stopportinfo.focus();
 		return false;
 		}
 	if (confirm("确认提交？")) {
 	return true;
 	}
 	else 
           return false;
 	
 }   
 
 function updateExamin(){
	 if (confirm("确认提交？")) {
 			document.sava.method="post";
			document.sava.action="updateFalse.action";
			document.sava.submit();
	 	}
	 	else 
	           return false;
	 }
</script>
</head>
<body>
<!--内容开始-->
<s:fielderror/>
<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
 <s:form action="save" theme="simple" id="save" name="sava" enctype ="multipart/form-data" method="post">
  <tr>
    <td class="list_r">&nbsp;</td>
    <td valign="top" class="right"><div class="title"><s:text name="local"/>： <s:text name="home"/> &gt;&gt; <s:text name="bussinessManage"/> &gt;&gt; <strong> <s:text name="examine"/></strong></div>
      <div>
      <table width="100%" align="center" class="tableheader">
          <tr>
            <td width="5%" height="26"><img src="${pageContext.request.contextPath}/images/bg_mcontentL.gif"  width="32" height="27" /></td>
            <td valign="middle"><h3><s:text name="Infomationandcertificate"/></h3></td>
            <td align="right" valign="middle">&nbsp;</td>
            <td width="1%" align="right"><img src="${pageContext.request.contextPath}/images/bg_mcontentR.gif"  width="7" height="27" /></td>
          </tr>
        </table>
        <table align="center" cellpadding="12" class="tabedit">
          <tr>
            <td align="left" valign="top"><!--编辑开始-->
              <table width="600" align="center" cellspacing="5">
              
                  <tr>
                    <td width="61" height="20"><s:text name="appno"/><input type="hidden" name="appId" value="<s:property value="#request.list.ID"/>" /></td>
                    <td width="518"><input name="appno" type="text" class="input" disabled="disabled" value="<s:property value="#request.list.APPNO"/>" size="55" /></td>
                  </tr>
                   <tr>
                    <td height="20"><s:text name="applicat"/></td>
                    <td><input name="libName6" type="text" class="input"  disabled="disabled"  value="<s:property value="#request.list.BUSINESSNAME"/>" size="55" /></td>
                  </tr>
                   <tr>
                    <td height="20"><s:text name="boatowner"/></td>
                    <td><input name="libName6" type="text" class="input"  disabled="disabled"  value="<s:property value="#request.list.LINKMANNAME"/>" size="55" /></td>
                  </tr>
                  <tr>
                    <td height="20"><s:text name="type"/></td>
                    <td><input name="libName6" type="text" class="input"  disabled="disabled"  value="<s:property value="#request.list.VESSELTYPE"/>" size="55" /></td>
                  </tr>
                  <tr>
                    <td height="20"><s:text name="shipper"/></td>
                    <td><input name="libName6" type="text" class="input"  disabled="disabled"  value="<s:property value="#request.list.REGISTRY"/>" size="55" /></td>
                  </tr>
                  <tr>
                    <td height="20"><s:text name="totalton"/></td>
                    <td><input name="libName6" type="text" class="input"  disabled="disabled"  value="<s:property value="#request.list.TONNAGE"/>" size="55" />MT</td>
                  </tr>
                  <tr>
                    <td height="20"><s:text name="planinportdate"/></td>
                    <td><input name="libName6" type="text" class="input"  disabled="disabled"  value="<s:property value="#request.list.PLANDATEIN"/>" size="55" /></td>
                  </tr>
                  <tr>
                    <td height="20"><s:text name="planoutportdate"/></td>
                    <td><input name="libName6" type="text" class="input"  disabled="disabled"  value="<s:property value="#request.list.PLANDATEIN"/>" size="55" /></td>
                  </tr>
                  <tr>
                    <td height="20"><s:text name="berth"/></td>
                    <td><input name="libName6" type="text" class="input"  disabled="disabled"  value="<s:property value="#request.list.BERTH"/>" size="55" /></td>
                  </tr>
                  <tr>
                    <td><s:text name="boatname"/></td>
                    <td><input name="" type="text" class="input" disabled="disabled" value="<s:property value="#request.list.VESSELNAME"/>" size="55" /></td>
                  </tr>
                  <tr>
                    <td height="20">IMO<s:text name="number"/></td>
                    <td><input name="libName4" type="text" class="input" disabled="disabled" value="<s:property value="#request.list.IMO"/>" size="55" /></td>
                  </tr>
                  <tr>
                    <td height="20"><s:text name="stopport"/></td>
                    <td><input name="libName5" type="text" class="input" disabled="disabled" value="<s:property value="#request.list.PORT_NAME"/>" size="55" /></td>
                  </tr>
                  <tr>
                    <td height="20"><s:text name="apptime"/></td>
                    <td><input name="libName6" type="text" class="input"  disabled="disabled"  value="<s:property value="#request.list.APPDATE"/>" size="55" /></td>
                  </tr>
                 <tr>
                    <td height="20"><s:text name="applicat"/><s:text name="name"/></td>
                    <td><input name="libName6" type="text" class="input"  disabled="disabled"  value="<s:property value="#request.list.BUSINESSNAME"/>" size="55" /></td>
                  </tr>
                  <tr>
                    <td height="20"><s:text name="applicat"/><s:text name="contacttel"/></td>
                    <td><input name="libName6" type="text" class="input"  disabled="disabled"  value="<s:property value="#request.list.PHONE"/>" size="55" /></td>
                  </tr>
                  <tr>
                    <td height="20"><s:text name="applicat"/><s:text name="fax"/></td>
                    <td><input name="libName6" type="text" class="input"  disabled="disabled"  value="<s:property value="#request.list.FAX"/>" size="55" /></td>
                  </tr>
                  <tr>
                    <td height="20"><s:text name="applicat"/><s:text name="email"/></td>
                    <td><input name="libName6" type="text" class="input"  disabled="disabled"  value="<s:property value="#request.list.EMAIL"/>" size="55" /></td>
                  </tr>
                  <tr>
                    <td height="20"><s:text name="examinetime"/></td>
                    <td><input name="examinetime" id="examinetime" type="text" class="input"    value="" size="55"  onclick="WdatePicker()"/>*</td>
                  </tr>
                   <tr>
                    <td height="20">&nbsp;&nbsp;</td>
                    <td height="20"><input  name="input" class="button" type="button" value="修改"  onclick="updateBussiness()" />&nbsp;&nbsp;<!-- <input  name="input" class="button" type="button" value="预览证书"  onclick="allotView(<s:property value="#request.list.ID"/>);" /> --></td>                    
                  </tr>
                </table>
            <!--编辑结束-->            </td>
          </tr>
        </table>
        <br />
        <table width="100%" align="center" class="tableheader">
          <tr>
            <td width="5%" height="26"><img src="${pageContext.request.contextPath}/images/bg_mcontentL.gif"  width="32" height="27" /></td>
            <td valign="middle"><h3><s:text name="beforestopport"/></h3></td>
            <td align="right" valign="middle">&nbsp;</td>
            <td width="1%" align="right"><img src="${pageContext.request.contextPath}/images/bg_mcontentR.gif"  width="7" height="27" /></td>
          </tr>
        </table>
        <table align="center" cellpadding="12" class="tabedit">
          <tr>
            <td align="left" valign="top"><!--编辑开始-->
                <table width="600" align="center" cellspacing="5" cellpadding='0' id="tblSample" name="tblSample">
                  <tr>
                    <td width="47" height="20"><s:text name="country"/></td>
                    <td width="341">
                        <select name="nationid" class="select" id="nationid" style="width:305px;">
                        <option value=""><s:text name="choose"/></option>
                        <option value="37"><s:text name="China"/></option>
	                    <option value="83"><s:text name="Japan"/></option>
                        <option value="88"><s:text name="Korea"/></option>
                        <option value="142"><s:text name="Russia"/></option>
                        <option value="113"><s:text name="Mongolia"/></option>
                    </select>
                    </td>
                    <td width="184">&nbsp;</td>
                  </tr>
                  <tr>
                    <td><s:text name="port1"/></td>
                    <td><input name="portName" id="portName" ="text" class="input" size="55" /></td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td height="20"><s:text name="date"/></td>
                    <td><input name="stopTime" id="stopTime" type="text" class="input" size="55" onclick="WdatePicker()"/></td>
                    <td>&nbsp;</td>
                  </tr>       
                
                  <tr>
                    <td height="20">&nbsp;</td>
                    <td><input type="button" class="button_s" value="<s:text name="addport"/>" onclick="addRowToTable1();" /><input type="button" class="button_s" name="input" onclick="deleteToTable1()" value=" <s:text name="deleteaddPort"/>"/></td>
                    <td>&nbsp;</td>
                  </tr>
                </table>
                <div id="add_div1" name="add_div1"></div>
            <!--编辑结束-->            </td>
          </tr>
        </table>
        <br />
        <table width="100%" align="center" class="tableheader">
          <tr>
            <td width="5%" height="26"><img src="${pageContext.request.contextPath}/images/bg_mcontentL.gif"  width="32" height="27" /></td>
            <td valign="middle"><h3><s:text name="aferstopport"/></h3></td>
            <td align="right" valign="middle">&nbsp;</td>
            <td width="1%" align="right"><img src="${pageContext.request.contextPath}/images/bg_mcontentR.gif"  width="7" height="27" /></td>
          </tr>
        </table>
        <table align="center" cellpadding="12" class="tabedit">
          <tr>
            <td align="left" valign="top"><!--编辑开始-->
              <table width="600" align="center" cellspacing="5" cellpadding='0' id="tblSample1" name="tblSample1">
                <tr>
                  <td width="47" height="20"><s:text name="country"/></td>
                  <td width="341"><select name="anationid" class="select" id="anationid" style="width:305px;">
                      <option><s:text name="choose"/></option>
                        <option value="37"><s:text name="China"/></option>
	                    <option value="83"><s:text name="Japan"/></option>
                        <option value="88"><s:text name="Korea"/></option>
                        <option value="142"><s:text name="Russia"/></option>
                        <option value="113"><s:text name="Mongolia"/></option>
                  </select></td>
                  <td width="184">&nbsp;</td>
                </tr>
                <tr>
                  <td><s:text name="port1"/></td>
                  <td><input name="aportName" id="aportName" type="text" class="input" size="55" /></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="20"><s:text name="date"/></td>
                  <td><input name="astopTime" id="astopTime"  type="text" class="input" size="55" onclick="WdatePicker()"/></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="20">&nbsp;</td>
                  <td><input type="button" class="button_s" value="<s:text name="addport"/>" onclick="addRowToTable();" /><input type="button" class="button_s" name="input" onclick="deleteToTable()" value=" <s:text name="deleteaddPort"/>"/></td>
                  <td>&nbsp;</td>
                </tr>
              </table>
              <div id="insert_div1" name="insert_div1"></div>
              <!--编辑结束-->
            </td>
          </tr>
        </table>
        <br />
        <table width="100%" align="center" class="tableheader">
          <tr>
            <td width="5%" height="26"><img src="${pageContext.request.contextPath}/images/bg_mcontentL.gif"  width="32" height="27" /></td>
            <td valign="middle"><h3><s:text name="gypsymothexamine"/></h3></td>
            <td align="right" valign="middle">&nbsp;</td>
            <td width="1%" align="right"><img src="${pageContext.request.contextPath}/images/bg_mcontentR.gif"  width="7" height="27" /></td>
          </tr>
        </table>
        <table width="100%" class="tableCont" align="center" style="margin:auto;">
          <tr>
            <td><div class="tableContent">
                <table width="100%"  border="0" cellspacing="1" cellpadding="0" align="center">
                  
                    <tr>
                      <th width="11%">&nbsp;</th>
                      <th width="15%" ><s:text name="egg"/></th>
                      <th width="15%"><s:text name="young"/></th>
                      <th width="15%"><s:text name="pupa"/></th>
                      <th width="15%"><s:text name="worms"/></th>
                      <th ><s:text name="suspected"/></th>
                    </tr>
                    <tr>
                      <td align="center"><s:text name="quantity"/></td>
                      <td align="center"><input name="gypsymoth1" id="gypsymoth1" type="text" style="border:1px solid #fff; text-align:center;" class="input" value="<s:property value="#request.examineList.GYPSYMOTH1"/>"/></td>
                      <td align="center"><input name="gypsymoth2" id="gypsymoth2" type="text" style="border:1px solid #fff; text-align:center;" class="input" value="<s:property value="#request.examineList.GYPSYMOTH2"/>"/></td>
                      <td align="center"><input name="gypsymoth3" id="gypsymoth3" type="text" style="border:1px solid #fff; text-align:center;" class="input" value="<s:property value="#request.examineList.GYPSYMOTH3"/>"/></td>
                      <td align="center"><input name="gypsymoth4" id="gypsymoth4" type="text" style="border:1px solid #fff; text-align:center;" class="input" value="<s:property value="#request.examineList.GYPSYMOTH4"/>"/></td>
                      <td align="center"><input name="doubt" id="doubt" type="text" style="border:1px solid #fff; text-align:center;" class="input" value="<s:property value="#request.examineList.DOUBT"/>"/></td>
                     </tr>
                 
                </table>
            </div></td>
          </tr>
        </table>
        <br />
        <table align="center" cellpadding="12" class="tabedit">
          <tr>
            <td align="left" valign="top"><!--编辑开始-->
              <table width="706" align="center" cellspacing="5">
                <tr>
                  <td width="180" height="20"><s:text name="uploadcertificate"/></td>
                  <td width="332"><label>
                  <input type="file" name="certificate" id="certificate" value="<s:property value="#request.examineList.CERTIFICATE"/>"/></label>&nbsp;&nbsp;<a href="javascript:void(addCertificateFile());">
                  <s:text name="addattachment"/>
                  </a></td>
                  <td width="272"><font color="red">*建议扫描的pdf或jpg格式文件</font></td>
  </tr>
                <tr>
                	<td></td>
                    <td><div id="certificateDiv1"></div></td> <td></td>
                </tr>
               <tr>
                  <td><s:text name="uploadexaminerecord"/></td>
                  <td><label>
                  <input type="file" name="examrecord" id="examrecord" value="<s:property value="#request.examineList.EXAMRECORD"/>"/></label>&nbsp;&nbsp;&nbsp;<a href="javascript:void(addExamrecordFile());"><s:text name="addattachment"/></a></td>
                  <td><font color="red">*建议扫描的pdf或jpg格式文件</font></td>
                <tr>
                	<td></td>
                     <td><div id="examrecordDiv1"></div></td> <td></td>
                </tr>              
                <tr>
                  <td height="20"><s:text name="uploadworkphoto"/></td>
                  <td><label>
                  <input type="file" name="workphoto" id="workphoto" value="<s:property value="#request.examineList.WORKPHOTO"/>"/>
                  </label>&nbsp;&nbsp;<a href="javascript:void(addWorkphotoFile());"><s:text name="addattachment"/></a></td>
                  <td><font color="red">*建议jpg格式图片,请避免上传压缩文件</font></td>
                  <tr>
                	<td></td>
                     <td><div id="workphotoDiv1"></div></td> <td></td>
                </tr> 
                 <tr>
                  <td height="20"><s:text name="uploadgypsymothphoto"/></td>
                  <td><label>
                  <input type="file" name="gypsymothphoto" id="gypsymothphoto" value="<s:property value="#request.examineList.GYPSYMOTHPHOTO"/>"/>
                  </label>&nbsp;&nbsp;<a href="javascript:void(addGypsymothphotoFile());"><s:text name="addattachment"/></a></td>
                  <td><font color="red">*建议jpg格式图片,请避免上传压缩文件</font></td>
                  <tr>
                	<td></td>
                     <td><div id="gypsymothphotoDiv1"></div></td> <td></td>
                </tr>  
                <tr>
                  <td height="20"><s:text name="uploadshipinfo"/></td>
                  <td><label>
                  <input type="file" name="shipinfo" id="shipinfo" value="<s:property value="#request.examineList.SHIPINFO"/>"/>
                  </label>&nbsp;&nbsp;<a href="javascript:void(addShipinfoFile());"><s:text name="addattachment"/></a></td>
                  <td><font color="red">*建议扫描的pdf或jpg格式文件</font></td>
                  <tr>
                	<td></td>
                     <td><div id="shipinfoDiv1"></div></td> <td></td>
                </tr>  
                <tr>
                  <td height="20"><s:text name="uploadstopportinfo"/></td>
                  <td><label>
                  <input type="file" name="stopportinfo" id="stopportinfo" value="<s:property value="#request.examineList.STOPPORTINFO"/>"/>
                  </label>&nbsp;&nbsp;<a href="javascript:void(addStopportinfoFile());"><s:text name="addattachment"/></a></td>
                  <td><font color="red">*建议扫描的pdf或jpg格式文件</font></td>
                  <tr>
                	<td></td>
                     <td><div id="stopportinfoDiv1"></div></td> <td></td>
                </tr>            
                 <tr>
                  <td height="38" align="left"></td>
                  <td height="38" align="left">
                  	<input name="submitButton" class="button" type="submit" value="<s:text name='submit'/>" onclick="return addsubmit();"/>
                  	<input name="savebutton" class="button" type="button"  value="<s:text name="savebutton"/>" onclick="updateExamin()" />
                    <input name="input2" class="button" type="reset" value="<s:text name="reset"/>" />
                      <input name="return" class="button" type="reset" value="<s:text name="return"/>"  onclick="history.back(-1)" /></td>
                  <td align="left">&nbsp;</td>
                </tr>
                
                <input type="hidden" name="id" value="<s:property value="#request.examineList.ID"/>"/>
                <input type="hidden" name="appIds" value="<s:property value="#request.list.ID"/>" />
                
              </table>
              <!--编辑结束-->            </td>
          </tr>
        </table>
   </s:form>
</table>
</body>
</html>
