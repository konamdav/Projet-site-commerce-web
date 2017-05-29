function loadProduct(id)
{	
	console.log("http://localhost:8080/API_REST/rest/products-service/products/"+id);
	callWebServiceByAjax("GET", "http://localhost:8080/API_REST/rest/products-service/products/"+id, successGetProduct, failureGetProduct);
	return false;
}



function successGetProduct(response)
{
	var product = JSON.parse(response);
	
	
	
	productname = document.getElementById("name");
	publisher = document.getElementById("publisher");
	date = document.getElementById("date");
	console = document.getElementById("console");
	price = document.getElementById("price");
	pictures = document.getElementById("pictures");
	avis = document.getElementById("reviews");


	publisher.innerHTML = product.videogame.publisher.name;
	console.innerHTML = product.console.name;
	date.innerHTML = product.date_release;
	price.innerHTML = product.price;
	productname.innerHTML = product.videogame.name;
	
	
	for(i=0; i<product.pictures.length;++i)
	{
		pictures.innerHTML += '<div class="card">'
			+'<img src="'+product.pictures[i].img+'" height="100" class="img-fluid" alt="'+product.pictures[i].name+'"/>'
			+'<div class="mask"></div>'
			+'</div>';
	}
	
	for(j=0; j<product.reviews.length;++j)
	{
		user = JSON.parse(product.reviews[j].user);
		
		avis.innerHTML +='<div class="row">'
		+'<div class="col md 4 text-center">'
		+'<img style="width: 100px;" src="user.png" /><br>'
		+'<div id="user'+product.reviews[j].id_user+'">'+user.username+'</div>'
		+'</div>'
		+'<div class="col md 8">'
		+'<b>'+product.reviews[j].title+'</b><br><em>'+product.reviews[j].note+'/10</em>'
		+'<p>'+product.reviews[j].content+'</p>'
		+'</div></div><hr><br>';
		
	}
	
	pegi = document.getElementById("pegi");
	for(i=0; i<product.videogame.classifications.length;++i)
	{
		pegi.innerHTML += ''
			+'<img height="30px" src="'+product.videogame.classifications[i].img+'" alt="'+product.videogame.classifications[i].name+'"/>'
			+'';
	}
	
	genres = document.getElementById("genres");
	for(i=0; i<product.videogame.genres.length;++i)
	{
		genres.innerHTML += ''
			+'<li>"'+product.videogame.genres[i].name+'</li>'
			+'';
	}

	document.getElementById("note").innerHTML= product.note;

}

function failureGetProduct(response)
{
	
}


