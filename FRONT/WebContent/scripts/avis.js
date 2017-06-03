var idp = undefined;
function submitNewAvis(id)
{	idp = id;
	var title = document.getElementById("title");
	var note = document.getElementById("note");
	var content = document.getElementById("content");
	
	title.className ="";
	note.className ="";
	content.className="";
	
	callProtectedWebServiceByAjax("POST", "http://localhost:8080/API_REST/rest/products-service/reviews/"+title.value+"/"+note.value+"/"+content.value+"/"+id, getCookie("username"), getCookie("password"), successNewAvis, failureNewAvis);
	return false;
}



function successNewAvis(response)
{
	setTimeout(window.location='product.jsp?id='+idp,5000);
}

function failure(response)
{
	var title = document.getElementById("title");
	var note = document.getElementById("note");
	var content = document.getElementById("content");
	
	failure = document.getElementById("error");
	failure.innerHTML = "Vous ne pouvez pas ajouter de nouvel avis";
	
	title.className ="has-error";
	note.className ="has-error";
	content.className="has-error";
}





