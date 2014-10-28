package dao;

public class DAOFactory {

	/**
	 * Méthode permettant de créer une instance de DAOMessage.
	 * 
	 * @return une instance de DAOMessage
	 */
	public static IDAOMessage createDAOMessage() {
		return new DAOMessage();
	}
	
	/**
	 * Méthode permettant de créer une instance de DAOTrain.
	 * 
	 * @return une instance de DAOTrain
	 */
	public static IDAOTrain createDAOTrain() {
		return new DAOTrain();
	}

	/**
	 * Méthode permettant de créer une instance de DAOUser.
	 * 
	 * @return une instance de DAOUser
	 */
	public static IDAOUser createDAOUser() {
		return new DAOUser();
	}
	
}
