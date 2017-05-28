function loadGenres()
{	
	callWebServiceByAjax("GET", "http://localhost:8080/API_REST/rest/products-service/genres", successGenres, undefined);
	return false;
}

function submitCreateGenre()
{	
	var form = document.forms["create"];
	name = form["name"].value;
	
	callProtectedWebServiceByAjax("POST", "http://localhost:8080/API_REST/rest/products-service/genres/"+name, getCookie("username"), getCookie("password"),successCreateGenre, undefined);
	return false;
}


function submitUpdateGenre()
{	
	var form = document.forms["update"];
	name = form["name"].value;
	id = form["id_genre"].value;
	
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

function successCreateGenre(response)
{
	loadGenres();
}

function successUpdateGenre(response)
{
	loadGenres();
}

function failureUpdateGenre(response)
{
	
}

function selectUpdateGenre(id, name)
{
	var form = document.forms["update"];
	
	//alert(id+" "+name);
	form["name"].value = name;
	form["id_genre"].value = id;
}



