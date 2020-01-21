package unitn.progweb.cocchiara.servlet.examples;// Import required java libraries

import unitn.progweb.cocchiara.dao.PersonaDAO;
import unitn.progweb.cocchiara.model.Persona;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

// Extend HttpServlet class

//@WebServlet("/MARCOEX")
public class ExampleMarcoRetrival extends HttpServlet {

    private String message;

    public void init() throws ServletException {
        // Do required initialization
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set response content type
        response.setContentType("text/html");

        // Actual logic goes here.
        PrintWriter out = response.getWriter();
        PersonaDAO dao = new PersonaDAO();
        Persona marco = dao.getUserCred("Marco","PassOne");

        HttpSession session = request.getSession();
        //session.setAttribute("marco", marco);
        //response.sendRedirect("marco.jsp");
        request.setAttribute("marco",marco);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/marco.jsp");
        rd.forward(request,response);
    }

    public void destroy() {
        // do nothing.
    }
}