<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%> 
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统——目的国业务量统计</title>
		<%@ include file="../include/head.jsp"%>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajax.js"></script>
		<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/jsFrame.js"></script>
	</head> 
  
 <script type="text/javascript" charset="utf-8"><!-- 
	
	
	function checksubmit(){
		var yearBegin = $("#yearBegin").val();
		var month_begin = $("#month_begin").val();
		var yearEnd = $("#yearEnd").val();
		var month_end = $("#month_end").val();
		
		if( yearBegin!='-1'||yearEnd!='-1' ){
			if(yearBegin==null||yearBegin=="-1"){
				alert('如果需要按时间区间统计，请完整填写开始年份。');
				document.getElementById("yearBegin").focus();
				return false;
			}
			if(month_begin==null||month_begin=="-1"){
				alert('如果需要按时间区间统计，请完整填写开始月份。');
				document.getElementById("month_begin").focus();
				return false;
			}
			if(yearEnd==null||yearEnd=="-1"){
				alert('如果需要按时间区间统计，请完整填写结束年份。');
				document.getElementById("yearEnd").focus();
				return false;
			}
			if(month_end==null||month_end=="-1"){
				alert('如果需要按时间区间统计，请完整填写结束月份。');
				document.getElementById("month_end").focus();
				return false;
			}
			if(yearBegin>yearEnd){
				alert('结束年份不能早于开始年份。');
				document.getElementById("yearEnd").focus();
				return false;
			}
			if(yearBegin==yearEnd){
				if(Number(month_begin)>Number(month_end)){
					alert('结束月份不能早于开始月份。');
					document.getElementById("month_end").focus();
					return false;
				}
			} 
		}
	 
		var selectOrg = document.getElementById("selectOrg").value;
		if(selectOrg!=null&&selectOrg=="-1"){
			alert('请选择统计机构级别。');
			document.getElementById("selectOrg").focus();
			return false;
		}
		
		if(selectOrg=="1"){
			var parentId = $("#parentId").val();
			if(parentId=="-1"){
				alert("一级公司暂无，请重新选择机构");
				document.getElementById("parentId").focus();
				return false;
			}
		}
		if(selectOrg=="2"){
			var orgId = $("#orgId").val();
			if(orgId=="-1"){
				alert('此一级公司不存在二级公司,请将级别置为一级公司,再进行查询');
				document.getElementById("orgId").focus();
				return false;
			}
		}
		if(selectOrg!=null&&selectOrg=="3"){
		    var orgId = $("#orgId").val();
			if(orgId=="-1"){
				alert('此一级公司不存在二级公司,请将级别置为一级公司,再进行查询');
				document.getElementById("orgId").focus();
				return false;
			}
			var portId = $("#portId").val();
			if(portId=="-1"){
				alert('港口不能为空');
				document.getElementById("portId").focus();
				return false;
			}
		}
		
		document.getElementById("mdgform").submit(); 
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
--></script>
 
<body>
 <!--内容开始-->
 <form method="post" action="toSecond.action" name="mdgform" id="mdgform" target="_blank">
 <table border="0" cellspacing="0" cellpadding="0" class="contenttab"> 
  <tr>
    <td class="list_r">&nbsp;</td>
    <td valign="top" class="right"><div class="title">您的当前位置： 首页 &gt;&gt; 数据统计 &gt;&gt; <strong>目的国业务量统计</strong></div>
      <div>
        <table width="100%" align="center" class="tableheader">
          <tr>
            <td width="5%" height="26"><img src="<%=basePath %>images/bg_mcontentL.gif"  width="32" height="27" /></td>
            <td valign="middle"><h3>目的国业务量统计</h3></td>
            <td align="right" valign="middle">&nbsp;</td>
            <td width="1%" align="right"><img src="<%=basePath %>images/bg_mcontentR.gif"  width="7" height="27" /></td>
          </tr>
        </table>
        <table align="center" cellpadding="12" class="tabedit">
          <tr>
            <td height="300" align="left" valign="top"><!--编辑开始-->
                  <table width="86%" align="center" cellspacing="5">
                    <tr>
                      <td width="13%" height="20"><strong>时 间 段</strong></td>
                      <td width="87%">
                    	年 份
						<s:select cssStyle="width: 105px" list="#request.yearList" name="yearBegin" id="yearBegin" headerKey="-1" headerValue="- 全部 -"  listKey="year" listValue="year" />
                        月
                        <select name="month_begin" class="select" id="month_begin">
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
                       —— 年 份
							<s:select cssStyle="width: 105px" list="#request.yearList" name="yearEnd" id="yearEnd" headerKey="-1" headerValue="- 全部 -"  listKey="year" listValue="year" />
							月 
							<select name="month_end" class="select" id="month_end">
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
							</select></td>
                    </tr>
                    
                    <tr>
                      <td height="20">&nbsp;</td>
                      <td>&nbsp;</td>
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
                      <td height="20">&nbsp;</td>
                      <td>&nbsp;</td>
                    </tr>
                    
                    <tr>
                      <td height="38" align="left"></td>
                      <td height="38" align="left" valign="bottom"><a href="#" onclick="return checksubmit();" target="_blank"><img src="../../images/tjbt.gif" width="67" height="21" /></a></td>
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
