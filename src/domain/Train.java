package domain;

import java.io.Serializable;
import java.util.Date;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.*;

@Entity
@Index
public class Train implements Serializable {

	private static final long serialVersionUID = 7754525956851059700L;
	//Id du passage du train qui sera généré par google
	@Id private Long id;
	// Numéro de train 
	private String num;
	//Date de passage du train dans la gare au format date pour pouvoir indexer par la date
	private Date date;
	// Code mission du train
	@Unindex private String miss;
	
	// Terminus du train
	@Unindex private String term;
	
	// Etat remarquable du train
	@Unindex private String etat;
	
	@Parent private Key<Gare> gare;
	
	public Train() {}
	
	public Train(Date date, String num, String miss, String term,
			String etat, Key<Gare> gare) {
		super();
		this.date = date;
		this.num  = num;
		this.miss = miss;
		this.term = term;
		this.etat = etat;
		this.gare = gare;
	}
	
	
	
	/***
	 * Retourne l'id de la gare
	 * 
	 * @return gare
	 */
	public Key<Gare> getGare() {
		return gare;
	}

	/**
	 * Retourne la date de d�part du train
	 * 
	 * @return date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Retourne le numéro du train
	 * 
	 * @return num
	 */
	public String getNum() {
		return num;
	}

	/**
	 * Retourne le code mission du train
	 * 
	 * @return miss
	 */
	public String getMiss() {
		return miss;
	}

	/**
	 * Retourne le terminus du train
	 * 
	 * @return term
	 */
	public String getTerm() {
		return term;
	}

	/**
	 * Retourne l'éat du train
	 * 
	 * @return etat
	 */
	public String getEtat() {
		return etat;
	}
	
    public void setDate(Date date) {
        this.date = date;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    
    public void setMiss(String miss) {
        this.miss = miss;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setTerm(String term) {
        this.term = term;
    }
	
	public void setGare(Key<Gare> gare) {
		this.gare = gare;
	}

}
