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
<script type="text/javascript" src="scripts/videogames.js"></script>
</head>

<body>

	<jsp:include page="menu.jsp">
		<jsp:param name="curr" value="COMPTE" />
	</jsp:include>

	<jsp:include page="menu-account.jsp">
		<jsp:param name="curr" value="ADD_PUBLISHER" />
	</jsp:include>

	<main class="col-sm-9 offset-sm-3 col-md-8 offset-md-2 pt-3">
	<h1>Mon compte</h1>
	<div class="row">

		<div class="col-md-6">
			<h2>Liste des jeux videos</h2>
			<div class="table-responsive">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>#</th>
							<th>Nom</th>
						</tr>
					</thead>
					<tbody id="videogames">
					</tbody>
				</table>
			</div>
		</div>
		<div class="col-md">
			<h2>Création</h2>
			<form id="create" action="" method="post"
				onsubmit="return submitCreateVideogame();">
				<div style="width: 19%; display: inline-block;">Nom :</div>
				<input style="width: 80%" id="name" name="name" type="text">
				<br>
				<br>
				<div style="width: 19%; display: inline-block;">Editeur :</div>
				<select style="height: 30px; width: 80%" id="id_publisher"
					name="id_publisher"></select> <br>
				<br>
				<button type="submit" class="btn btn-primary">Créer</button>
			</form>
			<hr>
			<h2>Modification</h2>
				<form id="update" action="" method="post"
				onsubmit="return submitUpdateVideogame();">
				<input type="hidden" name="id_videogame" id="id_videogame" />
				<div style="width: 19%; display: inline-block;">Nom :</div>
				<input style="width: 80%" id="name" name="name" type="text">
				<br>
				<br>
				<div style="width: 19%; display: inline-block;">Editeur :</div>
				<select style="height: 30px; width: 80%" id="id_publisher2"
					name="id_publisher2"></select> <br>
				<br>
				
				<hr>
				<div class="row">
					<div class="col-md-6">
						<div class="table-responsive" style="max-height:200px;">
							<table  class="table table-striped">
								<tbody id="vg_genres">
									
								</tbody>
							</table>
						</div>
					</div>
					<div class="col-md-6">
						<div style="width: 28%; display: inline-block;">Genre </div>
						<select style="height: 30px; width: 70%" id="id_genre"
							name="id_genre"></select> <br>
						<br>
						<button  onclick="addGenre()" class="btn btn-secondary">Ajout</button>
					</div>
				</div>
				<hr>
					<div class="row">
					<div class="col-md-6">
						<div style="max-height:200px;" class="table-responsive">
							<table class="table table-striped">
								<tbody id="vg_pegi">
									
								</tbody>
							</table>
						</div>
					</div>
					<div class="col-md-6">
						<div style="width: 28%; display: inline-block;">PEGI :</div>
						<select style="height: 30px; width: 70%" id="id_pegi"
							name="id_pegi"></select> <br>
						<br>
						<button  onclick="addPegi()" class="btn btn-secondary">Ajout</button>
					</div>
				</div>
				<hr>
				<div class="row">
					<div class="col-md-6">
						<div style="max-height:200px;" class="table-responsive">
							<table class="table table-striped">
								<tbody id="vg_tags">
									
								</tbody>
							</table>
						</div>
					</div>
					<div class="col-md-6">
						<div style="width: 28%; display: inline-block;">Tag :</div>
						<select style="height: 30px; width: 70%" id="id_tag"
							name="id_tag"></select> <br>
						<br>
						<button onclick="addTag()" class="btn btn-secondary">Ajout</button>
					</div>
				</div>
				
				<button type="submit" class="btn btn-primary">Modifier</button>
			</form>



		</div>
	</div>
	<section class="row  placeholders"></section>
	<script>
		window.onload = function() {
			loadVideogames();
			loadPublishers();
			loadGenres();
			loadTags();
			loadPegi();

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
