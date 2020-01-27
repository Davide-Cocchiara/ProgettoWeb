package unitn.progweb.cocchiara.servlet.sissan;// Import required java libraries

import unitn.progweb.cocchiara.model.Paziente;
import unitn.progweb.cocchiara.model.SistemaProvinciale;
import unitn.progweb.cocchiara.model.Utente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// Extend HttpServlet class

@WebServlet("/sissan/selectprovinciapaziente")
public class SelectProvinciaPaziente extends HttpServlet {

    public void init() throws ServletException {
        // Do required initialization
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { ;
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        String s_selectedprovinciapaziente = request.getParameter("selectedprovinciapaziente");

        if (s_selectedprovinciapaziente != null) {
            // Check that asked paziente is actually of medico
            Paziente selectedprovinciapaziente = new SistemaProvinciale(utente.getPaziente().getProvincia()).getPazienteOfProvincia(s_selectedprovinciapaziente);
            if (selectedprovinciapaziente != null) {
                session.setAttribute("selectedprovinciapaziente", selectedprovinciapaziente);
                response.sendRedirect(request.getContextPath() + "/sissan/paziente/infopaziente");
            } else {
                response.sendRedirect(request.getContextPath() + "/sissan/pazientilista");
            }
        }
        else {
            response.sendRedirect(request.getContextPath() + "/sissan/pazientilista");
        }
    }

    public void destroy() {
        // do nothing.
    }
}