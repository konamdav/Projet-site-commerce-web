function loadCommande(id)
{	
	callProtectedWebServiceByAjax("GET", "http://localhost:8080/API_REST/rest/users-service/user/commands/"+id, getCookie("username"), getCookie("password"), successCommande, undefined);
	return false;
}



function successCommande(response)
{	
	var commande = JSON.parse(response);
	total = document.getElementById("total");
	total.innerHTML = commande.total;
	
	count = document.getElementById("nbCommande");
	count.innerHTML = commande.count;

	line = document.getElementById("products");
	line.innerHTML ="";
	for(i=0; i<commande.linecommands.length;++i)
	{
		line.innerHTML += '<tr><td><img width="150" class="img-fluid" src="'+commande.linecommands[i].product.pictures[0].img+'"/>'
		+'</td><td>'+commande.linecommands[i].product.videogame.name+'</td><td>'+commande.linecommands[i].price+'</td><td><form>'
		+commande.linecommands[i].quantity
		+'</form></td>';


	}


}



