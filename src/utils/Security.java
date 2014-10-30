package utils;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

public class Security {

	private static final String ENCRYPTION_ALGO = "SHA-256";
	
	/*
	   Utilisation de la bibliothèque Jasypt pour chiffrer le mot de passe
	   efficacement.
	     
	   L'algorithme SHA-256 est ici utilisé, avec par défaut un salage
	   aléatoire et un grand nombre d'itérations de la fonction de hashage.
	   La String retournée est de longueur 56 et contient le hash en Base64.
	*/
	public static String encryptPassword(String password) {
		ConfigurablePasswordEncryptor passwordEncryptor = 
				new ConfigurablePasswordEncryptor();
		passwordEncryptor.setAlgorithm(ENCRYPTION_ALGO);
		passwordEncryptor.setPlainDigest( false );
		return passwordEncryptor.encryptPassword(password);
	}
	
	public static boolean checkPassword(String plainPassword,
			String encryptedPassword) {
		ConfigurablePasswordEncryptor passwordEncryptor = 
				new ConfigurablePasswordEncryptor();
		passwordEncryptor.setAlgorithm(ENCRYPTION_ALGO);
		passwordEncryptor.setPlainDigest( false );
		return passwordEncryptor.checkPassword(plainPassword, encryptedPassword);
	}
	
}
