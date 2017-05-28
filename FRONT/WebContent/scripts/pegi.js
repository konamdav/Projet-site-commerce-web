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
	callProtectedWebServiceByAjax("POST", "http://localhost:8080/API_REST/rest/products-service/pegi/"+name+"/"+encodeURIComponent(url), getCookie("username"), getCookie("password"),successCreatePegi, undefined);
	return false;
}


function submitUpdatePegi()
{	
	var form = document.forms["update"];
	name = form["name"].value;
	url = form["url"].value;
	id = form["id_pegi"].value;
	
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
	loadPegi();
}

function successUpdatePegi(response)
{
	loadPegi();
}

function failureUpdatePegi(response)
{
	
}

function selectUpdatePegi(id, name, url)
{
	var form = document.forms["update"];
	
	//alert(id+" "+name);
	form["name"].value = name;
	form["url"].value = decodeURIComponent(url);
	form["id_pegi"].value = id;
}



