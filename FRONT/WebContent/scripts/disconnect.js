function disconnect()
{	
	callProtectedWebServiceByAjax("PUT", "http://localhost:8080/API_REST/rest/users-service/disconnect", getCookie("username"), getCookie("password"),successDisconnectAPI, failureDisconnect);
	return false;
	
}

function successDisconnectAPI(response)
{
	//alert("disconnect ok (1/2) ");
	callWebServiceByAjax("GET", "session.jsp?DISCONNECT=true", successDisconnectFront, failureDisconnect);
}

function successDisconnectFront(response)
{
	//alert("disconnect ok (2/2) ");
	window.location='index.jsp';
}


function failureDisconnect(response)
{
	alert("failure disconnection -> try again");
}