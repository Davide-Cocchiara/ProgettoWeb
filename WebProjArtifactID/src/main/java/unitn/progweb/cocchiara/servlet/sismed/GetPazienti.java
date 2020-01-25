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

@WebServlet("/sismed/getpazienti")
public class GetPazienti extends HttpServlet {

    public void init() throws ServletException {
        // Do required initialization
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { ;

        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        ArrayList<Paziente> listaPagamenti = utente.getMedico().getListPazienti(utente.getPaziente().getCodicefiscale());

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = "{ \"content\": " + gson.toJson(listaPagamenti) + "}";

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");


        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }

    public void destroy() {
        // do nothing.
    }
}