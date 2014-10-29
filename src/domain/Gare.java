package domain;

import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;

import com.google.appengine.api.datastore.Key;
import com.googlecode.objectify.annotation.*;

@Entity
@Index
public class Gare implements Serializable {

	private static final long serialVersionUID = -6809445579040707116L;
	
	// Identifiant de la gare
	@Id private final String UIC;
	
	// Nom de la gare
	 private final String namae;
	 
	
	// Numéro et type des lignes passant par la gare
	//private final Map<String, String> lines;

	//Clé listegares pour lier les entités Gare
	@Parent Key listegares;
	
	/**
	 * Crée une gare avec son identifiant UIC, le nom de la gare et les lignes
	 * qui passent par la gare.
	 * 
	 * @param uic
	 * @param name
	 * @param lines
	 */
	public Gare(String uic, String name/*, Map<String, String> lines*/) {
		super();
		this.UIC   = uic;
		this.namae  = name;
		//this.lines = lines;
		this.listegares = null;
	}
	
	/**
	 * Crée une gare avec son identifiant UIC, le nom de la gare et les lignes
	 * qui passent par la gare. Une clé parente est associée à l'entité Gare
	 * dans le DataStore pour faciliter la recherche.
	 * 
	 * @param uic
	 * @param name
	 * @param lines
	 * @param listegares
	 */
	public Gare(String uic, String name,
			/*Map<String, String> lines,*/ Key listegares) {
		super();
		this.UIC   = uic;
		this.namae  = name;
		//this.lines = lines;
		this.listegares = listegares;
	}
	
	/**
	 * Retourne les lignes passant par la gare
	 * 
	 * @return lines
	 */
	/*public Map<String, String> getLines() {
		return lines;
	}
	*/
	/**
	 * Retourne le nom de la gare
	 * 
	 * @return name
	 */
	public String getName() {
		return namae;
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
	 * Modifie la clé parente de la gare
	 * 
	 * @param clé parente
	 */
	public void setKey(Key listegares) {
		this.listegares = listegares;
	}

	/**
	 * Affiche les données de la gare
	 * 
	 * @return description de la gare
	 */
	/*public String toString() {
		String str = "Gare [UIC=" + UIC + ", name=" + name + ", lines={";
		for (Entry<String, String> l : lines.entrySet())
			str += "(" + l.getKey() + "," + l.getValue() + "), ";
		str = str.substring(0, str.length() - 2);
		str += "}]";
		return str;
	}*/
	
}
