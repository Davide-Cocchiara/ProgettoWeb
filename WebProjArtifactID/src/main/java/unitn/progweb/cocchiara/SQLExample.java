package unitn.progweb.cocchiara;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Properties;

/**
 * Example of how to communicate with database using JDBC
 *
 * @author Stefano Chirico
 * @version 1.0.0
 * @since 1.0.0 2019.04.01
 */

@WebServlet("/SQLEX")
public class SQLExample extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Impossible to find the JavaDB Driver: " + ex.getMessage());
            System.exit(1);
        }

        String url = "jdbc:postgresql://localhost/webprog";
        Properties props = new Properties();
        props.setProperty("user","postgres");
        props.setProperty("password","qwerty123");
        //props.setProperty("ssl","true");
        Connection conn;
        try {
            conn = DriverManager.getConnection(url, props);
        } catch (SQLException ex) {
            System.err.println("Impossible to connect to database: " + ex.getMessage());
            ex.printStackTrace(System.err);
            System.exit(1);
            return;
        }

        Statement stmt;
        try {
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            System.err.println("Impossible to create the connection statement: " + ex.getMessage());
            System.exit(1);
            return;
        }

        try {
            // Set response content type
            response.setContentType("text/html");

            // Actual logic goes here.
            PrintWriter out = response.getWriter();
            ResultSet results = stmt.executeQuery("SELECT * FROM users");
            while (results.next()) {
                String username = results.getString(1);
                String password = results.getString(2);
                out.println(username);
                out.println(String.format("\t%s %s", username, password));
                out.println("\n");
            }
        } catch (SQLException ex) {
            System.err.println("Impossible to get the users: " + ex.getMessage());
        }

        try {
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.err.println("Impossible to release the resources: " + ex.getMessage());
        }
    }
}
