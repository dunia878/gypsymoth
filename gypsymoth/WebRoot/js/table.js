// jQueryScript Document
$(document).ready(function(){
	//表头经过
	$(".ct th").hover(function(){
	$(this).addClass("Hover");
	},function(){
	$(this).removeClass("Hover");
	})
	
	//表头选择
	$(".ct th").click(function(){
		$(".ct th.over").removeClass("over");
		$(this).addClass("over");
	})
	
	// 行选择js
	$("#tb tbody>tr").click(function(){
			var o = $("input[@name=checkbox1]",this);				   
			o.attr('checked')==true ? o.attr('checked','') : o.attr('checked',true);
			o.attr('checked')==true ? $(this).addClass('selected') : $(this).removeClass('selected');
	})
	
	$("input[@name=checkbox1]").click(function() {
		var p = $(this);				   
		p.attr('checked')==true ? p.attr('checked','') : p.attr('checked','true');
	})
	
	//鼠标划过行,切换图标
	$("#tb tbody>tr").mouseover(function() {
		$(".chk",this).css("display","block");
		$(".ico",this).css("display","none");
		$(this).addClass("trover");
			
	}).mouseout(function(){
		q = $("input[@name=checkbox1]",this);
		$(this).removeClass("trover");

		if (q.attr('checked')) {
			$(".chk",this).css("display","block");
			$(".ico",this).css("display","none");
			} 
		else {
			$(".chk",this).css("display","none");
			$(".ico",this).css("display","block");}
	})
	
	
})