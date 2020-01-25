package unitn.progweb.cocchiara.filters;


import unitn.progweb.cocchiara.model.Paziente;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(	urlPatterns = {"/sismed/paziente/*"})
public class SelectedPazienteFilter implements Filter{

    public void init(FilterConfig arg0) throws ServletException {}

    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {
        // Set response content type
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession();
        Paziente u = (Paziente) session.getAttribute("selectedpaziente");
        // No selected paziente!
        if (u==null) {
            response.sendRedirect(request.getContextPath() +"/sismed/pazientilista");
        }
        else {
            chain.doFilter(request, response);
        }
    }
    public void destroy() {}
}