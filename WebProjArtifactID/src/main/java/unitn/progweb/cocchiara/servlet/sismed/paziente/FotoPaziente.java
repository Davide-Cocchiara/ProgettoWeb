package unitn.progweb.cocchiara.servlet.sismed.paziente;// Import required java libraries

import unitn.progweb.cocchiara.model.Paziente;
import unitn.progweb.cocchiara.model.Utente;

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

@WebServlet("/sismed/paziente/fotopaziente")
public class FotoPaziente extends HttpServlet {

    public void init() throws ServletException {
        // Do required initialization
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set response content type
        HttpSession session = request.getSession();
        Paziente selectedpaziente = (Paziente) session.getAttribute("selectedpaziente");
        response.setContentType("image/jpeg");
            try {
                Context ctx = new InitialContext();
                Context env = (Context) ctx.lookup("java:comp/env");
                String imgPazienti = (String) env.lookup("pathimgpazienti");

                File inputFile = new File(imgPazienti + selectedpaziente.getCodicefiscale() + ".jpg");
                if (inputFile.exists()) {
                    BufferedImage img = ImageIO.read(inputFile);
                    OutputStream out = response.getOutputStream();

                    ImageIO.write(img, "jpg", out);
                    out.flush();
                    out.close();
                } else {
                    response.sendRedirect(request.getContextPath() + "/assets/img/utente.jpg");
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
        }
    }

    public void destroy() {
        // do nothing.
    }
}