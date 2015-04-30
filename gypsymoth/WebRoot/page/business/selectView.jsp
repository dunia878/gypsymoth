<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统——业务查询</title>
		<%@ include file="../include/head.jsp"%>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/dchecktree.js"></script>
		<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/jsFrame.js"></script>
		<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/ajax.js"></script>
	</head>
	
	<script type="text/javascript" charset="utf-8">
		function _onload(){
			var rolemessage = "${rolemsg}";
			if(!(rolemessage==""))
			{
				document.getElementById("role_name_mess").innerHTML="${rolemsg}";
			}
			_roleRights();
		}
        function checkAdd(){
   			document.getElementById("editSelectForm").submit();
        }
        function locking(name)
		  {
		  	if(name=='payment'){
		  	  //var style = document.all.viewpayment.style.display;
		  	  var style = document.getElementById("viewpayment").style.display;
			  if(style=="none"){
			  	//document.all.viewpayment.style.display='block';
			  	//document.paymentform.viewpaymentbutton.value="隐藏详细↑"
			  	document.getElementById("viewpayment").style.display = "block";
			  	document.getElementById("viewpaymentbutton").value = "隐藏详细↑";
			  }
			  else{
			  	//document.all.viewpayment.style.display='none';
			  	//document.paymentform.viewpaymentbutton.value="查看详细↓"
			  	document.getElementById("viewpayment").style.display = "none";
			  	document.getElementById("viewpaymentbutton").value = "查看详细↓";
			  }
			}
			else if(name=='examine'){
				//var style = document.all.viewexamine.style.display;
				var style = document.getElementById("viewexamine").style.display;
				  if(style=="none"){
				  	//document.all.viewexamine.style.display='block';
				  	//document.examineform.viewexaminebutton.value="隐藏详细↑"
				  	document.getElementById("viewexamine").style.display = "block";
			  		document.getElementById("viewexaminebutton").value = "隐藏详细↑";
				  }
				  else{
				  	//document.all.viewexamine.style.display='none';
				  	//document.examineform.viewexaminebutton.value="查看详细↓"
				  	document.getElementById("viewexamine").style.display = "none";
			  		document.getElementById("viewexaminebutton").value = "查看详细↓";
				  }
			}
		  }
    </script>
	<body>
					<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
					  <tr>
					    <td class="list_r">&nbsp;</td>
					    <td valign="top" class="right"><div class="title"><s:text name="您的当前位置"/>： <s:text name="首页"/> &gt;&gt; <s:text name="业务管理"/> &gt;&gt; <strong><s:text name="业务查询"/></strong><font color="red"> &nbsp;&nbsp;&nbsp;(<s:property value="#request.appno" />)</font></div>
					      <div>
					        <table align="center" cellpadding="12">
					          <tr>
					            <td height="300" align="left" valign="top">
					            <!--编辑开始-->
					            
					                
									<s:if test="#request.selectList.size>0">
					    			<s:iterator value="#request.selectList" status="stat">
					    			
					    			<div>
					    			<p>
					    			<font color="red"><b><s:text name="船舶信息"/></b></font>　
					    			<a href="apply.action?id=${ID }" class="content" target="_blank">[<s:text name='预览申请单'/>]</a>　
									<a href="payment.action?id=${ID }" class="content" target="_blank">[<s:text name='预览收费单'/>(<s:text name='中文'/>)]</a>
									<a href="paymentEnglish!payment.action?id=${ID }" class="content" target="_blank">[<s:text name='预览收费单'/>(<s:text name='英文'/>)]</a>　
									<s:if test="#request.get('isshow')==1">
									<a href="getAllotPageView.action?id=${ID }" class="content" target="_blank">[<s:text name='预览证书'/>]</a>
									</s:if>
					    			</p>
					    			</div>
					    			
					    			<table width="700" align="center" cellspacing="5" class="tabedit">
					                  <tr>
					                    <td height="20"><s:text name="船舶名称"/></td>
					                    <td width="120"><label>
					                      <input id="vesselname" name="business.vesselname" type="text" class="input" size="20"  value="${VESSELNAME }" disabled="disabled" />
					                    </label></td>

					                    <td height="20"><s:text name="船舶类型"/></td>
					                    <td width="120"><label>
					                      <input id="vesseltype" name="business.vesseltype" type="text" class="input" size="20" value="${VESSELTYPE }" disabled="disabled" />
					                    </label></td>

					                    <td height="20"></td>
					                    <td width="120">
					                    	<c:forEach items="${menu_parent}" var="mp3" varStatus="status">
												<c:if test="${mp3.rightId == 472}">
							                    	<input name="" class="button" type="button" value="修改业务信息"
															onclick="window.location.href='selectgetBusinessById.action?id=${ID }&view=1&goPage=<s:property value="#request.currPage"/>'" />
												</c:if>
											</c:forEach>
					                    </td>
					                  </tr>
					                  <tr>
					                    <td height="20"><s:text name="船籍"/></td>
					                    <td><label>
					                      <input id="registry" name="business.registry" type="text" class="input" size="20" value="${REGISTRY }" disabled="disabled" />
					                    </label></td>

					                    <td height="20"><s:text name="IMO号"/></td>
					                    <td><label>
					                      <input id="imo" name="business.imo" type="text" class="input" size="20" value="${IMO }" disabled="disabled" />
					                    </label></td>

					                    <td height="20"><s:text name="总吨位"/></td>
					                    <td><label>
					                      <input id="tonnage2" name="tonnage2" type="text" class="input" size="20" value="${TONNAGE }"  disabled="disabled"/>
					                    </label></td>
					                  </tr>
					                  <tr>
					                    <td height="20"><s:text name="船东或租船人名称"/></td>
					                    <td colspan="5"><label>
					                      <input id="company" name="business.company" type="text" class="input" size="100" value="${COMPANY }" disabled="disabled" />
					                    </label></td>
					                  </tr>
					                </table>
					                <p>
					    			<font color="red"><b><s:text name="申请信息"/></b></font>　
					    			</p>
					    			<table width="700" align="center" cellspacing="5" class="tabedit">
					                  <tr>
					                    <td height="20"><s:text name="申请人"/></td>
					                    <td width="120" colspan="5"><label>
					                      <input id="businessname2" name="businessname2" type="text" class="input" size="112" value="${BUSINESSNAME }"  disabled="disabled"/>
					                    </label></td>
					                  </tr>
					                  <tr>
					                    <td height="20"><s:text name="申请编号"/></td>
					                    <td><label>
					                      <input id="appno2" name="appno2" type="text" class="input" size="20" value="${APPNO }"  disabled="disabled"/>
					                    </label></td>

					                    <td height="20"><s:text name="申请日期"/></td>
					                    <td><label>
					                      <input id="appdate2" name="appdate2" type="text" class="input" size="20" value="${APPDATE }"  disabled="disabled"/>
					                    </label></td>

					                    <td height="20"><s:text name="用户名"/></td>
					                    <td><label>
					                      <input id="appdate2" name="appdate2" type="text" class="input" size="20" value="${ACCOUNT_NAME }"  disabled="disabled"/>
					                    </label></td>
					                  </tr>
					                  <tr>
					                    <td height="20"><s:text name="计划入港日期"/></td>
					                    <td><label>
					                      <input id="plandatein" name="business.plandatein" type="text" class="input" size="20" value="${PLANDATEIN }" disabled="disabled" />
					                    </label></td>

					                    <td height="20"><s:text name="计划离港日期"/></td>
					                    <td><label>
					                      <input id="plandateout" name="business.plandateout" type="text" class="input" size="20" value="${PLANDATEOUT }" disabled="disabled" />
					                    </label></td>

					                    <td height="20"><s:text name="计划检查日期"/></td>
					                    <td><label>
					                      <input id="checkdate" name="business.checkdate" type="text" class="input" size="20" value="${CHECKDATE }" disabled="disabled" />
					                    </label></td>
					                  </tr>
					                  <tr>
					                    <td height="20"><s:text name="港口城市"/></td>
					                    <td><label>
					                      <s:if test="#session.WW_TRANS_I18N_LOCALE!=null && #session.WW_TRANS_I18N_LOCALE.toString()=='en_US'">
					                  	  <input id="cityname" name="cityname" type="text" class="input" size="20" value="${ENGLISHCITYNAME }"  disabled="disabled"/>
					                  	  </s:if>
					                  	  <s:else>
					                  	  <input id="cityname" name="cityname" type="text" class="input" size="20" value="${CITYNAME }"  disabled="disabled"/>
					                  	  </s:else>
					                    </label></td>

					                    <td height="20"><s:text name="港口"/></td>
					                    <td><label>
					                      <s:if test="#session.WW_TRANS_I18N_LOCALE!=null && #session.WW_TRANS_I18N_LOCALE.toString()=='en_US'">
					                  	  <input id="portid" name="business.portid" type="text" class="input" size="20" value="${PORT_SNAME }"  disabled="disabled"/>
					                  	  </s:if>
					                  	  <s:else>
					                  	  <input id="portid" name="business.portid" type="text" class="input" size="20" value="${PORT_NAME }"  disabled="disabled"/>
					                  	  </s:else>
					                    </label></td>

					                    <td height="20"><s:text name="泊位"/></td>
					                    <td><label>
					                      <input id="berth" name="business.berth" type="text" class="input" size="20" value="${BERTH }" disabled="disabled" />
					                    </label></td>
					                  </tr>
					                </table>
					                <p>
					    			<font color="red"><b><s:text name="业务状态"/></b></font>　
					    			</p>
					                <table width="700" align="center" cellspacing="5" class="tabedit">
					                  <tr>
					                    <td height="20"><s:text name="业务状态"/></td>
					                    <td width="120"><label>
					                    	<s:if test="BUSINESSSTATE==0">
					                    		<input id="businessstate0" name="businessstate0" type="text" class="input" size="20" value="<s:text name="未提交"/>"  disabled="disabled"/>
					                    	</s:if>
					                    	<s:elseif test="BUSINESSSTATE==1">
					                    		<input id="businessstate1" name="businessstate1" type="text" class="input" size="20" value="<s:text name="未受理"/>"  disabled="disabled"/>
					                    	</s:elseif>
					                    	<s:elseif test="BUSINESSSTATE==2">
					                    		<input id="businessstate2" name="businessstate2" type="text" class="input" size="20" value="<s:text name="未通过"/>"  disabled="disabled"/>
					                    	</s:elseif>
					                    	<s:elseif test="BUSINESSSTATE==3">
					                    		<input id="businessstate3" name="businessstate3" type="text" class="input" size="20" value="<s:text name="已受理"/>"  disabled="disabled"/>
					                    	</s:elseif>
					                    	<s:elseif test="BUSINESSSTATE==4">
					                    		<input id="businessstate4" name="businessstate4" type="text" class="input" size="20" value="<s:text name="已分配"/>"  disabled="disabled"/>
					                    	</s:elseif>
					                    	<s:elseif test="BUSINESSSTATE==5">
					                    		<input id="businessstate5" name="businessstate5" type="text" class="input" size="20" value="<s:text name="已检查"/>"  disabled="disabled"/>
					                    	</s:elseif>
					                    	<s:else>
					                    		<input id="businessstate6" name="businessstate6" type="text" class="input" size="20" value=""  disabled="disabled"/>
					                    	</s:else>
					                    </label></td>

					                    <td height="20"><s:text name="缴费状态"/></td>
					                    <td width="120"><label>
					                    	<s:if test="PAYSTATE==0">
					                    		<input id="roleName" name="role.roleName" type="text" class="input" size="20" value="<s:text name="未付费未开发票"/>" disabled="disabled" />
					                    	</s:if>
					                    	<s:elseif test="PAYSTATE==1">
					                    		<input id="roleName" name="role.roleName" type="text" class="input" size="20" value="<s:text name="未付费已开发票"/>" disabled="disabled" />
					                    	</s:elseif>
					                    	<s:elseif test="PAYSTATE==2">
					                    		<input id="roleName" name="role.roleName" type="text" class="input" size="20" value="<s:text name="已付费未开发票"/>" disabled="disabled" />
					                    	</s:elseif>
					                    	<s:elseif test="PAYSTATE==3">
					                    		<input id="roleName" name="role.roleName" type="text" class="input" size="20" value="<s:text name="已付费已开发票"/>" disabled="disabled" />
					                    	</s:elseif>
					                    	<s:elseif test="PAYSTATE==4">
					                    		<input id="roleName" name="role.roleName" type="text" class="input" size="20" value="<s:text name="逾期欠款"/>" disabled="disabled" />
					                    	</s:elseif>
					                    	<s:else>
					                    		<input id="businessstate6" name="businessstate6" type="text" class="input" size="20" value=""  disabled="disabled"/>
					                    	</s:else>
					                    </label></td>

					                    <td height="20"><s:text name="分账状态"/></td>
					                    <td width="120"><label>
					                      	<s:if test="LEDGERSTATE==0">
					                    		<input id="roleName" name="role.roleName" type="text" class="input" size="20" value="<s:text name="未分账"/>" disabled="disabled" />
					                    	</s:if>
					                    	<s:elseif test="LEDGERSTATE==1">
					                    		<input id="roleName" name="role.roleName" type="text" class="input" size="20" value="<s:text name="已分账"/>" disabled="disabled" />
					                    	</s:elseif>
					                    	<s:else>
					                    		<input id="businessstate6" name="businessstate6" type="text" class="input" size="20" value=""  disabled="disabled"/>
					                    	</s:else>
					                    </label></td>
					                  </tr>
					                  <tr>
					                  	<td height="20"><s:text name="审核意见"/></td>
					                  	<td colspan="5">
					                  		<textarea cols="49" rows="3" id="remark" style="word-break:break-all" 
												name="remark" disabled="disabled">${REMARK }</textarea>
					                  	</td>
					                  </tr>
					                </table>

					                <form id="paymentform" name="paymentform">
					                <p>
					                	<font color="red"><b><s:text name="财务收费"/></b></font>
					                	<s:if test="#session.account.accountType==1">
					                		<input id="viewpaymentbutton" name="viewpaymentbutton" type="button" class="button" value="查看详细↓" onclick="locking('payment')"/>
					                	</s:if>
					                </p>
					                <table width="700" align="center" cellspacing="5" class="tabedit">
					                  <tr>
					                    <td height="20"><s:text name="应收合计"/></td>
					                    <td><label>
					                      <input id="total" name="total" type="text" class="input" size="10" value="${PAYSTATE }" disabled="disabled" />
					                    </label></td>
					                    
					                    <td height="20"><s:text name="应缴检查费金额"/></td>
					                    <td><label>
					                      <input id="money" name="money" type="text" class="input" size="10" value="${MONEY }" disabled="disabled" />
					                    </label></td>
					                    
					                    <td height="20"><s:text name="特别交通费"/></td>
					                    <td><label>
					                      <input id="specialpay" name="specialpay" type="text" class="input" size="10" value="${SPECIALPAY }" disabled="disabled" />
					                    </label></td>
					                  </tr>
					                  </table>
					                  </form>
					                  <s:if test="#session.account.accountType==1">
					                  <div id="viewpayment" style="display: none;">
					                  <table width="700" align="center" cellspacing="5" class="tabedit">
					                  <tr>
					                    <td height="20">实缴金额</td>
					                    <td><label>
					                      <input id="roleName" name="role.roleName" type="text" class="input" size="10" value="${AMOUNT }" disabled="disabled" />
					                    </label></td>

					                    <td height="20">到账日期</td>
					                    <td><label>
					                      <input id="roleName" name="role.roleName" type="text" class="input" size="10" value="${PAYMENTDATE }" disabled="disabled" />
					                    </label></td>
					                    
					                    <td height="20">付款单位</td>
					                    <td><label>
					                      <input id="roleName" name="role.roleName" type="text" class="input" size="10" value="${PAYMENT_BY }" disabled="disabled" />
					                    </label></td>
					                  </tr>
					                  <tr>
					                    <td height="20">分账状态</td>
					                    <td><label>
					                      	<s:if test="LEDGERSTATE==0">
					                    		<input id="roleName" name="role.roleName" type="text" class="input" size="10" value="未分账" disabled="disabled" />
						                    </s:if>
						                    <s:elseif test="LEDGERSTATE==1">
						                    	<input id="roleName" name="role.roleName" type="text" class="input" size="10" value="已分账" disabled="disabled" />
						                    </s:elseif>
						                    <s:else>
						                    	<input id="roleName" name="role.roleName" type="text" class="input" size="10" value="未知" disabled="disabled" />
						                    </s:else>
					                    </label></td>
					                    
					                    <td height="20">分账日期</td>
					                    <td><label>
					                      <input id="roleName" name="role.roleName" type="text" class="input" size="10" value="${LEDGERDATE }" disabled="disabled" />
					                    </label></td>
					                    
					                    <td height="20">缴费状态</td>
					                    <td><label>
					                    	<s:if test="PAYSTATE==0">
						                    	<input id="roleName" name="role.roleName" type="text" class="input" size="10" value="未付费未开发票" disabled="disabled" />
						                    </s:if>
						                    <s:elseif test="PAYSTATE==1">
						                    	<input id="roleName" name="role.roleName" type="text" class="input" size="10" value="未付费已开发票" disabled="disabled" />
						                    </s:elseif>
						                    <s:elseif test="PAYSTATE==2">
						                    	<input id="roleName" name="role.roleName" type="text" class="input" size="10" value="已付费未开发票" disabled="disabled" />
						                    </s:elseif>
						                    <s:elseif test="PAYSTATE==3">
						                    	<input id="roleName" name="role.roleName" type="text" class="input" size="10" value="已付费已开发票" disabled="disabled" />
						                    </s:elseif>
						                    <s:elseif test="PAYSTATE==4">
						                    	<input id="roleName" name="role.roleName" type="text" class="input" size="10" value="逾期欠款" disabled="disabled" />
						                    </s:elseif>
						                    <s:else>
						                    	<input id="roleName" name="role.roleName" type="text" class="input" size="10" value="未知" disabled="disabled" />
						                    </s:else>
					                    </label>
					                    </td>
					                  </tr>
					                  <tr>
					                    <td height="20" colspan="5" align="right">检验公司所分检查费金额</td>
					                    <td><label>
					                      <input id="roleName" name="role.roleName" type="text" class="input" size="10" value="${FIRSTCOMPAY }" disabled="disabled" />
					                    </label></td>
					                  </tr>
					                  <tr>
					                    <td height="20" colspan="5" align="right">一级公司（${TWONAME }）所分检查费金额</td>
					                    <td><label>
					                      <input id="roleName" name="role.roleName" type="text" class="input" size="10" value="${SECONDCOMPAY }" disabled="disabled" />
					                    </label></td>
					                  </tr>
					                  <tr>
					                    <td height="20" colspan="5" align="right">二级公司（${THREENAME }）所分检查费金额</td>
					                    <td><label>
					                      <input id="roleName" name="role.roleName" type="text" class="input" size="10" value="${THIRDCOMPAY }" disabled="disabled" />
					                    </label></td>
					                  </tr>
					                  
					                </table>
					                </div>
					                </s:if>
					                <form id="examineform" name="examineform">
					                <p>
					                	<font color="red"><b><s:text name="检查结果"/></b></font>
					                	<s:if test="#session.account.accountType==1">
					                		<input id="viewexaminebutton" name="viewexaminebutton" type="button" class="button" value="查看详细↓" onclick="locking('examine')"/>
					                	</s:if>
					                </p>
					                <table width="700" align="center" cellspacing="5" class="tabedit">
					                  <tr>
					                    <td height="20"><s:text name="检查日期"/></td>
					                    <td><label>
					                      <input id="operatortime" name="operatortime" type="text" class="input" size="10" value="${OPERATORTIME }" disabled="disabled" />
					                    </label></td>

					                    <td height="20"><s:text name="实施检查公司"/></td>
					                    <td><label>
					                      <input id="roleName" name="role.roleName" type="text" class="input" size="10" value="<s:property value="#request.orgName"/>" disabled="disabled" />
					                    </label></td>
					                  </tr>

					                  <tr>
					                    <td height="20"><s:text name="授权签字人"/></td>
					                    <td><label>
			                        		 <s:iterator value="#request.assignmentlist" id="ite"> 
						                	<s:if test="#ite.ASSIGNROLE==1">
							                	<s:property value="#ite.ORG_NAME"/> - <s:property value="#ite.NAME"/>&nbsp;&nbsp;
						                	</s:if>
							                </s:iterator>
					                    </label></td>

					                    <td height="20"><s:text name="检查员"/></td>
					                    <td><label>
				                    	 	<s:iterator value="#request.assignmentlist" id="ite"> 
							                <s:if test="#ite.ASSIGNROLE==2"> 
									                  <s:property value="#ite.ORG_NAME"/> - <s:property value="#ite.NAME"/>&nbsp;&nbsp;<br/>
							                </s:if> 
							                </s:iterator>
					                    </label></td>
					                  </tr>   
					                </table>
					                </form>
					                <s:if test="#session.account.accountType==1">
					                <div id="viewexamine" style="display: none;">
					                <table width="700" align="center" cellspacing="5" class="tabedit">
					                  <tr>
					                    <td height="20"><s:text name="舞毒蛾卵块(数量)"/></td>
					                    <td><label>
					                      <input id="roleName" name="role.roleName" type="text" class="input" size="10" value="${GYPSYMOTH1 }" disabled="disabled" />
					                    </label></td>

					                    <td height="20"><s:text name="舞毒蛾幼虫(数量)"/></td>
					                    <td><label>
					                      <input id="roleName" name="role.roleName" type="text" class="input" size="10" value="${GYPSYMOTH2 }" disabled="disabled" />
					                    </label></td>

					                    <td height="20"><s:text name="舞毒蛾蛹(数量)"/></td>
					                    <td><label>
					                      <input id="roleName" name="role.roleName" type="text" class="input" size="10" value="${GYPSYMOTH3 }" disabled="disabled" />
					                    </label></td>
					                  </tr>
					                  <tr>
					                    <td height="20"><s:text name="舞毒蛾成虫(数量)"/></td>
					                    <td><label>
					                      <input id="roleName" name="role.roleName" type="text" class="input" size="10" value="${GYPSYMOTH4 }" disabled="disabled" />
					                    </label></td>

					                    <td height="20"><s:text name="舞毒蛾疑似(数量)"/></td>
					                    <td><label>
					                      <input id="roleName" name="role.roleName" type="text" class="input" size="10" value="${DOUBT }" disabled="disabled" />
					                    </label></td>
					                    
					                    <td height="20"></td>
					                    <td></td>
					                  </tr>
					                  <tr>
					                    <td height="20"><s:text name="证书"/></td>
					                    <td><label>
					                      <s:if test="#request.certificateList.size>0">
					    					<!--<s:iterator value="#request.certificateList" status="st">
					    						<a href="${pageContext.request.contextPath}/page/include/downloadFile.jsp?fileUrl=${certificate}"><u style="color: blue"><font color="blue"><s:text name="证书"/><s:property value="#st.index+1"/></font></u></a>　
					    					</s:iterator>
					    				  --></s:if>
											<%int a=1; %>
					    				  	<c:forEach items="${certificateMap}" var="map">
					    				  	<a href="${pageContext.request.contextPath}/page/include/downloadFile.jsp?fileUrl=<c:out value="${map.value}"></c:out>"><u style="color: blue"><font color="blue"><s:text name="证书"/><%out.print(a ++);%></font></u></a>			    				  	
											</c:forEach>
					    				  <s:else><s:text name="暂无"/></s:else>
					                    </label></td>
					                     <td height="20" align="left"><s:text name="证书流水号"/></td>
					                    <td align="left" colspan="3"><label>
					                      <input id="roleName" name="role.roleName" type="text" class="input" size="10" value="${CERTNO }" disabled="disabled" />
					                    </label></td>
					                  </tr>
					                  <tr>
					                    <td height="20"><s:text name="检查记录表"/></td>
					                    <td colspan="5"><label>
					                      <s:if test="#request.examrecordList.size>0">
					    					<!--<s:iterator value="#request.examrecordList" status="st">
					    						<a href="${pageContext.request.contextPath}/page/include/downloadFile.jsp?fileUrl=${examrecord }"><u style="color: blue"><font color="blue"><s:text name="检查记录表"/><s:property value="#st.index+1"/></font></u></a>　
					    					</s:iterator>
					    				  -->
					    				  <%int b= 1; %>
					    				  <c:forEach items="${examrecordMap}" var="map">
					    				  	<a href="${pageContext.request.contextPath}/page/include/downloadFile.jsp?fileUrl=<c:out value="${map.value}"></c:out>"><u style="color: blue"><font color="blue"><s:text name="检查记录表"/><%out.print(b ++);%></font></u></a>					    				  	
										  </c:forEach>
					    				  </s:if>
					    				  <s:else><s:text name="暂无"/></s:else>
					                    </label></td>
					                  </tr>
					                  <tr>
					                    <td height="20"><s:text name="工作照"/></td>
					                    <td colspan="5"><label>
					                      <s:if test="#request.workphotoList.size>0">
					    					<!--<s:iterator value="#request.workphotoList" status="st">
					    						<a target="_blank" href="${pageContext.request.contextPath}/attachment/${workphoto }"><img src="${pageContext.request.contextPath}/attachment/${workphoto }" width="40" height="40" /></a>　
					    					</s:iterator>
					    				  -->
					    				  
					    				  <c:forEach items="${workphotoMap}" var="map">
					    				  		<a target="_blank" href="${pageContext.request.contextPath}/attachment/<c:out value="${map.value}"></c:out>"><img src="${pageContext.request.contextPath}/attachment/<c:out value="${map.value}"></c:out>" width="40" height="40" /></a>
										  </c:forEach>
					    				  </s:if>
					    				  <s:else><s:text name="暂无"/></s:else>
					                    </label></td>
					                  </tr>
					                  <tr>
					                    <td height="20"><s:text name="舞毒蛾照片"/></td>
					                    <td colspan="5"><label>
					                      <s:if test="#request.gypsymothphotoList.size>0">
					    					<!--<s:iterator value="#request.gypsymothphotoList" status="st">
					    						<a target="_blank" href="${pageContext.request.contextPath}/attachment/${gypsymothphoto }"><img src="${pageContext.request.contextPath}/attachment/${gypsymothphoto }" width="40" height="40" /></a>　
					    					</s:iterator>
					    				  -->
					    				  <c:forEach items="${gypsymothphotoMap}" var="map">
					    				  		<a target="_blank" href="${pageContext.request.contextPath}/attachment/<c:out value="${map.value}"></c:out>"><img src="${pageContext.request.contextPath}/attachment/<c:out value="${map.value}"></c:out>" width="40" height="40" /></a>
										  </c:forEach>
					    				  </s:if>
					    				  <s:else><s:text name="暂无"/></s:else>
					                    </label></td>
					                  </tr><!--
					                
					                  
					                  <tr>
					                    <td height="20"><s:text name="上传船舶资料"/></td>
					                    <td colspan="5"><label>
					                      <s:if test="#request.shipinfoList.size>0">
					    					<s:iterator value="#request.shipinfoList" status="st">
					    						<a target="_blank" href="${pageContext.request.contextPath}/attachment/${shipinfoList }"><img src="${pageContext.request.contextPath}/attachment/${shipinfoList }" width="40" height="40" /></a>　
					    					</s:iterator>
					    				  </s:if>
					    				  <s:else><s:text name="暂无"/></s:else>
					                    </label></td>
					                  </tr>
					                  
					                  <tr>
					                    <td height="20"><s:text name="上传船舶停靠港清单"/></td>
					                    <td colspan="5"><label>
					                      <s:if test="#request.stopportinfoList.size>0">
					    					<s:iterator value="#request.stopportinfoList" status="st">
					    						<a target="_blank" href="${pageContext.request.contextPath}/attachment/${stopportinfoList }"><img src="${pageContext.request.contextPath}/attachment/${stopportinfoList }" width="40" height="40" /></a>　
					    					</s:iterator>
					    				  </s:if>
					    				  <s:else><s:text name="暂无"/></s:else>
					                    </label></td>
					                  </tr>
					                  
					                  
					                  
					                  --><tr>
					                    <td height="20"><s:text name="船舶资料"/></td>
					                      <td colspan="5"><label>
					                      <s:if test="#request.shipinfoList.size>0">
					    					<!--<s:iterator value="#request.shipinfoList" status="st">
					    						<a href="${pageContext.request.contextPath}/page/include/downloadFile.jsp?fileUrl=${shipinfo }"><u style="color: blue"><font color="blue"><s:text name="上传船舶停靠港清单"/><s:property value="#st.index+1"/></font></u></a>　
					    					</s:iterator>
					    				  -->
					    				  <%int c = 1; %>
					    				  <c:forEach items="${shipinfoMap}" var="map">
					    				  	<a href="${pageContext.request.contextPath}/page/include/downloadFile.jsp?fileUrl=<c:out value="${map.value}"></c:out>"><u style="color: blue"><font color="blue"><s:text name="上传船舶资料"/><%out.print(c ++);%></font></u></a>　					    				  	
										  </c:forEach>	
					    				  </s:if>
					    				  <s:else><s:text name="暂无"/></s:else>
					                    </label></td>					                    
					                  </tr>
					                  <tr>
					                    <td height="20"><s:text name="船舶停靠港清单"/></td>
					                    <td colspan="5"><label>
					                      <s:if test="#request.stopportinfoList.size>0">
					    					<!--<s:iterator value="#request.stopportinfoList" status="st">
					    						<a href="${pageContext.request.contextPath}/page/include/downloadFile.jsp?fileUrl=${stopportinfo }"><u style="color: blue"><font color="blue"><s:text name="上传船舶停靠港清单"/><s:property value="#st.index+1"/></font></u></a>　
					    					</s:iterator>
					    				  -->
					    				  <%int d = 1; %>
					    				  <c:forEach items="${stopportinfoMap}" var="map">
					    				  	<a href="${pageContext.request.contextPath}/page/include/downloadFile.jsp?fileUrl=<c:out value="${map.value}"></c:out>"><u style="color: blue"><font color="blue"><s:text name="上传船舶停靠港清单"/><%out.print(d ++);%></font></u></a>　					    				  	
										  </c:forEach>	
					    				 
					    				  </s:if>
					    				  <s:else><s:text name="暂无"/></s:else>
					                    </label></td>
					                  <!--   <c:forEach items="${menu_parent}" var="mp5" varStatus="status">
					                    <c:if test="${mp5.rightId == 472}">
					                    	<s:if test="#request.bs == 5">
					                    	<td>
					                    		<s:form action="updateIt" method="post">
					                    			<s:hidden name="bsid" value="%{businessid}"></s:hidden>
					                    			
					                    			<input class="button" type="submit" value="修改附件" />
					                    		</s:form>
					                    	</td>
					                    	</s:if>
					                    </c:if>
					                    </c:forEach>
					                    -->
					                  </tr>
					                  
					                  
					                </table>
					                </div>
					                </s:if>
					                </s:iterator>
					                </s:if>
					                <s:if test="#request.desCountrylist.size>0">
					                <p><font color="red"><b><s:text name="目的国及港口"/></b></font></p>
					    			<table width="700" align="center" cellspacing="5" class="tabedit">
					    			<s:iterator value="#request.desCountrylist" status="stat">
					    			  <tr>
					    			  	<td height="20"><s:text name="目的国到达顺序"/></td>
					                    <td><label>
					                      <input id="roleName" name="role.roleName" type="text" class="input" size="10" value="${DESINDEX+1 }" disabled="disabled" />
					                    </label></td>
					                    <td height="20"><s:text name="目的国"/></td>
					                    <td><label>
					                      <input id="roleName" name="role.roleName" type="text" class="input" size="10" value="${COUNTRY }" disabled="disabled" />
					                    </label></td>
					                    <td height="20"><s:text name="目的国港口"/></td>
					                    <td><label>
					                      <input id="roleName" name="role.roleName" type="text" class="input" size="10" value="${PORT }" disabled="disabled" />
					                    </label></td>
					                  </tr>
					                  </s:iterator>
					                  
					                  <c:forEach items="${menu_parent}" var="mp5" varStatus="status">
									  <c:if test="${mp5.rightId == 472}">
					                  <tr>
					                  	<td height="20">北美到岸检查结果</td>
					                    <td><label>					                      
					                      <select id="qualified" name="business.qualified" style="width: 130px">
					                      	<option value="2" >请选择</option>
					                      	<option value="0" <s:if test="#request.QUALIFIED==0">selected="selected"</s:if> >合格</option>
					                      	<option value="1"<s:if test="#request.QUALIFIED==1">selected="selected"</s:if> >不合格</option>
					                      </select> 
					                    </label></td>
					                  	<td height="20"></td>
					                  	<td>
					                  		<input class="button" type="button" value="修改检查结果" onclick="javascript:ajaxsubmit('updateQualified.action','qualified','<s:property value="businessid"/>','qualifieddiv');" />
					                  	</td>
					                  	<td colspan="2"><div id="qualifieddiv"></div></td>
					                  </tr> 
					                  </c:if>
					                  </c:forEach>
					                  
					                  </table>
					    			</s:if>
					    			<!-- 
					    			<s:if test="#request.examPortlist.size>0">
					    			<p><font color="red"><b><s:text name="途经港口"/></b></font></p>
					    			<table width="700" align="center" cellspacing="5" class="tabedit">
					    			<s:iterator value="#request.examPortlist" status="stat">
					    			  <tr>
					                    <td width="68" height="20"><s:text name="停靠港口"/></td>
					                    <td><label>
					                      <input id="roleName" name="role.roleName" type="text" class="input" size="10" value="${PORT_NAME }" disabled="disabled" />
					                    </label></td>
					                    <td width="68" height="20"><s:text name="停靠时间"/></td>
					                    <td><label>
					                      <input id="roleName" name="role.roleName" type="text" class="input" size="10" value="${STOP_TIME }" disabled="disabled" />
					                    </label></td>
					                  </tr>
					    			</s:iterator>
					    			</table>
					    			</s:if>
					    			 -->
					    			<s:if test="#session.account.accountType==1">
					    			<p><font color="red"><b>系统操作记录</b></font></p>
					    			<s:if test="#request.operatorlist.size>0">
					    			<table width="700" align="center" cellspacing="5" class="tabedit">
					    			<s:iterator value="#request.operatorlist" status="stat">
					    			  <tr>
					                    
					                    <td height="20">操作类型</td>
					                    <td><label>
					                    	<s:if test="DISPOSETYPE==1">
					                    	<input id="roleName" name="role.roleName" type="text" class="input" size="10" value="提交" disabled="disabled" />
					                    	</s:if>
					                    	<s:elseif test="DISPOSETYPE==2">
					                    	<input id="roleName" name="role.roleName" type="text" class="input" size="10" value="驳回" disabled="disabled" />
					                    	</s:elseif>
					                    	<s:elseif test="DISPOSETYPE==3">
					                    	<input id="roleName" name="role.roleName" type="text" class="input" size="10" value="受理" disabled="disabled" />
					                    	</s:elseif>
					                    	<s:elseif test="DISPOSETYPE==4">
					                    	<input id="roleName" name="role.roleName" type="text" class="input" size="10" value="分配" disabled="disabled" />
					                    	</s:elseif>
					                    	<s:elseif test="DISPOSETYPE==5">
					                    	<input id="roleName" name="role.roleName" type="text" class="input" size="10" value="检查" disabled="disabled" />
					                    	</s:elseif>
					                    </label></td>
					                    <td height="20">操作人用户名</td>
					                    <td><label>
					                      <input id="roleName" name="role.roleName" type="text" class="input" size="10" value="${ACCOUNT_NAME }" disabled="disabled" />
					                    </label></td>
					                    <td height="20">操作时间</td>
					                    <td><label>
					                      <input id="roleName" name="role.roleName" type="text" class="input" size="10" value="${OPERATORTIME }" disabled="disabled" />
					                    </label></td>
					                  </tr> 
					    			</s:iterator>
					    			</table>
					    			</s:if>
					    			</s:if>
					    			<table width="700" align="center" cellspacing="5">
					                  <tr>
					                    <td height="38" align="left"></td>
					                    <td height="38" align="center">

											<input name="" class="button"
													onclick="window.location.href='getSelectByPage.action?goPage=<s:property value="#request.currPage"/>'" type="button"
													value="<s:text name='return'/>" />    
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
					<script type="text/javascript" charset="utf-8">
						//初始化应收合计
						var money = document.paymentform.money.value;
						var specialpay = document.paymentform.specialpay.value;
						if(money == ""){
						money=0;
						}
						if(specialpay == ""){
						specialpay=0;
						}
						document.paymentform.total.value = parseFloat(money)+parseFloat(specialpay);
					</script>
	</body>
</html>
