package dao;

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