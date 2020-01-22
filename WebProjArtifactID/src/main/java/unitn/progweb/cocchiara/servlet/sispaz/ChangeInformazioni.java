package unitn.progweb.cocchiara.servlet.sispaz;// Import required java libraries


import unitn.progweb.cocchiara.model.Persona;
import unitn.progweb.cocchiara.model.SistemaProvinciale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

// Extend HttpServlet class

@WebServlet("/sispaz/changeinformazioni")
public class ChangeInformazioni extends HttpServlet {

    public void init() throws ServletException {
        // Do required initialization
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String email = request.getParameter("email");
        String provincia = request.getParameter("provincia");
        Boolean error = false;
        Boolean changed = false;
        HttpSession session = request.getSession();
        Persona persona = (Persona) session.getAttribute("Persona");

        if (email != null && provincia != null) { // validate input

            // Mail change
            if (!persona.getEmail().equals(email)) { // If the email has changed, change it

                // Check mail validity
                String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                        "[a-zA-Z0-9_+&*-]+)*@" +
                        "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                        "A-Z]{2,7}$";
                Pattern pat = Pattern.compile(emailRegex);

                if (pat.matcher(email).matches()) { // If new mail is valid
                    if (!persona.setEmail(email)) { // if mail change is unsuccessful
                        System.err.println("Error in changing Email");
                        error = true;
                    } else {
                        changed = true;
                    } // For redirecting correctly
                }
                else {
                    error=true;
                }
            }

            // Provincia change
            if (!(persona.getProvincia().equals(provincia))) { // If the province has changed, change it
                SistemaProvinciale sis  = (SistemaProvinciale) getServletContext().getAttribute("SistemaProvinciale");
                LinkedHashMap<String, String> listaProvince = sis.getListaProvince();
                if (listaProvince.containsKey(provincia)) { // If the provided provincia is in the provincia list
                    if(!persona.setProvincia(provincia)) { // if provincia change is unsuccessful
                        System.err.println("Error in changing Provincia");
                        error = true;
                    } else { changed=true;} //  For redirecting correctly
                }
                else {
                    error=true;
                }
            }

            // Redirection
            if (error) {
                response.sendRedirect(request.getContextPath() + "/sispaz/profilocittadino?changedinfo=false");
            }
            else if (changed) {
                response.sendRedirect(request.getContextPath() + "/sispaz/profilocittadino?changedinfo=true");
            }
            else {
                response.sendRedirect(request.getContextPath() + "/sispaz/profilocittadino");
            }
        }
        else {
            response.sendRedirect(request.getContextPath() + "/sispaz/profilocittadino");
        }



        }

    public void destroy() {
        // do nothing.
    }
}