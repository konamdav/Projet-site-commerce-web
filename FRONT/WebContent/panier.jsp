<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="favicon.ico">

<title>Panier</title>

<!-- Bootstrap core CSS -->
<link href="dist/css/bootstrap.min.css" rel="stylesheet">


<!-- Custom styles for this template -->
<link href="jumbotron.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="grid.css" rel="stylesheet">

<script type="text/javascript" src="scripts/script.js"></script>
<script type="text/javascript" src="scripts/product.js"></script>
<script type="text/javascript" src="scripts/panier.js"></script>
</head>

<body>
	<jsp:include page="menu.jsp">
		<jsp:param name="curr" value="PANIER" />
	</jsp:include>
	<br>
	<br>
	<div class="container">
		
		<h1>Gestion de votre panier</h1>
		<div class="row">
			<div class="col-md-5">
			
						<h2>Récapitulatif</h2>
						<div class="table-responsive">
							<table class="table table-striped">
								<tbody>
									<tr>
										<th>Nombre d'articles</th>
										<td id="nbpanier2"></td>
									</tr>
									<tr>
										<th>Total TTC</th>
										<td id="total"></td>
									</tr>


								</tbody>
							</table>
						</div>

						<%
							if (request.getSession().getAttribute("USER") != null) {
						%>
						<p>
							<a class="btn btn-primary btn-lg" href="#" role="button"
								onclick="payPanier();">Payer la commande</a>
						</p>
						<%
							}
							else
							{
							 %>
						<p>
							<a class="btn btn-primary btn-lg" href="signin.jsp" role="button">Connexion</a>
							<br>
							<em>Un compte est necessaire pour valider le panier</em>
						</p>
						<%
							}
						%>
					
				
			</div>
			<div class="col s12 m4 l8">
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th></th>
								<th>Produit</th>
								<th>Prix</th>
								<th>Quantité</th>
								<th></th>
							</tr>
						</thead>

						<tbody id="products">
						</tbody>
					</table>
				</div>
			</div>



		</div>

		<footer>
			<p>&copy; David KONAM & Clément PASSOT | SR03 | 2017</p>
		</footer>


	</div>
	<!-- /container -->


	<script>
		window.onload = function() {
			loadNbPanier();
			loadPanier();

		}
	</script>


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
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
