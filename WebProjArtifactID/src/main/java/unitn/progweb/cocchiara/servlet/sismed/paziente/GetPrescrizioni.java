package unitn.progweb.cocchiara.servlet.sismed.paziente;// Import required java libraries

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import unitn.progweb.cocchiara.model.Paziente;
import unitn.progweb.cocchiara.model.Prescrizione;
import unitn.progweb.cocchiara.model.Referto;

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

@WebServlet("/sismed/paziente/getprescrizioni")
public class GetPrescrizioni extends HttpServlet {

    public void init() throws ServletException {
        // Do required initialization
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { ;

        HttpSession session = request.getSession();
        Paziente paziente = (Paziente) session.getAttribute("selectedpaziente");

        ArrayList<Prescrizione> listaReferti = paziente.getListaPrescrizioniMinimale();

        Gson gson = new GsonBuilder().setPrettyPrinting().setDateFormat("yyyy/MM/dd").create();
        String json = "{ \"content\": " + gson.toJson(listaReferti) + "}";

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