package dao;

import domain.RequestSNCF;
import domain.User;

public class DAOSearch implements IDAOSearch<User, RequestSNCF> {

	/**
	 * Ajoute une entité RequestSNCF dans la base de données DataStore.
	 * 
	 * @param entity
	 */
	public void add(RequestSNCF req) {
		
	}

	/**
	 * Cherche une entité User dans la base de données DataStore
	 * à partir de son pseudo.
	 * L'entité retournée contient l'utilisateur recherché.
	 * 
	 * @param string
	 * @return entity
	 */
	public User findMember(String pseudo) {
		return null;
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
	public RequestSNCF findTrain(String depart) {
		return null;
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
	public RequestSNCF findTrain(String depart, String arrivee) {
		if (arrivee.isEmpty())
			return findTrain(depart);
		return null;
	}
	
	/**
	 * Supprime une entité RequestSNCF de la base de données DataStore.
	 * 
	 * @param entity
	 */
	public void remove(RequestSNCF req) {
		
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
