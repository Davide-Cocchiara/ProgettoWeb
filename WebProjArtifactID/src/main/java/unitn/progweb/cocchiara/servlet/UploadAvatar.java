package unitn.progweb.cocchiara.servlet;// Import required java libraries


import unitn.progweb.cocchiara.model.Utente;

import javax.imageio.ImageIO;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;

// Extend HttpServlet class

@WebServlet("/sispaz/uploadavatar")
@MultipartConfig
public class UploadAvatar extends HttpServlet {

    public void init() throws ServletException {
        // Do required initialization
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.

        try (InputStream input = filePart.getInputStream()) {
            try {
                Context ctx = new InitialContext();
                Context env = (Context) ctx.lookup("java:comp/env");
                String imgPazienti = (String) env.lookup("pathimgpazienti");
                BufferedImage img = ImageIO.read(input);
                // It's an image (only BMP, GIF, JPG and PNG are recognized).
                File outputfile = new File(imgPazienti + utente.getPaziente().getCodicefiscale()+".jpg");
                //outputfile.createNewFile();
                ImageIO.write(img,"jpg",outputfile);
                response.sendRedirect(request.getContextPath() + "/sispaz/profilocittadino");
            } catch (Exception e) {
                System.err.println(e.getMessage());
                response.sendRedirect(request.getContextPath() + "/sispaz/profilocittadino?failedupload=true");
            }
        }
    }
    public void destroy() {
        // do nothing.
    }
}