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
			src="<%=basePath%>/js/My97DatePicker/WdatePicker.js"></script>
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>/js/business/validata.js"></script>  
	</head>
	<body>&nbsp;<script language="JavaScript" type="text/javascript">
		 var date = new Date();
		var datestamp = date.getTime();
		$("document").ready(function(){
			 
			initOption();
			initSelect();
			var registry_hidden = $("#registry_hidden").val();
			selectItem('registry',registry_hidden); 
			var businessname_hidden = $("#businessname_hidden").val();
			selectItem('businessname',businessname_hidden);
			
			var view = $("#view").val();
			if(view!=null&&view=="0"){
				$("input[@type='text']").attr("readonly",true);
				$("input[@type='text']").attr("disabled",true);
				$("select").attr("disabled",true); 
			}
			
		});
		
		
		//选中ID为obj的select控件的值为value的内容 如: 'city',1 
	function selectItem(obj,value)
		{
		  var objSelect = document.getElementById(obj);
		  for(var i=0;i<objSelect.options.length;i++){
	         if(objSelect.options[i].value == value)
	         {
	         	 setTimeout(function(){objSelect.options[i].selected = true;},1);
	             isExit = true;
	             break; 
	         } 
	  		 }    
		}
		
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
		
		
		
function changepromary()
{
	$("#city").html("<option value='-1'>- <s:text name='请选择'/> -</option>"); 
	$("#portid").html("<option value='-1'>- <s:text name='请选择'/> -</option>");
	var proid = $("#proid").val(); 
	var url = "ajaxSelect.action?promaryid="+proid+"&selecttype=0&tid="+datestamp;	//selecttype=0 为省份查询城市
	ajax_selectContent(url,'city'); 
	$("#city").attr("value","-1");
}

