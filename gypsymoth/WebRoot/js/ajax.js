function createXMLHttpRequest() {
	if (window.XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest();//mozilla浏览器
	} else if (window.ActiveXObject) {
		try {
			xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");//IE旧版本
		} catch (e) {
		}
		try {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");//IE新版本
		} catch (e) {
		}
		if (!xmlHttp) {
			window.alert("不能创建XMLHTTPREQUEST对象!");
			return false;
		}
	}
}
function init() {
	var xmlHttp;
	var inputField;
	var out;
	var url;
}

function ajaxselect(action, input, output) {
	init();

	//inputField = document.getElementById("libName");
	inputField = document.getElementById(input);
	//       alert(inputField.value);
	out = output;
	url = action;
	//     alert(url);
	if (inputField.value.length >= 0) {
		createXMLHttpRequest();
		//var url = "ajaxSelect.action?b=" +encodeURI(inputField.value);
		//var url = "ajaxSelect.action";
		var s = "input=" + inputField.value;
		xmlHttp.open("POST", url, true);
		xmlHttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xmlHttp.onreadystatechange = callback;
		xmlHttp.send(s);
	} else {
		//  clearNames();
	}
}

function ajaxsubmit(action, input, id, output) {
	init();

	//inputField = document.getElementById("libName");
	//alert(document.getElementById(input));
	inputField = document.getElementById(input);
	//       alert(inputField.value);
	out = output;
	url = action;
	//     alert(url);
	if (inputField.value.length >= 0) {
		createXMLHttpRequest();
		//var url = "ajaxSelect.action?b=" +encodeURI(inputField.value);
		//var url = "ajaxSelect.action";
		var s = "qualified=" + inputField.value + "&businessid=" + id;
		xmlHttp.open("POST", url, true);
		xmlHttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xmlHttp.onreadystatechange = callback;
		xmlHttp.send(s);
	} else {
		//  clearNames();
	}
}

function callback() {
	// alert("xmlHttp.readyState="+xmlHttp.readyState);
	if (xmlHttp.readyState == 4) {
		//alert("xmlHttp.status="+xmlHttp.status);
		if (xmlHttp.status == 200) {
			//document.all.show.innerHTML = xmlHttp.responseText;
			//alert(xmlHttp.responseText);
			// alert(out);
			//alert(document.getElementById(out).innerHTML );
			document.getElementById(out).innerHTML = xmlHttp.responseText;
			//   alert(document.getElementById(out).innerHTML );
		}
	}
}