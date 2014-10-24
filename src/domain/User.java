package domain;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.annotation.*;

@Entity
@Index
public class User implements Serializable {
	
	private static final long serialVersionUID = -678103706880391625L;
	//L'Id sera g�n�r� par nous
	@Id private String login;
	private String password;
	private String firstname;
	private String lastname;
	private String email;
	private String presentation;
	private String sexe;
	private int age;
	@Serialize private List<String> interests;
	//private Key<User> listeamis;
	//private Key<Train>
	//private horaire
	
	
	
	public User() {
	    //this.interests = new ArrayList<String>();
	}
	
	public User(String pseudo,String mdp,String email,String nom,String prenom,String presentation,List<String> interests,int age, String sexe){
		this.login = pseudo;
		this.password = mdp;
		this.email = email;
		this.lastname = nom;
		this.firstname = prenom;
		this.presentation = presentation;
		this.interests = interests;
		this.age = age;
		this.sexe = sexe;
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
	
	public int getAge(){
		return age;
	}
	
	public void setAge(int age){
		this.age = age;
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

    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }

}
