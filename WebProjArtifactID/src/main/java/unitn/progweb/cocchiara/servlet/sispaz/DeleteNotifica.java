package unitn.progweb.cocchiara.servlet.sispaz;// Import required java libraries

import unitn.progweb.cocchiara.model.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

// Extend HttpServlet class

@WebServlet("/sispaz/deletenotifica")
public class DeleteNotifica extends HttpServlet {

    public void init() throws ServletException {
        // Do required initialization
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String s_idNotifica = request.getParameter("value");
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        Boolean error=false;

        Integer idNotifica = null;
        try {
            idNotifica = Integer.parseInt(s_idNotifica);
        } catch (NumberFormatException e) {
            error = true;
        }

        if (error) {
            System.err.println("Error in deleting a notification.");
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/error.jsp");
            rd.forward(request,response);
        }
        else {
            if (utente.deleteNotifica(idNotifica)) {
                PrintWriter writer = response.getWriter();
                writer.write(s_idNotifica);
                writer.close();
            }
            else {
                PrintWriter writer = response.getWriter();
                writer.close();
            }

        }


    }

    public void destroy() {
        // do nothing.
    }
}