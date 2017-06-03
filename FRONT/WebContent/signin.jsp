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
<script type="text/javascript" src="scripts/signin.js"></script>
<script type="text/javascript" src="scripts/panier.js"></script>
</head>



<body>
	<jsp:include page="menu.jsp">
		<jsp:param name="curr" value="SIGNIN" />
	</jsp:include><br><br>

	<div class="container">
		<div class="row mt">

			<div class="col-lg-6">
				<h2>Inscription</h2>
				<div id="signin-success"></div>
				<div style="color:red" id="signin-error"></div>
				<hr>
				<form action="" name="formSignin" method="get"
					onsubmit="return submitSignin();">
					<div class="form-group">
						<label for="id_user">Identifiant</label> <input required type="text"
							class="form-control" id="username" name="username"
							placeholder="Username">
					</div>
					<div class="form-group">
						<label for="">Firstname</label> <input required type="text"
							class="form-control" id="firstname" name="firstname"
							placeholder="Firstname">
					</div>
					<div class="form-group">
						<label for="">Surname</label> <input required type="text"
							class="form-control" id="surname" name="surname"
							placeholder="Surname">
					</div>

					<div class="form-group">
						<label for="email_user">Mail</label> <input required type="email"
							class="form-control" id="mail" name="mail"
							aria-describedby="emailHelp" placeholder="Mail"> <small
							id="emailHelp" class="form-text text-muted">Votre mail
							ne sera jamais partagée.</small>
					</div>

					<div class="form-group">
						<label for="mdp_user">Mot de passe</label> <input required type="password"
							class="form-control" id="password" name="password"
							placeholder="Mot de passe">
					</div>
					<div class="form-group">
						<label for="mdp_user2">Confirmer le mot de passe</label> <input
							type="password" class="form-control" id="password2"
							name="password2" placeholder="Confirmer mot de passe">
					</div>

					<button type="submit" class="btn btn-green">Inscription</button>
				</form>

				<br> <br> <br>
			</div>
			<!-- col-lg-8 -->

			<div class="col-lg-6">
				<h2>Connexion</h2>
				<hr>
				<div id="login-success"></div>
				<div style="color:red" id="login-error"></div>
				<form action="" name="formLogin" method="get"
					onsubmit="return submitLogin();">
					<div class="form-group">
						<label for="id_user">Username</label> <input required type="text"
							class="form-control" id="id_user" name="username"
							placeholder="Username">
					</div>

					<div class="form-group">
						<label for="mdp_user">Mot de passe</label> <input required type="password"
							class="form-control" id="mdp_user" name="password"
							placeholder="Mot de passe">
					</div>

					<button type="submit" class="btn btn-green">Connexion</button>
				</form>
			</div>
		</div>
		<!-- /row -->


		<footer>
			<p>&copy; David KONAM & Clément PASSOT | SR03 | 2017</p>
		</footer>
	</div>
	<!-- /container -->


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
