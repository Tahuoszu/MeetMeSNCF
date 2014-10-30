package dao;

import java.util.List;

import domain.Train;

public interface IDAOTrain extends IDAO<Train> {
	
	/**
	 * Cherche une liste de trains partant de la gare de départ dans la base de 
	 * données DataStore.
	 * 
	 * @param gare de départ
	 * @return liste de trains
	 */
	public List<Train> findTrain(String depart);
	
	/**
	 * Cherche une liste de trains partant de la gare de départ et arrivant de
	 * la gare d'arrivée dans la base de données DataStore.
	 * 
	 * @param gare de départ
	 * @param gare d'arrivée
	 * @return liste de trains
	 */
	public List<Train> findTrain(String depart, String arrivee);
	
	/**
	 * Cherche une liste de trains � partir de la date dans la base de données DataStore.
	 * 
	 * @param gare de départ
	 * @param gare d'arrivée
	 * @return liste de trains
	 */
	public List<Train> findTrainByDate();
}
