import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.platform.suite.api.Select;

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
            String query2 = "INSERT INTO image (product_id, url) VALUES (?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query2) ){
                
                int product_id = 3;
                String[] urls = {
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRI8RMWhycdMYWoh0GstFAzus1UWsCpEaryyw&s",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6GAyKG0bmi78IJjVpUZ00texG8OggHQmuCg&s"
                };

                for(String urlImage : urls){
                    stmt.setInt(1, product_id);
                    stmt.setString(2, urlImage);
                    stmt.addBatch();
                }

                stmt.executeBatch();
                System.out.println("Batch Inset Successful!");
            };
            String query3 = "DELETE\n" + //
                                "FROM Product \n" + //
                                "WHERE product_id NOT IN (\n" + //
                                "\tSELECT DISTINCT c.product_id\n" + //
                                "    FROM cart As c\n" + //
                                "    JOIN Orders As o\n" + //
                                "    ON o.cart_id = c.cart_id\n" + //
                                "    WHERE o.order_date >= NOW() - INTERVAL 1 YEAR\n" + //
                                ")";
            statement.executeUpdate(query3);

            String query4 = "Select \n" + //
                                "\tC1.category_name As CategoryName,\n" + //
                                "\tCOUNT(c2.category_id) AS ChildCategoryCount\n" + //
                                "FROM category AS c1 \n" + //
                                "LEFT JOIN Category AS c2 \n" + //
                                "ON c1.category_id = c2.parent_id \n" + //
                                "WHERE c1.parent_id IS NULL \n" + //
                                "GROUP BY c1.category_id, c1.category_name \n" + //
                                "ORDER BY c1.category_name";
            
            int deletedRow = statement.executeUpdate(query3);
            ResultSet resultSet4 = statement.executeQuery(query4);

            while(resultSet4.next()){
                String name = resultSet4.getString("CategoryName");
                int child = resultSet4.getInt("ChildCategoryCount");

                System.out.print("Category Name : "+ name);
                System.out.println("\tChild Category : "+child);
            }

            System.out.println(deletedRow);
            
            resultSet3.close();
            resultSet4.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }
}
