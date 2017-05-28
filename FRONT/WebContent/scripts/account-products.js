function loadVideogames()
{	
	callWebServiceByAjax("GET", "http://localhost:8080/API_REST/rest/products-service/videogames", successVideogames, undefined);
	return false;
}



function loadProducts()
{	
	callWebServiceByAjax("GET", "http://localhost:8080/API_REST/rest/products-service/products", successProducts, undefined);
	return false;
}

function loadConsoles()
{	
	callWebServiceByAjax("GET", "http://localhost:8080/API_REST/rest/products-service/consoles", successConsoles, undefined);
	return false;
}

function successProducts(response)
{
	var products = JSON.parse(response);
	div = document.getElementById("products");	
	div.innerHTML = '';
	for(i=0; i<products.length;++i)
	{
		div.innerHTML += '<tr onclick="selectUpdateProduct('+products[i].id+')" role="button">'
		+'<td>'+products[i].id+'</td>'
		+'<td>'+products[i].videogame.name+'/'+products[i].console.name+'</td>'
		+'</tr>';
	}
}

function successConsoles(response)
{
	var consoles = JSON.parse(response);
	div =  document.forms["create"]["id_console"];
	div.innerHTML = '';
	div2 =  document.forms["update"]["id_console"];
	div2.innerHTML = '';
	for(i=0; i<consoles.length;++i)
	{
		div.innerHTML += '<option value="'+consoles[i].id+'">'+consoles[i].name+'</option>';
		div2.innerHTML += '<option value="'+consoles[i].id+'">'+consoles[i].name+'</option>';
	}
}
function successVideogames(response)
{
	var videogames = JSON.parse(response);

	div =  document.forms["create"]["id_videogame"];
	div.innerHTML = '';
	div2 =  document.forms["update"]["id_videogame"];
	div2.innerHTML = '';
	for(i=0; i<videogames.length;++i)
	{
		div.innerHTML += '<option value="'+videogames[i].id+'">'+videogames[i].name+'</option>';
		div2.innerHTML += '<option value="'+videogames[i].id+'">'+videogames[i].name+'</option>';

	}
}

function submitCreateProduct()
{	
	var form = document.forms["create"];
	id_console = form["id_console"].value;
	id_videogame = form["id_videogame"].value;
	year = form["date_release"].value;
	price  = form["price"].value;

	callProtectedWebServiceByAjax("POST", "http://localhost:8080/API_REST/rest/products-service/products/"+id_videogame+"/"+id_console+"/"+price+"/"+year, getCookie("username"), getCookie("password"),successCreateProduct, undefined);
	return false;
}


function submitUpdateProduct()
{	
	alert("ok");
	var form = document.forms["update"];
	id_product = form["id_product"].value;
	id_console = form["id_console"].value;
	id_videogame = form["id_videogame"].value;
	date_release = form["date_release"].value;
	price  = form["price"].value;

	alert("http://localhost:8080/API_REST/rest/products-service/products/"+id_product+"/"+id_videogame+"/"+id_console+"/"+price+"/"+date_release);
	callProtectedWebServiceByAjax("PUT", "http://localhost:8080/API_REST/rest/products-service/products/"+id_product+"/"+id_videogame+"/"+id_console+"/"+price+"/"+date_release, 
			getCookie("username"), getCookie("password"),successUpdateProduct, undefined);

	return false;
}





function successCreateProduct(response)
{
	loadProducts();
}


function successUpdateProduct(response)
{
	loadProducts();
}

function failureUpdateProduct(response)
{

}

function selectUpdateProduct(id)
{
	var form = document.forms["update"];
	form["id_product"].value = id;

	callWebServiceByAjax("GET", "http://localhost:8080/API_REST/rest/products-service/products/"+id, loadUpdatedProduct, undefined);
}

function loadUpdatedProduct(response)
{
	var form = document.forms["update"];
	product = JSON.parse(response);

	form["id_console"].value = product.console.id;
	form["id_videogame"].value = product.videogame.id;

	form["date_release"].value = product.date_release;
	form["price"].value = product.price;

	pictures = document.getElementById("pictures");
	pictures.innerHTML ="";
	for(i = 0; i<product.pictures.length; ++i)
	{
		pictures.innerHTML += '<tr><td><img height="40px" src="'+decodeURIComponent(product.pictures[i].img)+'"/></td>'
		+'<td><button onclick="removePicture('+product.pictures[i].id+');" class="btn btn-secondary">Retirer</button></td></tr>';
	}


}

function addPicture()
{
	var form = document.forms["update"];
	id_product= form["id_product"].value;
	picture= form["picture"].value;

	if(picture!=undefined){
		callProtectedWebServiceByAjax("POST", "http://localhost:8080/API_REST/rest/products-service/pictures/"+encodeURIComponent(picture)+"/"+id_product, getCookie("username"), getCookie("password"),successAddPicture, undefined);
	}
}

function removePicture(id)
{

	callProtectedWebServiceByAjax("DELETE", "http://localhost:8080/API_REST/rest/products-service/pictures/"+id, getCookie("username"), getCookie("password"),successAddPicture, undefined);

	return false;
}

function successAddPicture(response)
{
	alert("successAdd");

	var form = document.forms["update"];
	id = form["id_product"].value;
	callWebServiceByAjax("GET", "http://localhost:8080/API_REST/rest/products-service/products/"+id, loadUpdatedProduct, undefined);

}

