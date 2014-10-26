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
import dao.IDAOSearch;
import domain.Requete;
import domain.Train;
import domain.User;
import utils.GenerateGareToDB;

public class SearchServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String SEARCH_JSP = "/jsp/search.jsp";
	
	private IDAOSearch<User, Train> daoSearch;
       
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
		
		daoSearch = DAOFactory.createDAOSearch();
		try {
			GenerateGareToDB.init();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("login") != "login")
			session.removeAttribute("login");
		else {
			
			// Requête contenant au moins le nom d'une gare
			String depart   = request.getParameter("depart");
			String arrivee  = request.getParameter("arrivee");
			// Recherche des trains                                                                                                                                                                                                                                                                                                                                                                                                                                   
			List<Train> trains = daoSearch.findTrain(depart, arrivee);
			request.setAttribute("requestSNCF", trains);
			for (Train req : trains) {
				daoSearch.update(req);
			}
		    String json = new Gson().toJson(trains);
		    
			// Requête contenant le nom d'un utilisateur
			String user = request.getParameter("user");
			User member = daoSearch.findMember(user);

			List<User> users = new ArrayList<User>();
			users.add(member);
			
			json = new Gson().toJson(users);

			request.getRequestDispatcher(SEARCH_JSP).forward(request, response);
			
			response.setContentType("application/json");
		    response.setCharacterEncoding("ISO-8859-15");
		    response.getWriter().write(json);
			
		}
	}

}
