package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;

import dao.DAOFactory;
import dao.IDAOUser;

// Servlet qui permet denvoyer le message recu par le serveur au destinaire de celui-ci.
public class ChatServlet extends HttpServlet {

    private static final long serialVersionUID = -7865735291402742889L;
    
    private IDAOUser daoUser;
    
    @Override
    public void init() throws ServletException {
        super.init();
        daoUser = DAOFactory.createDAOUser();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
      System.out.println("Do POST");
        
      // login du destinataire
      String channelKey = req.getParameter("channelKey");
      
      String sender = req.getParameter("sender");
      String message = req.getParameter("message");
      
      message = sender + "/" + message;
      
      // Envoi un message base sur le 'channelKey' 
      // nimporte quel channel avec cette key va recevoir le message (le destinataire)
      ChannelService channelService = ChannelServiceFactory.getChannelService();
      
      channelService.sendMessage(new ChannelMessage(channelKey, message));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        System.out.println("Do GET");
        
        HttpSession session = req.getSession();
      
        Object o = session.getAttribute("login");
        if(o != null) {
            
            String login = (String)session.getAttribute("login");
            String chatToken = daoUser.find(login).getChatToken();
            
            // Envoi au client du chatToken
            // Le client peut ainsi commencer a utiliser le channel
            resp.setContentType("text/plain");
            resp.getWriter().print(chatToken);

        }
    }
}
