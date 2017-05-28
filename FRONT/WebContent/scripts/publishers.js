function loadPublishers()
{	
	callWebServiceByAjax("GET", "http://localhost:8080/API_REST/rest/products-service/publishers", successPublishers, undefined);
	return false;
}

function submitCreatePublisher()
{	
	var form = document.forms["create"];
	name = form["name"].value;
	
	callProtectedWebServiceByAjax("POST", "http://localhost:8080/API_REST/rest/products-service/publishers/"+name, getCookie("username"), getCookie("password"),successCreatePublisher, undefined);
	return false;
}


function submitUpdatePublisher()
{	
	var form = document.forms["update"];
	name = form["name"].value;
	id = form["id_genre"].value;
	
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
	loadPublishers();
}

function successUpdatePublisher(response)
{
	loadPublishers();
}

function failureUpdatePublisher(response)
{
	
}

function selectUpdatePublisher(id, name)
{
	var form = document.forms["update"];
	
	//alert(id+" "+name);
	form["name"].value = name;
	form["id_genre"].value = id;
}



