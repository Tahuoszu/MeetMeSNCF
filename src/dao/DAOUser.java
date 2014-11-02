package dao;

import java.util.List;

import utils.Security;
import domain.Gare;
import domain.Train;
import domain.User;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class DAOUser implements IDAOUser {
	
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
	 * Méthode permettant de trouver un utilisateur dans la base de données, 
	 * avec son identifiant et son mot de passe.
	 */
	public User find(String login, String password) {
		User u = ofy().load().type(User.class).id(login).now();
		if (u == null)
			return null;
		if (Security.checkPassword(password, u.getPassword()))
			return u;
		else
			return null;
	}

	/**
	 * Méthode permettant de trouver une liste d'utilisateur commençant par la
	 * query de son identifiant dans la base de données.
	 */
	public List<User> findUser(String login) {
		return ofy().load().type(User.class).filter("login ==", login).list();
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

    @Override
    public List<User> findConnectedUsers() {
        return ofy().load().type(User.class).filter("connected ==", true).list();
    }
    
    @Override
    public void updateTrain( String login, String num, String uic){
    	User moi = find(login);
    	Train train = (new DAOTrain()).find(num, Key.create(Gare.class,uic));
    	moi.setTrain(train.getKey());
    	update(moi);
    }
    
    @Override
    public List<User> findUsers (String train, String uic){
    	Train t = (new DAOTrain()).find(train, Key.create(Gare.class,uic));
    	List<User> lu = ofy().load().type(User.class).filter("train ==", t.getKey()).list();
    	return lu;
    }

}
