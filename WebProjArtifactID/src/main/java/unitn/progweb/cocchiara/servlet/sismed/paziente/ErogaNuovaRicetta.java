package unitn.progweb.cocchiara.servlet.sismed.paziente;// Import required java libraries


import unitn.progweb.cocchiara.model.Paziente;
import unitn.progweb.cocchiara.model.SistemaNazionale;
import unitn.progweb.cocchiara.model.Utente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

// Extend HttpServlet class

@WebServlet("/sismed/paziente/eroganuovaricetta")
public class ErogaNuovaRicetta extends HttpServlet {

    public void init() throws ServletException {
        // Do required initialization
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String selectedesame = request.getParameter("selectedesame");
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        Paziente selectedpaziente = (Paziente) session.getAttribute("selectedpaziente");


        LinkedHashMap<String,String>  listafarmaci = (LinkedHashMap<String,String>) session.getAttribute("listafarmaci");
        LinkedHashMap<String,String>  listaesami = (LinkedHashMap<String,String>) session.getAttribute("listaesami");

        session.setAttribute("listafarmaci",null); // No longer needed.
        session.setAttribute("listaesami",null); // No longer needed.

        // Provided id esame is valid and it's either a farmaco or an esame
        // Try adding the ricetta
        if (selectedesame!= null && (listafarmaci.containsKey(selectedesame) || listaesami.containsKey(selectedesame))
        &&  utente.getMedico().addRicetta(utente.getPaziente().getCodicefiscale(),utente.getPaziente().getProvincia(),selectedesame,selectedpaziente.getCodicefiscale())
        ) {
            response.sendRedirect(request.getContextPath() + "/sismed/paziente/nuovaricetta?erogata=true");
        } else {
            response.sendRedirect(request.getContextPath() + "/sismed/paziente/nuovaricetta?erogata=false");
        }





    }

    public void destroy() {
        // do nothing.
    }
}