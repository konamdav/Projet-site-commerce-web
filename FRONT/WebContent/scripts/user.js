
function loadUser(id)
{	idp = id;
	var title = document.getElementById("title");
	var note = document.getElementById("note");
	var content = document.getElementById("content");
	
	callProtectedWebServiceByAjax("GET", "http://localhost:8080/API_REST/rest/users-service/user", getCookie("username"), getCookie("password"), successUser, undefined);
	return false;
}



function successUser(response)
{
	var user = JSON.parse(response);
	
	document.getElementById("username").innerHTML = user.username;
	document.getElementById("firstname").innerHTML = user.firstname;
	document.getElementById("surname").innerHTML = user.surname;
	document.getElementById("mail").innerHTML = user.mail;

}



