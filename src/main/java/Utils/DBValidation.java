package Utils;

import java.sql.*;

public class DBValidation {

    // Check if customer exists by email
    public boolean isCustomerPresent(String email) {
        boolean exists = false;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            String query = "SELECT * FROM oc_customer WHERE email='" + email + "'";
            rs = stmt.executeQuery(query);
            if (rs.next()) exists = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, stmt, rs);
        }
        return exists;
    }

    // Check if product exists by name
    public boolean isProductPresent(String productName) {
        boolean exists = false;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            String query = "SELECT * FROM oc_product WHERE name='" + productName + "'";
            rs = stmt.executeQuery(query);
            if (rs.next()) exists = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, stmt, rs);
        }
        return exists;
    }

    // Check if cart is updated for a customer and product
    public boolean isCartUpdated(int customerId, int productId) {
        boolean exists = false;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            String query = "SELECT * FROM oc_cart WHERE customer_id=" + customerId + " AND product_id=" + productId;
            rs = stmt.executeQuery(query);
            if (rs.next()) exists = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, stmt, rs);
        }
        return exists;
    }

    // Check if order exists by order_id
    public boolean isOrderCreated(int orderId) {
        boolean exists = false;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            String query = "SELECT * FROM oc_order WHERE order_id=" + orderId;
            rs = stmt.executeQuery(query);
            if (rs.next()) exists = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, stmt, rs);
        }
        return exists;
    }
}
