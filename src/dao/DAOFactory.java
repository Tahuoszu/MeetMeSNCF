package dao;

import domain.RequestSNCF;
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
	public static IDAOSearch<User, RequestSNCF> createDAOSearch() {
		return new DAOSearch();
	}
	
}
