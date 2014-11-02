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
import dao.IDAOUser;
import domain.User;

public class SearchUserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String LOGIN_JSP  = "/jsp/login.jsp";
	
	private IDAOUser daoUser;
       
    /**
     * Crée la Servlet SearchServlet
     */
    public SearchUserServlet() {
        super();
    }
    
    /**
     * Initialise la Servlet SearchServlet
     */
	public void init() throws ServletException {
		super.init();
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
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("login") == null) {
			session.invalidate();
			request.getRequestDispatcher(LOGIN_JSP).forward(request, response);
		}
		
		// Recherche de membres
		searchUser(request, response);
		
	}
	
	/**
	 * Recherche de membres
	 */
	private void searchUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// En-tête de la réponse
		response.setContentType("application/json");
	    response.setCharacterEncoding("ISO-8859-15");
		
		try {
		    // Récupération de la requête utilisateur pour la recherche
			String user = request.getParameter("user");
			System.out.println("SearchUserServlet User : " + user);
			List<User> users = daoUser.findUser(user);
			request.setAttribute("users", users);
			Gson gson = new Gson();
			JsonElement element = gson.toJsonTree(users,
					new TypeToken<List<User>>(){}.getType());
			JsonArray jsonArray = element.getAsJsonArray();
			response.getWriter().print(jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
