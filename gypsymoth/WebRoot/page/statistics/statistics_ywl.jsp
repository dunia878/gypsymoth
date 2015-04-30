<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%> 
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统——客户业务量统计</title>
		<%@ include file="../include/head.jsp"%>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajax.js"></script>
		<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/jsFrame.js"></script>
	</head> 
  
 <script type="text/javascript" charset="utf-8"> 
	
	$(document).ready(function(){
		$("input[@name='statisticsby']").click(function(){
			var checkele = $("input[@name='statisticsby']:checked").val();
			if(checkele=="3"){
				initTimeZone();
				$("span[@name=span_2]").hide();
				$("span[@name=span_3]").hide();
			}else if(checkele=="2"){
				initTimeZone(); 
				$("span[@name=span_3]").hide();
				$("span[@name=span_2]").show();
			}else if(checkele=="1"){
				initTimeZone();
				$("span[@name=span_2]").hide();
				$("span[@name=span_3]").show();
			}
		});
		
		$("input[@name='searchby']").click(function(){
			var checkele = $("input[@name='searchby']:checked").val();
			if(checkele=="1"){
				initTimeZone();
				$("#div_search1").hide();
				$("#div_search2").show();
			}else if(checkele=="2"){
				$("#div_search2").hide();
				$("#div_search1").show();
			}
		});
		
	}); 
	
	
	function initTimeZone()
	{
		$("#year_begin").attr("value","-1");
		$("#year_end").attr("value","-1");
		$("#quarter_begin").attr("value","-1");
		$("#quarter_end").attr("value","-1");
		$("#month_begin").attr("value","-1");
		$("#month_end").attr("value","-1");
		
	}
	
	function checksubmit(){
	
	
		var year_begin = $("#year_begin").val();
		var year_end = $("#year_end").val();
		var quarter_begin = $("#quarter_begin").val();
		var quarter_end = $("#quarter_end").val();
		var month_begin = $("#month_begin").val();
		var month_end = $("#month_end").val();
		var statisticsby = $("input[@name='statisticsby']:checked").val();
		if(year_begin=="-1"){
			alert('开始年份不能为空');
			document.getElementById("year_begin").focus();
			return false;
		}else if(year_end=="-1"){
			alert('结束年份不能为空');
			document.getElementById("year_end").focus();
			return false;
		}
		if(year_begin>year_end){
			alert('开始年份不能晚于结束年份');
			document.getElementById("year_begin").focus();
			return false;
		}
		if(statisticsby=='2'){	//按季度
			if(quarter_begin=="-1"){
				alert('开始季度不能为空');
				document.getElementById("quarter_begin").focus();
				return false;
			}else if(quarter_end=="-1"){
				alert('结束季度不能为空');
				document.getElementById("quarter_end").focus();
				return false;
			}
			if(year_begin==year_end&&quarter_begin>=quarter_end){
				alert('同一年中开始季度必须早于结束季度');
				document.getElementById("quarter_begin").focus();
				return false;
			}
			if((year_end == year_begin && quarter_begin>quarter_end) || (year_end-year_begin>1) || (year_begin>year_end) || (year_end-year_begin==1 && quarter_end>quarter_begin)){
				alert("只能在12个周期内，请重新选择");
				document.getElementById("yearBegin").focus();
				return false;
			}
		}else if(statisticsby=='1'){
			if(month_begin=="-1"){
				alert('开始月份不能为空');
				document.getElementById("month_begin").focus();
				return false;
			}else if(month_end=="-1"){
				alert('结束月份不能为空');
				document.getElementById("month_end").focus();
				return false;
			}
			if(year_begin==year_end&&Number(month_begin)>=Number(month_end)){
				alert('同一年中开始月份必须早于结束月份');
				document.getElementById("month_begin").focus();
				return false;
			} 
			if((year_end == year_begin && month_begin>month_end) || (year_end-year_begin>1) || (year_begin>year_end) || (year_end-year_begin==1 && month_end>month_begin)){
				alert("只能在12个周期内，请重新选择");
				document.getElementById("yearBegin").focus();
				return false;
			}
		}
		
	 
	 	document.getElementById("yelform").submit(); 
	 	return false;
	 	
		var selectOrg = document.getElementById("selectOrg").value;
		if(selectOrg!=null&&selectOrg=="-1"){
			alert('请选择统计机构级别。');
			document.getElementById("selectOrg").focus();
			return false;
		}
		if(selectOrg!=null&&selectOrg=="2"){
			var orgId = $("#orgId").val();
			if(orgId==null||orgId==""){
				alert('二级公司不能为空');
				document.getElementById("orgId").focus();
				return false;
			}
		}
		if(selectOrg!=null&&selectOrg=="3"){
			var portId = $("#portId").val();
			if(portId==null||portId==""){
				alert('港口不能为空');
				document.getElementById("portId").focus();
				return false;
			}
		}
		
		document.getElementById("yelform").submit(); 
		return false;
		
	}
	
