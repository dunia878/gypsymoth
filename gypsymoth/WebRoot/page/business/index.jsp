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
		
		
	function goback(url)
		{
			document.form1.method="post";
			document.form1.action = url; 
			document.form1.submit();
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
	</script>
		
					<table border="0" cellspacing="0" cellpadding="0" width="100%">
						<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
  <tr>
    <td class="list_r">&nbsp;</td>
    <td valign="top" class="right"><div class="title">
		<s:text name="您的当前位置"/>： <s:text name="首页"/> &gt;&gt; <s:text name="业务管理"/> &gt;&gt;
						<strong><s:text name="业务申请"/></strong>
	  </div>
      <div>
        <table align="center" cellpadding="12" class="tabedit">
          <tr>
            <td height="300" align="left" valign="top"><!--编辑开始-->
             <s:form id="form1" name="form1" action="" theme="simple" >
             <input	type="hidden" name="page_countrys" id="page_countrys" value="${page_countrys}"	/>
			<input	type="hidden" name="page_ports" id="page_ports" value="${page_ports}"	/>
           	<input	type="hidden" name="page_selects" id="page_selects" value="${page_selects}"	/>
              <table width="706" align="center" cellspacing="5">
              	
           		<tr>
					<td width="180" height="20" colspan="3">
						<b><s:text name="基本信息"></s:text> </b> 
					</td>
				</tr>
           
                <tr>
                  <td width="180" height="20"> <s:text name="申请人"/><span class="mainred" >*</span> </td>
                  <td width="332">
                  <s:textfield readonly="true" name="businessname"  size="55" ></s:textfield>  
                 </td>
                  <td width="272">&nbsp;</td>
                </tr>
                <tr>
                  <td><s:text name="船东或租船人名称"/><span class="mainred" >*</span></td>
                  <td>
                    <s:textfield readonly="true" name="company"  size="55" />
                  </td>
                  <td>&nbsp;</td>
                </tr>
               
                <tr>
                  <td><s:text name="船名"/><span class="mainred" >*</span></td>
                  <td>
                    <s:textfield readonly="true" name="vesselname"  size="55" />
                  </td>
                  <td>&nbsp;</td>
                </tr>
                 <tr>
                  <td><s:text name="船舶类型"/><span class="mainred" >*</span></td>
                  <td>
                    <s:textfield readonly="true" name="vesseltype"  size="55" />
                  </td>
                  <td>&nbsp;</td>
                </tr>
                 <tr>
                  <td><s:text name="船籍"/><span class="mainred" >*</span></td>
                  <td>
                    <s:textfield readonly="true" name="registry"  size="55" />
                  </td>
                  <td>&nbsp;</td>
                </tr>
                 <tr>
                  <td><s:text name="IMO号"/><span class="mainred" >*</span></td>
                  <td>
                    <s:textfield readonly="true" name="imo"  size="55" />
                  </td>
                  <td>&nbsp;</td>
                </tr>
                 <tr>
                  <td><s:text name="总吨位"/><span class="mainred" >*</span></td>
                  <td>
                    <s:textfield readonly="true" name="tonnage"  size="50" />MT
                  </td>
                  <td>&nbsp;</td>
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
								<input type="text" name="countrys" id="countrys1" size="8" readonly="readonly" />
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
								<input type="text" name="countrys" id="countrys2" size="8" readonly="readonly" />
								<s:text name="目的国港口"/>
								<input name="ports" id="ports2" type="text" maxlength="250" class="input" size="12" />
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
						<s:text name="船舶停靠港"/><span class="mainred" >*</span>
					</td>

				<td>
						<select name="proid" id="proid" disabled="disabled">
							<option>${proname }</option>
						</select>(<s:text name="Province"/>) 
						<select name="city" id="city" disabled="disabled">
							<option>${cityname } </option>
						</select>(<s:text name="City"/>)
						<select name="portid" id="portid" disabled="disabled" > 
							<option>${portname } </option>
						</select> 				 
					</td>
					<td> 
						
					</td>
				</tr>
				
				<tr>
					<td height="20">
						<s:text name="泊位"/>
					</td>

					<td>
						<s:textfield readonly="true" name="berth"  size="55" />
					</td>
					<td>
						
					</td>
				</tr>
                 <tr>
                  <td><s:text name="计划入港日期"/><span class="mainred" >*</span></td>
                  <td>
                  	<input name="plandatein" readonly="readonly" value="<s:date name="plandatein" format="yyyy-MM-dd"/>" size="55" />
                  </td>
                  <td>&nbsp;</td>
                </tr>
                 <tr>
                  <td><s:text name="计划离港日期"/><span class="mainred" >*</span></td>
                  <td>
                  	<input name="plandateout" readonly="readonly" value="<s:date name="plandateout" format="yyyy-MM-dd"/>" size="55" />
                  </td>
                  <td>&nbsp;</td>
                </tr>
                 <tr>
                  <td><s:text name="合适的检查日期"/><span class="mainred" >*</span></td>
                  <td>
                 	 <input name="checkdate" readonly="readonly" value="<s:date name="checkdate" format="yyyy-MM-dd"/>" size="55" />
                  </td>
                  <td>&nbsp;</td> 
                </tr>
               <tr>
					<td height="20">  <s:text name="特别交通费"/> </td>
					<td> <s:textfield readonly="true" name="special"  size="50" /> &nbsp;RMB   </td>
					<td> &nbsp; </td>
				</tr> 
                
                 <tr>
                  <td><s:text name="联系人姓名"/><span class="mainred" >*</span></td>
                  <td>
                    <s:textfield readonly="true" name="linkmanname"  size="55" />
                  </td>
                  <td>&nbsp;</td>
                </tr>
                 <tr>
                  <td> <s:text name="联系人电话"/><span class="mainred" >*</span></td>
                  <td>
                    <s:textfield readonly="true" name="phone"  size="55" />
                  </td>
                  <td>&nbsp;</td>
                </tr>
                
                <tr>
                  <td><s:text name="联系人手机"/></td>
                  <td>
                    <s:textfield readonly="true" name="linkphone"  size="55" />
                  </td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td><s:text name="联系人传真"/></td>
                  <td>
                    <s:textfield readonly="true" name="fax"  size="55" />
                  </td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td><s:text name="电子邮件"/> </td>
                  <td>
                    <s:textfield readonly="true" name="email"  size="55" />
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
					<td height="20"> <s:text name="发票抬头"/><span class="mainred" >*</span> </td>
					<td> <s:textfield readonly="true" name="invoicetitle"  size="55" /> </td>
					<td> &nbsp; </td>
				</tr>
				<tr>
					<td height="20"> <s:text name="邮寄地址"/> <span class="mainred" >*</span></td>
					<td>  <s:textfield readonly="true" name="invoiceaddress"  size="55" /> </td>
					<td> &nbsp; </td>
				</tr>
				<tr>
					<td height="20"> <s:text name="收件人邮编"/><span class="mainred" >*</span> </td>
					<td> <s:textfield readonly="true" name="invoicepost"  size="55" /> </td>
					<td> &nbsp; </td>
				</tr>
				<tr>
					<td height="20"> <s:text name="发票收件人"/><span class="mainred" >*</span> </td>
					<td>  <s:textfield readonly="true" name="invoicer"  size="55" />  </td>
					<td> &nbsp; </td>
				</tr>
				<tr>
					<td height="20"> <s:text name="收件人联系电话"/><span class="mainred" >*</span> </td>
					<td> <s:textfield readonly="true" name="invoicetel"  size="55" />   </td>
					<td> &nbsp; </td>
				</tr>
                
                
                <tr>
                  <td height="20"> </td>
                  <td> 
                  	<input type="hidden" name="id" id="id" value="${id }" />
                  </td>
                  <td>&nbsp;</td>
                </tr>
               <tr>
                  <td height="38" align="left"></td>
                  <td height="38" align="left">
                  <input name="input4" class="button" type="button" onclick="apply();"   value="<s:text name='预览申请单'/>" />
                    <input name="input2" class="button" type="button" onclick="payment();" value="<s:text name='预览收费单'/>" />
          			<input name="input" class="button" type="button" value="<s:text name='return'/>"
						 	onclick="javascript:goback('getBusinessByPage.action');" />      
			
				</td>
                  <td align="left">&nbsp;</td>
                </tr>
             
              
					</table>
				</s:form>
				</td>
				<!-- 主内容结束 -->
			</tr>
		</table>
		<!--内容结束-->
	</body>
</html>
