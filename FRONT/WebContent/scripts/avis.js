var idp = undefined;
function submitNewAvis(id)
{	idp = id;
	var title = document.getElementById("title");
	var note = document.getElementById("note");
	var content = document.getElementById("content");
	
	callProtectedWebServiceByAjax("POST", "http://localhost:8080/API_REST/rest/products-service/reviews/"+title.value+"/"+note.value+"/"+content.value+"/"+id, getCookie("username"), getCookie("password"), successNewAvis, undefined);
	return false;
}



function successNewAvis(response)
{
	setTimeout(window.location='product.jsp?id='+idp,5000);
}



