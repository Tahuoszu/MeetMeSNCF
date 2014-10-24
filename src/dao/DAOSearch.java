package dao;

import java.util.List;

import com.googlecode.objectify.ObjectifyService;

import static com.googlecode.objectify.ObjectifyService.ofy;
import domain.RequestSNCF;
import domain.User;

public class DAOSearch implements IDAOSearch<User, RequestSNCF> {

	static {
		 ObjectifyService.register(User.class);
		 ObjectifyService.register(RequestSNCF.class);
    }
	
	/**
	 * Ajoute une entité RequestSNCF dans la base de données DataStore.
	 * 
	 * @param entity
	 */
	public void add(RequestSNCF req) {
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
	public List<RequestSNCF> findTrain(String depart) {
		return ofy().load().type(RequestSNCF.class).
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
	public List<RequestSNCF> findTrain(String depart, String arrivee) {
		if (arrivee.isEmpty())
			return findTrain(depart);
		List<RequestSNCF> trains = ofy().load().type(RequestSNCF.class).
				filter("name ==", depart).list();
		return trains;
	}
	
	/**
	 * Supprime une entité RequestSNCF de la base de données DataStore.
	 * 
	 * @param entity
	 */
	public void remove(RequestSNCF req) {
		ofy().delete().type(User.class).id(req.getNum()).now();
	}
	
	/**
	 * Met à jour une gare dans la base de données DataStore.
	 * 
	 * @param object
	 */
	public void update(RequestSNCF req) {
		add(req);
	}
	
}
