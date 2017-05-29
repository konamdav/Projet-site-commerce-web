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
<script type="text/javascript" src="scripts/account-products.js"></script>
</head>

<body>

	<jsp:include page="menu.jsp">
		<jsp:param name="curr" value="COMPTE" />
	</jsp:include>

	<jsp:include page="menu-account.jsp">
		<jsp:param name="curr" value="PRODUCTS" />
	</jsp:include>

	<main class="col-sm-9 offset-sm-3 col-md-8 offset-md-2 pt-3">
	<h1>Mon compte</h1>
	<div class="row">

		<div class="col-md-6">
			<h2>Liste des produits</h2>
			<div class="table-responsive">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>#</th>
							<th>Nom</th>
						</tr>
					</thead>
					<tbody id="products">
					</tbody>
				</table>
			</div>
		</div>
		<div class="col-md">
			<h2>Création</h2>
			<form id="create" action="" method="post"
				onsubmit="return submitCreateProduct();">
				<div style="width: 19%; display: inline-block;">Jeu video :</div>
				<select style="height: 30px; width: 80%" id="id_videogame"
					name="id_videogame"></select> <br> <br>
				<div style="width: 19%; display: inline-block;">Console :</div>
				<select style="height: 30px; width: 80%" id="id_console"
					name="id_console"></select> <br> <br>
				<div style="width: 19%; display: inline-block;">Prix :</div>
				<input style="width: 80%" id="price" name="price" type="text">
				<br> <br>
				<div style="width: 19%; display: inline-block;">Année :</div>
				<input style="width: 80%" id="date_release" name="date_release"
					type="text"> <br> <br>

				<button type="submit" class="btn btn-primary">Créer</button>
			</form>
			<hr>
			<h2>Modification</h2>
<form id="update" action="" method="post" 	onsubmit="return submitUpdateProduct();">
   <input type="hidden" name="id_product" id="id_product" />
   <div style="width: 19%; display: inline-block;">Jeu video :</div>
   <select style="height: 30px; width: 80%" id="id_videogame"
      name="id_videogame"></select> <br> <br>
   <div style="width: 19%; display: inline-block;">Console :</div>
   <select style="height: 30px; width: 80%" id="id_console"
      name="id_console"></select> <br> <br>
   <div style="width: 19%; display: inline-block;">Prix :</div>
   <input style="width: 80%" id="price" name="price" type="text">
   <br> <br>
   <div style="width: 19%; display: inline-block;">Année :</div>
   <input style="width: 80%" id="date_release" name="date_release"
      type="text"> <br> <br>
   <div class="row">
      <div class="col-md-6">
         <div class="table-responsive" style="max-height: 200px;">
            <table class="table table-striped">
               <tbody id="pictures">
               </tbody>
            </table>
         </div>
      </div>
      <div class="col-md-6">
         <div style="width: 25%; display: inline-block;">Image :</div>
         <input style="width: 73%" id="picture" name="picture" type="text">
         <br> <br>
         <button onclick="addPicture();" class="btn btn-secondary">Ajout</button>
      </div>
   </div>
   <button type="submit" class="btn btn-primary">Modifier</button>
</form>


		</div>
	</div>
	<section class="row  placeholders"></section>
	<script>
		window.onload = function() {
			loadProducts();
			loadVideogames();
			loadConsoles();

		}
	</script>
	</div>
	</div>



	<!-- Bootstrap core JavaScript
    ================================================== --> <!-- Placed at the end of the document so the pages load faster -->
	<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"
		integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n"
		crossorigin="anonymous"></script> <script>
			window.jQuery
					|| document
							.write('<script src="assets/js/vendor/jquery.min.js"><\/script>')
		</script> <script
		src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
		integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
		crossorigin="anonymous"></script> <script
		src="dist/js/bootstrap.min.js"></script> <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
