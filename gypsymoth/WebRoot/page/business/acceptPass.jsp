<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统</title>
		<%@ include file="../include/head.jsp"%>
		
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>/js/business/validata.js"></script>  
	</head>
	<body>
	 <script language="JavaScript" type="text/javascript">
		var max_num = 1;
		var cur_num = 0;
		$("document").ready(function(){
			initOption(); 
			$("select").attr('disabled','disabled');
			$("input").attr("readonly","readonly"); 
		}); 
	 
function acceptpass(){
if (confirm("确认提交？")) {
document.getElementById("pass_state").value="1";
document.form1.method="post";
document.form1.action="acceptpass.action"; 
document.form1.submit();
}
else 
           return false;
}
function nopass(){
var remark = document.getElementById("remark").value;
if(remark==null||remark==""){
	alert('业务单不通过需要在审核意见处输入不通过理由。');
	document.getElementById("remark").focus();
	return false;
} 
if(remark.length>250){ 
	alert('受理建议不能大于250个字。');
	document.getElementById("remark").focus();
	return false;
} 
document.getElementById("pass_state").value="0";
document.form1.method="post";
document.form1.action = "acceptpass.action";
document.form1.submit();
}


	</script>
		
	<table border="0" cellspacing="0" cellpadding="0" width="100%">
	<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
  <tr>
    <td class="list_r">&nbsp;</td>
    <td valign="top" class="right"><div class="title">您的当前位置： 首页 &gt;&gt; 业务管理 &gt;&gt; <strong>业务受理</strong></div>
      <div>
        <table align="center" cellpadding="12" class="tabedit">
          <tr>
            <td height="300" align="left" valign="top"><!--编辑开始-->
             <s:form id="form1" name="form1" action="" theme="simple" >
                <input	type="hidden" name="page_countrys" id="page_countrys" value="${page_countrys}"	/>
				<input	type="hidden" name="page_ports" id="page_ports" value="${page_ports}"	/>
				<input	type="hidden" name="page_selects" id="page_selects" value="${page_selects}"	/>
				<input 	type="hidden" name="pass_state"  id="pass_state"/>
             
              <s:iterator value="#request.list" status="stat">
              <table width="706" align="center" cellspacing="5">
              
             <tr>
				<td width="180" height="20" colspan="3">
					<b><s:text name="基本信息"></s:text> </b> 
				</td>
			</tr>
           
                <tr>
                  <td width="180" height="20">申请人</td>
                  <td width="332">
                  <s:textfield readonly="true" disabled="true" name="businessname"  size="55" ></s:textfield>  
                 </td>
                  <td width="272">&nbsp;</td>
                </tr>
                <tr>
                  <td>船东或租船人名称</td>
                  <td>
                    <s:textfield readonly="true" disabled="true" name="company"  size="55" />
                  </td>
                  <td>&nbsp;</td>
	               </tr>
	               
	               
	                <tr>
                  <td>船名</td>
                  <td>
                    <s:textfield readonly="true" disabled="true" name="vesselname"  size="55" />
                  </td>
                  <td>&nbsp;</td>
                </tr>
                 <tr>
                  <td>类型</td>
                  <td>
                    <s:textfield readonly="true" disabled="true" name="vesseltype"  size="55" />
                  </td>
                  <td>&nbsp;</td>
                </tr>
                 <tr>
                  <td>船籍</td>
                  <td>
                    <s:textfield readonly="true" disabled="true" name="registry"  size="55" />
                  </td>
                  <td>&nbsp;</td>
                </tr>
                 <tr>
                  <td>IMO编号</td>
                  <td>
                    <s:textfield readonly="true" disabled="true" name="imo"  size="55" />
                  </td>
                  <td>&nbsp;</td>
                </tr>
                 <tr>
                  <td>总吨位</td>
                  <td>
                    <s:textfield readonly="true" disabled="true" name="tonnage"  size="50" />&nbsp;MT
                  </td>
                  <td>&nbsp;</td>
                </tr>
	               
	          			<tr>
							<td height="20">
									<s:text name="目的国及港口1"/>
								</td>
								<td colspan="2">
								<table width="100%" id="append_tab">
								<tr>	
									<td>
										<select name="destinationcountrys" id="destinationcountrys1" onchange="changecountrtselect(1);">
											<option value="-1"><s:text name="请选择"/></option>
											<option value="USA">USA</option>
											<option value='CANADA'>CANADA</option>
											<option value='MEXICO'>MEXICO</option>
											<option value='OTHER'>OTHER</option>
										</select>
										<input type="text" name="countrys" id="countrys1" size="8" disabled="disabled" value="" />
										<s:text name="目的国港口"/>
										<input name="ports" id="ports1" type="text" maxlength="250" disabled="disabled" class="input" size="12" />
									</td>
								</tr>
								</table>
							</td>
						</tr>
						
						<tr>
							<td height="20">
									<s:text name="目的国及港口2"/>
								</td>
								<td colspan="2">
								<table width="100%" id="append_tab">
								<tr>	
									<td>
										<select name="destinationcountrys" id="destinationcountrys2" onchange="changecountrtselect(2);">
											<option value="-1"><s:text name="请选择"/></option>
											<option value="USA">USA</option>
											<option value='CANADA'>CANADA</option>
											<option value='MEXICO'>MEXICO</option>
											<option value='OTHER'>OTHER</option>
										</select>
										<input type="text" name="countrys" id="countrys2" size="8" disabled="disabled"  />
										<s:text name="目的国港口"/>
										<input name="ports" id="ports2" type="text" maxlength="250" disabled="disabled" class="input" size="12" />
									</td>
								</tr>
								</table>
							</td>
						</tr> 
                
                
                <tr>
					<td width="180" height="20" colspan="3">
						&nbsp; 
					</td>
				</tr>
				<tr>
					<td width="180" height="20" colspan="3">
						<b><s:text name="检查的申请信息"></s:text> </b> 
					</td>
				</tr>
				
				<tr>
					<td height="20">
						船舶停靠
					</td>

					<td>
						<select name="proid" id="proid" disabled="disabled">
							<option>${proname }</option>
						</select>省
						<select name="city" id="city" disabled="disabled">
							<option>${cityname } </option>
						</select>市
						<select name="portid" id="portid" disabled="disabled" > 
							<option>${portname } </option>
						</select> 	
					</td>
					<td>
						
					</td>
				</tr>
				
					<tr>
						<td height="20">
							泊位、锚位
						</td>

						<td>
							<s:textfield readonly="true" disabled="true" name="berth" size="55"></s:textfield>
						</td>
						<td>
							
						</td>
					</tr>
                
                 <tr>
                  <td>计划入港日期</td>
                  <td>
                  	<input type="text" size="55" name="plandatein" value="<s:date name="plandatein" format="yyyy-MM-dd" />"
                  		id="plandatein" disabled="disabled" />
                  </td>
                  <td>&nbsp;</td>
                </tr>
                 <tr>
                  <td>计划离港日期</td>
                  <td>
                  	<input type="text" size="55" name="plandateout" value="<s:date name="plandateout" format="yyyy-MM-dd" />"
                  		id="plandatein" disabled="disabled" />
                  </td>
                  <td>&nbsp;</td>
                </tr>
                
                
                 <tr>
                  <td>合适的检查日期</td>
                  <td>
                  	<input type="text" size="55" name="checkdate" value="<s:date name="checkdate" format="yyyy-MM-dd" />"
                  		id="plandatein" disabled="disabled" />
                  </td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
					<td height="20"> <s:text name="特别交通费"/> </td>
					<td> <s:textfield readonly="true" disabled="true" name="special"  size="50" />&nbsp;RMB </td>
					<td> &nbsp; </td>
				</tr> 
                 <tr>
                  <td>联系人姓名</td>
                  <td>
                    <s:textfield readonly="true" disabled="true" name="company"  size="55" />
                  </td>
                  <td>&nbsp;</td>
                </tr>
                 <tr>
                  <td>联系人联系电话</td>
                  <td>
                    <s:textfield readonly="true" disabled="true" name="phone"  size="55" />
                  </td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>联系人传真</td>
                  <td>
                    <s:textfield readonly="true" disabled="true" name="fax"  size="55" />
                  </td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>电子邮箱</td>
                  <td>
                    <s:textfield readonly="true" disabled="true" name="email"  size="55" />
                  </td>
                  <td>&nbsp;</td>
                </tr>
                
                
                
				<tr>
					<td width="180" height="20" colspan="3">
						&nbsp; 
					</td>
				</tr>
				<tr>
					<td width="180" height="20" colspan="3">
						<b><s:text name="发票抬头及邮寄地址"></s:text> </b> 
					</td> 
				</tr>
                
                  <tr>
					<td height="20"> <s:text name="发票抬头"/> </td>
					<td> <s:textfield readonly="true" disabled="true" name="invoicetitle"  size="55" /> </td>
					<td> &nbsp; </td>
				</tr>
				<tr>
					<td height="20"> <s:text name="邮寄地址"/> </td>
					<td>  <s:textfield readonly="true" disabled="true" name="invoiceaddress"  size="55" /> </td>
					<td> &nbsp; </td>
				</tr>
				<tr>
					<td height="20"> <s:text name="发票邮编"/> </td>
					<td> <s:textfield readonly="true" disabled="true" name="invoicepost"  size="55" /> </td>
					<td> &nbsp; </td>
				</tr>
				<tr>
					<td height="20"> <s:text name="发票收件人"/> </td>
					<td>  <s:textfield readonly="true" disabled="true" name="invoicer"  size="55" />  </td>
					<td> &nbsp; </td>
				</tr>
				<tr>
					<td height="20"> <s:text name="收件人联系电话"/> </td>
					<td> <s:textfield readonly="true" disabled="true" name="invoicetel"  size="55" />   </td>
					<td> &nbsp; </td>
				</tr>
                
                <tr>
                  <td height="20">检查机构</td>
                  <td><input name="certification" disabled="disabled" type="text" class="input" disabled="disabled" value="CCIC INSPECTION CO.,LTD" size="55" />
                  	<s:hidden name="id"></s:hidden> 
                  	</td> 
                  <td>&nbsp;</td>
                </tr>
                
                <s:if test="#request.business_accountname!=null&&#request.business_accountname!=''"> 
					<tr>
					<td width="180" height="20" colspan="3">
						&nbsp; 
					</td>
					</tr>
					<tr>
						<td width="180" height="20">
							<b><s:text name="申请人用户名"/></b> 
							</td>
							<td>&nbsp;${business_accountname }</td>
							<td></td>
						
					</tr>	
					
					<tr>
						<td width="180" height="20" colspan="3">
							
						</td>
					</tr>	
				</s:if> 
                
                 <tr>
                  <td height="20">审核意见</td>
                  <td>
						<textarea rows="5" cols="40" id="remark" name="remark" ></textarea>
                  	</td> 
                  <td>&nbsp;</td>
                </tr>
               <tr>
                  <td height="38" align="left"></td>
                  <td height="38" align="left">
                  <input name="input4" class="button" type="button" onclick="return acceptpass();"   value="通 过" />
                  <input name="input2" class="button" type="submit" onclick="return nopass();" value="不通过" />
                  <input  name="input" class="button" type="reset" value="返 回"  onclick="history.back(-1)" /></td>
                  <td align="left">&nbsp;</td>
                </tr>
             
              
					</table>
				 
				</s:iterator>
				</s:form>
				</td>
				<!-- 主内容结束 -->
			</tr>
		</table>
		<!--内容结束--> 
		
	</body>
</html>
