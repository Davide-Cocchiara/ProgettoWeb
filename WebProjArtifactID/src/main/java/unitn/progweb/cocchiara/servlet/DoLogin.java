package unitn.progweb.cocchiara.servlet;// Import required java libraries

import unitn.progweb.cocchiara.dao.UserDAO;
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

@WebServlet("/doLogin")
public class DoLogin extends HttpServlet {

    public void init() throws ServletException {
        // Do required initialization
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        UserDAO dao = new UserDAO();
        User loggedUser = dao.getUserCred(username,password);
        if (loggedUser.getUsername()!="") { // Login success!
            session.setAttribute("user", loggedUser);
            response.sendRedirect("welcome");
        }
        else {
            response.sendRedirect("login");
        }
    }

    public void destroy() {
        // do nothing.
    }
}