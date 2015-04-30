 function validata()
{
	var businessname = document.getElementById("businessname").value;
	var company = document.getElementById("company").value;
	var vesselname = document.getElementById("vesselname").value;
	var vesseltype = document.getElementById("vesseltype").value;
	var registry = document.getElementById("registry").value;
	var imo = document.getElementById("imo").value;
	var tonnage = document.getElementById("tonnage").value; 
	var destinationcountrys = document.getElementsByName("destinationcountrys")[0].value;
	var countrys = document.getElementsByName("countrys")[0].value;
	var ports = document.getElementsByName("ports")[0].value;
	
	var destinationcountrys_other = document.getElementsByName("destinationcountrys")[1].value;
	var countrys_other = document.getElementsByName("countrys")[1].value;
	var ports_other = document.getElementsByName("ports")[1].value;
	
	
	var proid = document.getElementById("proid").value;
	var city = document.getElementById("city").value;
	var portid = document.getElementById("portid").value;
	var plandatein = document.getElementById("plandatein").value;
	var plandateout = document.getElementById("plandateout").value;
	var checkdate = document.getElementById("checkdate").value;
	var special = document.getElementById("special").value;
	var linkmanname = document.getElementById("linkmanname").value;
 	var phone_begin = document.getElementById("phone_begin").value;
 	var phone_end = document.getElementById("phone_end").value;
 	var linkphone = document.getElementById("linkphone").value;
 	
 	var invoicetitle = document.getElementById("invoicetitle").value;
 	var invoiceaddress = document.getElementById("invoiceaddress").value;
 	var invoicepost = document.getElementById("invoicepost").value;
 	var invoicer = document.getElementById("invoicer").value;
 	var invoicetel_begin =  document.getElementById("invoicetel_begin").value;
	var invoicetel_end =  document.getElementById("invoicetel_end").value;
	 
	
	var reg_tonnage = /^(\+|-)?\d+$/;
	var reg_num = /^[\d]+$/;
	var reg_businessname = /^[a-zA-Z0-9][^\u4E00-\u9FA5]+$/;
	var reg_eng =  /^[a-zA-Z0-9]*[^\u4E00-\u9FA5]+$/;
	var reg_linkphone = /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/;
	var reg_post = /^[0-9]{6}$/;
	var reg_phone_begin = /\d[\d]*\d$/; 
	var reg_phone_end = /\d[\d]*\d$/;
	
	if(businessname==null||businessname==""){ 
		alert("申请人 不能为空");
		document.getElementById("businessname").focus();  
		return false;
	}
	if(!reg_businessname.test(businessname)){ 
		alert("申请人 不能包含中文"); 
		document.getElementById("businessname").focus();
		return false;
	}
	if(company==null||company==""){
		alert("船东或租船人名称 不能为空");
		document.getElementById("company").focus();
		return false;
	}
	if(!reg_eng.test(company)){ 
		alert("船东或租船人名称 不能包含中文"); 
		document.getElementById("company").focus();
		return false;
	}
	if(vesselname==null||vesselname==""){
		alert("船名 不能为空");
		document.getElementById("vesselname").focus();
		return false;
	}
	if(!reg_eng.test(vesselname)){ 
		alert("船名 不能包含中文"); 
		document.getElementById("vesselname").focus();
		return false;
	}
	if(vesseltype==null||vesseltype==""||vesseltype=="-1"){
		alert("船舶类型 不能为空");
		document.getElementById("vesseltype").focus();
		return false;
	}
	if(registry==null||registry==""||registry=="-1"){
		alert("船籍 不能为空");
		document.getElementById("registry").focus();
		return false;
	}
	if(imo==null||imo==""){
		alert("IMO号 不能为空");
		document.getElementById("imo").focus();
		return false;
	}
	if(!reg_num.test(imo)){ 
		alert("IMO号 必须是数字"); 
		document.getElementById("imo").focus();
		return false;
	}
	if(tonnage==null||tonnage==""){
		alert("总吨位 不能为空");
		document.getElementById("tonnage").focus();
		return false;
	}
	if(!reg_num.test(tonnage)){
		alert("总吨位 必须是数字");
		document.getElementById("tonnage").focus();
		return false;
	}
	

	if(destinationcountrys==null||destinationcountrys==""||destinationcountrys=="-1"){
		alert("目的国 不能为空");
		document.getElementById("destinationcountrys1").focus();
		return false;
	} 
	if(destinationcountrys=="OTHER"){
		if(countrys==null||countrys==""){
			alert("目的国 不能为空");
			document.getElementById("countrys1").focus();
			return false;
		}
	}
	if(ports!=null&&ports!=""){
		if(!reg_eng.test(ports)){
			alert("目的国港口 不能包含中文"); 
			document.getElementById("ports").focus();
			return false;
		}
	}

	if(destinationcountrys_other!=null&&destinationcountrys_other!=""&&destinationcountrys_other!="-1"){
		if(countrys_other==null||countrys_other==""){
			alert("目的国及港口2不是 请选择状态 目的国2 不能为空");
			document.getElementById("countrys2").focus();
			return false;
		}
		if(!reg_eng.test(countrys_other)){
			alert("目的国2 不能包含中文"); 
			document.getElementById("countrys2").focus();
			return false;
		}
		if(ports_other!=null&&ports_other!=""){
			if(!reg_eng.test(ports_other)){
				alert("目的国港口2 不能包含中文"); 
				document.getElementById("ports2").focus();
				return false;
			}
		} 
	
	}
	if(destinationcountrys_other!=null&&destinationcountrys_other=="-1"){
		if(countrys_other!=null&&countrys_other!=""){
			alert("目的国2  存在内容 目的国及港口不能为空 ");
			document.getElementById("destinationcountrys2").focus();
			return false;
		}
		if(ports_other!=null&&ports_other!=""){
			alert("目的国港口2 存在内容 目的国及港口 不能为空 ");
			document.getElementById("destinationcountrys2").focus();
			return false;
		} 
	}
   
	
	if(proid=="-1"){
		alert("省 不能为空");
		document.getElementById("proid").focus();
		return false;
	}
	if(city=="-1"){
		alert("市 不能为空");
		document.getElementById("city").focus();
		return false;
	}
	if(portid=="-1"){ 
		alert("船舶停靠港 不能为空");
		document.getElementById("portid").focus();
		return false;
	}
	if(plandatein==null||plandatein==""){
		alert("计划入港日期 不能为空");
		document.getElementById("plandatein").focus();
		return false;
	}
	if(plandateout==null||plandateout==""){
		alert("计划离港日期 不能为空");
		document.getElementById("plandateout").focus(); 
		return false;
	}
	if(checkdate==null||checkdate==""){
		alert("合适的检查日期及时间 不能为空");
		document.getElementById("checkdate").focus();
		return false;
	}
	if(Date.parse(plandatein.replace('-','/'))>Date.parse(plandateout.replace('-','/'))){
		$("plandateout").val("");
		alert("计划离港日期 必须晚于 计划入港日期");
		document.getElementById("plandateout").focus();
		return false; 
	}
	if(Date.parse(checkdate.replace('-','/'))>Date.parse(plandateout.replace('-','/'))){
		alert("合适的检查日期及时间 不能晚于 计划离港日期");
		document.getElementById("checkdate").focus();
		return false; 
	}
	if(Date.parse(plandatein.replace('-','/'))>Date.parse(checkdate.replace('-','/'))){
		alert("计划入港日期 不能晚于合适的检查日期及时间");
		document.getElementById("checkdate").focus(); 
		return false; 
	}
	
	
	if(special==null||special==""){
		alert("特别交通费 必须是数字");
		document.getElementById("special").value="0";
		document.getElementById("special").focus();
		return false;
	}
	if(linkmanname==null||linkmanname==""){
		alert("联系人姓名 不能为空");
		document.getElementById("linkmanname").focus();
		return false;
	}
	if(phone_begin==null||phone_begin==""||phone_end==null||phone_end=="")
	{
		if(phone_begin==null||phone_begin==""){
			alert("区号 不能为空");
			document.getElementById("phone_begin").focus();
			return false;
		}
		if(phone_end==null||phone_end==""){
			alert("电话号码 不能为空");
			document.getElementById("phone_end").focus();
			return false;
		}
	}else{
		if(!reg_num.test(phone_begin)){ 
			alert("区号 必须是数字");
			document.getElementById("phone_begin").focus();
			return false;
		}
		if(!reg_num.test(phone_end)){
			alert("电话号码 必须是数字");
			document.getElementById("phone_end").focus();
			return false;
		}   
	}
	if(linkphone!=null&&linkphone!="")
	{
		if(!reg_num.test(linkphone)){
		alert("联系人手机 必须是数字");
		document.getElementById("linkphone").focus();
		return false;
		}
	}
	
	
	
	
	if(invoicetitle==null||invoicetitle==""){
		alert("发票抬头 不能为空");
		document.getElementById("invoicetitle").focus();
		return false;
	}
	if(invoiceaddress==null||invoiceaddress==""){
		alert("邮寄地址 不能为空");
		document.getElementById("invoiceaddress").focus();
		return false;
	}
	if(invoicepost==null||invoicepost==""){
		alert("收件人邮编 不能为空");
		document.getElementById("invoicepost").focus();
		return false;
	}
	if(!reg_num.test(invoicepost)){
		alert("收件人邮编 格式错误,收件人邮编 必须是数字");
		document.getElementById("invoicepost").focus();
		return false; 
	}
	if(invoicer==null||invoicer==""){
		alert("发票收件人 不能为空");
		document.getElementById("invoicer").focus();
		return false;
	} 
	if(invoicetel_begin==null||invoicetel_begin==""||invoicetel_end==null||invoicetel_end=="")
	{
		if(invoicetel_begin==null||invoicetel_begin==""){
			alert("区号 不能为空");
			document.getElementById("invoicetel_begin").focus();
			return false;
		}
		if(invoicetel_end==null||invoicetel_end==""){
			alert("电话号码 不能为空");
			document.getElementById("invoicetel_end").focus();
			return false;
		}
	}else{
		if(!reg_num.test(invoicetel_begin)){ 
			alert("区号 必须是数字");
			document.getElementById("invoicetel_begin").focus();
			return false;
		}
		if(!reg_num.test(invoicetel_end)){
			alert("电话号码 必须是数字");
			document.getElementById("invoicetel_end").focus();
			return false;
		}   
	}
	 
	return true;  

}

