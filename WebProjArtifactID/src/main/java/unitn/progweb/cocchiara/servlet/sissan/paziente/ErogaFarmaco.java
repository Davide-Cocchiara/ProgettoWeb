package unitn.progweb.cocchiara.servlet.sissan.paziente;// Import required java libraries


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
import java.io.PrintWriter;
import java.util.Date;
import java.util.LinkedHashMap;

// Extend HttpServlet class

@WebServlet("/sissan/paziente/erogafarmaco")
public class ErogaFarmaco extends HttpServlet {

    public void init() throws ServletException {
        // Do required initialization
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        Paziente selectedpaziente = (Paziente) session.getAttribute("selectedprovinciapaziente");

        String selectedprestazione = request.getParameter("idprescrizione");

        Boolean error=false;
        Integer idprescrizione = null;
        try {
            idprescrizione = Integer.parseInt(selectedprestazione);
        } catch (NumberFormatException e) {
            error = true;
        }

        SistemaProvinciale sp = new SistemaProvinciale(utente.getPaziente().getProvincia());

        if(!error && (selectedprestazione != null) && sp != null &&
                sp.erogaFarmaco(idprescrizione))
        {
            PrintWriter p = response.getWriter();
            p.write("true");
            p.close();

        } else {

            PrintWriter p = response.getWriter();
            p.write("false");
            p.close();

        }


    }

    public void destroy() {
        // do nothing.
    }
}