<script type="text/javascript" src="scripts/disconnect.js"></script>
<nav
	class="navbar navbar-toggleable-md navbar-inverse fixed-top bg-inverse">
	<button class="navbar-toggler navbar-toggler-right" type="button"
		data-toggle="collapse" data-target="#navbarsExampleDefault"
		aria-controls="navbarsExampleDefault" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<a class="navbar-brand" href="#">Menu</a>

	<div class="collapse navbar-collapse" id="navbarsExampleDefault">
		<ul class="navbar-nav mr-auto">
			<li
				class="nav-item <%if (request.getParameter("curr").equals("ACCUEIL")) {%> active <%}%>"><a
				class="nav-link" href="index.jsp">Accueil<a></li>

			<%
				if (request.getSession().getAttribute("USER") == null) {
			%><li
				class="nav-item <%if (request.getParameter("curr").equals("SIGNIN")) {%> active <%}%>"><a
				class="nav-link" href="signin.jsp">Inscription/Connexion</a></li>
			<%
				}
			%>
			<%
				if (request.getSession().getAttribute("USER") != null) {
			%><li
				class="nav-item <%if (request.getParameter("curr").equals("DECONNEXION")) {%> active <%}%>"><a
				class="nav-link" onclick="disconnect();"href="">Deconnexion</a></li>
			<%
				}
			%>

			<%
				if (request.getSession().getAttribute("USER") != null) {
			%><li
				class="nav-item <%if (request.getParameter("curr").equals("COMPTE")) {%> active <%}%>"><a
				class="nav-link" href="account.jsp">Compte</a></li>
			<%
				}
			%>

			<li
				class="nav-item <%if (request.getParameter("curr").equals("PRODUCTS")) {%> active <%}%>"><a
				class="nav-link" href="products.jsp">Produits</a></li>
		</ul>
		<ul class="navbar-nav mr-auto">
			<li
				class="nav-item <%if (request.getParameter("curr").equals("PANIER")) {%> active <%}%>"><a
				class="nav-link" href="panier.jsp"><span
					class="glyphicon  glyphicon-shopping-cart"></span> Mon panier (<span
					id="nbpanier">0</span>)</a></li>
		</ul>
		<script type="text/javascript" src="scripts/panier.js"></script>
		<script>
			loadNbPanier();
		</script>

		<ul class="navbar-nav mr-auto">
			<form action="search.jsp" class="form-inline my-2 my-lg-0">
				<input name="name" class="form-control mr-sm-2" type="text"
					placeholder="Recherche">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Recherche</button>
			</form>
		</ul>
	</div>
</nav>

