<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
 	isELIgnored="false"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统</title>
		<%@ include file="../include/head.jsp"%>
		<link rel=stylesheet href="<%=basePath %>style/index.css" type="text/css" />
		<link rel=stylesheet href="<%=basePath %>style/right.css" type="text/css" />
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>/js/business/validata.js"></script>  
		<script language="text/javascript" type="text/javascript"> 
 		 $(document).ready(function(){
		 	 var portcity_hidden = $("#portcity_hidden").val();
		 	 if(portcity_hidden!=null&&portcity_hidden!=""){
		 		selectItem('portcity_form',portcity_hidden);
			 } 
 		 	 $("#search_1").click(function(){
 		 	 	var n = document.getElementById("appno_form");
					var v = n.value;
					var vs = v.substring(12);
					if(v.length != 0 && (v.length != 16 || (vs != '-AGM' && vs != '-agm')) ) {
						alert("您输入的申请编号有错误");
						return false;
					}
				 	document.certificateform.action = "certificate.action";
					document.certificateform.submit();  
			 });
 		 
 		 	var tagtype = $("#tagtype").val();
 		 	if(tagtype==null||tagtype==""||tagtype=="0"){
 		 		$("#li1").removeClass("normal"); 
 		 		$("#li1").addClass("active");
 		 		$("#li2").addClass("normal");
 		 		$("#li3").addClass("normal");
 		 	}
 		 	if(tagtype=="1"){
 		 		$("#li1").addClass("normal");
 		 		$("#li2").removeClass("normal"); 
 		 		$("#li2").addClass("active");
 		 		$("#li3").addClass("normal");
 		 	}
			if(tagtype=="2"){
 		 		$("#li1").addClass("normal");
 		 		$("#li2").addClass("normal");
 		 		$("#li3").removeClass("normal"); 
 		 		$("#li3").addClass("active"); 
 		 	}
 		 
		});
		 
		function nTabs(thisObj,Num){
			if(thisObj.className == "active")
				return;
			changeHtml(Num);	
			var tabObj = thisObj.parentNode.id;
			var tabList = document.getElementById(tabObj).getElementsByTagName("li");
			for(i=0; i <tabList.length; i++){
				if (i == Num){
			   		thisObj.className = "active"; 
				}else{ 
			   		tabList[i].className = "normal"; 
				}
			} 
			
		}
	 
		
		function changeHtml(tagid){
			document.getElementById("certificateform").action = "certificate.action?tagtype="+tagid; 
			document.getElementById("certificateform").submit(); 
		} 
		
		function createEmptycert()
		{
			document.getElementById("certificateform").action = "certificateEmptySend.jsp";
			document.getElementById("certificateform").submit(); 
			
		}

		function emptysubmit()
		{
			document.getElementById("certificateform").action = "searchEmpty.action"; 
			document.getElementById("certificateform").submit();   
		}
		
		function openurl(url)
		{
			document.getElementById("certificateform").action = url; 
			document.getElementById("certificateform").submit(); 
		} 
		
		function editcert(businessid)
		{
			document.getElementById("businessid").value= businessid; 
			document.getElementById("certificateform").action = "lookCert.action"; 
			document.getElementById("certificateform").submit();   
		}
		
		
		function editStamp(id)
		{
			document.getElementById("orgid").value= id; 
			document.getElementById("certificateform").action = "stamp.action"; 
			document.getElementById("certificateform").submit();   
		}
		
		function createStamp()
		{
			document.getElementById("certificateform").action = "createstamp.action"; 
			document.getElementById("certificateform").submit();
		
		}
		function allotView(id)
		{
			var url = "getAllotPageView.action?id="+id;
			window.open(url,"_blank");
		}
		
		function certinfo(id)
		{
			document.getElementById("orgid").value= id; 
			document.getElementById("certificateform").action = "certinfo.action"; 
			document.getElementById("certificateform").submit();
		}
		
		
		</script> 
	</head>

