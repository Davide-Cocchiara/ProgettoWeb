package unitn.progweb.cocchiara.servlet;// Import required java libraries

import unitn.progweb.cocchiara.model.Persona;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// Extend HttpServlet class

@WebServlet("/servizi")
public class Servizi extends HttpServlet {

    public void init() throws ServletException {
        // Do required initialization
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set response content type
        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        Persona persona = (Persona) session.getAttribute("Persona");
        //RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/servizi.jsp");
        //rd.forward(request, response);
        String page = "";

        page += "<html>\n" +
                "\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, shrink-to-fit=no\">\n" +
                "    <title>Blank Page - Servizi Sanitari per il cittadino</title>\n" +
                "    <meta name=\"description\" content=\"Sistema di Servizi Sanitari per il cittadino, 2020, ProgWeb\">\n" +
                "    <link rel=\"stylesheet\" href=\"assets/bootstrap/css/bootstrap.min.css\">\n" +
                "    <link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i\">\n" +
                "    <link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Bitter:400,700\">\n" +
                "    <link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Bungee\">\n" +
                "    <link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Lora\">\n" +
                "    <link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700\">\n" +
                "    <link rel=\"stylesheet\" href=\"assets/fonts/fontawesome-all.min.css\">\n" +
                "    <link rel=\"stylesheet\" href=\"assets/fonts/font-awesome.min.css\">\n" +
                "    <link rel=\"stylesheet\" href=\"assets/fonts/fontawesome5-overrides.min.css\">\n" +
                "    <link rel=\"stylesheet\" href=\"assets/css/Article-Clean.css\">\n" +
                "    <link rel=\"stylesheet\" href=\"assets/css/Features-Clean.css\">\n" +
                "    <link rel=\"stylesheet\" href=\"assets/css/Footer-Basic.css\">\n" +
                "    <link rel=\"stylesheet\" href=\"assets/css/Footer-Clean.css\">\n" +
                "    <link rel=\"stylesheet\" href=\"assets/css/Header-Blue--Sticky-Header--Smooth-Scroll.css\">\n" +
                "    <link rel=\"stylesheet\" href=\"assets/css/Header-Blue.css\">\n" +
                "    <link rel=\"stylesheet\" href=\"assets/css/Header-Dark.css\">\n" +
                "    <link rel=\"stylesheet\" href=\"assets/css/Highlight-Phone.css\">\n" +
                "    <link rel=\"stylesheet\" href=\"assets/css/Team-Boxed.css\">\n" +
                "</head>\n" +
                "\n" +
                "<body id=\"page-top\">\n" +
                "    <div id=\"wrapper\">\n" +
                "        <div class=\"d-flex flex-column\" id=\"content-wrapper\">\n" +
                "            <div id=\"content\">\n" +
                "                <nav class=\"navbar navbar-light navbar-expand bg-white shadow mb-4 topbar static-top\">\n" +
                "                    <div class=\"container-fluid\"><button class=\"btn btn-link d-md-none rounded-circle mr-3\" id=\"sidebarToggleTop\" type=\"button\"><i class=\"fas fa-bars\"></i></button>\n" +
                "                        <ul class=\"nav navbar-nav flex-nowrap ml-auto\">\n" +
                "                            <li class=\"nav-item dropdown d-sm-none no-arrow\"><a class=\"dropdown-toggle nav-link\" data-toggle=\"dropdown\" aria-expanded=\"false\" href=\"#\"><i class=\"fas fa-search\"></i></a>\n" +
                "                                <div class=\"dropdown-menu dropdown-menu-right p-3 animated--grow-in\" role=\"menu\" aria-labelledby=\"searchDropdown\">\n" +
                "                                    <form class=\"form-inline mr-auto navbar-search w-100\">\n" +
                "                                        <div class=\"input-group\"><input class=\"bg-light form-control border-0 small\" type=\"text\" placeholder=\"Search for ...\">\n" +
                "                                            <div class=\"input-group-append\"><button class=\"btn btn-primary py-0\" type=\"button\"><i class=\"fas fa-search\"></i></button></div>\n" +
                "                                        </div>\n" +
                "                                    </form>\n" +
                "                                </div>\n" +
                "                            </li>\n" +
                "                            <li class=\"nav-item dropdown no-arrow\" role=\"presentation\">\n" +
                "                                <div class=\"nav-item dropdown no-arrow\"><a class=\"dropdown-toggle nav-link\" data-toggle=\"dropdown\" aria-expanded=\"false\" href=\"#\"><span class=\"d-none d-lg-inline mr-2 text-gray-600 small\">"+persona.getNome()+ " " + persona.getCognome() +"</span><img class=\"border rounded-circle img-profile\" src=\"assets/img/utente.jpg\"></a>\n" +
                "                                    <div\n" +
                "                                        class=\"dropdown-menu shadow dropdown-menu-right animated--grow-in\" role=\"menu\"><a class=\"dropdown-item\" role=\"presentation\" href=\"logout\"><i class=\"fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400\"></i>&nbsp;Logout</a></div>\n" +
                "                    </div>\n" +
                "                    </li>\n" +
                "                    </ul>\n" +
                "            </div>\n" +
                "            </nav>\n" +
                "            <div class=\"container-fluid\">\n" +
                "                <h3 class=\"text-center text-dark mb-1\">Servizi Disponibili</h3>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div><a class=\"border rounded d-inline scroll-to-top\" href=\"#page-top\"><i class=\"fas fa-angle-up\"></i></a></div>\n" +
                "    <div class=\"team-boxed\">\n" +
                "        <div class=\"container\">\n" +
                "            <div class=\"intro\"></div>\n" +
                "            <div class=\"row people\">\n" +
                "                <div class=\"col-md-6 col-lg-4 item\">\n" +
                "                    <div class=\"box\" style=\"background-color: rgb(199,228,255);\"><img class=\"rounded-circle\" src=\"assets/img/utente.jpg\">\n" +
                "                        <h3 class=\"name\">Cittadino</h3>\n" +
                "                        <p class=\"description\">Servizi per il cittadino</p><button class=\"btn btn-primary\" type=\"button\">Entra</button></div>\n" +
                "                </div>\n" +
                "\n" +
                "\n";

        if (persona.isMedico()) {

page+=
            "                <div class=\"col-md-6 col-lg-4 item\">\n" +
                    "                    <div class=\"box\" style=\"background-color: rgb(255,214,214);\"><img class=\"rounded-circle\" src=\"assets/img/medico.jpg\">\n" +
                    "                        <h3 class=\"name\">Medico</h3>\n" +
                    "                        <p class=\"description\">Servizi per il medico di base</p><button class=\"btn btn-primary\" type=\"button\" style=\"background-color: rgb(223,104,78);\">Entra</button></div>\n" +
                    "                </div>\n" +
                    "\n";
        }
        if (persona.isAdmin()) {
            page +=
                    "                <div class=\"col-md-6 col-lg-4 item\">\n" +
                            "                    <div class=\"box\" style=\"background-color: rgb(224,255,205);\"><img class=\"rounded-circle\" src=\"assets/img/admin.jpg\">\n" +
                            "                        <h3 class=\"name\">Amministratore</h3>\n" +
                            "                        <p class=\"description\">Servizi amministrativi</p><button class=\"btn btn-primary\" type=\"button\" style=\"background-color: rgb(116,223,78);\">Entra</button></div>\n" +
                            "                </div>\n";
        }
        page+=
                "\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div class=\"footer-clean\">\n" +
                "        <footer>\n" +
                "            <div class=\"container\">\n" +
                "                <div class=\"row justify-content-center\"><span>Copyright © Servizi Sanitari per il cittadino 2020</span></div>\n" +
                "            </div>\n" +
                "        </footer>\n" +
                "    </div>\n" +
                "    <script src=\"assets/js/jquery.min.js\"></script>\n" +
                "    <script src=\"assets/bootstrap/js/bootstrap.min.js\"></script>\n" +
                "    <script src=\"assets/js/Header-Blue--Sticky-Header--Smooth-Scroll-1.js\"></script>\n" +
                "    <script src=\"assets/js/Header-Blue--Sticky-Header--Smooth-Scroll.js\"></script>\n" +
                "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.js\"></script>\n" +
                "    <script src=\"assets/js/theme.js\"></script>\n" +
                "</body>\n" +
                "\n" +
                "</html>";

    response.getWriter().print(page);
    }

    public void destroy() {
        // do nothing.
    }
}