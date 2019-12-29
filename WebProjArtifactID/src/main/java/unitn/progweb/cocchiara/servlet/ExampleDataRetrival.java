package unitn.progweb.cocchiara.servlet;// Import required java libraries

import unitn.progweb.cocchiara.dao.BasicDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// Extend HttpServlet class

@WebServlet("/DATAEX")
public class ExampleDataRetrival extends HttpServlet {

    private String message;

    public void init() throws ServletException {
        // Do required initialization
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set response content type
        response.setContentType("text/html");

        // Actual logic goes here.
        PrintWriter out = response.getWriter();
        BasicDAO bDao = new BasicDAO();
        String sout = "";
        sout = bDao.exampleUserQuery();
        out.println("<h1>" + sout + "</h1>");
    }

    public void destroy() {
        // do nothing.
    }
}