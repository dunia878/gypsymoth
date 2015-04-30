<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统</title>
		<%@ include file="../include/head.jsp"%>
	</head>
	<body>
		
					<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
  <tr>
    <td class="list_r">&nbsp;</td>
    <td valign="top" class="right"><div class="title">您的当前位置： 首页 &gt;&gt; 业务管理 &gt;&gt; <strong>业务申请</strong></div>
      <div>
        <!--编辑开始-->
        <table width="100%" class="tableheader">
          <tr>
            <td width="5%" height="26"><img src="../../images/bg_mcontentL.gif"  width="32" height="27" /></td>
            <td valign="middle"><h3>列表</h3></td>
            <td align="right" valign="middle"><input type="submit" class="button_s" onclick="javascript:window.location.href='businessApply.jsp'" value="申请新业务" />
              <input type="submit" class="button_s" onclick="javascript:window.location.href='shenqing_success.htm'" value="删 除" /></td>
            <td width="1%" align="right"><img src="../../images/bg_mcontentR.gif"  width="7" height="27" /></td>
          </tr>
        </table>
        <table width="100%" class="tableCont">
          <tr>
            <td><div class="tableContent">
                <table width="100%" border="0" cellspacing="1" cellpadding="0">
                 <tr>
          
                 	<td>ID</td>
    		<td><s:text name="业务编号"/></td>
    		<td><s:text name="申请人"/></td>
    		<td><s:text name="IMO号"/></td>
    		<td><s:text name="申请时间"/></td>
    		<td><s:text name="分配人"/></td>
    		<td><s:text name="被分配人"/></td>
    		<td><s:text name="检查人"/></td>
    		<td><s:text name="操作"/></td>
    		<td><s:text name="预览证书"/></td>
                   
                     
                    </tr>
                    <s:form id="list" name="list" action="selectAccept"
									theme="simple">
                  <s:if test="#request.list.size>0">
    	<s:iterator value="#request.list" status="stat">
    	<tr>
    		<td><s:property value="id"/></td>
    		<td><s:property value="imo"/></td>
    		<td><s:property value="vesselname"/></td>
    		<td><s:property value="vesseltype"/></td>
    		<td><s:property value="businessname"/></td>
    		
    		<td ><a href="/altershangjia.do?jznum=${v.id}">修改</a> </td> 
    		<td ><a href="/getallshangjias.do?jznum=${v.id}">查看</a></td>
    	</tr>
    	</s:iterator>
    	</s:if>
             </s:form>
               	<tr>
										<td height="25" align="right" class="right_tb1" colspan="3">
											<!--分页  -->
												<s:component template="pagev2.jsp" templateDir="page/pageutil"
													theme="template">
													<!--action name  -->
													<s:param name="formId" value="'list'" />
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
              </div></td>
          </tr>
        </table>
      
				</td>
				<!-- 主内容结束 -->
			</tr>
		</table>
		<!--内容结束-->
	</body>
</html>
