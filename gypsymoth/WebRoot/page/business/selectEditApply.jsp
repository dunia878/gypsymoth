<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ include file="../include/header.jsp"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统</title>
		<%@ include file="../include/head.jsp"%>
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>/js/My97DatePicker/WdatePicker.js"></script>
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>/js/business/validata.js"></script>  
	</head>
	<body>
		<script language="JavaScript" type="text/javascript">
		 
		var max_num = 1;
		var cur_num = 0;
		$("document").ready(function(){
			 
			initOption();
			initSelect();
			
			var view = $("#view").val();
			if(view!=null&&view=="0"){
				$("input[@type='text']").attr("readonly",true);
				$("input[@type='text']").attr("disabled",true);
				$("select").attr("disabled",true); 
			}
			
		});
		
		
	function changecountrtselect(id)
		{
		 	var countryname = "#countrys"+id;
		 	var portname = "#ports"+id;
		 	var destinationcountrysname = "#destinationcountrys"+id;
		 	var selectcontent = $(destinationcountrysname).val();
		 	if(selectcontent=="-1"){
		 		$(countryname).val("");
		 		$(countryname).attr("readonly",true);
		 		$(portname).val("");
		 	}else if(selectcontent=="OTHER"){
		 		$(countryname).val("");
		 		$(countryname).attr("readonly",false);
		 		$(portname).val("");
		 	}else{
		 		$(countryname).val(selectcontent);
		 		$(countryname).attr("readonly",true);
		 		$(portname).val("");
		 	} 
		 	
			 
		}	
		
	function initOption()	//初始化option值
	{
			var countrys = $("#page_countrys").val().split(",");
	     	var ports = $("#page_ports").val().split(","); 
	     	var selects = $("#page_selects").val().split(","); 
	     		
			for(i=0;i<countrys.length;i++){
				
		 		document.getElementsByName("countrys")[i].value = countrys[i];
				document.getElementsByName("ports")[i].value = ports[i]; 
     			
     			var country_select = document.getElementsByName("destinationcountrys")[i];
				for(j=0;j<country_select.length;j++) {
		       		if(selects[i]==country_select.options[j].value){
		     			country_select.options.selectedIndex=j;
		       		}
	      		} 
	      		
			} 
		
	}
		
		
		
		function updatebusiness(){
			if(!validata()){
				return false;
			} 
			if(confirm("您是否确认保存？")) {
			var businessname = $("#businessname").val()
			$("#businessname_hidden").val(businessname);
			document.form1.method="post";
			document.form1.action="selectupdataApply.action?goPage=<s:property value="#request.currpageb" />";
			document.form1.submit();
			}
		}
		function commitbusiness(){
			if(!validata()){
				return false;
			} 		
			var businessname = $("#businessname").val()
			$("#businessname_hidden").val(businessname); 
			document.form1.method="post";
			document.form1.action = "commit.action";
			document.form1.submit();
		}
		
		function commitaftersave(){
			var businessname = $("#businessname").val()
			$("#businessname_hidden").val(businessname); 
			document.form1.method="post";
			document.form1.action = "commitaftersave.action";
			document.form1.submit();
		}
		  

		function modify()
		{
			document.form1.method="post";
			document.form1.action = "getBusinessById.action?view=1"; 
			document.form1.submit();
		}

		function initSelect()
		{
			var proid = $("#select_proid").val();
			var cityid = $("#select_cityid").val(); 
			var portid =  $("#select_portid").val();
			var url1 = "ajaxSelect.action?promaryid="+proid+"&selecttype=0";	//selecttype=0 为省份查询城市
			ajax_selectContent(url1,'city'); 
			 selectItem('city',cityid) ;
			var url2 = "ajaxSelect.action?promaryid="+proid+"&cityid="+cityid+"&selecttype=1";	//selecttype=1 为按城市查询港口
			ajax_selectContent(url2,'portid');  
			selectItem('portid',portid) ;
		}
		 
		 
		function goback(url)
		{
			document.form1.method="post";
			document.form1.action = url;
			document.form1.submit();
		} 
		
		function apply(){
			var id = document.getElementById("id").value;
			var url = "apply.action?id="+id
			window.open(url,"_blank");
		}
		function payment(){
			var id = document.getElementById("id").value;
			var url = "payment.action?id="+id
			window.open(url,"_blank");
		}
		

    </script>
		
		
		
		<table border="0" cellspacing="0" cellpadding="0" width="100%">
			<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
				<tr>
					<td class="list_r">
						&nbsp;
					</td>
					<td valign="top" class="right">
						<div class="title">
							您的当前位置： 首页 &gt;&gt; 业务管理 &gt;&gt;
							<strong>业务查询</strong>
						</div>
						<div>
							<table align="center" cellpadding="12" class="tabedit">
								<tr>
									<td height="300" align="left" valign="top">
										<!--编辑开始-->
										<table width="706" align="center" cellspacing="5">
											<s:form id="form1" name="form1" action="" theme="simple">
												<tr>
												<td height="25" colspan="3" align="left"
													style="border-bottom: dotted 1px #8EC0E8;">
													<s:actionmessage/>
													<span style="color: #FF0000;">*</span>号为必填项
													&nbsp;&nbsp;&nbsp;
												</td>
												</tr>
												
												<tr>
												<td width="180" height="20" colspan="3"> <b><s:text name="基本信息"></s:text> </b> </td>
												</tr>
												
												<tr>
													<td width="180" height="20"> 申请人<span class="mainred" >*</span> </td>
													<td>
														<input name="businessname" id="businessname" type="text" class="input" size="55" maxlength="250" value="<s:property value="business.businessname"/>"
															disabled="disabled" readonly="readonly"  /> 
															<input type="hidden" name="businessname_hidden"  id="businessname_hidden" />	
													</td>
													<td width="272"><span class="mainred" title=""><s:text name="请填写公司名称(英文)"/></span> </td>
												</tr>
												<tr>
													<td>
														船东或租船人名称<span class="mainred" >*</span>
													</td>
													<td>
														<input name="company" id="company" type="text" class="input" size="55" value="<s:property value="business.company"/>" />
													
													</td>
													<td>
														&nbsp;
													</td>
												</tr>
												
												<tr>
													<td>
														船名<span class="mainred" >*</span>
													</td>
													<td>
													<input name="vesselname" id="vesselname" type="text" class="input" maxlength="250"
														size="55" value="<s:property value="business.vesselname"/>" />
													</td>
													<td>
														&nbsp;
													</td>
												</tr>
												
												
												<tr>
												<td height="20">
													船舶类型<span class="mainred" >*</span>
												</td>
												<td>
													<select name="vesseltype" id="vesseltype" class="select" id="vesseltype"
														style="width: 100%;">
														<option value="<s:property value="business.vesseltype"/>">
															<s:property value="business.vesseltype"/>
														</option>
														<option value="Container Vessel">
															Container Vessel
														</option>
														<option value="General Cargo Ship">
															General Cargo Ship
														</option>
														<option value="Bulk Carrier">
															Bulk Carrier
														</option>
														<option value="Multi-purpose vessel">
															Multi-purpose vessel
														</option>
														<option value="Oil Tanker">
															Oil Tanker
														</option>
														<option value="Roll on/Roll off //Vessel">
															Roll on/Roll off //Vessel
														</option>
														<option value="Refrigerated Vessel">
															Refrigerated Vessel
														</option>
														<option value="Vehicle and Passenger Ferry">
															Vehicle and Passenger Ferry
														</option>
														<option value="Heavy-cargo Carrier">
															Heavy-cargo Carrier
														</option>
														<option value="Lighter Aboard Ship--LASH">
															Lighter Aboard Ship--LASH
														</option>
														<option value="Liquefied natural gas carrier--LNC">
															Liquefied natural gas carrier--LNC
														</option>
														<option value="Liquefied petroleum gas carrier--LPC">
															Liquefied petroleum gas carrier--LPC
														</option>
													</select>
												</td>
												<td>
													
												</td>
											</tr>
											
											<tr>
												<td height="20">
													船籍<span class="mainred" >*</span>
												</td>
												<td>
													<!--   <s:select list="#request.nationList" name="registry" cssStyle="width: 100%;"
													 value="business.registry" id="registry" listKey="NATIONNAME" listValue="NATIONENNAME"></s:select>-->
													 <select name="registry" cssStyle="width: 100%;">
													 	<c:forEach items="${nationList}" var="nation">
													 		<option value="${nation.NATIONENNAME}">${nation.NATIONNAME}</option>
													 	</c:forEach>
													 </select>
												</td>
												<td> </td>
											</tr>
												
												<tr>
													<td>
														IMO编号<span class="mainred" >*</span>
													</td>
													<td>
														<input name="imo" id="imo" type="text" maxlength="250" 
															class="input" size="55" value="<s:property value="business.imo"/>" />
													</td>
													<td>
														&nbsp;
													</td>
												</tr>
												<tr>
													<td>
														总吨位<span class="mainred" >*</span>
													</td>
													<td>
														<input name="tonnage" id="tonnage" maxlength="250" 
															type="text" class="input" size="48" value="<s:property value="business.tonnage"/>" />MT
													</td>
													<td>
														&nbsp;
													</td>
												</tr>
												 
												<tr>
												<td height="20">
														<s:text name="目的国及港口1"/><span class="mainred" >*</span>
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
															<input type="text" name="countrys" id="countrys1" size="8" readonly="readonly" value="" />
															<s:text name="目的国港口"/>
															<input name="ports" id="ports1" type="text" maxlength="250" class="input" size="12" />
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
															<input type="text" name="countrys" id="countrys2" size="8" readonly="readonly"  />
															<s:text name="目的国港口"/>
															<input name="ports" id="ports2" type="text" maxlength="250" class="input" size="12" />
														</td>
													</tr>
													</table>
												</td>
											</tr> 
												
															 
												
											<tr> <td width="180" height="20" colspan="3"> &nbsp;  </td> </tr>
											<tr> <td width="180" height="20" colspan="3"> <b><s:text name="检查的申请信息"></s:text> </b>  </td> </tr>
												
												
											<tr>
												<td height="20">  船舶停靠<span class="mainred" >*</span> </td>
												<td>
													<s:select list="#request.promaryList" id="proid" name="proid" cssStyle="width:110px;"
														value="#request.select_proid" listKey="PROID" listValue="PRONAME" onchange="javascript:changepromary();" ></s:select>省
													<select id="city" name="city" style="width: 80px;" onchange="javascript:changcity();" > 
														<option value='-1'>- 请选择 -</option> 
													</select>市
													<select name="portid" id="portid" >
														<option value='-1'>- 请选择 -</option>
													</select>
												</td>
												<td> </td>
											</tr>
											
											<tr>
												<td height="20">
													泊位、锚位
												</td>

												<td>
													<input type="text" name="berth" maxlength="20" value="<s:property value="business.berth"/>"/>
												</td>
												<td>
													
												</td>
											</tr>	
												
											<tr>
												<td height="20">
													计划入港日期<span class="mainred" >*</span>
												</td>
												<td>
													<input class="Wdate" type="text" id="plandatein"
														name="plandatein" onfocus="WdatePicker()" value="<s:date name="business.plandatein" format="yyyy-MM-dd" /> " />
												</td>

												<td>
													
												</td>
											</tr>
											
											<tr>
												<td height="20">
													计划离港日期<span class="mainred" >*</span>
												</td>

												<td>
													<input class="Wdate" type="text" id="plandateout"
														name="plandateout" onfocus="WdatePicker()" value="<s:date name="business.plandateout" format="yyyy-MM-dd" /> " />
												</td> 
												<td>
													
												</td>
											</tr>
											
											
											<tr>
												<td height="20">
													<p>
														合适的检查日期<span class="mainred" >*</span>
													</p>
												</td>
												<td>
													<input class="Wdate" type="text" id="checkdate"
														name="checkdate" onfocus="WdatePicker()" value="<s:date name="business.checkdate" format="yyyy-MM-dd" /> " />
												</td>
												<td>
													
												</td>
											</tr>
												
											<tr>
												<td height="20"> <s:text name="特别交通费"/> </td>
												<td> <input name="special" id="special" 	value="<s:property value="business.special"/>"
												maxlength="32" type="text" class="input" size="55" /> </td>
												<td> &nbsp; </td>
											</tr> 	
												
											<tr>
												<td height="20"> 联系人姓名<span class="mainred" >*</span> 	</td>
												<td>
													<input name="linkmanname" id="linkmanname" type="text" class="input"
														size="55" maxlength="250" value="<s:property value="business.linkmanname"/>" />
												</td>
												<td> </td>
											</tr>
												
											<tr>
												<td> 联系人电话 </td>
												<td> 
												<input name="phone_begin" id="phone_begin" maxlength="8" type="text" class="input" size="5" value="${phone_begin }" />
													&nbsp;-&nbsp;
												<input name="phone_end" id="phone_end" maxlength="10" type="text" class="input" size="20" value="${phone_end }" />
												<input name="phone" type="hidden" id="phone" name="phone" value="<s:property value="business.phone"/>" />
												</td> <td> &nbsp; </td>
											</tr>
												
											<tr>
												<td height="20"> <s:text name="联系人手机"/> </td>
												<td>
													<input name="linkphone" id="linkphone" maxlength="20" value="<s:property value="business.linkphone"/>"
													  type="text" class="input" size="55" />
												</td> <td> </td>
											</tr>
												
												
											<tr>
												<td> 联系人传真 </td>
												<td> <input name="fax" id="fax" maxlength="20" type="text" 
														value="<s:property value="business.fax"/>" class="input" size="55" />
												</td> <td> &nbsp; </td>
											</tr>
											
											<tr> <td> 电子邮件 </td>
												<td> <input name="email" id="email" maxlength="32" type="text" 
														value="<s:property value="business.email"/>" class="input" size="55" />
												</td> <td> &nbsp; 	</td>
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
												<td height="20"> <s:text name="发票抬头"/> <span class="mainred" >*</span></td>
												<td> <input name="invoicetitle" id="invoicetitle" value="<s:property value="business.invoicetitle"/>"
												maxlength="32" type="text" class="input" size="55" /> </td>
												<td> &nbsp; </td>
											</tr>
											<tr>
												<td height="20"> <s:text name="邮寄地址"/> <span class="mainred" >*</span></td>
												<td> <input name="invoiceaddress" id="invoiceaddress" value="<s:property value="business.invoiceaddress"/>"
												maxlength="32" type="text" class="input" size="55" /> </td>
												<td> &nbsp; </td>
											</tr>
											<tr>
												<td height="20"> <s:text name="发票邮编"/><span class="mainred" >*</span> </td>	
												<td> <input name="invoicepost" id="invoicepost" maxlength="32" value="<s:property value="business.invoicepost"/>"
												type="text" class="input" size="55" /> </td>
												<td> &nbsp; </td>
											</tr>
											<tr>
												<td height="20"> <s:text name="发票收件人"/> <span class="mainred" >*</span></td>
												<td> <input name="invoicer" id="invoicer" value="<s:property value="business.invoicer"/>"
												maxlength="32" type="text" class="input" size="55" /> </td>
												<td> &nbsp; </td>
											</tr>
											<tr>
												<td height="20"> <s:text name="收件人联系电话"/><span class="mainred" >*</span> </td>
												<td> 
												<input name="invoicetel_begin" id="invoicetel_begin" maxlength="8" type="text" class="input" size="5" value="${invoicetel_begin }" />
													&nbsp;-&nbsp;
												<input name="invoicetel_end" id="invoicetel_end" maxlength="10" type="text" class="input" size="20" value="${invoicetel_end }" />
												
												<input name="invoicetel" type="hidden" id="invoicetel" value="<s:property value="business.invoicetel"/>"
												maxlength="32" type="text" class="input" size="55" /> </td>
												<td> &nbsp; </td>
											</tr>
												
												
											<tr>
												<td height="20">
													检查机构<span class="mainred" >*</span>
												</td>
												<td>
													<input name="certification_input" type="text" class="input"
														disabled="disabled" value="CCIC INSPECTION CO.,LTD"
														size="55" />
													<input type="hidden" name="certification" value="<s:property value="business.certification"/>" />	
													<input  type="hidden" name="id" id="id" value="<s:property value="business.id"/>" /> 
													<input  type="hidden" name="accountid" value="<s:property value="business.accountid"/>" />
													
												</td> 
												<td>
													&nbsp;
												</td>
											</tr>
											
												<tr>
													<td height="38" align="center" colspan="3"> 

																<input name="input2" class="button" type="button" onclick="updatebusiness();" value="修改" />
																<input name="input" class="button" type="button" value="返 回"
																onclick="javascript:goback('getSelectByBusinessid.action?businessid=<s:property value='business.id'/>&currPagea=<s:property value="#request.currpageb" />')" />
													</td>
													 
												</tr>


		<input	type="hidden" name="page_countrys" id="page_countrys" value="${page_countrys}"	/>
		<input	type="hidden" name="page_ports" id="page_ports" value="${page_ports}"	/>
		<input	type="hidden" name="page_selects" id="page_selects" value="${page_selects}"	/>
		<input type="hidden" name="select_proid" id="select_proid" value="${select_proid }"/> 
		<input type="hidden" name="select_cityid" id="select_cityid" value="${select_cityid }"/> 
		<input type="hidden" name="select_portid" id="select_portid" value="${select_portid }"/>
		<input type="hidden" name="appno" id="appno" value="<s:property value="business.appno"/>"/>
		<input type="hidden" name="appdate" id="appdate" value="<s:property value="business.appdate"/>"/>
		<input type="hidden" name="vesselno" id="vesselno" value="<s:property value="business.vesselno"/>"/>
		<input type="hidden" name="remark" id="remark" value="<s:property value="business.remark"/>"/>
		<input type="hidden" name="appname" id="appname" value="<s:property value="business.appname"/>"/>
		<input type="hidden" name="qualified" id="qualified" value="<s:property value="business.qualified"/>"/>
		<input type="hidden" name="certid" id="certid" value="<s:property value="business.certid"/>"/>
		<input type="hidden" name="stampid" id="stampid" value="<s:property value="business.stampid"/>"/>
		<input type="hidden" name="businessstate" id="businessstate" value="<s:property value="business.businessstate"/>"/>
		<input type="hidden" name="inspection1" id="inspection1" value="${inspection_input }" />
		

		<input type="hidden" name="view" id="view" value="${view }"/> 

											</s:form>
										</table>

									</td>
									<!-- 主内容结束 -->
								</tr>
				</table>
				</div>
				</td>
				</tr>
				</table>
				</table>
				
				
				
							<!--内容结束-->
	</body>
</html>
