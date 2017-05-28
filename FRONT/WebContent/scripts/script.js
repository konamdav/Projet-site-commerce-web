function callWebServiceByAjax(type , url, success, failure){
	var xmlhttp;
	// compatible with IE7+, Firefox, Chrome, Opera, Safari
	xmlhttp = new XMLHttpRequest();

	xmlhttp.onreadystatechange = function(){
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200){
			if(success !=undefined) success(xmlhttp.responseText);

		}
		if (xmlhttp.readyState == 4 && (xmlhttp.status >= 400)){
			if(failure!=undefined) failure(xmlhttp.responseText);

		}
	}

	xmlhttp.open(type, url, true);

	if(type == "POST")
	{
		xmlhttp.send('');
	}
	else
	{
		xmlhttp.send();
	}

}
function callWebServiceByAjaxWithBody(type , url, body, success, failure){
	var xmlhttp;
	// compatible with IE7+, Firefox, Chrome, Opera, Safari
	xmlhttp = new XMLHttpRequest();

	xmlhttp.onreadystatechange = function(){
		//alert("status "+xmlhttp.status);
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200){
			if(success !=undefined) success(xmlhttp.responseText);

		}
		if (xmlhttp.readyState == 4 && (xmlhttp.status >= 400)){
			if(failure!=undefined) failure(xmlhttp.responseText);

		}
	}

	xmlhttp.open(type, url, true);
	xmlhttp.send(body);


}

function callProtectedWebServiceByAjax(type , url, username, password, success, failure){
	var xmlhttp;
	// compatible with IE7+, Firefox, Chrome, Opera, Safari
	xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function(){
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200){
			if(success !=undefined) success(xmlhttp.responseText);

		}
		if (xmlhttp.readyState == 4 && (xmlhttp.status >= 400)){
			if(failure!=undefined) failure(xmlhttp.responseText);

		}
	}
	xmlhttp.open(type, url, true);
	xmlhttp.setRequestHeader("Authorization", "Basic " + btoa(username + ":" + password));
	xmlhttp.send();
}


function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

