package dao;

import java.util.List;

import domain.Gare;

public interface IDAOGare extends IDAO<Gare> {
	
	/**
	 * Cherche la liste de gares desservies par une ligne à partir d'une de ces
	 * gares dans la base de données DataStore. La requête est envoyée dans le
	 * DateStore dès qu'un début de nom est entré. 
	 * 
	 * @param nom de la gare
	 * @return liste de gares
	 */
	public List<String> findGare(String gare);
	
	/**
	 * Retourne le code UIC de la gare à partir de son nom (appel au DataStore).
	 * 
	 * @param nom de la gare
	 * @return code UIC de la gare
	 */
	public String getGareUIC(String gare);
	
	/**
	 * Retourne la liste des gares d'une ligne à partir d'une gare.
	 * 
	 * @param depart
	 * @param arrivee
	 * @return liste de gare
	 */
	public List<String> getGaresByLine(String depart, String arrivee);
	
}
