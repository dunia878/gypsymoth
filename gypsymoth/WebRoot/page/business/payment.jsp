<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
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
    <td class="txt_11_file3pt"><strong>　　一、	收费项目及标准</strong> <br />
    　　1.	船舶舞毒蛾检查费：船舶舞毒蛾检查费包含咨询服务、检查受理、登轮检查、舞毒蛾的处理、证书的签发、信息化支持等费用，根据船舶总吨位，收费标准如下：<br /></td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tab2">
  <tr>
    <td height="27" align="center">船舶总吨位（吨）</td>
    <td align="center">收费（元，人民币）</td>
    <td align="center">对应选择</td>
  </tr>
  <s:iterator value="#request.tonlist" id="mop">
  <tr>
    <td height="22" align="center">
    	<span class="ht">
    		<s:property value="#mop.MINTON"/>-
    		<s:if test="#mop.MAXTON>=9999999">
				以上
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
    <td class="txt_11_file3pt">　　 如果申请人以美元付款，则以付款当日汇率基准价结算。<br />
      　　2.	特别交通费：因为工作需要而实际发生的特别交通费。比如船舶停泊在锚地或海洋石油作业区的，需要租用水上特别交通工具。<br />
　　特别交通费收费数额：<span class="namber_no">
<input name="textfield2" type="text" class="input" id="textfield2" size="25" value="${special }" />
</span>(元，人民币)。<br /></td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tab">
  <tr>
    <td class="txt_11_file3pt"><strong>　　二、	付款须知 </strong><br />
      　　1．	申请人需要在<span class="ht">CCIC</span>实施登轮检查完成后的<span class="ht">5</span>个工作日之内，足额支付所有款项。特殊情况下，如申请人有不良信用记录时，申请人则须在<span class="ht">CCIC</span>登轮检查前足额付款；信用良好的，经批准后可以月度付款。<br />
    　　2．	收款方银行账户信息：<br /></td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tab2">
  <tr>
    <td width="77" align="left">区　分<br />
      <span class="ht">Currency</span></td>
    <td width="238" align="left">人民币帐户(<span class="ht10">RMB</span>)</td>
    <td width="264" align="left">美元账户（<span class="ht10">USD</span>）<br /></td>
  </tr>
  <tr>
    <td align="left">帐户名<br />
      <span class="ht10">A/C holder</span></td>
    <td align="left">中国检验认证集团检验有限公司</td>
    <td align="left" class="ht">China Certification  &amp; Inspection Group Inspection Co., LTD</td>
  </tr>
  <tr>
    <td align="left">帐　号<br />
      <span class="ht10">A/C No.</span></td>
    <td align="left" class="ht">348056018748</td>
    <td align="left" class="ht10">349356018741</td>
  </tr>
  <tr>
    <td align="left">开户行<br />
      <span class="ht">Bank</span></td>
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
    <td class="txt_11_file3pt">　　3．付款告知：<b><font style="font-size: 12px;">汇款附言请注明船舶的英文名称，付款后，国内申请人请尽快将银行付款凭证传真或者电邮至登轮检查的公司，国外申请人请尽快将银行付款凭证传真至：<span class="ht">010-84603176</span>，或电邮至：<span class="ht"><u>agm@ccic.com</u></span>，以便我们及时为您开具发票 。</font></b><br />
    <strong>　　三、发票抬头及邮寄地址<font color="red">(汇款人名称必须与发票抬头一致)</font></strong></td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tab2">
  <tr>
    <td width="78" height="30" align="center">发票抬头</td>
    <td colspan="3" align="left">&nbsp;${invoicetitle }</td>
  </tr>
  <tr>
    <td height="30" align="center">邮寄地址</td>
    <td width="352" align="left">&nbsp;${invoiceaddress }</td>
    <td width="68" align="center">邮  编</td>
    <td width="131" align="left">&nbsp;${invoicepost }</td>
  </tr>
  <tr>
    <td height="30" align="center">收 件 人</td>
    <td align="left">&nbsp;${invoicer }</td>
    <td align="center">电  话</td>
    <td align="left">&nbsp;${invoicetel }</td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tab">
  <tr>
    <td height="35" class="txt_11_file3pt">　　注：本须知要和《船舶舞毒蛾检查申请单》同时提交。</td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tab">
  <tr>
    <td width="68%" height="45" align="center" valign="middle"><table width="100%" border="0" cellpadding="0" cellspacing="1">
      <tr>
        <td width="263">申请人（<em>签字及公章</em>）：</td>
        <td width="295">&nbsp;</td>
      </tr>
      
    </table></td>
    <td align="center" valign="middle"><table width="100%" border="0" cellpadding="0" cellspacing="1">
      <tr>
        <td width="263">申请日期：</td>
        <td width="295">&nbsp;</td>
      </tr>
      
    </table></td>
  </tr>
</table>


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
