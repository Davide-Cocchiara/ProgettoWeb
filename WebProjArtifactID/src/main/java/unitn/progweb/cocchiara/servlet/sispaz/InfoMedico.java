package unitn.progweb.cocchiara.servlet.sispaz;// Import required java libraries

import unitn.progweb.cocchiara.dao.MedicoDAO;
import unitn.progweb.cocchiara.dao.UtenteDAO;

import unitn.progweb.cocchiara.model.SistemaProvinciale;
import unitn.progweb.cocchiara.model.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

// Extend HttpServlet class

@WebServlet("/sispaz/infomedico")
public class InfoMedico extends HttpServlet {

    public void init() throws ServletException {
        // Do required initialization
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        if (utente.getPaziente().getMedicoAssegnato() != null && !utente.getPaziente().getMedicoAssegnato().equals("")) { // If user has medico assegnato
            Utente medicoassegnato = new UtenteDAO().getUserFromCodice(utente.getPaziente().getMedicoAssegnato()); // Get medico assegnato as Utente
            request.setAttribute("medicoassegnato",medicoassegnato); // Set medic info to request
        } else {
            request.setAttribute("medicoassegnato", null); // Set medic info to none
        }

        // Get list of Medici of given province.
        SistemaProvinciale prov = new SistemaProvinciale(utente.getPaziente().getProvincia());
        LinkedHashMap<String, String> infomedici = prov.getListaMedici();
        // Divide the map into two lists with same order.
        ArrayList<String> listamedici = new ArrayList<String>(infomedici.values());
        ArrayList<String> codicefiscalemedici = new ArrayList<String>(infomedici.keySet());

        Integer selectedmedico = null;
        // Get correct index of existing medico.
         if (utente.getPaziente().getMedicoAssegnato() != null) {
             selectedmedico = codicefiscalemedici.indexOf(utente.getPaziente().getMedicoAssegnato());
        } else {
             selectedmedico = 0;
         }

        request.setAttribute("listamedici",listamedici);
        request.setAttribute("selectedmedico",selectedmedico);
        session.setAttribute("codicefiscalemedici",codicefiscalemedici);


        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/sispaz/info-medico.jsp");
        rd.forward(request, response);
    }

    public void destroy() {
        // do nothing.
    }
}