function changeOrg(){
		var orgLevel = document.getElementById("selectOrg").value;
		var id='${session.person_org}';
		var name='${session.org_name}';
		if(${session.is_child}==2){
			if(orgLevel==""){
				document.getElementById("firstOrgDiv").style.display="none";
				document.getElementById("secondOrgDiv").style.display="none";
				document.getElementById("portDiv").style.display="none";
				document.getElementById("firstOrgDiv").innerHTML = "一级公司 <select name='parentId' class='select' id='parentId' style='width: 125px;'><option selected='selected' value='-1' >请选择</option></select>";
				document.getElementById("secondOrgDiv").innerHTML = "二级公司 <select name='orgId' class='select' id='orgId' style='width: 125px;'><option selected='selected' value='-1' >请选择</option></select>";
				document.getElementById("portDiv").innerHTML = "港 口 <select name='portId' class='select' id='portId' style='width: 125px;'><option selected='selected' value='-1' >请选择</option></select>";
			}
			else if(orgLevel=="2"){
				document.getElementById("firstOrgDiv").style.display="block";
				document.getElementById("secondOrgDiv").style.display="block";
				document.getElementById("portDiv").style.display="none";
				document.getElementById("firstOrgDiv").innerHTML = "一级公司 <select name='parentId' class='select' id='parentId' style='width: 125px;'><option selected='selected' value='-1' >请选择</option></select>";
				document.getElementById("secondOrgDiv").innerHTML = "二级公司 <select name='orgId' class='select' id='orgId' style='width: 125px;'><option selected='selected' value='"+id+"'>"+name+"</option></select>";
				document.getElementById("portDiv").innerHTML = "港 口 <select name='portId' class='select' id='portId' style='width: 125px;'><option selected='selected' value='-1' >请选择</option></select>";
			}
			else if(orgLevel=="3"){
				document.getElementById("firstOrgDiv").style.display="block";
				document.getElementById("secondOrgDiv").style.display="block";
				document.getElementById("portDiv").style.display="block";
				document.getElementById("firstOrgDiv").innerHTML = "一级公司 <select name='parentId' class='select' id='parentId' style='width: 125px;'><option selected='selected' value='-1' >请选择</option></select>";
				document.getElementById("secondOrgDiv").innerHTML = "二级公司 <select name='orgId' class='select' id='orgId' style='width: 125px;'><option selected='selected' value='"+id+"'>"+name+"</option></select>";
				document.getElementById("portDiv").innerHTML = "港 口 <select name='portId' class='select' id='portId' style='width: 125px;'><option selected='selected' value='-1' >请选择</option></select>";
				ajaxselect('getPortListChargesStat.action','orgId','portDiv');
			}
		}
		else if(${session.is_child}==1){
			if(orgLevel==""){
				document.getElementById("firstOrgDiv").style.display="none";
				document.getElementById("secondOrgDiv").style.display="none";
				document.getElementById("portDiv").style.display="none";
				document.getElementById("firstOrgDiv").innerHTML = "一级公司 <select name='parentId' class='select' id='parentId' style='width: 125px;'><option selected='selected' value='-1' >请选择</option></select>";
				document.getElementById("secondOrgDiv").innerHTML = "二级公司 <select name='orgId' class='select' id='orgId' style='width: 125px;'><option selected='selected' value='-1' >请选择</option></select>";
				document.getElementById("portDiv").innerHTML = "港 口 <select name='portId' class='select' id='portId' style='width: 125px;'><option selected='selected' value='-1' >请选择</option></select>";
			}
			else if(orgLevel=="1"){
				document.getElementById("firstOrgDiv").style.display="block";
				document.getElementById("secondOrgDiv").style.display="none";
				document.getElementById("portDiv").style.display="none";
				document.getElementById("firstOrgDiv").innerHTML = "一级公司 <select name='parentId' class='select' id='parentId' style='width: 125px;'><option selected='selected' value='"+id+"'>"+name+"</option></select>";
				document.getElementById("secondOrgDiv").innerHTML = "二级公司 <select name='orgId' class='select' id='orgId' style='width: 125px;'><option selected='selected' value='-1' >请选择</option></select>";
				document.getElementById("portDiv").innerHTML = "港 口 <select name='portId' class='select' id='portId' style='width: 125px;'><option selected='selected' value='-1' >请选择</option></select>";
			}
			else if(orgLevel=="2"){
				document.getElementById("firstOrgDiv").style.display="block";
				document.getElementById("secondOrgDiv").style.display="block";
				document.getElementById("portDiv").style.display="none";
				document.getElementById("firstOrgDiv").innerHTML = "一级公司 <select name='parentId' class='select' id='parentId' style='width: 125px;'><option selected='selected' value='"+id+"'>"+name+"</option></select>";
				document.getElementById("secondOrgDiv").innerHTML = "二级公司 <select name='orgId' class='select' id='orgId' style='width: 125px;'><option selected='selected' value='-1' >请选择</option></select>";
				document.getElementById("portDiv").innerHTML = "港 口 <select name='portId' class='select' id='portId' style='width: 125px;'><option selected='selected' value='-1' >请选择</option></select>";
				ajaxselect('getSecondOrgListChargesStat.action','parentId','secondOrgDiv');
			}
			else if(orgLevel=="3"){
				document.getElementById("firstOrgDiv").style.display="block";
				document.getElementById("secondOrgDiv").style.display="block";
				document.getElementById("portDiv").style.display="block";
				document.getElementById("firstOrgDiv").innerHTML = "一级公司 <select name='parentId' class='select' id='parentId' style='width: 125px;'><option selected='selected' value='"+id+"'>"+name+"</option></select>";
				document.getElementById("secondOrgDiv").innerHTML = "二级公司 <select name='orgId' class='select' id='orgId' style='width: 125px;'><option selected='selected' value='-1' >请选择</option></select>";
				document.getElementById("portDiv").innerHTML = "港 口 <select name='portId' class='select' id='portId' style='width: 125px;'><option selected='selected' value='-1' >请选择</option></select>";
				ajaxselect('getSecondOrgListChargesStat.action','parentId','secondOrgDiv');
			}
		}
		
		else if(${session.is_child}==0){
			if(orgLevel==""){
				document.getElementById("firstOrgDiv").style.display="none";
				document.getElementById("secondOrgDiv").style.display="none";
				document.getElementById("portDiv").style.display="none";
				document.getElementById("firstOrgDiv").innerHTML = "一级公司 <select name='parentId' class='select' id='parentId' style='width: 125px;'><option selected='selected' value='-1' >请选择</option></select>";
				document.getElementById("secondOrgDiv").innerHTML = "二级公司 <select name='orgId' class='select' id='orgId' style='width: 125px;'><option selected='selected' value='-1' >请选择</option></select>";
				document.getElementById("portDiv").innerHTML = "港 口 <select name='portId' class='select' id='portId' style='width: 125px;'><option selected='selected' value='-1' >请选择</option></select>";
			}
			else if(orgLevel=="0"){
				document.getElementById("firstOrgDiv").style.display="none";
				document.getElementById("secondOrgDiv").style.display="none";
				document.getElementById("portDiv").style.display="none";
				document.getElementById("firstOrgDiv").innerHTML = "一级公司 <select name='parentId' class='select' id='parentId' style='width: 125px;'><option selected='selected' value='-1' >请选择</option></select>";
				document.getElementById("secondOrgDiv").innerHTML = "二级公司 <select name='orgId' class='select' id='orgId' style='width: 125px;'><option selected='selected' value='-1' >请选择</option></select>";
				document.getElementById("portDiv").innerHTML = "港 口 <select name='portId' class='select' id='portId' style='width: 125px;'><option selected='selected' value='-1' >请选择</option></select>";
			}
			else if(orgLevel=="1"){
				document.getElementById("firstOrgDiv").style.display="block";
				document.getElementById("secondOrgDiv").style.display="none";
				document.getElementById("portDiv").style.display="none";
				document.getElementById("secondOrgDiv").innerHTML = "二级公司 <select name='orgId' class='select' id='orgId' style='width: 125px;'><option selected='selected' value='-1' >请选择</option></select>";
				document.getElementById("portDiv").innerHTML = "港 口 <select name='portId' class='select' id='portId' style='width: 125px;'><option selected='selected' value='-1' >请选择</option></select>";
				ajaxselect('getFirstOrgListChargesStat.action','selectOrg','firstOrgDiv');
			}
			else if(orgLevel=="2"){
				document.getElementById("firstOrgDiv").style.display="block";
				document.getElementById("secondOrgDiv").style.display="block";
				document.getElementById("portDiv").style.display="none";
				document.getElementById("secondOrgDiv").innerHTML = "二级公司 <select name='orgId' class='select' id='orgId' style='width: 125px;'><option selected='selected' value='-1' >请选择</option></select>";
				document.getElementById("portDiv").innerHTML = "港 口 <select name='portId' class='select' id='portId' style='width: 125px;'><option selected='selected' value='-1' >请选择</option></select>";
				ajaxselect('getFirstOrgListChargesStat.action','selectOrg','firstOrgDiv');
			}
			else if(orgLevel=="3"){
				document.getElementById("firstOrgDiv").style.display="block";
				document.getElementById("secondOrgDiv").style.display="block";
				document.getElementById("portDiv").style.display="block";
				document.getElementById("secondOrgDiv").innerHTML = "二级公司 <select name='orgId' class='select' id='orgId' style='width: 125px;'><option selected='selected' value='-1' >请选择</option></select>";
				document.getElementById("portDiv").innerHTML = "港 口 <select name='portId' class='select' id='portId' style='width: 125px;'><option selected='selected' value='-1' >请选择</option></select>";
				ajaxselect('getFirstOrgListChargesStat.action','selectOrg','firstOrgDiv');
			}
		}
		
	}
	
	function check_it() {
		if(document.getElementById("searchby1").checked) {
			document.getElementById("client_input").value= "";
		}
		if(document.getElementById("searchby2").checked) {
			document.getElementById("client_input").value= " ";
		}
	}
	
