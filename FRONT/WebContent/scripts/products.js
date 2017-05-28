function loadProducts()
{	
	callWebServiceByAjax("GET", "http://localhost:8080/API_REST/rest/products-service/products/", successGetProduct, failureGetProduct);
	return false;
}



function successGetProduct(response)
{
	var products = JSON.parse(response);

	productsDiv = document.getElementById("products");	
	for(i=0; i<products.length;++i)
	{
		productsDiv.innerHTML += '<tr>'
			+'<td>'+products[i].id+'</td>'
			+'<td><a href="product.jsp?id='+products[i].id+'">'+products[i].videogame.name+'</a></td>'
			+'</tr>';
	}

}

function failureGetProduct(response)
{

}


