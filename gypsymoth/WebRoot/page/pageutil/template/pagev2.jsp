<!--Create on 2008-5-6   Copyright (c) 2007 by sinosoft. @author: gaoweipeng  @version 1.0 -->

<%@ page language="java"
	import="java.util.*,com.opensymphony.xwork2.util.ValueStack,com.sinosoft.gypsymoth.utils.Pagination,org.apache.struts2.dispatcher.SessionMap,org.apache.struts2.dispatcher.RequestMap"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%

	String formId="";
	
	String action_name = "";

	String namespace = "";

	String pagination_method_name = "";

	int currPage = 1;

	int totlePage = 1;

	int distanceNum = 1;

	boolean display = true;

	ValueStack valueStsck = (ValueStack) request
			.getAttribute("struts.valueStack");

	if (valueStsck != null) {

		//SessionMap sessionMap = (SessionMap) valueStsck.findValue("session");

		//change 
		RequestMap requestMap = (RequestMap) valueStsck
		.findValue("request");

		//Pagination pagination = (Pagination) sessionMap.get("framework_util_Pagination");

		Pagination pagination = (Pagination) requestMap
		.get("framework_util_Pagination");

		System.out.println(pagination!=null);
		if (pagination != null) {

			formId=valueStsck.findString("parameters.formId");

			action_name = valueStsck.findString("parameters.action_name");

			namespace = valueStsck.findString("parameters.namespace");

			pagination_method_name = valueStsck.findString("parameters.pagination_method_name");

			distanceNum = Integer.parseInt(valueStsck.findString("parameters.distanceNum"));

			currPage = pagination.getCurrPage();

			totlePage = pagination.getTotlePage();
			if(totlePage == 0){
				totlePage = 1;
			}
			
			display = true;

		} else {
		
			display = false;

		}

	}
%>


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>



