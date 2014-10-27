package domain;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class Requete {
	
	private String depart;
	private String arrivee;
	
	public Requete(String depart, String arrivee) {
		super();
		this.depart = depart;
		this.arrivee = arrivee;
	}
	
	public String requeteSNCF(){
		String xml = "";
		if (arrivee.isEmpty()){
			xml = lancerRequete(depart);
		}else{
			xml = lancerRequete(depart, arrivee);
		}
		return xml;
	}
	
	private String lancerRequete(String depart) {
		 ClientConfig config = new DefaultClientConfig();
		 Client client = Client.create(config);
		 client.addFilter(new HTTPBasicAuthFilter("upmc115", "Ej2fSb74"));
		 WebResource service = client.resource(getBaseURI());
		 return service.path("gare").path(depart).path("depart").
				 accept(MediaType.APPLICATION_XML).get(String.class);
	}
	
	private String lancerRequete(String depart, String arrivee) {
		 ClientConfig config = new DefaultClientConfig();
		 Client client = Client.create(config);
		 client.addFilter(new HTTPBasicAuthFilter("upmc115", "Ej2fSb74"));
		 WebResource service = client.resource(getBaseURI());
		 return service.path("gare").path(depart).path("depart").
				 path(arrivee).accept(MediaType.APPLICATION_XML).get(String.class);
	}
	
	private static URI getBaseURI() {
	    return UriBuilder.fromUri("http://api.transilien.com").build();
	}

}
