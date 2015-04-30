<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
 	isELIgnored="false"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统</title>
		<%@ include file="../include/head.jsp"%> 
		<link rel="StyleSheet" href="<%=basePath %>style/dtree.css" type="text/css" />
		<script type="text/javascript" src="<%=basePath %>js/dtree.js"></script> 
	<script type="text/javascript" language="javascript">
		
		
		function coor_click()
		{
			var allottype = $("#allottype").val(); 
			if(allottype!="2"){
				var person_name_id = $("input[@name='person_id']:checked").val();  
				var arys = person_name_id.split(":");
				var person_name = arys[0];
				var person_id = arys[1];
				if(allottype=="0"){	// 选择协调员
					window.opener.document.getElementById("coor_input_name").value=person_name; 
					window.opener.document.getElementById("coor_input_id").value=person_id;
				}else if(allottype=="1"){	//选择授权签字人
					window.opener.document.getElementById("auth_input_name").value=person_name; 
					window.opener.document.getElementById("auth_input_id").value=person_id;
				}
			}else if(allottype=="2"){	//选择检查员
			
				var ins = document.getElementsByName("person_id");
				var allvali='';
				var name = "";
				var ids =""; 
				  for(var i=0; i<ins.length; i++){
				    if(ins[i].checked){
				    	var pername  = ins[i].value.split(":");
				    	name += pername[0]+",";
				    	ids += pername[1]+",";
				    	allvali+=ins[i].value+',';  //如果选中，将value添加到变量s中
				    }	
				  }
				if(name.indexOf(",")!=-1){
					name = name.substring(0,name.length-1);
					ids = ids.substring(0,ids.length-1); 
				}
				  
				window.opener.document.getElementById("ins_input_name").value=name;
				window.opener.document.getElementById("ins_input_id_vali").value=allvali;
				window.opener.document.getElementById("ins_input_id").value=ids; 
			}
			
			window.close();
		}
		
		function ajax_tabContent(orgid)
		{	
			var allottype = document.getElementById("allottype").value;
			$.ajax({ 
			      url: "tableContent.action?orgid="+orgid+"&allottype="+allottype, 
			      type: "get",
			      beforeSend:function(){
			      },
			      dataType: "text",  
			      success: function(data) {
			      	$("#div_list").fadeOut(1000,function(){
			      		 $("#div_list").html(data); 
			      	}); 
				  },
			      async:false,  
			      cache:false
			   }
			);
			$("#div_list").fadeIn(1000); 
		}
	</script>	
		
	</head>

<body>
	<% List list = (List)request.getAttribute("list"); %>
 


<!--内容开始-->
<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
  <tr>
    <td class="list_r">&nbsp;</td>
    <td valign="top" class="right"><div class="title">您的当前位置： 首页 &gt;&gt; 业务管理 &gt;&gt; <strong>业务分配
    	<s:if test="#request.allottype==0"> (协调员)</s:if>
    	<s:elseif test="#request.allottype==1"> (授权签字人)</s:elseif>
    	<s:elseif test="#request.allottype==2"> (检查员)</s:elseif>
    	</strong>
    	
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
		<%
			for(int i=0;i<list.size();i++){
				HashMap map =	(HashMap)list.get(i);
				String rn = map.get("RN").toString();
				String parent_id = map.get("PARENT_ID").toString();
				String org_name = map.get("ORG_NAME").toString();
				String id = map.get("ID").toString();
				out.write("d.add("+id+","+parent_id+",'"+org_name+"',\"javascript:ajax_tabContent("+id+")\");");   
			}
		
		%> 
		document.write(d);

	 
	</script>

</div>

            </td>
            <td align="left" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                
                <tr>
                  <td height="460" valign="top" class="tabedit2"><table width="100%" class="tableheader">
                      <tr>
                        <td width="5%" height="26"><img src="<%=basePath%>images/bg_mcontentL.gif"  width="32" height="27" /></td>
                        <td valign="middle"><h3>人员列表</h3>	</td>
                        <td align="right" valign="middle"><!--<input type="submit" class="button_s" value="删 除" />--></td>
                        <td width="1%" align="right"><img src="<%=basePath%>images/bg_mcontentR.gif"  width="7" height="27" /></td>
                      </tr>
                    </table>
                    <table width="100%" class="tableCont">
                      <tr>
                        <td valign="top">
                        
                        <div class="tableContent" id="div_list">
                          <table width="100%" border="0" cellspacing="1" cellpadding="0">
                              
                              <tr>
                                <td width="15%" align="center">选择</td>
                                <td align="left">姓名</td>
                                <td width="15%" align="center">选择</td>
                                <td align="left">姓名</td>
                                <td width="15%" align="center">选择</td>
                                <td align="left">姓名</td>
                              </tr>
                               
                              <tr>
                                <td align="center">&nbsp;</td>
                                <td align="left">&nbsp;</td>
                                <td align="center">&nbsp;</td>
                                <td align="left">&nbsp;</td>
                                <td align="center">&nbsp;</td>
                                <td align="left">&nbsp;</td>
                              </tr>
                              <tr height="50px">
                                <td align="center" colspan="6">请选择一个机构&nbsp;</td>
                              </tr>
                            
                              
                               <tr>
                                <td align="center">&nbsp;</td>
                                <td align="left">&nbsp;</td> 
                                <td align="center">&nbsp;</td>
                                <td align="left">&nbsp;</td>
                                <td align="center">&nbsp;</td>
                                <td align="left">&nbsp;</td>
                              </tr>
                              <tr>
                                <td align="center">&nbsp;</td>
                                <td colspan="5" align="left"><input type="button" name="coor_button" onclick="coor_click();" id="coor_button" class="button" value="确 定" /> <input  name="input" class="button" type="button" value="关 闭"  onclick="window.close();" /></td>
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
 
<input type="hidden"  name="allottype" id="allottype" value="${allottype }" />
	</body>
</html>
