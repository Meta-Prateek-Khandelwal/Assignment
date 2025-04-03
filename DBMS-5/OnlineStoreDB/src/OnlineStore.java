import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/OnlineStore";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL Driver not found", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}

class Order {
    private int id;
    private Date orderDate;
    private double totalAmount;

    public Order(int id, Date orderDate, double totalAmount) {
        this.id = id;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Order ID: " + id + ", Date: " + orderDate + ", Total: " + totalAmount;
    }
}

class OrderDAO {
    
    public List<Order> getShippedOrders(int userId) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT orders_id, order_date, total_amount FROM Orders WHERE user_id = ? AND status = 'Shipped' ORDER BY order_date ASC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                orders.add(new Order(
                        rs.getInt("orders_id"),
                        rs.getDate("order_date"),
                        rs.getDouble("total_amount")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching orders: " + e.getMessage());
        }
        return orders;
    }

    public void insertProductImages(int productId, String[] imageUrls) {
        String query = "INSERT INTO image (product_id, url) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            for (String url : imageUrls) {
                stmt.setInt(1, productId);
                stmt.setString(2, url);
                stmt.addBatch();
            }
            stmt.executeBatch();
            System.out.println("Images inserted successfully!");
        } catch (SQLException e) {
            System.err.println("Batch insert error: " + e.getMessage());
        }
    }

    public int deleteUnusedProducts() {
        String query = "DELETE FROM product WHERE product_id NOT IN (SELECT DISTINCT c.product_id FROM cart AS c JOIN Orders AS o ON o.cart_id = c.cart_id WHERE o.order_date >= NOW() - INTERVAL 1 YEAR)";
        int deletedCount = 0;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            deletedCount = stmt.executeUpdate();
            System.out.println("Deleted Products: " + deletedCount);
        } catch (SQLException e) {
            System.err.println("Error deleting products: " + e.getMessage());
        }
        return deletedCount;
    }

    public void getParentCategories() {
        String query = "SELECT c1.category_name AS CategoryName, COUNT(c2.category_id) AS ChildCategoryCount FROM category AS c1 LEFT JOIN Category AS c2 ON c1.category_id = c2.parent_id WHERE c1.parent_id IS NULL GROUP BY c1.category_id, c1.category_name ORDER BY c1.category_name";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                System.out.println("Category: " + rs.getString("CategoryName") + " | Child Categories: " + rs.getInt("ChildCategoryCount"));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching categories: " + e.getMessage());
        }
    }
}

public class OnlineStore { 
    public static void main(String[] args) { 
        OrderDAO dao = new OrderDAO();
        System.out.println("Fetching Shipped Orders for User ID 4..."); 
        dao.getShippedOrders(4).forEach(System.out::println);
        System.out.println("\nInserting Product Images..."); 
        dao.insertProductImages(3, new String[]{ 
            "https://example.com/image1.jpg", 
            "https://example.com/image2.jpg" 
        });

        System.out.println("\nDeleting Unused Products..."); 
        dao.deleteUnusedProducts();
        System.out.println("\nFetching Parent Categories..."); 
        dao.getParentCategories(); 
    }
}