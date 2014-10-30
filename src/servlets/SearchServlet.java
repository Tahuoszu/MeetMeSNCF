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
import domain.Gare;
import domain.Train;
import domain.User;

public class SearchServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String SEARCH_JSP = "/jsp/search.jsp";
	
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
		
		// Récupération de la requête utilisateur pour l'autocomplétion
		String query_depart = request.getParameter("depart");
		//List<String> depart = daoGare.findGare(query_depart);
        List<String> depart = new ArrayList<String>();
        depart.add("Vincennes");
        depart.add("Val de Fontenay");
        depart.add("Nanterres");
        
        Map<String,List<String> > map = new HashMap<String, List<String> >();
        map.put("suggestions", depart);
        Gson gson = (new GsonBuilder()).create();
        // On crée une représentation JSON de notre map sous la forme
        // d'une chaîne de caractères.
        String json = gson.toJson(map);
        
        // On écrit du JSON sur le flux de sortie de la réponse HTTP.
        PrintWriter pw = new PrintWriter(response.getOutputStream());
        pw.print(json);
        pw.flush();
        
        // Génération de liste des gares d'arrivée
        String query_arrivee = request.getParameter("arrivee");
        //List<String> arrivee = daoGare.getGaresByLine(query_arrivee);
        /*List<String> arrivee = new ArrayList<String>();
        arrivee.add("Châtelet - Les Halles");
        arrivee.add("Gare de Lyon");
        arrivee.add("Nation");
        map.put("suggestions", arrivee);
        gson = (new GsonBuilder()).create();
		json = gson.toJson(map);
		
		pw = new PrintWriter(response.getOutputStream());
        pw.print(json);
        pw.flush();*/
        
		response.setContentType("application/json");
		response.setCharacterEncoding("ISO-8859-15");
	    
	}

	/**
	 * Traite les requêtes de type POST
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("login") != "login")
			session.removeAttribute("login");
		
		else {

			String json;
			String depart  = request.getParameter("depart");
			String arrivee = request.getParameter("arrivee");
			String user    = request.getParameter("user");
			
			// Recherche des trains 
			if (user.isEmpty()) {
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

			// Recharge la page avec les résultats
			request.getRequestDispatcher(SEARCH_JSP).forward(request, response);
			
			// Envoi de la requête
			response.setContentType("application/json");
		    response.setCharacterEncoding("ISO-8859-15");
		    response.getWriter().write(json);
			
		}
	}

}
