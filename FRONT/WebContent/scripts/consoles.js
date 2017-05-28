function loadConsoles()
{	
	callWebServiceByAjax("GET", "http://localhost:8080/API_REST/rest/products-service/consoles", successConsoles, undefined);
	return false;
}

function submitCreateConsole()
{	
	var form = document.forms["create"];
	name = form["name"].value;
	
	callProtectedWebServiceByAjax("POST", "http://localhost:8080/API_REST/rest/products-service/consoles/"+name, getCookie("username"), getCookie("password"),successCreateConsole, undefined);
	return false;
}


function submitUpdateConsole()
{	
	var form = document.forms["update"];
	name = form["name"].value;
	id = form["id_genre"].value;
	
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
	loadConsoles();
}

function successUpdateConsole(response)
{
	loadConsoles();
}

function failureUpdateConsole(response)
{
	
}

function selectUpdateConsole(id, name)
{
	var form = document.forms["update"];
	
	//alert(id+" "+name);
	form["name"].value = name;
	form["id_genre"].value = id;
}



