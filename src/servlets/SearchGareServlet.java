package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.DAOFactory;
import dao.IDAOGare;

public class SearchGareServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String LOGIN_JSP  = "/jsp/login.jsp";
	
	private IDAOGare daoGare;
       
    /**
     * Crée la Servlet SearchServlet
     */
    public SearchGareServlet() {
        super();
    }
    
    /**
     * Initialise la Servlet SearchServlet
     */
	public void init() throws ServletException {
		super.init();
		daoGare  = DAOFactory.createDAOGare();
	}

	/**
	 * Traite les requêtes de type GET
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * Traite les requêtes de type POST
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// Vérification de la session
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("login") == null) {
			session.invalidate();
			request.getRequestDispatcher(LOGIN_JSP).forward(request, response);
		}

		// Récupération des requêtes d'autocomplétion
		autoComplete(request, response);
		
	}
	
	private void autoComplete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// En-tête de la réponse
		response.setContentType("application/json");
	    response.setCharacterEncoding("ISO-8859-15");
		
		// Récupération de la requête utilisateur pour l'autocomplétion
		String depart  = request.getParameter("depart");
        String arrivee = request.getParameter("arrivee");
		String searchList = "";
		List<String> list = new ArrayList<String>();
		
		try {
	        //----------- Génération de liste des gares de départ ------------//
			if (depart != null) {
				depart = depart.toUpperCase();
				System.out.println("SearchGareServlet Depart : " + depart);
				list = daoGare.findGare(depart);
				for (String s : list)
					System.out.print(s + " ");
				System.out.println();
				// Ecriture du JSON sur le flux de sortie de la réponse HTTP
				searchList = new Gson().toJson(list);
				response.getWriter().write(searchList);
			}
	        //----------- Génération de liste des gares d'arrivée ------------//
			if (arrivee != null) {
		        arrivee = arrivee.toUpperCase();
				System.out.println("SearchGareServlet Arrivee : " + arrivee);
				list = daoGare.findGare(arrivee);
		        //list = daoGare.getGaresByLine(arrivee);
				for (String s : list)
					System.out.print(s + " ");
				System.out.println();
				// Ecriture du JSON sur le flux de sortie de la réponse HTTP
				searchList = new Gson().toJson(list);
				response.getWriter().write(searchList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}