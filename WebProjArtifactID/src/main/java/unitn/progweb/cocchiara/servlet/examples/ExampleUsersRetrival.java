package unitn.progweb.cocchiara.servlet.examples;// Import required java libraries

import unitn.progweb.cocchiara.dao.UserDAO;
import unitn.progweb.cocchiara.model.Persona;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

// Extend HttpServlet class

//@WebServlet("/USERSEX")
public class ExampleUsersRetrival extends HttpServlet {

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
        UserDAO dao = new UserDAO();
        List<Persona> personas = dao.getUsers();
        for (Persona u: personas) {
            out.println("<h1>Username:" + u.getCodiceFiscale() + "</h1>");
        }
    }

    public void destroy() {
        // do nothing.
    }
}