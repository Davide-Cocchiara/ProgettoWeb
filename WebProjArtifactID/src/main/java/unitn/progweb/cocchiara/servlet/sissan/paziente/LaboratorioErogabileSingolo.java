package unitn.progweb.cocchiara.servlet.sissan.paziente;// Import required java libraries

import unitn.progweb.cocchiara.model.Paziente;
import unitn.progweb.cocchiara.model.Prescrizione;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// Extend HttpServlet class

@WebServlet("/sissan/paziente/laboratorioerogabilesingolo")
public class LaboratorioErogabileSingolo extends HttpServlet {

    public void init() throws ServletException {
        // Do required initialization
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String s_prescrizione = request.getParameter("idprescrizione");
        HttpSession session = request.getSession();
        Paziente paziente = (Paziente) session.getAttribute("selectedprovinciapaziente");

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
            Prescrizione prescrizione = paziente.getPrescrizione(idprescrizione,paziente.getCodicefiscale());
            if (prescrizione != null &&
                    (prescrizione.getTipo() == Prescrizione.PRESTAZIONE_LABORATORIO)
                && prescrizione.getDataevasione() == null)
            {
                request.setAttribute("prescrizioneerogabile",prescrizione);
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/sissan/paziente/laboratorio-erogabile-singolo.jsp");
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