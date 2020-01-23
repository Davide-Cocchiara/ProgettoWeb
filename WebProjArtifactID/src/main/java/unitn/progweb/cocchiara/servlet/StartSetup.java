package unitn.progweb.cocchiara.servlet;// Import required java libraries

import unitn.progweb.cocchiara.model.SistemaNazionale;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

// Extend HttpServlet class


public class StartSetup extends HttpServlet {
// Performs initialization operations.
    public void init() throws ServletException {
        ServletContext ctx = getServletContext();
        ctx.setAttribute("sistemanazionale",new SistemaNazionale());
    }

    public void destroy() {
        // do nothing.
    }
}