function loadPegi()
{	
	callWebServiceByAjax("GET", "http://localhost:8080/API_REST/rest/products-service/pegi", successPegi, undefined);
	return false;
}

function submitCreatePegi()
{	
	var form = document.forms["create"];
	name = form["name"].value;
	url = form["url"].value;
	
	form["name"].className ="";
	form["url"].className ="";
	
	success = document.getElementById("create-success");
	success.innerHTML = "";
	
	failure = document.getElementById("create-error");
	failure.innerHTML = "";
	
	callProtectedWebServiceByAjax("POST", "http://localhost:8080/API_REST/rest/products-service/pegi/"+name+"/"+encodeURIComponent(url), getCookie("username"), getCookie("password"),successCreatePegi, failureCreatePegi);
	return false;
}


function submitUpdatePegi()
{	
	var form = document.forms["update"];
	name = form["name"].value;
	url = form["url"].value;
	id = form["id_pegi"].value;
	
	form["name"].className ="";
	form["url"].className ="";
	
	success = document.getElementById("update-success");
	success.innerHTML = "";
	
	failure = document.getElementById("update-error");
	failure.innerHTML = "";
	
	callProtectedWebServiceByAjax("PUT", "http://localhost:8080/API_REST/rest/products-service/pegi/"+id+"/"+name+"/"+encodeURIComponent(url), getCookie("username"), getCookie("password"),successUpdatePegi, failureUpdatePegi);
	return false;
}



function successPegi(response)
{
	var pegis = JSON.parse(response);

	div = document.getElementById("pegi");	
	div.innerHTML = '';
	for(i=0; i<pegis.length;++i)
	{
		div.innerHTML += '<tr onclick="selectUpdatePegi('+pegis[i].id+', \''+pegis[i].name+'\','+'\''+pegis[i].img+'\''+')" role="button">'
			+'<td>'+pegis[i].id+'</td>'
			+'<td><img src="'+decodeURIComponent(pegis[i].img)+'" width="50" alt="pegi" /></td>'
			+'<td>'+pegis[i].name+'</td>'
			+'</tr>';
	}
}

function successCreatePegi(response)
{
	success = document.getElementById("create-success");
	success.innerHTML = "<p>Modification effectuée</p>";
	loadPegi();
}

function successUpdatePegi(response)
{
	success = document.getElementById("update-success");
	success.innerHTML = "<p>Modification effectuée</p>";
	loadPegi();
}

function failureUpdatePegi(response)
{
	failure = document.getElementById("update-error");
	failure.innerHTML = "<p>Une erreur est survenue lors de la creation </p>";
	
	if(response!=undefined)
	{
		var form = document.forms["update"];
		form["name"].className += " has-error";
	}
}

function selectUpdatePegi(id, name, url)
{
	var form = document.forms["update"];
	
	//alert(id+" "+name);
	form["name"].value = name;
	form["url"].value = decodeURIComponent(url);
	form["id_pegi"].value = id;

}


function failureCreatePegi()
{
	failure = document.getElementById("create-error");
	failure.innerHTML = "<p>Une erreur est survenue lors de la creation </p>";
	
	if(response!=undefined)
	{
		var form = document.forms["update"];
		form["name"].className += " has-error";
	}
}

