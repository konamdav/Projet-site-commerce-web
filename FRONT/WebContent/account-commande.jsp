<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="favicon.ico">

<title>Mon compte</title>

<!-- Bootstrap core CSS -->
<link href="dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="dashboard.css" rel="stylesheet">
<script type="text/javascript" src="scripts/script.js"></script>
<script type="text/javascript" src="scripts/panier.js"></script>
<script type="text/javascript" src="scripts/commande.js"></script>
</head>

<body>

	<jsp:include page="menu.jsp">
		<jsp:param name="curr" value="COMPTE" />
	</jsp:include>

	<jsp:include page="menu-account.jsp">
		<jsp:param name="curr" value="COMMANDES" />
	</jsp:include>

	<main class="col-sm-9 offset-sm-3 col-md-10 offset-md-2 pt-3">
	<h1>Mon compte</h1>
	<%if(request.getParameter("id")!=null) {%>
	<section class="row  placeholders">
		<div class="col-4">
			<h2>Récapitulatif</h2>
			<hr>
			<div class="table-responsive">
				<table class="table table-striped">
					<tbody>
						<tr>
							<th>Nombre d'articles</th>
							<td id="nbCommande"></td>
						</tr>
						<tr>
							<th>Total TTC</th>
							<td id="total"></td>
						</tr>


					</tbody>
				</table>
			</div>

		</div>
		<div class="col-6">
			<h2>Ma commande</h2>
			<div class="table-responsive">
				<table class="table table-striped">
					<thead>
						<tr>
							<th></th>
							<th>Produit</th>
							<th>Prix unitaire</th>
							<th>Quantité</th>
						</tr>
					</thead>

					<tbody id="products">

					</tbody>
				</table>
			</div>
		</div>
	</section>
	<script>
		window.onload = function() {
			loadCommande(<%= request.getParameter("id") %>);

		}
	</script> <% } %> </main>
	</div>
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
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
