package com.prepare.panel;

import javax.swing.*;
import java.awt.*;

public class test {
    public static void main(String[] args) {
        new PanelDemo();
    }
}

class PanelDemo extends JFrame
{
    public PanelDemo()
    {
        Container container = getContentPane();
        container.setLayout(new GridLayout(2,1,5,5)); // r,c, gap, gap

        JPanel panel1 = new JPanel(new GridLayout(1,3));
        JPanel panel2 = new JPanel(new GridLayout(1,2));


        panel1.add(new JButton("1"));
        panel1.add(new JButton("1"));
        panel1.add(new JButton("1"));
        panel2.add(new JButton("2"));
        panel2.add(new JButton("2"));

        container.add(panel1);
        container.add(panel2);

        this.setVisible(true);
        this.setBounds(400,400,500,500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    }
}
