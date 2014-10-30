package tests;

import java.util.ArrayList;

import dao.DAOFactory;
import dao.IDAOUser;
import domain.User;

public class testUser {
	
	public static void createTestUsers () {
		IDAOUser daoUser = DAOFactory.createDAOUser();
		String[] pseudo = {"Saucisse", "Jambon", "Fromage", "Raclette", "Pain",
				"Charcuterie", "Tartiflette", "Serrano", "Patate", "Cornichon"};
		String[] password = {"12345", "12345", "12345", "12345", "12345",
				"12345", "1345", "12345", "12345", "12345"};
		String[] email = {"1@1.1", "2@2.2", "3@3.3", "4@4.4", "5@5.5", "6@6.6",
				"7@7.7", "8@8.8", "9@9.9", "0@0.0"};
		String[] nom = {"Ducon", "Dupré", "Ducu", "Durant", "Dur", "Durian",
				"Dusac", "Ducie", "Dusteak", "Dutard"};
		String[] prenom = {"Alice", "Bob", "Charlie", "Dante", "Elise",
				"Franck", "Gaël", "Hugo", "Ida", "Jean"};
		int[] age = {15, 90, 42, 20, 19, 32, 35, 28, 14, 49};
		String[] sexe = {"Féminin", "Masculin", "Masculin", "Masculin",
				"Féminin", "Masculin", "Masculin", "Masculin", "Féminin",
				"Masculin"};
		for (int i = 0; i < 10; i++) {
			daoUser.add(new User(pseudo[i], password[i], email[i], nom[i],
					prenom[i], "", new ArrayList<String>(), age[i], sexe[i]));
		}
	}

}