<body>
 
 <table border="0" cellspacing="0" cellpadding="0" class="contenttab">
  <tr>
    <td class="list_r">&nbsp;</td>
    <td valign="top" class="right"><div class="title">您的当前位置： 首页 &gt;&gt; 业务管理 &gt;&gt; <strong>证书管理</strong></div>
      <div class="TabTitle">
        <ul id="myTab0">
	       	 <c:forEach items="${menu_parent}" var="mp3" varStatus="status">
				<c:if test="${mp3.rightId == 441}"> 
					 <li class="normal" id="li1" onclick="nTabs(this,0);">证书编制</li>
				</c:if>
				<c:if test="${mp3.rightId == 442}"> 
					 <li class="normal" id="li2" onclick="nTabs(this,1);">空白证书管理</li>
				</c:if>
				<c:if test="${mp3.rightId == 443}"> 
 					 <li class="normal" id="li3" onclick="nTabs(this,2);">印章管理</li>  
				</c:if>
			</c:forEach>
         
          
          
        </ul>
      </div>
      
      <div class="TabContent">
          <table border="0" align="center" cellpadding="0" cellspacing="0" class="table_qkb">
            <tr>
              <td valign="top">
              	<br/>
              	<form id="certificateform" name="certificateform" method="post" action="certificateform.action">
              	  <input type="hidden" name="tagtype" id="tagtype" value="${tagtype }"/>
              	  <input type="hidden" name="businessstate_search" id="businessstate_search" value="4" />
              	  <input type="hidden" name="businessid" id="businessid" />
                 <input type="hidden" name="orgid" id="orgid" />
                 	
                 	<s:if test="#request.tagtype==0">
                 		<div class="TabContent">
                 			<table width="100%" border="0" cellpadding="6" cellspacing="0" class="tabman">
					          <tr>
					          	<td height="35" align="center">
					          		 <s:text name="申请编号"/>: &nbsp; <input type="text" name="appno_form" id="appno_form" value="${appno_form }" />
					          		&nbsp;  &nbsp;
					          		 <s:text name="船名"/>: &nbsp; <input type="text" name="vesselname_form" id="vesselname_form" value="${vesselname_form }" />
					          		&nbsp;  &nbsp; <s:text name="港口城市"/>: &nbsp; 
					          		 <s:select list="#request.portcitylist" name="portcity_form" id="portcity_form" headerKey="-1" headerValue="- %{getText('请选择')} -"  listKey="CITYNAME" listValue="CITYNAME" />
									&nbsp;  &nbsp;<input name="search_1" id="search_1" type="button" class="button" value="<s:text name='查询'/>" />
					          		<input type="hidden" name="portcity_hidden" id="portcity_hidden" value="${portcity_form}" />
					          	</td>
					          </tr>
					      </table> 
                 		</div>
                 	</s:if>
                 	<s:elseif test="#request.tagtype==1">
                 		<font style="color: red;">&nbsp;&nbsp;${message}</font> 
                 		<div class="TabContent">
                 		
                 		 <table width="100%" border="0" cellpadding="6" cellspacing="0" class="tabman">
					       <tr>
					         <td height="35" align="center">公司名称:&nbsp;
					           <input name="businessname_search" type="text" class="input" size="17" value="${businessname_search }"/>
					           &nbsp;
					           &nbsp;&nbsp;
					         <input name="search" id="search" type="button" onclick="javascript:emptysubmit();" class="button" value="查 询" /></td>
					       </tr>
						 </table>  
						 </div>
                 	</s:elseif>
				
              </form>
              
              	<div class="tableContent"> 
              	  	
                        <table width="100%" border="0" cellspacing="1" cellpadding="0" id="certificateTable">
	                       
	                       <s:if test="#request.tagtype==0">
		                         <tr>
		                          <th>申请编号</th>
		                          <th>申请人</th>
		                          <th>船名</th>
	                              <th>港口城市</th>
	                              <th>操作</th>
	                            </tr>
	                            <s:iterator value="#request.list" id="mop">
		                            <tr>
		                              <td height="30px;" align="center">&nbsp;
		                              	 <s:if test="#mop.CERTID==null||#mop.CERTID==''">
 											<a href="#" onclick="editcert(<s:property value="#mop.ID" />);"><font color="blue"><s:property value="#mop.APPNO" /></font></a>
										</s:if><s:else>
											 <a href="#" onclick="allotView(<s:property value="#mop.ID"/>);"><font color="blue"><s:property value="#mop.APPNO" /></font></a>
		                              		
		                              	 </s:else>
		                              </td>
		                              <td align="center"> &nbsp; <s:property value="#mop.BUSINESSNAME" /></td> 
		                              <td align="center"> &nbsp; <s:property value="#mop.VESSELNAME"/> </td>
		                              <td align="center"> &nbsp; <s:property value="#mop.PORTCITY"/></td>
		                              <td align="center"> 
		                              	 <s:if test="#mop.CERTID==null||#mop.CERTID==''">
 											<a href="#" onclick="editcert(<s:property value="#mop.ID" />);">编制证书</a>
										</s:if><s:else>
		                              		<a href="#" onclick="editcert(<s:property value="#mop.ID" />);">已编制证书</a>
		                              	 </s:else>
		                                  	 <a href="#" onclick="allotView(<s:property value="#mop.ID"/>);">预览</a>
		                              </td>
		                            </tr>
	                            </s:iterator> 
	                       </s:if>
	                       <s:elseif test="#request.tagtype==1">
		                       	 <tr>
								 	<td colspan="4" align="right" style="background-color: #E5EFF8;">
								 		<s:if test="#request.is_child==0">	<input type="button" class="button_s" id="empty_cert"  onclick="createEmptycert();" value="创建空白证书" /></s:if>
								 		<s:elseif test="#request.is_child==1"><input type="button" class="button_s" id="empty_cert"  onclick="javascript:openurl('certificateEmptylist.action');" value="发放证书" /></s:elseif>   
								 	</td>
							 	  </tr>
		                         <tr>
		                          <th>公司名称</th>
	                              <th>证书种类</th>
	                              <th>证书数量</th>
	                              
	                            </tr>
	                            <s:iterator value="#request.list" id="mop">
		                            <tr>
		                              <td height="30px;" align="center">
		                              <a href="#" onclick="javascript:certinfo('<s:property value="#mop.ORG_ID"/>');"><font color="blue"> <s:property value="#mop.ORG_NAME"/></font></a>
		                               </td> 
		                              <td align="center">
		                              	<div>正本</div>
		                              	<div>副本</div>
		                              </td>
		                              <td align="center">
										<div><s:property value="#mop.CN1"/></div>
		                              	<div><s:property value="#mop.CN2"/></div>
									  </td> 
		                            </tr>
	                            </s:iterator> 
	                           
	                       </s:elseif><s:elseif test="#request.tagtype==2">
	                       		<td colspan="4" align="right" style="background-color: #E5EFF8;">
								 		<s:if test="#request.is_child==0">	<input type="button" class="button_s" id="empty_cert"  onclick="createStamp();" value="新建印章" /></s:if>   
								 	</td>
		                         <tr>
		                          <th>机构名称</th>
		                          <th>印章ID</th>
	                              <th>操作</th>
	                            </tr>
	                            <s:iterator value="#request.list" id="mop">
		                            <tr>
		                            
		                            	  <td align="center"><s:property value="#mop.ORG_SNAME"/> </td>
		                            	<td align="center">
		                              	<s:if test="#mop.STAMP==''">暂无</s:if>
		                              	<s:else>&nbsp;<s:property value="#mop.STAMP"/></s:else> 
		                              	</td>
		                            
									  <td align="center">
									  	<a href="#" onclick="editStamp('<s:property value="#mop.ORGID"/>');">编辑</a>
									  </td> 
		                            </tr>
	                            </s:iterator> 
	                           
	                       </s:elseif>
	                       
	                       
                        </table> 
                   
                </div>
                </td>
                <td valign="bottom">
                	<tr>
                	
                	<table width="98%" height="35" border="0" cellpadding="0"
									cellspacing="0">
					<tr>
						<td height="25" align="right" class="right_tb1" colspan="3">
							<!--分页  -->
							<s:component template="pagev2.jsp" templateDir="page/pageutil"
								theme="template">
								<!--action name  -->
								<s:param name="formId" value="'certificateform'" />
								<s:param name="action_name" value="actionName" />
								<!-- name space -->
								<s:param name="namespace" value="'/page/business'" />
								<!-- pagination method name -->
								<s:param name="pagination_method_name" value="actionName" />
								<!--当前页前后显示的数量  -->
								<s:param name="distanceNum" value="5" /> 
							</s:component>
						</td> 
					</tr>
				</table>
                </tr>
                
            
          </table>
    </div></td></tr></table>
 
         
</body>
</html>
