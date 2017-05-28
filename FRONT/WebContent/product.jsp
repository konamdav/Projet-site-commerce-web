<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="favicon.ico">

<title>Produit</title>

<!-- Bootstrap core CSS -->
<link href="dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="album.css" rel="stylesheet">
<script type="text/javascript" src="scripts/script.js"></script>
<script type="text/javascript" src="scripts/product.js"></script>
<script type="text/javascript" src="scripts/panier.js"></script>
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
			<h1 id="name"></h1>
			
		</div>
		<br>
		<div class="row">
			<div class="col md">
			<p class="lead text-muted">Description du produit</p>
				<div align="center" class="table-responsive text-center"
					style="margin: auto; max-width: 500px">
					<table class="table table-striped">
						<tbody>
						<tr>
								<th>Genres</th>
								<td ><ul id="genres"></ul></td>
							</tr>
							<tr>
								<th>Editeur</th>
								<td id="publisher"></td>
							</tr>
							<tr>
								<th>Console</th>
								<td id="console">
							</tr>

							<tr>
								<th>Date</th>
								<td id="date">..</td>
							</tr>
							<tr>
								<th>Prix</th>
								<td id="price"></td>
							</tr>
							<tr>
								<th>PEGI</th>
								<td id="pegi"></td>
							</tr>
							<tr>
								<th>Note</th>
								<td id="note"></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<br>

		<p>
			<a href="" onclick="addPanier(<%=request.getParameter("id")%>);"
				class="btn btn-primary">Ajouter au panier</a>
			<%
						if (request.getSession().getAttribute("USER") != null) {
					%>
			<a href="avis.jsp?id=<%=request.getParameter("id")%>"
				class="btn btn-secondary">Rédiger un avis</a>
			<%
						}
					%>
		</p>
		</div>
	</section>

	<div class="album text-muted">
		<div id="pictures" class="container"></div>
	</div>

	<div class="container" style="">
		<br>
		<h2>Avis</h2>
		<hr>
		<br>
		<div id="reviews"></div>
	</div>
	<script>
		window.onload = function() {
			loadNbPanier();
			loadProduct(
	<%=request.getParameter("id")%>
		);

		}
	</script>
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
