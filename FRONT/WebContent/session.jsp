
<%
	if (request.getParameter("CONNECT") != null &&request.getSession().getAttribute("USER")==null ) {

		request.getSession().setAttribute("USER", true);
		
		if(request.getParameter("ROLE").equals("ADMIN"))
		{
			request.getSession().setAttribute("ADMIN", true);
		}
	} 

	if (request.getParameter("DISCONNECT") != null) {
		request.getSession().invalidate();
	}
%>