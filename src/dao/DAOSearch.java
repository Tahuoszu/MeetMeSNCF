package dao;

import java.util.List;

import com.googlecode.objectify.ObjectifyService;

import static com.googlecode.objectify.ObjectifyService.ofy;
import domain.Gare;
import domain.Train;
import domain.User;

public class DAOSearch implements IDAOSearch<User, Train> {

	static {
		ObjectifyService.register(Gare.class);
		 ObjectifyService.register(User.class);
		 ObjectifyService.register(Train.class);
    }
	
	/**
	 * Ajoute une entité RequestSNCF dans la base de données DataStore.
	 * 
	 * @param entity
	 */
	public void add(Train req) {
		ofy().save().entity(req);
	}

	/**
	 * Cherche une entité User dans la base de données DataStore
	 * à partir de son pseudo.
	 * L'entité retournée contient l'utilisateur recherché.
	 * 
	 * @param string
	 * @return entity
	 */
	public User findMember(String login) {
		User u = ofy().load().type(User.class).id(login).now();
		return u;
	}

	/**
	 * Cherche une entité RequestSNCF dans la base de données DataStore
	 * à partir du nom de la gare de départ.
	 * L'entité retournée contient la liste des noms de train et des horaires
	 * de départ.
	 * 
	 * @param string
	 * @return entity
	 */
	public List<Train> findTrain(String depart) {
		return ofy().load().type(Train.class).
				filter("name ==", depart).list();
	}
	
	/**
	 * Cherche une entité RequestSNCF dans la base de données DataStore
	 * à partir du nom de la gare de départ et celle d'arrivée.
	 * L'entité retournée contient la liste des noms de train, des horaires
	 * de départ et des horaires d'arrivée.
	 * 
	 * @param string
	 * @param string
	 * @return entity
	 */
	public List<Train> findTrain(String depart, String arrivee) {
		if (arrivee.isEmpty())
			return findTrain(depart);
		String num = ofy().load().type(Gare.class).
				filter("name ==", depart).list().get(0).getUIC();
		//String num = ofy().load().type(Gare.class).filter("name ==", depart).list().get(0).getUIC();
		List<Train> trains = ofy().load().type(Train.class).
				filter("num ==", num).list();
		return trains;
	}
	
	/**
	 * Supprime une entité RequestSNCF de la base de données DataStore.
	 * 
	 * @param entity
	 */
	public void remove(Train req) {
		ofy().delete().type(User.class).id(req.getNum()).now();
	}
	
	/**
	 * Met à jour une gare dans la base de données DataStore.
	 * 
	 * @param object
	 */
	public void update(Train req) {
		add(req);
	}
	
}
