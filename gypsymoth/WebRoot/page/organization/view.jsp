<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>中检集团船舶舞毒蛾检查业务信息系统</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
<link rel=stylesheet href="${pageContext.request.contextPath}/style/index.css" type="text/css" />
<link rel=stylesheet href="${pageContext.request.contextPath}/style/right.css" type="text/css" />
	<link rel="StyleSheet" href="${pageContext.request.contextPath}/style/dtree.css" type="text/css" />
	<%@ include file="../include/head.jsp"%> 
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajax.js"></script>
	<script type="text/javascript">	
	function updateorganization(){	
	document.forms[0].method="post";
 	document.forms[0].action ="${pageContext.request.contextPath}/page/organization/getOrganizationPre.action?orgId="+<s:property value="#request.list.ID"/> ;
 	document.forms[0].submit();	
	}	
</script>
<script type="text/javascript">
function jump(){
	window.location="${pageContext.request.contextPath}/page/organization/getAllOrganizationByPage.action?goPage="+<s:property value="#session.currtPage"/>;
}
</script> 
<script type="text/javascript" charset="utf-8">

		$(document).ready(function(){	
			var city_hidden = $("#city_hidden").val();
			selectItem('cityId',city_hidden);
		});
	
	
       function changeAddressDiv(){
        	if(document.getElementById("radio2").checked){
        		document.all.addressdiv1.style.display="none";
        		document.all.addressdiv2.style.display="block";
        		document.getElementById("nationId1").Value="";
        		document.getElementById("address1").Value="";
        		document.getElementById("proId").Value="";
        		document.getElementById("cityId").Value="";
        		
        	}
        	else if(document.getElementById("radio1").checked){
        		document.all.addressdiv1.style.display="block";
        		document.all.addressdiv2.style.display="none";
        		document.getElementById("nationId2").Value="";
        		document.getElementById("address2").Value="";
        	}
        }       
        
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
<script type="text/javascript">
			function selectOrgTree()
				{	
					var url = "getOrgList.action";
					window.open(url,"_blank","height=500px,width=200px,scrollbars=yes,top=200,left=500"); 
					
				}
</script>
</head>

