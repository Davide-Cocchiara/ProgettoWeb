package unitn.progweb.cocchiara.servlet;// Import required java libraries

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Extend HttpServlet class

@WebServlet("/privacy")
public class Privacy extends HttpServlet {

    public void init() throws ServletException {
        // Do required initialization
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set response content type
        response.setContentType("text/html");
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/privacy.jsp");
        rd.forward(request,response);
    }

    public void destroy() {
        // do nothing.
    }
}