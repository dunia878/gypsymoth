<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统——业务查询</title>
		<%@ include file="../include/head.jsp"%>
		<script language="javascript" type="text/javascript" src="<%=basePath%>/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript">
			function selectOrgTree()
				{
					var url = "getOrgList.action";
					window.open(url,"_blank","height=500px,width=200px,scrollbars=yes,top=100,left=500"); 
					
				}
			function clearSelectOrgname2()
				{
					document.searchdatalist.selectOrgname2.value="";
					document.searchdatalist.selectOrgid.value="";
					document.searchdatalist.selectOrgname.value="";
					
				}
				
			function doSearch() {
				var f=document.getElementById("searchdatalist");
				var n = document.getElementById("selectAppno");
					var v = n.value;
					var vs = v.substring(12);
					if(v.length != 0 && (v.length != 16 || (vs != '-AGM' && vs != '-agm')) ) {
						alert("您输入的申请编号有错误");
						return false;
					}
				
				f.action="getSelectByPage.action";
				f.submit();
			}
			function doGet() {
				var f=document.getElementById("searchdatalist");
				f.action="getExcel.action";
				f.submit();
			}
			function doFu() {
				var f=document.getElementById("searchdatalist");
				f.action="getBMBB.action";
				f.submit();
			}
		</script>
	</head>
	<body>
					
					<table border="0" cellspacing="0" cellpadding="0">
					 <tr>
					  	<td >
					  		<font color="red"><s:property value="#request.isDelete"/></font>
					  	</td>
					  </tr>
					</table>
					<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
					 
					  <tr>
					    <td class="list_r">&nbsp;</td>
					    <td valign="top" class="right"><div class="title"><s:text name="您的当前位置"/>： <s:text name="首页"/> &gt;&gt;  <s:text name="业务管理"/> &gt;&gt; <strong><s:text name="业务查询"/></strong></div>
					      <div>
					        <!--编辑开始-->
									<table width="100%" border="0" cellpadding="3" cellspacing="0" class="tabman">
									<s:form  action="" id="searchdatalist" theme="simple"
						name="searchdatalist">
							          <tr>
							            <td align="left">
							            	<input id="selectOrgid" name="selectOrgid" type="hidden" value="<s:property value="selectOrgid"/>"/>
							            	<input id="selectOrgname" name="selectOrgname" type="hidden" value="<s:property value="selectOrgname"/>"/>
							            	<s:text name="临时编号"/>:&nbsp;
							            </td><td>
							                <input id="selectTempno" name="selectTempno" type="text" class="input" size="15" value="<s:property value="selectTempno"/>"/>
							            </td><td>
							            	<s:text name="申请编号"/>:&nbsp;
							            </td><td>
							                <input id="selectAppno" name="selectAppno" type="text" class="input" size="15" value="<s:property value="selectAppno"/>"/>
							            </td><td>
							              	<!-- 
							              	<s:if test="#session.account.accountType==1">
							              	<s:text name="申请人"/>:&nbsp;
							                <input id="selectBusinessname" name="selectBusinessname" type="text" class="input" size="10" value="<s:property value="selectBusinessname"/>"/>
							              	&nbsp;
							              	</s:if>
							              	-->
							              	<s:text name="港口城市"/>:&nbsp;
							            </td><td>
							            	<s:if test="#session.WW_TRANS_I18N_LOCALE!=null && #session.WW_TRANS_I18N_LOCALE.toString()=='en_US'">
							            		<s:select cssStyle="width: 105px" list="#request.portcitylist" name="selectCityname" id="selectCityname" headerKey="" headerValue="- %{getText('请选择')} -"  listKey="CITYNAME" listValue="ENGLISHCITYNAME" />
							            	</s:if>
							            	<s:else>
							            		<s:select cssStyle="width: 105px" list="#request.portcitylist" name="selectCityname" id="selectCityname" headerKey="" headerValue="- %{getText('请选择')} -"  listKey="CITYNAME" listValue="CITYNAME" />
							            	</s:else>    
							            </td><td>
							              	<s:text name="船名"/>:&nbsp;
							            </td><td>
							                <input id="selectVesselname" name="selectVesselname" type="text" class="input" size="15" value="<s:property value="selectVesselname"/>"/>
							            </td>
							            </tr>
							            
							            <tr>
							            <td>
							              	<s:text name="申请日期"/>:
							            </td><td colspan="3">
							                <input id="selectBeginAppdate" name="selectBeginAppdate" class="Wdate" type="text" class="input" size="18" onfocus="WdatePicker(<s:if test="#session.WW_TRANS_I18N_LOCALE!=null && #session.WW_TRANS_I18N_LOCALE.toString()=='en_US'">{lang:'en'}</s:if>)" value="<s:property value="selectBeginAppdate"/>"/>
							                	<s:text name="至"/>
							                <input id="selectEndAppdate" name="selectEndAppdate" class="Wdate" type="text" class="input" size="18" onfocus="WdatePicker(<s:if test="#session.WW_TRANS_I18N_LOCALE!=null && #session.WW_TRANS_I18N_LOCALE.toString()=='en_US'">{lang:'en'}</s:if>)" value="<s:property value="selectEndAppdate"/>"/>
							            </td><td>
							              	<s:text name="实际检查日期"/>:
							            </td><td colspan="3">
							                <input id="selectBeginOperatortime" name="selectBeginOperatortime" class="Wdate" type="text" class="input" size="19" onfocus="WdatePicker(<s:if test="#session.WW_TRANS_I18N_LOCALE!=null && #session.WW_TRANS_I18N_LOCALE.toString()=='en_US'">{lang:'en'}</s:if>)" value="<s:property value="selectBeginOperatortime"/>"/>
							                	 <s:text name="至"/>
							                <input id="selectEndOperatortime" name="selectEndOperatortime" class="Wdate" type="text" class="input" size="19" onfocus="WdatePicker(<s:if test="#session.WW_TRANS_I18N_LOCALE!=null && #session.WW_TRANS_I18N_LOCALE.toString()=='en_US'">{lang:'en'}</s:if>)" value="<s:property value="selectEndOperatortime"/>"/>
							            </td>
							          	</tr>
							          	
							          	<tr>
							          	<s:if test="#session.account.accountType==1">
							          		<td>
							              	<s:text name="公司名称"/>:
							              	</td><td colspan="6">
							                <input id="selectOrgname2" name="selectOrgname2" type="text" class="input" size="30" disabled="disabled" value="<s:property value="selectOrgname"/>"/>
							                <input type="button" class="button" onclick="selectOrgTree()" value="<s:text name="选择"/>"   style="cursor: hand"/>
							                <input type="button" class="button" onclick="clearSelectOrgname2()" value="<s:text name="清除"/>"  style="cursor: hand" />
							          		</td>
							          	</s:if>
							          		 <td>
							            	<input id="fu" name="fu" type="button" align="right" class="button" value="导出北美报表" onclick="doFu()" style="cursor: hand"/>
							            </td>
							          	</tr>
							           
							          	<tr>
							            <td>
							              	<s:text name="业务状态"/>:&nbsp;
							            </td><td>
							                    <label>
							                    <select id="selectBusinesstate" name="selectBusinesstate" style="width: 105px">
							                    	<option value=""><s:text name="请选择"/></option>
							                      	<option value="0" <s:if test="selectBusinesstate==0">selected="selected"</s:if> ><s:text name="未提交"/></option>
							                      	<option value="1" <s:if test="selectBusinesstate==1">selected="selected"</s:if> ><s:text name="未受理"/></option>
							                      	<option value="2" <s:if test="selectBusinesstate==2">selected="selected"</s:if> ><s:text name="未通过"/></option>
							                      	<option value="3" <s:if test="selectBusinesstate==3">selected="selected"</s:if> ><s:text name="已受理"/></option>
							                      	<option value="4" <s:if test="selectBusinesstate==4">selected="selected"</s:if> ><s:text name="已分配"/></option>
							                      	<option value="5" <s:if test="selectBusinesstate==5">selected="selected"</s:if> ><s:text name="已检查"/></option>
							                    </select>
							                    </label>
							             </td><td>
							            	 <s:text name="付费状态"/>:&nbsp;
							             </td><td>
							                    <label>
							                    <select id="selectPaystate" name="selectPaystate" style="width: 105px">
							                    	<option value=""><s:text name="请选择"/></option>
							                      	<option value="0" <s:if test="selectPaystate==0">selected="selected"</s:if> ><s:text name="未付费未开发票"/></option>
							                      	<option value="1" <s:if test="selectPaystate==1">selected="selected"</s:if> ><s:text name="未付费已开发票"/></option>
							                      	<option value="2" <s:if test="selectPaystate==2">selected="selected"</s:if> ><s:text name="已付费未开发票"/></option>
							                      	<option value="3" <s:if test="selectPaystate==3">selected="selected"</s:if> ><s:text name="已付费已开发票"/></option>
							                      	<option value="4" <s:if test="selectPaystate==4">selected="selected"</s:if> ><s:text name="逾期欠款"/></option>
							                    </select>
							                    </label>
							              </td><td>
							                 <s:text name="分账状态"/>:&nbsp;
							              </td><td>
							                    <label>
							                    <select id="selectLedgerstate" name="selectLedgerstate" style="width: 105px">
							                    	<option value=""><s:text name="请选择"/></option>
							                      	<option value="0" <s:if test="selectLedgerstate==0">selected="selected"</s:if> ><s:text name="未分账"/></option>
							                      	<option value="1" <s:if test="selectLedgerstate==1">selected="selected"</s:if> ><s:text name="已分账"/></option>
							                    </select>
							                    </label>							            	
							            	</td>
							              <td >
							              	<input id="search" name="search" type="button" class="button" value="<s:text name="查询"/>" onclick="return doSearch()" style="cursor: hand" />
							              </td>
							              <td>
							              	<input id="get" name="get" type="button" class="button" value="导出Excel表" onclick="doGet()" style="cursor: hand"/>
							              </td>
							            </tr>
							            
							            <tr>
							            	<td>用户名: </td>
							            	<td>
							            	<input id="account_name" name="account_name" type="text" class="input" size="15" value="<s:property value="account_name"/>"/>
							            	</td>
							            	<td>申请人:</td>
							            	<td colspan="3">
							            	 <input id="selectBusinessname" name="selectBusinessname" type="text" class="input" size="35" value="<s:property value="selectBusinessname"/>"/>
							            	</td>
							            </tr>
							        </table>
					        <table width="100%" class="tableheader">
					          <tr>
					            <td width="5%" height="26"><img src="${pageContext.request.contextPath}/images/bg_mcontentL.gif"  width="32" height="27" /></td>
					            <td valign="middle"><h3><s:text name="业务查询列表"/></h3></td>
					            <td align="right" valign="middle"></td>
					            <td width="1%" align="right"><img src="${pageContext.request.contextPath}/images/bg_mcontentR.gif"  width="7" height="27" /></td>
					          </tr>
					        </table>
					        <table width="100%" class="tableCont"  >
					          <tr>
					            <td align="center" height="300">
					            <div class="tableContent" style="height:300px;width:768px;overflow: auto" >
					                  <table width=100% style="width:auto"  border="0" cellspacing="1" cellpadding="0" >
									<tr>
										  <th nowrap="nowrap"><s:text name="序号"/><input type="hidden" name="hidden_top" value="序号" /></th>
										  <th nowrap="nowrap"><s:text name="用户名"/><input type="hidden" name="hidden_top" value="用户名" /></th>
					                      <th nowrap="nowrap"><s:text name="申请编号"/><input type="hidden" name="hidden_top" value="申请编号" /></th>
					                      <th nowrap="nowrap"><s:text name="临时编号"/><input type="hidden" name="hidden_top" value="临时编号" /></th>
					                      <th nowrap="nowrap"><s:text name="船名"/><input type="hidden" name="hidden_top" value="船名" /></th>
					                      <th nowrap="nowrap"><s:text name="港口城市"/><input type="hidden" name="hidden_top" value="申请人" /></th>
					                      <th nowrap="nowrap"><s:text name="申请人"/><input type="hidden" name="hidden_top" value="申请日期" /></th>
					                      <th nowrap="nowrap"><s:text name="申请日期"/><input type="hidden" name="hidden_top" value="港口城市" /></th>
					                      <th nowrap="nowrap"><s:text name="业务状态"/><input type="hidden" name="hidden_top" value="业务状态" /></th>
					                      <th nowrap="nowrap"><s:text name="实际检查日期"/><input type="hidden" name="hidden_top" value="检查日期" /></th>
					                      <th nowrap="nowrap"><s:text name="付费状态"/><input type="hidden" name="hidden_top" value="付费状态" /></th>
					                      <th nowrap="nowrap"><s:text name="分账状态"/><input type="hidden" name="hidden_top" value="分账状态" /></th>
					                       <c:forEach items="${menu_parent}" var="mp3"
																	varStatus="status">
																	<c:if test="${mp3.rightId == 474}">
					                    
					                    	<th nowrap="nowrap"><s:text name="操作"/></th> 
					                  	</c:if>
					                  	</c:forEach>
										  
					                </tr>
					                <s:if test="#request.selectList.size>0">
					    			<s:iterator value="#request.selectList" status="stat">
					                  <tr>
					                  	<td align="center"><s:property value="#stat.index+1+#request.begin"/><s:hidden name="currPagea" value="#request.currPage1"></s:hidden></td>
					                    <td align="center">${ACCOUNT_NAME }</td>
					                    <!-- 
					                    	每次进入详细页面 利用currPagea传值（当前的页数）
					                     -->
					                  	<s:if test="APPNO!=null"><td align="center" nowrap="nowrap"><a href="getSelectByBusinessid.action?businessid=${BUSINESSID }&currPagea=<s:property value="#request.currPage1" />"><font color="blue">${APPNO }</font></a></td></s:if>
					                    <s:else><td align="center" nowrap="nowrap"><a href="getSelectByBusinessid.action?businessid=${BUSINESSID }&currPagea=<s:property value="#request.currPage1" />"><font color="blue"><s:text name="未知"/></font></a></td></s:else>
					                    <td align="center" nowrap="nowrap">${TEMPNO }</td>
					                    <td align="center" nowrap="nowrap">${VESSELNAME }</td>
					                    
					                    	<s:if test="#session.WW_TRANS_I18N_LOCALE!=null && #session.WW_TRANS_I18N_LOCALE.toString()=='en_US'">
					                  	<td align="center" nowrap="nowrap">${ENGLISHCITYNAME }</td>
					                  	</s:if>
					                  	<s:else>
					                  	<td align="center" nowrap="nowrap">${CITYNAME }</td>
					                  	</s:else>
					                    <td align="center"  nowrap="nowrap">${BUSINESSNAME }</td>
					                    <s:if test="APPDATE==null">
					                  	<td align="center" nowrap="nowrap"><s:text name="未知"/></td>
					                  	</s:if>
					                  	<s:else>
					                  	<td align="center" nowrap="nowrap">${APPDATE }</td>
					                  	</s:else>
					                  	
					                    <s:if test="BUSINESSSTATE==0" >
					                    	<td align="center"  nowrap="nowrap"><s:text name="未提交"/></td>
					                    </s:if>
					                    <s:elseif test="BUSINESSSTATE==1">
					                    	<td align="center" nowrap="nowrap"><s:text name="未受理"/></td>
					                    </s:elseif>
					                    <s:elseif test="BUSINESSSTATE==2" >
					                    	<td align="center" nowrap="nowrap"><s:text name="未通过"/></td>
					                    </s:elseif>
					                    <s:elseif test="BUSINESSSTATE==3" >
					                    	<td align="center" nowrap="nowrap"><s:text name="已受理"/></td>
					                    </s:elseif>
					                    <s:elseif test="BUSINESSSTATE==4">
					                    	<td align="center" nowrap="nowrap"><s:text name="已分配"/></td>
					                    </s:elseif>
					                    <s:elseif test="BUSINESSSTATE==5">
					                    	<td align="center" nowrap="nowrap"><s:text name="已检查"/></td>
					                    </s:elseif>
					                    <s:else>
					                    	<td align="center" nowrap="nowrap"><s:text name="未知"/></td>
					                    </s:else>
					                    <s:if test="OPERATORTIME==null">
					                  	<td align="center" nowrap="nowrap"><s:text name="未知"/></td>
					                  	</s:if>
					                  	<s:else>
					                  	<td align="center" nowrap="nowrap">${OPERATORTIME }</td>
					                  	</s:else>
					                    <s:if test="PAYSTATE==0">
					                    	<td align="center" nowrap="nowrap"><s:text name="未付费未开发票"/></td>
					                    </s:if>
					                    <s:elseif test="PAYSTATE==1">
					                    	<td align="center" nowrap="nowrap"><s:text name="未付费已开发票"/></td>
					                    </s:elseif>
					                    <s:elseif test="PAYSTATE==2">
					                    	<td align="center" nowrap="nowrap"><s:text name="已付费未开发票"/></td>
					                    </s:elseif>
					                    <s:elseif test="PAYSTATE==3">
					                    	<td align="center" nowrap="nowrap"><s:text name="已付费已开发票"/></td>
					                    </s:elseif>
					                    <s:elseif test="PAYSTATE==4">
					                    	<td align="center" nowrap="nowrap"><s:text name="逾期欠款"/></td>
					                    </s:elseif>
					                    <s:else>
					                    	<td align="center" nowrap="nowrap"><s:text name="未知"/></td>
					                    </s:else>
					                    <s:if test="LEDGERSTATE==0">
					                    	<td align="center" nowrap="nowrap"><s:text name="未分账"/></td>
					                    </s:if>
					                    <s:elseif test="LEDGERSTATE==1">
					                    	<td align="center" nowrap="nowrap"><s:text name="已分账"/></td>
					                    </s:elseif>
					                    <s:else>
					                    	<td align="center" nowrap="nowrap"><s:text name="未知"/></td>
					                    </s:else>
					                    <!--<td align="center"><a href="getSelectByBusinessid.action?businessid=${BUSINESSID }" class="content"><s:text name="查看"/></a></td>
					                    -->
					                   <c:forEach items="${menu_parent}" var="mp3"
																	varStatus="status">
																	<c:if test="${mp3.rightId == 474}">
					                    	<td align="center" nowrap="nowrap"><a href="deleteBusiness.action?businessid=${BUSINESSID }" accesskey=""
					                    	onclick="return window.confirm('是否删除！')"><s:text name="删除"/></a></td>
					                  	</c:if>
					                  	</c:forEach>
					                  </tr>
					                  </s:iterator>
					                  </s:if>
					                  </table>
					            </div>
					            </s:form>
					            </td>
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

