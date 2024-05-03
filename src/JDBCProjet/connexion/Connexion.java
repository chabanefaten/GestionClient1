package JDBCProjet.connexion;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Connexion {
    private static Connection conn = null;

    public static Connection getConnexion(){
        if (conn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demoJDBC", "root", "");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
    
    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}




