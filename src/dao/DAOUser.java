package dao;
import domain.User;


public class DAOUser implements IDAO<User> {

	/**
	 * Méthode permettant d'ajouter un utilisateur à la base de données.
	 */
	@Override
	public void add(User object) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Méthode permettant de supprimer un utilisateur de la base de données.
	 */
	@Override
	public void remove(User object) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Méthode permettant de mettre à jour un utilisateur de la base de données.
	 */
	@Override
	public void update(User object) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Méthode permettant de trouver un utilisateur dans la base de données, 
	 * avec son identifiant.
	 */
	@Override
	public User find(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public User find(String login, String password) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public User findByLogin(String login) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
