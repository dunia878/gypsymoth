<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>中检集团船舶舞毒蛾检查业务信息系统</title>
		<%@ include file="../include/head.jsp"%>
		<script language="javascript" type="text/javascript"
			src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>

	</head>
<body>
<!--内容开始-->
<table border="0" cellspacing="0" cellpadding="0" class="contenttab">
  <tr>
    <td class="list_r">&nbsp;</td>
    <td valign="top" class="right"><div class="title">您的当前位置： 首页 &gt;&gt; 客户管理 &gt;&gt; <strong>客户信息</strong></div>
      <div>
        <table align="center" cellpadding="12" class="tabedit">
          <tr>
            <td height="300" align="left" valign="top"><!--编辑开始-->
                <table width="600" align="center" cellspacing="5">
                  
                  <tr>
                    <td width="85" height="20" align="left"> 用 户 名 </td>
                    <td width="381" align="left"><label>
                      <input name="libName2" type="text" class="input" value="guoliang" size="55" />
                    </label></td>
                  </tr>
                  <tr>
                    <td height="20" align="left"> 密　　码 </td>
                    <td align="left"><input name="curator2" type="password" class="input" value="27950" size="55" style="width:335px;" />                    </td>
                  </tr>
                  <tr>
                    <td height="20" align="left"> 重复密码 </td>
                    <td align="left"><input name="curator2" type="password" class="input" value="27950" size="55" style="width:335px;" />                    </td>
                  </tr>
                  <tr>
                    <td height="20" align="left">公司名称</td>
                    <td align="left">中文
                      <input name="curator2" type="text" style="width:305px;" value="中科软科技" /></td>
                  </tr>
                  <tr>
                    <td height="20" align="left">&nbsp;</td>
                    <td align="left">英文
                      <input name="curator10" type="text" style="width:305px;" /></td>
                  </tr>
                  <tr>
                    <td height="20" align="left">工商注册号</td>
                    <td align="left"><input name="homepage2" type="text" class="input" size="55" />                    </td>
                  </tr>
                  <tr>
                    <td height="20" align="left">企业性质</td>
                    <td align="left"><label>
                      <select name="select" class="select" id="select">
                        <option>请选择</option>
                        <option>国有</option>
                        <option>合作</option>
                        <option>合资</option>
                        <option>外商独资</option>
                        <option>集体</option>
                        <option selected="selected">私营</option>
                      </select>
                    </label></td>
                  </tr>
                  <tr>
                    <td height="20" align="left">法人代表</td>
                    <td align="left"><input name="telReceiver2" type="text" class="input" size="55" /></td>
                  </tr>
                  <tr>
                    <td height="20" align="left"> 联系电话 </td>
                    <td align="left"><input name="telephone2" type="text" class="input" onkeypress="number()" value="13888888888" size="55"/>                    </td>
                  </tr>
                  <tr>
                    <td height="20" align="left">电子邮件</td>
                    <td align="left"><input name="telephone2" type="text" class="input" onkeypress="number()" size="55"/></td>
                  </tr>
                  <tr>
                    <td height="20" align="left">公司地址</td>
                    <td align="left"><label>
                      <input name="checkbox" type="checkbox" id="checkbox" checked="checked" />
                      中国　　&nbsp;　
                      <select name="select2" id="select2" style="width:100px;">
                        <option>请选择</option>
                        <option>安徽</option>
                        <option selected="selected">北京</option>
                        <option>福建</option>
                      </select>
                      省
                      <select name="select3" id="select3" style="width:100px;">
                        <option>请选择</option>
                        <option selected="selected">北京市</option>
                        <option>延庆县</option>
                        <option>密云县</option>
                      </select>
                      市 </label></td>
                  </tr>
                  <tr>
                    <td height="20" align="left">&nbsp;</td>
                    <td align="left"><input name="checkbox2" type="checkbox" id="checkbox2" checked="checked" />
                      其它国家 　
                      <select name="select4" id="select4" style="width:100px;">
          <option>请选择</option>
          <option selected="selected">美国</option>
          <option>日本</option>
        </select>
                      国</td>
                  </tr>
                  <tr>
                    <td height="20" align="left"> 邮　　编 </td>
                    <td align="left"><input name="des2" type="text" class="input" size="55" />                    </td>
                  </tr>
                  <tr>
                    <td height="20" align="left">联系人1</td>
                    <td align="left"><table width="73%" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                          <td width="20%"><input name="curator3" type="text" style="width:65px;" value="郭亮" /></td>
                          <td width="12%" align="center">电话</td>
                          <td><input name="curator5" type="text" style="width:85px;" value="88888888" /></td>
                          <td width="12%" align="center">手机</td>
                          <td><input name="curator6" type="text" style="width:85px;" value="13888888888" /></td>
                        </tr>
                        <tr>
                          <td><input name="curator4" type="text" style="width:65px;" /></td>
                          <td align="center">传真</td>
                          <td><input name="curator7" type="text" style="width:85px;" /></td>
                          <td align="center">Email</td>
                          <td><input name="curator8" type="text" style="width:85px;" /></td>
                        </tr>
                    </table></td>
                  </tr>
                  <tr>
                    <td height="20" align="left">联系人2</td>
                    <td align="left"><table width="73%" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                          <td width="20%"><input name="curator9" type="text" style="width:65px;" /></td>
                          <td width="12%" align="center">电话</td>
                          <td><input name="curator9" type="text" style="width:85px;" /></td>
                          <td width="12%" align="center">手机</td>
                          <td><input name="curator9" type="text" style="width:85px;" /></td>
                        </tr>
                        <tr>
                          <td><input name="curator9" type="text" style="width:65px;" /></td>
                          <td align="center">传真</td>
                          <td><input name="curator9" type="text" style="width:85px;" /></td>
                          <td align="center">Email</td>
                          <td><input name="curator9" type="text" style="width:85px;" /></td>
                        </tr>
                    </table></td>
                  </tr>
                  <tr>
                    <td height="20" align="left">注册激活邮箱</td>
                    <td align="left"><input name="des2" type="text" class="input" value="guoliang@sinosoft.com.cn" size="55" />                    </td>
                  </tr>
                  <tr>
                    <td height="20" align="left">备　注</td>
                    <td align="left"><input name="des2" type="text" class="input" size="55" /></td>
                  </tr>
                  <tr>
                    <td height="38" align="left"></td>
                    <td height="38" align="left"><input name="input4" class="button" type="submit" onclick="javascript:window.location.href='kehu_success.htm'" value="保 存" />
                      <input name="input4" class="button" type="reset" value="删 除" />
                      <input name="input4" class="button" type="reset" value="重 置" />
                      <input name="input4" class="button" type="reset" value="返 回"  onclick="history.back(-1)" /></td>
                  </tr>
                </table>              
            <!--编辑结束-->            </td>
          </tr>
        </table>
      </div></td>
  </tr>
</table>

</body>
</html>
