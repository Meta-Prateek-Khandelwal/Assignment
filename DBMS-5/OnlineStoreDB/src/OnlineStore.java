import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OnlineStore {
    private static final String url = "jdbc:mysql://localhost:3306/OnlineStore";
    private static final String username = "root";
    private static final String password = "root";

    public static void main(String[] args) throws Exception{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            Connection connection = DriverManager.getConnection(url,username, password);
            Statement statement = connection.createStatement();

            /*
             * Given the id of a user, fetch all orders (Id, Order Date, Order Total) of that user 
             * which are in shipped state. Orders should be sorted by order date column in chronological order.
             */
            int user_id = 4;
            String query1 = "SELECT orders_id, order_date, total_amount "+
                            "FROM Orders "+
                            "WHERE user_id = ? AND status = 'Delived' "+
                            "ORDER BY order_date ASC";

            try( PreparedStatement stmt1 = connection.prepareStatement(query1)) {
                stmt1.setInt(1, user_id);
                ResultSet resultSet1 = stmt1.executeQuery();
                // System.out.println();
                while(resultSet1.next()){
                    
                    int id = resultSet1.getInt("orders_id");
                    Date date = resultSet1.getDate("order_date");
                    double amount = resultSet1.getDouble("total_amount");

                    System.out.println("Order id : "+id+" Order date: "+date+" Total Amount: "+amount);
                }
            }
            
            String query2 = "INSERT INTO image (product_id, url) VALUES (?, ?)";
            try(PreparedStatement stmt2 = connection.prepareStatement(query2)) {
                
                int product_id = 3;
                String[] urls = {
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRI8RMWhycdMYWoh0GstFAzus1UWsCpEaryyw&s",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6GAyKG0bmi78IJjVpUZ00texG8OggHQmuCg&s"
                };

                for(String urlImage : urls){
                    stmt2.setInt(1, product_id);
                    stmt2.setString(2, urlImage);
                    stmt2.addBatch();
                }

                stmt2.executeBatch();
                System.out.println("Batch Insert Successful!");
            };
            // String query3 = "DELETE\n" + //
            //                     "FROM product \n" + //
            //                     "WHERE product_id NOT IN (\n" + //
            //                     "\t SELECT DISTINCT c.product_id\n" + //
            //                     "    FROM cart As c\n" + //
            //                     "    JOIN Orders As o\n" + //
            //                     "    ON o.cart_id = c.cart_id\n" + //
            //                     "    WHERE o.order_date >= NOW() - INTERVAL 1 YEAR\n" + //
            //                     ")";

            // try(PreparedStatement stmt3 = connection.prepareStatement(query3)) {
            //     int deletedRow = stmt3.executeUpdate();
            //     System.out.println("Deleted Rows: "+deletedRow);
            // }

            String query4 = "Select \n" + //
                                "\tC1.category_name As CategoryName,\n" + //
                                "\tCOUNT(c2.category_id) AS ChildCategoryCount\n" + //
                                "FROM category AS c1 \n" + //
                                "LEFT JOIN Category AS c2 \n" + //
                                "ON c1.category_id = c2.parent_id \n" + //
                                "WHERE c1.parent_id IS NULL \n" + //
                                "GROUP BY c1.category_id, c1.category_name \n" + //
                                "ORDER BY c1.category_name";
            
            try(ResultSet resultSet4 = statement.executeQuery(query4)){
            while(resultSet4.next()){
                String name = resultSet4.getString("CategoryName");
                int child = resultSet4.getInt("ChildCategoryCount");

                System.out.println("Category Name : "+ name+"| \tChild Category : "+child);
            }
            
        }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }
}