<%
if (display == true) {
%>

<!-- form 的action 后跟的参数名为goPage


<form id="<%=action_name%>" name="pagination" onsubmit="return true;"
	action="<%=namespace%>/<%=action_name%>!<%=pagination_method_name%>.action"
	method="post">
	
 -->

<input type="hidden" id="formId" value="<%=formId %>" />

<input type="hidden" id="actionUrl"
	value="<%=basePath%>/<%=namespace%>/<%=action_name%>!<%=pagination_method_name%>.action" />






<table width="100%" border="0" align="center" cellpadding="0"
	cellspacing="0">



	<tr>
		<td align="right">
			<div class="tableContent">
				<div class="divWidth">

					<span class="pageLeft"><s:text name="total"/> <%=totlePage%> <s:text name="page"/></span>

					<span class="pageRight"> <% if(totlePage != 0){%> <a
						onclick="firstPage();" style="cursor: pointer;"><s:text name="homePage"/></a>| <%} %> <!-- 上一页 -->
						<%
						if (currPage != 1) {
						%> <a onclick="previous();" style="cursor: pointer;"><s:text name="prevPage"/></a> <%
						}
						%> <!-- 前置 .... --> <%
						if (currPage > distanceNum + 1) {
						%> ..... <%
						}
						%> <!-- 1 2 3 4 5 --> <%
							for (int i = 1; i <= totlePage; i++) {

								if ((currPage - i) >= -distanceNum
								&& (currPage - i) <= distanceNum) {
							
									if(currPage==i){
				%> <span class="pageNow"> <!-- 
 								<a href="<%=namespace%>/<%=action_name%>!<%=pagination_method_name%>.action?goPage=<%=i%>" ><%=i%>&nbsp; </a>
 								 --> <a onclick="processUrlAndSubmit('<%=i%>')"
							style="cursor: pointer;"><%=i%>&nbsp; </a> </span>| <%
				
				
				}else{
				
				 %> <!--  
				<a
					href="<%=namespace%>/<%=action_name%>!<%=pagination_method_name%>.action?goPage=<%=i%>">
					<%=i%>&nbsp; </a>
--> <a onclick="processUrlAndSubmit('<%=i%>')" style="cursor: pointer;"><%=i%>&nbsp;
					</a> <%
				}
				
				
						}
						}
				%> <!-- 后置..... --> <%
				if (currPage < (totlePage - distanceNum)) {
				%> ..... <%
				}
				%> <!-- 下一页 --> <%
				if (currPage < totlePage) {
				%> <a onclick="next();" style="cursor: pointer;"><s:text name="nextPage"/></a> <a
						onclick="lastPage();" style="cursor: pointer;"><s:text name="endPage"/></a> <%
				}
				%> </span>

				</div>
			</div>


		</td>


		<!-- 跳转 -->
		<td width="17%" align="right">
			<s:text name="No."/>
			<input type="text" name="goPage" size="2" style="height:11px;width:20px;" id="goto" />
			<input type="hidden"  id="totlePages" value="<%=totlePage%>" />
			/<%=totlePage%><s:text name="page"/>
		</td>

		<td width="4%">

			<a onclick="validateGOTO();"><img src="${pageContext.request.contextPath}/images/go.gif"
					border="0" style="cursor: pointer;" /> </a>
		</td>


	</tr>



</table>




<input type="hidden" name="currPage" id="currPage" value="<%=currPage%>" />

<input type="hidden" name="totlePage" id="totlePage"
	value="<%=totlePage%>" />

<!--  
</form>
-->

<script type="text/javascript">
<!--

//oForm=document.forms['pagination'];

//form action的值
//strAction=oForm.action;

//alert(strAction);

// form 对象
var formIdValue = document.getElementById("formId").value
oForm=document.getElementById(formIdValue);

//alert("oForm"+oForm);

//form action url
strAction=document.getElementById("actionUrl").value;
//alert("strAction:"+strAction);


oHiddencurrPage=document.getElementById("currPage");

//当前页数
intCurrPage=oHiddencurrPage.value;
//alert("intCurrPage:"+intCurrPage);

oHiddenTotlePage=document.getElementById("totlePage");

//总页数
intTotlePage=oHiddenTotlePage.value;
//alert("intTotlePage:"+intTotlePage);






function firstPage(){


processUrlAndSubmit(1);



}


function lastPage(){


processUrlAndSubmit(intTotlePage);


}




function previous(){

	//alert(intCurrPage);

	intPreviousPage=((intCurrPage-1)<1)?intCurrPage:(intCurrPage-1);

	//alert(intPreviousPage);

	processUrlAndSubmit(intPreviousPage);

}

function next(){

	
	intCurrPage++;
	
	intNextPage=(intCurrPage>=intTotlePage)?intTotlePage:intCurrPage;
	
	
	processUrlAndSubmit(intNextPage);
	
}


function validateGOTO(){

	//alert("validateGOTO");

	oTextgoto=document.getElementById("goto");
	var totlePages = document.getElementById("totlePages").value;

	intgoto=oTextgoto.value;
	var isfloatorstr = false;

	if(intgoto!=""&&!isNaN(intgoto)){ 
		if(!/^\d\d*$/.test(intgoto)){
			isfloatorstr=true;  
		}
	}
    intgoto = (intgoto * 1).toString();   
    if(intgoto.indexOf(".")   ==   -1 && !isfloatorstr){
    	isfloatorstr=false;   
    }else{
   		isfloatorstr=true;  
	}

	if(intgoto!="" && intgoto - totlePages <= 0 && intgoto>0 && !isfloatorstr){

		processUrlAndSubmit(intgoto);
	}else if(intgoto - totlePages > 0){
		//alert("输入的页码不能大于总页码数");
	}else if(intgoto<=0){
		//alert("输入的页码不能小于1");
	}else if(isfloatorstr){
		//alert("输入的页码不能为非整数");
	}else{
	
		//alert("请输入页码进行跳转");
	
	}

	//alert(intgoto=="");

}


function processUrlAndSubmit(intGotoPage){

	//alert("processUrlAndSubmit");


	oForm.action=strAction+"?"+"goPage"+"="+intGotoPage;
	//document.getElementById(oForm).action=strAction+"?"+"goPage"+"="+intGotoPage;

	//alert(oForm.action);
	
	//alert(oForm.ID.value);
	
	oForm.submit();
	
	//alert("ok");

}


//-->
</script>

<%
} else {
%>

<%
}
%>
