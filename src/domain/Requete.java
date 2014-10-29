package domain;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class Requete {
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	private String depart;
	private String arrivee;
	
	public Requete(String depart, String arrivee) {
		super();
		this.depart = depart;
		this.arrivee = arrivee;

	}
	
	public String requeteSNCF(){
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		//on load l'index
		Key cleindex = null;
		Entity index = new Entity("Index", "ind");
		index.setProperty("nombre", 0);
		int idx = 0;
		try {
			cleindex = KeyFactory.createKey("Index", "ind");
			index =datastore.get(cleindex);
			idx = ((Long) index.getProperty("nombre")).intValue();
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			idx = 0;
		}
		int temp = (int) (idx % 60);
		//on entre le basic authentification
		if (temp < 20)
			client.addFilter(new HTTPBasicAuthFilter("upmc115", "Ej2fSb74"));
		if (temp >= 20 && temp < 40)
			client.addFilter(new HTTPBasicAuthFilter("upmc116", "8Nyz38aD"));
		if (temp >= 40 && temp <= 59)
			client.addFilter(new HTTPBasicAuthFilter("upmc117", "7F5Aaum3"));

		//on incrémente l'index
		idx ++;
		index.setProperty("nombre", idx);
		//on save l'index
		datastore.put(index);
		WebResource service = client.resource(getBaseURI());
		if (arrivee.isEmpty()){
			return service.path("gare").path(depart).path("depart").
					 accept(MediaType.APPLICATION_XML).get(String.class);
		}else{
			return service.path("gare").path(depart).path("depart").
					 path(arrivee).accept(MediaType.APPLICATION_XML).get(String.class);
		}
	}
	
	private static URI getBaseURI() {
	    return UriBuilder.fromUri("http://api.transilien.com").build();
	}

}
