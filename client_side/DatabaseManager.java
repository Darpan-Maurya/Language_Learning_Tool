import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;
public class DatabaseManager 
  {
    private static final String url = "jdbc:mysql://localhost:3306/language_learning_tool";
    private static final String username = "root";
    private static final String password = "darpan";
    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(url, username, password);
                System.out.println("Connected to the database");
            }
        } 
        catch (SQLException e) 
        {
            System.err.println("Failed to connect to the database: " + e.getMessage());
        }
        return connection;
    }

    public static int getUserId(String usrname) throws SQLException 
    {
        String query = "SELECT id FROM users WHERE username = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, usrname);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            } 
            else 
            {
                throw new SQLException("User not found");
            }
        }
    }
   
    
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Disconnected from the database");
            }
        } catch (SQLException e) {
            System.err.println("Failed to close the database connection: " + e.getMessage());
        }
    }
}
