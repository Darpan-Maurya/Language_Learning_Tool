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
    // Assuming you have a method to update checkbox values in the database
public static void updateCheckbox(String columnName, boolean value, int userId) {
    try (Connection connection = getConnection()) 
    {
        String query = "UPDATE last_login_update SET " + columnName + " = ? WHERE user_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setBoolean(1, value);
        statement.setInt(2, userId);
        statement.executeUpdate();
    } 
    catch (SQLException e) 
    {
        System.err.println("Failed to update checkbox: " + e.getMessage());
    }
}

    public static boolean checkLogin(String enteredUsername, String enteredPassword) 
    {
        boolean loginSuccessful = false;
        try (Connection connection = DriverManager.getConnection(url, username, password)) 
        {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, enteredUsername);
            statement.setString(2, enteredPassword);
            ResultSet resultSet = statement.executeQuery();
            loginSuccessful = resultSet.next();
            
        } 
        catch (SQLException e) 
        {
            System.err.println("Failed to check login: " + e.getMessage());
        }
        return loginSuccessful;
    }
    public static String updateLastLogin(String usrname) 
    {
        try (Connection connection = DriverManager.getConnection(url, username, password)) 
        {
            String query = "UPDATE users SET last_login = ? WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = sdf.format(new Date());
            statement.setString(1, currentTime);
            statement.setString(2, usrname);
            statement.executeUpdate();
            System.out.println("Last login updated for user: " + usrname);
            return currentTime;
        } 
        catch (SQLException e) 
        {
            System.err.println("Failed to update last login: " + e.getMessage());
            return "0";
        }
        
    }
    public static boolean isWithinTwoMinutes(String usrname) 
    {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT last_login FROM users WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, usrname);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String lastLoginStr = resultSet.getString("last_login");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date lastLogin = sdf.parse(lastLoginStr);
                Date now = new Date();
                long diff = now.getTime() - lastLogin.getTime();
                long diffMinutes = diff / (60 * 1000) % 60;
                return diffMinutes < 2;
            } 
            else 
            {
                return false;
            }
        } 
        catch (SQLException | java.text.ParseException e) 
        {
            System.err.println("Failed to check last login time: " + e.getMessage());
            return false;
        }
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
    public String getLastLoginUpdate(int userId) {
      String values="";
    try (Connection connection = DriverManager.getConnection(url, username, password)) {
        String query = "SELECT * FROM last_login_update WHERE user_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, userId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int id = resultSet.getInt("user_id");
            boolean testCompleted = resultSet.getBoolean("test_completed");
            boolean bookCompleted = resultSet.getBoolean("book_completed");
            boolean exerciseCompleted = resultSet.getBoolean("exercise_completed");
            boolean speakingCompleted = resultSet.getBoolean("speaking_completed");
            boolean writingCompleted = resultSet.getBoolean("writing_completed");
            values=Boolean.toString(testCompleted)+","+Boolean.toString(bookCompleted)+","+Boolean.toString(exerciseCompleted)+","+Boolean.toString(speakingCompleted)+","+Boolean.toString(writingCompleted);
        }

    } catch (SQLException e) {
        System.err.println("Failed to retrieve last login update: " + e.getMessage());
    }
    return values;
}
    public static void insertSessionRecord(int usrid, String loginTime) 
    {
        
        try (Connection connection = getConnection()) {
        String query = "INSERT INTO session_info (user_id, login_time, logout_time) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, usrid);
        statement.setString(2, loginTime);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(new Date());
        statement.setString(3, currentTime);
        statement.executeUpdate();
        System.out.println("Session record inserted for user: ");
    } 
    catch (SQLException e) 
    {
        System.err.println("Failed to insert session record: " + e.getMessage());
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
