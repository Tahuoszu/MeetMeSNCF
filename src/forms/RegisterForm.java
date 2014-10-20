package forms;

import java.util.ArrayList;
import java.util.Arrays;

import utils.Security;
import dao.IDAO;
import domain.User;

public class RegisterForm {
	
	private IDAO<User> daoUser;
	
	private String login;
	private String password;
	private String confirmation;
	private String firstname;
	private String lastname;
	private String email;
	private String presentation;
	private String sexe;
	private String[] interests;
	
	
	public RegisterForm(IDAO<User> daoUser) {
		this.daoUser = daoUser;
	}
		
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmation() {
		return confirmation;
	}

	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPresentation() {
		return presentation;
	}

	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}
	
	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String[] getInterests() {
        return interests;
    }

    public void setInterests(String[] interests) {
        this.interests = interests;
    }

    /**
	 * Permet de creer un utilisateur avec les informations du formulaire.
	 * @return un utilisateur.
	 */
	public User createUser() {
		
		password = Security.encryptPassword(password);
		
		User user = new User();
		
		user.setLogin(login);
		user.setEmail(email);
		user.setPassword(password);
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setPresentation(presentation);
		user.setSexe(sexe);
		if(interests != null)
		    user.setInterests(new ArrayList<String>(Arrays.asList(interests)));
		else
		    user.setInterests(new ArrayList<String>());
	
		return user;
	}
	
		
	/**
	 * Permet de valider le formulaire d'inscription.
	 */
	public boolean validate() {
		RegisterValidation rv = new RegisterValidation(daoUser);
		return rv.isLoginAvailable(login) && rv.isEmailAvailable(email);
	}

}

