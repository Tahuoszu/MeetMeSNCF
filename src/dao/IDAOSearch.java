package dao;

import java.util.List;

public interface IDAOSearch<S, T> {

	/**
	 * Ajoute une entité dans la base de données DataStore.
	 * 
	 * @param object
	 */
	public void add(T object);

	/**
	 * Cherche une entité dans la base de données DataStore.
	 * 
	 * @param string
	 * @return object
	 */
	public S findMember(String str);
	
	/**
	 * Cherche une entité dans la base de données DataStore.
	 * 
	 * @param string
	 * @return object
	 */
	public List<T> findTrain(String str);
	
	/**
	 * Cherche une entité dans la base de données DataStore.
	 * 
	 * @param string
	 * @return object
	 */
	public List<T> findTrain(String str1, String str2);
	
	/**
	 * Supprime une entité de la base de données DataStore.
	 * 
	 * @param object
	 */
	public void remove(T object);
	
	/**
	 * Met à jour une entité dans la base de données DataStore.
	 * 
	 * @param object
	 */
	public void update(T object);
	
}
