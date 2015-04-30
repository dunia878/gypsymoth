<!--Create on 2008-5-6   Copyright (c) 2007 by sinosoft. @author: gaoweipeng  @version 1.0 -->

<%@ page language="java"
	import="java.util.*,com.opensymphony.xwork2.util.ValueStack,com.sinosoft.gypsymoth.utils.Pagination,org.apache.struts2.dispatcher.SessionMap"
	pageEncoding="UTF-8"%>
		

<%
	String action_name = "";

	String namespace = "";

	String pagination_method_name = "";

	int currPage = 1;

	int totlePage = 1;

	int distanceNum = 1;
	
	
	boolean display=true;

	ValueStack valueStsck = (ValueStack) request
			.getAttribute("struts.valueStack");
			
								

	if (valueStsck != null) {

		
		SessionMap sessionMap = (SessionMap) valueStsck.findValue("session");
		
		Pagination pagination = (Pagination)sessionMap.get("framework_util_Pagination");
		
		if(pagination!=null){

			action_name = valueStsck.findString("parameters.action_name");
	
			namespace = valueStsck.findString("parameters.namespace");
	
			pagination_method_name = valueStsck
			.findString("parameters.pagination_method_name");
	
			distanceNum = Integer.parseInt(valueStsck
			.findString("parameters.distanceNum"));
	
			currPage = pagination.getCurrPage();
	
			totlePage = pagination.getTotlePage();
			
			display=true;
			
		}else{
		
			display=false;
		
		}

	}
%>






<%

if(display==true){

 %>

<!-- form 的action 后跟的参数名为goPage -->


<form id="<%=action_name%>" name="pagination" onsubmit="return true;"
	action="<%=namespace%>/<%=action_name%>!<%=pagination_method_name%>.action"
	method="post">
	<table>

		<tr>
			<td>
				<!-- 上一页 -->
<%
				if (currPage != 1) {
				%>

				<a onclick="previous();">&lt; &lt;previous</a>


	<%
				}
				%>

			</td>
			<td>




				<!-- 前置 -->



					<%
				if (currPage > distanceNum+1) {
				%>
				.....

				<%
				}
				%>


				<!-- 1 2 3 4 5 -->




	<%
						for (int i = 1; i <= totlePage; i++) {

						if ((currPage-i)>=-distanceNum&&(currPage-i)<=distanceNum) {
						
						
				%>



				<a
					href="<%=namespace%>/<%=action_name%>!<%=pagination_method_name%>.action?goPage=<%=i %>">
					<%=i%>&nbsp; </a>



	<%
					}
					}
				%>









				<!-- 后置..... -->


					<%
				if (currPage< (totlePage-distanceNum)) {
				%>
				.....
				<%
				}
				%>



			</td>
			<td>
				<!-- 下一页 -->


	<%
				if (currPage < totlePage) {
				%>

				<a onclick="next();">next&gt; &gt;</a>

	<%
				}
				%>

			</td>
			<td>

				<!-- 跳转 -->
				<input type="text" name="goPage" value="" id="goto" />
				<a onclick="validateGOTO();">goto</a>
			</td>
			<td>
				currenty page:
				<%=currPage%>
			</td>

			<td>
				totle page:
				<%=totlePage%>
			</td>
		</tr>

	</table>

	<input type="hidden" name="currPage" id="currPage"
		value="<%=currPage%>" />
		
		<input type="hidden" name="totlePage" id="totlePage"
		value="<%=totlePage%>" />

</form>

<script type="text/javascript">
<!--

oForm=document.forms['pagination'];

//form action的值
strAction=oForm.action;

//alert(strAction);

oHiddencurrPage=document.getElementById("currPage");

//当前页数
intCurrPage=oHiddencurrPage.value;

//alert("intCurrPage:"+intCurrPage);

oHiddenTotlePage=document.getElementById("totlePage");

//总页数
intTotlePage=oHiddenTotlePage.value;

//alert("intTotlePage:"+intTotlePage);

function previous(){

	//alert("previous");

	intPreviousPage=((intCurrPage-1)<=1)?intCurrPage:(intCurrPage-1);

	//alert(intGoPage);

	processUrlAndSubmit(intPreviousPage);

}

function next(){

	//alert("next");
	
	intCurrPage++;
	
	intNextPage=(intCurrPage>=intTotlePage)?intTotlePage:intCurrPage;
	
	//alert("intNextPage:"+intNextPage);
	
	processUrlAndSubmit(intNextPage);
	
}


function processUrlAndSubmit(intGotoPage){

	//alert("processUrlAndSubmit");

	oForm.action=strAction+"?"+"goPage"+"="+intGotoPage;

	//alert(oForm.action);
	
	oForm.submit();
	
	//alert("ok");

}

function validateGOTO(){

	//alert("validateGOTO");

	oTextgoto=document.getElementById("goto");

	intgoto=oTextgoto.value;

	if(intgoto!=""&&!isNaN(intgoto)){

		processUrlAndSubmit(intgoto);

	}else{
	
		alert("please input numeral ");
	
	}

	//alert(intgoto=="");

}


//-->
</script>

<%
}else{
 %>
分页标签未能正常显示，请阅读《分页标签使用说明书》
<%
}
%>