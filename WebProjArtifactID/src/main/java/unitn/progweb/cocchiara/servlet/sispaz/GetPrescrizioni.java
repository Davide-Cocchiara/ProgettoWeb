package unitn.progweb.cocchiara.servlet.sispaz;// Import required java libraries

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import unitn.progweb.cocchiara.model.Pagamento;
import unitn.progweb.cocchiara.model.Prescrizione;
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

@WebServlet("/sispaz/getprescrizioni")
public class GetPrescrizioni extends HttpServlet {

    public void init() throws ServletException {
        // Do required initialization
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { ;

        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        ArrayList<Prescrizione> listaPrescrizioni = utente.getPaziente().getListaPrescrizioniMinimale();


        Gson gson = new GsonBuilder().setPrettyPrinting().setDateFormat("yyyy/MM/dd").create();
        String json = "{ \"content\": " + gson.toJson(listaPrescrizioni) + "}";

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