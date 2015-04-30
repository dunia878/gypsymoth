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
		var chargesStatType = document.getElementById("chargesStatType").value;
		var selectOrg = document.getElementById("selectOrg").value;
		var orgId = document.getElementById("orgId").value;
		var portId = document.getElementById("portId").value;
		var parentId = document.getElementById("parentId").value;
		var searchLedger2 = document.getElementById("searchLedger2").checked;
		if(chargesStatType==""){
			alert("请选择财务统计类别");
			document.getElementById("chargesStatType").focus();
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

		//赋值
		var yearBegin = document.getElementById("yearBegin").value;
		var yearEnd = document.getElementById("yearEnd").value;
		var monthBegin = document.getElementById("monthBegin").value;
		var monthEnd = document.getElementById("monthEnd").value;
		if(yearBegin!=""){
			document.chargesStat.selectBeginAppdate.value = yearBegin+"-"+monthBegin;
		}
		if(yearEnd!=""){
			document.chargesStat.selectEndAppdate.value = yearEnd+"-"+monthEnd;
		}
		
		document.getElementById("parentId2").value = document.getElementById("parentId").value;
		document.getElementById("orgId2").value = document.getElementById("orgId").value;
		document.getElementById("portId2").value = document.getElementById("portId").value;
		if(chargesStatType==2){
			document.chargesStat.action="chargesAllStat.action"; 
			document.chargesStat.submit();
		}
		else if(chargesStatType==1 && !searchLedger2){
			document.chargesStat.action="chargesLedgerStatByApp.action"; 
			document.chargesStat.submit();
		}
		else if(chargesStatType==1 && searchLedger2){
			document.chargesStat.action="chargesLedgerStatByOrg.action"; 
			document.chargesStat.submit();
		}
	}
	
	function changeMonth(){
		var yearBegin = document.getElementById("yearBegin").value;
		var yearEnd = document.getElementById("yearEnd").value;
		if(yearBegin!=""){
			document.chargesStat.monthBegin.disabled = false;
		}
		else{
			document.chargesStat.monthBegin.disabled = true;
		}
		if(yearEnd!=""){
			document.chargesStat.monthEnd.disabled = false;
		}
		else{
			document.chargesStat.monthEnd.disabled = true;
		}
	}
	
	function changeLedgerDiv(){
		var chargesStatType = document.getElementById("chargesStatType").value;
		if(chargesStatType==1){
			document.getElementById("ledgerDiv").style.display = "block";
		}
		else{
			document.getElementById("ledgerDiv").style.display = "none";
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
						<strong>财务记录统计</strong>
					</div>
					<div>
						<table width="100%" align="center" class="tableheader">
							<tr>
								<td width="5%" height="26">
									<img src="${pageContext.request.contextPath}/images/bg_mcontentL.gif" width="32" height="27" />
								</td>
								<td valign="middle">
									<h3>
										财务记录统计
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
											id="chargesStat" name="chargesStat" target="_blank">
											<input type="hidden" id="selectBeginAppdate" name="selectBeginAppdate" value="" />
											<input type="hidden" id="selectEndAppdate" name="selectEndAppdate" value="" />
											<input type="hidden" name="parentId2" id="parentId2" value="" />
											<input type="hidden" name="orgId2" id="orgId2" value="" />
											<input type="hidden" name="portId2" id="portId2" value="" />
											<tr>
												<td width="15%" height="20">
													<strong>财务统计类别</strong>
												</td>
												<td width="85%">
													<select name="chargesStatType" class="select" id="chargesStatType"
														style="width: 125px;" onchange="changeLedgerDiv();">
														<option selected="selected" value="">
															请选择
														</option>
														<option value="1">
															分帐明细
														</option>
														<option value="2">
															综合统计
														</option>
													</select>
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
													<strong>时 间 段</strong>
												</td>
												<td>
													年 份
													<s:select cssStyle="width: 105px" list="#request.yearList" name="yearBegin" id="yearBegin" headerKey="" headerValue="- 全部 -"  listKey="year" listValue="year" onchange="changeMonth();" />
													月
													<select name="monthBegin" class="select" id="monthBegin" disabled="disabled">
														<option value="01">1月</option>
														<option value="02">2月</option>
														<option value="03">3月</option>
														<option value="04">4月</option>
														<option value="05">5月</option>
														<option value="06">6月</option>
														<option value="07">7月</option>
														<option value="08">8月</option>
														<option value="09">9月</option>
														<option value="10">10月</option>
														<option value="11">11月</option>
														<option value="12">12月</option>
													</select>
													—— 年 份
													<s:select cssStyle="width: 105px" list="#request.yearList" name="yearEnd" id="yearEnd" headerKey="" headerValue="- 全部 -"  listKey="year" listValue="year" onchange="changeMonth();"/>
													月
													<select name="monthEnd" class="select" id="monthEnd" disabled="disabled">
														<option value="01">1月</option>
														<option value="02">2月</option>
														<option value="03">3月</option>
														<option value="04">4月</option>
														<option value="05">5月</option>
														<option value="06">6月</option>
														<option value="07">7月</option>
														<option value="08">8月</option>
														<option value="09">9月</option>
														<option value="10">10月</option>
														<option value="11">11月</option>
														<option value="12">12月</option>
													</select>
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
													&nbsp;
												</td>
												<td>
													<div id="ledgerDiv" name="ledgerDiv" style="display: none;">
														<input type="radio" name="searchLedger" id="searchLedger1" value="1" checked="checked" />
														业务单号
	
														<input type="radio" name="searchLedger" id="searchLedger2" value="2" />
														机构名称
													</div>
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
