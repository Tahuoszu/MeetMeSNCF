package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import dao.DAOFactory;
import dao.IDAOGare;
import dao.IDAOTrain;
import domain.Train;

public class SearchTrainServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String LOGIN_JSP  = "/jsp/login.jsp";
	
	private IDAOGare daoGare;
	private IDAOTrain daoTrain;
       
    /**
     * Crée la Servlet SearchServlet
     */
    public SearchTrainServlet() {
        super();
    }
    
    /**
     * Initialise la Servlet SearchServlet
     */
	public void init() throws ServletException {
		super.init();
		daoGare  = DAOFactory.createDAOGare();
		daoTrain = DAOFactory.createDAOTrain();
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
		
		// Recherche de trains
		searchTrain(request, response);
		
	}

	/**
	 * Recherche de trains
	 */ 
	private void searchTrain(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// En-tête de la réponse
		response.setContentType("application/json");
	    response.setCharacterEncoding("ISO-8859-15");
		
		try {
		    // Récupération de la requête utilisateur pour la recherche
			String depart  = request.getParameter("depart");
			String arrivee = request.getParameter("arrivee");
			System.out.println("SearchTrainServlet Depart : " + depart);
			System.out.println("SearchTrainServlet Arrivee : " + arrivee);
			// Test
			depart = "VERSAILLES CHANTIERS";
			arrivee = "INVALIDES";
			// Convertit le nom des gares vers leur code UIC respectif
			String num_depart  = daoGare.getGareUIC(depart);
			String num_arrivee = daoGare.getGareUIC(arrivee);
			System.out.println("Depart : " + num_depart);
			System.out.println("Arrivee : " + num_arrivee);
			// Recherche dans le DataStore et/ou envoie une requête SNCF 
			List<Train> trains = daoTrain.findTrain(num_depart, num_arrivee);
			Gson gson = new Gson();
			JsonElement element = gson.toJsonTree(trains,
					new TypeToken<List<Train>>(){}.getType());
			JsonArray jsonArray = element.getAsJsonArray();
			response.getWriter().print(jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
