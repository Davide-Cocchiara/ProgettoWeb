package unitn.progweb.cocchiara.servlet.sismed;// Import required java libraries

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import unitn.progweb.cocchiara.model.Paziente;
import unitn.progweb.cocchiara.model.Utente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

// Extend HttpServlet class

@WebServlet("/sismed/selectpaziente")
public class SelectPaziente extends HttpServlet {

    public void init() throws ServletException {
        // Do required initialization
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { ;
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        String s_selectedpaziente = request.getParameter("selectedpaziente");

        // Check that asked paziente is actually of medico
        Paziente selectedpaziente = utente.getMedico().getOwnpaziente(s_selectedpaziente);
        if (selectedpaziente != null) {
            session.setAttribute("selectedpaziente", selectedpaziente);
            response.sendRedirect(request.getContextPath() +"/sismed/paziente/infopaziente");
        }
        else {
            response.sendRedirect(request.getContextPath() +"/sismed/pazientilista");
        }
    }

    public void destroy() {
        // do nothing.
    }
}