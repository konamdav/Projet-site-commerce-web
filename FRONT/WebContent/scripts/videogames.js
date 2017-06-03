function loadVideogames()
{	
	callWebServiceByAjax("GET", "http://localhost:8080/API_REST/rest/products-service/videogames", successVideogames, undefined);
	return false;
}

function loadPublishers()
{	
	callWebServiceByAjax("GET", "http://localhost:8080/API_REST/rest/products-service/publishers", successPublishers, undefined);
	return false;
}


function successPublishers(response)
{
	var publishers = JSON.parse(response);

	div = document.getElementById("id_publisher");	
	div.innerHTML = '';
	div2 = document.getElementById("id_publisher2");	
	div2.innerHTML = '';
	for(i=0; i<publishers.length;++i)
	{
		div.innerHTML += '<option value="'+publishers[i].id+'">'+publishers[i].name+'</option>';
		div2.innerHTML = div.innerHTML;
	}	
}

function loadTags()
{	
	callWebServiceByAjax("GET", "http://localhost:8080/API_REST/rest/products-service/tags", successTags, undefined);
	return false;
}


function successTags(response)
{
	var tags = JSON.parse(response);

	div = document.getElementById("id_tag");	
	div.innerHTML = '';
	for(i=0; i<tags.length;++i)
	{
		div.innerHTML += '<option value="'+tags[i].name+'">'+tags[i].name+'</option>';
	}	
}

function loadGenres()
{	
	callWebServiceByAjax("GET", "http://localhost:8080/API_REST/rest/products-service/genres", successGenres, undefined);
	return false;
}


function successGenres(response)
{
	var genres = JSON.parse(response);

	div = document.getElementById("id_genre");	
	div.innerHTML = '';
	for(i=0; i<genres.length;++i)
	{
		div.innerHTML += '<option value="'+genres[i].id+'">'+genres[i].name+'</option>';
	}	
}

function loadPegi()
{	
	callWebServiceByAjax("GET", "http://localhost:8080/API_REST/rest/products-service/pegi", successPegi, undefined);
	return false;
}


function successPegi(response)
{
	var pegi = JSON.parse(response);

	div = document.getElementById("id_pegi");	
	div.innerHTML = '';
	for(i=0; i<pegi.length;++i)
	{
		div.innerHTML += '<option value="'+pegi[i].id+'">'+pegi[i].name+'</option>';
	}	
}


function submitCreateVideogame()
{	
	var form = document.forms["create"];
	name = form["name"].value;
	id_publisher = form["id_publisher"].value;
	
	form["name"].className = "";
	form["id_publisher"].className = "";
	
	success = document.getElementById("create-success");
	success.innerHTML = "";
	
	failure = document.getElementById("create-error");
	failure.innerHTML = "";
	
	//alert("http://localhost:8080/API_REST/rest/products-service/videogames/"+name+"/"+id_publisher);
	callProtectedWebServiceByAjax("POST", "http://localhost:8080/API_REST/rest/products-service/videogames/"+name+"/"+id_publisher, getCookie("username"), getCookie("password"),successCreateVideogame, failureCreateVideogame);
	return false;
}

function failureCreateVideogame(response)
{
	failure = document.getElementById("create-error");
	failure.innerHTML = "<p>Une erreur est survenue lors de la validation </p>";
	
	if(response!=undefined)
	{
		var form = document.forms["create"];
		form["name"].className = " has-error";
		form["id_publisher"].className = " has-error";
	}
}

function failureUpdateVideogame(response)
{
	failure = document.getElementById("update-error");
	failure.innerHTML = "<p>Une erreur est survenue lors de la validation </p>";
	
	if(response!=undefined)
	{
		var form = document.forms["update"];
		form["name"].className = " has-error";
		form["id_publisher2"].className = " has-error";
	}
}

function submitUpdateVideogame()
{	
	var form = document.forms["update"];
	name = form["name"].value;
	id = form["id_videogame"].value;
	id_publisher = form["id_publisher2"].value;
	
	form["name"].className = "";
	form["id_publisher2"].className = "";
	
	success = document.getElementById("update-success");
	success.innerHTML = "";
	
	failure = document.getElementById("update-error");
	failure.innerHTML = "";

	callProtectedWebServiceByAjax("PUT", "http://localhost:8080/API_REST/rest/products-service/videogames/"+id+"/"+name+"/"+id_publisher, getCookie("username"), getCookie("password"),successUpdateVideogame, failureUpdateVideogame);
	return false;
}



function successVideogames(response)
{
	var videogames = JSON.parse(response);

	div = document.getElementById("videogames");	
	div.innerHTML = '';
	for(i=0; i<videogames.length;++i)
	{
		div.innerHTML += '<tr onclick="selectUpdateVideogame('+videogames[i].id+')" role="button">'
		+'<td>'+videogames[i].id+'</td>'
		+'<td>'+videogames[i].name+'</td>'
		+'</tr>';
	}
}

function successCreateVideogame(response)
{
	success = document.getElementById("create-success");
	success.innerHTML = "<p>Formulaire validé avec succès</p>";
	loadVideogames();
}

//
function successUpdateVideogame(response)
{
	success = document.getElementById("update-success");
	success.innerHTML = "<p>Formulaire validé avec succès</p>";
	loadVideogames();
}

function removeGenre(id)
{
	var form = document.forms["update"];
	id_videogame= form["id_videogame"].value;
	

	callProtectedWebServiceByAjax("DELETE", "http://localhost:8080/API_REST/rest/products-service/videogames/"+id_videogame+"/genres/"+id, getCookie("username"), getCookie("password"),successAddToVG, undefined);

}


