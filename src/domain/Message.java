package domain;
import java.util.Date;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.*;

@Entity
public class Message {
	@Id private long id;
	@Index private String message;
	@Parent Key<User> sender;
	@Parent Key<User> receiver;
	@Index private Date date; //J'ai un doute sur le import
	
	public Message (){}
	public Message (String message,Key<User> sender,Key<User> receiver){
		this.message = message;
		this.sender = sender;
		this.receiver = receiver;
	}
	
	public String getMessage(){
		return message;
	}
	
	public void setMessage(String message){
		this.message = message;
	}
	
	public Key<User> getSender(){
		return sender;
	}
	
	public void setSender(Key<User> sender){
		this.sender = sender;
	}
	
	public Key<User> getReceiver(){
		return receiver;
	}
	
	public void setReceiver(Key<User> receiver){
		this.receiver = receiver;
	}
}
