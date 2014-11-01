package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.DAOFactory;
import dao.IDAOGare;
import dao.IDAOTrain;
import dao.IDAOUser;
import domain.Train;
import domain.User;

public class SearchServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String SEARCH_JSP = "/jsp/search.jsp";
	private static final String LOGIN_JSP  = "/jsp/login.jsp";
	
	private IDAOGare daoGare;
	private IDAOTrain daoTrain;
	private IDAOUser daoUser;
       
    /**
     * Crée la Servlet SearchServlet
     */
    public SearchServlet() {
        super();
    }
    
    /**
     * Initialise la Servlet SearchServlet
     */
	public void init() throws ServletException {
		super.init();
		daoGare  = DAOFactory.createDAOGare();
		daoTrain = DAOFactory.createDAOTrain();
		daoUser  = DAOFactory.createDAOUser();
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
		/*HttpSession session = request.getSession();
		if (session.getAttribute("login") != "login") {
			session.removeAttribute("login");
			request.getRequestDispatcher(LOGIN_JSP).forward(request, response);
		}*/

		// Récupération des requêtes d'autocomplétion
		autoComplete(request, response);
		
		// Recherche de trains et/ou de membres
		// search(request, response);
		
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
			if ((depart != null) && (arrivee == null)) {
				depart = depart.toUpperCase();
				System.out.println("SearchServlet Depart : " + depart);
				list = daoGare.findGare(depart);
				for (String s : list)
					System.out.print(s + " ");
				System.out.println();
				// Ecriture du JSON sur le flux de sortie de la réponse HTTP
				searchList = new Gson().toJson(list);
				response.getWriter().write(searchList);
			}
	        //----------- Génération de liste des gares d'arrivée ------------//
			if ((depart == null) && (arrivee != null)) {
		        arrivee = arrivee.toUpperCase();
				System.out.println("SearchServlet Arrivee : " + arrivee);
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
	
	private void search(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// En-tête de la réponse
		response.setContentType("application/json");
	    response.setCharacterEncoding("ISO-8859-15");
	    
	    // Récupération de la requête utilisateur pour la recherche
		String depart  = request.getParameter("depart");
		String arrivee = request.getParameter("arrivee");
		String user    = request.getParameter("user");
		String json;
		
		try {
			// Recherche des trains 
			if (user == null) {
				// Convertit le nom des gares vers leur code UIC respectif
				String num_depart  = daoGare.getGareUIC(depart);
				String num_arrivee = daoGare.getGareUIC(arrivee);
				// Recherche dans le DataStore et/ou envoie une requête SNCF 
				List<Train> trains = daoTrain.findTrain(num_depart, num_arrivee);
				request.setAttribute("train", trains);
			    json = new Gson().toJson(trains);
			// Recherche des utilisateurs
			} else {
				List<User> users = daoUser.findUser(user);
				request.setAttribute("users", users);
				json = new Gson().toJson(users);
			}
			// Envoi de la requête
		    response.getWriter().write(json);
			// Recharge la page avec les résultats
			request.getRequestDispatcher(SEARCH_JSP).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
