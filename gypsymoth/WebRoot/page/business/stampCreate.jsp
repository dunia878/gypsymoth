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
		$(document).ready(function(){
			
		});
	 	
	 	function chooseORG(name,id)
	 	{
	 		$("#orgto").val(id);
	 		$("#orgto_name").val(name); 
	 	}
	 	
	function saveStamp()
	{
		 var stampid = document.getElementById("stampid").value;
		 var regstampid = /^[0-9]+$/;
		 if(!regstampid.test(stampid)){
		 	alert('印章ID应该是数字。');
		 	document.getElementById("stampid").focus();
		 	return false;
		 }
		 
		 document.getElementById("stampform").action ="savestamp.action";
		 document.getElementById("stampform").submit();
		 
	}
	 	
	</script>	
		

<body>

 <form id="stampform" name="stampform" action="createstamp.action">
<% List list = (ArrayList)request.getAttribute("list"); %>

<!--内容开始-->
<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
  <tr>
    <td class="list_r">&nbsp;</td>
    <td valign="top" class="right">
    	<div class="title">您的当前位置： 首页 &gt;&gt; 业务管理 &gt;&gt; 证书管理 &gt;&gt;<strong><s:text name="certificate_sendemt">新建印章</s:text></strong>
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
                        <td valign="middle"><h3><s:text name="certificate_sendemt">新建印章</s:text> </h3>	</td>
                        <td align="right" valign="middle"><!--<input type="submit" class="button_s" value="删 除" />--></td>
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
							                  <td width="30%"></td> 
							                  <td width="40%"></td>
                                	  		</tr>
                                	  		<tr>
							                  <td align="center" style="color: red;" colspan="3">${message }</td> 
							                </tr>
							                
							                <tr>
							                  <td height="20">印章ID <br /></td>
							                  <td align="left">
							                  	 	<input type="text" id="stampid" name="stampid" size="35"/>
							                  </td>
							                  <td>&nbsp;</td>
							                </tr>
							                
							                <tr>
							                  <td height="20"><s:text name="certificate_org">目标分配机构</s:text></td>
							                  <td><s:textfield id="orgto_name" name="orgto_name" readonly="true" size="35"></s:textfield> 
							                 	</td>
							                  <td>&nbsp;*请点击左侧树来选择分配机构</td>
							                </tr>
                                	  
							              </table>
                                	 
                                	 
                                </td> 
                              </tr>
                              
                              <tr style="height: 20%">
                                <td align="center"> 
                                	<input type="button" name="cert_toallot" id="cert_toallot" onclick="javascript:saveStamp();" class="button" value="确 定" /> <input  name="input" class="button" type="button" value="返 回"  onclick="location.href='certificate.action?tagtype=2'" />
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
