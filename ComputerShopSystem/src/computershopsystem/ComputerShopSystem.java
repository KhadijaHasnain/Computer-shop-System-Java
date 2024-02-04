package computershopsystem;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.sql.*;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.apache.commons.lang3.math.NumberUtils;

public class ComputerShopSystem {

    JFrame frame = new JFrame();
    JPanel contPanel = new JPanel();
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel titlePanel = new JPanel();
    JLabel title = new JLabel("Admin Login");
    JLabel label1 = new JLabel("Enter Your User Name");
    JLabel label2 = new JLabel("Enter Your Password");
    JLabel label3 = new JLabel("Forgot Password?");
    JTextField userName = new JTextField();
    JPasswordField password = new JPasswordField();
    JButton login = new JButton("LOG IN");
    JButton getPass = new JButton("GET PASS");
    JButton back = new JButton("BACK");

    CardLayout card = new CardLayout();
    public static final Font MY_FONT = new Font("Times New Roman", Font.BOLD, 20);

    ComputerShopSystem() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setBounds(450, 200, 450, 450);
        frame.setResizable(false);

        //contPanel
        //PANEL 1
        contPanel.setBackground(Color.red);
        contPanel.setBounds(0, 0, 450, 450);
        contPanel.setLayout(card);
        contPanel.add(panel1, "1");
        contPanel.add(panel2, "2");

        //PANEL 1
        panel1.setBackground(Color.darkGray);
        panel1.setLayout(null);

        //PANEL 2
        panel2.setBackground(Color.darkGray);
        panel2.setLayout(null);

        //TITLE PANEL
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 11));
        titlePanel.setBounds(0, 0, 450, 70);
        titlePanel.setBackground(new Color(67, 107, 160));

        //LABEL TITLE
        title.setFont(new Font("Times New Roman", Font.BOLD, 36));
        title.setForeground(Color.white);

        //LABEL 1
        label1.setBounds(120, 120, 200, 30);
        label1.setForeground(Color.white);
        label1.setFont(MY_FONT);

        //LABEL 2
        label2.setBounds(120, 210, 200, 30);
        label2.setForeground(Color.white);
        label2.setFont(MY_FONT);

        //LABEL 3
        label3.setBounds(145, 370, 250, 20);
        label3.setForeground(Color.white);
        label3.setFont(MY_FONT);
        label3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        //TEXTFIELD : USERNAME
        userName.setBounds(120, 160, 200, 30);

        //PASSWORD FIELD : PASSWORD
        password.setBounds(120, 250, 200, 30);

        //BUTTON : LOGIN
        login.setBounds(160, 310, 120, 40);
        login.setFont(MY_FONT);
        login.setForeground(Color.black);
        login.setBackground(new Color(200, 202, 204));
        login.setFocusable(false);

        //BUTTON : getPass
        getPass.setBounds(145, 210, 150, 40);
        getPass.setFont(MY_FONT);
        getPass.setForeground(Color.black);
        getPass.setBackground(new Color(200, 202, 204));
        getPass.setFocusable(false);

        //BUTTON : BACK
        back.setBounds(15, 80, 80, 40);
        back.setBackground(Color.white);
        back.setFont(new Font("Times New Roman", Font.BOLD, 16));

        //PANEL 1 ADD INTO
        panel1.add(label1);
        panel1.add(userName);
        panel1.add(label2);
        panel1.add(password);
        panel1.add(login);
        panel1.add(label3);
        panel1.add(title);
        titlePanel.add(title);
        panel1.add(titlePanel);

        card.show(contPanel, "1");

        //FRAME ADD INTO
        frame.add(contPanel);

        //EVENT LISTENERS
        getPass.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int random = (int) (Math.random() * (900000 - 100000 + 1) + 100000);
                Connection conn = null;
                PreparedStatement pst = null;
                ResultSet rs = null;
                try {
                    Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                    conn = DriverManager.getConnection("jdbc:ucanaccess://E:\\ProjectDatabase.accdb");
                    String query1 = "update  loginInfo set otp='" + random + "' where username='" + userName.getText() + "'";
                    pst = conn.prepareStatement(query1);
                    int a = pst.executeUpdate();
                    if (a > 0) {
                        System.out.println("OTP SET");
                        String query2 = "Select * from loginInfo WHERE username='" + userName.getText() + "'";
                        pst = conn.prepareStatement(query2);
                        rs = pst.executeQuery();
                        if (rs.next()) {
                            String pass = rs.getString("password");
                            String otp = rs.getString("otp");
                            System.out.println(otp);
                            String otpField = JOptionPane.showInputDialog(null, "Enter otp sent to your email, in your case DATABASE");
                            if (!NumberUtils.isDigits(otpField)) {
                                JOptionPane.showMessageDialog(null, "Please enter digits");
                            } else if (Integer.parseInt(otpField) != random) {
                                JOptionPane.showMessageDialog(null, "Wrong Otp");
                            } else {
                                JOptionPane.showMessageDialog(null, "Your Password is " + pass);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "invalid username");
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        label3.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                panel2.add(label1);
                panel2.add(userName);
                panel2.add(titlePanel);
                panel2.add(getPass);
                panel2.add(back);
                card.show(contPanel, "2");

            }
        });
        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Connection conn = null;
                PreparedStatement pst = null;
                ResultSet rs = null;
                try {
                    Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                    conn = DriverManager.getConnection("jdbc:ucanaccess://E:\\ProjectDatabase.accdb");
                    String query = "Select * from loginInfo where username='" + userName.getText() + "' and password='" + password.getText() + "'";
                    pst = conn.prepareStatement(query);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "Login Successfull");
                        frame.dispose();
                        new Home();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid username or password");
                    }
                } catch (SQLException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Exception Error");
                    System.out.println(ex.getMessage());
                }
            }
        });

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                panel1.add(label1);
                panel1.add(userName);
                panel1.add(titlePanel);
                card.show(contPanel, "1");
            }

        });

        frame.setVisible(true);
    }
}
