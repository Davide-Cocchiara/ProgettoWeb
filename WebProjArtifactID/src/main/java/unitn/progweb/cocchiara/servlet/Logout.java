package unitn.progweb.cocchiara.servlet;// Import required java libraries

import unitn.progweb.cocchiara.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// Extend HttpServlet class

@WebServlet("/logout")
public class Logout extends HttpServlet {

    public void init() throws ServletException {
        // Do required initialization
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set response content type
        HttpSession session = request.getSession();
        session.setAttribute("user", null);
        response.sendRedirect("index");
    }

    public void destroy() {
        // do nothing.
    }
}