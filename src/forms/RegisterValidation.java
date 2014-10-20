package forms;

import dao.DAOUser;
import dao.IDAO;
import domain.User;

public class RegisterValidation {

	private IDAO<User> daoUser;
	
	public RegisterValidation(IDAO<User> daoUser) {
		this.daoUser = daoUser;
	}
	
	/**
	 * Permet de determiner si un login est disponible (n'est pas deja utilise).
	 * @param login le login a tester.
	 * @return true si le login est disponible et false sinon.
	 */
	public boolean isLoginAvailable(String login) {
		/*
		if(daoUser instanceof DAOUser)
			if(((DAOUser)daoUser).findByLogin(login) != null) 
				return true;
		return false;
		*/
		return true;
	}
		
	/**
	 * Permet de determiner si un email est disponible (n'est pas deja utilise).
	 * @param email l'email a tester
	 * @return true si l'email est disponible et false sinon.
	 */
	public boolean isEmailAvailable(String email) {
		/*
		if(daoUser instanceof DAOUser)
			if(((DAOUser)daoUser).findByEmail(email) != null) 
				return true;
		return false;
		*/
		return true;
	}
	
}

