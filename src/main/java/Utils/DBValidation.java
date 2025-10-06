package Utils;

import java.sql.*;

public class DBValidation {

    // ----------------- Customer Methods -----------------
    public boolean isCustomerPresent(String email) {
        boolean exists = false;
        Connection conn = null; Statement stmt = null; ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            String query = "SELECT * FROM oc_customer WHERE email='" + email + "'";
            rs = stmt.executeQuery(query);
            if (rs.next()) exists = true;
        } catch (SQLException e) { e.printStackTrace(); }
        finally { DBUtil.close(conn, stmt, rs); }
        return exists;
    }

    public int getCustomerIdByEmail(String email) {
        int id = 0;
        Connection conn = null; Statement stmt = null; ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            String query = "SELECT customer_id FROM oc_customer WHERE email='" + email + "'";
            rs = stmt.executeQuery(query);
            if (rs.next()) id = rs.getInt("customer_id");
        } catch (SQLException e) { e.printStackTrace(); }
        finally { DBUtil.close(conn, stmt, rs); }
        return id;
    }

    // ----------------- Product Methods -----------------
    public boolean isProductPresent(String productName) {
        boolean exists = false;
        Connection conn = null; Statement stmt = null; ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            String query = "SELECT * FROM oc_product_description WHERE name='" + productName + "'";
            rs = stmt.executeQuery(query);
            if (rs.next()) exists = true;
        } catch (SQLException e) { e.printStackTrace(); }
        finally { DBUtil.close(conn, stmt, rs); }
        return exists;
    }

    public int getProductIdByName(String productName) {
        int id = 0;
        Connection conn = null; Statement stmt = null; ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            String query = "SELECT product_id FROM oc_product_description WHERE name='" + productName + "'";
            rs = stmt.executeQuery(query);
            if (rs.next()) id = rs.getInt("product_id");
        } catch (SQLException e) { e.printStackTrace(); }
        finally { DBUtil.close(conn, stmt, rs); }
        return id;
    }

    public boolean isProductPresent(String productName, String price) {
        boolean exists = false;
        Connection conn = null; Statement stmt = null; ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            String query;
            if (price != null && !price.isEmpty()) {
                query = "SELECT p.product_id FROM oc_product p " +
                        "JOIN oc_product_description d ON p.product_id = d.product_id " +
                        "WHERE d.name='" + productName + "' AND p.price='" + price + "'";
            } else {
                query = "SELECT p.product_id FROM oc_product p " +
                        "JOIN oc_product_description d ON p.product_id = d.product_id " +
                        "WHERE d.name='" + productName + "'";
            }
            rs = stmt.executeQuery(query);
            if (rs.next()) exists = true;
        } catch (SQLException e) { e.printStackTrace(); }
        finally { DBUtil.close(conn, stmt, rs); }
        return exists;
    }

    public boolean isProductUpdated(String productName, double price) {
        boolean updated = false;
        Connection conn = null; Statement stmt = null; ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            String query = "SELECT p.price FROM oc_product p " +
                    "JOIN oc_product_description d ON p.product_id = d.product_id " +
                    "WHERE d.name='" + productName + "' AND p.price=" + price;
            rs = stmt.executeQuery(query);
            if (rs.next()) updated = true;
        } catch (SQLException e) { e.printStackTrace(); }
        finally { DBUtil.close(conn, stmt, rs); }
        return updated;
    }

    public boolean isProductDeleted(String productName) {
        return !isProductPresent(productName);
    }

    // ----------------- Cart Methods -----------------
    public boolean isCartUpdated(int customerId, int productId) {
        boolean exists = false;
        Connection conn = null; Statement stmt = null; ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            String query = "SELECT * FROM oc_cart WHERE customer_id=" + customerId + " AND product_id=" + productId;
            rs = stmt.executeQuery(query);
            if (rs.next()) exists = true;
        } catch (SQLException e) { e.printStackTrace(); }
        finally { DBUtil.close(conn, stmt, rs); }
        return exists;
    }

    public boolean isCartQuantityUpdated(int customerId, int productId, int qty) {
        boolean updated = false;
        Connection conn = null; Statement stmt = null; ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            String query = "SELECT * FROM oc_cart WHERE customer_id=" + customerId +
                    " AND product_id=" + productId + " AND quantity=" + qty;
            rs = stmt.executeQuery(query);
            if (rs.next()) updated = true;
        } catch (SQLException e) { e.printStackTrace(); }
        finally { DBUtil.close(conn, stmt, rs); }
        return updated;
    }

    public boolean isCouponApplied(int customerId, String couponCode) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean applied = false;

        try {
            conn = DBUtil.getConnection();

            // Query to check if coupon was applied for this customer
            String query = "SELECT COUNT(*) AS count " +
                           "FROM oc_coupon_history ch " +
                           "JOIN oc_coupon c ON ch.coupon_id = c.coupon_id " +
                           "JOIN oc_order o ON ch.order_id = o.order_id " +
                           "WHERE o.customer_id = ? AND c.code = ?";

            ps = conn.prepareStatement(query);
            ps.setInt(1, customerId);
            ps.setString(2, couponCode);
            rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt("count");
                applied = count > 0;
                System.out.println("DB Check - Customer ID: " + customerId + ", Coupon: " + couponCode + ", Applied Count: " + count);
            } else {
                System.out.println("DB Check - Customer ID: " + customerId + ", Coupon: " + couponCode + " not found in DB.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }

        return applied;
    }



    // ----------------- Order Methods -----------------
    public boolean isOrderCreated(int orderId) {
        boolean exists = false;
        Connection conn = null; Statement stmt = null; ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            String query = "SELECT * FROM oc_order WHERE order_id=" + orderId;
            rs = stmt.executeQuery(query);
            if (rs.next()) exists = true;
        } catch (SQLException e) { e.printStackTrace(); }
        finally { DBUtil.close(conn, stmt, rs); }
        return exists;
    }

    public boolean isOrderCreatedForCustomer(int customerId) {
        boolean exists = false;
        Connection conn = null; Statement stmt = null; ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            String query = "SELECT * FROM oc_order WHERE customer_id=" + customerId;
            rs = stmt.executeQuery(query);
            if (rs.next()) exists = true;
        } catch (SQLException e) { e.printStackTrace(); }
        finally { DBUtil.close(conn, stmt, rs); }
        return exists;
    }

    public boolean isOrderReturned(int orderId) {
        boolean returned = false;
        Connection conn = null; Statement stmt = null; ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            String query = "SELECT * FROM oc_return WHERE order_id = " + orderId;
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                returned = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, stmt, rs);
        }
        return returned;
    }

 // ----------------- Order Methods -----------------
    public int getLatestOrderIdForCustomer(int customerId) {
        int orderId = 0;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            String query = "SELECT order_id FROM oc_order WHERE customer_id = " + customerId + " ORDER BY order_id DESC LIMIT 1";
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                orderId = rs.getInt("order_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, stmt, rs);
        }
        return orderId;
    }


    public void clearCustomerOrders(int customerId) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConnection();
            String query = "DELETE FROM oc_order WHERE customer_id = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, customerId);
            ps.executeUpdate();
            System.out.println("Existing orders cleared for customer: " + customerId);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, null);
        }
    }
    public boolean isLatestOrderReturnedForCustomer(int customerId) {
        boolean returned = false;
        Connection conn = null; 
        Statement stmt = null; 
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            String query = "SELECT * FROM oc_return WHERE customer_id = " + customerId + " ORDER BY return_id DESC LIMIT 1";
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                returned = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, stmt, rs);
        }
        return returned;
    }

    
}
