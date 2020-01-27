package unitn.progweb.cocchiara.filters;


import unitn.progweb.cocchiara.model.Utente;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(	filterName = "adminFilter")
public class adminFilter implements Filter{

    public void init(FilterConfig arg0) throws ServletException {}

    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {
        // Set response content type
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession();
        Utente u = (Utente) session.getAttribute("utente");

        if (!u.isAdmin()) {
            response.sendRedirect(request.getContextPath() +"/servizi");
        }
        else {
            chain.doFilter(request, response);
        }
    }
    public void destroy() {}
}