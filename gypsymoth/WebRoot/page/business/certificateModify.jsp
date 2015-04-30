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
	<body>
	 <script language="JavaScript" type="text/javascript">
	 	var date = new Date();
	 	var timestamp = date.getTime();
		$("document").ready(function(){
			initOption();
			var vesseltype_hidden = $("#vesseltype_hidden").val();
			selectItem('vesseltype',vesseltype_hidden); 
			var portid_hidden = $("#portid_hidden").val();
			selectItem('portid',portid_hidden);
		});  
	 


 function valiform()
{

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
	
	var plandatein = document.getElementById("plandatein").value;
	var plandateout = document.getElementById("plandateout").value;
	var checkdate = document.getElementById("checkdate").value;
 	
	
	var reg_tonnage = /^(\+|-)?\d+$/;
	var reg_num = /^[\d]+$/;
	var reg_eng =  /^[a-zA-Z0-9]*[^\u4E00-\u9FA5]+$/;
	var reg_phone_begin = /\d[\d]*\d$/; 
	var reg_phone_end = /\d[\d]*\d$/;
	
	
	if(company==null||company==""){
		alert("船东或租船人名称不能为空。");
		document.getElementById("company").focus();
		return false;
	}
	if(!reg_eng.test(company)){ 
		alert("船东或租船人名称不能包含中文。"); 
		document.getElementById("company").focus();
		return false;
	}
	if(vesselname==null||vesselname==""){
		alert("船名不能为空");
		document.getElementById("vesselname").focus();
		return false;
	}
	if(!reg_eng.test(vesselname)){ 
		alert("船名不能包含中文"); 
		document.getElementById("vesselname").focus();
		return false;
	}
	if(vesseltype==null||vesseltype==""||vesseltype=="-1"){
		alert("船舶类型不能为空");
		document.getElementById("vesseltype").focus();
		return false;
	}
	if(registry==null||registry==""||registry=="-1"){
		alert("船籍不能为空");
		document.getElementById("registry").focus();
		return false;
	}
	if(imo==null||imo==""){
		alert("IMO号不能为空");
		document.getElementById("imo").focus();
		return false;
	}
	if(!reg_num.test(imo)){ 
		alert("IMO号必须是数字"); 
		document.getElementById("imo").focus();
		return false;
	}
	if(tonnage==null||tonnage==""){
		alert("总吨位不能为空");
		document.getElementById("tonnage").focus();
		return false;
	}
	if(!reg_num.test(tonnage)){
		alert("总吨位必须是数字");
		document.getElementById("tonnage").focus();
		return false;
	}
	
	
	if(destinationcountrys==null||destinationcountrys==""||destinationcountrys=="-1"){
		alert("目的国 不能为空");
		document.getElementById("destinationcountrys1").focus();
		return false;
	} 
	if(destinationcountrys=="OTHER"){
		if(countrys==null||countrys==""){
			alert("目的国 不能为空");
			document.getElementById("countrys1").focus();
			return false;
		}
	}
	if(ports!=null&&ports!=""){
		if(!reg_eng.test(ports)){ 
			alert("目的国港口 不能包含中文"); 
			document.getElementById("ports").focus();
			return false;
		}
	}
	

	if(destinationcountrys_other!=null&&destinationcountrys_other!=""&&destinationcountrys_other!="-1"){
		if(countrys_other==null||countrys_other==""){
			alert("目的国及港口2不是 请选择状态 目的国2 不能为空");
			document.getElementById("countrys2").focus();
			return false;
		}
		if(!reg_eng.test(countrys_other)){
			alert("目的国2 不能包含中文"); 
			document.getElementById("countrys2").focus();
			return false;
		}
		if(ports_other!=null&&ports_other!=""){
			if(!reg_eng.test(ports_other)){
				alert("目的国港口2 不能包含中文"); 
				document.getElementById("ports").focus();
				return false;
			}
		}
	}
	if(destinationcountrys_other!=null&&destinationcountrys_other=="-1"){
		if(countrys_other!=null&&countrys_other!=""){
			alert("目的国2 存在内容 目的国及港口不能为空 ");
			document.getElementById("destinationcountrys2").focus();
			return false;
		}
	}
	 
	if(plandatein==null||plandatein==""){
		alert("计划入港日期 不能为空");
		document.getElementById("plandatein").focus(); 
		return false;
	}
	if(plandateout==null||plandateout==""){
		alert("计划离港日期 不能为空");
		document.getElementById("plandateout").focus(); 
		return false;
	}
	if(checkdate==null||checkdate==""){
		alert("合适的检查日期及时间 不能为空");
		document.getElementById("checkdate").focus();
		return false;
	}
	if(Date.parse(plandatein.replace('-','/'))>Date.parse(plandateout.replace('-','/'))){
		$("plandateout").val("");
		alert("计划离港日期 必须晚于 计划入港日期");
		document.getElementById("plandateout").focus();
		return false; 
	}
	if(Date.parse(checkdate.replace('-','/'))>Date.parse(plandateout.replace('-','/'))){
		alert("合适的检查日期及时间 不能晚于 计划离港日期");
		document.getElementById("checkdate").focus();
		return false; 
	}
	if(Date.parse(plandatein.replace('-','/'))>Date.parse(checkdate.replace('-','/'))){
		alert("计划入港日期 不能晚于合适的检查日期及时间");
		document.getElementById("checkdate").focus(); 
		return false; 
	}
	 
	return true;  

}




	function saveEdit()
	{
		if(!valiform()){
			return false;
		}
		if(confirm("您确认保存?")){
			document.getElementById("form1").action = "savecertificate.action"; 
			document.getElementById("form1").submit(); 
		}else{
			return false;  
		}
		 
	}


		function toEdit()
		{
			document.getElementById("form1").action = "lookCert.action"; 
			document.getElementById("form1").submit();   
		}	  
		
	function searchallotlist(type)
	{
		var allottype = "1";			// 分配对象类型	  0:协调员/ 1:授权签字人 / 2:检查员 
		var radio_check = "1";	//得分配对象类型  0:协调员/ 1:检查员及授权签字人
		var orgid = $("#orgto").val(); 
		var url = "allotlistbyorg.action?allottype="+allottype+"&radio_check="+radio_check+"&orgid="+orgid;
		window.open(url,"_blank","height=500px,width=700px,scrollbars=yes"); 
		
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
	</script>
		
	<table border="0" cellspacing="0" cellpadding="0" width="100%">
	<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
  <tr>
    <td class="list_r">&nbsp;</td>
    <td valign="top" class="right"><div class="title">您的当前位置： 首页 &gt;&gt; 业务管理 &gt;&gt;证书管理 &gt;&gt; <strong>编制证书</strong></div>
      <div>
        <table align="center" cellpadding="12" class="tabedit">
          <tr>
            <td height="300" align="left" valign="top"><!--编辑开始-->
             <s:form id="form1" name="form1" action="" theme="simple" >
              <input name="businessid" id="businessid" type="hidden" value="${businessid }" />
				<input	type="hidden" name="page_countrys" id="page_countrys" value="${page_countrys}"	/>
				<input	type="hidden" name="page_ports" id="page_ports" value="${page_ports}"	/>
				<input	type="hidden" name="page_selects" id="page_selects" value="${page_selects}"	/>
		
			
			  <input	type="hidden" name="orgto" id="orgto" value="${orgto}"	/> 
			
              <s:iterator value="#request.list" status="stat">
              <table width="706" align="center" cellspacing="5">
              
             	<tr>
					<td height="25" colspan="3" align="left"
						style="border-bottom: dotted 1px #8EC0E8;">
						<s:actionmessage/>
						<span style="color: #FF0000;">*</span>号为必填项
						&nbsp;&nbsp;&nbsp;<b style="color: red;">${message }</b> 
					</td>
				</tr>
           
                <tr>
                  <td width="180" height="20">申请人<span class="mainred" >*</span></td>
                  <td width="332">
                  <s:textfield readonly="true" disabled="true" name="businessname"  size="55" ></s:textfield>  
                 </td>
                  <td width="272">&nbsp;</td>
                </tr>
                <tr>
                  <td>船东或租船人名称<span class="mainred" >*</span></td>
                  <td>
                    <s:textfield name="company" id="company" size="55" />
                  </td>
                  <td>&nbsp;</td>
	               </tr>
	               
	               
	                <tr>
                  <td>船名<span class="mainred" >*</span></td>
                  <td>
                    <s:textfield name="vesselname" id="vesselname" size="55" />
                  </td>
                  <td>&nbsp;</td>
                </tr>
                 <tr>
                  <td>类型<span class="mainred" >*</span></td>
                  <td>
                   <select name="vesseltype" id="vesseltype" class="select" id="vesseltype"
							style="width: 100%;"> 
							<option value="-1">
								- <s:text name="请选择"/> -
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
						  <input type="hidden" name="vesseltype_hidden" id="vesseltype_hidden" value="${vesseltype }" />
                  </td>
                  <td>&nbsp;</td>
                </tr>
                 <tr>
                  <td>船籍<span class="mainred" >*</span></td>
                  <td>
                     <s:select list="#request.nationList" name="registry" cssStyle="width: 100%;"
								id="registry" listKey="NATIONENNAME" listValue="NATIONENNAME" 
								headerKey="-1" headerValue="- %{getText('请选择')} -"></s:select> 
                  </td>
                  <td>&nbsp;</td>
                </tr>
                
                    <tr>
                  <td>IMO编号<span class="mainred" >*</span></td>
                  <td>
                    <s:textfield name="imo" id="imo" size="55" />
                  </td>
                  <td>&nbsp;</td>
                </tr>
                 <tr>
                  <td>总吨位<span class="mainred" >*</span></td>
                  <td>
                    <s:textfield name="tonnage" id="tonnage"  size="50" />&nbsp;MT
                  </td>
                  <td>&nbsp;</td>
                </tr>
	          		 
	          		<tr>
						<td height="20">
								<s:text name="目的国及港口1"/><span class="mainred" >*</span>
							</td>
							<td colspan="2">
							<table width="100%" id="append_tab">
							<tr>	
								<td>
									<select name="destinationcountrys" id="destinationcountrys1" onchange="changecountrtselect(1);" >
										<option value="-1"><s:text name="请选择"/></option>
										<option value="USA">USA</option>
										<option value='CANADA'>CANADA</option>
										<option value='MEXICO'>MEXICO</option>
										<option value='OTHER'>OTHER</option>
									</select>
									<input type="text" name="countrys" id="countrys1" size="8" readonly="readonly"  />
									<s:text name="目的国港口"/>
									<input name="ports" id="ports1" type="text" maxlength="250" class="input" size="12"/>
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
									<input type="text" name="countrys" id="countrys2" size="8" readonly="readonly" />
									<s:text name="目的国港口"/>
									<input name="ports" id="ports2" type="text" maxlength="250" class="input" size="12" />
								</td>
							</tr>
							</table>
						</td>
					</tr>  
	          		 
                
                
             
                
                
                	<tr>
						<td height="20">
							船舶停靠<span class="mainred" >*</span>
						</td>

						<td>
							<select name="proid" id="proid" disabled="disabled">
								<option>${proname }</option>
							</select>省
							<select name="city" id="city" disabled="disabled">
								<option>${cityname } </option>
							</select>市
							<s:select list="#request.portlist" name="portid" id="portid" listKey="ID" listValue="PORT_NAME"></s:select>
							<input  type="hidden" name="portid_hidden" id="portid_hidden" value="${portname } " />
							
						</td>
						<td>
							
						</td>
					</tr>
					
						<tr>
							<td height="20">
								泊位、锚位 
							</td>

							<td>
								<s:textfield name="berth" id="berth" size="55"></s:textfield>
							</td>
							<td>
								
							</td>
						</tr>
                
               
	               <tr>
	                  <td>计划入港日期<span class="mainred" >*</span></td>
	                  <td>
	                  	<input class="Wdate" type="text" id="plandatein"
							 	name="plandatein" onfocus="WdatePicker()" value="<s:date name="plandatein" format="yyyy-MM-dd" /> " />
	                  </td>
	                  <td>&nbsp;</td>
	                </tr> 
                 
                 <tr>
                  <td>计划离港日期<span class="mainred" >*</span></td>
                  <td>
                  	<input class="Wdate" type="text" id="plandateout"
						 	name="plandateout" onfocus="WdatePicker()" value="<s:date name="plandateout" format="yyyy-MM-dd" /> " />
                  </td>
                  <td>&nbsp;</td>
                </tr> 
                 
                 
                  <tr>
                  <td>合适的检查日期及时间<span class="mainred" >*</span></td>
                  <td>
                  	<input class="Wdate" type="text" id="checkdate"
						 	name="checkdate" onfocus="WdatePicker()" value="<s:date name="checkdate" format="yyyy-MM-dd" /> " />
                  		 
                  </td>
                  <td>&nbsp;</td>
                </tr>
               
             <tr>
	               <td>授权签字人<span class="mainred" >*</span></td>
                  <td>
                  	 <input  name="auth_input_name" id="auth_input_name" type="text" class="input" size="25" readonly="readonly" value="${coor_view }" onclick="alert('为了避免重名，请使用选择按钮通过机构选择人员。')"  />
                  	 <input  name="input4" class="button_s" type="button" value="选 择"  onclick="javascript:searchallotlist(1);" />
                 	 <input type="hidden" name="auth_input_id" id="auth_input_id" value="${auth_view}" />
                  </td>   
                  <td>&nbsp;</td> 
                	
                </tr>
 
                
               <tr>
                  <td height="38" align="left"></td>
                  <td height="38" align="left">
                   <input  name="input" class="button" type="button" value="保 存"  onclick="javascript:saveEdit();" />
                  <input  name="input" class="button" type="button" value="返 回"  onclick="javascript:toEdit();" /></td>
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
