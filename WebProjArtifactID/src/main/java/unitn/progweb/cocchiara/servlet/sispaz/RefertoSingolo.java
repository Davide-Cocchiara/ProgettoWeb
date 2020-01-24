package unitn.progweb.cocchiara.servlet.sispaz;// Import required java libraries

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        String referto = request.getParameter("referto");
        // Validazione intero..
        // TODO
    }

    public void destroy() {
        // do nothing.
    }
}