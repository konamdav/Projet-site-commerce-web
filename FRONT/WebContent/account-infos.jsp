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
<script type="text/javascript" src="scripts/updateUser.js"></script>
</head>

<body>

	<jsp:include page="menu.jsp">
		<jsp:param name="curr" value="COMPTE" />
	</jsp:include>

<jsp:include page="menu-account.jsp">
		<jsp:param name="curr" value="PROFILE" />
	</jsp:include>


	<main class="col-sm-9 offset-sm-3 col-md-10 offset-md-2 pt-3">
	<h1>Mon compte</h1>

	<section class="row  placeholders">
		<div class="col-6">
			<h2>Modifier mes informations</h2>

			<form action="" name="updateProfile" method="post"
				onsubmit="return submitUpdateProfile();">
				<div class="form-group">
					<label for="username">Username</label> <input type="text"
						class="form-control" id="username" name="username"
						placeholder="Username">
				</div>
				<div class="form-group">
					<label for="firstname">Firstname</label> <input type="text"
						class="form-control" id="firstname" name="firstname"
						placeholder="Firstname">
				</div>
				<div class="form-group">
					<label for="surname">Surname</label> <input type="text"
						class="form-control" id="surname" name="surname"
						placeholder="Surname">
				</div>

				<div class="form-group">
					<label for="mail">Mail</label> <input type="email"
						class="form-control" id="mail" name="mail"
						aria-describedby="emailHelp" placeholder="Mail"> <small
						id="emailHelp" class="form-text text-muted">Votre adresse
						ne sera jamais partagée.</small>
				</div>
				<button type="submit" class="btn btn-green">Modifier</button>
			</form>
		</div>
		<div class="col-4">
			<h2>Modifier mon mot de passe</h2>

			<form action="" name="updatePassword" method="post"
				onsubmit="return submitUpdatePassword();">
				<div class="form-group">
					<label for="password">Mot de passe</label> <input type="password"
						class="form-control" id="password" name="password"
						placeholder="Mot de passe">
				</div>
				<div class="form-group">
					<label for="password2">Confirmer le mot de passe</label> <input
						type="password" class="form-control" id="password2"
						name="password2" placeholder="Confirmer mot de passe">
				</div>

				<button type="submit" class="btn btn-green">Modifier</button>
			</form>
		</div>
	</section>


	</main>
	</div>
	</div>
<script>
		window.onload = function() {
			loadProfile();

		}
	</script>
	<!-- Bootstrap core JavaScript
    ================================================== -->
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
