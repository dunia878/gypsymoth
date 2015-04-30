<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统</title> 
		<link href="<%=basePath %>/style/printer.css"
			type="text/css" rel="stylesheet" />
			
	<style media="print">
         .Noprint { DISPLAY: none }
   </style>			
<script language="javascript" type="text/javascript">
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
			
	</head>

	<body>

		<table width="1002" border="0" cellpadding="2" cellspacing="3" class="tab"  style="width:690px;">
			  <tr>
			    <td width="82%" height="92" valign="bottom">&nbsp;</td>
			    <td width="18%" valign="bottom" class="namber_no"> ${appno_view }  </td>
			  </tr>
			  <tr>
			    <td height="40">&nbsp;</td>
			    <td valign="top" class="namber_date" style="padding-top:4px;">${checkdate_view }</td>
			  </tr>
			</table>
			<table width="100%" border="0" cellpadding="5" cellspacing="0" class="tab">
			  <tr>
			    <td align="center" class="title">船舶无亚洲型舞毒蛾检查证书</td>
			  </tr>
			  <tr>
			    <td align="center" class="title_en">CERTIFICATE OF VESSEL INSPECTION FOR FREEDOM FROM <br />
			    THE ASIAN GYPSY MOTH IN CHINA<br /></td>
			  </tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tab">
			  <tr>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
			      <tr>
			        <td>兹证明如下船舶已经<span class="ht">CCIC</span>检查，被认为无亚洲型舞毒蛾<span class="ht">（AGM）</span>。<br />
			            <span class="ht">This is to certify that the vessel described below has been inspected by CCIC and is considered to be free from AGM on board at the time of inspection.</span></td>
			      </tr>
			    </table></td>
			  </tr>
			  <tr>
			    <td>&nbsp;</td>
			  </tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tab">
			  
			  <tr>
			    <td width="43" align="center">1.</td>
			    <td width="223"> 申请人名称</td>
			    <td width="10">&nbsp;</td>
			    <td width="344">&nbsp;</td>
			  </tr>
			  <tr>
			    <td align="center">&nbsp;</td>
			    <td valign="top" class="ht11">Name of applicant</td>
			    <td valign="top">:</td>
			    <td valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
			      <tr>
			        <td class="ht10">&nbsp;${businessname_view }</td>
			      </tr>
			      <tr>
			        <td height="1" bgcolor="#000000"></td>
			      </tr>
			    </table>    </td>
			  </tr>
			  <tr>
			    <td align="center">2.</td>
			    <td>船东或租船人名称</td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td align="center">&nbsp;</td>
			    <td valign="top" class="ht11">Name of owner or charterer</td>
			    <td valign="top">:</td>
			    <td valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
			        <tr>
			          <td class="ht11">&nbsp;${company_view }</td>
			        </tr>
			        <tr>
			          <td height="1" bgcolor="#000000"></td>
			        </tr>
			      </table></td>
			  </tr>
			  <tr>
			    <td align="center">3.</td>
			    <td>船名及类型</td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td align="center">&nbsp;</td>
			    <td valign="top" class="ht11">Name and type of vessel</td>
			    <td valign="top">:</td>
			    <td valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
			      <tr>
			        <td class="ht10">&nbsp;${vesselnameandtype_view }</td>
			      </tr>
			      <tr>
			        <td height="1" bgcolor="#000000"></td>
			      </tr>
			    </table>    </td>
			  </tr>
			  <tr>
			    <td align="center">4.</td>
			    <td> 船籍、IMO编号及总吨位</td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td align="center">&nbsp;</td>
			    <td class="ht11">Nationality, IMO number<br /></td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td align="center">&nbsp;</td>
			    <td valign="top" class="ht11">and gross tonnage of vessel</td>
			    <td valign="top">:</td>
			    <td valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
			        <tr>
			          <td class="ht11">&nbsp;${reg_imo_tonnage_view }</td>
			        </tr>
			        <tr>
			          <td height="1" bgcolor="#000000"></td>
			        </tr>
			      </table></td>
			  </tr>
			
			   <tr>
			    <td align="center">5.</td>
			    <td>目的国</td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td align="center">&nbsp;</td>
			    <td valign="top" class="ht11">Country of destination</td>
			    <td valign="top">:</td>
			    <td valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
			        <tr>
			          <td class="ht10"> &nbsp;${countrysandports_input }</td>
			        </tr>
			        <tr>
			          <td height="1" bgcolor="#000000"></td>
			        </tr>
			      </table></td>
			  </tr>
			  <tr>
			    <td align="center">6.</td>
			    <td>检查港</td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td align="center">&nbsp;</td>
			    <td valign="top" class="ht11">Port of inspection</td>
			    <td valign="top">:</td>
			    <td valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
			        <tr>
			          <td class="ht10"> &nbsp;${breth_inpit }</td>
			        </tr>
			        <tr>
			          <td height="1" bgcolor="#000000"></td>
			        </tr>
			      </table></td>
			  </tr>
			   <tr>
			    <td align="center">7.</td>
			    <td> 计划离港日期</td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td align="center">&nbsp;</td>
			    <td valign="top" class="ht11">The estimated date of departure</td>
			    <td valign="top">:</td>
			    <td valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
			        <tr>
			          <td class="ht11">&nbsp;${plandateout_view }</td>
			        </tr>
			        <tr>
			          <td height="1" bgcolor="#000000"></td>
			        </tr>
			      </table></td>
			  </tr>
			  <tr>
			    <td align="center">8.</td>
			    <td>检查日期</td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td> 
			  </tr>
			  <tr>
			    <td align="center">&nbsp;</td>
			    <td valign="top" class="ht11">Date of inspection</td>
			    <td valign="top">:</td>
			    <td valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
			        <tr>
			          <td class="ht11">&nbsp;${checkdate_view }</td>
			        </tr>
			        <tr>
			          <td height="1" bgcolor="#000000"></td>
			        </tr>
			    </table></td>
			  </tr>
			  <tr>
			    <td align="center">9.</td>
			    <td>检查方法</td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td align="center">&nbsp;</td>
			    <td valign="top" class="ht11">Method of inspection</td>
			    <td valign="top">:</td>
			    <td valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
			        <tr>
			          <td class="ht11">&nbsp;${inspection_input }</td>
			        </tr>
			        <tr>
			          <td height="1" bgcolor="#000000"></td> 
			        </tr>
			      </table></td>
			  </tr>
			  <tr>
			    <td align="center">10.</td>
			    <td>授权签字人</td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td align="center">&nbsp;</td>
			    <td valign="top" class="ht11">Name of authorized signature</td>
			    <td valign="top">:</td>
			    <td valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
			        <tr>
			          <td class="ht11">&nbsp;${auth_input }</td>
			        </tr>
			        <tr>
			          <td height="1" bgcolor="#000000"></td> 
			        </tr>
			      </table></td>
			  </tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tab">
			  <tr>
			    <td height="36" align="center" valign="bottom"><span class="ht11">*　　　　　          *　　　　　          </span><span class="ht">END</span><span class="ht11">　　　　　          *　　　　　          *</span></td>
			  </tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tab">
			  <tr>
			    <td height="204" align="center" valign="bottom">
			    <p align="right">&nbsp;&nbsp;&nbsp;</p>
			    <p align="center" class="namber_date">Page 1 of 1</p></td>
			  </tr>
			</table>
	 <div id="print_div" class="Noprint">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" height="48">
			<tr>
				<td height="38" align="right">
					<input name="button" class="button" type="button"
						onclick="window.focus();window.print()" value="打 印" />
				</td>
				<td width="5%" align="center"> &nbsp; </td>
				<td height="38" align="left">	
					<input name="input" class="button" type="button" value="关 闭"
						onclick="window.close();" /> 
				</td>
			</tr>
		</table>
	</div>
	
	</body>
</html>
