package unitn.progweb.cocchiara.servlet;// Import required java libraries

import unitn.progweb.cocchiara.model.Persona;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

// Extend HttpServlet class

@WebServlet("/avatar")
public class Avatar extends HttpServlet {

    public void init() throws ServletException {
        // Do required initialization
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set response content type
        HttpSession session = request.getSession();
        Persona persona = (Persona) session.getAttribute("Persona");
        response.setContentType("image/gif");
        if (persona.getFoto() != null) {
            OutputStream o = response.getOutputStream();
            o.write(persona.getFoto());
            o.flush();
            o.close();
        }
        else {
            response.sendRedirect(request.getContextPath() + "/assets/img/utente.jpg");
        }

    }

    public void destroy() {
        // do nothing.
    }
}