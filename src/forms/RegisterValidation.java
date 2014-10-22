package forms;

import java.util.HashMap;
import java.util.Map;

import dao.DAOUser;
import dao.IDAO;
import domain.User;

public class RegisterValidation {

    private static final String LOGIN_FIELD = "login";
    private static final String PRESENTATION_FIELD = "presentation";
    private static final String EMAIL_FIELD = "email";
    private static final String PASSWORD_FIELD = "password";
    private static final String CONFIRMATION_FIELD = "confirmation";
    private static final String SEXE_FIELD = "sexe";
    private static final String EMAIL_PATTERN = "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)";

    private IDAO<User> daoUser;
    private Map<String,String> errors;

    public RegisterValidation(IDAO<User> daoUser) {
        this.daoUser = daoUser;
        this.errors = new HashMap<String,String>();
    }


    public Map<String, String> getErrors() {
        return errors;
    }

    public void addError(String field, String error) {
        errors.put(field, error);
    }

    public void passwordValidation(String password) {

        if(password.isEmpty())
            addError(PASSWORD_FIELD, "Vous n'avez pas saisi de mot de passe.");
        else if (password.length() < 5)
            addError(PASSWORD_FIELD, 
                    "Le mot de passe doit contenir au moins 5 caractères.");

    }
    
    public void confirmationValidation(String password, String confirmation) {

        if(confirmation.isEmpty())
            addError(CONFIRMATION_FIELD, "Vous n'avez pas saisi de confirmation.");
        else if (!password.equals(confirmation))
            addError(CONFIRMATION_FIELD, 
                    "La confirmation diffère du mot de passe.");

    }

    public void loginValidation(String login) {

        if(login.isEmpty())
            addError(LOGIN_FIELD, "Vous n'avez pas saisi de login.");
        else if(daoUser instanceof DAOUser)
            if(((DAOUser)daoUser).find(login) != null) 
                addError(LOGIN_FIELD, "Ce login est déjà utilisé.");

    }

    public void presentationValidation(String presentation) {
        if(presentation.isEmpty())
            addError(PRESENTATION_FIELD, "Vous n'avez pas saisi de présentation.");
    }

    public void emailValidation(String email) {

        if(email.isEmpty())
            addError(EMAIL_FIELD, "Vous n'avez pas saisi d'adresse e-mail.");
        else if(!email.matches(EMAIL_PATTERN))
            addError(EMAIL_FIELD, "Merci de saisir une adresse e-mail valide.");
        else if(daoUser instanceof DAOUser) {
            if(((DAOUser)daoUser).findByEmail(email) != null) 
                addError(EMAIL_FIELD, "Cette adresse e-mail est déjà utilisée.");
        }

    }
    
    public void sexeValidation(String sexe) {

        if(sexe.isEmpty())
            addError(SEXE_FIELD, "Veuillez indiquer votre sexe.");
        
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

