<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
 	isELIgnored="false"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统</title>
		<%@ include file="../include/head.jsp"%>
		
<style type="text/css">
.allotclass{ 
border:1px solid #8ec0e8; width:306px;padding:5px;
}
</style>		
		
	</head>
	<body>
	
<script language="text/javascript" type="text/javascript"> 
	$(document).ready(function(){
			initOption();
			$("select").attr("disabled",true);
	}); 
 
 	 
 
  
		
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
</script>	
 
 <!--内容开始-->
<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
  <tr>
    <td class="list_r">&nbsp;</td>
    <td valign="top" class="right"><div class="title">您的当前位置： 首页 &gt;&gt; 业务管理 &gt;&gt; <strong>业务分配</strong></div>
      <div>
        <table align="center" cellpadding="12" class="tabedit">
          <tr>
            <td height="300" align="left" valign="top"><!--编辑开始-->
              <table width="706" align="center" cellspacing="5">
                
                <tr>
					<td width="180" height="20" colspan="3">
						<b><s:text name="基本信息"></s:text> </b> 
					</td>
				</tr>
           
	             <tr>
	                  <td width="180" height="20">业务编号</td>
	                  <td width="332"><label>
	                    <s:textfield readonly="true" name="business.appno" size="55"></s:textfield>
	                  </label></td>
	                  <td width="272">&nbsp;</td>
	                </tr>
                <tr>
                  <td width="180" height="20">申请人<span class="mainred" >*</span></td>
                  <td width="332">
                  <s:textfield readonly="true" name="business.businessname"  size="55" ></s:textfield>  
                 </td>
                  <td width="272">&nbsp;</td>
                </tr>
                <tr>
                  <td>船东或租船人名称<span class="mainred" >*</span></td>
                  <td>
                    <s:textfield readonly="true" name="business.company"  size="55" />
                  </td>
                  <td>&nbsp;</td>
                </tr>
               
                <tr>
                  <td>船名<span class="mainred" >*</span></td>
                  <td>
                    <s:textfield readonly="true" name="business.vesselname"  size="55" />
                  </td>
                  <td>&nbsp;</td>
                </tr>
                 <tr>
                  <td>船舶类型<span class="mainred" >*</span></td>
                  <td>
                    <s:textfield readonly="true" name="business.vesseltype"  size="55" />
                  </td>
                  <td>&nbsp;</td>
                </tr>
                 <tr>
                  <td>船籍<span class="mainred" >*</span></td>
                  <td>
                    <s:textfield readonly="true" name="business.registry"  size="55" />
                  </td>
                  <td>&nbsp;</td>
                </tr>
                 <tr>
                  <td>IMO编号<span class="mainred" >*</span></td>
                  <td>
                    <s:textfield readonly="true" name="business.imo"  size="55" />
                  </td>
                  <td>&nbsp;</td>
                </tr>
                 <tr>
                  <td>总吨位<span class="mainred" >*</span></td>
                  <td>
                    <s:textfield readonly="true" name="business.tonnage"  size="50" />&nbsp;MT
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
						船舶停靠<span class="mainred" >*</span>
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
						<s:textfield readonly="true" name="business.berth"  size="55" />
					</td>
					<td>
						
					</td>
				</tr>
                 <tr>
                  <td>计划入港日期<span class="mainred" >*</span></td>
                  <td>
                  	<input name="plandatein" readonly="readonly" value="<s:date name="business.plandatein" format="yyyy-MM-dd"/>" size="55" />
                  </td>
                  <td>&nbsp;</td>
                </tr>
                 <tr>
                  <td>计划离港日期<span class="mainred" >*</span></td>
                  <td>
                  	<input name="plandateout" readonly="readonly" value="<s:date name="business.plandateout" format="yyyy-MM-dd"/>" size="55" />
                  </td>
                  <td>&nbsp;</td>
                </tr>
                 <tr>
                  <td>合适的检查日期<span class="mainred" >*</span></td>
                  <td>
                 	 <input name="checkdate" readonly="readonly" value="<s:date name="business.checkdate" format="yyyy-MM-dd"/>" size="55" />
                  </td>
                  <td>&nbsp;</td> 
                </tr>
               <tr>
					<td height="20"> <s:text name="特别交通费"/> </td>
					<td> <s:textfield readonly="true" name="business.special"  size="50" /> &nbsp;RMB   </td>
					<td> &nbsp; </td>
				</tr> 
                
                 <tr>
                  <td>联系人姓名<span class="mainred" >*</span></td>
                  <td>
                    <s:textfield readonly="true" name="business.linkmanname"  size="55" />
                  </td>
                  <td>&nbsp;</td>
                </tr>
                 <tr>
                  <td>联系人电话<span class="mainred" >*</span></td>
                  <td>
                    <s:textfield readonly="true" name="business.phone"  size="55" />
                  </td>
                  <td>&nbsp;</td>
                </tr>
                
                <tr>
                  <td>联系人手机</td>
                  <td>
                    <s:textfield readonly="true" name="business.linkphone"  size="55" />
                  </td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>联系人传真</td>
                  <td>
                    <s:textfield readonly="true" name="business.fax"  size="55" />
                  </td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>电子邮件</td>
                  <td>
                    <s:textfield readonly="true" name="business.email"  size="55" />
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
					<td> <s:textfield readonly="true" name="business.invoicetitle"  size="55" /> </td>
					<td> &nbsp; </td>
				</tr>
				<tr>
					<td height="20"> <s:text name="邮寄地址"/> <span class="mainred" >*</span></td>
					<td>  <s:textfield readonly="true" name="business.invoiceaddress"  size="55" /> </td>
					<td> &nbsp; </td>
				</tr>
				<tr>
					<td height="20"> <s:text name="发票邮编"/><span class="mainred" >*</span> </td>
					<td> <s:textfield readonly="true" name="business.invoicepost"  size="55" /> </td>
					<td> &nbsp; </td>
				</tr>
				<tr>
					<td height="20"> <s:text name="发票收件人"/><span class="mainred" >*</span> </td>
					<td>  <s:textfield readonly="true" name="business.invoicer"  size="55" />  </td>
					<td> &nbsp; </td>
				</tr>
				<tr>
					<td height="20"> <s:text name="收件人联系电话"/><span class="mainred" >*</span> </td>
					<td> <s:textfield readonly="true" name="business.invoicetel"  size="55" />   </td>
					<td> &nbsp; </td>
				</tr>
                
                
                <tr>
                  <td height="20">检查机构</td>
                  <td><input name="certification" type="text" class="input" disabled="disabled" value="CCIC INSPECTION CO.,LTD" size="55" />
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
							<b><s:text name="申请人用户名"/></b> </td>
						<td>&nbsp;${business_accountname }</td>
						<td></td>
					</tr>	
				 
				</s:if> 
                
                <tr>
				<td width="180" height="20" colspan="3">
					&nbsp; 
				</td>
				</tr>
				<tr>
					<td width="180" height="20" colspan="3">
						<b><s:text name="分 配"></s:text> </b> 
				</td>
				</tr>
				
				
				 <tr>
                  <td height="20">授权签字人</td>
                  <td>  
                  	 <div class="allotclass" >
                  	 <s:iterator value="#request.list" id="ite"> 
                	<s:if test="#ite.ASSIGNROLE==1">
	                	<s:property value="#ite.ORG_NAME"/> - <s:property value="#ite.NAME"/>&nbsp;&nbsp;
                	</s:if>
	                </s:iterator>
	                </div>
	                </td>
	                <td>&nbsp;</td>
                </tr>	
                
                <tr>
                <td height="20">检查人</td>
                <td> 
               		 <div class="allotclass" >
	                <s:iterator value="#request.list" id="ite"> 
	                <s:if test="#ite.ASSIGNROLE==2"> 
			                  <s:property value="#ite.ORG_NAME"/> - <s:property value="#ite.NAME"/>&nbsp;&nbsp;<br/>
	                </s:if> 
	                </s:iterator>
	               </div>
                </td>
                <td>&nbsp;</td>
		        </tr>
				
                <tr>
                  <td height="100">&nbsp;详情</td>
                  <td>
                  	 <div class="allotclass" >
                  	<s:iterator value="#request.list" id="mop">
                  		<s:if test="#mop.ASSIGNERSTATE==0">
                  			<s:property value="#mop.ASSIGN_TIME"/> 由 系统管理员 开始<br/>
                  		</s:if>
                  		<s:if test="#mop.ASSIGNERSTATE==1">
                  	 	<s:property value="#mop.ASSIGN_TIME"/>		至 <s:property value="#mop.ORG_NAME"/> 的 <s:property value="#mop.NAME"/><br/>
                  		</s:if>
                  		<s:if test="#mop.ASSIGNERSTATE==2">
                  	 	<s:property value="#mop.ASSIGN_TIME"/>		至 <s:property value="#mop.ORG_NAME"/> 的 <s:property value="#mop.NAME"/><br/>
                  		</s:if>
                  		<s:if test="#mop.ASSIGNERSTATE==3">
                  		<s:property value="#mop.ASSIGN_TIME"/>		至 <s:property value="#mop.ORG_NAME"/> 的 <s:property value="#mop.NAME"/><br/>
                  		</s:if>
                  		<s:if test="#mop.ASSIGNERSTATE==4">
                  		<s:property value="#mop.ASSIGN_TIME"/>	至 <s:property value="#mop.ORG_NAME"/> 的 <s:property value="#mop.NAME"/><br/>
                  		</s:if>
                  	
                  	</s:iterator>
                  	</div>
                  </td>
                  <td>&nbsp;
                  
                  	<input	type="hidden" name="page_countrys" id="page_countrys" value="${page_countrys}"	/>
					<input	type="hidden" name="page_ports" id="page_ports" value="${page_ports}"	/>
					<input	type="hidden" name="page_selects" id="page_selects" value="${page_selects}"	/>
					<input type="hidden" name="select_proid" id="select_proid" value="${select_proid }"/> 
					<input type="hidden" name="select_cityid" id="select_cityid" value="${select_cityid }"/> 
					<input type="hidden" name="select_portid" id="select_portid" value="${select_portid }"/>
                  </td>
                </tr>
                
                <tr>
                  <td height="20">&nbsp;</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="38" align="left"></td>
                  <td height="38" align="left"> 
                      <input  name="input" class="button" type="reset" value="返 回"  onclick="history.back(-1)" />
                      </td>
                  <td align="left">&nbsp;</td>
                </tr>
              </table>
              <!--编辑结束-->
            </td>
          </tr>
        </table>
      </div></td>
  </tr>
</table>


 
 
<form action="allotlist" method="post" name="allotlistform" id="allotlistform">
	 
</form>

	</body>
</html>
