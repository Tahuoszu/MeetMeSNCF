package dao;

import domain.Message;
import domain.Train;
import domain.User;

public class DAOFactory {

	/**
	 * Méthode permettant de créer une instance de DAOUser.
	 * @return une instance de DAOUser.
	 */
	public static IDAO<User> createDAOUser() {
		return new DAOUser();
	}

	/**
	 * Crée une instance de DAOSearch.
	 * 
	 * @return DAOSearch
	 */
	public static IDAOSearch<User, Train> createDAOSearch() {
		return new DAOSearch();
	}
	
	public static IDAO<Message> createDAOMessage(){
		return new DAOMessage();
	}
}
