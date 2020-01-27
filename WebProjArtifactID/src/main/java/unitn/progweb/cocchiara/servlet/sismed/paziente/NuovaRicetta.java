package unitn.progweb.cocchiara.servlet.sismed.paziente;// Import required java libraries

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
import java.util.LinkedHashMap;
import java.util.Map;

// Extend HttpServlet class

@WebServlet("/sismed/paziente/nuovaricetta")
public class NuovaRicetta extends HttpServlet {

    public void init() throws ServletException {
        // Do required initialization
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String esamespecialisticodropdown = "";
        String esamelaboratoriodropdown = "";
        String farmacodropdown = "";
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        SistemaProvinciale sistema=  new SistemaProvinciale(utente.getPaziente().getProvincia());

        // Get lista esami and farmaci of Medico's provincia, and use it's ids as values in dropdown menu
        LinkedHashMap<String,String> listaesamiSpecialistici = sistema.getListaEsamiDisponibili(false);
        LinkedHashMap<String,String> listaesamiLaboratorio = sistema.getListaEsamiDisponibili(true);
        LinkedHashMap<String,String> listafarmaci=  sistema.getListaFarmaciDisponibili();

        if (  listaesamiSpecialistici!= null && listaesamiLaboratorio!= null && listafarmaci != null) {
            //same_dropdown += "";

            for (Map.Entry<String,String> entry :listaesamiSpecialistici.entrySet()) {
                esamespecialisticodropdown+= "<option value=\"";
                esamespecialisticodropdown+= entry.getKey();
                esamespecialisticodropdown+= "\">";
                esamespecialisticodropdown+=entry.getValue();
                esamespecialisticodropdown+="</option>";
            }

            for (Map.Entry<String,String> entry :listaesamiLaboratorio.entrySet()) {
                esamelaboratoriodropdown += "<option value=\"";
                esamelaboratoriodropdown += entry.getKey();
                esamelaboratoriodropdown += "\">";
                esamelaboratoriodropdown += entry.getValue();
                esamelaboratoriodropdown += "</option>";
            }

            for (Map.Entry<String,String> entry :listafarmaci.entrySet()) {
                farmacodropdown+= "<option value=\"";
                farmacodropdown+= entry.getKey();
                farmacodropdown+= "\">";
                farmacodropdown+=entry.getValue();
                farmacodropdown+="</option>";
            }
            session.setAttribute("listaesamispec",listaesamiSpecialistici);
            session.setAttribute("listaesamilab",listaesamiLaboratorio);
            session.setAttribute("listafarmaci",listafarmaci);

            request.setAttribute("esamespecdropdown",esamespecialisticodropdown);
            request.setAttribute("esamelabdropdown",esamelaboratoriodropdown);
            request.setAttribute("farmacodropdown",farmacodropdown);


            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/sismed/paziente/nuova-ricetta.jsp");
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