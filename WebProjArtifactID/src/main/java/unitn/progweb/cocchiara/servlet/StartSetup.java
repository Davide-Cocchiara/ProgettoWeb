package unitn.progweb.cocchiara.servlet;// Import required java libraries

import unitn.progweb.cocchiara.model.SistemaProvinciale;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Extend HttpServlet class


public class StartSetup extends HttpServlet {
// Performs initialization operations.
    public void init() throws ServletException {
        ServletContext ctx = getServletContext();
        ctx.setAttribute("sistemaprovinciale",new SistemaProvinciale());
    }

    public void destroy() {
        // do nothing.
    }
}