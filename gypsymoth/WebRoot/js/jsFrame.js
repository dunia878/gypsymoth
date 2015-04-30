		
		function isEmpty(value)
		{
			return (value == null || value =="");
		}
		
		function checkUserName(username)
		{
			if(isEmpty(username))
			{
				return false;
			}
			return true;
		}

		function getField(id)
		{
			 return document.getElementById(id);
		}
		
		function setNoteValue(id,pvalue,pcolor)
		{
			var obj = getField(id);
			obj.innerHTML = "<font color='"+pcolor+"'>"+pvalue+"</font>";
		}
		
		//获取中英文混合字符串长度
		function getCLength(value){
	    	var totalLength = 0;
	   		var tempValue = null;
	    	for(var i = 0;i < value.length ; i++)
	    	{
	    		tempValue = value.charAt(i);
	    		if(tempValue == ' '){
	    			totalLength = -1;
	    			break;
	    		}
	    		if(checkChinese(tempValue)){
	    			totalLength += 2;
	    		}else if(checkCharacterOrDigit(tempValue)){
	    			totalLength += 1;
	    		}else{
	    			totalLength = -1;
	    			break;
	    		}
	    	}	
	    	return totalLength;
	    }	
	    
	     function checkCharacterOrDigit(value)
	    {
	    	var pp = /^[A-Za-z0-9_-]$/;	
	    	if (!pp.exec(value))
				return false;
			return true;
	    }
	    
	    function checkChinese(value)
	    {
			var pp = /^[\u4e00-\u9fa5]$/;
	    	if (!pp.exec(value))
				return false;
			return true;
	    }
	    //校验数字
	    function isDigit(s)
		{
			var patrn=/^[0-9]{1,20}$/;
			if (!patrn.exec(s)) return false
			return true
		}
	    //校验密码：只能输入字母、数字、下划线
		function isNCL(str)
		{
			var patrn = /^\w+$/;
			if (!patrn.exec(str)){
				return false;
			}else{
				return true;
			}
		}
		//校验电话：区号-电话/手机号
	    function isPhone(phone){
	    	var formatPhone = /\d{3}-\d{8}|\d{4}-\d{7}|\d{3}-\d{7}|\d{4}-\d{8}/;
 			var formatMobile = /^0{0,1}(13[0-9]?|15[0-9])[0-9]{8}$/;
 			
 			if((!formatPhone.test(phone))&&(!formatMobile.test(phone))){
 				return false;
 			}else{
 				return true;
 			}
	    }
	    //校验邮编：
	    function isPost(postnumber){
	    	var formatPost = /[1-9]\d{5}(?!\d)/;//验证邮编
 			
 			if((!formatPost.test(postnumber))&&(!formatPost.test(postnumber))){
 				return false;
 			}else{
 				return true;
 			}
	    }
	    //校验路径
	    function isDirectPath(paths){
	    	var formatPath = /^[a-zA-Z]:(([a-zA-Z]*)||([a-zA-Z]*\\))*|(([a-zA-Z]*)||([a-zA-Z]*\\))*/;
 			
 			if(!formatPath.test(paths)){
 				return false;
 			}else{
 				return true;
 			}
	    }
	    /**
	    *字符串校验
	    *id:输入框ID,mess_id:提示目录ID
	    *start_len:字符串符合条件的最短长度,end_len：最大长度
		*field_name:字段中文名称
		*isNull：是否可以为空 1为可以，2为不可以
	    */
	    function char_valid(id,mess_id,start_len,end_len,field_name,isNull){
			var name = getField(id);
			if(isNull == 2 && isEmpty(name.value)){
					setNoteValue(mess_id,field_name+'不可为空','red');
					return false;
			}
			if(start_len != 0 && end_len !=0){		
				if(getCLength(name.value)<start_len){			
						setNoteValue(mess_id,field_name+'长度不能小于'+start_len,'red');
						return false;
				}
				if(getCLength(name.value)>end_len){			
						setNoteValue(mess_id,field_name+'长度不能大于'+end_len,'red');
						return false;
				}else{
					setNoteValue(mess_id," ",'red');
				}				
			}
		}
	    
	    /**
	    *数字校验
	    *id:输入框ID,mess_id:提示目录ID
	    *start_len:字符串符合条件的最短长度,end_len：最大长度
		*field_name:字段中文名称
		*isNull：是否可以为空 1为可以，2为不可以
	    */
	    function number_valid(id,mess_id,start_len,end_len,field_name,isNull){
			var name = getField(id);
			if(isNull == 2 && isEmpty(name.value)){
					setNoteValue(mess_id,field_name+'不可为空','red');
					return false;
			}
			if(start_len != 0 && end_len !=0){		
				if(getCLength(name.value)<start_len){			
						setNoteValue(mess_id,field_name+'长度不能小于'+start_len,'red');
						return false;
				}
				if(getCLength(name.value)>end_len){			
						setNoteValue(mess_id,field_name+'长度不能大于'+end_len,'red');
						return false;
				}				
			}
			if(name.value!="" && !isDigit(name.value)){
				setNoteValue(mess_id,field_name+'只能是数字','red');
				return false;
			}else{
				setNoteValue(mess_id," ",'red');
			}    	
	    }
	    
		//校验密码：只能字母C、数字N、下划线L的组合的字符串校验,isNull是否可以为空：1可以，2不可以为空
		function ncl_valid(id,mess_id,start_len,end_len,field_name,isNull){
			var name = getField(id);
			if(isNull == 2 && isEmpty(name.value)){
					setNoteValue(mess_id,field_name+'不可为空','red');
					return false;
			}
			if(start_len != 0 && end_len !=0){		
				if(getCLength(name.value)<start_len){			
						setNoteValue(mess_id,field_name+'长度不能小于'+start_len,'red');
						return false;
				}
				if(getCLength(name.value)>end_len){			
						setNoteValue(mess_id,field_name+'长度不能大于'+end_len,'red');
						return false;
				}				
			}
			if(!isNCL(name.value)){
					setNoteValue(mess_id,field_name+'只能字母、数字、下划线的组合','red');
					return false;
			}else{
				setNoteValue(mess_id," ",'red');
			}
		}	    
	    /**
	    *电话校验
	    *id:输入框ID,mess_id:提示目录ID
	    *start_len:字符串符合条件的最短长度,end_len：最大长度
		*field_name:字段中文名称
		*isNull：是否可以为空 1为可以，2为不可以
	    */
	    function phone_valid(id,mess_id,start_len,end_len,field_name,isNull){
			var name = getField(id);
			if(isNull == 2 && isEmpty(name.value)){
					setNoteValue(mess_id,field_name+'不可为空','red');
					return false;
			}
			if(start_len != 0 && end_len !=0){		
				if(getCLength(name.value)<start_len){			
						setNoteValue(mess_id,field_name+'长度不能小于'+start_len,'red');
						return false;
				}
				if(getCLength(name.value)>end_len){			
						setNoteValue(mess_id,field_name+'长度不能大于'+end_len,'red');
						return false;
				}else{
					setNoteValue(mess_id," ",'red');
				}				
			}
			if(!isPhone(name.value)){
					setNoteValue(mess_id,'请输入正确格式的'+field_name+'，如“区号-座机号”，“手机号”！','red');
					return false;
			}else{
				setNoteValue(mess_id," ",'red');
			}
		}	
	    /**
	    *EMAIL校验
	    *id:输入框ID,mess_id:提示目录ID
		*field_name:字段中文名称
		*isNull：是否可以为空 1为可以，2为不可以
	    */
	    function email_valid(id,mess_id,field_name,isNull){
			var name = getField(id);
			if(isNull == 2 && isEmpty(name.value)){
					setNoteValue(mess_id,field_name+'不可为空','red');
					return false;
			}
			if(!checkEmail(name.value)){
					setNoteValue(mess_id,'请输入正确格式的'+field_name,'red');
					return false;
			}else{
				setNoteValue(mess_id," ",'red');
			}
		}	
		 /**
	    *路径校验
	    *id:输入框ID,mess_id:提示目录ID
		*field_name:字段中文名称
		*isNull：是否可以为空 1为可以，2为不可以
	    */
	    function path_valid(id,mess_id,start_len,end_len,field_name,isNull){
			var name = getField(id);
			if(isNull == 2 && isEmpty(name.value)){
					setNoteValue(mess_id,field_name+'不可为空','red');
					return false;
			}
			if(start_len != 0 && end_len !=0){		
				if(name.value.length<start_len){			
						setNoteValue(mess_id,field_name+'长度不能小于'+start_len,'red');
						return false;
				}
				if(name.value.length>end_len){			
						setNoteValue(mess_id,field_name+'长度不能大于'+end_len,'red');
						return false;
				}else{
					setNoteValue(mess_id," ",'red');
				}				
			}
			if(!isDirectPath(name.value)){
					setNoteValue(mess_id,'请输入正确格式的'+field_name,'red');
					return false;
			}else{
				setNoteValue(mess_id," ",'red');
			}
		}   		    
	    function checkEmail(value) {
			var patrn=/^\s*([\w-]+(\.\w+)*@([\w-]+\.)+\w+)\s*$/;
			if (!patrn.exec(value)) return false;
			return true;
		}	  

		function checkName(name)
		{
			var username = getField(name);
			if(isEmpty(username.value))
			{
					setNoteValue('noteMail','用户名不可为空','red');
					return false;
			}
		}
				
		function checkPs()
		{
			var password = getField('password');
			if(isEmpty(password.value))
			{
				setNoteValue('notePs','密码不可为空','red');
				return;
			}
			if(!isPasswd(password.value))
			{
				setNoteValue('notePs','密码格式不正确','red');
				return;
			}
			setNoteValue('notePs','','green');
			return;
		}
		/**
	    *邮编校验
	    *id:输入框ID,mess_id:提示目录ID
	    *start_len:字符串符合条件的最短长度,end_len：最大长度
		*field_name:字段中文名称
		*isNull：是否可以为空 1为可以，2为不可以
	    */
	    function post_valid(id,mess_id,start_len,end_len,field_name,isNull){
			var name = getField(id);
			if(isNull == 2 && isEmpty(name.value)){
					setNoteValue(mess_id,field_name+'不可为空','red');
					return false;
			}
			if(start_len != 0 && end_len !=0){
				if(getCLength(name.value)<start_len){			
						setNoteValue(mess_id,field_name+'长度不能小于'+start_len,'red');
						return false;
				}
				if(getCLength(name.value)>end_len){			
						setNoteValue(mess_id,field_name+'长度不能大于'+end_len,'red');
						return false;
				}else{
					setNoteValue(mess_id," ",'red');
				}				
			}
			if(name.value!="" && !isPost(name.value)){
					setNoteValue(mess_id,'请输入正确格式的'+field_name,'red');
					return false;
			}else{
				setNoteValue(mess_id," ",'red');
			}
		}
		/**
		function checkConfirmPs()
		{
			var cpassword = getField('passwordRepeat');
			var password = getField('password');
			if(isEmpty(cpassword.value))
			{
			
				setNoteValue('noteConfirmPs','重复密码不可为空','red');
				return;
			}
			if(!isPasswd(cpassword.value))
			{
				setNoteValue('noteConfirmPs','重复密码格式不正确','red');
				return;
			}
			if(password.value != cpassword.value)
			{
				setNoteValue('noteConfirmPs','两次密码输入不一致，请重新输入','red');
				return;
			}
			setNoteValue('noteConfirmPs','','green');
			return;
		}**/
		function checkConfirmPs(ps_id,reps_id,mess_id){
			var cpassword = getField(reps_id);
			var password = getField(ps_id);
			if(isEmpty(cpassword.value))
			{
				setNoteValue(mess_id,'重复密码不可为空','red');
				return false;
			}
			if(!isNCL(cpassword.value))
			{
				setNoteValue(mess_id,'重复密码格式不正确','red');
				return false;
			}
			if(password.value != cpassword.value)
			{
				setNoteValue(mess_id,'两次密码输入不一致，请重新输入','red');
				return false;
			}
			setNoteValue(mess_id,'','green');
			return true;
		}
		
		function checkVerifyCode()
		{
			var verify = getField('verifyCode');
			if(isEmpty(verify.value))
			{
				setNoteValue('noteVerifyCode','请输入验证码','red');
				return;
			}
			setNoteValue('noteVerifyCode','','green');
			return;
		}
		
		function checkPS(password)
		{
			if(isEmpty(password))
			{
				return false;
			}
			return true;
		}
		
		function checkAjax(id,msgid,actionmsg){
			var inputvalue = document.getElementById(id).value;
			if(inputvalue==""){
				document.getElementById(msgid).innerHTML = "请先填写内容！please input contents";
				return false;
			}
			else if(inputvalue.length<3){
				document.getElementById(msgid).innerHTML = "不能少于3个字符！least 3 characters";
				return false;
			}
			else if(inputvalue.length>15){
				document.getElementById(msgid).innerHTML = "不能大于15个字符！most 15 characters";
				return false;
			}
			else{
				ajaxselect(actionmsg,id,msgid);
			}
		}

