package utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

    @Override
    public void destroy() { }

    @Override
    public void doFilter(ServletRequest req, ServletResponse rep,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) rep;
        HttpSession session = request.getSession(false);

        String requestPath = request.getRequestURI();

        if( needsAuthentication(requestPath) && 
            (session == null || session.getAttribute("login") == null))
            // Pas dutilisateur loge trouve, on redirige vers la page de login.
            response.sendRedirect(request.getContextPath() + "/login");

        else {
            // Le login de lutilisateur a ete trouve, on continue la requete.
            chain.doFilter(req, rep);
        }

    }

    @Override
    public void init(FilterConfig fconfig) throws ServletException { }

    private boolean needsAuthentication(String url) {
        String[] validNonAuthenticationUrls =
            { "login", "register", "logout" };
        for(String validUrl : validNonAuthenticationUrls) {
            if (url.endsWith(validUrl)) {
                return false;
            }
        }
        return true;
    }
    
}
