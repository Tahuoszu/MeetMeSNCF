package domain;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
@Entity
@Index
public class Train implements Serializable {

	private static final long serialVersionUID = 7754525956851059700L;

	// Date de passage du train dans la gare
	@Id private String date;
	
	//Date de passage du train dans la gare au format date pour pouvoir indexer par la date
	//private Date dateDate;
	//mettre dans le phraseur : Date(int year, int month, int date, int hrs, int min) pour cr�er le type date
	
	// Numéro de train (UIC)
	private String num;
	
	// Code mission du train
	private String miss;
	
	// Terminus du train
	private String term;
	
	// Etat remarquable du train
	private String etat;
	
	public Train() {}
	
	public Train(String date, String num, String miss, String term,
			String etat) {
		super();
		this.date = date;
		this.num  = num;
		this.miss = miss;
		this.term = term;
		this.etat = etat;
	}

	/**
	 * Retourne le nom de la gare
	 * 
	 * @return date
	 */
	public String getDate() {
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
	
    public void setDate(String date) {
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
	
}
