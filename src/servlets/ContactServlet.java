package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.EmailSender;

public class ContactServlet extends HttpServlet {

    private static final long serialVersionUID = 5175463349863657818L;
    private static final String CONTACT_JSP  = "/jsp/contact.jsp";
    
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher(CONTACT_JSP).forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        String message = req.getParameter("message");
        
        // A remplacer avec infos sur l'user dans la session ?????
        String senderName = req.getParameter("name");
        String senderAddress = req.getParameter("email");
        
        EmailSender.sendContactEmail(senderName, senderAddress,message);

    }

}
