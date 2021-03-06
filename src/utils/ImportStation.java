package utils;

import java.io.*;
import java.util.ArrayList;
//import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
//import java.util.Map;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import au.com.bytecode.opencsv.CSVReader;
import dao.DAOGare;
import domain.Gare;

public class ImportStation {

	// Fichier CVS contenant les informations sur les gares
	private final static String FILENAME = "sncf-lignes-par-gares-idf.csv";
	
	// Chemin du fichier contenant la liste des gares
	private final static String STATIONS = "src/liste_gares.txt";
	
	// Première ligne du fichier CVS
	private final static String[] ARRAY = {"CODE_UIC", "LIBELLE_POINT_ARRET",
		"TRAIN", "RER", "TRAM", "BUS", "A", "B", "C", "D", "E", "H", "J", "K",
		"L", "N", "P", "R", "U", "T4", "TER"};
	
	/**
	 * Importe les stations dans la base de données DataStore à partir de la
	 * liste des gares
	 */
	public static void init() {
		// Récupère les gares à ajouter dans la base de données
		List<Gare> gares = filterGare();
		// Ajoute les gares dans la base de données DataStore
		addGare(gares);
	}
	
	/**
	 * Récupère la liste des gares avec les lignes associées à partir du fichier
	 * CSV fournit par l'API GTFS
	 * 
	 * @return liste des gares
	 */
	public static List<Gare> getGareList() {
		List<Gare> gares = new LinkedList<Gare>();
		// Création du flux d'entrée
		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(FILENAME);
		BufferedReader buf = new BufferedReader(new InputStreamReader(is));
		// Ecriture et lecture du buffer
		try {
			CSVReader reader = new CSVReader(buf, ';');
			String[] row = null;
			//Map<String, String> lines;
			List<String> lines;
			// On saute la première ligne.
			row = reader.readNext();
			while ((row = reader.readNext()) != null) {
				lines = reduceArray(row);
				gares.add(new Gare(row[0], row[1], lines));
			}
			reader.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return gares;
	}
	
	/**
	 * Réduit le tableau de buffer et retourne la liste des lignes passat par la
	 * gare.
	 * 
	 * @param lines
	 * @return
	 */
	public static List<String> reduceArray(String[] row) {
		List<String> lines = new ArrayList<String>();
		for (int i = 6; i < row.length; i++) {
			// Si la case est vide, on la saute.
			if (row[i].isEmpty())
				continue;
			// Liste des RER
			if (!row[3].isEmpty() && i < 11)
				lines.add(ARRAY[i]);
			// Liste des bus
			else if (row[3].isEmpty() && i < 11)
				lines.add(ARRAY[i]);
			else if (!row[5].isEmpty() && i >= 11)
				lines.add(ARRAY[i]);
			// Liste des trams
			else if (!row[4].isEmpty() && i == 19)
				lines.add(ARRAY[i]);
			// Liste des trains
			else if (!row[2].isEmpty() && i >= 11)
				lines.add(ARRAY[i]);
			// Liste des TER (= trains)
			else if (!row[2].isEmpty() && i == 20)
				lines.add(ARRAY[i]);
			else
				System.out.print("");
		}
		return lines;
	}
	
	/**
	 * Réduit le tableau de buffer et retourne la liste des lignes passat par la
	 * gare.
	 * 
	 * @param lines
	 * @return
	 */
	/*public static Map<String, String> reduceArray(String[] row) {
		Map<String, String> lines = new HashMap<String, String>();
		for (int i = 6; i < row.length; i++) {
			// Si la case est vide, on la saute.
			if (row[i].isEmpty())
				continue;
			// Liste des RER
			if (!row[3].isEmpty() && i < 11)
				lines.put(ARRAY[i], ARRAY[3]);
			// Liste des bus
			else if (row[3].isEmpty() && i < 11)
				lines.put(ARRAY[i], ARRAY[5]);
			else if (!row[5].isEmpty() && i >= 11)
				lines.put(ARRAY[i], ARRAY[5]);
			// Liste des trams
			else if (!row[4].isEmpty() && i == 19)
				lines.put(ARRAY[i], ARRAY[4]);
			// Liste des trains
			else if (!row[2].isEmpty() && i >= 11)
				lines.put(ARRAY[i], ARRAY[2]);
			// Liste des TER (= trains)
			else if (!row[2].isEmpty() && i == 20)
				lines.put(ARRAY[i], ARRAY[2]);
			else
				System.out.print("");
		}
		return lines;
	}*/
	
	/**
	 * Ajoute les gares dans la base de données DataStore
	 * 
	 * @param gares
	 */
	public static void addGare(List<Gare> gares) {
		DAOGare daogare = new DAOGare();
		Key cle = KeyFactory.createKey("ListeGares", "ListeGares");
		daogare.remove();
		for (Gare gare : gares) {
			daogare.remove(gare);
			gare.setKey(cle);
			daogare.add(gare);
		}
	}
	
	/**
	 * Récupère la liste des gares avec leur code UIC à partir d'un fichier
	 * 
	 * @return liste des gares avec leur code UIC
	 */
	private static List<String> readGareFromFile() {
		List<String> liste = new LinkedList<String>();
		try {
			BufferedReader buff = new BufferedReader(
					new InputStreamReader(new 
				      java.io.FileInputStream(STATIONS), "ISO-8859-15"));
			try {
				String line, gare, uic;
				int line_length;
				while ((line = buff.readLine()) != null) {
					line_length = line.length();
					gare = line.substring(0, line_length - 8);
					uic = line.substring(line_length - 8, line_length);
					//System.out.println(gare + "\t" + uic);
					liste.add(uic);
				}
			} finally {
				buff.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return liste;
	}
	
	/**
	 * Filtre les gares à ajouter dans la base de données Datastore
	 * 
	 * @return liste des gares
	 */
	public static List<Gare> filterGare() {
		List<String> uic = readGareFromFile();
		List<Gare> gares = getGareList();
		List<Gare> liste = new LinkedList<Gare>();
		for(String l : uic) {
			for (Gare g : gares) {
				if (g.getUIC().equals(l)) {
					liste.add(g);
				}
			}
		}
		return liste;
	}
	
}
