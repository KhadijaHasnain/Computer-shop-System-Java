package computershopsystem;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;

public class Home {

    public static final Font MY_FONT = new Font("Ubuntu Mono", Font.BOLD, 24);

    JFrame frame = new JFrame();
    JPanel topPanel = new JPanel();
    JPanel contPanel = new JPanel();
    JPanel addProd = new JPanel();
    JPanel stockDetails = new JPanel();
    JPanel removeProd = new JPanel();
    JLabel backgroundImage = new JLabel(new ImageIcon(getClass().getResource("/image/computerImage.jpg")));
    CardLayout card = new CardLayout();

    JButton[] b = new JButton[10];

    Home() {
        //Frame
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setBounds(0, 0, screensize.width, screensize.height);

        //BACKGROUND IMAGE
        backgroundImage.setBounds(0, 0, screensize.width, screensize.height);

        //PANEL : TOP
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 16));
        topPanel.setBounds(frame.getWidth() / 2 - 500, 100, 1000, 70);
        topPanel.setBackground(new Color(67, 107, 149));

        //PANEL : CONTAINER
        contPanel.setBounds(topPanel.getLocation().x, 170, 1000, 700);
        contPanel.setBackground(Color.black);
        contPanel.setLayout(card);
        contPanel.add(new addProduct().panel, "0");
        contPanel.add(new stockDetails().panel, "1");
        contPanel.add(new removeProduct().panel, "2");
        contPanel.add(new orders().panel, "3");

        //BUTTONS: 
        b[0] = new JButton("ADD PRODUCT");
        b[1] = new JButton("STOCK DETAILS");
        b[2] = new JButton("SEARCH/REMOVE PRODUCT");
        b[3] = new JButton("SALES");
        b[4] = new JButton("LOG OUT");
        for (int i = 0; i <= 4; i++) {
            b[i].setFocusable(false);
            b[i].setBackground(Color.darkGray);
            b[i].setForeground(Color.white);
            b[i].setFont(MY_FONT);
            topPanel.add(b[i]);
        }

        //EVENT LISTENERS
        b[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i <= 4; i++) {
                    b[i].setBackground(Color.darkGray);
                    b[i].setForeground(Color.white);
                }
                b[0].setBackground(new Color(222, 224, 227));
                b[0].setForeground(Color.black);
                contPanel.setVisible(false);
                card.show(contPanel, "0");
                contPanel.setVisible(true);
            }
        });

        b[1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i <= 4; i++) {
                    b[i].setBackground(Color.darkGray);
                    b[i].setForeground(Color.white);
                }
                b[1].setBackground(new Color(222, 224, 227));
                b[1].setForeground(Color.black);
                contPanel.setVisible(false);
                card.show(contPanel, "1");
                contPanel.setVisible(true);
            }
        });

        b[2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i <= 4; i++) {
                    b[i].setBackground(Color.darkGray);
                    b[i].setForeground(Color.white);
                }
                b[2].setBackground(new Color(222, 224, 227));
                b[2].setForeground(Color.black);
                contPanel.setVisible(false);
                card.show(contPanel, "2");
                contPanel.setVisible(true);
            }
        });

        b[3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i <= 4; i++) {
                    b[i].setBackground(Color.darkGray);
                    b[i].setForeground(Color.white);
                }
                b[3].setBackground(new Color(222, 224, 227));
                b[3].setForeground(Color.black);
                contPanel.setVisible(false);
                card.show(contPanel, "3");
                contPanel.setVisible(true);
            }
        });
        b[4].addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Do you want to log out", "Log Out", JOptionPane.YES_NO_OPTION);
                if (confirm == 0) {
                    frame.dispose();
                    new ComputerShopSystem();
                }
            }
        });

        frame.add(topPanel);
        frame.add(contPanel);
        b[0].setBackground(new Color(222, 224, 227));
        b[0].setForeground(Color.black);
        card.show(contPanel, "0");
        frame.add(backgroundImage);
        frame.setVisible(true);
    }

}
