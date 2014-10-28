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
//	private int rolling;
	
	public Requete(String depart, String arrivee) {
		super();
		this.depart = depart;
		this.arrivee = arrivee;

	}
	
	public String requeteSNCF(){
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		client.addFilter(new HTTPBasicAuthFilter("upmc115", "Ej2fSb74"));
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
