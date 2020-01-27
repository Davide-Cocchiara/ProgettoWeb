package unitn.progweb.cocchiara.servlet.sispaz;// Import required java libraries

import unitn.progweb.cocchiara.model.SistemaNazionale;
import unitn.progweb.cocchiara.model.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

// Extend HttpServlet class

@WebServlet("/sispaz/profilocittadino")
public class ProfiloCittadino extends HttpServlet {

    public void init() throws ServletException {
        // Do required initialization
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String provinciaoutgroup = "";
        HttpSession session = request.getSession();
        SistemaNazionale sis  = (SistemaNazionale) getServletContext().getAttribute("sistemanazionale");
        LinkedHashMap<String, String> listaProvince = sis.getListaProvince();
        Utente utente = (Utente)session.getAttribute("utente");

        for (Map.Entry<String,String> entry :listaProvince.entrySet()) {
            if (entry.getKey().equals(utente.getPaziente().getProvincia())) {
               provinciaoutgroup+= "<option selected value=\"";
            }
            else {
                provinciaoutgroup+= "<option value=\"";
            }
            provinciaoutgroup+= entry.getKey();
            provinciaoutgroup+= "\">";
            provinciaoutgroup+=entry.getValue();
            provinciaoutgroup+="</option>";
        }

        request.setAttribute("provinciaoutgroup",provinciaoutgroup);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/sispaz/profilo-cittadino.jsp");
        rd.forward(request, response);
    }

    public void destroy() {
        // do nothing.
    }
}