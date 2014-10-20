package domain;

import com.google.appengine.api.datastore.*;
import java.io.Serializable;

public class Gare implements Serializable {

	private static final long serialVersionUID = -6809445579040707116L;
	
	// Identifiant de la gare
	private final long UIC;
	
	// Nom de la gare
	private final String name;

	public Gare(long uic, String name) {
		super();
		this.UIC  = uic;
		this.name = name;
	}

	/**
	 * Retourne l'identifiant de la gare;
	 * 
	 * @return uic
	 */
	public long getUIC() {
		return UIC;
	}

	/**
	 * Retourne le nom de la gare
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Ajoute une gare dans la base de données DataStore.
	 * 
	 * @param gare
	 * @return boolean
	 */
	public boolean add(Gare gare) {
		if (gare.equals(null))
			return false;
		else {
			DatastoreService datastore;
			datastore = DatastoreServiceFactory.getDatastoreService();
			Entity g  = new Entity("Gare");
			g.setProperty("UIC", gare.getUIC());
			g.setProperty("name", gare.getName());
			datastore.put(g);
			return true;
		}
	}
	
	/**
	 * Retire une gare de la base de données DataStore.
	 * 
	 * @param gare
	 * @return boolean
	 */
	public boolean remove(Gare gare) {
		if (gare.equals(null))
			return false;
		else {
			DatastoreService datastore;
			datastore = DatastoreServiceFactory.getDatastoreService();
			Key gkey  = KeyFactory.createKey("Gare", gare.getUIC());
			datastore.delete(gkey);
			return true;
		}
	}

}
