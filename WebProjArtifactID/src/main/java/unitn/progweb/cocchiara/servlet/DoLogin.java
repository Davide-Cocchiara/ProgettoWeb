package unitn.progweb.cocchiara.servlet;// Import required java libraries

import unitn.progweb.cocchiara.dao.PersonaDAO;
import unitn.progweb.cocchiara.model.Persona;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// Extend HttpServlet class

@WebServlet("/doLogin")
public class DoLogin extends HttpServlet {

    public void init() throws ServletException {
        // Do required initialization
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //String rememberme = request.getParameter("rememberme");
        // TODO
        HttpSession session = request.getSession();
        PersonaDAO dao = new PersonaDAO();
        Persona loggedPersona = dao.getUserCred(username,password);
        if (loggedPersona.getCodicefiscale()!="") { // Login success!
            session.setAttribute("Persona", loggedPersona);
            response.sendRedirect("welcome");
        }
        else {
            response.sendRedirect("login?failedlogin=true");
        }
    }

    public void destroy() {
        // do nothing.
    }
}