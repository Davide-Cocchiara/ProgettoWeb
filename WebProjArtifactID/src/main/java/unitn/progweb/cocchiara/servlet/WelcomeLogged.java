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

@WebServlet("/welcome")
public class WelcomeLogged extends HttpServlet {

    public void init() throws ServletException {
        // Do required initialization
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set response content type
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        // TODO Build page, give page parameters to JSP
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/welcomeLogged.jsp");
        rd.forward(request,response);
    }

    public void destroy() {
        // do nothing.
    }
}