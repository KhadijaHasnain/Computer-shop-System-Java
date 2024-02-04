package computershopsystem;

import java.awt.Color;
import java.awt.Font;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import org.apache.commons.lang3.math.NumberUtils;

public class addProduct {

    public static final Font MY_FONT = new Font("Times New Roman", Font.BOLD, 22);
    public static final Font MY_FONT2 = new Font("Times New Roman", Font.PLAIN, 18);
    String category[] = {"Select Category", "Laptops", "Desktops", "Computer Accessories", "MultiMedia", "Other"};
    String Brand[] = {"Select Brand", "Samsung", "Apple", "Dell", "HP", "Acer", "Lenovo", "Other"};

    JPanel panel = new JPanel();
    JLabel label1 = new JLabel("Product Name:");
    JLabel label2 = new JLabel("Product Category");
    JLabel label3 = new JLabel("Product Brand");
    JLabel label4 = new JLabel("Product Price");
    JLabel label5 = new JLabel("Product Description");
    JLabel label6 = new JLabel("Product Quantity");

    JTextField tx1 = new JTextField("E.g : Keyboard");
    JComboBox tx2 = new JComboBox(category);
    JComboBox tx3 = new JComboBox(Brand);
    JTextField tx4 = new JTextField("E.g : 2000");
    JTextArea tx5 = new JTextArea("E.g : Lightening Keyboard, with anti-ghosting mode");
    JTextField tx6 = new JTextField("E.g : 20");
    JButton b1 = new JButton("Add Product");
    JButton b2 = new JButton("RESET");

    public addProduct() {
        panel.setBackground(new Color(0.f, 0.f, 0.f, 0.6f));
        panel.setLayout(null);

        //Label1
        label1.setBounds(200, 30, 200, 30);
        label1.setFont(MY_FONT);
        label1.setForeground(Color.white);

        //Tx1
        tx1.setBounds(410, 30, 280, 30);
        tx1.setFont(MY_FONT2);

        //Label2
        label2.setBounds(200, 90, 200, 30);
        label2.setFont(MY_FONT);
        label2.setForeground(Color.white);

        //Tx3
        tx2.setBounds(410, 90, 280, 30);
        tx2.setFont(MY_FONT2);

        //Label4
        label3.setBounds(200, 150, 200, 30);
        label3.setFont(MY_FONT);
        label3.setForeground(Color.white);

        //Tx4
        tx3.setBounds(410, 150, 280, 30);
        tx3.setFont(MY_FONT2);

        //Label5
        label4.setBounds(200, 210, 200, 30);
        label4.setFont(MY_FONT);
        label4.setForeground(Color.white);

        //Tx5
        tx4.setBounds(410, 210, 280, 30);
        tx4.setFont(MY_FONT2);

        //Label5
        label5.setBounds(200, 280, 200, 30);
        label5.setFont(MY_FONT);
        label5.setForeground(Color.white);

        //Tx5
        tx5.setBounds(410, 280, 280, 60);
        tx5.setFont(MY_FONT2);

        //Label6
        label6.setBounds(200, 360, 200, 30);
        label6.setFont(MY_FONT);
        label6.setForeground(Color.white);

        //Tx6
        tx6.setBounds(410, 360, 280, 30);
        tx6.setFont(MY_FONT2);

        //BUTTON : ADD PRODUCTS
        b1.setBounds(460, 420, 180, 40);
        b1.setFont(MY_FONT);
        b1.setBackground(Color.darkGray);
        b1.setForeground(Color.white);

        //BUTTON : RESET 
        b2.setBounds(460, 470, 180, 40);
        b2.setFont(MY_FONT);
        b2.setBackground(Color.darkGray);
        b2.setForeground(Color.white);

        panel.add(label1);
        panel.add(tx1);
        panel.add(label2);
        panel.add(tx2);
        panel.add(label3);
        panel.add(tx3);
        panel.add(label4);
        panel.add(tx4);
        panel.add(label5);
        panel.add(tx5);
        panel.add(label6);
        panel.add(tx6);
        panel.add(b1);
        panel.add(b2);

        //EVENT LISTENERS
        tx1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (tx1.getText().equals("E.g : Keyboard")) {
                    tx1.setText("");
                }
            }
        });

        tx4.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (tx4.getText().equals("E.g : 2000")) {
                    tx4.setText("");
                }
            }
        });
        tx5.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (tx5.getText().equals("E.g : Lightening Keyboard, with anti-ghosting mode")) {
                    tx5.setText("");
                }
            }
        });

        tx6.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (tx6.getText().equals("E.g : 20")) {
                    tx6.setText("");
                }
            }
        });

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (tx1.getText().equals("") || tx4.getText().equals("") || tx5.getText().equals("") || tx6.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please Enter All Fields");
                } else if (tx3.getSelectedItem() == "Select Category" || tx3.getSelectedItem() == "Select Brand") {
                    JOptionPane.showMessageDialog(null, "Please Select Category And Brand");
                } else if (!NumberUtils.isDigits(tx4.getText()) || !NumberUtils.isDigits(tx6.getText())) {
                    JOptionPane.showMessageDialog(null, "Please Enter A Number in \"Product Price\" and \"Product Quantity\" Field");
                } else {
                    //MY SQL CONNECTION
                    Connection con = null;
                    PreparedStatement pst = null;
                    ResultSet rs = null;
                    try {
                        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                        con = DriverManager.getConnection("jdbc:ucanaccess://E:\\ProjectDatabase.accdb");

                        String query1 = "select * from stockDetails where prodName='" + tx1.getText() + "' and prodCategory='" + tx2.getSelectedItem().toString() + "' and prodCompany='" + tx3.getSelectedItem().toString() + "' and prodPrice='" + tx4.getText() + "' and prodDescription='" + tx5.getText() + "' and prodQuantity='" + tx6.getText() + "'";
                        pst = con.prepareStatement(query1);
                        rs = pst.executeQuery();
                        if (rs.next()) {
                            JOptionPane.showMessageDialog(null, "Product already available");
                        } else {
                            String query2 = "Insert into stockDetails (prodName, prodCategory, prodCompany, prodPrice, prodDescription, prodQuantity) values ('" + tx1.getText() + "', '" + tx2.getSelectedItem().toString() + "', '" + tx3.getSelectedItem().toString() + "', '" + tx4.getText() + "', '" + tx5.getText() + "', '" + tx6.getText() + "')";
                            System.out.println("SQL quERY EXECUTED");
                            pst = con.prepareStatement(query2);
                            int a = pst.executeUpdate();
                            if (a > 0) {
                                JOptionPane.showMessageDialog(null, "Product Added");
                            }
                        }

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }

                }
            }

        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                tx1.setText("");
                tx2.setSelectedIndex(0);
                tx3.setSelectedIndex(0);
                tx4.setText("");
                tx5.setText("");
                tx6.setText("");

            }
        });

    }

}
