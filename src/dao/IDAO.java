package dao;

import java.util.List;

public interface IDAO<T> {

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
	public T find(String str);
	
	/**
	 * Cherche une liste d'entité dans la base de données DataStore.
	 * 
	 * @param string
	 * @param string
	 * @return list of objects
	 */
	//public List<T> find(String str1, String str2);
	
	
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