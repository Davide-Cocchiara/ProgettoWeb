package unitn.progweb.cocchiara.servlet;// Import required java libraries
import unitn.progweb.cocchiara.model.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// Extend HttpServlet class

@WebServlet("/welcome")
public class Welcome extends HttpServlet {

    public void init() throws ServletException {
        // Do required initialization
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set response content type
        HttpSession session = request.getSession();
        Utente u = (Utente) session.getAttribute("utente");

        if (u.isMedico() || u.isAdmin()) {
            response.sendRedirect(request.getContextPath()+"/servizi");
        }
        else {
            response.sendRedirect(request.getContextPath()+"/sispaz/profilocittadino");
        }
    }

    public void destroy() {
        // do nothing.
    }
}