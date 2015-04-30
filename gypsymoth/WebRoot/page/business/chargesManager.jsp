<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统——财务收费管理</title>
		<%@ include file="../include/head.jsp"%>
		<script language="javascript" type="text/javascript" src="<%=basePath%>/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript">
			function check() {
				var n = document.getElementById("selectAppno");
				var v = n.value;
				var vs = v.substring(12);
				if(v.length != 0 && (v.length != 16 || (vs != '-AGM' && vs != '-agm')) ) {
					alert("您输入的申请编号有错误");
					return false;
				}
				return true;
			}
		</script>
	</head>
	<body>
					<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
					  <tr>
					    <td class="list_r">&nbsp;</td>
					    <td valign="top" class="right"><div class="title">您的当前位置： 首页 &gt;&gt; 业务管理 &gt;&gt; <strong>财务收费</strong></div>
					      <div>
					        <!--编辑开始-->
									<table width="100%" border="0" cellpadding="6" cellspacing="0" class="tabman">
									<s:form  action="getPaymentByPage" id="searchdatalist" theme="simple"
						name="searchdatalist">
							          <tr>
							            <td height="35" align="center">申请编号:&nbsp;
							                <input id="selectAppno" name="selectAppno" type="text" class="input" size="5" value="<s:property value="selectAppno"/>"/>
							              	&nbsp;
							              	船名:&nbsp;
							                <input id="selectVesselname" name="selectVesselname" type="text" class="input" size="15" value="<s:property value="selectVesselname"/>"/>
							              	&nbsp;
							              	港口城市:&nbsp;							           
							                <s:select list="#request.portcitylist" name="selectCityname" id="selectCityname" headerKey="" headerValue="- %{getText('请选择')} -"  listKey="CITYNAME" listValue="CITYNAME" />
							              	&nbsp;
							              	申请日期:&nbsp;
							                <input id="selectBeginAppdate" name="selectBeginAppdate" class="Wdate" type="text" class="input" size="15" onfocus="WdatePicker()" value="<s:property value="selectBeginAppdate"/>"/> 至
							                <input id="selectEndAppdate" name="selectEndAppdate" class="Wdate" type="text" class="input" size="15" onfocus="WdatePicker()" value="<s:property value="selectEndAppdate"/>"/>
							              	&nbsp;
							              	<br/>
							              	用户名:&nbsp;
							              	<input id="account_name" name="account_name" type="text" class="input" size="24" value="<s:property value="account_name"/>"/>
							              	业务状态:&nbsp;
							                    <label>
							                    <select id="selectBusinessstate" name="selectBusinessstate">
							                    	<option value="">请选择</option>
							                      	<option value="0" <s:if test="selectBusinessstate==0">selected="selected"</s:if> >未提交</option>
							                      	<option value="1" <s:if test="selectBusinessstate==1">selected="selected"</s:if> >待受理</option>
							                      	<option value="2" <s:if test="selectBusinessstate==2">selected="selected"</s:if> >未通过</option>
							                      	<option value="3" <s:if test="selectBusinessstate==3">selected="selected"</s:if> >已受理</option>
							                      	<option value="4" <s:if test="selectBusinessstate==4">selected="selected"</s:if> >已分配</option>
							                      	<option value="5" <s:if test="selectBusinessstate==5">selected="selected"</s:if> >已检查</option>
							                    </select>
							                    </label>
							                &nbsp;
							              	付费状态:&nbsp;
							                    <label>
							                    <select id="selectPaystate" name="selectPaystate">
							                    	<option value="">请选择</option>
							                      	<option value="0" <s:if test="selectPaystate==0">selected="selected"</s:if> >未付费未开发票</option>
							                      	<option value="1" <s:if test="selectPaystate==1">selected="selected"</s:if> >未付费已开发票</option>
							                      	<option value="2" <s:if test="selectPaystate==2">selected="selected"</s:if> >已付费未开发票</option>
							                      	<option value="3" <s:if test="selectPaystate==3">selected="selected"</s:if> >已付费已开发票</option>
							                      	<option value="4" <s:if test="selectPaystate==4">selected="selected"</s:if> >逾期欠款</option>
							                    </select>
							                    </label>
							                &nbsp;
							              	分账状态:&nbsp;
							                    <label>
							                    <select id="selectLedgerstate" name="selectLedgerstate">
							                    	<option value="">请选择</option>
							                      	<option value="0" <s:if test="selectLedgerstate==0">selected="selected"</s:if> >未分账</option>
							                      	<option value="1" <s:if test="selectLedgerstate==1">selected="selected"</s:if> >已分账</option>
							                    </select>
							                    </label>
							              &nbsp;
							            <input name="查询" type="submit" class="button" value="查 询" onclick="return check()" /></td>
							          </tr>
							          </s:form>
							        </table>
					        <table width="100%" class="tableheader">
					          <tr>
					            <td width="5%" height="26"><img src="${pageContext.request.contextPath}/images/bg_mcontentL.gif"  width="32" height="27" /></td>
					            <td valign="middle"><h3>列表</h3></td>
					            <td align="right" valign="middle"></td>
					            <td width="1%" align="right"><img src="${pageContext.request.contextPath}/images/bg_mcontentR.gif"  width="7" height="27" /></td>
					          </tr>
					        </table>
					        <table width="100%" class="tableCont">
					          <tr>
					            <td>
					            <div class="tableContent">
					                  <table width="100%" border="0" cellspacing="1" cellpadding="0">
					                <s:form id="datalist" name="datalist" action="getRoleByPage" theme="simple">
									<tr>
										  <th>序号</th>
					                      <th>申请编号</th>
					                      <th>用户名</th>
					                      <th>船名</th>
					                      <!-- <th>IMO号</th> -->
					                      <th>总吨位</th>
					                      <!-- <th>申请日期</th> -->
					                      <!-- <th>受理日期</th> -->
					                      <th>港口城市</th>
					                      <th>计划检查日期</th>
					                      <th>业务状态</th>
					                      <th>付费状态</th>
					                      <th>分账状态</th>
					                      <th>操作</th>
					                </tr>
					                <s:if test="#request.paymentList.size>0">
					    			<s:iterator value="#request.paymentList" status="stat">
					                  <tr>
					                    <td align="center"><s:property value="#stat.index+1+#request.begin"/></td>
					                  	<s:if test="APPNO==null">
					                  	<td align="center"><a href="getPaymentByBusinessid.action?businessid=${BUSINESSID }&currPagea=<s:property value="#request.currPage1" />"><font color="blue">未知</font></a></td>
					                  	</s:if>
					                  	<s:else>
					                  	<td align="center"><a href="getPaymentByBusinessid.action?businessid=${BUSINESSID }&currPagea=<s:property value="#request.currPage1" />"><font color="blue">${APPNO }</font></a></td>
					                  	</s:else>
					                    <td align="center">${ACCOUNT_NAME }</td>
					                    <td align="center">${VESSELNAME }</td>
					                    <!-- <td align="center">${IMO }</td> -->
					                    <td align="center">${TONNAGE }</td>
					                    <!-- <td align="center">${APPDATE }</td>
					                    <s:if test="OPERATORTIME!=null">
					                    	<td align="center">${OPERATORTIME }</td>
					                    </s:if>
					                    <s:else>
					                    	<td align="center">暂无</td>
					                    </s:else> -->
					                    <td align="center">${CITYNAME }</td>
					                    <td align="center">${CHECKDATE }</td>
					                    <s:if test="BUSINESSSTATE==0">
					                    	<td align="center">未提交</td>
					                    </s:if>
					                    <s:elseif test="BUSINESSSTATE==1">
					                    	<td align="center">待受理</td>
					                    </s:elseif>
					                    <s:elseif test="BUSINESSSTATE==2">
					                    	<td align="center">未通过</td>
					                    </s:elseif>
					                    <s:elseif test="BUSINESSSTATE==3">
					                    	<td align="center">已受理</td>
					                    </s:elseif>
					                    <s:elseif test="BUSINESSSTATE==4">
					                    	<td align="center">已分配</td>
					                    </s:elseif>
					                    <s:elseif test="BUSINESSSTATE==5">
					                    	<td align="center">已检查</td>
					                    </s:elseif>
					                    <s:else>
					                    	<td align="center">未知</td>
					                    </s:else>
					                    <s:if test="PAYSTATE==0">
					                    	<td align="center">未付费未开发票</td>
					                    </s:if>
					                    <s:elseif test="PAYSTATE==1">
					                    	<td align="center">未付费已开发票</td>
					                    </s:elseif>
					                    <s:elseif test="PAYSTATE==2">
					                    	<td align="center">已付费未开发票</td>
					                    </s:elseif>
					                    <s:elseif test="PAYSTATE==3">
					                    	<td align="center">已付费已开发票</td>
					                    </s:elseif>
					                    <s:elseif test="PAYSTATE==4">
					                    	<td align="center">逾期欠款</td>
					                    </s:elseif>
					                    <s:else>
					                    	<td align="center">未知</td>
					                    </s:else>
					                    <s:if test="LEDGERSTATE==0">
					                    	<td align="center">未分账</td>
					                    </s:if>
					                    <s:elseif test="LEDGERSTATE==1">
					                    	<td align="center">已分账</td>
					                    </s:elseif>
					                    <s:else>
					                    	<td align="center">未知</td>
					                    </s:else>
					                    
					                    <td align="center"><a href="getPaymentByBusinessid.action?businessid=${BUSINESSID }&currPagea=<s:property value="#request.currPage1" />" class="content">录入</a>
					                    </td>
					                  </tr>
					                  </s:iterator>
					                  </s:if>
					                    </s:form>
					                  </table>
					            </div></td>
					          </tr>
					        </table>
					        <table width="98%" height="35" border="0" cellpadding="0" cellspacing="0">
					          <tr>
								<td height="25" align="right" class="right_tb1" colspan="3">
								<!--分页  -->
									<s:component template="pagev2.jsp" templateDir="page/pageutil" theme="template">
									<!--action name  -->
										<s:param name="formId" value="'searchdatalist'" />
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
					        <!--编辑结束-->
					      </div>
					     </td>
					  </tr>
					</table>
	</body>
</html>

