function loadReviews()
{	
	callProtectedWebServiceByAjax("GET", "http://localhost:8080/API_REST/rest/users-service/user/reviews", getCookie("username"), getCookie("password"), successReviews, undefined);
	return false;
}



function successReviews(response)
{
	var reviews = JSON.parse(response);

	div = document.getElementById("commandes");	
	for(i=0; i<reviews.length;++i)
	{
		div.innerHTML += '<tr>'
			+'<td>'+reviews[i].id+'</td>'
			+'<td>'+reviews[i].productName+'</td>'
			+'<td>'+reviews[i].title+'</td>'
			+'</tr>';
	}

}



