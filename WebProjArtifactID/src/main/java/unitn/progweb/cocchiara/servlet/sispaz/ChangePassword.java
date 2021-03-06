package unitn.progweb.cocchiara.servlet.sispaz;// Import required java libraries

import unitn.progweb.cocchiara.model.Utente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// Extend HttpServlet class

@WebServlet("/sispaz/changepassword")
public class ChangePassword extends HttpServlet {

    public void init() throws ServletException {
        // Do required initialization
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String password = request.getParameter("password");
        String newpassword = request.getParameter("newpassword");

        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        if (password != null && newpassword != null && utente.changePassword(password,newpassword)) {
            response.sendRedirect(request.getContextPath() + "/sispaz/profilocittadino?newpassword=true");
        }
        else {
            response.sendRedirect(request.getContextPath() + "/sispaz/profilocittadino?newpassword=false");
        }

    }

    public void destroy() {
        // do nothing.
    }
}