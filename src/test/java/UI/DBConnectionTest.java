package UI;
import Utils.DBUtil;
import java.sql.Connection;
public class DBConnectionTest {
	public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null && !conn.isClosed()) {
                System.out.println("✅ Database connection successful!");
            } else {
                System.out.println("❌ Failed to connect to database.");
            }
        } catch (Exception e) {
            System.out.println("❌ Error connecting to database: " + e.getMessage());
        } finally {
            DBUtil.close(conn, null, null);
        }
    }
}
