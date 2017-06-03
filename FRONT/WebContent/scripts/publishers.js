function loadPublishers()
{	
	callWebServiceByAjax("GET", "http://localhost:8080/API_REST/rest/products-service/publishers", successPublishers, undefined);
	return false;
}

function submitCreatePublisher()
{	
	success = document.getElementById("update-success");
	success.innerHTML = "";
	
	failure = document.getElementById("update-error");
	failure.innerHTML = "";
	
	var form = document.forms["create"];
	name = form["name"].value;
	

	form["name"].className ="";
	
	
	callProtectedWebServiceByAjax("POST", "http://localhost:8080/API_REST/rest/products-service/publishers/"+name, getCookie("username"), getCookie("password"),successCreatePublisher, failureCreatePublisher);
	return false;
}


function submitUpdatePublisher()
{	
	success = document.getElementById("update-success");
	success.innerHTML = "";
	
	failure = document.getElementById("update-error");
	failure.innerHTML = "";
	

	
	var form = document.forms["update"];
	name = form["name"].value;
	id = form["id_publisher"].value;
	
	form["name"].className ="";
	
	callProtectedWebServiceByAjax("PUT", "http://localhost:8080/API_REST/rest/products-service/publishers/"+id+"/"+name, getCookie("username"), getCookie("password"),successUpdatePublisher, failureUpdatePublisher);
	return false;
}



function successPublishers(response)
{
	var publishers = JSON.parse(response);

	div = document.getElementById("publishers");	
	div.innerHTML = '';
	for(i=0; i<publishers.length;++i)
	{
		div.innerHTML += '<tr onclick="selectUpdatePublisher('+publishers[i].id+', \''+publishers[i].name+'\')" role="button">'
			+'<td>'+publishers[i].id+'</td>'
			+'<td>'+publishers[i].name+'</td>'
			+'</tr>';
	}
}

function successCreatePublisher(response)
{
	success = document.getElementById("create-success");
	success.innerHTML = "<p>Formulaire validé avec succès</p>";
	loadPublishers();
}

function successUpdatePublisher(response)
{
	success = document.getElementById("update-success");
	success.innerHTML = "<p>Formulaire validé avec succès</p>";
	loadPublishers();
}

function failureCreatePublisher(response)
{
	failure = document.getElementById("create-error");
	failure.innerHTML = "<p>Une erreur est survenue</p>";
	
	if(response!=undefined)
	{
		var form = document.forms["create"];
		form["name"].className += " has-error";
	}
}

function failureUpdatePublisher(response)
{
	failure = document.getElementById("update-error");
	failure.innerHTML = "<p>Une erreur est survenue lors de la validation </p>";
	
	if(response!=undefined)
	{
		var form = document.forms["update"];
		form["name"].className += " has-error";
	}
}


function selectUpdatePublisher(id, name)
{
	var form = document.forms["update"];
	
	//alert(id+" "+name);
	form["name"].value = name;
	form["id_publisher"].value = id;
}



