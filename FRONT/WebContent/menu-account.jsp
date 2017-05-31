
<div class="container-fluid">
	<div class="row">
		<nav class="col-sm-3 col-md-2 hidden-xs-down bg-faded sidebar">
			<ul class="nav nav-pills flex-column">
				<li class="nav-item active "><a class="nav-link <%if (request.getParameter("curr").equals("ACCOUNT")) {%> active <%}%>" href="account.jsp">Général
				</a></li>
				<li class="nav-item "><a class="nav-link <%if (request.getParameter("curr").equals("PROFILE")) {%> active <%}%>"
					href="account-infos.jsp">Informations</a></li>
				<li class="nav-item "><a class="nav-link <%if (request.getParameter("curr").equals("COMMANDES")) {%> active <%}%>" href="account-commandes.jsp">Commandes</a></li>
				<li class="nav-item "><a class="nav-link <%if (request.getParameter("curr").equals("REVIEWS")) {%> active <%}%>" href="account-avis.jsp">Avis</a>
				</li>
			</ul>

			<ul class="nav nav-pills flex-column">
				<li class="nav-item "><a class="nav-link <%if (request.getParameter("curr").equals("PEGI")) {%> active <%}%>" href="account-pegi.jsp">PEGI</a></li>
				<li class="nav-item "><a class="nav-link <%if (request.getParameter("curr").equals("GENRES")) {%> active <%}%>" href="account-genres.jsp">Genres</a></li>
				<li class="nav-item "><a class="nav-link <%if (request.getParameter("curr").equals("PUBLISHERS")) {%> active <%}%>" href="account-publishers.jsp">Editeurs</a></li>
				<li class="nav-item "><a class="nav-link <%if (request.getParameter("curr").equals("CONSOLES")) {%> active <%}%>" href="account-consoles.jsp">Consoles</a></li>
				<li class="nav-item "><a class="nav-link <%if (request.getParameter("curr").equals("VIDEOGAMES")) {%> active <%}%>" href="account-videogames.jsp">Jeux videos</a></li>
				<li class="nav-item "><a class="nav-link <%if (request.getParameter("curr").equals("PRODUCTS")) {%> active <%}%>" href="account-products.jsp">Produits</a></li>


			</ul>

		</nav>
	</div>
</div>