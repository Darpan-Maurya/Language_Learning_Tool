import java.io.*;
import java.net.*;
import java.sql.*;
public class LanguageLearningServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(54321); // Port number for the server
            System.out.println("Server started. Waiting for clients...");

            while (true) 
            {
                Socket clientSocket = serverSocket.accept(); // Wait for a client to connect
                System.out.println("Client connected: " + clientSocket.getInetAddress());
                DatabaseManager obj =  new DatabaseManager();
                //obj.getConnection();
                
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String message = in.readLine();
                System.out.println("Received message from client: " + message);
                String[] parts=message.split(" ");
                String usrname,psswrd;
                usrname=parts[2];
                psswrd=parts[5];
                // System.out.println(usrname+psswrd);
                // Execute function based on message
                boolean response = obj.checkLogin(usrname,psswrd);
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                if (response) 
                {
                    if (!obj.isWithinTwoMinutes(usrname)) 
                    {
                        out.println("Login successful");
                        String login_Time=obj.updateLastLogin(usrname);
                        out.println(response ? "true":"false");
                        try
                        {
                        int x=obj.getUserId(usrname);
                        out.println(obj.getLastLoginUpdate(x));
                        }
                        catch( SQLException e)
                        {
                            System.err.println("failed to retrieve from database");
                        }
                        
                        while(true)
                        {
                            message = in.readLine();
                            if(message.equals("LOGOUT"))
                            {
                                System.out.println("Received message from client: " + message);
                                try
                                {
                                 int userid=obj.getUserId(usrname);
                                obj.insertSessionRecord(userid,login_Time);
                                break;
                                }
                               catch(SQLException e)
                               {
                                   System.err.println("error in setting enrty");
                                }
                            }
                            else if(message.equals("11"))
                            {
                                try
                                {
                                    int userid=obj.getUserId(usrname);
                                obj.updateCheckbox("test_completed",true,userid);
                                }
                                catch(SQLException e)
                                {
                                    System.err.println("unable to update database");
                                }
                            }
                            else if(message.equals("21"))
                            {
                                try
                                {
                                    int userid=obj.getUserId(usrname);
                                obj.updateCheckbox("book_completed",true,userid);
                                }
                                catch(SQLException e)
                                {
                                    System.err.println("unable to update database");
                                }
                            }
                            else if(message.equals("31"))
                            {
                                try
                                {
                                    int userid=obj.getUserId(usrname);
                                obj.updateCheckbox("exercise_completed",true,userid);
                                }
                                catch(SQLException e)
                                {
                                    System.err.println("unable to update database");
                                }
                            }
                            else if(message.equals("41"))
                            {
                                try
                                {
                                    int userid=obj.getUserId(usrname);
                                obj.updateCheckbox("speaking_completed",true,userid);
                                }
                                catch(SQLException e)
                                {
                                    System.err.println("unable to update database");
                                }
                            }
                            else if(message.equals("51"))
                            {
                                try
                                {
                                    int userid=obj.getUserId(usrname);
                                obj.updateCheckbox("writing_completed",true,userid);
                                }
                                catch(SQLException e)
                                {
                                    System.err.println("unable to update database");
                                }
                            }
                            else if(message.equals("10"))
                            {
                                try
                                {
                                    int userid=obj.getUserId(usrname);
                                obj.updateCheckbox("test_completed",false,userid);
                                }
                                catch(SQLException e)
                                {
                                    System.err.println("unable to update database");
                                }
                            }
                            else if(message.equals("20"))
                            {
                                try
                                {
                                    int userid=obj.getUserId(usrname);
                                obj.updateCheckbox("book_completed",false,userid);
                                }
                                catch(SQLException e)
                                {
                                    System.err.println("unable to update database");
                                }
                            }
                            else if(message.equals("30"))
                            {
                                try
                                {
                                    int userid=obj.getUserId(usrname);
                                obj.updateCheckbox("exercise_completed",false,userid);
                                }
                                catch(SQLException e)
                                {
                                    System.err.println("unable to update database");
                                }
                            }
                            else if(message.equals("40"))
                            {
                                try
                                {
                                    int userid=obj.getUserId(usrname);
                                obj.updateCheckbox("speaking_completed",false,userid);
                                }
                                catch(SQLException e)
                                {
                                    System.err.println("unable to update database");
                                }
                            }
                            else if(message.equals("50"))
                            {
                                try
                                {
                                    int userid=obj.getUserId(usrname);
                                obj.updateCheckbox("writing_completed",false,userid);
                                }
                                catch(SQLException e)
                                {
                                    System.err.println("unable to update database");
                                }
                            }
                        }
                        
                        
                    }
                    else 
                    {
                        out.println("Login failed - Last login was less than 2 minutes ago");
                        response=false;
                    }
                } 
                else 
                {
                    out.println("Login failed - Invalid username or password");
                }
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}  