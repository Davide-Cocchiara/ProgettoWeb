package unitn.progweb.cocchiara.servlet.sispaz;// Import required java libraries

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import unitn.progweb.cocchiara.model.Referto;
import unitn.progweb.cocchiara.model.Utente;

// Extend HttpServlet class

@WebServlet("/sispaz/getreferti")
public class GetReferti extends HttpServlet {

    public void init() throws ServletException {
        // Do required initialization
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { ;

        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        ArrayList<Referto> listaReferti = utente.getPaziente().getListaRefertiMinimale();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = "{ \"content\": " + gson.toJson(listaReferti) + "}";

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String outstr = "{\n" +
                "  \"content\": [\n" +
                "  {\n" +
                "\t  \"data\": \"3 Gennaio\",\n" +
                "\t  \"prestazione\" : \"Controllo generico\",\n" +
                "\t  \"medico\" : \"Paolo limiti\",\n" +
                "\t  \"id\" : 1\n" +
                "  },\n" +
                "   {\n" +
                "\t  \"data\": \"4 Gennaio\",\n" +
                "\t  \"prestazione\" : \"Controllo preciso\",\n" +
                "\t  \"medico\" : \"Areulio Sardi\",\n" +
                "\t  \"id\" : 2\n" +
                "  }\n" +
                " ]\n" +
                " }";
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }

    public void destroy() {
        // do nothing.
    }
}