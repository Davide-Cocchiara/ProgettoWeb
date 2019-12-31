package unitn.progweb.cocchiara.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Properties;

public class BasicDAO {

    public BasicDAO() {

    }

    static protected Connection startConnection() {
        String username ="";
        String password = "";
        String dbString = "";
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Impossible to find the JavaDB Driver: " + ex.getMessage());
            System.exit(1);
        }
        try {
            Context ctx = new InitialContext();
            Context env = (Context) ctx.lookup("java:comp/env");
            username = (String) env.lookup("dbuser");
            password = (String) env.lookup("dbpass");
            dbString = (String) env.lookup("dbstring");

        } catch (NamingException ex) {
            System.err.println("Impossible to find envirorment context: " + ex.getMessage());
            System.exit(1);
        }

        Properties props = new Properties();
        props.setProperty("user",username);
        props.setProperty("password",password);
        //props.setProperty("ssl","true");
        Connection conn= null;
        try {
            conn = DriverManager.getConnection(dbString, props);
        } catch (SQLException ex) {
            System.err.println("Impossible to connect to database: " + ex.getMessage());
            ex.printStackTrace(System.err);
            System.exit(1);
        }
        return conn;
    }

    static protected Statement readyBasicStatement(Connection conn) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            System.err.println("Impossible to create the statement: " + ex.getMessage());
            System.exit(1);
        }
        return stmt;
    }
// Example query; Should be never used if not for testing. Extracts the users and sends them as obj string.
     public String exampleUserQuery() {
        String query = "SELECT username,type FROM account";
        Connection conn = startConnection();
        Statement stmt = readyBasicStatement(conn);
        String out = "";
        try {
            ResultSet results = stmt.executeQuery(query);
            while (results.next()) {
                String username = results.getString(1);
                String type = results.getString(2);
                out += (String.format("\t%s %s<br/>", username, type));
            }
            results.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException ex) {
            System.err.println("Impossible to get the users: " + ex.getMessage());
        }
        return out;
    }

}
