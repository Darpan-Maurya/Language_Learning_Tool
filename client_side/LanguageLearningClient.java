import java.io.*;
import java.net.*;

class LanguageLearningClient {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public LanguageLearningClient() {
        try {
            socket = new Socket("localhost", 54321); // Connect to server
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) 
    {
        out.println(message); // Send message to server
    }

    public String receiveMessage() 
    {
        try {
            return in.readLine(); // Receive message from server
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            return null;
        }
    }

    public void closeConnection() 
    {
        try {
            socket.close(); // Close connection
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}
