package unitn.progweb.cocchiara.servlet.sispaz;// Import required java libraries

import unitn.progweb.cocchiara.dao.MedicoDAO;
import unitn.progweb.cocchiara.dao.UtenteDAO;
import unitn.progweb.cocchiara.model.Medico;

import unitn.progweb.cocchiara.model.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Extend HttpServlet class

@WebServlet("/sispaz/infomedico")
public class InfoMedico extends HttpServlet {

    public void init() throws ServletException {
        // Do required initialization
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        if (utente.getPaziente().getMedicoAssegnato() != null && utente.getPaziente().getMedicoAssegnato() != "") { // If user has medico assegnato
            Utente medicoassegnato = new UtenteDAO().getUserCodice(utente.getPaziente().getMedicoAssegnato()); // Get medico assegnato info
            request.setAttribute("medicoassegnato",medicoassegnato); // Set medic info to request
        } else {
            request.setAttribute("medicoassegnato", null); // Set medic info to none
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/sispaz/info-medico.jsp");
        rd.forward(request, response);
    }

    public void destroy() {
        // do nothing.
    }
}