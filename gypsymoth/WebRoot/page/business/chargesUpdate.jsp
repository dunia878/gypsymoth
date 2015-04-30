<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统——业务管理</title>
		<%@ include file="../include/head.jsp"%>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/dchecktree.js"></script>
		<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/jsFrame.js"></script>
		<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
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
		
		/*	到账记录修改后计算其他值	*/
		function valiamount()
		{
			var amount1 = $("#amount1").val();
			var yingshouMoney = document.editPaymentForm.yingshouMoney.value;
			var yingshouSpecialpay = document.editPaymentForm.yingshouSpecialpay.value;
			document.editPaymentForm.yingshouAll.value = parseFloat(yingshouMoney)+parseFloat(yingshouSpecialpay);
			document.editPaymentForm.shishouAll.value = parse_Number(amount1);
			
			if(parseFloat(amount1)<parseFloat(yingshouMoney)+parseFloat(yingshouSpecialpay)){
				if(parseFloat(amount1)<=parseFloat(yingshouMoney)){
					$("#shishouMoney").val(parse_Number(amount1));
					$("#shishouSpecialpay").val(0); 
				}else{
					$("#shishouMoney").val(parse_Number(yingshouMoney));
					$("#shishouSpecialpay").val(parse_Number(amount1)-parse_Number(yingshouMoney)); 
				}
			}else if(parseFloat(amount1)==parseFloat(yingshouMoney)+parseFloat(yingshouSpecialpay)){
				$("#shishouMoney").val(parse_Number(yingshouMoney));
				$("#shishouSpecialpay").val(parse_Number(yingshouSpecialpay)); 
			}else if(parseFloat(amount1)>parseFloat(yingshouMoney)+parseFloat(yingshouSpecialpay)){
				$("#shishouMoney").val(parse_Number(amount1)-parse_Number(yingshouSpecialpay));
				$("#shishouSpecialpay").val(parse_Number(yingshouSpecialpay));
			}
			
		}
		
		/*	数字	*/
		function parse_Number(number)
		{
			var parsenumber = parseFloat(number);
			if(isNaN(parsenumber))
				return 0;
			return parseFloat(number);
		}
		
		/**
		*某个数据改变时，计算其他数据值
		*/
		function count(){
			var yingshouMoney = document.editPaymentForm.yingshouMoney.value;
			var yingshouSpecialpay = document.editPaymentForm.yingshouSpecialpay.value;
			var amount1 = document.editPaymentForm.amount1.value;
			//var amount2 = document.editPaymentForm.amount2.value;
			if(yingshouMoney==""){
				yingshouMoney=0;
			}
			if(yingshouSpecialpay==""){
				yingshouSpecialpay=0;
			}
			if(amount1==""){
				amount1=0;
			}
			//if(amount2==""){
			//	amount2=0;
			//}
			document.editPaymentForm.yingshouAll.value = parseFloat(yingshouMoney)+parseFloat(yingshouSpecialpay);
			document.editPaymentForm.shishouAll.value = parseFloat(amount1);//+parseFloat(amount2);
			var shishouAll = document.editPaymentForm.shishouAll.value;
			var shishouMoney = document.editPaymentForm.shishouMoney.value;
			var shishouSpecialpay = document.editPaymentForm.shishouSpecialpay.value;
			if((shishouMoney=="" || shishouMoney==0)&&(shishouSpecialpay=="" || shishouSpecialpay==0)){
				
				if(parseFloat(shishouAll)>=parseFloat(yingshouMoney)){
				document.editPaymentForm.shishouMoney.value = parseFloat(yingshouMoney);
				document.editPaymentForm.shishouSpecialpay.value = parseFloat(shishouAll)-parseFloat(yingshouMoney);
				specialChange();
				moneyChange();
				}
				else{
					document.editPaymentForm.shishouMoney.value = parseFloat(shishouAll);
					document.editPaymentForm.shishouSpecialpay.value = 0;
					moneyChange();
				}
			
			}
			var money1 = document.editPaymentForm.money1.value;
			var money2 = document.editPaymentForm.money2.value;
			var money3 = document.editPaymentForm.money3.value;
			var specialpay1 = document.editPaymentForm.specialpay1.value;
			var specialpay2 = document.editPaymentForm.specialpay2.value;
			var specialpay3 = document.editPaymentForm.specialpay3.value;
			if(specialpay1==""){
				specialpay1=0;
				document.editPaymentForm.specialpay1.value=0;
			}
			if(specialpay2==""){
				specialpay2=0;
				document.editPaymentForm.specialpay2.value=0;
			}
			if(specialpay3==""){
				specialpay3=0;
				document.editPaymentForm.specialpay3.value=0;
			}
			if(money1==""){
				money1=0;
				document.editPaymentForm.money1.value=0;
			}
			if(money2==""){
				money2=0;
				document.editPaymentForm.money2.value=0;
			}
			if(money3==""){
				money3=0;
				document.editPaymentForm.money3.value=0;
			}
			document.editPaymentForm.all1.value = parseFloat(money1)+parseFloat(specialpay1);
			document.editPaymentForm.all2.value = parseFloat(money2)+parseFloat(specialpay2);
			document.editPaymentForm.all3.value = parseFloat(money3)+parseFloat(specialpay3);
		}
		
		//实收特别交通费改变
		function specialChange(){
			var money1 = document.editPaymentForm.money1.value;
			var money2 = document.editPaymentForm.money2.value;
			var money3 = document.editPaymentForm.money3.value;	
			if(document.editPaymentForm.specialpay1.type=="text" && document.editPaymentForm.specialpay2.type=="hidden"){
				document.editPaymentForm.specialpay1.value = document.editPaymentForm.shishouSpecialpay.value;
				var specialpay1 = document.editPaymentForm.specialpay1.value;
				document.editPaymentForm.all1.value = parseFloat(money1)+parseFloat(specialpay1);
			}
			else if(document.editPaymentForm.specialpay2.type=="text" && document.editPaymentForm.specialpay3.type=="hidden"){
				document.editPaymentForm.specialpay2.value = document.editPaymentForm.shishouSpecialpay.value;
				var specialpay2 = document.editPaymentForm.specialpay2.value;
				document.editPaymentForm.all2.value = parseFloat(money2)+parseFloat(specialpay2);
			}
			else if(document.editPaymentForm.specialpay3.type=="text"){
				document.editPaymentForm.specialpay3.value = document.editPaymentForm.shishouSpecialpay.value;
				var specialpay3 = document.editPaymentForm.specialpay3.value;
				document.editPaymentForm.all3.value = parseFloat(money3)+parseFloat(specialpay3);
			}
		}
		
		//实收检查费改变
		function moneyChange(){
			var one = document.editPaymentForm.one.value;
			var two = document.editPaymentForm.two.value;
			var three = document.editPaymentForm.three.value;
			var shishouMoney = document.editPaymentForm.shishouMoney.value;
			var money1 = document.editPaymentForm.money1.value;
			var money2 = document.editPaymentForm.money2.value;
			var money3 = document.editPaymentForm.money3.value;
			var specialpay1 = document.editPaymentForm.specialpay1.value;
			var specialpay2 = document.editPaymentForm.specialpay2.value;
			var specialpay3 = document.editPaymentForm.specialpay3.value;
		
			if((money1=="" || money1==0)&&(money2=="" || money2==0)&&(money3=="" || money3==0)){
				var tmpmoney1 = parseFloat(shishouMoney)*parseFloat(one)/10;
				var tmpmoney2 = parseFloat(shishouMoney)*parseFloat(two)/10;
				var tmpmoney3 = parseFloat(shishouMoney)*parseFloat(three)/10;
				document.editPaymentForm.all1.value = parseFloat(money1)+parseFloat(specialpay1);
				document.editPaymentForm.all2.value = parseFloat(money2)+parseFloat(specialpay2);
				document.editPaymentForm.all3.value = parseFloat(money3)+parseFloat(specialpay3);
				
				if(document.editPaymentForm.money1.type=="text" && document.editPaymentForm.money2.type=="hidden"){
					document.editPaymentForm.money1.value = tmpmoney1+tmpmoney2+tmpmoney3;
				}
				else if(document.editPaymentForm.money2.type=="text" && document.editPaymentForm.money3.type=="hidden"){
					document.editPaymentForm.money1.value = tmpmoney1;
					document.editPaymentForm.money2.value = tmpmoney2+tmpmoney3;
				}
				else if(document.editPaymentForm.money3.type=="text"){
					document.editPaymentForm.money1.value = tmpmoney1;
					document.editPaymentForm.money2.value = tmpmoney2;
					document.editPaymentForm.money3.value = tmpmoney3;
				}
			}
		
		}
		
        function checkAdd(type){
        	
   			var money1 = document.editPaymentForm.money1.value;
			var money2 = document.editPaymentForm.money2.value;
			var money3 = document.editPaymentForm.money3.value;
			var all1 = document.editPaymentForm.all1.value;
			var all2 = document.editPaymentForm.all2.value;
			var all3 = document.editPaymentForm.all3.value;
			var amount1 = document.editPaymentForm.amount1.value;
			var shishouSpecialpay = document.editPaymentForm.shishouSpecialpay.value;
			var shishouMoney = document.editPaymentForm.shishouMoney.value;
			if(money1==""){
				money1=0;
			}
			if(money2==""){
				money2=0;
			}
			if(money3==""){
				money3=0;
			}
			if(amount1==""){
				amount1=0;
			}
			if(shishouSpecialpay==""){
				shishouSpecialpay=0;
			}
			if(shishouMoney==""){
				shishouMoney=0;
			}
			if(BASEisNotFloat(money1) || BASEisNotFloat(money2) || BASEisNotFloat(money3) || BASEisNotFloat(amount1)|| BASEisNotFloat(shishouSpecialpay)|| BASEisNotFloat(shishouMoney) ){
        		alert("有非数字字符，请改正！");
        		return false;
        	}
			if(parseFloat(amount1)!=(parseFloat(all1)+parseFloat(all2)+parseFloat(all3))){
				//alert(parseFloat(amount1));
				//alert(parseFloat(all1)+parseFloat(all2)+parseFloat(all3));
				//alert("合计分账数与实收合计数不符！");
				//return false;
			}
			if(parseFloat(amount1)!=(parseFloat(shishouSpecialpay)+parseFloat(shishouMoney))){
				//alert("实收检查费与实收特别交通费总数与实收合计不符！");
				//return false;
			}
			if(parseFloat(shishouMoney)!=(parseFloat(money1)+parseFloat(money2)+parseFloat(money3))){
				//alert("检查费分账总数与实收检查费不符！");
				//return false;
			}
			if(type=="updatePayment"){
				document.getElementById("editPaymentForm").action="updatePayment.action?goPage=<s:property value="#request.currPageb" />";
			}
			else{
				document.getElementById("editPaymentForm").action="havePayment.action";
			}
			if (confirm("确认提交？")) {
   			document.getElementById("editPaymentForm").submit();
   			}
   			else {
          	 return false;
           	}
        }
        function BASEisNotFloat(theFloat)    
		{    
		//判断是否为浮点数    
		  len=theFloat.length;    
		  dotNum=0;    
		  if (len==0)    
		  return true;    
		  for(var i=0;i<len;i++){    
		    oneNum=theFloat.substring(i,i+1);    
		    if (oneNum==".")    
		      dotNum++;    
		    if ( ((oneNum<"0" || oneNum>"9") && oneNum!=".") || dotNum>1)    
		      return true;    
		    }    
		    if (len>1 && theFloat.substring(0,1)=="0"){    
		    if (theFloat.substring(1,2)!=".")    
		      return true;    
		    }    
		     return false;
		 }
    </script>
	<body>
		<%int isCharge = 0; %>
		<c:forEach items="${menu_parent}" var="mp3" varStatus="status">
			<c:if test="${mp3.rightId == 461}">
				<%isCharge = 1; %>
			</c:if>
		</c:forEach>
					<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
					  <tr>
					    <td class="list_r">&nbsp;</td>
					    <td valign="top" class="right"><div class="title">您的当前位置： 首页 &gt;&gt; 业务管理 &gt;&gt; <strong>财务收费</strong></div>
					      <div>
					        <table align="center" cellpadding="12" class="tabedit">
					          <tr>
					            <td height="300" align="left" valign="top">
					            <!--编辑开始-->
					            <s:if test="#request.payment.size>0">
					    			<s:iterator value="#request.payment" status="stat">
					                <table width="700" align="center" cellspacing="5">
					                <s:form theme="simple" method="post" action=""
									id="editPaymentForm" name="editPaymentForm">
										<input id="one" name="one" type="hidden"" value="${ONE }" />
										<input id="two" name="two" type="hidden"" value="${TWO }" />
										<input id="three" name="three" type="hidden"" value="${THREE }" />
										<input id="paymentId" name="payment.id" type="hidden"" value="${ID }" />
										<input id="businessid" name="payment.businessid" type="hidden"" value="${BUSINESSID }" />
										<input id="paymentmoney" name="payment.money" type="hidden"" value="${MONEY }" />
										<input id="firstcomid" name="payment.firstcomid" type="hidden"" value="${FIRSTCOMID }" />
										<input id="secondcomid" name="payment.secondcomid" type="hidden"" value="${SECONDCOMID }" />
										<input id="thirdcomid" name="payment.thirdcomid" type="hidden"" value="${THIRDCOMID }" />
					                  <tr>
					                    <td height="20" colspan="2">申请编号</td>
					                    <td ><label>
					                      <input id="appno" name="appno" type="text" class="input" size="10" value="${APPNO }" disabled="disabled" />
					                    </label></td>
					                     <td height="20">船名</td>
					                    <td><label>
					                      <input id="vesselname" name="role.roleName" type="text" class="input" size="10" value="${VESSELNAME }" disabled="disabled" />
					                    </label></td>
					                     <td height="20"></td>
					                    <td><label>
					                    </label></td>
					                  </tr>
					                  <tr>
					                    <td colspan="2" height="20">船舶类型</td>
					                    <td><label>
					                      <input id="vesseltype" name="role.roleName" type="text" class="input" size="10" value="${VESSELTYPE }" disabled="disabled" />
					                    </label></td>
					                     <td height="20">总吨位</td>
					                    <td ><label>
					                      <input id="tonnage" name="role.roleName" type="text" class="input" size="10" value="${TONNAGE }" disabled="disabled" />
					                    </label></td>
					                     <td height="20"></td>
					                    <td ></td>
					                  </tr>
					                  <tr>
					                    <td colspan="2" height="20">应收合计</td>
					                    <td ><label>
					                      <input id="yingshouAll" name="yingshou" type="text" class="input" size="10" value="" disabled="disabled" />
					                    </label></td>
					                     <td height="20">检查费</td>
					                    <td ><label>
					                      <input id="yingshouMoney" name="yingshouMoney" type="text" class="input" size="10" disabled="disabled" value="${MONEY }" />
					                    </label></td>
					                     <td height="20">特别交通费</td>
					                    <td ><label>
					                      <input id="yingshouSpecialpay" name="business.special" type="text" class="input" size="10" disabled="disabled" value="${SPECIAL }" />
					                    </label></td>
					                  </tr>
					                  <tr>
					                  	<td colspan="7"><hr/></td>
					                  </tr>
					                  <tr>
					                    <td colspan="2" height="20">到账记录</td>
					                    <td><label>
					                      <input id="amount1" name="amount1" type="text" class="input" onkeyup="valiamount();" onchange="count()" size="10" value="${AMOUNT+SPECIALPAY }" <%if(isCharge==0){ %>disabled="disabled"<%} %> />
					                      </label>
					                      <select id="currency" name="payment.currency" <%if(isCharge==0){ %>disabled="disabled"<%} %> >
					                      	<!--<option value="">选择币种</option>-->
					                      	<option value="0" <s:if test="CURRENCY==0">selected="selected"</s:if> selected="selected">人民币</option>
					                      	<option value="1" <s:if test="CURRENCY==1">selected="selected"</s:if> >外币</option>
					                      </select>
					                    </td>
					                     <td height="20">到账日期</td>
					                    <td><label>
					                      <input id="amountDate1" name="payment.paymentdate" type="text" class="input" size="10" value="${PAYMENTDATE }" onfocus="WdatePicker()" <%if(isCharge==0){ %>disabled="disabled"<%} %> />
					                    </label></td>
					                     <td height="20">付款单位</td>
					                    <td><label>
					                      <input id="amountBy1" name="payment.paymentBy" type="text" class="input" size="10" value="${INVOICETITLE }"  <%if(isCharge==0){ %>disabled="disabled"<%} %> />
					                    </label></td>
					                  </tr>
					                  <!-- tr>
					                    <td colspan="2" height="20">到账记录（二）</td>
					                    <td><label>
					                      <input id="amount2" name="role.roleName" type="text" class="input" onchange="count()" size="10" value="" />
					                      <select id="currency" name="payment.currency">
					                      	<option value="">请选择</option>
					                      	<option value="0" <s:if test="CURRENCY==0">selected="selected"</s:if> >人民币</option>
					                      	<option value="1" <s:if test="CURRENCY==1">selected="selected"</s:if> >外币</option>
					                      </select>
					                    </label></td>
					                     <td height="20">到账日期</td>
					                    <td><label>
					                      <input id="amountDate2" name="payment.paymentdate" type="text" class="input" size="10" value="${PAYMENTDATE }" onfocus="WdatePicker()"/>
					                    </label></td>
					                     <td height="20">付款单位</td>
					                    <td><label>
					                      <input id="amountBy2" name="role.roleName" type="text" class="input" size="10" value="" />
					                    </label></td>
					                  </tr-->
					                  <tr>
					                    <td colspan="2" height="20">实收合计</td>
					                    <td><label>
					                      <input id="shishouAll" name="role.roleName" type="text" class="input" size="10" value="" disabled="disabled"/>
					                      <%if(isCharge==1){ %>
					                      		<input name="" class="button" type="button" value="已到账" onclick="checkAdd('havePayment')" />
					                      <%} %>
					                    </label></td>
					                     <td height="20">检查费</td>
					                    <td><label>
					                      <input id="shishouMoney" name="payment.amount" type="text" class="input" size="10" onchange="moneyChange()" value="${AMOUNT }" <%if(isCharge==0){ %>disabled="disabled"<%} %> />
					                    </label></td>
					                     <td height="20">特别交通费</td>
					                    <td><label>
					                      <input id="shishouSpecialpay" name="payment.specialpay" type="text" class="input" size="10" onchange="specialChange()" value="${SPECIALPAY }" <%if(isCharge==0){ %>disabled="disabled"<%} %> />
					                    </label></td>
					                  </tr>
					                  <tr>
					                  	<td colspan="7"><hr/></td>
					                  </tr>
					                  <tr>
					                    <td height="20" colspan="2">分账设定</td>
					                    <td height="20" colspan="5"><font color="red">检查费默认分账比例为 ${ONE }0% : ${TWO }0% : ${THREE }0% </font></td>
					                  </tr>
					                  <tr>
					                    <td height="20">检查费分账</td>
					                    <td height="20">${ONENAME }</td>
					                    <td><label>
					                      <input id="money1" name="payment.firstcompay" <s:if test="ONENAME==null">type="hidden" </s:if><s:else>type="text" </s:else> class="input" size="10" onchange="count()" value="${FIRSTCOMPAY }" <%if(isCharge==0){ %>disabled="disabled"<%} %> />
					                    </label></td>
					                     <td height="20">${TWONAME }</td>
					                    <td><label>
					                      <input id="money2" name="payment.secondcompay" <s:if test="TWONAME==null">type="hidden" </s:if><s:else>type="text" </s:else> class="input" size="10" onchange="count()" value="${SECONDCOMPAY }" <%if(isCharge==0){ %>disabled="disabled"<%} %> />
					                    </label></td>
					                     <td height="20">${THREENAME }</td>
					                    <td><label>
					                      <input id="money3" name="payment.thirdcompay" <s:if test="THREENAME==null">type="hidden" </s:if><s:else>type="text" </s:else> class="input" size="10" onchange="count()" value="${THIRDCOMPAY }" <%if(isCharge==0){ %>disabled="disabled"<%} %> />
					                    </label></td>
					                  </tr>
					                  <tr>
					                    <td height="20">特别交通费</td>
					                    <td height="20">${ONENAME }</td>
					                    <td><label>
					                      <input id="specialpay1" name="role.roleName" <s:if test="ONENAME==null">type="hidden" </s:if><s:else>type="text" </s:else> class="input" size="10" value="0" disabled="disabled"/>
					                    </label></td>
					                     <td height="20">${TWONAME }</td>
					                    <td><label>
					                      <input id="specialpay2" name="role.roleName" <s:if test="TWONAME==null">type="hidden" </s:if><s:else>type="text" </s:else> class="input" onchange="count()" size="10" value="0" disabled="disabled"/>
					                    </label></td>
					                     <td height="20">${THREENAME }</td>
					                    <td><label>
					                      <input id="specialpay3" name="role.roleName" <s:if test="THREENAME==null">type="hidden" </s:if><s:else>type="text" </s:else> class="input" onchange="count()" size="10" value="" disabled="disabled"/>
					                    </label></td>
					                  </tr>
					                  <tr>
					                    <td height="20">合   计</td>
					                    <td height="20">${ONENAME }</td>
					                    <td><label>
					                      <input id="all1" name="role.roleName" <s:if test="ONENAME==null">type="hidden" </s:if><s:else>type="text" </s:else> class="input" size="10" disabled="disabled" value="" />
					                    </label></td>
					                     <td height="20">${TWONAME }</td>
					                    <td><label>
					                      <input id="all2" name="role.roleName" <s:if test="TWONAME==null">type="hidden" </s:if><s:else>type="text" </s:else> class="input" size="10" disabled="disabled" value="" />
					                    </label></td>
					                     <td height="20">${THREENAME }</td>
					                    <td><label>
					                      <input id="all3" name="role.roleName" <s:if test="THREENAME==null">type="hidden" </s:if><s:else>type="text" </s:else> class="input" size="10" disabled="disabled" value="" />
					                    </label></td>
					                  </tr>
					                  <tr>
					                    <td height="20">分账记录</td>
					                    <td height="20">分账日期</td>
					                    <td><label>
					                      <input id="ledgerdate" name="payment.ledgerdate" type="text" class="input" size="10" value="${LEDGERDATE }" onfocus="WdatePicker()" <%if(isCharge==0){ %>disabled="disabled"<%} %> />
					                    </label></td>
					                     <td height="20">备注</td>
					                    <td colspan="3"><input id="payremark" name="payment.payremark" type="text" class="input" size="52" value="${PAYREMARK }" <%if(isCharge==0){ %>disabled="disabled"<%} %> /></td>
					                  </tr>
					                  <tr>
					                    <td height="20" colspan="2">付费状态</td>
					                    <td><label>
					                    <s:if test="AMOUNT!=null">
					                      <select id="paystate" name="payment.paystate" <%if(isCharge==0){ %>disabled="disabled"<%} %> >
					                      	<option value="0" <s:if test="PAYSTATE==0">selected="selected"</s:if> >未选择</option>
					                      	<option value="2" <s:if test="PAYSTATE==2">selected="selected"</s:if> >已付费未开发票</option>
					                      	<option value="3" <s:if test="PAYSTATE==3">selected="selected"</s:if> >已付费已开发票</option>
					                      </select>
					                    </s:if>
					                    <s:else>
					                      <select id="paystate" name="payment.paystate" <%if(isCharge==0){ %>disabled="disabled"<%} %> >
					                      	<option value="0" <s:if test="PAYSTATE==0">selected="selected"</s:if> >未付费未开发票</option>
					                      	<option value="1" <s:if test="PAYSTATE==1">selected="selected"</s:if> >未付费已开发票</option>
					                      	<option value="4" <s:if test="PAYSTATE==4">selected="selected"</s:if> >逾期欠款</option>
					                      </select>
					                    </s:else>
					                    </label></td>
					                     <td height="20">分账状态</td>
					                    <td><label>
					                      <select id="ledgerstate" name="payment.ledgerstate" <%if(isCharge==0){ %>disabled="disabled"<%} %> >
					                      	<option value="0" <s:if test="LEDGERSTATE==0">selected="selected"</s:if> >未分账</option>
					                      	<option value="1" <s:if test="LEDGERSTATE==1">selected="selected"</s:if> >已分账</option>
					                      </select>
					                    </label></td>
					                    <td height="20">
						                    <c:forEach items="${menu_parent}" var="mp3" varStatus="status">
												<c:if test="${mp3.rightId == 461}">
							                    <input name="" class="button" type="button" value="保 存"
															onclick="checkAdd('updatePayment')" />
												</c:if>
											</c:forEach>
										</td>
					                    <td height="20"><input name="" class="button"
													onclick="window.location.href='getPaymentByPage.action?goPage=<s:property value="#request.currPageb" />'" type="button"
													value="返 回" /></td>
					                  </tr>
					                  </s:form>
					                </table>
					                </s:iterator>
					                </s:if>
					              <!--编辑结束-->
					            </td>
					          </tr>
					        </table>
					         <p><font color="red"><b>财务操作记录</b></font></p>
					             <table width="700" align="center" cellspacing="5" class="tabedit">
					              			<tr>
												<td>操作时间</td>					              			
												<td>操作用户</td>					              			
												<td>操作状态</td>					              			
														              			
					              			</tr>
					              		<s:iterator value="#request.opaccounts" status="stat" id="op">
					              			<tr>
												<td><s:property value="opTime" /></td>					              			
												<td><s:property value="operator" /></td>	
												<td>
													<s:property value="opContent"/>
												</td>
												<!--<td><s:property value="#op.isPay" /></td>					              			
											--><!--	<td><s:property value="isCurrency" /></td>					              			
												<td><s:property value="isPay" /></td>					              			
					              			--></tr>
					                	</s:iterator>
					              	</table>
					      </div></td>
					  </tr>
					</table>
					<script type="text/javascript" charset="utf-8">
						//初始化数据
						count();
						specialChange();
						/**
						var one = document.editPaymentForm.one.value;
						var two = document.editPaymentForm.two.value;
						var three = document.editPaymentForm.three.value;
						var shishouMoney = document.editPaymentForm.shishouMoney.value;
						var tmpMoney01 = document.editPaymentForm.money1.value;
						var tmpMoney02 = document.editPaymentForm.money2.value;
						var tmpMoney03 = document.editPaymentForm.money3.value;
						if(tmpMoney01=="" && tmpMoney02=="" && tmpMoney03==""){
							document.editPaymentForm.money1.value = parseFloat(shishouMoney)*parseFloat(one)/10;
							document.editPaymentForm.money2.value = parseFloat(shishouMoney)*parseFloat(two)/10;
							document.editPaymentForm.money3.value = parseFloat(shishouMoney)*parseFloat(three)/10;
						}
						var tmpMoney1 = document.editPaymentForm.money1.value;
						var tmpMoney2 = document.editPaymentForm.money2.value;
						var tmpMoney3 = document.editPaymentForm.money3.value;
						var tmpSpecialpay = document.editPaymentForm.shishouSpecialpay.value;
						document.editPaymentForm.all1.value = tmpMoney1;
						document.editPaymentForm.all2.value = tmpMoney2;
						if(tmpMoney3 == ""){
						tmpMoney3=0;
						}
						document.editPaymentForm.all3.value = parseFloat(tmpMoney3)+parseFloat(tmpSpecialpay);
						*/
					</script>
	</body>
</html>
