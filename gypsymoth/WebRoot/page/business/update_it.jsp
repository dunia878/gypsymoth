<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<%
String path1 = request.getContextPath();
String basePath1 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

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
 function addsubmit(){
 	var certificate =   document.getElementById("certificate");
 	var examrecord = document.getElementById("examrecord");
 	var workphoto = document.getElementById("workphoto");
 	var gypsymothphoto = document.getElementById("gypsymothphoto");
 	var shipinfo = document.getElementById("shipinfo");
 	var stopportinfo = document.getElementById("stopportinfo");
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
 	if(gypsymothphoto.value==""){
 		alert("请上传舞毒蛾照片！");
 		gypsymothphoto.focus();
 		return false;
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
 	return true;
 }  
</script>
</head>
<body>
<!--内容开始-->
<s:fielderror/>
<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
 <s:form action="updateF" theme="simple" id="save" enctype ="multipart/form-data" method="post" namespace="/page/examine">
 <s:hidden name="bsid" value="%{#request.businessId}"></s:hidden>
  <tr>
    <td class="list_r">&nbsp;</td>
    <td valign="top" class="right"><div class="title"><s:text name="local"/>： <s:text name="home"/> &gt;&gt; <s:text name="bussinessManage"/> &gt;&gt; <strong> 修改附件</strong></div>
      <div>
      <table width="100%" align="center" class="tableheader">
          <tr>
            <td width="5%" height="26"><img src="${pageContext.request.contextPath}/images/bg_mcontentL.gif"  width="32" height="27" /></td>
            <td valign="middle"><h3>修改上传附件</h3></td>
            <td align="right" valign="middle">&nbsp;</td>
            <td width="1%" align="right"><img src="${pageContext.request.contextPath}/images/bg_mcontentR.gif"  width="7" height="27" /></td>
          </tr>
        </table>
        <table align="center" cellpadding="12" class="tabedit">
          <tr>
            <td align="left" valign="top"><!--编辑开始-->
              <table width="706" align="center" cellspacing="5">
                <tr>
                  <td width="180" height="20"><s:text name="uploadcertificate"/></td>
                  <td width="332"><label>
                  <input type="file" name="certificate" id="certificate" /></label>&nbsp;&nbsp;<a href="javascript:void(addCertificateFile());">
                  <s:text name="addattachment"/>
                  </a></td>
                  <td width="272">&nbsp;</td>
  </tr>
                <tr>
                	<td></td>
                    <td><div id="certificateDiv1"></div></td> <td></td>
                </tr>
               <tr>
                  <td><s:text name="uploadexaminerecord"/></td>
                  <td><label>
                  <input type="file" name="examrecord" id="examrecord" /></label>&nbsp;&nbsp;&nbsp;<a href="javascript:void(addExamrecordFile());"><s:text name="addattachment"/></a></td>
                  <td>&nbsp;</td>
                <tr>
                	<td></td>
                     <td><div id="examrecordDiv1"></div></td> <td></td>
                </tr>              
                <tr>
                  <td height="20"><s:text name="uploadworkphoto"/></td>
                  <td><label>
                  <input type="file" name="workphoto" id="workphoto" />
                  </label>&nbsp;&nbsp;<a href="javascript:void(addWorkphotoFile());"><s:text name="addattachment"/></a></td>
                  <td>&nbsp;</td>
                  <tr>
                	<td></td>
                     <td><div id="workphotoDiv1"></div></td> <td></td>
                </tr> 
                 <tr>
                  <td height="20"><s:text name="uploadgypsymothphoto"/></td>
                  <td><label>
                  <input type="file" name="gypsymothphoto" id="gypsymothphoto" />
                  </label>&nbsp;&nbsp;<a href="javascript:void(addGypsymothphotoFile());"><s:text name="addattachment"/></a></td>
                  <td>&nbsp;</td>
                  <tr>
                	<td></td>
                     <td><div id="gypsymothphotoDiv1"></div></td> <td></td>
                </tr>  
                <tr>
                  <td height="20"><s:text name="uploadshipinfo"/></td>
                  <td><label>
                  <input type="file" name="shipinfo" id="shipinfo" />
                  </label>&nbsp;&nbsp;<a href="javascript:void(addShipinfoFile());"><s:text name="addattachment"/></a></td>
                  <td>&nbsp;</td>
                  <tr>
                	<td></td>
                     <td><div id="shipinfoDiv1"></div></td> <td></td>
                </tr>  
                <tr>
                  <td height="20"><s:text name="uploadstopportinfo"/></td>
                  <td><label>
                  <input type="file" name="stopportinfo" id="stopportinfo" />
                  </label>&nbsp;&nbsp;<a href="javascript:void(addStopportinfoFile());"><s:text name="addattachment"/></a></td>
                  <td>&nbsp;</td>
                  <tr>
                	<td></td>
                     <td><div id="stopportinfoDiv1"></div></td> <td></td>
                </tr>            
                 <tr>
                  <td height="38" align="left"></td>
                  <td height="38" align="left"><input name="savebutton" class="button" type="submit"  value="<s:text name="savebutton"/>" onclick="return addsubmit();"/>
                    <input name="input2" class="button" type="reset" value="<s:text name="reset"/>" />
                      <input name="return" class="button" type="reset" value="<s:text name="return"/>"  onclick="history.back(-1)" /></td>
                  <td align="left">&nbsp;</td>
                </tr>
              </table>
              <!--编辑结束-->            </td>
          </tr>
        </table>
   </s:form>
</table>
</body>
</html>
