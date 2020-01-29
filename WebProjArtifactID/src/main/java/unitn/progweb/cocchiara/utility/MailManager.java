package unitn.progweb.cocchiara.utility;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.naming.Context;
import javax.naming.InitialContext;

public class MailManager {
    public static void SendMail(String to, String msg) {
        Thread t = new Thread() {
            public void run() {
                //Thread Implmentation code here
                try {


                Context ctx = new InitialContext();
                Context env = (Context) ctx.lookup("java:comp/env");

                String host = (String) env.lookup("mailhost");
                final String user = (String) env.lookup("mailusername");
                final String password = (String) env.lookup("mailuserpassword");


                //Get the session object
                Properties props = new Properties();
                props.put("mail.smtp.host", host);
                props.put("mail.smtp.auth", "true");

                Session session = Session.getDefaultInstance(props,
                        new javax.mail.Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(user, password);
                            }
                        });

                //Compose the message

                    MimeMessage message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(user));
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                    message.setSubject("javatpoint");
                    message.setText(msg);

                    //send the message
                    Transport.send(message);


                } catch (Exception e) {
                    e.printStackTrace()
                    ;
                }

            }

        };
        t.start();


    }

}  