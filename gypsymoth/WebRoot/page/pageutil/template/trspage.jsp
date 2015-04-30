<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.opensymphony.xwork2.util.ValueStack"%>

<%
	String currentPageNo="";
	String pageCount = "";
	String totalCount = "";
	String actionurl = "";
	String namespace ="";
	int tempCurrentNo =1;
	int tempPageCount =0;
	ValueStack valueStsck = (ValueStack) request.getAttribute("struts.valueStack");
	if (valueStsck != null) {		
			currentPageNo=valueStsck.findString("parameters.currentPageNo");
			pageCount = valueStsck.findString("parameters.pageCount");
			actionurl = valueStsck.findString("parameters.actionurl");
			namespace = valueStsck.findString("parameters.namespace");
			totalCount = valueStsck.findString("parameters.totalCount");
			if(null!= currentPageNo){
				tempCurrentNo = Integer.parseInt(currentPageNo);
			}
			tempPageCount = Integer.parseInt(pageCount);		
	}
 %>
<form id="pagination" name="pagination" onsubmit="return false;" method="post" >
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<span class="pageLeft">共<%=pageCount%> 页</span>
				<span class="pageLeft">共<%=totalCount%> 条</span>
				<span class="pageRight"> <a onclick="firstPage();" style="cursor: pointer;">首页</a>| 
				<% if(tempCurrentNo > 1){ %> 
				<a onclick="previous();" style="cursor: pointer;">上一页</a>| 
				<%} %> 
				<% if(tempCurrentNo < tempPageCount){ %> 
				<a onclick="next();" style="cursor: pointer;">下一页</a>| 
				<%} %>
				<a onclick="lastPage();" style="cursor: pointer;">未页</a> </span>
			</td>
			<td width="15%" align="right">
				第
				<input type="text" class="input" name="goPage" size="2" id="goto" value="<%=currentPageNo %>" onkeydown="pressCheck();"/>
				/<%=pageCount%>页
				<a onclick="validateGOTO();"><img src="${pageContext.request.contextPath}/images/go.gif" width="22" height="20" border="0" style="cursor: pointer;" /> </a>
			</td>


		</tr>
	</table>
	<input type="hidden" id="actionUrl" value="${pageContext.request.contextPath}/<%=namespace%>/<%=actionurl%>" />
</form>
<script type="text/javascript" langage="javascript">
		oForm=document.getElementById("pagination");
		actionurl = document.getElementById("actionUrl").value;
		//当前页数
		var intCurrPage=<%=currentPageNo%>;		
		//总页数
		var intTotlePage=<%=pageCount%>;
		function firstPage(){
			processUrlAndSubmit(1);		
		}			
		function previous(){
			var intPreviousPage=((intCurrPage-1)<1)?intCurrPage:(intCurrPage-1);
			processUrlAndSubmit(intPreviousPage);
		}
		function next(){	
			intCurrPage++;	
			var intNextPage=(intCurrPage>=intTotlePage)?intTotlePage:intCurrPage;
			processUrlAndSubmit(intNextPage);	
		}
		function lastPage(){
			processUrlAndSubmit(intTotlePage);
		}		
		function processUrlAndSubmit(intGotoPage){
			oForm.action=actionurl+"&goPage"+"="+intGotoPage;
			oForm.submit();
		}
		function validateGOTO(){		    
			oTextgoto=document.getElementById("goto");
			intgoto=oTextgoto.value;
			if(intgoto!=""&&!isNaN(intgoto)){
				if(!/^\d\d*$/.test(intgoto)){
				    alert("请输入整数！");
				}else if(intgoto><%=pageCount%> || intgoto<=0){
					alert("输入数字超出范围！");	
				}else if(intgoto<=0){
				    alert("输入的页码不能小于1");
				}else{
					processUrlAndSubmit(intgoto);
				}
			}else{	
				alert("请输入数字！");
			}
			return ;
		}
  		function pressCheck(){   
  			if(event.keyCode==13)
  			validateGOTO();
  		}
   </script>