function initOption()	//初始化option值
	{
			var countrys = $("#page_countrys").val().split(",");
	     	var ports = $("#page_ports").val().split(","); 
	     	var selects = $("#page_selects").val().split(","); 
	     		
			for(i=0;i<countrys.length;i++){
				
		 		document.getElementsByName("countrys")[i].value = countrys[i];
				document.getElementsByName("ports")[i].value = ports[i]; 
     			
     			var country_select = document.getElementsByName("destinationcountrys")[i];
				for(j=0;j<country_select.length;j++) {
		       		if(selects[i]==country_select.options[j].value){
		     			country_select.options.selectedIndex=j;
		       		}
	      		} 
	      		
			} 
		
	}


function add_option()
{
	if(cur_num>=max_num){
		alert("最多只可以添加2个目的国家及港口。");
		return ; 
	} 
	str = " <tr id='append_tr'> <td>目的国 <select name='destinationcountrys'><option value='-1'>请选择</option> <option value='USA'>USA</option> <option value='CANADA'>CANADA</option> ";
	str += "	<option value='MEXICO'>MEXICO</option> <option value='OTHER'>OTHER</option>  "; 
	str += "	</select> 目的国港口 <input name='ports' type='text' maxlength='250' class='input' size='12' /> ";
	str += "	</td> </tr>	";  
	$("#append_tab").append(str); 
	cur_num ++;
}

