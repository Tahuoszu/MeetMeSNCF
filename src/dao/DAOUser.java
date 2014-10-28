package dao;

import java.util.List;

import domain.User;

import com.googlecode.objectify.ObjectifyService;
import static com.googlecode.objectify.ObjectifyService.ofy;

public class DAOUser implements IDAO<User> {
	
	static {
        ObjectifyService.register(User.class);
    }
	
	/**
	 * Méthode permettant d'ajouter un utilisateur à la base de données.
	 */
	@Override
	public void add(User object) {
		ofy().save().entity(object);
	}

	/**
	 * Méthode permettant de trouver un utilisateur dans la base de données, 
	 * avec son identifiant.
	 */
	public User find(String login) {
		return ofy().load().type(User.class).id(login).now();
	}
	

	/**
	 * Méthode permettant de trouver une liste d'utilisateur commençant par la
	 * query de son identifiant dans la base de données.
	 */
	public List<User> findUser(String login) {
		return ofy().load().type(User.class).filter("login ==", login).list();
	}
	
	/**
	 * Méthode permettant de trouver un utilisateur dans la base de données, 
	 * avec son identifiant et son mot de passe.
	 */
	public User find(String login, String password) {
		User u = ofy().load().type(User.class).id(login).now();
		if (u == null)
			return null;
		if (u.getPassword() != password) {
			return null;
		}
		return u;
	}
	
	public User findByEmail(String email) {
		List<User> lu = ofy().load().type(User.class).filter("email ==", email).list();
		if(lu == null)
			return null;
		if(lu.isEmpty())
			return null;
		return lu.get(0);
	}

	/**
	 * Méthode permettant de supprimer un utilisateur de la base de données.
	 */
	@Override
	public void remove(User object) {
		ofy().delete().type(User.class).id(object.getLogin()).now();
		
	}

	/**
	 * Méthode permettant de mettre à jour un utilisateur de la base de données.
	 */
	@Override
	public void update(User object) {
		ofy().save().entity(object);
	}

	
}
