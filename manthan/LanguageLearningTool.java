import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.sql.*;

class LanguageLearningTool  
{
    public static void maain() 
    {
        LanguageLearningClient c1=new LanguageLearningClient();
       // boolean isConnected = true;
         // Create the main login frame
        JFrame f = new JFrame("Language Learner Tool");
        DatabaseManager obj = new DatabaseManager();
         // Login screen components
        final JLabel label = new JLabel();
        label.setBounds(360, 220, 200, 50);
        final JPasswordField value = new JPasswordField();
        value.setBounds(360, 110, 100, 30);
        JLabel l1 = new JLabel("Username:");
        l1.setBounds(280, 60, 80, 30);
        JLabel l2 = new JLabel("Password:");
        l2.setBounds(280, 110, 80, 30);
        JButton b = new JButton("Login");
        b.setBounds(360, 190, 80, 30);
        final JTextField text = new JTextField();
        text.setBounds(360, 60, 100, 30);
        ImageIcon backgroundIcon = new ImageIcon("C:\\Users\\RAKESH MOURYA\\Pictures\\Screenshots\\Screenshot (129).png");
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        
        // Add components to the login frame
        backgroundLabel.setLayout(null);
        backgroundLabel.add(value);
        backgroundLabel.add(l1);
        backgroundLabel.add(label);
        backgroundLabel.add(l2);
        backgroundLabel.add(b);
        backgroundLabel.add(text);
        backgroundLabel.setSize(500, 500);

        f.setContentPane(backgroundLabel);
        f.setLayout(null);                   // Use null layout for absolute positioning
        f.setVisible(true);
        f.setSize(500, 500);
        // EVENT HANDLING  using action listener
        b.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                String data = text.getText();
                String data1 = new String(value.getPassword());
                c1.sendMessage("username = "+data+" passwword = "+data1);
                System.out.println(c1.receiveMessage());
                String received_message=c1.receiveMessage();
                System.out.println(received_message);
                // action listener to check password field
                if (received_message.equals("true")) 
                {
                    label.setText("Login Accepted");// Login successful
                    // Close the login frame
                    f.dispose();    

                    JFrame newFrame = new JFrame("Language Learner Tool");
                    newFrame.setSize(500, 500);
                    newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                    // Create menu bar and menus
                    JMenuBar menuBar = new JMenuBar();
                    JMenu helpMenu = new JMenu("Help");
                    helpMenu.setPreferredSize(new Dimension(50,30));
                    JMenu settingsMenu = new JMenu("Settings");
                    settingsMenu.setPreferredSize(new Dimension(60,30));
                    JMenuItem changeLanguageItem = new JMenuItem("Change Language");
                    JMenuItem logoutItem = new JMenuItem("Log Out");
                    
                    // Create submenu for Add Language
                    JMenu addLanguageMenu = new JMenu("Add Language");
                    JCheckBoxMenuItem japaneseItem = new JCheckBoxMenuItem("Japanese");
                    JCheckBoxMenuItem sanskritItem = new JCheckBoxMenuItem("Sanskrit");
                    JCheckBoxMenuItem englishItem = new JCheckBoxMenuItem("English");
                    JCheckBoxMenuItem frenchItem = new JCheckBoxMenuItem("French");
                    JCheckBoxMenuItem hindiItem = new JCheckBoxMenuItem("Hindi");

                    // Add menu items to menus
                    addLanguageMenu.add(japaneseItem);
                    addLanguageMenu.add(sanskritItem);
                    addLanguageMenu.add(englishItem);
                    addLanguageMenu.add(frenchItem);
                    addLanguageMenu.add(hindiItem);
                    frenchItem.setSelected(true);  // Set French as default learning language
                    settingsMenu.add(changeLanguageItem);
                    settingsMenu.add(logoutItem);
                    
                    // event handling if logout menuitem is selected
                    logoutItem.addActionListener(new ActionListener() 
                  {
                    public void actionPerformed(ActionEvent e) 
                    {
                         newFrame.dispose();// Close the current frame
                         c1.sendMessage("LOGOUT"); // Send logout message to server
                        c1.closeConnection(); // Close the connection
                        //isConnected = false;
                         //f.setVisible(true); // Reopen the login frame
                    }
                  });
                    settingsMenu.add(addLanguageMenu);
                    menuBar.add(helpMenu);
                    menuBar.add(settingsMenu);
                    newFrame.setJMenuBar(menuBar);
                    
                    // Create dashboard panel
                    JPanel panel=new JPanel();  
                    panel.setBounds(40,80,300, 300);    
                    panel.setBackground(Color.gray);
                    JLabel l0 = new JLabel("Dashboard:");
                    l0.setBounds(40, 50, 80, 30);
                    
                    // Language buttons
                    JButton b1=new JButton("French");     
                    b1.setBounds(0,20,90,30);    
                    b1.setBackground(Color.white);   
                    JButton b2=new JButton("Sanskrit");   
                    b2.setBounds(90,20,90,30);    
                    b2.setBackground(Color.white);
                    JButton b3=new JButton("English");   
                    b3.setBounds(180,20,90,30);    
                    b3.setBackground(Color.white);
                    panel.add(b1); 
                    panel.add(b2); 
                    panel.add(b3);
                     b1.setVisible(true);
                     b2.setVisible(false);
                     b3.setVisible(false);
                     
                     // Language checkboxes listener
                    frenchItem.addItemListener(new ItemListener() 
                    {
                       public void itemStateChanged(ItemEvent e) 
                       {
                         if (frenchItem.isSelected()) 
                          {
                            b1.setVisible(true);
                          } 
                         else 
                         {
                            b1.setVisible(false);
                         }
                       }
                    });
                    sanskritItem.addItemListener(new ItemListener() 
                    {
                       public void itemStateChanged(ItemEvent e) 
                       {
                         if (sanskritItem.isSelected()) 
                          {
                            b2.setVisible(true);
                          } 
                         else 
                         {
                            b2.setVisible(false);
                         }
                       }
                    });
                    englishItem.addItemListener(new ItemListener() 
                    {
                       public void itemStateChanged(ItemEvent e) 
                       {
                         if (englishItem.isSelected()) 
                          {
                            b3.setVisible(true);
                          } 
                         else 
                         {
                            b3.setVisible(false);
                         }
                       }
                    });
                                // Other checkboxes and progress bar
                                    JCheckBox testCheckbox = new JCheckBox("Test");
                                    JCheckBox bookCheckbox = new JCheckBox("Books");
                                    JCheckBox exerciseCheckbox = new JCheckBox("Exercise");
                                    JCheckBox speakingCheckbox = new JCheckBox("Speaking");
                                    JCheckBox writingCheckbox = new JCheckBox("Writing");
                            
                                try
                                {
                                    String value;
                                    boolean[] values={false,false,false,false,false};
                                    value=c1.receiveMessage();
                                    String[] value1=value.split(",");
                                    for(int i=0;i<=4;i++)
                                    {
                                        values[i]=Boolean.parseBoolean(value1[i]);
                                    }
                                    testCheckbox.setSelected(values[0]);
                                    bookCheckbox.setSelected(values[1]);
                                    exerciseCheckbox.setSelected(values[2]);
                                    speakingCheckbox.setSelected(values[3]);
                                    writingCheckbox.setSelected(values[4]);
                                }
                                catch(Exception ex)
                                {
                                    ex.printStackTrace();
                                }
                                    
                                    testCheckbox.setBounds(40, 80, 100, 30);
                                    bookCheckbox.setBounds(40, 110, 100, 30);
                                    exerciseCheckbox.setBounds(40, 140, 100, 30);
                                    speakingCheckbox.setBounds(40, 170, 100, 30);
                                    writingCheckbox.setBounds(40, 200, 100, 30);
                            
                                    // Add other checkboxes and progress bar
                                    JProgressBar progressBar = new JProgressBar(0, 1000);
                                    progressBar.setValue(0);
                                    progressBar.setBounds(40, 270, 220, 15);
                                    progressBar.setForeground(Color.BLUE.darker());
                                   JLabel l00=new JLabel("Learning the language");
                                   l00.setBounds(40,240,150,30);
                                   
                                   // adding contents to panel
                                    panel.add(l00);
                                    panel.add(testCheckbox);
                                    panel.add(bookCheckbox);
                                    panel.add(exerciseCheckbox);
                                    panel.add(speakingCheckbox);
                                    panel.add(writingCheckbox);
                                    panel.add(progressBar);
                                   panel.setLayout(null);
                                   
                                   if (testCheckbox.isSelected()) 
                                  {
                                      progressBar.setValue(progressBar.getValue() + 200);
                                    }
                                    if (bookCheckbox.isSelected()) 
                                  {
                                      progressBar.setValue(progressBar.getValue() + 200);
                                    }
                                    if (exerciseCheckbox.isSelected()) 
                                  {
                                      progressBar.setValue(progressBar.getValue() + 200);
                                    }
                                    if (speakingCheckbox.isSelected()) 
                                  {
                                      progressBar.setValue(progressBar.getValue() + 200);
                                    }
                                    if (writingCheckbox.isSelected()) 
                                  {
                                      progressBar.setValue(progressBar.getValue() + 200);
                                    }
                                  
                                   // creating action listener for every dashboard checkbox
                                    testCheckbox.addActionListener(new ActionListener() 
                                    {
                                        public void actionPerformed(ActionEvent e) 
                                        {
                                            if (testCheckbox.isSelected()) 
                                            {
                                                // if checkbox is selected progressbar shows progress
                                                progressBar.setValue(progressBar.getValue() + 200);
                                                c1.sendMessage("11");
                                                try 
                                               {
                                                        String googleDriveFileUrl = "http://localhost:8080/Lang_Learning/test";
                                                        Desktop.getDesktop().browse(new URI(googleDriveFileUrl));
                                                } 
                                                catch (IOException | URISyntaxException exc) 
                                                {
                                                    exc.printStackTrace();
                                                }
                                            }
                                            else
                                            {
                                                // deselected then progress bar comes down
                                            progressBar.setValue(progressBar.getValue() - 200);
                                            c1.sendMessage("10");
                                            
                                            }
                                            if(progressBar.getValue()==1000)
                                            {
                                                l00.setText("Language learnt");
                                            }
                                            else
                                            {
                                                l00.setText("Language learning");
                                            }
                                        }
                                    });
                            
                                    bookCheckbox.addActionListener(new ActionListener() 
                                    {
                                        public void actionPerformed(ActionEvent e) 
                                        {
                                            if (bookCheckbox.isSelected()) 
                                            {
                                                progressBar.setValue(progressBar.getValue() + 200);
                                                c1.sendMessage("21");
                                                 try 
                                               {
                                                        String googleDriveFileUrl = "https://drive.google.com/drive/folders/1Yo1FB4D-N6nY84-nHBupF0MsW_PNPxv_";
                                                        Desktop.getDesktop().browse(new URI(googleDriveFileUrl));
                                                } 
                                                catch (IOException | URISyntaxException exc) 
                                                {
                                                    exc.printStackTrace();
                                                }
                                            }
                                            else
                                            {
                                            progressBar.setValue(progressBar.getValue() - 200);
                                            c1.sendMessage("20");
                                        }
                                            if(progressBar.getValue()==1000)
                                            {
                                                l00.setText("Language learnt");
                                            }
                                            else
                                            {
                                                l00.setText("Language learning");
                                            }
                                        }
                                    });
                            
                                    exerciseCheckbox.addActionListener(new ActionListener() 
                                    {
                                        public void actionPerformed(ActionEvent e) 
                                        {
                                            if (exerciseCheckbox.isSelected()) 
                                            {
                                                progressBar.setValue(progressBar.getValue() + 200);
                                                c1.sendMessage("31");
                                                 try 
                                               {
                                                        String googleDriveFileUrl = "https://drive.google.com/drive/folders/1l68qUHy1K69q53IBmAkUIukPcp9nfM5w";
                                                        Desktop.getDesktop().browse(new URI(googleDriveFileUrl));
                                                } 
                                                catch (IOException | URISyntaxException exc) 
                                                {
                                                    exc.printStackTrace();
                                                }
                                            }
                                            else
                                            {
                                            progressBar.setValue(progressBar.getValue() - 200);
                                            c1.sendMessage("30");
                                        }
                                            if(progressBar.getValue()==1000)
                                            {
                                                l00.setText("Language learnt");
                                            }
                                            else
                                            {
                                                l00.setText("Language learning");
                                            }
                                        }
                                    });
                            
                                    speakingCheckbox.addActionListener(new ActionListener() 
                                    {
                                        public void actionPerformed(ActionEvent e) 
                                        {
                                            if (speakingCheckbox.isSelected()) 
                                            {
                                                progressBar.setValue(progressBar.getValue() + 200);
                                                c1.sendMessage("41");
                                               try 
                                               {
                                                        String googleDriveFileUrl = "https://www.youtube.com/watch?v=2rF1TEnkNfU&list=PPSV";
                                                        Desktop.getDesktop().browse(new URI(googleDriveFileUrl));
                                                } 
                                                catch (IOException | URISyntaxException exc) 
                                                {
                                                    exc.printStackTrace();
                                                }
                                            }
                                            else
                                            {
                                                progressBar.setValue(progressBar.getValue() - 200);
                                                c1.sendMessage("40");
                                            }
                                            if(progressBar.getValue()==1000)
                                            {
                                                l00.setText("Language learnt");
                                            }
                                            else
                                            {
                                                l00.setText("Language learning");
                                            }
                                        }
                                    });
                            
                                    writingCheckbox.addActionListener(new ActionListener() 
                                    {
                                        public void actionPerformed(ActionEvent e) 
                                        {
                                            if (writingCheckbox.isSelected()) 
                                            {
                                                progressBar.setValue(progressBar.getValue() + 200);
                                                c1.sendMessage("51");
                                                 try 
                                               {
                                                        String googleDriveFileUrl = "https://drive.google.com/drive/folders/1Ln1DG9X-HWOTCMuXZnDa2PWlAIy16e7e";
                                                        Desktop.getDesktop().browse(new URI(googleDriveFileUrl));
                                                } 
                                                catch (IOException | URISyntaxException exc) 
                                                {
                                                    exc.printStackTrace();
                                                }
                                            }
                                            else
                                            {
                                                progressBar.setValue(progressBar.getValue() - 200);
                                                c1.sendMessage("50");
                                            }
                                            if(progressBar.getValue()==1000)
                                            {
                                                // if progress bar fills completely then language is indeed learnt
                                                l00.setText("Language learnt");
                                            }
                                            else
                                            {
                                                // else always the user is learning language
                                                l00.setText("Language learning");
                                            }
                                        }
                                    });
                                    
                       // Add panel to the frame
                    newFrame.add(panel);
                    newFrame.add(l0);
                    newFrame.setLayout(null);
                    newFrame.setVisible(true);// Display the main application frame
                } 
                else 
                {
                     // Login failed
                    label.setText("Login Failed");
                }
            }
        });
    }
}
