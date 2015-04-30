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
	 	var date = new Date();
	 	var timestamp = date.getTime();
		$("document").ready(function(){
			initOption();
		});  
	 
	 	 function changeSelect()
	 	{
	 		var certname_select = $("#certname_select").val();
	 		var url = "certificateContent.action?certname="+certname_select+"&tid="+timestamp;
	 		ajax_selectContent(url,'certnolist');
	 	} 
	 	
	 	function savecert()
	 	{ 
	 	
	 	//	var certname_select = document.getElementById("certname_select").value;
	 		var certnolist = document.getElementById("certnolist").value;
	 		
	 	//	if(certname_select==null||certname_select=="-1"){
	 	//		alert("流水号区间不能为空，请确定还有可使用的空白流水号区间");
	 	//		document.getElementById("certname_select").focus();
	 	//		return false; 
	 	//	} 
	 		if(certnolist==null||certnolist==""){ 
	 			alert("流水号不能为空。 ");
	 			document.getElementById("certnolist").focus(); 
	 			return false;
	 		}
	 		if (confirm("确认提交？")) {
	 		document.getElementById("form1").action = "saveCertApp.action";
	 		document.getElementById("form1").submit(); 
	 		}
	 		else 
           return false;
	 	}
	 	
	 	function modifycert()
	 	{
	 		document.getElementById("form1").action = "certificatemodify.action";
	 		document.getElementById("form1").submit(); 
	 	}
	  
	  	function backcertificate()
	  	{
	  		document.getElementById("form1").action = "certificate.action"; 
	 		document.getElementById("form1").submit(); 
	  	}
	  	
	  	function allotView()
		{
			var businessid = document.getElementById("businessid").value; 
			var url = "getAllotPageView.action?id="+businessid;
			window.open(url,"_blank");
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
					<td height="25" colspan="3" align="left"
						style="border-bottom: dotted 1px #8EC0E8;">
						<s:actionmessage/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b style="color: red;">${message }</b> 
					</td>
				</tr>
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
                    <s:textfield readonly="true" disabled="true" name="tonnage"  size="55" />
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
									<select name="destinationcountrys" id="destinationcountrys1" onchange="changecountrtselect(1);" disabled="disabled">
										<option value="-1"><s:text name="请选择"/></option>
										<option value="USA">USA</option>
										<option value='CANADA'>CANADA</option>
										<option value='MEXICO'>MEXICO</option>
										<option value='OTHER'>OTHER</option>
									</select>
									<input type="text" name="countrys" id="countrys1" size="8" readonly="readonly" disabled="disabled" value="" />
									<s:text name="目的国港口"/>
									<input name="ports" id="ports1" type="text" maxlength="250" class="input" size="12" disabled="disabled" />
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
									<select name="destinationcountrys" id="destinationcountrys2" disabled="disabled" onchange="changecountrtselect(2);">
										<option value="-1"><s:text name="请选择"/></option>
										<option value="USA">USA</option>
										<option value='CANADA'>CANADA</option>
										<option value='MEXICO'>MEXICO</option>
										<option value='OTHER'>OTHER</option>
									</select>
									<input type="text" name="countrys" id="countrys2" size="8" disabled="disabled" />
									<s:text name="目的国港口"/>
									<input name="ports" id="ports2" type="text" maxlength="250" disabled="disabled" class="input" size="12" />
								</td>
							</tr>
							</table>
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
                  		<input type="text" readonly="readonly" disabled="disabled" value="<s:date name="plandatein" format="yyyy-MM-dd" />" size="55" />
                  </td>
                  <td>&nbsp;</td>
                </tr>
                 
                 <tr>
                  <td>计划离港日期</td>
                  <td>
                  		<input type="text" readonly="readonly" disabled="disabled" value="<s:date name="plandateout" format="yyyy-MM-dd" />" size="55" />
                  </td>
                  <td>&nbsp;</td>
                </tr>
                 
                 
                  <tr>
                  <td>合适的检查日期及时间</td>
                  <td>
                  <input type="text" readonly="readonly" disabled="disabled" value="<s:date name="checkdate" format="yyyy-MM-dd" />" size="55" />
                  </td>
                  <td>&nbsp;</td>
                </tr>
               
             <tr>
	               <td>授权签字人</td>
                  <td>
                  	 <input type="text" readonly="readonly" disabled="disabled" name="coor_view" id="coor_view" value="${coor_view }" />
                  	 <input type="hidden" name="coor" id="coor" value="${coor_view }" />
                  </td>   
                  <td>&nbsp;</td>
                	
                </tr>
                
                  <tr>
                  <td>证书流水号</td>
                  <td>
                    
                    <!-- 
                    <s:select list="#request.selectlist" listKey="CERTNAME" listValue="CERTNAME" id="certname_select" name="certname_select"
                  		onchange="changeSelect();" headerKey="-1" headerValue="- 请选择 -">
                  	</s:select> 
                  	
                  	<select id="certnolist" name="certnolist">
                  		<option value="-1">- 请选择 -</option>
                  	</select>
                     --> 
                    <input type="text" name="certnolist" id="certnolist" />
                    
                  </td>
                  <td>&nbsp;<font style="color: red;">*需要填写</font></td>
                </tr>
                
                
               <tr>
                  <td height="38" align="left"></td>
                  <td height="38" align="left">
                   <input  name="input" class="button" type="button" value="确 定"  onclick="javascript:savecert();" />
                   <input  name="input" class="button" type="button" value="预 览"  onclick="javascript:allotView();" />
                   <input  name="input" class="button" type="button" value="修 改"  onclick="javascript:modifycert();" /> 
                  <input  name="input" class="button" type="button" value="返 回"  onclick="javascript:history.back(-1);" /><!-- javascript:backcertificate(); -->
                 </td>
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
