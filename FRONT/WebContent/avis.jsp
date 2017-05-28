<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="favicon.ico">

<title>Avis</title>

<!-- Bootstrap core CSS -->
<link href="dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="album.css" rel="stylesheet">
<script type="text/javascript" src="scripts/script.js"></script>
<script type="text/javascript" src="scripts/panier.js"></script>
<script type="text/javascript" src="scripts/avis.js"></script>
</head>

<body>



	<jsp:include page="menu.jsp">
		<jsp:param name="curr" value="PRODUCTS" />
	</jsp:include>

	<%
		if (request.getParameter("id") != null) {
	%>

	<section class="jumbotron text-center">
		<div class="container">
			<h1 >Ajout d'un avis</h1>
			<hr>
		</div>
		<br>

		<div class="container ">
			<div class="col text-center">
				<form align="center" class ="text-center" action="" method="post"
					onsubmit="return submitNewAvis(<%=request.getParameter("id")%>);">
					<div style="width:19%; display:inline-block;">Titre :</div> <input style="width:80%" id="title" name="title" type="text"> 
					<br><br>
					<div style="width:20%; display:inline-block;">Note :</div><select style="width:80%" id="note">
						<option value="0">0</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select> <br> <br>
					<textarea style="height:100px;width:100%" id="content"></textarea><br><br>
					<button type="submit" class="btn btn-primary">Valider</button>
					
				</form>

			</div>
		</div>
	</section>

	<%
		}
	%>

	<div class="container">
		<footer>
			<p>&copy; Company 2017</p>
		</footer>
	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"
		integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n"
		crossorigin="anonymous"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="assets/js/vendor/jquery.min.js"><\/script>')
	</script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
		integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
		crossorigin="anonymous"></script>
	<script src="dist/js/bootstrap.min.js"></script>
</body>
</html>
