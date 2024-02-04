package computershopsystem;

import static computershopsystem.stockDetails.MY_FONT;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class orders {

    static int total = 0;
    static int discount = 0;
    JPanel panel = new JPanel();
    JTextField tx1 = new JTextField();
    JTextField tx2 = new JTextField();
    JLabel label1 = new JLabel("Product Id");
    JLabel label2 = new JLabel("Product Quantity");
    JButton b1 = new JButton("Add Order");
    JButton b2 = new JButton("End Order & Generate Reciept");
    JButton b3 = new JButton("Reset Table");
    static JTable table = new JTable();
    static DefaultTableModel model = new DefaultTableModel();
    JScrollPane pane = new JScrollPane(table);
    Object columns[] = {"Product ID", "Name", "Category", "Company", "Price", "Description", "Quantity"};

    public static final Font MY_FONT = new Font("Times New Roman", Font.BOLD, 24);
    public static final Font MY_FONT2 = new Font("Times New Roman", Font.PLAIN, 20);

    public orders() {
        panel.setBackground(new Color(0.f, 0.f, 0.f, 0.5f));
        panel.setLayout(null);

        //Label 1
        label1.setBounds(220, 60, 250, 30);
        label1.setFont(MY_FONT);
        label1.setForeground(Color.white);

        //TEXTFIELD 1
        tx1.setBounds(220, 110, 200, 30);
        tx1.setFont(MY_FONT2);
        tx1.setForeground(Color.black);

        //Label 2
        label2.setBounds(580, 60, 250, 30);
        label2.setFont(MY_FONT);
        label2.setForeground(Color.white);

        //TEXTFIELD 2
        tx2.setBounds(580, 110, 200, 30);
        tx2.setFont(MY_FONT2);
        tx2.setForeground(Color.black);

        //BUTTON 1
        b1.setBounds(400, 160, 220, 40);
        b1.setFont(MY_FONT);
        b1.setBackground(new Color(222, 224, 227));

        //BUTTON 2
        b2.setBounds(330, 450, 360, 40);
        b2.setFont(MY_FONT);
        b2.setBackground(new Color(222, 224, 227));

        //BUTTON 3
        b3.setBounds(20, 450, 200, 40);
        b3.setFont(MY_FONT);
        b3.setBackground(new Color(222, 224, 227));

        //Table + PAnE
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.setRowHeight(20);
        table.getTableHeader().setFont(MY_FONT2);
        table.getTableHeader().setBackground(Color.darkGray);
        table.getTableHeader().setForeground(Color.white);
        pane.setBounds(0, 220, 1000, 200);

        //Action Listeners
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (tx1.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please Enter Product ID");
                } else if (tx2.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please Enter Product Quantity");
                } else {
                    Connection con = null;
                    PreparedStatement pst = null;
                    ResultSet rs = null;
                    try {
                        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                        con = DriverManager.getConnection("jdbc:ucanaccess://E:\\ProjectDatabase.accdb");
                        String query = "select * from stockDetails where prodId='" + tx1.getText() + "'";
                        pst = con.prepareStatement(query);
                        rs = pst.executeQuery();

                        if (rs.next()) {
                            String id = rs.getString("prodId");
                            String name = rs.getString("prodName");
                            String category = rs.getString("prodCategory");
                            String company = rs.getString("prodCompany");
                            int price = Integer.parseInt(rs.getString("prodPrice"));
                            String desc = rs.getString("prodDescription");
                            int quantity = Integer.parseInt(rs.getString("prodQuantity"));
                            int updQuantity = Integer.parseInt(rs.getString("prodQuantity"));
                            updQuantity -= Integer.parseInt(tx2.getText());
                            Object[] data = {id, name, category, company, price, desc, tx2.getText()};
                            if (updQuantity >= 0) {
                                String query2 = "update stockDetails set prodQuantity='" + updQuantity + "' where prodId='" + tx1.getText() + "'";
                                pst = con.prepareStatement(query2);
                                int a = pst.executeUpdate();
                                model.addRow(data);
                                JOptionPane.showMessageDialog(null, "Order Added");
                            } else {
                                JOptionPane.showMessageDialog(null, "OOPS, STOCK LEFT = " + quantity);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Please Enter Valid Product ID");
                        }

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }
            }
        });

        b2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                for (int i = 0; i < table.getRowCount(); i++) {
                    int amount = Integer.parseInt(table.getValueAt(i, 4) + "");
                    int qty = Integer.parseInt(table.getValueAt(i, 6) + "");

                    total += amount * qty;
                }
                if (total >= 10000) {
                    discount = total * 5 / 100;
                }

                try {
                    new reciept();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }

            }

        });

        panel.add(label1);
        panel.add(tx1);
        panel.add(label2);
        panel.add(tx2);
        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(pane);

    }

}
