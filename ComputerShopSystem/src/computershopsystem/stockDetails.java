package computershopsystem;

import java.awt.Color;
import java.sql.*;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class stockDetails {

    public static final Font MY_FONT = new Font("Times New Roman", Font.PLAIN, 18);
    public static final Font MY_FONT2 = new Font("Times New Roman", Font.BOLD, 24);
    JPanel panel = new JPanel();
    Object columns[] = {"Product ID", "Name", "Category", "Company", "Price", "Description", "Quantity"};
    Object data[] = {"1", "Hello", "123", "123", "123", "123"};
    DefaultTableModel model = new DefaultTableModel();
    JTable table = new JTable();
    JScrollPane pane = new JScrollPane(table);
    TableColumnModel tc = table.getColumnModel();
    JButton submit = new JButton("LOAD STOCK FROM DATABASE");

    public stockDetails() {

        panel.setBackground(new Color(0.f, 0.f, 0.f, 0.6f));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        pane.setPreferredSize(new Dimension(1000, 460));
        model.setColumnIdentifiers(columns);
        table.setRowHeight(20);
        table.setModel(model);
        table.setFont(MY_FONT);
        table.getTableHeader().setFont(MY_FONT);
        table.getTableHeader().setBackground(Color.darkGray);
        table.getTableHeader().setForeground(Color.white);

        //BUTTON : SUBMIT
        submit.setFocusable(false);
        submit.setBackground(Color.darkGray);
        submit.setForeground(Color.white);
        submit.setFont(MY_FONT2);

        //EVENT LISTENERS
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);
                Connection con = null;
                PreparedStatement pst = null;
                ResultSet rs = null;
                try {
                    Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                    con = DriverManager.getConnection("jdbc:ucanaccess://E:\\ProjectDatabase.accdb");

                    String query = "Select * from stockDetails";
                    pst = con.prepareStatement(query);
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        String id = rs.getString("prodId");
                        String name = rs.getString("prodName");
                        String category = rs.getString("prodCategory");
                        String company = rs.getString("prodCompany");
                        int price = Integer.parseInt(rs.getString("prodPrice"));
                        String desc = rs.getString("prodDescription");
                        int quantity = Integer.parseInt(rs.getString("prodQuantity"));
                        if (quantity >= 0) {
                            Object[] data = {id, name, category, company, price, desc, quantity};
                            model.addRow(data);
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }

            }

        });

        panel.add(submit);
        panel.add(pane);

    }

}
