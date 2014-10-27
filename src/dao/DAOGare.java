package dao;

import java.util.List;

import com.googlecode.objectify.ObjectifyService;

import static com.googlecode.objectify.ObjectifyService.ofy;
import domain.Gare;

public class DAOGare implements IDAO<Gare> {

	static {
		ObjectifyService.register(Gare.class);
    }
	
	/**
	 * Ajoute une gare dans la base de données DataStore.
	 * 
	 * @param gare
	 */
	public void add(Gare gare) {
		ofy().save().entity(gare);
	}

	/**
	 * Cherche une gare à partir de son nom dans la base de données DataStore.
	 * 
	 * @param nom de la gare
	 * @return gare
	 */
	public Gare find(String gare) {
		return ofy().load().type(Gare.class).
				filter("name ==", gare).list().get(0);
	}
	
	/**
	 * Cherche la liste de gares desservies par une ligne à partir d'une de ces
	 * gares dans la base de données DataStore. La requête est envoyée dans le
	 * DateStore dès qu'un début de nom est entré. 
	 * 
	 * @param nom de la gare
	 * @return liste de gares
	 */
	public List<Gare> findGare(String gare) {
		return ofy().load().type(Gare.class).filter("field >=", gare).
				filter("field <", gare + "\uFFFD").list();
	}
	
	/**
	 * Supprime une gare à partir de son nom dans la base de données DataStore.
	 * 
	 * @param gare
	 */
	public void remove(Gare gare) {
		ofy().delete().type(Gare.class).id(gare.getUIC()).now();
	}
	
	/**
	 * Met à jour une gare dans la base de données DataStore.
	 * 
	 * @param gare
	 */
	public void update(Gare gare) {
		add(gare);
	}
	
}
