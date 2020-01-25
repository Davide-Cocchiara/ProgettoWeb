package unitn.progweb.cocchiara.servlet.sismed;// Import required java libraries


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

@WebServlet("/sismed/changeinformazioniclinica")
public class ChangeInformazioniClinica extends HttpServlet {

    public void init() throws ServletException {
        // Do required initialization
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String indirizzo = request.getParameter("indirizzo");
        String telefono = request.getParameter("telefono");
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        Boolean error = false;


        if (indirizzo != null && telefono != null) { // validate input
            Boolean changed = false;
            if (!indirizzo.equals(utente.getMedico().getClinica())) {
                if (!utente.getMedico().setClinica(indirizzo)) {
                    error=true;
                } else {changed =true;}
            }
            if (!telefono.equals(utente.getMedico().getTelefonoclinica())) {
                if (!utente.getMedico().setTelefonoclinica(telefono)) {
                    error=true;
                } else {changed =true;}
            }
            if (error) {
                response.sendRedirect(request.getContextPath() + "/sismed/profilomedico?changedinfo=false");
            }
            else if (changed) {
                response.sendRedirect(request.getContextPath() + "/sismed/profilomedico?changedinfo=true");
            }
            else {
                response.sendRedirect(request.getContextPath() + "/sismed/profilomedico");

            }
        }
       else {
            response.sendRedirect(request.getContextPath() + "/sismed/profilomedico?changedinfo=false");
        }
    }

    public void destroy() {
        // do nothing.
    }
}