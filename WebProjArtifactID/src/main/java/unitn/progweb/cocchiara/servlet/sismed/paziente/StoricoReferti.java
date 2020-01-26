package unitn.progweb.cocchiara.servlet.sismed.paziente;// Import required java libraries

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Extend HttpServlet class

@WebServlet("/sismed/paziente/storicoreferti")
public class StoricoReferti extends HttpServlet {

    public void init() throws ServletException {
        // Do required initialization
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { ;
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/sismed/paziente/storico-referti.jsp");
        rd.forward(request, response);
    }

    public void destroy() {
        // do nothing.
    }
}