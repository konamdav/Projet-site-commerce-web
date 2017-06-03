function loadConsoles()
{	
	callWebServiceByAjax("GET", "http://localhost:8080/API_REST/rest/products-service/consoles", successConsoles, undefined);
	return false;
}

function submitCreateConsole()
{	
	success = document.getElementById("create-success");
	success.innerHTML = "";
	
	failure = document.getElementById("create-error");
	failure.innerHTML = "";
	
	var form = document.forms["create"];
	name = form["name"].value;
	
	form["name"].className ="";
	
	callProtectedWebServiceByAjax("POST", "http://localhost:8080/API_REST/rest/products-service/consoles/"+name, getCookie("username"), getCookie("password"),successCreateConsole, failureCreateConsole);
	return false;
}


function submitUpdateConsole()
{	
	success = document.getElementById("update-success");
	success.innerHTML = "";
	
	failure = document.getElementById("update-error");
	failure.innerHTML = "";
	
	form["name"].className ="";
	
	var form = document.forms["update"];
	name = form["name"].value;
	id = form["id_console"].value;
	
	callProtectedWebServiceByAjax("PUT", "http://localhost:8080/API_REST/rest/products-service/consoles/"+id+"/"+name, getCookie("username"), getCookie("password"),successUpdateConsole, failureUpdateConsole);
	return false;
}



function successConsoles(response)
{
	var consoles = JSON.parse(response);

	div = document.getElementById("consoles");	
	div.innerHTML = '';
	for(i=0; i<consoles.length;++i)
	{
		div.innerHTML += '<tr onclick="selectUpdateConsole('+consoles[i].id+', \''+consoles[i].name+'\')" role="button">'
			+'<td>'+consoles[i].id+'</td>'
			+'<td>'+consoles[i].name+'</td>'
			+'</tr>';
	}
}

function successCreateConsole(response)
{	
	success = document.getElementById("create-success");
	success.innerHTML = "<p>Formulaire validé avec succès</p>";
	loadConsoles();
}

function successUpdateConsole(response)
{
	success = document.getElementById("update-success");
	success.innerHTML = "<p>Modification effectuée</p>";
	loadConsoles();
}

function failureUpdateConsole(response)
{
	failure = document.getElementById("update-error");
	failure.innerHTML = "<p>Une erreur est survenue lors de la creation </p>";
	
	if(response!=undefined)
	{
		var form = document.forms["update"];
		form["name"].className += " has-error";
	}
}

function failureCreateConsole(response)
{
	failure = document.getElementById("create-error");
	failure.innerHTML = "<p>Une erreur est survenue lors de la creation </p>";
	
	if(response!=undefined)
	{
		var form = document.forms["create"];
		form["name"].className += " has-error";
	}
}


function selectUpdateConsole(id, name)
{
	var form = document.forms["update"];
	
	//alert(id+" "+name);
	form["name"].value = name;
	form["id_console"].value = id;
}



