<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统</title>
		<%@ include file="../include/head.jsp"%>
		<link href="<%=basePath %>/style/printer.css" type="text/css" rel="stylesheet" />
	</head>
	<body>
	
			
	<style media="print">
         .Noprint { DISPLAY: none }
   </style>	
	<script language="javascript" type="text/javascript">
    $(document).ready(function(){
        
	});

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

<form action="" method="post">
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tab">
  <tr>
    <td width="70%" valign="top"><img src="<%=basePath %>printer/images/printer_logo.gif" width="246" height="39" /></td>
    <td width="30%" align="right" valign="middle"><span class="namber_no">申请编号:
        <input name="textfield" type="text" class="input" id="textfield" size="17" value="${appno }" />
    </span></td>
  </tr>
</table>
<table width="1002" border="0" cellpadding="1" cellspacing="0" class="tab">
  <tr>
    <td width="73%" height="32" align="center" valign="bottom"><strong class="tit_13pt">船舶舞毒蛾检查收费标准及付款须知</strong></td>
  </tr>
  <tr>
    <td height="25" align="center" valign="bottom" class="tit_en13pt"><strong>SERVICE FEE AND PAYMENT FOR AGM VESSEL INSPECTION</strong></td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tab">
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td class="txt_11_file3pt"><strong>　　一、	TERMS AND STANDARDS OF SERVICE FEE </strong> <br />
    　　1.	INSPECTION FEE: The AGM inspection fee is for AGM inspection, removal of AGM founded, issuance of the certificate, and so on. The amount of fee will be based on the vessel’s gross tonnage, the standard fee listed as follows:<br /></td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tab2">
  <tr>
    <td height="27" align="center">Gross Tonnage（MT）</td>
    <td align="center">Fee（RMB）</td>
    <td align="center">Applicable</td>
  </tr>
  <s:iterator value="#request.tonlist" id="mop">
  <tr>
    <td height="22" align="center">
    	<span class="ht">
    		<s:property value="#mop.MINTON"/>-
    		<s:if test="#mop.MAXTON>=9999999">
				Over
			</s:if><s:else>
				<s:property value="#mop.MAXTON"/>
			</s:else>
		</span> 
	</td>
    <td align="center" class="ht">
    	<s:property value="#mop.MONEY"/>
	</td>
    <td align="center"> 
   	 <s:if test="#request.ton<=#mop.MAXTON&&#request.ton>=#mop.MINTON">
   	 	<input type="checkbox" checked="checked"  onclick="return false;"/>
   	 </s:if><s:else>
   	 	<input type="checkbox" onclick="return false;" />
   	 </s:else>
    </td>
  </tr>
  </s:iterator>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tab">
  
  <tr>
    <td class="txt_11_file3pt">　　If the payment is settled in US dollars, the applicable exchange rate will be subject to the official bank exchange rate at the day of settlement.<br />
      　2.	SPECIAL TRANSPORTATION FEE: In case of launch service needed, the subject special transportation fee will be on the account of applicant.<br />
　　SPECIAL TRANSPORTATION FEE:<span class="namber_no">
<input name="textfield2" type="text" class="input" id="textfield2" size="25" value="${special }" />
</span>(￥, RMB).<br /></td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tab">
  <tr>
    <td class="txt_11_file3pt"><strong>　　二、	PAYMENT INSTRUCTION </strong><br />
      　1．	The inspection fee should be paid within 5 days after completion of inspection. If the applicant fails to pay in due period, the pre-paid service policy will be adopted in future nomination.<br />
    　　2．	CCIC’S BANK ACCOUNT INFORMATION<br /></td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tab2">
  <tr>
    <td width="77" align="left">Currency<br />
      <span class="ht">Currency</span></td>
    <td width="238" align="left">RMB (￥)</td>
    <td width="264" align="left">USD (＄)<br /></td>
  </tr>
  <tr>
    <td align="left">A/C holder<br />
    <td align="left">中国检验认证集团检验有限公司</td>
    <td align="left" class="ht">China Certification  &amp; Inspection Group Inspection Co., LTD</td>
  </tr>
  <tr>
    <td align="left">A/C No.<br />
    <td align="left" class="ht">348056018748</td>
    <td align="left" class="ht10">349356018741</td>
  </tr>
  <tr>
    <td align="left">Bank<br />
    <td align="left">中国银行北京市分行东城支行营业部</td>
    <td align="left" class="ht10">BANK OF CHINA BEI JING DONG CHENG <br />
    SUB－BRANCH</td>
  </tr>
  <tr>
    <td align="left" class="ht">SWIFT<br />
    Code</td>
    <td align="left" class="ht">BKCHCNBJ 110</td>
    <td align="left" class="ht10">BKCHCNBJ 110</td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tab">
  <tr>
    <td class="txt_11_file3pt">　　3.NOTICE AFTER PAYMENT: Please attach the English name of vessel to remittance information.Foreign applicants should send bank payment voucher to CCIC head office by fax (86-10-84603176) or by Email (agm@ccic.com); Domestic applicants should send bank payment voucher to CCIC local inspection office either by Email or by fax. <br />
    <strong>　　三、INVOICE TITLE AND MAILING ADDRESS</strong><br/>
	The remitter name must be consistent with the title of invoice which can not be the name of vessel.
	</td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tab2">
  <tr>
    <td width="78" height="30" align="center">Invoice Title</td>
    <td colspan="3" align="left">&nbsp;${invoicetitle }</td>
  </tr>
  <tr>
    <td height="30" align="center">Address</td>
    <td width="352" align="left">&nbsp;${invoiceaddress }</td>
    <td width="68" align="center">P.C.</td>
    <td width="131" align="left">&nbsp;${invoicepost }</td>
  </tr>
  <tr>
    <td height="30" align="center">Addressee</td>
    <td align="left">&nbsp;${invoicer }</td>
    <td align="center">Tel.</td>
    <td align="left">&nbsp;${invoicetel }</td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tab">
  <tr>
    <td height="35" class="txt_11_file3pt">　　Remark: Please submit this form to CCIC with the application form.</td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tab">
  <tr>
    <td width="68%" height="45" align="center" valign="middle"><table width="100%" border="0" cellpadding="0" cellspacing="1">
      <tr>
        <td width="320">Applicant (Singnature and Stamp): </td>
        <td width="263">&nbsp;</td>
      </tr>
      
    </table></td>
    <td align="center" valign="middle"><table width="100%" border="0" cellpadding="0" cellspacing="1">
      <tr>
        <td width="263">Date:</td>
        <td width="295">&nbsp;</td>
      </tr>
      
    </table></td>
  </tr>
</table>

</form>
<div id="print_div"  class="Noprint">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" height="48">
			<tr>
				<td height="38" align="right">
					<input name="button" class="button" type="button"
						onclick="window.focus();window.print()" value="打 印" />
					</td>
					<td width="5%" align="center">&nbsp; 
					
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
