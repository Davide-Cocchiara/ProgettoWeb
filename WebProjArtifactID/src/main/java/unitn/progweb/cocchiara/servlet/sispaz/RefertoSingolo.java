package unitn.progweb.cocchiara.servlet.sispaz;// Import required java libraries

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
import java.io.PrintWriter;

// Extend HttpServlet class

@WebServlet("/sispaz/refertosingolo")
public class RefertoSingolo extends HttpServlet {

    public void init() throws ServletException {
        // Do required initialization
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String s_referto = request.getParameter("idreferto");
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

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
            Referto referto = utente.getPaziente().getReferto(idreferto,utente.getPaziente().getCodicefiscale());
            if (referto != null) {

                request.setAttribute("referto",referto);
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/sispaz/referto-singolo.jsp");
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