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
	
	
		$(document).ready(function(){	
			var city_hidden = $("#city_hidden").val(); 
			selectItem('cityId',city_hidden);
		});
	
	
       function changeAddressDiv(){
        	if(document.getElementById("radio2").checked){
        	//	$("#addressdiv2").show();
        	//	$("#addressdiv1").hide();
        	document.getElementById('addressdiv1').style.display="none";
        	document.getElementById('addressdiv2').style.display="block";
        	//	document.all.addressdiv1.style.display="none";
        	//	document.all.addressdiv2.style.display="block";
        		document.getElementById("nationId1").Value="";
        		document.getElementById("address1").Value="";
        		document.getElementById("proId").Value="";
        		document.getElementById("cityId").Value="";
        		
        	}
        	else if(document.getElementById("radio1").checked){
        	//	$("#addressdiv1").show();
        	//	$("#addressdiv2").hide();
        	document.getElementById('addressdiv2').style.display="none";
        	document.getElementById('addressdiv1').style.display="block";
        	//	document.all.addressdiv1.style.display="block"; 
        	//	document.all.addressdiv2.style.display="none";
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

function jump(){
	window.location="${pageContext.request.contextPath}/page/organization/getAllOrganizationByPage.action?goPage="+<s:property value="#session.currtPage"/>;
}
function selectOrgTree()
				{	
					var url = "getOrgList.action";
					window.open(url,"_blank","height=500px,width=200px,scrollbars=yes,top=200,left=500"); 
					
				}
function addport(){	
      
		document.forms[0].method="post";		
	 	document.forms[0].action ="${pageContext.request.contextPath}/page/organization/selectPort.action?oId="+<s:property value="#request.list.ID"/> ;
	 	document.forms[0].submit();	
	}	
function addsubmit(){
	var orgId=document.getElementById("orgId");	
	var orgSname=document.getElementById("orgSname");
	var orgFname=document.getElementById("orgFname");
	var contact=document.getElementById("contact");
	var tel=document.getElementById("tel");
	var fax=document.getElementById("fax");
	var email=document.getElementById("email");
	var nationId1=document.getElementById("nationId1");
	var proId=document.getElementById("proId");
	var cityId=document.getElementById("cityId");	
	
	
	var formatEmail = /^\s*([\w-]+(\.\w+)*@([\w-]+\.)+\w+)\s*$/; //email正则表达式
   	var formatZNEN = /^[a-zA-Z0-9_\u4e00-\u9fa5]+$/;//判断中英文的正则表达式
 	var formatPost = /[1-9]\d{5}(?!\d)/;//验证邮编
 	var formatPhone = /\d{3}-\d{8}|\d{4}-\d{7}|\d{3}-\d{7}|\d{4}-\d{8}/;
 	var formatMobile = /^0{0,1}(13[0-9]?|15[0-9]|18[0-9])[0-9]{8}$/;
 	
 	
	if(orgId.value==""){
		alert("机构编号不能为空!");
		orgId.focus();	
		return false;	
	}
	if(orgSname.value==""){	
		alert("公司简称不能为空!");
		orgSname.focus();
		return false;	
	}
	
	if(nationId1.value=="37"){
		if(proId.value==""){	
		alert("请选择省份!");
		return false;	
		}
	    if(cityId.value==""){
	    	alert("请选择城市!");
	    	return false;	
	    }		
	}
		
	if(email.value!=""){
	if(!formatEmail.test(email.value)){	
		alert("邮件格式不正确!");
		email.focus();
		return false;	
	}
	}	
	return true;
	}

</script>
</head>

<body>
<!--内容开始-->
<s:form action="update" theme="simple" id="update" enctype ="multipart/form-data" method="post">
<input type="hidden" name="oId" value="<s:property value="#request.list.ID"/>"/>
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
                      <input name="orgId" type="text" class="input" id="orgId" size="55" style="width:360px;"   value="<s:property value="#request.list.ORG_ID"/>"/>*
                    </label></td>
                	</tr>          
                    <tr>
                      <td height="20" align="left"><s:text name="companySName" /></td>
                      <td align="left"><input name="orgSname" id="orgSname" type="text" class="input"  style="width:360px;"   value="<s:property value="#request.list.ORG_SNAME"/>" size="55" />                      </td>
                    </tr>
                    <tr>
                      <td height="20" align="left"><s:text name="companyFName" /></td>
                      <td align="left"><input name="orgFname" id="orgFname" type="text" class="input"  style="width:360px;"   value="<s:property value="#request.list.ORG_FNAME"/>" size="55"  />                      </td>
                    </tr>
                    <tr>
                      <td height="20" align="left"><s:text name="contact" /></td>
                      <td align="left"><input name="contact" id="contact" type="text" class="input"  style="width:360px;"   value="<s:property value="#request.list.CONTACT"/>" size="55"  />                      </td>
                    </tr>

                    <tr>
                      <td height="20" align="left"> <s:text name="contacttel" /></td>
                      <td align="left"><input name="tel" id="tel" type="text" class="input"  style="width:360px;"   value="<s:property value="#request.list.TEL"/>" size="55" />                      </td>
                    </tr>
                     <tr>
                      <td height="20" align="left"> <s:text name="contactfax" /></td>
                      <td align="left"><input name="fax" id="fax" type="text" class="input"  style="width:360px;"   value="<s:property value="#request.list.FAX"/>" size="55" />                      </td>
                    </tr>
                    <tr>
                      <td height="20" align="left"><s:text name="contactemail" /></td>
                      <td align="left"><input name="email" id="email" type="text" class="input"  style="width:360px;"   value="<s:property value="#request.list.EMAIL"/>" size="55" /></td>
                    </tr>
                    <tr>
														
														<td height="20" align="left">
															<s:text name="companyAddress"/>
														</td>
														<td align="left">
															<table>
																<tr>
																	<td><div><input type="radio"" name="changeAddress" id="radio1" value="1" checked="checked" onclick="changeAddressDiv()" /></div></td>
																	<td><input type="hidden" name="nationId1" id="nationId1" value="37" /><s:text name="中国"/> &nbsp;</td>
																	<td><div><input type="radio"" name="changeAddress" id="radio2" value="2" onclick="changeAddressDiv()"/></div></td>
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
																		onchange="javascript:ajaxselect('getCity.action','proId','citydiv');" ></s:select>　<s:text name="Province"/>*
																		</td>
																		<td>
																		<div id="citydiv">
																			<s:select name="cityId" id="cityId" list="#request.cityList" listKey="CITYID" listValue="CITYNAME"   headerValue="-%{getText('请选择')}-" headerKey="-1"  >
																			</s:select>  
																			 <s:text name="City"/>*
																		</div>
																		<input type="hidden" name="city_hidden" id="city_hidden" value="<s:property value="#request.list.CITYID" />" />
																		</td>
																	</tr>
																	<tr>
																		<td colspan="2"><input type="text" size="65" name="address1" id="address1" value="<s:property value="#request.list.ADDRESS"/>" /></td>
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
																			></s:select>　<s:text name="nation"/>
																		</td>
																	</tr>
																	<tr>
																		<td>
																			<input type="text" size="65" name="address2" id="address2" value="<s:property value="#request.list.ADDRESS"/>" />
																		</td>
																	</tr>
																</table>
															</div>
														</td>
													</tr>  
					<tr>
                    <td height="20" align="left"> <s:text name="organizationType"/></td>
                    <td align="left">                    
                    <select name="organizationType" id="organizationType" style="width:360px;" >
                    	<option value="0" <s:if test="#request.list.IS_CHILD==0">selected</s:if>><s:text name="user_yzgs"/></option>
                    	<option  value="1" <s:if test="#request.list.IS_CHILD==1">selected</s:if>><s:text name="user_one"/></option>
                    	<option  value="2" <s:if test="#request.list.IS_CHILD==2">selected</s:if>><s:text name="user_two"/></option>
                    </select>*</td> 
                  </tr>
                  <tr>
                    <td height="20"><s:text name="belongOrganizaiton"/></td>
                    <td> 
                     <input id="selectOrgname2" name="selectOrgname2" type="text" size="50" disabled="disabled" value="${selectOrgname }" />
							                <input type="button" class="button" onclick="selectOrgTree()"  value="<s:text name="choose"/>" />* </td>
                  </tr> 
                  <tr>
                    <td height="20">&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr> 
                  <tr>
                    <td height="20" align="left"><s:text name="bankaccount"/></td>
                    <td align="left"><input name="bankaccount" id="bankaccount" type="text" class="input" size="55" value="<s:property value="#request.list.BANKACCOUNT"/>"/></td>
                  </tr>
                  <tr>
                    <td height="20" align="left"><s:text name="vbrk"/></td>
                    <td align="left"><input name="vbrk" id="vbrk" type="text" class="input" size="55" value="<s:property value="#request.list.VBRK"/>"/></td>
                  </tr>                      
                     <tr>
                      <td height="20" valign="top"><s:text name="manageport" /></td>
                      <td align="left"><table width="100%" class="tableCont" style="width:360px;">
                          <tr>
                            <td><div class="tableContent">
                                <table width="100%" border="0" cellspacing="1" cellpadding="0">
                                  
                                    <tr>
                                      <th ><s:text name="cityfull" /></th>
                                      <th ><s:text name="portID" /></th>
                                      <th><s:text name="ChineseName" /></th>
                                      <th><s:text name="EnglishName" /></th>
                                      <th><s:text name="operation" /></th>
                                    </tr>
                                   <s:if test="#request.portList.size>0">
    								<s:iterator value="#request.portList" status="stat">
    								<tr>
    								<td><s:property value="CITYNAME"/></td>
    								<td><s:property value="PORT_ID"/></td>
    								<td><a href="${pageContext.request.contextPath}/page/organization/getPortById.action?portId=<s:property value="ID"/>"><s:property value="PORT_NAME"/></a></td>
    								<td><s:property value="PORT_SNAME"/></td>
    								<td><a href="${pageContext.request.contextPath}/page/organization/getPrePortById.action?portId=<s:property value="ID"/>"><s:text name="update"/></a>&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/page/organization/deletePort.action?portId=<s:property value="ID"/>" onclick="return confirm('你确定删除此港口吗？')"><s:text name="delete"/></a></td>
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
                      <input type="submit"  class="button_s" value="<s:text name="savebutton" />" onclick="return addsubmit()"/>
                      </c:if>
                      </c:forEach>
                      <input type="button" class="button_s" value="<s:text name="addport"/>" onclick="addport();" />
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
</s:form>
</body>
</html>
