function loadGenres()
{	
	callWebServiceByAjax("GET", "http://localhost:8080/API_REST/rest/products-service/genres", successGenres, undefined);
	return false;
}

function submitCreateGenre()
{	
	var form = document.forms["create"];
	name = form["name"].value;
	
	success = document.getElementById("create-success");
	success.innerHTML = "";
	
	failure = document.getElementById("create-error");
	failure.innerHTML = "";
	
	form["name"].className ="";
	form["id_genre"].className ="";
	
	callProtectedWebServiceByAjax("POST", "http://localhost:8080/API_REST/rest/products-service/genres/"+name, getCookie("username"), getCookie("password"),successCreateGenre, failureCreateGenre);
	return false;
}


function submitUpdateGenre()
{	
	var form = document.forms["update"];
	name = form["name"].value;
	id = form["id_genre"].value;
	
	success = document.getElementById("update-success");
	success.innerHTML = "";
	
	failure = document.getElementById("update-error");
	failure.innerHTML = "";
	
	form["name"].className ="";
	form["id_genre"].className ="";
	
	callProtectedWebServiceByAjax("PUT", "http://localhost:8080/API_REST/rest/products-service/genres/"+id+"/"+name, getCookie("username"), getCookie("password"),successUpdateGenre, failureUpdateGenre);
	return false;
}



function successGenres(response)
{
	var genres = JSON.parse(response);

	div = document.getElementById("genres");	
	div.innerHTML = '';
	for(i=0; i<genres.length;++i)
	{
		div.innerHTML += '<tr onclick="selectUpdateGenre('+genres[i].id+', \''+genres[i].name+'\')" role="button">'
			+'<td>'+genres[i].id+'</td>'
			+'<td>'+genres[i].name+'</td>'
			+'</tr>';
	}
}

function failureCreateGenre(response)
{
	
	failure = document.getElementById("create-error");
	failure.innerHTML = "<p>Une erreur est survenue lors de la creation </p>";
	
	if(response!=undefined)
	{
		var form = document.forms["create"];
		form["name"].className += " has-error";
	}
}


function successCreateGenre(response)
{
	success = document.getElementById("create-success");
	success.innerHTML = "<p>Creation effectuée</p>";
	
	loadGenres();
}

function successUpdateGenre(response)
{
	success = document.getElementById("update-success");
	success.innerHTML = "<p>Modification effectuée</p>";
	
	loadGenres();
}

function failureUpdateGenre(response)
{
	failure = document.getElementById("update-error");
	failure.innerHTML = "<p>Une erreur est survenue lors de la creation </p>";
	
	if(response!=undefined)
	{
		var form = document.forms["update"];
		form["name"].className += " has-error";
	}
}

function selectUpdateGenre(id, name)
{
	var form = document.forms["update"];
	
	//alert(id+" "+name);
	form["name"].value = name;
	form["id_genre"].value = id;
}



