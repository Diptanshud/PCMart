import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    public Connection getConn(String dbUrl, String username, String password) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbUrl, username, password);
            System.out.println(" connection established");
            return conn;
        } catch (SQLException e) {
            System.out.println("Could not connect to database");
            e.printStackTrace();
            return null;
        }
    }
}

