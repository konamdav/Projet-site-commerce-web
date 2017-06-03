<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="./favicon.ico">

<title>GameShop - Video Game - SR03</title>

<!-- Bootstrap core CSS -->
<link href="dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="jumbotron.css" rel="stylesheet">
<script type="text/javascript" src="scripts/script.js"></script>
<script type="text/javascript" src="scripts/panier.js"></script>
<script type="text/javascript" src="scripts/discovery.js"></script>
<script type="text/javascript" src="scripts/best.js"></script>
</head>

<body>

	<jsp:include page="menu.jsp">
		<jsp:param name="curr" value="ACCUEIL" />
	</jsp:include>

	<!-- Main jumbotron for a primary marketing message or call to action -->
	<div class="jumbotron">
		<div class="container">
			<img width="200" style="float:right;" src="logo.png" />
			<h1 class="display-3">Game Shop</h1>
			<p>Bienvenue sur l'espace de vente de jeux vidéos</p>
			<p>
				<a class="btn btn-primary btn-lg" href="products.jsp" role="button">Découvrir
					nos produits</a>
			</p>
		</div>
	</div>

	<div class="container">
		<h2 class="display-5">Les mieux notés</h2>
		<hr>
		<div id="best" class="row"></div>

		<h2 class="display-5">Découverte</h2>
		<hr>
		<div id="discovery" class="row"></div>
		<hr>

		<footer>
			<p>&copy; David KONAM & Clément PASSOT | SR03 | 2017</p>
		</footer>
	</div>
	<!-- /container -->
	<script>
		window.onload = function() {
			loadBest();
			loadDiscovery();
			

		}
	</script>

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
