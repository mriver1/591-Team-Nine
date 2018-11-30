import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class sqlConnection {
	
	public static Connection dbConnection() {
		Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:/Users/lifengjun/Desktop/couse.db ";//root for db file
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established.");
            return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        }
