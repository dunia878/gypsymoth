<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
 	isELIgnored="false"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<s:head theme="ajax"/>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统</title>
		<%@ include file="../include/head.jsp"%> 
		<link rel="StyleSheet" href="<%=basePath %>style/dtree.css" type="text/css" />
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=basePath %>js/dtree.js"></script> 
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>/js/business/validata.js"></script>  
		<script type="text/javascript" language="javascript">
		var date = new Date();
		var timestamp = date.getTime();
		$(document).ready(function(){
			$("#cert_toallot").click(function(){
				$("#cert_toallot").val("提交中..");
				$("#cert_toallot").attr("disabled","disabled"); 
				if(!valiinput()){ 
					$("#cert_toallot").val("确 定");
					$("#cert_toallot").attr("disabled",false); 
					return false; 
				} 
				$("#certnolist_select option").attr("selected",true); 
				document.getElementById("cert_emptyform").action = "toAllotCertNo.action";
				document.getElementById("cert_emptyform").submit();
			});
			
		});
	 	
	 	function valiinput()
	 	{
	 		var regcertm = /^[0-9]+$/; 
	 		var certtype = $("#certtype").val();
	 		var orgto_name = $("#orgto_name").val();
	 		var datefrom = $("#datefrom").val();
	 		var certcount =$("#certcount").val();
	 		var certname_select = $("#certname_select").val();
	 		var certnolist_select =  document.getElementById("certnolist_select");
	 		
	 		if(certtype==null||certtype==""||certtype=="-1"){
	 			alert("发放流水号类型不能为空");
	 			document.getElementById("certtype").focus();
	 			return false;
	 		}else if(certtype=="0"){ 
		 		if(orgto_name==null||orgto_name==""){
		 			alert("目标分配机构不能为空");
		 			document.getElementById("orgto_name").focus();
		 			return false;
		 		}
		 		if(datefrom==null||datefrom==""){
		 			alert("发放日期不能为空");
		 			document.getElementById("datefrom").focus();
		 			return false;
		 		}
		 		if(certname_select==null||certname_select=="-1"){
		 			alert("流水号区间不能为空，请确认是否还有可用流水号区间");
		 			document.getElementById("certname_select").focus();
		 			return false;
		 		} 
		 		if(certnolist_select==null||certnolist_select.length==0){
		 			alert("分配流水号不能为空");
		 			document.getElementById("certnolist_select").focus();
		 			return false;
		 		}  
		 	}else{
		 		if(orgto_name==null||orgto_name==""){
		 			alert("目标分配机构不能为空");
		 			document.getElementById("orgto_name").focus();
		 			return false;
		 		}
		 		if(datefrom==null||datefrom==""){
		 			alert("发放日期不能为空");
		 			document.getElementById("datefrom").focus();
		 			return false;
		 		}
		 		if(certcount==null||certcount==""){
		 			alert("发放数量不能为空");
		 			document.getElementById("certcount").focus();
		 			return false;
		 		}
		 		if(!regcertm.test(certcount)){
		 			alert("发放数量只能为数字");
		 			document.getElementById("certcount").focus();
		 			return false;
		 		}
		 	}
	 		return true; 
	 	}
	 	
	 	function chooseORG(name,id)
	 	{
	 		$("#orgto").val(id);
	 		$("#orgto_name").val(name); 
	 	}
	 	
	 	function changeCerttype()
	 	{
	 		var certtype = $("#certtype").val();
	 		if(certtype=="0"){
	 			$("#certtr1").show();
	 			$("#certtr2").show(); 
	 			$("#certtr3").hide();
	 		}else if(certtype=="1"){
	 			$("#certtr1").hide();
	 			$("#certtr2").hide(); 
	 			$("#certtr3").show();
	 			
	 		} 
	 		 
	 	}
	 	
	 	
	 	function changeSelect()
	 	{
	 		var certname_select = $("#certname_select").val();
	 		var url = "certificateContent.action?certname="+certname_select+"&tid="+timestamp;
	 		ajax_selectContent(url,'certnolist');
	 	} 
	 	
	 	function moveSingle(t1, t2) 
	 	{
	 		var n1 = document.getElementById(t1);
	 		var n2 = document.getElementById(t2); 
			while (n1.selectedIndex != -1) {
				var indx = n1.selectedIndex;
				var t = n1.options[indx].text;
				var id = n1.options[indx].value;
				n2.options.add(new Option(t, id));
				n1.remove(indx);
			}
		}
			
		function moveAll(t1, t2) {
			var n1 = document.getElementById(t1);
	 		var n2 = document.getElementById(t2); 
			for (var i = 0; i < n1.length; i++) {
				var t = n1.options[i].text;
				var id = n1.options[i].value;
					n2.options.add(new Option(t, id));
					n1.remove(i);
					i--;
				}
		}
	 	
	 
	</script>	
		

