package dao;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.ObjectifyService;
import static com.googlecode.objectify.ObjectifyService.ofy;

import domain.Gare;

public class DAOGare implements IDAOGare {

	static {
		ObjectifyService.register(Gare.class);
    }
	
	/**
	 * Ajoute une gare dans la base de données DataStore.
	 * 
	 * @param gare
	 */
	public void add(Gare gare) {
		ofy().save().entity(gare).now();
	}

	/**
	 * Cherche une gare à partir de son nom dans la base de données DataStore.
	 * 
	 * @param nom de la gare
	 * @return gare
	 */
	public Gare find(String gare) {
		return ofy().load().type(Gare.class).
				filter("nomGare ==", gare).list().get(0);
	}
	
	/**
	 * Cherche la liste de gares desservies par une ligne à partir d'une de ces
	 * gares dans la base de données DataStore. La requête est envoyée dans le
	 * DateStore dès qu'un début de nom est entré. 
	 * 
	 * @param nom de la gare
	 * @return liste de gares
	 */
	public List<String> findGare(String gare) {
		List<Gare> gares = ofy().load().type(Gare.class).
				filter("nomGare >=", gare).
				filter("nomGare <", gare + "\uFFFD").list();
		List<String> gares_name = new ArrayList<String>();
		for (Gare g : gares) {
			gares_name.add(g.getName());
		}
		return gares_name;
	}

	/**
	 * Retourne le code UIC de la gare à partir de son nom (appel au DataStore).
	 * 
	 * @param nom de la gare
	 * @return code UIC de la gare
	 */
	public String getGareUIC(String gare) {
		String gare_name = "";
		List<Gare> gares = ofy().load().type(Gare.class).
				filter("nomGare ==", gare).list();
		if (gares.isEmpty())
			gare_name = "";
		else
			gare_name = gares.get(0).getUIC();
		return gare_name;
	}
	
	/**
	 * Retourne la liste des gares d'une ligne à partir d'une gare.
	 * 
	 * @param gare
	 * @return liste de gare
	 */
	public List<String> getGaresByLine(String gare) {
		// Récupère la liste des lignes
		List<String> lines = ofy().load().type(Gare.class).
				filter("nomGare ==", gare).list().get(0).getLines();
		// Récupère la liste des gares
		List<Gare> gares = new ArrayList<Gare>();
		for (String line : lines) {
			gares.addAll(ofy().load().type(Gare.class).
					filter("line ==", line).list());
		}
		// Récupère le nom des gares
		List<String> gares_name = new ArrayList<String>();
		for (Gare g : gares) {
			gares_name.add(g.getName());
		}
		return gares_name;
	}
	
	/**
	 * Supprime une gare à partir de son nom dans la base de données DataStore.
	 * 
	 * @param gare
	 */
	public void remove(Gare gare) {
		ofy().delete().type(Gare.class).id(gare.getUIC()).now();
	}
//	
//	/**
//	 * Supprime l'entité Gare de la base de données DataStore.
//	 */
//	public void remove(){
//		ofy().delete().type(Gare.class);
//	}
//	
	/**
	 * Met à jour une gare dans la base de données DataStore.
	 * 
	 * @param gare
	 */
	public void update(Gare gare) {
		add(gare);
	}
	
}
