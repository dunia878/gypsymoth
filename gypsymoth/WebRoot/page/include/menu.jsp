<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<div class="menu">
	<ul id="nav">
		<!-- <li class="top">
			<a href="${pageContext.request.contextPath}/language.action"
				class="top_link"><span class="down"><s:text name="首页"/></span>
			</a>
		</li> -->

		<s:if test="#session.menu_parent.size>0">
			<s:iterator value="#session.menu_parent" status="stat">
				<s:if test="ischild == 0">
					<li class="top">
						<a href="${pageContext.request.contextPath}/getLeftRight.action?right_id=${rightId }"
							class="top_link" target="leftframe" onclick="rightfranme_onload('${resourceUrl }')">
							<span class="down">
								<s:text name="%{rightName}"/>
							</span>
						</a>
					</li>
				</s:if>
			</s:iterator>
		</s:if>
		<li class="top">
			 <span class="down">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> 
		</li>
		
		<li class="top" align="right" >
			<span class="down" id="map1"  onclick="divcontrol();" ><img src="${pageContext.request.contextPath}/images/menu_bt.gif" alt="隐藏"/></span>
			<span class="down" id="map2" onclick="divcontrol();" style="display:none"><img src="${pageContext.request.contextPath}/images/menu_bt1.gif" alt="显示"/></span>
		</li>
		
	</ul>
	
	
</div>
