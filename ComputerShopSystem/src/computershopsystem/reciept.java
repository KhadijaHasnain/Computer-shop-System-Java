package computershopsystem;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class reciept {

    public static final Font MY_FONT = new Font("Times New Roman", Font.BOLD, 20);
    public static final Font MY_FONT2 = new Font("Times New Roman", Font.PLAIN, 20);

    int TOTAL = orders.total;
    int DISC = orders.discount;
    int PAYABLE = TOTAL - DISC;
    JTable TABLE = orders.table;
    DefaultTableModel MODEL = orders.model;
    int BALANCE = 0;
    int CASH=0;
    String pName;
    String QTY;
    String PRICE;
    

    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JPanel titlePanel = new JPanel();

    JLabel label1 = new JLabel("PAYMENT DETAILS");
    JLabel label2 = new JLabel("Amount");
    JLabel label3 = new JLabel("Discount");
    JLabel label4 = new JLabel("Payable");
    JLabel label5 = new JLabel("Cash");
    JLabel label6 = new JLabel("Balance");

    JTextField amount = new JTextField();
    JTextField discount = new JTextField("Discount = 5% on 10,000 and above");
    JTextField payable = new JTextField();
    JTextField cash = new JTextField();
    JTextField balance = new JTextField();

    JTextArea reciept = new JTextArea();
    JScrollPane pane = new JScrollPane(reciept);
    JButton generate = new JButton("Generate Reciept");
    JButton print = new JButton("Print Reciept");

    reciept() {
        LocalDateTime date = LocalDateTime.now();
        
        frame.setBounds(350, 150, 800, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);

        panel.setBounds(0, 0, 800, 500);
        panel.setBackground(Color.darkGray);
        panel.setLayout(null);

        titlePanel.setBounds(0, 0, 800, 60);
        titlePanel.setBackground(Color.gray);
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 15));

        label2.setBounds(20, 70, 200, 30);
        label2.setForeground(Color.white);
        label2.setFont(MY_FONT);

        amount.setBounds(200, 70, 200, 30);
        amount.setFont(MY_FONT2);
        amount.setEditable(false);

        label3.setBounds(20, 120, 200, 30);
        label3.setForeground(Color.white);
        label3.setFont(MY_FONT);

        discount.setBounds(200, 120, 200, 30);
        discount.setFont(MY_FONT2);
        discount.setEditable(false);

        label4.setBounds(20, 170, 200, 30);
        label4.setForeground(Color.white);
        label4.setFont(MY_FONT);

        payable.setBounds(200, 170, 200, 30);
        payable.setFont(MY_FONT2);
        payable.setEditable(false);

        label5.setBounds(20, 220, 200, 30);
        label5.setForeground(Color.white);
        label5.setFont(MY_FONT);

        cash.setBounds(200, 220, 200, 30);
        cash.setFont(MY_FONT2);

        label6.setBounds(20, 270, 200, 30);
        label6.setForeground(Color.white);
        label6.setFont(MY_FONT);

        balance.setBounds(200, 270, 200, 30);
        balance.setFont(MY_FONT2);
        balance.setEditable(false);

        generate.setBounds(150, 330, 150, 35);
        print.setBounds(150, 380, 150, 35);
        print.setVisible(false);

        label1.setFont(MY_FONT);
        label1.setForeground(Color.white);

        pane.setBounds(430, 60, 360, 430);
        reciept.setText("===============================================" + "\n"
                + "\t" + "TECH by Farooq Ali" + "\n"
                + "Contact No:\t03019033214" + "\n"
                + "Address:\tBharakahu, Islamabad, Pakistan" + "\n"
                + "DATED: \t"+date+"\n"
                + "===============================================" + "\n"
                + "Product Name" + "\t" + "QTY" + "\t" + "Price Per Unit" +"\t"+"Total" + "\n");

        amount.setText(String.valueOf(TOTAL));
        discount.setText(String.valueOf(DISC));
        payable.setText(String.valueOf(PAYABLE));
        cash.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                CASH = Integer.parseInt(cash.getText());
                BALANCE = PAYABLE - CASH;
                balance.setText(String.valueOf(BALANCE));
            }
        });

        generate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
         reciept.setText("===============================================" + "\n"
                + "\t" + "TECH by Farooq Ali" + "\n"
                + "Contact No:\t03019033214" + "\n"
                + "Address:\tBharakahu, Islamabad, Pakistan" + "\n"
                + "DATED: "+date+"\n"
                + "===============================================" + "\n"
                + "Product Name" + "\t" + "QTY" + "\t" + "Price Per Unit" +"\t"+"Total" + "\n");
                    
                      if (BALANCE<0 && !balance.getText().equals("")) {
                    int subTotal = 0;
                    for (int i = 0; i < TABLE.getRowCount(); i++) {
                        pName = String.valueOf(MODEL.getValueAt(i, 1));
                        QTY = String.valueOf(MODEL.getValueAt(i, 6));
                        PRICE = String.valueOf(MODEL.getValueAt(i, 4));
                        double totPRICE = Integer.parseInt(QTY) * Integer.parseInt(PRICE);
                        subTotal += totPRICE;
                        reciept.append(pName+"\n");
                        reciept.append("\t"+QTY);
                        reciept.append("\t"+PRICE);
                        reciept.append("\t"+totPRICE+"\n");
                    }
                   reciept.append("\n"+"GROSS TOTAL:"+"\t\t\t"+TOTAL+"\n"
                           + "DISCOUNT:"+"\t\t\t"+(-DISC)+"\n"
                            + "SUBTOTAL:" + "\t\t\t" + PAYABLE + "\n"
                            + "CASH"+"\t\t\t"+CASH+"\n"
                            + "BALANCE" + "\t\t\t"+ BALANCE + "\n");
                   reciept.append("===============================================" + "\n"
                            + "DEVELOPED BY FAROOQ ALI");
                    print.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "INPUT BALANCE, OR BALANCE IS POSITIVE");
                }
            }
        });
        
        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               try{
                boolean print = reciept.print();
                if(print)
                    JOptionPane.showMessageDialog(null, "Print Done");
               }
               catch(Exception ex){
                   JOptionPane.showMessageDialog(null, ex.getMessage());
               }
                
            }
        });
        
        

        for (int i = 0; i < TABLE.getRowCount(); i++) {
            pName = String.valueOf(MODEL.getValueAt(i, 1));
        }

        titlePanel.add(label1);

        panel.add(titlePanel);
        panel.add(label1);
        panel.add(label2);
        panel.add(amount);
        panel.add(label3);
        panel.add(discount);
        panel.add(label4);
        panel.add(payable);
        panel.add(label5);
        panel.add(cash);
        panel.add(label6);
        panel.add(balance);
        panel.add(pane);
        panel.add(generate);
        panel.add(print);
        

        panel.add(label2);

        frame.add(panel);
        frame.setVisible(true);


        
    }

}
                
