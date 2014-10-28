package dao;

import java.util.List;

import domain.User;

public interface IDAOUser extends IDAO<User> {
	
	/**
	 * Méthode permettant de trouver une liste d'utilisateur commençant par la
	 * query de son identifiant dans la base de données.
	 * 
	 * @param login
	 * @return liste d'utilisateurs
	 * */
	public List<User> findUser(String login);

}
