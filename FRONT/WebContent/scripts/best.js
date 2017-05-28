function loadBest()
{	
	callWebServiceByAjax("GET", "http://localhost:8080/API_REST/rest/products-service/products/best", successBest, undefined);
	return false;
}



function successBest(response)
{
	var products = JSON.parse(response);

	productsDiv = document.getElementById("best");
	productsDiv.innerHTML = '';
	for(i=0; i<products.length;++i)
	{
		productsDiv.innerHTML += '<div class="col-md-4">'
			+'<h3>'+products[i].videogame.name+'</h3>'
			+'<em>'+products[i].console.name+'</em>'
			+'<p><img width="100%" src="'+products[i].pictures[0].img+'"/></p>'
			+'<p><a class="btn btn-secondary" href="product.jsp?id='+products[i].id+'" role="button">En savoir +</a></p></div>';
	}
}



