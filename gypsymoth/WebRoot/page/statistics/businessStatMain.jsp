<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统——财务统计</title>
		<%@ include file="../include/head.jsp"%>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajax.js"></script>
		<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/jsFrame.js"></script>
	</head>

	<script type="text/javascript" charset="utf-8">
	function checkSubmit() {
		//校验
		var selectOrg = document.getElementById("selectOrg").value;
		var orgId = document.getElementById("orgId").value;
		var portId = document.getElementById("portId").value;
		var searchLedger2 = document.getElementById("searchLedger2").checked;
		var parentId = document.getElementById("parentId").value;
		if(!changeYearQuarterMonth()){
			return false;
		}
		
		if(selectOrg==""){
			alert("请选择机构级别");
			document.getElementById("selectOrg").focus();
   			return false;
		}
		if(selectOrg==1 && parentId==-1){
			alert("一级公司暂无，请重新选择机构");
			document.getElementById("selectOrg").value = "";
			changeOrg();
			document.getElementById("selectOrg").focus();
   			return false;
		}
		if(selectOrg==2 && orgId==-1){
			alert("二级公司暂无，请重新选择机构");
			document.getElementById("selectOrg").value = "";
			changeOrg();
			document.getElementById("selectOrg").focus();
   			return false;
		}
		if(selectOrg==3 && orgId==-1){
			alert("二级公司暂无，请重新选择机构");
			document.getElementById("selectOrg").value = "";
			changeOrg();
			document.getElementById("selectOrg").focus();
   			return false;
		}
		if(selectOrg==3 && portId==-1){
			alert("港口暂无，请重新选择机构");
			document.getElementById("selectOrg").value = "";
			changeOrg();
			document.getElementById("selectOrg").focus();
   			return false;
		}

		document.getElementById("parentId2").value = document.getElementById("parentId").value;
		document.getElementById("orgId2").value = document.getElementById("orgId").value;
		document.getElementById("portId2").value = document.getElementById("portId").value;
		if(!searchLedger2){
			document.businessStat.action="getBusinessStat.action"; 
			document.businessStat.submit();
		}
		else{
			document.businessStat.action="getQualifiedStat.action"; 
			document.businessStat.submit();
		}
	}
	
	//时间周期
	function changeCycleTime(){
		var cycleTime1 = document.getElementById("cycleTime1").checked;
		var cycleTime2 = document.getElementById("cycleTime2").checked;
		var cycleTime3 = document.getElementById("cycleTime3").checked;
		if(cycleTime1){
			document.getElementById("quarterBeginDiv").style.display = "none";
			document.getElementById("quarterEndDiv").style.display = "none";
			document.getElementById("monthBeginDiv").style.display = "none";
			document.getElementById("monthEndDiv").style.display = "none";
		}
		else if(cycleTime2){
			document.getElementById("quarterBeginDiv").style.display = "block";
			document.getElementById("quarterEndDiv").style.display = "block";
			document.getElementById("monthBeginDiv").style.display = "none";
			document.getElementById("monthEndDiv").style.display = "none";
		}
		else if(cycleTime3){
			document.getElementById("monthBeginDiv").style.display = "block";
			document.getElementById("monthEndDiv").style.display = "block";
			document.getElementById("quarterBeginDiv").style.display = "none";
			document.getElementById("quarterEndDiv").style.display = "none";
		}
	}
	
	//校验月季年
	function changeYearQuarterMonth(){
		var cycleTime1 = document.getElementById("cycleTime1").checked;
		var cycleTime2 = document.getElementById("cycleTime2").checked;
		var cycleTime3 = document.getElementById("cycleTime3").checked;
		var yearBegin = parseFloat(document.getElementById("yearBegin").value);
		var yearEnd = parseFloat(document.getElementById("yearEnd").value);
		var quarterBegin = parseFloat(document.getElementById("quarterBegin").value);
		var quarterEnd = parseFloat(document.getElementById("quarterEnd").value);
		var monthBegin = parseFloat(document.getElementById("monthBegin").value);
		var monthEnd = parseFloat(document.getElementById("monthEnd").value);
		if(cycleTime1){
			if(yearBegin==0){
				alert("起始年份未选择");
				document.getElementById("yearBegin").focus();
				return false;
			}
			else if(yearEnd==0){
				alert("终止年份未选择");
				document.getElementById("yearEnd").focus();
				return false;
			}
			else if(yearEnd-yearBegin<0){
				alert("终止年份大于起始年份");
				document.getElementById("yearEnd").focus();
				return false;
			}
			else{
				return true;
			}
		}
		else if(cycleTime2){
			if(yearBegin==0){
				alert("起始年份未选择");
				document.getElementById("yearBegin").focus();
				return false;
			}
			else if(yearEnd==0){
				alert("终止年份未选择");
				document.getElementById("yearEnd").focus();
				return false;
			}
			else if(quarterBegin==0){
				alert("起始季度未选择");
				document.getElementById("quarterBegin").focus();
				return false;
			}
			else if(quarterEnd==0){
				alert("终止季度未选择");
				document.getElementById("quarterEnd").focus();
				return false;
			}
			else if(((yearEnd*10+quarterEnd)-(yearBegin*10+quarterBegin))>23 || ((yearEnd*10+quarterEnd)-(yearBegin*10+quarterBegin))<0){
				alert("只能在12个周期内，请重新选择");
				document.getElementById("yearBegin").focus();
				return false;
			}
			else{
				return true;
			}
		}
		else if(cycleTime3){
			if(yearBegin==0){
				alert("起始年份未选择");
				document.getElementById("yearBegin").focus();
				return false;
			}
			else if(yearEnd==0){
				alert("终止年份未选择");
				document.getElementById("yearEnd").focus();
				return false;
			}
			else if(monthBegin==0){
				alert("起始月份未选择");
				document.getElementById("monthBegin").focus();
				return false;
			}
			else if(monthEnd==0){
				alert("终止月份未选择");
				document.getElementById("monthEnd").focus();
				return false;
			}
			else if((yearEnd == yearBegin && monthBegin>monthEnd) || (yearEnd-yearBegin>1) || (yearBegin>yearEnd) || (yearEnd-yearBegin==1 && monthEnd>monthBegin)){
				alert("只能在12个周期内，请重新选择");
				document.getElementById("yearBegin").focus();
				return false;
			}
			else{
				return true;
			}
		}
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
</script>
	<body>
		<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
			<tr>
				<td class="list_r">
					&nbsp;
				</td>
				<td valign="top" class="right">
					<div class="title">
						您的当前位置： 首页 &gt;&gt; 数据统计 &gt;&gt;
						<strong>业务量统计</strong>
					</div>
					<div>
						<table width="100%" align="center" class="tableheader">
							<tr>
								<td width="5%" height="26">
									<img src="${pageContext.request.contextPath}/images/bg_mcontentL.gif" width="32" height="27" />
								</td>
								<td valign="middle">
									<h3>
										业务量统计
									</h3>
								</td>
								<td align="right" valign="middle">
									&nbsp;
								</td>
								<td width="1%" align="right">
									<img src="${pageContext.request.contextPath}/images/bg_mcontentR.gif" width="7" height="27" />
								</td>
							</tr>
						</table>
						<table align="center" cellpadding="12" class="tabedit">
							<tr>
								<td height="300" align="left" valign="top">
									<!--编辑开始-->
									<table width="86%" align="center" cellspacing="5">
										<s:form theme="simple" method="post" action=""
											id="businessStat" name="businessStat" target="_blank">
											<input type="hidden" name="parentId2" id="parentId2" value="" />
											<input type="hidden" name="orgId2" id="orgId2" value="" />
											<input type="hidden" name="portId2" id="portId2" value="" />
											<tr>
												<td width="15%" height="20">
													<strong>时间周期</strong>
												</td>
												<td width="85%">
													<table>
														<tr>
															<td>
																<div>
																	<input type="radio" name="cycleTime" id="cycleTime1"
																		value="1" checked="checked"
																		onclick="changeCycleTime();" />
																	年度
																</div>
															</td>
															<td>
																<div>
																	<input type="radio" name="cycleTime" id="cycleTime2"
																		value="2" onclick="changeCycleTime();" />
																	季度
																</div>
															</td>
															<td>
																<div>
																	<input type="radio" name="cycleTime" id="cycleTime3"
																		value="3" onclick="changeCycleTime();" />
																	月份
																</div>
															</td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td height="20">
													&nbsp;
												</td>
												<td>
													<table>
														<tr>
															<td>
																<s:select cssStyle="width: 80px"
																	list="#request.yearList" name="yearBegin"
																	id="yearBegin" listKey="year" listValue="year" headerKey="0" headerValue="-请选择-" />
																年
															</td>
															<td>
																<div id="quarterBeginDiv" style="display: none;">
																	<select name="quarterBegin" class="select"
																		id="quarterBegin" >
																		<option value="0">
																			请选择
																		</option>
																		<option value="1">
																			一
																		</option>
																		<option value="2">
																			二
																		</option>
																		<option value="3">
																			三
																		</option>
																		<option value="4">
																			四
																		</option>
																	</select>
																	季度
																</div>
															</td>
															<td>
																<div id="monthBeginDiv" style="display: none;">
																	<select name="monthBegin" class="select"
																		id="monthBegin" >
																		<option value="0">
																			请选择
																		</option>
																		<option value="01">
																			1
																		</option>
																		<option value="02">
																			2
																		</option>
																		<option value="03">
																			3
																		</option>
																		<option value="04">
																			4
																		</option>
																		<option value="05">
																			5
																		</option>
																		<option value="06">
																			6
																		</option>
																		<option value="07">
																			7
																		</option>
																		<option value="08">
																			8
																		</option>
																		<option value="09">
																			9
																		</option>
																		<option value="10">
																			10
																		</option>
																		<option value="11">
																			11
																		</option>
																		<option value="12">
																			12
																		</option>
																	</select>
																	月
																</div>
															</td>
															<td valign="bottom">
																至
															</td>
															<td>
																<s:select cssStyle="width: 80px"
																	list="#request.yearList" name="yearEnd"
																	id="yearEnd" listKey="year" listValue="year" headerKey="0" headerValue="-请选择-" />
																年
															</td>
															<td>
																<div id="quarterEndDiv" style="display: none;">
																	<select name="quarterEnd" class="select"
																		id="quarterEnd" >
																		<option value="0">
																			请选择
																		</option>
																		<option value="1">
																			一
																		</option>
																		<option value="2">
																			二
																		</option>
																		<option value="3">
																			三
																		</option>
																		<option value="4">
																			四
																		</option>
																	</select>
																	季度
																</div>
															</td>
															<td>
																<div id="monthEndDiv" style="display: none;">
																	<select name="monthEnd" class="select" id="monthEnd" >
																		<option value="0">
																			请选择
																		</option>
																		<option value="01">
																			1
																		</option>
																		<option value="02">
																			2
																		</option>
																		<option value="03">
																			3
																		</option>
																		<option value="04">
																			4
																		</option>
																		<option value="05">
																			5
																		</option>
																		<option value="06">
																			6
																		</option>
																		<option value="07">
																			7
																		</option>
																		<option value="08">
																			8
																		</option>
																		<option value="09">
																			9
																		</option>
																		<option value="10">
																			10
																		</option>
																		<option value="11">
																			11
																		</option>
																		<option value="12">
																			12
																		</option>
																	</select>
																	月
																</div>
															</td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td height="20">
													&nbsp;
												</td>
												<td>
													&nbsp;
												</td>
											</tr>
											<tr>
												<td height="20" valign="top">
													<strong>机　　构</strong>
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
											<tr>
												<td height="20">
													&nbsp;
												</td>
												<td>
													&nbsp;
												</td>
											</tr>
											<tr>
												<td height="20">
													<strong>统计类型</strong>
												</td>
												<td>
													<input type="radio" name="searchLedger" id="searchLedger1" value="1" checked="checked" />
														业务量
	
													<input type="radio" name="searchLedger" id="searchLedger2" value="2" />
														合格率
												</td>
											</tr>

											<tr>
												<td height="38" align="left"></td>
												<td height="38" align="left" valign="bottom">
													<a href="#" onclick="checkSubmit();"><img
															src="${pageContext.request.contextPath}/images/tjbt.gif" width="67" height="21" /> </a>
												</td>
											</tr>
										</s:form>
									</table>
									<!--编辑结束-->
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table>
	</body>
</html>
