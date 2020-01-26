package unitn.progweb.cocchiara.servlet.sismed.paziente;// Import required java libraries

import unitn.progweb.cocchiara.model.Paziente;
import unitn.progweb.cocchiara.model.Referto;
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

@WebServlet("/sismed/paziente/storicorefertosingolo")
public class StoricoRefertoSingolo extends HttpServlet {

    public void init() throws ServletException {
        // Do required initialization
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String s_referto = request.getParameter("idreferto");
        HttpSession session = request.getSession();
        Paziente paziente = (Paziente) session.getAttribute("selectedpaziente");

        Boolean error=false;
        Integer idreferto = null;
        try {
            idreferto = Integer.parseInt(s_referto);
        } catch (NumberFormatException e) {
            error = true;
        }

        if (error) {
            System.err.println("Error in accessing specific referto");
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/error.jsp");
            rd.forward(request,response);
        }
        else {
            Referto referto = paziente.getReferto(idreferto,paziente.getCodicefiscale());
            if (referto != null) {

                request.setAttribute("referto",referto);
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/sismed/paziente/storico-referto-singolo.jsp");
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