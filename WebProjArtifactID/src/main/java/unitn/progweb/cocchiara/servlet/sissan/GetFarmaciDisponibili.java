package unitn.progweb.cocchiara.servlet.sissan;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import unitn.progweb.cocchiara.model.SistemaProvinciale;
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
import java.util.LinkedHashMap;
import java.util.Map;

// Restituisce in JSON le info da convertire in XLS
@WebServlet("/sissan/getfarmacidisponibili")
public class GetFarmaciDisponibili extends HttpServlet {

    public void init() throws ServletException {
        // Do required initialization
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        SistemaProvinciale prov = new SistemaProvinciale(utente.getPaziente().getProvincia());
        ArrayList<Map.Entry<String, Map.Entry<String, String>>> listaFarmaciDisponibili = prov.getListaFarmaciDisponibili();

        Gson gson = new GsonBuilder().setPrettyPrinting().setDateFormat("yyyy/MM/dd").create();
        String json = "{ \"content\": " + gson.toJson(listaFarmaciDisponibili) + "}";

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