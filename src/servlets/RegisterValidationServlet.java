package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.DAOFactory;
import dao.IDAO;
import domain.User;
import forms.RegisterValidation;

public class RegisterValidationServlet extends HttpServlet {

	private static final long serialVersionUID = 1688646464068095465L;

	private IDAO<User> daoUser;
	
	public void init() throws ServletException {
		super.init();
		daoUser = DAOFactory.createDAOUser();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String type = req.getParameter("type");
		RegisterValidation rv = new RegisterValidation(daoUser);
		boolean isAvailable = false;
		
		// Si le champ dont on veut verifier la disponibilite est le login
		if(type.equals("login")) {
		  String login = req.getParameter("login");
		  isAvailable = rv.isLoginAvailable(login);
		}
		// Si le champ dont on veut verifier la disponibilite est l'email
		else if(type.equals("email")) {
		  String email = req.getParameter("email");
		  isAvailable = rv.isEmailAvailable(email);
		}
	
		resp.setContentType("application/json");
		
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		map.put("valid", new Boolean(isAvailable));
		
		Gson gson = (new GsonBuilder()).create();
		
		// On cree une representation JSON de notre map, 
		// sous la forme d'une chaine de caracteres.
		String json = gson.toJson(map);
		
		// On ecrit du JSON sur le flux de sortie de la reponse HTTP
		// Si isAvailable est egal a true, on envoie { "valid", true }
		// Sinon, on envoie { "valid", false }
		PrintWriter pw = new PrintWriter(resp.getOutputStream());
		
		pw.print(json);
		pw.flush();
	
	}
	
}
