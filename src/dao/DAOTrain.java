package dao;

import java.util.List;

import utils.XmlTools;

import com.googlecode.objectify.ObjectifyService;

import static com.googlecode.objectify.ObjectifyService.ofy;
import domain.Gare;
import domain.Requete;
import domain.Train;

public class DAOTrain implements IDAOTrain {

	static {
		ObjectifyService.register(Gare.class);
		ObjectifyService.register(Train.class);
    }
	
	/**
	 * Ajoute un train dans la base de données DataStore.
	 * 
	 * @param train
	 */
	public void add(Train train) {
		ofy().save().entity(train);
	}

	/**
	 * Cherche un train à partir de son code UIC dans la base de données
	 * DataStore.
	 * 
	 * @param code UIC
	 * @return train
	 */
	public Train find(String num) {
		return ofy().load().type(Train.class).
				filter("num ==", num).list().get(0);
	}
	
	/**
	 * Cherche une liste de trains partant de la gare de départ dans la base de 
	 * données DataStore.
	 * 
	 * @param gare de départ
	 * @return liste de trains
	 */
	public List<Train> findTrain(String depart) {
		return ofy().load().type(Train.class).filter("name ==", depart).list();
	}
	
	/**
	 * Cherche une liste de trains partant de la gare de départ et arrivant de
	 * la gare d'arrivée dans la base de données DataStore.
	 * 
	 * @param gare de départ
	 * @param gare d'arrivée
	 * @param index de roulement 
	 * @return liste de trains
	 */
	public List<Train> findTrain(String depart, String arrivee) {
		if (arrivee.isEmpty())
			return findTrain(depart);
		// Chercher dans le DataStore
		String num = ofy().load().type(Gare.class).
				filter("name ==", depart).list().get(0).getUIC();
		List<Train> trains = ofy().load().type(Train.class).
				filter("num ==", num).list();
		//requete a la sncf
		if (trains.isEmpty()){
			trains = new XmlTools().XmlToTrains(new Requete(depart,arrivee).requeteSNCF());
			for (int i = 0 ; i < trains.size() - 1; i++){
				add(trains.get(i));
			}
		}
		return trains;
	}
	
	/**
	 * Supprime un train de la base de données DataStore.
	 * 
	 * @param train
	 */
	public void remove(Train train) {
		ofy().delete().type(Train.class).id(train.getNum()).now();
	}
	
	/**
	 * Met à jour un train dans la base de données DataStore.
	 * 
	 * @param train
	 */
	public void update(Train train) {
		add(train);
	}
	
}
