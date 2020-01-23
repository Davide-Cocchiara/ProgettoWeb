package unitn.progweb.cocchiara.filters;


import unitn.progweb.cocchiara.model.Utente;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(	urlPatterns = {"/welcome","/avatar","/servizi","/sispaz/*","/sissan/*","/sismed/*"})
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
        if (u == null) {
            response.sendRedirect(request.getContextPath() +"/login");
        }
        else {
            chain.doFilter(request, response);
        }
    }
    public void destroy() {}
}