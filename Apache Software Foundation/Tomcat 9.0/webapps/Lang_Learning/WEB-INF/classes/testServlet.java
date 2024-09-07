import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;


@WebServlet("/test")
public class testServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Test</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background-color: #f0f0f0; }");
        out.println("h1 { color: #333; text-align: center; }");
        out.println("form { width: 80%; margin: 0 auto; background-color: #fff; padding: 20px; border-radius: 10px; box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); }");
        out.println("h2 { color: #333; }");
        out.println("textarea { width: 100%; height: 100px; padding: 10px; margin-bottom: 20px; border: 1px solid #ccc; border-radius: 5px; }");
        out.println("input[type='submit'] { background-color: #007bff; color: #fff; padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer; }");
        out.println("</style>");
        out.println("</head><body>");
        out.println("<h1>Test</h1>");
        out.println("<form action='test' method='post'>");
        out.println("<h2>Writing Section</h2>");
        out.println("<h3>Question 1: Write an essay on plagiarism</h3>");
        out.println("<textarea name='essay1'></textarea><br>");
        out.println("<h3>Question 2: Write a letter to your director about installing new facilities</h3>");
        out.println("<textarea name='letter1'></textarea><br>");
        out.println("<input type='submit' value='Submit'>");
        out.println("</form>");
        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String essay1 = request.getParameter("essay1");
        String letter1 = request.getParameter("letter1");
    
        // Write responses to a file
        try (PrintWriter writer = new PrintWriter(new FileWriter("responses.txt", true))) {
            writer.println("Response 1:");
            writer.println(essay1);
            writer.println();
            writer.println("Response 2:");
            writer.println(letter1);
            writer.println("--------------------------------------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        // Display a confirmation message to the user
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
    
        out.println("<html><head><title>Test Submitted</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background-color: #f0f0f0; text-align: center; }");
        out.println("h1 { color: #333; }");
        out.println("p { color: #007bff; }");
        out.println("</style>");
        out.println("</head><body>");
        out.println("<h1>Test Submitted</h1>");
        out.println("<p>Your responses have been recorded.</p>");
        out.println("</body></html>");
    }
    
}
