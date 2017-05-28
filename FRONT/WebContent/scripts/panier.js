function loadNbPanier()
{	
	callWebServiceByAjax("GET", "http://localhost:8080/API_REST/rest/users-service/user/panier/nb", successPanierNb, failurePanierNb);
	return false;
}


function loadPanier()
{	
	callWebServiceByAjax("GET", "http://localhost:8080/API_REST/rest/users-service/user/panier/nb", successPanierNb2, failurePanierNb2);
	callWebServiceByAjax("GET", "http://localhost:8080/API_REST/rest/users-service/user/panier/", successPanier, failurePanier);
	return false;
}


function successPanier(response)
{	
	var panier = JSON.parse(response);

	total = document.getElementById("total");
	total.innerHTML = panier.total;

	line = document.getElementById("products");
	line.innerHTML ="";
	for(i=0; i<panier.linecommands.length;++i)
	{
		line.innerHTML += '<tr><td><img width="150" class="img-fluid" src="'+panier.linecommands[i].product.pictures[0].img+'"/>'
		+'</td><td>'+panier.linecommands[i].product.videogame.name+'</td><td>'+panier.linecommands[i].price+'</td><td><form>'
		+'<input id="qt_product_'+panier.linecommands[i].product.id+'" style="width: 50px" value="'+panier.linecommands[i].quantity+'" />'
		+'</form></td>'
		+'<td><p><a align="right" class="btn btn-secondary" href="#" role="button" onclick="updateProduct('+panier.linecommands[i].product.id+');" >Modifier</a></p><p><a align="right" class="btn btn-secondary" href="#" role="button" onclick="deleteProduct('+panier.linecommands[i].product.id+');">Retirer</a></p></form></td></tr>';

		//loadProductPanier(panier.linecommands[i].id_product);

	}


}

function failurePanier(response)
{

}


function successPanierNb(response)
{	
	var nb = document.getElementById("nbpanier");
	nb.innerHTML = response;
}

function failurePanierNb(response)
{
	var nb = document.getElementById("nbpanier");
	nb.innerHTML = "0";
}

function successPanierNb2(response)
{	
	var nb = document.getElementById("nbpanier2");
	nb.innerHTML = response;
}

function failurePanierNb2(response)
{
	var nb = document.getElementById("nbpanier2");
	nb.innerHTML = "0";
}


function addPanier(id)
{	
	callWebServiceByAjax("POST", "http://localhost:8080/API_REST/rest/users-service/user/panier/add/"+id+"/1", successAdd, failureAdd);
	return false;
}

function failureAdd(response)
{	
	return false;
}

function successAdd(id)
{	
	loadNbPanier();
	return false;
}


function deleteProduct(id)
{	
	callWebServiceByAjax("DELETE", "http://localhost:8080/API_REST/rest/users-service/user/panier/"+id, successDelete, failureDelete);
	return false;
}

function failureDelete(response)
{	
	return false;
}

function successDelete(response)
{	
	loadNbPanier()
	loadPanier();
	return false;
}

function updateProduct(id)
{	
	quantity = document.getElementById("qt_product_"+id);
	qt = quantity.value;
	callWebServiceByAjax("PUT", "http://localhost:8080/API_REST/rest/users-service/user/panier/"+id+"/"+qt, successUpdate, failureUpdate);
	return false;
}

function failureUpdate(response)
{	
	return false;
}

function successUpdate(response)
{	
	console.log(response);
	loadPanier();
	loadNbPanier()
	return false;
}



function payPanier()
{	
	alert("pay ");
	callProtectedWebServiceByAjax("PUT", "http://localhost:8080/API_REST/rest/users-service/user/panier/pay", getCookie("username"), getCookie("password"), successPay, failurePay);
	return false;
}

function failurePay(response)
{	
	alert("fail pay");
	return false;
}

function successPay(response)
{	
	loadNbPanier()
	loadPanier();
	return false;
}

