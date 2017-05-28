function loadCommandes()
{	
	callProtectedWebServiceByAjax("GET", "http://localhost:8080/API_REST/rest/users-service/user/commands", getCookie("username"), getCookie("password"), successCommands, undefined);
	return false;
}



function successCommands(response)
{
	var commandes = JSON.parse(response);

	commandesDiv = document.getElementById("commandes");	
	for(i=0; i<commandes.length;++i)
	{
		commandesDiv.innerHTML += '<tr>'
			+'<td><a href="account-commande.jsp?id='+commandes[i].id+'">'+commandes[i].id+'</a></td>'
			+'<td><a href="account-commande.jsp?id='+commandes[i].id+'">'+commandes[i].date_command+'</a></td>'
			+'<td><a href="account-commande.jsp?id='+commandes[i].id+'">'+commandes[i].count+'</a></td>'
			+'<td><a href="account-commande.jsp?id='+commandes[i].id+'">'+commandes[i].total+'</a></td>'
			+'</tr>';
	}

}



