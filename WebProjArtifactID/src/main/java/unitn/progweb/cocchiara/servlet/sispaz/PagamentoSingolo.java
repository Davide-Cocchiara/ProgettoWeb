package unitn.progweb.cocchiara.servlet.sispaz;// Import required java libraries

import unitn.progweb.cocchiara.model.Pagamento;
import unitn.progweb.cocchiara.model.Referto;
import unitn.progweb.cocchiara.model.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// Extend HttpServlet class

@WebServlet("/sispaz/pagamentosingolo")
public class PagamentoSingolo extends HttpServlet {

    public void init() throws ServletException {
        // Do required initialization
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String s_pagamento = request.getParameter("idpagamento");
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        Boolean error=false;
        Integer idpagamento = null;
        try {
            idpagamento = Integer.parseInt(s_pagamento);
        } catch (NumberFormatException e) {
            error = true;
        }

        if (error) {
            System.err.println("Error in accessing specific pagamento");
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/error.jsp");
            rd.forward(request,response);
        }
        else {
            Pagamento pagamento = utente.getPaziente().getPagamento(idpagamento,utente.getPaziente().getCodicefiscale());
            if (pagamento != null) {

                request.setAttribute("pagamento",pagamento);
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/sispaz/pagamento-singolo.jsp");
                rd.forward(request,response);
            }
            else {
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/error.jsp");
                rd.forward(request,response);
            }

        }
    }

    public void destroy() {
        // do nothing.
    }
}