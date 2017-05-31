function submitUpdateProfile(){
	var form = document.forms["updateProfile"];
	var username = form["username"].value;
	var mail = form["mail"].value;
	var firstname = form["firstname"].value;
	var surname = form["surname"].value;

	callProtectedWebServiceByAjax("PUT", "http://localhost:8080/API_REST/rest/users-service/user/"+username+"/"+firstname+"/"+surname+"/"+mail, getCookie("username"), getCookie("password"), successUpdateProfile, failureUpdateProfile);

	return false;
}


function successUpdateProfile(response)
{
	loadProfile();
}

function failureUpdateProfile(response)
{

}

function submitUpdatePassword(){
	var form = document.forms["updatePassword"];
	var password = form["password"].value;
	var password2 = form["password"].value;
	if(password == password2)
	{
		callProtectedWebServiceByAjax("PUT", "http://localhost:8080/API_REST/rest/users-service/user/password/"+getCookie("password"), getCookie("username"), getCookie("password"), successUpdatePassword, failureUpdatePassword);

	}
	else
	{
		failureUpdatePassword();
	}



	return false;
}


function successUpdatePassword(response)
{
	var form = document.forms["updatePassword"];
	var password = form["password"].value;
	document.cookie = 'password='+password+'; expires=Fri, 31 Dec 2020 23:59:59 GMT';
}

function failureUpdatePassword(response)
{

}


function loadProfile(response)
{
	callProtectedWebServiceByAjax("GET", "http://localhost:8080/API_REST/rest/users-service/user", getCookie("username"), getCookie("password"), successLoadProfile, undefined);
}

function successLoadProfile(response)
{
	var user = JSON.parse(response);
	var form = document.forms["updateProfile"];
	form["username"].value = user.username;
	form["mail"].value = user.mail;
	form["firstname"].value = user.firstname;
	form["surname"].value = user.surname;

}


