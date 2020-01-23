package unitn.progweb.cocchiara.servlet.sispaz;// Import required java libraries


import unitn.progweb.cocchiara.model.SistemaNazionale;
import unitn.progweb.cocchiara.model.Utente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

// Extend HttpServlet class

@WebServlet("/sispaz/changemedico")
public class ChangeMedico extends HttpServlet {

    public void init() throws ServletException {
        // Do required initialization
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Boolean error = false;
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        String s_nuovomedico = (String) request.getParameter("nuovomedico");
        Integer nuovomedico = null;
        try {
            nuovomedico = Integer.parseInt(s_nuovomedico);
        } catch (NumberFormatException e) {
            error = true;
        }

        // Get list of medici
        ArrayList<String> listaCodiciMedici = (ArrayList<String>) session.getAttribute("codicefiscalemedici");
        session.setAttribute("codicefiscalemedici",null); // No longer needed.
        if (nuovomedico!= null && nuovomedico>=0 && nuovomedico<listaCodiciMedici.size() && !error) { // If range is valid
            utente.getPaziente().setMedicoAssegnato(listaCodiciMedici.get(nuovomedico)); // Set medico with index as codice fiscale
            response.sendRedirect(request.getContextPath() + "/sispaz/infomedico?changedmedico=true");
        } else {
            response.sendRedirect(request.getContextPath() + "/sispaz/infomedico?changedmedico=false");
        }



        }

    public void destroy() {
        // do nothing.
    }
}