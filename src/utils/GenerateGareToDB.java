package utils;

import com.google.appengine.api.datastore.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class GenerateGareToDB {
	
	// Chemin du fichier contenant la liste des gares
	private final static String FILEPATH = "liste_gares.txt";

	public static void init() throws IOException {
		Map<String, String> listeGares = readGareFromFile();
		addGareToDB(listeGares);
		System.out.println("Liste des gares ajoutée au DataStore !");
	}
	
	private static Map<String, String> readGareFromFile() throws IOException {
		Map<String, String> liste = new HashMap<String, String>();
		try {
			BufferedReader buff = new BufferedReader(
					new InputStreamReader(new 
				      java.io.FileInputStream(FILEPATH), "ISO-8859-15"));
			try {
				String line, gare, uic;
				int line_length;
				while ((line = buff.readLine()) != null) {
					line_length = line.length();
					gare = line.substring(0, line_length - 8);
					uic = line.substring(line_length - 8, line_length - 1);
					System.out.println(gare + "\t" + uic);
					liste.put(gare, uic);
				}
			} finally {
				buff.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return liste;
	}
	
	private static void addGareToDB(Map<String, String> liste) {
		DatastoreService datastore;
		datastore = DatastoreServiceFactory.getDatastoreService();
		for (Entry<String, String> g : liste.entrySet()) {
			Entity gare = new Entity("Gare",
					KeyFactory.createKey("ListeGares", "ListeGares"));
			gare.setProperty("UIC", g.getValue());
			gare.setProperty("name", g.getKey());
			datastore.put(gare);
		}
	}
}
