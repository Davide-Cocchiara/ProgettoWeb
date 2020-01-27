package unitn.progweb.cocchiara.servlet.sismed.paziente;// Import required java libraries

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import unitn.progweb.cocchiara.model.Paziente;
import unitn.progweb.cocchiara.model.Prescrizione;
import unitn.progweb.cocchiara.model.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

// Extend HttpServlet class

@WebServlet("/sispaz/getqrstoricoprescrizione")
public class GetQRStoricoPrescrizione extends HttpServlet {

    public void init() throws ServletException {
        // Do required initialization
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { ;
        String s_prescrizione = request.getParameter("idprescrizione");
        HttpSession session = request.getSession();
        Paziente paziente = (Paziente) session.getAttribute("selectedpaziente");

        Boolean error=false;
        Integer idprescrizione = null;
        try {
            idprescrizione = Integer.parseInt(s_prescrizione);
        } catch (NumberFormatException e) {
            error = true;
        }

        if (error) {
            System.err.println("Error in accessing specific prescrzione");
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/error.jsp");
            rd.forward(request,response);
        }
        else {
            Prescrizione prescrizione = paziente.getPrescrizione(idprescrizione,paziente.getCodicefiscale());
            if (prescrizione != null) {

                response.setContentType("image/png");
                QRCodeWriter qrCodeWriter = new QRCodeWriter();
                String QRtext = "Medico: " + prescrizione.getMedico() + " (" + prescrizione.getCodiceMedico() + ")"+
                        "\nCodice fiscale paziente: " + paziente.getCodicefiscale() +
                        "\nTimestamp prescrizione: "+ prescrizione.getDatarilascio() +
                        "\nCodice univoco prescrizione: "+prescrizione.getIdprescrizione()+
                        "\nDescrizione prestazione: "+prescrizione.getPrestazione();
                try {
                    BitMatrix bitMatrix = qrCodeWriter.encode(QRtext, BarcodeFormat.QR_CODE, 125, 125);

                    ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
                    MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);

                    OutputStream out = response.getOutputStream();
                    out.write(pngOutputStream.toByteArray());
                    out.flush();
                    out.close();
                }
                catch (Exception e) {
                    System.err.println("Error in generating QR code image");
                    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/error.jsp");
                    rd.forward(request,response);
                }
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