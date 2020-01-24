package unitn.progweb.cocchiara.servlet.sispaz;// Import required java libraries

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

// Extend HttpServlet class

@WebServlet("/sispaz/getreferti")
public class GetReferti extends HttpServlet {

    public void init() throws ServletException {
        // Do required initialization
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { ;
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String outstr = "{\n" +
                "  \"content\": [\n" +
                "  {\n" +
                "\t  \"data\": \"3 Gennaio\",\n" +
                "\t  \"prescrizione\" : \"Controllo generico\",\n" +
                "\t  \"medico\" : \"Paolo limiti\",\n" +
                "\t  \"idprescrizione\" : 1\n" +
                "  },\n" +
                "   {\n" +
                "\t  \"data\": \"4 Gennaio\",\n" +
                "\t  \"prescrizione\" : \"Controllo preciso\",\n" +
                "\t  \"medico\" : \"Areulio Sardi\",\n" +
                "\t  \"idprescrizione\" : 2\n" +
                "  }\n" +
                " ]\n" +
                " }";
        PrintWriter out = response.getWriter();
        out.print(outstr);
        out.flush();
    }

    public void destroy() {
        // do nothing.
    }
}