package forms;

import dao.DAOUser;
import dao.IDAO;
import domain.User;

public class LoginValidation {

	private IDAO<User> daoUser;
	
	public LoginValidation(IDAO<User> daoUser) {
		this.daoUser = daoUser;
	}
	
	/**
	 * Permet de déterminer si un couple login - mot de passe est valide.
	 * @param login un login
	 * @param password un mot de passe
	 * @return true si le couple login - mot de passe est valide et false sinon.
	 */
	public boolean isValid(String login, String password) {
		/*
		if(daoUser instanceof DAOUser) {
			User user = ((DAOUser) daoUser).find(login, password);
			if(user != null)
				return true;
		}
		return false;
		*/
		return false;
	}
	
}