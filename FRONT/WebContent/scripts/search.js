function searchSimple(name)
{	json = ' { "videogame" : "'+name+'", "publisher" : "", "console" : "", "genres" : [], "tags" : [], "pegis" : [] }';
json2 = ' { "videogame" : "", "publisher" : "'+name+'", "console" : "", "genres" : [], "tags" : [], "pegis" : [] }';
json3 = ' { "videogame" : "", "publisher" : "", "console" : "'+name+'", "genres" : [], "tags" : [], "pegis" : [] }';
callWebServiceByAjaxWithBody("POST", "http://localhost:8080/API_REST/rest/products-service/products/research", json, successSearch, failureSearch);
callWebServiceByAjaxWithBody("POST", "http://localhost:8080/API_REST/rest/products-service/products/research", json2, successSearch, failureSearch);
callWebServiceByAjaxWithBody("POST", "http://localhost:8080/API_REST/rest/products-service/products/research", json3, successSearch, failureSearch);
return false;
}



function successSearch(response)
{
	var products = JSON.parse(response);

	productsDiv = document.getElementById("products");	
	for(i=0; i<products.length;++i)
	{
		div = document.getElementById('res_'+products[i].id);
		if(div == undefined)
		{
			productsDiv.innerHTML += '<tr id="res_'+products[i].id+'">'
			+'<td>'+products[i].id+'</td>'
			+'<td><a href="product.jsp?id='+products[i].id+'">'+products[i].videogame.name+'</a></td>'
			+'</tr>';

		}
	}

}

function failureSearch(response)
{

}


