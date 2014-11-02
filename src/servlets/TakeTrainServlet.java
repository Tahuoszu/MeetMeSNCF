package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import dao.DAOFactory;
import dao.IDAOUser;
import domain.Train;
import domain.User;

public class TakeTrainServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8053868891670658718L;
	private IDAOUser daoUser;
	
	@Override
	public void init() throws ServletException {
		super.init();
		daoUser = DAOFactory.createDAOUser();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			String login = req.getParameter("login");
			String uic = req.getParameter("depart");
			String num = req.getParameter("num");

			daoUser.updateTrain(login, num, uic);
			List<User> users = daoUser.findUsers(num, uic);
			Gson gson = new Gson();
			JsonElement element = gson.toJsonTree(users,
					new TypeToken<List<Train>>(){}.getType());
			JsonArray jsonArray = element.getAsJsonArray();
			resp.getWriter().print(jsonArray);
			
			
	}
}
