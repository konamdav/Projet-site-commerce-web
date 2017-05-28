<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="product.jsp?id=1">
<meta name="author" content="product.jsp?id=1">
<link rel="icon" href="favicon.ico">

<title>Produits</title>

<!-- Bootstrap core CSS -->
<link href="dist/css/bootstrap.min.css" rel="stylesheet">


<!-- Custom styles for this template -->
<link href="jumbotron.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="grid.css" rel="stylesheet">
<script type="text/javascript" src="scripts/script.js"></script>
<script type="text/javascript" src="scripts/search.js"></script>
<script type="text/javascript" src="scripts/panier.js"></script>
</head>

<body>
		<jsp:include page="menu.jsp">
		<jsp:param name="curr" value="PRODUCTS" />
		</jsp:include>
	<br>
	<br>
	
	<%
		if (request.getParameter("name") != null) {
	%>
	<div class="container">
		<h1>Résultats</h1>
		<div class="row">
			<div class="col">
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>#</th>
								<th>Produit</th>
							</tr>
						</thead>

						<tbody id="products">
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<footer>
			<p>&copy; Company 2017</p>
		</footer>


	</div>
	<!-- /container -->
	<script>
		window.onload = function() {
			searchSimple("<%= request.getParameter("name") %>");
			
		}
	</script>
	<%
		}
	%>
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"
		integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n"
		crossorigin="anonymous"></script>
	<script>window.jQuery || document.write('<script src="assets/js/vendor/jquery.min.js"><\/script>')</script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
		integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
		crossorigin="anonymous"></script>
	<script src="dist/js/bootstrap.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