function delete_option()
{
	if(cur_num <= 0){
		alert("最少要有1个目的国家及港口。");
		return ;  
	}
	$("#append_tab tr:last").remove();  
	cur_num --; 
}


function changepromary()
{
	$("#city").html("<option value='-1'>- 请选择 -</option>"); 
	$("#portid").html("<option value='-1'>- 请选择 -</option>");
	var proid = $("#proid").val(); 
	var url = "ajaxSelect.action?promaryid="+proid+"&selecttype=0";	//selecttype=0 为省份查询城市
	ajax_selectContent(url,'city'); 
	$("#city").attr("value","-1"); 
}

function changcity()
{
	$("#portid").html("<option value='-1'>- 请选择 -</option>");
	var proid = $("#proid").val(); 
	var cityid = $("#city").val();
	var url = "ajaxSelect.action?promaryid="+proid+"&cityid="+cityid+"&selecttype=1";	//selecttype=1 为按城市查询港口
	ajax_selectContent(url,'portid');
	$("#portid").attr("value","-1");
}


function ajax_selectContent(select_action,select_id)
{	
	var select = "#"+select_id;
	$.ajax({ 
	      url: select_action,
	      type: "get",
	      beforeSend:function(){
	      },
	      dataType: "text",  
	      success: function(data) {
	      	 $(select).html(data);
		  },
	      async:false,  
	      cache:false
	   }
	);
	 
}


