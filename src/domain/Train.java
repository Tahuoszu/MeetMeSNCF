package domain;

import java.io.Serializable;
import java.util.Date;

import com.googlecode.objectify.annotation.*;

@Entity
@Index
public class Train implements Serializable {

	private static final long serialVersionUID = 7754525956851059700L;
	// Numéro de train (UIC)
	@Id private String num;
	
	//Date de passage du train dans la gare au format date pour pouvoir indexer par la date
	private Date date;
	//mettre dans le phraseur : Date(int year, int month, int date, int hrs, int min) pour cr�er le type date
	
	// Code mission du train
	private String miss;
	
	// Terminus du train
	private String term;
	
	// Etat remarquable du train
	private String etat;
	
	public Train() {}
	
	public Train(Date date, String num, String miss, String term,
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
	
}
