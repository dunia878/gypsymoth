<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="s" uri="/struts-tags"%>

 <% List list = (List)request.getAttribute("list"); 
 	if(list.size()>0){
 %>

<table width="100%" border="0" cellspacing="1" cellpadding="0">
                              
       <tr>
         <td width="15%" align="center">选择</td>
         <td align="left">姓名</td>
         <td width="15%" align="center">选择</td>
         <td align="left">姓名</td>
         <td width="15%" align="center">选择</td>
         <td align="left">姓名</td>
       </tr>
       
        <tr>
       <s:iterator value="#request.list" status="it" id="map">
       	  <s:if test="#it.index%3==0">
         	 </tr><tr> 
         </s:if>
         <td align="center"><input type="checkbox" name="person_id" id="person_id" value="<s:property value="#map.NAME"/>:<s:property value="#map.PERSON_ID"/>" /></td>
         <td align="left"><a href="#"><s:property value="#map.NAME"/></a></td>
       </s:iterator>  
       </tr>
         
       <tr>
         <td align="center">&nbsp;</td>
         <td align="left">&nbsp;</td>
         <td align="center">&nbsp;</td>
         <td align="left">&nbsp;</td>
         <td align="center">&nbsp;</td>
         <td align="left">&nbsp;</td>
       </tr>

       <tr>
         <td align="center">&nbsp;</td>
         <td colspan="5" align="left"><input type="button" id="coor_button" onclick="coor_click();" name="coor_button" class="button" value="确 定" /> <input  name="input" class="button" type="button" value="关 闭"  onclick="window.close();" /></td>
       </tr>
   </table>

<% }else{ %>



	<table width="100%" border="0" cellspacing="1" cellpadding="0">
       <tr>
         <td width="15%" align="center">选择</td>
         <td align="left">姓名</td>
         <td width="15%" align="center">选择</td>
         <td align="left">姓名</td>
         <td width="15%" align="center">选择</td>
         <td align="left">姓名</td>
       </tr>
       
        <tr>
         <td align="center">&nbsp;</td>
         <td align="left">&nbsp;</td>
         <td align="center">&nbsp;</td>
         <td align="left">&nbsp;</td>
         <td align="center">&nbsp;</td>
         <td align="left">&nbsp;</td>
       </tr>
       
        <tr height="50px">
         <td align="center" colspan="6">查无符合条件用户&nbsp;</td> 
       </tr>
         
       <tr>
         <td align="center">&nbsp;</td>
         <td align="left">&nbsp;</td>
         <td align="center">&nbsp;</td>
         <td align="left">&nbsp;</td>
         <td align="center">&nbsp;</td>
         <td align="left">&nbsp;</td>
       </tr>

       <tr>
         <td align="center">&nbsp;</td>
         <td colspan="5" align="left"><input type="button" id="coor_button" onclick="coor_click();" name="coor_button"  class="button" value="确 定" /> <input  name="input" class="button" type="button" value="关 闭"  onclick="window.close();" /></td>
       </tr>
   </table>

<%	} %>


 
   