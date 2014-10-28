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
import dao.DAOTrain;
import dao.IDAO;
import domain.Requete;
import domain.Train;
import domain.User;
import utils.GenerateGareToDB;

public class SearchServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String SEARCH_JSP = "/jsp/search.jsp";
	
	//private IDAO<Train> daoTrain;
	private DAOTrain daoTrain;
	private IDAO<User> daoUser;
       
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
		daoTrain = new DAOTrain();
		daoUser  = DAOFactory.createDAOUser();
		/*try {
			GenerateGareToDB.init();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}

	/**
	 * Traite les requêtes de type GET
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(SEARCH_JSP).forward(request, response);
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
			
			String depart  = request.getParameter("depart");
			String arrivee = request.getParameter("arrivee");
			String user    = request.getParameter("user");
			String json;
			
			// Recherche des trains 
			if (user.isEmpty()) {                                                                                                                                                                                                                                                                                                                                                                                                                                  
				List<Train> trains = daoTrain.findTrain(depart, arrivee);
				request.setAttribute("train", trains);
				for (Train req : trains) {
					daoTrain.update(req);
				}
			    json = new Gson().toJson(trains);
			
			 // Recherche des utilisateurs
			} else {
				User member = daoUser.find(user);
				List<User> users = new ArrayList<User>();
				users.add(member);
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