</script>
 
<body>
 <!--内容开始-->
 <form method="post" action="toFifth.action" name="yelform" id="yelform" target="_blank">
  
  
  <table border="0" cellspacing="0" cellpadding="0" class="contenttab">
  <tr>
    <td class="list_r">&nbsp;</td>
    <td valign="top" class="right"><div class="title">您的当前位置： 首页 &gt;&gt; 数据统计 &gt;&gt; <strong>客户业务量统计</strong></div>
      <div>
        <table width="100%" align="center" class="tableheader">
          <tr>
            <td width="5%" height="26"><img src="<%=basePath%>images/bg_mcontentL.gif"  width="32" height="27" /></td>
            <td valign="middle"><h3>客户业务量统计</h3></td>
            <td align="right" valign="middle">&nbsp;</td>
            <td width="1%" align="right"><img src="<%=basePath%>images/bg_mcontentR.gif"  width="7" height="27" /></td>
          </tr>
        </table>
        <table align="center" cellpadding="12" class="tabedit">
          <tr>
            <td height="300" align="left" valign="top"><!--编辑开始-->
                  <table width="86%" align="center" cellspacing="5">
                    <tr>
                      <td width="13%" height="20"><strong>时间周期</strong></td>
                      <td width="87%">
                      	<input type="radio" name="statisticsby" id="statisticsby1" value="3" checked="checked" />按年度
                      	<input type="radio" name="statisticsby" id="statisticsby2" value="2"/>按季度
                      	<input type="radio" name="statisticsby" id="statisticsby3" value="1"/>按月份
                      </td>
                    </tr>
                     <tr>
                      <td>&nbsp;</td>
                      <td>
                      	年 份 <s:select cssStyle="width: 105px" list="#request.yearList" name="year_begin" id="year_begin" headerKey="-1" headerValue="- 请选择 -"  listKey="year" listValue="year" />
	                      <span id="span_2" name="span_2" style="display: none;">
	                      	季 度<select name="quarter_begin" class="select" id="quarter_begin" >
	                            <option value="-1">请选择</option>
	                            <option value="1">第一季</option>
	                            <option value="2">第二季</option>
	                            <option value="3">第三季</option>
	                            <option value="4">第四季</option>
                          		</select> 
                          </span>
	                      <span id="span_3" name="span_3" style="display: none;">
	                      	 月 份<select name="month_begin" class="select" id="month_begin">
	                            <option value="-1">请选择</option>
	                            <option value="1">1月</option>
	                            <option value="2">2月</option>
	                            <option value="3">3月</option>
	                            <option value="4">4月</option>
	                            <option value="5">5月</option>
	                            <option value="6">6月</option>
	                            <option value="7">7月</option>
	                            <option value="8">8月</option>
	                            <option value="9">9月</option>
	                            <option value="10">10月</option>
	                            <option value="11">11月</option>
	                            <option value="12">12月</option>
	                        </select>
	                      </span>
	                      --
	                      年 份 <s:select cssStyle="width: 105px" list="#request.yearList" name="year_end" id="year_end" headerKey="-1" headerValue="- 请选择 -"  listKey="year" listValue="year" />
                      <span id="span_2" name="span_2" style="display: none;">
                     	季 度<select name="quarter_end" class="select" id="quarter_end" >
                            	<option value="-1">请选择</option>
	                            <option value="1">第一季</option>
	                            <option value="2">第二季</option>
	                            <option value="3">第三季</option>
	                            <option value="4">第四季</option>
                        	</select> 
                        </span>
                      <span id="span_3" name="span_3" style="display: none;">
                      	 月 份<select name="month_end" class="select" id="month_end">
	                            <option value="-1">请选择</option>
	                            <option value="1">1月</option>
	                            <option value="2">2月</option>
	                            <option value="3">3月</option>
	                            <option value="4">4月</option>
	                            <option value="5">5月</option>
	                            <option value="6">6月</option>
	                            <option value="7">7月</option>
	                            <option value="8">8月</option>
	                            <option value="9">9月</option>
	                            <option value="10">10月</option>
	                            <option value="11">11月</option>
	                            <option value="12">12月</option>
	                        </select>
                      </span>
                      </td>
                    </tr>
                    
                    <tr>
                      <td height="20">&nbsp;</td>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                      <td height="20"><strong>统计类型</strong></td>
                      <td><input type="radio" name="searchby" id="searchby1" value="1" checked="checked" onchange="check_it()"/>
						按客户名称模糊查询 
						<input type="radio" name="searchby" id="searchby2" value="2" onchange="check_it()" />
						机构客户业务量
                      　  </td>
                    </tr>
                    <tr>
                      <td height="20">&nbsp;</td>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                       	<td colspan="2">
                       		<div id="div_search1" style="display: none;">
							<table width="100%" cellpadding="0" cellspacing="0" border="0">
								    <tr>
												<td height="20" valign="top">
													<strong>机 构</strong>
												</td>
												<td>
													<table>
														<tr>
															<td>
																级 别
																<select id="selectOrg" name="selectOrg" class="select" 
																	style="width: 125px;" onchange="changeOrg();">
																	<option value="" selected="selected">
																		请选择
																	</option>
																	<s:if test="#session.is_child<1">
																	<option value="0">
																		全国
																	</option>
																	</s:if>
																	<s:if test="#session.is_child<2">
																	<option value="1">
																		一级公司
																	</option>
																	</s:if>
																	<option value="2">
																		二级公司
																	</option>
																	<option value="3">
																		港口
																	</option>
																</select>
															</td>
														</tr>
													</table>
													<table>
														<tr>
															<td>
																<div id="firstOrgDiv" name="firstOrgDiv" style="display: none;">
																	一级公司
																	<select name="parentId" class="select" id="parentId"
																		style="width: 125px;">
																		<option selected="selected" value="">
																			全部
																		</option>
																	</select>
																</div>
															</td>
														</tr>
													</table>
													<table>
														<tr>
															<td>
																<div id="secondOrgDiv" name="secondOrgDiv" style="display: none;">
																	二级公司
																	<select name="orgId" class="select" id="orgId"
																		style="width: 125px;">
																		<option selected="selected" value="">
																			全部
																		</option>
																	</select>
																</div>
															</td>
														</tr>
													</table>
													<table>
														<tr>
															<td>
																<div id="portDiv" name="portDiv" style="display: none;">
																	港 口
																	<select name="portId" class="select" id="portId"
																		style="width: 125px;">
																		<option selected="selected" value="">
																			全部
																		</option>
																	</select>
																</div>
															</td>
														</tr>
													</table>
												</td>
											</tr>
							</table>
							</div>
							<div id="div_search2" style="display: block;">
								  <table><tr>
			                      <td width="25%" height="20" ><strong>检索条件</strong></td>
			                      <td width="75%" align="center">
			                      	
			                      	<input name="client_input" type="text" class="input" id="client_input" size="12" />
			                      	<!-- 
			                        <input name="button" type="button" class="button_s" id="button" value="查询" onclick="javascript:searchselect();" />
			                         <select name="client_select" class="select" id="client_select" style="width:125px;">
			                          <option value="-1">- 请选择 -</option>
			                         </select>
			                          -->
			                      </td>
			                    </tr>
			                    </table> 
							</div>
							
						</td>
                    </tr>
                    
                    
                    <tr>
                      <td height="20">&nbsp;</td>
                      <td>&nbsp;</td>
                    </tr>
                    
                    

                    <tr>
                      <td height="38" align="left"></td>
                      <td height="38" align="left" valign="bottom"><a href="#" onclick="javascript:checksubmit();"><img src="<%=basePath%>images/tjbt.gif" width="67" height="21" /></a></td>
                    </tr>
                  </table>
              <!--编辑结束-->
            </td>
          </tr>
        </table>
      </div></td>
  </tr>
</table>
  
</form>
	</body>
</html>
