package Utils;

import java.sql.*;

public class DBUtil {
    // Update the port to 3307
	private static final String URL = "jdbc:mysql://localhost:3307/opencartproject";

    private static final String USER = "root";
    private static final String PASS = ""; 

    // Get connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    // Close resources
    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
