<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统</title> 
		<%@ include file="../include/head.jsp"%>
		<link href="<%=basePath %>/style/printer.css"
			type="text/css" rel="stylesheet" />
	</head>
	<style media="print">
        .Noprint { DISPLAY: none }
   </style>	 	
<script language="javascript" type="text/javascript">
function save(){
document.form1.method="post";
document.form1.action="print.action";
document.form1.submit();

}
function commit(){ 
document.form1.method="post";
document.form1.action = "commit.action";
document.form1.submit();
}

	window.onbeforeprint=beforePrint; 
	window.onafterprint=afterPrint; 
	function beforePrint() 
	{ 
	document.all.input.border= "0";
	document.getElementById("print_div").style.display='none'; 
	}  
	
	function afterPrint() 
	{ 
	document.getElementById("print_div").style.display='';  
	} 
</script>  
	<body>

		 
 <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tab">
  <tr>
    <td width="50%" height="36" valign="top"><img src="<%=basePath %>printer/images/printer_logo.gif" width="246" height="39" /></td>
  	<td width="50%" align="right">
  		<s:if test="#request.inspection2_input!=null">
  		<table style="border:1px solid #000;" ><tr><td>
  			<span class="txt_inspection">${inspection2_input }</span>
  		</td></tr></table>
  		</s:if>
  	</td> 
  
  </tr>
</table>
<table width="1002" border="0" cellpadding="1" cellspacing="0" class="tab">
  <tr>
    <td width="65%" height="40" align="center" valign="bottom" class="tit_16pt">船 舶 舞 毒 蛾 检 查 申 请 单</td>
    <td width="35%" align="right" valign="middle" class="namber_no"><div align="left" class="txt_8pt" style="margin-top:8px;">编号/NO.：
        <input name="textfield2" readonly="readonly" type="text" class="input" id="textfield2" size="17" value="${appno_input }" />
    </div>
      <label>      
      <div align="center"></div>
    </label></td>
  </tr>
  <tr>
    <td height="40" align="center" valign="middle" class="tit_en12pt">APPLICATION FORM FOR AGM VESSEL INSPECTION</td>
    <td width="35%" align="right" valign="middle" class="namber_no"><div align="left" class="txt_8pt">日期/Date: &nbsp;&nbsp;
        <input name="textfield" type="text" readonly="readonly" class="input" id="textfield" size="17" value="${date_input }" />
    </div>
      <label>
      <div align="center"></div>
    </label></td>
  </tr>
</table>
<br />
<table width="100%" border="0" cellpadding="1" cellspacing="0" class="tab1" height="570">
  <tr>
    <td width="181">申请人<br />
      <span class="en_9pt">Applicant</span></td>
    <td width="444" class="txt_11pt">&nbsp;${businessname }</td>
  </tr>
  <tr>
    <td>船东或租船人名称<br />
      <span class="en_9pt">Name of owner or charterer</span></td>
    <td>&nbsp;${company }</td>
  </tr>
  <tr>
    <td>目的国<br />
      <span class="en_9pt">Country of destination</span></td>
    <td>&nbsp;${countrysandports_input }</td>
  </tr>
  <tr>
    <td>船名及类型<br />
      <span class="en_9pt">Name and type of vessel</span></td>
    <td>&nbsp;${vesselnameandtype_input }</td>
  </tr>
  <tr>
    <td>船籍、IMO编号及总吨位<br />
      <span class="en_9pt">Nationality, IMO number and<br />
    Gross tonnage</span></td>
    <td>&nbsp;${reg_imo_tonnage_input }</td> 
  </tr> 
  <tr>
    <td>计划入港/离港日期<br />
      <span class="en_9pt">The estimated date <br />
    of arrival and departure</span></td>
    <td>&nbsp;${inoutdate_input }</td>
  </tr>
  <tr>
    <td>船舶停靠泊位、锚位<br />
      <span class="en_9pt">Place of berthed or anchored</span></td> 
    <td>&nbsp;${breth_inpit }</td>
  </tr> 
  <tr> 
    <td>合适的检查日期及时间<br />
      <span class="en_9pt">Feasible date and time of<br />
    vessel inspection</span></td>
    <td>&nbsp;${checkdate_input }</td>
  </tr>
  <tr>
    <td>申请人联系人及联系电话<br />
      <span class="en_9pt">Name of contact personnel and<br />
    Tel number of the Applicant</span></td>
    <td>&nbsp;${link_input }</td>
  </tr>
  <tr>
    <td>检查机构 
      <br />
      <span class="en_9pt">Certification body</span></td>
    <td>CCIC INSPECTION  CO.,LTD</td>
  </tr>
</table>
<br />
<table width="94%" border="0" cellpadding="0" cellspacing="0" class="tab">
  <tr>
    <td height="35" align="center"><table width="99%" border="0" cellpadding="0" cellspacing="1">
      <tr>
        <td width="263" align="left">申请人（<em>签字及公章</em>）：</td>
        </tr>
      <tr>
        <td align="left" valign="top" class="ht">Applicant(<em>Signature and Stamp</em>):</td>
        </tr>
      <tr>
        <td align="left" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="50">&nbsp;</td>
          </tr>
          <tr>
            <td height="1" bgcolor="#000000"></td>
          </tr>
        </table></td>
        </tr>
    </table></td>
    <td width="15%" align="center">&nbsp;</td>
    <td align="left"><table width="90%" border="0" cellpadding="0" cellspacing="1">
      <tr>
        <td width="263" align="left">申请日期：</td>
        </tr>
      <tr>
        <td align="left" valign="top" class="ht">Date:</td>
        </tr>
      <tr>
        <td align="left" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="50">&nbsp;</td>
          </tr>
          <tr>
            <td height="1" bgcolor="#000000"></td>
          </tr>
        </table></td>
        </tr>
    </table></td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tab">
  <tr>
    <td height="50" valign="bottom" class="txt_11pt" style="line-height:18px;">※	请用英文清晰填写申请表并用<span class="ht"></span><span class="ht10">A4</span>纸打印。 <br />
    ※	<span class="ht">PLEASE FILL IN THE FORM IN ENGLISH AND PRINT OUT THE PAGE.</span>
    </td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tab">
  <tr>
    <td height="30" valign="bottom" class="txt_11pt" style="line-height:18px;">
    <span class="ht"> 实施日期: ${inspectiondate_input }</span>
    </td>
  </tr>
</table>
   
		 
		 
		<div id="print_div" class="Noprint">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" height="20">
			<tr>
				<td height="38" align="right">
					<input name="button" class="button" type="button"
						onclick="window.focus();window.print()" value="打 印" />
					</td>
					<td width="5%" align="center"> 
					&nbsp;
				</td>
					<td height="38" align="left">	
					<input name="input" class="button" type="button" value="关 闭"
						onclick="window.close();" />
				</td>
			</tr>
		</table>
		</div>
		 

	</body>
</html>
