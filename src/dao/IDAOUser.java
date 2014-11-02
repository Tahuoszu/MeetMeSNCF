package dao;

import java.util.List;


import domain.User;

public interface IDAOUser extends IDAO<User> {
	
	/**
	 * Méthode permettant de vérifier la présence d'un utilisateur
	 * dans la base de donnée DataStore à partir de son login et mot de passe.
	 * 
	 * @param login
	 * @param password 
	 * @return user
	 * */
	public User find(String login, String password);
	
	/**
	 * Méthode permettant de trouver une liste d'utilisateur commençant par la
	 * query de son identifiant dans la base de données.
	 * 
	 * @param login
	 * @return liste d'utilisateurs
	 * */
	public List<User> findUser(String login);

	/**
	 * Méthode permettant de trouver les utilisateurs connectés.
	 * @return la liste des utilisateurs connectés.
	 */
	public List<User> findConnectedUsers();
	
	/**
	 * Méthode permettant de mettre à jour le train que va prendre
	 * @param login de l'utilisateur
	 * @param numéro du train à prendre
	 * @param numero uic de la gare  
	 */
	public void updateTrain(String login, String num, String uic);
	
	
	public List<User> findUsers (String train, String user);
}
