package dao;

import static com.googlecode.objectify.ObjectifyService.ofy;
import domain.Message;

public class DAOMessage implements IDAO<Message>{


	@Override
	public void add(Message object) {
		// TODO Auto-generated method stub
		ofy().save().entity(object);
	}

	@Override
	public void remove(Message object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Message object) {
		// TODO Auto-generated method stub
		ofy().save().entity(object);
	}

	

}
