//更新用户做角色变更的js
function moveSingleRole(n1, n2) {
	//var account_type = document.getElementById("accountType").value;
	if (n1.selectedIndex == -1) {
		alert("\u8bf7\u9009\u62e9\u89d2\u8272!");
	}
	if (n2.length > 0 && n2.options[0].value == "-123") {
		n2.remove(0);
	}
	while (n1.selectedIndex != -1) {
		var indx = n1.selectedIndex;
		var t = n1.options[indx].text;
		var id = n1.options[indx].value;
//		if (account_type == 3 && id == 1 || account_type == 2 && id == 5 || account_type == 1 && id == 7 || account_type == 4 && id == 3 || account_type == 6 && id == 6) {
//			alert("\u8be5\u89d2\u8272\u4e0d\u80fd\u79fb\u9664");
//			return false;
//		} else {
			n2.options.add(new Option(t, id));
			n1.remove(indx);
//		}
	}
}
function moveAllRole(n1, n2) {
//	var account_type = document.getElementById("accountType").value;
	if (n2.length > 0 && n2.options[0].value == "-123") {
		n2.remove(0);
	}
	for (var i = 0; i < n1.length; i++) {
		var t = n1.options[i].text;
		var id = n1.options[i].value;
//		if (account_type == 3 && id == 1 || account_type == 2 && id == 5 || account_type == 1 && id == 7 || account_type == 4 && id == 3 || account_type == 6 && id == 6) {
//		} else {
			n2.options.add(new Option(t, id));
			n1.remove(i);
			i--;
//		}
	}
}

//用户更新提交选中所有已赋予角色的js
function selectRole()
{
	alert("123====");
	for(var j=0; j<document.forms[0].roleHas.length; j++)
	{
		document.forms[0].roleHas.options[j].selected=true;
	}	
	alert("456====");
	return true;
}

