package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOFactory;
import dao.DAOUser;
import dao.IDAOUser;
import domain.User;
import forms.LoginValidation;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -8431430892643244256L;
	private static final String LOGIN_JSP  = "/jsp/login.jsp";
	private static final String SEARCH_JSP = "/jsp/search.jsp";
	
	private IDAOUser daoUser;
	
	@Override
	public void init() throws ServletException {
		super.init();
		daoUser = DAOFactory.createDAOUser();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher(LOGIN_JSP).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		
		LoginValidation lv = new LoginValidation(daoUser);
		String page; 
		
		// Si le couple login - password est valide
		if(lv.isValid(login, password)) {
			HttpSession session = req.getSession();
			session.setAttribute("login", login);
			
			 // POUR TESTER =============================
            List<User> connectedUsers = ((DAOUser)daoUser).findConnectedUsers();
            session.setAttribute("usersConnectedList", connectedUsers);
			
			page = SEARCH_JSP;
		}
		// Si le couple login - password n'est pas valide :
		// on reaffiche la page avec les erreurs
		else {
			req.setAttribute("oldLogin", login);
			req.setAttribute("errorLogin", true);
			page = LOGIN_JSP;
		}

		req.getRequestDispatcher(page).forward(req, resp);
	}

	
	
}
