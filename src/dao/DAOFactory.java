package dao;

import domain.Message;
import domain.Train;
import domain.User;

public class DAOFactory {

	/**
	 * Méthode permettant de créer une instance de DAOMessage.
	 * 
	 * @return une instance de DAOMessage
	 */
	public static IDAO<Message> createDAOMessage() {
		return new DAOMessage();
	}
	
	/**
	 * Méthode permettant de créer une instance de DAOTrain.
	 * 
	 * @return une instance de DAOTrain
	 */
	public static IDAO<Train> createDAOTrain() {
		return new DAOTrain();
	}

	/**
	 * Méthode permettant de créer une instance de DAOUser.
	 * 
	 * @return une instance de DAOUser
	 */
	public static IDAO<User> createDAOUser() {
		return new DAOUser();
	}
	
}