<body>
<s:action name="certificateEmptySend"></s:action>

 <form id="cert_emptyform" name="cert_emptyform" action="toCreateEmptyCert.action">
 <input type="hidden" name="tagtype" id="tagtype" value="1" />
<% List list = (ArrayList)request.getAttribute("list"); %>

<!--内容开始-->
<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
  <tr>
    <td class="list_r">&nbsp;</td>
    <td valign="top" class="right">
    	<div class="title">您的当前位置： 首页 &gt;&gt; 业务管理 &gt;&gt; 证书管理 &gt;&gt;<strong><s:text name="certificate_sendemt">发放空白证书</s:text></strong>
    	</div>
      	
      
      	<div>
        <table width="100%" align="center" cellpadding="0" cellspacing="5">
          <tr>
            <td align="left" valign="top" class="tabedit1" style="width:170px;">
	            <div class="dtree">
				<p><a href="javascript: d.openAll();">打开全部</a> | <a href="javascript: d.closeAll();">关闭全部</a></p>
	
				<script type="text/javascript" language="javascript">
					d = new dTree('d');
					d.add(0,-1,'组织结构树');
					<%	if(list!=null&&list.size()>0){
						for(int i=0;i<list.size();i++){
					 		HashMap map = (HashMap) list.get(i);
					 		String rn = (String)map.get("RN").toString();
					 		String id =  map.get("ID").toString();  
					 		String parentid = (String)map.get("PARENT_ID");  
					 		String org_name = (String)map.get("ORG_NAME");
					 		String script = "javascript:chooseORG('"+org_name+"',"+id+");";  
					 		out.write("d.add("+rn+","+parentid+",'"+org_name+"',\""+script+"\");");  
					 	} 
					 } %> 
					document.write(d);
				</script>
				</div>
            </td>
            
            <td align="left" valign="top">
            
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td height="460" valign="top" class="tabedit2"><table width="100%" class="tableheader">
                      <tr>
                        <td width="5%" height="26"><img src="<%=basePath%>images/bg_mcontentL.gif"  width="32" height="27" /></td>
                        <td valign="middle"><h3><s:text name="certificate_sendemt">发放证书</s:text> </h3>	</td>
                        <td align="right" valign="middle"></td>
                        <td width="1%" align="right"><img src="<%=basePath%>images/bg_mcontentR.gif"  width="7" height="27" /></td>
                      </tr>
                    </table>
                    <table width="100%" class="tableCont">
                      <tr>
                        <td valign="top">
                        
                        <div class="tableContent" id="div_list">
                          <table width="100%" border="0" cellspacing="1" cellpadding="0" style="height: 300px;">
                              
                              <tr style="height: 80%">
                                <td height="90%" align="center" colspan="6">
                                	 
                                	 
                                	 
                                	  <table width="100%" align="center" cellspacing="5">
                                	  		
                                	  		<tr>
                                	  		  <td width="30%" height="0" ></td> 
							                  <td width="50%"></td> 
							                  <td width="20%"></td>
                                	  		</tr>
                                	  		<tr>
							                  <td align="center" style="color: red;" colspan="3">${message }</td> 
							                </tr>
							                
							                <tr>
							                  <td height="20">证书种类 <br /></td>
							                  <td align="left">
							                  	<select id="certtype" name="certtype" onchange="javascript:changeCerttype()">
							                  		<option value="-1">- 请选择 -</option>
							                  		<option value="0">正本</option>
							                  		<option value="1">副本</option>
							                  	</select>
							                  </td>
							                  <td>&nbsp;</td>
							                </tr>
							                
							                <tr>
							                  <td height="20"><s:text name="certificate_org">目标分配机构</s:text></td>
							                  <td align="left"><s:textfield id="orgto_name" name="orgto_name" readonly="true" size="35"></s:textfield> 
							                 	</td>
							                  <td>&nbsp;*请点击左侧树来选择分配机构</td>
							                </tr>
                                	  
                                	   		<tr>
							                  <td height="20"><s:text name="certificate_org">发放人</s:text></td>
							                  <td align="left"><input name="personname" id="personname" value="${personname }" readonly="readonly" size="35" />
							                 	</td>
							                  <td>&nbsp;</td> 
							                </tr> 
							                
							                 <tr>
							                  <td height="20"><s:text name="certificate_org">发放日期</s:text></td>
							                  <td align="left">
												<input class="Wdate" type="text" id="datefrom"
														name="datefrom" onfocus="WdatePicker()" />
							                 	</td>
							                  <td>&nbsp;</td>
							                </tr> 
							                
							                
							                 <tr style="height: 20px" id="certtr1">  <td height="20">可用流水号区间</td>
							                  <td align="left">
							                  	<s:select list="#request.selectlist" listKey="CERTNAME" listValue="CERTNAME" id="certname_select" name="certname_select"
							                  		onchange="changeSelect();" headerKey="-1" headerValue="- 请选择 -">
							                  	</s:select>
							                  </td>
							                  <td>&nbsp;</td> </tr> 
							                  
							                  
							                  <tr id="certtr2">
							                  <td height="20">流水号列表</td>
							                  <td align="left">
							                  	<table width="100%" border="0">
							                  		<tr align="left">
							                  			<td>
							                  				<select name="certnolist" id="certnolist" style="width: 80px;" multiple="multiple" size="10">
															</select>
							                  			</td>
							                  			<td valign="middle">
							                  					<input type="button" value=" > " class="button"
																	onclick="moveSingle('certnolist','certnolist_select');" />
																<br />
																<input type="button" value=">>" class="button"
																	onclick="moveAll('certnolist','certnolist_select');" />
																<br />
																<input type="button" value=" < " class="button"
																	onclick="moveSingle('certnolist_select','certnolist');" />
																<br />
																<input type="button" value="&lt;&lt;"  class="button"
																	onclick="moveAll('certnolist_select','certnolist');" />
							                  			</td> 
							                  			<td>
							                  				<select name="certnolist_select" id="certnolist_select" style="width: 80px;" multiple="multiple" size="10">
													
															</select>
							                  			</td>
							                  		</tr>
							                  	</table>
							                  </td>
							                  <td>&nbsp;</td>
							                </tr> 
							                
							                
							                 <tr style="height: 20px" id="certtr3">  <td height="20">证书数量</td>
							                  <td align="left">
							                  		 <input name="certcount" id="certcount" size="35" />
							                  </td>
							                  <td>&nbsp;</td> </tr> 
							                
							              </table>
                                	 
                                	 
                                </td> 
                              </tr>
                              
                              <tr style="height: 20%">
                                <td align="center"> 
                                	<input type="button" name="cert_toallot" id="cert_toallot" class="button" value="确 定" /> <input  name="input" class="button" type="button" value="返 回"  onclick="history.back(-1);" />
                                </td>
                              </tr>
                          </table> 
                           
                        </div></td>
                      </tr>
                    </table>
              
              
                  </td>
                </tr>
            </table></td>
          </tr>
        </table>
      </div></td>
  </tr>
</table>
 

 	<input type="hidden" name="orgto" id="orgto" />
 </form>
 
</body>
</html>
