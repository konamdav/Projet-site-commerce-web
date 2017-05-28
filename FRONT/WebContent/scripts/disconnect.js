function disconnect()
{	
	callProtectedWebServiceByAjax("PUT", "http://localhost:8080/API_REST/rest/users-service/disconnect", getCookie("username"), getCookie("password"),successDisconnectAPI, failureDisconnect);
	return false;
	
}

function successDisconnectAPI(response)
{
	callWebServiceByAjax("GET", "session.jsp?DISCONNECT", successDisconnectFront, failureDisconnect);
}

function successDisconnectFront(response)
{
	window.location='index.jsp';
}


function failureDisconnect(response)
{
	
}