package tests;

import java.util.LinkedList;
import java.util.List;

import dao.DAOFactory;
import dao.IDAOTrain;
import domain.Train;

public class TestRequete {
	public static void createTestRequete (){
		String[] departs = {"87281873", "87547000", "87271163", "87271460", "87381848", "87281873", "87381137", "87382002", "87545194", "87545285"};
		IDAOTrain dao = DAOFactory.createDAOTrain();
		List<Train> trains = new LinkedList<Train>(); 
		for(int i= 0; i< 10; i++){
			trains = dao.findTrain(departs[i], "");
		}
	}
}
