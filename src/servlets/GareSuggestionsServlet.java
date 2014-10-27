package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GareSuggestionsServlet extends HttpServlet {

    private static final long serialVersionUID = 662834190109807098L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String query = req.getParameter("query");
        resp.setContentType("application/json");

        //... = ofy().load().type(MyEntity.class).filter("field >=", start).filter("field <", start + "\uFFFD"); 
        // En vrai chercher dans la bd en se servant de query
        // Par exemple doit commencer par query
        List<String> list = new ArrayList<String>();
        list.add("Gare1");
        list.add("Gare2");
        list.add("Test");
        Map<String,List<String>> map = new HashMap<String,List<String>>();
        map.put("suggestions", list);

        Gson gson = (new GsonBuilder()).create();

        // On cree une representation JSON de notre map, 
        // sous la forme d'une chaine de caracteres.
        String json = gson.toJson(map);

        // On ecrit du JSON sur le flux de sortie de la reponse HTTP
        PrintWriter pw = new PrintWriter(resp.getOutputStream());

        pw.print(json);
        pw.flush();
    
        
    }

    
    
}
