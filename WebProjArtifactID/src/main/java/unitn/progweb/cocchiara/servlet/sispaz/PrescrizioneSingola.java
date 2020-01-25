package unitn.progweb.cocchiara.servlet.sispaz;// Import required java libraries

import unitn.progweb.cocchiara.model.Pagamento;
import unitn.progweb.cocchiara.model.Prescrizione;
import unitn.progweb.cocchiara.model.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// Extend HttpServlet class

@WebServlet("/sispaz/prescrizionesingola")
public class PrescrizioneSingola extends HttpServlet {

    public void init() throws ServletException {
        // Do required initialization
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String s_prescrizione = request.getParameter("idprescrizione");
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        Boolean error=false;
        Integer idprescrizione = null;
        try {
            idprescrizione = Integer.parseInt(s_prescrizione);
        } catch (NumberFormatException e) {
            error = true;
        }

        if (error) {
            System.err.println("Error in accessing specific prescrzione");
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/error.jsp");
            rd.forward(request,response);
        }
        else {
            Prescrizione prescrizione = utente.getPaziente().getPrescrizione(idprescrizione,utente.getPaziente().getCodicefiscale());
            if (prescrizione != null) {

                request.setAttribute("prescrizione",prescrizione);
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/sispaz/prescrizione-singola.jsp");
                rd.forward(request,response);
            }
            else {
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/error.jsp");
                rd.forward(request,response);
            }

        }
    }

    public void destroy() {
        // do nothing.
    }
}