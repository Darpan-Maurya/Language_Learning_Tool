import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Registration Form</title>");
        out.println("<style>");
        out.println("body {");
        out.println("  background-image: url('C:/Screenshot (168).png');");
        out.println("  background-size: cover;");
        out.println("}");
        out.println(".content {");
        out.println("  width: 50%;");
        out.println("  margin: 0 auto;");
        out.println("  padding: 20px;");
        out.println("  background-color: rgba(255, 0, 0, 0.5);"); // Red background color
        out.println("  border-radius: 10px;");
        out.println("}");
        out.println("form {");
        out.println("  display: flex;");
        out.println("  flex-direction: column;");
        out.println("}");
        out.println("label {");
        out.println("  width: 200px;"); // Set a consistent width for labels
        out.println("}");
        out.println("input, select {");
        out.println("  width: 300px;"); // Set a consistent width for input fields
        out.println("  margin-bottom: 10px;");
        out.println("}");
        out.println("input[type='submit'] {");
        out.println("  width: 100px;"); // Set a small width for the submit button
        out.println("}");
        out.println("</style>");
        out.println("</head><body>");
        out.println("<div class='content'>");
        out.println("<h1>Registration Form</h1>");
        out.println("<form action='register' method='post'>");
        out.println("<label for='name'>Name:</label><input type='text' name='name'><br>");
        out.println("<label for='password'>Password:</label><input type='password' name='password'><br>");
        out.println("<label for='retypePassword'>Retype Password:</label><input type='password' name='retypePassword'><br>");
        out.println("<label for='email'>Email:</label><input type='email' name='email'><br>");
        out.println("<input type='submit' value='Register'>");
        
        out.println("<div id='languageLearning' style='position: fixed; bottom: 10px; right: 10px; font-size: 60px; color: transparent; text-shadow: 10px 10px 10px rgba(0,0,0,3), -1px -1px 0 black, 1px -1px 0 black, -1px 1px 0 black, 1px 1px 0 black; background-color: blue; -webkit-background-clip: text; background-clip: text; opacity: 0;'>LANGUAGE LEARNING</div>");
        out.println("<style>");
        out.println("@keyframes fadeIn {");
        out.println("  from { opacity: 0; }");
        out.println("  to { opacity: 1; }");
        out.println("}");
        out.println("#languageLearning {");
        out.println("  animation: fadeIn 6s forwards;");
        out.println("  color: red;");
        out.println("}");
        out.println("</style>");

        

        out.println("</form>");
        out.println("</div>");
        out.println("</body></html>");
    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Process registration data here
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String retypePassword = request.getParameter("retypePassword");
        String email = request.getParameter("email");

        // Get the current date and time
        Timestamp registrationDate = new Timestamp(System.currentTimeMillis());

        // You can perform further operations like storing the data in a database here
        String jdbcUrl = "jdbc:mysql://localhost:3306/language_learning_tool";
        String username = "root";
        String password1 = "darpan";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password1)) {
            String sql = "INSERT INTO users (username, password, email, registration_date) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, name);
                statement.setString(2, password);
                statement.setString(3, email);
                statement.setTimestamp(4, registrationDate);
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new user was registered successfully.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

                out.println("<html><head><title>Registration Successful</title>");
        out.println("<style>");
        out.println("body {");
        out.println("  background-image: url('C:/Screenshot (168).png');");
        out.println("  background-size: cover;");
        out.println("}");
        out.println(".content {");
        out.println("  width: 50%;");
        out.println("  margin: 0 auto;");
        out.println("  padding: 20px;");
        out.println("  background-color: rgba(255, 0, 0, 0.5);"); // Red background color
        out.println("  border-radius: 10px;");
        out.println("}");
        out.println(".success {");
        out.println("  margin-top: 20px;"); // Add margin to separate from the inputs
        out.println("}");
        out.println(".success label {");
        out.println("  margin-bottom: 20px;"); // Add a larger margin between labels
        out.println("}");
        out.println("</style>");
        out.println("</head><body>");
        out.println("<div class='content'>");
        out.println("<h1>Registration Successful</h1>");
        out.println("<div class='success'>");
        out.println("<label for='name'>Name:</label><input type='text' name='name' value='" + name + "' readonly><br>");
        out.println("<label for='email'>Email:</label><input type='email' name='email' value='" + email + "' readonly><br>");
        out.println("<p>Entry added to database.</p>");
        out.println("<div id='languageLearning' style='position: fixed; bottom: 100px; right: 100px; font-size: 60px; color: transparent; text-shadow: 10px 10px 10px rgba(0,0,0,3), -1px -1px 0 black, 1px -1px 0 black, -1px 1px 0 black, 1px 1px 0 black; background-color: blue; -webkit-background-clip: text; background-clip: text; opacity: 0;'>LANGUAGE LEARNING</div>");
        out.println("<style>");
        out.println("@keyframes fadeIn {");
        out.println("  from { opacity: 0; }");
        out.println("  to { opacity: 1; }");
        out.println("}");
        out.println("#languageLearning {");
        out.println("  animation: fadeIn 6s forwards;");
        out.println("  color: red;");
        out.println("}");
        out.println("</style>");
        out.println("</div>");
        out.println("</div>");
        out.println("</body></html>");



    }
}
