package unitn.progweb.cocchiara.filters;


import com.google.gson.Gson;
import unitn.progweb.cocchiara.dao.UtenteDAO;
import unitn.progweb.cocchiara.model.Utente;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Base64;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(	urlPatterns = {"/welcome","/avatar","/servizi","/sispaz/*","/sissan/*","/sismed/*", "/login"})
public class loginFilter implements Filter{

    public void init(FilterConfig arg0) throws ServletException {}

    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {
        // Set response content type
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;



        response.setContentType("text/html");
        response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
        response.setHeader("Pragma","no-cache");
        response.setHeader("Expires","0");

        HttpSession session = request.getSession();
        Utente u = (Utente) session.getAttribute("utente");

        String curPlace = request.getServletPath();
        if (u == null) {
            Cookie cookie = null;
            Cookie[] cookies = null;
            cookies = request.getCookies();

            if( cookies != null ){
                try {
                    for (int i = 0; i < cookies.length; i++) {
                        cookie = cookies[i];
                        if (cookie.getName().equals("login")) {
                            UtenteDAO ud = new UtenteDAO();

                            Gson g = new Gson();
                            Map.Entry<String, String> entr = new AbstractMap.SimpleEntry<>("", "");

                            String cookieJsonVal = new String(Base64.getDecoder().decode(cookie.getValue()));
                            entr = g.fromJson(cookieJsonVal, entr.getClass());

                            String resultVal = ud.tryLoginWithCookie(entr.getKey(), entr.getValue());
                            if (resultVal != null) // Cookie login success!
                            {
                                session.setAttribute("utente", ud.getUserFromCodice(resultVal));


                                if (!curPlace.equals("/login"))
                                    chain.doFilter(request, response);
                                else
                                    response.sendRedirect(request.getContextPath() + "/welcome");
                                return;
                            }
                            break;
                        }
                    }
                }
                catch(Exception e)
                {
                    
                }
            }

            // I'm not logged, go to login
            if(!curPlace.equals("/login"))
                response.sendRedirect(request.getContextPath() + "/login");
            else
                chain.doFilter(request, response);
        }
        else {
            // I'm logged, skip login and go to welcome
            if(curPlace.equals("/login"))
                response.sendRedirect(request.getContextPath() + "/welcome");
            else
                chain.doFilter(request, response);
        }
    }
    public void destroy() {}
}