function removePegi(id)
{
	var form = document.forms["update"];
	id_videogame= form["id_videogame"].value;
	

	callProtectedWebServiceByAjax("DELETE", "http://localhost:8080/API_REST/rest/products-service/videogames/"+id_videogame+"/pegi/"+id, getCookie("username"), getCookie("password"),successAddToVG, undefined);

}

function removeTag(id)
{
	var form = document.forms["update"];
	id_videogame= form["id_videogame"].value;
	

	callProtectedWebServiceByAjax("DELETE", "http://localhost:8080/API_REST/rest/products-service/videogames/"+id_videogame+"/tags/"+id, getCookie("username"), getCookie("password"),successAddToVG, undefined);

}

function addTag()
{
	var form = document.forms["update"];
	id_videogame= form["id_videogame"].value;
	id_tag = form["id_tag"].value;
	
	if(id_tag!=undefined){
		callProtectedWebServiceByAjax("POST", "http://localhost:8080/API_REST/rest/products-service/videogames/"+id_videogame+"/tags/"+id_tag, getCookie("username"), getCookie("password"),successAddToVG, undefined);
	}
}

function successAddToVG(response)
{

	var form = document.forms["update"];
	id = form["id_videogame"].value;
	callWebServiceByAjax("GET", "http://localhost:8080/API_REST/rest/products-service/videogames/"+id, loadUpdatedVideogame2, undefined);
}

function addGenre()
{
	var form = document.forms["update"];
	id_videogame= form["id_videogame"].value;
	id_genre= form["id_genre"].value;
	
	if(id_genre!=undefined){
		callProtectedWebServiceByAjax("POST", "http://localhost:8080/API_REST/rest/products-service/videogames/"+id_videogame+"/genres/"+id_genre, getCookie("username"), getCookie("password"),successAddToVG, undefined);
	}
}

function addPegi()
{
	var form = document.forms["update"];
	id_videogame= form["id_videogame"].value;
	id_pegi= form["id_pegi"].value;
	
	if(id_pegi!=undefined){
		callProtectedWebServiceByAjax("POST", "http://localhost:8080/API_REST/rest/products-service/videogames/"+id_videogame+"/pegi/"+id_pegi, getCookie("username"), getCookie("password"),successAddToVG, undefined);
	}
}



function selectUpdateVideogame(id)
{
	var form = document.forms["update"];
	form["id_videogame"].value = id;

	callWebServiceByAjax("GET", "http://localhost:8080/API_REST/rest/products-service/videogames/"+id, loadUpdatedVideogame, undefined);
}

function loadUpdatedVideogame(response)
{
	var form = document.forms["update"];
	videogame = JSON.parse(response);

	form["name"].value = videogame.name;
	form["id_publisher2"].value = videogame.publisher.id;

	genres = document.getElementById("vg_genres");
	genres.innerHTML ="";
	for(i = 0; i<videogame.genres.length; ++i)
	{
		genres.innerHTML += '<tr><td>'+videogame.genres[i].name+'</td>'
		+'<td><button type="submit" onclick="removeGenre('+videogame.genres[i].id+');"class="btn btn-secondary">Retirer</button></td></tr>';
	}

	pegi = document.getElementById("vg_pegi");
	pegi.innerHTML ="";
	for(i = 0; i<videogame.classifications.length; ++i)
	{
		pegi.innerHTML += '<tr><td>'+videogame.classifications[i].name+'</td>'
		+'<td><button type="submit" onclick="removePegi('+videogame.classifications[i].id+');"class="btn btn-secondary">Retirer</button></td></tr>';
	}

	tags = document.getElementById("vg_tags");
	tags.innerHTML ="";
	for(i = 0; i<videogame.tags.length; ++i)
	{
		tags.innerHTML += '<tr><td>'+videogame.tags[i].name+'</td>'
		+'<td><button type="submit" onclick="removeTag('+videogame.tags[i].name+');"class="btn btn-secondary">Retirer</button></td></tr>';
	}


}


function loadUpdatedVideogame2(response)
{
	var form = document.forms["update"];
	videogame = JSON.parse(response);

	genres = document.getElementById("vg_genres");
	genres.innerHTML ="";
	for(i = 0; i<videogame.genres.length; ++i)
	{
		genres.innerHTML += '<tr><td>'+videogame.genres[i].name+'</td>'
		+'<td><button type="submit" onclick="removeGenre('+videogame.genres[i].id+');"class="btn btn-secondary">Retirer</button></td></tr>';
	}

	pegi = document.getElementById("vg_pegi");
	pegi.innerHTML ="";
	for(i = 0; i<videogame.classifications.length; ++i)
	{
		pegi.innerHTML += '<tr><td>'+videogame.classifications[i].name+'</td>'
		+'<td><button type="submit" onclick="removePegi('+videogame.classifications[i].id+');"class="btn btn-secondary">Retirer</button></td></tr>';
	}

	tags = document.getElementById("vg_tags");
	tags.innerHTML ="";
	for(i = 0; i<videogame.tags.length; ++i)
	{
		tags.innerHTML += '<tr><td>'+videogame.tags[i].name+'</td>'
		+'<td><button type="submit" onclick="removeTag('+videogame.tags[i].name+');"class="btn btn-secondary">Retirer</button></td></tr>';
	}


}




