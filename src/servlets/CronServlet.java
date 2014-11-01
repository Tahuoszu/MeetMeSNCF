package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;

import dao.IDAOTrain;
import domain.Train;


public class CronServlet  extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2131927808579387931L;
	private IDAOTrain daoTrain;
	@Override
	public void init() throws ServletException {
		super.init();
		daoTrain = DAOFactory.createDAOTrain();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doDelete(req,resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doDelete(req,resp);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<Train> trains = daoTrain.findTrainByDate();
		for (int i = 0; i< trains.size(); i++){
			daoTrain.remove(trains.get(i));
		}
		
	}
	
}
