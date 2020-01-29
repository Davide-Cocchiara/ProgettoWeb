package unitn.progweb.cocchiara.servlet.sismed.paziente;// Import required java libraries


import unitn.progweb.cocchiara.model.Paziente;
import unitn.progweb.cocchiara.model.Utente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedHashMap;

// Extend HttpServlet class

@WebServlet("/sismed/paziente/eroganuovavisita")
public class ErogaNuovaVisita extends HttpServlet {

    public void init() throws ServletException {
        // Do required initialization
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        Paziente selectedpaziente = (Paziente) session.getAttribute("selectedpaziente");

        String selectedprestazione = request.getParameter("selectedprestazione");
        String pagato = request.getParameter("pagato");
        String relazione = request.getParameter("relazione");


        LinkedHashMap<String, String> listaprestazioni = (LinkedHashMap<String, String>) session.getAttribute("listaprestazioni");

        session.setAttribute("listaprestazioni", null); // No longer needed.


        if (selectedprestazione != null && listaprestazioni.containsKey(selectedprestazione) && pagato != null && relazione != null &&
                (utente.getMedico().addReferto(utente.getPaziente().getCodicefiscale(),
                        selectedprestazione,
                        relazione,
                        selectedpaziente.getCodicefiscale(),
                        pagato.equals("1"),selectedpaziente.getEmail()))
        ) {
            response.sendRedirect(request.getContextPath() + "/sismed/paziente/nuovavisita?erogata=true");

        } else {
            response.sendRedirect(request.getContextPath() + "/sismed/paziente/nuovavisita?erogata=false");
        }


    }

    public void destroy() {
        // do nothing.
    }
}