//选中ID为obj的select控件的值为value的内容 如: 'city',1 
function selectItem(obj,value)
	{
	  var objSelect = document.getElementById(obj);
	  for(var i=0;i<objSelect.options.length;i++){
         if(objSelect.options[i].value == value)
         {
         	 setTimeout(function(){objSelect.options[i].selected = true;},1);
             isExit = true;
             break; 
         } 
  		 }    
	}

//创建空白证书验证
 function valicreateCert() 
 {
	var regcertname = /[A-Z]/;
	var regcertm = /^[0-9]+$/; 
	var cert_min = $("#cert_min").val();
	var cert_max = $("#cert_max").val();
	var certtype = $("#certtype").val();
	var certcount = $("#certcount").val();
	var orgto_name = $("#orgto_name").val();
	var datefrom = $("#datefrom").val();
	var cert_name = $("#cert_name").val();
	

	
	if(certtype==null||certtype=="-1"){ 
		alert("证书类型不能为空。"); 
		document.getElementById("certtype").focus();
		return false; 
	}else if(certtype=="0"){
		if(certcount==null||certcount==""){
			alert("证书数量不能为空。"); 
			document.getElementById("certcount").focus();
			return false; 
		} 
		if(orgto_name==null||orgto_name==""){
			alert("目标分配机构不能为空。"); 
			document.getElementById("orgto_name").focus();
			return false; 
		} 
		if(datefrom==null||datefrom==""){ 
			alert("日期不能为空。"); 
			document.getElementById("datefrom").focus();
			return false; 
		}
		if(cert_name==null||cert_name==""){ 
			alert("流水号前部标识不能为空。"); 
			document.getElementById("cert_name").focus();
			return false; 
		}
		if(!regcertname.test(cert_name)){ 
			alert("流水号前部标识必须为一位大写英文字母。"); 
			document.getElementById("cert_name").focus();
			return false; 
		}
		if(cert_min==null||cert_min==""){ 
			alert("流水号最小值不能为空。"); 
			document.getElementById("cert_min").focus();
			return false; 
		}
		if(!regcertm.test(cert_min)||cert_min.length!=7) 
		{
			alert("流水号最小值必须为7位数字。"); 
			document.getElementById("cert_min").focus();
			return false; 
		}
		if(cert_max==null||cert_max==""){ 
			alert("流水号最大值不能为空。"); 
			document.getElementById("cert_max").focus();
			return false; 
		}
		if(!regcertm.test(cert_max)||cert_max.length!=7)
		{
			alert("流水号最大值必须为7位数字。"); 
			document.getElementById("cert_max").focus();
			return false; 
		}
	}else{
		if(certcount==null||certcount==""){
			alert("证书数量不能为空。"); 
			document.getElementById("certcount").focus();
			return false; 
		} 
		if(orgto_name==null||orgto_name==""){
			alert("目标分配机构不能为空。"); 
			document.getElementById("orgto_name").focus();
			return false; 
		} 
		if(datefrom==null||datefrom==""){ 
			alert("日期不能为空。"); 
			document.getElementById("datefrom").focus();
			return false; 
		}

	
	}
	
	
	
	return true;
} 
	