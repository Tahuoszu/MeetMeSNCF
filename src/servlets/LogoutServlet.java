package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOFactory;
import dao.IDAOUser;
import domain.User;

public class LogoutServlet extends HttpServlet {
	
	private static final long serialVersionUID = 7659973059188428381L;

	private IDAOUser daoUser;
	
	@Override
	public void init() throws ServletException {
	    super.init();
	    daoUser = DAOFactory.createDAOUser();
	}
	
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
	    HttpSession session = req.getSession();
        
        Object o = session.getAttribute("login");
        if(o != null) {
            if(o instanceof String) {
                String login = (String)o;
                User user = daoUser.find(login);
                if(user != null) {
                    
                    // On met a jour letat de connexion de lutilisateur dans la bd
                    user.setConnected(false);
                    daoUser.update(user);
                }
            }
        }
        
        session.removeAttribute("login");
        
        req.getRequestDispatcher("/login").forward(req, resp);
    }

}
