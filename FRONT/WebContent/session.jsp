
<%
	if (request.getSession().getAttribute("USER")==null && request.getParameter("CONNECT") != null) {

		request.getSession().setAttribute("USER", true);
		request.getSession().setAttribute("USERNAME", request.getParameter("USERNAME"));
		request.getSession().setAttribute("PASSWORD", request.getParameter("PASSWORD"));
		
		
	} else if (request.getSession().getAttribute("USER")!=null  && request.getParameter("DISCONNECT") != null) {
		request.getSession().invalidate();
	}
%>