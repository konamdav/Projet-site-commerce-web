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
<script type="text/javascript" src="scripts/user.js"></script>
</head>

<body>
	<jsp:include page="menu.jsp">
		<jsp:param name="curr" value="COMPTE" />
	</jsp:include>

	<jsp:include page="menu-account.jsp">
		<jsp:param name="curr" value="ACCOUNT" />
	</jsp:include>

	<main class="col-sm-9 offset-sm-3 col-md-10 offset-md-2 pt-3">
	<h1>Mon compte</h1>
	<%
			if(request.getSession().getAttribute("USER")!=null){
		%>
	<section class="row text-center placeholders">
		<div class="col-md-3">
			<img src="user.png" width="200" height="200" class="img-fluid "
				alt="">
		</div>

		<div class="col s12 m4 l8">
			<h2 align="left">Mes informations</h2>
			<div class="table-responsive">
				<table class="table table-striped">
					<tbody>
						<tr>
							<th>Username</th>
							<td id="username"></td>
						</tr>
						<tr>
							<th>Firstname</th>
							<td id="firstname"></td>
						</tr>
						<tr>
							<th>Surname</th>
							<td id="surname"></td>
						</tr>

						<tr>
							<th >Mail</th>
							<td id="mail"></td>
						</tr>


					</tbody>
				</table>
			</div>
		</div>
	</section>

	<script>
		window.onload = function() {
			loadUser();

		}
	</script> <%
			}
	%> </main>
	</div>
	</div>

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
