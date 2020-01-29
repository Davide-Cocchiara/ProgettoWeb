package unitn.progweb.cocchiara.servlet;// Import required java libraries

import com.google.gson.Gson;

import unitn.progweb.cocchiara.dao.UtenteDAO;
import unitn.progweb.cocchiara.model.SistemaNazionale;
import unitn.progweb.cocchiara.model.Utente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;


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
        String rememberme = request.getParameter("rememberme");

        HttpSession session = request.getSession();
        UtenteDAO dao = new UtenteDAO();
        Utente loggedPersona = dao.getUserCred(username,password);
        if (loggedPersona != null) { // Login success!
            session.setAttribute("utente", loggedPersona);

            SistemaNazionale sis  = (SistemaNazionale) getServletContext().getAttribute("sistemanazionale");
            LinkedHashMap<String, String> listaProvince = sis.getListaProvince();
            session.setAttribute("provinciastampabile", listaProvince.get(loggedPersona.getPaziente().getProvincia()));

            if(rememberme != null) {
                Map.Entry<String, String> cookVal = dao.createOrUpdateCookieForUser(username);
                Gson g = new Gson();

                String cookieBase64Val = Base64.getEncoder().encodeToString(g.toJson(cookVal).getBytes());

                Cookie userCookie = new Cookie("login", cookieBase64Val);
                userCookie.setMaxAge(60 * 60 * 24 * 7); // 1 week

                response.addCookie(userCookie);
            }
            response.sendRedirect("welcome");
        }
        else {
            response.sendRedirect("login?failedlogin=true");
        }
    }

    public void destroy() {
        // do nothing.
    }
}