<body>
<!--内容开始-->
<form id="org" name="org" method="post" action="">
<input type="hidden" name="orgId" value="<s:property value="#request.list.ID"/>"/>
<input id="selectOrgid" name="selectOrgid" type="hidden" value="${selectOrgid }"/>
<input id="selectOrgname" name="selectOrgname" type="hidden" value="${selectOrgname }"/>
<input type="hidden" name="goPage" value="<s:property value="#session.currtPage"/>" />
<table border="0" cellspacing="0" cellpadding="0" class="contenttab"> 
   <tr>
    <td class="list_r">&nbsp;</td>
    <td valign="top" class="right"><div class="title"><s:text name="local" />： <s:text name="home" /> &gt;&gt; <s:text name="orgAndPerson" /> &gt;&gt; <strong><s:text name="OrganizationManage" /></strong></div>
      <div>
        <table width="100%" align="center" cellpadding="0" cellspacing="5">
          <tr>
            <td align="left" valign="top" class="tabedit1" style="width:0px;">
            <%@ include file="orgTree.jsp"%>

            </td>
            <td align="left" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                
                <tr>
                  <td height="460" valign="top" class="tabedit2"><table width="550" align="center" cellspacing="5">
                    <tr>
                    <td width="60" height="20" align="left"><s:text name="orgId"/></td>
                    <td width="362" align="left"><label>
                      <input name="orgId" type="text" class="input" id="orgId" size="55" style="width:360px;"  disabled="true" value="<s:property value="#request.list.ORG_ID"/>"/>*
                    </label></td>
                	</tr>          
                    <tr>
                      <td height="20" align="left"><s:text name="companySName" /></td>
                      <td align="left"><input name="orgSname" type="text" class="input"  style="width:360px;" disabled="true" value="<s:property value="#request.list.ORG_SNAME"/>" size="55" style="width:335px;" />                      </td>
                    </tr>
                    <tr>
                      <td height="20" align="left"><s:text name="companyFName" /></td>
                      <td align="left"><input name="orgFname" type="text" class="input" style="width:360px;" style="width:360px;"disabled="true" value="<s:property value="#request.list.ORG_FNAME"/>" size="55" style="width:335px;" />                      </td>
                    </tr>
                    <tr>
                      <td height="20" align="left"><s:text name="contact" /></td>
                      <td align="left"><input name="contact" type="text" class="input" style="width:360px;" disabled="true" value="<s:property value="#request.list.CONTACT"/>" size="55" style="width:335px;" />                      </td>
                    </tr>

                    <tr>
                      <td height="20" align="left"> <s:text name="contacttel" /></td>
                      <td align="left"><input name="tel" type="text" class="input"  style="width:360px;" disabled="true" value="<s:property value="#request.list.TEL"/>" size="55" style="width:335px;"/>                      </td>
                    </tr>
                     <tr>
                      <td height="20" align="left"> <s:text name="contactfax" /></td>
                      <td align="left"><input name="fax" type="text" class="input" style="width:360px;" disabled="true" value="<s:property value="#request.list.FAX"/>" size="55" style="width:335px;"/>                      </td>
                    </tr>
                    <tr>
                      <td height="20" align="left"><s:text name="contactemail" /></td>
                      <td align="left"><input name="email" type="text" class="input" style="width:360px;" disabled="true" value="<s:property value="#request.list.EMAIL"/>" size="55" style="width:335px;"/></td>
                    </tr>
                    <tr>
														
														<td height="20" align="left">
															<s:text name="companyAddress"/>
														</td>
														<td align="left">
															<table>
																<tr>
																	<td><div><input type="radio"" name="changeAddress" id="radio1" value="1" disabled="true" checked="checked" onclick="changeAddressDiv()" /></div></td>
																	<td><input type="hidden" name="nationId1" id="nationId1" value="37" /><s:text name="中国"/> &nbsp;</td>
																	<td><div><input type="radio"" name="changeAddress" id="radio2" value="2" disabled="true" onclick="changeAddressDiv()"/></div></td>
																	<td><s:text name="othercountry"/></td>
																</tr>
															</table>
															<div id="addressdiv1" style="display: block">
																<table>
																	<tr>
																		<td>
																		<s:select list="#request.promaryList" name="proId"
																		id="proId" listKey="PROID" listValue="PRONAME"
																		headerKey="0" headerValue="-%{getText('请选择')}-" value="#request.list.PROID"
																		onchange="javascript:ajaxselect('getCity.action','proId','citydiv');" disabled="true"></s:select>　<s:text name="Province"/>*
																		</td>
																		<td>
																		<div id="citydiv">
																			<s:select name="cityId" id="cityId" list="#request.cityList" listKey="CITYID" listValue="CITYNAME"   headerValue="-%{getText('请选择')}-" headerKey="-1"  disabled="true">
																			</s:select>  
																			 <s:text name="City"/>*
																		</div>
																		<input type="hidden" name="city_hidden" id="city_hidden" value="<s:property value="#request.list.CITYID" />" />
																		</td>
																	</tr>
																	<tr>
																		<td colspan="2"><input type="text" size="65" name="address1" id="address1" value="<s:property value="#request.list.ADDRESS"/>" disabled="true"/></td>
																	</tr>
																</table>
															</div>
															<div id="addressdiv2" style="display: none">
																<table>
																	<tr>
																		<td>
																			<s:select list="#request.nationList" name="nationId2"
																			id="nationId2" listKey="NATIONID" listValue="NATIONNAME"
																			headerKey="0" headerValue="-%{getText('请选择')}-"  																			
																			disabled="true"></s:select>　<s:text name="nation"/>
																		</td>
																	</tr>
																	<tr>
																		<td>
																			<input type="text" size="65" name="address2" id="address2" value="<s:property value="#request.list.ADDRESS"/>" disabled="true"/>
																		</td>
																	</tr>
																</table>
															</div>
														</td>
													</tr>  
					<tr>
                    <td height="20" align="left"> <s:text name="organizationType"/></td>
                    <td align="left">                    
                    <select name="organizationType" style="width:360px;" disabled="true">
                    	<option value="0" <s:if test="#request.list.IS_CHILD==0">selected</s:if>><s:text name="user_yzgs"/></option>
                    	<option  value="1" <s:if test="#request.list.IS_CHILD==1">selected</s:if>><s:text name="user_one"/></option>
                    	<option  value="2" <s:if test="#request.list.IS_CHILD==2">selected</s:if>><s:text name="user_two"/></option>
                    </select>*</td> 
                  </tr>
                  <tr>
                    <td height="20"><s:text name="belongOrganizaiton"/></td>
                    <td> 
                     <input id="selectOrgname2" name="selectOrgname2" type="text" size="50" disabled="disabled" value="${selectOrgname }" disabled="true"/>
							                <input type="button" class="button" onclick="selectOrgTree()" disabled="true" value="<s:text name="choose"/>" />* </td>
                  </tr> 
                  <tr>
                    <td height="20">&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>   
                  <tr>
                    <td height="20" align="left"><s:text name="bankaccount"/></td>
                    <td align="left"><input name="bankaccount" id="bankaccount" type="text" class="input" size="55"  disabled="disabled"  value="<s:property value="#request.list.BANKACCOUNT"/>"/></td>
                  </tr>
                  <tr>
                    <td height="20" align="left"><s:text name="vbrk"/></td>
                    <td align="left"><input name="vbrk" id="vbrk" type="text" class="input" size="55"  disabled="disabled"  value="<s:property value="#request.list.VBRK"/>"/></td>
                  </tr>                    
                     <tr>
                      <td height="20" valign="top"><s:text name="manageport" /></td>
                      <td align="left"><table width="100%" class="tableCont" style="width:360px;">
                          <tr>
                            <td><div class="tableContent">
                                <table width="100%" border="0" cellspacing="1" cellpadding="0" >
                                  
                                    <tr>
                                      <th ><s:text name="cityfull" /></th>
                                      <th ><s:text name="portID" /></th>
                                      <th><s:text name="ChineseName" /></th>
                                      <th><s:text name="EnglishName" /></th>                                       
                                    </tr>
                                   <s:if test="#request.portList.size>0">
    								<s:iterator value="#request.portList" status="stat">
    								<tr>
    								<td><s:property value="CITYNAME"/></td>
    								<td><s:property value="PORT_ID"/></td>
    								<td><a href="${pageContext.request.contextPath}/page/organization/getPortById.action?portId=<s:property value="ID"/>"><s:property value="PORT_NAME"/></a></td>
    								<td><s:property value="PORT_SNAME"/></td>    								
    								</tr>
    								</s:iterator>
    								</s:if>
                                 	
                                </table>
                            </div></td>
                          </tr>
                      </table></td>
                    </tr>
                    
                    <tr>
                      <td height="38" align="left"></td>
                      <td height="38" align="left">
                      <c:forEach items="${menu_parent}" var="mp3" varStatus="status">
                      <c:if test="${mp3.rightId == 312}">
                      <input type="button"  class="button_s" value="<s:text name="update" />" onclick="updateorganization()"/>
                      </c:if>
                      </c:forEach>
                      <input type="button" onclick="jump()" class="button_s" value="<s:text name="return" />" />
                       
                  </td>
                </tr>
            </table></td>
          </tr>
        </table>
        
</td>
  </tr>
</table>
</div>
</td>
</tr>
</table>
</form>
</body>
</html>
