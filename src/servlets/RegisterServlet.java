package servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.EmailSender;
import dao.DAOFactory;
import dao.IDAO;
import domain.Requete;
import domain.User;
import forms.RegisterForm;

public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = -1451723899712538224L;

	private static final String REGISTER_JSP = "/jsp/register.jsp";
	
	private IDAO<User> daoUser;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher(REGISTER_JSP).forward(req, resp);
	}

	@Override
	public void init() throws ServletException {
		super.init();
		daoUser = DAOFactory.createDAOUser();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		RegisterForm registerForm = new RegisterForm(daoUser);
		registerForm.setLogin(req.getParameter("login"));
		registerForm.setEmail(req.getParameter("email"));
		registerForm.setPassword(req.getParameter("password"));
		registerForm.setConfirmation(req.getParameter("confirmation"));
		registerForm.setFirstname(req.getParameter("firstname"));
		registerForm.setLastname(req.getParameter("lastname"));
		registerForm.setPresentation(req.getParameter("presentation"));
		registerForm.setSexe(req.getParameter("sexe"));
		registerForm.setInterests(req.getParameterValues("interests"));
		
		req.setAttribute("registerForm", registerForm);

		// S'il n'y a pas eu d'erreurs lors de la validation du formulaire :
		Map<String,String> errors = registerForm.validate();
		if(errors.isEmpty()) {
			User user = registerForm.createUser();
			daoUser.add(user);
			EmailSender.sendConfirmationEmail(user.getEmail());
		}
		// Sinon, re-affichage du formulaire d'inscription :
		else {
		    req.setAttribute("errorsRegister", errors);
			getServletContext().getRequestDispatcher(REGISTER_JSP).forward(req,resp);
		}
	}
	
}
