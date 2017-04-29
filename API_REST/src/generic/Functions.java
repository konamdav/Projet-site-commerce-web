package generic;

import javax.servlet.http.HttpServletRequest;

import command.Command;

public class Functions {

	public static final String stringToHtmlString(String s){
		StringBuffer sb = new StringBuffer();
		int n = s.length();
		for (int i = 0; i < n; i++) {
			char c = s.charAt(i);
			switch (c) {
			case '<': sb.append("&lt;"); break;
			case '>': sb.append("&gt;"); break;
			case '&': sb.append("&amp;"); break;
			case '"': sb.append("&quot;"); break;
			default:  sb.append(c); break;
			}
		}
		return sb.toString();
	}

	public static Command getPanier(HttpServletRequest request)
	{
		/**creation panier **/
		if(request.getSession().getAttribute("PANIER")==null){
			Command panier = new Command();
			request.getSession().setAttribute("PANIER", panier);
		}

		return (Command) request.getSession().getAttribute("PANIER");
	}

	public static void newPanier(HttpServletRequest request)
	{
		/**creation panier **/
		Command panier = new Command();
		request.getSession().setAttribute("PANIER", panier);


	}
}