function changcity()
{
	$("#portid").html("<option value='-1'>- <s:text name='请选择'/> -</option>");
	var proid = $("#proid").val(); 
	var cityid = $("#city").val();
	var url = "ajaxSelect.action?promaryid="+proid+"&cityid="+cityid+"&selecttype=1&tid="+datestamp;	//selecttype=1 为按城市查询港口
	ajax_selectContent(url,'portid');
	$("#city").attr("portid","-1");
}

		
		function updatebusiness(){
			if(!validata()){
				return false;
			} 
			if(confirm("您确认保存?")){
				var businessname = $("#businessname").val()
				$("#businessname_hidden").val(businessname);
				document.form1.method="post";
				document.form1.action="updataApply.action?goPage=<s:property value="#request.currPagec"/>";
				document.form1.submit();
			}else
			return false;
		}
		
		function commitbusiness(){
			if(!validata()){
				return false;
			} 
			if (confirm("确认提交？")) {
			var businessname = $("#businessname").val()
			$("#businessname_hidden").val(businessname); 
			document.form1.method="post";
			document.form1.action = "commit.action";
			document.form1.submit();
			}
			else 
           return false;
		}
		
		function commitaftersave(){
		if (confirm("确认提交？")) {
			var businessname = $("#businessname").val()
			$("#businessname_hidden").val(businessname); 
			document.form1.method="post";
			document.form1.action = "commitaftersave.action";
			document.form1.submit();
			}
			else 
           return false;
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
			var url1 = "ajaxSelect.action?promaryid="+proid+"&selecttype=0&tid"+datestamp;	//selecttype=0 为省份查询城市
			ajax_selectContent(url1,'city'); 
			selectItem('city',cityid) ;
			var url2 = "ajaxSelect.action?promaryid="+proid+"&cityid="+cityid+"&selecttype=1&tid="+datestamp;	//selecttype=1 为按城市查询港口
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
		
		
function validata()
{
	var businessname = document.getElementById("businessname").value;
	var company = document.getElementById("company").value;
	var vesselname = document.getElementById("vesselname").value;
	var vesseltype = document.getElementById("vesseltype").value;
	var registry = document.getElementById("registry").value;
	var imo = document.getElementById("imo").value;
	var tonnage = document.getElementById("tonnage").value; 
	var destinationcountrys = document.getElementsByName("destinationcountrys")[0].value;
	var countrys = document.getElementsByName("countrys")[0].value;
	var ports = document.getElementsByName("ports")[0].value;
	
	var destinationcountrys_other = document.getElementsByName("destinationcountrys")[1].value;
	var countrys_other = document.getElementsByName("countrys")[1].value;
	var ports_other = document.getElementsByName("ports")[1].value;
	
	
	var proid = document.getElementById("proid").value;
	var city = document.getElementById("city").value;
	var portid = document.getElementById("portid").value;
	var plandatein = document.getElementById("plandatein").value;
	var plandateout = document.getElementById("plandateout").value;
	var checkdate = document.getElementById("checkdate").value;
	var special = document.getElementById("special").value;
	var linkmanname = document.getElementById("linkmanname").value;
 	var phone_begin = document.getElementById("phone_begin").value;
 	var phone_end = document.getElementById("phone_end").value;
 	var linkphone = document.getElementById("linkphone").value;
 	
 	var invoicetitle = document.getElementById("invoicetitle").value;
 	var invoiceaddress = document.getElementById("invoiceaddress").value;
 	var invoicepost = document.getElementById("invoicepost").value;
 	var invoicer = document.getElementById("invoicer").value;
 	var invoicetel_begin =  document.getElementById("invoicetel_begin").value;
	var invoicetel_end =  document.getElementById("invoicetel_end").value;
	 
	
	var reg_tonnage = /^(\+|-)?\d+$/;
	var reg_num = /^[\d]+$/;
	var reg_businessname = /^[a-zA-Z0-9][^\u4E00-\u9FA5]+$/;
	var reg_eng =  /^[a-zA-Z0-9]*[^\u4E00-\u9FA5]+$/;
	var reg_linkphone = /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/;
	var reg_post = /^[0-9]{6}$/;
	var reg_phone_begin = /\d[\d]*\d$/; 
	var reg_phone_end = /\d[\d]*\d$/;
	
	if(businessname==null||businessname=="-1"){ 
		alert("<s:text name='申请人'/> <s:text name='cannot_empty_error1'/>");
		document.getElementById("businessname").focus();  
		return false;
	}
	if(!reg_businessname.test(businessname)){ 
		alert("<s:text name='申请人'/> <s:text name='onlyenglish'/>"); 
		document.getElementById("businessname").focus();
		return false;
	}
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
	
	if(destinationcountrys==null||destinationcountrys==""||destinationcountrys=="-1"){
		alert("<s:text name='目的国'/> <s:text name='cannot_empty_error1'/>");
		document.getElementById("destinationcountrys1").focus();
		return false;
	} 
	if(destinationcountrys=="OTHER"){
		if(countrys==null||countrys==""){
			alert("<s:text name='目的国'/> <s:text name='cannot_empty_error1'/>");
			document.getElementById("countrys1").focus();
			return false;
		}
	}
	if(ports!=null&&ports!=""){
		if(!reg_eng.test(ports)){
			alert("<s:text name='目的国港口'/> <s:text name='onlyenglish'/>"); 
			document.getElementById("ports").focus();
			return false;
		}
	}

	if(destinationcountrys_other!=null&&destinationcountrys_other!=""&&destinationcountrys_other!="-1"){
		if(countrys_other==null||countrys_other==""){
			alert("目的国及港口2不是 请选择状态 <s:text name='目的国'/>2 <s:text name='cannot_empty_error1'/>");
			document.getElementById("countrys2").focus();
			return false;
		}
		if(!reg_eng.test(countrys_other)){
			alert("<s:text name='目的国'/>2 <s:text name='onlyenglish'/>"); 
			document.getElementById("countrys2").focus();
			return false;
		}
		if(ports_other!=null&&ports_other!=""){
			if(!reg_eng.test(ports_other)){
				alert("<s:text name='目的国港口'/>2 <s:text name='onlyenglish'/>"); 
				document.getElementById("ports2").focus();
				return false;
			}
		} 
	
	}
	if(destinationcountrys_other!=null&&destinationcountrys_other=="-1"){
		if(countrys_other!=null&&countrys_other!=""){
			alert("<s:text name='目的国'/>2 <s:text name='存在内容'/> <s:text name='目的国及港口'/><s:text name='cannot_empty_error1'/> ");
			document.getElementById("destinationcountrys2").focus();
			return false;
		}
		if(ports_other!=null&&ports_other!=""){
			alert("<s:text name='目的国港口'/>2 <s:text name='存在内容'/> <s:text name='目的国及港口'/><s:text name='cannot_empty_error1'/> ");
			document.getElementById("destinationcountrys2").focus();
			return false;
		} 
	}
    
	
	if(proid=="-1"){
		alert("<s:text name='province'/> <s:text name='cannot_empty_error1'/>");
		document.getElementById("proid").focus();
		return false;
	}
	if(city=="-1"){
		alert("<s:text name='city'/> <s:text name='cannot_empty_error1'/>");
		document.getElementById("city").focus();
		return false;
	}
	if(portid=="-1"){ 
		alert("<s:text name='船舶停靠港'/> <s:text name='cannot_empty_error1'/>");
		document.getElementById("portid").focus();
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
	if(checkdate==null||checkdate==""){
		alert("<s:text name='合适的检查日期及时间'/> <s:text name='cannot_empty_error1'/>");
		document.getElementById("checkdate").focus();
		return false;
	}
	if(Date.parse(plandatein.replace('-','/'))>Date.parse(plandateout.replace('-','/'))){
		$("plandateout").val("");
		alert("<s:text name='计划离港日期'/> <s:text name='必须晚于'/> <s:text name='计划入港日期'/>");
		document.getElementById("plandateout").focus();
		return false; 
	}
	if(Date.parse(checkdate.replace('-','/'))>Date.parse(plandateout.replace('-','/'))){
		alert("<s:text name='合适的检查日期及时间'/> <s:text name='不能晚于'/> <s:text name='计划离港日期'/>");
		document.getElementById("checkdate").focus();
		return false; 
	}
	if(Date.parse(plandatein.replace('-','/'))>Date.parse(checkdate.replace('-','/'))){
		alert("<s:text name='计划入港日期'/> <s:text name='不能晚于'/> <s:text name='合适的检查日期及时间'/>");
		document.getElementById("checkdate").focus(); 
		return false; 
	}
	
	
	if(special==null||special==""){
		alert("<s:text name='特别交通费'/> <s:text name='onlynumber' />");
		document.getElementById("special").value="0";
		document.getElementById("special").focus();
		return false;
	}
	if(linkmanname==null||linkmanname==""){
		alert("<s:text name='联系人姓名'/> <s:text name='cannot_empty_error1'/>");
		document.getElementById("linkmanname").focus();
		return false;
	}
	if(phone_begin==null||phone_begin==""||phone_end==null||phone_end=="")
	{
		if(phone_begin==null||phone_begin==""){
			alert("<s:text name='区号'/> <s:text name='cannot_empty_error1'/>");
			document.getElementById("phone_begin").focus();
			return false;
		}
		if(phone_end==null||phone_end==""){
			alert("<s:text name='联系人电话'/> <s:text name='cannot_empty_error1'/>");
			document.getElementById("phone_end").focus();
			return false;
		} 
	}else{
		if(!reg_num.test(phone_begin)){ 
			alert("<s:text name='区号'/> <s:text name='onlynumber' />");
			document.getElementById("phone_begin").focus();
			return false;
		}
		if(!reg_num.test(phone_end)){
			alert("<s:text name='联系人电话'/> <s:text name='onlynumber' />");
			document.getElementById("phone_end").focus();
			return false;
		}   
	}
	if(linkphone!=null&&linkphone!="")
	{
		if(!reg_num.test(linkphone)){
		alert("<s:text name='联系人手机'/> <s:text name='onlynumber' />");
		document.getElementById("linkphone").focus();
		return false;
		}
	}
	
	
	
	
	if(invoicetitle==null||invoicetitle==""){
		alert("<s:text name='发票抬头'/> <s:text name='cannot_empty_error1'/>");
		document.getElementById("invoicetitle").focus();
		return false;
	}
	if(invoiceaddress==null||invoiceaddress==""){
		alert("<s:text name='邮寄地址'/> <s:text name='cannot_empty_error1'/>");
		document.getElementById("invoiceaddress").focus();
		return false;
	}
	if(invoicepost==null||invoicepost==""){
		alert("<s:text name='收件人邮编'/> <s:text name='cannot_empty_error1'/>");
		document.getElementById("invoicepost").focus();
		return false;
	}
	if(!reg_num.test(invoicepost)){
		alert("<s:text name='收件人邮编'/> <s:text name='格式错误'/>,<s:text name='收件人邮编'/> <s:text name='onlynumber' />");
		document.getElementById("invoicepost").focus();
		return false; 
	}
	if(invoicer==null||invoicer==""){
		alert("<s:text name='发票收件人'/> <s:text name='cannot_empty_error1'/>");
		document.getElementById("invoicer").focus();
		return false;
	} 
	if(invoicetel_begin==null||invoicetel_begin==""||invoicetel_end==null||invoicetel_end=="")
	{
		if(invoicetel_begin==null||invoicetel_begin==""){
			alert("<s:text name='区号'/> <s:text name='cannot_empty_error1'/>");
			document.getElementById("invoicetel_begin").focus();
			return false;
		}
		if(invoicetel_end==null||invoicetel_end==""){
			alert("<s:text name='电话'/> <s:text name='cannot_empty_error1'/>");
			document.getElementById("invoicetel_end").focus();
			return false;
		}
	}else{
		if(!reg_num.test(invoicetel_begin)){ 
			alert("<s:text name='区号'/> <s:text name='onlynumber' />");
			document.getElementById("invoicetel_begin").focus();
			return false;
		}
		if(!reg_num.test(invoicetel_end)){
			alert("<s:text name='电话'/> <s:text name='onlynumber' />");
			document.getElementById("invoicetel_end").focus();
			return false;
		}   
	}
	 
	return true;  

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
						<s:text name="您的当前位置"/>： <s:text name="首页"/> &gt;&gt; <s:text name="业务管理"/> &gt;&gt;
						<strong><s:text name="业务申请"/></strong>
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
													<span style="color: #FF0000;">*</span>
													<s:text name="号为必填项"/>
													&nbsp;&nbsp;&nbsp;
													<b style="color: red;"><s:property value="%{getText(#request.message)}" /> </b>
												</td>
												</tr>
												
												<tr>
												<td width="180" height="20" colspan="3"> <b><s:text name="基本信息"></s:text> </b> </td>
												</tr>
												
												<tr>
													<td width="180" height="20"> <s:text name="申请人"/><span class="mainred" >*</span> </td>
													<td> 
													<div style="width: 305px;overflow-x:scroll;"> 
														<s:select list="#request.businessnamelist" name="businessname" size="1"
																	id="businessname" listKey="CO_EN_NAME" listValue="CO_EN_NAME"  
																	headerKey="-1" headerValue="- %{getText('请选择')} -"></s:select>
															<input type="hidden" name="businessname_hidden"  id="businessname_hidden" value="<s:property value="business.businessname"/>"  />	
													</div>
													</td> 
													<td width="272"><span class="mainred" title=""><s:text name="请填写公司名称(英文)"/></span> </td>
												</tr>
												<tr>
													<td>
														<s:text name="船东或租船人名称"/><span class="mainred" >*</span>
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
														<s:text name="船名"/><span class="mainred" >*</span>
													</td>
													<td>
													<input name="vesselname" id="vesselname" type="text" class="input" maxlength="250"
														size="55" value="<s:property value="business.vesselname"/>" />
													</td>
													<td>
														<font color="red">*船名中避免 \  /  ‘ 和 “ ” 的符号</font>
													</td>
												</tr>
												
												
												<tr>
												<td height="20">
													<s:text name="船舶类型"/><span class="mainred" >*</span>
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
													<s:text name="船籍"/><span class="mainred" >*</span>
												</td>
												<td>
													 	<s:select list="#request.nationList" name="registry" cssStyle="width: 100%;"
																	id="registry" listKey="NATIONENNAME" listValue="NATIONENNAME"
																	headerKey="-1" headerValue="- %{getText('请选择')} -"></s:select> 
													<input type="hidden" name="registry_hidden" id="registry_hidden" value="${registry_hidden }" />
												</td>
												<td> </td>
											</tr>
												
												<tr>
													<td>
														<s:text name="IMO号"/><span class="mainred" >*</span>
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
														<s:text name="总吨位"/><span class="mainred" >*</span>
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
														<s:text name="目的国及港口"/>1<span class="mainred" >*</span>
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
														<s:text name="目的国及港口"/>2
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
												<td height="20">  <s:text name="船舶停靠港"/><span class="mainred" >*</span> </td>
												<td>
													<s:select list="#request.promaryList" id="proid" name="proid" cssStyle="width:90px;" 
														value="#request.select_proid" listKey="PROID" listValue="%{getText(PRONAME)}" onchange="javascript:changepromary();" ></s:select>
														(<s:text name="Province"/>) 
													<select id="city" name="city" style="width: 80px;" onchange="javascript:changcity();" > 
														<option value='-1'>- <s:text name="请选择"/> -</option> 
													</select>(<s:text name="City"/>)
													<select name="portid" id="portid" >
														<option value='-1'>- <s:text name="请选择"/> -</option>
													</select> 
												</td>
												<td> </td>
											</tr>
											
											<tr>
												<td height="20">
													<s:text name="泊位"/>
												</td>

												<td>
													<input type="text" name="berth" maxlength="20" value="<s:property value="business.berth"/>"/>
												</td>
												<td>
													
												</td>
											</tr>	
												
											<tr>
												<td height="20">
													<s:text name="计划入港日期"/><span class="mainred" >*</span>
												</td>
												<td>
													<s:if test="#request.language=='CN'"><input class="Wdate" type="text" id="plandatein"
															name="plandatein" onfocus="WdatePicker()" value="<s:date name="business.plandatein" format="yyyy-MM-dd" /> " /></s:if>
													<s:else>	<input class="Wdate" type="text" id="plandatein"
															name="plandatein" onfocus="WdatePicker({dateFmt:'MM/dd/yyyy',lang:'en'})" value="<s:date name="business.plandatein" format="MM/dd/yyyy" /> " /> </s:else>
												
												</td>

												<td>
													
												</td>
											</tr>
											
											<tr>
												<td height="20">
													<s:text name="计划离港日期"/><span class="mainred" >*</span>
												</td>

												<td>
													<s:if test="#request.language=='CN'"><input class="Wdate" type="text" id="plandateout"
															name="plandateout" onfocus="WdatePicker()" value="<s:date name="business.plandateout" format="yyyy-MM-dd" /> " /></s:if>
													<s:else>	<input class="Wdate" type="text" id="plandateout"
															name="plandateout" onfocus="WdatePicker({dateFmt:'MM/dd/yyyy',lang:'en'})" value="<s:date name="business.plandateout" format="MM/dd/yyyy" /> " /> </s:else>
												
												</td> 
												<td>
													
												</td>
											</tr>
											
											
											<tr>
												<td height="20">
													<p>
														<s:text name="合适的检查日期"/><span class="mainred" >*</span>
													</p>
												</td>
												<td>
													<s:if test="#request.language=='CN'"><input class="Wdate" type="text" id="checkdate"
														name="checkdate" onfocus="WdatePicker()" value="<s:date name="business.checkdate" format="yyyy-MM-dd" /> " /></s:if>
													<s:else>	<input class="Wdate" type="text" id="checkdate"
														name="checkdate" onfocus="WdatePicker({dateFmt:'MM/dd/yyyy',lang:'en'})" value="<s:date name="business.checkdate" format="MM/dd/yyyy" /> " /> </s:else>
												
												</td>
												<td>
													
												</td>
											</tr>
												
											<tr>
												<td height="20">  <s:text name="特别交通费"/><span class="mainred" >*</span> </td>
												<td> <input name="special" id="special" 	value="<s:property value="business.special"/>"
												maxlength="32" type="text" class="input" size="55" /> </td>
												<td> &nbsp; </td>
											</tr> 	
												
											<tr>
												<td height="20"><s:text name="联系人姓名"/><span class="mainred" >*</span> 	</td>
												<td>
													<input name="linkmanname" id="linkmanname" type="text" class="input"
														size="55" maxlength="250" value="<s:property value="business.linkmanname"/>" />
												</td>
												<td> </td>
											</tr>
												
											<tr>
												<td> <s:text name="联系人电话"/> <span class="mainred" >*</span></td>
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
												<td> <s:text name="联系人传真"/> </td>
												<td> <input name="fax" id="fax" maxlength="20" type="text" 
														value="<s:property value="business.fax"/>" class="input" size="55" />
												</td> <td> &nbsp; </td>
											</tr>
											
											<tr> <td> <s:text name="电子邮件"/> </td>
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
													<b><s:text name="发票抬头及邮寄地址"/></b> 
												</td>
											</tr>	
												
											<tr>
												<td height="20"> <s:text name="发票抬头"/><span class="mainred" >*</span> </td>
												<td> <input name="invoicetitle" id="invoicetitle" value="<s:property value="business.invoicetitle"/>"
												maxlength="32" type="text" class="input" size="55" /> </td>
												<td> &nbsp; </td>
											</tr>
											<tr>
												<td height="20"> <s:text name="邮寄地址"/><span class="mainred" >*</span></td>
												<td> <input name="invoiceaddress" id="invoiceaddress" value="<s:property value="business.invoiceaddress"/>"
												maxlength="32" type="text" class="input" size="55" /> </td>
												<td> &nbsp; </td>
											</tr>
											<tr>
												<td height="20"> <s:text name="收件人邮编"/><span class="mainred" >*</span> </td>	
												<td> <input name="invoicepost" id="invoicepost" maxlength="32" value="<s:property value="business.invoicepost"/>"
												type="text" class="input" size="55" /> </td>
												<td> &nbsp; </td>
											</tr>
											<tr>
												<td height="20"> <s:text name="发票收件人"/><span class="mainred" >*</span> </td>
												<td> <input name="invoicer" id="invoicer" value="<s:property value="business.invoicer"/>"
												maxlength="32" type="text" class="input" size="55" /> </td>
												<td> &nbsp; </td>
											</tr>
											<tr>
												<td height="20"> <s:text name="收件人联系电话"/><span class="mainred" >*</span></td>
												<td> 
												<input name="invoicetel_begin" id="invoicetel_begin" maxlength="16" type="text" class="input" size="5" value="${invoicetel_begin }" />
													&nbsp;-&nbsp;
												<input name="invoicetel_end" id="invoicetel_end" maxlength="40" type="text" class="input" size="20" value="${invoicetel_end }" />
												
												<input name="invoicetel" type="hidden" id="invoicetel" value="<s:property value="business.invoicetel"/>"
												maxlength="32" type="text" class="input" size="55" /> </td>
												<td> &nbsp; </td>
											</tr>
												
												
											<tr>
												<td height="20">
												
												</td>
												<td>
													<input type="hidden" name="certification" value="<s:property value="business.certification"/>" />	
													<input  type="hidden" name="id" id="id" value="<s:property value="business.id"/>" /> 
													<input  type="hidden" name="tempno" value="<s:property value="business.tempno"/>" /> 
													<input  type="hidden" name="accountid" value="<s:property value="business.accountid"/>" />
												</td> 
												<td>
													&nbsp;
												</td>
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
													<td height="38" align="center" colspan="3"> 
													
															<c:if test="${view==0}">
																<input name="input4" class="button" type="button" onclick="apply();"   value="<s:text name='预览申请单'/>" />
                  												<input name="input2" class="button" type="button" onclick="payment();" value="<s:text name='预览收费单'/>" />
                  												<input name="input3" class="button" type="button" onclick="modify();" value="<s:text name="update"/>" />
																<input name="input4" class="button" type="button" onclick="commitaftersave();" value="<s:text name='submit'/>" />
																<input name="input" class="button" type="button" value="<s:text name='return'/>"
																onclick="javascript:goback('getBusinessByPage.action?goPage=<s:property value="#request.currPagec"/>');" />
															</c:if>
															<c:if test="${view==1}">
																<input name="input4" class="button" type="button" onclick="apply();"   value="<s:text name='预览申请单'/>" />
                  												<input name="input2" class="button" type="button" onclick="payment();" value="<s:text name='预览收费单'/>" />
																<input name="input2" class="button" type="button" onclick="updatebusiness();" value="<s:text name='save'/>" />
																<input name="input4" class="button" type="button" onclick="commitbusiness();" value="<s:text name='submit'/>" />
																<input name="input" class="button" type="button" value="<s:text name='return'/>"
																onclick="javascript:goback('getBusinessByPage.action?goPage=<s:property value="#request.currPagec"/>');" />
															</c:if> 
														
													</td>
													 
												</tr>


		<input	type="hidden" name="page_countrys" id="page_countrys" value="${page_countrys}"	/>
		<input	type="hidden" name="page_ports" id="page_ports" value="${page_ports}"	/>
		<input	type="hidden" name="page_selects" id="page_selects" value="${page_selects}"	/>
		<input type="hidden" name="select_proid" id="select_proid" value="${select_proid }"/> 
		<input type="hidden" name="select_cityid" id="select_cityid" value="${select_cityid }"/> 
		<input type="hidden" name="select_portid" id="select_portid" value="${select_portid }"/>
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
