package dao;

public interface IDAO<T> {
	
	/**
	 * Méthode permettant d'ajouter un élément à la base de données.
	 */
	public void add(T object);
	
	/**
	 * Méthode permettant de supprimer un élément de la base de données.
	 */
	public void remove(T object);
	
	/**
	 * Méthode permettant de mettre à jour un élément dans la base de données.
	 */
	public void update(T object);

	/**
	 * Méthode permettant de trouver un élément dans la base de données avec
	 * son identifiant.
	 */
	public T find(long id);

}
