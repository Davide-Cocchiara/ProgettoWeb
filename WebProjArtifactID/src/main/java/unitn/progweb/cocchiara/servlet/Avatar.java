package unitn.progweb.cocchiara.servlet;// Import required java libraries

import unitn.progweb.cocchiara.model.Persona;

import javax.imageio.ImageIO;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

// Extend HttpServlet class

@WebServlet("/avatar")
public class Avatar extends HttpServlet {

    public void init() throws ServletException {
        // Do required initialization
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set response content type
        HttpSession session = request.getSession();
        Persona persona = (Persona) session.getAttribute("Persona");
        response.setContentType("image/gif");
        try {
            Context ctx = new InitialContext();
            Context env = (Context) ctx.lookup("java:comp/env");
            String imgPazienti = (String) env.lookup("pathimgpazienti");

            File inputFile = new File(imgPazienti + persona.getCodicefiscale()+".jpg");
            if (inputFile.exists()) {
                BufferedImage img = ImageIO.read(inputFile);
                OutputStream out = response.getOutputStream();

                ImageIO.write(img, "jpg", out);
                out.flush();
                out.close();
            }
            else {
                response.sendRedirect(request.getContextPath() + "/assets/img/utente.jpg");
            }
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }


    }

    public void destroy() {
        // do nothing.
    }
}