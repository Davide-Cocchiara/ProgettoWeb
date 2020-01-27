package unitn.progweb.cocchiara.servlet.sismed.paziente;// Import required java libraries

import unitn.progweb.cocchiara.model.Paziente;
import unitn.progweb.cocchiara.model.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

// Extend HttpServlet class

@WebServlet("/sismed/paziente/nuovavisita")
public class NuovaVisita extends HttpServlet {

    public void init() throws ServletException {
        // Do required initialization
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { ;
        String prestazionedropdown = "";
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        Paziente selectedpaziente = (Paziente) session.getAttribute("selectedpaziente");
        LinkedHashMap<String,Map.Entry<String,String>> listaprestazioni = utente.getMedico().getListEsamiRefertabiliPaziente(selectedpaziente.getCodicefiscale(), selectedpaziente.getProvincia());
        if (listaprestazioni!= null) {
            for (Map.Entry<String,Map.Entry<String,String>> entry :listaprestazioni.entrySet()) {
                prestazionedropdown+= "<option value=\"";
                prestazionedropdown+= entry.getKey();
                prestazionedropdown+= "\">";
                prestazionedropdown+= entry.getValue().getKey() + " (" + entry.getValue().getValue() + "€)";
                if(!entry.getKey().equals("-1")) {
                    prestazionedropdown += " - Ricetta #";
                    prestazionedropdown += entry.getKey();
                }
                prestazionedropdown+="</option>";
            }
            session.setAttribute("listaprestazioni",listaprestazioni);
            request.setAttribute("prestazionedropdown",prestazionedropdown);

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/sismed/paziente/nuova-visita.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/error.jsp");
            rd.forward(request, response);
        }

    }

    public void destroy() {
        // do nothing.
    }
}