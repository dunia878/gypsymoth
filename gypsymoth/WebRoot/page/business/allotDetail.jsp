<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
 	isELIgnored="false"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统</title>
		<%@ include file="../include/head.jsp"%>
	</head>
	<body>
	
<script language="text/javascript" type="text/javascript"> 
	$(document).ready(function(){
		initDiv();
		
	
		$("input[@name='coordinator_radio']").click(function(){
			var div_other = document.getElementById("div_other");
			var div_coordinator = document.getElementById("div_coordinator");
			var radio_check = $("input[@name='coordinator_radio']:checked").val(); 
			if(radio_check==0){
				div_other.style.display = "none";
				$("#div_coordinator").fadeIn("slow");
			}else if(radio_check==1){ 
				div_coordinator.style.display = "none";
				$("#div_other").fadeIn("slow");
			}
		});
		
	});
	
	function searchallotlist(type)
	{
		var allottype = type;			// 分配对象类型	  0:协调员/ 1:授权签字人 / 2:检查员 
		var radio_check = $("input[@name='coordinator_radio']:checked").val();	//得分配对象类型  0:协调员/ 1:检查员及授权签字人
		var orgid = document.getElementById("orgid").value;
		var url = "allotlist.action?allottype="+allottype+"&radio_check="+radio_check+"&orgid="+orgid;
		window.open(url,"_blank","height=500px,width=700px,scrollbars=yes"); 
	}
	
	function submitallot()
	{
	if (confirm("确认提交？")) {
		var allotform = document.getElementById("allotlistform"); 
		allotform.action = "toAllot.action?goPage=<s:property value="#request.currPageb"/>";
		$("#submit_button").val("提交中....");
		$("#submit_button").attr("disabled","disabled"); 
		var radio_check = $("input[@name='coordinator_radio']:checked").val(); 
		if(radio_check==0){		//分配协调员
			var coor_value = $("#coor_input_id").val();
			if(coor_value==null||coor_value==""){
				alert('分配协调员不能为空。');
				$("#submit_button").val("提 交");
				$("#submit_button").attr("disabled",false);
				return false;
			}
			document.getElementById("allottype").value="0";
			allotform.submit(); 
			$("#submit_button").val("提 交");
			$("#submit_button").attr("disabled",false);  
		}else if(radio_check==1){ //分配授权签字人,检查员
			var auth_input_name = $("#auth_input_name").val();
			var ins_input_name = $("#ins_input_name").val();
			if(auth_input_name==null||auth_input_name==""){
				alert('分配授权签字人不能为空。');
				$("#submit_button").val("提 交");
				$("#submit_button").attr("disabled",false);
				return false;
			}
			if(ins_input_name==null||ins_input_name==""){
				alert('分配检查员不能为空。');
				$("#submit_button").val("提 交");
				$("#submit_button").attr("disabled",false);
				return false;
			} 
			
			var	ins_id = $("#ins_input_id_vali").val();
			var	temp = $("#ins_input_id").val();
			var	auth_id = $("#auth_input_id").val();
			if(ins_id.indexOf(auth_id)!=-1){
				alert("授权签字人不能同时为检查员。");
				$("#submit_button").val("提 交");
				$("#submit_button").attr("disabled",false); 
				return false; 
			} 
			document.getElementById("allottype").value="1";
			allotform.submit();
			$("#submit_button").val("提 交");
			$("#submit_button").attr("disabled",false);   
		}
		}
		return false;
 
	}
	
	//初始化可显示的分配单元
	function initDiv()
	{
		var assignstate = $("#assignstate").val();	
		if(assignstate=="0"){	//一级管理员用户
			$("#span_0").show();
			$("#span_1").hide();  
		}else if(assignstate=="1"){	//二级协调员
			$("#span_0").show();
			$("#span_1").show();  
		}else if(assignstate=="3"){	//三级协调员
			$("#coordinator_radio_1").attr("checked","true"); 
			$("#span_0").hide(); 
			$("#span_1").show();  
			$("#div_coordinator").hide();
			$("#div_other").show();
		}
		
		// alert(assignstate);
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
                  <td width="180" height="20">申请编号</td>
                  <td width="332"><label>
                    <s:textfield readonly="true" name="business.appno" size="55"></s:textfield>
                  </label></td>
                  <td width="272">&nbsp;</td>
                </tr>
                <tr>
                  <td>申 请 人</td>
                  <td><label>
                  	<s:textfield name="business.businessname" readonly="true" size="55"></s:textfield>
                  </label></td>
                  <td>&nbsp;</td> 
                </tr>
                
                  <tr>
                  <td>船名 </td>
                  <td>
                    <s:textfield readonly="true" name="business.vesselname"  size="55" />
                  </td>
                  <td>&nbsp;</td>
                </tr>
                
                <tr>
                  <td height="20">IMO 编号</td>
                  <td><s:textfield name="business.imo" readonly="true" size="55"></s:textfield>
                 	</td>
                  <td>&nbsp;</td>
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
                  <td height="20">分 配 人</td>
                  <td><input name="account_name" type="text" value="${account_name }" id="account_name" readonly="readonly" size="55" />
                 	</td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="20">分 配</td>
                  <td>
                  	 <span id="span_0">
	                  	<input name="coordinator_radio" type="radio" id="coordinator_radio_0" value="0" checked="checked" />
	                  	 分配给下级协调员
	                 </span>
                  	 <span id="span_1">
	                      <input name="coordinator_radio" type="radio" id="coordinator_radio_1" value="1" /> 
	                  	 分配给检查员
                  	 </span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="50">&nbsp;</td>
                  <td>
                  
                 <div id="div_coordinator" style="height: 100px;">
                  <table width="100%" border="0" cellspacing="1" cellpadding="2">
                  	 
                    <tr>
                      <td height="20">分配协调员</td>
                      <td><input  name="coor_input_name" id="coor_input_name" type="text" class="input" size="25" readonly="readonly" onclick="alert('为了避免重名，请使用选择按钮通过机构选择人员。')" />
                          <input  name="searchallot" class="button_s" type="button" value="选 择" onclick="javascript:searchallotlist(0);" />
                          </td>
                    </tr>
                    
                    </table>
                   </div>
                    
                    <div id="div_other" style="display: none;"  style="height: 100px;">
                     <table width="100%" border="0" cellspacing="1" cellpadding="2"> 
                      
                    <tr>
                      <td height="20">分配授权签字人</td>
                      <td><input  name="auth_input_name" id="auth_input_name" type="text" class="input" size="25" readonly="readonly" onclick="alert('为了避免重名，请使用选择按钮通过机构选择人员。')"  />
                          <input  name="input4" class="button_s" type="button" value="选 择"  onclick="javascript:searchallotlist(1);" /></td>
                    </tr>
                    <tr>
                      <td height="20">分配检查员</td>
                      <td>
                        <input  name="ins_input_name" id="ins_input_name" type="text" class="input" size="25" readonly="readonly" onclick="alert('为了避免重名，请使用选择按钮通过机构选择人员。')"  />
                       <input  name="checkbutton" id="checkbutton" class="button_s" type="button" value="选 择"  onclick="javascript:searchallotlist(2);" /> 
                      </td>
                    </tr>
                   
                  </table>
                  </div>
                  
                  </td>
                  <td>&nbsp;</td>
                </tr>
                
                <tr>
                  <td height="20">&nbsp;</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="38" align="left"></td>
                  <td height="38" align="left"><input type="button" id="submit_button" onclick="return submitallot();" class="button" value="提 交" />
                      <input  name="input" class="button" type="reset" value="返 回"  onclick="history.back(-1);" />
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
	<input type="hidden" name="businessid" value="${business_id}" />
	<input type="hidden" name="orgid"	id="orgid" value="${orgid}"	/>
	<input  type="hidden" name="coor_input_id" id="coor_input_id" />
	<input type="text" name="auth_input_id" id="auth_input_id" />
	<input type="hidden" name="ins_input_id" id="ins_input_id" />
	<input type="text" name="ins_input_id_vali" id="ins_input_id_vali" />
	
	<input  type="hidden" name="allottype" id="allottype" />
	<input type="hidden" name="assignstate" id="assignstate" value="${assignstate }"/>
</form>

	</body>
</html>
