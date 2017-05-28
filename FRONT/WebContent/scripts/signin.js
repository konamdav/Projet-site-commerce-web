function submitSignin(){
	var form = document.forms["formSignin"];
	var username = form["username"].value;
	var password = form["password"].value;
	var password2 = form["password2"].value;
	var mail = form["mail"].value;
	var firstname = form["firstname"].value;
	var surname = form["surname"].value;

	
	console.log("http://localhost:8080/API_REST/rest/users-service/user/"+username+"/"+password+"/"+firstname+"/"+surname+"/"+mail);
	callWebServiceByAjax("POST", "http://localhost:8080/API_REST/rest/users-service/user/"+username+"/"+password+"/"+firstname+"/"+surname+"/"+mail, successSignin, failureSignin);
	
	return false;
}



function successSignin(response)
{
	success = document.getElementById("signin-success");
	success.innerHTML = "<p>Inscription réussie</p>";
	
	document.forms["formSignin"].reset();

}

function failureSignin(response)
{
	failure = document.getElementById("signin-success");
	failure.innerHTML = "<p>Une erreur est survenue lors de l'inscription</p>";

}


function submitLogin(){
	var form = document.forms["formLogin"];
	var username = form["username"].value;
	var password = form["password"].value;
	
	//alert("login");
	
	
	
	console.log("http://localhost:8080/API_REST/rest/users-service/connect/"+username+"/"+password);
	callWebServiceByAjax("PUT", "http://localhost:8080/API_REST/rest/users-service/connect/"+username+"/"+password, successLogin, failureLogin);
	//callWebServiceByAjax("GET", "http://localhost:8080/API_REST/rest/users-service/testGet", okSignin);
	//callWebServiceByAjax("PUT", "http://localhost:8080/API_REST/rest/users-service/testPut", okSignin);
	//callWebServiceByAjax("POST", "http://localhost:8080/API_REST/rest/users-service/t", okSignin);
	//alert("http://localhost:8080/API_REST/rest/users-service/user/"+username+"/"+password+"/"+firstname+"/"+surname+"/"+mail);
	//alert("return false");
	return false;
}

function successLogin(response)
{
	var form = document.forms["formLogin"];
	var username = form["username"].value;
	var password = form["password"].value;
	
	callWebServiceByAjax("GET", "session.jsp?CONNECT&USERNAME="+username+"&PASSWORD="+password, successLoginFront, undefined);
	
	success = document.getElementById("login-success");
	success.innerHTML = "<p>Connexion réussie<br>Redirection dans quelques secondes</p>";	
	document.forms["formLogin"].reset();
	
	document.cookie = 'username='+username+'; expires=Fri, 31 Dec 2020 23:59:59 GMT';
	document.cookie = 'password='+password+'; expires=Fri, 31 Dec 2020 23:59:59 GMT';
	
	
}

function successLoginFront(response)
{
	setTimeout(window.location='index.jsp',5000);
}

function failureLogin(response)
{
	failure = document.getElementById("login-success");
	failure.innerHTML = "<p>Connexion impossible</p>";

}
