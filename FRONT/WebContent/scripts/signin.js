function submitSignin(){
	var form = document.forms["formSignin"];
	form["username"].className = "form-control";
	form["password"].className = "form-control";
	form["password2"].className = "form-control";
	form["mail"].className = "form-control";
	form["surname"].className = "form-control";
	form["firstname"].className = "form-control";

	success = document.getElementById("signin-success");
	success.innerHTML = "";	
	
	failure = document.getElementById("signin-error");
	failure.innerHTML = "";
	
	var username = form["username"].value;
	var password = form["password"].value;
	var password2 = form["password2"].value;
	var mail = form["mail"].value;
	var firstname = form["firstname"].value;
	var surname = form["surname"].value;

	flag = false;

	//verif

	if(password!=password2)
	{
		form["password"].className += " has-error";
		form["password2"].className += " has-error";
		flag = true;
	}


	if(!flag){
		console.log("http://localhost:8080/API_REST/rest/users-service/user/"+username+"/"+password+"/"+firstname+"/"+surname+"/"+mail);
		callWebServiceByAjax("POST", "http://localhost:8080/API_REST/rest/users-service/user/"+username+"/"+password+"/"+firstname+"/"+surname+"/"+mail, successSignin, failureSignin);
	}
	
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
	
	if(response!=undefined)
	{
		var form = document.forms["formSignin"];
		form["username"].className += " has-error";
		form["password2"].className += " has-error";
		form["password"].className += " has-error";
		form["mail"].className += " has-error";
		form["firstname"].className += " has-error";
		form["surname"].className += " has-error";
	}
}


function submitLogin(){
	var form = document.forms["formLogin"];
	var username = form["username"].value;
	var password = form["password"].value;

	form["username"].className = "form-control";
	form["password"].className = "form-control";
	
	success = document.getElementById("login-success");
	success.innerHTML = "";	
	
	failure = document.getElementById("login-error");
	failure.innerHTML = "";
	
	console.log("http://localhost:8080/API_REST/rest/users-service/connect/"+username+"/"+password);
	callWebServiceByAjax("PUT", "http://localhost:8080/API_REST/rest/users-service/connect/"+username+"/"+password, successLogin, failureLogin);

	return false;
}

function successLogin(response)
{
	user = JSON.parse(response);
	role = user.role;
	form = document.forms["formLogin"];
	username = form["username"].value;
	password = form["password"].value;

	
	
	callWebServiceByAjax("GET", "session.jsp?CONNECT&ROLE="+role, successLoginFront, undefined);

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
	failure = document.getElementById("login-error");
	failure.innerHTML = "<p>Connexion impossible</p>";
	
	if(response!=undefined)
	{
		var form = document.forms["formLogin"];
		form["username"].className += " has-error";
		form["password"].className += " has-error";
	}

}
