package domain;

import java.io.Serializable;

import com.google.appengine.api.datastore.Key;
import com.googlecode.objectify.annotation.*;

@Entity
public class Gare implements Serializable {

	private static final long serialVersionUID = -6809445579040707116L;
	

	// Identifiant de la gare
	@Id private final String UIC;
	
	// Nom de la gare
	@Index private final String name;

	//Clé listegares 
	@Parent Key listesgares;
	
	public Gare(String uic, String name, Key listesgares) {
		super();
		this.UIC  = uic;
		this.name = name;
		this.listesgares = listesgares;
	}

	/**
	 * Retourne l'identifiant de la gare;
	 * 
	 * @return uic
	 */
	public String getUIC() {
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

}
