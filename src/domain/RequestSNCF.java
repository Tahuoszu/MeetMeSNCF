package domain;

import java.io.Serializable;

public class RequestSNCF implements Serializable {

	private static final long serialVersionUID = 7754525956851059700L;

	// Date de passage du train dans la gare
	private final String name;
	
	// Numéro de train
	private final String num;
	
	// Code mission du train
	private final String miss;
	
	// Terminus du train
	private final String term;
	
	// Etat remarquable du train
	private final String etat;
	
	public RequestSNCF(String name, String num, String miss, String term,
			String etat) {
		super();
		this.name = name;
		this.num  = num;
		this.miss = miss;
		this.term = term;
		this.etat = etat;
	}

	/**
	 * Retourne le nom de la gare
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
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
	
}
